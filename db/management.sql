-- MySQL dump 10.13  Distrib 8.0.29, for macos12 (arm64)
--
-- Host: localhost    Database: management
-- ------------------------------------------------------
-- Server version	8.0.29

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE IF NOT EXISTS `management` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

USE `management`;

--
-- Table structure for table `sys_job`
--

DROP TABLE IF EXISTS `sys_job`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
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
) ENGINE=InnoDB AUTO_INCREMENT=155 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_job`
--

LOCK TABLES `sys_job` WRITE;
/*!40000 ALTER TABLE `sys_job` DISABLE KEYS */;
INSERT INTO `sys_job` VALUES (147,'com.linck.management.quartz.job.Test2Job','Test2Job',NULL,'*/10 * * * * ?',0,NULL,'default','2021-11-15 22:37:34','2021-11-22 21:46:47',NULL,NULL,0x00,1),(150,'com.linck.management.quartz.job.TestJob','TestJob',NULL,'*/5 * * * * ?',0,NULL,'default','2021-11-22 21:24:37','2021-11-22 21:49:42',NULL,NULL,0x00,1),(151,'com.linck.management.quartz.job.Test3Job','Test3Job',NULL,'*/3 * * * * ?',0,NULL,'default','2021-11-22 21:55:18','2021-11-22 21:55:43',NULL,NULL,0x00,1),(152,'com.linck.management.quartz.job.RandomJob','RandomJob',NULL,'* */1 * * * ?',0,NULL,'default','2021-11-24 22:28:00','2021-11-24 22:28:00',NULL,NULL,0x00,1),(153,'com.linck.management.quartz.job.SuccessJob','SuccessJob',NULL,'* */1 * * * ?',0,NULL,'default','2021-11-24 22:28:00','2021-11-24 22:28:00',NULL,NULL,0x00,1),(154,'com.linck.management.quartz.job.ExceptionJob','ExceptionJob',NULL,'* */1 * * * ?',0,NULL,'default','2021-11-24 22:28:00','2021-11-24 22:28:00',NULL,NULL,0x00,1);
/*!40000 ALTER TABLE `sys_job` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_job_log`
--

DROP TABLE IF EXISTS `sys_job_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_job_log` (
                               `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
                               `job_id` bigint unsigned NOT NULL COMMENT 'JobId',
                               `msg` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '描述',
                               `result` tinyint NOT NULL DEFAULT '1' COMMENT '结果 1-成功 0-失败',
                               `spend_time` int NOT NULL COMMENT '运行花费时间',
                               `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                               PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=874 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_job_log`
--

LOCK TABLES `sys_job_log` WRITE;
/*!40000 ALTER TABLE `sys_job_log` DISABLE KEYS */;
INSERT INTO `sys_job_log` VALUES (849,153,'成功',1,1,'2023-03-31 10:47:28'),(850,154,'java.lang.ArithmeticException: / by zero',0,0,'2023-03-31 10:47:28'),(851,153,'成功',1,0,'2023-03-31 10:47:29'),(852,154,'java.lang.ArithmeticException: / by zero',0,0,'2023-03-31 10:47:29'),(853,154,'java.lang.ArithmeticException: / by zero',0,0,'2023-03-31 10:47:30'),(854,153,'成功',1,0,'2023-03-31 10:47:30'),(855,153,'成功',1,0,'2023-03-31 10:47:31'),(856,154,'java.lang.ArithmeticException: / by zero',0,0,'2023-03-31 10:47:31'),(857,152,'成功',1,2962,'2023-03-31 10:47:31'),(858,152,'成功',1,2355,'2023-03-31 10:47:31'),(859,152,'java.lang.NoSuchMethodException: com.linck.management.quartz.job.RandomJob.error()',0,1606,'2023-03-31 10:47:32'),(860,153,'成功',1,0,'2023-03-31 10:47:32'),(861,154,'java.lang.ArithmeticException: / by zero',0,0,'2023-03-31 10:47:32'),(862,154,'java.lang.ArithmeticException: / by zero',0,0,'2023-03-31 10:47:33'),(863,153,'成功',1,0,'2023-03-31 10:47:33'),(864,152,'成功',1,2262,'2023-03-31 10:47:33'),(865,153,'成功',1,0,'2023-03-31 10:47:34'),(866,154,'java.lang.ArithmeticException: / by zero',0,0,'2023-03-31 10:47:34'),(867,153,'成功',1,0,'2023-03-31 10:47:35'),(868,154,'java.lang.ArithmeticException: / by zero',0,0,'2023-03-31 10:47:35'),(869,154,'java.lang.ArithmeticException: / by zero',0,0,'2023-03-31 10:47:36'),(870,153,'成功',1,0,'2023-03-31 10:47:36'),(871,152,'成功',1,3630,'2023-03-31 10:47:37'),(872,152,'java.lang.NoSuchMethodException: com.linck.management.quartz.job.RandomJob.error()',0,1785,'2023-03-31 10:47:37'),(873,152,'成功',1,4788,'2023-03-31 10:47:37');
/*!40000 ALTER TABLE `sys_job_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_permission`
--

DROP TABLE IF EXISTS `sys_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_permission` (
                                  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
                                  `pid` bigint unsigned NOT NULL DEFAULT '0' COMMENT '父级权限id',
                                  `name` char(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '名称',
                                  `value` char(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '内容',
                                  `type` tinyint(1) NOT NULL DEFAULT '1' COMMENT '类型 1-菜单 2-按钮 3-权限',
                                  `icon` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '图标',
                                  `url` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '前端资源路径',
                                  `status` tinyint NOT NULL DEFAULT '0' COMMENT '启用状态(1-启用 0-禁用)',
                                  `sort` int NOT NULL DEFAULT '0' COMMENT '排序',
                                  `create_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                  `update_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
                                  `create_by` bigint unsigned DEFAULT NULL,
                                  `update_by` bigint unsigned DEFAULT NULL,
                                  `del_flag` bit(1) NOT NULL DEFAULT b'0',
                                  `version` bigint unsigned NOT NULL DEFAULT '1',
                                  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_permission`
--

LOCK TABLES `sys_permission` WRITE;
/*!40000 ALTER TABLE `sys_permission` DISABLE KEYS */;
INSERT INTO `sys_permission` VALUES (1,0,'测试菜单',NULL,1,'el-icon-folder',NULL,1,0,'2020-08-09 23:49:49','2021-11-12 10:01:52',NULL,NULL,0x00,1),(2,1,'测试列表',NULL,2,NULL,'/first',1,0,'2020-08-09 23:49:55','2020-11-15 20:10:36',NULL,NULL,0x00,1),(7,0,'系统管理',NULL,1,'el-icon-setting',NULL,1,11,'2020-10-22 22:47:17','2020-10-28 23:30:55',NULL,NULL,0x00,1),(8,7,'角色管理',NULL,2,NULL,'/sysrole',1,1,'2020-10-22 22:47:46','2020-11-15 20:03:59',NULL,NULL,0x00,1),(9,7,'权限管理',NULL,2,NULL,'/syspermission',1,2,'2020-10-22 22:48:09','2020-11-15 20:04:05',NULL,NULL,0x00,1),(10,8,'新增角色','role:add',3,NULL,NULL,1,0,'2020-11-11 22:56:27','2020-11-15 19:56:40',NULL,NULL,0x00,1),(11,9,'新增权限','permission:add',3,NULL,NULL,1,0,'2020-11-11 22:57:01','2020-11-15 19:57:52',NULL,NULL,0x00,1),(12,7,'用户管理',NULL,2,NULL,'/sysuser',1,0,'2020-11-15 20:03:50','2020-11-15 20:03:50',NULL,NULL,0x00,1),(14,12,'查看用户','user:view',3,NULL,NULL,1,0,'2020-11-15 20:05:56','2020-11-15 20:05:56',NULL,NULL,0x00,1),(15,12,'新增用户','user:add',3,NULL,NULL,1,0,'2020-11-15 20:06:09','2020-11-15 20:06:52',NULL,NULL,0x00,1),(16,12,'修改用户','user:update',3,NULL,NULL,1,0,'2020-11-15 20:06:43','2020-11-15 20:06:42',NULL,NULL,0x00,1),(17,12,'删除用户','user:remove',3,NULL,NULL,1,0,'2020-11-15 20:07:45','2020-11-15 20:07:44',NULL,NULL,0x00,1),(18,12,'查看角色','role:view',8,NULL,NULL,1,0,'2020-11-15 20:09:28','2020-11-15 20:09:27',NULL,NULL,0x00,1),(19,8,'查看角色','role:view',3,NULL,NULL,1,0,'2020-11-15 20:11:08','2020-11-15 20:11:07',NULL,NULL,0x00,1),(20,8,'修改角色','role:update',3,NULL,NULL,1,0,'2020-11-15 20:29:40','2020-11-15 20:29:39',NULL,NULL,0x00,1),(21,8,'删除角色','role:remove',3,NULL,NULL,1,0,'2020-11-15 20:30:20','2020-11-15 20:30:19',NULL,NULL,0x00,1),(22,9,'查看权限','permission:view',3,NULL,NULL,1,0,'2020-11-15 20:30:59','2020-11-15 20:30:58',NULL,NULL,0x00,1),(23,9,'修改权限','permission:update',3,NULL,NULL,1,0,'2020-11-15 20:31:22','2020-11-15 20:31:21',NULL,NULL,0x00,1),(24,9,'删除权限','permission:remove',3,NULL,NULL,1,0,'2020-11-15 20:31:53','2020-11-15 20:31:52',NULL,NULL,0x00,1),(26,7,'Job管理',NULL,2,NULL,'/sysjob',1,0,'2020-12-11 16:57:28','2021-11-11 22:32:32',NULL,NULL,0x00,1),(27,26,'查看Job','job:view',3,NULL,NULL,1,0,'2020-12-11 17:01:45','2021-11-11 22:32:22',NULL,NULL,0x00,1),(28,26,'修改Job','job:update',3,NULL,NULL,1,3,'2020-12-11 17:02:11','2021-11-11 22:33:10',NULL,NULL,0x00,1),(29,26,'新增Job','job:add',3,NULL,NULL,1,2,'2021-11-11 22:31:44','2021-11-11 22:33:02',NULL,NULL,0x00,1),(30,26,'删除Job','job:remove',3,NULL,NULL,1,4,'2021-11-11 22:32:06','2021-11-11 22:32:05',NULL,NULL,0x00,1);
/*!40000 ALTER TABLE `sys_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role`
--

DROP TABLE IF EXISTS `sys_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_role` (
                            `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
                            `value` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '编码值',
                            `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '中文名称',
                            `description` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '描述',
                            `status` tinyint NOT NULL DEFAULT '0' COMMENT '启用状态(1-启用 0-禁用)',
                            `create_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                            `update_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
                            `create_by` bigint unsigned DEFAULT NULL,
                            `update_by` bigint unsigned DEFAULT NULL,
                            `del_flag` bit(1) NOT NULL DEFAULT b'0',
                            `version` bigint unsigned NOT NULL DEFAULT '1',
                            PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='系统角色';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role`
--

LOCK TABLES `sys_role` WRITE;
/*!40000 ALTER TABLE `sys_role` DISABLE KEYS */;
INSERT INTO `sys_role` VALUES (1,'ROLE_ADMIN','系统管理员','系统管理员',1,'2020-08-09 23:40:11','2020-08-09 23:40:11',NULL,NULL,0x00,1),(6,'ROLE_USER','用户','用户',1,'2020-11-01 21:20:37','2020-11-01 21:20:37',NULL,NULL,0x00,1),(7,'USER_GUESS','游客','访问游客',1,'2021-11-11 22:33:47','2021-11-11 22:33:47',NULL,NULL,0x00,1),(8,'test1','test1',NULL,0,'2023-04-11 15:34:51','2023-04-11 15:34:51',1,1,0x00,1),(9,'test23','test2',NULL,1,'2023-04-11 15:42:10','2023-04-11 15:42:42',NULL,1,0x00,2);
/*!40000 ALTER TABLE `sys_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role_permission`
--

DROP TABLE IF EXISTS `sys_role_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_role_permission` (
                                       `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
                                       `r_id` bigint unsigned NOT NULL COMMENT '角色id',
                                       `p_id` bigint unsigned NOT NULL COMMENT '权限id',
                                       PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=120 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role_permission`
--

LOCK TABLES `sys_role_permission` WRITE;
/*!40000 ALTER TABLE `sys_role_permission` DISABLE KEYS */;
INSERT INTO `sys_role_permission` VALUES (7,1,7),(8,1,8),(9,1,9),(84,1,10),(86,1,1),(87,1,2),(88,1,3),(89,1,4),(90,1,5),(91,1,6),(92,1,12),(93,1,14),(94,1,15),(95,1,16),(96,1,17),(97,1,19),(98,1,21),(99,1,20),(100,1,22),(103,1,11),(104,1,23),(105,1,24),(106,1,26),(107,1,27),(108,1,28),(109,7,2),(110,7,1),(111,7,7),(112,7,12),(113,7,14),(114,7,26),(115,7,27),(116,7,8),(117,7,9),(118,7,22),(119,7,19);
/*!40000 ALTER TABLE `sys_role_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user`
--

DROP TABLE IF EXISTS `sys_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_user` (
                            `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
                            `account` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '账号',
                            `pwd` char(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '密码',
                            `status` tinyint NOT NULL DEFAULT '1' COMMENT '启用状态(1-启用 0-禁用)',
                            `create_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                            `update_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
                            `create_by` bigint unsigned DEFAULT NULL,
                            `update_by` bigint unsigned DEFAULT NULL,
                            `del_flag` bit(1) NOT NULL DEFAULT b'0',
                            `version` bigint unsigned NOT NULL DEFAULT '1',
                            PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=72 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user`
--

LOCK TABLES `sys_user` WRITE;
/*!40000 ALTER TABLE `sys_user` DISABLE KEYS */;
INSERT INTO `sys_user` VALUES (1,'admin','$2a$10$zxjPkL7gpB.NV2Jyq6bDZ./4FoNJNBo9h1ITgJZYT91umTc.ttJDe',1,'2020-08-10 17:15:48','2020-08-10 17:15:48',NULL,NULL,0x00,1),(69,'test','123456',1,'2020-11-22 13:43:44','2020-11-22 13:43:44',NULL,NULL,0x00,1),(70,'test11','test11',1,'2020-11-22 13:45:05','2020-11-22 13:45:05',NULL,NULL,0x00,1),(71,'test22','test22',0,'2020-11-22 13:51:32','2020-11-22 13:51:32',NULL,NULL,0x00,1);
/*!40000 ALTER TABLE `sys_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user_role`
--

DROP TABLE IF EXISTS `sys_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_user_role` (
                                 `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
                                 `u_id` bigint unsigned NOT NULL COMMENT '用户id',
                                 `r_id` bigint unsigned NOT NULL COMMENT '角色id',
                                 PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user_role`
--

LOCK TABLES `sys_user_role` WRITE;
/*!40000 ALTER TABLE `sys_user_role` DISABLE KEYS */;
INSERT INTO `sys_user_role` VALUES (1,1,1),(2,1,6),(6,69,7);
/*!40000 ALTER TABLE `sys_user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'management'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-04-11 18:05:51
