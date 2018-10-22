/*
 Navicat Premium Data Transfer

 Source Server         : MySQL Local
 Source Server Type    : MySQL
 Source Server Version : 50723
 Source Host           : localhost:3306
 Source Schema         : train

 Target Server Type    : MySQL
 Target Server Version : 50723
 File Encoding         : 65001

 Date: 13/09/2018 10:05:44
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for direction
-- ----------------------------
DROP TABLE IF EXISTS `direction`;
CREATE TABLE `direction`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `direction_int` int(11) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `active` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for light_activity
-- ----------------------------
DROP TABLE IF EXISTS `light_activity`;
CREATE TABLE `light_activity`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `train_activity_id` bigint(20) NOT NULL,
  `direction` int(11) NOT NULL,
  `card_id` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `machine_number` varchar(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `in_place_time` bigint(20) NOT NULL DEFAULT 0,
  `up_signal_time` bigint(20) NOT NULL DEFAULT 0,
  `down_signal_time` bigint(20) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for light_data
-- ----------------------------
DROP TABLE IF EXISTS `light_data`;
CREATE TABLE `light_data`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `raw` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `save_time` bigint(20) NOT NULL,
  `track` int(11) NOT NULL,
  `imei_10` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `send_time` datetime(0) NOT NULL,
  `direction` int(11) NOT NULL,
  `card_id` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `light_signal` int(11) NOT NULL,
  `voltage` decimal(3, 2) NOT NULL,
  `signal_value` int(11) NOT NULL,
  `acceleration` int(11) NOT NULL,
  `machine_number` varchar(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `data_type` tinyint(4) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 78796 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for pressure_data
-- ----------------------------
DROP TABLE IF EXISTS `pressure_data`;
CREATE TABLE `pressure_data`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `raw` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `save_time` bigint(20) NOT NULL,
  `machine_number` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `send_time` datetime(0) NOT NULL,
  `track` int(11) NOT NULL,
  `pressure` int(11) NOT NULL,
  `voltage` decimal(3, 2) NOT NULL,
  `crc` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `data_type` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 49303 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for track
-- ----------------------------
DROP TABLE IF EXISTS `track`;
CREATE TABLE `track`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `track_number` int(11) NOT NULL,
  `priority` int(11) NOT NULL,
  `remarks` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `track_unique`(`track_number`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for train_activity
-- ----------------------------
DROP TABLE IF EXISTS `train_activity`;
CREATE TABLE `train_activity`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `start_time` bigint(20) NOT NULL,
  `finish_time` bigint(20) NOT NULL DEFAULT 0,
  `track` int(11) NOT NULL,
  `pressure_machine_number` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `peak_pressure` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for worker
-- ----------------------------
DROP TABLE IF EXISTS `worker`;
CREATE TABLE `worker`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `card_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `remarks` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Procedure structure for getLightDataActiveDownSignalTime
-- ----------------------------
DROP PROCEDURE IF EXISTS `getLightDataActiveDownSignalTime`;
delimiter ;;
CREATE PROCEDURE `getLightDataActiveDownSignalTime`(IN trackNum INT, IN directionIn INT)
BEGIN
	DECLARE last_end_time BIGINT DEFAULT 0;
	SELECT save_time INTO last_end_time FROM light_data WHERE voltage = 0.01 AND track = trackNum AND direction = directionIn ORDER BY save_time DESC LIMIT 0, 1;
	SELECT send_time FROM light_data WHERE track = trackNum AND direction = directionIn AND save_time > last_end_time AND voltage = 0.04 ORDER BY save_time DESC LIMIT 0, 1;
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for getLightDataActiveInPlaceTime
-- ----------------------------
DROP PROCEDURE IF EXISTS `getLightDataActiveInPlaceTime`;
delimiter ;;
CREATE PROCEDURE `getLightDataActiveInPlaceTime`(IN trackNum INT, IN directionIn INT)
BEGIN
	DECLARE last_end_time BIGINT DEFAULT 0;
	SELECT save_time INTO last_end_time FROM light_data WHERE voltage = 0.01 AND track = trackNum AND direction = directionIn ORDER BY save_time DESC LIMIT 0, 1;
	SELECT send_time FROM light_data WHERE track = trackNum AND direction = directionIn AND save_time > last_end_time AND voltage = 0.02 ORDER BY save_time DESC LIMIT 0, 1;
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for getLightDataActiveRecent
-- ----------------------------
DROP PROCEDURE IF EXISTS `getLightDataActiveRecent`;
delimiter ;;
CREATE PROCEDURE `getLightDataActiveRecent`(IN trackNum INT, IN directionIn INT)
BEGIN
	DECLARE markVoltage DOUBLE DEFAULT 0.10;
	DECLARE markId BIGINT DEFAULT 0;
	DECLARE flag INT DEFAULT 0;
	SELECT id, voltage INTO markId, markVoltage FROM light_data WHERE track = trackNum AND direction = directionIn ORDER BY send_time DESC LIMIT 0, 1;
	WHILE markVoltage = 0.03 AND flag < 10 DO
		SELECT id, voltage INTO markId, markVoltage FROM light_data WHERE track = trackNum AND direction = directionIn ORDER BY send_time DESC LIMIT flag, 1;
		SET flag = flag + 1;
	END WHILE;
	IF markVoltage = 0.01  THEN
			SELECT id, raw, save_time, track, imei_10, send_time, direction, card_id, light_signal, voltage, signal_value, acceleration, machine_number FROM light_data WHERE id = -1;
	ELSE
			SELECT id, raw, save_time, track, imei_10, send_time, direction, card_id, light_signal, voltage, signal_value, acceleration, machine_number FROM light_data WHERE id = markId;
	END IF;
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for getLightDataActiveUpSignalTime
-- ----------------------------
DROP PROCEDURE IF EXISTS `getLightDataActiveUpSignalTime`;
delimiter ;;
CREATE PROCEDURE `getLightDataActiveUpSignalTime`(IN trackNum INT, IN directionIn INT)
BEGIN
	DECLARE last_end_time BIGINT DEFAULT 0;
	SELECT save_time INTO last_end_time FROM light_data WHERE voltage = 0.01 AND track = trackNum AND direction = directionIn ORDER BY save_time DESC LIMIT 0, 1;
	SELECT send_time FROM light_data WHERE track = trackNum AND direction = directionIn AND save_time > last_end_time AND voltage = 0 ORDER BY save_time DESC LIMIT 0, 1;
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for getPressureDataActive
-- ----------------------------
DROP PROCEDURE IF EXISTS `getPressureDataActive`;
delimiter ;;
CREATE PROCEDURE `getPressureDataActive`(IN trackNum INT)
BEGIN
	DECLARE last_end_time BIGINT DEFAULT 0;
	SELECT save_time INTO last_end_time FROM pressure_data WHERE pressure = 30 AND voltage = 0 AND track = trackNum ORDER BY save_time DESC LIMIT 0, 1;
	SELECT id, raw, save_time, machine_number, send_time, track, pressure, voltage, crc FROM pressure_data WHERE track = trackNum AND save_time > last_end_time ORDER BY save_time ASC;
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for getPressureDataActivePeak
-- ----------------------------
DROP PROCEDURE IF EXISTS `getPressureDataActivePeak`;
delimiter ;;
CREATE PROCEDURE `getPressureDataActivePeak`(IN trackNum INT)
BEGIN
	DECLARE last_end_time BIGINT DEFAULT 0;
	SELECT save_time INTO last_end_time FROM pressure_data WHERE pressure = 30 AND voltage = 0 AND track = trackNum ORDER BY save_time DESC LIMIT 0, 1;
	SELECT MAX(pressure) AS pressure FROM pressure_data WHERE track = trackNum AND save_time > last_end_time ORDER BY save_time ASC;
END
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
