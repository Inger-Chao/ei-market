-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: market
-- ------------------------------------------------------
-- Server version	5.7.14

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `cart`
--

DROP TABLE IF EXISTS `cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cart` (
  `id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `goods_id` int(11) DEFAULT NULL COMMENT '商品id',
  PRIMARY KEY (`id`),
  KEY `cart_user_foreign_idx` (`user_id`),
  KEY `cart_goods_foreign_idx` (`goods_id`),
  CONSTRAINT `cart_goods_foreign` FOREIGN KEY (`goods_id`) REFERENCES `goods` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `cart_user_foreign` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='购物车表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart`
--

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comments`
--

DROP TABLE IF EXISTS `comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comments` (
  `id` int(11) NOT NULL COMMENT '评论主键',
  `user_id` int(11) DEFAULT NULL COMMENT '用户，外键',
  `goods_id` int(11) DEFAULT NULL COMMENT '商品，外键',
  `content` text COMMENT '评论内容',
  `create_time` date DEFAULT NULL COMMENT '评论时间',
  PRIMARY KEY (`id`),
  KEY `comment_user_foreign_idx` (`user_id`),
  KEY `comment_goods_foreign_idx` (`goods_id`),
  CONSTRAINT `comment_goods_foreign` FOREIGN KEY (`goods_id`) REFERENCES `goods` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `comment_user_foreign` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comments`
--

LOCK TABLES `comments` WRITE;
/*!40000 ALTER TABLE `comments` DISABLE KEYS */;
/*!40000 ALTER TABLE `comments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `deal`
--

DROP TABLE IF EXISTS `deal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `deal` (
  `id` int(11) NOT NULL,
  `saler_id` int(11) NOT NULL COMMENT '卖家id',
  `buyer_id` int(11) NOT NULL COMMENT '买家id',
  `goods_id` int(11) NOT NULL COMMENT '商品id',
  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '交易状态\n1：未付款 2：未发货 3：正在路上 4：已确认收货 5：已评价',
  `create_time` date DEFAULT NULL COMMENT '交易创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `deal_user_buyer_foreign_idx` (`buyer_id`),
  KEY `deal_user_saler_foreign_idx` (`saler_id`),
  KEY `deal_goods_foreign_idx` (`goods_id`),
  CONSTRAINT `deal_goods_foreign` FOREIGN KEY (`goods_id`) REFERENCES `goods` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `deal_user_buyer_foreign` FOREIGN KEY (`buyer_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `deal_user_saler_foreign` FOREIGN KEY (`saler_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='货品交易';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `deal`
--

LOCK TABLES `deal` WRITE;
/*!40000 ALTER TABLE `deal` DISABLE KEYS */;
/*!40000 ALTER TABLE `deal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `goods`
--

DROP TABLE IF EXISTS `goods`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `goods` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品主键',
  `type_id` int(11) DEFAULT NULL COMMENT '商品分类，外键',
  `user_id` int(11) NOT NULL COMMENT '用户外键',
  `name` varchar(50) NOT NULL COMMENT '商品名称',
  `price` float(11,2) NOT NULL COMMENT '出售价格',
  `img_url` varchar(45) DEFAULT NULL,
  `real_price` float(11,2) DEFAULT NULL COMMENT '真实价格',
  `create_time` date DEFAULT NULL COMMENT '发布时间',
  `polish_time` date DEFAULT NULL COMMENT '擦亮时间，按该时间进行查询，精确到时分秒',
  `end_time` date DEFAULT NULL COMMENT '下架时间',
  `description` text COMMENT '详细信息',
  `comment_num` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `catelog_id` (`type_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `goods_type_foreign` FOREIGN KEY (`type_id`) REFERENCES `type` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `goods_user_id_foreign` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `goods`
--

LOCK TABLES `goods` WRITE;
/*!40000 ALTER TABLE `goods` DISABLE KEYS */;
INSERT INTO `goods` VALUES (1,6,1,'精美吉他',130.00,'ae2bdee2-0abb-428d-961f-eadf9905bf4c.jpg',160.00,'2017-05-13','2017-05-13','2017-05-23','自用二手吉他，9成新，低价出售，有意者联系。',NULL,1),(2,2,1,'山地车',520.00,'f4ccf238-e458-4dda-9ed8-88bca2d42309.jpg',890.00,'2017-05-13','2017-05-13','2017-05-23','8成新山地车，无损坏，喜欢Call我。',NULL,1),(3,3,1,'无线鼠标',23.00,'f34e49f8-bbef-4e1d-8138-e57aa97e408a.jpg',48.00,'2017-05-13','2017-05-13','2017-05-23','罗技无线鼠标，自用一个月，9成新，手感好，反应灵敏。',NULL,1),(5,1,2,'数码相机',580.00,'24e34c65-1ff9-4eba-b97a-7cd8d8b4ecb1.jpg',1350.00,'2017-05-14','2017-05-14','2017-05-24','自用的数码相机，一年前购买，非常爱好，无磕碰。可议价。',NULL,1),(6,1,2,'笔记本电脑',690.00,'bec28d84-7d5e-4d50-8bb2-91409de00859.jpg',2300.00,'2017-05-14','2017-05-14','2017-05-24','7成新14寸笔记本电脑，商务本，适合办公，2G内存。',NULL,1),(7,3,2,'收纳盒',15.00,'4859432c-5955-411b-bdaa-d65275a9a61a.jpg',36.00,'2017-05-14','2017-05-14','2017-05-24','3层塑料物品收纳盒，毕业了，低价出售。',NULL,1),(8,3,1,'台灯',32.00,'3c95fa0d-d5e3-40f7-826f-cafe6e6af740.jpg',58.00,'2017-05-14','2017-05-14','2017-05-24','考研时购买的台灯，可插USB接口。',NULL,1),(9,5,2,'女鞋',35.00,'d8ca0740-bdf3-416b-8557-8384e7299924.jpg',86.00,'2017-05-14','2017-05-14','2017-05-24','学生女鞋',NULL,1),(11,1,3,'无线传呼机',230.00,'68bac661-f69f-4ec5-8f21-f92ec1970547.jpg',370.00,'2017-05-14','2017-05-14','2017-05-24','一对无线传呼机，带有充电器，可以传呼2公里。',NULL,1),(12,5,3,'手提女包',36.00,'83e6a0be-cf28-4d74-8db6-ed1e3bafdec0.jpg',89.00,'2017-05-14','2017-05-14','2017-05-24','手提女包，自用一个月。',NULL,1),(13,5,1,'手提包',15.00,'e26183d0-244c-4da4-86dd-387d0ef8dfeb.jpg',23.00,'2017-05-14','2017-05-14','2017-05-24','自用手提包，8成新，便宜出。',NULL,1),(16,6,2,'耐克运动书包',56.00,'f845fd90-db8a-4701-aa22-0968b2694fb0.jpg',198.00,'2017-05-14','2017-05-14','2017-05-24','去年年底购买的耐克书包，8成新，无损坏，可以装电脑，很漂亮，有意者联系。',NULL,1),(17,4,2,'二手小说',10.00,'c378a8bb-0561-42bb-b77e-c89ed375efca.jpg',65.00,'2017-05-14','2017-05-14','2017-05-24','毕业季，一书架小说，便宜出售。',NULL,1),(18,4,2,'公务员考试资料',35.00,'8222a2f2-6287-4530-a399-da7696b73fdf.jpg',79.00,'2017-05-14','2017-05-14','2017-05-24','刚刚考完公务员的复习资料。淘宝购买，低价出售。',NULL,1),(20,1,4,'Thinkpad笔记本',1600.00,'5aceed48-21f6-4aa8-9cfa-6bb831694c3a.jpg',2300.00,'2017-05-14','2017-05-14','2017-05-24','京东购买的Thinkpad笔记本电脑，九成新。限鲁大学生。',NULL,1),(21,7,4,'水晶挂件',20.00,'d01acfd0-3b87-4983-b46d-d2864e722437.jpg',60.00,'2017-05-14','2017-05-14','2017-05-24','水晶挂件。一套，全部出。很精美很漂亮。',NULL,1),(22,4,3,'《Python编程实践》',32.00,'dee36400-df6a-46ac-96df-d71a7c42f328.jpg',69.00,'2017-05-14','2017-05-14','2017-05-24','亚马逊购买',NULL,1),(23,4,3,'《未来简史》',40.00,'0a281a3b-f4e1-445b-b7a0-3f7a829849bc.jpg',69.00,'2017-05-14','2017-05-14','2017-05-24','9.5成新书籍，《未来简史》，已读完，推荐。',NULL,1),(24,7,3,'玉挂',260.00,'f2857094-1642-4d22-88b8-d06c290944fe.jpg',450.00,'2017-05-14','2017-05-14','2017-05-24','纯玉，购买于食品店，有发票。',NULL,1),(26,2,3,'概念代步车',1300.00,'27c63995-9ff9-453d-b24f-d9aa95dcc138.jpg',3500.00,'2017-05-14','2017-05-14','2017-05-24','9成新概念代步车，上市3个月。',NULL,1),(27,2,3,'爱玛电动车',800.00,'e80e60a1-859c-4277-9a6a-c6bfe95dcf7a.jpg',1500.00,'2017-05-14','2017-05-14','2017-05-24','爱玛电动车，爱就马上行动吧。',NULL,1),(28,3,3,'公牛插排',15.00,'786c6d89-b27a-4a41-8f96-352aed2a9e6c.jpg',36.00,'2017-05-14','2017-05-14','2017-05-24','京东购买的插排，9成新。',NULL,1),(29,5,1,'李宁音速',90.00,'4ff2f2e2-5b3e-4ce6-9161-fb09cffea277.jpg',100.00,'2017-05-14','2017-05-14','2017-05-24','李宁旗舰店购买的李宁音速，9成新，41号，有意者联系。',NULL,1),(31,2,1,'山地车',860.00,'2952fe1e-0633-4f9d-9249-f999603235f5.jpg',1300.00,'2017-05-15','2017-05-15','2017-05-25','GIANT山地车，9成新，骑行两个月，无任何损坏。',NULL,1),(32,4,1,'《形势与政策》',35.00,'75599f16-ef35-4013-8c71-9f8c1682ad64.jpg',65.00,'2017-05-15','2017-05-15','2017-05-25','一个月前购买的书籍，现在已看完，还不错，收获很多。现转卖。',NULL,1),(34,4,2,'《世界汉学》',18.00,'370298f4-e81c-46b6-9dbd-83118922bb22.jpg',36.00,'2017-05-15','2017-05-15','2017-05-25','一本记载了汉代发展的书籍。',NULL,1),(35,1,2,'欧达数码相机',890.00,'a90f891e-9b74-4f92-a800-03ef83c0a69c.jpg',1300.00,'2017-05-15','2017-05-15','2017-05-25','9成新欧达数码相机，刚入手一个索尼，打算出掉，可议价。',NULL,1),(36,3,2,'手机充电器',15.00,'6c8a2d88-ab90-488e-a468-d379c730bd46.jpg',38.00,'2017-05-15','2017-05-15','2017-05-25','华为手机充电器，手机已毁，现转让充电器。',NULL,1),(37,1,2,'摄像头',360.00,'85d132d0-bb61-4519-a08e-ec29937e4426.jpg',480.00,'2017-05-15','2017-05-15','2017-05-25','可旋转摄像头',NULL,1),(38,3,2,'漫步者耳机',380.00,'2f3fc053-090d-49de-a184-39e78cb4fc7e.jpg',690.00,'2017-05-15','2017-05-15','2017-05-25','漫步者耳机，音质好，9.5成新。',NULL,1),(39,1,2,'小米笔记本',3200.00,'9d0782df-277f-45e6-b3b4-c424d688e312.jpg',5800.00,'2017-05-15','2017-05-15','2017-05-25','去年年底京东购买的笔记本，未过保，无损坏。',NULL,1),(40,2,2,'死飞自行车',350.00,'4b85e359-6fc8-4c12-93b9-edec64320f67.jpg',680.00,'2017-05-15','2017-05-15','2017-05-25','黄色死飞自行车，骑行一年。9成新。',NULL,1),(41,2,2,'自行车',360.00,'26c2c89b-9312-4759-88a0-7f73f86c549d.jpg',480.00,'2017-05-15','2017-05-15','2017-05-25','银色自行车。',NULL,1),(44,5,2,'t恤套装',40.00,'01974fb4-da95-4191-8c80-e89db4a4e7a3.jpg',90.00,'2017-05-15','2017-05-15','2017-05-25','上身+下身，全套出。',NULL,1),(45,5,2,'t恤',13.00,'75131adc-b3a8-495b-94a5-c7b144c41a0e.jpg',36.00,'2017-05-15','2017-05-15','2017-05-25','淘宝购买，低价出。',NULL,1),(46,4,1,'Spring3.x--企业开发实战',95.00,'f80708c6-e242-465d-9f0e-18f79f0d0b00.jpg',112.00,'2017-05-15','2017-05-15','2017-05-25','作为Spring3.x的升级版，书内容还是相当不错，讲解非常细致，分析非常到位，真正做到理论结合实际，对目前大环境下质量普遍不好的情况下，该书还真是值得购买和通读的。',NULL,1),(47,4,1,'spring实战',60.00,'816c281c-3c37-4e93-beb9-074276838bb5.jpg',80.00,'2017-05-16','2017-05-16','2017-05-26','非常畅销的Spring图书',NULL,1),(48,4,1,'图书',25.70,'58a655a4-3bb1-4d63-87c1-257865393741.jpg',36.50,'2017-05-16','2017-05-16','2017-05-26','淘宝购买。',NULL,1),(49,8,5,'张宇高数18讲',9.90,'gaoshu18jiang.jpg',18.00,'2018-09-06',NULL,'2020-05-08','考研数学狄一书',NULL,1);
/*!40000 ALTER TABLE `goods` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `type`
--

DROP TABLE IF EXISTS `type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `type` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(30) DEFAULT NULL COMMENT '分类名',
  `number` int(11) DEFAULT '0' COMMENT '该分类下的商品数量',
  `status` tinyint(10) DEFAULT '0' COMMENT '分类状态，0正常，1暂用',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `type`
--

LOCK TABLES `type` WRITE;
/*!40000 ALTER TABLE `type` DISABLE KEYS */;
INSERT INTO `type` VALUES (1,'闲置数码',7,1),(2,'校园代步',6,1),(3,'电器日用',6,1),(4,'图书教材',11,1),(5,'美妆衣物',6,1),(6,'运动棋牌',2,1),(7,'票券小物',2,1),(8,'线下交易',1,1);
/*!40000 ALTER TABLE `type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `phone` char(11) CHARACTER SET utf8 DEFAULT NULL COMMENT '登录使用的手机号',
  `username` varchar(30) CHARACTER SET utf8 NOT NULL COMMENT '用户名',
  `password` char(32) CHARACTER SET utf8 NOT NULL COMMENT '密码',
  `QQ` varchar(12) CHARACTER SET utf8 DEFAULT NULL COMMENT '即时通讯',
  `create_time` date DEFAULT NULL COMMENT '创建时间',
  `goods_num` int(11) DEFAULT '0' COMMENT '发布过的物品数量',
  `power` tinyint(10) DEFAULT '0' COMMENT '权限值，0：普通用户，1：管理员',
  `coin` float DEFAULT '0' COMMENT '账户金额',
  `credit` float DEFAULT '5',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'17862821585','hlk1135','12345','862186722','2017-05-10',11,0,0,5),(2,'17862826440','llwwlql','123456','462821493','2017-05-14',17,0,0,5),(3,'17862821996','lduldj','123456','421330323','2017-05-14',8,0,0,5),(4,'15552201622','策马奔腾hly','123456','782697347','2017-05-15',2,0,0,5),(5,'17862821996','admin','admin','1256972301','2017-05-01',0,1,0,5),(6,'15735659693','ingerjo','ingerjo','396558528','2018-09-06',0,1,50,5),(7,'11456789134','tong','tongtong','415678945','2018-09-06',0,1,0,5),(20,'15735659693','joki','joki233',NULL,'2018-09-07',NULL,NULL,0,5);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-09-07 18:05:44
