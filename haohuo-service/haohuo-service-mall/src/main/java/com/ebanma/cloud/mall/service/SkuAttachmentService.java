package com.ebanma.cloud.mall.service;

import com.ebanma.cloud.mall.model.dto.SkuAttachmentSearchDTO;
import com.ebanma.cloud.mall.model.po.SkuAttachmentPO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ebanma.cloud.mall.model.vo.SkuAttachmentVO;

import java.util.List;
import java.util.Map;

/**
* @author kmkmj
* @description 针对表【sku_attachment】的数据库操作Service
* @createDate 2023-06-07 14:57:08
*/
public interface SkuAttachmentService extends IService<SkuAttachmentPO> {

    List<SkuAttachmentVO> getAttachmentList(SkuAttachmentSearchDTO skuAttachmentSearchDTO);

    Map<String,List<SkuAttachmentVO>> getAttachmentMap(SkuAttachmentSearchDTO skuAttachment);

    SkuAttachmentVO queryById(String id);
}
