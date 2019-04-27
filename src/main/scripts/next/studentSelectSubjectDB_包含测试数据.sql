CREATE DATABASE /*!32312 IF NOT EXISTS*/`studengSelectSubject` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `studengSelectSubject`;
/*
Navicat MySQL Data Transfer

Source Server         : 192.168.120.134
Source Server Version : 50643
Source Host           : 192.168.120.134:3306
Source Database       : studentSelectSubjectDB

Target Server Type    : MYSQL
Target Server Version : 50643
File Encoding         : 65001

Date: 2019-04-05 10:54:37
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for STUDENT_BACK_FEE_INFO
-- ----------------------------
DROP TABLE IF EXISTS `STUDENT_BACK_FEE_INFO`;
CREATE TABLE `STUDENT_BACK_FEE_INFO` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `stu_id` bigint(10) NOT NULL COMMENT '学生ID',
  `back_fee` float NOT NULL COMMENT '退费金额',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `apply_status` bigint(10) NOT NULL COMMENT '申请状态:0申请中，1审批通过，2驳回',
  `audit_user_name` varchar(255) DEFAULT NULL COMMENT '审批人员姓名',
  `audit_time` varchar(255) DEFAULT NULL COMMENT '审批时间',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_user_name` varchar(255) DEFAULT NULL COMMENT '创建人姓名',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user_id` bigint(20) DEFAULT NULL COMMENT '更新人',
  `update_user_name` varchar(255) DEFAULT NULL COMMENT '更新人姓名',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='学生退费信息表';

-- ----------------------------
-- Records of STUDENT_BACK_FEE_INFO
-- ----------------------------
INSERT INTO `STUDENT_BACK_FEE_INFO` VALUES ('1', '1', '500', '', '2', 'admin', '2019-04-01 19:21:11', '1', 'admin', '2019-03-31 11:19:34', '1', '', '2019-03-31 11:19:34');
INSERT INTO `STUDENT_BACK_FEE_INFO` VALUES ('2', '1', '600', '', '2', 'admin', '2019-04-01 19:21:54', '1', 'admin', '2019-03-31 11:15:08', '1', '', '2019-03-31 11:15:08');
INSERT INTO `STUDENT_BACK_FEE_INFO` VALUES ('3', '1', '66', '', '2', 'admin', '2019-04-01 19:21:28', '1', 'admin', '2019-03-31 11:14:39', '1', '', '2019-03-31 11:14:39');
INSERT INTO `STUDENT_BACK_FEE_INFO` VALUES ('4', '1', '6000', '', '2', 'admin', '2019-04-01 19:26:30', '1', 'admin', '2019-03-31 11:20:07', '1', '', '2019-03-31 11:20:07');
INSERT INTO `STUDENT_BACK_FEE_INFO` VALUES ('5', '1', '66', '', '2', 'admin', '2019-04-05 09:17:56', '1', 'admin', '2019-03-31 22:10:44', '1', '', '2019-03-31 22:10:44');
INSERT INTO `STUDENT_BACK_FEE_INFO` VALUES ('6', '1', '555', '', '1', 'admin', '2019-04-05 09:29:32', '1', 'admin', '2019-03-31 22:23:19', '1', '', '2019-03-31 22:23:19');

-- ----------------------------
-- Table structure for STUDENT_BASE_INFO
-- ----------------------------
DROP TABLE IF EXISTS `STUDENT_BASE_INFO`;
CREATE TABLE `STUDENT_BASE_INFO` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `stu_name` varchar(255) DEFAULT NULL COMMENT '学生姓名',
  `stu_no` varchar(255) DEFAULT NULL COMMENT '学生编号',
  `user_pass` varchar(255) DEFAULT NULL COMMENT '登陆密码',
  `mobile` varchar(255) DEFAULT NULL COMMENT '手机号码',
  `address` varchar(255) DEFAULT NULL COMMENT '联系地址',
  `sex` int(10) DEFAULT NULL COMMENT '性别，0男，1女',
  `train_intro` varchar(255) DEFAULT NULL COMMENT '培训意向',
  `train_to` int(10) DEFAULT NULL COMMENT '培训方向,1:php,2:java,3:前端',
  `train_level` int(10) DEFAULT NULL COMMENT '培训等级,1:初级,2:中级,3:高级',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_user_name` varchar(255) DEFAULT NULL COMMENT '创建人姓名',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user_id` bigint(20) DEFAULT NULL COMMENT '更新人',
  `update_user_name` varchar(255) DEFAULT NULL COMMENT '更新人姓名',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8 COMMENT='学生基础信息表';

-- ----------------------------
-- Records of STUDENT_BASE_INFO
-- ----------------------------
INSERT INTO `STUDENT_BASE_INFO` VALUES ('1', '0001', '0001', 'e10adc3949ba59abbe56e057f20f883e', '15838028033', '河南周口', '1', 'java', '3', '3', '1', 'admin', '2019-03-31 23:38:03', '1', '', '2019-03-31 23:38:03');
INSERT INTO `STUDENT_BASE_INFO` VALUES ('2', '0005', '555', 'e10adc3949ba59abbe56e057f20f883e', '55', '55', null, '55', '3', '3', '1', 'admin', '2019-03-31 23:38:10', '1', '', '2019-03-31 23:38:10');
INSERT INTO `STUDENT_BASE_INFO` VALUES ('3', '666', '66', 'e10adc3949ba59abbe56e057f20f883e', '15838028036', '66', '1', '66', null, null, '1', 'admin', '2019-03-31 23:38:00', '1', '', '2019-03-31 23:38:00');
INSERT INTO `STUDENT_BASE_INFO` VALUES ('17', '学生01', '0001', 'e10adc3949ba59abbe56e057f20f883e', '15838028033', '重庆地区', null, 'java', '3', '3', '1', 'admin', '2019-03-31 23:38:01', '1', '', '2019-03-31 23:38:01');
INSERT INTO `STUDENT_BASE_INFO` VALUES ('18', '学生02', '0002', 'e10adc3949ba59abbe56e057f20f883e', '15838028033', '重庆地区02', null, 'java', '2', '2', '1', 'admin', '2019-03-31 23:38:02', '1', '', '2019-03-31 23:38:02');
INSERT INTO `STUDENT_BASE_INFO` VALUES ('19', '学生03', '0003', 'e10adc3949ba59abbe56e057f20f883e', '15838028033', '重庆地区03', '0', 'java', '2', '3', '1', 'admin', '2019-03-31 23:38:54', '19', '', '2019-03-31 23:38:54');
INSERT INTO `STUDENT_BASE_INFO` VALUES ('20', '学生04', '0004', 'e10adc3949ba59abbe56e057f20f883e', '1583802804', '重庆地区03', null, 'C++', '3', '3', '1', 'admin', '2019-03-31 23:37:54', '1', '', '2019-03-31 23:37:54');
INSERT INTO `STUDENT_BASE_INFO` VALUES ('21', '1', '00007', 'e10adc3949ba59abbe56e057f20f883e', '15838028033', '重庆市', null, 'java ', null, null, '1', 'admin', '2019-03-31 23:37:57', '1', '', '2019-03-31 23:37:57');
INSERT INTO `STUDENT_BASE_INFO` VALUES ('22', '3', '3', 'e10adc3949ba59abbe56e057f20f883e', '15838028033', '33', null, '33', null, null, '1', 'admin', '2019-03-31 23:37:58', null, '', '2019-03-31 23:37:58');
INSERT INTO `STUDENT_BASE_INFO` VALUES ('23', '4', '4', 'e10adc3949ba59abbe56e057f20f883e', '15838028033', '33', '0', '33', null, null, '1', 'admin', '2019-03-31 23:37:59', '1', '', '2019-03-31 23:37:59');

-- ----------------------------
-- Table structure for STUDENT_END_INFO
-- ----------------------------
DROP TABLE IF EXISTS `STUDENT_END_INFO`;
CREATE TABLE `STUDENT_END_INFO` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `stu_id` bigint(10) NOT NULL COMMENT '学生ID',
  `apply_status` bigint(10) NOT NULL COMMENT '申请状态:0申请中，1审批通过，2驳回',
  `audit_user_name` varchar(255) DEFAULT NULL COMMENT '审批人员姓名',
  `audit_time` varchar(255) DEFAULT NULL COMMENT '审批时间',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_user_name` varchar(255) DEFAULT NULL COMMENT '创建人姓名',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user_id` bigint(20) DEFAULT NULL COMMENT '更新人',
  `update_user_name` varchar(255) DEFAULT NULL COMMENT '更新人姓名',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COMMENT='学生毕业信息表';

-- ----------------------------
-- Records of STUDENT_END_INFO
-- ----------------------------
INSERT INTO `STUDENT_END_INFO` VALUES ('14', '19', '1', '教导主任', '2019-04-05 10:49:48', '19', '学生03', '2019-03-31 23:50:16', '2', '', '2019-03-31 23:50:16');

-- ----------------------------
-- Table structure for STUDENT_FEE_INFO
-- ----------------------------
DROP TABLE IF EXISTS `STUDENT_FEE_INFO`;
CREATE TABLE `STUDENT_FEE_INFO` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `stu_id` bigint(10) NOT NULL COMMENT '学生ID',
  `fee` float NOT NULL COMMENT '缴费金额',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `apply_status` bigint(10) NOT NULL COMMENT '申请状态:0申请中，1审批通过，2驳回',
  `audit_user_name` varchar(255) DEFAULT NULL COMMENT '审批人员姓名',
  `audit_time` varchar(255) DEFAULT NULL COMMENT '审批时间',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_user_name` varchar(255) DEFAULT NULL COMMENT '创建人姓名',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user_id` bigint(20) DEFAULT NULL COMMENT '更新人',
  `update_user_name` varchar(255) DEFAULT NULL COMMENT '更新人姓名',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='学生缴费信息表';

-- ----------------------------
-- Records of STUDENT_FEE_INFO
-- ----------------------------
INSERT INTO `STUDENT_FEE_INFO` VALUES ('1', '14', '33', '', '1', 'admin', '2019-03-31 16:20:28', '1', 'admin', '2019-03-31 08:20:28', '1', '', '2019-03-31 08:20:28');
INSERT INTO `STUDENT_FEE_INFO` VALUES ('2', '15', '66', '', '1', 'admin', '2019-03-31 16:22:53', '1', 'admin', '2019-03-31 08:22:53', '1', '', '2019-03-31 08:22:53');
INSERT INTO `STUDENT_FEE_INFO` VALUES ('3', '14', '66', '', '0', '', '', '1', 'admin', '2019-03-31 16:45:47', null, '', null);
INSERT INTO `STUDENT_FEE_INFO` VALUES ('4', '1', '600', '', '1', 'admin', '2019-04-04 20:09:23', '1', 'admin', '2019-03-31 20:24:54', '1', '', '2019-03-31 20:24:54');
INSERT INTO `STUDENT_FEE_INFO` VALUES ('5', '17', '500', '', '1', 'admin', '2019-04-04 22:57:35', '1', 'admin', '2019-03-31 20:53:28', '1', '', '2019-03-31 20:53:28');
INSERT INTO `STUDENT_FEE_INFO` VALUES ('6', '18', '200', '', '1', 'admin', '2019-04-04 22:57:42', '1', 'admin', '2019-03-31 20:53:36', '1', '', '2019-03-31 20:53:36');
INSERT INTO `STUDENT_FEE_INFO` VALUES ('7', '19', '300', '', '1', 'admin', '2019-04-04 22:57:50', '1', 'admin', '2019-03-31 20:53:45', '1', '', '2019-03-31 20:53:45');
INSERT INTO `STUDENT_FEE_INFO` VALUES ('8', '20', '400', '', '1', 'admin', '2019-04-04 22:57:57', '1', 'admin', '2019-03-31 20:53:52', '1', '', '2019-03-31 20:53:52');

-- ----------------------------
-- Table structure for STUDENT_GRADE_INFO
-- ----------------------------
DROP TABLE IF EXISTS `STUDENT_GRADE_INFO`;
CREATE TABLE `STUDENT_GRADE_INFO` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `stu_id` bigint(10) NOT NULL COMMENT '学生ID',
  `sum_stu_score` float DEFAULT NULL COMMENT '总分数',
  `sum_sign_status` float DEFAULT NULL COMMENT '总考勤',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `apply_status` bigint(10) NOT NULL COMMENT '申请状态:0申请中，1审批通过，2驳回',
  `audit_user_name` varchar(255) DEFAULT NULL COMMENT '审批人员姓名',
  `audit_time` varchar(255) DEFAULT NULL COMMENT '审批时间',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_user_name` varchar(255) DEFAULT NULL COMMENT '创建人姓名',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user_id` bigint(20) DEFAULT NULL COMMENT '更新人',
  `update_user_name` varchar(255) DEFAULT NULL COMMENT '更新人姓名',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COMMENT='学生升学信息表';

-- ----------------------------
-- Records of STUDENT_GRADE_INFO
-- ----------------------------
INSERT INTO `STUDENT_GRADE_INFO` VALUES ('18', '19', '70', '1', '', '1', '教导主任', '2019-04-05 10:48:17', '19', '学生03', '2019-03-31 23:48:37', '2', '', '2019-03-31 23:48:37');

-- ----------------------------
-- Table structure for SUBJECT_BASE_INFO
-- ----------------------------
DROP TABLE IF EXISTS `SUBJECT_BASE_INFO`;
CREATE TABLE `SUBJECT_BASE_INFO` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `subject_name` varchar(255) DEFAULT NULL COMMENT '课程姓名',
  `subject_no` varchar(255) DEFAULT NULL COMMENT '课程编号',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_user_name` varchar(255) DEFAULT NULL COMMENT '创建人姓名',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user_id` bigint(20) DEFAULT NULL COMMENT '更新人',
  `update_user_name` varchar(255) DEFAULT NULL COMMENT '更新人姓名',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COMMENT='课程基础信息表';

-- ----------------------------
-- Records of SUBJECT_BASE_INFO
-- ----------------------------
INSERT INTO `SUBJECT_BASE_INFO` VALUES ('1', 'c++', '002', '1', 'admin', '2019-03-31 15:51:11', '1', '', '2019-03-31 15:51:11');
INSERT INTO `SUBJECT_BASE_INFO` VALUES ('2', 'java', '003', '1', 'admin', '2019-03-31 15:51:14', '1', '', '2019-03-31 15:51:14');
INSERT INTO `SUBJECT_BASE_INFO` VALUES ('16', '前端', '003', '1', 'admin', '2019-04-04 23:01:25', null, '', null);
INSERT INTO `SUBJECT_BASE_INFO` VALUES ('17', 'java高级课程', 'J0001', '1', 'admin', '2019-03-31 22:33:53', '1', '', '2019-03-31 22:33:53');

-- ----------------------------
-- Table structure for SUBJECT_PLAN_INFO
-- ----------------------------
DROP TABLE IF EXISTS `SUBJECT_PLAN_INFO`;
CREATE TABLE `SUBJECT_PLAN_INFO` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `plan_name` varchar(255) DEFAULT NULL COMMENT '计划名称',
  `subject_id` bigint(10) NOT NULL COMMENT '课程ID',
  `teach_id` bigint(10) NOT NULL COMMENT '教师ID',
  `stu_id` bigint(10) NOT NULL COMMENT '学生ID',
  `start_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '上课开始时间',
  `end_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '上课结束时间',
  `sign_status` bigint(10) DEFAULT NULL COMMENT '签到状态,1:到，-1:未到',
  `plan_remarks` varchar(255) DEFAULT NULL COMMENT '上课备注',
  `stu_score` float DEFAULT NULL COMMENT '学生分数',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_user_name` varchar(255) DEFAULT NULL COMMENT '创建人姓名',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user_id` bigint(20) DEFAULT NULL COMMENT '更新人',
  `update_user_name` varchar(255) DEFAULT NULL COMMENT '更新人姓名',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8 COMMENT='课程计划信息表';

-- ----------------------------
-- Records of SUBJECT_PLAN_INFO
-- ----------------------------
INSERT INTO `SUBJECT_PLAN_INFO` VALUES ('20', 'J++', '2', '1', '19', '2019-03-31 23:31:17', '2019-03-31 23:31:17', '1', '星期一第一节课', '70', '1', '', '2019-03-31 23:31:17', '1', '', '2019-03-31 23:31:17');
INSERT INTO `SUBJECT_PLAN_INFO` VALUES ('21', '前端', '16', '1', '20', '2019-03-31 23:31:05', '2019-03-31 23:31:05', '1', '星期一第2节课', '80', '1', '', '2019-03-31 23:31:05', '1', '', '2019-03-31 23:31:05');
INSERT INTO `SUBJECT_PLAN_INFO` VALUES ('22', '前端', '16', '17', '20', '2019-03-31 23:29:49', '2019-03-31 23:29:49', '1', '星期一第2节课', '80', '1', '', '2019-03-31 23:29:49', '17', '', '2019-03-31 23:29:49');

-- ----------------------------
-- Table structure for SYS_USER
-- ----------------------------
DROP TABLE IF EXISTS `SYS_USER`;
CREATE TABLE `SYS_USER` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_name` varchar(255) DEFAULT NULL COMMENT '登陆用户',
  `user_no` varchar(255) DEFAULT NULL COMMENT '登陆账号',
  `user_pass` varchar(255) DEFAULT NULL COMMENT '登陆密码',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_user_name` varchar(255) DEFAULT NULL COMMENT '创建人姓名',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user_id` bigint(20) DEFAULT NULL COMMENT '更新人',
  `update_user_name` varchar(255) DEFAULT NULL COMMENT '更新人姓名',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `role_type` int(11) DEFAULT NULL COMMENT '角色类型,0:超级管理员,3:财务，4:咨询员,5:教导主任',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='系统基础信息表';

-- ----------------------------
-- Records of SYS_USER
-- ----------------------------
INSERT INTO `SYS_USER` VALUES ('1', '超级管理员', 'admin', 'e10adc3949ba59abbe56e057f20f883e', '0', 'admin', '2019-03-31 04:07:57', '1', 'admin', '2019-03-31 04:07:57', '0');
INSERT INTO `SYS_USER` VALUES ('2', '教导主任', 'teachAdmin', 'e10adc3949ba59abbe56e057f20f883e', '0', 'admin', '2019-03-31 20:45:12', null, null, '2019-03-31 20:45:12', '5');
INSERT INTO `SYS_USER` VALUES ('3', '咨询员', 'infoAdmin', 'e10adc3949ba59abbe56e057f20f883e', '0', 'admin', '2019-03-31 20:45:41', null, null, '2019-03-31 20:45:41', '4');
INSERT INTO `SYS_USER` VALUES ('4', '财务人员', 'financialAdmin', 'e10adc3949ba59abbe56e057f20f883e', '0', 'admin', '2019-03-31 20:45:16', null, null, '2019-03-31 20:45:16', '3');

-- ----------------------------
-- Table structure for TEACH_BASE_INFO
-- ----------------------------
DROP TABLE IF EXISTS `TEACH_BASE_INFO`;
CREATE TABLE `TEACH_BASE_INFO` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `teach_name` varchar(255) DEFAULT NULL COMMENT '教师姓名',
  `teach_no` varchar(255) DEFAULT NULL COMMENT '教师编号',
  `teach_pass` varchar(255) DEFAULT NULL COMMENT '登陆密码',
  `address` varchar(255) DEFAULT NULL COMMENT '联系地址',
  `train_info` varchar(255) DEFAULT NULL COMMENT '教课方向',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_user_name` varchar(255) DEFAULT NULL COMMENT '创建人姓名',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user_id` bigint(20) DEFAULT NULL COMMENT '更新人',
  `update_user_name` varchar(255) DEFAULT NULL COMMENT '更新人姓名',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COMMENT='教师基础信息表';

-- ----------------------------
-- Records of TEACH_BASE_INFO
-- ----------------------------
INSERT INTO `TEACH_BASE_INFO` VALUES ('1', '张老师', '0001', 'e10adc3949ba59abbe56e057f20f883e', '河南郑州', 'java', '1', 'admin', '2019-03-31 17:52:25', '1', '', '2019-03-31 17:52:25');
INSERT INTO `TEACH_BASE_INFO` VALUES ('16', '教师02', '0002', 'e10adc3949ba59abbe56e057f20f883e', '重庆02', 'java高级', '1', 'admin', '2019-03-31 22:29:23', '1', '', '2019-03-31 22:29:23');
INSERT INTO `TEACH_BASE_INFO` VALUES ('17', '教师10001', '教师10001', 'e10adc3949ba59abbe56e057f20f883e', '重庆', 'web前端', '1', 'admin', '2019-03-31 23:30:10', '17', '', '2019-03-31 23:30:10');
SET FOREIGN_KEY_CHECKS=1;
