SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for trans_info
-- ----------------------------
DROP TABLE IF EXISTS `trans_info`;
CREATE TABLE `trans_info` (
                             `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
                             `user_id` varchar(32) NOT NULL COMMENT '用户ID',
                             `trans_type` varchar(32) NOT NULL COMMENT '账户类型：积分、红包',
                             `trans_type_code` varchar(32) NOT NULL COMMENT '账户类型码：0001对应积分',
                             `trans_id` varchar(32) NOT NULL COMMENT '账务ID',
                             `status` char(1) NULL DEFAULT NULL COMMENT '使用状态（1正常 0非正常）',
                             `creator` varchar(32) DEFAULT NULL COMMENT '创建者',
                             `modifier` varchar(32) DEFAULT NULL COMMENT '修改者',
                             `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                             `modified_time` datetime DEFAULT NULL COMMENT '修改时间',
                             PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='账务用户信息表';

-- ----------------------------
-- Table structure for trans_account
-- ----------------------------
DROP TABLE IF EXISTS `trans_account`;
CREATE TABLE `trans_account` (
                              `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
                              `trans_id` varchar(32) NOT NULL COMMENT '账务ID',
                              `init_amount` bigint(20) DEFAULT 0 COMMENT '初始余额。普通用户为0，总账账户为10000000',
                              `balance` bigint(20) DEFAULT 0 COMMENT '当前余额',
                              `freeze_Balance` bigint(20) DEFAULT 0 COMMENT '当前冻结余额',
                              `total_in_amount` bigint(20) DEFAULT 0 COMMENT '当前余额',
                              `total_out_amount` bigint(20) DEFAULT 0 COMMENT '总流出金额',
                              `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                              `modified_time` datetime DEFAULT NULL COMMENT '修改时间',
                              PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='账务余额主表';