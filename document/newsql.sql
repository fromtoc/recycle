/*
SQLyog Ultimate v11.42 (64 bit)
MySQL - 5.7.28 : Database - recycle
*********************************************************************
*/
-- CREATE DATABASE /*!32312 IF NOT EXISTS*/`recycle` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `recycle`;

/*Table structure for table `biz_consumer` */

DROP TABLE IF EXISTS `biz_consumer`;

CREATE TABLE `biz_consumer` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL COMMENT '物资消费方',
  `address` varchar(20) DEFAULT NULL COMMENT '地址',
  `create_time` datetime DEFAULT NULL,
  `modified_time` datetime DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL COMMENT '联系电话',
  `sort` int(11) DEFAULT NULL,
  `contact` varchar(10) DEFAULT NULL COMMENT '联系人姓名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4;

/*Data for the table `biz_consumer` */

insert  into `biz_consumer`(`id`,`name`,`address`,`create_time`,`modified_time`,`phone`,`sort`,`contact`) values (7,'324234234','天津市/市辖区/和平区','2020-04-25 20:12:06','2020-05-10 09:21:37','15079437282',1,'老王'),(8,'43535345','江西省/九江市/共青城市','2020-04-25 20:12:43','2020-04-25 20:12:43','15079437282',2,'11'),(9,'3424243424','山西省/大同市/矿区','2020-04-25 20:13:20','2020-05-10 09:21:33','15079437282',1,'小李'),(11,'3424','江西省/抚州市/黎川县','2020-04-25 20:28:26','2020-05-10 09:21:47','15079437282',1,'44'),(13,'32424','天津市/市辖区/和平区','2020-04-25 21:38:42','2020-04-25 21:38:42','15079437282',2,'22'),(14,'34242','江西省/抚州市/黎川县','2020-04-25 21:38:59','2020-04-25 21:38:59','15079437282',3,'444'),(15,'5他','河北省/石家庄市/长安区','2020-04-25 21:39:25','2020-04-25 21:39:25','15079437282',2,'555'),(16,'3243242','山东省/青岛市/历下区','2020-04-25 21:40:43','2020-04-25 21:40:43','15079437282',1,'555'),(17,'一栋705宿舍','江西省/九江市/共青城市','2020-04-25 21:56:04','2020-04-25 21:56:04','15079437282',3,'章宇康'),(18,'反反复复','内蒙古自治区/赤峰市/阿鲁科尔沁旗','2020-04-25 22:05:47','2020-04-25 22:05:47','15079437282',2,'反反复复'),(19,'test224444','江西省/九江市/共青城市','2020-04-26 09:47:37','2020-04-26 09:47:50','15079437282',3,'test'),(20,'324234','天津市/市辖区/和平区','2020-05-25 11:00:23','2020-05-25 11:00:23','15079437282',1,'32424'),(21,'test111','福建省/莆田市/秀屿区','2020-05-25 11:01:18','2020-05-25 11:01:55','15079437282',2,'testman'),(22,'6666666','天津市/市辖区/和平区','2020-05-25 11:16:12','2020-05-25 11:16:12','15079437282',1,'23423424'),(23,'6666666','天津市/市辖区/和平区','2020-05-25 11:17:15','2020-05-25 11:17:15','15079437282',1,'23423424'),(24,'hemei','河北省/唐山市/古冶区','2020-05-25 11:27:42','2020-05-25 11:27:42','15079437282',1,'hemei'),(25,'hemei','河北省/唐山市/古冶区','2020-05-25 11:32:39','2020-05-25 11:32:39','15079437282',1,'hemei'),(26,'武汉汉口校医院','天津市/市辖区/和平区','2020-05-25 15:46:07','2020-05-25 15:46:07','15079437282',1,'李大牛');

/*Table structure for table `biz_health` */

DROP TABLE IF EXISTS `biz_health`;

CREATE TABLE `biz_health` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address` varchar(50) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `situation` int(1) NOT NULL,
  `touch` int(1) NOT NULL,
  `passby` int(1) NOT NULL,
  `reception` int(1) NOT NULL,
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4;

/*Data for the table `biz_health` */

insert  into `biz_health`(`id`,`address`,`user_id`,`situation`,`touch`,`passby`,`reception`,`create_time`) values (24,'湖北省/武汉市/汉南区',5,0,0,0,0,'2020-05-07 15:23:40'),(25,'河北省/秦皇岛市/北戴河区',5,0,1,1,1,'2020-05-10 11:26:27'),(28,'天津市/市辖区/和平区',5,0,1,1,1,'2020-05-13 22:43:09'),(29,'天津市/市辖区/南開区',5,0,1,1,1,'2020-05-14 20:10:12'),(30,'天津市/市辖区/和平区',5,1,0,0,0,'2020-05-14 20:23:07'),(31,'天津市/市辖区/和平区',184,2,1,1,1,'2020-05-14 21:06:47'),(35,'天津市/市辖区/和平区',5,0,1,1,1,'2020-05-18 09:33:33');

/*Table structure for table `biz_in_stock` */

DROP TABLE IF EXISTS `biz_in_stock`;

CREATE TABLE `biz_in_stock` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `in_num` varchar(36) DEFAULT NULL COMMENT '入库单编号',
  `type` int(2) DEFAULT NULL COMMENT '類型：1：捐赠，2：下拨，3：采购,4:退货入库',
  `operator` varchar(20) DEFAULT NULL COMMENT '操作人员',
  `create_time` datetime DEFAULT NULL COMMENT '入库单創建時間',
  `modified` datetime DEFAULT NULL COMMENT '入库单修改時間',
  `product_number` int(11) DEFAULT NULL COMMENT '物资总数',
  `supplier_id` bigint(20) DEFAULT NULL COMMENT '来源',
  `remark` varchar(100) DEFAULT NULL COMMENT '描述信息',
  `status` int(1) DEFAULT '2' COMMENT '0:正常入库单,1:已进入回收,2:等待审核',
  PRIMARY KEY (`id`),
  KEY `operator_id` (`operator`),
  KEY `supplier_id` (`supplier_id`)
) ENGINE=InnoDB AUTO_INCREMENT=111 DEFAULT CHARSET=utf8;

/*Data for the table `biz_in_stock` */

insert  into `biz_in_stock`(`id`,`in_num`,`type`,`operator`,`create_time`,`modified`,`product_number`,`supplier_id`,`remark`,`status`) values (84,'a2ea9b97-ad12-4d85-a9b4-1a644d86',2,'zhangyukang','2020-05-09 20:01:48','2020-05-09 20:01:48',13,15,'333333',0),(85,'78473f1a-405a-4f5e-b08c-42a7b2e8',2,'zhangyukang','2020-05-10 09:27:45','2020-05-10 09:27:45',48,14,'324324242424',0),(86,'31ff3701-c44f-4b98-91e0-1d41d8b2',1,'zhangyukang','2020-05-11 09:59:30','2020-05-11 09:59:30',51,15,'32424',0),(87,'38ed0881-20ed-4cec-a321-eb8f4c3b',1,'zhangyukang','2020-05-11 10:04:05','2020-05-11 10:04:05',30,15,'232323232',0),(90,'db1c2afe-8117-43fd-adf7-866ef7e4',1,'系统测试人员','2020-05-18 10:11:11','2020-05-16 17:37:12',4,15,'2222222',0),(91,'df793f2b-ea13-4d93-b22c-60454f32',2,'系统测试人员','2020-05-18 10:11:09','2020-05-18 10:11:00',25,15,'2342424',0),(92,'c2054c39-a88b-4f47-9f9e-5c57f9e6',1,'系统测试人员','2020-05-18 10:42:04','2020-05-18 10:41:55',3,15,'222222',0),(93,'08a51486-49e9-402e-a10e-3e6a45df',1,'系统测试人员','2020-05-18 11:01:16','2020-05-18 11:01:09',4,15,'2222222',0),(99,'bb5de246-bd56-4987-b027-8fbcf3c3',2,'系统测试人员','2020-05-18 12:21:41','2020-05-18 11:43:49',6,19,'33333',0),(100,'7657f747-ab27-49d7-b1ce-d6d47ecf',1,'系统测试人员','2020-05-18 12:21:40','2020-05-18 12:21:29',2,20,'23432424',1),(101,'03fbc3b3-e28b-418c-a457-87c376c3',1,'系统测试人员','2020-05-18 13:16:38','2020-05-18 13:16:28',12,21,'454545454545',0),(102,'d83621b8-b5c7-4499-a8a0-56af2849',1,'系统测试人员','2020-05-18 13:18:51','2020-05-18 13:18:41',18,17,'33333',0),(103,'51fa7a04-535f-43b5-8972-23d0e55a',1,'系统测试人员','2020-05-18 13:42:41','2020-05-18 13:42:29',6,22,'222222',1),(104,'c96eaa3e-22ee-4f6b-98bd-87d34372',1,'zhangyukang','2020-05-24 21:46:16','2020-05-24 21:46:06',6,15,'432424',1),(105,'5ad278ed-ce30-4f0d-bb67-7f9070fc',1,'zhangyukang','2020-05-25 11:45:10','2020-05-25 10:27:06',6,15,'32432424',0),(106,'efeef1d2-b8c9-4eb5-8ea2-b0695fb9',1,'zhangyukang','2020-05-26 09:35:23','2020-05-26 09:35:17',10,17,'2342424',0),(107,'4d7d332469eb4f2987cbc38fea2b',1,'admin','2020-12-17 19:27:30','2020-12-17 19:26:39',1,14,'2313123',0),(108,'69edf117b8dd478abc37d33bca41',2,'admin','2020-12-17 19:30:44','2020-12-17 19:28:52',6,15,'231313',0),(109,'5e69ad91b41a439b8b4a809e84b4',4,'admin','2020-12-17 19:30:42','2020-12-17 19:30:28',9,14,'232323',0),(110,'532f9951e1d54f4f98263002bea6',1,'admin','2020-12-17 21:00:57','2020-12-17 21:00:50',2,15,'33333',0);

/*Table structure for table `biz_in_stock_info` */

DROP TABLE IF EXISTS `biz_in_stock_info`;

CREATE TABLE `biz_in_stock_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `in_num` varchar(36) DEFAULT NULL COMMENT '入库单编号',
  `p_num` varchar(36) DEFAULT NULL COMMENT '商品编号',
  `product_number` int(11) DEFAULT NULL COMMENT '数量',
  `create_time` datetime DEFAULT NULL,
  `modified_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=375 DEFAULT CHARSET=utf8;

/*Data for the table `biz_in_stock_info` */

insert  into `biz_in_stock_info`(`id`,`in_num`,`p_num`,`product_number`,`create_time`,`modified_time`) values (282,'a2ea9b97-ad12-4d85-a9b4-1a644d86','3DFC8EA0-6',2,'2020-05-09 20:01:49','2020-05-09 20:01:49'),(283,'a2ea9b97-ad12-4d85-a9b4-1a644d86','2C15F1B6-1',2,'2020-05-09 20:01:49','2020-05-09 20:01:49'),(284,'a2ea9b97-ad12-4d85-a9b4-1a644d86','6976D3B4-A',3,'2020-05-09 20:01:49','2020-05-09 20:01:49'),(285,'a2ea9b97-ad12-4d85-a9b4-1a644d86','f248ee7a-c30c-447c-ae9c-0a1c09e9',3,'2020-05-09 20:01:49','2020-05-09 20:01:49'),(286,'a2ea9b97-ad12-4d85-a9b4-1a644d86','beb944c4-01ae-497b-bfdd-2132032f',3,'2020-05-09 20:01:49','2020-05-09 20:01:49'),(287,'78473f1a-405a-4f5e-b08c-42a7b2e8','3DFC8EA0-6',2,'2020-05-10 09:27:45','2020-05-10 09:27:45'),(288,'78473f1a-405a-4f5e-b08c-42a7b2e8','2C15F1B6-1',1,'2020-05-10 09:27:45','2020-05-10 09:27:45'),(289,'78473f1a-405a-4f5e-b08c-42a7b2e8','6976D3B4-A',2,'2020-05-10 09:27:45','2020-05-10 09:27:45'),(290,'78473f1a-405a-4f5e-b08c-42a7b2e8','f248ee7a-c30c-447c-ae9c-0a1c09e9',2,'2020-05-10 09:27:45','2020-05-10 09:27:45'),(291,'78473f1a-405a-4f5e-b08c-42a7b2e8','beb944c4-01ae-497b-bfdd-2132032f',3,'2020-05-10 09:27:45','2020-05-10 09:27:45'),(292,'78473f1a-405a-4f5e-b08c-42a7b2e8','24573d5b-0c9b-403b-9a88-c214702e',3,'2020-05-10 09:27:45','2020-05-10 09:27:45'),(293,'78473f1a-405a-4f5e-b08c-42a7b2e8','c98183c8-bc47-4505-abbb-1dc219b5',2,'2020-05-10 09:27:45','2020-05-10 09:27:45'),(294,'78473f1a-405a-4f5e-b08c-42a7b2e8','de16b9e6-bb49-4547-ab91-db7ae7b6',3,'2020-05-10 09:27:45','2020-05-10 09:27:45'),(295,'78473f1a-405a-4f5e-b08c-42a7b2e8','6AF405A1-C',10,'2020-05-10 09:27:45','2020-05-10 09:27:45'),(296,'78473f1a-405a-4f5e-b08c-42a7b2e8','0b9e9176-f996-4384-bb6c-209f55d0',10,'2020-05-10 09:27:45','2020-05-10 09:27:45'),(297,'78473f1a-405a-4f5e-b08c-42a7b2e8','894b8218-36ee-4a0d-9ad1-d9c5e455',10,'2020-05-10 09:27:45','2020-05-10 09:27:45'),(298,'31ff3701-c44f-4b98-91e0-1d41d8b2','3DFC8EA0-6',10,'2020-05-11 09:59:31','2020-05-11 09:59:31'),(299,'31ff3701-c44f-4b98-91e0-1d41d8b2','2C15F1B6-1',10,'2020-05-11 09:59:32','2020-05-11 09:59:32'),(300,'31ff3701-c44f-4b98-91e0-1d41d8b2','15bc064e-991d-40e3-bcd6-f6aff0e3',10,'2020-05-11 09:59:32','2020-05-11 09:59:32'),(301,'31ff3701-c44f-4b98-91e0-1d41d8b2','f248ee7a-c30c-447c-ae9c-0a1c09e9',10,'2020-05-11 09:59:32','2020-05-11 09:59:32'),(302,'31ff3701-c44f-4b98-91e0-1d41d8b2','beb944c4-01ae-497b-bfdd-2132032f',10,'2020-05-11 09:59:32','2020-05-11 09:59:32'),(303,'31ff3701-c44f-4b98-91e0-1d41d8b2','0e17f606-9aaa-48a7-b4e9-ef18462e',1,'2020-05-11 09:59:32','2020-05-11 09:59:32'),(304,'38ed0881-20ed-4cec-a321-eb8f4c3b','3DFC8EA0-6',10,'2020-05-11 10:04:05','2020-05-11 10:04:05'),(305,'38ed0881-20ed-4cec-a321-eb8f4c3b','24573d5b-0c9b-403b-9a88-c214702e',10,'2020-05-11 10:04:06','2020-05-11 10:04:06'),(306,'38ed0881-20ed-4cec-a321-eb8f4c3b','6EF5F2C0-9',10,'2020-05-11 10:04:06','2020-05-11 10:04:06'),(315,'db1c2afe-8117-43fd-adf7-866ef7e4','3DFC8EA0-6',2,'2020-05-16 17:37:12','2020-05-16 17:37:12'),(316,'db1c2afe-8117-43fd-adf7-866ef7e4','2C15F1B6-1',2,'2020-05-16 17:37:13','2020-05-16 17:37:13'),(317,'df793f2b-ea13-4d93-b22c-60454f32','3DFC8EA0-6',3,'2020-05-18 10:11:00','2020-05-18 10:11:00'),(318,'df793f2b-ea13-4d93-b22c-60454f32','2C15F1B6-1',3,'2020-05-18 10:11:00','2020-05-18 10:11:00'),(319,'df793f2b-ea13-4d93-b22c-60454f32','15bc064e-991d-40e3-bcd6-f6aff0e3',3,'2020-05-18 10:11:00','2020-05-18 10:11:00'),(320,'df793f2b-ea13-4d93-b22c-60454f32','6976D3B4-A',3,'2020-05-18 10:11:00','2020-05-18 10:11:00'),(321,'df793f2b-ea13-4d93-b22c-60454f32','f248ee7a-c30c-447c-ae9c-0a1c09e9',10,'2020-05-18 10:11:00','2020-05-18 10:11:00'),(322,'df793f2b-ea13-4d93-b22c-60454f32','beb944c4-01ae-497b-bfdd-2132032f',3,'2020-05-18 10:11:00','2020-05-18 10:11:00'),(323,'c2054c39-a88b-4f47-9f9e-5c57f9e6','3DFC8EA0-6',3,'2020-05-18 10:41:55','2020-05-18 10:41:55'),(324,'08a51486-49e9-402e-a10e-3e6a45df','3DFC8EA0-6',2,'2020-05-18 11:01:09','2020-05-18 11:01:09'),(325,'08a51486-49e9-402e-a10e-3e6a45df','2C15F1B6-1',2,'2020-05-18 11:01:09','2020-05-18 11:01:09'),(335,'bb5de246-bd56-4987-b027-8fbcf3c3','2C15F1B6-1',3,'2020-05-18 11:43:50','2020-05-18 11:43:50'),(336,'bb5de246-bd56-4987-b027-8fbcf3c3','15bc064e-991d-40e3-bcd6-f6aff0e3',3,'2020-05-18 11:43:50','2020-05-18 11:43:50'),(337,'7657f747-ab27-49d7-b1ce-d6d47ecf','3DFC8EA0-6',1,'2020-05-18 12:21:29','2020-05-18 12:21:29'),(338,'7657f747-ab27-49d7-b1ce-d6d47ecf','2C15F1B6-1',1,'2020-05-18 12:21:29','2020-05-18 12:21:29'),(339,'03fbc3b3-e28b-418c-a457-87c376c3','3DFC8EA0-6',4,'2020-05-18 13:16:28','2020-05-18 13:16:28'),(340,'03fbc3b3-e28b-418c-a457-87c376c3','2C15F1B6-1',4,'2020-05-18 13:16:28','2020-05-18 13:16:28'),(341,'03fbc3b3-e28b-418c-a457-87c376c3','15bc064e-991d-40e3-bcd6-f6aff0e3',4,'2020-05-18 13:16:28','2020-05-18 13:16:28'),(342,'d83621b8-b5c7-4499-a8a0-56af2849','3DFC8EA0-6',3,'2020-05-18 13:18:41','2020-05-18 13:18:41'),(343,'d83621b8-b5c7-4499-a8a0-56af2849','2C15F1B6-1',3,'2020-05-18 13:18:42','2020-05-18 13:18:42'),(344,'d83621b8-b5c7-4499-a8a0-56af2849','15bc064e-991d-40e3-bcd6-f6aff0e3',3,'2020-05-18 13:18:42','2020-05-18 13:18:42'),(345,'d83621b8-b5c7-4499-a8a0-56af2849','6976D3B4-A',3,'2020-05-18 13:18:42','2020-05-18 13:18:42'),(346,'d83621b8-b5c7-4499-a8a0-56af2849','f248ee7a-c30c-447c-ae9c-0a1c09e9',3,'2020-05-18 13:18:42','2020-05-18 13:18:42'),(347,'d83621b8-b5c7-4499-a8a0-56af2849','beb944c4-01ae-497b-bfdd-2132032f',3,'2020-05-18 13:18:42','2020-05-18 13:18:42'),(348,'51fa7a04-535f-43b5-8972-23d0e55a','3DFC8EA0-6',2,'2020-05-18 13:42:29','2020-05-18 13:42:29'),(349,'51fa7a04-535f-43b5-8972-23d0e55a','2C15F1B6-1',2,'2020-05-18 13:42:29','2020-05-18 13:42:29'),(350,'51fa7a04-535f-43b5-8972-23d0e55a','15bc064e-991d-40e3-bcd6-f6aff0e3',2,'2020-05-18 13:42:29','2020-05-18 13:42:29'),(351,'c96eaa3e-22ee-4f6b-98bd-87d34372','3DFC8EA0-6',3,'2020-05-24 21:46:06','2020-05-24 21:46:06'),(352,'c96eaa3e-22ee-4f6b-98bd-87d34372','2C15F1B6-1',3,'2020-05-24 21:46:06','2020-05-24 21:46:06'),(353,'5ad278ed-ce30-4f0d-bb67-7f9070fc','3DFC8EA0-6',1,'2020-05-25 10:27:07','2020-05-25 10:27:07'),(354,'5ad278ed-ce30-4f0d-bb67-7f9070fc','2C15F1B6-1',1,'2020-05-25 10:27:07','2020-05-25 10:27:07'),(355,'5ad278ed-ce30-4f0d-bb67-7f9070fc','15bc064e-991d-40e3-bcd6-f6aff0e3',1,'2020-05-25 10:27:07','2020-05-25 10:27:07'),(356,'5ad278ed-ce30-4f0d-bb67-7f9070fc','6976D3B4-A',1,'2020-05-25 10:27:07','2020-05-25 10:27:07'),(357,'5ad278ed-ce30-4f0d-bb67-7f9070fc','f248ee7a-c30c-447c-ae9c-0a1c09e9',1,'2020-05-25 10:27:07','2020-05-25 10:27:07'),(358,'5ad278ed-ce30-4f0d-bb67-7f9070fc','beb944c4-01ae-497b-bfdd-2132032f',1,'2020-05-25 10:27:07','2020-05-25 10:27:07'),(359,'efeef1d2-b8c9-4eb5-8ea2-b0695fb9','6EF5F2C0-9',10,'2020-05-26 09:35:17','2020-05-26 09:35:17'),(360,'4d7d332469eb4f2987cbc38fea2b','3DFC8EA0-6',1,'2020-12-17 19:26:39','2020-12-17 19:26:39'),(361,'69edf117b8dd478abc37d33bca41','3DFC8EA0-6',1,'2020-12-17 19:28:52','2020-12-17 19:28:52'),(362,'69edf117b8dd478abc37d33bca41','2C15F1B6-1',1,'2020-12-17 19:28:52','2020-12-17 19:28:52'),(363,'69edf117b8dd478abc37d33bca41','15bc064e-991d-40e3-bcd6-f6aff0e3',1,'2020-12-17 19:28:52','2020-12-17 19:28:52'),(364,'69edf117b8dd478abc37d33bca41','6976D3B4-A',1,'2020-12-17 19:28:52','2020-12-17 19:28:52'),(365,'69edf117b8dd478abc37d33bca41','f248ee7a-c30c-447c-ae9c-0a1c09e9',1,'2020-12-17 19:28:52','2020-12-17 19:28:52'),(366,'69edf117b8dd478abc37d33bca41','beb944c4-01ae-497b-bfdd-2132032f',1,'2020-12-17 19:28:52','2020-12-17 19:28:52'),(367,'5e69ad91b41a439b8b4a809e84b4','3DFC8EA0-6',1,'2020-12-17 19:30:28','2020-12-17 19:30:28'),(368,'5e69ad91b41a439b8b4a809e84b4','2C15F1B6-1',1,'2020-12-17 19:30:28','2020-12-17 19:30:28'),(369,'5e69ad91b41a439b8b4a809e84b4','15bc064e-991d-40e3-bcd6-f6aff0e3',1,'2020-12-17 19:30:28','2020-12-17 19:30:28'),(370,'5e69ad91b41a439b8b4a809e84b4','6976D3B4-A',2,'2020-12-17 19:30:28','2020-12-17 19:30:28'),(371,'5e69ad91b41a439b8b4a809e84b4','f248ee7a-c30c-447c-ae9c-0a1c09e9',2,'2020-12-17 19:30:28','2020-12-17 19:30:28'),(372,'5e69ad91b41a439b8b4a809e84b4','beb944c4-01ae-497b-bfdd-2132032f',2,'2020-12-17 19:30:28','2020-12-17 19:30:28'),(373,'532f9951e1d54f4f98263002bea6','2C15F1B6-1',1,'2020-12-17 21:00:50','2020-12-17 21:00:50'),(374,'532f9951e1d54f4f98263002bea6','15bc064e-991d-40e3-bcd6-f6aff0e3',1,'2020-12-17 21:00:50','2020-12-17 21:00:50');

/*Table structure for table `biz_out_stock` */

DROP TABLE IF EXISTS `biz_out_stock`;

CREATE TABLE `biz_out_stock` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `out_num` varchar(36) NOT NULL COMMENT '出库单',
  `type` int(1) NOT NULL COMMENT '出库類型:0:直接出库,1:审核出库',
  `operator` varchar(20) DEFAULT NULL COMMENT '操作人',
  `create_time` datetime DEFAULT NULL COMMENT '出库时间',
  `product_number` int(11) DEFAULT NULL COMMENT '出库总数',
  `consumer_id` bigint(20) NOT NULL COMMENT '消费者id',
  `remark` varchar(50) DEFAULT NULL COMMENT '备注',
  `status` int(1) DEFAULT NULL COMMENT '状态:0:正常入库,1:已进入回收,2:等待审核',
  `priority` int(1) NOT NULL COMMENT '紧急程度:1:不急,2:常规,3:紧急4:特急',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4;

/*Data for the table `biz_out_stock` */

insert  into `biz_out_stock`(`id`,`out_num`,`type`,`operator`,`create_time`,`product_number`,`consumer_id`,`remark`,`status`,`priority`) values (1,'fdasfsfsaf',1,'1','2020-05-10 14:42:04',111,7,'1',0,1),(2,'11fdsafsafasf',0,'324','2020-05-10 15:16:03',3,8,'3',0,4),(4,'8d468e4b-0c45-4807-9e0e-6c2c65d6',0,'zhangyukang','2020-05-26 09:38:02',7,25,'2342424',0,1),(5,'2d7158d3-2b15-473e-8cbe-6f6bd45a',0,'zhangyukang','2020-05-26 09:37:49',10,24,'4545454545',0,4),(6,'bd5032d0-c84a-4413-a72d-ba80d33f',0,'zhangyukang','2020-05-25 12:08:07',93,9,'23424234324',2,1),(7,'c23e95d0-0607-4e00-9041-cc97d299',0,'zhangyukang','2020-05-25 15:46:07',6,26,'testtest',0,2),(9,'982e449e-1ab0-4456-8e9a-5403a685',0,'zhangyukang','2020-05-26 09:32:43',30,9,'3434343',0,1),(10,'622e8b6d-924a-4dfe-bc51-2143cf3c',0,'zhangyukang','2020-05-26 09:34:30',13,8,'23232323',0,1),(11,'55e4942f-3c0a-4bc2-85f7-2f2a7bff',0,'zhangyukang','2020-12-15 18:12:44',2,9,'34243424',1,3),(12,'453544fd-53ec-430f-9899-73f2edf5',0,'zhangyukang','2020-05-26 10:00:04',4,8,'11111111',0,1),(13,'4d7e5874-7d9c-4fca-ac08-ee4f4975',3,'zhangyukang','2020-05-26 10:02:11',3,8,'123213',0,1);

/*Table structure for table `biz_out_stock_info` */

DROP TABLE IF EXISTS `biz_out_stock_info`;

CREATE TABLE `biz_out_stock_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `out_num` varchar(36) DEFAULT NULL,
  `p_num` varchar(36) DEFAULT NULL,
  `product_number` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `modified_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4;

/*Data for the table `biz_out_stock_info` */

insert  into `biz_out_stock_info`(`id`,`out_num`,`p_num`,`product_number`,`create_time`,`modified_time`) values (1,'8d468e4b-0c45-4807-9e0e-6c2c65d6','3DFC8EA0-6',1,'2020-05-25 11:32:39','2020-05-25 11:32:39'),(2,'8d468e4b-0c45-4807-9e0e-6c2c65d6','6EF5F2C0-9',2,'2020-05-25 11:32:39','2020-05-25 11:32:39'),(3,'8d468e4b-0c45-4807-9e0e-6c2c65d6','6976D3B4-A',4,'2020-05-25 11:32:39','2020-05-25 11:32:39'),(4,'2d7158d3-2b15-473e-8cbe-6f6bd45a','3DFC8EA0-6',1,'2020-05-25 11:41:02','2020-05-25 11:41:02'),(5,'2d7158d3-2b15-473e-8cbe-6f6bd45a','6EF5F2C0-9',1,'2020-05-25 11:41:02','2020-05-25 11:41:02'),(6,'2d7158d3-2b15-473e-8cbe-6f6bd45a','6976D3B4-A',1,'2020-05-25 11:41:02','2020-05-25 11:41:02'),(7,'2d7158d3-2b15-473e-8cbe-6f6bd45a','AB0E206E-A',3,'2020-05-25 11:41:02','2020-05-25 11:41:02'),(8,'2d7158d3-2b15-473e-8cbe-6f6bd45a','6AF405A1-C',1,'2020-05-25 11:41:02','2020-05-25 11:41:02'),(9,'2d7158d3-2b15-473e-8cbe-6f6bd45a','2C15F1B6-1',3,'2020-05-25 11:41:02','2020-05-25 11:41:02'),(10,'bd5032d0-c84a-4413-a72d-ba80d33f','3DFC8EA0-6',80,'2020-05-25 12:08:07','2020-05-25 12:08:07'),(11,'bd5032d0-c84a-4413-a72d-ba80d33f','6EF5F2C0-9',13,'2020-05-25 12:08:07','2020-05-25 12:08:07'),(12,'c23e95d0-0607-4e00-9041-cc97d299','3DFC8EA0-6',1,'2020-05-25 15:46:07','2020-05-25 15:46:07'),(13,'c23e95d0-0607-4e00-9041-cc97d299','6EF5F2C0-9',1,'2020-05-25 15:46:07','2020-05-25 15:46:07'),(14,'c23e95d0-0607-4e00-9041-cc97d299','6976D3B4-A',1,'2020-05-25 15:46:07','2020-05-25 15:46:07'),(15,'c23e95d0-0607-4e00-9041-cc97d299','AB0E206E-A',1,'2020-05-25 15:46:07','2020-05-25 15:46:07'),(16,'c23e95d0-0607-4e00-9041-cc97d299','6AF405A1-C',1,'2020-05-25 15:46:07','2020-05-25 15:46:07'),(17,'c23e95d0-0607-4e00-9041-cc97d299','2C15F1B6-1',1,'2020-05-25 15:46:07','2020-05-25 15:46:07'),(21,'982e449e-1ab0-4456-8e9a-5403a685','3DFC8EA0-6',30,'2020-05-26 09:32:25','2020-05-26 09:32:25'),(22,'622e8b6d-924a-4dfe-bc51-2143cf3c','6EF5F2C0-9',13,'2020-05-26 09:34:23','2020-05-26 09:34:23'),(23,'55e4942f-3c0a-4bc2-85f7-2f2a7bff','3DFC8EA0-6',1,'2020-05-26 09:52:09','2020-05-26 09:52:09'),(24,'55e4942f-3c0a-4bc2-85f7-2f2a7bff','6EF5F2C0-9',1,'2020-05-26 09:52:09','2020-05-26 09:52:09'),(25,'453544fd-53ec-430f-9899-73f2edf5','6AF405A1-C',1,'2020-05-26 09:59:57','2020-05-26 09:59:57'),(26,'453544fd-53ec-430f-9899-73f2edf5','2C15F1B6-1',1,'2020-05-26 09:59:57','2020-05-26 09:59:57'),(27,'453544fd-53ec-430f-9899-73f2edf5','6976D3B4-A',1,'2020-05-26 09:59:57','2020-05-26 09:59:57'),(28,'453544fd-53ec-430f-9899-73f2edf5','6EF5F2C0-9',1,'2020-05-26 09:59:57','2020-05-26 09:59:57'),(29,'4d7e5874-7d9c-4fca-ac08-ee4f4975','3DFC8EA0-6',1,'2020-05-26 10:02:01','2020-05-26 10:02:01'),(30,'4d7e5874-7d9c-4fca-ac08-ee4f4975','6EF5F2C0-9',1,'2020-05-26 10:02:03','2020-05-26 10:02:03'),(31,'4d7e5874-7d9c-4fca-ac08-ee4f4975','6976D3B4-A',1,'2020-05-26 10:02:03','2020-05-26 10:02:03');


/*Table structure for table `biz_product_stock` */

DROP TABLE IF EXISTS `biz_product_stock`;

CREATE TABLE `biz_product_stock` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `p_num` varchar(32) NOT NULL,
  `stock` bigint(20) DEFAULT NULL COMMENT '商品库存结余',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8;

/*Data for the table `biz_product_stock` */

insert  into `biz_product_stock`(`id`,`p_num`,`stock`) values (43,'3DFC8EA0-6',49),(44,'2C15F1B6-1',67),(45,'6976D3B4-A',40),(46,'967CE098-3',4),(47,'79f01380-43c7-4506-9e0c-e2640288',5),(48,'f248ee7a-c30c-447c-ae9c-0a1c09e9',52),(49,'6AF405A1-C',18),(50,'0b9e9176-f996-4384-bb6c-209f55d0',16),(51,'894b8218-36ee-4a0d-9ad1-d9c5e455',14),(52,'beb944c4-01ae-497b-bfdd-2132032f',34),(53,'cf5a5f37-b299-4d96-bcb2-c4a46737',5),(54,'3fa0d5c1-4922-4078-8c7c-8d1cfeb5',2),(55,'0e17f606-9aaa-48a7-b4e9-ef18462e',2),(56,'d9155553-fe5c-4d35-bb9a-1e1ab8d4',2),(57,'6EF5F2C0-9',4),(58,'AB0E206E-A',6),(59,'24573d5b-0c9b-403b-9a88-c214702e',31),(60,'c98183c8-bc47-4505-abbb-1dc219b5',7),(61,'de16b9e6-bb49-4547-ab91-db7ae7b6',9),(62,'15bc064e-991d-40e3-bcd6-f6aff0e3',31);

/*Table structure for table `biz_supplier` */

DROP TABLE IF EXISTS `biz_supplier`;

CREATE TABLE `biz_supplier` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '供应商名稱',
  `address` varchar(255) DEFAULT NULL COMMENT '供应商地址',
  `email` varchar(255) DEFAULT NULL COMMENT '供应商邮箱',
  `phone` varchar(255) DEFAULT NULL COMMENT '供应商电话',
  `create_time` datetime DEFAULT NULL,
  `modified_time` datetime DEFAULT NULL,
  `sort` int(10) DEFAULT NULL COMMENT '排序',
  `contact` varchar(20) DEFAULT NULL COMMENT '联系人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

/*Data for the table `biz_supplier` */

insert  into `biz_supplier`(`id`,`name`,`address`,`email`,`phone`,`create_time`,`modified_time`,`sort`,`contact`) values (14,'324234','河北省/秦皇岛市/长安区','424324@qq.com','15079437282','2020-04-26 10:00:05','2020-04-26 10:00:05',2,'324324'),(15,'大街','河北省/邯郸市/峰峰矿区','3424324@qq.com','15079437282','2020-05-09 19:37:46','2020-05-10 11:25:01',1,'324324'),(16,'2342424','内蒙古自治区/鄂尔多斯市/鄂托克前旗','432423424@qq.com','15079437282','2020-05-09 19:42:07','2020-05-09 19:42:07',1,'3242423'),(17,'北京人民大会堂','北京市/市辖区/朝阳区','xiong@qq.com','15079437282','2020-05-18 11:38:04','2020-05-18 11:38:04',1,'熊老板'),(18,'xixiixxxxi','河北省/秦皇岛市/北戴河区','chrome@qq.com','15079437282','2020-05-18 11:41:09','2020-05-18 11:41:09',1,'perterchro'),(19,'xixiixxxxi','河北省/秦皇岛市/北戴河区','chrome@qq.com','15079437282','2020-05-18 11:43:49','2020-05-18 11:43:49',1,'perterchro'),(20,'aloooodf','山西省/晋城市/陵川县','justiner@qq.com','15245745454','2020-05-18 12:21:29','2020-05-18 12:21:29',1,'justiner'),(21,'HHH','河北省/秦皇岛市/长安区','HHH@qq.com','15079437282','2020-05-18 13:16:28','2020-05-18 13:16:28',1,'HHH'),(22,'PuPuPu34','山西省/阳泉市/平定县','124545454@qq.com','15254541241','2020-05-18 13:42:29','2020-05-18 13:43:05',1,'封小新');



/*Table structure for table `tb_image` */

DROP TABLE IF EXISTS `tb_image`;

CREATE TABLE `tb_image` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `path` varchar(1023) DEFAULT NULL COMMENT '图片路径',
  `size` bigint(20) DEFAULT NULL COMMENT '图片大小',
  `media_type` varchar(20) DEFAULT NULL COMMENT '图片類型',
  `suffix` varchar(50) DEFAULT NULL COMMENT '图片后缀',
  `height` int(20) DEFAULT NULL COMMENT '图片高度',
  `width` int(20) DEFAULT NULL COMMENT '图片宽度',
  `create_time` datetime DEFAULT NULL COMMENT '創建時間',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4;

/*Data for the table `tb_image` */

/*Table structure for table `tb_login_log` */

DROP TABLE IF EXISTS `tb_login_log`;

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

DROP TABLE IF EXISTS `tb_dictionary`;

CREATE TABLE `tb_dictionary` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type` int(2) NOT NULL COMMENT '類型：1：公司類型，2：成本中心，3：區域',
  `code` varchar(20) NOT NULL,
  `value` varchar(100) NOT NULL,
  `status` int(2) NOT NULL COMMENT '狀態：0：刪除，1：存在',
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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=83 DEFAULT CHARSET=utf8;


/*Table structure for table `tb_department` */

DROP TABLE IF EXISTS `tb_department`;

CREATE TABLE `tb_department` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type_code` bigint(20) DEFAULT NULL COMMENT '公司類型id',
  `type_name` varchar(100) DEFAULT NULL COMMENT '公司類型名稱',
  `number` bigint(20) DEFAULT NULL COMMENT '流水編號',
  `type_number` varchar(100) DEFAULT NULL COMMENT '公司帳號',
  `name` varchar(100) DEFAULT NULL COMMENT '公司名稱',
  `nickname` varchar(100) DEFAULT NULL COMMENT '公司簡稱',
  `region_id` bigint(20) DEFAULT NULL COMMENT '區域id',
  `contact` varchar(20) NOT NULL COMMENT '聯絡人',
  `phone` varchar(20) NOT NULL COMMENT '電話',
  `cell_phone` varchar(20) NOT NULL COMMENT '手機',
  `email` varchar(100) NOT NULL COMMENT '信箱',
  `remark` varchar(200) DEFAULT NULL COMMENT '備註',
  `food` int(2) DEFAULT NULL COMMENT '廚餘標記：0：無，1：有',
  `status` int(2) DEFAULT NULL COMMENT '狀態：0：刪除，1：存在',
  `address` varchar(20) DEFAULT NULL COMMENT '办公室地点',
  `create_time` datetime DEFAULT NULL COMMENT '創建時間',
  `modified_time` datetime DEFAULT NULL COMMENT '修改時間',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

INSERT INTO recycle.tb_department
(id, type_code, type_name, `number`, type_number, name, nickname, region_id, contact, phone, cell_phone, email, remark, food, status, address, create_time, modified_time)
VALUES(1, 1, 'A', 1, 'A001', '101大樓', '101', 1, 'jimmy', '22222222', '0911111111', '123456@gmail.com', '123', 1, 1, '123', '2020-12-17 21:31:44', '2020-12-17 21:31:44');


/*Table structure for table `tb_user` */

DROP TABLE IF EXISTS `tb_user`;

CREATE TABLE `tb_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '帳號id',
  `username` varchar(50) NOT NULL COMMENT '帳號',
  `nickname` varchar(20) DEFAULT NULL,
  `department_id` bigint(20) DEFAULT '1' COMMENT '公司id',
  `email` varchar(128) DEFAULT NULL COMMENT '信箱',
  `status` int(1) NOT NULL COMMENT '状态 0锁定 1有效',
  `password` varchar(128) NOT NULL COMMENT '密码',
  `salt` varchar(255) DEFAULT NULL COMMENT '盐',
  `type` int(11) NOT NULL DEFAULT '1' COMMENT '0:超级管理员，1：系统用户',
  `avatar` text COMMENT '頭像',
  `phone_number` varchar(20) DEFAULT NULL COMMENT '联系电话',
  `create_time` datetime NOT NULL COMMENT '創建時間',
  `modified_time` datetime DEFAULT NULL COMMENT '修改時間',
  `sex` int(1) DEFAULT NULL COMMENT '性别 0男 1女 2保密',
  `birth` date DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=200 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户表';

/*Data for the table `tb_user` */

insert  into `tb_user`(`id`,`username`,`nickname`,`email`,`avatar`,`phone_number`,`status`,`create_time`,`modified_time`,`sex`,`salt`,`type`,`password`,`birth`,`department_id`) values (5,'admin','小章鱼','Jana@126.com','http://thirdqq.qlogo.cn/g?b=oidb&k=icTYjyV5afABvE1v4ge9SLg&s=100&t=1584195695','17744444444',1,'2019-06-14 21:12:16','2020-03-19 04:20:40',0,'cfbf6d34-d3e4-4653-86f0-e33d4595d52b',0,'d7b9c28cac022955cff27947eafce0ad','2020-03-27',1),(196,'jack','testetst','test@qq.com','http://badidol.com/uploads/images/avatars/201910/24/18_1571921832_HG9E55x9NY.jpg','15045414141',1,'2020-08-19 17:41:20','2020-08-19 17:41:20',1,'303191e1-4082-4d2d-8976-5a93426a',1,'49bdaf7293cc9bd6fc9f50c3b03b7d6d','2020-08-17',12),(197,'3333333','33333','333@qq.com','http://badidol.com/uploads/images/avatars/201910/24/18_1571921832_HG9E55x9NY.jpg','15041414141',0,'2020-12-16 21:32:22','2020-12-16 21:32:22',1,'62a6dd8f-9efd-4ae4-98f3-c0382299',1,'2168d955d03701181dd6b3bab7647694','2020-12-29',1),(198,'test','testnickn','test@qq.com','http://badidol.com/uploads/images/avatars/201910/24/18_1571921832_HG9E55x9NY.jpg','15074857474',1,'2020-12-17 18:49:59','2020-12-17 18:50:08',1,'7cb34dcf-62a7-4404-b802-93ebcb1f',1,'9b9013e2729f0c23852ef2801cd5344b','2020-12-15',12),(199,'蔡徐坤','偶像练习生','caixukun@qq.com','http://badidol.com/uploads/images/avatars/201910/24/18_1571921832_HG9E55x9NY.jpg','15041414514',0,'2020-12-17 21:31:44','2020-12-17 21:31:44',1,'9fb8c514-7484-4f6e-a155-6c90ca16',1,'d0e8cf620adb72d66e975e932afb960b','2020-12-16',14);

DROP TABLE IF EXISTS `tb_user_card`;

CREATE TABLE `tb_user_card` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `card_id` bigint(20) NOT NULL,
  `status` int(2) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `tb_card_product`;

CREATE TABLE `tb_card_product` (
  `card_id` bigint(20) NOT NULL,
  `product_id` bigint(20) NOT NULL
) ENGINE=InnoDB CHARSET=utf8mb4;

/*Table structure for table `biz_product` */

DROP TABLE IF EXISTS `biz_product`;

CREATE TABLE `biz_product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `p_num` varchar(255) DEFAULT NULL COMMENT '商品编号',
  `name` varchar(255) DEFAULT NULL COMMENT '名稱',
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
  KEY `id` (`id`),
  KEY `category_id` (`one_category_id`),
  KEY `two_category_id` (`two_category_id`),
  KEY `three_category_id` (`three_category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8;

/*Table structure for table `biz_product_price` */

DROP TABLE IF EXISTS `biz_product_price`;

CREATE TABLE `biz_product_price` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `product_id` bigint(20) NOT NULL,
  `price` bigint(20) DEFAULT NULL,
  `unit` varchar(50) DEFAULT NULL,
  `start_time` datetime DEFAULT NULL COMMENT '生效時間',
  `end_time` datetime DEFAULT NULL COMMENT '失效時間',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB CHARSET=utf8mb4;

/*Table structure for table `tb_weight` */

DROP TABLE IF EXISTS `tb_weight`;

CREATE TABLE `tb_weight` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `department_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `card_id` bigint(20) NOT NULL,
  `product_id` bigint(20) NOT NULL,
  `total_weight` double DEFAULT NULL,
  `deduct_weight` double DEFAULT NULL,
  `net_weight` double DEFAULT NULL,
  `status` int(2) NOT NULL,
  `create_time` datetime NOT NULL COMMENT '創建時間',
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
  `create_time` datetime NOT NULL COMMENT '創建時間',
  `modified_time` datetime DEFAULT NULL COMMENT '修改時間',
  `available` int(11) DEFAULT '1' COMMENT '0：不可用，1：可用',
  `open` int(1) DEFAULT '1' COMMENT '0:不展開，1：展開',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=349 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='選單表';

/*Data for the table `tb_menu` */
INSERT INTO recycle.tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`)
VALUES(1, 0, '一般設定', '', NULL, 'el-icon-setting', '0', 1, '2020-03-07 17:41:30', '2020-08-19 17:57:20', 1, 0);
INSERT INTO recycle.tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`)
VALUES(2, 0, '基本資料', '', NULL, 'el-icon-setting', '0', 1, '2020-03-07 17:41:30', '2020-08-19 17:57:20', 1, 0);
INSERT INTO recycle.tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`)
VALUES(3, 0, '秤重管理', '', NULL, 'el-icon-setting', '0', 1, '2020-03-07 17:41:30', '2020-08-19 17:57:20', 1, 0);
INSERT INTO recycle.tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`)
VALUES(4, 0, '報表查詢', '', NULL, 'el-icon-setting', '0', 1, '2020-03-07 17:41:30', '2020-08-19 17:57:20', 1, 0);
INSERT INTO recycle.tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`)
VALUES(5, 0, '權限管理', '', NULL, 'el-icon-setting', '0', 1, '2020-03-07 17:41:30', '2020-08-19 17:57:20', 1, 0);
INSERT INTO recycle.tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`)
VALUES(6, 0, '後台功能', '', NULL, 'el-icon-setting', '0', 1, '2020-03-07 17:41:30', '2020-08-19 17:57:20', 1, 0);
INSERT INTO recycle.tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`)
VALUES(7, 1, '公司類型設定', '', '', 'el-icon-star-off', '0', 1, '2020-03-16 09:01:48', '2020-12-15 19:51:44', 1, 0);
INSERT INTO recycle.tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`)
VALUES(8, 1, '樓層區域設定', '', '', 'el-icon-star-off', '0', 2, '2020-03-16 09:01:48', '2020-12-15 19:51:44', 1, 0);
INSERT INTO recycle.tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`)
VALUES(9, 1, '成本中心設定', '', '', 'el-icon-star-off', '0', 3, '2020-03-16 09:01:48', '2020-12-15 19:51:44', 1, 0);
INSERT INTO recycle.tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`)
VALUES(10, 1, '廢棄物類型設定', '/business/product/categories', '', 'el-icon-star-off', '0', 4, '2020-03-16 09:01:48', '2020-12-15 19:51:44', 1, 0);
INSERT INTO recycle.tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`)
values(11, 1, '跑馬燈設定', '', '', 'el-icon-star-off', '0', 5, '2020-03-16 09:01:48', '2020-12-15 19:51:44', 1, 0);
INSERT INTO recycle.tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`)
VALUES(12, 2, '公司管理', '/system/departments', '', 'el-icon-s-home', '0', 1, '2020-03-15 06:05:48', '2020-12-15 17:25:18', 1, 0);
INSERT INTO recycle.tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`)
VALUES(13, 2, '用户管理', '/system/users', 'users', 'el-icon-user', '0', 2, '2020-03-10 05:27:54', '2020-12-15 17:24:22', 1, 0);
INSERT INTO recycle.tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`)
VALUES(14, 2, '廢棄物管理', '/business/product/list', '', 'el-icon-goods', '0', 3, '2020-03-16 09:01:02', '2020-12-15 19:51:38', 1, 0);
INSERT INTO recycle.tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`)
VALUES(15, 3, '秤重明細查詢', '/business/product/in-stocks', 'el-icon-date', 'el-icon-date', '0', 1, '2020-03-10 05:34:28', '2020-12-15 19:57:21', 1, 0);
INSERT INTO recycle.tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`)
VALUES(16, 3, '相關資料補登', '', 'el-icon-date', 'el-icon-date', '0', 2, '2020-03-10 05:34:28', '2020-12-15 19:57:21', 1, 0);
INSERT INTO recycle.tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`)
VALUES(17, 5, '選單管理', '/system/menus', 'menus', 'el-icon-help', '0', 1, '2020-03-07 18:57:42', '2020-12-15 17:25:02', 1, 0);
INSERT INTO recycle.tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`)
VALUES(18, 5, '權限管理', '/system/roles', 'roles', 'el-icon-postcard', '0', 3, '2020-03-10 05:51:28', '2020-12-15 17:24:41', 1, 0);
INSERT INTO recycle.tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`)
VALUES(19, 6, '操作日志', '/monitor/logs', '', 'el-icon-edit', '0', 1, '2020-04-04 19:04:53', '2020-12-15 18:34:36', 1, 0);
-- 用戶
INSERT INTO recycle.tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`)
VALUES(20, 13, '用戶添加', '', 'user:add', 'el-icon-plus', '1', 1, '2020-03-10 05:50:44', '2020-03-10 07:51:56', 1, 0);
INSERT INTO recycle.tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`)
VALUES(21, 13, '用戶刪除', '', 'user:delete', 'el-icon-picture', '1', 1, '2020-03-10 06:05:30', '2020-03-10 08:10:19', 1, 0);
INSERT INTO recycle.tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`)
VALUES(22, 13, '用戶編輯', '', 'user:edit', 'el-icon-video-camera-solid', '1', 1, '2020-03-10 06:06:30', '2020-03-10 07:52:28', 1, 0);
INSERT INTO recycle.tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`)
VALUES(23, 13, '分配角色', '', 'user:assign', 'el-icon-s-tools', '1', 3, '2020-03-11 01:32:29', '2020-04-27 10:58:30', 1, 0);
INSERT INTO recycle.tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`)
VALUES(24, 13, '禁用用戶', '', 'user:status', 'el-icon-circle-close', '1', 1, '2020-03-11 06:50:04', '2020-03-14 05:05:49', 1, 0);
INSERT INTO recycle.tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`)
VALUES(25, 13, '用戶更新', '', 'user:update', 'el-icon-refresh', '1', 1, '2020-03-11 08:26:54', '2020-03-11 08:26:54', 1, 0);
INSERT INTO recycle.tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`)
VALUES(26, 13, '導出表格', '', 'user:export', 'el-icon-edit', '1', 1, '2020-04-17 18:02:05', '2020-04-17 18:02:05', 1, 0);
-- 功能
-- 角色
INSERT INTO recycle.tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`)
VALUES(27, 18, '添加角色', '', 'role:add', 'el-icon-help', '1', 1, '2020-03-11 01:34:18', '2020-03-11 01:34:18', 1, 0);
INSERT INTO recycle.tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`)
VALUES(28, 18, '角色編輯', '', 'role:edit', 'el-icon-s-promotion', '1', 2, '2020-03-10 06:11:03', '2020-03-11 11:40:19', 1, 0);
INSERT INTO recycle.tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`)
VALUES(29, 18, '分配權限', '', 'role:authority', 'el-icon-document-add', '1', 1, '2020-03-10 08:13:22', '2020-03-11 11:39:30', 1, 0);
INSERT INTO recycle.tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`)
VALUES(30, 18, '角色更新', '', 'role:update', 'el-icon-refresh-left', '1', 1, '2020-03-11 11:45:20', '2020-03-11 11:45:20', 1, 0);
INSERT INTO recycle.tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`)
VALUES(31, 18, '狀態更新', '', 'role:status', 'el-icon-refresh', '1', 1, '2020-03-14 05:07:02', '2020-03-14 05:07:24', 1, 0);
INSERT INTO recycle.tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`)
VALUES(32, 18, '角色刪除', '', 'role:delete', 'el-icon-s-marketing', '1', 3, '2020-03-10 06:15:29', '2020-03-11 11:43:36', 1, 0);
-- 選單
INSERT INTO recycle.tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`)
VALUES(33, 17, '添加選單', '', 'menu:add', 'el-icon-s-opportunity', '1', 1, '2020-03-10 07:55:10', '2020-04-27 09:59:43', 1, 0);
INSERT INTO recycle.tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`)
VALUES(34, 17, '修改選單', '', 'menu:update', 'el-icon-share', '1', 2, '2020-03-10 07:56:55', '2020-03-15 13:29:29', 1, 0);
INSERT INTO recycle.tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`)
VALUES(35, 17, '刪除選單', '', 'menu:delete', 'el-icon-folder-opened', '1', 3, '2020-03-10 07:57:38', '2020-03-15 13:29:41', 1, 0);
INSERT INTO recycle.tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`)
VALUES(36, 17, '編輯選單', '', 'menu:edit', 'el-icon-edit', '1', 1, '2020-03-22 23:12:25', '2020-03-22 23:12:25', 1, 0);
INSERT INTO recycle.tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`)
VALUES(37, 17, '導出選單', NULL, 'menu:export', 'el-icon-edit', '1', 1, '2020-04-27 17:26:40', '2020-04-27 17:26:40', 1, 0);
-- 公司
INSERT INTO recycle.tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`)
VALUES(38, 12, '添加公司', '', 'department:add', 'el-icon-plus', '1', 1, '2020-03-15 11:57:42', '2020-03-21 12:37:21', 1, 0);
INSERT INTO recycle.tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`)
VALUES(39, 12, '編輯公司', '', 'department:edit', 'el-icon-edit', '1', 1, '2020-03-15 11:59:52', '2020-03-15 12:16:36', 1, 0);
INSERT INTO recycle.tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`)
VALUES(40, 12, '更新公司', '', 'department:update', 'el-icon-refresh', '1', 1, '2020-03-15 12:02:34', '2020-03-15 12:16:32', 1, 0);
INSERT INTO recycle.tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`)
VALUES(41, 12, '刪除公司', NULL, 'department:delete', 'el-icon-delete', '1', 1, '2020-03-15 12:03:21', '2020-03-15 12:03:21', 1, 0);
-- 廢棄物資料
INSERT INTO recycle.tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`)
VALUES(42, 14, '添加物資', '', 'product:add', 'el-icon-s-opportunity', '1', 1, '2020-03-21 02:04:24', '2020-03-21 02:04:24', 1, 0);
INSERT INTO recycle.tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`)
VALUES(43, 14, '上傳圖片', NULL, 'upload:image', 'el-icon-finished', '1', 2, '2020-03-21 02:05:21', '2020-03-21 02:05:21', 1, 0);
INSERT INTO recycle.tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`)
VALUES(44, 14, '更新物資', NULL, 'product:update', 'el-icon-folder', '1', 3, '2020-03-21 02:05:56', '2020-03-21 02:05:56', 1, 0);
INSERT INTO recycle.tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`)
VALUES(45, 14, '編輯物資', NULL, 'product:edit', 'el-icon-edit', '1', 1, '2020-03-21 02:06:23', '2020-03-21 02:06:23', 1, 0);
INSERT INTO recycle.tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`)
VALUES(46, 14, '刪除物資', NULL, 'product:delete', 'el-icon-delete', '1', 1, '2020-04-30 18:27:02', '2020-04-30 19:05:31', 1, 0);
INSERT INTO recycle.tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`)
VALUES(47, 14, '回收物資', '', 'product:remove', 'el-icon-remove', '1', 1, '2020-04-30 18:56:48', '2020-04-30 18:56:48', 1, 1);
INSERT INTO recycle.tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`)
VALUES(48, 14, '物資審核', NULL, 'product:publish', 'el-icon-edit', '1', 1, '2020-04-30 18:58:38', '2020-04-30 19:05:42', 1, 0);
INSERT INTO recycle.tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`)
VALUES(49, 14, '物資還原', NULL, 'product:back', 'el-icon-top-left', '1', 1, '2020-04-30 19:06:22', '2020-04-30 19:06:22', 1, 0);
-- 廢棄物類別
INSERT INTO recycle.tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`)
VALUES(50, 10, '添加類別', '', 'productCategory:add', ' el-icon-folder-add', '1', 1, '2020-03-21 02:26:12', '2020-03-21 02:44:22', 1, 0);
INSERT INTO recycle.tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`)
VALUES(51, 10, '刪除類別', NULL, 'productCategory:delete', 'el-icon-delete', '1', 1, '2020-03-21 02:27:05', '2020-03-21 02:28:49', 1, 0);
INSERT INTO recycle.tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`)
VALUES(52, 10, '編輯類別', NULL, 'productCategory:edit', 'el-icon-scissors', '1', 2, '2020-03-21 02:27:42', '2020-03-21 02:27:42', 1, 0);
INSERT INTO recycle.tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`)
VALUES(53, 10, '更新類別', NULL, 'productCategory:update', ' el-icon-coordinate', '1', 1, '2020-03-21 02:28:17', '2020-03-21 02:28:17', 1, 0);
-- 操作日誌
INSERT INTO recycle.tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`)
VALUES(54, 19, '删除日志', '', 'log:delete', 'el-icon-circle-close', '1', 1, '2020-04-04 19:59:20', '2020-04-04 19:59:20', 1, 1);
INSERT INTO recycle.tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`)
VALUES(55, 19, '批量删除', NULL, 'log:batchDelete', 'el-icon-document-delete', '1', 2, '2020-04-04 19:59:59', '2020-04-04 19:59:59', 1, 0);
-- 秤重明細查詢(不合?)
INSERT INTO recycle.tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`)
VALUES(56, 15, '添加入庫', '', 'inStock:in', 'el-icon-plus', '1', 3, '2020-04-27 17:07:04', '2020-08-19 17:57:15', 1, 1);
INSERT INTO recycle.tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`)
VALUES(57, 15, '入庫明細', NULL, 'inStock:detail', 'el-icon-zoom-in', '1', 2, '2020-04-27 17:08:25', '2020-04-27 17:08:25', 1, 0);
INSERT INTO recycle.tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`)
VALUES(58, 15, '入庫回收', '', 'inStock:remove', 'el-icon-remove', '1', 3, '2020-04-30 19:12:56', '2020-08-19 17:57:55', 1, 1);
INSERT INTO recycle.tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`)
VALUES(59, 15, '入庫審核', NULL, 'inStock:publish', 'el-icon-edit', '1', 2, '2020-04-30 19:13:32', '2020-08-19 17:57:32', 1, 0);
INSERT INTO recycle.tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`)
VALUES(60, 15, '刪除紀錄', NULL, 'inStock:delete', 'el-icon-delete', '1', 4, '2020-04-30 19:14:03', '2020-08-19 17:57:42', 1, 0);
INSERT INTO recycle.tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`)
VALUES(61, 15, '入庫還原', '', 'inStock:back', 'el-icon-d-arrow-left', '1', 3, '2020-04-30 19:17:27', '2020-08-19 17:57:49', 1, 0);

-- 參考選單
INSERT INTO recycle.tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`)
VALUES(1000, 0, '參考資料', '/monitor/logs', '', 'el-icon-edit', '0', 1, '2020-04-04 19:04:53', '2020-12-15 18:34:36', 1, 0);
INSERT INTO recycle.tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`)
VALUES(1001, 1000, '控制面板', '/system/welcome', 'welcome:view', 'el-icon-star-off', '0', 1, '2020-03-10 08:46:44', '2020-12-15 19:22:46', 1, 0);
INSERT INTO recycle.tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`)
VALUES(1002, 1000, '物资来源', '/business/product/suppliers', '', 'el-icon-coordinate', '0', 5, '2020-03-16 12:35:10', '2020-12-15 19:52:19', 1, 0);
INSERT INTO recycle.tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`)
VALUES(1003, 1000, '发放记录', '/business/product/out-stocks', '', 'el-icon-goods', '0', 5, '2020-03-16 13:55:49', '2020-12-15 19:57:34', 1, 1);
INSERT INTO recycle.tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`)
VALUES(1004, 1000, '登入日志', '/monitor/login-log', 'login:log', 'el-icon-date', '0', 1, '2020-03-20 10:31:12', '2020-12-15 18:28:47', 1, 0);
INSERT INTO recycle.tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`)
VALUES(1005, 1000, '全国疫情', '/covid19/map', 'map:view', 'el-icon-s-opportunity', '0', 1, '2020-03-20 11:32:02', '2020-12-15 20:15:48', 1, 1);
INSERT INTO recycle.tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`)
VALUES(1006, 1000, 'swagger文档', '/monitor/swagger-ui', NULL, 'el-icon-document', '0', 2, '2020-03-22 01:22:48', '2020-12-15 18:32:54', 1, 0);
INSERT INTO recycle.tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`)
VALUES(1007, 1000, 'SQL监控', '/monitor/druid', NULL, 'el-icon-view', '0', 1, '2020-03-22 02:48:05', '2020-12-15 19:42:32', 1, 0);
INSERT INTO recycle.tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`)
VALUES(1008, 1000, '健康打卡', '/covid19/health', '', 'el-icon-s-cooperation', '0', 1, '2020-03-24 10:12:57', '2020-12-15 20:19:14', 1, 0);
INSERT INTO recycle.tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`)
VALUES(1009, 1000, '物资去处', '/business/product/consumers', '', 'el-icon-edit', '0', 1, '2020-04-05 10:08:21', '2020-12-15 19:52:10', 1, 0);
INSERT INTO recycle.tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`)
VALUES(1010, 1000, '物资库存', '/business/product/stocks', '', 'el-icon-tickets', '0', 5, '2020-04-16 08:45:08', '2020-12-15 19:51:58', 1, 0);
INSERT INTO recycle.tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`)
VALUES(1011, 1000, '图标管理', '/system/icon', '', 'el-icon-star-off', '0', 7, '2020-04-21 12:06:33', '2020-12-17 21:47:49', 1, 1);
INSERT INTO recycle.tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`)
VALUES(1012, 1000, '文件管理', '/system/files', '', 'el-icon-picture-outline', '0', 2, '2020-04-25 10:52:17', '2020-12-15 19:21:15', 1, 1);
INSERT INTO recycle.tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`)
VALUES(1013, 1000, '个人博客', '/blog', '', 'el-icon-view', '0', 1, '2020-05-07 19:34:31', '2020-05-07 19:34:31', 1, 0);
INSERT INTO recycle.tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`)
VALUES(1014, 1000, '项目接口', '/monitor/swagger-ui', '', 'el-icon-edit', '0', 1, '2020-12-15 18:35:18', '2020-12-15 18:35:18', 1, 1);
-- 參考功能
INSERT INTO recycle.tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`)
VALUES(1015, 1002, '删除来源', '', 'supplier:delete', 'el-icon-document-delete', '1', 1, '2020-03-21 02:17:29', '2020-03-21 12:32:22', 1, 0);
INSERT INTO recycle.tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`)
VALUES(1016, 1002, '更新来源', '', 'supplier:update', 'el-icon-paperclip', '1', 1, '2020-03-21 02:18:34', '2020-03-21 12:36:41', 1, 0);
INSERT INTO recycle.tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`)
VALUES(1017, 1002, '添加来源', NULL, 'supplier:add', 'el-icon-document-add', '1', 1, '2020-03-21 02:19:02', '2020-03-21 02:19:02', 1, 1);
INSERT INTO recycle.tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`)
VALUES(1018, 1002, '编辑来源', NULL, 'supplier:edit', 'el-icon-scissors', '1', 2, '2020-03-21 02:19:36', '2020-03-21 02:19:36', 1, 1);
INSERT INTO recycle.tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`)
VALUES(1019, 1004, '刪除日誌', '', 'loginLog:delete', 'el-icon-delete', '1', 1, '2020-03-22 21:55:44', '2020-03-22 21:55:44', 1, 0);
INSERT INTO recycle.tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`)
VALUES(1020, 1004, '批量刪除', '', 'loginLog:batchDelete', 'el-icon-delete-solid', '1', 1, '2020-03-22 22:19:26', '2020-03-22 22:19:26', 1, 0);
INSERT INTO recycle.tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`)
VALUES(1021, 1009, '添加去处', '', 'consumer:add', 'el-icon-plus', '1', 2, '2020-04-27 16:57:04', '2020-04-27 16:58:21', 1, 1);
INSERT INTO recycle.tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`)
VALUES(1022, 1009, '删除去处', NULL, 'consumer:delete', 'el-icon-delete', '1', 1, '2020-04-27 16:57:42', '2020-04-27 16:57:42', 1, 0);
INSERT INTO recycle.tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`)
VALUES(1023, 1009, '编辑去处', '', 'consumer:edit', 'el-icon-edit', '1', 1, '2020-04-27 16:59:17', '2020-04-27 16:59:17', 1, 0);
INSERT INTO recycle.tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`)
VALUES(1024, 1009, '更新去处', NULL, 'consumer:update', 'el-icon-star-off', '1', 1, '2020-04-27 17:00:18', '2020-04-27 17:00:18', 1, 1);
INSERT INTO recycle.tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`)
VALUES(1025, 1008, '健康上报', '', 'health:report', 'el-icon-edit', '1', 1, '2020-05-14 20:21:09', '2020-05-14 20:21:09', 1, 0);

INSERT INTO recycle.tb_menu
(id, parent_id, menu_name, url, perms, icon, `type`, order_num, create_time, modified_time, available, `open`)
VALUES(1026, 4, 'XX報表', '', '', 'el-icon-edit', '0', 1, '2020-12-15 18:35:18', '2020-12-15 18:35:18', 1, 0);

/*Table structure for table `tb_role` */

DROP TABLE IF EXISTS `tb_role`;

CREATE TABLE `tb_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(100) NOT NULL COMMENT '角色名稱',
  `remark` varchar(100) DEFAULT NULL COMMENT '角色描述',
  `create_time` datetime NOT NULL COMMENT '創建時間',
  `modified_time` datetime DEFAULT NULL COMMENT '修改時間',
  `status` int(1) DEFAULT '1' COMMENT '是否可用,0:不可用，1：可用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=146 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='角色表';

/*Data for the table `tb_role` */

insert  into `tb_role`(`id`,`role_name`,`remark`,`create_time`,`modified_time`,`status`) values (145,'测试角色','用于测试的账号','2020-12-17 00:00:00','2020-12-17 20:33:46',1);

/*Table structure for table `tb_role_menu` */

DROP TABLE IF EXISTS `tb_role_menu`;

CREATE TABLE `tb_role_menu` (
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `menu_id` bigint(20) NOT NULL COMMENT '選單/按鈕ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='角色選單关联表';

/*Data for the table `tb_role_menu` */

insert  into `tb_role_menu`(`role_id`,`menu_id`) values (145,253),(145,234),(145,239),(145,240),(145,258),(145,317),(145,318),(145,321),(145,247),(145,301),(145,329),(145,255),(145,259),(145,241),(145,261),(145,262),(145,264),(145,312),(145,230),(145,328),(145,338),(145,326),(145,337),(145,340),(145,339),(145,310),(145,323),(145,324),(145,325),(145,322),(145,267),(145,274),(145,278),(145,331),(145,332),(145,333),(145,336),(145,276),(145,277),(145,268),(145,283),(145,284),(145,286),(145,285),(145,269),(145,279),(145,280),(145,281),(145,282),(145,270),(145,316),(145,303),(145,273),(145,304),(145,343),(145,5),(145,271),(145,299),(145,300),(145,298),(145,307),(145,308),(145,309),(145,344),(145,1),(145,226),(145,4),(145,235);

/*Table structure for table `tb_user_role` */

DROP TABLE IF EXISTS `tb_user_role`;

CREATE TABLE `tb_user_role` (
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户角色关联表';

/*Data for the table `tb_user_role` */

insert  into `tb_user_role`(`user_id`,`role_id`) values (194,125),(196,145),(199,145);


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
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2192 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='操作日志表';

--  Auto-generated SQL script #202111060034
UPDATE recycle.tb_menu
	SET url='/system/departmentCategories'
	WHERE id=7;
UPDATE recycle.tb_menu
	SET url='/system/region'
	WHERE id=8;
UPDATE recycle.tb_menu
	SET url='/business/product/costCenter'
	WHERE id=9;
UPDATE recycle.tb_menu
	SET url='/system/runText'
	WHERE id=11;
UPDATE recycle.tb_menu
	SET url='/business/weight/list'
	WHERE id=15;
UPDATE recycle.tb_menu
	SET url='/business/weight/refill'
	WHERE id=16;







