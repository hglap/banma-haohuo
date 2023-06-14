package com.ebanma.cloud.post.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ebanma.cloud.common.dto.ResultGenerator;
import com.ebanma.cloud.post.dao.PostInfoMapper;
import com.ebanma.cloud.post.model.UserInfo;
import com.ebanma.cloud.post.model.dto.ImgDto;
import com.ebanma.cloud.post.model.dto.PostSearchDto;
import com.ebanma.cloud.post.model.po.PostInfoPO;
import com.ebanma.cloud.post.model.po.PostLikePO;
import com.ebanma.cloud.post.model.vo.PostInfoVO;
import com.ebanma.cloud.post.service.PostInfoService;
import com.ebanma.cloud.post.service.PostLikeService;
import com.ebanma.cloud.post.service.PostReadService;
import com.ebanma.cloud.user.api.openfeign.UserServiceFeign;
import com.github.pagehelper.PageInfo;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.Resource;
import java.io.IOException;
import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
* @author banma-0163
* @description 针对表【post_info(推荐帖信息表)】的数据库操作Service实现
* @createDate 2023-06-06 19:10:57
*/
@Service
public class PostInfoServiceImpl extends ServiceImpl<PostInfoMapper, PostInfoPO>
    implements PostInfoService{

    @Resource
    private FastFileStorageClient fastFileStorageClient;

    @Resource
    private PostLikeService postLikeService;

    @Resource
    private PostReadService postReadService;

    @Resource
    private UserServiceFeign userServiceFeign;

    @Resource
    private RedisTemplate<String,Long> redisTemplate;

    @Resource
    private PostInfoMapper mapper;

//    @Resource
//    private MongoTemplate mongoTemplate;

//    private final String FASTDFSSERVERIMAGE = "http://124.221.94.214:8888/";
    // 本地版
    private final String FASTDFSSERVERIMAGE = "http://30.16.95.140:8080/";

    private ThreadLocal<Long> USERID = new ThreadLocal<Long>();

    private String SEPARATOR = ";";

    @Override
    public PostInfoVO getByPostId(PostInfoVO postInfoVO) {
        USERID.set(postInfoVO.getUserId());
        // hotKey

        // Redis中取
        String redisKey = "post" + postInfoVO.getId();

        // todo mq消费 阅读流水增加
        postReadService.addRead(postInfoVO);


        // 获取 数据库数据
        PostInfoPO postInfoPO = getById(postInfoVO.getPostId());
        // 增加阅读数 统计 hotkey

        // 获取当前登录人点赞更新
        getLike(postInfoPO,postInfoVO.getUserId());

        if (postInfoPO.getId() == null){
            return null;
        }
        PostInfoVO postInfoVOResult = getVObyPO(postInfoPO);
        // todo 存入redis中
        // redisKey

        USERID.remove();
        return postInfoVOResult;
    }

    @Override
    public String upload(MultipartFile multipartFile) throws IOException {
        return uploadOneFile(multipartFile);
    }

    /**
     * 上传所有
     *
     * @param file 文件
     * @return {@link String[]}
     */
    @Override
    public String[] uploadAll(MultipartFile[] file) throws IOException {
        int fileLength = file.length;
        String[] content = new String[fileLength];
        for (int i = 0; i < content.length; i++) {
            content[i] = uploadOneFile(file[i]);
        }
        return content;
    }

    /**
     * 删除img
     *
     * @param imgDto img dto
     * @return boolean
     */
    @Override
    public boolean removeImg(ImgDto imgDto) {
        if (StringUtils.isNotEmpty(imgDto.getUrl())){

        }
        if(CollectionUtils.isEmpty(imgDto.getUrls())){

        }
        return true;
    }

    /**
     * 添加帖子
     *
     * @param postInfo 发布信息
     * @return {@link Long}
     */
    @Override
    public boolean add(PostInfoVO postInfo) {
        PostInfoPO postInfoPO = new PostInfoPO();
        // 处理信息
        BeanUtils.copyProperties(postInfo,postInfoPO);

        // todo 使用中间件 存储内容
        String key = postInfo.getContent();

        postInfoPO.setContent(key);
        // 判断img是否为空
        if(postInfo.getImg().length != 0){
            String[] img = postInfo.getImg();
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < img.length; i++) {
                stringBuilder.append(img[i]);
                if(i < img.length-1){
                    stringBuilder.append(SEPARATOR);
                }
            }
            postInfoPO.setImg(stringBuilder.toString());
        }
        // todo api 处理 当前用户信息
        postInfoPO.setUserId(postInfo.getUserId());
        String userIdLocal = userServiceFeign.getUserIdByToken();
        UserInfo user=mapper.queryUser(userIdLocal);
//        UserInfoSearchVO userInfo = userServiceFeign.getUserInfo(postInfo.getUserId().toString()).getData();
        if(user != null){
            postInfoPO.setNickName(user.getNickname());
            postInfoPO.setHeadImg(user.getAvator());
        }else{
            postInfoPO.setNickName("姜鹏小小姜");
            postInfoPO.setHeadImg("http://30.16.95.140:8080/group1/M00/00/00/HhBfjGSAZ4SAcEP9ADkqq6Y4bzo429.jpg");
        }
        postInfoPO.setCreateTime(new Date());

        return save(postInfoPO);
    }

    /**
     * 服务端搜索
     *
     * @param postSearchDto 职位搜索dto
     * @return {@link List}<{@link PostInfoVO}>
     */
    @Override
    public List<PostInfoVO> search(PostSearchDto postSearchDto) throws ParseException {
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH);
        // 处理mybatis-plus 问题
        boolean flag = false;
        Date start = null;
        Date end = null;
        if(postSearchDto.getTimeRange()!=null && postSearchDto.getTimeRange().length > 0){
            flag =true;
            start = formatDate.parse(postSearchDto.getTimeRange()[0]);
            end = formatDate.parse(postSearchDto.getTimeRange()[1]);
        }
        // title 查询 author查询 rangeTime
        List<PostInfoPO> list = lambdaQuery().like(StringUtils.isNotEmpty(postSearchDto.getTitle()), PostInfoPO::getTitle, postSearchDto.getTitle())
                //author查询
                .like(StringUtils.isNotEmpty(postSearchDto.getAuthor()), PostInfoPO::getNickName, postSearchDto.getAuthor())
                //rangeTime 2前端传递
                .ge(flag, PostInfoPO::getCreateTime,start)
                .lt(flag, PostInfoPO::getCreateTime, end)
                //增加 id限制
                .eq(postSearchDto.getId()!=null ,PostInfoPO::getId, postSearchDto.getId())
                .list();

        // 处理数据
        return list.stream().map(this::getVObyPO).collect(Collectors.toList());
    }

    /**
     * app端推荐
     *
     * @param page   页面
     * @param size   大小
     * @param userId 用户id
     * @return {@link List}<{@link PostInfoVO}>
     */
    @Override
    public List<PostInfoVO> getList(Integer page, Integer size, Long userId) {
        //
        USERID.set(userId);
        // 大数据请求接口
        Long[] mockIds= {1L,2L,3L,4L,5L,6L,7L,8L,1666432838122123266L,1666434140591927298L};
        List<Long> ids = new ArrayList<>();
        String url = "http://30.16.95.147:8090/recommend/list";
        url = url +"/"+userId+"/"+0;
        List<String> result=new ArrayList<>();
        HttpHeaders headers = new HttpHeaders();
        LinkedMultiValueMap<String, String> params= new LinkedMultiValueMap<>();
//        params.add("userId", String.valueOf(userId));
//        params.add("type", String.valueOf(0));
        params.add("page", String.valueOf(page));
        params.add("size", String.valueOf(size));
        //
        RestTemplate restTemplate = new RestTemplate();
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        URI uri = builder.queryParams(params).build().encode().toUri();
        //使用getForObject或者getForEntity方法
        ResponseEntity<List> response = restTemplate.getForEntity(uri, List.class);
        if(response.getStatusCodeValue()== ResultGenerator.genSuccessResult().getCode()) {
            ids= response.getBody();
        }
        if(ids.size() <0){
            ids = new ArrayList<>(Arrays.asList(mockIds));
        }
        // 查询数据
        List<PostInfoPO> postInfoPOList = lambdaQuery().in(PostInfoPO::getId, ids).list();
        // 当前用户是否点赞 postLikeService
        postInfoPOList.forEach(item -> getLike(item,userId));
        // 数据处理
        List<PostInfoVO> collect = postInfoPOList.stream().map(this::getVObyPO).collect(Collectors.toList());
        USERID.remove();
        return collect;

    }

    @Override
    public List<PostInfoVO> getListByUserIdOrSku(Integer pageNum, Integer pageSize, Long userId,String sku,Long readerId) {
        if(readerId !=null){
            USERID.set(readerId);
        }

        List<PostInfoPO> postInfoPOList = lambdaQuery().eq(userId!=null,PostInfoPO::getUserId, userId).eq(sku!=null,PostInfoPO::getSku, sku).list();
        // 当前用户是否点赞 postLikeService
        postInfoPOList.forEach(item -> getLike(item,userId));
        // 数据处理
        List<PostInfoVO> collect = postInfoPOList.stream().map(this::getVObyPO).collect(Collectors.toList());
        if(readerId !=null) {
            USERID.remove();
        }
        PageInfo<PostInfoVO> postInfoVOPageInfo = new PageInfo<>(collect);
        return postInfoVOPageInfo.getList();
    }

    private void getLike(PostInfoPO item, Long userId){
        List<PostLikePO> list = postLikeService.lambdaQuery().eq(PostLikePO::getPostId, item.getId())
                .eq(PostLikePO::getUserId, userId).list();
        if (!CollectionUtils.isEmpty(list)){
            item.setLike(true);
        }
    }

    /**
     * PO 转 VO
     *
     * @param postInfoPO 发布信息阿宝
     * @return {@link PostInfoVO}
     */
    private PostInfoVO getVObyPO(PostInfoPO postInfoPO){
        PostInfoVO postInfoVO = new PostInfoVO();
        BeanUtils.copyProperties(postInfoPO, postInfoVO);
        // 拼接字符串
        // 增加防御
        if (StringUtils.isNotEmpty((postInfoPO.getImg()))){
            postInfoVO.setImg(postInfoPO.getImg().split(SEPARATOR));
        }
        // 中间件 获取内容
        String key = postInfoPO.getContent();
        // todo 中间件
        String content = key;
        // 赋值
        postInfoVO.setContent(content);
        // 查询点赞数
        Long count = postLikeService.lambdaQuery().eq(PostLikePO::getPostId, postInfoVO.getId()).count();
        postInfoVO.setLikes(Math.toIntExact(count));
        // 查看是否本人点赞
        Long count1 = postLikeService.lambdaQuery().eq(PostLikePO::getPostId, postInfoPO.getId()).eq(PostLikePO::getUserId, USERID.get())
                .count();
        postInfoVO.setLike(count1>0);

        return postInfoVO;
    }


    /**
     * 上传一个文件
     *
     * @param multipartFile 多部分文件
     * @return {@link String}
     * @throws IOException ioexception
     */
    private String uploadOneFile(MultipartFile multipartFile) throws IOException {
        String fullPath = fastFileStorageClient.uploadFile(multipartFile.getInputStream(), multipartFile.getSize(),
                FilenameUtils.getExtension(multipartFile.getOriginalFilename()), null).getFullPath();
        if (fullPath == null) {
            return "";
        }
        return FASTDFSSERVERIMAGE+fullPath;
    }


}




