## springboot 2.0.7 整合 ehcache3.5.3(二级缓存)+redis(一级缓存)
## 无注解无xml,以配置类的形式
## sql语句
/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80012
 Source Host           : localhost:3306
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 80012
 File Encoding         : 65001

 Date: 13/01/2019 21:20:34
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'yzx', 'aaa');
INSERT INTO `user` VALUES ('9c037d67-2001-4ff3-8e8f-7b40b937cb12', 'insert1111', '111122');
INSERT INTO `user` VALUES ('b8cd9efe-357c-40aa-b5cd-ca0b3e28d345', 'dadada', 'da');
INSERT INTO `user` VALUES ('d74c7a9c-743d-438d-8abf-6fc716454506', '104aa', '444');

SET FOREIGN_KEY_CHECKS = 1;
