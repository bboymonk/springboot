/*
Navicat MySQL Data Transfer

Source Server         : wjb
Source Server Version : 50716
Source Host           : localhost:3306
Source Database       : springboot

Target Server Type    : MYSQL
Target Server Version : 50716
File Encoding         : 65001

Date: 2019-06-06 10:05:46
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_account
-- ----------------------------
DROP TABLE IF EXISTS `t_account`;
CREATE TABLE `t_account` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `appleid` varchar(128) DEFAULT NULL,
  `password` varchar(128) NOT NULL,
  `amount` decimal(8,2) DEFAULT '0.00' COMMENT '金额',
  `cost` decimal(8,2) NOT NULL DEFAULT '0.00' COMMENT '使用金额',
  `country_id` int(11) NOT NULL,
  `question1` int(11) DEFAULT NULL COMMENT '问题1关联t_question表id',
  `answer1` varchar(255) DEFAULT NULL COMMENT '答案1',
  `question2` int(11) DEFAULT NULL COMMENT '问题2关联t_question表id',
  `answer2` varchar(255) DEFAULT NULL COMMENT '答案2',
  `question3` int(11) DEFAULT NULL COMMENT '问题3关联t_question表id',
  `answer3` varchar(255) DEFAULT NULL COMMENT '答案3',
  `flag` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT '0' COMMENT '0：自有 1： 卡商提供',
  `status` int(11) DEFAULT '0' COMMENT '状态，0：未使用 1：使用中（已领取） 2：部分使用 3：使用失败 4：使用完成 5：已结算 6：审核中 8：已删除',
  `modify_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `submit_time` timestamp NULL DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `appleid` (`appleid`) USING BTREE,
  KEY `user_id` (`user_id`),
  KEY `create_time` (`create_time`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_account
-- ----------------------------
INSERT INTO `t_account` VALUES ('12', '24', 'ivx136913@163.com', 'Dd112211', '50.00', '0.00', '2', '29', '101', '21', '202', '27', '303', '0', '0', '2', '2017-10-23 10:03:49', null, '2017-10-15 14:32:01');
INSERT INTO `t_account` VALUES ('15', '25', 'alkwktn28@163.com', 'Dd112211', '50.00', '0.00', '2', '1', '101', '7', '202', '13', '303', '0', '0', '2', '2017-10-23 10:03:49', null, '2017-10-17 15:27:11');
INSERT INTO `t_account` VALUES ('16', '25', 'uz7346@163.com', 'Dd112211', '50.00', '0.00', '2', '1', '101', '7', '202', '13', '303', '0', '0', '2', '2017-10-23 10:03:50', null, '2017-10-17 16:59:20');
INSERT INTO `t_account` VALUES ('17', '24', 'msnbcn341@163.com', 'Dd112211', '50.00', '0.00', '1', '1', '101', '7', '202', '13', '303', '0', '0', '5', '2017-10-23 10:03:50', '2017-10-20 15:22:30', '2017-10-24 17:22:48');
INSERT INTO `t_account` VALUES ('18', null, '123456@qq.com', '1', null, '0.00', '10', '23', '1111', '26', '222', '32', '333', '0', '0', '8', '2017-10-23 10:03:50', null, '2017-10-19 10:03:44');

-- ----------------------------
-- Table structure for t_asset
-- ----------------------------
DROP TABLE IF EXISTS `t_asset`;
CREATE TABLE `t_asset` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `country_id` int(11) NOT NULL,
  `balance` decimal(8,2) DEFAULT '0.00',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_id` (`user_id`,`country_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_asset
-- ----------------------------

-- ----------------------------
-- Table structure for t_country
-- ----------------------------
DROP TABLE IF EXISTS `t_country`;
CREATE TABLE `t_country` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL COMMENT '涓?枃鍚',
  `english` varchar(64) DEFAULT NULL COMMENT '鑻辨枃鍚',
  `symbol` varchar(20) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '鍒涘缓鏃堕棿',
  PRIMARY KEY (`id`),
  KEY `name_english` (`name`,`english`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_country
-- ----------------------------
INSERT INTO `t_country` VALUES ('1', '中国', 'China', '￥', '2017-04-02 14:19:29');
INSERT INTO `t_country` VALUES ('2', '美国', 'America', '$', '2017-04-02 14:19:29');
INSERT INTO `t_country` VALUES ('3', '加拿大', 'Canada', 'C$', '2017-10-16 11:07:33');
INSERT INTO `t_country` VALUES ('4', '英国', 'Britain', '￡', '2017-10-16 11:07:41');
INSERT INTO `t_country` VALUES ('5', '欧盟(非德)', 'European Unio', '€', '2017-10-16 11:10:34');
INSERT INTO `t_country` VALUES ('6', '德国', 'Germany', '€', '2017-10-16 11:10:43');
INSERT INTO `t_country` VALUES ('7', '澳大利亚', 'Australia', 'A$', '2017-10-16 11:10:51');
INSERT INTO `t_country` VALUES ('8', '瑞士', 'Switzerland', '₣', '2017-10-16 11:10:58');
INSERT INTO `t_country` VALUES ('9', '香港', 'Hong Kong', 'HK＄', '2017-10-16 11:11:04');
INSERT INTO `t_country` VALUES ('10', '日本', 'Japan', 'J￥', '2017-10-16 11:11:10');

-- ----------------------------
-- Table structure for t_login_log
-- ----------------------------
DROP TABLE IF EXISTS `t_login_log`;
CREATE TABLE `t_login_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `ip` varchar(32) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `create_time` (`create_time`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COMMENT='平台用户（玩家）登录日志';

-- ----------------------------
-- Records of t_login_log
-- ----------------------------
INSERT INTO `t_login_log` VALUES ('1', '1', '127.0.0.1', '2017-10-17 18:44:22');
INSERT INTO `t_login_log` VALUES ('2', '1', '127.0.0.1', '2017-10-16 18:45:17');
INSERT INTO `t_login_log` VALUES ('3', '1', '127.0.0.1', '2017-10-20 18:59:32');
INSERT INTO `t_login_log` VALUES ('4', '2', '127.0.0.1', '2017-10-17 19:11:35');
INSERT INTO `t_login_log` VALUES ('5', '22', '127.0.0.1', '2017-10-07 09:44:28');
INSERT INTO `t_login_log` VALUES ('6', '22', '127.0.0.1', '2017-10-07 09:46:38');
INSERT INTO `t_login_log` VALUES ('7', '22', '127.0.0.1', '2017-10-07 09:47:51');
INSERT INTO `t_login_log` VALUES ('8', '22', '127.0.0.1', '2017-10-08 09:54:07');
INSERT INTO `t_login_log` VALUES ('9', '22', '127.0.0.1', '2017-10-09 09:55:18');
INSERT INTO `t_login_log` VALUES ('10', '22', '127.0.0.1', '2017-10-10 09:57:40');
INSERT INTO `t_login_log` VALUES ('11', '23', '127.0.0.1', '2017-10-09 10:08:09');
INSERT INTO `t_login_log` VALUES ('12', '24', '127.0.0.1', '2017-10-19 09:27:26');
INSERT INTO `t_login_log` VALUES ('13', '24', '127.0.0.1', '2017-10-19 09:28:01');
INSERT INTO `t_login_log` VALUES ('14', '25', '127.0.0.1', '2017-10-19 09:29:33');
INSERT INTO `t_login_log` VALUES ('15', '25', '127.0.0.1', '2017-10-19 09:30:23');
INSERT INTO `t_login_log` VALUES ('16', '25', '127.0.0.1', '2017-10-19 09:31:01');
INSERT INTO `t_login_log` VALUES ('17', '24', '127.0.0.1', '2017-10-20 09:34:21');

-- ----------------------------
-- Table structure for t_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_menu`;
CREATE TABLE `t_menu` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `PARENT_ID` int(11) NOT NULL,
  `MENU_NM` varchar(120) CHARACTER SET utf8 NOT NULL,
  `MAPPING` varchar(120) CHARACTER SET utf8 NOT NULL,
  `RANK_SORT` bigint(100) NOT NULL,
  `PICTURE_URL` varchar(60) CHARACTER SET utf8 NOT NULL,
  `DISP_INDEX` int(11) DEFAULT NULL,
  `DISP_KBN` varchar(3) CHARACTER SET utf8 DEFAULT NULL,
  `REMARKS` varchar(1000) CHARACTER SET utf8 DEFAULT NULL,
  `STATE` int(11) NOT NULL,
  `CREATER` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `CREATER_ID` bigint(11) DEFAULT NULL,
  `OPERATOR` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `OPERATOR_ID` bigint(11) DEFAULT NULL,
  `CREATE_TIME` datetime DEFAULT NULL,
  `UPDATE_TIME` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `MENU_NM` (`MENU_NM`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=89 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='菜单表';

-- ----------------------------
-- Records of t_menu
-- ----------------------------
INSERT INTO `t_menu` VALUES ('1', '0', '首页管理', '/', '-1', '/', null, null, null, '1', null, null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('2', '0', '权限管理', '/', '4', '/', null, null, null, '1', null, null, null, null, null, '2017-02-27 15:36:59');
INSERT INTO `t_menu` VALUES ('3', '0', '客户管理', '/', '0', '/', null, null, null, '1', null, null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('4', '0', '设备管理', '/', '2', '/', null, null, null, '1', null, null, null, null, null, '2017-02-23 11:54:47');
INSERT INTO `t_menu` VALUES ('5', '0', '统计分析', '/', '3', '/', null, null, null, '1', null, null, null, null, null, '2017-02-23 11:54:59');
INSERT INTO `t_menu` VALUES ('6', '0', '商城管理', '/', '6', '/', null, null, null, '1', null, null, null, null, null, '2017-02-27 15:37:38');
INSERT INTO `t_menu` VALUES ('7', '0', '短信管理', '/', '10', '/', null, null, null, '1', null, null, null, null, null, '2017-02-27 12:09:50');
INSERT INTO `t_menu` VALUES ('8', '0', '知识库  ', '/', '8', '/', null, null, null, '1', null, null, null, null, null, '2017-02-23 11:14:36');
INSERT INTO `t_menu` VALUES ('9', '2', '管理员中心', '/admin/userCenter', '1', '/', null, null, null, '1', null, null, null, null, null, '2017-02-10 15:28:34');
INSERT INTO `t_menu` VALUES ('10', '2', '角色中心', '/admin/roleCenter', '2', '/', null, null, null, '1', null, null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('11', '2', '资源管理', '/admin/resourceList', '3', '/', null, null, null, '1', null, null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('12', '2', '组别管理', '/admin/roleGroup', '4', '/', null, null, null, '1', null, null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('13', '2', '部门管理', '/admin/userGroup', '5', '/', null, null, null, '1', null, null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('14', '2', '权限分管', '/admin/partmentGroup', '6', '/', null, null, null, '1', null, null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('16', '1', '修改帐户信息', '/admin/index', '2', '/', null, null, null, '1', null, null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('17', '1', '修改密码', '/admin/index', '3', '/', null, null, null, '1', null, null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('18', '1', '首页', '/admin/index', '1', '/', null, null, null, '1', null, null, null, null, null, '2017-06-29 13:36:15');
INSERT INTO `t_menu` VALUES ('19', '3', '客户视图', '/admin/customer/index', '1', '/', null, null, null, '1', null, null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('20', '3', '设备视图', '/admin/customer/deviceView', '2', '/', null, null, null, '1', null, null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('21', '3', '企业视图', '/admin/customer/companyView', '3', '/', null, null, null, '1', null, null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('22', '30', '慢病统计报表', '/admin/customer/chartReport', '4', '/', null, null, null, '1', null, null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('23', '4', '出入库管理', '/admin/deviceManagement/outStorage', '1', '/', null, null, null, '1', null, null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('24', '4', '设备设置', '/admin/deviceManagement/deviceControl', '2', '/', null, null, null, '1', null, null, null, null, null, '2017-02-23 16:53:52');
INSERT INTO `t_menu` VALUES ('25', '4', 'sim卡管理', '/admin/index', '3', '/', null, null, null, '1', null, null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('26', '4', '退换货登记', '/admin/refundItem', '4', '/', null, null, null, '1', null, null, null, null, null, '2017-02-28 18:06:14');
INSERT INTO `t_menu` VALUES ('27', '4', '测量日志', '/admin/index', '5', '/', null, null, null, '1', null, null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('28', '4', '续费管理', '/admin/index', '6', '/', null, null, null, '1', null, null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('29', '5', '设备使用统计', '/admin/statisticAnalysis/deviceStatistics', '1', '/', null, null, null, '1', null, null, null, null, null, '2017-03-06 17:06:30');
INSERT INTO `t_menu` VALUES ('30', '5', '异常数据统计', '/admin/statisticAnalysis/unusualStatistics', '2', '/', null, null, null, '1', null, null, null, null, null, '2017-03-07 18:00:36');
INSERT INTO `t_menu` VALUES ('31', '5', '设备销售统计', '/admin/statisticAnalysis/salesStatistics', '3', '/', null, null, null, '1', null, null, null, null, null, '2017-03-07 18:10:26');
INSERT INTO `t_menu` VALUES ('32', '5', '回访统计', '/admin/statisticAnalysis/payStatistics', '4', '/', null, null, null, '1', null, null, null, null, null, '2017-03-10 09:41:12');
INSERT INTO `t_menu` VALUES ('33', '5', '企业设备统计', '/admin/statisticAnalysis/expire', '5', '/', null, null, null, '1', null, null, null, null, null, '2017-03-10 09:34:52');
INSERT INTO `t_menu` VALUES ('34', '5', '到期统计', '/admin/statisticAnalysis/expireStatistics', '6', '/', null, null, null, '1', null, null, null, null, null, '2017-03-09 09:59:40');
INSERT INTO `t_menu` VALUES ('35', '6', '订单管理', '/admin/orderManager', '1', '/', null, null, null, '1', null, null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('36', '6', '商城推送配置', '/admin/shopPush', '2', '/', null, null, null, '1', null, null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('37', '7', '短信群发', '/admin/smsSend', '1', '/', null, null, null, '1', null, null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('38', '7', '短信发送记录', '/admin/smsRecord', '2', '/', null, null, null, '1', null, null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('39', '8', '短信模板', '/admin/repository/smsTemplate', '1', '/', null, null, null, '1', null, null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('40', '8', '药品库', '/admin/drugStorage', '2', '/', null, null, null, '1', null, null, null, null, null, '2017-06-29 20:08:47');
INSERT INTO `t_menu` VALUES ('41', '8', '回访模板', '/admin/index', '3', '/', null, null, null, '1', null, null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('42', '8', '题目库', '/admin/index', '4', '/', null, null, null, '1', null, null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('43', '8', '微信模板', '/admin/news/chatNew', '5', '/', null, null, null, '1', null, null, null, null, null, '2017-06-29 20:19:14');
INSERT INTO `t_menu` VALUES ('44', '8', '回访结果', '/admin/index', '6', '/', null, null, null, '1', null, null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('45', '8', '运动方案', '/admin/index', '7', '/', null, null, null, '1', null, null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('46', '8', '运动视频', '/admin/index', '8', '/', null, null, null, '1', null, null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('58', '30', '客户区域统计', '/admin/customer/areaChats', '5', '/', null, null, null, '1', null, null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('59', '30', '干预随访报表', '/admin/customer/followList', '6', '/', null, null, null, '1', null, null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('60', '30', '健康服务开展月度报表', '/admin/customer/healthStatistics', '7', '/', null, null, null, '1', null, null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('61', '0', '菜单管理', '/admin/menuList', '5', '/', null, null, null, '1', null, null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('64', '200', '角色菜单', '/admin/roleList', '8', '/', null, null, null, '1', null, null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('65', '0', '资讯管理  ', '/admin/index', '7', '/', null, null, null, '1', null, null, null, null, null, '2017-02-23 11:14:04');
INSERT INTO `t_menu` VALUES ('66', '65', '资讯科普', '/admin/news', '1', '/', null, null, null, '1', null, null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('67', '0', '健康小屋  ', '/admin/index', '9', '/', null, null, null, '1', null, null, null, null, null, '2017-02-23 11:14:43');
INSERT INTO `t_menu` VALUES ('68', '67', '健康小屋管理', '/admin/hutManager', '1', '/', null, null, null, '1', null, null, null, null, null, '2017-02-23 11:16:17');
INSERT INTO `t_menu` VALUES ('69', '67', '客户检测记录', '/admin/cusCheck', '2', '/', null, null, null, '1', null, null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('70', '67', '设备使用记录', '/admin/equReports', '3', '/', null, null, null, '1', null, null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('71', '0', '园区管理', '/admin/index', '1', '/', null, null, null, '1', null, null, null, null, null, '2017-02-27 15:36:50');
INSERT INTO `t_menu` VALUES ('72', '71', '园区视图', '/admin/gardenView', '1', '/', null, null, null, '1', null, null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('73', '71', '住户管理', '/admin/residentManager', '2', '/', null, null, null, '1', null, null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('86', '0', '客服聊天', 'http://ocean.hzpaiding.com/green/adminChat/index', '13', '/', null, null, null, '1', 'dhhua', '1', null, null, '2017-02-28 10:00:10', '2017-02-28 15:55:48');
INSERT INTO `t_menu` VALUES ('87', '71', '数据统计', '/admin/gardenManage/dataStatistics', '4', 'http://', null, null, null, '1', 'dhhua', '1', null, null, '2017-06-29 17:52:38', null);
INSERT INTO `t_menu` VALUES ('88', '65', '微信评论审核', '/admin/news/chatNew', '2', 'http://', null, null, null, '1', 'dhhua', '1', null, null, '2017-06-29 18:21:28', '2017-06-29 18:53:05');

-- ----------------------------
-- Table structure for t_menu_role
-- ----------------------------
DROP TABLE IF EXISTS `t_menu_role`;
CREATE TABLE `t_menu_role` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ROLE_ID` int(11) NOT NULL,
  `MENU_ID` int(11) NOT NULL,
  `STATE` int(11) DEFAULT NULL,
  `CREATER` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `CREATER_ID` bigint(11) DEFAULT NULL,
  `OPERATOR` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `OPERATOR_ID` bigint(11) DEFAULT NULL,
  `CREATE_TIME` datetime DEFAULT NULL,
  `UPDATE_TIME` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=74 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='菜单角色表';

-- ----------------------------
-- Records of t_menu_role
-- ----------------------------
INSERT INTO `t_menu_role` VALUES ('1', '1', '1', '1', null, null, null, null, null, null);
INSERT INTO `t_menu_role` VALUES ('2', '1', '2', '1', null, null, null, null, null, null);
INSERT INTO `t_menu_role` VALUES ('3', '1', '3', '1', null, null, null, null, null, null);
INSERT INTO `t_menu_role` VALUES ('4', '1', '4', '1', null, null, null, null, null, null);
INSERT INTO `t_menu_role` VALUES ('5', '1', '5', '1', null, null, null, null, null, null);
INSERT INTO `t_menu_role` VALUES ('6', '1', '6', '1', null, null, null, null, null, null);
INSERT INTO `t_menu_role` VALUES ('7', '1', '7', '1', null, null, null, null, null, null);
INSERT INTO `t_menu_role` VALUES ('8', '1', '8', '1', null, null, null, null, null, null);
INSERT INTO `t_menu_role` VALUES ('9', '1', '9', '1', null, null, null, null, null, null);
INSERT INTO `t_menu_role` VALUES ('10', '1', '10', '1', null, null, null, null, null, null);
INSERT INTO `t_menu_role` VALUES ('11', '1', '11', '1', null, null, null, null, null, null);
INSERT INTO `t_menu_role` VALUES ('12', '1', '12', '1', null, null, null, null, null, null);
INSERT INTO `t_menu_role` VALUES ('13', '1', '13', '1', null, null, null, null, null, null);
INSERT INTO `t_menu_role` VALUES ('14', '1', '14', '1', null, null, null, null, null, null);
INSERT INTO `t_menu_role` VALUES ('16', '1', '16', '1', null, null, null, null, null, null);
INSERT INTO `t_menu_role` VALUES ('17', '1', '17', '1', null, null, null, null, null, null);
INSERT INTO `t_menu_role` VALUES ('18', '1', '18', '1', null, null, null, null, null, null);
INSERT INTO `t_menu_role` VALUES ('19', '1', '19', '1', null, null, null, null, null, null);
INSERT INTO `t_menu_role` VALUES ('20', '1', '20', '1', null, null, null, null, null, null);
INSERT INTO `t_menu_role` VALUES ('21', '1', '21', '1', null, null, null, null, null, null);
INSERT INTO `t_menu_role` VALUES ('22', '1', '220', '1', null, null, null, null, null, null);
INSERT INTO `t_menu_role` VALUES ('23', '1', '23', '1', null, null, null, null, null, null);
INSERT INTO `t_menu_role` VALUES ('24', '1', '24', '1', null, null, null, null, null, null);
INSERT INTO `t_menu_role` VALUES ('25', '1', '25', '1', null, null, null, null, null, null);
INSERT INTO `t_menu_role` VALUES ('26', '1', '26', '1', null, null, null, null, null, null);
INSERT INTO `t_menu_role` VALUES ('27', '1', '27', '1', null, null, null, null, null, null);
INSERT INTO `t_menu_role` VALUES ('28', '1', '28', '1', null, null, null, null, null, null);
INSERT INTO `t_menu_role` VALUES ('29', '1', '29', '1', null, null, null, null, null, null);
INSERT INTO `t_menu_role` VALUES ('30', '1', '30', '1', null, null, null, null, null, null);
INSERT INTO `t_menu_role` VALUES ('31', '1', '31', '1', null, null, null, null, null, null);
INSERT INTO `t_menu_role` VALUES ('32', '1', '32', '1', null, null, null, null, null, null);
INSERT INTO `t_menu_role` VALUES ('33', '1', '33', '1', null, null, null, null, null, null);
INSERT INTO `t_menu_role` VALUES ('34', '1', '34', '1', null, null, null, null, null, null);
INSERT INTO `t_menu_role` VALUES ('35', '1', '35', '1', null, null, null, null, null, null);
INSERT INTO `t_menu_role` VALUES ('36', '1', '36', '1', null, null, null, null, null, null);
INSERT INTO `t_menu_role` VALUES ('37', '1', '37', '1', null, null, null, null, null, null);
INSERT INTO `t_menu_role` VALUES ('38', '1', '38', '1', null, null, null, null, null, null);
INSERT INTO `t_menu_role` VALUES ('39', '1', '39', '1', null, null, null, null, null, null);
INSERT INTO `t_menu_role` VALUES ('40', '1', '40', '1', null, null, null, null, null, null);
INSERT INTO `t_menu_role` VALUES ('41', '1', '41', '1', null, null, null, null, null, null);
INSERT INTO `t_menu_role` VALUES ('42', '1', '42', '1', null, null, null, null, null, null);
INSERT INTO `t_menu_role` VALUES ('43', '1', '43', '1', null, null, null, null, null, null);
INSERT INTO `t_menu_role` VALUES ('44', '1', '44', '1', null, null, null, null, null, null);
INSERT INTO `t_menu_role` VALUES ('45', '1', '45', '1', null, null, null, null, null, null);
INSERT INTO `t_menu_role` VALUES ('46', '1', '46', '1', null, null, null, null, null, null);
INSERT INTO `t_menu_role` VALUES ('51', '1', '580', '1', null, null, null, null, null, null);
INSERT INTO `t_menu_role` VALUES ('52', '1', '590', '1', null, null, null, null, null, null);
INSERT INTO `t_menu_role` VALUES ('53', '1', '600', '1', null, null, null, null, null, null);
INSERT INTO `t_menu_role` VALUES ('54', '1', '61', '1', null, null, null, null, null, null);
INSERT INTO `t_menu_role` VALUES ('55', '1', '640', '1', null, null, null, null, null, null);
INSERT INTO `t_menu_role` VALUES ('56', '1', '65', '1', null, null, null, null, null, null);
INSERT INTO `t_menu_role` VALUES ('57', '1', '66', '1', null, null, null, null, null, null);
INSERT INTO `t_menu_role` VALUES ('58', '1', '67', '1', null, null, null, null, null, null);
INSERT INTO `t_menu_role` VALUES ('59', '1', '68', '1', null, null, null, null, null, null);
INSERT INTO `t_menu_role` VALUES ('60', '1', '69', '1', null, null, null, null, null, null);
INSERT INTO `t_menu_role` VALUES ('61', '1', '70', '1', null, null, null, null, null, null);
INSERT INTO `t_menu_role` VALUES ('62', '1', '71', '1', null, null, null, null, null, null);
INSERT INTO `t_menu_role` VALUES ('63', '1', '72', '1', null, null, null, null, null, null);
INSERT INTO `t_menu_role` VALUES ('64', '1', '73', '1', null, null, null, null, null, null);
INSERT INTO `t_menu_role` VALUES ('71', '1', '86', '1', 'dhhua', '1', null, null, null, '2017-02-28 10:00:10');
INSERT INTO `t_menu_role` VALUES ('72', '1', '87', '1', 'dhhua', '1', null, null, null, '2017-06-29 17:52:39');
INSERT INTO `t_menu_role` VALUES ('73', '1', '88', '1', 'dhhua', '1', null, null, null, '2017-06-29 18:21:29');

-- ----------------------------
-- Table structure for t_offer
-- ----------------------------
DROP TABLE IF EXISTS `t_offer`;
CREATE TABLE `t_offer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `country_id` int(11) DEFAULT NULL,
  `unit` decimal(8,2) DEFAULT '0.00',
  `price` decimal(8,2) DEFAULT '0.00',
  `type` int(11) DEFAULT '0' COMMENT '0：当前报价 1：历史报价',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `country_id` (`country_id`)
) ENGINE=InnoDB AUTO_INCREMENT=80 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_offer
-- ----------------------------
INSERT INTO `t_offer` VALUES ('1', '1', '10000.00', '0.00', '1', '2017-10-20 13:24:30');
INSERT INTO `t_offer` VALUES ('2', '2', '100.00', '0.00', '0', '2017-10-20 13:15:35');
INSERT INTO `t_offer` VALUES ('3', '3', '100.00', '347.00', '0', '2017-10-16 12:19:57');
INSERT INTO `t_offer` VALUES ('4', '4', '100.00', '545.00', '0', '2017-10-16 12:19:57');
INSERT INTO `t_offer` VALUES ('5', '5', '100.00', '468.00', '0', '2017-10-16 12:19:57');
INSERT INTO `t_offer` VALUES ('6', '6', '100.00', '453.00', '0', '2017-10-16 12:19:57');
INSERT INTO `t_offer` VALUES ('7', '7', '100.00', '0.00', '0', '2017-10-16 14:10:15');
INSERT INTO `t_offer` VALUES ('8', '8', '100.00', '545.00', '0', '2017-10-16 12:19:57');
INSERT INTO `t_offer` VALUES ('9', '9', '1000.00', '650.00', '0', '2017-10-16 12:19:57');
INSERT INTO `t_offer` VALUES ('10', '10', '0.00', '431.00', '0', '2017-10-16 12:19:57');
INSERT INTO `t_offer` VALUES ('68', '1', '888.00', '888.00', '1', '2017-10-23 15:16:58');
INSERT INTO `t_offer` VALUES ('69', '1', '999.00', '999.00', '1', '2017-10-23 15:39:11');
INSERT INTO `t_offer` VALUES ('70', '1', '555.00', '555.00', '1', '2017-10-23 15:40:25');
INSERT INTO `t_offer` VALUES ('72', '1', '6666.00', '6666.00', '1', '2017-10-23 19:03:13');
INSERT INTO `t_offer` VALUES ('74', '1', '555.00', '555.00', '1', '2017-10-24 09:32:00');
INSERT INTO `t_offer` VALUES ('75', '1', '111.00', '111.00', '1', '2017-10-24 09:52:41');
INSERT INTO `t_offer` VALUES ('76', '1', '789.00', '789.00', '1', '2017-10-24 17:21:41');
INSERT INTO `t_offer` VALUES ('77', '1', '567.00', '567.00', '1', '2017-10-24 17:24:19');
INSERT INTO `t_offer` VALUES ('78', '1', '345.00', '345.00', '1', '2017-10-24 17:26:30');
INSERT INTO `t_offer` VALUES ('79', '1', '234.00', '234.00', '0', '2017-10-24 17:31:36');

-- ----------------------------
-- Table structure for t_question
-- ----------------------------
DROP TABLE IF EXISTS `t_question`;
CREATE TABLE `t_question` (
  `id` int(11) NOT NULL,
  `question` varchar(255) DEFAULT NULL,
  `type` int(11) DEFAULT NULL COMMENT '0：中文 1：英文',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='美版';

-- ----------------------------
-- Records of t_question
-- ----------------------------
INSERT INTO `t_question` VALUES ('1', '{id: \"130\", question: \"你少年时代最好的朋友叫什么名字？\", number: 1}', '0', '2017-10-14 11:38:41');
INSERT INTO `t_question` VALUES ('2', '\r{id: \"131\", question: \"你的第一个宠物叫什么名字？\", number: 1}', '0', '2017-09-28 10:27:17');
INSERT INTO `t_question` VALUES ('3', '\r{id: \"132\", question: \"你学会做的第一道菜是什么？\", number: 1}', '0', '2017-09-28 10:27:18');
INSERT INTO `t_question` VALUES ('4', '\r{id: \"133\", question: \"你第一次去电影院看的是哪一部电影？\", number: 1}', '0', '2017-09-28 10:27:19');
INSERT INTO `t_question` VALUES ('5', '\r{id: \"134\", question: \"你第一次坐飞机是去哪里？\", number: 1}', '0', '2017-09-28 10:27:20');
INSERT INTO `t_question` VALUES ('6', '\r{id: \"135\", question: \"你上小学时最喜欢的老师姓什么？\", number: 1}', '0', '2017-09-28 10:27:21');
INSERT INTO `t_question` VALUES ('7', '\r{id: \"136\", question: \"你的理想工作是什么？\", number: 2}', '0', '2017-09-28 10:27:22');
INSERT INTO `t_question` VALUES ('8', '\r{id: \"137\", question: \"你小时候最喜欢哪一本书？\", number: 2}', '0', '2017-09-28 10:27:22');
INSERT INTO `t_question` VALUES ('9', '\r{id: \"138\", question: \"你拥有的第一辆车是什么型号？\", number: 2}', '0', '2017-09-28 10:27:23');
INSERT INTO `t_question` VALUES ('10', '\r{id: \"139\", question: \"你童年时代的绰号是什么？\", number: 2}', '0', '2017-09-28 10:27:23');
INSERT INTO `t_question` VALUES ('11', '{id: \"140\", question: \"你在学生时代最喜欢哪个电影明星或角色？\", number: 2}', '0', '2017-09-28 10:27:24');
INSERT INTO `t_question` VALUES ('12', '\r{id: \"141\", question: \"你在学生时代最喜欢哪个歌手或乐队？\", number: 2}', '0', '2017-09-28 10:27:25');
INSERT INTO `t_question` VALUES ('13', '\r{id: \"142\", question: \"你的父母是在哪里认识的？\", number: 3}', '0', '2017-09-28 10:27:27');
INSERT INTO `t_question` VALUES ('14', '\r{id: \"143\", question: \"你的第一个上司叫什么名字？\", number: 3}', '0', '2017-09-28 10:27:26');
INSERT INTO `t_question` VALUES ('15', '\r{id: \"144\", question: \"您从小长大的那条街叫什么？\", number: 3}', '0', '2017-09-28 10:27:28');
INSERT INTO `t_question` VALUES ('16', '\r{id: \"145\", question: \"你去过的第一个海滨浴场是哪一个？\", number: 3}', '0', '2017-09-28 10:27:29');
INSERT INTO `t_question` VALUES ('17', '\r{id: \"146\", question: \"你购买的第一张专辑是什么？\", number: 3}', '0', '2017-09-28 10:27:29');
INSERT INTO `t_question` VALUES ('18', '\r{id: \"147\", question: \"您最喜欢哪个球队？\", number: 3}', '0', '2017-09-28 10:27:32');
INSERT INTO `t_question` VALUES ('20', '{id: \"19\", question: \"What is your favorite children’s book?\", number: 1}', '1', '2017-10-09 15:49:23');
INSERT INTO `t_question` VALUES ('21', '{id: \"20\", question: \"What is your dream job?\", number: 1}', '1', '2017-10-09 15:49:23');
INSERT INTO `t_question` VALUES ('22', '{id: \"21\", question: \"What was your childhood nickname?\", number: 1}', '1', '2017-10-09 15:49:23');
INSERT INTO `t_question` VALUES ('23', '{id: \"22\", question: \"What was the model of your first car?\", number: 1}', '1', '2017-10-09 15:49:23');
INSERT INTO `t_question` VALUES ('24', '{id: \"23\", question: \"Who was your favorite singer or band in high school?\", number: 1}', '1', '2017-10-09 15:49:23');
INSERT INTO `t_question` VALUES ('25', '{id: \"24\", question: \"Who was your favorite film star or character in school?\", number: 1}', '1', '2017-10-09 15:49:23');
INSERT INTO `t_question` VALUES ('26', '{id: \"25\", question: \"What was the first name of your first boss?\", number: 2}', '1', '2017-10-09 15:49:23');
INSERT INTO `t_question` VALUES ('27', '{id: \"26\", question: \"In what city did your parents meet?\", number: 2}', '1', '2017-10-09 15:49:23');
INSERT INTO `t_question` VALUES ('28', '{id: \"27\", question: \"What was the name of your first pet?\", number: 2}', '1', '2017-10-09 15:49:23');
INSERT INTO `t_question` VALUES ('29', '{id: \"28\", question: \"What is the first name of your best friend in high school?\", number: 2}', '1', '2017-10-09 15:49:23');
INSERT INTO `t_question` VALUES ('30', '{id: \"29\", question: \"What was the first film you saw in the theater?\", number: 2}', '1', '2017-10-09 15:49:23');
INSERT INTO `t_question` VALUES ('31', '{id: \"30\", question: \"What was the first thing you learned to cook?\", number: 2}', '1', '2017-10-09 15:49:23');
INSERT INTO `t_question` VALUES ('32', '{id: \"31\", question: \"What is the last name of your favorite elementary school teacher?\", number: 3}', '1', '2017-10-09 15:49:23');
INSERT INTO `t_question` VALUES ('33', '{id: \"32\", question: \"Where did you go the first time you flew on a plane?\", number: 3}', '1', '2017-10-09 15:49:23');
INSERT INTO `t_question` VALUES ('34', '{id: \"33\", question: \"What is the name of the street where you grew up?\", number: 3}', '1', '2017-10-09 15:49:23');
INSERT INTO `t_question` VALUES ('35', '{id: \"34\", question: \"What is the name of the first beach you visited?\", number: 3}', '1', '2017-10-09 15:49:23');
INSERT INTO `t_question` VALUES ('36', '{id: \"35\", question: \"What was the first album that you purchased?\", number: 3}', '1', '2017-10-09 15:49:24');
INSERT INTO `t_question` VALUES ('37', '{id: \"36\", question: \"What is the name of your favorite sports team?\", number: 3}', '1', '2017-10-09 15:49:24');

-- ----------------------------
-- Table structure for t_question_copy
-- ----------------------------
DROP TABLE IF EXISTS `t_question_copy`;
CREATE TABLE `t_question_copy` (
  `id` int(11) NOT NULL,
  `question` varchar(255) DEFAULT NULL,
  `type` int(11) DEFAULT NULL COMMENT '0：中文 1：英文',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='英版';

-- ----------------------------
-- Records of t_question_copy
-- ----------------------------
INSERT INTO `t_question_copy` VALUES ('1', '\r{id: \"130\", question: \"你少年时代最好的朋友叫什么名字？\", number: 1}', '0', '2017-09-28 10:27:16');
INSERT INTO `t_question_copy` VALUES ('2', '\r{id: \"131\", question: \"你的第一个宠物叫什么名字？\", number: 1}', '0', '2017-09-28 10:27:17');
INSERT INTO `t_question_copy` VALUES ('3', '\r{id: \"132\", question: \"你学会做的第一道菜是什么？\", number: 1}', '0', '2017-09-28 10:27:18');
INSERT INTO `t_question_copy` VALUES ('4', '\r{id: \"133\", question: \"你第一次去电影院看的是哪一部电影？\", number: 1}', '0', '2017-09-28 10:27:19');
INSERT INTO `t_question_copy` VALUES ('5', '\r{id: \"134\", question: \"你第一次坐飞机是去哪里？\", number: 1}', '0', '2017-09-28 10:27:20');
INSERT INTO `t_question_copy` VALUES ('6', '\r{id: \"135\", question: \"你上小学时最喜欢的老师姓什么？\", number: 1}', '0', '2017-09-28 10:27:21');
INSERT INTO `t_question_copy` VALUES ('7', '\r{id: \"136\", question: \"你的理想工作是什么？\", number: 2}', '0', '2017-09-28 10:27:22');
INSERT INTO `t_question_copy` VALUES ('8', '\r{id: \"137\", question: \"你小时候最喜欢哪一本书？\", number: 2}', '0', '2017-09-28 10:27:22');
INSERT INTO `t_question_copy` VALUES ('9', '\r{id: \"138\", question: \"你拥有的第一辆车是什么型号？\", number: 2}', '0', '2017-09-28 10:27:23');
INSERT INTO `t_question_copy` VALUES ('10', '\r{id: \"139\", question: \"你童年时代的绰号是什么？\", number: 2}', '0', '2017-09-28 10:27:23');
INSERT INTO `t_question_copy` VALUES ('11', '{id: \"140\", question: \"你在学生时代最喜欢哪个电影明星或角色？\", number: 2}', '0', '2017-09-28 10:27:24');
INSERT INTO `t_question_copy` VALUES ('12', '\r{id: \"141\", question: \"你在学生时代最喜欢哪个歌手或乐队？\", number: 2}', '0', '2017-09-28 10:27:25');
INSERT INTO `t_question_copy` VALUES ('13', '\r{id: \"142\", question: \"你的父母是在哪里认识的？\", number: 3}', '0', '2017-09-28 10:27:27');
INSERT INTO `t_question_copy` VALUES ('14', '\r{id: \"143\", question: \"你的第一个上司叫什么名字？\", number: 3}', '0', '2017-09-28 10:27:26');
INSERT INTO `t_question_copy` VALUES ('15', '\r{id: \"144\", question: \"您从小长大的那条街叫什么？\", number: 3}', '0', '2017-09-28 10:27:28');
INSERT INTO `t_question_copy` VALUES ('16', '\r{id: \"145\", question: \"你去过的第一个海滨浴场是哪一个？\", number: 3}', '0', '2017-09-28 10:27:29');
INSERT INTO `t_question_copy` VALUES ('17', '\r{id: \"146\", question: \"你购买的第一张专辑是什么？\", number: 3}', '0', '2017-09-28 10:27:29');
INSERT INTO `t_question_copy` VALUES ('18', '\r{id: \"147\", question: \"您最喜欢哪个球队？\", number: 3}', '0', '2017-09-28 10:27:32');
INSERT INTO `t_question_copy` VALUES ('20', '{id: \"19\", question: \"What was the name of your best friend as a teenager?\", number: 1}', '1', '2017-10-09 18:15:04');
INSERT INTO `t_question_copy` VALUES ('21', '{id: \"20\", question: \"What was the name of your first pet?\", number: 1}', '1', '2017-10-09 18:15:16');
INSERT INTO `t_question_copy` VALUES ('22', '{id: \"21\", question: \"What was the first dish you learned to cook?\", number: 1}', '1', '2017-10-09 18:15:30');
INSERT INTO `t_question_copy` VALUES ('23', '{id: \"22\", question: \"What was the first film you saw in the cinema?\", number: 1}', '1', '2017-10-09 18:15:41');
INSERT INTO `t_question_copy` VALUES ('24', '{id: \"23\", question: \"Where did you go the first time you flew on an airplane??\", number: 1}', '1', '2017-10-09 18:15:52');
INSERT INTO `t_question_copy` VALUES ('25', '{id: \"24\", question: \"What is the name of your favourite elementary or primary school teacher?\", number: 1}', '1', '2017-10-09 18:16:00');
INSERT INTO `t_question_copy` VALUES ('26', '{id: \"25\", question: \"What is your dream job?\", number: 2}', '1', '2017-10-09 18:16:25');
INSERT INTO `t_question_copy` VALUES ('27', '{id: \"26\", question: \"What is your favourite children’s book?\", number: 2}', '1', '2017-10-09 18:16:36');
INSERT INTO `t_question_copy` VALUES ('28', '{id: \"27\", question: \"What was the model of your first motorised vehicle?\", number: 2}', '1', '2017-10-09 18:16:43');
INSERT INTO `t_question_copy` VALUES ('29', '{id: \"28\", question: \"What was your childhood nickname?\", number: 2}', '1', '2017-10-09 18:17:00');
INSERT INTO `t_question_copy` VALUES ('30', '{id: \"29\", question: \"Who was your favourite film star or character in school?\", number: 2}', '1', '2017-10-09 18:17:10');
INSERT INTO `t_question_copy` VALUES ('31', '{id: \"30\", question: \"Who was your favourite singer or band in school?\", number: 2}', '1', '2017-10-09 18:17:23');
INSERT INTO `t_question_copy` VALUES ('32', '{id: \"31\", question: \"In what city did your parents meet?\", number: 3}', '1', '2017-10-09 18:17:35');
INSERT INTO `t_question_copy` VALUES ('33', '{id: \"32\", question: \"What was the name of your first manager?\", number: 3}', '1', '2017-10-09 18:17:53');
INSERT INTO `t_question_copy` VALUES ('34', '{id: \"33\", question: \"What is the name of the street where you grew up?\", number: 3}', '1', '2017-10-09 15:49:23');
INSERT INTO `t_question_copy` VALUES ('35', '{id: \"34\", question: \"What is the name of the first beach you visited?\", number: 3}', '1', '2017-10-09 15:49:23');
INSERT INTO `t_question_copy` VALUES ('36', '{id: \"35\", question: \"What was the first album that you purchased?\", number: 3}', '1', '2017-10-09 15:49:24');
INSERT INTO `t_question_copy` VALUES ('37', '{id: \"36\", question: \"What is the name of your favorite sports team?\", number: 3}', '1', '2017-10-09 15:49:24');

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(64) COLLATE utf8_bin NOT NULL,
  `SN` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `GROUP_ID` bigint(20) NOT NULL,
  `GROUP_NAME` varchar(64) COLLATE utf8_bin NOT NULL,
  `STATE` int(11) NOT NULL,
  `CREATER` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `CREATER_ID` bigint(11) DEFAULT NULL,
  `OPERATOR` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `OPERATOR_ID` bigint(11) DEFAULT NULL,
  `CREATE_TIME` datetime DEFAULT NULL,
  `UPDATE_TIME` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `NAME` (`NAME`,`SN`,`GROUP_ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='角色表';

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('1', '超级管理员', null, '1', '超级管理员组', '1', '董航华', '1', '董航华', '1', null, '2017-01-11 18:51:42');
INSERT INTO `t_role` VALUES ('22', '小丫', null, '12', '客服组', '1', '董航华', '1', null, null, '2017-01-23 18:23:46', null);
INSERT INTO `t_role` VALUES ('23', '小欣', null, '12', '客服组', '1', '董航华', '1', null, null, '2017-01-23 18:24:01', null);
INSERT INTO `t_role` VALUES ('26', '小紫', null, '22', '售后组', '1', 'dhhua', '1', null, null, '2017-03-13 16:14:24', null);
INSERT INTO `t_role` VALUES ('27', '大强', null, '22', '售后组', '1', null, null, 'dhhua', '1', null, '2017-03-13 16:28:44');

-- ----------------------------
-- Table structure for t_settlement
-- ----------------------------
DROP TABLE IF EXISTS `t_settlement`;
CREATE TABLE `t_settlement` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account_id` int(11) NOT NULL,
  `amount` decimal(8,2) DEFAULT NULL,
  `status` int(11) DEFAULT '1' COMMENT '1,审核中 2，审核通过',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `operator` int(11) DEFAULT NULL,
  `modify_time` timestamp NULL DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=82 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_settlement
-- ----------------------------
INSERT INTO `t_settlement` VALUES ('78', '12', null, '1', null, '0', '2017-10-20 11:34:01', '2017-10-17 10:49:29');
INSERT INTO `t_settlement` VALUES ('81', '17', null, '2', null, '2', '2017-10-20 14:34:26', '2017-10-20 09:55:02');

-- ----------------------------
-- Table structure for t_sys_user
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_user`;
CREATE TABLE `t_sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `mobile` varchar(45) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_user_username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sys_user
-- ----------------------------
INSERT INTO `t_sys_user` VALUES ('1', 'super', 'a294763bc6d9c0d6d8b57a3585579410', '超级管理员', null, null, '1', '2017-09-04 10:35:19', null);
INSERT INTO `t_sys_user` VALUES ('2', 'admin', '439b9b33eb18d644f3b57e182f45b86a', '管理员', null, null, '1', '2017-09-04 10:35:19', null);
INSERT INTO `t_sys_user` VALUES ('3', 'huangqf', 'ec642c18c8ef5665d05369621c888346', 'huangqf', '', null, '0', '2017-09-01 10:42:43', '2017-09-06 14:19:25');
INSERT INTO `t_sys_user` VALUES ('4', '33', '5d66e6a326f7dfef80dea553d83736ca', 'dsds', '2564@q564', '1356562323', '1', '2017-09-01 10:54:50', null);
INSERT INTO `t_sys_user` VALUES ('5', 'hhhh', '52c95cde0a57bd0567dde04a877eeb0a', '舒适的飞机', '', '', '1', '2017-09-01 18:48:32', null);
INSERT INTO `t_sys_user` VALUES ('6', '14', 'e9a7a843fd23a2c0df64e0cd03ad55b8', '22221', '2', '2', '1', '2017-09-04 14:00:31', '2017-09-04 14:46:15');
INSERT INTO `t_sys_user` VALUES ('7', 'huang111', 'ec642c18c8ef5665d05369621c888346', '黄乔飞', '', '', '1', '2017-09-04 14:03:28', '2017-09-06 15:43:21');
INSERT INTO `t_sys_user` VALUES ('8', 'machine', 'ae707272191b193f3535f4dd6cda006b', 'machine', '', '', '1', '2017-09-07 18:04:26', null);
INSERT INTO `t_sys_user` VALUES ('9', 'boss', '5844591dff62349ce65f98c60baa669e', '老板', null, '12345678912', null, '2017-10-11 09:30:07', null);
INSERT INTO `t_sys_user` VALUES ('10', 'Finance', '5844591dff62349ce65f98c60baa669e', '财务', null, '12345678900', null, '2017-10-11 09:31:11', null);

-- ----------------------------
-- Table structure for t_task
-- ----------------------------
DROP TABLE IF EXISTS `t_task`;
CREATE TABLE `t_task` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `account_id` int(11) DEFAULT NULL,
  `amount` decimal(10,2) DEFAULT '0.00',
  `status` int(11) DEFAULT '0' COMMENT '状态，0：入库中 1：入库完成 2：入库失败 3：停止',
  `code` int(11) DEFAULT NULL COMMENT '入库编号',
  `type` int(11) DEFAULT '0' COMMENT '0：热血传奇 1：王者荣耀',
  `modify_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `account_id` (`account_id`) USING BTREE,
  KEY `user_id` (`user_id`) USING BTREE,
  KEY `status` (`status`) USING BTREE,
  KEY `create_time` (`modify_time`,`create_time`) USING BTREE,
  KEY `code` (`code`),
  KEY `amount` (`amount`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_task
-- ----------------------------

-- ----------------------------
-- Table structure for t_user_bill
-- ----------------------------
DROP TABLE IF EXISTS `t_user_bill`;
CREATE TABLE `t_user_bill` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `amount` decimal(11,2) DEFAULT NULL,
  `balance` decimal(11,2) DEFAULT '0.00',
  `country_id` int(11) NOT NULL,
  `type` int(11) DEFAULT '0' COMMENT '0 支出，1收入',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `userid` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user_bill
-- ----------------------------

-- ----------------------------
-- Table structure for t_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user_role
-- ----------------------------
INSERT INTO `t_user_role` VALUES ('1', '1', '1', '2017-10-26 10:47:01', null);
