-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.0.67-community-nt


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema g2p_tracker
--

CREATE DATABASE IF NOT EXISTS g2p_tracker;
USE g2p_tracker;

--
-- Definition of table `acceso_menu`
--

DROP TABLE IF EXISTS `acceso_menu`;
CREATE TABLE `acceso_menu` (
  `menu_id` int(10) unsigned NOT NULL default '0',
  `rol_id` int(10) unsigned default '0',
  `user_id` int(10) unsigned default '0',
  `acceso_menu_id` int(10) unsigned NOT NULL auto_increment,
  PRIMARY KEY  (`acceso_menu_id`),
  KEY `rol_FK` (`rol_id`),
  KEY `FK_acceso_menu_3` (`menu_id`),
  KEY `FK_acceso_menu_4` (`user_id`),
  CONSTRAINT `FK_acceso_menu_3` FOREIGN KEY (`menu_id`) REFERENCES `menu` (`menu_id`),
  CONSTRAINT `FK_acceso_menu_4` FOREIGN KEY (`user_id`) REFERENCES `website_user` (`user_id`),
  CONSTRAINT `rol_FK` FOREIGN KEY (`rol_id`) REFERENCES `roles` (`rol_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `acceso_menu`
--

/*!40000 ALTER TABLE `acceso_menu` DISABLE KEYS */;
/*!40000 ALTER TABLE `acceso_menu` ENABLE KEYS */;


--
-- Definition of table `acciones_apps`
--

DROP TABLE IF EXISTS `acciones_apps`;
CREATE TABLE `acciones_apps` (
  `accion` int(10) unsigned NOT NULL auto_increment,
  `nombre` varchar(90) NOT NULL,
  `descripcion` varchar(255) default NULL,
  `observaciones` varchar(255) default NULL,
  `circuito` varchar(4) NOT NULL,
  `manual` char(1) NOT NULL default 'V',
  PRIMARY KEY  (`accion`),
  KEY `circuito` (`circuito`),
  KEY `uq_acciones_aplicacion_circuito` (`circuito`,`nombre`),
  CONSTRAINT `FK_acciones_apps_1` FOREIGN KEY (`circuito`) REFERENCES `circuitos_estados` (`circuito`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `acciones_apps`
--

/*!40000 ALTER TABLE `acciones_apps` DISABLE KEYS */;
/*!40000 ALTER TABLE `acciones_apps` ENABLE KEYS */;


--
-- Definition of table `aplica_circuito`
--

DROP TABLE IF EXISTS `aplica_circuito`;
CREATE TABLE `aplica_circuito` (
  `tipo_objeto` varchar(15) NOT NULL,
  `nombre_objeto` varchar(45) NOT NULL,
  `tipo_detalle` varchar(15) NOT NULL,
  `nombre_detalle` varchar(45) NOT NULL,
  `circuito` varchar(4) NOT NULL,
  PRIMARY KEY  (`nombre_detalle`,`nombre_objeto`,`tipo_detalle`,`tipo_objeto`),
  KEY `circuito` (`circuito`),
  CONSTRAINT `FK_aplica_circuito_2` FOREIGN KEY (`circuito`) REFERENCES `circuitos_estados` (`circuito`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `aplica_circuito`
--

/*!40000 ALTER TABLE `aplica_circuito` DISABLE KEYS */;
/*!40000 ALTER TABLE `aplica_circuito` ENABLE KEYS */;


--
-- Definition of table `atributos_configuracion`
--

DROP TABLE IF EXISTS `atributos_configuracion`;
CREATE TABLE `atributos_configuracion` (
  `configuracion_id` int(15) unsigned NOT NULL,
  `atributo_id` int(15) unsigned NOT NULL,
  `valor` varchar(255) NOT NULL,
  `valor_hasta` varchar(255) NOT NULL,
  PRIMARY KEY  (`configuracion_id`,`atributo_id`),
  KEY `atributo_id` (`atributo_id`),
  CONSTRAINT `atributos_configuracion_ibfk_1` FOREIGN KEY (`configuracion_id`) REFERENCES `configuracion` (`configuracion_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `atributos_configuracion_ibfk_2` FOREIGN KEY (`atributo_id`) REFERENCES `atributos_rol` (`atributo_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `atributos_configuracion`
--

/*!40000 ALTER TABLE `atributos_configuracion` DISABLE KEYS */;
/*!40000 ALTER TABLE `atributos_configuracion` ENABLE KEYS */;


--
-- Definition of table `atributos_entidad`
--

DROP TABLE IF EXISTS `atributos_entidad`;
CREATE TABLE `atributos_entidad` (
  `valor` varchar(255) default NULL COMMENT 'Valor del atributo',
  `valor_entero` int(11) default NULL,
  `valor_real` double default NULL,
  `valor_fecha` date default NULL,
  `valor_logico` char(1) default NULL,
  `atributo_id` int(15) unsigned NOT NULL COMMENT 'IdentificaciÃ³n Ãºnica de atributo de entidad',
  `entidad_id` int(15) unsigned default NULL,
  `atributo_entidad_id` int(15) unsigned NOT NULL auto_increment,
  `objeto_id` int(15) unsigned default NULL,
  `tipo_objeto` varchar(15) default NULL,
  `nombre_objeto` varchar(45) default NULL,
  PRIMARY KEY  USING BTREE (`atributo_entidad_id`),
  UNIQUE KEY `unique_index` USING BTREE (`atributo_id`,`objeto_id`,`tipo_objeto`,`nombre_objeto`),
  UNIQUE KEY `unique_entidad_index` USING BTREE (`entidad_id`,`atributo_id`),
  KEY `atributo_id` (`atributo_id`),
  KEY `entidad_id` (`entidad_id`),
  CONSTRAINT `FK_atributos_entidad_1` FOREIGN KEY (`atributo_id`) REFERENCES `atributos_rol` (`atributo_id`),
  CONSTRAINT `FK_atributos_entidad_2` FOREIGN KEY (`entidad_id`) REFERENCES `entidad_externa` (`entidad_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Atributos asociados a la entidad; InnoDB free: 6144 kB; (`at';

--
-- Dumping data for table `atributos_entidad`
--

/*!40000 ALTER TABLE `atributos_entidad` DISABLE KEYS */;
/*!40000 ALTER TABLE `atributos_entidad` ENABLE KEYS */;


--
-- Definition of table `atributos_rol`
--

DROP TABLE IF EXISTS `atributos_rol`;
CREATE TABLE `atributos_rol` (
  `atributo_id` int(15) unsigned NOT NULL auto_increment COMMENT 'IdentificaciÃ³n Ãºnica de atributo de entidad',
  `nombre` varchar(90) NOT NULL COMMENT 'Nombre Ãºnico de atributo para entidad',
  `descripcion` varchar(255) default NULL COMMENT 'DescripciÃ³n del atributo',
  `observaciones` varchar(255) default NULL COMMENT 'Observaciones. Comentarios sobre la definiciÃ³n del atributo',
  `rol` char(4) default NULL,
  `desde` date NOT NULL COMMENT 'fecha de vigencia del atributo',
  `hasta` date default NULL COMMENT 'Indica hasta que fecha el atributo estÃ¡ vigente',
  `anulado` char(1) default 'F' COMMENT 'Indica si el registro estÃ¡ anulado: baja lÃ³gica',
  `clave` char(1) NOT NULL default 'F',
  `obligatorio` char(1) NOT NULL default 'F' COMMENT 'Indica si el atributo es obligatorio',
  `tipo_dato` enum('entero','real','texto','fecha','logico') NOT NULL default 'texto' COMMENT 'Indica el tipo de dato del atributo',
  `lov` varchar(255) default NULL COMMENT 'Lista de valores asociada al atributo. Nulo sin lista',
  `validador` varchar(255) default NULL COMMENT 'Rutina de validaciÃ³n asociada al atributo. Nulo implica sin validaciÃ³n',
  `clase_atributo_rol_id` int(15) unsigned default NULL COMMENT 'IdentificaciÃ³n Ãºnica para la clase de atributo',
  `nombre_objeto` varchar(45) default NULL COMMENT 'Nombre del objeto de la aplicacin',
  `tipo_objeto` varchar(15) default NULL COMMENT 'tipo del objeto (TABLA, VISTA, PROGRAMA, INDICE, etc.)',
  `clase_lov_atributo_id` int(15) unsigned default NULL,
  PRIMARY KEY  (`atributo_id`),
  KEY `rol` (`rol`),
  KEY `clase_atributo_rol_id` (`clase_atributo_rol_id`),
  KEY `tipo_objeto` (`tipo_objeto`,`nombre_objeto`),
  CONSTRAINT `FK_atributos_rol_3` FOREIGN KEY (`tipo_objeto`, `nombre_objeto`) REFERENCES `diccionario_aplicacion` (`tipo_objeto`, `nombre_objeto`),
  CONSTRAINT `FK_clase_atributo_rol_1` FOREIGN KEY (`rol`) REFERENCES `rol_entidad` (`rol`),
  CONSTRAINT `FK_clase_atributo_rol_2` FOREIGN KEY (`clase_atributo_rol_id`) REFERENCES `clase_atributo_rol` (`clase_atributo_rol_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `atributos_rol`
--

/*!40000 ALTER TABLE `atributos_rol` DISABLE KEYS */;
/*!40000 ALTER TABLE `atributos_rol` ENABLE KEYS */;


--
-- Definition of table `audita_estados_circuitos`
--

DROP TABLE IF EXISTS `audita_estados_circuitos`;
CREATE TABLE `audita_estados_circuitos` (
  `audita_id` int(15) unsigned NOT NULL auto_increment COMMENT 'IdentificaciÃ³n Ãºnica del registro de auditorÃ­a',
  `circuito` varchar(4) NOT NULL COMMENT 'Circuito asociado al cambio de estado',
  `fecha` datetime NOT NULL COMMENT 'Fecha y hora en que se realiza la acciÃ³n .Timestamp',
  `accion` int(10) unsigned NOT NULL COMMENT 'AcciÃ³n que provoca (dispara) el cambio de estado',
  `de_estado` varchar(15) NOT NULL COMMENT 'Estado de origen inicial antes del cambio',
  `a_estado` varchar(15) NOT NULL COMMENT 'Estado destino, despues del cambio de estado',
  `user_id` int(10) unsigned NOT NULL COMMENT 'Usuario que realizÃ³ el cambio de estado',
  `nombre_tabla` varchar(45) NOT NULL COMMENT 'Nombre de la tabla en donde se realiza el cambio',
  `registro_id` int(15) unsigned NOT NULL COMMENT 'IdentificaciÃ³n de la clave primarai (ID) de la tabla donde se realiza el cambio. Objeto de la aplicaciÃ³n',
  `host` varchar(30) default NULL COMMENT 'equipo desde donde se realiza la acciÃ³n. identificaciÃ³n de la mÃ¡quina',
  `observaciones` varchar(255) default NULL,
  PRIMARY KEY  (`audita_id`),
  KEY `circuito` (`circuito`),
  KEY `de_estado` (`de_estado`),
  KEY `a_estado` (`a_estado`),
  KEY `user_id` (`user_id`),
  KEY `accion` (`accion`),
  KEY `IX_audita_estados_circuitos_2` (`registro_id`,`circuito`),
  CONSTRAINT `FK_audita_estados_circuitos_1` FOREIGN KEY (`circuito`) REFERENCES `circuitos_estados` (`circuito`),
  CONSTRAINT `FK_audita_estados_circuitos_3` FOREIGN KEY (`de_estado`) REFERENCES `estados` (`estado`),
  CONSTRAINT `FK_audita_estados_circuitos_4` FOREIGN KEY (`a_estado`) REFERENCES `estados` (`estado`),
  CONSTRAINT `FK_audita_estados_circuitos_5` FOREIGN KEY (`user_id`) REFERENCES `website_user` (`user_id`),
  CONSTRAINT `FK_audita_estados_circuitos_6` FOREIGN KEY (`accion`) REFERENCES `acciones_apps` (`accion`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `audita_estados_circuitos`
--

/*!40000 ALTER TABLE `audita_estados_circuitos` DISABLE KEYS */;
/*!40000 ALTER TABLE `audita_estados_circuitos` ENABLE KEYS */;


--
-- Definition of table `circuitos_estados`
--

DROP TABLE IF EXISTS `circuitos_estados`;
CREATE TABLE `circuitos_estados` (
  `circuito` varchar(4) NOT NULL,
  `nombre` varchar(90) NOT NULL,
  `descripcion` varchar(255) default NULL,
  `observaciones` varchar(255) default NULL,
  PRIMARY KEY  (`circuito`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `circuitos_estados`
--

/*!40000 ALTER TABLE `circuitos_estados` DISABLE KEYS */;
/*!40000 ALTER TABLE `circuitos_estados` ENABLE KEYS */;


--
-- Definition of table `clase_atributo_rol`
--

DROP TABLE IF EXISTS `clase_atributo_rol`;
CREATE TABLE `clase_atributo_rol` (
  `clase_atributo_rol_id` int(15) unsigned NOT NULL auto_increment COMMENT 'IdentificaciÃ³n Ãºnica para la clase de atributo',
  `etiqueta` varchar(90) NOT NULL COMMENT 'Etiqueta que le corresponde a la clase de atributos',
  PRIMARY KEY  (`clase_atributo_rol_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `clase_atributo_rol`
--

/*!40000 ALTER TABLE `clase_atributo_rol` DISABLE KEYS */;
/*!40000 ALTER TABLE `clase_atributo_rol` ENABLE KEYS */;


--
-- Definition of table `clase_lov_atributo`
--

DROP TABLE IF EXISTS `clase_lov_atributo`;
CREATE TABLE `clase_lov_atributo` (
  `clase_lov_atributo_id` int(15) unsigned NOT NULL auto_increment,
  `nombre` varchar(90) NOT NULL,
  `descripcion` varchar(255) default NULL,
  `observaciones` varchar(255) default NULL,
  PRIMARY KEY  (`clase_lov_atributo_id`),
  UNIQUE KEY `nombre_UK` (`nombre`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `clase_lov_atributo`
--

/*!40000 ALTER TABLE `clase_lov_atributo` DISABLE KEYS */;
/*!40000 ALTER TABLE `clase_lov_atributo` ENABLE KEYS */;


--
-- Definition of table `configuracion`
--

DROP TABLE IF EXISTS `configuracion`;
CREATE TABLE `configuracion` (
  `configuracion_id` int(15) unsigned NOT NULL auto_increment,
  `esquema_configuracion_id` int(15) unsigned NOT NULL,
  `nombre` varchar(90) NOT NULL,
  `descripcion` varchar(255) default NULL,
  `observaciones` varchar(255) default NULL,
  `cardinalidad` int(3) unsigned NOT NULL,
  `prioridad` int(3) unsigned NOT NULL default '50',
  PRIMARY KEY  (`configuracion_id`),
  UNIQUE KEY `configuracion_UK1` (`esquema_configuracion_id`,`nombre`),
  KEY `esquema_configuracion_id` (`esquema_configuracion_id`),
  CONSTRAINT `configuracion_ibfk_1` FOREIGN KEY (`esquema_configuracion_id`) REFERENCES `esquema_configuracion` (`esquema_configuracion_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `configuracion`
--

/*!40000 ALTER TABLE `configuracion` DISABLE KEYS */;
/*!40000 ALTER TABLE `configuracion` ENABLE KEYS */;


--
-- Definition of table `diccionario_aplicacion`
--

DROP TABLE IF EXISTS `diccionario_aplicacion`;
CREATE TABLE `diccionario_aplicacion` (
  `tipo_objeto` varchar(15) NOT NULL default '' COMMENT 'tipo del objeto (TABLA, VISTA, PROGRAMA, INDICE, etc.)',
  `nombre_objeto` varchar(45) NOT NULL default '' COMMENT 'Nombre del objeto de la aplicacin',
  PRIMARY KEY  (`tipo_objeto`,`nombre_objeto`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Permite guardar nombres de objetos usados en la aplicacin, t';

--
-- Dumping data for table `diccionario_aplicacion`
--

/*!40000 ALTER TABLE `diccionario_aplicacion` DISABLE KEYS */;
/*!40000 ALTER TABLE `diccionario_aplicacion` ENABLE KEYS */;


--
-- Definition of table `diccionario_aplicacion_detalle`
--

DROP TABLE IF EXISTS `diccionario_aplicacion_detalle`;
CREATE TABLE `diccionario_aplicacion_detalle` (
  `tipo_objeto` varchar(15) NOT NULL default '' COMMENT 'Tipo Objeto. Relaciona al objeto',
  `nombre_objeto` varchar(45) NOT NULL default '' COMMENT 'Nombre del objeto. relaciona al objeto',
  `tipo_detalle` varchar(15) NOT NULL default '' COMMENT 'Tipo del detalle: (COLUMNA, CAMPO, etc.)',
  `nombre_detalle` varchar(45) NOT NULL default '' COMMENT 'Nombre del detalle del objeto. Por ejemplo nombre de la columna de una tabla',
  PRIMARY KEY  (`tipo_objeto`,`nombre_objeto`,`tipo_detalle`,`nombre_detalle`),
  CONSTRAINT `FK_diccionario_aplicacion_detalle_1` FOREIGN KEY (`tipo_objeto`, `nombre_objeto`) REFERENCES `diccionario_aplicacion` (`tipo_objeto`, `nombre_objeto`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Detalle de un objeto de la aplicacin. En el caso de tabla, s';

--
-- Dumping data for table `diccionario_aplicacion_detalle`
--

/*!40000 ALTER TABLE `diccionario_aplicacion_detalle` DISABLE KEYS */;
/*!40000 ALTER TABLE `diccionario_aplicacion_detalle` ENABLE KEYS */;


--
-- Definition of table `entidad_externa`
--

DROP TABLE IF EXISTS `entidad_externa`;
CREATE TABLE `entidad_externa` (
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

--
-- Dumping data for table `entidad_externa`
--

/*!40000 ALTER TABLE `entidad_externa` DISABLE KEYS */;
/*!40000 ALTER TABLE `entidad_externa` ENABLE KEYS */;


--
-- Definition of table `esquema_configuracion`
--

DROP TABLE IF EXISTS `esquema_configuracion`;
CREATE TABLE `esquema_configuracion` (
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

--
-- Dumping data for table `esquema_configuracion`
--

/*!40000 ALTER TABLE `esquema_configuracion` DISABLE KEYS */;
/*!40000 ALTER TABLE `esquema_configuracion` ENABLE KEYS */;


--
-- Definition of table `estados`
--

DROP TABLE IF EXISTS `estados`;
CREATE TABLE `estados` (
  `estado` varchar(15) NOT NULL,
  `nombre` varchar(90) NOT NULL,
  `descripcion` varchar(255) default NULL,
  `observaciones` varchar(255) default NULL,
  `circuito` varchar(15) NOT NULL,
  PRIMARY KEY  (`estado`),
  KEY `circuito` (`circuito`),
  KEY `uq_estados_nombre` (`nombre`,`circuito`),
  CONSTRAINT `FK_estados_1` FOREIGN KEY (`circuito`) REFERENCES `circuitos_estados` (`circuito`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `estados`
--

/*!40000 ALTER TABLE `estados` DISABLE KEYS */;
/*!40000 ALTER TABLE `estados` ENABLE KEYS */;


--
-- Definition of table `lov_atributo`
--

DROP TABLE IF EXISTS `lov_atributo`;
CREATE TABLE `lov_atributo` (
  `clase_lov_atributo_id` int(15) unsigned NOT NULL,
  `valor` varchar(255) NOT NULL,
  `descripcion` varchar(255) default NULL,
  PRIMARY KEY  (`clase_lov_atributo_id`,`valor`),
  CONSTRAINT `lov_atributo_ibfk_1` FOREIGN KEY (`clase_lov_atributo_id`) REFERENCES `clase_lov_atributo` (`clase_lov_atributo_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `lov_atributo`
--

/*!40000 ALTER TABLE `lov_atributo` DISABLE KEYS */;
/*!40000 ALTER TABLE `lov_atributo` ENABLE KEYS */;


--
-- Definition of table `menu`
--

DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `menu_id` int(10) unsigned NOT NULL auto_increment,
  `nombre` varchar(90) NOT NULL default '',
  `descripcion` varchar(255) default NULL,
  `url` varchar(255) default NULL,
  `grupo` varchar(90) NOT NULL default '',
  PRIMARY KEY  (`menu_id`),
  KEY `grupo_IDX` (`grupo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `menu`
--

/*!40000 ALTER TABLE `menu` DISABLE KEYS */;
/*!40000 ALTER TABLE `menu` ENABLE KEYS */;


--
-- Definition of table `preferencias`
--

DROP TABLE IF EXISTS `preferencias`;
CREATE TABLE `preferencias` (
  `preferencia_id` int(10) unsigned NOT NULL default '0',
  `nombre` varchar(90) NOT NULL default '',
  `descripcion` varchar(255) default NULL,
  `observaciones` varchar(255) default NULL,
  `default` varchar(255) NOT NULL default '',
  PRIMARY KEY  (`preferencia_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `preferencias`
--

/*!40000 ALTER TABLE `preferencias` DISABLE KEYS */;
/*!40000 ALTER TABLE `preferencias` ENABLE KEYS */;


--
-- Definition of table `rol_entidad`
--

DROP TABLE IF EXISTS `rol_entidad`;
CREATE TABLE `rol_entidad` (
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

--
-- Dumping data for table `rol_entidad`
--

/*!40000 ALTER TABLE `rol_entidad` DISABLE KEYS */;
/*!40000 ALTER TABLE `rol_entidad` ENABLE KEYS */;


--
-- Definition of table `roles`
--

DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles` (
  `rol_id` int(10) unsigned NOT NULL auto_increment,
  `nombre` varchar(90) NOT NULL default '',
  `descripcion` varchar(255) default NULL,
  `observaciones` varchar(255) default NULL,
  PRIMARY KEY  (`rol_id`),
  UNIQUE KEY `IX_roles_1` (`nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `roles`
--

/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;


--
-- Definition of table `roles_entidad`
--

DROP TABLE IF EXISTS `roles_entidad`;
CREATE TABLE `roles_entidad` (
  `desde` date NOT NULL COMMENT 'Fecha desde cuando estÃ¡ activo en el rol',
  `hasta` date default NULL COMMENT 'Hasta que fecha estÃ¡ activo en el rol',
  `activo` char(1) NOT NULL default 'F' COMMENT 'Indica si el rol est''aactivo, sino no es considerado. Activar garantiza reglas para atributos',
  `anulado` char(1) NOT NULL default 'F' COMMENT 'Indica que el registro est''aanulado. Baja lÃ³gica',
  `entidad_id` int(15) unsigned NOT NULL,
  `rol` char(4) NOT NULL,
  PRIMARY KEY  (`entidad_id`,`rol`),
  KEY `entidad_id` (`entidad_id`),
  KEY `rol` (`rol`),
  CONSTRAINT `FK_roles_entidad_1` FOREIGN KEY (`entidad_id`) REFERENCES `entidad_externa` (`entidad_id`),
  CONSTRAINT `FK_roles_entidad_2` FOREIGN KEY (`rol`) REFERENCES `rol_entidad` (`rol`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Roles asociados a la entidad.';

--
-- Dumping data for table `roles_entidad`
--

/*!40000 ALTER TABLE `roles_entidad` DISABLE KEYS */;
/*!40000 ALTER TABLE `roles_entidad` ENABLE KEYS */;


--
-- Definition of table `transicion_estados`
--

DROP TABLE IF EXISTS `transicion_estados`;
CREATE TABLE `transicion_estados` (
  `estado_origen` varchar(15) NOT NULL,
  `accion` int(10) unsigned NOT NULL default '0',
  `estado_destino` varchar(15) NOT NULL,
  `prompt_accion` varchar(30) default NULL,
  `validador` varchar(255) default NULL,
  PRIMARY KEY  (`accion`,`estado_origen`),
  KEY `accion` (`accion`),
  KEY `estado_destino` (`estado_destino`),
  KEY `estado_origen` (`estado_origen`),
  CONSTRAINT `FK_transicion_estados_1` FOREIGN KEY (`accion`) REFERENCES `acciones_apps` (`accion`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_transicion_estados_destino` FOREIGN KEY (`estado_destino`) REFERENCES `estados` (`estado`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_transicion_estados_origen` FOREIGN KEY (`estado_origen`) REFERENCES `estados` (`estado`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `transicion_estados`
--

/*!40000 ALTER TABLE `transicion_estados` DISABLE KEYS */;
/*!40000 ALTER TABLE `transicion_estados` ENABLE KEYS */;


--
-- Definition of table `usuario_preferencias`
--

DROP TABLE IF EXISTS `usuario_preferencias`;
CREATE TABLE `usuario_preferencias` (
  `user_id` int(10) unsigned NOT NULL default '0',
  `preferencia_id` int(10) unsigned NOT NULL default '0',
  `valor` varchar(255) NOT NULL default '',
  PRIMARY KEY  (`preferencia_id`,`user_id`),
  KEY `FK_usuario_preferencias_2` (`user_id`),
  CONSTRAINT `FK_usuario_preferencias_2` FOREIGN KEY (`user_id`) REFERENCES `website_user` (`user_id`),
  CONSTRAINT `preferencia_FK` FOREIGN KEY (`preferencia_id`) REFERENCES `preferencias` (`preferencia_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `usuario_preferencias`
--

/*!40000 ALTER TABLE `usuario_preferencias` DISABLE KEYS */;
/*!40000 ALTER TABLE `usuario_preferencias` ENABLE KEYS */;


--
-- Definition of table `usuario_roles`
--

DROP TABLE IF EXISTS `usuario_roles`;
CREATE TABLE `usuario_roles` (
  `user_id` int(10) unsigned NOT NULL default '0',
  `rol_id` int(10) unsigned NOT NULL default '0',
  `desde` date NOT NULL default '0000-00-00',
  `hasta` date default NULL,
  `anulado` char(1) NOT NULL default 'F',
  PRIMARY KEY  (`rol_id`,`user_id`),
  KEY `FK_usuario_roles_2` (`user_id`),
  CONSTRAINT `FK_usuario_roles_1` FOREIGN KEY (`rol_id`) REFERENCES `roles` (`rol_id`),
  CONSTRAINT `FK_usuario_roles_2` FOREIGN KEY (`user_id`) REFERENCES `website_user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `usuario_roles`
--

/*!40000 ALTER TABLE `usuario_roles` DISABLE KEYS */;
/*!40000 ALTER TABLE `usuario_roles` ENABLE KEYS */;


--
-- Definition of table `website_user`
--

DROP TABLE IF EXISTS `website_user`;
CREATE TABLE `website_user` (
  `user_id` int(10) unsigned NOT NULL auto_increment,
  `login_name` varchar(60) NOT NULL default '',
  `login_password` varchar(60) NOT NULL default '',
  `nivel_visibilidad` varchar(255) default 'Todo',
  `nombre_completo` varchar(90) default NULL,
  `email` varchar(90) default NULL,
  `nro_legajo` int(11) default NULL,
  `nro_comprador` int(10) unsigned default NULL,
  PRIMARY KEY  (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `website_user`
--

/*!40000 ALTER TABLE `website_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `website_user` ENABLE KEYS */;


--
-- Definition of function `getAtributoTabla`
--

DROP FUNCTION IF EXISTS `getAtributoTabla`;

DELIMITER $$

/*!50003 SET @TEMP_SQL_MODE=@@SQL_MODE, SQL_MODE='' */ $$
CREATE DEFINER=`root`@`localhost` FUNCTION `getAtributoTabla`(p_tabla varchar(90),
						      p_objeto_id int,
                                                      p_nombre_atributo varchar(90)) RETURNS varchar(255) CHARSET latin1
BEGIN
   DECLARE v_atributo_id int;
   DECLARE resultado varchar(255);
   DECLARE no_rows BOOLEAN DEFAULT FALSE;
   DECLARE CONTINUE HANDLER FOR NOT FOUND SET no_rows = TRUE;

   select atributo_id
     into v_atributo_id
     from atributos_rol
    where tipo_objeto = 'TABLA'
      and (nombre = UPPER(p_nombre_atributo) or
           nombre is null);

   IF (no_rows) THEN
      RETURN '';
   END IF;

   select valor
     into resultado
     from atributos_entidad
    where tipo_objeto = 'TABLA'
      and nombre_objeto = p_tabla
      and objeto_id = p_objeto_id
      and atributo_id = v_atributo_id;

   IF (no_rows) THEN
      RETURN '';
   ELSE
      RETURN resultado;
   END IF;

END $$
/*!50003 SET SESSION SQL_MODE=@TEMP_SQL_MODE */  $$

DELIMITER ;

--
-- Definition of function `getAtributoTablaDate`
--

DROP FUNCTION IF EXISTS `getAtributoTablaDate`;

DELIMITER $$

/*!50003 SET @TEMP_SQL_MODE=@@SQL_MODE, SQL_MODE='' */ $$
CREATE DEFINER=`root`@`localhost` FUNCTION `getAtributoTablaDate`(p_tabla varchar(90),
						      p_objeto_id int,
                                                      p_nombre_atributo varchar(90)) RETURNS int(11)
BEGIN
   DECLARE resultado_tmp varchar(255);
   DECLARE resultado date;
   DECLARE CONTINUE HANDLER FOR 1292 SET resultado = NULL;

   SET resultado_tmp = getAtributoTabla(p_tabla,p_objeto_id,p_nombre_atributo);
   SET resultado = CAST(resultado_tmp as date);

   RETURN resultado;

END $$
/*!50003 SET SESSION SQL_MODE=@TEMP_SQL_MODE */  $$

DELIMITER ;

--
-- Definition of function `getAtributoTablaDateTime`
--

DROP FUNCTION IF EXISTS `getAtributoTablaDateTime`;

DELIMITER $$

/*!50003 SET @TEMP_SQL_MODE=@@SQL_MODE, SQL_MODE='' */ $$
CREATE DEFINER=`root`@`localhost` FUNCTION `getAtributoTablaDateTime`(p_tabla varchar(90),
						      p_objeto_id int,
                                                      p_nombre_atributo varchar(90)) RETURNS int(11)
BEGIN
   DECLARE resultado_tmp varchar(255);
   DECLARE resultado datetime;
   DECLARE CONTINUE HANDLER FOR 1292 SET resultado = NULL;

   SET resultado_tmp = getAtributoTabla(p_tabla,p_objeto_id,p_nombre_atributo);
   SET resultado = CAST(resultado_tmp as datetime);

   RETURN resultado;

END $$
/*!50003 SET SESSION SQL_MODE=@TEMP_SQL_MODE */  $$

DELIMITER ;

--
-- Definition of function `getAtributoTablaInt`
--

DROP FUNCTION IF EXISTS `getAtributoTablaInt`;

DELIMITER $$

/*!50003 SET @TEMP_SQL_MODE=@@SQL_MODE, SQL_MODE='' */ $$
CREATE DEFINER=`root`@`localhost` FUNCTION `getAtributoTablaInt`(p_tabla varchar(90),
						      p_objeto_id int,
                                                      p_nombre_atributo varchar(90)) RETURNS int(11)
BEGIN
   DECLARE resultado_tmp varchar(255);
   DECLARE resultado int;
   DECLARE CONTINUE HANDLER FOR 1292 SET resultado = NULL;

   SET resultado_tmp = getAtributoTabla(p_tabla,p_objeto_id,p_nombre_atributo);
   SET resultado = CAST(resultado_tmp as signed);

   RETURN resultado;

END $$
/*!50003 SET SESSION SQL_MODE=@TEMP_SQL_MODE */  $$

DELIMITER ;

--
-- Definition of function `getAtributoTablaReal`
--

DROP FUNCTION IF EXISTS `getAtributoTablaReal`;

DELIMITER $$

/*!50003 SET @TEMP_SQL_MODE=@@SQL_MODE, SQL_MODE='' */ $$
CREATE DEFINER=`root`@`localhost` FUNCTION `getAtributoTablaReal`(p_tabla varchar(90),
						      p_objeto_id int,
                                                      p_nombre_atributo varchar(90)) RETURNS int(11)
BEGIN
   DECLARE resultado_tmp varchar(255);
   DECLARE resultado real;
   DECLARE CONTINUE HANDLER FOR 1292 SET resultado = NULL;

   SET resultado_tmp = getAtributoTabla(p_tabla,p_objeto_id,p_nombre_atributo);
   SET resultado = CAST(resultado_tmp as decimal);

   RETURN resultado;

END $$
/*!50003 SET SESSION SQL_MODE=@TEMP_SQL_MODE */  $$

DELIMITER ;



/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
