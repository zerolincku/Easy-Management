/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50729
 Source Host           : localhost:3306
 Source Schema         : mymanagement

 Target Server Type    : MySQL
 Target Server Version : 50729
 File Encoding         : 65001

 Date: 15/10/2020 00:00:09
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_job
-- ----------------------------
DROP TABLE IF EXISTS `sys_job`;
CREATE TABLE `sys_job`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `job_class` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'job类路径',
  `name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'job名字',
  `description` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '描述',
  `cron` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'cron',
  `state` tinyint(1) NOT NULL DEFAULT 0 COMMENT '启用状态(1-启用 0-禁用)',
  `trigger_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '触发器name',
  `trigger_group` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '触发器组',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `unique_job_class`(`job_class`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_job
-- ----------------------------
INSERT INTO `sys_job` VALUES (1, 'com.linck.management.quartz.job.TestJob', 'TestJob', NULL, NULL, 0, NULL, NULL, '2020-10-14 22:39:15', '2020-10-14 22:39:15');

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission`  (
  `id` bigint(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `pid` bigint(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '父级权限id',
  `name` char(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '名称',
  `value` char(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '内容',
  `type` tinyint(1) NOT NULL DEFAULT 1 COMMENT '类型 1-菜单 2-按钮 3-权限',
  `icon` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '图标',
  `uri` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '前端资源路径',
  `state` tinyint(1) NOT NULL DEFAULT 1 COMMENT '状态 1-启用 0-禁用',
  `sort` int(1) NOT NULL DEFAULT 0 COMMENT '排序',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES (1, 0, '用户管理', NULL, 1, NULL, NULL, 1, 0, '2020-08-09 23:49:49', '2020-08-09 23:49:49');
INSERT INTO `sys_permission` VALUES (2, 1, '用户列表', NULL, 2, NULL, NULL, 1, 0, '2020-08-09 23:49:55', '2020-08-09 23:49:55');
INSERT INTO `sys_permission` VALUES (3, 2, '产看用户', 'user:view', 3, NULL, NULL, 1, 0, '2020-08-10 18:56:46', '2020-08-10 18:56:46');
INSERT INTO `sys_permission` VALUES (4, 2, '新增用户', 'user:add', 3, NULL, NULL, 1, 0, '2020-08-10 18:57:49', '2020-08-10 18:57:49');
INSERT INTO `sys_permission` VALUES (5, 2, '删除用户', 'user:delete', 3, NULL, NULL, 1, 0, '2020-08-10 18:58:07', '2020-08-10 18:58:07');
INSERT INTO `sys_permission` VALUES (6, 2, '修改用户', 'user:update', 3, NULL, NULL, 1, 0, '2020-08-10 18:58:27', '2020-08-10 18:58:27');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` bigint(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `name` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '角色名称',
  `description` char(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '描述',
  `state` tinyint(3) UNSIGNED NOT NULL DEFAULT 1 COMMENT '状态 1-启用 0-禁用',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, 'ROLE_ADMIN', '系统管理员', 1, '2020-08-09 23:40:11', '2020-08-09 23:40:11');

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `r_id` bigint(20) UNSIGNED NOT NULL COMMENT '角色id',
  `p_id` bigint(20) UNSIGNED NOT NULL COMMENT '权限id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
INSERT INTO `sys_role_permission` VALUES (1, 1, 1);
INSERT INTO `sys_role_permission` VALUES (2, 1, 2);
INSERT INTO `sys_role_permission` VALUES (3, 1, 3);
INSERT INTO `sys_role_permission` VALUES (4, 1, 4);
INSERT INTO `sys_role_permission` VALUES (5, 1, 5);
INSERT INTO `sys_role_permission` VALUES (6, 1, 6);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` bigint(255) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `account` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '账号',
  `pwd` char(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '密码',
  `state` tinyint(4) NOT NULL DEFAULT 1 COMMENT '状态 1-启用 0-禁用',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 69 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'admin', '$2a$10$oXf/01CfrZKT2r.Vr8//nuBO7L7AzmUSaAkROMRlsqCytEvLMc89e', 1, '2020-08-10 17:15:48', '2020-08-10 17:15:48');
INSERT INTO `sys_user` VALUES (2, 'admin1', '$2a$10$/.hV6b0BhC4adV7TqCW5OuNlYnNKSytl3Q4EznD4xupuBXmk9u5Fi', 1, '2020-08-10 17:25:44', '2020-08-10 17:25:44');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `u_id` bigint(20) UNSIGNED NOT NULL COMMENT '用户id',
  `r_id` bigint(20) UNSIGNED NOT NULL COMMENT '角色id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1, 1, 1);

SET FOREIGN_KEY_CHECKS = 1;
