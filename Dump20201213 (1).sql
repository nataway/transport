-- MySQL dump 10.13  Distrib 8.0.20, for Win64 (x86_64)
--
-- Host: localhost    Database: transport
-- ------------------------------------------------------
-- Server version	8.0.20

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `driver`
--

DROP TABLE IF EXISTS `driver`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `driver` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `id_card` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `license_num` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `license_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `dob` date NOT NULL,
  `seniority` int NOT NULL,
  `status` int NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_card` (`id_card`),
  UNIQUE KEY `license_num` (`license_num`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `driver`
--

LOCK TABLES `driver` WRITE;
/*!40000 ALTER TABLE `driver` DISABLE KEYS */;
INSERT INTO `driver` VALUES (1,'Nguyễn Trung Hiếu','001099009956','790456732333','C','Mỹ Đình, Nam Từ Liêm, Hà Nội','1992-03-18',5,0),(2,'Nguyễn Duy Hưng','001045673478','790011113423','C','Mỹ Sơn, Đô Lương, Nghệ An','1995-05-05',3,1),(4,'Bùi Minh Chí','001012341234','790002231234','C','Trung Văn, Nam Từ Liêm, Hà Nội','1991-10-11',4,1),(5,'Hàn Nhật Tuấn','001289309873','791230003213','C','Nguyễn Trãi, Thường Tín, Hà Nội','1982-12-12',7,1),(6,'Nguyễn Trung A','000103033708','010103033708','D','Hoàng Diệu, Chương Mỹ, Hà Nội','1993-02-28',4,1),(7,'Nguyễn Duy A','001289333873','011103333708','D','Tân Mai, Hoàng Mai, Hà Nội','1972-05-08',10,1),(8,'Bùi Minh A','001022223478','010101223408','D','Tân An, Hội An, Quảng Nam','1979-03-12',7,1),(9,'Hàn Nhật A','001244449873','799923452322','E','Nguyễn Thái Học, Quy Nhơn, Bình Định','1978-11-11',8,1),(10,'Nguyễn Trung B','001211133873','799933332322','E','Trung Dũng, Biên Hòa, Đồng Nai','1975-04-11',9,1),(11,'Bùi Minh B','001211132273','799933332112','E','Minh Khai, Từ Liêm, Hà Nội','1980-04-04',8,1),(22,'Hàn Nhật B','038472612','799938452112','C','Minh Khai, Từ Liêm, Hà Nội','1999-10-06',6,1);
/*!40000 ALTER TABLE `driver` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `persistent_logins`
--

DROP TABLE IF EXISTS `persistent_logins`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `persistent_logins` (
  `username` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `series` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `token` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `last_used` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`series`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `persistent_logins`
--

LOCK TABLES `persistent_logins` WRITE;
/*!40000 ALTER TABLE `persistent_logins` DISABLE KEYS */;
/*!40000 ALTER TABLE `persistent_logins` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'ADMIN'),(2,'MANAGER'),(3,'EMPLOYEE'),(4,'GUEST');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `route`
--

DROP TABLE IF EXISTS `route`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `route` (
  `id` int NOT NULL AUTO_INCREMENT,
  `entry_point` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `end_point` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `route_length` float NOT NULL,
  `routecomplexity_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `routecomplexity_id` (`routecomplexity_id`),
  CONSTRAINT `route_ibfk_1` FOREIGN KEY (`routecomplexity_id`) REFERENCES `routecomplexity` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `route`
--

LOCK TABLES `route` WRITE;
/*!40000 ALTER TABLE `route` DISABLE KEYS */;
INSERT INTO `route` VALUES (1,'Hà Nội - BX Lương Yên','Thái Nguyên - BX Thái Nguyên',90,1),(2,'Hà Nội - BX Mỹ Đình','Hải Phòng - Văn Phong Tiên Lãng',120,1),(3,'Hà Nội - BX Giáp Bát','Quảng Ninh - BX Tiên Yên',200,2),(4,'Hà Nội - BX Yên Nghĩa','Bắc Giang - BX Hiệp Hòa',80,1),(5,'Hà Nội - BX Mỹ Đình','Bắc Ninh - Quế Võ ',60,1),(6,'Hà Nội - BX Mỹ Đình','Hà Giang - BX Hà Giang',300,3),(7,'Hà Nội - BX Giáp Bát','Hà Nam - Chân Lý',115,1),(8,'Hà Nội - BX Yên Nghĩa','Hòa Bình - BX Hòa Bình',165,2),(9,'Hà Nội - BX Nước Ngầm','Hưng Yên - BX Hưng Yên',110,1),(10,'Hà Nội - BX Nước Ngầm','Nghệ An - BX Vinh',400,3),(11,'Nhà thờ Hàng Bột - Đống Đa - HN','Ecopark Hưng Yên',60.99,1),(12,'Bến xe Mỹ Đình','Núi Đèo - Thủy Nguyên - Hải Phòng',98.247,2);
/*!40000 ALTER TABLE `route` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `routecomplexity`
--

DROP TABLE IF EXISTS `routecomplexity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `routecomplexity` (
  `id` int NOT NULL AUTO_INCREMENT,
  `coefficients_salary` float NOT NULL,
  `coefficients_route` float NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `routecomplexity`
--

LOCK TABLES `routecomplexity` WRITE;
/*!40000 ALTER TABLE `routecomplexity` DISABLE KEYS */;
INSERT INTO `routecomplexity` VALUES (1,8.8,70.67),(2,9.9,82.453),(3,12.3,96.2988);
/*!40000 ALTER TABLE `routecomplexity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `salary`
--

DROP TABLE IF EXISTS `salary`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `salary` (
  `id` int NOT NULL AUTO_INCREMENT,
  `salary` bigint NOT NULL,
  `from_date` date NOT NULL,
  `to_date` date NOT NULL,
  `driver_id` int NOT NULL,
  `tour_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `driver_id` (`driver_id`),
  KEY `tour_id` (`tour_id`),
  CONSTRAINT `salary_ibfk_1` FOREIGN KEY (`driver_id`) REFERENCES `driver` (`id`),
  CONSTRAINT `salary_ibfk_2` FOREIGN KEY (`tour_id`) REFERENCES `tour` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `salary`
--

LOCK TABLES `salary` WRITE;
/*!40000 ALTER TABLE `salary` DISABLE KEYS */;
INSERT INTO `salary` VALUES (7,281599,'2020-12-02','2020-12-06',4,2),(8,140799,'2020-12-02','2020-12-06',8,2);
/*!40000 ALTER TABLE `salary` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `staff`
--

DROP TABLE IF EXISTS `staff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `staff` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `address` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `phone` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `staff_ibfk_1` FOREIGN KEY (`id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `staff`
--

LOCK TABLES `staff` WRITE;
/*!40000 ALTER TABLE `staff` DISABLE KEYS */;
INSERT INTO `staff` VALUES (1,'Trung Hiếu Nguyễn','254 Minh Khai','+84333794662',NULL,'2020-11-13 00:49:33'),(2,'Bùi Minh Chí','Ha Noi','0333324435',NULL,'2020-10-08 20:28:55'),(3,'Nguyễn Duy Hưng','Ha Noi','0333324435','2020-10-01 08:41:18','2020-10-12 01:05:32');
/*!40000 ALTER TABLE `staff` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tour`
--

DROP TABLE IF EXISTS `tour`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tour` (
  `id` int NOT NULL AUTO_INCREMENT,
  `passengers_num` int NOT NULL,
  `fare` bigint NOT NULL,
  `driver_id` int DEFAULT NULL,
  `assistant_id` int DEFAULT NULL,
  `vehicle_id` int NOT NULL,
  `route_id` int NOT NULL,
  `started_at` timestamp NULL DEFAULT NULL,
  `finished_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `driver_id` (`driver_id`),
  KEY `assistant_id` (`assistant_id`),
  KEY `vehicle_id` (`vehicle_id`),
  KEY `route_id` (`route_id`),
  CONSTRAINT `tour_ibfk_1` FOREIGN KEY (`driver_id`) REFERENCES `driver` (`id`),
  CONSTRAINT `tour_ibfk_2` FOREIGN KEY (`assistant_id`) REFERENCES `driver` (`id`),
  CONSTRAINT `tour_ibfk_3` FOREIGN KEY (`vehicle_id`) REFERENCES `vehicle` (`id`),
  CONSTRAINT `tour_ibfk_4` FOREIGN KEY (`route_id`) REFERENCES `route` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tour`
--

LOCK TABLES `tour` WRITE;
/*!40000 ALTER TABLE `tour` DISABLE KEYS */;
INSERT INTO `tour` VALUES (2,40,80000,4,8,6,11,'2020-12-02 15:44:54','2020-12-11 07:39:42');
/*!40000 ALTER TABLE `tour` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `status` int NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'trunghieunguyen2903@gmail.com','$2y$12$yb7bs2pKa1UXv4FqTwySn.BgsL1PSQbUGNvN.xgqFrF3xAW/mx0hG',1),(2,'buiminhchi@gmail.com','$2y$12$yb7bs2pKa1UXv4FqTwySn.BgsL1PSQbUGNvN.xgqFrF3xAW/mx0hG',1),(3,'tranduyhung@gmail.com','$2y$12$yb7bs2pKa1UXv4FqTwySn.BgsL1PSQbUGNvN.xgqFrF3xAW/mx0hG',1),(4,'hannhattuan@gmail.com','$2y$12$yb7bs2pKa1UXv4FqTwySn.BgsL1PSQbUGNvN.xgqFrF3xAW/mx0hG',1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `role_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `role_id` (`role_id`),
  CONSTRAINT `user_role_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `user_role_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (1,1,1),(2,2,2),(3,3,3),(4,4,4);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vehicle`
--

DROP TABLE IF EXISTS `vehicle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vehicle` (
  `id` int NOT NULL AUTO_INCREMENT,
  `license_plate` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `color` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `manufacturer` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `car_life` int NOT NULL,
  `model` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `thumbnail` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `seats_num` int NOT NULL,
  `years_of_use` int NOT NULL,
  `last_maintenance` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `description` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `status` int NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `license_plate` (`license_plate`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vehicle`
--

LOCK TABLES `vehicle` WRITE;
/*!40000 ALTER TABLE `vehicle` DISABLE KEYS */;
INSERT INTO `vehicle` VALUES (1,'30B-111.11','Trắng','Huyndai',2017,'Universe Noble Doosan','https://lh3.googleusercontent.com/proxy/BwdKKOeQWu5tbQ4cpKxA5Aitaj3j06r5gelVNCdDlVVqaYhp1SMG0f51fnbJF8n97_zJ4Dsv7-D-9haCNyufkUbknK3sUcwuUggHB7CnV50pXMTr8P5rHH-PMFzVAhl-dkZZJmPhKUW7R-1NlNE',45,25,'2019-02-11 17:00:00',NULL,1),(2,'30E-222.22','Đỏ','Huyndai',2016,'H350','https://images.summitmedia-digital.com/topgear/images/2019/07/01/2018-hyundai-h350-main-1561969031.jpg',16,25,'2018-04-10 17:00:00',NULL,1),(3,'30A-333.33','Xám','Huyndai',2015,'HAECO UNIVERSE K39S','https://img2.canbanoto.com/2016/07/22/0059ae1edd2a3cr805824-89ce.jpg',39,25,'2018-04-07 17:00:00',NULL,1),(4,'29B-444.44','Trắng','Samco',2017,'Samco Felix','https://muabanxekhach.com.vn/wp-content/uploads/2019/05/samco-felix-29-cho-ngoi.jpg',29,25,'2019-11-10 17:00:00',NULL,1),(5,'30B-555.55','Xanh','Thaco',2016,'Thaco Meadow TB85s','https://dailyxetaihaiphong.vn/wp-content/uploads/2018/12/Ban-xe-khach-bau-hoi-29-cho_result.jpg',29,25,'2020-12-06 12:29:06',NULL,1),(6,'30B-666.66','Vàng','Toyota',2018,'Toyota Hiace','https://img1.oto.com.vn/2019/03/27/2nYr5R9Z/danh-gia-xe-toyota-hiace-2019-oto-com-vn-10-d812.jpg',16,25,'2019-12-31 17:00:00',NULL,1),(7,'30B-777.77','Đỏ','Ford',2017,'Ford Transit','https://fordcaothang.com/wp-content/uploads/2019/06/ford-transit.jpg',16,25,'2019-10-09 17:00:00',NULL,1),(8,'30B-888.88','Xám','Tracomeco',2018,'Tracomeco Global','https://muabanxekhach.com.vn/wp-content/uploads/2018/12/xe-khach-tracomeco-universe-29-cho-ngoi-1.jpg',29,25,'2019-12-31 17:00:00',NULL,1),(9,'30B-999.99','Xanh','Samco',2017,'Universe Samco','https://muabanxekhach.com.vn/wp-content/uploads/2018/07/xe-khach-universe-samco-primas-37-giuong-1.jpg',35,25,'2020-12-09 04:45:00',NULL,1),(10,'30B-686.68','Đỏ','Hyundai',2016,'Hyundai Universe','https://hyundai-mienbac.com/wp-content/uploads/2019/07/hyundai-universe-premium.jpg',45,25,'2019-06-11 17:00:00',NULL,1),(11,'30A-284.22','Red','Honda',1990,'Meadow','https://res.cloudinary.com/ziczacgroup/image/upload/v1546930652/mpjjump5dubyned6a207.jpg',30,28,'2020-09-05 17:00:00','<p>sadjhwaf</p><p><span style=\"font-family: &quot;Courier New&quot;;\">fawouhag</span></p><p><b><span style=\"font-family: &quot;Courier New&quot;;\">awughawu</span></b></p><p><b>fiwagfa</b></p><p><b><i><u>ahsfbhbabgu</u></i></b></p><p><b><i><u>sadawfwgagwa</u></i></b></p>',1);
/*!40000 ALTER TABLE `vehicle` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'transport'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-12-13 15:28:24
