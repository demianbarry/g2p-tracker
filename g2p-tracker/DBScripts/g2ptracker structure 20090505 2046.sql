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
  KEY `FKBBA074DE8101218C` (`rol_id`),
  KEY `FKBBA074DE150B56FC` (`menu_id`),
  KEY `FKBBA074DEDA06AAD9` (`user_id`),
  CONSTRAINT `FKBBA074DE150B56FC` FOREIGN KEY (`menu_id`) REFERENCES `menu` (`menu_id`),
  CONSTRAINT `FKBBA074DE8101218C` FOREIGN KEY (`rol_id`) REFERENCES `roles` (`rol_id`),
  CONSTRAINT `FKBBA074DEDA06AAD9` FOREIGN KEY (`user_id`) REFERENCES `website_user` (`user_id`),
  CONSTRAINT `FK_acceso_menu_3` FOREIGN KEY (`menu_id`) REFERENCES `menu` (`menu_id`),
  CONSTRAINT `FK_acceso_menu_4` FOREIGN KEY (`user_id`) REFERENCES `website_user` (`user_id`),
  CONSTRAINT `FK_acceso_menu_menu_id` FOREIGN KEY (`menu_id`) REFERENCES `menu` (`menu_id`),
  CONSTRAINT `FK_acceso_menu_rol_id` FOREIGN KEY (`rol_id`) REFERENCES `roles` (`rol_id`),
  CONSTRAINT `FK_acceso_menu_user_id` FOREIGN KEY (`user_id`) REFERENCES `website_user` (`user_id`),
  CONSTRAINT `rol_FK` FOREIGN KEY (`rol_id`) REFERENCES `roles` (`rol_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `acceso_menu`
--

/*!40000 ALTER TABLE `acceso_menu` DISABLE KEYS */;
INSERT INTO `acceso_menu` (`menu_id`,`rol_id`,`user_id`,`acceso_menu_id`) VALUES 
 (1,NULL,1,1),
 (2,NULL,1,2),
 (3,NULL,1,3),
 (4,NULL,1,4);
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
  KEY `FKB4D18D9C228E4B88` (`circuito`),
  CONSTRAINT `FKB4D18D9C228E4B88` FOREIGN KEY (`circuito`) REFERENCES `circuitos_estados` (`circuito`),
  CONSTRAINT `FK_acciones_apps_1` FOREIGN KEY (`circuito`) REFERENCES `circuitos_estados` (`circuito`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_acciones_apps_circuito` FOREIGN KEY (`circuito`) REFERENCES `circuitos_estados` (`circuito`)
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
  KEY `FK74AE36FB228E4B88` (`circuito`),
  CONSTRAINT `FK74AE36FB228E4B88` FOREIGN KEY (`circuito`) REFERENCES `circuitos_estados` (`circuito`),
  CONSTRAINT `FK_aplica_circuito_2` FOREIGN KEY (`circuito`) REFERENCES `circuitos_estados` (`circuito`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_aplica_circuito_circuito` FOREIGN KEY (`circuito`) REFERENCES `circuitos_estados` (`circuito`)
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
  KEY `FK3E09BA2392F06DF8` (`atributo_id`),
  KEY `FK3E09BA234F91D51E` (`configuracion_id`),
  CONSTRAINT `atributos_configuracion_ibfk_1` FOREIGN KEY (`configuracion_id`) REFERENCES `configuracion` (`configuracion_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `atributos_configuracion_ibfk_2` FOREIGN KEY (`atributo_id`) REFERENCES `atributos_rol` (`atributo_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK3E09BA234F91D51E` FOREIGN KEY (`configuracion_id`) REFERENCES `configuracion` (`configuracion_id`),
  CONSTRAINT `FK3E09BA2392F06DF8` FOREIGN KEY (`atributo_id`) REFERENCES `atributos_rol` (`atributo_id`),
  CONSTRAINT `FK_atributos_configuracion_atributo_id` FOREIGN KEY (`atributo_id`) REFERENCES `atributos_rol` (`atributo_id`),
  CONSTRAINT `FK_atributos_configuracion_configuracion_id` FOREIGN KEY (`configuracion_id`) REFERENCES `configuracion` (`configuracion_id`)
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
  KEY `FK6C60A66592F06DF8` (`atributo_id`),
  KEY `FK6C60A665238589EB` (`entidad_id`),
  CONSTRAINT `FK6C60A665238589EB` FOREIGN KEY (`entidad_id`) REFERENCES `entidad_externa` (`entidad_id`),
  CONSTRAINT `FK6C60A66592F06DF8` FOREIGN KEY (`atributo_id`) REFERENCES `atributos_rol` (`atributo_id`),
  CONSTRAINT `FK_atributos_entidad_1` FOREIGN KEY (`atributo_id`) REFERENCES `atributos_rol` (`atributo_id`),
  CONSTRAINT `FK_atributos_entidad_2` FOREIGN KEY (`entidad_id`) REFERENCES `entidad_externa` (`entidad_id`),
  CONSTRAINT `FK_atributos_entidad_atributo_id` FOREIGN KEY (`atributo_id`) REFERENCES `atributos_rol` (`atributo_id`),
  CONSTRAINT `FK_atributos_entidad_entidad_id` FOREIGN KEY (`entidad_id`) REFERENCES `entidad_externa` (`entidad_id`)
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
  KEY `FK5475A44BFBCB950B` (`rol`),
  KEY `FK5475A44B697AB8EC` (`clase_atributo_rol_id`),
  CONSTRAINT `FK5475A44B697AB8EC` FOREIGN KEY (`clase_atributo_rol_id`) REFERENCES `clase_atributo_rol` (`clase_atributo_rol_id`),
  CONSTRAINT `FK5475A44BFBCB950B` FOREIGN KEY (`rol`) REFERENCES `rol_entidad` (`rol`),
  CONSTRAINT `FK_atributos_rol_3` FOREIGN KEY (`tipo_objeto`, `nombre_objeto`) REFERENCES `diccionario_aplicacion` (`tipo_objeto`, `nombre_objeto`),
  CONSTRAINT `FK_atributos_rol_clase_atributo_rol_id` FOREIGN KEY (`clase_atributo_rol_id`) REFERENCES `clase_atributo_rol` (`clase_atributo_rol_id`),
  CONSTRAINT `FK_atributos_rol_rol` FOREIGN KEY (`rol`) REFERENCES `rol_entidad` (`rol`),
  CONSTRAINT `FK_atributos_rol_tipo_objeto` FOREIGN KEY (`tipo_objeto`, `nombre_objeto`) REFERENCES `diccionario_aplicacion` (`tipo_objeto`, `nombre_objeto`),
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
  KEY `FKA060E582228E4B88` (`circuito`),
  KEY `FKA060E582F1B3C3D5` (`a_estado`),
  KEY `FKA060E5827BF3D3B5` (`de_estado`),
  KEY `FKA060E582DA06AAD9` (`user_id`),
  KEY `FKA060E58235D24370` (`accion`),
  CONSTRAINT `FKA060E582228E4B88` FOREIGN KEY (`circuito`) REFERENCES `circuitos_estados` (`circuito`),
  CONSTRAINT `FKA060E58235D24370` FOREIGN KEY (`accion`) REFERENCES `acciones_apps` (`accion`),
  CONSTRAINT `FKA060E5827BF3D3B5` FOREIGN KEY (`de_estado`) REFERENCES `estados` (`estado`),
  CONSTRAINT `FKA060E582DA06AAD9` FOREIGN KEY (`user_id`) REFERENCES `website_user` (`user_id`),
  CONSTRAINT `FKA060E582F1B3C3D5` FOREIGN KEY (`a_estado`) REFERENCES `estados` (`estado`),
  CONSTRAINT `FK_audita_estados_circuitos_1` FOREIGN KEY (`circuito`) REFERENCES `circuitos_estados` (`circuito`),
  CONSTRAINT `FK_audita_estados_circuitos_3` FOREIGN KEY (`de_estado`) REFERENCES `estados` (`estado`),
  CONSTRAINT `FK_audita_estados_circuitos_4` FOREIGN KEY (`a_estado`) REFERENCES `estados` (`estado`),
  CONSTRAINT `FK_audita_estados_circuitos_5` FOREIGN KEY (`user_id`) REFERENCES `website_user` (`user_id`),
  CONSTRAINT `FK_audita_estados_circuitos_6` FOREIGN KEY (`accion`) REFERENCES `acciones_apps` (`accion`),
  CONSTRAINT `FK_audita_estados_circuitos_accion` FOREIGN KEY (`accion`) REFERENCES `acciones_apps` (`accion`),
  CONSTRAINT `FK_audita_estados_circuitos_a_estado` FOREIGN KEY (`a_estado`) REFERENCES `estados` (`estado`),
  CONSTRAINT `FK_audita_estados_circuitos_circuito` FOREIGN KEY (`circuito`) REFERENCES `circuitos_estados` (`circuito`),
  CONSTRAINT `FK_audita_estados_circuitos_de_estado` FOREIGN KEY (`de_estado`) REFERENCES `estados` (`estado`),
  CONSTRAINT `FK_audita_estados_circuitos_user_id` FOREIGN KEY (`user_id`) REFERENCES `website_user` (`user_id`)
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
INSERT INTO `circuitos_estados` (`circuito`,`nombre`,`descripcion`,`observaciones`) VALUES 
 ('0001','Tracks','Workflow de administración de tracks',NULL);
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
  KEY `FK732BBAA75EC5F36D` (`esquema_configuracion_id`),
  CONSTRAINT `configuracion_ibfk_1` FOREIGN KEY (`esquema_configuracion_id`) REFERENCES `esquema_configuracion` (`esquema_configuracion_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK732BBAA75EC5F36D` FOREIGN KEY (`esquema_configuracion_id`) REFERENCES `esquema_configuracion` (`esquema_configuracion_id`),
  CONSTRAINT `FK_configuracion_esquema_configuracion_id` FOREIGN KEY (`esquema_configuracion_id`) REFERENCES `esquema_configuracion` (`esquema_configuracion_id`)
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
  CONSTRAINT `FK_diccionario_aplicacion_detalle_1` FOREIGN KEY (`tipo_objeto`, `nombre_objeto`) REFERENCES `diccionario_aplicacion` (`tipo_objeto`, `nombre_objeto`),
  CONSTRAINT `FK_diccionario_aplicacion_detalle_tipo_objeto` FOREIGN KEY (`tipo_objeto`, `nombre_objeto`) REFERENCES `diccionario_aplicacion` (`tipo_objeto`, `nombre_objeto`)
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
  CONSTRAINT `esquema_configuracion_ibfk_1` FOREIGN KEY (`tipo_objeto`, `nombre_objeto`) REFERENCES `diccionario_aplicacion` (`tipo_objeto`, `nombre_objeto`),
  CONSTRAINT `FK_esquema_configuracion_tipo_objeto` FOREIGN KEY (`tipo_objeto`, `nombre_objeto`) REFERENCES `diccionario_aplicacion` (`tipo_objeto`, `nombre_objeto`)
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
  KEY `FKA9A2F3CD228E4B88` (`circuito`),
  CONSTRAINT `FKA9A2F3CD228E4B88` FOREIGN KEY (`circuito`) REFERENCES `circuitos_estados` (`circuito`),
  CONSTRAINT `FK_estados_1` FOREIGN KEY (`circuito`) REFERENCES `circuitos_estados` (`circuito`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_estados_circuito` FOREIGN KEY (`circuito`) REFERENCES `circuitos_estados` (`circuito`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `estados`
--

/*!40000 ALTER TABLE `estados` DISABLE KEYS */;
INSERT INTO `estados` (`estado`,`nombre`,`descripcion`,`observaciones`,`circuito`) VALUES 
 ('0001.0001','EN GENERACIÓN','El track se está generando',NULL,'0001');
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
  KEY `FK8A7DE224C50A3144` (`clase_lov_atributo_id`),
  CONSTRAINT `FK8A7DE224C50A3144` FOREIGN KEY (`clase_lov_atributo_id`) REFERENCES `clase_lov_atributo` (`clase_lov_atributo_id`),
  CONSTRAINT `FK_lov_atributo_clase_lov_atributo_id` FOREIGN KEY (`clase_lov_atributo_id`) REFERENCES `clase_lov_atributo` (`clase_lov_atributo_id`),
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `menu`
--

/*!40000 ALTER TABLE `menu` DISABLE KEYS */;
INSERT INTO `menu` (`menu_id`,`nombre`,`descripcion`,`url`,`grupo`) VALUES 
 (1,'ABM Roles','Pantalla de ABM de roles','AbmcRoles.zul','BaseRolesPage.zul'),
 (2,'Asignacion de roles','Pantalla de asignación de roles a usuarios','AbmcUsuariosRoles.zul','BaseRolesPage.zul'),
 (3,'Página Principal','Página principal','HomePage.zul',''),
 (4,'Roles','Menú de administración de roles','BaseRolesPage.zul','HomePage.zul');
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
  `OBJ_VERSION` datetime default NULL,
  PRIMARY KEY  (`rol_id`),
  UNIQUE KEY `IX_roles_1` (`nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `roles`
--

/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` (`rol_id`,`nombre`,`descripcion`,`observaciones`,`OBJ_VERSION`) VALUES 
 (1,'Administrador','Administrador del sistema','','2009-04-20 00:00:00'),
 (13,'Hola 8','Hola','','2009-04-21 22:48:19'),
 (18,'Hola 66','Hola','Hola','2009-05-05 13:02:01'),
 (19,'Rol 1','Este es el rol nº 1','Este rol se usa en dos casos: \n- Cuando yo quiero.\n- Cuando se me antoja.','2009-04-30 13:28:07');
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
  KEY `FK86408267FBCB950B` (`rol`),
  KEY `FK86408267238589EB` (`entidad_id`),
  CONSTRAINT `FK86408267238589EB` FOREIGN KEY (`entidad_id`) REFERENCES `entidad_externa` (`entidad_id`),
  CONSTRAINT `FK86408267FBCB950B` FOREIGN KEY (`rol`) REFERENCES `rol_entidad` (`rol`),
  CONSTRAINT `FK_roles_entidad_1` FOREIGN KEY (`entidad_id`) REFERENCES `entidad_externa` (`entidad_id`),
  CONSTRAINT `FK_roles_entidad_2` FOREIGN KEY (`rol`) REFERENCES `rol_entidad` (`rol`),
  CONSTRAINT `FK_roles_entidad_entidad_id` FOREIGN KEY (`entidad_id`) REFERENCES `entidad_externa` (`entidad_id`),
  CONSTRAINT `FK_roles_entidad_rol` FOREIGN KEY (`rol`) REFERENCES `rol_entidad` (`rol`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Roles asociados a la entidad.';

--
-- Dumping data for table `roles_entidad`
--

/*!40000 ALTER TABLE `roles_entidad` DISABLE KEYS */;
/*!40000 ALTER TABLE `roles_entidad` ENABLE KEYS */;


--
-- Definition of table `tracks`
--

DROP TABLE IF EXISTS `tracks`;
CREATE TABLE `tracks` (
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

--
-- Dumping data for table `tracks`
--

/*!40000 ALTER TABLE `tracks` DISABLE KEYS */;
/*!40000 ALTER TABLE `tracks` ENABLE KEYS */;


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
  KEY `FK3F9DE79431B0C074` (`estado_origen`),
  KEY `FK3F9DE79435D24370` (`accion`),
  KEY `FK3F9DE7949502500` (`estado_destino`),
  CONSTRAINT `FK3F9DE79431B0C074` FOREIGN KEY (`estado_origen`) REFERENCES `estados` (`estado`),
  CONSTRAINT `FK3F9DE79435D24370` FOREIGN KEY (`accion`) REFERENCES `acciones_apps` (`accion`),
  CONSTRAINT `FK3F9DE7949502500` FOREIGN KEY (`estado_destino`) REFERENCES `estados` (`estado`),
  CONSTRAINT `FK_transicion_estados_1` FOREIGN KEY (`accion`) REFERENCES `acciones_apps` (`accion`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_transicion_estados_accion` FOREIGN KEY (`accion`) REFERENCES `acciones_apps` (`accion`),
  CONSTRAINT `fk_transicion_estados_destino` FOREIGN KEY (`estado_destino`) REFERENCES `estados` (`estado`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_transicion_estados_estado_destino` FOREIGN KEY (`estado_destino`) REFERENCES `estados` (`estado`),
  CONSTRAINT `FK_transicion_estados_estado_origen` FOREIGN KEY (`estado_origen`) REFERENCES `estados` (`estado`),
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
  KEY `FKC4089EC2FC0A158B` (`preferencia_id`),
  KEY `FKC4089EC2DA06AAD9` (`user_id`),
  CONSTRAINT `FKC4089EC2DA06AAD9` FOREIGN KEY (`user_id`) REFERENCES `website_user` (`user_id`),
  CONSTRAINT `FKC4089EC2FC0A158B` FOREIGN KEY (`preferencia_id`) REFERENCES `preferencias` (`preferencia_id`),
  CONSTRAINT `FK_usuario_preferencias_2` FOREIGN KEY (`user_id`) REFERENCES `website_user` (`user_id`),
  CONSTRAINT `FK_usuario_preferencias_preferencia_id` FOREIGN KEY (`preferencia_id`) REFERENCES `preferencias` (`preferencia_id`),
  CONSTRAINT `FK_usuario_preferencias_user_id` FOREIGN KEY (`user_id`) REFERENCES `website_user` (`user_id`),
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
  `desde` date default NULL,
  `hasta` date default NULL,
  `anulado` char(1) NOT NULL default 'F',
  `OBJ_VERSION` datetime default NULL,
  PRIMARY KEY  (`rol_id`,`user_id`),
  KEY `FK_usuario_roles_2` (`user_id`),
  KEY `FK4D484A6C8101218C` (`rol_id`),
  KEY `FK4D484A6CDA06AAD9` (`user_id`),
  CONSTRAINT `FK4D484A6C8101218C` FOREIGN KEY (`rol_id`) REFERENCES `roles` (`rol_id`),
  CONSTRAINT `FK4D484A6CDA06AAD9` FOREIGN KEY (`user_id`) REFERENCES `website_user` (`user_id`),
  CONSTRAINT `FK_usuario_roles_1` FOREIGN KEY (`rol_id`) REFERENCES `roles` (`rol_id`),
  CONSTRAINT `FK_usuario_roles_2` FOREIGN KEY (`user_id`) REFERENCES `website_user` (`user_id`),
  CONSTRAINT `FK_usuario_roles_rol_id` FOREIGN KEY (`rol_id`) REFERENCES `roles` (`rol_id`),
  CONSTRAINT `FK_usuario_roles_user_id` FOREIGN KEY (`user_id`) REFERENCES `website_user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `usuario_roles`
--

/*!40000 ALTER TABLE `usuario_roles` DISABLE KEYS */;
INSERT INTO `usuario_roles` (`user_id`,`rol_id`,`desde`,`hasta`,`anulado`,`OBJ_VERSION`) VALUES 
 (1,1,'2007-01-01',NULL,'F',NULL),
 (1,13,'2009-04-22','2009-05-03','\0','2009-04-30 13:27:33'),
 (1,18,'2009-04-30',NULL,'\0','2009-04-29 17:30:34'),
 (1,19,'2009-05-02',NULL,'\0','2009-04-30 13:29:28');
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
  `OBJ_VERSION` datetime default NULL,
  PRIMARY KEY  (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `website_user`
--

/*!40000 ALTER TABLE `website_user` DISABLE KEYS */;
INSERT INTO `website_user` (`user_id`,`login_name`,`login_password`,`nivel_visibilidad`,`nombre_completo`,`email`,`nro_legajo`,`nro_comprador`,`OBJ_VERSION`) VALUES 
 (1,'Juanma','123456','Todo','Juan Manuel',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `website_user` ENABLE KEYS */;


--
-- Definition of table `workers_per_track`
--

DROP TABLE IF EXISTS `workers_per_track`;
CREATE TABLE `workers_per_track` (
  `track_id` int(10) unsigned NOT NULL,
  `worker_id` int(10) unsigned NOT NULL,
  PRIMARY KEY  (`track_id`,`worker_id`),
  KEY `FK_workers_per_track_website_users` (`worker_id`),
  CONSTRAINT `FK_workers_per_track_tracks` FOREIGN KEY (`track_id`) REFERENCES `tracks` (`track_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_workers_per_track_website_users` FOREIGN KEY (`worker_id`) REFERENCES `website_user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Almacena los workers asociados a un track';

--
-- Dumping data for table `workers_per_track`
--

/*!40000 ALTER TABLE `workers_per_track` DISABLE KEYS */;
/*!40000 ALTER TABLE `workers_per_track` ENABLE KEYS */;


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
