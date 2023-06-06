SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for post_info
-- ----------------------------
DROP TABLE IF EXISTS `post_info`;
CREATE TABLE `post_info` (
                             `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
                             `post_id` varchar(32) NOT NULL COMMENT '帖子ID',
                             `user_id` varchar(32) NOT NULL COMMENT '用户ID',
                             `nick_name` varchar(200) DEFAULT NULL COMMENT '用户昵称',
                             `head_img` varchar(200) DEFAULT NULL COMMENT '用户头像',
                             `post_title` varchar(200) DEFAULT NULL COMMENT '帖子标题',
                             `post_content` varchar(200) DEFAULT NULL COMMENT '帖子内容',
                             `status` char(1) NULL DEFAULT NULL COMMENT '使用状态（1正常 0非正常）',
                             `tag_ids` varchar(200) DEFAULT NULL COMMENT '标签列表',
                             `praise_count` int(11) DEFAULT 0 COMMENT '帖子点赞数量',
                             `reply_count` int(11) DEFAULT 0 COMMENT '帖子评论数量',
                             `sku_url` varchar(200) DEFAULT NULL COMMENT '商品链接',
                             `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                             `modified_time` datetime DEFAULT NULL COMMENT '修改时间',
                             PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='推荐帖信息表';