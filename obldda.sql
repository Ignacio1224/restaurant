SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

CREATE DATABASE IF NOT EXISTS `obldda` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `obldda`;

CREATE TABLE `beneficio` (
  `oid` int(11) NOT NULL,
  `descripcion` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `beneficio` (`oid`, `descripcion`) VALUES
(6, 'Comun'),
(5, 'De la casa'),
(7, 'Preferencial');

CREATE TABLE `cliente` (
  `oid` int(11) NOT NULL,
  `id` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `oidBeneficio` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `cliente` (`oid`, `id`, `nombre`, `email`, `oidBeneficio`) VALUES
(8, 1, 'Shubert', 'c1@gmail.con', 5),
(9, 2, 'Titina', 'c2@gmail.con', 6),
(10, 3, 'Pepe', 'c3@gmail.con', 7);

CREATE TABLE `itemservicio` (
  `oid` int(11) NOT NULL,
  `mesa` int(11) NOT NULL,
  `oidServicio` int(11) NOT NULL,
  `oidProducto` int(11) NOT NULL,
  `descripcion` varchar(50) DEFAULT NULL,
  `cantidad` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `mesa` (
  `oid` int(11) NOT NULL,
  `nroMesa` int(11) NOT NULL,
  `oidMozo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `mesa` (`oid`, `nroMesa`, `oidMozo`) VALUES
(11, 1, 1),
(12, 2, 1),
(13, 3, 1),
(14, 4, 1),
(15, 5, 1),
(16, 6, 1),
(17, 7, 1),
(18, 8, 1),
(19, 9, 1),
(20, 10, 2),
(21, 11, 2),
(22, 12, 2),
(23, 13, 2),
(24, 14, 2),
(25, 15, 2),
(26, 16, 2),
(27, 17, 2),
(28, 18, 2);

CREATE TABLE `oid` (
  `ultimo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `oid` (`ultimo`) VALUES
(39);

CREATE TABLE `producto` (
  `oid` int(11) NOT NULL,
  `codigo` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `precio` float NOT NULL,
  `stock` int(11) NOT NULL,
  `oidUnidadProcesadora` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `producto` (`oid`, `codigo`, `nombre`, `precio`, `stock`, `oidUnidadProcesadora`) VALUES
(32, 1, 'Pan', 20, 40, 29),
(33, 2, 'Pure', 20, 40, 29),
(34, 3, 'Asado', 20, 40, 29),
(35, 4, 'Helado', 20, 40, 31),
(36, 5, 'Flan', 20, 40, 31),
(37, 6, 'Papas fritas', 20, 40, 29),
(38, 7, 'Agua', 20, 40, 30),
(39, 8, 'Cafe', 20, 40, 30);

CREATE TABLE `servicio` (
  `mesa` int(11) NOT NULL,
  `oidCliente` int(11) DEFAULT NULL,
  `oidMozo` int(11) NOT NULL,
  `costo` float NOT NULL,
  `oid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `unidadprocesadora` (
  `oid` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `unidadprocesadora` (`oid`, `nombre`) VALUES
(30, 'Bar'),
(29, 'Cocina'),
(31, 'Postres');

CREATE TABLE `usuario` (
  `oid` int(11) NOT NULL,
  `usuario` varchar(50) NOT NULL,
  `clave` varchar(50) NOT NULL,
  `nombreCompleto` varchar(50) NOT NULL,
  `tipo` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `usuario` (`oid`, `usuario`, `clave`, `nombreCompleto`, `tipo`) VALUES
(4, 'gestorNacho', '1234', 'Gestor Nacho', 'gestor'),
(3, 'gestorSantiago', '1234', 'Gestor Santiago', 'gestor'),
(1, 'mozoNacho', '1234', 'Mozo Nacho', 'mozo'),
(2, 'mozoSantiago', '1234', 'Mozo Santiago', 'mozo');


ALTER TABLE `beneficio`
  ADD PRIMARY KEY (`descripcion`);

ALTER TABLE `cliente`
  ADD PRIMARY KEY (`id`);

ALTER TABLE `itemservicio`
  ADD PRIMARY KEY (`oid`,`oidServicio`);

ALTER TABLE `mesa`
  ADD PRIMARY KEY (`nroMesa`);

ALTER TABLE `oid`
  ADD PRIMARY KEY (`ultimo`);

ALTER TABLE `producto`
  ADD PRIMARY KEY (`codigo`);

ALTER TABLE `servicio`
  ADD PRIMARY KEY (`oid`);

ALTER TABLE `unidadprocesadora`
  ADD PRIMARY KEY (`nombre`);

ALTER TABLE `usuario`
  ADD PRIMARY KEY (`usuario`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
