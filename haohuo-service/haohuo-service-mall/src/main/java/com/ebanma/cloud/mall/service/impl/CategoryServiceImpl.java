package com.ebanma.cloud.mall.service.impl;


import com.ebanma.cloud.common.core.AbstractService;
import com.ebanma.cloud.mall.dao.CategoryMapper;
import com.ebanma.cloud.mall.model.Category;
import com.ebanma.cloud.mall.service.CategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2023/06/06.
 */
@Service
@Transactional
public class CategoryServiceImpl extends AbstractService<Category> implements CategoryService {
    @Resource
    private CategoryMapper categoryMapper;

}
