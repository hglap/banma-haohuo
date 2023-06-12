package com.ebanma.cloud.seckill.service.impl;

import com.ebanma.cloud.common.core.AbstractService;
import com.ebanma.cloud.common.util.BeanUtil;
import com.ebanma.cloud.seckill.dao.SeckillMapper;
import com.ebanma.cloud.seckill.model.po.Activity;
import com.ebanma.cloud.seckill.model.po.Seckill;
import com.ebanma.cloud.seckill.model.vo.ActivitySearchInfoVo;
import com.ebanma.cloud.seckill.service.SeckillService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by CodeGenerator on 2023/06/06.
 */
@Service
@Transactional
public class SeckillServiceImpl extends AbstractService<Seckill> implements SeckillService {
    @Resource
    private SeckillMapper seckillMapper;


}
