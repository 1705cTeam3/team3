SET FOREIGN_KEY_CHECKS=0;

DROP TABLE IF EXISTS `loginCount`;
CREATE TABLE `loginCount` (
  `id` integer(10) NOT NULL  COMMENT '����',
  `userName` varchar(100) DEFAULT NULL COMMENT '����',
  `counts` integer(10) DEFAULT NULL COMMENT '����',
  `createTime` date DEFAULT NULL COMMENT '����',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='001';


