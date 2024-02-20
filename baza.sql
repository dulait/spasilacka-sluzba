/*
SQLyog Community v13.2.1 (64 bit)
MySQL - 10.4.28-MariaDB : Database - spasilacka_sluzba
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`spasilacka_sluzba` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;

USE `spasilacka_sluzba`;

/*Table structure for table `koordinator` */

DROP TABLE IF EXISTS `koordinator`;

CREATE TABLE `koordinator` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `korisnickoIme` varchar(255) NOT NULL,
  `lozinka` varchar(255) NOT NULL,
  `ime` varchar(255) NOT NULL,
  `prezime` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `koordinator` */

insert  into `koordinator`(`id`,`korisnickoIme`,`lozinka`,`ime`,`prezime`) values 
(1,'admin','admin','Admin','Adminovic');

/*Table structure for table `spasilac` */

DROP TABLE IF EXISTS `spasilac`;

CREATE TABLE `spasilac` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `ime` varchar(255) NOT NULL,
  `prezime` varchar(255) NOT NULL,
  `jmbg` varchar(13) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `jmbg` (`jmbg`),
  CHECK (octet_length(`jmbg`) = 13 and `jmbg` regexp '^[0-9]+$')
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Table structure for table `smena` */

DROP TABLE IF EXISTS `smena`;

CREATE TABLE `smena` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `pocetak` int(11) NOT NULL,
  `kraj` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UNIQUE` (`pocetak`,`kraj`),
  CONSTRAINT `CONSTRAINT_1` CHECK (`pocetak` < `kraj`),
  CONSTRAINT `CONSTRAINT_2` CHECK (`pocetak` > -1),
  CONSTRAINT `CONSTRAINT_3` CHECK (`kraj` < 25)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Table structure for table `raspored` */

DROP TABLE IF EXISTS `raspored`;

CREATE TABLE `raspored` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `datum` date NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `datum` (`datum`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Table structure for table `angazovanje` */

DROP TABLE IF EXISTS `angazovanje`;

CREATE TABLE `angazovanje` (
  `spasilacId` int(10) unsigned DEFAULT NULL,
  `smenaId` int(10) unsigned DEFAULT NULL,
  `rasporedId` int(10) unsigned DEFAULT NULL,
  UNIQUE KEY `UNIQUE` (`spasilacId`,`smenaId`,`rasporedId`),
  KEY `angazovanje_ibfk_1` (`spasilacId`),
  KEY `angazovanje_ibfk_2` (`smenaId`),
  KEY `angazovanje_ibfk_3` (`rasporedId`),
  CONSTRAINT `angazovanje_ibfk_1` FOREIGN KEY (`spasilacId`) REFERENCES `spasilac` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `angazovanje_ibfk_2` FOREIGN KEY (`smenaId`) REFERENCES `smena` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `angazovanje_ibfk_3` FOREIGN KEY (`rasporedId`) REFERENCES `raspored` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Table structure for table `izvestaj` */

DROP TABLE IF EXISTS `izvestaj`;

CREATE TABLE `izvestaj` (
  `spasilacId` int(10) unsigned DEFAULT NULL,
  `smenaId` int(10) unsigned DEFAULT NULL,
  `rasporedId` int(10) unsigned DEFAULT NULL,
  `opis` longtext DEFAULT NULL,
  UNIQUE KEY `UNIQUE` (`spasilacId`,`smenaId`,`rasporedId`),
  KEY `izvestaj_ibfk_1` (`spasilacId`),
  KEY `izvestaj_ibfk_2` (`smenaId`),
  KEY `izvestaj_ibfk_3` (`rasporedId`),
  CONSTRAINT `izvestaj_ibfk_1` FOREIGN KEY (`spasilacId`) REFERENCES `spasilac` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `izvestaj_ibfk_2` FOREIGN KEY (`smenaId`) REFERENCES `smena` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `izvestaj_ibfk_3` FOREIGN KEY (`rasporedId`) REFERENCES `raspored` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
