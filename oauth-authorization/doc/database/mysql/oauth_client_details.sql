/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : localhost:3306
 Source Schema         : highzap_cloud

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : 65001

 Date: 02/02/2021 18:53:13
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for oauth_client_details
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details` (
  `client_id` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `resource_ids` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `client_secret` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `scope` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `authorized_grant_types` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `web_server_redirect_uri` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `authorities` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `access_token_validity` int DEFAULT NULL,
  `refresh_token_validity` int DEFAULT NULL,
  `additional_information` varchar(4096) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `autoapprove` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of oauth_client_details
-- ----------------------------
BEGIN;
INSERT INTO `oauth_client_details` VALUES ('clientId', '', '$2a$10$3rDpNHmtsX3tw7Ay6yIQbOwp3MELtD8ZN61j8ZkLcNrMVrtwDZ5BK', 'snsapi_base,snsapi_userinfo,all', 'authorization_code,refresh_token,password', 'http://localhost:8080,http://localhost:8081,http://192.168.2.81:8080', '', NULL, NULL, '{}', 'snsapi_base,snsapi_userinfo');
INSERT INTO `oauth_client_details` VALUES ('cloud-user-service', '', '$2a$10$FE8hF..zqtzPksdGIRiS0euh3yLuc8XSUL12W1LPBTT92NQQla0.S', 'snsapi_base,snsapi_userinfo', 'authorization_code,refresh_token,password,client_credentials', NULL, '', NULL, NULL, '{}', 'snsapi_base,snsapi_userinfo');
INSERT INTO `oauth_client_details` VALUES ('highzap_cloud', '', '$2a$10$Qp7SY6jP61Zcy.SWe0dZMuMVtfrgJmY3TOuzwzNlLOD8tnDx2Zv.a', 'snsapi_base,snsapi_userinfo', 'authorization_code,refresh_token,password,client_credentials', NULL, '', NULL, NULL, '{}', 'snsapi_base,snsapi_userinfo');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
