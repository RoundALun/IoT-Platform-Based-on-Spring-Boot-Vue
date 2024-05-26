
CREATE DATABASE /*!32312 IF NOT EXISTS*/`myiot` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `myiot`;

/*Table structure for table `sys_collection` */

DROP TABLE IF EXISTS `sys_collection`;

CREATE TABLE `sys_collection` (
  `collection_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '采集ID',
  `device_sn` bigint(10) DEFAULT NULL COMMENT '设备SN',
  `value` json DEFAULT NULL COMMENT '采集数据',
  `collection_time` datetime DEFAULT NULL COMMENT '采集时间',
  PRIMARY KEY (`collection_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_collection` */

/*Table structure for table `sys_device` */

DROP TABLE IF EXISTS `sys_device`;

CREATE TABLE `sys_device` (
  `device_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '设备ID',
  `device_sn` bigint(10) DEFAULT NULL COMMENT '设备SN',
  `device_pwd` varchar(20) DEFAULT NULL COMMENT '设备密码',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `protocol_id` bigint(20) DEFAULT NULL COMMENT '协议ID',
  PRIMARY KEY (`device_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_device` */

/*Table structure for table `sys_prop_type` */

DROP TABLE IF EXISTS `sys_prop_type`;

CREATE TABLE `sys_prop_type` (
  `prop_type_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '属性类型ID',
  `prop_type_name` varchar(50) DEFAULT NULL COMMENT '属性类型名称',
  `remark` varchar(50) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`prop_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `sys_prop_type` */

insert  into `sys_prop_type`(`prop_type_id`,`prop_type_name`,`remark`) values 
(1,'int','整数型'),
(2,'float','单精度浮点型'),
(3,'double','双精度浮点型'),
(4,'bool','布尔型'),
(5,'text','字符串'),
(6,'date','时间型');

/*Table structure for table `sys_property` */

DROP TABLE IF EXISTS `sys_property`;

CREATE TABLE `sys_property` (
  `property_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '属性ID',
  `protocol_id` bigint(20) DEFAULT NULL COMMENT '协议ID',
  `prop_type_id` bigint(20) DEFAULT NULL COMMENT '属性类型ID',
  `property_name` varchar(50) DEFAULT NULL COMMENT '属性名称',
  `property_key` varchar(50) DEFAULT NULL COMMENT '属性标识',
  `unit` varchar(20) DEFAULT '' COMMENT '单位',
  PRIMARY KEY (`property_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_property` */

/*Table structure for table `sys_protocol` */

DROP TABLE IF EXISTS `sys_protocol`;

CREATE TABLE `sys_protocol` (
  `protocol_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '协议ID',
  `protocol_name` varchar(50) DEFAULT NULL COMMENT '协议名称',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  PRIMARY KEY (`protocol_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_protocol` */

/*Table structure for table `sys_user` */

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `user_name` varchar(20) DEFAULT NULL COMMENT '用户名',
  `password` varchar(32) DEFAULT NULL COMMENT '密码',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父级ID',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `sys_user` */

insert  into `sys_user`(`user_id`,`user_name`,`password`,`parent_id`) values 
(1,'admin','e10adc3949ba59abbe56e057f20f883e',0);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
