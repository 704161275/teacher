/*
SQLyog Ultimate v12.08 (64 bit)
MySQL - 5.7.18-log : Database - test
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`test` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `test`;

/*Table structure for table `t_anser` */

DROP TABLE IF EXISTS `t_anser`;

CREATE TABLE `t_anser` (
  `aid` int(50) NOT NULL AUTO_INCREMENT,
  `aname` varchar(10) NOT NULL,
  `adaan` text NOT NULL,
  `tid` int(50) NOT NULL,
  `istrue` int(1) DEFAULT NULL,
  PRIMARY KEY (`aid`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

/*Data for the table `t_anser` */

insert  into `t_anser`(`aid`,`aname`,`adaan`,`tid`,`istrue`) values (1,'A','asdfasdas',1,NULL),(2,'B','adsgdsgdsfgfsdgsdfg',1,NULL),(3,'C','sadfsadfsadfasdfsadfasdfsadfsa',1,NULL),(4,'D','sadfasdfsadfasdfsadf',1,1),(5,'A','asdfasdas',2,NULL),(6,'B','adsgdsgdsfgfsdgsdfg',2,NULL),(7,'C','sadfsadfsadfasdfsadfasdfsadfsa',2,NULL),(8,'D','sadfasdfsadfasdfsadf',2,1),(9,'A','asdfasdas',3,NULL),(10,'B','adsgdsgdsfgfsdgsdfg',3,NULL),(11,'C','sadfsadfsadfasdfsadfasdfsadfsa',3,NULL),(13,'D','sadfasdfsadfasdfsadf',3,1),(14,'A','asdfasdas',4,NULL),(15,'B','adsgdsgdsfgfsdgsdfg',4,NULL),(16,'C','sadfsadfsadfasdfsadfasdfsadfsa',4,NULL),(17,'D','sadfasdfsadfasdfsadf',4,1);

/*Table structure for table `t_menu` */

DROP TABLE IF EXISTS `t_menu`;

CREATE TABLE `t_menu` (
  `mid` int(30) NOT NULL AUTO_INCREMENT,
  `mname` varchar(30) NOT NULL,
  `pid` int(5) DEFAULT NULL,
  `url` varchar(40) DEFAULT NULL,
  `target` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`mid`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Data for the table `t_menu` */

insert  into `t_menu`(`mid`,`mname`,`pid`,`url`,`target`) values (1,'课程学习',0,'./studyServlet?method=study','mainpage'),(2,'在线考试',0,'./testServlet?method=test','mainpage'),(3,'成绩查询',0,NULL,NULL),(4,'上传课件',0,NULL,NULL),(5,'组织试题',0,NULL,NULL),(6,'成绩统计',0,NULL,NULL),(7,'论坛交流',0,NULL,NULL);

/*Table structure for table `t_question` */

DROP TABLE IF EXISTS `t_question`;

CREATE TABLE `t_question` (
  `tid` int(50) NOT NULL AUTO_INCREMENT,
  `tquestion` text NOT NULL,
  PRIMARY KEY (`tid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `t_question` */

insert  into `t_question`(`tid`,`tquestion`) values (1,'sadfsdafsadfsadfsadfsadfsadfasdfasdfsdfsadfsafd'),(2,'sadfsadfasdfasdfasdfasfsadf'),(3,'sadfasdfasdfsadfasdfsadfsadfsadf'),(4,'asdfsadfasdfasdfsadfasdfasdfsadfasdf');

/*Table structure for table `t_r_m` */

DROP TABLE IF EXISTS `t_r_m`;

CREATE TABLE `t_r_m` (
  `rid` int(30) DEFAULT NULL,
  `mid` int(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_r_m` */

insert  into `t_r_m`(`rid`,`mid`) values (2,4),(2,5),(2,6),(2,7),(3,1),(3,2),(3,3),(3,7);

/*Table structure for table `t_role` */

DROP TABLE IF EXISTS `t_role`;

CREATE TABLE `t_role` (
  `rid` int(30) NOT NULL AUTO_INCREMENT,
  `rname` varchar(30) NOT NULL,
  PRIMARY KEY (`rid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `t_role` */

insert  into `t_role`(`rid`,`rname`) values (1,'管理员'),(2,'教师'),(3,'学生');

/*Table structure for table `t_study` */

DROP TABLE IF EXISTS `t_study`;

CREATE TABLE `t_study` (
  `sid` int(30) NOT NULL AUTO_INCREMENT,
  `spath` varchar(50) DEFAULT NULL,
  `sname` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_study` */

/*Table structure for table `t_u_r` */

DROP TABLE IF EXISTS `t_u_r`;

CREATE TABLE `t_u_r` (
  `uid` int(30) DEFAULT NULL,
  `rid` int(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_u_r` */

insert  into `t_u_r`(`uid`,`rid`) values (1,1),(2,2),(3,3);

/*Table structure for table `t_user` */

DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `uid` int(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `t_user` */

insert  into `t_user`(`uid`,`username`,`password`) values (1,'admin','admin'),(2,'zhangs','111aaa'),(3,'lis','111aaa');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
