DROP TABLE IF EXISTS `g2p_tracker`.`tracks`;
CREATE TABLE  `g2p_tracker`.`tracks` (
  `track_id` int(10) unsigned NOT NULL auto_increment,
  `descripcion` varchar(90) NOT NULL,
  `observaciones` varchar(255) default NULL,
  `criticidad` int(10) unsigned NOT NULL,
  `prioridad` int(10) unsigned NOT NULL,
  `fecha_creacion` datetime NOT NULL,
  `fecha_estimada_realizacion` date default NULL,
  `deadline` date default NULL,
  `fecha_realizacion` datetime default NULL,
  `estado` varchar(9) NOT NULL,
  `user_id_owner` int(10) unsigned NOT NULL,
  PRIMARY KEY  (`track_id`),
  KEY `FK_tracks_estados` (`estado`),
  KEY `FK_tracks_website_user_owner` (`user_id_owner`),
  CONSTRAINT `FK_tracks_estados` FOREIGN KEY (`estado`) REFERENCES `estados` (`estado`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_tracks_website_user_owner` FOREIGN KEY (`user_id_owner`) REFERENCES `website_user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Tabla que almacena cada track en la aplicación';

DROP TABLE IF EXISTS `g2p_tracker`.`workers_per_track`;
CREATE TABLE  `g2p_tracker`.`workers_per_track` (
  `track_id` int(10) unsigned NOT NULL,
  `worker_id` int(10) unsigned NOT NULL,
  PRIMARY KEY  (`track_id`,`worker_id`),
  KEY `FK_workers_per_track_website_users` (`worker_id`),
  CONSTRAINT `FK_workers_per_track_tracks` FOREIGN KEY (`track_id`) REFERENCES `tracks` (`track_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_workers_per_track_website_users` FOREIGN KEY (`worker_id`) REFERENCES `website_user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Almacena los workers asociados a un track';