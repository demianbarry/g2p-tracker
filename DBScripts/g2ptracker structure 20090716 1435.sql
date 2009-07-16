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
  `acceso_menu_id` int(10) unsigned NOT NULL auto_increment,
  `menu_id` int(10) unsigned NOT NULL default '0',
  `rol_id` int(10) unsigned default '0',
  `user_id` int(10) unsigned default '0',
  PRIMARY KEY  (`acceso_menu_id`),
  KEY `rol_FK` (`rol_id`),
  KEY `FK_acceso_menu_3` (`menu_id`),
  KEY `FK_acceso_menu_4` (`user_id`),
  CONSTRAINT `FK_acceso_menu_3` FOREIGN KEY (`menu_id`) REFERENCES `menu` (`menu_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_acceso_menu_4` FOREIGN KEY (`user_id`) REFERENCES `website_users` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `rol_FK` FOREIGN KEY (`rol_id`) REFERENCES `roles` (`rol_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `acceso_menu`
--

/*!40000 ALTER TABLE `acceso_menu` DISABLE KEYS */;
INSERT INTO `acceso_menu` (`acceso_menu_id`,`menu_id`,`rol_id`,`user_id`) VALUES 
 (1,1,NULL,11),
 (2,2,NULL,11),
 (3,3,NULL,11),
 (4,4,NULL,11),
 (5,5,1,NULL),
 (6,1,NULL,14),
 (7,2,NULL,14),
 (8,3,NULL,14),
 (9,4,NULL,14),
 (10,5,NULL,14),
 (11,6,NULL,14);
/*!40000 ALTER TABLE `acceso_menu` ENABLE KEYS */;


--
-- Definition of table `acciones_apps`
--

DROP TABLE IF EXISTS `acciones_apps`;
CREATE TABLE `acciones_apps` (
  `accion_id` int(10) unsigned NOT NULL auto_increment,
  `nombre` varchar(90) NOT NULL,
  `descripcion` varchar(255) default NULL,
  `observaciones` varchar(255) default NULL,
  `circuito_id` int(11) NOT NULL,
  `manual` char(1) NOT NULL default 'V',
  `accion` int(11) default NULL,
  `circuito` varchar(255) default NULL,
  PRIMARY KEY  (`accion_id`),
  KEY `fk_acciones_apps_circuitos_estados` (`circuito_id`),
  CONSTRAINT `fk_acciones_apps_circuitos_estados` FOREIGN KEY (`circuito_id`) REFERENCES `circuitos_estados` (`circuito_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `acciones_apps`
--

/*!40000 ALTER TABLE `acciones_apps` DISABLE KEYS */;
INSERT INTO `acciones_apps` (`accion_id`,`nombre`,`descripcion`,`observaciones`,`circuito_id`,`manual`,`accion`,`circuito`) VALUES 
 (1,'Completar','Completar el track hasta dejarlo listo para poder iniciarlo',NULL,1,'V',NULL,NULL),
 (2,'Iniciar','Se comienza a trabajar sobre el track',NULL,1,'V',NULL,NULL),
 (3,'Finalizar','Se termina el trabajo sobre el track',NULL,1,'V',NULL,NULL),
 (4,'Aceptar','Se acepta el trabajo producido en el track',NULL,1,'V',NULL,NULL),
 (5,'Rechazar','Se rechaza el trabajo producido en el track',NULL,1,'V',NULL,NULL),
 (6,'Revisar','Se revisa el track rechazado para su corrección.',NULL,1,'V',NULL,NULL),
 (7,'Probar','Acción de prueba',NULL,2,'V',NULL,NULL);
/*!40000 ALTER TABLE `acciones_apps` ENABLE KEYS */;


--
-- Definition of table `aplica_circuito`
--

DROP TABLE IF EXISTS `aplica_circuito`;
CREATE TABLE `aplica_circuito` (
  `aplica_circuito_id` int(11) NOT NULL,
  `tipo_objeto` varchar(15) NOT NULL,
  `nombre_objeto` varchar(45) NOT NULL,
  `tipo_detalle` varchar(15) NOT NULL,
  `nombre_detalle` varchar(45) NOT NULL,
  `circuito_id` int(11) NOT NULL,
  `circuito` varchar(255) default NULL,
  PRIMARY KEY  (`aplica_circuito_id`),
  UNIQUE KEY `unique_tipo_nombre_objeto_tipo_nombre_detalle` (`tipo_objeto`,`nombre_objeto`,`tipo_detalle`,`nombre_detalle`),
  KEY `fk_aplica_circuito_circuitos_estados` (`circuito_id`),
  CONSTRAINT `fk_aplica_circuito_circuitos_estados` FOREIGN KEY (`circuito_id`) REFERENCES `circuitos_estados` (`circuito_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
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
  CONSTRAINT `FK_atributos_entidad_1` FOREIGN KEY (`atributo_id`) REFERENCES `atributos_rol` (`atributo_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_atributos_entidad_2` FOREIGN KEY (`entidad_id`) REFERENCES `entidad_externa` (`entidad_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
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
  `atributo_id` int(15) unsigned NOT NULL auto_increment COMMENT 'IdentificaciÃÂ³n ÃÂºnica de atributo de entidad',
  `nombre` varchar(90) NOT NULL COMMENT 'Nombre ÃÂºnico de atributo para entidad',
  `descripcion` varchar(255) default NULL COMMENT 'DescripciÃÂ³n del atributo',
  `observaciones` varchar(255) default NULL COMMENT 'Observaciones. Comentarios sobre la definiciÃÂ³n del atributo',
  `rol` char(4) default NULL,
  `desde` date NOT NULL COMMENT 'fecha de vigencia del atributo',
  `hasta` date default NULL COMMENT 'Indica hasta que fecha el atributo estÃÂ¡ vigente',
  `anulado` char(1) default 'F' COMMENT 'Indica si el registro estÃÂ¡ anulado: baja lÃÂ³gica',
  `clave` char(1) NOT NULL default 'F',
  `obligatorio` char(1) NOT NULL default 'F' COMMENT 'Indica si el atributo es obligatorio',
  `tipo_dato` enum('entero','real','texto','fecha','logico') NOT NULL default 'texto' COMMENT 'Indica el tipo de dato del atributo',
  `lov` varchar(255) default NULL COMMENT 'Lista de valores asociada al atributo. Nulo sin lista',
  `validador` varchar(255) default NULL COMMENT 'Rutina de validaciÃÂ³n asociada al atributo. Nulo implica sin validaciÃÂ³n',
  `clase_atributo_rol_id` int(15) unsigned default NULL COMMENT 'IdentificaciÃÂ³n ÃÂºnica para la clase de atributo',
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
-- Definition of table `attachment`
--

DROP TABLE IF EXISTS `attachment`;
CREATE TABLE `attachment` (
  `documento_id` int(10) NOT NULL,
  `track_id` int(10) unsigned NOT NULL,
  `user_id` int(10) unsigned NOT NULL,
  `fecha` datetime NOT NULL,
  PRIMARY KEY  (`documento_id`,`track_id`),
  KEY `fk_attachment_documentos` (`documento_id`),
  KEY `fk_attachment_tracks` (`track_id`),
  KEY `FK_attachment_users` (`user_id`),
  CONSTRAINT `FK_attachment_users` FOREIGN KEY (`user_id`) REFERENCES `website_users` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=FIXED;

--
-- Dumping data for table `attachment`
--

/*!40000 ALTER TABLE `attachment` DISABLE KEYS */;
INSERT INTO `attachment` (`documento_id`,`track_id`,`user_id`,`fecha`) VALUES 
 (1,1,11,'2009-07-13 20:57:47'),
 (2,1,11,'2009-07-13 21:33:30'),
 (3,1,11,'2009-07-13 23:43:27'),
 (4,4,11,'2009-07-13 23:48:06'),
 (5,7,11,'2009-07-13 23:51:35'),
 (6,1,11,'2009-07-14 13:25:41'),
 (7,1,11,'2009-07-14 13:33:59'),
 (8,1,11,'2009-07-14 13:42:46'),
 (9,1,11,'2009-07-14 13:47:22'),
 (10,1,11,'2009-07-14 14:09:46'),
 (11,1,11,'2009-07-14 14:14:02'),
 (12,1,11,'2009-07-14 14:17:32'),
 (13,3,11,'2009-07-14 14:20:48'),
 (14,6,11,'2009-07-14 14:33:29'),
 (15,6,11,'2009-07-14 20:12:33');
/*!40000 ALTER TABLE `attachment` ENABLE KEYS */;


--
-- Definition of table `audita_estados_circuitos`
--

DROP TABLE IF EXISTS `audita_estados_circuitos`;
CREATE TABLE `audita_estados_circuitos` (
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
  `a_estado` varchar(255) default NULL,
  `accion` int(11) default NULL,
  `circuito` varchar(255) default NULL,
  `de_estado` varchar(255) default NULL,
  PRIMARY KEY  (`audita_id`),
  KEY `fk_aec_circuitos` (`circuito_id`),
  KEY `fk_aec_acciones_apps` (`accion_id`),
  KEY `fk_aec_estados_de` (`estado_id_de`),
  KEY `fk_aec_estados_a` (`estado_id_a`),
  KEY `fk_aec_website_users` (`user_id`),
  CONSTRAINT `fk_aec_acciones_apps` FOREIGN KEY (`accion_id`) REFERENCES `acciones_apps` (`accion_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_aec_circuitos` FOREIGN KEY (`circuito_id`) REFERENCES `circuitos_estados` (`circuito_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_aec_estados_a` FOREIGN KEY (`estado_id_a`) REFERENCES `estados` (`estado_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_aec_estados_de` FOREIGN KEY (`estado_id_de`) REFERENCES `estados` (`estado_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_aec_website_users` FOREIGN KEY (`user_id`) REFERENCES `website_users` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
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
  `circuito_id` int(11) NOT NULL auto_increment,
  `nombre` varchar(40) NOT NULL,
  `descripcion` varchar(255) default NULL,
  `observaciones` varchar(255) default NULL,
  `circuito` varchar(255) default NULL,
  PRIMARY KEY  (`circuito_id`),
  UNIQUE KEY `unique_nombre` (`nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `circuitos_estados`
--

/*!40000 ALTER TABLE `circuitos_estados` DISABLE KEYS */;
INSERT INTO `circuitos_estados` (`circuito_id`,`nombre`,`descripcion`,`observaciones`,`circuito`) VALUES 
 (1,'Tracks','Circuito Administrativo para los tracks','Por ahora es el único circuito',NULL),
 (2,'Prueba','Circuito de prueba','Probando',NULL),
 (10,'Prueba2',NULL,NULL,NULL),
 (11,'Prueba3',NULL,NULL,NULL),
 (12,'Prueba4',NULL,NULL,NULL),
 (13,'Prueba5',NULL,NULL,NULL),
 (14,'Prueba6',NULL,NULL,NULL),
 (15,'Prueba7',NULL,NULL,NULL),
 (16,'Prueba8',NULL,NULL,NULL);
/*!40000 ALTER TABLE `circuitos_estados` ENABLE KEYS */;


--
-- Definition of table `clase_atributo_rol`
--

DROP TABLE IF EXISTS `clase_atributo_rol`;
CREATE TABLE `clase_atributo_rol` (
  `clase_atributo_rol_id` int(15) unsigned NOT NULL auto_increment COMMENT 'IdentificaciÃÂ³n ÃÂºnica para la clase de atributo',
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
-- Dumping data for table `diccionario_aplicacion_detalle`
--

/*!40000 ALTER TABLE `diccionario_aplicacion_detalle` DISABLE KEYS */;
/*!40000 ALTER TABLE `diccionario_aplicacion_detalle` ENABLE KEYS */;


--
-- Definition of table `documentos`
--

DROP TABLE IF EXISTS `documentos`;
CREATE TABLE `documentos` (
  `id_documento` int(10) NOT NULL auto_increment,
  `titulo` varchar(100) NOT NULL,
  `descripcion` varchar(255) default NULL,
  `docPath` varchar(255) NOT NULL,
  `version` double NOT NULL,
  `tipo` varchar(255) default NULL,
  PRIMARY KEY  (`id_documento`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `documentos`
--

/*!40000 ALTER TABLE `documentos` DISABLE KEYS */;
INSERT INTO `documentos` (`id_documento`,`titulo`,`descripcion`,`docPath`,`version`,`tipo`) VALUES 
 (1,'','','/files/carga.txt',1,NULL),
 (2,'','','/files/Desarrollo_web.pdf',1,NULL),
 (3,'','','/files/consulta.sql',1,NULL),
 (4,'','','/files/datos.txt',1,NULL),
 (5,'','','/files/menu1.sql',1,NULL),
 (6,'','','/files/mat.sql',1,NULL),
 (7,'','','/files/PIN.txt',1,NULL),
 (8,'','','/files/regs.txt',1,NULL),
 (9,'','','/files/truncate inventario.sql',1,NULL),
 (10,'','','/files/TCPClient.c',1,NULL),
 (11,'','','/files/cantos.txt',1,NULL),
 (12,'','','/files/mat.sql',1,NULL),
 (13,'','','/files/carga.txt',1,NULL),
 (14,'','','/files/ant.txt',1,NULL),
 (15,'Barra','','/files/datos.txt',1,NULL);
/*!40000 ALTER TABLE `documentos` ENABLE KEYS */;


--
-- Definition of table `entidad_externa`
--

DROP TABLE IF EXISTS `entidad_externa`;
CREATE TABLE `entidad_externa` (
  `entidad_id` int(15) unsigned NOT NULL auto_increment,
  `codigo` varchar(15) default NULL COMMENT 'CÃÂ³digo externo que identifica a la entidad',
  `nombre` varchar(90) NOT NULL,
  `descripcion` varchar(255) default NULL COMMENT 'DescripciÃÂ³n de la entidad',
  `observaciones` varchar(255) default NULL COMMENT 'Observaciones. Comentarios sobre la entidad',
  `activo` char(1) NOT NULL default 'F' COMMENT 'Indica si la entidad estÃÂ¡ activa. SÃÂ³lo entidades activas son permitidas usar. Activar garantiza las reglas de nogocio asociadas a los atributos y roles',
  `anulado` char(1) NOT NULL default 'F' COMMENT 'Indica si la entidad estÃÂ¡ nulada. Baja lÃÂ³gica',
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
  `estado_id` int(11) NOT NULL auto_increment,
  `nombre` varchar(30) NOT NULL,
  `descripcion` varchar(255) default NULL,
  `observaciones` varchar(255) default NULL,
  `circuito_id` int(11) NOT NULL,
  `circuito` varchar(255) default NULL,
  `estado` varchar(255) default NULL,
  PRIMARY KEY  (`estado_id`),
  UNIQUE KEY `unique_nombre` (`nombre`),
  KEY `fk_estados_circuitos_estados` (`circuito_id`),
  CONSTRAINT `fk_estados_circuitos_estados` FOREIGN KEY (`circuito_id`) REFERENCES `circuitos_estados` (`circuito_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `estados`
--

/*!40000 ALTER TABLE `estados` DISABLE KEYS */;
INSERT INTO `estados` (`estado_id`,`nombre`,`descripcion`,`observaciones`,`circuito_id`,`circuito`,`estado`) VALUES 
 (1,'Generado','Track recién creado',NULL,1,NULL,NULL),
 (2,'Listo','Track ya listo para ser iniciado',NULL,1,NULL,NULL),
 (3,'Iniciado','Track iniciado',NULL,1,NULL,NULL),
 (4,'Finalizado','Track terminado, pendiente de revisión',NULL,1,NULL,NULL),
 (5,'Terminado','Track terminado correctamente, por lo cual se acepta como terminado',NULL,1,NULL,NULL),
 (6,'Rechazado','Track rechazado por estar incorrecto.',NULL,1,NULL,NULL),
 (7,'Probado','Estado de prueba',NULL,2,NULL,NULL),
 (8,'Prueba3','Estado de prueba 3','para Circuito Prueba3',11,NULL,NULL),
 (9,'Prueba4','Estado de prueba 4','para Circuito Prueba4',12,NULL,NULL),
 (10,'Prueba6','Estado de prueba 6','para Circuito Prueba6',14,NULL,NULL),
 (11,'Prueba7','Estado de prueba7','para Circuito prueba7',15,NULL,NULL),
 (12,'Prueba8','Estado de prueba 8','prueba8',16,NULL,NULL);
/*!40000 ALTER TABLE `estados` ENABLE KEYS */;


--
-- Definition of table `importancia`
--

DROP TABLE IF EXISTS `importancia`;
CREATE TABLE `importancia` (
  `importancia_id` int(11) NOT NULL auto_increment,
  `descripcion` varchar(255) default NULL,
  `nombre` varchar(255) NOT NULL,
  `peso` int(11) NOT NULL,
  PRIMARY KEY  (`importancia_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `importancia`
--

/*!40000 ALTER TABLE `importancia` DISABLE KEYS */;
INSERT INTO `importancia` (`importancia_id`,`descripcion`,`nombre`,`peso`) VALUES 
 (1,NULL,'Mucha',5);
/*!40000 ALTER TABLE `importancia` ENABLE KEYS */;


--
-- Definition of table `lov_atributo`
--

DROP TABLE IF EXISTS `lov_atributo`;
CREATE TABLE `lov_atributo` (
  `clase_lov_atributo_id` int(15) unsigned NOT NULL,
  `valor` varchar(255) NOT NULL,
  `descripcion` varchar(255) default NULL,
  PRIMARY KEY  (`clase_lov_atributo_id`),
  UNIQUE KEY `unique_lov_id_valor` (`clase_lov_atributo_id`,`valor`),
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
  `menu_id` int(10) unsigned NOT NULL,
  `nombre` varchar(90) NOT NULL default '',
  `descripcion` varchar(255) default NULL,
  `url` varchar(255) default NULL,
  `grupo` varchar(90) default NULL,
  PRIMARY KEY  (`menu_id`),
  KEY `grupo_IDX` (`grupo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `menu`
--

/*!40000 ALTER TABLE `menu` DISABLE KEYS */;
INSERT INTO `menu` (`menu_id`,`nombre`,`descripcion`,`url`,`grupo`) VALUES 
 (1,'ABM Roles','Pantalla de ABM de roles','AbmcRoles.zul','BaseRolesPage.zul'),
 (2,'Asignacion de roles','Pantalla de asignación de roles a usuarios','AbmcUsuariosRoles.zul','BaseRolesPage.zul'),
 (3,'Página Principal','Página principal','HomePage.zul',''),
 (4,'Roles','Menú de administración de roles','BaseRolesPage.zul','HomePage.zul'),
 (5,'Tracks','Alta de tracks','AbmcTracks.zul','HomePage.zul'),
 (6,'Circuitos','Administración de circuitos','AbmcCircuitos.zul','HomePage.zul');
/*!40000 ALTER TABLE `menu` ENABLE KEYS */;


--
-- Definition of table `posts`
--

DROP TABLE IF EXISTS `posts`;
CREATE TABLE `posts` (
  `post_id` int(11) NOT NULL auto_increment,
  `contenido` text NOT NULL,
  `user_id` int(10) unsigned NOT NULL,
  `track_id` int(10) unsigned NOT NULL,
  `fecha_creacion` datetime NOT NULL,
  `OBJ_VERSION` datetime default NULL,
  PRIMARY KEY  (`post_id`),
  KEY `fk_website_users` (`user_id`),
  KEY `fk_posts_tracks` (`track_id`),
  CONSTRAINT `fk_posts_tracks` FOREIGN KEY (`track_id`) REFERENCES `tracks` (`track_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_posts_website_users` FOREIGN KEY (`user_id`) REFERENCES `website_users` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `posts`
--

/*!40000 ALTER TABLE `posts` DISABLE KEYS */;
INSERT INTO `posts` (`post_id`,`contenido`,`user_id`,`track_id`,`fecha_creacion`,`OBJ_VERSION`) VALUES 
 (1,'<p>Hola</p>',11,1,'2009-06-29 13:04:57',NULL),
 (2,'<p><span style=\"color: rgb(255, 0, 0);\"><strong>Pepe </strong></span></p>',11,1,'2009-06-29 13:26:59',NULL),
 (3,'<p><u>Holassss</u></p>',11,1,'2009-06-29 13:37:53',NULL),
 (4,'<p>Holaaaa</p>',11,1,'2009-06-29 15:07:11',NULL),
 (5,'<p><strong>1111111</strong></p>',11,1,'2009-06-29 15:46:33',NULL),
 (6,'<p>2222</p>',11,1,'2009-06-29 15:53:00',NULL),
 (7,'<p>Perrooo</p>',11,2,'2009-06-29 20:14:49',NULL),
 (8,'<p>Perrroooo2</p>',11,2,'2009-06-29 20:15:03',NULL),
 (9,'<p><span style=\"color: rgb(255, 255, 153);\"><em><strong>Peeeeeeeepe 1</strong></em></span></p>',11,1,'2009-07-01 18:18:16',NULL),
 (10,'<p><span style=\"color: rgb(0, 255, 0);\"><strong>Pepitoooo 2</strong></span></p>',11,1,'2009-07-01 18:18:42',NULL),
 (11,'<p>Buenasss</p>',11,3,'2009-07-02 16:20:29',NULL),
 (12,'<p><strong><span style=\"color: rgb(255, 102, 0);\">Y ahora???</span></strong></p>',11,3,'2009-07-03 14:13:29',NULL);
/*!40000 ALTER TABLE `posts` ENABLE KEYS */;


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
-- Definition of table `prioridades`
--

DROP TABLE IF EXISTS `prioridades`;
CREATE TABLE `prioridades` (
  `prioridad_id` int(11) NOT NULL auto_increment,
  `descripcion` varchar(255) default NULL,
  `nombre` varchar(255) NOT NULL,
  `peso` int(11) NOT NULL,
  PRIMARY KEY  (`prioridad_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `prioridades`
--

/*!40000 ALTER TABLE `prioridades` DISABLE KEYS */;
INSERT INTO `prioridades` (`prioridad_id`,`descripcion`,`nombre`,`peso`) VALUES 
 (1,'','alta',5);
/*!40000 ALTER TABLE `prioridades` ENABLE KEYS */;


--
-- Definition of table `proveedores_sso`
--

DROP TABLE IF EXISTS `proveedores_sso`;
CREATE TABLE `proveedores_sso` (
  `proveedor_sso_id` int(11) NOT NULL auto_increment,
  `nombre` varchar(40) NOT NULL,
  `descripcion` varchar(255) default NULL,
  `observaciones` varchar(255) default NULL,
  `url_discovery` text NOT NULL,
  `url_icono` text,
  PRIMARY KEY  (`proveedor_sso_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `proveedores_sso`
--

/*!40000 ALTER TABLE `proveedores_sso` DISABLE KEYS */;
INSERT INTO `proveedores_sso` (`proveedor_sso_id`,`nombre`,`descripcion`,`observaciones`,`url_discovery`,`url_icono`) VALUES 
 (1,'Google','OpenID de Google','','https://www.google.com/accounts/o8/id','/img/gmail-openid.jpg'),
 (2,'Yahoo','OpenID de Yahoo','','https://me.yahoo.com/','http://l.yimg.com/a/i/ydn/openid-signin-yellow.png');
/*!40000 ALTER TABLE `proveedores_sso` ENABLE KEYS */;


--
-- Definition of table `rol_entidad`
--

DROP TABLE IF EXISTS `rol_entidad`;
CREATE TABLE `rol_entidad` (
  `rol` char(4) NOT NULL,
  `nombre` varchar(90) NOT NULL COMMENT 'nombre del rol de entidad',
  `descripcion` varchar(255) default NULL COMMENT 'DescripciÃÂ³n del rol de entidad',
  `observaciones` varchar(255) default NULL COMMENT 'Observaciones. Comentarios sobre el rol de la entidad',
  `desde` date NOT NULL COMMENT 'Fecha de vigencia del rol de entidad',
  `hasta` date default NULL COMMENT 'Fecha hasta donde estÃÂ¡ vigente el rol de entidad',
  `anulado` char(1) NOT NULL default 'F' COMMENT 'Indica si el registro estÃÂ¡ anulado. Baja lÃÂ³gica',
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
  `OBJ_VERSION` datetime default NULL,
  PRIMARY KEY  (`rol_id`),
  UNIQUE KEY `IX_roles_1` (`nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `roles`
--

/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` (`rol_id`,`nombre`,`descripcion`,`observaciones`,`OBJ_VERSION`) VALUES 
 (1,'Administrador','Administrador del sistema','','2009-04-20 00:00:00'),
 (18,'Hola 666','Hola','Hola','2009-05-05 13:02:01'),
 (19,'Rol 1','Este es el rol nº 1','Este rol se usa en dos casos: \n- Cuando yo quiero.\n- Cuando se me antoja.','2009-04-30 13:28:07'),
 (20,'Pringao','El que hace todo','Sin que le paguen',NULL);
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;


--
-- Definition of table `roles_entidad`
--

DROP TABLE IF EXISTS `roles_entidad`;
CREATE TABLE `roles_entidad` (
  `entidad_id` int(15) unsigned NOT NULL,
  `rol_id` char(4) NOT NULL,
  `desde` date NOT NULL COMMENT 'Fecha desde cuando estÃ¡ activo en el rol',
  `hasta` date default NULL COMMENT 'Hasta que fecha estÃ¡ activo en el rol',
  `activo` char(1) NOT NULL default 'F' COMMENT 'Indica si el rol est''aactivo, sino no es considerado. Activar garantiza reglas para atributos',
  `anulado` char(1) NOT NULL default 'F' COMMENT 'Indica que el registro est''aanulado. Baja lÃ³gica',
  `rol` varchar(255) default NULL,
  PRIMARY KEY  (`entidad_id`,`rol_id`),
  KEY `entidad_id` (`entidad_id`),
  KEY `rol` (`rol_id`),
  CONSTRAINT `FK_roles_entidad_1` FOREIGN KEY (`entidad_id`) REFERENCES `entidad_externa` (`entidad_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_roles_entidad_2` FOREIGN KEY (`rol_id`) REFERENCES `rol_entidad` (`rol`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Roles asociados a la entidad.';

--
-- Dumping data for table `roles_entidad`
--

/*!40000 ALTER TABLE `roles_entidad` DISABLE KEYS */;
/*!40000 ALTER TABLE `roles_entidad` ENABLE KEYS */;


--
-- Definition of table `roles_per_website_users`
--

DROP TABLE IF EXISTS `roles_per_website_users`;
CREATE TABLE `roles_per_website_users` (
  `user_id` int(10) unsigned NOT NULL default '0',
  `rol_id` int(10) unsigned NOT NULL default '0',
  `desde` date NOT NULL,
  `hasta` date default NULL,
  `anulado` char(1) NOT NULL default 'F',
  `OBJ_VERSION` datetime default NULL,
  PRIMARY KEY  (`rol_id`,`user_id`),
  KEY `fk_website_users` (`user_id`),
  KEY `fk_roles` (`rol_id`),
  CONSTRAINT `fk_roles` FOREIGN KEY (`rol_id`) REFERENCES `roles` (`rol_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_website_users` FOREIGN KEY (`user_id`) REFERENCES `website_users` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `roles_per_website_users`
--

/*!40000 ALTER TABLE `roles_per_website_users` DISABLE KEYS */;
INSERT INTO `roles_per_website_users` (`user_id`,`rol_id`,`desde`,`hasta`,`anulado`,`OBJ_VERSION`) VALUES 
 (11,1,'2009-01-01',NULL,'F',NULL),
 (14,1,'2009-07-10','2010-07-10','\0',NULL),
 (11,18,'2009-05-10',NULL,'\0',NULL),
 (11,19,'2009-05-10',NULL,'\0',NULL);
/*!40000 ALTER TABLE `roles_per_website_users` ENABLE KEYS */;


--
-- Definition of table `sticky_notes`
--

DROP TABLE IF EXISTS `sticky_notes`;
CREATE TABLE `sticky_notes` (
  `sticky_note_id` int(11) NOT NULL auto_increment,
  `titulo` varchar(90) NOT NULL,
  `contenido` text,
  `track_id` int(10) unsigned default NULL,
  `leido` tinyint(1) default '0',
  PRIMARY KEY  (`sticky_note_id`),
  KEY `fk_sticky_notes_tracks` (`track_id`),
  CONSTRAINT `fk_sticky_notes_tracks` FOREIGN KEY (`track_id`) REFERENCES `tracks` (`track_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sticky_notes`
--

/*!40000 ALTER TABLE `sticky_notes` DISABLE KEYS */;
INSERT INTO `sticky_notes` (`sticky_note_id`,`titulo`,`contenido`,`track_id`,`leido`) VALUES 
 (1,'Sticky','Hacer esto...',3,0),
 (2,'Pepe 1','Este es pepe',1,1),
 (3,'José','Este es José',1,0),
 (4,'Hacer algo','Para completar el track hay que hacer algo!!!',1,1);
/*!40000 ALTER TABLE `sticky_notes` ENABLE KEYS */;


--
-- Definition of table `tags`
--

DROP TABLE IF EXISTS `tags`;
CREATE TABLE `tags` (
  `tag_id` int(10) NOT NULL auto_increment,
  `tag` varchar(45) NOT NULL,
  `descripcion` varchar(255) default NULL,
  `observaciones` varchar(255) default NULL,
  `tag_id_grupo` int(10) default NULL,
  PRIMARY KEY  (`tag_id`),
  UNIQUE KEY `unique_tag` (`tag`),
  KEY `fk_tags_tags` (`tag_id_grupo`),
  CONSTRAINT `fk_tags_tags` FOREIGN KEY (`tag_id_grupo`) REFERENCES `tags` (`tag_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tags`
--

/*!40000 ALTER TABLE `tags` DISABLE KEYS */;
INSERT INTO `tags` (`tag_id`,`tag`,`descripcion`,`observaciones`,`tag_id_grupo`) VALUES 
 (1,'Tag 1','Este es el tag 1',NULL,NULL),
 (2,'Tag 2','Este es el tag 2',NULL,NULL),
 (3,'Tag 1.1','Hijo 1 del tag 1',NULL,1),
 (4,'Tag 1.2','Hijo 2 del tag 1',NULL,1),
 (5,'Tag 1.1.1','Hijo 1 del tag 3',NULL,3),
 (6,'Tag 2.1','Este es un nuevo tag',NULL,2),
 (7,'Tag x','Este es otro tag',NULL,2),
 (8,'Tag xx (Tag 1.1.1)','Otro tag',NULL,5),
 (9,'Tag xx (Tag 2)','Otro tag',NULL,2),
 (10,'HOla (Tag 1.2)','Gola',NULL,4),
 (11,'Chau (HOla (Tag 1.2))','Chau',NULL,10),
 (12,'Pero (Tag x)','Pero',NULL,7),
 (13,'Tag 5','Este es otro tag de prueba',NULL,NULL),
 (14,'Tag 6','Tag 6',NULL,NULL),
 (15,'Tag 7','Este es ooootro tag',NULL,NULL),
 (16,'Otro tag','Este es otro',NULL,NULL),
 (17,'Juan','Manuel',NULL,NULL),
 (18,'Otro','Este es otro',NULL,NULL),
 (19,'Otrooo','Y?',NULL,NULL),
 (20,'Nacho','Nacho',NULL,NULL);
/*!40000 ALTER TABLE `tags` ENABLE KEYS */;


--
-- Definition of table `tags_per_tracks`
--

DROP TABLE IF EXISTS `tags_per_tracks`;
CREATE TABLE `tags_per_tracks` (
  `tag_id` int(10) NOT NULL,
  `track_id` int(10) unsigned NOT NULL,
  `tag_tag_id` int(11) default NULL,
  `track_track_id` int(11) default NULL,
  PRIMARY KEY  (`tag_id`,`track_id`),
  KEY `fk_tags` (`tag_id`),
  KEY `fk_tracks` (`track_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tags_per_tracks`
--

/*!40000 ALTER TABLE `tags_per_tracks` DISABLE KEYS */;
INSERT INTO `tags_per_tracks` (`tag_id`,`track_id`,`tag_tag_id`,`track_track_id`) VALUES 
 (10,8,NULL,NULL),
 (1,1,NULL,NULL),
 (1,3,NULL,NULL),
 (1,4,NULL,NULL),
 (10,1,NULL,NULL);
/*!40000 ALTER TABLE `tags_per_tracks` ENABLE KEYS */;


--
-- Definition of table `tracks`
--

DROP TABLE IF EXISTS `tracks`;
CREATE TABLE `tracks` (
  `track_id` int(10) unsigned NOT NULL auto_increment,
  `descripcion` varchar(90) NOT NULL,
  `observaciones` varchar(255) default NULL,
  `fecha_creacion` datetime NOT NULL,
  `fecha_estimada_realizacion` date default NULL,
  `deadline` date default NULL,
  `fecha_realizacion` datetime default NULL,
  `estado_id` int(11) NOT NULL,
  `user_id_owner` int(10) unsigned NOT NULL,
  `titulo` varchar(255) default NULL,
  `importancia_id` int(11) default NULL,
  `prioridad_id` int(11) default NULL,
  `complejidad` float NOT NULL default '1',
  `orden` int(10) unsigned NOT NULL,
  `OBJ_VERSION` datetime default NULL,
  PRIMARY KEY  (`track_id`),
  KEY `fk_estados` (`estado_id`),
  KEY `fk_website_user` (`user_id_owner`),
  KEY `FK_tracks_criticidad` (`importancia_id`),
  KEY `FK_tracks_prioridad` (`prioridad_id`),
  CONSTRAINT `FK_tracks_criticidad` FOREIGN KEY (`importancia_id`) REFERENCES `importancia` (`importancia_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_tracks_estados` FOREIGN KEY (`estado_id`) REFERENCES `estados` (`estado_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_tracks_prioridad` FOREIGN KEY (`prioridad_id`) REFERENCES `prioridades` (`prioridad_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_tracks_website_user` FOREIGN KEY (`user_id_owner`) REFERENCES `website_users` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1 COMMENT='Tabla que almacena cada track en la aplicaciÃ³n';

--
-- Dumping data for table `tracks`
--

/*!40000 ALTER TABLE `tracks` DISABLE KEYS */;
INSERT INTO `tracks` (`track_id`,`descripcion`,`observaciones`,`fecha_creacion`,`fecha_estimada_realizacion`,`deadline`,`fecha_realizacion`,`estado_id`,`user_id_owner`,`titulo`,`importancia_id`,`prioridad_id`,`complejidad`,`orden`,`OBJ_VERSION`) VALUES 
 (1,'Este es el primer track','','2009-06-02 19:11:29',NULL,NULL,NULL,1,11,'Primer track',1,1,1,1,'0000-00-00 00:00:00'),
 (2,'Hola','','2009-06-02 20:20:50',NULL,NULL,NULL,1,11,'Hola',1,1,1,2,'0000-00-00 00:00:00'),
 (3,'Este es otro track','','2009-06-03 18:11:49',NULL,'2009-07-14',NULL,1,11,'Track 1',1,1,1,3,'0000-00-00 00:00:00'),
 (4,'Este es pepe','','2009-06-03 20:09:19',NULL,'2009-07-14',NULL,1,11,'Pepe',1,1,1,4,'0000-00-00 00:00:00'),
 (5,'Este es otro','','2009-06-03 20:14:01',NULL,'2009-07-14',NULL,1,13,'Otro',1,1,1,5,'0000-00-00 00:00:00'),
 (6,'Juan es un track','','2009-06-03 20:16:21',NULL,'2009-07-14',NULL,1,11,'Juan',1,1,1,6,'0000-00-00 00:00:00'),
 (7,'Ahora si va a andar','','2009-06-03 20:19:31',NULL,NULL,NULL,1,11,'Ahora si',1,1,1,7,'0000-00-00 00:00:00'),
 (8,'Hola','','2009-06-03 20:25:37',NULL,'2009-07-14',NULL,1,11,'Esta',1,1,1,8,'0000-00-00 00:00:00'),
 (9,'Este es un track','','2009-06-19 12:59:17',NULL,'2009-07-14',NULL,1,11,'Track nº 1',1,1,1,9,'0000-00-00 00:00:00'),
 (10,'Este es el track nº 2','','2009-06-27 17:14:20',NULL,'2009-07-14',NULL,1,11,'Track nº 2',1,1,1,10,'0000-00-00 00:00:00');
/*!40000 ALTER TABLE `tracks` ENABLE KEYS */;


--
-- Definition of table `transicion_estados`
--

DROP TABLE IF EXISTS `transicion_estados`;
CREATE TABLE `transicion_estados` (
  `transicion_id` int(11) NOT NULL,
  `estado_id_origen` int(11) NOT NULL,
  `accion_id` int(10) unsigned NOT NULL default '0',
  `estado_id_destino` int(11) NOT NULL,
  `prompt_accion` varchar(30) default NULL,
  `validador` varchar(255) default NULL,
  `accion` int(11) default NULL,
  `estado_destino` varchar(255) default NULL,
  `estado_origen` varchar(255) default NULL,
  PRIMARY KEY  (`transicion_id`),
  UNIQUE KEY `unique_estado_origen_accion` (`estado_id_origen`,`accion_id`),
  KEY `fk_transicion_estados_acciones_apps` (`accion_id`),
  KEY `fk_transicion_estados_estados_origen` (`estado_id_origen`),
  KEY `fk_transicion_estados_estados_destino` (`estado_id_destino`),
  CONSTRAINT `fk_transicion_estados_acciones_apps` FOREIGN KEY (`accion_id`) REFERENCES `acciones_apps` (`accion_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_transicion_estados_estados_destino` FOREIGN KEY (`estado_id_destino`) REFERENCES `estados` (`estado_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_transicion_estados_estados_origen` FOREIGN KEY (`estado_id_origen`) REFERENCES `estados` (`estado_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=FIXED;

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
  CONSTRAINT `FK_usuario_preferencias_2` FOREIGN KEY (`user_id`) REFERENCES `website_users` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `preferencia_FK` FOREIGN KEY (`preferencia_id`) REFERENCES `preferencias` (`preferencia_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=FIXED;

--
-- Dumping data for table `usuario_preferencias`
--

/*!40000 ALTER TABLE `usuario_preferencias` DISABLE KEYS */;
/*!40000 ALTER TABLE `usuario_preferencias` ENABLE KEYS */;


--
-- Definition of table `website_users`
--

DROP TABLE IF EXISTS `website_users`;
CREATE TABLE `website_users` (
  `user_id` int(10) unsigned NOT NULL auto_increment,
  `login_name` varchar(60) NOT NULL default '',
  `login_password` varchar(60) default NULL,
  `nivel_visibilidad` varchar(255) default 'Todo',
  `nombre` varchar(90) NOT NULL,
  `apellido` varchar(90) NOT NULL,
  `email` varchar(90) default NULL,
  `nro_legajo` int(11) default NULL,
  `nro_comprador` int(10) unsigned default NULL,
  `OBJ_VERSION` datetime default NULL,
  `fecha_nacimiento` datetime NOT NULL,
  PRIMARY KEY  (`user_id`),
  UNIQUE KEY `unique_nombre_completo_fecha_nacimiento` USING BTREE (`nombre`,`fecha_nacimiento`,`apellido`),
  UNIQUE KEY `unique_login_name` (`login_name`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `website_users`
--

/*!40000 ALTER TABLE `website_users` DISABLE KEYS */;
INSERT INTO `website_users` (`user_id`,`login_name`,`login_password`,`nivel_visibilidad`,`nombre`,`apellido`,`email`,`nro_legajo`,`nro_comprador`,`OBJ_VERSION`,`fecha_nacimiento`) VALUES 
 (11,'Juanma','BqfWmg9HKe4bWol4/ATXU4jBNEg=',NULL,'Juan Manuel','Cortez','juanmanuelcortez@gmail.com',NULL,NULL,NULL,'1985-02-06 00:00:00'),
 (13,'juanperez',NULL,NULL,'Juan Manuel','Perez','jmcthemaster@hotmail.com',NULL,NULL,NULL,'1985-02-06 00:00:00'),
 (14,'nacho','KfCVPY4DC/T32ix/QdaKZXgYhkg=',NULL,'Luis Ignacio','Aita',NULL,NULL,NULL,NULL,'1979-09-02 00:00:00');
/*!40000 ALTER TABLE `website_users` ENABLE KEYS */;


--
-- Definition of table `website_users_per_proveedores_openid`
--

DROP TABLE IF EXISTS `website_users_per_proveedores_openid`;
CREATE TABLE `website_users_per_proveedores_openid` (
  `user_id` int(10) unsigned NOT NULL,
  `proveedor_sso_id` int(11) NOT NULL,
  `fecha_asociacion` datetime default NULL,
  `claimed_id` varchar(255) NOT NULL,
  PRIMARY KEY  (`user_id`,`proveedor_sso_id`),
  KEY `fk_website_users` (`user_id`),
  KEY `fk_proveedores_sso` (`proveedor_sso_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `website_users_per_proveedores_openid`
--

/*!40000 ALTER TABLE `website_users_per_proveedores_openid` DISABLE KEYS */;
INSERT INTO `website_users_per_proveedores_openid` (`user_id`,`proveedor_sso_id`,`fecha_asociacion`,`claimed_id`) VALUES 
 (11,1,'2009-06-09 12:56:12','https://www.google.com/accounts/o8/id?id=AItOawkyHPyFcgE4v41K11DqyfLH2KcsIVlMPcg'),
 (13,1,'2009-05-21 13:25:14','https://www.google.com/accounts/o8/id?id=AItOawl0YIZdwyrrTj0WoJB9LtNlJngmI01dv0I');
/*!40000 ALTER TABLE `website_users_per_proveedores_openid` ENABLE KEYS */;


--
-- Definition of table `workers_per_tracks`
--

DROP TABLE IF EXISTS `workers_per_tracks`;
CREATE TABLE `workers_per_tracks` (
  `track_id` int(10) unsigned NOT NULL,
  `user_id` int(10) unsigned NOT NULL,
  PRIMARY KEY  (`track_id`,`user_id`),
  KEY `fk_tracks` (`track_id`),
  KEY `fk_website_users` (`user_id`),
  CONSTRAINT `FK_workers_per_tracks_tracks` FOREIGN KEY (`track_id`) REFERENCES `tracks` (`track_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_workers_per_tracks_user` FOREIGN KEY (`user_id`) REFERENCES `website_users` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=FIXED;

--
-- Dumping data for table `workers_per_tracks`
--

/*!40000 ALTER TABLE `workers_per_tracks` DISABLE KEYS */;
INSERT INTO `workers_per_tracks` (`track_id`,`user_id`) VALUES 
 (1,11),
 (1,13),
 (1,14),
 (2,14),
 (3,13),
 (3,14),
 (7,13);
/*!40000 ALTER TABLE `workers_per_tracks` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
