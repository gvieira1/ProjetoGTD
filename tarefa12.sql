CREATE DATABASE  IF NOT EXISTS `tarefa` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_bin */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `tarefa`;
-- MySQL dump 10.13  Distrib 8.0.36, for Linux (x86_64)
--
-- Host: localhost    Database: tarefa
-- ------------------------------------------------------
-- Server version	8.0.40-0ubuntu0.22.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `categoria`
--

DROP TABLE IF EXISTS `categoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categoria` (
  `id` int NOT NULL AUTO_INCREMENT,
  `descricao` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categoria`
--

LOCK TABLES `categoria` WRITE;
/*!40000 ALTER TABLE `categoria` DISABLE KEYS */;
INSERT INTO `categoria` VALUES (1,'Quando Puder'),(2,'Agendado'),(3,'Projeto'),(4,'Aguardando Resposta'),(5,'Arquivo'),(6,'Caixa de Entrada'),(7,'Concluído');
/*!40000 ALTER TABLE `categoria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `projeto`
--

DROP TABLE IF EXISTS `projeto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `projeto` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_usuario` int NOT NULL,
  `descricao` varchar(255) NOT NULL,
  `feito` tinyint(1) DEFAULT '0',
  `categoria_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_usuario` (`id_usuario`),
  KEY `fk_categoria` (`categoria_id`),
  CONSTRAINT `fk_categoria` FOREIGN KEY (`categoria_id`) REFERENCES `categoria` (`id`) ON DELETE SET NULL,
  CONSTRAINT `projeto_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `projeto`
--

LOCK TABLES `projeto` WRITE;
/*!40000 ALTER TABLE `projeto` DISABLE KEYS */;
INSERT INTO `projeto` VALUES (1,2,'Projeto com caracteres especiais: ã, ç, é, ô',0,3),(2,2,'Projeto piloto',0,3),(3,2,'Estudar React',0,3),(5,2,'Estudar Angular',0,3),(6,2,'Estudar JS',0,3);
/*!40000 ALTER TABLE `projeto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subtarefa`
--

DROP TABLE IF EXISTS `subtarefa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `subtarefa` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_projeto` int NOT NULL,
  `descricao` varchar(255) NOT NULL,
  `feito` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `id_projeto` (`id_projeto`),
  CONSTRAINT `subtarefa_ibfk_1` FOREIGN KEY (`id_projeto`) REFERENCES `projeto` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subtarefa`
--

LOCK TABLES `subtarefa` WRITE;
/*!40000 ALTER TABLE `subtarefa` DISABLE KEYS */;
/*!40000 ALTER TABLE `subtarefa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tarefa`
--

DROP TABLE IF EXISTS `tarefa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tarefa` (
  `id` int NOT NULL AUTO_INCREMENT,
  `descricao` varchar(255) NOT NULL,
  `prioridade` tinyint(1) DEFAULT '0',
  `prazo` date DEFAULT NULL,
  `tempo_estimado_id` int DEFAULT NULL,
  `assunto` varchar(100) DEFAULT NULL,
  `feito` tinyint(1) DEFAULT '0',
  `delegado` tinyint(1) DEFAULT '0',
  `usuario_id` int NOT NULL,
  `categoria_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `tempo_estimado_id` (`tempo_estimado_id`),
  KEY `fk_usuario` (`usuario_id`),
  KEY `fk_categoria_tarefa` (`categoria_id`),
  CONSTRAINT `fk_categoria_tarefa` FOREIGN KEY (`categoria_id`) REFERENCES `categoria` (`id`) ON DELETE SET NULL,
  CONSTRAINT `fk_usuario` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`) ON DELETE CASCADE,
  CONSTRAINT `tarefa_ibfk_2` FOREIGN KEY (`tempo_estimado_id`) REFERENCES `tempo_estimado` (`id`) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=108 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tarefa`
--

LOCK TABLES `tarefa` WRITE;
/*!40000 ALTER TABLE `tarefa` DISABLE KEYS */;
INSERT INTO `tarefa` VALUES (1,'Estudar Jquery',1,'2024-11-20',2,NULL,0,0,1,NULL),(13,'Teste Final',3,'2024-11-18',3,'show de bola',0,0,1,NULL),(14,'Tarefas',1,'2024-11-18',1,'showsa',0,0,1,NULL),(81,'Teste Osmar',0,NULL,2,'Teste Osamra',0,1,1,4),(82,'Teste SOmara',0,NULL,NULL,NULL,0,0,1,6),(91,'Revisar Conteúdo Sistemas Operacionais',0,'2024-12-11',2,'Sistemas Operacionais',1,0,2,7),(93,'Atividade Sprint Extensão',1,'2024-12-07',2,'Extensão',0,1,2,4),(94,'Estudar GraphQL',0,NULL,4,'GraphQL',0,0,2,5),(95,'Ler Sommerville',0,NULL,4,'Engenharia de Software',0,0,2,5),(96,'Live tira dúvidas Sistemas Operacionais',0,'2024-12-11',2,'Sistemas Operacionais',0,0,2,2),(97,'Ver feedback GPD1',1,NULL,1,'TCC',1,0,2,7),(98,'Slide TCC',1,'2024-12-09',2,'TCC',0,1,2,4),(99,'Estudar AWS',0,NULL,4,'Nuvem',0,0,2,5),(100,'Ler Engenharia de Software Moderna',0,NULL,4,'Engenharia de Software',0,0,2,5),(101,'Projeto Prático Sistemas Operacionais',0,'2024-12-11',2,'Sistemas Operacionais',0,0,2,2),(102,'Atividade APRM Semana 13',1,'2024-11-26',2,'Machine Learning',1,0,2,7),(103,'Entrega Relatório TCC',1,NULL,2,'TCC',1,0,2,7),(104,'Palestra AWS',0,'2024-11-09',2,'Nuvem',1,0,2,7),(105,'Diagrama de Classes TCC',0,NULL,2,'TCC',1,0,2,7),(106,'Gravar Vídeo TCC',0,NULL,NULL,NULL,0,0,2,6),(107,'Fazer apresentação TCC',0,NULL,NULL,NULL,0,0,2,6);
/*!40000 ALTER TABLE `tarefa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tempo_estimado`
--

DROP TABLE IF EXISTS `tempo_estimado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tempo_estimado` (
  `id` int NOT NULL AUTO_INCREMENT,
  `tempo` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tempo_estimado`
--

LOCK TABLES `tempo_estimado` WRITE;
/*!40000 ALTER TABLE `tempo_estimado` DISABLE KEYS */;
INSERT INTO `tempo_estimado` VALUES (1,'Até 1 hora'),(2,'Até 2 horas'),(3,'Mais de 2 horas'),(4,'Mais que um dia');
/*!40000 ALTER TABLE `tempo_estimado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `senha` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'osmar','osmar@gmail','123'),(2,'jose','jose@gmail','321'),(3,'guilherme','gui@gmail','456');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-12-10 11:45:52
