DROP TABLE IF EXISTS `g2p_tracker`.`audita_estados_circuitos`;
CREATE TABLE  `g2p_tracker`.`audita_estados_circuitos` (
  `audita_id` int(15) unsigned NOT NULL auto_increment COMMENT 'IdentificaciÃ³n Ãºnica del registro de auditorÃ­a',
  `circuito_id` int(11) NOT NULL COMMENT 'Circuito asociado al cambio de estado',
  `fecha` datetime NOT NULL COMMENT 'Fecha y hora en que se realiza la acciÃ³n .Timestamp',
  `accion_id` int(10) unsigned NOT NULL COMMENT 'AcciÃ³n que provoca (dispara) el cambio de estado',
  `estado_id_de` int(11) NOT NULL COMMENT 'Estado de origen inicial antes del cambio',
  `estado_id_a` int(11) NOT NULL COMMENT 'Estado destino, despues del cambio de estado',
  `user_id` int(10) unsigned NOT NULL COMMENT 'Usuario que realizÃ³ el cambio de estado',
  `nombre_tabla` varchar(45) NOT NULL COMMENT 'Nombre de la tabla en donde se realiza el cambio',
  `registro_id` int(15) unsigned NOT NULL COMMENT 'IdentificaciÃ³n de la clave primarai (ID) de la tabla donde se realiza el cambio. Objeto de la aplicaciÃ³n',
  `host` varchar(30) default NULL COMMENT 'equipo desde donde se realiza la acciÃ³n. identificaciÃ³n de la mÃ¡quina',
  `observaciones` varchar(255) default NULL,
  PRIMARY KEY  (`audita_id`),
  KEY `fk_aec_circuitos` (`circuito_id`),
  KEY `fk_aec_acciones_apps` (`accion_id`),
  KEY `fk_aec_estados_de` (`estado_id_de`),
  KEY `fk_aec_estados_a` (`estado_id_a`),
  KEY `fk_aec_website_users` (`user_id`),
  CONSTRAINT `fk_aec_circuitos` FOREIGN KEY (`circuito_id`) REFERENCES `circuitos_estados` (`circuito_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_aec_acciones_apps` FOREIGN KEY (`accion_id`) REFERENCES `acciones_apps` (`accion_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_aec_estados_de` FOREIGN KEY (`estado_id_de`) REFERENCES `estados` (`estado_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_aec_estados_a` FOREIGN KEY (`estado_id_a`) REFERENCES `estados` (`estado_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_aec_website_users` FOREIGN KEY (`user_id`) REFERENCES `website_users` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `g2p_tracker`.`clase_lov_atributo`;
CREATE TABLE  `g2p_tracker`.`clase_lov_atributo` (
  `clase_lov_atributo_id` int(15) unsigned NOT NULL auto_increment,
  `nombre` varchar(90) NOT NULL,
  `descripcion` varchar(255) default NULL,
  `observaciones` varchar(255) default NULL,
  PRIMARY KEY  (`clase_lov_atributo_id`),
  UNIQUE KEY `nombre_UK` (`nombre`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `g2p_tracker`.`diccionario_aplicacion`;
CREATE TABLE  `g2p_tracker`.`diccionario_aplicacion` (
  `tipo_objeto` varchar(15) NOT NULL default '' COMMENT 'tipo del objeto (TABLA, VISTA, PROGRAMA, INDICE, etc.)',
  `nombre_objeto` varchar(45) NOT NULL default '' COMMENT 'Nombre del objeto de la aplicacin',
  PRIMARY KEY  (`tipo_objeto`,`nombre_objeto`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Permite guardar nombres de objetos usados en la aplicacin, t';

DROP TABLE IF EXISTS `g2p_tracker`.`entidad_externa`;
CREATE TABLE  `g2p_tracker`.`entidad_externa` (
  `entidad_id` int(15) unsigned NOT NULL auto_increment,
  `codigo` varchar(15) default NULL COMMENT 'CÃ³digo externo que identifica a la entidad',
  `nombre` varchar(90) NOT NULL,
  `descripcion` varchar(255) default NULL COMMENT 'DescripciÃ³n de la entidad',
  `observaciones` varchar(255) default NULL COMMENT 'Observaciones. Comentarios sobre la entidad',
  `activo` char(1) NOT NULL default 'F' COMMENT 'Indica si la entidad estÃ¡ activa. SÃ³lo entidades activas son permitidas usar. Activar garantiza las reglas de nogocio asociadas a los atributos y roles',
  `anulado` char(1) NOT NULL default 'F' COMMENT 'Indica si la entidad estÃ¡ nulada. Baja lÃ³gica',
  PRIMARY KEY  (`entidad_id`),
  UNIQUE KEY `UQ_entidad_externa_2` (`nombre`),
  UNIQUE KEY `UQ_entidad_externa_1` (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `g2p_tracker`.`esquema_configuracion`;
CREATE TABLE  `g2p_tracker`.`esquema_configuracion` (
  `esquema_configuracion_id` int(15) unsigned NOT NULL auto_increment,
  `nombre` varchar(90) NOT NULL,
  `descripcion` varchar(255) default NULL,
  `observaciones` varchar(255) default NULL,
  `tipo_objeto` varchar(15) NOT NULL,
  `nombre_objeto` varchar(45) NOT NULL,
  PRIMARY KEY  (`esquema_configuracion_id`),
  UNIQUE KEY `esquema_configuracion_UK1` (`nombre`),
  KEY `tipo_objeto` (`tipo_objeto`,`nombre_objeto`),
  CONSTRAINT `esquema_configuracion_ibfk_1` FOREIGN KEY (`tipo_objeto`, `nombre_objeto`) REFERENCES `diccionario_aplicacion` (`tipo_objeto`, `nombre_objeto`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `g2p_tracker`.`menu`;
CREATE TABLE  `g2p_tracker`.`menu` (
  `menu_id` int(10) unsigned NOT NULL,
  `nombre` varchar(90) NOT NULL default '',
  `descripcion` varchar(255) default NULL,
  `url` varchar(255) default NULL,
  `grupo` varchar(90) default NULL,
  PRIMARY KEY  (`menu_id`),
  KEY `grupo_IDX` (`grupo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `g2p_tracker`.`rol_entidad`;
CREATE TABLE  `g2p_tracker`.`rol_entidad` (
  `rol` char(4) NOT NULL,
  `nombre` varchar(90) NOT NULL COMMENT 'nombre del rol de entidad',
  `descripcion` varchar(255) default NULL COMMENT 'DescripciÃ³n del rol de entidad',
  `observaciones` varchar(255) default NULL COMMENT 'Observaciones. Comentarios sobre el rol de la entidad',
  `desde` date NOT NULL COMMENT 'Fecha de vigencia del rol de entidad',
  `hasta` date default NULL COMMENT 'Fecha hasta donde estÃ¡ vigente el rol de entidad',
  `anulado` char(1) NOT NULL default 'F' COMMENT 'Indica si el registro estÃ¡ anulado. Baja lÃ³gica',
  PRIMARY KEY  (`rol`),
  UNIQUE KEY `UQ_rol_entidad_1` (`nombre`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `g2p_tracker`.`roles`;
CREATE TABLE  `g2p_tracker`.`roles` (
  `rol_id` int(10) unsigned NOT NULL auto_increment,
  `nombre` varchar(90) NOT NULL default '',
  `descripcion` varchar(255) default NULL,
  `observaciones` varchar(255) default NULL,
  `OBJ_VERSION` datetime default NULL,
  PRIMARY KEY  (`rol_id`),
  UNIQUE KEY `IX_roles_1` (`nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `g2p_tracker`.`transicion_estados`;
CREATE TABLE  `g2p_tracker`.`transicion_estados` (
  `transicion_id` int(11) NOT NULL,
  `estado_id_origen` int(11) NOT NULL,
  `accion_id` int(10) unsigned NOT NULL default '0',
  `estado_id_destino` int(11) NOT NULL,
  `prompt_accion` varchar(30) default NULL,
  `validador` varchar(255) default NULL,
  PRIMARY KEY  (`transicion_id`),
  UNIQUE KEY `unique_estado_origen_accion` (`estado_id_origen`,`accion_id`),
  KEY `fk_transicion_estados_acciones_apps` (`accion_id`),
  KEY `fk_transicion_estados_estados_origen` (`estado_id_origen`),
  KEY `fk_transicion_estados_estados_destino` (`estado_id_destino`),
  CONSTRAINT `fk_transicion_estados_acciones_apps` FOREIGN KEY (`accion_id`) REFERENCES `acciones_apps` (`accion_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_transicion_estados_estados_origen` FOREIGN KEY (`estado_id_origen`) REFERENCES `estados` (`estado_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_transicion_estados_estados_destino` FOREIGN KEY (`estado_id_destino`) REFERENCES `estados` (`estado_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `g2p_tracker`.`website_users`;
CREATE TABLE  `g2p_tracker`.`website_users` (
  `user_id` int(10) unsigned NOT NULL auto_increment,
  `login_name` varchar(60) NOT NULL default '',
  `login_password` varchar(60) NOT NULL default '',
  `nivel_visibilidad` varchar(255) default 'Todo',
  `nombre_completo` varchar(90) NOT NULL,
  `email` varchar(90) default NULL,
  `nro_legajo` int(11) default NULL,
  `nro_comprador` int(10) unsigned default NULL,
  `OBJ_VERSION` datetime default NULL,
  `fecha_nacimiento` datetime NOT NULL,
  PRIMARY KEY  (`user_id`),
  UNIQUE KEY `unique_nombre_nacimiento` (`nombre_completo`,`fecha_nacimiento`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
