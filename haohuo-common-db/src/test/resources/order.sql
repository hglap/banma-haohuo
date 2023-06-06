SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for order_info
-- ----------------------------
DROP TABLE IF EXISTS `order_info`;
CREATE TABLE `order_info` (
                              `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
                              `order_id` varchar(32) DEFAULT NULL COMMENT '订单编号',
                              `total_amount` decimal(10,2) DEFAULT NULL COMMENT '总金额',
                              `order_status` varchar(20) DEFAULT NULL COMMENT '订单状态',
                              `user_id` varchar(32) DEFAULT NULL COMMENT '用户id',
                              `payment_way` varchar(20) DEFAULT NULL COMMENT '付款方式',
                              `delivery_address` varchar(1000) DEFAULT NULL COMMENT '送货地址',
                              `consignee` varchar(100) DEFAULT NULL COMMENT '收货人',
                              `consignee_tel` varchar(20) DEFAULT NULL COMMENT '收件人电话',
                              `order_comment` varchar(200) DEFAULT NULL COMMENT '订单备注',
                              `out_trade_no` varchar(50) DEFAULT NULL COMMENT '订单交易编号（第三方支付用)',
                              `trade_body` varchar(200) DEFAULT NULL COMMENT '订单描述(第三方支付用)',
                              `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                              `payment_time` datetime DEFAULT NULL COMMENT '支付时间',
                              `delivery_time` datetime DEFAULT NULL COMMENT '发货时间',
                              `consign_time` datetime DEFAULT NULL COMMENT '签收时间',
                              `img_url` varchar(200) DEFAULT NULL COMMENT '图片路径',
                              PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单表信息表';

-- ----------------------------
-- Table structure for order_detail
-- ----------------------------
DROP TABLE IF EXISTS `order_detail`;
CREATE TABLE `order_detail` (
                                `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
                                `order_id` varchar(32) DEFAULT NULL COMMENT '订单编号',
                                `sku_id` varchar(32) DEFAULT NULL COMMENT 'sku_id',
                                `sku_name` varchar(200) DEFAULT NULL COMMENT 'sku名称（冗余)',
                                `sku_img` varchar(200) DEFAULT NULL COMMENT '图片地址（冗余)',
                                `order_price` decimal(10,2) DEFAULT NULL COMMENT '购买价格(下单时sku价格）',
                                `sku_num` varchar(200) DEFAULT NULL COMMENT '购买个数',
                                `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                                PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单明细表';

-- ----------------------------
-- Table structure for payment_info
-- ----------------------------
DROP TABLE IF EXISTS `payment_info`;
CREATE TABLE `payment_info` (
                                `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
                                `out_trade_no` varchar(20) DEFAULT NULL COMMENT '对外业务编号',
                                `order_id` varchar(32) DEFAULT NULL COMMENT '订单编号',
                                `user_id` varchar(32) DEFAULT NULL COMMENT '用户编号',
                                `alipay_trade_no` varchar(32) DEFAULT NULL COMMENT '支付宝交易流水编号',
                                `total_amount` decimal(16,2) DEFAULT NULL COMMENT '支付金额',
                                `subject` varchar(200) DEFAULT NULL COMMENT '交易内容',
                                `payment_type` varchar(32) DEFAULT NULL COMMENT '支付方式',
                                `payment_time` datetime DEFAULT NULL COMMENT '支付时间',
                                PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='支付信息表';