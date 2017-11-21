SET FOREIGN_KEY_CHECKS=0;

DROP TABLE IF EXISTS `loginCount`;
CREATE TABLE `loginCount` (
  `id` integer(10) NOT NULL  COMMENT '主键',
  `userName` varchar(100) DEFAULT NULL COMMENT '名字',
  `counts` integer(10) DEFAULT NULL COMMENT '名字',
  `createTime` date DEFAULT NULL COMMENT '名字',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='001';


