/*
 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50729
 Source Host           : localhost:3306
 Source Schema         : mymanagement

 Target Server Type    : MySQL
 Target Server Version : 50729
 File Encoding         : 65001

 Date: 16/11/2020 23:49:13
*/

SET NAMES utf8mb4;
SET
FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_host
-- ----------------------------
DROP TABLE IF EXISTS `sys_host`;
CREATE TABLE `sys_host`
(
    `id`          bigint(20) NOT NULL,
    `host`        varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  NOT NULL COMMENT 'host地址',
    `description` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '描述',
    `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP (0) COMMENT '创建时间',
    `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP (0) COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = 'host表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_host
-- ----------------------------

-- ----------------------------
-- Table structure for sys_job
-- ----------------------------
DROP TABLE IF EXISTS `sys_job`;
CREATE TABLE `sys_job`
(
    `id`           bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `job_class`    varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'job类路径',
    `name`         varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'job名字',
    `description`  varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '描述',
    `cron`         varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'cron',
    `state`        tinyint(3) NOT NULL DEFAULT 0 COMMENT '启用状态(1-启用 0-禁用)',
    `trigger_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '触发器name',
    `group`        varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '触发器组',
    `create_time`  datetime(0) NOT NULL COMMENT '创建时间',
    `update_time`  datetime(0) NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `unique_job_class`(`job_class`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission`
(
    `id`          bigint(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `pid`         bigint(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '父级权限id',
    `name`        char(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '名称',
    `value`       char(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '内容',
    `type`        tinyint(1) NOT NULL DEFAULT 1 COMMENT '类型 1-菜单 2-按钮 3-权限',
    `icon`        varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '图标',
    `url`         varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '前端资源路径',
    `state`       tinyint(1) NOT NULL DEFAULT 1 COMMENT '状态 1-启用 0-禁用',
    `sort`        int(1) NOT NULL DEFAULT 0 COMMENT '排序',
    `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP (0) COMMENT '创建时间',
    `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP (0) COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission`
VALUES (1, 0, '用户管理', NULL, 1, 'el-icon-user', NULL, 1, 0, '2020-08-09 23:49:49', '2020-11-01 14:22:56');
INSERT INTO `sys_permission`
VALUES (2, 1, '测试列表', NULL, 2, NULL, '/first', 1, 0, '2020-08-09 23:49:55', '2020-11-15 20:10:36');
INSERT INTO `sys_permission`
VALUES (7, 0, '系统管理', NULL, 1, 'el-icon-setting', NULL, 1, 11, '2020-10-22 22:47:17', '2020-10-28 23:30:55');
INSERT INTO `sys_permission`
VALUES (8, 7, '角色管理', NULL, 2, NULL, '/sysrole', 1, 1, '2020-10-22 22:47:46', '2020-11-15 20:03:59');
INSERT INTO `sys_permission`
VALUES (9, 7, '权限管理', NULL, 2, NULL, '/syspermission', 1, 2, '2020-10-22 22:48:09', '2020-11-15 20:04:05');
INSERT INTO `sys_permission`
VALUES (10, 8, '新增角色', 'role:add', 3, NULL, NULL, 1, 0, '2020-11-11 22:56:27', '2020-11-15 19:56:40');
INSERT INTO `sys_permission`
VALUES (11, 9, '新增权限', 'permission:add', 3, NULL, NULL, 1, 0, '2020-11-11 22:57:01', '2020-11-15 19:57:52');
INSERT INTO `sys_permission`
VALUES (12, 7, '用户管理', NULL, 2, NULL, '/sysuser', 1, 0, '2020-11-15 20:03:50', '2020-11-15 20:03:50');
INSERT INTO `sys_permission`
VALUES (14, 12, '查看用户', 'user:view', 3, NULL, NULL, 1, 0, '2020-11-15 20:05:56', '2020-11-15 20:05:56');
INSERT INTO `sys_permission`
VALUES (15, 12, '新增用户', 'user:add', 3, NULL, NULL, 1, 0, '2020-11-15 20:06:09', '2020-11-15 20:06:52');
INSERT INTO `sys_permission`
VALUES (16, 12, '修改用户', 'user:update', 3, NULL, NULL, 1, 0, '2020-11-15 20:06:43', '2020-11-15 20:06:42');
INSERT INTO `sys_permission`
VALUES (17, 12, '删除用户', 'user:remove', 3, NULL, NULL, 1, 0, '2020-11-15 20:07:45', '2020-11-15 20:07:44');
INSERT INTO `sys_permission`
VALUES (18, 12, '查看角色', 'role:view', 8, NULL, NULL, 1, 0, '2020-11-15 20:09:28', '2020-11-15 20:09:27');
INSERT INTO `sys_permission`
VALUES (19, 8, '查看角色', 'role:view', 3, NULL, NULL, 1, 0, '2020-11-15 20:11:08', '2020-11-15 20:11:07');
INSERT INTO `sys_permission`
VALUES (20, 8, '修改角色', 'role:update', 3, NULL, NULL, 1, 0, '2020-11-15 20:29:40', '2020-11-15 20:29:39');
INSERT INTO `sys_permission`
VALUES (21, 8, '删除角色', 'role:remove', 3, NULL, NULL, 1, 0, '2020-11-15 20:30:20', '2020-11-15 20:30:19');
INSERT INTO `sys_permission`
VALUES (22, 9, '查看权限', 'permission:view', 3, NULL, NULL, 1, 0, '2020-11-15 20:30:59', '2020-11-15 20:30:58');
INSERT INTO `sys_permission`
VALUES (23, 9, '修改权限', 'permission:update', 3, NULL, NULL, 1, 0, '2020-11-15 20:31:22', '2020-11-15 20:31:21');
INSERT INTO `sys_permission`
VALUES (24, 9, '删除权限', 'permission:remove', 3, NULL, NULL, 1, 0, '2020-11-15 20:31:53', '2020-11-15 20:31:52');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`
(
    `id`          bigint(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `value`       varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  NOT NULL COMMENT '英文值',
    `name`        varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  NOT NULL COMMENT '中文名称',
    `description` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '描述',
    `state`       tinyint(3) UNSIGNED NOT NULL DEFAULT 1 COMMENT '状态 1-启用 0-禁用',
    `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP (0) COMMENT '创建时间',
    `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP (0) COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 111 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '系统角色' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role`
VALUES (1, 'ROLE_ADMIN', '系统管理员', '系统管理员', 1, '2020-08-09 23:40:11', '2020-08-09 23:40:11');
INSERT INTO `sys_role`
VALUES (6, 'ROLE_USER', '用户', '用户', 1, '2020-11-01 21:20:37', '2020-11-01 21:20:37');

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission`
(
    `id`   bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `r_id` bigint(20) UNSIGNED NOT NULL COMMENT '角色id',
    `p_id` bigint(20) UNSIGNED NOT NULL COMMENT '权限id',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 106 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
INSERT INTO `sys_role_permission`
VALUES (7, 1, 7);
INSERT INTO `sys_role_permission`
VALUES (8, 1, 8);
INSERT INTO `sys_role_permission`
VALUES (9, 1, 9);
INSERT INTO `sys_role_permission`
VALUES (84, 1, 10);
INSERT INTO `sys_role_permission`
VALUES (86, 1, 1);
INSERT INTO `sys_role_permission`
VALUES (87, 1, 2);
INSERT INTO `sys_role_permission`
VALUES (88, 1, 3);
INSERT INTO `sys_role_permission`
VALUES (89, 1, 4);
INSERT INTO `sys_role_permission`
VALUES (90, 1, 5);
INSERT INTO `sys_role_permission`
VALUES (91, 1, 6);
INSERT INTO `sys_role_permission`
VALUES (92, 1, 12);
INSERT INTO `sys_role_permission`
VALUES (93, 1, 14);
INSERT INTO `sys_role_permission`
VALUES (94, 1, 15);
INSERT INTO `sys_role_permission`
VALUES (95, 1, 16);
INSERT INTO `sys_role_permission`
VALUES (96, 1, 17);
INSERT INTO `sys_role_permission`
VALUES (97, 1, 19);
INSERT INTO `sys_role_permission`
VALUES (98, 1, 21);
INSERT INTO `sys_role_permission`
VALUES (99, 1, 20);
INSERT INTO `sys_role_permission`
VALUES (100, 1, 22);
INSERT INTO `sys_role_permission`
VALUES (103, 1, 11);
INSERT INTO `sys_role_permission`
VALUES (104, 1, 23);
INSERT INTO `sys_role_permission`
VALUES (105, 1, 24);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`
(
    `id`          bigint(255) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `account`     char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '账号',
    `pwd`         char(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '密码',
    `state`       tinyint(4) NOT NULL DEFAULT 1 COMMENT '状态 1-启用 0-禁用',
    `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP (0) COMMENT '创建时间',
    `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP (0) COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 69 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user`
VALUES (1, 'admin', '$2a$10$oXf/01CfrZKT2r.Vr8//nuBO7L7AzmUSaAkROMRlsqCytEvLMc89e', 1, '2020-08-10 17:15:48',
        '2020-08-10 17:15:48');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`
(
    `id`   bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `u_id` bigint(20) UNSIGNED NOT NULL COMMENT '用户id',
    `r_id` bigint(20) UNSIGNED NOT NULL COMMENT '角色id',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role`
VALUES (1, 1, 1);

-- ----------------------------
-- Table structure for sys_job_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_job_log`;
CREATE TABLE `sys_job_log`
(
    `id`          bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `job_id`      bigint(20) UNSIGNED NOT NULL COMMENT 'JobId',
    `msg`         varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '描述',
    `result`      tinyint(4) NOT NULL DEFAULT 1 COMMENT '结果 1-成功 0-失败',
    `spend_time`  int(20) NOT NULL COMMENT '运行花费时间',
    `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP (0) COMMENT '创建时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

SET
FOREIGN_KEY_CHECKS = 1;
