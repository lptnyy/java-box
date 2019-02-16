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
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4;

-- テーブル java_box.box_app: ~1 rows (approximately) のデータをダンプしています
DELETE FROM `box_app`;
/*!40000 ALTER TABLE `box_app` DISABLE KEYS */;
INSERT INTO `box_app` (`appId`, `name`, `route`, `jarUrl`, `jarName`, `jarMd5`, `stats`, `createTime`, `jarType`) VALUES
	(16, 'demo', '/demo', '/upload/155021867192055962.jar', 'java-box-dev.jar', '2d249daf43f85dc11c736cd2ca130a55', 0, '2019-02-15 16:17:54', 1);
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
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4;

-- テーブル java_box.box_app_api: ~1 rows (approximately) のデータをダンプしています
DELETE FROM `box_app_api`;
/*!40000 ALTER TABLE `box_app_api` DISABLE KEYS */;
INSERT INTO `box_app_api` (`apiId`, `appId`, `route`, `name`, `linkUrl`, `jarMd5`, `stats`, `runClass`, `runFunction`, `createTime`) VALUES
	(14, 16, '/demo', 'demoapi4', '/demo/demo', '2d249daf43f85dc11c736cd2ca130a55', 0, 'com.demo.UserZdyWork', 'getTest', '2019-02-15 16:17:54');
/*!40000 ALTER TABLE `box_app_api` ENABLE KEYS */;

--  テーブル java_box.box_config の構造をダンプしています
CREATE TABLE IF NOT EXISTS `box_config` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `k` varchar(50) NOT NULL,
  `v` varchar(50) NOT NULL,
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=80 DEFAULT CHARSET=utf8mb4 COMMENT='读取配置文件信息';

-- テーブル java_box.box_config: ~25 rows (approximately) のデータをダンプしています
DELETE FROM `box_config`;
/*!40000 ALTER TABLE `box_config` DISABLE KEYS */;
INSERT INTO `box_config` (`id`, `k`, `v`, `createTime`) VALUES
	(55, 'service_api_get_fliters', '/butt/getfliters', '2019-02-15 13:44:18'),
	(56, 'service_api_get_app_api_list', '/butt/getAppApiList', '2019-02-15 13:44:18'),
	(57, 'service_api_get_app_list', '/butt/getApplist', '2019-02-15 13:44:18'),
	(58, 'service_api_get_id', '/butt/getAppId', '2019-02-15 13:44:18'),
	(59, 'service_api_base_url', 'http://localhost:8762', '2019-02-16 16:00:42'),
	(60, 'server_charset', 'utf-8', '2019-02-15 13:44:18'),
	(61, 'service_apt_down_jar', '/downJar?downUrl=', '2019-02-15 13:44:18'),
	(62, 'druidpoolPreparedStatements', '', '2019-02-15 16:15:49'),
	(63, 'druidpassword', '', '2019-02-15 16:15:59'),
	(64, 'druidusername', '', '2019-02-15 16:15:59'),
	(65, 'druidminEvictableIdleTimeMillis', '', '2019-02-15 16:15:59'),
	(66, 'druidinitialSize', '', '2019-02-15 16:15:59'),
	(67, 'druidmaxWait', '', '2019-02-15 16:15:59'),
	(68, 'druidurl', '', '2019-02-15 16:15:59'),
	(69, 'druiddriverClassName', '', '2019-02-15 16:15:59'),
	(70, 'druidtimeBetweenEvictionRunsMillis', '', '2019-02-15 16:15:59'),
	(71, 'druidconnectionProperties', '', '2019-02-15 16:15:59'),
	(72, 'druidmaxActive', '', '2019-02-15 16:15:59'),
	(73, 'druidtestOnReturn', '', '2019-02-15 16:15:59'),
	(74, 'druidmaxPoolPreparedStatementPerConnectionSize', '', '2019-02-15 16:15:59'),
	(75, 'druidtestOnBorrow', '', '2019-02-15 16:15:59'),
	(76, 'druidvalidationQuery', '', '2019-02-15 16:15:59'),
	(77, 'druidminIdle', '', '2019-02-15 16:15:59'),
	(78, 'druidtestWhileIdle', '123', '2019-02-15 16:16:40'),
	(79, 'service_api_get_connect_pool', '/butt/getConnectPools', '2019-02-16 17:30:01');
/*!40000 ALTER TABLE `box_config` ENABLE KEYS */;

--  テーブル java_box.box_connection_pool の構造をダンプしています
CREATE TABLE IF NOT EXISTS `box_connection_pool` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL DEFAULT '0',
  `className` varchar(50) NOT NULL DEFAULT '0',
  `methods` varchar(100) NOT NULL DEFAULT '0',
  `jarName` varchar(100) NOT NULL DEFAULT '0',
  `jarUrl` varchar(100) NOT NULL DEFAULT '0',
  `jarMd5` varchar(100) NOT NULL DEFAULT '0',
  `configStr` varchar(500) NOT NULL DEFAULT '0',
  `stat` int(11) NOT NULL DEFAULT '0',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COMMENT='Box  连接池插件';

-- テーブル java_box.box_connection_pool: ~1 rows (approximately) のデータをダンプしています
DELETE FROM `box_connection_pool`;
/*!40000 ALTER TABLE `box_connection_pool` DISABLE KEYS */;
INSERT INTO `box_connection_pool` (`id`, `name`, `className`, `methods`, `jarName`, `jarUrl`, `jarMd5`, `configStr`, `stat`, `createTime`) VALUES
	(4, '', 'com.wzy.plug.db.druid.BoxDruidDataSource', 'init;close;dataSources;dataSource;wait;wait;wait;equals;toString;hashCode;getClass;notify;notifyAll;', 'java-box-plug.jar', '/upload/155030094862617043.jar', '7568b944ca33f9dbbbf099342be3adfd', 'druidurl;druidusername;druidpassword;druiddriverClassName;druidinitialSize;druidminIdle;druidmaxActive;druidmaxWait;druidtimeBetweenEvictionRunsMillis;druidminEvictableIdleTimeMillis;druidvalidationQuery;druidtestWhileIdle;druidtestOnBorrow;druidtestOnReturn;druidpoolPreparedStatements;druidmaxPoolPreparedStatementPerConnectionSize;druidconnectionProperties;', 1, '2019-02-16 17:26:22');
/*!40000 ALTER TABLE `box_connection_pool` ENABLE KEYS */;

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
  `name` varchar(50) NOT NULL,
  `stat` int(11) NOT NULL,
  `jarName` varchar(50) NOT NULL,
  `jarUrl` varchar(50) NOT NULL,
  `jarMd5` varchar(50) NOT NULL,
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
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
	(1, '123', '202cb962ac59075b964b07152d234b70', 0, '2018-09-28 13:39:52', '20190216110635530', '2018-09-28 13:39:54');
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
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4;

-- テーブル java_box.box_work_filter: ~1 rows (approximately) のデータをダンプしています
DELETE FROM `box_work_filter`;
/*!40000 ALTER TABLE `box_work_filter` DISABLE KEYS */;
INSERT INTO `box_work_filter` (`id`, `jarUrl`, `jarMd5`, `name`, `path`, `className`, `stat`, `createTime`) VALUES
	(19, '/upload/155021867192055962.jar', '2d249daf43f85dc11c736cd2ca130a55', '测试过滤器', '/demo/*', 'com.demo.TestWorkFilter', 0, '2019-02-15 16:17:54');
/*!40000 ALTER TABLE `box_work_filter` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
