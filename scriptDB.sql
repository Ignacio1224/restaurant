/*
SQLyog Trial v13.1.5  (64 bit)
MySQL - 8.0.18 : Database - obldda
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`obldda` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `obldda`;

/*Table structure for table `beneficio` */

DROP TABLE IF EXISTS `beneficio`;

CREATE TABLE `beneficio` (
  `descripcion` varchar(50) NOT NULL,
  PRIMARY KEY (`descripcion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `beneficio` */

insert  into `beneficio`(`descripcion`) values 
('Comun'),
('De la casa'),
('Preferencial');

/*Table structure for table `cliente` */

DROP TABLE IF EXISTS `cliente`;

CREATE TABLE `cliente` (
  `id` int(11) NOT NULL,
  `nombre` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `beneficio` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Cliente_Beneficio` (`beneficio`),
  CONSTRAINT `FK_Cliente_Beneficio` FOREIGN KEY (`beneficio`) REFERENCES `beneficio` (`descripcion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `cliente` */

insert  into `cliente`(`id`,`nombre`,`email`,`beneficio`) values 
(1,'Shubert','c1@gmail.com','De la casa'),
(2,'Titina','c2@gmail.com','Comun');

/*Table structure for table `itemservicio` */

DROP TABLE IF EXISTS `itemservicio`;

CREATE TABLE `itemservicio` (
  `mesa` int(11) NOT NULL,
  `idServicio` int(11) NOT NULL,
  `producto` int(11) NOT NULL,
  `descripcion` varchar(50) DEFAULT NULL,
  `cantidad` int(11) NOT NULL,
  `posicion` int(11) NOT NULL,
  `oid` int(11) NOT NULL,
  PRIMARY KEY (`idServicio`,`posicion`),
  KEY `mesa` (`mesa`),
  KEY `producto` (`producto`),
  CONSTRAINT `itemservicio_ibfk_1` FOREIGN KEY (`mesa`) REFERENCES `mesa` (`nroMesa`),
  CONSTRAINT `itemservicio_ibfk_2` FOREIGN KEY (`idServicio`) REFERENCES `servicio` (`idServicio`),
  CONSTRAINT `itemservicio_ibfk_3` FOREIGN KEY (`producto`) REFERENCES `producto` (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `itemservicio` */

/*Table structure for table `mesa` */

DROP TABLE IF EXISTS `mesa`;

CREATE TABLE `mesa` (
  `nroMesa` int(11) NOT NULL,
  `mozo` varchar(50) NOT NULL,
  PRIMARY KEY (`nroMesa`),
  KEY `FK_MesaMozo` (`mozo`),
  CONSTRAINT `FK_MesaMozo` FOREIGN KEY (`mozo`) REFERENCES `usuario` (`usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `mesa` */

insert  into `mesa`(`nroMesa`,`mozo`) values 
(1,'mozoNacho'),
(2,'mozoNacho'),
(3,'mozoNacho'),
(4,'mozoNacho'),
(5,'mozoNacho'),
(6,'mozoNacho'),
(7,'mozoNacho'),
(8,'mozoNacho'),
(9,'mozoNacho'),
(10,'mozoSantiago'),
(11,'mozoSantiago'),
(12,'mozoSantiago'),
(13,'mozoSantiago'),
(14,'mozoSantiago'),
(15,'mozoSantiago'),
(16,'mozoSantiago'),
(17,'mozoSantiago'),
(18,'mozoSantiago');

/*Table structure for table `oid` */

DROP TABLE IF EXISTS `oid`;

CREATE TABLE `oid` (
  `ultimo` int(11) NOT NULL,
  PRIMARY KEY (`ultimo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `oid` */

/*Table structure for table `producto` */

DROP TABLE IF EXISTS `producto`;

CREATE TABLE `producto` (
  `codigo` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `precio` float NOT NULL,
  `stock` int(11) NOT NULL,
  `unidadProcesadora` varchar(50) NOT NULL,
  PRIMARY KEY (`codigo`),
  KEY `FK_Producto_UProcesadora` (`unidadProcesadora`),
  CONSTRAINT `FK_Producto_UProcesadora` FOREIGN KEY (`unidadProcesadora`) REFERENCES `unidadprocesadora` (`nombre`),
  CONSTRAINT `CST_Precio` CHECK ((`precio` > 0)),
  CONSTRAINT `CST_Stock` CHECK ((`stock` >= 0))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `producto` */

insert  into `producto`(`codigo`,`nombre`,`precio`,`stock`,`unidadProcesadora`) values 
(1,'Pan',20,20,'bar'),
(2,'Papas Fritas',50,40,'bar'),
(3,'Milanesa',100,39,'bar'),
(4,'Pure',80,20,'bar'),
(5,'Omelette',49,23,'bar'),
(6,'Helado',30,49,'postres'),
(7,'Vino',20,49,'bebidas'),
(8,'Agua',40,29,'bebidas'),
(9,'Cafe',30,20,'bar');

/*Table structure for table `servicio` */

DROP TABLE IF EXISTS `servicio`;

CREATE TABLE `servicio` (
  `mesa` int(11) NOT NULL,
  `cliente` varchar(50) DEFAULT NULL,
  `mozo` varchar(50) NOT NULL,
  `costo` float NOT NULL,
  `oid` int(11) NOT NULL,
  `idServicio` int(11) NOT NULL,
  PRIMARY KEY (`idServicio`),
  KEY `FK_Servicio_Mozo` (`mozo`),
  KEY `FK_Servicio_Mesa` (`mesa`),
  CONSTRAINT `FK_Servicio_Mesa` FOREIGN KEY (`mesa`) REFERENCES `mesa` (`nroMesa`),
  CONSTRAINT `FK_Servicio_Mozo` FOREIGN KEY (`mozo`) REFERENCES `usuario` (`usuario`),
  CONSTRAINT `CTR_Costo` CHECK ((`costo` > 0))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `servicio` */

/*Table structure for table `unidadprocesadora` */

DROP TABLE IF EXISTS `unidadprocesadora`;

CREATE TABLE `unidadprocesadora` (
  `nombre` varchar(50) NOT NULL,
  PRIMARY KEY (`nombre`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `unidadprocesadora` */

insert  into `unidadprocesadora`(`nombre`) values 
('bar'),
('bebidas'),
('postres');

/*Table structure for table `usuario` */

DROP TABLE IF EXISTS `usuario`;

CREATE TABLE `usuario` (
  `usuario` varchar(50) NOT NULL,
  `clave` varchar(50) NOT NULL,
  `nombreCompleto` varchar(50) NOT NULL,
  `tipo` varchar(10) NOT NULL,
  PRIMARY KEY (`usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `usuario` */

insert  into `usuario`(`usuario`,`clave`,`nombreCompleto`,`tipo`) values 
('gestorNacho','1234','Cabrera Ignacio','gestor'),
('gestorSantiago','1234','Manzoni Santiago','gestor'),
('mozoNacho','1234','Ignacio Cabrera','mozo'),
('mozoSantiago','1234','Santiago Manzoni','mozo');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
