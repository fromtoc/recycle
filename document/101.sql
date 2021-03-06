/*
SQLyog Ultimate v11.42 (64 bit)
MySQL - 5.7.28 : Database - recycle
*********************************************************************
*/
-- CREATE DATABASE /*!32312 IF NOT EXISTS*/`recycle` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `101`;

/*Table structure for table `biz_consumer` */

-- DROP TABLE IF EXISTS `biz_consumer`;
--
-- CREATE TABLE `biz_consumer` (
--   `id` bigint(20) NOT NULL AUTO_INCREMENT,
--   `name` varchar(20) DEFAULT NULL COMMENT '物資消费方',
--   `address` varchar(20) DEFAULT NULL COMMENT '地址',
--   `create_time` datetime DEFAULT NULL,
--   `modified_time` datetime DEFAULT NULL,
--   `phone` varchar(20) DEFAULT NULL COMMENT '聯繫电话',
--   `sort` int(11) DEFAULT NULL,
--   `contact` varchar(10) DEFAULT NULL COMMENT '聯繫人姓名',
--   PRIMARY KEY (`id`)
-- ) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4;

/*Data for the table `biz_consumer` */

-- insert  into `biz_consumer`(`id`,`name`,`address`,`create_time`,`modified_time`,`phone`,`sort`,`contact`) values (7,'324234234','天津市/市辖区/和平区','2020-04-25 20:12:06','2020-05-10 09:21:37','15079437282',1,'老王'),(8,'43535345','江西省/九江市/共青城市','2020-04-25 20:12:43','2020-04-25 20:12:43','15079437282',2,'11'),(9,'3424243424','山西省/大同市/矿区','2020-04-25 20:13:20','2020-05-10 09:21:33','15079437282',1,'小李'),(11,'3424','江西省/抚州市/黎川县','2020-04-25 20:28:26','2020-05-10 09:21:47','15079437282',1,'44'),(13,'32424','天津市/市辖区/和平区','2020-04-25 21:38:42','2020-04-25 21:38:42','15079437282',2,'22'),(14,'34242','江西省/抚州市/黎川县','2020-04-25 21:38:59','2020-04-25 21:38:59','15079437282',3,'444'),(15,'5他','河北省/石家庄市/长安区','2020-04-25 21:39:25','2020-04-25 21:39:25','15079437282',2,'555'),(16,'3243242','山东省/青岛市/历下区','2020-04-25 21:40:43','2020-04-25 21:40:43','15079437282',1,'555'),(17,'一栋705宿舍','江西省/九江市/共青城市','2020-04-25 21:56:04','2020-04-25 21:56:04','15079437282',3,'章宇康'),(18,'反反复复','内蒙古自治区/赤峰市/阿鲁科尔沁旗','2020-04-25 22:05:47','2020-04-25 22:05:47','15079437282',2,'反反复复'),(19,'test224444','江西省/九江市/共青城市','2020-04-26 09:47:37','2020-04-26 09:47:50','15079437282',3,'test'),(20,'324234','天津市/市辖区/和平区','2020-05-25 11:00:23','2020-05-25 11:00:23','15079437282',1,'32424'),(21,'test111','福建省/莆田市/秀屿区','2020-05-25 11:01:18','2020-05-25 11:01:55','15079437282',2,'testman'),(22,'6666666','天津市/市辖区/和平区','2020-05-25 11:16:12','2020-05-25 11:16:12','15079437282',1,'23423424'),(23,'6666666','天津市/市辖区/和平区','2020-05-25 11:17:15','2020-05-25 11:17:15','15079437282',1,'23423424'),(24,'hemei','河北省/唐山市/古冶区','2020-05-25 11:27:42','2020-05-25 11:27:42','15079437282',1,'hemei'),(25,'hemei','河北省/唐山市/古冶区','2020-05-25 11:32:39','2020-05-25 11:32:39','15079437282',1,'hemei'),(26,'武汉汉口校医院','天津市/市辖区/和平区','2020-05-25 15:46:07','2020-05-25 15:46:07','15079437282',1,'李大牛');

/*Table structure for table `biz_health` */

-- DROP TABLE IF EXISTS `biz_health`;
--
-- CREATE TABLE `biz_health` (
--   `id` bigint(20) NOT NULL AUTO_INCREMENT,
--   `address` varchar(50) NOT NULL,
--   `user_id` bigint(20) NOT NULL,
--   `situation` int(1) NOT NULL,
--   `touch` int(1) NOT NULL,
--   `passby` int(1) NOT NULL,
--   `reception` int(1) NOT NULL,
--   `create_time` datetime NOT NULL,
--   PRIMARY KEY (`id`)
-- ) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4;

/*Data for the table `biz_health` */

-- insert  into `biz_health`(`id`,`address`,`user_id`,`situation`,`touch`,`passby`,`reception`,`create_time`) values (24,'湖北省/武汉市/汉南区',5,0,0,0,0,'2020-05-07 15:23:40'),(25,'河北省/秦皇岛市/北戴河区',5,0,1,1,1,'2020-05-10 11:26:27'),(28,'天津市/市辖区/和平区',5,0,1,1,1,'2020-05-13 22:43:09'),(29,'天津市/市辖区/南開区',5,0,1,1,1,'2020-05-14 20:10:12'),(30,'天津市/市辖区/和平区',5,1,0,0,0,'2020-05-14 20:23:07'),(31,'天津市/市辖区/和平区',184,2,1,1,1,'2020-05-14 21:06:47'),(35,'天津市/市辖区/和平区',5,0,1,1,1,'2020-05-18 09:33:33');

/*Table structure for table `biz_in_stock` */

-- DROP TABLE IF EXISTS `biz_in_stock`;
--
-- CREATE TABLE `biz_in_stock` (
--   `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
--   `in_num` varchar(36) DEFAULT NULL COMMENT '入库单编号',
--   `type` int(2) DEFAULT NULL COMMENT '類型：1：捐赠，2：下拨，3：采购,4:退货入库',
--   `operator` varchar(20) DEFAULT NULL COMMENT '操作人員',
--   `create_time` datetime DEFAULT NULL COMMENT '入库单創建時間',
--   `modified` datetime DEFAULT NULL COMMENT '入库单修改時間',
--   `product_number` int(11) DEFAULT NULL COMMENT '物資总数',
--   `supplier_id` bigint(20) DEFAULT NULL COMMENT '来源',
--   `remark` varchar(100) DEFAULT NULL COMMENT '描述信息',
--   `status` int(1) DEFAULT '2' COMMENT '0:正常入库单,1:已进入回收,2:等待审核',
--   PRIMARY KEY (`id`),
--   KEY `operator_id` (`operator`),
--   KEY `supplier_id` (`supplier_id`)
-- ) ENGINE=InnoDB AUTO_INCREMENT=111 DEFAULT CHARSET=utf8;

/*Data for the table `biz_in_stock` */

-- insert  into `biz_in_stock`(`id`,`in_num`,`type`,`operator`,`create_time`,`modified`,`product_number`,`supplier_id`,`remark`,`status`) values (84,'a2ea9b97-ad12-4d85-a9b4-1a644d86',2,'zhangyukang','2020-05-09 20:01:48','2020-05-09 20:01:48',13,15,'333333',0),(85,'78473f1a-405a-4f5e-b08c-42a7b2e8',2,'zhangyukang','2020-05-10 09:27:45','2020-05-10 09:27:45',48,14,'324324242424',0),(86,'31ff3701-c44f-4b98-91e0-1d41d8b2',1,'zhangyukang','2020-05-11 09:59:30','2020-05-11 09:59:30',51,15,'32424',0),(87,'38ed0881-20ed-4cec-a321-eb8f4c3b',1,'zhangyukang','2020-05-11 10:04:05','2020-05-11 10:04:05',30,15,'232323232',0),(90,'db1c2afe-8117-43fd-adf7-866ef7e4',1,'系统测试人員','2020-05-18 10:11:11','2020-05-16 17:37:12',4,15,'2222222',0),(91,'df793f2b-ea13-4d93-b22c-60454f32',2,'系统测试人員','2020-05-18 10:11:09','2020-05-18 10:11:00',25,15,'2342424',0),(92,'c2054c39-a88b-4f47-9f9e-5c57f9e6',1,'系统测试人員','2020-05-18 10:42:04','2020-05-18 10:41:55',3,15,'222222',0),(93,'08a51486-49e9-402e-a10e-3e6a45df',1,'系统测试人員','2020-05-18 11:01:16','2020-05-18 11:01:09',4,15,'2222222',0),(99,'bb5de246-bd56-4987-b027-8fbcf3c3',2,'系统测试人員','2020-05-18 12:21:41','2020-05-18 11:43:49',6,19,'33333',0),(100,'7657f747-ab27-49d7-b1ce-d6d47ecf',1,'系统测试人員','2020-05-18 12:21:40','2020-05-18 12:21:29',2,20,'23432424',1),(101,'03fbc3b3-e28b-418c-a457-87c376c3',1,'系统测试人員','2020-05-18 13:16:38','2020-05-18 13:16:28',12,21,'454545454545',0),(102,'d83621b8-b5c7-4499-a8a0-56af2849',1,'系统测试人員','2020-05-18 13:18:51','2020-05-18 13:18:41',18,17,'33333',0),(103,'51fa7a04-535f-43b5-8972-23d0e55a',1,'系统测试人員','2020-05-18 13:42:41','2020-05-18 13:42:29',6,22,'222222',1),(104,'c96eaa3e-22ee-4f6b-98bd-87d34372',1,'zhangyukang','2020-05-24 21:46:16','2020-05-24 21:46:06',6,15,'432424',1),(105,'5ad278ed-ce30-4f0d-bb67-7f9070fc',1,'zhangyukang','2020-05-25 11:45:10','2020-05-25 10:27:06',6,15,'32432424',0),(106,'efeef1d2-b8c9-4eb5-8ea2-b0695fb9',1,'zhangyukang','2020-05-26 09:35:23','2020-05-26 09:35:17',10,17,'2342424',0),(107,'4d7d332469eb4f2987cbc38fea2b',1,'admin','2020-12-17 19:27:30','2020-12-17 19:26:39',1,14,'2313123',0),(108,'69edf117b8dd478abc37d33bca41',2,'admin','2020-12-17 19:30:44','2020-12-17 19:28:52',6,15,'231313',0),(109,'5e69ad91b41a439b8b4a809e84b4',4,'admin','2020-12-17 19:30:42','2020-12-17 19:30:28',9,14,'232323',0),(110,'532f9951e1d54f4f98263002bea6',1,'admin','2020-12-17 21:00:57','2020-12-17 21:00:50',2,15,'33333',0);

/*Table structure for table `biz_in_stock_info` */

-- DROP TABLE IF EXISTS `biz_in_stock_info`;
--
-- CREATE TABLE `biz_in_stock_info` (
--   `id` bigint(20) NOT NULL AUTO_INCREMENT,
--   `in_num` varchar(36) DEFAULT NULL COMMENT '入库单编号',
--   `p_num` varchar(36) DEFAULT NULL COMMENT '商品编号',
--   `product_number` int(11) DEFAULT NULL COMMENT '数量',
--   `create_time` datetime DEFAULT NULL,
--   `modified_time` datetime DEFAULT NULL,
--   PRIMARY KEY (`id`)
-- ) ENGINE=InnoDB AUTO_INCREMENT=375 DEFAULT CHARSET=utf8;

/*Data for the table `biz_in_stock_info` */

-- insert  into `biz_in_stock_info`(`id`,`in_num`,`p_num`,`product_number`,`create_time`,`modified_time`) values (282,'a2ea9b97-ad12-4d85-a9b4-1a644d86','3DFC8EA0-6',2,'2020-05-09 20:01:49','2020-05-09 20:01:49'),(283,'a2ea9b97-ad12-4d85-a9b4-1a644d86','2C15F1B6-1',2,'2020-05-09 20:01:49','2020-05-09 20:01:49'),(284,'a2ea9b97-ad12-4d85-a9b4-1a644d86','6976D3B4-A',3,'2020-05-09 20:01:49','2020-05-09 20:01:49'),(285,'a2ea9b97-ad12-4d85-a9b4-1a644d86','f248ee7a-c30c-447c-ae9c-0a1c09e9',3,'2020-05-09 20:01:49','2020-05-09 20:01:49'),(286,'a2ea9b97-ad12-4d85-a9b4-1a644d86','beb944c4-01ae-497b-bfdd-2132032f',3,'2020-05-09 20:01:49','2020-05-09 20:01:49'),(287,'78473f1a-405a-4f5e-b08c-42a7b2e8','3DFC8EA0-6',2,'2020-05-10 09:27:45','2020-05-10 09:27:45'),(288,'78473f1a-405a-4f5e-b08c-42a7b2e8','2C15F1B6-1',1,'2020-05-10 09:27:45','2020-05-10 09:27:45'),(289,'78473f1a-405a-4f5e-b08c-42a7b2e8','6976D3B4-A',2,'2020-05-10 09:27:45','2020-05-10 09:27:45'),(290,'78473f1a-405a-4f5e-b08c-42a7b2e8','f248ee7a-c30c-447c-ae9c-0a1c09e9',2,'2020-05-10 09:27:45','2020-05-10 09:27:45'),(291,'78473f1a-405a-4f5e-b08c-42a7b2e8','beb944c4-01ae-497b-bfdd-2132032f',3,'2020-05-10 09:27:45','2020-05-10 09:27:45'),(292,'78473f1a-405a-4f5e-b08c-42a7b2e8','24573d5b-0c9b-403b-9a88-c214702e',3,'2020-05-10 09:27:45','2020-05-10 09:27:45'),(293,'78473f1a-405a-4f5e-b08c-42a7b2e8','c98183c8-bc47-4505-abbb-1dc219b5',2,'2020-05-10 09:27:45','2020-05-10 09:27:45'),(294,'78473f1a-405a-4f5e-b08c-42a7b2e8','de16b9e6-bb49-4547-ab91-db7ae7b6',3,'2020-05-10 09:27:45','2020-05-10 09:27:45'),(295,'78473f1a-405a-4f5e-b08c-42a7b2e8','6AF405A1-C',10,'2020-05-10 09:27:45','2020-05-10 09:27:45'),(296,'78473f1a-405a-4f5e-b08c-42a7b2e8','0b9e9176-f996-4384-bb6c-209f55d0',10,'2020-05-10 09:27:45','2020-05-10 09:27:45'),(297,'78473f1a-405a-4f5e-b08c-42a7b2e8','894b8218-36ee-4a0d-9ad1-d9c5e455',10,'2020-05-10 09:27:45','2020-05-10 09:27:45'),(298,'31ff3701-c44f-4b98-91e0-1d41d8b2','3DFC8EA0-6',10,'2020-05-11 09:59:31','2020-05-11 09:59:31'),(299,'31ff3701-c44f-4b98-91e0-1d41d8b2','2C15F1B6-1',10,'2020-05-11 09:59:32','2020-05-11 09:59:32'),(300,'31ff3701-c44f-4b98-91e0-1d41d8b2','15bc064e-991d-40e3-bcd6-f6aff0e3',10,'2020-05-11 09:59:32','2020-05-11 09:59:32'),(301,'31ff3701-c44f-4b98-91e0-1d41d8b2','f248ee7a-c30c-447c-ae9c-0a1c09e9',10,'2020-05-11 09:59:32','2020-05-11 09:59:32'),(302,'31ff3701-c44f-4b98-91e0-1d41d8b2','beb944c4-01ae-497b-bfdd-2132032f',10,'2020-05-11 09:59:32','2020-05-11 09:59:32'),(303,'31ff3701-c44f-4b98-91e0-1d41d8b2','0e17f606-9aaa-48a7-b4e9-ef18462e',1,'2020-05-11 09:59:32','2020-05-11 09:59:32'),(304,'38ed0881-20ed-4cec-a321-eb8f4c3b','3DFC8EA0-6',10,'2020-05-11 10:04:05','2020-05-11 10:04:05'),(305,'38ed0881-20ed-4cec-a321-eb8f4c3b','24573d5b-0c9b-403b-9a88-c214702e',10,'2020-05-11 10:04:06','2020-05-11 10:04:06'),(306,'38ed0881-20ed-4cec-a321-eb8f4c3b','6EF5F2C0-9',10,'2020-05-11 10:04:06','2020-05-11 10:04:06'),(315,'db1c2afe-8117-43fd-adf7-866ef7e4','3DFC8EA0-6',2,'2020-05-16 17:37:12','2020-05-16 17:37:12'),(316,'db1c2afe-8117-43fd-adf7-866ef7e4','2C15F1B6-1',2,'2020-05-16 17:37:13','2020-05-16 17:37:13'),(317,'df793f2b-ea13-4d93-b22c-60454f32','3DFC8EA0-6',3,'2020-05-18 10:11:00','2020-05-18 10:11:00'),(318,'df793f2b-ea13-4d93-b22c-60454f32','2C15F1B6-1',3,'2020-05-18 10:11:00','2020-05-18 10:11:00'),(319,'df793f2b-ea13-4d93-b22c-60454f32','15bc064e-991d-40e3-bcd6-f6aff0e3',3,'2020-05-18 10:11:00','2020-05-18 10:11:00'),(320,'df793f2b-ea13-4d93-b22c-60454f32','6976D3B4-A',3,'2020-05-18 10:11:00','2020-05-18 10:11:00'),(321,'df793f2b-ea13-4d93-b22c-60454f32','f248ee7a-c30c-447c-ae9c-0a1c09e9',10,'2020-05-18 10:11:00','2020-05-18 10:11:00'),(322,'df793f2b-ea13-4d93-b22c-60454f32','beb944c4-01ae-497b-bfdd-2132032f',3,'2020-05-18 10:11:00','2020-05-18 10:11:00'),(323,'c2054c39-a88b-4f47-9f9e-5c57f9e6','3DFC8EA0-6',3,'2020-05-18 10:41:55','2020-05-18 10:41:55'),(324,'08a51486-49e9-402e-a10e-3e6a45df','3DFC8EA0-6',2,'2020-05-18 11:01:09','2020-05-18 11:01:09'),(325,'08a51486-49e9-402e-a10e-3e6a45df','2C15F1B6-1',2,'2020-05-18 11:01:09','2020-05-18 11:01:09'),(335,'bb5de246-bd56-4987-b027-8fbcf3c3','2C15F1B6-1',3,'2020-05-18 11:43:50','2020-05-18 11:43:50'),(336,'bb5de246-bd56-4987-b027-8fbcf3c3','15bc064e-991d-40e3-bcd6-f6aff0e3',3,'2020-05-18 11:43:50','2020-05-18 11:43:50'),(337,'7657f747-ab27-49d7-b1ce-d6d47ecf','3DFC8EA0-6',1,'2020-05-18 12:21:29','2020-05-18 12:21:29'),(338,'7657f747-ab27-49d7-b1ce-d6d47ecf','2C15F1B6-1',1,'2020-05-18 12:21:29','2020-05-18 12:21:29'),(339,'03fbc3b3-e28b-418c-a457-87c376c3','3DFC8EA0-6',4,'2020-05-18 13:16:28','2020-05-18 13:16:28'),(340,'03fbc3b3-e28b-418c-a457-87c376c3','2C15F1B6-1',4,'2020-05-18 13:16:28','2020-05-18 13:16:28'),(341,'03fbc3b3-e28b-418c-a457-87c376c3','15bc064e-991d-40e3-bcd6-f6aff0e3',4,'2020-05-18 13:16:28','2020-05-18 13:16:28'),(342,'d83621b8-b5c7-4499-a8a0-56af2849','3DFC8EA0-6',3,'2020-05-18 13:18:41','2020-05-18 13:18:41'),(343,'d83621b8-b5c7-4499-a8a0-56af2849','2C15F1B6-1',3,'2020-05-18 13:18:42','2020-05-18 13:18:42'),(344,'d83621b8-b5c7-4499-a8a0-56af2849','15bc064e-991d-40e3-bcd6-f6aff0e3',3,'2020-05-18 13:18:42','2020-05-18 13:18:42'),(345,'d83621b8-b5c7-4499-a8a0-56af2849','6976D3B4-A',3,'2020-05-18 13:18:42','2020-05-18 13:18:42'),(346,'d83621b8-b5c7-4499-a8a0-56af2849','f248ee7a-c30c-447c-ae9c-0a1c09e9',3,'2020-05-18 13:18:42','2020-05-18 13:18:42'),(347,'d83621b8-b5c7-4499-a8a0-56af2849','beb944c4-01ae-497b-bfdd-2132032f',3,'2020-05-18 13:18:42','2020-05-18 13:18:42'),(348,'51fa7a04-535f-43b5-8972-23d0e55a','3DFC8EA0-6',2,'2020-05-18 13:42:29','2020-05-18 13:42:29'),(349,'51fa7a04-535f-43b5-8972-23d0e55a','2C15F1B6-1',2,'2020-05-18 13:42:29','2020-05-18 13:42:29'),(350,'51fa7a04-535f-43b5-8972-23d0e55a','15bc064e-991d-40e3-bcd6-f6aff0e3',2,'2020-05-18 13:42:29','2020-05-18 13:42:29'),(351,'c96eaa3e-22ee-4f6b-98bd-87d34372','3DFC8EA0-6',3,'2020-05-24 21:46:06','2020-05-24 21:46:06'),(352,'c96eaa3e-22ee-4f6b-98bd-87d34372','2C15F1B6-1',3,'2020-05-24 21:46:06','2020-05-24 21:46:06'),(353,'5ad278ed-ce30-4f0d-bb67-7f9070fc','3DFC8EA0-6',1,'2020-05-25 10:27:07','2020-05-25 10:27:07'),(354,'5ad278ed-ce30-4f0d-bb67-7f9070fc','2C15F1B6-1',1,'2020-05-25 10:27:07','2020-05-25 10:27:07'),(355,'5ad278ed-ce30-4f0d-bb67-7f9070fc','15bc064e-991d-40e3-bcd6-f6aff0e3',1,'2020-05-25 10:27:07','2020-05-25 10:27:07'),(356,'5ad278ed-ce30-4f0d-bb67-7f9070fc','6976D3B4-A',1,'2020-05-25 10:27:07','2020-05-25 10:27:07'),(357,'5ad278ed-ce30-4f0d-bb67-7f9070fc','f248ee7a-c30c-447c-ae9c-0a1c09e9',1,'2020-05-25 10:27:07','2020-05-25 10:27:07'),(358,'5ad278ed-ce30-4f0d-bb67-7f9070fc','beb944c4-01ae-497b-bfdd-2132032f',1,'2020-05-25 10:27:07','2020-05-25 10:27:07'),(359,'efeef1d2-b8c9-4eb5-8ea2-b0695fb9','6EF5F2C0-9',10,'2020-05-26 09:35:17','2020-05-26 09:35:17'),(360,'4d7d332469eb4f2987cbc38fea2b','3DFC8EA0-6',1,'2020-12-17 19:26:39','2020-12-17 19:26:39'),(361,'69edf117b8dd478abc37d33bca41','3DFC8EA0-6',1,'2020-12-17 19:28:52','2020-12-17 19:28:52'),(362,'69edf117b8dd478abc37d33bca41','2C15F1B6-1',1,'2020-12-17 19:28:52','2020-12-17 19:28:52'),(363,'69edf117b8dd478abc37d33bca41','15bc064e-991d-40e3-bcd6-f6aff0e3',1,'2020-12-17 19:28:52','2020-12-17 19:28:52'),(364,'69edf117b8dd478abc37d33bca41','6976D3B4-A',1,'2020-12-17 19:28:52','2020-12-17 19:28:52'),(365,'69edf117b8dd478abc37d33bca41','f248ee7a-c30c-447c-ae9c-0a1c09e9',1,'2020-12-17 19:28:52','2020-12-17 19:28:52'),(366,'69edf117b8dd478abc37d33bca41','beb944c4-01ae-497b-bfdd-2132032f',1,'2020-12-17 19:28:52','2020-12-17 19:28:52'),(367,'5e69ad91b41a439b8b4a809e84b4','3DFC8EA0-6',1,'2020-12-17 19:30:28','2020-12-17 19:30:28'),(368,'5e69ad91b41a439b8b4a809e84b4','2C15F1B6-1',1,'2020-12-17 19:30:28','2020-12-17 19:30:28'),(369,'5e69ad91b41a439b8b4a809e84b4','15bc064e-991d-40e3-bcd6-f6aff0e3',1,'2020-12-17 19:30:28','2020-12-17 19:30:28'),(370,'5e69ad91b41a439b8b4a809e84b4','6976D3B4-A',2,'2020-12-17 19:30:28','2020-12-17 19:30:28'),(371,'5e69ad91b41a439b8b4a809e84b4','f248ee7a-c30c-447c-ae9c-0a1c09e9',2,'2020-12-17 19:30:28','2020-12-17 19:30:28'),(372,'5e69ad91b41a439b8b4a809e84b4','beb944c4-01ae-497b-bfdd-2132032f',2,'2020-12-17 19:30:28','2020-12-17 19:30:28'),(373,'532f9951e1d54f4f98263002bea6','2C15F1B6-1',1,'2020-12-17 21:00:50','2020-12-17 21:00:50'),(374,'532f9951e1d54f4f98263002bea6','15bc064e-991d-40e3-bcd6-f6aff0e3',1,'2020-12-17 21:00:50','2020-12-17 21:00:50');

/*Table structure for table `biz_out_stock` */

-- DROP TABLE IF EXISTS `biz_out_stock`;
--
-- CREATE TABLE `biz_out_stock` (
--   `id` bigint(20) NOT NULL AUTO_INCREMENT,
--   `out_num` varchar(36) NOT NULL COMMENT '出库单',
--   `type` int(1) NOT NULL COMMENT '出库類型:0:直接出库,1:审核出库',
--   `operator` varchar(20) DEFAULT NULL COMMENT '操作人',
--   `create_time` datetime DEFAULT NULL COMMENT '出库时间',
--   `product_number` int(11) DEFAULT NULL COMMENT '出库总数',
--   `consumer_id` bigint(20) NOT NULL COMMENT '消费者id',
--   `remark` varchar(50) DEFAULT NULL COMMENT '备注',
--   `status` int(1) DEFAULT NULL COMMENT '狀態:0:正常入库,1:已进入回收,2:等待审核',
--   `priority` int(1) NOT NULL COMMENT '紧急程度:1:不急,2:常规,3:紧急4:特急',
--   PRIMARY KEY (`id`)
-- ) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4;

/*Data for the table `biz_out_stock` */

-- insert  into `biz_out_stock`(`id`,`out_num`,`type`,`operator`,`create_time`,`product_number`,`consumer_id`,`remark`,`status`,`priority`) values (1,'fdasfsfsaf',1,'1','2020-05-10 14:42:04',111,7,'1',0,1),(2,'11fdsafsafasf',0,'324','2020-05-10 15:16:03',3,8,'3',0,4),(4,'8d468e4b-0c45-4807-9e0e-6c2c65d6',0,'zhangyukang','2020-05-26 09:38:02',7,25,'2342424',0,1),(5,'2d7158d3-2b15-473e-8cbe-6f6bd45a',0,'zhangyukang','2020-05-26 09:37:49',10,24,'4545454545',0,4),(6,'bd5032d0-c84a-4413-a72d-ba80d33f',0,'zhangyukang','2020-05-25 12:08:07',93,9,'23424234324',2,1),(7,'c23e95d0-0607-4e00-9041-cc97d299',0,'zhangyukang','2020-05-25 15:46:07',6,26,'testtest',0,2),(9,'982e449e-1ab0-4456-8e9a-5403a685',0,'zhangyukang','2020-05-26 09:32:43',30,9,'3434343',0,1),(10,'622e8b6d-924a-4dfe-bc51-2143cf3c',0,'zhangyukang','2020-05-26 09:34:30',13,8,'23232323',0,1),(11,'55e4942f-3c0a-4bc2-85f7-2f2a7bff',0,'zhangyukang','2020-12-15 18:12:44',2,9,'34243424',1,3),(12,'453544fd-53ec-430f-9899-73f2edf5',0,'zhangyukang','2020-05-26 10:00:04',4,8,'11111111',0,1),(13,'4d7e5874-7d9c-4fca-ac08-ee4f4975',3,'zhangyukang','2020-05-26 10:02:11',3,8,'123213',0,1);

/*Table structure for table `biz_out_stock_info` */

-- DROP TABLE IF EXISTS `biz_out_stock_info`;
--
-- CREATE TABLE `biz_out_stock_info` (
--   `id` bigint(20) NOT NULL AUTO_INCREMENT,
--   `out_num` varchar(36) DEFAULT NULL,
--   `p_num` varchar(36) DEFAULT NULL,
--   `product_number` int(11) DEFAULT NULL,
--   `create_time` datetime DEFAULT NULL,
--   `modified_time` datetime DEFAULT NULL,
--   PRIMARY KEY (`id`)
-- ) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4;

/*Data for the table `biz_out_stock_info` */

-- insert  into `biz_out_stock_info`(`id`,`out_num`,`p_num`,`product_number`,`create_time`,`modified_time`) values (1,'8d468e4b-0c45-4807-9e0e-6c2c65d6','3DFC8EA0-6',1,'2020-05-25 11:32:39','2020-05-25 11:32:39'),(2,'8d468e4b-0c45-4807-9e0e-6c2c65d6','6EF5F2C0-9',2,'2020-05-25 11:32:39','2020-05-25 11:32:39'),(3,'8d468e4b-0c45-4807-9e0e-6c2c65d6','6976D3B4-A',4,'2020-05-25 11:32:39','2020-05-25 11:32:39'),(4,'2d7158d3-2b15-473e-8cbe-6f6bd45a','3DFC8EA0-6',1,'2020-05-25 11:41:02','2020-05-25 11:41:02'),(5,'2d7158d3-2b15-473e-8cbe-6f6bd45a','6EF5F2C0-9',1,'2020-05-25 11:41:02','2020-05-25 11:41:02'),(6,'2d7158d3-2b15-473e-8cbe-6f6bd45a','6976D3B4-A',1,'2020-05-25 11:41:02','2020-05-25 11:41:02'),(7,'2d7158d3-2b15-473e-8cbe-6f6bd45a','AB0E206E-A',3,'2020-05-25 11:41:02','2020-05-25 11:41:02'),(8,'2d7158d3-2b15-473e-8cbe-6f6bd45a','6AF405A1-C',1,'2020-05-25 11:41:02','2020-05-25 11:41:02'),(9,'2d7158d3-2b15-473e-8cbe-6f6bd45a','2C15F1B6-1',3,'2020-05-25 11:41:02','2020-05-25 11:41:02'),(10,'bd5032d0-c84a-4413-a72d-ba80d33f','3DFC8EA0-6',80,'2020-05-25 12:08:07','2020-05-25 12:08:07'),(11,'bd5032d0-c84a-4413-a72d-ba80d33f','6EF5F2C0-9',13,'2020-05-25 12:08:07','2020-05-25 12:08:07'),(12,'c23e95d0-0607-4e00-9041-cc97d299','3DFC8EA0-6',1,'2020-05-25 15:46:07','2020-05-25 15:46:07'),(13,'c23e95d0-0607-4e00-9041-cc97d299','6EF5F2C0-9',1,'2020-05-25 15:46:07','2020-05-25 15:46:07'),(14,'c23e95d0-0607-4e00-9041-cc97d299','6976D3B4-A',1,'2020-05-25 15:46:07','2020-05-25 15:46:07'),(15,'c23e95d0-0607-4e00-9041-cc97d299','AB0E206E-A',1,'2020-05-25 15:46:07','2020-05-25 15:46:07'),(16,'c23e95d0-0607-4e00-9041-cc97d299','6AF405A1-C',1,'2020-05-25 15:46:07','2020-05-25 15:46:07'),(17,'c23e95d0-0607-4e00-9041-cc97d299','2C15F1B6-1',1,'2020-05-25 15:46:07','2020-05-25 15:46:07'),(21,'982e449e-1ab0-4456-8e9a-5403a685','3DFC8EA0-6',30,'2020-05-26 09:32:25','2020-05-26 09:32:25'),(22,'622e8b6d-924a-4dfe-bc51-2143cf3c','6EF5F2C0-9',13,'2020-05-26 09:34:23','2020-05-26 09:34:23'),(23,'55e4942f-3c0a-4bc2-85f7-2f2a7bff','3DFC8EA0-6',1,'2020-05-26 09:52:09','2020-05-26 09:52:09'),(24,'55e4942f-3c0a-4bc2-85f7-2f2a7bff','6EF5F2C0-9',1,'2020-05-26 09:52:09','2020-05-26 09:52:09'),(25,'453544fd-53ec-430f-9899-73f2edf5','6AF405A1-C',1,'2020-05-26 09:59:57','2020-05-26 09:59:57'),(26,'453544fd-53ec-430f-9899-73f2edf5','2C15F1B6-1',1,'2020-05-26 09:59:57','2020-05-26 09:59:57'),(27,'453544fd-53ec-430f-9899-73f2edf5','6976D3B4-A',1,'2020-05-26 09:59:57','2020-05-26 09:59:57'),(28,'453544fd-53ec-430f-9899-73f2edf5','6EF5F2C0-9',1,'2020-05-26 09:59:57','2020-05-26 09:59:57'),(29,'4d7e5874-7d9c-4fca-ac08-ee4f4975','3DFC8EA0-6',1,'2020-05-26 10:02:01','2020-05-26 10:02:01'),(30,'4d7e5874-7d9c-4fca-ac08-ee4f4975','6EF5F2C0-9',1,'2020-05-26 10:02:03','2020-05-26 10:02:03'),(31,'4d7e5874-7d9c-4fca-ac08-ee4f4975','6976D3B4-A',1,'2020-05-26 10:02:03','2020-05-26 10:02:03');


/*Table structure for table `biz_product_stock` */

-- DROP TABLE IF EXISTS `biz_product_stock`;
--
-- CREATE TABLE `biz_product_stock` (
--   `id` bigint(20) NOT NULL AUTO_INCREMENT,
--   `p_num` varchar(32) NOT NULL,
--   `stock` bigint(20) DEFAULT NULL COMMENT '商品库存结余',
--   PRIMARY KEY (`id`)
-- ) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8;

/*Data for the table `biz_product_stock` */

-- insert  into `biz_product_stock`(`id`,`p_num`,`stock`) values (43,'3DFC8EA0-6',49),(44,'2C15F1B6-1',67),(45,'6976D3B4-A',40),(46,'967CE098-3',4),(47,'79f01380-43c7-4506-9e0c-e2640288',5),(48,'f248ee7a-c30c-447c-ae9c-0a1c09e9',52),(49,'6AF405A1-C',18),(50,'0b9e9176-f996-4384-bb6c-209f55d0',16),(51,'894b8218-36ee-4a0d-9ad1-d9c5e455',14),(52,'beb944c4-01ae-497b-bfdd-2132032f',34),(53,'cf5a5f37-b299-4d96-bcb2-c4a46737',5),(54,'3fa0d5c1-4922-4078-8c7c-8d1cfeb5',2),(55,'0e17f606-9aaa-48a7-b4e9-ef18462e',2),(56,'d9155553-fe5c-4d35-bb9a-1e1ab8d4',2),(57,'6EF5F2C0-9',4),(58,'AB0E206E-A',6),(59,'24573d5b-0c9b-403b-9a88-c214702e',31),(60,'c98183c8-bc47-4505-abbb-1dc219b5',7),(61,'de16b9e6-bb49-4547-ab91-db7ae7b6',9),(62,'15bc064e-991d-40e3-bcd6-f6aff0e3',31);

/*Table structure for table `biz_supplier` */

-- DROP TABLE IF EXISTS `biz_supplier`;
--
-- CREATE TABLE `biz_supplier` (
--   `id` bigint(20) NOT NULL AUTO_INCREMENT,
--   `name` varchar(255) DEFAULT NULL COMMENT '供应商名稱',
--   `address` varchar(255) DEFAULT NULL COMMENT '供应商地址',
--   `email` varchar(255) DEFAULT NULL COMMENT '供应商邮箱',
--   `phone` varchar(255) DEFAULT NULL COMMENT '供应商电话',
--   `create_time` datetime DEFAULT NULL,
--   `modified_time` datetime DEFAULT NULL,
--   `sort` int(10) DEFAULT NULL COMMENT '排序',
--   `contact` varchar(20) DEFAULT NULL COMMENT '聯繫人',
--   PRIMARY KEY (`id`)
-- ) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

/*Data for the table `biz_supplier` */

-- insert  into `biz_supplier`(`id`,`name`,`address`,`email`,`phone`,`create_time`,`modified_time`,`sort`,`contact`) values (14,'324234','河北省/秦皇岛市/长安区','424324@qq.com','15079437282','2020-04-26 10:00:05','2020-04-26 10:00:05',2,'324324'),(15,'大街','河北省/邯郸市/峰峰矿区','3424324@qq.com','15079437282','2020-05-09 19:37:46','2020-05-10 11:25:01',1,'324324'),(16,'2342424','内蒙古自治区/鄂尔多斯市/鄂托克前旗','432423424@qq.com','15079437282','2020-05-09 19:42:07','2020-05-09 19:42:07',1,'3242423'),(17,'北京人民大会堂','北京市/市辖区/朝阳区','xiong@qq.com','15079437282','2020-05-18 11:38:04','2020-05-18 11:38:04',1,'熊老板'),(18,'xixiixxxxi','河北省/秦皇岛市/北戴河区','chrome@qq.com','15079437282','2020-05-18 11:41:09','2020-05-18 11:41:09',1,'perterchro'),(19,'xixiixxxxi','河北省/秦皇岛市/北戴河区','chrome@qq.com','15079437282','2020-05-18 11:43:49','2020-05-18 11:43:49',1,'perterchro'),(20,'aloooodf','山西省/晋城市/陵川县','justiner@qq.com','15245745454','2020-05-18 12:21:29','2020-05-18 12:21:29',1,'justiner'),(21,'HHH','河北省/秦皇岛市/长安区','HHH@qq.com','15079437282','2020-05-18 13:16:28','2020-05-18 13:16:28',1,'HHH'),(22,'PuPuPu34','山西省/阳泉市/平定县','124545454@qq.com','15254541241','2020-05-18 13:42:29','2020-05-18 13:43:05',1,'封小新');



/*Table structure for table `tb_image` */

-- DROP TABLE IF EXISTS `tb_image`;
--
-- CREATE TABLE `tb_image` (
--   `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
--   `path` varchar(1023) DEFAULT NULL COMMENT '图片路径',
--   `size` bigint(20) DEFAULT NULL COMMENT '图片大小',
--   `media_type` varchar(20) DEFAULT NULL COMMENT '图片類型',
--   `suffix` varchar(50) DEFAULT NULL COMMENT '图片后缀',
--   `height` int(20) DEFAULT NULL COMMENT '图片高度',
--   `width` int(20) DEFAULT NULL COMMENT '图片宽度',
--   `create_time` datetime DEFAULT NULL COMMENT '創建時間',
--   PRIMARY KEY (`id`)
-- ) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4;

/*Data for the table `tb_image` */

/*Table structure for table `tb_login_log` */

DROP TABLE IF EXISTS `tb_login_log`;
--
CREATE TABLE `tb_login_log` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `login_time` datetime NOT NULL COMMENT '登录时间',
  `location` varchar(50) DEFAULT NULL COMMENT '登录地点',
  `ip` varchar(50) DEFAULT NULL COMMENT 'IP地址',
  `user_system` varchar(50) DEFAULT NULL COMMENT '操作系统',
  `user_browser` varchar(50) DEFAULT NULL COMMENT '浏览器',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1643 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='登录日志表';


/*==========================================*/

DROP TABLE IF EXISTS `tb_run_text`;

CREATE TABLE `tb_run_text` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `message` varchar(200) NOT NULL,
  `status` int(2) NOT NULL COMMENT '狀態：0：刪除，1：存在',
  `load_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `tb_dictionary`;

CREATE TABLE `tb_dictionary` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type` int(2) NOT NULL COMMENT '類型：1：公司類型，2：成本中心，3：區域',
  `code` varchar(20) NOT NULL,
  `value` varchar(100) NOT NULL,
  `status` int(2) NOT NULL COMMENT '狀態：0：刪除，1：存在',
  `load_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

/*Table structure for table `biz_product_category` */

DROP TABLE IF EXISTS `biz_product_category`;

CREATE TABLE `biz_product_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '類別id',
  `name` varchar(100) DEFAULT NULL COMMENT '類別名稱',
  `remark` varchar(200) DEFAULT NULL COMMENT '備註',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `create_time` datetime DEFAULT NULL,
  `modified_time` datetime DEFAULT NULL,
  `pid` bigint(20) DEFAULT NULL COMMENT '父级分類id',
  `status` int(2) DEFAULT NULL COMMENT '狀態：0：刪除，1：存在',
  `load_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


/*Table structure for table `tb_department` */

DROP TABLE IF EXISTS `tb_department`;

CREATE TABLE `tb_department` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type_id` bigint(20) DEFAULT NULL COMMENT '公司類型id',
  `type_code` varchar(100) DEFAULT NULL COMMENT '公司類型代碼',
  `number` bigint(20) DEFAULT NULL COMMENT '流水編號',
  `type_number` varchar(100) DEFAULT NULL COMMENT '公司帳號',
  `name` varchar(100) DEFAULT NULL COMMENT '公司名稱',
  `nickname` varchar(100) DEFAULT NULL COMMENT '公司簡稱',
  `contact` varchar(20) DEFAULT NULL COMMENT '聯絡人',
  `phone` varchar(20) DEFAULT NULL COMMENT '電話',
  `cell_phone` varchar(20) DEFAULT NULL COMMENT '手機',
  `email` varchar(100) DEFAULT NULL COMMENT '信箱',
  `remark` varchar(200) DEFAULT NULL COMMENT '備註',
  `food` int(2) DEFAULT NULL COMMENT '廚餘標記：0：無，1：有',
  `status` int(2) DEFAULT NULL COMMENT '狀態：0：刪除，1：存在',
  `address` varchar(20) DEFAULT NULL COMMENT '办公室地点',
  `create_time` datetime DEFAULT NULL COMMENT '創建時間',
  `modified_time` datetime DEFAULT NULL COMMENT '修改時間',
  `load_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


/*Table structure for table `tb_user` */

DROP TABLE IF EXISTS `tb_user`;

CREATE TABLE `tb_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '帳號id',
  `username` varchar(50) NOT NULL COMMENT '帳號',
  `nickname` varchar(20) DEFAULT NULL,
  `department_id` bigint(20) DEFAULT '1' COMMENT '公司id',
  `region_id` bigint(20) DEFAULT '1' COMMENT '地區id',
  `email` varchar(128) DEFAULT NULL COMMENT '信箱',
  `status` int(1) NOT NULL COMMENT '狀態 0锁定 1有效',
  `password` varchar(128) NOT NULL COMMENT '密碼',
  `salt` varchar(255) DEFAULT NULL COMMENT '盐',
  `type` int(11) NOT NULL DEFAULT '1' COMMENT '0:超级管理員，1：系统用户',
  `avatar` text COMMENT '頭像',
  `phone_number` varchar(20) DEFAULT NULL COMMENT '聯繫电话',
  `create_time` datetime DEFAULT NULL COMMENT '創建時間',
  `modified_time` datetime DEFAULT NULL COMMENT '修改時間',
  `sex` int(1) DEFAULT NULL COMMENT '性别 0男 1女 2保密',
  `birth` date DEFAULT NULL,
  `load_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户表';

/*Data for the table `tb_user` */

INSERT INTO tb_user
(id, username, nickname, department_id, region_id, email, status, password, salt, `type`, avatar, phone_number, create_time, modified_time, sex, birth)
VALUES(200, 'admin', 'superuser', 1, 2, '', 1, 'd7b9c28cac022955cff27947eafce0ad', 'cfbf6d34-d3e4-4653-86f0-e33d4595d52b', 0, 'http://thirdqq.qlogo.cn/g?b=oidb&k=icTYjyV5afABvE1v4ge9SLg&s=100&t=1584195695', '17744444444', '2019-06-14 21:12:16', '2020-03-19 04:20:40', 0, '2020-03-27');

DROP TABLE IF EXISTS `tb_user_card`;

CREATE TABLE `tb_user_card` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `card_name` varchar(100) NOT NULL,
  `status` int(2) NOT NULL,
  `load_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `tb_card_product`;

CREATE TABLE `tb_card_product` (
  `card_id` bigint(20) NOT NULL,
  `product_id` bigint(20) NOT null,
  `load_time` datetime DEFAULT NULL
) ENGINE=InnoDB CHARSET=utf8mb4;

/*Table structure for table `biz_product` */

DROP TABLE IF EXISTS `biz_product`;

CREATE TABLE `biz_product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `p_num` varchar(255) DEFAULT NULL COMMENT '商品编号',
  `name` varchar(255) NOT NULL UNIQUE COMMENT '名稱',
  `image_url` text COMMENT '图片',
  `model` varchar(100) DEFAULT NULL COMMENT '成本中心id',
  `unit` varchar(10) DEFAULT NULL COMMENT '计算单位',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `create_time` datetime DEFAULT NULL COMMENT '創建時間',
  `modified_time` datetime DEFAULT NULL COMMENT '修改時間',
  `one_category_id` bigint(20) DEFAULT NULL COMMENT '廢棄物大類',
  `two_category_id` bigint(20) DEFAULT NULL COMMENT '廢棄物小類',
  `three_category_id` bigint(20) DEFAULT NULL COMMENT '3级分類',
  `status` int(1) DEFAULT '0' COMMENT '狀態:1啟用,0:停用',
  `load_time` datetime DEFAULT NULL,
  KEY `id` (`id`),
  KEY `category_id` (`one_category_id`),
  KEY `two_category_id` (`two_category_id`),
  KEY `three_category_id` (`three_category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

/*Table structure for table `biz_product_price` */

DROP TABLE IF EXISTS `biz_product_price`;

CREATE TABLE `biz_product_price` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `one_category_id` bigint(20) DEFAULT NULL COMMENT '廢棄物大類',
  `two_category_id` bigint(20) DEFAULT NULL COMMENT '廢棄物小類',
  `three_category_id` bigint(20) DEFAULT NULL COMMENT '廢棄物三分類',
  `product_id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL COMMENT '名稱',
  `price` decimal(20,6) DEFAULT NULL,
  `unit` varchar(50) DEFAULT NULL,
  `valid_month` varchar(50) DEFAULT NULL COMMENT '適用月份',
  `load_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB CHARSET=utf8mb4;

INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(1, 3, 7, 4, '家用電器', 3.000000, 'KG', '2019-01', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(2, 3, 7, 6, '碳粉匣', 530.000000, '批', '2019-01', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(3, 3, 8, 8, '舊衣服', 0.000000, 'KG', '2019-01', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(4, 3, 9, 9, '廢食用油', 6.600000, 'KG', '2019-01', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(5, 3, 10, 12, '廢鐵', 7.000000, 'KG', '2019-01', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(6, 3, 10, 13, '鋁罐/鋁料', 25.000000, 'KG', '2019-01', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(7, 3, 10, 14, '鐵罐', 4.000000, 'KG', '2019-01', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(8, 3, 11, 15, '化妝品玻璃', 0.000000, 'KG', '2019-01', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(9, 3, 11, 16, '玻璃瓶', 0.000000, 'KG', '2019-01', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(10, 3, 12, 17, '化妝品塑膠', 0.000000, 'KG', '2019-01', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(11, 3, 12, 18, '其他塑膠', 0.000000, 'KG', '2019-01', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(12, 3, 12, 19, '保麗龍', 0.000000, 'KG', '2019-01', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(13, 3, 12, 20, '塑膠杯盒', 0.000000, 'KG', '2019-01', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(14, 3, 12, 21, '塑膠瓶', 3.000000, 'KG', '2019-01', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(15, 3, 12, 22, '塑膠袋', 200.000000, '批', '2019-01', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(16, 3, 12, 23, '廢光碟片', 5.000000, 'KG', '2019-01', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(17, 3, 12, 24, '壓克力', 5.000000, 'KG', '2019-01', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(18, 3, 13, 25, '日光燈管', 1.500000, 'KG', '2019-01', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(19, 3, 13, 26, '省電燈泡', 2.000000, 'KG', '2019-01', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(20, 3, 14, 27, '乾電池', 12.000000, 'KG', '2019-01', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(21, 3, 15, 30, '熟廚餘', 0.000000, '批', '2019-01', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(22, 3, 16, 31, '便當紙盒', 0.000000, 'KG', '2019-01', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(23, 3, 16, 32, '紙杯', 0.000000, 'KG', '2019-01', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(24, 3, 16, 33, '紙張/紙板', 2.500000, 'KG', '2019-01', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(25, 3, 16, 34, '鋁箔包', 1.000000, 'KG', '2019-01', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(26, 3, 17, 35, '廢電線', 32.000000, 'KG', '2019-01', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(27, 3, 7, 4, '家用電器', 3.000000, 'KG', '2019-02', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(28, 3, 7, 6, '碳粉匣', 0.000000, '批', '2019-02', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(29, 3, 8, 8, '舊衣服', 0.000000, 'KG', '2019-02', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(30, 3, 9, 9, '廢食用油', 6.600000, 'KG', '2019-02', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(31, 3, 10, 12, '廢鐵', 7.000000, 'KG', '2019-02', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(32, 3, 10, 13, '鋁罐/鋁料', 25.000000, 'KG', '2019-02', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(33, 3, 10, 14, '鐵罐', 4.000000, 'KG', '2019-02', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(34, 3, 11, 15, '化妝品玻璃', 0.000000, 'KG', '2019-02', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(35, 3, 11, 16, '玻璃瓶', 0.000000, 'KG', '2019-02', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(36, 3, 12, 17, '化妝品塑膠', 0.000000, 'KG', '2019-02', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(37, 3, 12, 18, '其他塑膠', 0.000000, 'KG', '2019-02', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(38, 3, 12, 19, '保麗龍', 0.000000, 'KG', '2019-02', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(39, 3, 12, 20, '塑膠杯盒', 0.000000, 'KG', '2019-02', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(40, 3, 12, 21, '塑膠瓶', 3.000000, 'KG', '2019-02', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(41, 3, 12, 22, '塑膠袋', 150.000000, '批', '2019-02', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(42, 3, 12, 23, '廢光碟片', 5.000000, 'KG', '2019-02', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(43, 3, 12, 24, '壓克力', 5.000000, 'KG', '2019-02', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(44, 3, 13, 25, '日光燈管', 1.500000, 'KG', '2019-02', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(45, 3, 13, 26, '省電燈泡', 2.000000, 'KG', '2019-02', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(46, 3, 14, 27, '乾電池', 12.000000, 'KG', '2019-02', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(47, 3, 15, 30, '熟廚餘', 0.000000, '批', '2019-02', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(48, 3, 16, 31, '便當紙盒', 0.000000, 'KG', '2019-02', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(49, 3, 16, 32, '紙杯', 0.000000, 'KG', '2019-02', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(50, 3, 16, 33, '紙張/紙板', 2.500000, 'KG', '2019-02', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(51, 3, 16, 34, '鋁箔包', 1.000000, 'KG', '2019-02', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(52, 3, 17, 35, '廢電線', 32.000000, 'KG', '2019-02', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(53, 3, 7, 4, '家用電器', 3.000000, 'KG', '2019-03', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(54, 3, 7, 6, '碳粉匣', 0.000000, '批', '2019-03', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(55, 3, 8, 8, '舊衣服', 0.000000, 'KG', '2019-03', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(56, 3, 9, 9, '廢食用油', 6.600000, 'KG', '2019-03', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(57, 3, 10, 12, '廢鐵', 7.000000, 'KG', '2019-03', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(58, 3, 10, 13, '鋁罐/鋁料', 25.000000, 'KG', '2019-03', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(59, 3, 10, 14, '鐵罐', 4.000000, 'KG', '2019-03', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(60, 3, 11, 15, '化妝品玻璃', 0.000000, 'KG', '2019-03', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(61, 3, 11, 16, '玻璃瓶', 0.000000, 'KG', '2019-03', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(62, 3, 12, 17, '化妝品塑膠', 0.000000, 'KG', '2019-03', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(63, 3, 12, 18, '其他塑膠', 0.000000, 'KG', '2019-03', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(64, 3, 12, 19, '保麗龍', 0.000000, 'KG', '2019-03', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(65, 3, 12, 20, '塑膠杯盒', 0.000000, 'KG', '2019-03', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(66, 3, 12, 21, '塑膠瓶', 3.000000, 'KG', '2019-03', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(67, 3, 12, 22, '塑膠袋', 0.000000, '批', '2019-03', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(68, 3, 12, 23, '廢光碟片', 5.000000, 'KG', '2019-03', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(69, 3, 12, 24, '壓克力', 5.000000, 'KG', '2019-03', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(70, 3, 13, 25, '日光燈管', 1.500000, 'KG', '2019-03', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(71, 3, 13, 26, '省電燈泡', 2.000000, 'KG', '2019-03', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(72, 3, 14, 27, '乾電池', 12.000000, 'KG', '2019-03', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(73, 3, 15, 30, '熟廚餘', 0.000000, '批', '2019-03', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(74, 3, 16, 31, '便當紙盒', 0.000000, 'KG', '2019-03', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(75, 3, 16, 32, '紙杯', 0.000000, 'KG', '2019-03', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(76, 3, 16, 33, '紙張/紙板', 2.500000, 'KG', '2019-03', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(77, 3, 16, 34, '鋁箔包', 1.000000, 'KG', '2019-03', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(78, 3, 17, 35, '廢電線', 32.000000, 'KG', '2019-03', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(79, 3, 7, 4, '家用電器', 3.000000, 'KG', '2019-04', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(80, 3, 7, 6, '碳粉匣', 350.000000, '批', '2019-04', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(81, 3, 8, 8, '舊衣服', 0.000000, 'KG', '2019-04', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(82, 3, 9, 9, '廢食用油', 6.600000, 'KG', '2019-04', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(83, 3, 10, 12, '廢鐵', 7.000000, 'KG', '2019-04', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(84, 3, 10, 13, '鋁罐/鋁料', 25.000000, 'KG', '2019-04', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(85, 3, 10, 14, '鐵罐', 4.000000, 'KG', '2019-04', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(86, 3, 11, 15, '化妝品玻璃', 0.000000, 'KG', '2019-04', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(87, 3, 11, 16, '玻璃瓶', 0.000000, 'KG', '2019-04', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(88, 3, 12, 17, '化妝品塑膠', 0.000000, 'KG', '2019-04', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(89, 3, 12, 18, '其他塑膠', 0.000000, 'KG', '2019-04', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(90, 3, 12, 19, '保麗龍', 0.000000, 'KG', '2019-04', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(91, 3, 12, 20, '塑膠杯盒', 0.000000, 'KG', '2019-04', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(92, 3, 12, 21, '塑膠瓶', 6.000000, 'KG', '2019-04', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(93, 3, 12, 22, '塑膠袋', 0.000000, '批', '2019-04', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(94, 3, 12, 23, '廢光碟片', 5.000000, 'KG', '2019-04', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(95, 3, 12, 24, '壓克力', 5.000000, 'KG', '2019-04', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(96, 3, 13, 25, '日光燈管', 1.500000, 'KG', '2019-04', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(97, 3, 13, 26, '省電燈泡', 2.000000, 'KG', '2019-04', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(98, 3, 14, 27, '乾電池', 12.000000, 'KG', '2019-04', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(99, 3, 15, 30, '熟廚餘', 0.000000, '批', '2019-04', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(100, 3, 16, 31, '便當紙盒', 0.000000, 'KG', '2019-04', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(101, 3, 16, 32, '紙杯', 0.000000, 'KG', '2019-04', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(102, 3, 16, 33, '紙張/紙板', 2.500000, 'KG', '2019-04', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(103, 3, 16, 34, '鋁箔包', 1.000000, 'KG', '2019-04', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(104, 3, 17, 35, '廢電線', 32.000000, 'KG', '2019-04', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(105, 3, 7, 4, '家用電器', 3.000000, 'KG', '2019-05', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(106, 3, 7, 6, '碳粉匣', 350.000000, '批', '2019-05', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(107, 3, 8, 8, '舊衣服', 0.000000, 'KG', '2019-05', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(108, 3, 9, 9, '廢食用油', 6.600000, 'KG', '2019-05', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(109, 3, 10, 12, '廢鐵', 7.000000, 'KG', '2019-05', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(110, 3, 10, 13, '鋁罐/鋁料', 25.000000, 'KG', '2019-05', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(111, 3, 10, 14, '鐵罐', 4.000000, 'KG', '2019-05', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(112, 3, 11, 15, '化妝品玻璃', 0.000000, 'KG', '2019-05', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(113, 3, 11, 16, '玻璃瓶', 0.000000, 'KG', '2019-05', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(114, 3, 12, 17, '化妝品塑膠', 0.000000, 'KG', '2019-05', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(115, 3, 12, 18, '其他塑膠', 0.000000, 'KG', '2019-05', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(116, 3, 12, 19, '保麗龍', 0.000000, 'KG', '2019-05', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(117, 3, 12, 20, '塑膠杯盒', 0.000000, 'KG', '2019-05', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(118, 3, 12, 21, '塑膠瓶', 6.000000, 'KG', '2019-05', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(119, 3, 12, 22, '塑膠袋', 0.000000, '批', '2019-05', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(120, 3, 12, 23, '廢光碟片', 5.000000, 'KG', '2019-05', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(121, 3, 12, 24, '壓克力', 5.000000, 'KG', '2019-05', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(122, 3, 13, 25, '日光燈管', 1.500000, 'KG', '2019-05', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(123, 3, 13, 26, '省電燈泡', 2.000000, 'KG', '2019-05', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(124, 3, 14, 27, '乾電池', 12.000000, 'KG', '2019-05', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(125, 3, 15, 30, '熟廚餘', 0.000000, '批', '2019-05', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(126, 3, 16, 31, '便當紙盒', 0.000000, 'KG', '2019-05', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(127, 3, 16, 32, '紙杯', 0.000000, 'KG', '2019-05', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(128, 3, 16, 33, '紙張/紙板', 1.700000, 'KG', '2019-05', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(129, 3, 16, 34, '鋁箔包', 1.000000, 'KG', '2019-05', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(130, 3, 17, 35, '廢電線', 32.000000, 'KG', '2019-05', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(131, 3, 7, 4, '家用電器', 3.000000, 'KG', '2019-06', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(132, 3, 7, 6, '碳粉匣', 200.000000, '批', '2019-06', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(133, 3, 8, 8, '舊衣服', 0.000000, 'KG', '2019-06', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(134, 3, 9, 9, '廢食用油', 6.600000, 'KG', '2019-06', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(135, 3, 10, 12, '廢鐵', 6.000000, 'KG', '2019-06', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(136, 3, 10, 13, '鋁罐/鋁料', 25.000000, 'KG', '2019-06', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(137, 3, 10, 14, '鐵罐', 4.000000, 'KG', '2019-06', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(138, 3, 11, 15, '化妝品玻璃', 0.000000, 'KG', '2019-06', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(139, 3, 11, 16, '玻璃瓶', 0.000000, 'KG', '2019-06', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(140, 3, 12, 17, '化妝品塑膠', 0.000000, 'KG', '2019-06', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(141, 3, 12, 18, '其他塑膠', 0.000000, 'KG', '2019-06', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(142, 3, 12, 19, '保麗龍', 0.000000, 'KG', '2019-06', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(143, 3, 12, 20, '塑膠杯盒', 0.000000, 'KG', '2019-06', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(144, 3, 12, 21, '塑膠瓶', 4.000000, 'KG', '2019-06', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(145, 3, 12, 22, '塑膠袋', 0.000000, '批', '2019-06', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(146, 3, 12, 23, '廢光碟片', 5.000000, 'KG', '2019-06', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(147, 3, 12, 24, '壓克力', 5.000000, 'KG', '2019-06', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(148, 3, 13, 25, '日光燈管', 1.500000, 'KG', '2019-06', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(149, 3, 13, 26, '省電燈泡', 2.000000, 'KG', '2019-06', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(150, 3, 14, 27, '乾電池', 12.000000, 'KG', '2019-06', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(151, 3, 15, 30, '熟廚餘', 0.000000, '批', '2019-06', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(152, 3, 16, 31, '便當紙盒', 0.000000, 'KG', '2019-06', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(153, 3, 16, 32, '紙杯', 0.000000, 'KG', '2019-06', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(154, 3, 16, 33, '紙張/紙板', 1.500000, 'KG', '2019-06', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(155, 3, 16, 34, '鋁箔包', 1.000000, 'KG', '2019-06', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(156, 3, 17, 35, '廢電線', 32.000000, 'KG', '2019-06', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(157, 3, 7, 4, '家用電器', 3.000000, 'KG', '2019-07', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(158, 3, 7, 6, '碳粉匣', 0.000000, '批', '2019-07', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(159, 3, 8, 8, '舊衣服', 0.000000, 'KG', '2019-07', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(160, 3, 9, 9, '廢食用油', 6.600000, 'KG', '2019-07', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(161, 3, 10, 12, '廢鐵', 6.000000, 'KG', '2019-07', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(162, 3, 10, 13, '鋁罐/鋁料', 25.000000, 'KG', '2019-07', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(163, 3, 10, 14, '鐵罐', 4.000000, 'KG', '2019-07', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(164, 3, 11, 15, '化妝品玻璃', 0.000000, 'KG', '2019-07', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(165, 3, 11, 16, '玻璃瓶', 0.000000, 'KG', '2019-07', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(166, 3, 12, 17, '化妝品塑膠', 0.000000, 'KG', '2019-07', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(167, 3, 12, 18, '其他塑膠', 0.000000, 'KG', '2019-07', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(168, 3, 12, 19, '保麗龍', 0.000000, 'KG', '2019-07', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(169, 3, 12, 20, '塑膠杯盒', 0.000000, 'KG', '2019-07', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(170, 3, 12, 21, '塑膠瓶', 4.000000, 'KG', '2019-07', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(171, 3, 12, 22, '塑膠袋', 0.000000, '批', '2019-07', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(172, 3, 12, 23, '廢光碟片', 5.000000, 'KG', '2019-07', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(173, 3, 12, 24, '壓克力', 5.000000, 'KG', '2019-07', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(174, 3, 13, 25, '日光燈管', 1.500000, 'KG', '2019-07', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(175, 3, 13, 26, '省電燈泡', 2.000000, 'KG', '2019-07', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(176, 3, 14, 27, '乾電池', 12.000000, 'KG', '2019-07', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(177, 3, 15, 30, '熟廚餘', 0.000000, '批', '2019-07', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(178, 3, 16, 31, '便當紙盒', 0.000000, 'KG', '2019-07', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(179, 3, 16, 32, '紙杯', 0.000000, 'KG', '2019-07', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(180, 3, 16, 33, '紙張/紙板', 1.500000, 'KG', '2019-07', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(181, 3, 16, 34, '鋁箔包', 1.000000, 'KG', '2019-07', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(182, 3, 17, 35, '廢電線', 32.000000, 'KG', '2019-07', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(183, 3, 7, 4, '家用電器', 3.000000, 'KG', '2019-08', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(184, 3, 7, 6, '碳粉匣', 300.000000, '批', '2019-08', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(185, 3, 8, 8, '舊衣服', 0.000000, 'KG', '2019-08', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(186, 3, 9, 9, '廢食用油', 6.600000, 'KG', '2019-08', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(187, 3, 10, 12, '廢鐵', 6.000000, 'KG', '2019-08', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(188, 3, 10, 13, '鋁罐/鋁料', 25.000000, 'KG', '2019-08', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(189, 3, 10, 14, '鐵罐', 4.000000, 'KG', '2019-08', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(190, 3, 11, 15, '化妝品玻璃', 0.000000, 'KG', '2019-08', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(191, 3, 11, 16, '玻璃瓶', 0.000000, 'KG', '2019-08', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(192, 3, 12, 17, '化妝品塑膠', 0.000000, 'KG', '2019-08', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(193, 3, 12, 18, '其他塑膠', 0.000000, 'KG', '2019-08', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(194, 3, 12, 19, '保麗龍', 0.000000, 'KG', '2019-08', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(195, 3, 12, 20, '塑膠杯盒', 0.000000, 'KG', '2019-08', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(196, 3, 12, 21, '塑膠瓶', 4.000000, 'KG', '2019-08', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(197, 3, 12, 22, '塑膠袋', 0.000000, '批', '2019-08', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(198, 3, 12, 23, '廢光碟片', 5.000000, 'KG', '2019-08', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(199, 3, 12, 24, '壓克力', 5.000000, 'KG', '2019-08', '2021-11-01 12:00:00');
INSERT INTO biz_product_price
(id, one_category_id, two_category_id, product_id, name, price, unit, valid_month, load_time)
VALUES(200, 3, 13, 25, '日光燈管', 1.500000, 'KG', '2019-08', '2021-11-01 12:00:00');



/*Table structure for table `tb_weight` */

DROP TABLE IF EXISTS `tb_weight`;

CREATE TABLE `tb_weight` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `department_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `card_id` bigint(20) NOT NULL,
  `product_id` bigint(20) NOT NULL,
  `total_weight` decimal(20,6) DEFAULT NULL,
  `deduct_weight` decimal(20,6) DEFAULT NULL,
  `net_weight` decimal(20,6) DEFAULT NULL,
  `status` int(2) NOT NULL,
  `create_time` datetime DEFAULT NULL COMMENT '創建時間',
  `load_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB CHARSET=utf8mb4;


/*Table structure for table `tb_menu` */

DROP TABLE IF EXISTS `tb_menu`;

CREATE TABLE `tb_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '選單/按鈕ID',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '上级選單ID',
  `menu_name` varchar(50) NOT NULL COMMENT '選單/按鈕名稱',
  `url` varchar(50) DEFAULT NULL COMMENT '選單URL',
  `perms` text COMMENT '權限標示',
  `icon` varchar(50) DEFAULT NULL COMMENT '圖標',
  `type` char(2) NOT NULL COMMENT '類型 0選單 1按鈕',
  `order_num` bigint(20) DEFAULT NULL COMMENT '排序',
  `create_time` datetime DEFAULT NULL COMMENT '創建時間',
  `modified_time` datetime DEFAULT NULL COMMENT '修改時間',
  `available` int(11) DEFAULT '1' COMMENT '0：不可用，1：可用',
  `open` int(1) DEFAULT '1' COMMENT '0:不展開，1：展開',
  `load_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='選單表';

DROP TABLE IF EXISTS `tb_menu`;

CREATE TABLE `tb_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '選單/按鈕ID',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '上级選單ID',
  `menu_name` varchar(50) NOT NULL COMMENT '選單/按鈕名稱',
  `url` varchar(50) DEFAULT NULL COMMENT '選單URL',
  `perms` text COMMENT '權限標示',
  `icon` varchar(50) DEFAULT NULL COMMENT '圖標',
  `type` char(2) NOT NULL COMMENT '類型 0選單 1按鈕',
  `order_num` bigint(20) DEFAULT NULL COMMENT '排序',
  `create_time` datetime DEFAULT NULL COMMENT '創建時間',
  `modified_time` datetime DEFAULT NULL COMMENT '修改時間',
  `available` int(11) DEFAULT '1' COMMENT '0：不可用，1：可用',
  `open` int(1) DEFAULT '1' COMMENT '0:不展開，1：展開',
  `load_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='選單表';

INSERT INTO tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`, load_time)
VALUES(1, 0, '一般設定', '', NULL, 'el-icon-setting', '0', 1, '2020-03-07 17:41:30', '2021-11-13 15:06:08', 1, 0, NULL);
INSERT INTO tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`, load_time)
VALUES(2, 0, '基本資料', '', NULL, 'el-icon-setting', '0', 2, '2020-03-07 17:41:30', '2020-08-19 17:57:20', 1, 0, NULL);
INSERT INTO tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`, load_time)
VALUES(3, 0, '秤重管理', '', NULL, 'el-icon-setting', '0', 3, '2020-03-07 17:41:30', '2020-08-19 17:57:20', 1, 0, NULL);
INSERT INTO tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`, load_time)
VALUES(4, 0, '報表查詢', '', NULL, 'el-icon-setting', '0', 4, '2020-03-07 17:41:30', '2020-08-19 17:57:20', 1, 0, NULL);
INSERT INTO tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`, load_time)
VALUES(5, 0, '權限管理', '', NULL, 'el-icon-setting', '0', 5, '2020-03-07 17:41:30', '2020-08-19 17:57:20', 1, 0, NULL);
INSERT INTO tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`, load_time)
VALUES(6, 0, '後臺功能', '', NULL, 'el-icon-setting', '0', 6, '2020-03-07 17:41:30', '2020-08-19 17:57:20', 1, 0, NULL);
INSERT INTO tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`, load_time)
VALUES(7, 1, '公司類型設定', '/system/departmentCategories', '', 'el-icon-star-off', '0', 1, '2020-03-16 09:01:48', '2020-12-15 19:51:44', 1, 0, NULL);
INSERT INTO tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`, load_time)
VALUES(8, 1, '區域設定', '/system/region', '', 'el-icon-star-off', '0', 2, '2020-03-16 09:01:48', '2020-12-15 19:51:44', 1, 0, NULL);
INSERT INTO tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`, load_time)
VALUES(10, 1, '廢棄物類型設定', '/business/product/categories', '', 'el-icon-star-off', '0', 3, '2020-03-16 09:01:48', '2021-11-13 15:06:04', 1, 0, NULL);
INSERT INTO tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`, load_time)
VALUES(11, 1, '跑馬燈設定', '/system/runText', '', 'el-icon-star-off', '0', 4, '2020-03-16 09:01:48', '2021-11-13 14:09:52', 1, 0, NULL);
INSERT INTO tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`, load_time)
VALUES(12, 2, '公司管理', '/system/departments', '', 'el-icon-s-home', '0', 1, '2020-03-15 06:05:48', '2020-12-15 17:25:18', 1, 0, NULL);
INSERT INTO tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`, load_time)
VALUES(13, 2, '用戶管理', '/system/users', 'users', 'el-icon-user', '0', 2, '2020-03-10 05:27:54', '2020-12-15 17:24:22', 1, 0, NULL);
INSERT INTO tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`, load_time)
VALUES(14, 2, '廢棄物管理', '/business/product/list', '', 'el-icon-goods', '0', 3, '2020-03-16 09:01:02', '2020-12-15 19:51:38', 1, 0, NULL);
INSERT INTO tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`, load_time)
VALUES(15, 3, '秤重明細維護', '/business/weight/list', 'el-icon-date', 'el-icon-date', '0', 1, '2020-03-10 05:34:28', '2020-12-15 19:57:21', 1, 0, NULL);
INSERT INTO tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`, load_time)
VALUES(16, 3, '相關資料補登', '/business/weight/refill', 'el-icon-date', 'el-icon-date', '0', 2, '2020-03-10 05:34:28', '2020-12-15 19:57:21', 1, 0, NULL);
INSERT INTO tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`, load_time)
VALUES(17, 5, '選單管理', '/system/menus', 'menus', 'el-icon-help', '0', 1, '2020-03-07 18:57:42', '2020-12-15 17:25:02', 1, 0, NULL);
INSERT INTO tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`, load_time)
VALUES(18, 5, '角色權限', '/system/roles', 'roles', 'el-icon-postcard', '0', 3, '2020-03-10 05:51:28', '2020-12-15 17:24:41', 1, 0, NULL);
INSERT INTO tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`, load_time)
VALUES(19, 6, '操作日誌', '/monitor/logs', '', 'el-icon-edit', '0', 1, '2020-04-04 19:04:53', '2020-12-15 18:34:36', 1, 0, NULL);
INSERT INTO tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`, load_time)
VALUES(20, 4, '廢棄物統計', '/business/report/report1', '', 'el-icon-s-marketing', '0', 1, '2020-12-15 18:35:18', '2020-12-15 18:35:18', 1, 0, NULL);
INSERT INTO tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`, load_time)
VALUES(21, 7, '新增公司類型', '', 'departmentCategory:add', 'el-icon-setting', '1', 1, '2020-12-15 18:35:18', '2020-12-15 18:35:18', 1, 0, NULL);
INSERT INTO tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`, load_time)
VALUES(22, 7, '編辑公司類型', '', 'departmentCategory:edit', 'el-icon-setting', '1', 2, '2020-12-15 18:35:18', '2020-12-15 18:35:18', 1, 0, NULL);
INSERT INTO tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`, load_time)
VALUES(24, 7, '更新公司類型狀態', '', 'departmentCategory:status', 'el-icon-setting', '1', 3, '2020-12-15 18:35:18', '2020-12-15 18:35:18', 1, 0, NULL);
INSERT INTO tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`, load_time)
VALUES(25, 7, '下載公司類型', '', 'departmentCategory:export', 'el-icon-setting', '1', 4, '2020-12-15 18:35:18', '2020-12-15 18:35:18', 1, 0, NULL);
INSERT INTO tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`, load_time)
VALUES(26, 8, '新增區域', '', 'region:add', 'el-icon-setting', '1', 1, '2020-12-15 18:35:18', '2020-12-15 18:35:18', 1, 0, NULL);
INSERT INTO tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`, load_time)
VALUES(27, 8, '編辑區域', '', 'region:edit', 'el-icon-setting', '1', 2, '2020-12-15 18:35:18', '2020-12-15 18:35:18', 1, 0, NULL);
INSERT INTO tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`, load_time)
VALUES(29, 8, '更新區域狀態', '', 'region:status', 'el-icon-setting', '1', 3, '2020-12-15 18:35:18', '2020-12-15 18:35:18', 1, 0, NULL);
INSERT INTO tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`, load_time)
VALUES(30, 8, '下載區域', '', 'region:export', 'el-icon-setting', '1', 4, '2020-12-15 18:35:18', '2020-12-15 18:35:18', 1, 0, NULL);
INSERT INTO tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`, load_time)
VALUES(31, 10, '新增分類', '', 'productCategory:add', 'el-icon-setting', '1', 1, '2020-12-15 18:35:18', '2020-12-15 18:35:18', 1, 0, NULL);
INSERT INTO tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`, load_time)
VALUES(32, 10, '編辑分類', '', 'productCategory:edit', 'el-icon-setting', '1', 2, '2020-12-15 18:35:18', '2020-12-15 18:35:18', 1, 0, NULL);
INSERT INTO tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`, load_time)
VALUES(34, 10, '更新分類狀態', '', 'productCategory:status', 'el-icon-setting', '1', 3, '2020-12-15 18:35:18', '2020-12-15 18:35:18', 1, 0, NULL);
INSERT INTO tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`, load_time)
VALUES(35, 10, '下載分類', '', 'productCategory:export', 'el-icon-setting', '1', 4, '2020-12-15 18:35:18', '2020-12-15 18:35:18', 1, 0, NULL);
INSERT INTO tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`, load_time)
VALUES(36, 11, '新增跑馬燈', '', 'runText:add', 'el-icon-setting', '1', 1, '2020-12-15 18:35:18', '2020-12-15 18:35:18', 1, 0, NULL);
INSERT INTO tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`, load_time)
VALUES(37, 11, '編辑跑馬燈', '', 'runText:edit', 'el-icon-setting', '1', 2, '2020-12-15 18:35:18', '2020-12-15 18:35:18', 1, 0, NULL);
INSERT INTO tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`, load_time)
VALUES(39, 11, '更新跑馬燈狀態', '', 'runText:status', 'el-icon-setting', '1', 3, '2020-12-15 18:35:18', '2020-12-15 18:35:18', 1, 0, NULL);
INSERT INTO tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`, load_time)
VALUES(40, 11, '下載跑馬燈', '', 'runText:export', 'el-icon-setting', '1', 4, '2020-12-15 18:35:18', '2020-12-15 18:35:18', 1, 0, NULL);
INSERT INTO tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`, load_time)
VALUES(41, 12, '新增公司', '', 'department:add', 'el-icon-setting', '1', 1, '2020-12-15 18:35:18', '2020-12-15 18:35:18', 1, 0, NULL);
INSERT INTO tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`, load_time)
VALUES(42, 12, '編辑公司', '', 'department:edit', 'el-icon-setting', '1', 2, '2020-12-15 18:35:18', '2020-12-15 18:35:18', 1, 0, NULL);
INSERT INTO tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`, load_time)
VALUES(44, 12, '更新公司狀態', '', 'department:status', 'el-icon-setting', '1', 3, '2020-12-15 18:35:18', '2020-12-15 18:35:18', 1, 0, NULL);
INSERT INTO tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`, load_time)
VALUES(45, 12, '下載公司', '', 'department:export', 'el-icon-setting', '1', 4, '2020-12-15 18:35:18', '2020-12-15 18:35:18', 1, 0, NULL);
INSERT INTO tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`, load_time)
VALUES(46, 12, '更新廚餘標記', '', 'department:food', 'el-icon-setting', '1', 5, '2020-12-15 18:35:18', '2020-12-15 18:35:18', 1, 0, NULL);
INSERT INTO tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`, load_time)
VALUES(47, 13, '新增用戶', '', 'user:add', 'el-icon-setting', '1', 1, '2020-12-15 18:35:18', '2020-12-15 18:35:18', 1, 0, NULL);
INSERT INTO tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`, load_time)
VALUES(48, 13, '編辑用戶', '', 'user:edit', 'el-icon-setting', '1', 2, '2020-12-15 18:35:18', '2020-12-15 18:35:18', 1, 0, NULL);
INSERT INTO tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`, load_time)
VALUES(50, 13, '更新用戶狀態', '', 'user:status', 'el-icon-setting', '1', 3, '2020-12-15 18:35:18', '2020-12-15 18:35:18', 1, 0, NULL);
INSERT INTO tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`, load_time)
VALUES(51, 13, '下載用戶', '', 'user:export', 'el-icon-setting', '1', 4, '2020-12-15 18:35:18', '2020-12-15 18:35:18', 1, 0, NULL);
INSERT INTO tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`, load_time)
VALUES(52, 13, '分配角色', '', 'user:assign', 'el-icon-setting', '1', 5, '2020-12-15 18:35:18', '2020-12-15 18:35:18', 1, 0, NULL);
INSERT INTO tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`, load_time)
VALUES(53, 13, '更新用戶密碼', '', 'user:changePassword', 'el-icon-setting', '1', 6, '2020-12-15 18:35:18', '2020-12-15 18:35:18', 1, 0, NULL);
INSERT INTO tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`, load_time)
VALUES(54, 13, '卡片管理', '', 'card:manage', 'el-icon-setting', '1', 7, '2020-12-15 18:35:18', '2020-12-15 18:35:18', 1, 0, NULL);
INSERT INTO tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`, load_time)
VALUES(55, 14, '新增廢棄物', '', 'product:add', 'el-icon-setting', '1', 1, '2020-12-15 18:35:18', '2020-12-15 18:35:18', 1, 0, NULL);
INSERT INTO tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`, load_time)
VALUES(56, 14, '編辑廢棄物', '', 'product:edit', 'el-icon-setting', '1', 2, '2020-12-15 18:35:18', '2020-12-15 18:35:18', 1, 0, NULL);
INSERT INTO tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`, load_time)
VALUES(57, 14, '更新廢棄物狀態', '', 'product:status', 'el-icon-setting', '1', 3, '2020-12-15 18:35:18', '2020-12-15 18:35:18', 1, 0, NULL);
INSERT INTO tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`, load_time)
VALUES(58, 14, '下載廢棄物', '', 'product:export', 'el-icon-setting', '1', 4, '2020-12-15 18:35:18', '2020-12-15 18:35:18', 1, 0, NULL);
INSERT INTO tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`, load_time)
VALUES(59, 14, '單價維護', '', 'product:price', 'el-icon-setting', '1', 5, '2020-12-15 18:35:18', '2020-12-15 18:35:18', 1, 0, NULL);
INSERT INTO tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`, load_time)
VALUES(60, 15, '新增秤重明細', '', 'weight:add', 'el-icon-setting', '1', 1, '2020-12-15 18:35:18', '2020-12-15 18:35:18', 1, 0, NULL);
INSERT INTO tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`, load_time)
VALUES(61, 15, '編辑秤重明細', '', 'weight:edit', 'el-icon-setting', '1', 2, '2020-12-15 18:35:18', '2020-12-15 18:35:18', 1, 0, NULL);
INSERT INTO tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`, load_time)
VALUES(63, 15, '更新秤重明細狀態', '', 'weight:status', 'el-icon-setting', '1', 3, '2020-12-15 18:35:18', '2020-12-15 18:35:18', 1, 0, NULL);
INSERT INTO tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`, load_time)
VALUES(64, 15, '下載秤重明細', '', 'weight:export', 'el-icon-setting', '1', 4, '2020-12-15 18:35:18', '2020-12-15 18:35:18', 1, 0, NULL);
INSERT INTO tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`, load_time)
VALUES(65, 17, '新增選單', '', 'menu:add', 'el-icon-setting', '1', 1, '2020-12-15 18:35:18', '2020-12-15 18:35:18', 1, 0, NULL);
INSERT INTO tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`, load_time)
VALUES(66, 17, '編辑選單', '', 'menu:edit', 'el-icon-setting', '1', 2, '2020-12-15 18:35:18', '2020-12-15 18:35:18', 1, 0, NULL);
INSERT INTO tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`, load_time)
VALUES(68, 17, '下載選單', '', 'menu:export', 'el-icon-setting', '1', 3, '2020-12-15 18:35:18', '2020-12-15 18:35:18', 1, 0, NULL);
INSERT INTO tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`, load_time)
VALUES(69, 18, '新增角色', '', 'role:add', 'el-icon-setting', '1', 1, '2020-12-15 18:35:18', '2020-12-15 18:35:18', 1, 0, NULL);
INSERT INTO tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`, load_time)
VALUES(70, 18, '編辑角色', '', 'role:edit', 'el-icon-setting', '1', 2, '2020-12-15 18:35:18', '2020-12-15 18:35:18', 1, 0, NULL);
INSERT INTO tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`, load_time)
VALUES(72, 18, '更新角色狀態', '', 'role:status', 'el-icon-setting', '1', 3, '2020-12-15 18:35:18', '2020-12-15 18:35:18', 1, 0, NULL);
INSERT INTO tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`, load_time)
VALUES(73, 18, '下載角色', '', 'role:export', 'el-icon-setting', '1', 4, '2020-12-15 18:35:18', '2020-12-15 18:35:18', 1, 0, NULL);
INSERT INTO tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`, load_time)
VALUES(74, 18, '角色授權', '', 'role:authority', 'el-icon-setting', '1', 5, '2020-12-15 18:35:18', '2020-12-15 18:35:18', 1, 0, NULL);
INSERT INTO tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`, load_time)
VALUES(75, 4, '資源回收統計', '/business/report/report2', '', 'el-icon-s-marketing', '0', 2, '2020-12-15 18:35:18', '2020-12-15 18:35:18', 1, 0, NULL);
INSERT INTO tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`, load_time)
VALUES(76, 4, '每月過磅統計表', '/business/report/report3', '', 'el-icon-s-marketing', '0', 3, '2020-12-15 18:35:18', '2020-12-15 18:35:18', 1, 0, NULL);

/*Table structure for table `tb_role` */

DROP TABLE IF EXISTS `tb_role`;

CREATE TABLE `tb_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `type` int(1) DEFAULT '1' COMMENT '類型,0:所有資料，1:限本帳號',
  `role_name` varchar(100) NOT NULL COMMENT '角色名稱',
  `remark` varchar(100) DEFAULT NULL COMMENT '角色描述',
  `create_time` datetime DEFAULT NULL COMMENT '創建時間',
  `modified_time` datetime DEFAULT NULL COMMENT '修改時間',
  `status` int(1) DEFAULT '1' COMMENT '是否可用,0:不可用，1:可用',
  `load_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='角色表';

/*Data for the table `tb_role` */

insert  into `tb_role`(`id`,`type`,`role_name`,`remark`,`create_time`,`modified_time`,`status`) values (145,0,'测试角色','用于测试的账号','2020-12-17 00:00:00','2020-12-17 20:33:46',1);

/*Table structure for table `tb_role_menu` */

DROP TABLE IF EXISTS `tb_role_menu`;

CREATE TABLE `tb_role_menu` (
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `menu_id` bigint(20) NOT NULL COMMENT '選單/按鈕ID',
  `load_time` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='角色選單关联表';

/*Data for the table `tb_role_menu` */

INSERT INTO tb_role_menu
(role_id, menu_id, load_time)
VALUES(2, 11, '2021-12-11 20:27:04');
INSERT INTO tb_role_menu
(role_id, menu_id, load_time)
VALUES(2, 36, '2021-12-11 20:27:04');
INSERT INTO tb_role_menu
(role_id, menu_id, load_time)
VALUES(2, 37, '2021-12-11 20:27:04');
INSERT INTO tb_role_menu
(role_id, menu_id, load_time)
VALUES(2, 39, '2021-12-11 20:27:04');
INSERT INTO tb_role_menu
(role_id, menu_id, load_time)
VALUES(2, 40, '2021-12-11 20:27:04');
INSERT INTO tb_role_menu
(role_id, menu_id, load_time)
VALUES(2, 3, '2021-12-11 20:27:04');
INSERT INTO tb_role_menu
(role_id, menu_id, load_time)
VALUES(2, 15, '2021-12-11 20:27:04');
INSERT INTO tb_role_menu
(role_id, menu_id, load_time)
VALUES(2, 4, '2021-12-11 20:27:04');
INSERT INTO tb_role_menu
(role_id, menu_id, load_time)
VALUES(2, 20, '2021-12-11 20:27:04');
INSERT INTO tb_role_menu
(role_id, menu_id, load_time)
VALUES(2, 6, '2021-12-11 20:27:04');
INSERT INTO tb_role_menu
(role_id, menu_id, load_time)
VALUES(2, 19, '2021-12-11 20:27:04');
INSERT INTO tb_role_menu
(role_id, menu_id, load_time)
VALUES(2, 1, '2021-12-11 20:27:04');
INSERT INTO tb_role_menu
(role_id, menu_id, load_time)
VALUES(2, 3, '2021-12-11 20:27:04');
INSERT INTO tb_role_menu
(role_id, menu_id, load_time)
VALUES(2, 15, '2021-12-11 20:27:04');
INSERT INTO tb_role_menu
(role_id, menu_id, load_time)
VALUES(3, 15, '2021-12-11 20:28:33');
INSERT INTO tb_role_menu
(role_id, menu_id, load_time)
VALUES(3, 60, '2021-12-11 20:28:33');
INSERT INTO tb_role_menu
(role_id, menu_id, load_time)
VALUES(3, 61, '2021-12-11 20:28:33');
INSERT INTO tb_role_menu
(role_id, menu_id, load_time)
VALUES(3, 63, '2021-12-11 20:28:33');
INSERT INTO tb_role_menu
(role_id, menu_id, load_time)
VALUES(3, 64, '2021-12-11 20:28:33');
INSERT INTO tb_role_menu
(role_id, menu_id, load_time)
VALUES(3, 3, '2021-12-11 20:28:33');
INSERT INTO tb_role_menu
(role_id, menu_id, load_time)
VALUES(4, 4, '2021-12-11 20:28:55');
INSERT INTO tb_role_menu
(role_id, menu_id, load_time)
VALUES(4, 20, '2021-12-11 20:28:55');
INSERT INTO tb_role_menu
(role_id, menu_id, load_time)
VALUES(5, 4, '2021-12-11 20:29:17');
INSERT INTO tb_role_menu
(role_id, menu_id, load_time)
VALUES(5, 20, '2021-12-11 20:29:17');
INSERT INTO tb_role_menu
(role_id, menu_id, load_time)
VALUES(6, 15, '2021-12-11 20:29:44');
INSERT INTO tb_role_menu
(role_id, menu_id, load_time)
VALUES(6, 60, '2021-12-11 20:29:44');
INSERT INTO tb_role_menu
(role_id, menu_id, load_time)
VALUES(6, 61, '2021-12-11 20:29:44');
INSERT INTO tb_role_menu
(role_id, menu_id, load_time)
VALUES(6, 63, '2021-12-11 20:29:44');
INSERT INTO tb_role_menu
(role_id, menu_id, load_time)
VALUES(6, 64, '2021-12-11 20:29:44');
INSERT INTO tb_role_menu
(role_id, menu_id, load_time)
VALUES(6, 4, '2021-12-11 20:29:44');
INSERT INTO tb_role_menu
(role_id, menu_id, load_time)
VALUES(6, 20, '2021-12-11 20:29:44');
INSERT INTO tb_role_menu
(role_id, menu_id, load_time)
VALUES(6, 3, '2021-12-11 20:29:44');

/*Table structure for table `tb_user_role` */

DROP TABLE IF EXISTS `tb_user_role`;

CREATE TABLE `tb_user_role` (
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `load_time` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户角色關聯表';

/*Data for the table `tb_user_role` */

/*Table structure for table `tb_log` */

DROP TABLE IF EXISTS `tb_log`;

CREATE TABLE `tb_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '日志ID',
  `username` varchar(50) DEFAULT NULL COMMENT '操作用户',
  `operation` text COMMENT '操作内容',
  `time` decimal(11,0) DEFAULT NULL COMMENT '耗时',
  `method` text COMMENT '操作方法',
  `params` text COMMENT '方法参数',
  `ip` varchar(64) DEFAULT NULL COMMENT '操作者IP',
  `create_time` datetime DEFAULT NULL COMMENT '創建時間',
  `location` varchar(50) DEFAULT NULL COMMENT '操作地点',
  `load_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='操作日志表';

/*Table structure for table `input_element` */

DROP TABLE IF EXISTS `input_element`;

CREATE TABLE `input_element` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '流水號',
  `item` varchar(100) NOT NULL COMMENT '項目',
  `status` int(1) DEFAULT '1' COMMENT '狀態(1:正常 0:停用)',
  `load_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='補登維表';

DROP TABLE IF EXISTS `input_data`;

CREATE TABLE `input_data` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '流水號',
  `item_id` varchar(100) NOT NULL COMMENT '補登項目代碼',
  `value` int(1) NOT NULL COMMENT '值',
  `valid_month` varchar(50) DEFAULT NULL COMMENT '適用月份',
  `load_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='補登資料';





