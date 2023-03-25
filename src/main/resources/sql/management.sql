/*
 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50735
 Source Host           : localhost:3306
 Source Schema         : management

 Target Server Type    : MySQL
 Target Server Version : 50735
 File Encoding         : 65001

 Date: 09/06/2022 15:23:06
*/

SET NAMES utf8mb4;
SET
FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_job
-- ----------------------------
DROP TABLE IF EXISTS `sys_job`;
CREATE TABLE `sys_job`
(
    `id`           bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `job_class`    varchar(128) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'job类路径',
    `name`         varchar(128) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'job名字',
    `description`  varchar(512) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '描述',
    `cron`         varchar(64) COLLATE utf8mb4_unicode_ci  DEFAULT NULL COMMENT 'cron',
    `status`       tinyint(3) NOT NULL DEFAULT '0' COMMENT '启用状态(1-启用 0-禁用)',
    `trigger_name` varchar(64) COLLATE utf8mb4_unicode_ci  DEFAULT NULL COMMENT '触发器name',
    `group_name`   varchar(64) COLLATE utf8mb4_unicode_ci  DEFAULT NULL COMMENT '组',
    `create_time`  datetime                                NOT NULL COMMENT '创建时间',
    `update_time`  datetime                                NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE KEY `unique_job_class` (`job_class`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=155 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sys_job
-- ----------------------------
BEGIN;
INSERT INTO `sys_job` (`id`, `job_class`, `name`, `description`, `cron`, `status`, `trigger_name`, `group_name`,
                       `create_time`, `update_time`)
VALUES (147, 'com.linck.management.quartz.job.Test2Job', 'Test2Job', NULL, '*/10 * * * * ?', 0, NULL, 'default',
        '2021-11-15 22:37:34', '2021-11-22 21:46:47');
INSERT INTO `sys_job` (`id`, `job_class`, `name`, `description`, `cron`, `status`, `trigger_name`, `group_name`,
                       `create_time`, `update_time`)
VALUES (150, 'com.linck.management.quartz.job.TestJob', 'TestJob', NULL, '*/5 * * * * ?', 0, NULL, 'default',
        '2021-11-22 21:24:37', '2021-11-22 21:49:42');
INSERT INTO `sys_job` (`id`, `job_class`, `name`, `description`, `cron`, `status`, `trigger_name`, `group_name`,
                       `create_time`, `update_time`)
VALUES (151, 'com.linck.management.quartz.job.Test3Job', 'Test3Job', NULL, '*/3 * * * * ?', 0, NULL, 'default',
        '2021-11-22 21:55:18', '2021-11-22 21:55:43');
INSERT INTO `sys_job` (`id`, `job_class`, `name`, `description`, `cron`, `status`, `trigger_name`, `group_name`,
                       `create_time`, `update_time`)
VALUES (152, 'com.linck.management.quartz.job.RandomJob', 'RandomJob', NULL, '* */1 * * * ?', 0, NULL, 'default',
        '2021-11-24 22:28:00', '2021-11-24 22:28:00');
INSERT INTO `sys_job` (`id`, `job_class`, `name`, `description`, `cron`, `status`, `trigger_name`, `group_name`,
                       `create_time`, `update_time`)
VALUES (153, 'com.linck.management.quartz.job.SuccessJob', 'SuccessJob', NULL, '* */1 * * * ?', 0, NULL, 'default',
        '2021-11-24 22:28:00', '2021-11-24 22:28:00');
INSERT INTO `sys_job` (`id`, `job_class`, `name`, `description`, `cron`, `status`, `trigger_name`, `group_name`,
                       `create_time`, `update_time`)
VALUES (154, 'com.linck.management.quartz.job.ExceptionJob', 'ExceptionJob', NULL, '* */1 * * * ?', 0, NULL, 'default',
        '2021-11-24 22:28:00', '2021-11-24 22:28:00');
COMMIT;

-- ----------------------------
-- Table structure for sys_job_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_job_log`;
CREATE TABLE `sys_job_log`
(
    `id`          bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `job_id`      bigint(20) unsigned NOT NULL COMMENT 'JobId',
    `msg`         varchar(1024) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '描述',
    `result`      tinyint(4) NOT NULL DEFAULT '1' COMMENT '结果 1-成功 0-失败',
    `spend_time`  int(20) NOT NULL COMMENT '运行花费时间',
    `create_time` datetime                                 NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=849 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission`
(
    `id`          bigint(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `pid`         bigint(10) unsigned NOT NULL DEFAULT '0' COMMENT '父级权限id',
    `name`        char(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '名称',
    `value`       char(64) COLLATE utf8mb4_unicode_ci          DEFAULT NULL COMMENT '内容',
    `type`        tinyint(1) NOT NULL DEFAULT '1' COMMENT '类型 1-菜单 2-按钮 3-权限',
    `icon`        varchar(256) COLLATE utf8mb4_unicode_ci      DEFAULT NULL COMMENT '图标',
    `url`         varchar(256) COLLATE utf8mb4_unicode_ci      DEFAULT NULL COMMENT '前端资源路径',
    `status`      tinyint(3) NOT NULL DEFAULT '0' COMMENT '启用状态(1-启用 0-禁用)',
    `sort`        int(1) NOT NULL DEFAULT '0' COMMENT '排序',
    `create_time` datetime                            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime                            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
BEGIN;
INSERT INTO `sys_permission` (`id`, `pid`, `name`, `value`, `type`, `icon`, `url`, `status`, `sort`, `create_time`,
                              `update_time`)
VALUES (1, 0, '测试菜单', NULL, 1, 'el-icon-folder', NULL, 1, 0, '2020-08-09 23:49:49', '2021-11-12 10:01:52');
INSERT INTO `sys_permission` (`id`, `pid`, `name`, `value`, `type`, `icon`, `url`, `status`, `sort`, `create_time`,
                              `update_time`)
VALUES (2, 1, '测试列表', NULL, 2, NULL, '/first', 1, 0, '2020-08-09 23:49:55', '2020-11-15 20:10:36');
INSERT INTO `sys_permission` (`id`, `pid`, `name`, `value`, `type`, `icon`, `url`, `status`, `sort`, `create_time`,
                              `update_time`)
VALUES (7, 0, '系统管理', NULL, 1, 'el-icon-setting', NULL, 1, 11, '2020-10-22 22:47:17', '2020-10-28 23:30:55');
INSERT INTO `sys_permission` (`id`, `pid`, `name`, `value`, `type`, `icon`, `url`, `status`, `sort`, `create_time`,
                              `update_time`)
VALUES (8, 7, '角色管理', NULL, 2, NULL, '/sysrole', 1, 1, '2020-10-22 22:47:46', '2020-11-15 20:03:59');
INSERT INTO `sys_permission` (`id`, `pid`, `name`, `value`, `type`, `icon`, `url`, `status`, `sort`, `create_time`,
                              `update_time`)
VALUES (9, 7, '权限管理', NULL, 2, NULL, '/syspermission', 1, 2, '2020-10-22 22:48:09', '2020-11-15 20:04:05');
INSERT INTO `sys_permission` (`id`, `pid`, `name`, `value`, `type`, `icon`, `url`, `status`, `sort`, `create_time`,
                              `update_time`)
VALUES (10, 8, '新增角色', 'role:add', 3, NULL, NULL, 1, 0, '2020-11-11 22:56:27', '2020-11-15 19:56:40');
INSERT INTO `sys_permission` (`id`, `pid`, `name`, `value`, `type`, `icon`, `url`, `status`, `sort`, `create_time`,
                              `update_time`)
VALUES (11, 9, '新增权限', 'permission:add', 3, NULL, NULL, 1, 0, '2020-11-11 22:57:01', '2020-11-15 19:57:52');
INSERT INTO `sys_permission` (`id`, `pid`, `name`, `value`, `type`, `icon`, `url`, `status`, `sort`, `create_time`,
                              `update_time`)
VALUES (12, 7, '用户管理', NULL, 2, NULL, '/sysuser', 1, 0, '2020-11-15 20:03:50', '2020-11-15 20:03:50');
INSERT INTO `sys_permission` (`id`, `pid`, `name`, `value`, `type`, `icon`, `url`, `status`, `sort`, `create_time`,
                              `update_time`)
VALUES (14, 12, '查看用户', 'user:view', 3, NULL, NULL, 1, 0, '2020-11-15 20:05:56', '2020-11-15 20:05:56');
INSERT INTO `sys_permission` (`id`, `pid`, `name`, `value`, `type`, `icon`, `url`, `status`, `sort`, `create_time`,
                              `update_time`)
VALUES (15, 12, '新增用户', 'user:add', 3, NULL, NULL, 1, 0, '2020-11-15 20:06:09', '2020-11-15 20:06:52');
INSERT INTO `sys_permission` (`id`, `pid`, `name`, `value`, `type`, `icon`, `url`, `status`, `sort`, `create_time`,
                              `update_time`)
VALUES (16, 12, '修改用户', 'user:update', 3, NULL, NULL, 1, 0, '2020-11-15 20:06:43', '2020-11-15 20:06:42');
INSERT INTO `sys_permission` (`id`, `pid`, `name`, `value`, `type`, `icon`, `url`, `status`, `sort`, `create_time`,
                              `update_time`)
VALUES (17, 12, '删除用户', 'user:remove', 3, NULL, NULL, 1, 0, '2020-11-15 20:07:45', '2020-11-15 20:07:44');
INSERT INTO `sys_permission` (`id`, `pid`, `name`, `value`, `type`, `icon`, `url`, `status`, `sort`, `create_time`,
                              `update_time`)
VALUES (18, 12, '查看角色', 'role:view', 8, NULL, NULL, 1, 0, '2020-11-15 20:09:28', '2020-11-15 20:09:27');
INSERT INTO `sys_permission` (`id`, `pid`, `name`, `value`, `type`, `icon`, `url`, `status`, `sort`, `create_time`,
                              `update_time`)
VALUES (19, 8, '查看角色', 'role:view', 3, NULL, NULL, 1, 0, '2020-11-15 20:11:08', '2020-11-15 20:11:07');
INSERT INTO `sys_permission` (`id`, `pid`, `name`, `value`, `type`, `icon`, `url`, `status`, `sort`, `create_time`,
                              `update_time`)
VALUES (20, 8, '修改角色', 'role:update', 3, NULL, NULL, 1, 0, '2020-11-15 20:29:40', '2020-11-15 20:29:39');
INSERT INTO `sys_permission` (`id`, `pid`, `name`, `value`, `type`, `icon`, `url`, `status`, `sort`, `create_time`,
                              `update_time`)
VALUES (21, 8, '删除角色', 'role:remove', 3, NULL, NULL, 1, 0, '2020-11-15 20:30:20', '2020-11-15 20:30:19');
INSERT INTO `sys_permission` (`id`, `pid`, `name`, `value`, `type`, `icon`, `url`, `status`, `sort`, `create_time`,
                              `update_time`)
VALUES (22, 9, '查看权限', 'permission:view', 3, NULL, NULL, 1, 0, '2020-11-15 20:30:59', '2020-11-15 20:30:58');
INSERT INTO `sys_permission` (`id`, `pid`, `name`, `value`, `type`, `icon`, `url`, `status`, `sort`, `create_time`,
                              `update_time`)
VALUES (23, 9, '修改权限', 'permission:update', 3, NULL, NULL, 1, 0, '2020-11-15 20:31:22', '2020-11-15 20:31:21');
INSERT INTO `sys_permission` (`id`, `pid`, `name`, `value`, `type`, `icon`, `url`, `status`, `sort`, `create_time`,
                              `update_time`)
VALUES (24, 9, '删除权限', 'permission:remove', 3, NULL, NULL, 1, 0, '2020-11-15 20:31:53', '2020-11-15 20:31:52');
INSERT INTO `sys_permission` (`id`, `pid`, `name`, `value`, `type`, `icon`, `url`, `status`, `sort`, `create_time`,
                              `update_time`)
VALUES (26, 7, 'Job管理', NULL, 2, NULL, '/sysjob', 1, 0, '2020-12-11 16:57:28', '2021-11-11 22:32:32');
INSERT INTO `sys_permission` (`id`, `pid`, `name`, `value`, `type`, `icon`, `url`, `status`, `sort`, `create_time`,
                              `update_time`)
VALUES (27, 26, '查看Job', 'job:view', 3, NULL, NULL, 1, 0, '2020-12-11 17:01:45', '2021-11-11 22:32:22');
INSERT INTO `sys_permission` (`id`, `pid`, `name`, `value`, `type`, `icon`, `url`, `status`, `sort`, `create_time`,
                              `update_time`)
VALUES (28, 26, '修改Job', 'job:update', 3, NULL, NULL, 1, 3, '2020-12-11 17:02:11', '2021-11-11 22:33:10');
INSERT INTO `sys_permission` (`id`, `pid`, `name`, `value`, `type`, `icon`, `url`, `status`, `sort`, `create_time`,
                              `update_time`)
VALUES (29, 26, '新增Job', 'job:add', 3, NULL, NULL, 1, 2, '2021-11-11 22:31:44', '2021-11-11 22:33:02');
INSERT INTO `sys_permission` (`id`, `pid`, `name`, `value`, `type`, `icon`, `url`, `status`, `sort`, `create_time`,
                              `update_time`)
VALUES (30, 26, '删除Job', 'job:remove', 3, NULL, NULL, 1, 4, '2021-11-11 22:32:06', '2021-11-11 22:32:05');
COMMIT;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`
(
    `id`          bigint(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `value`       varchar(32) COLLATE utf8mb4_unicode_ci  NOT NULL COMMENT '编码值',
    `name`        varchar(32) COLLATE utf8mb4_unicode_ci  NOT NULL COMMENT '中文名称',
    `description` varchar(128) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '描述',
    `status`      tinyint(3) NOT NULL DEFAULT '0' COMMENT '启用状态(1-启用 0-禁用)',
    `create_time` datetime                                NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime                                NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='系统角色';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_role` (`id`, `value`, `name`, `description`, `status`, `create_time`, `update_time`)
VALUES (1, 'ROLE_ADMIN', '系统管理员', '系统管理员', 1, '2020-08-09 23:40:11', '2020-08-09 23:40:11');
INSERT INTO `sys_role` (`id`, `value`, `name`, `description`, `status`, `create_time`, `update_time`)
VALUES (6, 'ROLE_USER', '用户', '用户', 1, '2020-11-01 21:20:37', '2020-11-01 21:20:37');
INSERT INTO `sys_role` (`id`, `value`, `name`, `description`, `status`, `create_time`, `update_time`)
VALUES (7, 'USER_GUESS', '游客', '访问游客', 1, '2021-11-11 22:33:47', '2021-11-11 22:33:47');
COMMIT;

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission`
(
    `id`   bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `r_id` bigint(20) unsigned NOT NULL COMMENT '角色id',
    `p_id` bigint(20) unsigned NOT NULL COMMENT '权限id',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=120 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
BEGIN;
INSERT INTO `sys_role_permission` (`id`, `r_id`, `p_id`)
VALUES (7, 1, 7);
INSERT INTO `sys_role_permission` (`id`, `r_id`, `p_id`)
VALUES (8, 1, 8);
INSERT INTO `sys_role_permission` (`id`, `r_id`, `p_id`)
VALUES (9, 1, 9);
INSERT INTO `sys_role_permission` (`id`, `r_id`, `p_id`)
VALUES (84, 1, 10);
INSERT INTO `sys_role_permission` (`id`, `r_id`, `p_id`)
VALUES (86, 1, 1);
INSERT INTO `sys_role_permission` (`id`, `r_id`, `p_id`)
VALUES (87, 1, 2);
INSERT INTO `sys_role_permission` (`id`, `r_id`, `p_id`)
VALUES (88, 1, 3);
INSERT INTO `sys_role_permission` (`id`, `r_id`, `p_id`)
VALUES (89, 1, 4);
INSERT INTO `sys_role_permission` (`id`, `r_id`, `p_id`)
VALUES (90, 1, 5);
INSERT INTO `sys_role_permission` (`id`, `r_id`, `p_id`)
VALUES (91, 1, 6);
INSERT INTO `sys_role_permission` (`id`, `r_id`, `p_id`)
VALUES (92, 1, 12);
INSERT INTO `sys_role_permission` (`id`, `r_id`, `p_id`)
VALUES (93, 1, 14);
INSERT INTO `sys_role_permission` (`id`, `r_id`, `p_id`)
VALUES (94, 1, 15);
INSERT INTO `sys_role_permission` (`id`, `r_id`, `p_id`)
VALUES (95, 1, 16);
INSERT INTO `sys_role_permission` (`id`, `r_id`, `p_id`)
VALUES (96, 1, 17);
INSERT INTO `sys_role_permission` (`id`, `r_id`, `p_id`)
VALUES (97, 1, 19);
INSERT INTO `sys_role_permission` (`id`, `r_id`, `p_id`)
VALUES (98, 1, 21);
INSERT INTO `sys_role_permission` (`id`, `r_id`, `p_id`)
VALUES (99, 1, 20);
INSERT INTO `sys_role_permission` (`id`, `r_id`, `p_id`)
VALUES (100, 1, 22);
INSERT INTO `sys_role_permission` (`id`, `r_id`, `p_id`)
VALUES (103, 1, 11);
INSERT INTO `sys_role_permission` (`id`, `r_id`, `p_id`)
VALUES (104, 1, 23);
INSERT INTO `sys_role_permission` (`id`, `r_id`, `p_id`)
VALUES (105, 1, 24);
INSERT INTO `sys_role_permission` (`id`, `r_id`, `p_id`)
VALUES (106, 1, 26);
INSERT INTO `sys_role_permission` (`id`, `r_id`, `p_id`)
VALUES (107, 1, 27);
INSERT INTO `sys_role_permission` (`id`, `r_id`, `p_id`)
VALUES (108, 1, 28);
INSERT INTO `sys_role_permission` (`id`, `r_id`, `p_id`)
VALUES (109, 7, 2);
INSERT INTO `sys_role_permission` (`id`, `r_id`, `p_id`)
VALUES (110, 7, 1);
INSERT INTO `sys_role_permission` (`id`, `r_id`, `p_id`)
VALUES (111, 7, 7);
INSERT INTO `sys_role_permission` (`id`, `r_id`, `p_id`)
VALUES (112, 7, 12);
INSERT INTO `sys_role_permission` (`id`, `r_id`, `p_id`)
VALUES (113, 7, 14);
INSERT INTO `sys_role_permission` (`id`, `r_id`, `p_id`)
VALUES (114, 7, 26);
INSERT INTO `sys_role_permission` (`id`, `r_id`, `p_id`)
VALUES (115, 7, 27);
INSERT INTO `sys_role_permission` (`id`, `r_id`, `p_id`)
VALUES (116, 7, 8);
INSERT INTO `sys_role_permission` (`id`, `r_id`, `p_id`)
VALUES (117, 7, 9);
INSERT INTO `sys_role_permission` (`id`, `r_id`, `p_id`)
VALUES (118, 7, 22);
INSERT INTO `sys_role_permission` (`id`, `r_id`, `p_id`)
VALUES (119, 7, 19);
COMMIT;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`
(
    `id`          bigint(255) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `account`     char(32) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '账号',
    `pwd`         char(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '密码',
    `status`      tinyint(3) NOT NULL DEFAULT '1' COMMENT '启用状态(1-启用 0-禁用)',
    `create_time` datetime                            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime                            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=72 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
BEGIN;
INSERT INTO `sys_user` (`id`, `account`, `pwd`, `status`, `create_time`, `update_time`)
VALUES (1, 'admin', '$2a$10$FceyccF8jbnKHbXx0dAArubDHoz21mhOqbaRFs96iUQTMzdCR5dW6', 1, '2020-08-10 17:15:48',
        '2020-08-10 17:15:48');
INSERT INTO `sys_user` (`id`, `account`, `pwd`, `status`, `create_time`, `update_time`)
VALUES (69, 'test', '$2a$10$p8w8piQ/MFIdVKBSxGVocOJh1Z3m29MLBC.qt5PjwwSqgTDbgIFYa', 1, '2020-11-22 13:43:44',
        '2020-11-22 13:43:44');
INSERT INTO `sys_user` (`id`, `account`, `pwd`, `status`, `create_time`, `update_time`)
VALUES (70, 'test11', 'test11', 1, '2020-11-22 13:45:05', '2020-11-22 13:45:05');
INSERT INTO `sys_user` (`id`, `account`, `pwd`, `status`, `create_time`, `update_time`)
VALUES (71, 'test22', 'test22', 0, '2020-11-22 13:51:32', '2020-11-22 13:51:32');
COMMIT;

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`
(
    `id`   bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `u_id` bigint(20) unsigned NOT NULL COMMENT '用户id',
    `r_id` bigint(20) unsigned NOT NULL COMMENT '角色id',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_user_role` (`id`, `u_id`, `r_id`)
VALUES (1, 1, 1);
INSERT INTO `sys_user_role` (`id`, `u_id`, `r_id`)
VALUES (2, 1, 6);
INSERT INTO `sys_user_role` (`id`, `u_id`, `r_id`)
VALUES (6, 69, 7);
COMMIT;

SET
FOREIGN_KEY_CHECKS = 1;
