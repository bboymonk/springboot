/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50636
Source Host           : localhost:3306
Source Database       : buyback

Target Server Type    : MYSQL
Target Server Version : 50636
File Encoding         : 65001

Date: 2017-10-26 09:45:38
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
  `salt` varchar(45) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `mobile` varchar(45) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL COMMENT '0管理员  1充值人员 2主管 3财务',
  `status` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_user_username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sys_user
-- ----------------------------
INSERT INTO `t_sys_user` VALUES ('1', 'super', '5844591dff62349ce65f98c60baa669e', 'e8z0i', '超级管理员', null, null, '2', '1', '2017-09-04 10:35:19', null);
INSERT INTO `t_sys_user` VALUES ('2', 'admin', '439b9b33eb18d644f3b57e182f45b86a', 'bycca', '管理员', null, null, '0', '1', '2017-09-04 10:35:19', null);
INSERT INTO `t_sys_user` VALUES ('3', 'huangqf', 'ec642c18c8ef5665d05369621c888346', '3tqob', 'huangqf', '', null, '1', '0', '2017-09-01 10:42:43', '2017-09-06 14:19:25');
INSERT INTO `t_sys_user` VALUES ('4', '33', '5d66e6a326f7dfef80dea553d83736ca', 'k27z4', 'dsds', '2564@q564', '1356562323', '1', '1', '2017-09-01 10:54:50', null);
INSERT INTO `t_sys_user` VALUES ('5', 'hhhh', '52c95cde0a57bd0567dde04a877eeb0a', '18g4s', '舒适的飞机', '', '', '1', '1', '2017-09-01 18:48:32', null);
INSERT INTO `t_sys_user` VALUES ('6', '14', 'e9a7a843fd23a2c0df64e0cd03ad55b8', 'enb9c', '22221', '2', '2', '1', '1', '2017-09-04 14:00:31', '2017-09-04 14:46:15');
INSERT INTO `t_sys_user` VALUES ('7', 'huang111', 'ec642c18c8ef5665d05369621c888346', '0279o', '黄乔飞', '', '', '1', '1', '2017-09-04 14:03:28', '2017-09-06 15:43:21');
INSERT INTO `t_sys_user` VALUES ('8', 'machine', 'ae707272191b193f3535f4dd6cda006b', 'k5li6', 'machine', '', '', '1', '1', '2017-09-07 18:04:26', null);
INSERT INTO `t_sys_user` VALUES ('9', 'boss', '5844591dff62349ce65f98c60baa669e', 'e8z0i', '老板', null, '12345678912', '2', null, '2017-10-11 09:30:07', null);
INSERT INTO `t_sys_user` VALUES ('10', 'Finance', '5844591dff62349ce65f98c60baa669e', 'e8z0i', '财务', null, '12345678900', '3', null, '2017-10-11 09:31:11', null);

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
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) NOT NULL COMMENT '用户id',
  `token` varchar(64) CHARACTER SET utf8 DEFAULT NULL,
  `status` int(11) DEFAULT '0' COMMENT '0未开通，1开通, 2 状态锁定',
  `modify_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '0：自注册用户，1：qq，2：微信',
  PRIMARY KEY (`id`),
  KEY `create_time` (`create_time`),
  KEY `uid` (`uid`),
  KEY `token` (`token`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('24', '100018', '7d07686c63eaf368be830ab2b5d04fbd788d30f9a83d874953598bd06d3a08a0', '1', '2017-10-20 09:34:21', '2017-10-19 09:27:00');
INSERT INTO `t_user` VALUES ('25', '100019', '6f2cf98655bdf29050a0660adca15ebd788d30f9a83d874953598bd06d3a08a0', '1', '2017-10-19 09:31:01', '2017-10-19 09:29:02');
INSERT INTO `t_user` VALUES ('26', '100020', '6f2cf98655bdf29050a0660adca15ebd788d30f9a83d874953598bd06d3a08a0', '1', null, '2017-10-24 09:19:38');
INSERT INTO `t_user` VALUES ('27', '100021', '6f2cf98655bdf29050a0660adca15ebd788d30f9a83d874953598bd06d3a08a0', '1', null, '2017-10-24 09:19:38');
INSERT INTO `t_user` VALUES ('28', '100022', '6f2cf98655bdf29050a0660adca15ebd788d30f9a83d874953598bd06d3a08a0', '1', null, '2017-10-24 09:19:38');
INSERT INTO `t_user` VALUES ('29', '100023', '6f2cf98655bdf29050a0660adca15ebd788d30f9a83d874953598bd06d3a08a0', '1', null, '2017-10-24 09:19:38');
INSERT INTO `t_user` VALUES ('30', '100024', '6f2cf98655bdf29050a0660adca15ebd788d30f9a83d874953598bd06d3a08a0', '1', null, '2017-10-24 09:19:38');
INSERT INTO `t_user` VALUES ('31', '100025', '6f2cf98655bdf29050a0660adca15ebd788d30f9a83d874953598bd06d3a08a0', '1', null, '2017-10-24 09:19:38');
INSERT INTO `t_user` VALUES ('32', '100026', '6f2cf98655bdf29050a0660adca15ebd788d30f9a83d874953598bd06d3a08a0', '1', null, '2017-10-24 09:19:38');
INSERT INTO `t_user` VALUES ('33', '100027', '6f2cf98655bdf29050a0660adca15ebd788d30f9a83d874953598bd06d3a08a0', '1', null, '2017-10-24 09:19:38');
INSERT INTO `t_user` VALUES ('34', '100028', '6f2cf98655bdf29050a0660adca15ebd788d30f9a83d874953598bd06d3a08a0', '1', null, '2017-10-24 09:19:38');
INSERT INTO `t_user` VALUES ('35', '100029', '6f2cf98655bdf29050a0660adca15ebd788d30f9a83d874953598bd06d3a08a0', null, '2017-10-24 09:21:15', '2017-10-24 09:19:38');
INSERT INTO `t_user` VALUES ('36', '100030', '6f2cf98655bdf29050a0660adca15ebd788d30f9a83d874953598bd06d3a08a0', null, '2017-10-24 09:21:15', '2017-10-24 09:19:38');
INSERT INTO `t_user` VALUES ('37', '100031', '6f2cf98655bdf29050a0660adca15ebd788d30f9a83d874953598bd06d3a08a0', '0', '2017-10-24 09:21:15', '2017-10-24 09:19:38');
INSERT INTO `t_user` VALUES ('38', '100032', '6f2cf98655bdf29050a0660adca15ebd788d30f9a83d874953598bd06d3a08a0', '0', '2017-10-24 09:21:15', '2017-10-24 09:19:38');
INSERT INTO `t_user` VALUES ('39', '100033', '6f2cf98655bdf29050a0660adca15ebd788d30f9a83d874953598bd06d3a08a0', '0', '2017-10-24 09:21:15', '2017-10-24 09:19:38');
INSERT INTO `t_user` VALUES ('40', '100034', '6f2cf98655bdf29050a0660adca15ebd788d30f9a83d874953598bd06d3a08a0', '0', '2017-10-24 09:21:15', '2017-10-24 09:19:38');
INSERT INTO `t_user` VALUES ('41', '100035', '6f2cf98655bdf29050a0660adca15ebd788d30f9a83d874953598bd06d3a08a0', '0', '2017-10-24 09:21:15', '2017-10-24 09:19:38');
INSERT INTO `t_user` VALUES ('42', '100036', '6f2cf98655bdf29050a0660adca15ebd788d30f9a83d874953598bd06d3a08a0', '0', '2017-10-24 09:21:15', '2017-10-24 09:19:38');
INSERT INTO `t_user` VALUES ('43', '100037', '6f2cf98655bdf29050a0660adca15ebd788d30f9a83d874953598bd06d3a08a0', '0', '2017-10-24 09:21:15', '2017-10-24 09:19:38');
INSERT INTO `t_user` VALUES ('44', '100039', '6f2cf98655bdf29050a0660adca15ebd788d30f9a83d874953598bd06d3a08a0', '0', '2017-10-24 09:21:15', '2017-10-24 09:19:38');

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
