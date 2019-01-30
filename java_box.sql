-- --------------------------------------------------------
-- ホスト:                          127.0.0.1
-- サーバーのバージョン:                   5.6.41-log - MySQL Community Server (GPL)
-- サーバー OS:                      Win64
-- HeidiSQL バージョン:               9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- java_box のデータベース構造をダンプしています
CREATE DATABASE IF NOT EXISTS `java_box` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
USE `java_box`;

--  テーブル java_box.box_app の構造をダンプしています
CREATE TABLE IF NOT EXISTS `box_app` (
  `appId` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL DEFAULT '0',
  `route` varchar(50) NOT NULL DEFAULT '0',
  `jarUrl` varchar(50) NOT NULL DEFAULT '0',
  `jarName` varchar(50) NOT NULL DEFAULT '0',
  `jarMd5` varchar(50) NOT NULL DEFAULT '0',
  `stats` smallint(6) NOT NULL DEFAULT '0',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `jarType` int(11) NOT NULL,
  PRIMARY KEY (`appId`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4;

-- テーブル java_box.box_app: ~1 rows (approximately) のデータをダンプしています
DELETE FROM `box_app`;
/*!40000 ALTER TABLE `box_app` DISABLE KEYS */;
INSERT INTO `box_app` (`appId`, `name`, `route`, `jarUrl`, `jarName`, `jarMd5`, `stats`, `createTime`, `jarType`) VALUES
	(13, 'demo', '/demo', '/upload/154865981662140539.jar', 'java-box-dev.jar', '3b9e8e21db6198caa65cd26f39d1eafc', 1, '2019-01-28 15:17:04', 1);
/*!40000 ALTER TABLE `box_app` ENABLE KEYS */;

--  テーブル java_box.box_app_api の構造をダンプしています
CREATE TABLE IF NOT EXISTS `box_app_api` (
  `apiId` int(11) NOT NULL AUTO_INCREMENT,
  `appId` int(11) NOT NULL,
  `route` varchar(50) NOT NULL DEFAULT '0',
  `name` varchar(50) NOT NULL DEFAULT '0',
  `linkUrl` varchar(50) NOT NULL DEFAULT '0',
  `jarMd5` varchar(50) NOT NULL DEFAULT '0',
  `stats` smallint(6) NOT NULL DEFAULT '0',
  `runClass` varchar(50) NOT NULL,
  `runFunction` varchar(50) NOT NULL,
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`apiId`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4;

-- テーブル java_box.box_app_api: ~1 rows (approximately) のデータをダンプしています
DELETE FROM `box_app_api`;
/*!40000 ALTER TABLE `box_app_api` DISABLE KEYS */;
INSERT INTO `box_app_api` (`apiId`, `appId`, `route`, `name`, `linkUrl`, `jarMd5`, `stats`, `runClass`, `runFunction`, `createTime`) VALUES
	(11, 13, '/demo', 'demoapi4', '/demo/demo', '3b9e8e21db6198caa65cd26f39d1eafc', 0, 'com.demo.UserZdyWork', 'getTest', '2019-01-28 15:17:04');
/*!40000 ALTER TABLE `box_app_api` ENABLE KEYS */;

--  テーブル java_box.box_config の構造をダンプしています
CREATE TABLE IF NOT EXISTS `box_config` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `k` varchar(50) NOT NULL,
  `v` varchar(50) NOT NULL,
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COMMENT='读取配置文件信息';

-- テーブル java_box.box_config: ~0 rows (approximately) のデータをダンプしています
DELETE FROM `box_config`;
/*!40000 ALTER TABLE `box_config` DISABLE KEYS */;
INSERT INTO `box_config` (`id`, `k`, `v`, `createTime`) VALUES
	(1, 'keys', 'ahha', '2019-01-30 15:32:44'),
	(8, '123', '44444', '2019-01-30 16:38:07'),
	(9, '456', '4444', '2019-01-30 16:39:08'),
	(10, '456789', '123123123', '2019-01-30 16:40:27');
/*!40000 ALTER TABLE `box_config` ENABLE KEYS */;

--  テーブル java_box.box_project の構造をダンプしています
CREATE TABLE IF NOT EXISTS `box_project` (
  `projectId` int(11) NOT NULL AUTO_INCREMENT,
  `projectName` varchar(50) NOT NULL,
  `Route` varchar(200) NOT NULL,
  `openStat` int(11) NOT NULL,
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`projectId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- テーブル java_box.box_project: ~0 rows (approximately) のデータをダンプしています
DELETE FROM `box_project`;
/*!40000 ALTER TABLE `box_project` DISABLE KEYS */;
/*!40000 ALTER TABLE `box_project` ENABLE KEYS */;

--  テーブル java_box.box_public_jar の構造をダンプしています
CREATE TABLE IF NOT EXISTS `box_public_jar` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `jarName` varchar(50) NOT NULL,
  `jarUrl` varchar(50) NOT NULL,
  `jarMd5` varchar(50) NOT NULL,
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `isInit` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- テーブル java_box.box_public_jar: ~0 rows (approximately) のデータをダンプしています
DELETE FROM `box_public_jar`;
/*!40000 ALTER TABLE `box_public_jar` DISABLE KEYS */;
/*!40000 ALTER TABLE `box_public_jar` ENABLE KEYS */;

--  テーブル java_box.box_user の構造をダンプしています
CREATE TABLE IF NOT EXISTS `box_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(50) NOT NULL DEFAULT '0',
  `user_pass` varchar(50) NOT NULL DEFAULT '0',
  `user_stat` smallint(6) NOT NULL DEFAULT '0',
  `login_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `user_token` varchar(50) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- テーブル java_box.box_user: ~0 rows (approximately) のデータをダンプしています
DELETE FROM `box_user`;
/*!40000 ALTER TABLE `box_user` DISABLE KEYS */;
INSERT INTO `box_user` (`user_id`, `user_name`, `user_pass`, `user_stat`, `login_time`, `user_token`, `create_time`) VALUES
	(1, '123', '202cb962ac59075b964b07152d234b70', 0, '2018-09-28 13:39:52', '2019013015310580343', '2018-09-28 13:39:54');
/*!40000 ALTER TABLE `box_user` ENABLE KEYS */;

--  テーブル java_box.box_work_filter の構造をダンプしています
CREATE TABLE IF NOT EXISTS `box_work_filter` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `jarUrl` varchar(100) NOT NULL,
  `jarMd5` varchar(100) NOT NULL,
  `name` varchar(100) NOT NULL,
  `path` varchar(100) NOT NULL,
  `className` varchar(100) NOT NULL,
  `stat` int(11) NOT NULL DEFAULT '0',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4;

-- テーブル java_box.box_work_filter: ~1 rows (approximately) のデータをダンプしています
DELETE FROM `box_work_filter`;
/*!40000 ALTER TABLE `box_work_filter` DISABLE KEYS */;
INSERT INTO `box_work_filter` (`id`, `jarUrl`, `jarMd5`, `name`, `path`, `className`, `stat`, `createTime`) VALUES
	(16, '/upload/154865981662140539.jar', '3b9e8e21db6198caa65cd26f39d1eafc', '测试过滤器', '/demo/*', 'com.demo.TestWorkFilter', 0, '2019-01-28 15:17:04');
/*!40000 ALTER TABLE `box_work_filter` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
