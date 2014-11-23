/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50540
Source Host           : localhost:3306
Source Database       : eya

Target Server Type    : MYSQL
Target Server Version : 50540
File Encoding         : 65001

Date: 2014-11-11 18:24:36
*/

-- ----------------------------
-- Table structure for articulo
-- ----------------------------
DROP TABLE IF EXISTS `articulo`;
CREATE TABLE `articulo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `titulo` varchar(50) DEFAULT NULL,
  `contenido` longtext,
  `fechaEscritura` date NOT NULL,
  `fechaPub` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for cuenta
-- ----------------------------
DROP TABLE IF EXISTS `cuenta`;
CREATE TABLE `cuenta` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `usuario` varchar(20) NOT NULL UNIQUE,
  `contrasenia` varchar(15) NOT NULL,
  `tipo` varchar(15) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for autor
-- ----------------------------
DROP TABLE IF EXISTS `autor`;
CREATE TABLE `autor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) DEFAULT NULL,
  `fechaUltimaPublicacion` date DEFAULT NULL,
  `tipoAutor` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for cartaEditorJefe
-- ----------------------------
DROP TABLE IF EXISTS `cartaEditorJefe`;
CREATE TABLE `cartaEditorJefe` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `contenido` longtext,
  `idEditor` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for consejo
-- ----------------------------
DROP TABLE IF EXISTS `consejo`;
CREATE TABLE `consejo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fechaInicio` date NOT NULL,
  `fechaFin` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for editorJefe
-- ----------------------------
DROP TABLE IF EXISTS `editorJefe`;
CREATE TABLE `editorJefe` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) DEFAULT NULL,
  `idConsejo` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for guru
-- ----------------------------
DROP TABLE IF EXISTS `guru`;
CREATE TABLE `guru` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) DEFAULT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for guruPub
-- ----------------------------
DROP TABLE IF EXISTS `guruPub`;
CREATE TABLE `guruPub` (
  `idGuru` int(11) NOT NULL,
  `idPub` int(11) NOT NULL,
  `fechaEnvio` date DEFAULT NULL,
  PRIMARY KEY (`idGuru`,`idPub`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for juez
-- ----------------------------
DROP TABLE IF EXISTS `juez`;
CREATE TABLE `juez` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) DEFAULT NULL,
  `idConsejo` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for publicacion
-- ----------------------------
DROP TABLE IF EXISTS `publicacion`;
CREATE TABLE `publicacion` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idJefe` int(11) NOT NULL,
  `idCarta` int(11) NOT NULL,
  `fechaPub` date NOT NULL,
  `titulo` varchar(50) NOT NULL,
  `tema` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for puestoRevista
-- ----------------------------
DROP TABLE IF EXISTS `puestoRevistas`;
CREATE TABLE `puestoRevistas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `direccion` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for puestoRevistasPub
-- ----------------------------
DROP TABLE IF EXISTS `puestoRevistasPub`;
CREATE TABLE `puestoRevistasPub` (
  `idPuesto` int(11) NOT NULL,
  `idPub` int(11) NOT NULL,
  `fechaEnvio` date DEFAULT NULL,
  `cantidadEnviada` int(11) DEFAULT NULL,
  `fechaRegreso` date DEFAULT NULL,
  `cantidadRegresada` int(11) DEFAULT NULL,
  PRIMARY KEY (`idPuesto`,`idPub`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for subscripcion
-- ----------------------------
DROP TABLE IF EXISTS `subscripcion`;
CREATE TABLE `subscripcion` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idSubscriptor` int(11) NOT NULL,
  `anios` int(11) DEFAULT NULL,
  `total` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for subscriptor
-- ----------------------------
DROP TABLE IF EXISTS `subscriptor`;
CREATE TABLE `subscriptor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  `tipo` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for subscriptorPub
-- ----------------------------
DROP TABLE IF EXISTS `subscriptorPub`;
CREATE TABLE `subscriptorPub` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idSub` int(11) NOT NULL,
  `idPub` int(11) NOT NULL,
  `fechaEnvio` date NOT NULL,
  `costoAdicional` double DEFAULT NULL,
  PRIMARY KEY (`id`,`idSub`,`idPub`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for temaSugerido
-- ----------------------------
DROP TABLE IF EXISTS `temaSugerido`;
CREATE TABLE `temaSugerido` (
  `idJuez` int(11) NOT NULL,
  `idPub` int(11) NOT NULL,
  `tema` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idJuez`,`idPub`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


-- ----------------------------
-- Table structure for autorArticulo
-- ----------------------------
DROP TABLE IF EXISTS `autorArticulo`;
CREATE TABLE `autorArticulo` (
  `idAutor` int(11) NOT NULL,
  `idArticulo` int(11) NOT NULL,
  PRIMARY KEY (`idAutor`,`idArticulo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


ALTER TABLE cartaEditorJefe

ADD CONSTRAINT
FOREIGN KEY (idEditor) REFERENCES editorJefe(id)
ON UPDATE CASCADE
ON DELETE CASCADE;


ALTER TABLE editorJefe

ADD CONSTRAINT
FOREIGN KEY (idConsejo) REFERENCES consejo(id)
ON UPDATE CASCADE
ON DELETE CASCADE;


ALTER TABLE guruPub

ADD CONSTRAINT
FOREIGN KEY (idGuru) REFERENCES guru(id)
ON UPDATE CASCADE
ON DELETE CASCADE,

ADD CONSTRAINT
FOREIGN KEY (idPub) REFERENCES publicacion(id)
ON UPDATE CASCADE
ON DELETE CASCADE;


ALTER TABLE juez

ADD CONSTRAINT
FOREIGN KEY (idConsejo) REFERENCES consejo(id)
ON UPDATE CASCADE
ON DELETE CASCADE;


ALTER TABLE publicacion

ADD CONSTRAINT
FOREIGN KEY (idJefe) REFERENCES editorJefe(id)
ON UPDATE CASCADE
ON DELETE CASCADE,

ADD CONSTRAINT
FOREIGN KEY (idCarta) REFERENCES cartaEditorJefe(id)
ON UPDATE CASCADE
ON DELETE CASCADE;


ALTER TABLE puestoRevistasPub

ADD CONSTRAINT
FOREIGN KEY (idPuesto) REFERENCES puestoRevistas(id)
ON UPDATE CASCADE
ON DELETE CASCADE,

ADD CONSTRAINT
FOREIGN KEY (idPub) REFERENCES publicacion(id)
ON UPDATE CASCADE
ON DELETE CASCADE;


ALTER TABLE subscripcion

ADD CONSTRAINT
FOREIGN KEY (idSubscriptor) REFERENCES subscriptor(id)
ON UPDATE CASCADE
ON DELETE CASCADE;


ALTER TABLE subscriptorPub

ADD CONSTRAINT
FOREIGN KEY (idSub) REFERENCES subscriptor(id)
ON UPDATE CASCADE
ON DELETE CASCADE,

ADD CONSTRAINT
FOREIGN KEY (idPub) REFERENCES publicacion(id)
ON UPDATE CASCADE
ON DELETE CASCADE;


ALTER TABLE temaSugerido

ADD CONSTRAINT
FOREIGN KEY (idPub) REFERENCES publicacion(id)
ON UPDATE CASCADE
ON DELETE CASCADE,

ADD CONSTRAINT
FOREIGN KEY (idJuez) REFERENCES juez(id)
ON UPDATE CASCADE
ON DELETE CASCADE;


ALTER TABLE autorArticulo

ADD CONSTRAINT
FOREIGN KEY (idAutor) REFERENCES autor(id)
ON UPDATE CASCADE
ON DELETE CASCADE,

ADD CONSTRAINT
FOREIGN KEY (idArticulo) REFERENCES articulo(id)
ON UPDATE CASCADE
ON DELETE CASCADE;
