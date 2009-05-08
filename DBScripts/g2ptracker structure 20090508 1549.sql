-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.0.77


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
-- Definition of table `g2p_tracker`.`acceso_menu`
--

DROP TABLE IF EXISTS `g2p_tracker`.`acceso_menu`;
CREATE TABLE  `g2p_tracker`.`acceso_menu` (
  `acceso_menu_id` int(10) unsigned NOT NULL auto_increment,
  `menu_id` int(10) unsigned NOT NULL default '0',
  `rol_id` int(10) unsigned default '0',
  `user_id` int(10) unsigned default '0',
  PRIMARY KEY  (`acceso_menu_id`),
  KEY `rol_FK` (`rol_id`),
  KEY `FK_acceso_menu_3` (`menu_id`),
  KEY `FK_acceso_menu_4` (`user_id`),
  KEY `FKBBA074DE8101218C` (`rol_id`),
  KEY `FKBBA074DE150B56FC` (`menu_id`),
  KEY `FKBBA074DEDA06AAD9` (`user_id`),
  CONSTRAINT `FKBBA074DE150B56FC` FOREIGN KEY (`menu_id`) REFERENCES `menu` (`menu_id`),
  CONSTRAINT `FKBBA074DE8101218C` FOREIGN KEY (`rol_id`) REFERENCES `roles` (`rol_id`),
  CONSTRAINT `FKBBA074DEDA06AAD9` FOREIGN KEY (`user_id`) REFERENCES `website_users` (`user_id`),
  CONSTRAINT `FK_acceso_menu_3` FOREIGN KEY (`menu_id`) REFERENCES `menu` (`menu_id`),
  CONSTRAINT `FK_acceso_menu_4` FOREIGN KEY (`user_id`) REFERENCES `website_users` (`user_id`),
  CONSTRAINT `rol_FK` FOREIGN KEY (`rol_id`) REFERENCES `roles` (`rol_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `g2p_tracker`.`acceso_menu`
--

/*!40000 ALTER TABLE `acceso_menu` DISABLE KEYS */;
LOCK TABLES `acceso_menu` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `acceso_menu` ENABLE KEYS */;


--
-- Definition of table `g2p_tracker`.`acciones_apps`
--

DROP TABLE IF EXISTS `g2p_tracker`.`acciones_apps`;
CREATE TABLE  `g2p_tracker`.`acciones_apps` (
  `accion_id` int(10) unsigned NOT NULL auto_increment,
  `nombre` varchar(90) NOT NULL,
  `descripcion` varchar(255) default NULL,
  `observaciones` varchar(255) default NULL,
  `circuito_id` int(11) NOT NULL,
  `manual` char(1) NOT NULL default 'V',
  PRIMARY KEY  (`accion_id`),
  KEY `fk_acciones_apps_circuitos_estados` (`circuito_id`),
  CONSTRAINT `fk_acciones_apps_circuitos_estados` FOREIGN KEY (`circuito_id`) REFERENCES `circuitos_estados` (`circuito_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `g2p_tracker`.`acciones_apps`
--

/*!40000 ALTER TABLE `acciones_apps` DISABLE KEYS */;
LOCK TABLES `acciones_apps` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `acciones_apps` ENABLE KEYS */;


--
-- Definition of table `g2p_tracker`.`aplica_circuito`
--

DROP TABLE IF EXISTS `g2p_tracker`.`aplica_circuito`;
CREATE TABLE  `g2p_tracker`.`aplica_circuito` (
  `aplica_circuito_id` int(11) NOT NULL,
  `tipo_objeto` varchar(15) NOT NULL,
  `nombre_objeto` varchar(45) NOT NULL,
  `tipo_detalle` varchar(15) NOT NULL,
  `nombre_detalle` varchar(45) NOT NULL,
  `circuito_id` int(11) NOT NULL,
  PRIMARY KEY  (`aplica_circuito_id`),
  UNIQUE KEY `unique_tipo_nombre_objeto_tipo_nombre_detalle` (`tipo_objeto`,`nombre_objeto`,`tipo_detalle`,`nombre_detalle`),
  KEY `fk_aplica_circuito_circuitos_estados` (`circuito_id`),
  CONSTRAINT `fk_aplica_circuito_circuitos_estados` FOREIGN KEY (`circuito_id`) REFERENCES `circuitos_estados` (`circuito_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `g2p_tracker`.`aplica_circuito`
--

/*!40000 ALTER TABLE `aplica_circuito` DISABLE KEYS */;
LOCK TABLES `aplica_circuito` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `aplica_circuito` ENABLE KEYS */;


--
-- Definition of table `g2p_tracker`.`atributos_configuracion`
--

DROP TABLE IF EXISTS `g2p_tracker`.`atributos_configuracion`;
CREATE TABLE  `g2p_tracker`.`atributos_configuracion` (
  `configuracion_id` int(15) unsigned NOT NULL,
  `atributo_id` int(15) unsigned NOT NULL,
  `valor` varchar(255) NOT NULL,
  `valor_hasta` varchar(255) NOT NULL,
  PRIMARY KEY  (`configuracion_id`,`atributo_id`),
  KEY `atributo_id` (`atributo_id`),
  KEY `FK3E09BA2392F06DF8` (`atributo_id`),
  KEY `FK3E09BA234F91D51E` (`configuracion_id`),
  CONSTRAINT `atributos_configuracion_ibfk_1` FOREIGN KEY (`configuracion_id`) REFERENCES `configuracion` (`configuracion_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `atributos_configuracion_ibfk_2` FOREIGN KEY (`atributo_id`) REFERENCES `atributos_rol` (`atributo_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK3E09BA234F91D51E` FOREIGN KEY (`configuracion_id`) REFERENCES `configuracion` (`configuracion_id`),
  CONSTRAINT `FK3E09BA2392F06DF8` FOREIGN KEY (`atributo_id`) REFERENCES `atributos_rol` (`atributo_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `g2p_tracker`.`atributos_configuracion`
--

/*!40000 ALTER TABLE `atributos_configuracion` DISABLE KEYS */;
LOCK TABLES `atributos_configuracion` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `atributos_configuracion` ENABLE KEYS */;


--
-- Definition of table `g2p_tracker`.`atributos_entidad`
--

DROP TABLE IF EXISTS `g2p_tracker`.`atributos_entidad`;
CREATE TABLE  `g2p_tracker`.`atributos_entidad` (
  `atributo_entidad_id` int(15) unsigned NOT NULL auto_increment,
  `valor` varchar(255) default NULL COMMENT 'Valor del atributo',
  `valor_entero` int(11) default NULL,
  `valor_real` double default NULL,
  `valor_fecha` date default NULL,
  `valor_logico` char(1) default NULL,
  `atributo_id` int(15) unsigned NOT NULL COMMENT 'IdentificaciÃ³n Ãºnica de atributo de entidad',
  `entidad_id` int(15) unsigned default NULL,
  `objeto_id` int(15) unsigned default NULL,
  `tipo_objeto` varchar(15) default NULL,
  `nombre_objeto` varchar(45) default NULL,
  PRIMARY KEY  USING BTREE (`atributo_entidad_id`),
  UNIQUE KEY `unique_index` USING BTREE (`atributo_id`,`objeto_id`,`tipo_objeto`,`nombre_objeto`),
  UNIQUE KEY `unique_entidad_index` USING BTREE (`entidad_id`,`atributo_id`),
  KEY `atributo_id` (`atributo_id`),
  KEY `entidad_id` (`entidad_id`),
  KEY `FK6C60A66592F06DF8` (`atributo_id`),
  KEY `FK6C60A665238589EB` (`entidad_id`),
  CONSTRAINT `FK6C60A665238589EB` FOREIGN KEY (`entidad_id`) REFERENCES `entidad_externa` (`entidad_id`),
  CONSTRAINT `FK6C60A66592F06DF8` FOREIGN KEY (`atributo_id`) REFERENCES `atributos_rol` (`atributo_id`),
  CONSTRAINT `FK_atributos_entidad_1` FOREIGN KEY (`atributo_id`) REFERENCES `atributos_rol` (`atributo_id`),
  CONSTRAINT `FK_atributos_entidad_2` FOREIGN KEY (`entidad_id`) REFERENCES `entidad_externa` (`entidad_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Atributos asociados a la entidad; InnoDB free: 6144 kB; (`at';

--
-- Dumping data for table `g2p_tracker`.`atributos_entidad`
--

/*!40000 ALTER TABLE `atributos_entidad` DISABLE KEYS */;
LOCK TABLES `atributos_entidad` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `atributos_entidad` ENABLE KEYS */;


--
-- Definition of table `g2p_tracker`.`atributos_rol`
--

DROP TABLE IF EXISTS `g2p_tracker`.`atributos_rol`;
CREATE TABLE  `g2p_tracker`.`atributos_rol` (
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
  KEY `FK5475A44BFBCB950B` (`rol`),
  KEY `FK5475A44B697AB8EC` (`clase_atributo_rol_id`),
  CONSTRAINT `FK5475A44B697AB8EC` FOREIGN KEY (`clase_atributo_rol_id`) REFERENCES `clase_atributo_rol` (`clase_atributo_rol_id`),
  CONSTRAINT `FK5475A44BFBCB950B` FOREIGN KEY (`rol`) REFERENCES `rol_entidad` (`rol`),
  CONSTRAINT `FK_atributos_rol_3` FOREIGN KEY (`tipo_objeto`, `nombre_objeto`) REFERENCES `diccionario_aplicacion` (`tipo_objeto`, `nombre_objeto`),
  CONSTRAINT `FK_clase_atributo_rol_1` FOREIGN KEY (`rol`) REFERENCES `rol_entidad` (`rol`),
  CONSTRAINT `FK_clase_atributo_rol_2` FOREIGN KEY (`clase_atributo_rol_id`) REFERENCES `clase_atributo_rol` (`clase_atributo_rol_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `g2p_tracker`.`atributos_rol`
--

/*!40000 ALTER TABLE `atributos_rol` DISABLE KEYS */;
LOCK TABLES `atributos_rol` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `atributos_rol` ENABLE KEYS */;


--
-- Definition of table `g2p_tracker`.`attachment`
--

DROP TABLE IF EXISTS `g2p_tracker`.`attachment`;
CREATE TABLE  `g2p_tracker`.`attachment` (
  `documento_id` int(10) NOT NULL,
  `track_id` int(10) unsigned NOT NULL,
  PRIMARY KEY  (`documento_id`,`track_id`),
  KEY `fk_attachment_documentos` (`documento_id`),
  KEY `fk_attachment_tracks` (`track_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `g2p_tracker`.`attachment`
--

/*!40000 ALTER TABLE `attachment` DISABLE KEYS */;
LOCK TABLES `attachment` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `attachment` ENABLE KEYS */;


--
-- Definition of table `g2p_tracker`.`audita_estados_circuitos`
--

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

--
-- Dumping data for table `g2p_tracker`.`audita_estados_circuitos`
--

/*!40000 ALTER TABLE `audita_estados_circuitos` DISABLE KEYS */;
LOCK TABLES `audita_estados_circuitos` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `audita_estados_circuitos` ENABLE KEYS */;


--
-- Definition of table `g2p_tracker`.`circuitos_estados`
--

DROP TABLE IF EXISTS `g2p_tracker`.`circuitos_estados`;
CREATE TABLE  `g2p_tracker`.`circuitos_estados` (
  `circuito_id` int(11) NOT NULL,
  `nombre` varchar(40) NOT NULL,
  `descripcion` varchar(255) default NULL,
  `observaciones` varchar(255) default NULL,
  PRIMARY KEY  (`circuito_id`),
  UNIQUE KEY `unique_nombre` (`nombre`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `g2p_tracker`.`circuitos_estados`
--

/*!40000 ALTER TABLE `circuitos_estados` DISABLE KEYS */;
LOCK TABLES `circuitos_estados` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `circuitos_estados` ENABLE KEYS */;


--
-- Definition of table `g2p_tracker`.`clase_atributo_rol`
--

DROP TABLE IF EXISTS `g2p_tracker`.`clase_atributo_rol`;
CREATE TABLE  `g2p_tracker`.`clase_atributo_rol` (
  `clase_atributo_rol_id` int(15) unsigned NOT NULL auto_increment COMMENT 'IdentificaciÃ³n Ãºnica para la clase de atributo',
  `etiqueta` varchar(90) NOT NULL COMMENT 'Etiqueta que le corresponde a la clase de atributos',
  PRIMARY KEY  (`clase_atributo_rol_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `g2p_tracker`.`clase_atributo_rol`
--

/*!40000 ALTER TABLE `clase_atributo_rol` DISABLE KEYS */;
LOCK TABLES `clase_atributo_rol` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `clase_atributo_rol` ENABLE KEYS */;


--
-- Definition of table `g2p_tracker`.`clase_lov_atributo`
--

DROP TABLE IF EXISTS `g2p_tracker`.`clase_lov_atributo`;
CREATE TABLE  `g2p_tracker`.`clase_lov_atributo` (
  `clase_lov_atributo_id` int(15) unsigned NOT NULL auto_increment,
  `nombre` varchar(90) NOT NULL,
  `descripcion` varchar(255) default NULL,
  `observaciones` varchar(255) default NULL,
  PRIMARY KEY  (`clase_lov_atributo_id`),
  UNIQUE KEY `nombre_UK` (`nombre`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `g2p_tracker`.`clase_lov_atributo`
--

/*!40000 ALTER TABLE `clase_lov_atributo` DISABLE KEYS */;
LOCK TABLES `clase_lov_atributo` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `clase_lov_atributo` ENABLE KEYS */;


--
-- Definition of table `g2p_tracker`.`configuracion`
--

DROP TABLE IF EXISTS `g2p_tracker`.`configuracion`;
CREATE TABLE  `g2p_tracker`.`configuracion` (
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
  KEY `FK732BBAA75EC5F36D` (`esquema_configuracion_id`),
  CONSTRAINT `configuracion_ibfk_1` FOREIGN KEY (`esquema_configuracion_id`) REFERENCES `esquema_configuracion` (`esquema_configuracion_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK732BBAA75EC5F36D` FOREIGN KEY (`esquema_configuracion_id`) REFERENCES `esquema_configuracion` (`esquema_configuracion_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `g2p_tracker`.`configuracion`
--

/*!40000 ALTER TABLE `configuracion` DISABLE KEYS */;
LOCK TABLES `configuracion` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `configuracion` ENABLE KEYS */;


--
-- Definition of table `g2p_tracker`.`diccionario_aplicacion`
--

DROP TABLE IF EXISTS `g2p_tracker`.`diccionario_aplicacion`;
CREATE TABLE  `g2p_tracker`.`diccionario_aplicacion` (
  `tipo_objeto` varchar(15) NOT NULL default '' COMMENT 'tipo del objeto (TABLA, VISTA, PROGRAMA, INDICE, etc.)',
  `nombre_objeto` varchar(45) NOT NULL default '' COMMENT 'Nombre del objeto de la aplicacin',
  PRIMARY KEY  (`tipo_objeto`,`nombre_objeto`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Permite guardar nombres de objetos usados en la aplicacin, t';

--
-- Dumping data for table `g2p_tracker`.`diccionario_aplicacion`
--

/*!40000 ALTER TABLE `diccionario_aplicacion` DISABLE KEYS */;
LOCK TABLES `diccionario_aplicacion` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `diccionario_aplicacion` ENABLE KEYS */;


--
-- Definition of table `g2p_tracker`.`diccionario_aplicacion_detalle`
--

DROP TABLE IF EXISTS `g2p_tracker`.`diccionario_aplicacion_detalle`;
CREATE TABLE  `g2p_tracker`.`diccionario_aplicacion_detalle` (
  `id_diccionario` int(11) NOT NULL,
  `tipo_objeto` varchar(15) NOT NULL default '' COMMENT 'Tipo Objeto. Relaciona al objeto',
  `nombre_objeto` varchar(45) NOT NULL default '' COMMENT 'Nombre del objeto. relaciona al objeto',
  `tipo_detalle` varchar(15) NOT NULL default '' COMMENT 'Tipo del detalle: (COLUMNA, CAMPO, etc.)',
  `nombre_detalle` varchar(45) NOT NULL default '' COMMENT 'Nombre del detalle del objeto. Por ejemplo nombre de la columna de una tabla',
  PRIMARY KEY  (`id_diccionario`),
  UNIQUE KEY `unique_tipo_nombre_objeto_tipo_nombre_detalle` (`tipo_objeto`,`nombre_objeto`,`tipo_detalle`,`nombre_detalle`),
  CONSTRAINT `FK_diccionario_aplicacion_detalle_1` FOREIGN KEY (`tipo_objeto`, `nombre_objeto`) REFERENCES `diccionario_aplicacion` (`tipo_objeto`, `nombre_objeto`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Detalle de un objeto de la aplicacin. En el caso de tabla, s';

--
-- Dumping data for table `g2p_tracker`.`diccionario_aplicacion_detalle`
--

/*!40000 ALTER TABLE `diccionario_aplicacion_detalle` DISABLE KEYS */;
LOCK TABLES `diccionario_aplicacion_detalle` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `diccionario_aplicacion_detalle` ENABLE KEYS */;


--
-- Definition of table `g2p_tracker`.`documentos`
--

DROP TABLE IF EXISTS `g2p_tracker`.`documentos`;
CREATE TABLE  `g2p_tracker`.`documentos` (
  `id_documento` int(10) NOT NULL,
  `titulo` varchar(100) NOT NULL,
  `descripción` varchar(255) default NULL,
  `path` varchar(255) NOT NULL,
  `version` double NOT NULL,
  PRIMARY KEY  (`id_documento`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `g2p_tracker`.`documentos`
--

/*!40000 ALTER TABLE `documentos` DISABLE KEYS */;
LOCK TABLES `documentos` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `documentos` ENABLE KEYS */;


--
-- Definition of table `g2p_tracker`.`entidad_externa`
--

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

--
-- Dumping data for table `g2p_tracker`.`entidad_externa`
--

/*!40000 ALTER TABLE `entidad_externa` DISABLE KEYS */;
LOCK TABLES `entidad_externa` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `entidad_externa` ENABLE KEYS */;


--
-- Definition of table `g2p_tracker`.`esquema_configuracion`
--

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

--
-- Dumping data for table `g2p_tracker`.`esquema_configuracion`
--

/*!40000 ALTER TABLE `esquema_configuracion` DISABLE KEYS */;
LOCK TABLES `esquema_configuracion` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `esquema_configuracion` ENABLE KEYS */;


--
-- Definition of table `g2p_tracker`.`estados`
--

DROP TABLE IF EXISTS `g2p_tracker`.`estados`;
CREATE TABLE  `g2p_tracker`.`estados` (
  `estado_id` int(11) NOT NULL,
  `nombre` varchar(30) NOT NULL,
  `descripcion` varchar(255) default NULL,
  `observaciones` varchar(255) default NULL,
  `circuito_id` int(11) NOT NULL,
  PRIMARY KEY  (`estado_id`),
  UNIQUE KEY `unique_nombre` (`nombre`),
  KEY `fk_estados_circuitos_estados` (`circuito_id`),
  CONSTRAINT `fk_estados_circuitos_estados` FOREIGN KEY (`circuito_id`) REFERENCES `circuitos_estados` (`circuito_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `g2p_tracker`.`estados`
--

/*!40000 ALTER TABLE `estados` DISABLE KEYS */;
LOCK TABLES `estados` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `estados` ENABLE KEYS */;


--
-- Definition of table `g2p_tracker`.`lov_atributo`
--

DROP TABLE IF EXISTS `g2p_tracker`.`lov_atributo`;
CREATE TABLE  `g2p_tracker`.`lov_atributo` (
  `clase_lov_atributo_id` int(15) unsigned NOT NULL,
  `valor` varchar(255) NOT NULL,
  `descripcion` varchar(255) default NULL,
  PRIMARY KEY  (`clase_lov_atributo_id`),
  UNIQUE KEY `unique_lov_id_valor` (`clase_lov_atributo_id`,`valor`),
  KEY `FK8A7DE224C50A3144` (`clase_lov_atributo_id`),
  CONSTRAINT `FK8A7DE224C50A3144` FOREIGN KEY (`clase_lov_atributo_id`) REFERENCES `clase_lov_atributo` (`clase_lov_atributo_id`),
  CONSTRAINT `lov_atributo_ibfk_1` FOREIGN KEY (`clase_lov_atributo_id`) REFERENCES `clase_lov_atributo` (`clase_lov_atributo_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `g2p_tracker`.`lov_atributo`
--

/*!40000 ALTER TABLE `lov_atributo` DISABLE KEYS */;
LOCK TABLES `lov_atributo` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `lov_atributo` ENABLE KEYS */;


--
-- Definition of table `g2p_tracker`.`menu`
--

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

--
-- Dumping data for table `g2p_tracker`.`menu`
--

/*!40000 ALTER TABLE `menu` DISABLE KEYS */;
LOCK TABLES `menu` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `menu` ENABLE KEYS */;


--
-- Definition of table `g2p_tracker`.`posts`
--

DROP TABLE IF EXISTS `g2p_tracker`.`posts`;
CREATE TABLE  `g2p_tracker`.`posts` (
  `post_id` int(11) NOT NULL,
  `contenido` text NOT NULL,
  `user_id` int(10) unsigned NOT NULL,
  `track_id` int(10) unsigned NOT NULL,
  `fecha_creacion` datetime NOT NULL,
  PRIMARY KEY  (`post_id`),
  KEY `fk_website_users` (`user_id`),
  KEY `fk_posts_tracks` (`track_id`),
  CONSTRAINT `fk_posts_website_users` FOREIGN KEY (`user_id`) REFERENCES `website_users` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_posts_tracks` FOREIGN KEY (`track_id`) REFERENCES `tracks` (`track_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `g2p_tracker`.`posts`
--

/*!40000 ALTER TABLE `posts` DISABLE KEYS */;
LOCK TABLES `posts` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `posts` ENABLE KEYS */;


--
-- Definition of table `g2p_tracker`.`preferencias`
--

DROP TABLE IF EXISTS `g2p_tracker`.`preferencias`;
CREATE TABLE  `g2p_tracker`.`preferencias` (
  `preferencia_id` int(10) unsigned NOT NULL default '0',
  `nombre` varchar(90) NOT NULL default '',
  `descripcion` varchar(255) default NULL,
  `observaciones` varchar(255) default NULL,
  `default` varchar(255) NOT NULL default '',
  PRIMARY KEY  (`preferencia_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `g2p_tracker`.`preferencias`
--

/*!40000 ALTER TABLE `preferencias` DISABLE KEYS */;
LOCK TABLES `preferencias` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `preferencias` ENABLE KEYS */;


--
-- Definition of table `g2p_tracker`.`proveedores_sso`
--

DROP TABLE IF EXISTS `g2p_tracker`.`proveedores_sso`;
CREATE TABLE  `g2p_tracker`.`proveedores_sso` (
  `proveedor_sso_id` int(11) NOT NULL,
  `nombre` varchar(40) NOT NULL,
  `descripcion` varchar(255) default NULL,
  `observaciones` varchar(255) default NULL,
  `url_discovery` text NOT NULL,
  PRIMARY KEY  (`proveedor_sso_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `g2p_tracker`.`proveedores_sso`
--

/*!40000 ALTER TABLE `proveedores_sso` DISABLE KEYS */;
LOCK TABLES `proveedores_sso` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `proveedores_sso` ENABLE KEYS */;


--
-- Definition of table `g2p_tracker`.`rol_entidad`
--

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

--
-- Dumping data for table `g2p_tracker`.`rol_entidad`
--

/*!40000 ALTER TABLE `rol_entidad` DISABLE KEYS */;
LOCK TABLES `rol_entidad` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `rol_entidad` ENABLE KEYS */;


--
-- Definition of table `g2p_tracker`.`roles`
--

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

--
-- Dumping data for table `g2p_tracker`.`roles`
--

/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
LOCK TABLES `roles` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;


--
-- Definition of table `g2p_tracker`.`roles_entidad`
--

DROP TABLE IF EXISTS `g2p_tracker`.`roles_entidad`;
CREATE TABLE  `g2p_tracker`.`roles_entidad` (
  `entidad_id` int(15) unsigned NOT NULL,
  `rol_id` char(4) NOT NULL,
  `desde` date NOT NULL COMMENT 'Fecha desde cuando estÃ¡ activo en el rol',
  `hasta` date default NULL COMMENT 'Hasta que fecha estÃ¡ activo en el rol',
  `activo` char(1) NOT NULL default 'F' COMMENT 'Indica si el rol est''aactivo, sino no es considerado. Activar garantiza reglas para atributos',
  `anulado` char(1) NOT NULL default 'F' COMMENT 'Indica que el registro est''aanulado. Baja lÃ³gica',
  PRIMARY KEY  (`entidad_id`,`rol_id`),
  KEY `entidad_id` (`entidad_id`),
  KEY `rol` (`rol_id`),
  KEY `FK86408267FBCB950B` (`rol_id`),
  KEY `FK86408267238589EB` (`entidad_id`),
  CONSTRAINT `FK86408267238589EB` FOREIGN KEY (`entidad_id`) REFERENCES `entidad_externa` (`entidad_id`),
  CONSTRAINT `FK86408267FBCB950B` FOREIGN KEY (`rol_id`) REFERENCES `rol_entidad` (`rol`),
  CONSTRAINT `FK_roles_entidad_1` FOREIGN KEY (`entidad_id`) REFERENCES `entidad_externa` (`entidad_id`),
  CONSTRAINT `FK_roles_entidad_2` FOREIGN KEY (`rol_id`) REFERENCES `rol_entidad` (`rol`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Roles asociados a la entidad.';

--
-- Dumping data for table `g2p_tracker`.`roles_entidad`
--

/*!40000 ALTER TABLE `roles_entidad` DISABLE KEYS */;
LOCK TABLES `roles_entidad` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `roles_entidad` ENABLE KEYS */;


--
-- Definition of table `g2p_tracker`.`roles_per_website_users`
--

DROP TABLE IF EXISTS `g2p_tracker`.`roles_per_website_users`;
CREATE TABLE  `g2p_tracker`.`roles_per_website_users` (
  `user_id` int(10) unsigned NOT NULL default '0',
  `rol_id` int(10) unsigned NOT NULL default '0',
  `desde` date NOT NULL,
  `hasta` date default NULL,
  `anulado` char(1) NOT NULL default 'F',
  `OBJ_VERSION` datetime default NULL,
  PRIMARY KEY  (`rol_id`,`user_id`),
  KEY `fk_website_users` (`user_id`),
  KEY `fk_roles` (`rol_id`),
  CONSTRAINT `fk_website_users` FOREIGN KEY (`user_id`) REFERENCES `website_users` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_roles` FOREIGN KEY (`rol_id`) REFERENCES `roles` (`rol_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `g2p_tracker`.`roles_per_website_users`
--

/*!40000 ALTER TABLE `roles_per_website_users` DISABLE KEYS */;
LOCK TABLES `roles_per_website_users` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `roles_per_website_users` ENABLE KEYS */;


--
-- Definition of table `g2p_tracker`.`sticky_notes`
--

DROP TABLE IF EXISTS `g2p_tracker`.`sticky_notes`;
CREATE TABLE  `g2p_tracker`.`sticky_notes` (
  `sticky_note_id` int(11) NOT NULL,
  `titulo` varchar(90) NOT NULL,
  `contenido` text,
  `track_id` int(10) unsigned default NULL,
  `pegado` tinyint(1) NOT NULL,
  PRIMARY KEY  (`sticky_note_id`),
  KEY `fk_sticky_notes_tracks` (`track_id`),
  CONSTRAINT `fk_sticky_notes_tracks` FOREIGN KEY (`track_id`) REFERENCES `tracks` (`track_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `g2p_tracker`.`sticky_notes`
--

/*!40000 ALTER TABLE `sticky_notes` DISABLE KEYS */;
LOCK TABLES `sticky_notes` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `sticky_notes` ENABLE KEYS */;


--
-- Definition of table `g2p_tracker`.`tags`
--

DROP TABLE IF EXISTS `g2p_tracker`.`tags`;
CREATE TABLE  `g2p_tracker`.`tags` (
  `tag_id` int(10) NOT NULL,
  `tag` varchar(45) NOT NULL,
  `descripcion` varchar(255) default NULL,
  `observaciones` varchar(255) default NULL,
  `tag_id_grupo` int(10) default NULL,
  PRIMARY KEY  (`tag_id`),
  UNIQUE KEY `unique_tag` (`tag`),
  KEY `fk_tags_tags` (`tag_id_grupo`),
  CONSTRAINT `fk_tags_tags` FOREIGN KEY (`tag_id_grupo`) REFERENCES `tags` (`tag_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `g2p_tracker`.`tags`
--

/*!40000 ALTER TABLE `tags` DISABLE KEYS */;
LOCK TABLES `tags` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `tags` ENABLE KEYS */;


--
-- Definition of table `g2p_tracker`.`tags_per_tracks`
--

DROP TABLE IF EXISTS `g2p_tracker`.`tags_per_tracks`;
CREATE TABLE  `g2p_tracker`.`tags_per_tracks` (
  `tag_id` int(10) NOT NULL,
  `track_id` int(10) unsigned NOT NULL,
  PRIMARY KEY  (`tag_id`,`track_id`),
  KEY `fk_tags` (`tag_id`),
  KEY `fk_tracks` (`track_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `g2p_tracker`.`tags_per_tracks`
--

/*!40000 ALTER TABLE `tags_per_tracks` DISABLE KEYS */;
LOCK TABLES `tags_per_tracks` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `tags_per_tracks` ENABLE KEYS */;


--
-- Definition of table `g2p_tracker`.`tracks`
--

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
  `estado_id` int(11) NOT NULL,
  `user_id_owner` int(10) unsigned NOT NULL,
  PRIMARY KEY  (`track_id`),
  KEY `fk_estados` (`estado_id`),
  KEY `fk_website_user` (`user_id_owner`),
  CONSTRAINT `fk_tracks_estados` FOREIGN KEY (`estado_id`) REFERENCES `estados` (`estado_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_tracks_website_user` FOREIGN KEY (`user_id_owner`) REFERENCES `website_users` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Tabla que almacena cada track en la aplicación';

--
-- Dumping data for table `g2p_tracker`.`tracks`
--

/*!40000 ALTER TABLE `tracks` DISABLE KEYS */;
LOCK TABLES `tracks` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `tracks` ENABLE KEYS */;


--
-- Definition of table `g2p_tracker`.`transicion_estados`
--

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

--
-- Dumping data for table `g2p_tracker`.`transicion_estados`
--

/*!40000 ALTER TABLE `transicion_estados` DISABLE KEYS */;
LOCK TABLES `transicion_estados` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `transicion_estados` ENABLE KEYS */;


--
-- Definition of table `g2p_tracker`.`usuario_preferencias`
--

DROP TABLE IF EXISTS `g2p_tracker`.`usuario_preferencias`;
CREATE TABLE  `g2p_tracker`.`usuario_preferencias` (
  `user_id` int(10) unsigned NOT NULL default '0',
  `preferencia_id` int(10) unsigned NOT NULL default '0',
  `valor` varchar(255) NOT NULL default '',
  PRIMARY KEY  (`preferencia_id`,`user_id`),
  KEY `FK_usuario_preferencias_2` (`user_id`),
  KEY `FKC4089EC2FC0A158B` (`preferencia_id`),
  KEY `FKC4089EC2DA06AAD9` (`user_id`),
  CONSTRAINT `FKC4089EC2DA06AAD9` FOREIGN KEY (`user_id`) REFERENCES `website_users` (`user_id`),
  CONSTRAINT `FKC4089EC2FC0A158B` FOREIGN KEY (`preferencia_id`) REFERENCES `preferencias` (`preferencia_id`),
  CONSTRAINT `FK_usuario_preferencias_2` FOREIGN KEY (`user_id`) REFERENCES `website_users` (`user_id`),
  CONSTRAINT `preferencia_FK` FOREIGN KEY (`preferencia_id`) REFERENCES `preferencias` (`preferencia_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `g2p_tracker`.`usuario_preferencias`
--

/*!40000 ALTER TABLE `usuario_preferencias` DISABLE KEYS */;
LOCK TABLES `usuario_preferencias` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `usuario_preferencias` ENABLE KEYS */;


--
-- Definition of table `g2p_tracker`.`website_users`
--

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

--
-- Dumping data for table `g2p_tracker`.`website_users`
--

/*!40000 ALTER TABLE `website_users` DISABLE KEYS */;
LOCK TABLES `website_users` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `website_users` ENABLE KEYS */;


--
-- Definition of table `g2p_tracker`.`website_users_per_proveedores_openid`
--

DROP TABLE IF EXISTS `g2p_tracker`.`website_users_per_proveedores_openid`;
CREATE TABLE  `g2p_tracker`.`website_users_per_proveedores_openid` (
  `user_id` int(10) unsigned NOT NULL,
  `proveedor_sso_id` int(11) NOT NULL,
  `fecha_asociacion` datetime NOT NULL,
  PRIMARY KEY  (`user_id`,`proveedor_sso_id`),
  KEY `fk_website_users` (`user_id`),
  KEY `fk_proveedores_sso` (`proveedor_sso_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `g2p_tracker`.`website_users_per_proveedores_openid`
--

/*!40000 ALTER TABLE `website_users_per_proveedores_openid` DISABLE KEYS */;
LOCK TABLES `website_users_per_proveedores_openid` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `website_users_per_proveedores_openid` ENABLE KEYS */;


--
-- Definition of table `g2p_tracker`.`workers_per_tracks`
--

DROP TABLE IF EXISTS `g2p_tracker`.`workers_per_tracks`;
CREATE TABLE  `g2p_tracker`.`workers_per_tracks` (
  `track_id` int(10) unsigned NOT NULL,
  `user_id` int(10) unsigned NOT NULL,
  PRIMARY KEY  (`track_id`,`user_id`),
  KEY `fk_tracks` (`track_id`),
  KEY `fk_website_users` (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `g2p_tracker`.`workers_per_tracks`
--

/*!40000 ALTER TABLE `workers_per_tracks` DISABLE KEYS */;
LOCK TABLES `workers_per_tracks` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `workers_per_tracks` ENABLE KEYS */;


--
-- Definition of function `g2p_tracker`.`getAtributoTabla`
--

DROP FUNCTION IF EXISTS `g2p_tracker`.`getAtributoTabla`;

DELIMITER $$

/*!50003 SET @TEMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,STRICT_ALL_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,TRADITIONAL,NO_AUTO_CREATE_USER' */ $$
CREATE DEFINER=`root`@`localhost` FUNCTION  `g2p_tracker`.`getAtributoTabla`(p_tabla varchar(90),
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
-- Definition of function `g2p_tracker`.`getAtributoTablaDate`
--

DROP FUNCTION IF EXISTS `g2p_tracker`.`getAtributoTablaDate`;

DELIMITER $$

/*!50003 SET @TEMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,STRICT_ALL_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,TRADITIONAL,NO_AUTO_CREATE_USER' */ $$
CREATE DEFINER=`root`@`localhost` FUNCTION  `g2p_tracker`.`getAtributoTablaDate`(p_tabla varchar(90),
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
-- Definition of function `g2p_tracker`.`getAtributoTablaDateTime`
--

DROP FUNCTION IF EXISTS `g2p_tracker`.`getAtributoTablaDateTime`;

DELIMITER $$

/*!50003 SET @TEMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,STRICT_ALL_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,TRADITIONAL,NO_AUTO_CREATE_USER' */ $$
CREATE DEFINER=`root`@`localhost` FUNCTION  `g2p_tracker`.`getAtributoTablaDateTime`(p_tabla varchar(90),
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
-- Definition of function `g2p_tracker`.`getAtributoTablaInt`
--

DROP FUNCTION IF EXISTS `g2p_tracker`.`getAtributoTablaInt`;

DELIMITER $$

/*!50003 SET @TEMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,STRICT_ALL_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,TRADITIONAL,NO_AUTO_CREATE_USER' */ $$
CREATE DEFINER=`root`@`localhost` FUNCTION  `g2p_tracker`.`getAtributoTablaInt`(p_tabla varchar(90),
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
-- Definition of function `g2p_tracker`.`getAtributoTablaReal`
--

DROP FUNCTION IF EXISTS `g2p_tracker`.`getAtributoTablaReal`;

DELIMITER $$

/*!50003 SET @TEMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,STRICT_ALL_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,TRADITIONAL,NO_AUTO_CREATE_USER' */ $$
CREATE DEFINER=`root`@`localhost` FUNCTION  `g2p_tracker`.`getAtributoTablaReal`(p_tabla varchar(90),
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
