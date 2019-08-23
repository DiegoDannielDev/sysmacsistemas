-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: dbimobiliaria
-- ------------------------------------------------------
-- Server version	5.5.5-10.1.31-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `empresa`
--

DROP TABLE IF EXISTS `empresa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `empresa` (
  `CODIGO` int(11) NOT NULL AUTO_INCREMENT,
  `NOME` varchar(45) NOT NULL,
  `CNPJ` int(10) unsigned DEFAULT NULL,
  `IE` int(10) unsigned DEFAULT NULL,
  `ENDERECO` varchar(45) DEFAULT NULL,
  `N_ENDERECO` varchar(45) DEFAULT NULL,
  `CEP` int(11) DEFAULT NULL,
  `CIDADE` varchar(45) NOT NULL,
  `UF` char(1) DEFAULT NULL,
  `COMPLEMENTO` varchar(45) DEFAULT NULL,
  `RAMO` varchar(45) DEFAULT NULL,
  `STATUS` char(1) DEFAULT NULL,
  PRIMARY KEY (`CODIGO`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empresa`
--

LOCK TABLES `empresa` WRITE;
/*!40000 ALTER TABLE `empresa` DISABLE KEYS */;
INSERT INTO `empresa` VALUES (1,'SysMac Sistemas',541000145,14142512,'AV IMACULADA CONCEIÇÃO','1101-F',79750000,'NOVA ANDRADINA','M','RUA MARIO LOPES BEIRO','SISTEMAS','A');
/*!40000 ALTER TABLE `empresa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario_empresa`
--

DROP TABLE IF EXISTS `usuario_empresa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario_empresa` (
  `EMPRESA_CODIGO` int(11) NOT NULL,
  `USUARIOS_CODIGO` int(11) NOT NULL,
  PRIMARY KEY (`EMPRESA_CODIGO`,`USUARIOS_CODIGO`),
  KEY `fk_EMPRESA_has_USUARIOS_USUARIOS1_idx` (`USUARIOS_CODIGO`),
  KEY `fk_EMPRESA_has_USUARIOS_EMPRESA_idx` (`EMPRESA_CODIGO`),
  CONSTRAINT `fk_EMPRESA_has_USUARIOS_EMPRESA` FOREIGN KEY (`EMPRESA_CODIGO`) REFERENCES `empresa` (`CODIGO`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_EMPRESA_has_USUARIOS_USUARIOS1` FOREIGN KEY (`USUARIOS_CODIGO`) REFERENCES `usuarios` (`CODIGO`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario_empresa`
--

LOCK TABLES `usuario_empresa` WRITE;
/*!40000 ALTER TABLE `usuario_empresa` DISABLE KEYS */;
INSERT INTO `usuario_empresa` VALUES (1,1);
/*!40000 ALTER TABLE `usuario_empresa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuarios` (
  `CODIGO` int(11) NOT NULL AUTO_INCREMENT,
  `NOME` varchar(45) NOT NULL,
  `LOGIN` varchar(45) NOT NULL,
  `SENHA` varchar(45) NOT NULL,
  `EMAIL` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`CODIGO`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (1,'DIEGO DANNIEL DE SOUSA DINIZ','DIEGO','5715258','diego.diniz@novaandradina.org');
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'dbimobiliaria'
--

--
-- Dumping routines for database 'dbimobiliaria'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-05-06 14:49:07
