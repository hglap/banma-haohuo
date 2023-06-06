SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
                             `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
                             `user_id` varchar(32) NOT NULL COMMENT '用户ID',
                             `user_phone` varchar(20) NOT NULL COMMENT '注册手机号',
                             `password` varchar(100) NOT NULL COMMENT '密码，加密存储',
                             `status` char(1) NULL DEFAULT NULL COMMENT '使用状态（1正常 0非正常）',
                             `nick_name` varchar(200) DEFAULT NULL COMMENT '用户昵称',
                             `head_img` varchar(200) DEFAULT NULL COMMENT '头像',
                             `user_level` varchar(200) DEFAULT NULL COMMENT '用户级别',
                             `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                             `modified_time` datetime DEFAULT NULL COMMENT '修改时间',
                             `last_login_time` datetime NULL DEFAULT NULL COMMENT '最后登录时间',
                             PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Table structure for address
-- ----------------------------
DROP TABLE IF EXISTS `prod_lifetime`;
CREATE TABLE `prod_lifetime`  (
                                `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
                                `principal_id` varchar(64) NULL DEFAULT NULL COMMENT '主体ID',
                                `principal_type` varchar(20) NULL DEFAULT NULL COMMENT '主体类型',
                                `product_code` varchar(20) NULL DEFAULT NULL COMMENT '产品码',
                                `amount` bigint(20) NULL DEFAULT NULL COMMENT '累计金额',
                                `count` bigint(20) NULL DEFAULT NULL COMMENT '累计次数',
                                `writeoff_amount` bigint(20) NULL DEFAULT NULL COMMENT '反向累计金额',
                                `writeoff_count` bigint(20) NULL DEFAULT NULL COMMENT '反向累计次数',
                                `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                                `modified_time` datetime DEFAULT NULL COMMENT '修改时间',
                                PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB DEFAULT CHARSET=utf8 COMMENT='产品账表';

-- ----------------------------
-- Table structure for address
-- ----------------------------
DROP TABLE IF EXISTS `address`;
CREATE TABLE `address`  (
                               `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
                               `user_id` varchar(32) NULL DEFAULT NULL COMMENT '用户ID',
                               `province` varchar(64) NULL DEFAULT NULL COMMENT '省',
                               `city` varchar(64) NULL DEFAULT NULL COMMENT '市',
                               `area` varchar(64) NULL DEFAULT NULL COMMENT '县/区',
                               `phone` varchar(20) NULL DEFAULT NULL COMMENT '电话',
                               `address` varchar(200) NULL DEFAULT NULL COMMENT '详细地址',
                               `contact` varchar(64) NULL DEFAULT NULL COMMENT '联系人',
                               `is_default` char(1) NULL DEFAULT NULL COMMENT '是否是默认 1默认 0否',
                               PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB DEFAULT CHARSET=utf8 COMMENT='收货地址表';