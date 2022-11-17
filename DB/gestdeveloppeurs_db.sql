-- MySQL dump 10.13  Distrib 5.7.26, for Win32 (AMD64)
--
-- Host: localhost    Database: gestdeveloppeurs_db
-- ------------------------------------------------------
-- Server version	5.7.26

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
-- Table structure for table `actions`
--

DROP TABLE IF EXISTS `actions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `actions` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `str_xtype` varchar(30) DEFAULT NULL,
  `str_name` varchar(50) DEFAULT NULL,
  `str_id` varchar(50) NOT NULL,
  `str_description` varchar(200) DEFAULT NULL,
  `str_text` varchar(50) NOT NULL,
  `p_key` varchar(100) DEFAULT NULL,
  `str_class` varchar(100) DEFAULT NULL,
  `str_datatarget` varchar(50) DEFAULT '',
  `str_datatoggle` varchar(40) DEFAULT NULL,
  `str_icon` varchar(200) DEFAULT NULL,
  `str_class_legende` varchar(150) DEFAULT '',
  `str_dataplacement` varchar(40) DEFAULT '',
  `int_priority` tinyint(4) NOT NULL DEFAULT '0',
  `str_status` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 AVG_ROW_LENGTH=292 PACK_KEYS=0;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actions`
--

LOCK TABLES `actions` WRITE;
/*!40000 ALTER TABLE `actions` DISABLE KEYS */;
INSERT INTO `actions` VALUES (1,'button-toolbar','create','ajouter','Action de créer un nouvel enregistrement','AJOUTER','P_BTN_CREATE','btn btn-oval btn-dark','#myModal','modal','fas fa-plus','','',0,'enable'),(2,'button','edit','editer','Action de modifier un nouvel enregistrement','MODIFIER','P_BTN_EDIT','btn btn-sm btn-outline-info','#myeditModal','modal','fas fa-edit','btn btn-sm btn-outline-info disabled','',1,'enable'),(3,'button','delete','supprimer','Permet de supprimer un enregistrement','SUPPRIMER','P_BTN_DELETE','btn btn-outline-danger btn-sm','','tooltip','fas fa-trash','btn btn-sm btn-outline-danger disabled','bottom',2,'enable'),(4,'button','openprivilege','afficherprivilege','Action pour afficher ou ajouter des privileges','AFFICHER OU AJOUTER DES PRIVILEGES','P_BTN_AFFICHERPRIVILEGE','btn btn-sm btn-outline-secondary','','tooltip','fas fa-eye','btn btn-sm btn-outline-dark disabled','bottom',3,'enable'),(5,'button','addskills','ajoutercompetence','Action pour ajouter des competences','AJOUTER DES COMPETENCES','P_BTN_ADD_SKILLS','btn btn-sm btn-outline-secondary','','tooltip','fas fa-plus','btn btn-sm btn-outline-dark disabled','bottom',4,'enable'),(6,'button','adddeveloper','ajouterdeveloppeurs','Action pour Ajouter des Developpeurs','AJOUTER DES DEVELOPPEURS','P_BTN_ADD_DEVELOPER','btn btn-sm btn-outline-secondary','','tooltip','fas fa-plus','btn btn-sm btn-outline-dark disabled','bottom',5,'enable'),(7,'button','senddemande','envoyerdemande','Action pour envoyer une demande','ENVOYER DEMANDE','P_BTN_SENDDEMANDE','btn btn-sm btn-outline-secondary','','tooltip','fas fa-reply','btn btn-sm btn-outline-dark disabled','bottom',6,'enable'),(8,'button','valided','valider','Action pour validation','VALIDER','P_BTN_VALIDED','btn btn-sm btn-outline-secondary','','tooltip','fas fa-check','btn btn-sm btn-outline-dark disabled','bottom',7,'enable'),(9,'button','rejected','rejeter','Action de Rejet','REJETER','P_BTN_REJECT','btn btn-sm btn-outline-secondary','','tooltip','fas fa-ban','btn btn-sm btn-outline-dark disabled','bottom',8,'enable');
/*!40000 ALTER TABLE `actions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `developer`
--

DROP TABLE IF EXISTS `developer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `developer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(75) DEFAULT NULL,
  `last_name` varchar(200) DEFAULT NULL,
  `iduser` int(11) DEFAULT NULL,
  `created_by` varchar(40) DEFAULT NULL,
  `updated_by` varchar(40) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `str_statut` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=latin1 PACK_KEYS=0;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `developer`
--

LOCK TABLES `developer` WRITE;
/*!40000 ALTER TABLE `developer` DISABLE KEYS */;
INSERT INTO `developer` VALUES (3,'Konate','Donald',3,'2',NULL,'2022-11-14 14:38:37',NULL,'enable'),(6,'Alla','Franco',4,'2',NULL,'2022-11-15 15:27:06',NULL,'enable'),(7,'moussa','secouta',5,'2',NULL,'2022-11-15 22:35:57',NULL,'enable'),(8,'Talla','jules',6,'2',NULL,'2022-11-15 23:16:47',NULL,'enable');
/*!40000 ALTER TABLE `developer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `developer_skills`
--

DROP TABLE IF EXISTS `developer_skills`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `developer_skills` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `iddeveloper` int(11) NOT NULL,
  `idskills` int(11) NOT NULL,
  `description` varchar(500) DEFAULT NULL,
  `created_by` varchar(40) DEFAULT NULL,
  `updated_by` varchar(40) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `str_statut` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 AVG_ROW_LENGTH=98 PACK_KEYS=0;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `developer_skills`
--

LOCK TABLES `developer_skills` WRITE;
/*!40000 ALTER TABLE `developer_skills` DISABLE KEYS */;
INSERT INTO `developer_skills` VALUES (8,3,3,NULL,'2',NULL,'2022-11-14 17:21:34',NULL,'enable'),(10,3,4,NULL,'2',NULL,'2022-11-14 18:28:58',NULL,'enable'),(11,3,7,NULL,'2',NULL,'2022-11-15 15:26:03',NULL,'enable'),(12,3,13,NULL,'2',NULL,'2022-11-15 15:26:19',NULL,'enable'),(13,6,11,NULL,'2',NULL,'2022-11-15 15:27:17',NULL,'enable'),(14,6,12,NULL,'2',NULL,'2022-11-15 15:27:21',NULL,'enable'),(15,6,5,NULL,'2',NULL,'2022-11-15 15:27:26',NULL,'enable'),(16,6,6,NULL,'2',NULL,'2022-11-15 15:27:29',NULL,'enable'),(17,7,14,NULL,'2',NULL,'2022-11-15 23:13:14',NULL,'enable'),(18,7,15,NULL,'2',NULL,'2022-11-15 23:13:16',NULL,'enable');
/*!40000 ALTER TABLE `developer_skills` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `menu`
--

DROP TABLE IF EXISTS `menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `str_value` varchar(30) DEFAULT NULL,
  `str_description` varchar(200) DEFAULT NULL,
  `int_priority` int(11) DEFAULT '0',
  `str_class` varchar(100) DEFAULT NULL,
  `str_href` varchar(100) DEFAULT NULL,
  `str_status` varchar(20) DEFAULT NULL,
  `p_key` varchar(100) DEFAULT NULL,
  `idmodule` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `menu_fk1` (`idmodule`),
  CONSTRAINT `menu_fk1` FOREIGN KEY (`idmodule`) REFERENCES `module` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 PACK_KEYS=0 ROW_FORMAT=COMPACT;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menu`
--

LOCK TABLES `menu` WRITE;
/*!40000 ALTER TABLE `menu` DISABLE KEYS */;
INSERT INTO `menu` VALUES (1,'gestiondesdroitsutilisateurs','Droits Utilisateurs',1,'icon-layers','#gestiondesdroitsutilisateurs','enable','P_M_GESTION_DES_DROITSUTILISATEURS',1),(2,'gestiondesdeveloppeurs','Gestion des Developpeurs',1,'icon-layers','#gestiondesdeveloppeurs','enable','P_M_GESTION_DES_DEVELOPPEURS',2),(3,'gestiondesprojets','Gestion des Projets',2,'icon-layers','#gestiondesprojets','enable','P_M_GESTION_DES_PROJETS',3);
/*!40000 ALTER TABLE `menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `module`
--

DROP TABLE IF EXISTS `module`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `module` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `str_value` varchar(30) DEFAULT NULL,
  `str_description` varchar(200) DEFAULT NULL,
  `int_priority` int(11) DEFAULT '0',
  `str_statut` varchar(20) DEFAULT NULL,
  `p_key` varchar(100) DEFAULT NULL,
  `str_link` varchar(100) DEFAULT NULL,
  `str_icone` varchar(20) DEFAULT NULL,
  `str_icone_hover` varchar(20) DEFAULT NULL,
  `str_icone_out` varchar(20) DEFAULT NULL,
  `str_link_default` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `module`
--

LOCK TABLES `module` WRITE;
/*!40000 ALTER TABLE `module` DISABLE KEYS */;
INSERT INTO `module` VALUES (1,'Administration','Administration',0,'enable','P_MOD_ADMINISTRATION','../common/index.jsp?mod=1',NULL,NULL,NULL,'administration/welcome'),(2,'Gestionnaire','Gestionnaire',1,'enable','P_MOD_GESTIONNAIRE',NULL,NULL,NULL,NULL,'gestionnaire/welcome'),(3,'Developpeur','Developpeur',2,'enable','P_MOD_DEVELOPPEUR',NULL,NULL,NULL,NULL,'developpeurs/welcome');
/*!40000 ALTER TABLE `module` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `privilegelevel`
--

DROP TABLE IF EXISTS `privilegelevel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `privilegelevel` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `str_statut` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 AVG_ROW_LENGTH=98 PACK_KEYS=0;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `privilegelevel`
--

LOCK TABLES `privilegelevel` WRITE;
/*!40000 ALTER TABLE `privilegelevel` DISABLE KEYS */;
INSERT INTO `privilegelevel` VALUES (1,'Module','2019-06-25 14:09:46',NULL,'enable'),(2,'Menu','2019-06-25 14:10:10',NULL,'enable'),(3,'Sous Menu','2019-06-25 14:10:50',NULL,'enable'),(4,'Button','2019-06-25 14:11:18',NULL,'enable');
/*!40000 ALTER TABLE `privilegelevel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `privileges`
--

DROP TABLE IF EXISTS `privileges`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `privileges` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `description` varchar(254) DEFAULT NULL,
  `idprivilegelevel` int(11) DEFAULT NULL,
  `created_by` varchar(40) DEFAULT NULL,
  `updated_by` varchar(40) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `str_statut` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `idprivilegelevel` (`idprivilegelevel`),
  CONSTRAINT `privileges_fk1` FOREIGN KEY (`idprivilegelevel`) REFERENCES `privilegelevel` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8 AVG_ROW_LENGTH=98 PACK_KEYS=0;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `privileges`
--

LOCK TABLES `privileges` WRITE;
/*!40000 ALTER TABLE `privileges` DISABLE KEYS */;
INSERT INTO `privileges` VALUES (1,'P_MOD_ADMINISTRATION','Module Administration',1,'1',NULL,'2022-03-03 17:23:38',NULL,'enable'),(2,'P_MOD_GESTIONNAIRE','Module Gestionnaire',1,'1',NULL,'2022-03-07 15:07:30',NULL,'enable'),(3,'P_MOD_DEVELOPPEUR','Module Developpeur',1,'1',NULL,'2022-03-07 16:55:56',NULL,'enable'),(4,'P_M_GESTION_DES_DROITSUTILISATEURS','Menu Gestion des Droits Utilisateurs',2,'1',NULL,'2022-03-09 10:06:48','2022-03-16 14:50:51','enable'),(5,'P_SM_PRIVILEGES','Sous Menu Privileges',3,'1',NULL,'2022-03-09 10:12:54',NULL,'enable'),(6,'P_SM_MODULES','Sous Menu Modules',3,'1',NULL,'2022-03-09 10:13:32',NULL,'enable'),(7,'P_SM_ROLES','Sous Menu Roles',3,'1',NULL,'2022-03-09 10:14:48',NULL,'enable'),(29,'P_BTN_CREATE','Boutton Creer',4,'1',NULL,'2022-03-16 17:30:54',NULL,'enable'),(30,'P_BTN_EDIT','Boutton Editer',4,'1',NULL,'2022-03-16 17:31:56',NULL,'enable'),(31,'P_BTN_DELETE','Boutton Supprimer',4,'1',NULL,'2022-03-16 17:32:42',NULL,'enable'),(32,'P_BTN_AFFICHERPRIVILEGE','Boutton Afficher ou Ajouter un privilege',4,'1',NULL,'2022-03-18 16:52:11',NULL,'enable'),(34,'P_SM_MENUS','Sous Menu Menus',3,'2',NULL,'2022-07-29 17:11:11',NULL,'enable'),(35,'P_SM_SOUSMENUS','Sous Menu  SousMenus',3,'2',NULL,'2022-07-29 17:56:38',NULL,'enable'),(36,'P_M_GESTION_DES_DEVELOPPEURS','Menu Gestion des Developpeurs',2,'2',NULL,'2022-11-14 11:51:37',NULL,'enable'),(37,'P_SM_DEVELOPPEUR','Sous Menu Developpeur',3,'2',NULL,'2022-11-14 11:57:50',NULL,'enable'),(38,'P_M_GESTION_DES_PROJETS','Menu Gestion des Projets',2,'2',NULL,'2022-11-14 12:00:45',NULL,'enable'),(39,'P_SM_PROJETS','Sous menu Projets',3,'2',NULL,'2022-11-14 12:02:39',NULL,'enable'),(40,'P_BTN_ADD_SKILLS','Action Ajouter des Competences',4,'2',NULL,'2022-11-14 16:20:45',NULL,'enable'),(41,'P_BTN_ADD_DEVELOPER','Action Ajouter des Developpeurs',4,'2',NULL,'2022-11-15 13:08:47',NULL,'enable'),(42,'P_SM_LISTESDESPROJETS','Sous menu Liste des projets',3,'2',NULL,'2022-11-15 22:26:58',NULL,'enable'),(43,'P_SM_MESPROJETS','Sous menu mes projets',3,'2',NULL,'2022-11-16 07:18:44',NULL,'enable'),(44,'P_BTN_SENDDEMANDE','Action envoyer une demande',4,'2',NULL,'2022-11-16 07:52:09',NULL,'enable'),(45,'P_SM_LISTESDESDEMANDES','Sous menu Liste des demandes',3,'2',NULL,'2022-11-16 16:54:38',NULL,'enable'),(46,'P_BTN_VALIDED','Action de Validation',4,'2',NULL,'2022-11-16 17:10:43',NULL,'enable'),(47,'P_BTN_REJECT','Action de Rejet',4,'2',NULL,'2022-11-16 17:11:28',NULL,'enable');
/*!40000 ALTER TABLE `privileges` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `projectmember`
--

DROP TABLE IF EXISTS `projectmember`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `projectmember` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idproject` int(11) NOT NULL,
  `iddeveloper` int(11) NOT NULL,
  `created_by` varchar(40) DEFAULT NULL,
  `updated_by` varchar(40) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `str_statut` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 AVG_ROW_LENGTH=98 PACK_KEYS=0;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `projectmember`
--

LOCK TABLES `projectmember` WRITE;
/*!40000 ALTER TABLE `projectmember` DISABLE KEYS */;
INSERT INTO `projectmember` VALUES (2,4,6,'2',NULL,'2022-11-15 15:27:50',NULL,'enable'),(3,5,3,'2',NULL,'2022-11-16 11:51:03',NULL,'enable');
/*!40000 ALTER TABLE `projectmember` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `projects`
--

DROP TABLE IF EXISTS `projects`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `projects` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(75) DEFAULT NULL,
  `description` text,
  `devnumber` int(11) DEFAULT '0',
  `created_by` varchar(40) DEFAULT NULL,
  `etatdemande` tinyint(1) DEFAULT NULL,
  `updated_by` varchar(40) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `str_statut` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=latin1 PACK_KEYS=0;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `projects`
--

LOCK TABLES `projects` WRITE;
/*!40000 ALTER TABLE `projects` DISABLE KEYS */;
INSERT INTO `projects` VALUES (3,'Projet Kames','Projet Kamess',0,'2',NULL,'2','2022-11-15 13:51:16','2022-11-15 14:26:33','delete'),(4,'Lorem Project','extrait d\'un passage du Lorem Ipsum, et en étudiant tous les usages de ce mot dans la littérature classique, découvrit la source incontestable du Lorem Ipsum. ',1,'2',NULL,NULL,'2022-11-15 14:40:00',NULL,'enable'),(5,'Projet UVC','L\'Unité de Vente Consommateur (UVC) est le conditionnement élémentaire d\'un produit qui correspond à l\'unité que le consommateur peut acquérir séparément.',1,'2',NULL,NULL,'2022-11-16 11:50:50',NULL,'enable'),(6,'Projet APONH ','Le projet APONH a pour objectif de contribuer à l\'amélioration des conditions de vie des producteurs agricoles de la région de l\'Ouest camerounais.',0,'2',NULL,NULL,'2022-11-16 11:52:03',NULL,'enable');
/*!40000 ALTER TABLE `projects` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `requesttoparticipate`
--

DROP TABLE IF EXISTS `requesttoparticipate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `requesttoparticipate` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idproject` int(11) NOT NULL,
  `iddeveloper` int(11) NOT NULL,
  `str_message` varchar(500) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `str_statut` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 AVG_ROW_LENGTH=98 PACK_KEYS=0;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `requesttoparticipate`
--

LOCK TABLES `requesttoparticipate` WRITE;
/*!40000 ALTER TABLE `requesttoparticipate` DISABLE KEYS */;
INSERT INTO `requesttoparticipate` VALUES (3,6,3,'Konate Donald souhaiterait participer au developpement du projet : Projet APONH ','2022-11-16 16:24:15','2022-11-16 17:51:35','en_attente');
/*!40000 ALTER TABLE `requesttoparticipate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(65) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 AVG_ROW_LENGTH=98 PACK_KEYS=0;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'ROLE_ADMIN'),(2,'ROLE_DEVELOPER');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_privileges`
--

DROP TABLE IF EXISTS `role_privileges`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role_privileges` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idroles` int(11) NOT NULL,
  `idprivileges` int(11) NOT NULL,
  `created_by` varchar(40) DEFAULT NULL,
  `updated_by` varchar(40) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `str_statut` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `role_privileges_fk1` (`idprivileges`),
  KEY `idroles` (`idroles`),
  CONSTRAINT `role_privileges_fk1` FOREIGN KEY (`idprivileges`) REFERENCES `privileges` (`id`),
  CONSTRAINT `role_privileges_fk2` FOREIGN KEY (`idroles`) REFERENCES `role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8 AVG_ROW_LENGTH=98 PACK_KEYS=0;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_privileges`
--

LOCK TABLES `role_privileges` WRITE;
/*!40000 ALTER TABLE `role_privileges` DISABLE KEYS */;
INSERT INTO `role_privileges` VALUES (1,1,1,'1',NULL,'2022-03-03 17:27:47',NULL,'enable'),(2,1,2,'1',NULL,'2022-03-07 15:08:13',NULL,'enable'),(4,1,4,'1',NULL,'2022-03-09 10:07:18',NULL,'enable'),(5,1,5,'1',NULL,'2022-03-09 10:15:24',NULL,'enable'),(7,1,7,'1',NULL,'2022-03-09 10:16:21',NULL,'enable'),(9,1,30,'1',NULL,'2022-03-17 18:00:39',NULL,'enable'),(11,1,32,'1',NULL,'2022-03-18 16:53:24',NULL,'enable'),(13,1,29,NULL,NULL,'2022-04-15 12:43:18',NULL,'enable'),(14,1,31,NULL,NULL,'2022-04-15 12:44:08',NULL,'enable'),(15,1,6,NULL,NULL,'2022-04-15 12:44:32',NULL,'enable'),(17,1,34,NULL,NULL,'2022-07-29 17:12:05',NULL,'enable'),(18,1,35,NULL,NULL,'2022-07-29 18:05:44',NULL,'enable'),(19,1,36,NULL,NULL,'2022-11-14 11:52:19',NULL,'enable'),(20,1,37,NULL,NULL,'2022-11-14 11:58:08',NULL,'enable'),(21,2,38,NULL,NULL,'2022-11-14 12:01:00',NULL,'enable'),(22,1,39,NULL,NULL,'2022-11-14 12:02:54',NULL,'enable'),(23,1,40,NULL,NULL,'2022-11-14 16:21:37',NULL,'enable'),(24,1,41,NULL,NULL,'2022-11-15 14:11:50',NULL,'enable'),(25,2,42,NULL,NULL,'2022-11-15 22:27:33',NULL,'enable'),(26,2,3,NULL,NULL,'2022-11-15 22:37:14',NULL,'enable'),(27,2,43,NULL,NULL,'2022-11-16 07:19:23',NULL,'enable'),(28,2,44,NULL,NULL,'2022-11-16 07:52:50',NULL,'enable'),(29,1,45,NULL,NULL,'2022-11-16 16:55:07',NULL,'enable'),(30,1,46,NULL,NULL,'2022-11-16 17:12:07',NULL,'enable'),(31,1,47,NULL,NULL,'2022-11-16 17:12:29',NULL,'enable');
/*!40000 ALTER TABLE `role_privileges` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `skills`
--

DROP TABLE IF EXISTS `skills`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `skills` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(75) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `str_statut` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=16 DEFAULT CHARSET=latin1 PACK_KEYS=0;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `skills`
--

LOCK TABLES `skills` WRITE;
/*!40000 ALTER TABLE `skills` DISABLE KEYS */;
INSERT INTO `skills` VALUES (3,'HTML et CSS','HTML et CSS','2022-11-14 11:31:14',NULL,'enable'),(4,'JAVASCRIPT','JAVASCRIPT','2022-11-14 11:31:46',NULL,'enable'),(5,'REACT JS','REACT JS','2022-11-14 11:32:23',NULL,'enable'),(6,'NODE JS','NODE JS','2022-11-14 11:33:04',NULL,'enable'),(7,'JAVA','JAVA','2022-11-14 11:33:41',NULL,'enable'),(8,'ANGULAR','ANGULAR','2022-11-14 11:34:13',NULL,'enable'),(9,'PHP','PHP','2022-11-14 11:34:52',NULL,'enable'),(10,'VUE JS','VUE JS','2022-11-14 11:35:21',NULL,'enable'),(11,'C#','C#','2022-11-14 11:35:49',NULL,'enable'),(12,'NEXT JS','NEXT JS','2022-11-14 11:36:20',NULL,'enable'),(13,'SPRING BOOT','SPRING BOOT','2022-11-14 11:36:47',NULL,'enable'),(14,'PYTHON','PYTHON','2022-11-14 11:37:23',NULL,'enable'),(15,'SWIFT','SWIFT','2022-11-14 11:38:08',NULL,'enable');
/*!40000 ALTER TABLE `skills` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sous_menu`
--

DROP TABLE IF EXISTS `sous_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sous_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `str_value` varchar(30) DEFAULT NULL,
  `str_description` varchar(200) DEFAULT NULL,
  `str_composant` varchar(40) NOT NULL,
  `idmenu` varchar(20) DEFAULT NULL,
  `int_priority` int(11) DEFAULT '0',
  `str_url` varchar(50) DEFAULT NULL,
  `str_status` varchar(20) DEFAULT NULL,
  `p_key` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 AVG_ROW_LENGTH=4096 PACK_KEYS=0 ROW_FORMAT=COMPACT;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sous_menu`
--

LOCK TABLES `sous_menu` WRITE;
/*!40000 ALTER TABLE `sous_menu` DISABLE KEYS */;
INSERT INTO `sous_menu` VALUES (1,'privilege','Privileges','privilege','1',1,'privilege','enable','P_SM_PRIVILEGES'),(2,'module','Modules','modules','1',3,'modules','enable','P_SM_MODULES'),(3,'touslesroles','Roles','roles','1',2,'roles','enable','P_SM_ROLES'),(4,'menu','Menus','menus','1',4,'menus','enable','P_SM_MENUS'),(5,'Sousmenu','Sous menus','Sousmenus','1',5,'sousmenus','enable','P_SM_SOUSMENUS'),(6,'developpeur','Developpeurs','developpeur','2',1,'developpeur','enable','P_SM_DEVELOPPEUR'),(7,'projets','Projets','projets','2',2,'projets','enable','P_SM_PROJETS'),(8,'listedesprojets','Liste des Projets','listedesprojets','3',1,'listedesprojets','enable','P_SM_LISTESDESPROJETS'),(9,'mesprojets','Mes Projets','mesprojets','3',2,'mesprojets','enable','P_SM_MESPROJETS'),(10,'listedesdemandes','Liste des Demandes','listedesdemandes','2',3,'listedesdemandes','enable','P_SM_LISTESDESDEMANDES');
/*!40000 ALTER TABLE `sous_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sous_menu_actions`
--

DROP TABLE IF EXISTS `sous_menu_actions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sous_menu_actions` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idactions` int(11) NOT NULL,
  `idsousmenu` int(11) NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `str_statut` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `idactions` (`idactions`),
  KEY `idsousmenu` (`idsousmenu`),
  CONSTRAINT `sous_menu_actions_fk1` FOREIGN KEY (`idactions`) REFERENCES `actions` (`id`),
  CONSTRAINT `sous_menu_actions_fk2` FOREIGN KEY (`idsousmenu`) REFERENCES `sous_menu` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8 AVG_ROW_LENGTH=44 PACK_KEYS=0;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sous_menu_actions`
--

LOCK TABLES `sous_menu_actions` WRITE;
/*!40000 ALTER TABLE `sous_menu_actions` DISABLE KEYS */;
INSERT INTO `sous_menu_actions` VALUES (1,1,1,'2022-03-16 17:03:56',NULL,'enable'),(2,2,1,'2022-03-16 17:04:09',NULL,'enable'),(3,3,1,'2022-03-16 17:04:23',NULL,'enable'),(4,1,3,'2022-03-18 12:42:16',NULL,'enable'),(5,3,3,'2022-03-18 12:42:38',NULL,'enable'),(6,2,3,'2022-03-18 14:45:16',NULL,'enable'),(7,4,3,'2022-03-18 16:52:53',NULL,'enable'),(8,1,2,'2022-07-28 16:59:09',NULL,'enable'),(9,2,2,'2022-07-28 16:59:29',NULL,'enable'),(10,3,2,'2022-07-28 16:59:52',NULL,'enable'),(11,1,4,'2022-08-04 12:33:30',NULL,'enable'),(12,3,4,'2022-08-04 12:33:56',NULL,'enable'),(13,1,5,'2022-08-11 16:14:40',NULL,'enable'),(14,3,5,'2022-08-11 16:15:09',NULL,'enable'),(15,1,6,'2022-11-14 15:00:24',NULL,'enable'),(16,2,6,'2022-11-14 15:00:41',NULL,'enable'),(17,3,6,'2022-11-14 15:00:55',NULL,'enable'),(18,5,6,'2022-11-14 16:22:15',NULL,'enable'),(19,1,7,'2022-11-15 13:09:30',NULL,'enable'),(20,2,7,'2022-11-15 13:09:48',NULL,'enable'),(21,3,7,'2022-11-15 13:10:08',NULL,'enable'),(22,6,7,'2022-11-15 13:10:32',NULL,'enable'),(23,7,8,'2022-11-16 07:53:47',NULL,'enable'),(24,8,10,'2022-11-16 17:09:35',NULL,'enable'),(25,9,10,'2022-11-16 17:09:51',NULL,'enable');
/*!40000 ALTER TABLE `sous_menu_actions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(75) DEFAULT NULL,
  `last_name` varchar(80) DEFAULT NULL,
  `username` varchar(65) DEFAULT NULL,
  `password` varchar(64) DEFAULT NULL,
  `email` varchar(115) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `role_id` (`role_id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=latin1 PACK_KEYS=0;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (2,'admin','admin','admin','$2a$10$bpNMKeaQXKpJ4JVxOHWvu.tZdmCLT9nKcZreJ/ELfCgmTCyhC7GPy','admin@example.com',1),(3,'Konate','Donald','konatedonald','$2a$10$bpNMKeaQXKpJ4JVxOHWvu.tZdmCLT9nKcZreJ/ELfCgmTCyhC7GPy','admin@example.com',2),(4,'Alla','Franco','allafranco','$2a$10$bpNMKeaQXKpJ4JVxOHWvu.tZdmCLT9nKcZreJ/ELfCgmTCyhC7GPy','admin@example.com',2),(5,'moussa','secouta','moussasecouta','$2a$10$bpNMKeaQXKpJ4JVxOHWvu.tZdmCLT9nKcZreJ/ELfCgmTCyhC7GPy','moussasecouta@gmail.com',2),(6,'Talla','jules','tallajules','$2a$10$bpNMKeaQXKpJ4JVxOHWvu.tZdmCLT9nKcZreJ/ELfCgmTCyhC7GPy','tallajules@gmail.com',2);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'gestdeveloppeurs_db'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-11-17 11:50:41
