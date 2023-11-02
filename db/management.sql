/*
 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80029 (8.0.29)
 Source Host           : localhost:3306
 Source Schema         : management

 Target Server Type    : MySQL
 Target Server Version : 80029 (8.0.29)
 File Encoding         : 65001

 Date: 02/11/2023 10:34:51
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_job
-- ----------------------------
DROP TABLE IF EXISTS `sys_job`;
CREATE TABLE `sys_job` (
                           `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
                           `job_class` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'job类路径',
                           `name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'job名字',
                           `description` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '描述',
                           `cron` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'cron',
                           `status` tinyint NOT NULL DEFAULT '0' COMMENT '启用状态(1-启用 0-禁用)',
                           `trigger_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '触发器name',
                           `group_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '组',
                           `create_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                           `update_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
                           `create_by` bigint unsigned DEFAULT NULL,
                           `update_by` bigint unsigned DEFAULT NULL,
                           `del_flag` bit(1) NOT NULL DEFAULT b'0',
                           `version` bigint unsigned NOT NULL DEFAULT '1',
                           PRIMARY KEY (`id`) USING BTREE,
                           UNIQUE KEY `unique_job_class` (`job_class`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=156 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='任务';

-- ----------------------------
-- Records of sys_job
-- ----------------------------
BEGIN;
INSERT INTO `sys_job` (`id`, `job_class`, `name`, `description`, `cron`, `status`, `trigger_name`, `group_name`, `create_at`, `update_at`, `create_by`, `update_by`, `del_flag`, `version`) VALUES (147, 'com.linck.management.quartz.job.Test2Job', 'Test2Job', NULL, '*/10 * * * * ?', 0, NULL, 'default', '2021-11-15 22:37:34', '2023-05-09 09:25:51', NULL, 1, b'0', 2);
INSERT INTO `sys_job` (`id`, `job_class`, `name`, `description`, `cron`, `status`, `trigger_name`, `group_name`, `create_at`, `update_at`, `create_by`, `update_by`, `del_flag`, `version`) VALUES (150, 'com.linck.management.quartz.job.TestJob', 'TestJob', NULL, '*/5 * * * * ?', 0, NULL, 'default', '2021-11-22 21:24:37', '2021-11-22 21:49:42', NULL, NULL, b'0', 1);
INSERT INTO `sys_job` (`id`, `job_class`, `name`, `description`, `cron`, `status`, `trigger_name`, `group_name`, `create_at`, `update_at`, `create_by`, `update_by`, `del_flag`, `version`) VALUES (151, 'com.linck.management.quartz.job.Test3Job', 'Test3Job', NULL, '*/3 * * * * ?', 0, NULL, 'default', '2021-11-22 21:55:18', '2021-11-22 21:55:43', NULL, NULL, b'0', 1);
INSERT INTO `sys_job` (`id`, `job_class`, `name`, `description`, `cron`, `status`, `trigger_name`, `group_name`, `create_at`, `update_at`, `create_by`, `update_by`, `del_flag`, `version`) VALUES (152, 'com.linck.management.quartz.job.RandomJob', 'RandomJob', NULL, '* */1 * * * ?', 0, NULL, 'default', '2021-11-24 22:28:00', '2021-11-24 22:28:00', NULL, NULL, b'0', 1);
INSERT INTO `sys_job` (`id`, `job_class`, `name`, `description`, `cron`, `status`, `trigger_name`, `group_name`, `create_at`, `update_at`, `create_by`, `update_by`, `del_flag`, `version`) VALUES (153, 'com.linck.management.quartz.job.SuccessJob', 'SuccessJob', NULL, '* */1 * * * ?', 0, NULL, 'default', '2021-11-24 22:28:00', '2021-11-24 22:28:00', NULL, NULL, b'0', 1);
INSERT INTO `sys_job` (`id`, `job_class`, `name`, `description`, `cron`, `status`, `trigger_name`, `group_name`, `create_at`, `update_at`, `create_by`, `update_by`, `del_flag`, `version`) VALUES (154, 'com.linck.management.quartz.job.ExceptionJob', 'ExceptionJob', NULL, '* */1 * * * ?', 0, NULL, 'default', '2021-11-24 22:28:00', '2021-11-24 22:28:00', NULL, NULL, b'0', 1);
INSERT INTO `sys_job` (`id`, `job_class`, `name`, `description`, `cron`, `status`, `trigger_name`, `group_name`, `create_at`, `update_at`, `create_by`, `update_by`, `del_flag`, `version`) VALUES (155, 'test1', 'test1', NULL, '1', 0, NULL, 'default', '2023-05-09 09:26:14', '2023-05-09 09:26:18', 1, 1, b'1', 1);
COMMIT;

-- ----------------------------
-- Table structure for sys_job_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_job_log`;
CREATE TABLE `sys_job_log` (
                               `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
                               `job_id` bigint unsigned NOT NULL COMMENT 'JobId',
                               `msg` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '描述',
                               `result` tinyint NOT NULL DEFAULT '1' COMMENT '结果 1-成功 0-失败',
                               `spend_time` int NOT NULL COMMENT '运行花费时间',
                               `create_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                               PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=874 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='任务日志';

-- ----------------------------
-- Records of sys_job_log
-- ----------------------------
BEGIN;
INSERT INTO `sys_job_log` (`id`, `job_id`, `msg`, `result`, `spend_time`, `create_at`) VALUES (849, 153, '成功', 1, 1, '2023-03-31 10:47:28');
INSERT INTO `sys_job_log` (`id`, `job_id`, `msg`, `result`, `spend_time`, `create_at`) VALUES (850, 154, 'java.lang.ArithmeticException: / by zero', 0, 0, '2023-03-31 10:47:28');
INSERT INTO `sys_job_log` (`id`, `job_id`, `msg`, `result`, `spend_time`, `create_at`) VALUES (851, 153, '成功', 1, 0, '2023-03-31 10:47:29');
INSERT INTO `sys_job_log` (`id`, `job_id`, `msg`, `result`, `spend_time`, `create_at`) VALUES (852, 154, 'java.lang.ArithmeticException: / by zero', 0, 0, '2023-03-31 10:47:29');
INSERT INTO `sys_job_log` (`id`, `job_id`, `msg`, `result`, `spend_time`, `create_at`) VALUES (853, 154, 'java.lang.ArithmeticException: / by zero', 0, 0, '2023-03-31 10:47:30');
INSERT INTO `sys_job_log` (`id`, `job_id`, `msg`, `result`, `spend_time`, `create_at`) VALUES (854, 153, '成功', 1, 0, '2023-03-31 10:47:30');
INSERT INTO `sys_job_log` (`id`, `job_id`, `msg`, `result`, `spend_time`, `create_at`) VALUES (855, 153, '成功', 1, 0, '2023-03-31 10:47:31');
INSERT INTO `sys_job_log` (`id`, `job_id`, `msg`, `result`, `spend_time`, `create_at`) VALUES (856, 154, 'java.lang.ArithmeticException: / by zero', 0, 0, '2023-03-31 10:47:31');
INSERT INTO `sys_job_log` (`id`, `job_id`, `msg`, `result`, `spend_time`, `create_at`) VALUES (857, 152, '成功', 1, 2962, '2023-03-31 10:47:31');
INSERT INTO `sys_job_log` (`id`, `job_id`, `msg`, `result`, `spend_time`, `create_at`) VALUES (858, 152, '成功', 1, 2355, '2023-03-31 10:47:31');
INSERT INTO `sys_job_log` (`id`, `job_id`, `msg`, `result`, `spend_time`, `create_at`) VALUES (859, 152, 'java.lang.NoSuchMethodException: com.linck.management.quartz.job.RandomJob.error()', 0, 1606, '2023-03-31 10:47:32');
INSERT INTO `sys_job_log` (`id`, `job_id`, `msg`, `result`, `spend_time`, `create_at`) VALUES (860, 153, '成功', 1, 0, '2023-03-31 10:47:32');
INSERT INTO `sys_job_log` (`id`, `job_id`, `msg`, `result`, `spend_time`, `create_at`) VALUES (861, 154, 'java.lang.ArithmeticException: / by zero', 0, 0, '2023-03-31 10:47:32');
INSERT INTO `sys_job_log` (`id`, `job_id`, `msg`, `result`, `spend_time`, `create_at`) VALUES (862, 154, 'java.lang.ArithmeticException: / by zero', 0, 0, '2023-03-31 10:47:33');
INSERT INTO `sys_job_log` (`id`, `job_id`, `msg`, `result`, `spend_time`, `create_at`) VALUES (863, 153, '成功', 1, 0, '2023-03-31 10:47:33');
INSERT INTO `sys_job_log` (`id`, `job_id`, `msg`, `result`, `spend_time`, `create_at`) VALUES (864, 152, '成功', 1, 2262, '2023-03-31 10:47:33');
INSERT INTO `sys_job_log` (`id`, `job_id`, `msg`, `result`, `spend_time`, `create_at`) VALUES (865, 153, '成功', 1, 0, '2023-03-31 10:47:34');
INSERT INTO `sys_job_log` (`id`, `job_id`, `msg`, `result`, `spend_time`, `create_at`) VALUES (866, 154, 'java.lang.ArithmeticException: / by zero', 0, 0, '2023-03-31 10:47:34');
INSERT INTO `sys_job_log` (`id`, `job_id`, `msg`, `result`, `spend_time`, `create_at`) VALUES (867, 153, '成功', 1, 0, '2023-03-31 10:47:35');
INSERT INTO `sys_job_log` (`id`, `job_id`, `msg`, `result`, `spend_time`, `create_at`) VALUES (868, 154, 'java.lang.ArithmeticException: / by zero', 0, 0, '2023-03-31 10:47:35');
INSERT INTO `sys_job_log` (`id`, `job_id`, `msg`, `result`, `spend_time`, `create_at`) VALUES (869, 154, 'java.lang.ArithmeticException: / by zero', 0, 0, '2023-03-31 10:47:36');
INSERT INTO `sys_job_log` (`id`, `job_id`, `msg`, `result`, `spend_time`, `create_at`) VALUES (870, 153, '成功', 1, 0, '2023-03-31 10:47:36');
INSERT INTO `sys_job_log` (`id`, `job_id`, `msg`, `result`, `spend_time`, `create_at`) VALUES (871, 152, '成功', 1, 3630, '2023-03-31 10:47:37');
INSERT INTO `sys_job_log` (`id`, `job_id`, `msg`, `result`, `spend_time`, `create_at`) VALUES (872, 152, 'java.lang.NoSuchMethodException: com.linck.management.quartz.job.RandomJob.error()', 0, 1785, '2023-03-31 10:47:37');
INSERT INTO `sys_job_log` (`id`, `job_id`, `msg`, `result`, `spend_time`, `create_at`) VALUES (873, 152, '成功', 1, 4788, '2023-03-31 10:47:37');
COMMIT;

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
                                  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
                                  `pid` bigint unsigned NOT NULL DEFAULT '0' COMMENT '父级权限id',
                                  `name` char(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '名称',
                                  `value` char(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '内容',
                                  `type` tinyint(1) NOT NULL DEFAULT '1' COMMENT '类型 1-菜单 2-按钮 3-权限',
                                  `icon` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '图标',
                                  `url` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '前端资源路径',
                                  `sort` int NOT NULL DEFAULT '0' COMMENT '排序',
                                  `create_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                  `update_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
                                  `create_by` bigint unsigned DEFAULT NULL,
                                  `update_by` bigint unsigned DEFAULT NULL,
                                  `del_flag` bit(1) NOT NULL DEFAULT b'0',
                                  `version` bigint unsigned NOT NULL DEFAULT '1',
                                  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
BEGIN;
INSERT INTO `sys_permission` (`id`, `pid`, `name`, `value`, `type`, `icon`, `url`, `sort`, `create_at`, `update_at`, `create_by`, `update_by`, `del_flag`, `version`) VALUES (1, 0, '测试菜单', NULL, 1, 'el-icon-folder', NULL, 0, '2020-08-09 23:49:49', '2023-05-05 15:09:20', NULL, 1, b'0', 1);
INSERT INTO `sys_permission` (`id`, `pid`, `name`, `value`, `type`, `icon`, `url`, `sort`, `create_at`, `update_at`, `create_by`, `update_by`, `del_flag`, `version`) VALUES (2, 1, '测试列表', NULL, 2, NULL, '/first', 0, '2020-08-09 23:49:55', '2023-05-05 15:29:55', NULL, 1, b'0', 1);
INSERT INTO `sys_permission` (`id`, `pid`, `name`, `value`, `type`, `icon`, `url`, `sort`, `create_at`, `update_at`, `create_by`, `update_by`, `del_flag`, `version`) VALUES (7, 0, '系统管理', NULL, 1, 'el-icon-setting', NULL, 11, '2020-10-22 22:47:17', '2020-10-28 23:30:55', NULL, NULL, b'0', 1);
INSERT INTO `sys_permission` (`id`, `pid`, `name`, `value`, `type`, `icon`, `url`, `sort`, `create_at`, `update_at`, `create_by`, `update_by`, `del_flag`, `version`) VALUES (8, 7, '角色管理', NULL, 2, NULL, '/sysrole', 1, '2020-10-22 22:47:46', '2020-11-15 20:03:59', NULL, NULL, b'0', 1);
INSERT INTO `sys_permission` (`id`, `pid`, `name`, `value`, `type`, `icon`, `url`, `sort`, `create_at`, `update_at`, `create_by`, `update_by`, `del_flag`, `version`) VALUES (9, 7, '权限管理', NULL, 2, NULL, '/syspermission', 2, '2020-10-22 22:48:09', '2020-11-15 20:04:05', NULL, NULL, b'0', 1);
INSERT INTO `sys_permission` (`id`, `pid`, `name`, `value`, `type`, `icon`, `url`, `sort`, `create_at`, `update_at`, `create_by`, `update_by`, `del_flag`, `version`) VALUES (10, 8, '新增角色', 'role:add', 3, NULL, NULL, 0, '2020-11-11 22:56:27', '2020-11-15 19:56:40', NULL, NULL, b'0', 1);
INSERT INTO `sys_permission` (`id`, `pid`, `name`, `value`, `type`, `icon`, `url`, `sort`, `create_at`, `update_at`, `create_by`, `update_by`, `del_flag`, `version`) VALUES (11, 9, '新增权限', 'permission:add', 3, NULL, NULL, 0, '2020-11-11 22:57:01', '2020-11-15 19:57:52', NULL, NULL, b'0', 1);
INSERT INTO `sys_permission` (`id`, `pid`, `name`, `value`, `type`, `icon`, `url`, `sort`, `create_at`, `update_at`, `create_by`, `update_by`, `del_flag`, `version`) VALUES (12, 7, '用户管理', NULL, 2, NULL, '/sysuser', 0, '2020-11-15 20:03:50', '2020-11-15 20:03:50', NULL, NULL, b'0', 1);
INSERT INTO `sys_permission` (`id`, `pid`, `name`, `value`, `type`, `icon`, `url`, `sort`, `create_at`, `update_at`, `create_by`, `update_by`, `del_flag`, `version`) VALUES (14, 12, '查看用户', 'user:view', 3, NULL, NULL, 0, '2020-11-15 20:05:56', '2020-11-15 20:05:56', NULL, NULL, b'0', 1);
INSERT INTO `sys_permission` (`id`, `pid`, `name`, `value`, `type`, `icon`, `url`, `sort`, `create_at`, `update_at`, `create_by`, `update_by`, `del_flag`, `version`) VALUES (15, 12, '新增用户', 'user:add', 3, NULL, NULL, 0, '2020-11-15 20:06:09', '2020-11-15 20:06:52', NULL, NULL, b'0', 1);
INSERT INTO `sys_permission` (`id`, `pid`, `name`, `value`, `type`, `icon`, `url`, `sort`, `create_at`, `update_at`, `create_by`, `update_by`, `del_flag`, `version`) VALUES (16, 12, '修改用户', 'user:update', 3, NULL, NULL, 0, '2020-11-15 20:06:43', '2020-11-15 20:06:42', NULL, NULL, b'0', 1);
INSERT INTO `sys_permission` (`id`, `pid`, `name`, `value`, `type`, `icon`, `url`, `sort`, `create_at`, `update_at`, `create_by`, `update_by`, `del_flag`, `version`) VALUES (17, 12, '删除用户', 'user:remove', 3, NULL, NULL, 0, '2020-11-15 20:07:45', '2020-11-15 20:07:44', NULL, NULL, b'0', 1);
INSERT INTO `sys_permission` (`id`, `pid`, `name`, `value`, `type`, `icon`, `url`, `sort`, `create_at`, `update_at`, `create_by`, `update_by`, `del_flag`, `version`) VALUES (18, 12, '查看角色', 'role:view', 3, NULL, NULL, 0, '2020-11-15 20:09:28', '2020-11-15 20:09:27', NULL, NULL, b'0', 1);
INSERT INTO `sys_permission` (`id`, `pid`, `name`, `value`, `type`, `icon`, `url`, `sort`, `create_at`, `update_at`, `create_by`, `update_by`, `del_flag`, `version`) VALUES (19, 8, '查看角色', 'role:view', 3, NULL, NULL, 0, '2020-11-15 20:11:08', '2020-11-15 20:11:07', NULL, NULL, b'0', 1);
INSERT INTO `sys_permission` (`id`, `pid`, `name`, `value`, `type`, `icon`, `url`, `sort`, `create_at`, `update_at`, `create_by`, `update_by`, `del_flag`, `version`) VALUES (20, 8, '修改角色', 'role:update', 3, NULL, NULL, 0, '2020-11-15 20:29:40', '2020-11-15 20:29:39', NULL, NULL, b'0', 1);
INSERT INTO `sys_permission` (`id`, `pid`, `name`, `value`, `type`, `icon`, `url`, `sort`, `create_at`, `update_at`, `create_by`, `update_by`, `del_flag`, `version`) VALUES (21, 8, '删除角色', 'role:remove', 3, NULL, NULL, 0, '2020-11-15 20:30:20', '2020-11-15 20:30:19', NULL, NULL, b'0', 1);
INSERT INTO `sys_permission` (`id`, `pid`, `name`, `value`, `type`, `icon`, `url`, `sort`, `create_at`, `update_at`, `create_by`, `update_by`, `del_flag`, `version`) VALUES (22, 9, '查看权限', 'permission:view', 3, NULL, NULL, 0, '2020-11-15 20:30:59', '2020-11-15 20:30:58', NULL, NULL, b'0', 1);
INSERT INTO `sys_permission` (`id`, `pid`, `name`, `value`, `type`, `icon`, `url`, `sort`, `create_at`, `update_at`, `create_by`, `update_by`, `del_flag`, `version`) VALUES (23, 9, '修改权限', 'permission:update', 3, NULL, NULL, 0, '2020-11-15 20:31:22', '2020-11-15 20:31:21', NULL, NULL, b'0', 1);
INSERT INTO `sys_permission` (`id`, `pid`, `name`, `value`, `type`, `icon`, `url`, `sort`, `create_at`, `update_at`, `create_by`, `update_by`, `del_flag`, `version`) VALUES (24, 9, '删除权限', 'permission:remove', 3, NULL, NULL, 0, '2020-11-15 20:31:53', '2020-11-15 20:31:52', NULL, NULL, b'0', 1);
INSERT INTO `sys_permission` (`id`, `pid`, `name`, `value`, `type`, `icon`, `url`, `sort`, `create_at`, `update_at`, `create_by`, `update_by`, `del_flag`, `version`) VALUES (26, 7, 'Job管理', NULL, 2, NULL, '/sysjob', 0, '2020-12-11 16:57:28', '2021-11-11 22:32:32', NULL, NULL, b'0', 1);
INSERT INTO `sys_permission` (`id`, `pid`, `name`, `value`, `type`, `icon`, `url`, `sort`, `create_at`, `update_at`, `create_by`, `update_by`, `del_flag`, `version`) VALUES (27, 26, '查看Job', 'job:view', 3, NULL, NULL, 0, '2020-12-11 17:01:45', '2021-11-11 22:32:22', NULL, NULL, b'0', 1);
INSERT INTO `sys_permission` (`id`, `pid`, `name`, `value`, `type`, `icon`, `url`, `sort`, `create_at`, `update_at`, `create_by`, `update_by`, `del_flag`, `version`) VALUES (28, 26, '修改Job', 'job:update', 3, NULL, NULL, 3, '2020-12-11 17:02:11', '2021-11-11 22:33:10', NULL, NULL, b'0', 1);
INSERT INTO `sys_permission` (`id`, `pid`, `name`, `value`, `type`, `icon`, `url`, `sort`, `create_at`, `update_at`, `create_by`, `update_by`, `del_flag`, `version`) VALUES (29, 26, '新增Job', 'job:add', 3, NULL, NULL, 2, '2021-11-11 22:31:44', '2021-11-11 22:33:02', NULL, NULL, b'0', 1);
INSERT INTO `sys_permission` (`id`, `pid`, `name`, `value`, `type`, `icon`, `url`, `sort`, `create_at`, `update_at`, `create_by`, `update_by`, `del_flag`, `version`) VALUES (30, 26, '删除Job', 'job:remove', 3, NULL, NULL, 4, '2021-11-11 22:32:06', '2021-11-11 22:32:05', NULL, NULL, b'0', 1);
INSERT INTO `sys_permission` (`id`, `pid`, `name`, `value`, `type`, `icon`, `url`, `sort`, `create_at`, `update_at`, `create_by`, `update_by`, `del_flag`, `version`) VALUES (32, 1, 'test3', NULL, 2, NULL, '32', 0, '2023-05-05 15:00:28', '2023-05-05 15:09:40', 1, 1, b'1', 1);
INSERT INTO `sys_permission` (`id`, `pid`, `name`, `value`, `type`, `icon`, `url`, `sort`, `create_at`, `update_at`, `create_by`, `update_by`, `del_flag`, `version`) VALUES (35, 1, 'test4', NULL, 2, NULL, '13', 0, '2023-05-05 15:18:47', '2023-05-05 15:18:52', 1, 1, b'1', 1);
INSERT INTO `sys_permission` (`id`, `pid`, `name`, `value`, `type`, `icon`, `url`, `sort`, `create_at`, `update_at`, `create_by`, `update_by`, `del_flag`, `version`) VALUES (36, 1, 'test3', NULL, 2, NULL, '123', 0, '2023-05-05 15:22:44', '2023-05-05 15:22:49', 1, 1, b'1', 1);
INSERT INTO `sys_permission` (`id`, `pid`, `name`, `value`, `type`, `icon`, `url`, `sort`, `create_at`, `update_at`, `create_by`, `update_by`, `del_flag`, `version`) VALUES (37, 2, '111', '111', 3, NULL, NULL, 0, '2023-05-05 15:28:30', '2023-05-05 15:28:30', 1, 1, b'1', 1);
INSERT INTO `sys_permission` (`id`, `pid`, `name`, `value`, `type`, `icon`, `url`, `sort`, `create_at`, `update_at`, `create_by`, `update_by`, `del_flag`, `version`) VALUES (38, 2, '222', '222', 3, NULL, NULL, 0, '2023-05-05 15:28:40', '2023-05-05 15:28:40', 1, 1, b'1', 1);
INSERT INTO `sys_permission` (`id`, `pid`, `name`, `value`, `type`, `icon`, `url`, `sort`, `create_at`, `update_at`, `create_by`, `update_by`, `del_flag`, `version`) VALUES (39, 2, '333', '333', 3, NULL, NULL, 0, '2023-05-05 15:29:48', '2023-05-05 15:29:48', 1, 1, b'1', 1);
INSERT INTO `sys_permission` (`id`, `pid`, `name`, `value`, `type`, `icon`, `url`, `sort`, `create_at`, `update_at`, `create_by`, `update_by`, `del_flag`, `version`) VALUES (40, 1, '3333', NULL, 2, NULL, '333', 0, '2023-05-05 15:30:17', '2023-05-05 15:30:17', 1, 1, b'0', 1);
INSERT INTO `sys_permission` (`id`, `pid`, `name`, `value`, `type`, `icon`, `url`, `sort`, `create_at`, `update_at`, `create_by`, `update_by`, `del_flag`, `version`) VALUES (41, 40, '3331', '3331', 3, NULL, NULL, 0, '2023-05-05 15:33:59', '2023-05-05 15:33:59', 1, 1, b'0', 1);
COMMIT;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
                            `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
                            `value` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '编码值',
                            `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '中文名称',
                            `description` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '描述',
                            `create_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                            `update_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
                            `create_by` bigint unsigned DEFAULT NULL,
                            `update_by` bigint unsigned DEFAULT NULL,
                            `del_flag` bit(1) NOT NULL DEFAULT b'0',
                            `version` bigint unsigned NOT NULL DEFAULT '1',
                            PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='系统角色';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_role` (`id`, `value`, `name`, `description`, `create_at`, `update_at`, `create_by`, `update_by`, `del_flag`, `version`) VALUES (1, 'ROLE_ADMIN', '系统管理员', '系统管理员', '2020-08-09 23:40:11', '2020-08-09 23:40:11', NULL, NULL, b'0', 1);
INSERT INTO `sys_role` (`id`, `value`, `name`, `description`, `create_at`, `update_at`, `create_by`, `update_by`, `del_flag`, `version`) VALUES (6, 'ROLE_USER', '用户', '用户', '2020-11-01 21:20:37', '2020-11-01 21:20:37', NULL, NULL, b'0', 1);
INSERT INTO `sys_role` (`id`, `value`, `name`, `description`, `create_at`, `update_at`, `create_by`, `update_by`, `del_flag`, `version`) VALUES (7, 'USER_GUESS', '游客', '访问游客', '2021-11-11 22:33:47', '2021-11-11 22:33:47', NULL, NULL, b'0', 1);
INSERT INTO `sys_role` (`id`, `value`, `name`, `description`, `create_at`, `update_at`, `create_by`, `update_by`, `del_flag`, `version`) VALUES (8, 'test1', 'test1', NULL, '2023-04-11 15:34:51', '2023-04-11 15:34:51', 1, 1, b'0', 1);
INSERT INTO `sys_role` (`id`, `value`, `name`, `description`, `create_at`, `update_at`, `create_by`, `update_by`, `del_flag`, `version`) VALUES (9, 'test23', 'test2', NULL, '2023-04-11 15:42:10', '2023-05-05 15:46:35', NULL, 1, b'0', 3);
INSERT INTO `sys_role` (`id`, `value`, `name`, `description`, `create_at`, `update_at`, `create_by`, `update_by`, `del_flag`, `version`) VALUES (10, 'test3', 'test3', NULL, '2023-05-05 14:39:30', '2023-05-05 14:41:09', 1, 1, b'1', 1);
INSERT INTO `sys_role` (`id`, `value`, `name`, `description`, `create_at`, `update_at`, `create_by`, `update_by`, `del_flag`, `version`) VALUES (11, 'test3', 'test3', NULL, '2023-05-05 14:41:19', '2023-05-08 16:43:53', 1, 1, b'0', 6);
COMMIT;

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission` (
                                       `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
                                       `r_id` bigint unsigned NOT NULL COMMENT '角色id',
                                       `p_id` bigint unsigned NOT NULL COMMENT '权限id',
                                       `create_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                       `update_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
                                       `create_by` bigint DEFAULT NULL COMMENT '创建人id',
                                       `update_by` bigint DEFAULT NULL COMMENT '更新人id',
                                       `del_flag` bit(1) NOT NULL DEFAULT b'0' COMMENT '删除标记 1-已删除',
                                       `version` bigint unsigned NOT NULL DEFAULT '1' COMMENT '乐观锁版本',
                                       PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=124 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
BEGIN;
INSERT INTO `sys_role_permission` (`id`, `r_id`, `p_id`, `create_at`, `update_at`, `create_by`, `update_by`, `del_flag`, `version`) VALUES (7, 1, 7, '2023-11-02 09:39:08', '2023-11-02 09:39:08', NULL, NULL, b'0', 0);
INSERT INTO `sys_role_permission` (`id`, `r_id`, `p_id`, `create_at`, `update_at`, `create_by`, `update_by`, `del_flag`, `version`) VALUES (8, 1, 8, '2023-11-02 09:39:08', '2023-11-02 09:39:08', NULL, NULL, b'0', 0);
INSERT INTO `sys_role_permission` (`id`, `r_id`, `p_id`, `create_at`, `update_at`, `create_by`, `update_by`, `del_flag`, `version`) VALUES (9, 1, 9, '2023-11-02 09:39:08', '2023-11-02 09:39:08', NULL, NULL, b'0', 0);
INSERT INTO `sys_role_permission` (`id`, `r_id`, `p_id`, `create_at`, `update_at`, `create_by`, `update_by`, `del_flag`, `version`) VALUES (84, 1, 10, '2023-11-02 09:39:08', '2023-11-02 09:39:08', NULL, NULL, b'0', 0);
INSERT INTO `sys_role_permission` (`id`, `r_id`, `p_id`, `create_at`, `update_at`, `create_by`, `update_by`, `del_flag`, `version`) VALUES (86, 1, 1, '2023-11-02 09:39:08', '2023-11-02 09:39:08', NULL, NULL, b'0', 0);
INSERT INTO `sys_role_permission` (`id`, `r_id`, `p_id`, `create_at`, `update_at`, `create_by`, `update_by`, `del_flag`, `version`) VALUES (87, 1, 2, '2023-11-02 09:39:08', '2023-11-02 09:39:08', NULL, NULL, b'0', 0);
INSERT INTO `sys_role_permission` (`id`, `r_id`, `p_id`, `create_at`, `update_at`, `create_by`, `update_by`, `del_flag`, `version`) VALUES (88, 1, 3, '2023-11-02 09:39:08', '2023-11-02 09:39:08', NULL, NULL, b'0', 0);
INSERT INTO `sys_role_permission` (`id`, `r_id`, `p_id`, `create_at`, `update_at`, `create_by`, `update_by`, `del_flag`, `version`) VALUES (89, 1, 4, '2023-11-02 09:39:08', '2023-11-02 09:39:08', NULL, NULL, b'0', 0);
INSERT INTO `sys_role_permission` (`id`, `r_id`, `p_id`, `create_at`, `update_at`, `create_by`, `update_by`, `del_flag`, `version`) VALUES (90, 1, 5, '2023-11-02 09:39:08', '2023-11-02 09:39:08', NULL, NULL, b'0', 0);
INSERT INTO `sys_role_permission` (`id`, `r_id`, `p_id`, `create_at`, `update_at`, `create_by`, `update_by`, `del_flag`, `version`) VALUES (91, 1, 6, '2023-11-02 09:39:08', '2023-11-02 09:39:08', NULL, NULL, b'0', 0);
INSERT INTO `sys_role_permission` (`id`, `r_id`, `p_id`, `create_at`, `update_at`, `create_by`, `update_by`, `del_flag`, `version`) VALUES (92, 1, 12, '2023-11-02 09:39:08', '2023-11-02 09:39:08', NULL, NULL, b'0', 0);
INSERT INTO `sys_role_permission` (`id`, `r_id`, `p_id`, `create_at`, `update_at`, `create_by`, `update_by`, `del_flag`, `version`) VALUES (93, 1, 14, '2023-11-02 09:39:08', '2023-11-02 09:39:08', NULL, NULL, b'0', 0);
INSERT INTO `sys_role_permission` (`id`, `r_id`, `p_id`, `create_at`, `update_at`, `create_by`, `update_by`, `del_flag`, `version`) VALUES (94, 1, 15, '2023-11-02 09:39:08', '2023-11-02 09:39:08', NULL, NULL, b'0', 0);
INSERT INTO `sys_role_permission` (`id`, `r_id`, `p_id`, `create_at`, `update_at`, `create_by`, `update_by`, `del_flag`, `version`) VALUES (95, 1, 16, '2023-11-02 09:39:08', '2023-11-02 09:39:08', NULL, NULL, b'0', 0);
INSERT INTO `sys_role_permission` (`id`, `r_id`, `p_id`, `create_at`, `update_at`, `create_by`, `update_by`, `del_flag`, `version`) VALUES (96, 1, 17, '2023-11-02 09:39:08', '2023-11-02 09:39:08', NULL, NULL, b'0', 0);
INSERT INTO `sys_role_permission` (`id`, `r_id`, `p_id`, `create_at`, `update_at`, `create_by`, `update_by`, `del_flag`, `version`) VALUES (97, 1, 19, '2023-11-02 09:39:08', '2023-11-02 09:39:08', NULL, NULL, b'0', 0);
INSERT INTO `sys_role_permission` (`id`, `r_id`, `p_id`, `create_at`, `update_at`, `create_by`, `update_by`, `del_flag`, `version`) VALUES (98, 1, 21, '2023-11-02 09:39:08', '2023-11-02 09:39:08', NULL, NULL, b'0', 0);
INSERT INTO `sys_role_permission` (`id`, `r_id`, `p_id`, `create_at`, `update_at`, `create_by`, `update_by`, `del_flag`, `version`) VALUES (99, 1, 20, '2023-11-02 09:39:08', '2023-11-02 09:39:08', NULL, NULL, b'0', 0);
INSERT INTO `sys_role_permission` (`id`, `r_id`, `p_id`, `create_at`, `update_at`, `create_by`, `update_by`, `del_flag`, `version`) VALUES (100, 1, 22, '2023-11-02 09:39:08', '2023-11-02 09:39:08', NULL, NULL, b'0', 0);
INSERT INTO `sys_role_permission` (`id`, `r_id`, `p_id`, `create_at`, `update_at`, `create_by`, `update_by`, `del_flag`, `version`) VALUES (103, 1, 11, '2023-11-02 09:39:08', '2023-11-02 09:39:08', NULL, NULL, b'0', 0);
INSERT INTO `sys_role_permission` (`id`, `r_id`, `p_id`, `create_at`, `update_at`, `create_by`, `update_by`, `del_flag`, `version`) VALUES (104, 1, 23, '2023-11-02 09:39:08', '2023-11-02 09:39:08', NULL, NULL, b'0', 0);
INSERT INTO `sys_role_permission` (`id`, `r_id`, `p_id`, `create_at`, `update_at`, `create_by`, `update_by`, `del_flag`, `version`) VALUES (105, 1, 24, '2023-11-02 09:39:08', '2023-11-02 09:39:08', NULL, NULL, b'0', 0);
INSERT INTO `sys_role_permission` (`id`, `r_id`, `p_id`, `create_at`, `update_at`, `create_by`, `update_by`, `del_flag`, `version`) VALUES (106, 1, 26, '2023-11-02 09:39:08', '2023-11-02 09:39:08', NULL, NULL, b'0', 0);
INSERT INTO `sys_role_permission` (`id`, `r_id`, `p_id`, `create_at`, `update_at`, `create_by`, `update_by`, `del_flag`, `version`) VALUES (107, 1, 27, '2023-11-02 09:39:08', '2023-11-02 09:39:08', NULL, NULL, b'0', 0);
INSERT INTO `sys_role_permission` (`id`, `r_id`, `p_id`, `create_at`, `update_at`, `create_by`, `update_by`, `del_flag`, `version`) VALUES (108, 1, 28, '2023-11-02 09:39:08', '2023-11-02 09:39:08', NULL, NULL, b'0', 0);
INSERT INTO `sys_role_permission` (`id`, `r_id`, `p_id`, `create_at`, `update_at`, `create_by`, `update_by`, `del_flag`, `version`) VALUES (109, 7, 2, '2023-11-02 09:39:08', '2023-11-02 09:39:08', NULL, NULL, b'0', 0);
INSERT INTO `sys_role_permission` (`id`, `r_id`, `p_id`, `create_at`, `update_at`, `create_by`, `update_by`, `del_flag`, `version`) VALUES (110, 7, 1, '2023-11-02 09:39:08', '2023-11-02 09:39:08', NULL, NULL, b'0', 0);
INSERT INTO `sys_role_permission` (`id`, `r_id`, `p_id`, `create_at`, `update_at`, `create_by`, `update_by`, `del_flag`, `version`) VALUES (111, 7, 7, '2023-11-02 09:39:08', '2023-11-02 09:39:08', NULL, NULL, b'0', 0);
INSERT INTO `sys_role_permission` (`id`, `r_id`, `p_id`, `create_at`, `update_at`, `create_by`, `update_by`, `del_flag`, `version`) VALUES (112, 7, 12, '2023-11-02 09:39:08', '2023-11-02 09:39:08', NULL, NULL, b'0', 0);
INSERT INTO `sys_role_permission` (`id`, `r_id`, `p_id`, `create_at`, `update_at`, `create_by`, `update_by`, `del_flag`, `version`) VALUES (113, 7, 14, '2023-11-02 09:39:08', '2023-11-02 09:39:08', NULL, NULL, b'0', 0);
INSERT INTO `sys_role_permission` (`id`, `r_id`, `p_id`, `create_at`, `update_at`, `create_by`, `update_by`, `del_flag`, `version`) VALUES (114, 7, 26, '2023-11-02 09:39:08', '2023-11-02 09:39:08', NULL, NULL, b'0', 0);
INSERT INTO `sys_role_permission` (`id`, `r_id`, `p_id`, `create_at`, `update_at`, `create_by`, `update_by`, `del_flag`, `version`) VALUES (115, 7, 27, '2023-11-02 09:39:08', '2023-11-02 09:39:08', NULL, NULL, b'0', 0);
INSERT INTO `sys_role_permission` (`id`, `r_id`, `p_id`, `create_at`, `update_at`, `create_by`, `update_by`, `del_flag`, `version`) VALUES (116, 7, 8, '2023-11-02 09:39:08', '2023-11-02 09:39:08', NULL, NULL, b'0', 0);
INSERT INTO `sys_role_permission` (`id`, `r_id`, `p_id`, `create_at`, `update_at`, `create_by`, `update_by`, `del_flag`, `version`) VALUES (117, 7, 9, '2023-11-02 09:39:08', '2023-11-02 09:39:08', NULL, NULL, b'0', 0);
INSERT INTO `sys_role_permission` (`id`, `r_id`, `p_id`, `create_at`, `update_at`, `create_by`, `update_by`, `del_flag`, `version`) VALUES (118, 7, 22, '2023-11-02 09:39:08', '2023-11-02 09:39:08', NULL, NULL, b'0', 0);
INSERT INTO `sys_role_permission` (`id`, `r_id`, `p_id`, `create_at`, `update_at`, `create_by`, `update_by`, `del_flag`, `version`) VALUES (119, 7, 19, '2023-11-02 09:39:08', '2023-11-02 09:39:08', NULL, NULL, b'0', 0);
INSERT INTO `sys_role_permission` (`id`, `r_id`, `p_id`, `create_at`, `update_at`, `create_by`, `update_by`, `del_flag`, `version`) VALUES (120, 8, 40, '2023-11-02 09:39:08', '2023-11-02 09:39:08', NULL, NULL, b'0', 0);
INSERT INTO `sys_role_permission` (`id`, `r_id`, `p_id`, `create_at`, `update_at`, `create_by`, `update_by`, `del_flag`, `version`) VALUES (121, 8, 1, '2023-11-02 09:39:08', '2023-11-02 09:39:08', NULL, NULL, b'0', 0);
INSERT INTO `sys_role_permission` (`id`, `r_id`, `p_id`, `create_at`, `update_at`, `create_by`, `update_by`, `del_flag`, `version`) VALUES (122, 11, 1, '2023-11-02 09:39:08', '2023-11-02 09:39:08', NULL, NULL, b'0', 0);
INSERT INTO `sys_role_permission` (`id`, `r_id`, `p_id`, `create_at`, `update_at`, `create_by`, `update_by`, `del_flag`, `version`) VALUES (123, 11, 7, '2023-11-02 09:39:08', '2023-11-02 09:39:08', NULL, NULL, b'0', 0);
COMMIT;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
                            `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
                            `account` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '账号',
                            `pwd` char(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '密码',
                            `create_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                            `update_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
                            `create_by` bigint unsigned DEFAULT NULL,
                            `update_by` bigint unsigned DEFAULT NULL,
                            `del_flag` bit(1) NOT NULL DEFAULT b'0',
                            `version` bigint unsigned NOT NULL DEFAULT '1',
                            PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=81 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='系统用户';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
BEGIN;
INSERT INTO `sys_user` (`id`, `account`, `pwd`, `create_at`, `update_at`, `create_by`, `update_by`, `del_flag`, `version`) VALUES (1, 'admin', '$2a$10$zxjPkL7gpB.NV2Jyq6bDZ./4FoNJNBo9h1ITgJZYT91umTc.ttJDe', '2020-08-10 17:15:48', '2020-08-10 17:15:48', NULL, NULL, b'0', 1);
INSERT INTO `sys_user` (`id`, `account`, `pwd`, `create_at`, `update_at`, `create_by`, `update_by`, `del_flag`, `version`) VALUES (69, 'test', '123456', '2020-11-22 13:43:44', '2020-11-22 13:43:44', NULL, NULL, b'0', 1);
INSERT INTO `sys_user` (`id`, `account`, `pwd`, `create_at`, `update_at`, `create_by`, `update_by`, `del_flag`, `version`) VALUES (70, 'test11', 'test11', '2020-11-22 13:45:05', '2020-11-22 13:45:05', NULL, NULL, b'0', 1);
INSERT INTO `sys_user` (`id`, `account`, `pwd`, `create_at`, `update_at`, `create_by`, `update_by`, `del_flag`, `version`) VALUES (71, 'test22', 'test22', '2020-11-22 13:51:32', '2020-11-22 13:51:32', NULL, NULL, b'0', 1);
INSERT INTO `sys_user` (`id`, `account`, `pwd`, `create_at`, `update_at`, `create_by`, `update_by`, `del_flag`, `version`) VALUES (72, 'test3', '$2a$10$Fk9x/S8ynoXptETum8/tmOLbLSyztBsXHh.JWomujKWjhPcRU8tWS', '2023-05-05 14:03:25', '2023-05-05 14:25:07', 1, 1, b'1', 8);
INSERT INTO `sys_user` (`id`, `account`, `pwd`, `create_at`, `update_at`, `create_by`, `update_by`, `del_flag`, `version`) VALUES (73, 'test3', '$2a$10$2pfSzxsr3KQfvbvRoPqaQ.dlcvQ6nWaIyiHsHy2Jkba7agmXcojA.', '2023-05-05 14:25:38', '2023-05-05 14:25:38', 1, 1, b'0', 1);
INSERT INTO `sys_user` (`id`, `account`, `pwd`, `create_at`, `update_at`, `create_by`, `update_by`, `del_flag`, `version`) VALUES (74, 'test4', '$2a$10$AvtImjg3qqWhbC/I1TwfEO/Oc8HZUCqf7Mjkz0NuKc1atB/o46nP2', '2023-05-05 14:25:50', '2023-05-05 14:25:50', 1, 1, b'0', 1);
INSERT INTO `sys_user` (`id`, `account`, `pwd`, `create_at`, `update_at`, `create_by`, `update_by`, `del_flag`, `version`) VALUES (75, 'test5', '$2a$10$KUNKkdlFPJeXdF/VGf.WEuyqeQEsZgwimG/VM1dJliW0jS.tk8Lza', '2023-05-05 14:26:03', '2023-05-05 14:26:03', 1, 1, b'0', 1);
INSERT INTO `sys_user` (`id`, `account`, `pwd`, `create_at`, `update_at`, `create_by`, `update_by`, `del_flag`, `version`) VALUES (76, 'test6', '$2a$10$MDUzipJSYudrHfMytzxGquL3BfAGQLn/3WVf/lonPrmUuIPtIlI3K', '2023-05-05 14:26:12', '2023-05-05 14:26:12', 1, 1, b'0', 1);
INSERT INTO `sys_user` (`id`, `account`, `pwd`, `create_at`, `update_at`, `create_by`, `update_by`, `del_flag`, `version`) VALUES (77, 'test7', '$2a$10$LwdP4dIuPx.XxAJStFzMYOAy1VpO1Ny4PmETl50SL5EQ/Etq7ko6K', '2023-05-05 14:28:19', '2023-05-05 14:28:19', 1, 1, b'0', 1);
INSERT INTO `sys_user` (`id`, `account`, `pwd`, `create_at`, `update_at`, `create_by`, `update_by`, `del_flag`, `version`) VALUES (78, 'test8', '', '2023-05-05 14:28:28', '2023-05-05 14:30:25', 1, 1, b'0', 3);
INSERT INTO `sys_user` (`id`, `account`, `pwd`, `create_at`, `update_at`, `create_by`, `update_by`, `del_flag`, `version`) VALUES (79, 'test9', '$2a$10$zjxQECQGZkCfDIy.jCZzUOtUFbNM3sx1LO4J7tcvfncwlfwmv6js6', '2023-05-05 14:28:35', '2023-05-05 14:29:09', 1, 1, b'1', 1);
INSERT INTO `sys_user` (`id`, `account`, `pwd`, `create_at`, `update_at`, `create_by`, `update_by`, `del_flag`, `version`) VALUES (80, 'test10', '$2a$10$iD.aqmqSrqTOqurtfClxx.DUgE.SMdK9WF43dLbuRLe6Y7Bsvx9.W', '2023-05-05 14:28:42', '2023-05-05 14:29:07', 1, 1, b'1', 1);
COMMIT;

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
                                 `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
                                 `u_id` bigint unsigned NOT NULL COMMENT '用户id',
                                 `r_id` bigint unsigned NOT NULL COMMENT '角色id',
                                 `create_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                 `update_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
                                 `create_by` bigint DEFAULT NULL COMMENT '创建人id',
                                 `update_by` bigint DEFAULT NULL COMMENT '更新人id',
                                 `del_flag` bit(1) NOT NULL COMMENT '删除标记 0-已删除',
                                 `version` bigint NOT NULL COMMENT '乐观锁版本',
                                 PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_user_role` (`id`, `u_id`, `r_id`, `create_at`, `update_at`, `create_by`, `update_by`, `del_flag`, `version`) VALUES (1, 1, 1, '2023-11-02 09:39:42', '2023-11-02 09:39:44', NULL, NULL, b'0', 0);
INSERT INTO `sys_user_role` (`id`, `u_id`, `r_id`, `create_at`, `update_at`, `create_by`, `update_by`, `del_flag`, `version`) VALUES (2, 1, 6, '2023-11-02 09:39:42', '2023-11-02 09:39:42', NULL, NULL, b'0', 0);
INSERT INTO `sys_user_role` (`id`, `u_id`, `r_id`, `create_at`, `update_at`, `create_by`, `update_by`, `del_flag`, `version`) VALUES (6, 69, 7, '2023-11-02 09:39:42', '2023-11-02 09:39:42', NULL, NULL, b'0', 0);
INSERT INTO `sys_user_role` (`id`, `u_id`, `r_id`, `create_at`, `update_at`, `create_by`, `update_by`, `del_flag`, `version`) VALUES (7, 77, 6, '2023-11-02 09:39:42', '2023-11-02 09:39:42', NULL, NULL, b'0', 0);
INSERT INTO `sys_user_role` (`id`, `u_id`, `r_id`, `create_at`, `update_at`, `create_by`, `update_by`, `del_flag`, `version`) VALUES (8, 77, 7, '2023-11-02 09:39:42', '2023-11-02 09:39:42', NULL, NULL, b'0', 0);
INSERT INTO `sys_user_role` (`id`, `u_id`, `r_id`, `create_at`, `update_at`, `create_by`, `update_by`, `del_flag`, `version`) VALUES (9, 75, 8, '2023-11-02 09:39:42', '2023-11-02 09:39:42', NULL, NULL, b'0', 0);
INSERT INTO `sys_user_role` (`id`, `u_id`, `r_id`, `create_at`, `update_at`, `create_by`, `update_by`, `del_flag`, `version`) VALUES (10, 75, 9, '2023-11-02 09:39:42', '2023-11-02 09:39:42', NULL, NULL, b'0', 0);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
