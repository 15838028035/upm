CREATE DATABASE /*!32312 IF NOT EXISTS*/`UPM` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `UPM`;


SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for SYS_USER
-- ----------------------------
DROP TABLE IF EXISTS `UPM_USER`;
CREATE TABLE `UPM_USER` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_no` varchar(255) DEFAULT NULL COMMENT '登陆账号',
  `user_pass` varchar(255) DEFAULT NULL COMMENT '登陆密码',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_user_name` varchar(255) DEFAULT NULL COMMENT '创建人姓名',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user_id` bigint(20) DEFAULT NULL COMMENT '更新人',
  `update_user_name` varchar(255) DEFAULT NULL COMMENT '更新人姓名',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='系统用户表';

-- ----------------------------
-- Records of UPM_USER
-- ----------------------------
INSERT INTO `UPM_USER` VALUES ('1', 'admin', 'e10adc3949ba59abbe56e057f20f883e', '0', 'admin', '2019-03-31 04:07:57', '1', 'admin', '2019-03-31 04:07:57');
SET FOREIGN_KEY_CHECKS=1;
