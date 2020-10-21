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

 Date: 21/10/2020 23:34:44
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
  `url` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '前端资源路径',
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
INSERT INTO `sys_user` VALUES (3, '7d5bddcc-6d32-4', '$2a$10$Z6fObWXlBLPxs/Q4GLCzLeVmamhwB/w9GyyuNmi.3ysDGiSePBrzq', 1, '2020-09-12 11:08:52', '2020-09-12 11:08:52');
INSERT INTO `sys_user` VALUES (4, 'e43a467a-8569-43f1-9', '$2a$10$ypBOtZ2YmN1ZcphhLJRIA.XzSkveMgIPLsu5F6Mg6QUA9DNvCtoyK', 1, '2020-09-12 11:08:52', '2020-09-12 11:08:52');
INSERT INTO `sys_user` VALUES (5, '3e3ebb90-c8f6-4e37-a37a-ff09479', '$2a$10$0al8yr3KXb59ufd8byLE9u2KEd5nu.xgVHoYTgkVxhvcoRyj..SrK', 1, '2020-09-12 11:08:52', '2020-09-12 11:08:52');
INSERT INTO `sys_user` VALUES (6, '00563bb', '$2a$10$lpDkltc0n6nTJ8bsY18o/eM1nwHdMp3nDhWNE2ZD.x2UQn9MDR70.', 1, '2020-09-12 11:08:52', '2020-09-12 11:08:52');
INSERT INTO `sys_user` VALUES (7, 'ca862695-fcb5-4', '$2a$10$5pmQAPbSXLJrhAcVFnl2o.7Gy9N49htHmvArRpgTATt5J.btwLi3e', 1, '2020-09-12 11:08:52', '2020-09-12 11:08:52');
INSERT INTO `sys_user` VALUES (8, '5a179e69-cbf5', '$2a$10$CzENfB9a8RtEYjq/898HEOhnSMmui0t1I1v48zdpckTUcQy1vZkza', 1, '2020-09-12 11:08:52', '2020-09-12 11:08:52');
INSERT INTO `sys_user` VALUES (9, 'e4f64b9d-3b20-481d-830d-51', '$2a$10$s.i/Lgaf.GMF20EsewpfjOBp.pWblVQmqUDuKwQIU3mNvwbFJbLH6', 1, '2020-09-12 11:08:52', '2020-09-12 11:08:52');
INSERT INTO `sys_user` VALUES (10, 'f', '$2a$10$oShQgXzbgh9QWGB7v/eZ6OCYduAv3tg3GL74q6IUgXiqLa0hbbM9K', 1, '2020-09-12 11:08:52', '2020-09-12 11:08:52');
INSERT INTO `sys_user` VALUES (11, '141da748-a77e-4b39-b841-796', '$2a$10$QcYeMwler9Ok2x/2MwHYWuPdlIpDQaz5mdkjw3VtPpHwHI2ZxQgFK', 1, '2020-09-12 11:08:52', '2020-09-12 11:08:52');
INSERT INTO `sys_user` VALUES (12, 'ac9216cd-3d58-4532-a11', '$2a$10$/OBOm9pcajWnTbrIAZm1cuhnBvhmSVe8iiebGM.mFwunSXyvmqyVy', 1, '2020-09-12 11:08:52', '2020-09-12 11:08:52');
INSERT INTO `sys_user` VALUES (13, '1b62ad01-e41d-4', '$2a$10$E4HW7SlOriO/kFxXYkPnI.V5G6FMzOmrnp3Q3Uhi5ZFF3LSF2BPkO', 1, '2020-09-12 11:08:53', '2020-09-12 11:08:53');
INSERT INTO `sys_user` VALUES (14, 'cf77f6', '$2a$10$WtFXpf1SCBPIhCBbg4hpMemGAaBUOW.8wp4auSqS1V1k0VlpWcBeq', 1, '2020-09-12 11:08:53', '2020-09-12 11:08:53');
INSERT INTO `sys_user` VALUES (15, '7986fe1f-7c67-408d-a19', '$2a$10$Iu5A3SDoULnFwDc4VSfqw.arswX9rAvF3pjf/.WZf661H/.7ZORxy', 1, '2020-09-12 11:08:53', '2020-09-12 11:08:53');
INSERT INTO `sys_user` VALUES (16, '88b3a900-dd90-410d-af87-', '$2a$10$0mlFt/n4vpm0I0W5f.6.eeQqW0WMSu2CgxJZ4826WKpru21ajbAQy', 1, '2020-09-12 11:08:53', '2020-09-12 11:08:53');
INSERT INTO `sys_user` VALUES (17, 'd27a3a74-e8b7-4c2', '$2a$10$tOomBnJIK4ZaZ5X/XvtWB.yjkagPT70Qn7.UqMdJ9Cce9vUVBYNj.', 1, '2020-09-12 11:08:53', '2020-09-12 11:08:53');
INSERT INTO `sys_user` VALUES (18, '06d172fd-d065-43e5-9271-66', '$2a$10$.DjNfJiA4obhFYUofyMLse8XYFleodPZJeCn4LHxTAYal5lyiqWay', 1, '2020-09-12 11:08:53', '2020-09-12 11:08:53');
INSERT INTO `sys_user` VALUES (19, 'b82a61d4-36d6-', '$2a$10$hXwvAxFRxXO6Reui.z5.xe0dJ/KdJMaj28nvk91ZTlpRujwx083/a', 1, '2020-09-12 11:08:53', '2020-09-12 11:08:53');
INSERT INTO `sys_user` VALUES (20, 'eb46365c-2fc2-4a5', '$2a$10$62o5UU3bYFW1fUYYZf/HIuk9T0wmCbv6CJycZxgLX3343tOgJ7PxC', 1, '2020-09-12 11:08:53', '2020-09-12 11:08:53');
INSERT INTO `sys_user` VALUES (21, '3cfd5ca1-a792-4f84-bdb9-13', '$2a$10$ns.vggHPa9iPZrrEZppRo.O7rbwblvp6dGxUwd2prOKZwRprldY/O', 1, '2020-09-12 11:08:53', '2020-09-12 11:08:53');
INSERT INTO `sys_user` VALUES (22, 'c05df891-593a-4136-98db-9b13297', '$2a$10$p.1I7wF2NpydoSVZ/4p4fOKdy4o32pqXrOgy6wX3QcxMGzYlxccX6', 1, '2020-09-12 11:08:53', '2020-09-12 11:08:53');
INSERT INTO `sys_user` VALUES (23, 'ec8345b9-a386-408f-', '$2a$10$nhz40DG0QAYOfDYtTKz4LO/eYP56ywkWAkBcQndMQOu5P2Y69rdpe', 1, '2020-09-12 11:08:53', '2020-09-12 11:08:53');
INSERT INTO `sys_user` VALUES (24, '2e78dd', '$2a$10$QQ267GEBR2Q11Pa959QR0uPcc2ChIJVhniK7psjTPz9hQrm7saJSm', 1, '2020-09-12 11:08:53', '2020-09-12 11:08:53');
INSERT INTO `sys_user` VALUES (25, '3eb', '$2a$10$w1PCXYDuoyRBphRDYCy.UeHbpd5YAZc1S1yS2gVyHEeulPWFhQfDu', 1, '2020-09-12 11:08:53', '2020-09-12 11:08:53');
INSERT INTO `sys_user` VALUES (26, '14a9', '$2a$10$DdQPYm./RqgvlSKjgCAs3OBrRe0APs6FmVNb5tk4ny72kcn/9x31.', 1, '2020-09-12 11:08:53', '2020-09-12 11:08:53');
INSERT INTO `sys_user` VALUES (27, '402d7b60-9031-48b3-ab47-21338a5', '$2a$10$u2/14Pwy9icCmfLHYhvEJuaS3oOybMddsbUUk.HGG4nuVrLnmThn6', 1, '2020-09-12 11:08:54', '2020-09-12 11:08:54');
INSERT INTO `sys_user` VALUES (28, '40eae234-4bd3-442c-9de3-da8b86', '$2a$10$tMQq8pOGcPsOCptJnCfUrO1JNQeEZUXXP.FXUpGvhJ8MMOmA.X8wG', 1, '2020-09-12 11:08:54', '2020-09-12 11:08:54');
INSERT INTO `sys_user` VALUES (29, '2bec3fd', '$2a$10$0uUjuvWS5ldBmpOM4TKNG.og.cSc6//lNP8wiODaUSRrwmXfZQ.iG', 1, '2020-09-12 11:08:54', '2020-09-12 11:08:54');
INSERT INTO `sys_user` VALUES (30, '5f57a3b1-5f64-492f-b146-6ab2bb', '$2a$10$M6t.zZWkdYpKTva9FTNufObMqWLWIjCzWhmbFhla8QKJ5gXgyOD1q', 1, '2020-09-12 11:08:54', '2020-09-12 11:08:54');
INSERT INTO `sys_user` VALUES (31, 'bdc8a222-9241-459e-96a8-6a2e880', '$2a$10$QeaSzbZD0WM6q1bb8tJ/z.J.JHuqQV1QgFJ1A1.Wr20b0B0sAmqAa', 1, '2020-09-12 11:08:54', '2020-09-12 11:08:54');
INSERT INTO `sys_user` VALUES (32, 'ab390435-df61-4f43-b6c4-fb12', '$2a$10$5Y99/8aJ.dkZ5SlyD/e3kegbIOmLHvWY2fWe50AyUtI7exj2SMydW', 1, '2020-09-12 11:08:54', '2020-09-12 11:08:54');
INSERT INTO `sys_user` VALUES (33, 'bd2defda-ec', '$2a$10$wiYFHD7G08Hhh8f7g6VzVeF3dKgGKZif.DUb/rhx1.oUs9JEaEE0.', 1, '2020-09-12 11:08:54', '2020-09-12 11:08:54');
INSERT INTO `sys_user` VALUES (34, 'f13cdb1', '$2a$10$9AdppVf6ie6XvvSz7vFX8eRYg8GPCzlV6sKK9GN5c/0ZEANuqMzCO', 1, '2020-09-12 11:08:54', '2020-09-12 11:08:54');
INSERT INTO `sys_user` VALUES (35, '35caa047-f8ba-4', '$2a$10$K4JjQyXZZ9JO5Z9DQSDMl.sgnwczJlWGXf4p0RdaMNYuxG/WZsAXy', 1, '2020-09-12 11:08:54', '2020-09-12 11:08:54');
INSERT INTO `sys_user` VALUES (36, '3520d4d7-b4e8-42f8-', '$2a$10$TunX1.bo3mkpnvF./1dL4uW3vf9ZCdK/ItuMG0ayEdlyubhNKXsiC', 1, '2020-09-12 11:08:54', '2020-09-12 11:08:54');
INSERT INTO `sys_user` VALUES (37, '1cf8b621-c5a7-4f76-', '$2a$10$8Wn5DcAURpK82wF.CXzUEuF8hirUpR2CWVEpOintgKJxKV9AGqPqu', 1, '2020-09-12 11:08:54', '2020-09-12 11:08:54');
INSERT INTO `sys_user` VALUES (38, '1ffaaf7b-54b4-460d-937b-b03e8', '$2a$10$AsWR4cc0LMasN.zf478jAO4ChtUn2JnoiY7ZDZxSOAkL8.5KZpqqi', 1, '2020-09-12 11:08:54', '2020-09-12 11:08:54');
INSERT INTO `sys_user` VALUES (39, '7c85ed56-31cd-43da', '$2a$10$qCBnf2HCKJLRGKDMhOSgd.ML/hBiE4ezYA5jjS8OtzjslgC/.nqlK', 1, '2020-09-12 11:08:54', '2020-09-12 11:08:54');
INSERT INTO `sys_user` VALUES (40, '66b90af7-9fe1-4515-94b4-f95d', '$2a$10$pck3oXsCOLZs3s4iO1xbUukkMHbmtA8qMcf7fWUmvYg5HRcGwttfe', 1, '2020-09-12 11:08:54', '2020-09-12 11:08:54');
INSERT INTO `sys_user` VALUES (41, '5d552c5b-095d-451b-a', '$2a$10$d09P0baugAK4UZ58sVIoau1OJxRSdwoptxfkLo3BcD49.oquySv3q', 1, '2020-09-12 11:08:54', '2020-09-12 11:08:54');
INSERT INTO `sys_user` VALUES (42, '60cb5b78-3b1', '$2a$10$EGVqO.jFon2cJf2KiMF42ec0xSwScEHPOxtJnr0La5sF2QlouVB.e', 1, '2020-09-12 11:08:55', '2020-09-12 11:08:55');
INSERT INTO `sys_user` VALUES (43, '55d79a', '$2a$10$pZIPIWuTd1CIq83Jiz.wau8bC2eUrhv1eT1hYzM0kL8HfMIDe7/Xm', 1, '2020-09-12 11:08:55', '2020-09-12 11:08:55');
INSERT INTO `sys_user` VALUES (44, '04b64229-1a8f-4300-9103-b736f29', '$2a$10$Wz4/70x2sDeMAiC3zI0KJeO.czdNj2.Ar9skaKH7PNaQDcQgLspkC', 1, '2020-09-12 11:08:55', '2020-09-12 11:08:55');
INSERT INTO `sys_user` VALUES (45, '7f145c10-8', '$2a$10$HCTZZzBgwvxdYZ7v45NdoeWxM4wzKWV/QYNpGkVIOyOCO3mUO04aK', 1, '2020-09-12 11:08:55', '2020-09-12 11:08:55');
INSERT INTO `sys_user` VALUES (46, '68b82ace-aab', '$2a$10$7BOK1rtituqH9u3Y68GVFOqSfkk5c/pkN.T43JNIuZ8QNsfbRgQ2C', 1, '2020-09-12 11:08:55', '2020-09-12 11:08:55');
INSERT INTO `sys_user` VALUES (47, 'eed799b', '$2a$10$P2d0MWSYvLoS4Ojz.NR95OjBBfnX5t2o8MIf1GjZs7wgdzDNbFHuG', 1, '2020-09-12 11:08:55', '2020-09-12 11:08:55');
INSERT INTO `sys_user` VALUES (48, '2f793f85-ed0f-4f65-87c5-071a', '$2a$10$lxXr4N4X2b.LcxwCpzfL8e5OCxc7WaBCyIm1iDa5tuXw3Rz0bmIKm', 1, '2020-09-12 11:08:55', '2020-09-12 11:08:55');
INSERT INTO `sys_user` VALUES (49, '9', '$2a$10$wT1nEgy/TeLBUv2ZfvFEFuJjcmYUWT1y3CrUVG9NbdduP7n2ZU70i', 1, '2020-09-12 11:08:55', '2020-09-12 11:08:55');
INSERT INTO `sys_user` VALUES (50, '28115794-5d1f-4e7a-87eb-2', '$2a$10$sb0YO6EipkSBMnQGomAkHuoOyCGQ7vjI4voMhpy4UiGzzk.FTBXgK', 1, '2020-09-12 11:08:55', '2020-09-12 11:08:55');
INSERT INTO `sys_user` VALUES (51, '0a777be9-27af-439d-bc8a-2da6', '$2a$10$FAM5aNmBoQtohru8OnhQQ.p33bB/nc2k/4EKN3GK0Em/YXHMENGDy', 1, '2020-09-12 11:08:55', '2020-09-12 11:08:55');
INSERT INTO `sys_user` VALUES (52, 'a696cf4f-453d-435d-8de8-c05', '$2a$10$LZONPd41NQ/KhAzVR5xvQ.42vyjeweBd4EQ.X49j5YXqrX.KNbkFe', 1, '2020-09-12 11:08:55', '2020-09-12 11:08:55');
INSERT INTO `sys_user` VALUES (53, 'a8774c60-d327-40', '$2a$10$CkUPh.9hhpAp/bS1iWH8DOdhJXddZxDg/bBc1BTGWzHuLT950yqrG', 1, '2020-09-12 11:08:55', '2020-09-12 11:08:55');
INSERT INTO `sys_user` VALUES (54, '5c2', '$2a$10$h4gqNxYOrbiAVGl/9STRUem2R.3GgMtYhuOk9eLYImrAJT.5tflDK', 1, '2020-09-12 11:08:55', '2020-09-12 11:08:55');
INSERT INTO `sys_user` VALUES (55, '00320a74-19c7-4', '$2a$10$BHENwG8nFu73U5Cmu/Grre49rRexbEVnpp9G74C5h0khftm5YN1s6', 1, '2020-09-12 11:08:55', '2020-09-12 11:08:55');
INSERT INTO `sys_user` VALUES (56, '590ed421-6d27-497a-8ec2', '$2a$10$lVsDj00cX4pt9I5QOV6uS.WrZDRwI1jduPJgo1FnXyFnGycsgQa6e', 1, '2020-09-12 11:08:55', '2020-09-12 11:08:55');
INSERT INTO `sys_user` VALUES (57, 'ae326205-523a-4c', '$2a$10$RanVquSm1b1SG70lfGbOQuFppDRslfaEHjsLXwGNlLW1OUUAFw3wO', 1, '2020-09-12 11:08:56', '2020-09-12 11:08:56');
INSERT INTO `sys_user` VALUES (58, 'b794c27f-24', '$2a$10$q8rhqUzffYkbv0kJ0w58jugrYpx0d5DDDnR0syY6g3LfEcfRkAXfu', 1, '2020-09-12 11:08:56', '2020-09-12 11:08:56');
INSERT INTO `sys_user` VALUES (59, '4b25e8cb-686c-40b', '$2a$10$e9AAiBtTwAFUTnAvqMat8.8VglSN.NDBahrkbnjqkzSps20a7Ec1u', 1, '2020-09-12 11:08:56', '2020-09-12 11:08:56');
INSERT INTO `sys_user` VALUES (60, '436d1ea8-d200-45ac-806b', '$2a$10$/oe/5Yf0seWF6mmRy0zblu.GE28SUPxvWUgA4KPqL0mJC5Uw/T/52', 1, '2020-09-12 11:08:56', '2020-09-12 11:08:56');
INSERT INTO `sys_user` VALUES (61, '66f40', '$2a$10$JGjCSGvOMH/kMncHIC191erG2lEz8xd8iPfPFfDuxbJdB.QNYvRAG', 1, '2020-09-12 11:08:56', '2020-09-12 11:08:56');
INSERT INTO `sys_user` VALUES (62, '3e1726b9-2e82-479e-8374-38264', '$2a$10$WIc450sxScL0xbRS.HLVBOlwH7nyNRJyIeB24dCl3SJnAwwI5FTKa', 1, '2020-09-12 11:08:56', '2020-09-12 11:08:56');
INSERT INTO `sys_user` VALUES (63, 'd857476', '$2a$10$R9o5KaezEOgaiVWTQeQ.o.zvKljGG6HwTPx4/xqDvKNqBNsTslRRa', 1, '2020-09-12 11:08:56', '2020-09-12 11:08:56');
INSERT INTO `sys_user` VALUES (64, '884bca69-7f00-4', '$2a$10$cNpTq0of71SwJG/XmLOzrOd/MMoZZPL.KN9s9ayMeHK80Q8VAsyia', 1, '2020-09-12 11:08:56', '2020-09-12 11:08:56');
INSERT INTO `sys_user` VALUES (65, '60ca8e8f-9f31-4dee-8630-b6d21', '$2a$10$Fpmx5Bm6XdcbOe3W00rxlu4Ffe4ovGWeUiV8FDztP0vh6LGc.wTs6', 1, '2020-09-12 11:08:56', '2020-09-12 11:08:56');
INSERT INTO `sys_user` VALUES (66, '2ee6a897-e2e5-', '$2a$10$DA2FHGN/KX0BWKo2hUqd9OXFdUYMzcvSXql5yshIEuefQdaQFlDIG', 1, '2020-09-12 11:08:56', '2020-09-12 11:08:56');
INSERT INTO `sys_user` VALUES (67, 'admin3', '$2a$10$YM/ze7rBPXJlvHBJiAffHuJJzhqYM6q3jj2CcGPIvF.W5tg8EoX4u', 1, '2020-09-12 11:18:58', '2020-09-12 11:18:58');
INSERT INTO `sys_user` VALUES (68, 'admin4', '$2a$10$V0luJNCj4ynRSOGv8r7bCOPiEaEzg0marEUhcpHSq0mtjzdFm8kEa', 1, '2020-09-12 11:20:04', '2020-09-12 11:20:04');

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
