CREATE DATABASE  IF NOT EXISTS `mdm_as_db` /*!40100 DEFAULT CHARACTER SET gbk */;
USE `mdm_as_db`;
-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: 114.215.177.133    Database: mdm_as_db
-- ------------------------------------------------------
-- Server version	5.6.27-log

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
-- Table structure for table `check`
--

DROP TABLE IF EXISTS `check`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `check` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Time` datetime NOT NULL,
  `Weather` varchar(100) DEFAULT NULL,
  `Temperature` float DEFAULT NULL,
  `UploadUserID` int(11) NOT NULL,
  `Remark` varchar(4000) DEFAULT NULL,
  `Title` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `check_upload_user_idx` (`UploadUserID`),
  CONSTRAINT `check_upload_user` FOREIGN KEY (`UploadUserID`) REFERENCES `mdm_common_db`.`tb_user` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `check_image`
--

DROP TABLE IF EXISTS `check_image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `check_image` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `CheckID` int(11) NOT NULL,
  `RecordID` int(11) NOT NULL,
  `ImagePath` varchar(500) NOT NULL,
  `Remark` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `check_image_check_idx` (`CheckID`),
  KEY `check_image_record_idx` (`RecordID`),
  CONSTRAINT `check_image_check` FOREIGN KEY (`CheckID`) REFERENCES `check` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `check_image_record` FOREIGN KEY (`RecordID`) REFERENCES `record` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `check_person`
--

DROP TABLE IF EXISTS `check_person`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `check_person` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `CheckID` int(11) NOT NULL,
  `UserID` int(11) NOT NULL,
  `Name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `check_person_check_idx` (`CheckID`),
  KEY `check_person_user_idx` (`UserID`),
  CONSTRAINT `check_person_check` FOREIGN KEY (`CheckID`) REFERENCES `check` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `check_person_user` FOREIGN KEY (`UserID`) REFERENCES `mdm_common_db`.`tb_user` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `check_video`
--

DROP TABLE IF EXISTS `check_video`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `check_video` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `CheckID` int(11) NOT NULL,
  `RecordID` int(11) NOT NULL,
  `VideoPath` varchar(500) NOT NULL,
  `Remark` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `check_video_check_idx` (`CheckID`),
  KEY `check_video_record_idx` (`RecordID`),
  CONSTRAINT `check_video_check` FOREIGN KEY (`CheckID`) REFERENCES `check` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `check_video_record` FOREIGN KEY (`RecordID`) REFERENCES `record` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `db_info`
--

DROP TABLE IF EXISTS `db_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `db_info` (
  `DB_Version` varchar(100) NOT NULL,
  PRIMARY KEY (`DB_Version`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `record`
--

DROP TABLE IF EXISTS `record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `record` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `CheckID` int(11) NOT NULL,
  `RegionID` int(11) DEFAULT NULL,
  `Lat` double DEFAULT NULL,
  `Lng` double DEFAULT NULL,
  `RecordTime` datetime NOT NULL,
  `UploadTime` datetime DEFAULT NULL,
  `SecurityStatus` int(11) DEFAULT NULL,
  `RegionStatus` varchar(1000) DEFAULT NULL,
  `AroundStatus` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `record_check_idx` (`CheckID`),
  KEY `record_region_idx` (`RegionID`),
  CONSTRAINT `record_check` FOREIGN KEY (`CheckID`) REFERENCES `check` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `record_region` FOREIGN KEY (`RegionID`) REFERENCES `mdm_common_db`.`tb_region` (`ID`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_channel`
--

DROP TABLE IF EXISTS `tb_channel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_channel` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `SensorID` int(11) NOT NULL,
  `ChannelName` varchar(100) NOT NULL,
  `TriggerLevel` float DEFAULT NULL,
  `Sensitivity` float DEFAULT NULL,
  `Shifting` float DEFAULT NULL,
  `Mzx` int(11) DEFAULT NULL,
  `Lzx` int(11) DEFAULT NULL,
  `Unit` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  `Range` int(11) DEFAULT NULL,
  `TriggerMode` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `tb_channel_sensor_idx` (`SensorID`),
  CONSTRAINT `tb_channel_sensor` FOREIGN KEY (`SensorID`) REFERENCES `tb_sensor_attri` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_channel_data`
--

DROP TABLE IF EXISTS `tb_channel_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_channel_data` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `SensorID` int(11) NOT NULL,
  `ChannelID` int(11) NOT NULL,
  `FileID` varchar(100) NOT NULL,
  `Time` datetime NOT NULL,
  `DataCount` int(11) DEFAULT NULL,
  `RecordLength` float DEFAULT NULL,
  `Data` longtext,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `channel_unique_three` (`SensorID`,`ChannelID`,`FileID`,`Time`),
  KEY `tb_channel_data_sensor_idx` (`SensorID`),
  KEY `tb_channel_data_channel_idx` (`ChannelID`),
  KEY `tb_channel_data_file_time_idx` (`FileID`,`Time`),
  KEY `tb_channel_data_all_idx` (`SensorID`,`ChannelID`,`FileID`,`Time`),
  KEY `tb_channel_data_sensor_channel_time` (`SensorID`,`ChannelID`,`Time`),
  KEY `tb_channel_data_sensor_time` (`SensorID`,`Time`),
  CONSTRAINT `tb_channel_data_channel` FOREIGN KEY (`ChannelID`) REFERENCES `tb_channel` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tb_channel_data_sensor` FOREIGN KEY (`SensorID`) REFERENCES `tb_sensor_attri` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_da_data`
--

DROP TABLE IF EXISTS `tb_da_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_da_data` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `SensorID` int(11) NOT NULL,
  `Time` datetime NOT NULL,
  `Angel` double NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `da_unique` (`SensorID`,`Time`),
  KEY `da_index` (`SensorID`,`Time`),
  CONSTRAINT `da_sensor` FOREIGN KEY (`SensorID`) REFERENCES `tb_sensor_attri` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_da_data_avg`
--

DROP TABLE IF EXISTS `tb_da_data_avg`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_da_data_avg` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `SensorID` int(11) NOT NULL,
  `Time` datetime NOT NULL,
  `Angel` double NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `da_avg_unique` (`SensorID`,`Time`),
  KEY `da_avg_index` (`SensorID`,`Time`),
  CONSTRAINT `da_avg_sensor` FOREIGN KEY (`SensorID`) REFERENCES `tb_sensor_attri` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_db_data`
--

DROP TABLE IF EXISTS `tb_db_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_db_data` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `SensorID` int(11) NOT NULL,
  `Time` datetime NOT NULL,
  `Lenth` double NOT NULL,
  `Angle` double NOT NULL,
  `SafeHeight` double NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `dbunique` (`SensorID`,`Time`),
  KEY `dbindex` (`SensorID`,`Time`),
  CONSTRAINT `fk_db_sensor` FOREIGN KEY (`SensorID`) REFERENCES `tb_sensor_attri` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_db_data_avg`
--

DROP TABLE IF EXISTS `tb_db_data_avg`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_db_data_avg` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `SensorID` int(11) NOT NULL,
  `Time` datetime NOT NULL,
  `Lenth` double NOT NULL,
  `Angle` double NOT NULL,
  `SafeHeight` double NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `dbunique` (`SensorID`,`Time`),
  KEY `dbindex` (`SensorID`,`Time`),
  CONSTRAINT `tb_db_data_avg_ibfk_1` FOREIGN KEY (`SensorID`) REFERENCES `tb_sensor_attri` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_dm_data`
--

DROP TABLE IF EXISTS `tb_dm_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_dm_data` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `SensorID` int(11) NOT NULL,
  `Time` datetime NOT NULL,
  `Disp_a` double NOT NULL,
  `Disp_b` double NOT NULL,
  `Velocity_a` double NOT NULL,
  `Velocity_b` double NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `dmunique` (`SensorID`,`Time`),
  KEY `dmindex` (`Time`,`SensorID`),
  CONSTRAINT `fk_dm_sensor` FOREIGN KEY (`SensorID`) REFERENCES `tb_sensor_attri` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_dm_data_avg`
--

DROP TABLE IF EXISTS `tb_dm_data_avg`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_dm_data_avg` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `SensorID` int(11) NOT NULL,
  `Time` datetime NOT NULL,
  `Disp_a` double NOT NULL,
  `Disp_b` double NOT NULL,
  `Velocity_a` double NOT NULL,
  `Velocity_b` double NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `dmunique` (`SensorID`,`Time`),
  KEY `dmindex` (`Time`,`SensorID`),
  CONSTRAINT `tb_dm_data_avg_ibfk_1` FOREIGN KEY (`SensorID`) REFERENCES `tb_sensor_attri` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_ds2_data`
--

DROP TABLE IF EXISTS `tb_ds2_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_ds2_data` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `SensorID` int(11) NOT NULL,
  `Time` datetime NOT NULL,
  `Value` double NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ds2_unique` (`SensorID`,`Time`),
  KEY `ds2_index` (`SensorID`,`Time`),
  CONSTRAINT `ds2_sensor` FOREIGN KEY (`SensorID`) REFERENCES `tb_sensor_attri` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_ds2_data_avg`
--

DROP TABLE IF EXISTS `tb_ds2_data_avg`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_ds2_data_avg` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `SensorID` int(11) NOT NULL,
  `Time` datetime NOT NULL,
  `Value` double NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ds2_avg_unique` (`SensorID`,`Time`),
  KEY `ds2_avg_index` (`SensorID`,`Time`),
  CONSTRAINT `ds2_avg_sensor` FOREIGN KEY (`SensorID`) REFERENCES `tb_sensor_attri` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_ds_data`
--

DROP TABLE IF EXISTS `tb_ds_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_ds_data` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `SensorID` int(11) NOT NULL,
  `Time` datetime NOT NULL,
  `Value` double NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ds_unique` (`SensorID`,`Time`),
  KEY `ds_index` (`SensorID`,`Time`),
  CONSTRAINT `ds_sensor` FOREIGN KEY (`SensorID`) REFERENCES `tb_sensor_attri` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=39710 DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_ds_data_avg`
--

DROP TABLE IF EXISTS `tb_ds_data_avg`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_ds_data_avg` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `SensorID` int(11) NOT NULL,
  `Time` datetime NOT NULL,
  `Value` double NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ds_avg_unique` (`SensorID`,`Time`),
  KEY `ds_avg_index` (`SensorID`,`Time`),
  CONSTRAINT `ds_avg_sensor` FOREIGN KEY (`SensorID`) REFERENCES `tb_sensor_attri` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=173 DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_event_record`
--

DROP TABLE IF EXISTS `tb_event_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_event_record` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Time` datetime NOT NULL,
  `Record` longtext NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_fl_data`
--

DROP TABLE IF EXISTS `tb_fl_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_fl_data` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `SensorID` int(11) NOT NULL,
  `Time` datetime NOT NULL,
  `Value` double NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `flunique` (`SensorID`,`Time`),
  KEY `flindex` (`SensorID`,`Time`),
  CONSTRAINT `fk_fl_sensor` FOREIGN KEY (`SensorID`) REFERENCES `tb_sensor_attri` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_fl_data_avg`
--

DROP TABLE IF EXISTS `tb_fl_data_avg`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_fl_data_avg` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `SensorID` int(11) NOT NULL,
  `Time` datetime NOT NULL,
  `Value` double NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `flunique` (`SensorID`,`Time`),
  KEY `flindex` (`SensorID`,`Time`),
  CONSTRAINT `tb_fl_data_avg_ibfk_1` FOREIGN KEY (`SensorID`) REFERENCES `tb_sensor_attri` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_group`
--

DROP TABLE IF EXISTS `tb_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_group` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `SensorType` int(11) NOT NULL,
  `Name` varchar(45) NOT NULL,
  `Alias` varchar(45) DEFAULT NULL,
  `Note` varchar(100) DEFAULT NULL,
  `ExValues` longtext,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `gr_unique` (`SensorType`,`Name`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_hl_data`
--

DROP TABLE IF EXISTS `tb_hl_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_hl_data` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `SensorID` int(11) NOT NULL,
  `Time` datetime NOT NULL,
  `Height` double NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `hlunique` (`SensorID`,`Time`),
  KEY `hlindex` (`Time`,`SensorID`),
  CONSTRAINT `fk_hl_sensor` FOREIGN KEY (`SensorID`) REFERENCES `tb_sensor_attri` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_hl_data_avg`
--

DROP TABLE IF EXISTS `tb_hl_data_avg`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_hl_data_avg` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `SensorID` int(11) NOT NULL,
  `Time` datetime NOT NULL,
  `Height` double NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `hlunique` (`SensorID`,`Time`),
  KEY `hlindex` (`Time`,`SensorID`),
  CONSTRAINT `tb_hl_data_avg_ibfk_1` FOREIGN KEY (`SensorID`) REFERENCES `tb_sensor_attri` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_image_meta`
--

DROP TABLE IF EXISTS `tb_image_meta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_image_meta` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ProjID` int(11) NOT NULL,
  `SensorID` int(11) DEFAULT NULL,
  `ImagePath` varchar(500) NOT NULL,
  `Note` varchar(500) DEFAULT NULL,
  `IsProjImage` bit(1) NOT NULL,
  `UploadTime` datetime NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `image_proj_idx` (`ProjID`),
  KEY `image_sensor_idx` (`SensorID`),
  CONSTRAINT `image_proj` FOREIGN KEY (`ProjID`) REFERENCES `mdm_common_db`.`tb_proj_info` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `image_sensor` FOREIGN KEY (`SensorID`) REFERENCES `tb_sensor_attri` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_monitor_area`
--

DROP TABLE IF EXISTS `tb_monitor_area`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_monitor_area` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `AreaName` varchar(50) NOT NULL,
  `CenterPoint` varchar(100) DEFAULT NULL,
  `Boundary` longtext,
  `Note` longtext,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_monitor_type`
--

DROP TABLE IF EXISTS `tb_monitor_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_monitor_type` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `SensorType` int(11) NOT NULL DEFAULT '0',
  `TypeName` varchar(50) NOT NULL,
  `Name` varchar(50) NOT NULL,
  `IsValid` bit(1) NOT NULL DEFAULT b'1',
  `Values` varchar(100) NOT NULL,
  `DisplayOrder` int(11) NOT NULL DEFAULT '0',
  `ShowInterval` int(11) NOT NULL DEFAULT '24',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `SensorType_UNIQUE` (`SensorType`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_mp_data`
--

DROP TABLE IF EXISTS `tb_mp_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_mp_data` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `SensorID` int(11) NOT NULL DEFAULT '-1',
  `Time` datetime NOT NULL,
  `Disp_X` double NOT NULL,
  `Disp_Y` double NOT NULL,
  `Disp_H` double NOT NULL,
  `Velocity_X` double NOT NULL,
  `Velocity_Y` double NOT NULL,
  `Velocity_H` double NOT NULL,
  `Acceler_X` double NOT NULL,
  `Acceler_Y` double NOT NULL,
  `Acceler_H` double NOT NULL,
  `Azimuth` double NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `MpUnique` (`Time`,`SensorID`),
  KEY `MpIndex` (`Time`,`SensorID`),
  KEY `fk_mp_sensor` (`SensorID`),
  CONSTRAINT `fk_mp_sensor` FOREIGN KEY (`SensorID`) REFERENCES `tb_sensor_attri` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_mp_data_avg`
--

DROP TABLE IF EXISTS `tb_mp_data_avg`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_mp_data_avg` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `SensorID` int(11) NOT NULL DEFAULT '-1',
  `Time` datetime NOT NULL,
  `Disp_X` double NOT NULL,
  `Disp_Y` double NOT NULL,
  `Disp_H` double NOT NULL,
  `Velocity_X` double NOT NULL,
  `Velocity_Y` double NOT NULL,
  `Velocity_H` double NOT NULL,
  `Acceler_X` double NOT NULL,
  `Acceler_Y` double NOT NULL,
  `Acceler_H` double NOT NULL,
  `Azimuth` double NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `MpUnique` (`Time`,`SensorID`),
  KEY `MpIndex` (`Time`,`SensorID`),
  KEY `fk_mp_sensor` (`SensorID`),
  CONSTRAINT `tb_mp_data_avg_ibfk_1` FOREIGN KEY (`SensorID`) REFERENCES `tb_sensor_attri` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_rg_data`
--

DROP TABLE IF EXISTS `tb_rg_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_rg_data` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `SensorID` int(11) NOT NULL,
  `Time` datetime NOT NULL,
  `Rainfall` double NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `RgUnique` (`SensorID`,`Time`),
  KEY `RgIndex` (`Time`,`SensorID`),
  CONSTRAINT `fk_rg_sensor` FOREIGN KEY (`SensorID`) REFERENCES `tb_sensor_attri` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=19046 DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_rg_data_avg`
--

DROP TABLE IF EXISTS `tb_rg_data_avg`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_rg_data_avg` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `SensorID` int(11) NOT NULL,
  `Time` datetime NOT NULL,
  `Rainfall` double NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `RgUnique` (`SensorID`,`Time`),
  KEY `RgIndex` (`Time`,`SensorID`),
  CONSTRAINT `tb_rg_data_avg_ibfk_1` FOREIGN KEY (`SensorID`) REFERENCES `tb_sensor_attri` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=89 DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_sensor_attri`
--

DROP TABLE IF EXISTS `tb_sensor_attri`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_sensor_attri` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(45) DEFAULT NULL,
  `Alias` varchar(45) DEFAULT NULL,
  `Location` varchar(100) DEFAULT NULL COMMENT 'JSON鏍煎紡{BLH:{},XYZ:{}}',
  `Status` int(11) NOT NULL,
  `GroupID` int(11) DEFAULT NULL,
  `Note` text,
  `SensorType` int(11) NOT NULL,
  `IsValid` bit(1) NOT NULL DEFAULT b'1',
  `ExValues` longtext,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `Sensor_Name_UNIQUE` (`Name`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_sensor_warn_value`
--

DROP TABLE IF EXISTS `tb_sensor_warn_value`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_sensor_warn_value` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `SensorID` int(11) NOT NULL,
  `SensorType` int(11) NOT NULL,
  `WarnType` varchar(50) NOT NULL,
  `WarnLevel` smallint(6) NOT NULL,
  `Value` double NOT NULL,
  `IsValid` bit(1) NOT NULL,
  `Remark` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `tb_sensor_warn_value_sensor_idx` (`SensorID`),
  CONSTRAINT `tb_sensor_warn_value_sensor` FOREIGN KEY (`SensorID`) REFERENCES `tb_sensor_attri` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_smc_data`
--

DROP TABLE IF EXISTS `tb_smc_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_smc_data` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `SensorID` int(11) NOT NULL,
  `Time` datetime NOT NULL,
  `Value` double NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `smc_unique` (`SensorID`,`Time`),
  KEY `smc_index` (`SensorID`,`Time`),
  CONSTRAINT `smc_sensor` FOREIGN KEY (`SensorID`) REFERENCES `tb_sensor_attri` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=34105 DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_smc_data_avg`
--

DROP TABLE IF EXISTS `tb_smc_data_avg`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_smc_data_avg` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `SensorID` int(11) NOT NULL,
  `Time` datetime NOT NULL,
  `Value` double NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `smc_avg_unique` (`SensorID`,`Time`),
  KEY `smc_avg_inde` (`SensorID`,`Time`),
  CONSTRAINT `smc_avg_sensor` FOREIGN KEY (`SensorID`) REFERENCES `tb_sensor_attri` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=153 DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_socket`
--

DROP TABLE IF EXISTS `tb_socket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_socket` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Type` smallint(6) DEFAULT NULL,
  `Model` smallint(6) NOT NULL DEFAULT '0',
  `Addr` varchar(50) NOT NULL DEFAULT '',
  `Port` int(11) NOT NULL DEFAULT '0',
  `Remark` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`ID`,`Model`,`Addr`,`Port`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_st_data`
--

DROP TABLE IF EXISTS `tb_st_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_st_data` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `SensorID` int(11) NOT NULL,
  `Time` datetime NOT NULL,
  `Depth` double NOT NULL COMMENT '鍩嬫繁',
  `Stage` double NOT NULL COMMENT '姘翠綅',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `stunique` (`SensorID`,`Time`),
  KEY `stindex` (`Time`,`SensorID`),
  CONSTRAINT `fk_st_sensor` FOREIGN KEY (`SensorID`) REFERENCES `tb_sensor_attri` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_st_data_avg`
--

DROP TABLE IF EXISTS `tb_st_data_avg`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_st_data_avg` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `SensorID` int(11) NOT NULL,
  `Time` datetime NOT NULL,
  `Depth` double NOT NULL COMMENT '鍩嬫繁',
  `Stage` double NOT NULL COMMENT '姘翠綅',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `stunique` (`SensorID`,`Time`),
  KEY `stindex` (`Time`,`SensorID`),
  CONSTRAINT `tb_st_data_avg_ibfk_1` FOREIGN KEY (`SensorID`) REFERENCES `tb_sensor_attri` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_td_data`
--

DROP TABLE IF EXISTS `tb_td_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_td_data` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `SensorID` int(11) NOT NULL,
  `Time` datetime DEFAULT NULL,
  `Value` double NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `td_unique` (`SensorID`,`Time`),
  KEY `td_index` (`SensorID`,`Time`),
  CONSTRAINT `td_sensor` FOREIGN KEY (`SensorID`) REFERENCES `tb_sensor_attri` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_td_data_avg`
--

DROP TABLE IF EXISTS `tb_td_data_avg`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_td_data_avg` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `SensorID` int(11) NOT NULL,
  `Time` datetime DEFAULT NULL,
  `Value` double NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `td_avg_unique` (`SensorID`,`Time`),
  KEY `td_avg_index` (`SensorID`,`Time`),
  CONSTRAINT `td_avg_sensor` FOREIGN KEY (`SensorID`) REFERENCES `tb_sensor_attri` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_tpr_data`
--

DROP TABLE IF EXISTS `tb_tpr_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_tpr_data` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `SensorID` int(11) NOT NULL,
  `Time` datetime NOT NULL,
  `Temperature` double NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `tprunique` (`SensorID`,`Time`),
  KEY `tprindex` (`Time`,`SensorID`),
  CONSTRAINT `fk_tpr_sensor` FOREIGN KEY (`SensorID`) REFERENCES `tb_sensor_attri` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_tpr_data_avg`
--

DROP TABLE IF EXISTS `tb_tpr_data_avg`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_tpr_data_avg` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `SensorID` int(11) NOT NULL,
  `Time` datetime NOT NULL,
  `Temperature` double NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `tprunique` (`SensorID`,`Time`),
  KEY `tprindex` (`Time`,`SensorID`),
  CONSTRAINT `tb_tpr_data_avg_ibfk_1` FOREIGN KEY (`SensorID`) REFERENCES `tb_sensor_attri` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_tps2_data`
--

DROP TABLE IF EXISTS `tb_tps2_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_tps2_data` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `SensorID` int(11) NOT NULL,
  `Time` datetime NOT NULL,
  `Disp_X` double NOT NULL,
  `Disp_Y` double NOT NULL,
  `Disp_H` double NOT NULL,
  `Velocity_X` double NOT NULL,
  `Velocity_Y` double NOT NULL,
  `Velocity_H` double NOT NULL,
  `Azimuth` double NOT NULL,
  `HzAngle` double NOT NULL,
  `VertAngle` double NOT NULL,
  `Distance` double NOT NULL,
  `RawDisp_X` double NOT NULL,
  `RawDisp_Y` double NOT NULL,
  `RawDisp_H` double NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID` (`ID`),
  KEY `SensorID` (`SensorID`),
  CONSTRAINT `fk_tps2_sensor` FOREIGN KEY (`SensorID`) REFERENCES `tb_sensor_attri` (`ID`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_tps2_data_avg`
--

DROP TABLE IF EXISTS `tb_tps2_data_avg`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_tps2_data_avg` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `SensorID` int(11) NOT NULL,
  `Time` datetime NOT NULL,
  `Disp_X` double NOT NULL,
  `Disp_Y` double NOT NULL,
  `Disp_H` double NOT NULL,
  `Velocity_X` double NOT NULL,
  `Velocity_Y` double NOT NULL,
  `Velocity_H` double NOT NULL,
  `Azimuth` double NOT NULL,
  `HzAngle` double NOT NULL,
  `VertAngle` double NOT NULL,
  `Distance` double NOT NULL,
  `RawDisp_X` double NOT NULL,
  `RawDisp_Y` double NOT NULL,
  `RawDisp_H` double NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID` (`ID`),
  KEY `SensorID` (`SensorID`),
  CONSTRAINT `fk_tps2_avg_sensor` FOREIGN KEY (`SensorID`) REFERENCES `tb_sensor_attri` (`ID`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_tps2_konwn_point`
--

DROP TABLE IF EXISTS `tb_tps2_konwn_point`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_tps2_konwn_point` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(45) NOT NULL,
  `Disp_X` double DEFAULT NULL,
  `Dsip_Y` double DEFAULT NULL,
  `Disp_H` double DEFAULT NULL,
  `Note` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_tps2_observe_station`
--

DROP TABLE IF EXISTS `tb_tps2_observe_station`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_tps2_observe_station` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(100) NOT NULL,
  `Disp_X` double DEFAULT NULL,
  `Disp_Y` double DEFAULT NULL,
  `Disp_H` double DEFAULT NULL,
  `LookName` varchar(100) DEFAULT NULL,
  `LookDisp_X` double DEFAULT NULL,
  `LookDisp_Y` double DEFAULT NULL,
  `LookDisp_H` double DEFAULT NULL,
  `MachineHeight` double DEFAULT NULL,
  `GlassHeight` double DEFAULT NULL,
  `LookAzimuth` double DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_tps2_project`
--

DROP TABLE IF EXISTS `tb_tps2_project`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_tps2_project` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ProjName` varchar(45) DEFAULT NULL,
  `Operator` varchar(45) DEFAULT NULL,
  `ProjTime` datetime DEFAULT NULL,
  `Weather` varchar(255) DEFAULT NULL,
  `ProjInfo` text,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_tps_data`
--

DROP TABLE IF EXISTS `tb_tps_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_tps_data` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `SensorID` int(11) NOT NULL DEFAULT '-1',
  `Time` datetime NOT NULL,
  `Disp_X` double NOT NULL,
  `Disp_Y` double NOT NULL,
  `Disp_H` double NOT NULL,
  `Velocity_X` double NOT NULL,
  `Velocity_Y` double NOT NULL,
  `Velocity_H` double NOT NULL,
  `Azimuth` double NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `TpsUnique` (`Time`,`SensorID`),
  KEY `TpsIndex` (`Time`,`SensorID`),
  KEY `fk_tps_sensor` (`SensorID`),
  CONSTRAINT `fk_tps_sensor` FOREIGN KEY (`SensorID`) REFERENCES `tb_sensor_attri` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_tps_data_avg`
--

DROP TABLE IF EXISTS `tb_tps_data_avg`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_tps_data_avg` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `SensorID` int(11) NOT NULL DEFAULT '-1',
  `Time` datetime NOT NULL,
  `Disp_X` double NOT NULL,
  `Disp_Y` double NOT NULL,
  `Disp_H` double NOT NULL,
  `Velocity_X` double NOT NULL,
  `Velocity_Y` double NOT NULL,
  `Velocity_H` double NOT NULL,
  `Azimuth` double NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `TpsAvgUnique` (`Time`,`SensorID`),
  KEY `TpsAvgIndex` (`Time`,`SensorID`),
  KEY `fk_tps_avg_sensor` (`SensorID`),
  CONSTRAINT `tb_tps_data_avg_ibfk_1` FOREIGN KEY (`SensorID`) REFERENCES `tb_sensor_attri` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_volume`
--

DROP TABLE IF EXISTS `tb_volume`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_volume` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Stage` double DEFAULT NULL,
  `Volume` double DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_warn_log`
--

DROP TABLE IF EXISTS `tb_warn_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_warn_log` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `SensorID` int(11) DEFAULT NULL,
  `SensorType` int(11) DEFAULT NULL,
  `Time` datetime DEFAULT NULL,
  `WarnMainType` varchar(20) DEFAULT NULL,
  `WarnChildType` varchar(20) DEFAULT NULL,
  `WarnLevel` smallint(6) DEFAULT NULL,
  `SendMessage` varchar(800) DEFAULT NULL,
  `SendFlag` bit(1) DEFAULT NULL,
  `Dealed` bit(1) DEFAULT NULL,
  `DealName` varchar(30) DEFAULT NULL,
  `DealPhone` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `indexlogwarn` (`Time`),
  KEY `fk_sensor_idx` (`SensorID`),
  CONSTRAINT `fk_warnlog_sensor` FOREIGN KEY (`SensorID`) REFERENCES `tb_sensor_attri` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=390 DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_warn_person`
--

DROP TABLE IF EXISTS `tb_warn_person`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_warn_person` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `UserName` varchar(30) NOT NULL,
  `UserWarnLevel` smallint(6) DEFAULT NULL,
  `UserPhone` varchar(30) DEFAULT NULL,
  `UserEmail` varchar(80) DEFAULT NULL,
  `OnLine` bit(1) DEFAULT NULL,
  `Position` varchar(50) DEFAULT NULL,
  `CompanyID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `warn_person_company_idx` (`CompanyID`),
  CONSTRAINT `warn_person_company` FOREIGN KEY (`CompanyID`) REFERENCES `mdm_common_db`.`tb_company` (`ID`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_warn_revise`
--

DROP TABLE IF EXISTS `tb_warn_revise`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_warn_revise` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `SensorID` int(11) NOT NULL,
  `WarnChildType` varchar(50) NOT NULL,
  `Value` double NOT NULL,
  `IsValid` bit(1) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `wr_sensor_idx` (`SensorID`),
  CONSTRAINT `wr_sensor` FOREIGN KEY (`SensorID`) REFERENCES `tb_sensor_attri` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_warn_value`
--

DROP TABLE IF EXISTS `tb_warn_value`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_warn_value` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `SensorType` int(11) NOT NULL DEFAULT '0',
  `WarnType` varchar(50) NOT NULL,
  `WarnLevel` smallint(6) NOT NULL DEFAULT '0',
  `Value` double(20,4) NOT NULL,
  `IsValid` bit(1) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `uq_warn` (`WarnType`,`WarnLevel`,`SensorType`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_weather_data`
--

DROP TABLE IF EXISTS `tb_weather_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_weather_data` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `SensorID` int(11) NOT NULL,
  `Time` datetime NOT NULL,
  `Value1_1` double NOT NULL,
  `Value1_2` double NOT NULL,
  `Value1_3` double NOT NULL,
  `Value2_1` double NOT NULL,
  `Value2_2` double NOT NULL,
  `Value2_3` double NOT NULL,
  `Value3_1` double NOT NULL,
  `Value3_2` double NOT NULL,
  `Value3_3` double NOT NULL,
  `Value4_1` double NOT NULL,
  `Value4_2` double NOT NULL,
  `Value4_3` double NOT NULL,
  `Value5_1` double NOT NULL,
  `Value5_2` double NOT NULL,
  `Value5_3` double NOT NULL,
  `Value6_1` double NOT NULL,
  `Value6_2` double NOT NULL,
  `Value6_3` double NOT NULL,
  `Temperature` double NOT NULL,
  `PowerVoltage` double NOT NULL,
  `DeviceVoltage` double NOT NULL,
  `AirPressure` double NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `weatheruniqueavg1411` (`SensorID`,`Time`),
  KEY `indexweatherdavg1411` (`Time`,`SensorID`),
  CONSTRAINT `fk_weather_sensor` FOREIGN KEY (`SensorID`) REFERENCES `tb_sensor_attri` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_weather_data_avg`
--

DROP TABLE IF EXISTS `tb_weather_data_avg`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_weather_data_avg` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `SensorID` int(11) NOT NULL,
  `Time` datetime NOT NULL,
  `Value1_1` double NOT NULL,
  `Value1_2` double NOT NULL,
  `Value1_3` double NOT NULL,
  `Value2_1` double NOT NULL,
  `Value2_2` double NOT NULL,
  `Value2_3` double NOT NULL,
  `Value3_1` double NOT NULL,
  `Value3_2` double NOT NULL,
  `Value3_3` double NOT NULL,
  `Value4_1` double NOT NULL,
  `Value4_2` double NOT NULL,
  `Value4_3` double NOT NULL,
  `Value5_1` double NOT NULL,
  `Value5_2` double NOT NULL,
  `Value5_3` double NOT NULL,
  `Value6_1` double NOT NULL,
  `Value6_2` double NOT NULL,
  `Value6_3` double NOT NULL,
  `Temperature` double NOT NULL,
  `PowerVoltage` double NOT NULL,
  `DeviceVoltage` double NOT NULL,
  `AirPressure` double NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `weatheruniqueavg1411tq` (`SensorID`,`Time`),
  KEY `we_avg_index` (`SensorID`,`Time`),
  CONSTRAINT `fk_weather_sensortq` FOREIGN KEY (`SensorID`) REFERENCES `tb_sensor_attri` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_wt_data`
--

DROP TABLE IF EXISTS `tb_wt_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_wt_data` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `SensorID` int(11) NOT NULL,
  `Time` datetime NOT NULL,
  `Stage` double NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `wtunique` (`SensorID`,`Time`),
  KEY `wtindex` (`Time`,`SensorID`),
  CONSTRAINT `fk_wt_sensor` FOREIGN KEY (`SensorID`) REFERENCES `tb_sensor_attri` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_wt_data_avg`
--

DROP TABLE IF EXISTS `tb_wt_data_avg`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_wt_data_avg` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `SensorID` int(11) NOT NULL,
  `Time` datetime NOT NULL,
  `Stage` double NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `wtunique` (`SensorID`,`Time`),
  KEY `wtindex` (`Time`,`SensorID`),
  CONSTRAINT `tb_wt_data_avg_ibfk_1` FOREIGN KEY (`SensorID`) REFERENCES `tb_sensor_attri` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-07-06 13:04:18
