SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
                             `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
                             `name` varchar(10) NOT NULL COMMENT '分类名称',
                             PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='基础分类表';

DROP TABLE IF EXISTS `sku_info`;
CREATE TABLE `sku_info` (
                            `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'skuid(itemID)',
                            `sku_id` varchar(32) DEFAULT NULL COMMENT 'sku_id',
                            `price` decimal(10,0) DEFAULT NULL COMMENT '价格',
                            `sku_name` varchar(200) DEFAULT NULL COMMENT 'sku名称',
                            `sku_desc` varchar(2000) DEFAULT NULL COMMENT '商品规格描述',
                            `weight` decimal(10,2) DEFAULT NULL COMMENT '重量',
                            `category_id` bigint(20) DEFAULT NULL COMMENT '分类id（冗余)',
                            `sku_default_img` varchar(200) DEFAULT NULL COMMENT '默认显示图片(冗余)',
                            `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                            `modified_time` datetime DEFAULT NULL COMMENT '修改时间',
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品信息表';