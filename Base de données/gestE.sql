-- MySQL dump 10.16  Distrib 10.1.48-MariaDB, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: gestEhpad
-- ------------------------------------------------------
-- Server version	10.1.48-MariaDB-0+deb9u1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Admin`
--

DROP TABLE IF EXISTS `Admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Admin` (
  `idAdmin` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `idPersonnel` int(10) unsigned NOT NULL,
  PRIMARY KEY (`idAdmin`,`idPersonnel`),
  KEY `fk_Admin_Personnel1_idx` (`idPersonnel`),
  CONSTRAINT `fk_Admin_Personnel1` FOREIGN KEY (`idPersonnel`) REFERENCES `Personnel` (`idPersonnel`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Admin`
--

LOCK TABLES `Admin` WRITE;
/*!40000 ALTER TABLE `Admin` DISABLE KEYS */;
INSERT INTO `Admin` VALUES (1,21);
/*!40000 ALTER TABLE `Admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Allergie`
--

DROP TABLE IF EXISTS `Allergie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Allergie` (
  `idAllergie` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nomAllergie` varchar(45) NOT NULL,
  PRIMARY KEY (`idAllergie`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Allergie`
--

LOCK TABLES `Allergie` WRITE;
/*!40000 ALTER TABLE `Allergie` DISABLE KEYS */;
INSERT INTO `Allergie` VALUES (1,'Noix'),(2,'Crustacés'),(3,'Lactose');
/*!40000 ALTER TABLE `Allergie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Chef`
--

DROP TABLE IF EXISTS `Chef`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Chef` (
  `idChef` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `idPersonnel` int(10) unsigned NOT NULL,
  PRIMARY KEY (`idChef`,`idPersonnel`),
  KEY `fk_Chef_Personnel1_idx` (`idPersonnel`),
  CONSTRAINT `fk_Chef_Personnel1` FOREIGN KEY (`idPersonnel`) REFERENCES `Personnel` (`idPersonnel`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Chef`
--

LOCK TABLES `Chef` WRITE;
/*!40000 ALTER TABLE `Chef` DISABLE KEYS */;
INSERT INTO `Chef` VALUES (1,18),(2,7),(3,1),(4,3);
/*!40000 ALTER TABLE `Chef` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ContactUrgence`
--

DROP TABLE IF EXISTS `ContactUrgence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ContactUrgence` (
  `idContactUrgence` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nomContact` varchar(45) DEFAULT NULL,
  `numContact` varchar(45) DEFAULT NULL,
  `relationUrgence` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idContactUrgence`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ContactUrgence`
--

LOCK TABLES `ContactUrgence` WRITE;
/*!40000 ALTER TABLE `ContactUrgence` DISABLE KEYS */;
INSERT INTO `ContactUrgence` VALUES (1,'Hammett Barry','06 45 81 06 62','Fraterie'),(2,'Lilah Whitehead','06 85 27 92 12','Fraterie'),(3,'Yetta Woods','06 97 87 46 19','Petit-Enfant'),(4,'Raya Whitney','06 34 84 55 01','Petit-Enfant'),(5,'Athena Holden','06 81 46 37 65','Fraterie'),(6,'Hermione Alford','06 43 61 52 24','Enfant'),(7,'Austin Strong','06 92 12 52 24','Petit-Enfant'),(8,'Sage Wilder','06 97 48 50 50','Enfant'),(9,'Colton Arnold','06 36 14 97 44','Fraterie'),(10,'Raja Holland','06 07 71 94 23','Petit-Enfant'),(11,'Hu Elliott','06 62 19 40 65','Enfant'),(12,'Fatima Abbott','06 43 49 75 42','Enfant'),(13,'Claire Hogan','06 43 93 73 71','Fraterie'),(14,'Ashely Orr','06 30 89 33 82','Fraterie'),(15,'Buckminster Molina','06 84 64 72 68','Enfant'),(16,'Lawrence Morgan','06 44 58 82 28','Fraterie'),(17,'Kimberly Estrada','06 86 43 49 93','Enfant'),(18,'Iris Maynard','06 10 26 52 17','Enfant'),(19,'Yvette Howell','06 29 57 66 30','Enfant'),(20,'Belle Williamson','06 52 94 97 91','Petit-Enfant'),(21,'Chaney Watts','06 29 99 19 45','Fraterie'),(22,'Kelly Poole','06 74 08 59 97','Petit-Enfant'),(23,'Eleanor Shields','06 11 06 76 87','Fraterie'),(24,'Thor Hull','06 44 70 26 65','Fraterie'),(25,'Scarlet Henry','06 00 08 59 42','Petit-Enfant'),(26,'Fuller Simon','06 84 03 54 47','Enfant'),(27,'Brynn Pitts','06 23 70 81 18','Petit-Enfant'),(28,'Hillary Lyons','06 58 26 40 81','Petit-Enfant'),(29,'Barbara Daugherty','06 38 68 04 52','Petit-Enfant'),(30,'Brent Newton','06 93 38 27 61','Petit-Enfant'),(31,'Stacey Guerrero','06 45 58 38 90','Petit-Enfant'),(32,'Kenyon Lindsey','06 83 62 71 53','Petit-Enfant'),(33,'Mechelle Pierce','06 79 19 09 30','Enfant'),(34,'Maya Perez','06 09 70 92 43','Petit-Enfant'),(35,'Ciaran Cochran','06 69 31 48 74','Enfant'),(36,'Vance Galloway','06 90 10 59 74','Fraterie'),(37,'Rose Castro','06 42 12 71 92','Enfant'),(38,'Stewart Bridges','06 64 14 27 13','Enfant'),(39,'Kuame Acosta','06 24 35 77 86','Enfant'),(40,'Hayden Vaughan','06 56 38 39 40','Enfant'),(41,'Garrison Hutchinson','06 51 25 94 44','Enfant'),(42,'Amery Irwin','06 20 94 48 82','Fraterie'),(43,'Briar Finley','06 91 98 70 77','Enfant'),(44,'Prescott Dotson','06 86 86 94 71','Enfant'),(45,'Shellie Wilkins','06 30 75 65 46','Fraterie'),(46,'Ronan Fields','06 89 13 24 91','Fraterie'),(47,'Harper Pope','06 12 76 05 64','Petit-Enfant'),(48,'Maia Booth','06 04 31 27 81','Enfant'),(49,'Felix Kinney','06 49 59 88 72','Enfant'),(50,'Murphy Carpenter','06 31 49 50 84','Fraterie');
/*!40000 ALTER TABLE `ContactUrgence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Directeur`
--

DROP TABLE IF EXISTS `Directeur`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Directeur` (
  `idDirecteur` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `idPersonnel` int(10) unsigned NOT NULL,
  PRIMARY KEY (`idDirecteur`,`idPersonnel`),
  KEY `fk_Directeur_Personnel1_idx` (`idPersonnel`),
  CONSTRAINT `fk_Directeur_Personnel1` FOREIGN KEY (`idPersonnel`) REFERENCES `Personnel` (`idPersonnel`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Directeur`
--

LOCK TABLES `Directeur` WRITE;
/*!40000 ALTER TABLE `Directeur` DISABLE KEYS */;
INSERT INTO `Directeur` VALUES (1,10);
/*!40000 ALTER TABLE `Directeur` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `DossierMedical`
--

DROP TABLE IF EXISTS `DossierMedical`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `DossierMedical` (
  `idDossierMedical` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `idRegime` int(10) unsigned NOT NULL,
  `idResident` int(10) unsigned NOT NULL,
  PRIMARY KEY (`idDossierMedical`),
  KEY `fk_DossierMedical_Resident1_idx` (`idResident`),
  KEY `fk_regime_idx` (`idRegime`),
  CONSTRAINT `fk_DossierMedical_Resident1` FOREIGN KEY (`idResident`) REFERENCES `Resident` (`idResident`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_regime` FOREIGN KEY (`idRegime`) REFERENCES `Regime` (`idRegime`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `DossierMedical`
--

LOCK TABLES `DossierMedical` WRITE;
/*!40000 ALTER TABLE `DossierMedical` DISABLE KEYS */;
INSERT INTO `DossierMedical` VALUES (1,1,4),(2,2,5),(3,3,6);
/*!40000 ALTER TABLE `DossierMedical` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Employe`
--

DROP TABLE IF EXISTS `Employe`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Employe` (
  `idEmploye` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `idPersonnel` int(10) unsigned NOT NULL,
  `idEquipe` int(10) unsigned NOT NULL,
  PRIMARY KEY (`idEmploye`,`idPersonnel`),
  KEY `fk_Employe_Personnel1_idx` (`idPersonnel`),
  KEY `fk_Employe_Equipe1_idx` (`idEquipe`),
  CONSTRAINT `fk_Employe_Equipe1` FOREIGN KEY (`idEquipe`) REFERENCES `Equipe` (`idEquipe`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Employe_Personnel1` FOREIGN KEY (`idPersonnel`) REFERENCES `Personnel` (`idPersonnel`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Employe`
--

LOCK TABLES `Employe` WRITE;
/*!40000 ALTER TABLE `Employe` DISABLE KEYS */;
INSERT INTO `Employe` VALUES (1,2,4),(2,6,4),(4,15,5),(5,17,5),(7,4,6),(8,19,6),(9,11,6),(10,13,6),(11,14,6),(12,5,7),(13,16,7),(14,9,8),(15,12,8),(3,8,9),(6,20,9);
/*!40000 ALTER TABLE `Employe` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Equipe`
--

DROP TABLE IF EXISTS `Equipe`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Equipe` (
  `idEquipe` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `horaireEquipe` varchar(45) NOT NULL,
  `idChef` int(10) unsigned NOT NULL,
  `idPersonnel` int(10) unsigned NOT NULL,
  PRIMARY KEY (`idEquipe`),
  KEY `fk_Chef1_idx` (`idChef`,`idPersonnel`),
  CONSTRAINT `fk_Chef1` FOREIGN KEY (`idChef`, `idPersonnel`) REFERENCES `Chef` (`idChef`, `idPersonnel`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Equipe`
--

LOCK TABLES `Equipe` WRITE;
/*!40000 ALTER TABLE `Equipe` DISABLE KEYS */;
INSERT INTO `Equipe` VALUES (4,'Jour',1,18),(5,'Jour',3,1),(6,'Matin',2,7),(7,'Soir',2,7),(8,'Nuit',2,7),(9,'Jour',4,3);
/*!40000 ALTER TABLE `Equipe` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Etablissement`
--

DROP TABLE IF EXISTS `Etablissement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Etablissement` (
  `idEtablissement` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nomEtablissement` varchar(45) NOT NULL,
  PRIMARY KEY (`idEtablissement`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Etablissement`
--

LOCK TABLES `Etablissement` WRITE;
/*!40000 ALTER TABLE `Etablissement` DISABLE KEYS */;
INSERT INTO `Etablissement` VALUES (1,'Les Jardins des Acacias'),(2,'Les Tulipes Bleus');
/*!40000 ALTER TABLE `Etablissement` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Evenement`
--

DROP TABLE IF EXISTS `Evenement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Evenement` (
  `idEvenement` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `graviteEvent` varchar(45) NOT NULL,
  `dateEmission` datetime NOT NULL,
  `titreEvent` varchar(45) NOT NULL,
  `descriptionEvent` varchar(500) NOT NULL,
  `idRegistre` int(10) unsigned NOT NULL,
  `idPersonnel` int(10) unsigned NOT NULL,
  PRIMARY KEY (`idEvenement`),
  KEY `fk_Evenement_Registre1_idx` (`idRegistre`),
  KEY `fk_Evenement_Personnel1_idx` (`idPersonnel`),
  CONSTRAINT `fk_Evenement_Personnel1` FOREIGN KEY (`idPersonnel`) REFERENCES `Personnel` (`idPersonnel`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Evenement_Registre1` FOREIGN KEY (`idRegistre`) REFERENCES `Registre` (`idRegistre`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Evenement`
--

LOCK TABLES `Evenement` WRITE;
/*!40000 ALTER TABLE `Evenement` DISABLE KEYS */;
INSERT INTO `Evenement` VALUES (1,'3','2021-02-15 00:00:00','robinet 2eme étage','Bah le robinet quoi!',5,2),(2,'2','2021-02-12 00:00:00','Chute résident','Chute Os cassé',1,4);
/*!40000 ALTER TABLE `Evenement` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ListResidentEvent`
--

DROP TABLE IF EXISTS `ListResidentEvent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ListResidentEvent` (
  `idResident` int(10) unsigned NOT NULL,
  `idEvent` int(10) unsigned NOT NULL,
  PRIMARY KEY (`idResident`,`idEvent`),
  KEY `fk_event_idx` (`idEvent`),
  CONSTRAINT `fk_event` FOREIGN KEY (`idEvent`) REFERENCES `Evenement` (`idEvenement`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_resident` FOREIGN KEY (`idResident`) REFERENCES `Resident` (`idResident`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ListResidentEvent`
--

LOCK TABLES `ListResidentEvent` WRITE;
/*!40000 ALTER TABLE `ListResidentEvent` DISABLE KEYS */;
INSERT INTO `ListResidentEvent` VALUES (5,2);
/*!40000 ALTER TABLE `ListResidentEvent` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ListeAllergie`
--

DROP TABLE IF EXISTS `ListeAllergie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ListeAllergie` (
  `idDossierMedical` int(10) unsigned NOT NULL,
  `idAllergie` int(10) unsigned NOT NULL,
  PRIMARY KEY (`idDossierMedical`,`idAllergie`),
  KEY `fk_DossierMedical_has_Allergie_DossierMedical1_idx` (`idDossierMedical`),
  KEY `fk_allergie_idx` (`idAllergie`),
  CONSTRAINT `fk_DossierMedical_has_Allergie_DossierMedical1` FOREIGN KEY (`idDossierMedical`) REFERENCES `DossierMedical` (`idDossierMedical`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_allergie` FOREIGN KEY (`idAllergie`) REFERENCES `Allergie` (`idAllergie`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ListeAllergie`
--

LOCK TABLES `ListeAllergie` WRITE;
/*!40000 ALTER TABLE `ListeAllergie` DISABLE KEYS */;
INSERT INTO `ListeAllergie` VALUES (1,2),(3,3);
/*!40000 ALTER TABLE `ListeAllergie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ListePathologie`
--

DROP TABLE IF EXISTS `ListePathologie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ListePathologie` (
  `idPathologie` int(10) unsigned NOT NULL,
  `idDossierMedical` int(10) unsigned NOT NULL,
  PRIMARY KEY (`idPathologie`,`idDossierMedical`),
  KEY `fk_Pathologie_has_DossierMedical_DossierMedical1_idx` (`idDossierMedical`),
  KEY `fk_Pathologie_has_DossierMedical_Pathologie1_idx` (`idPathologie`),
  CONSTRAINT `fk_Pathologie_has_DossierMedical_DossierMedical1` FOREIGN KEY (`idDossierMedical`) REFERENCES `DossierMedical` (`idDossierMedical`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Pathologie_has_DossierMedical_Pathologie1` FOREIGN KEY (`idPathologie`) REFERENCES `Pathologie` (`idPathologie`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ListePathologie`
--

LOCK TABLES `ListePathologie` WRITE;
/*!40000 ALTER TABLE `ListePathologie` DISABLE KEYS */;
INSERT INTO `ListePathologie` VALUES (2,1),(2,2),(2,3),(3,2),(3,3),(4,3);
/*!40000 ALTER TABLE `ListePathologie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ListeReunionChef`
--

DROP TABLE IF EXISTS `ListeReunionChef`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ListeReunionChef` (
  `idReunion` int(10) unsigned NOT NULL,
  `idChef` int(10) unsigned NOT NULL,
  `idPersonnel` int(10) unsigned NOT NULL,
  PRIMARY KEY (`idReunion`,`idChef`,`idPersonnel`),
  KEY `fk_Reunion_has_Chef_Chef1_idx` (`idChef`,`idPersonnel`),
  KEY `fk_Reunion_has_Chef_Reunion1_idx` (`idReunion`),
  CONSTRAINT `fk_Reunion_has_Chef_Chef1` FOREIGN KEY (`idChef`, `idPersonnel`) REFERENCES `Chef` (`idChef`, `idPersonnel`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Reunion_has_Chef_Reunion1` FOREIGN KEY (`idReunion`) REFERENCES `Reunion` (`idReunion`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ListeReunionChef`
--

LOCK TABLES `ListeReunionChef` WRITE;
/*!40000 ALTER TABLE `ListeReunionChef` DISABLE KEYS */;
INSERT INTO `ListeReunionChef` VALUES (1,1,18);
/*!40000 ALTER TABLE `ListeReunionChef` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ListeReunionDirecteur`
--

DROP TABLE IF EXISTS `ListeReunionDirecteur`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ListeReunionDirecteur` (
  `idReunion` int(10) unsigned NOT NULL,
  `idDirecteur` int(10) unsigned NOT NULL,
  `idPersonnel` int(10) unsigned NOT NULL,
  PRIMARY KEY (`idDirecteur`,`idPersonnel`,`idReunion`),
  KEY `fk_Reunion_has_Directeur_Directeur1_idx` (`idDirecteur`,`idPersonnel`),
  KEY `fk_Reunion_has_Directeur_Reunion1_idx` (`idReunion`),
  CONSTRAINT `fk_Reunion_has_Directeur_Directeur1` FOREIGN KEY (`idDirecteur`, `idPersonnel`) REFERENCES `Directeur` (`idDirecteur`, `idPersonnel`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Reunion_has_Directeur_Reunion1` FOREIGN KEY (`idReunion`) REFERENCES `Reunion` (`idReunion`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='	';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ListeReunionDirecteur`
--

LOCK TABLES `ListeReunionDirecteur` WRITE;
/*!40000 ALTER TABLE `ListeReunionDirecteur` DISABLE KEYS */;
INSERT INTO `ListeReunionDirecteur` VALUES (1,1,10);
/*!40000 ALTER TABLE `ListeReunionDirecteur` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Pathologie`
--

DROP TABLE IF EXISTS `Pathologie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Pathologie` (
  `idPathologie` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nomPathologie` varchar(45) NOT NULL,
  PRIMARY KEY (`idPathologie`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Pathologie`
--

LOCK TABLES `Pathologie` WRITE;
/*!40000 ALTER TABLE `Pathologie` DISABLE KEYS */;
INSERT INTO `Pathologie` VALUES (2,'Alzheimer'),(3,'Diabète'),(4,'Colestérol');
/*!40000 ALTER TABLE `Pathologie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Personnel`
--

DROP TABLE IF EXISTS `Personnel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Personnel` (
  `idPersonnel` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nomPersonnel` varchar(45) NOT NULL,
  `prenomPersonnel` varchar(45) NOT NULL,
  `identifiant` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `intitulePoste` varchar(45) NOT NULL,
  `idEtablissement` int(10) unsigned NOT NULL,
  `numTelPoste` int(10) unsigned NOT NULL,
  `idService` int(10) unsigned NOT NULL,
  PRIMARY KEY (`idPersonnel`),
  KEY `fk_idEtab_idx` (`idEtablissement`),
  KEY `fk_service_idx` (`idService`),
  CONSTRAINT `fk_idEtab` FOREIGN KEY (`idEtablissement`) REFERENCES `Etablissement` (`idEtablissement`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_service` FOREIGN KEY (`idService`) REFERENCES `Service` (`idService`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Personnel`
--

LOCK TABLES `Personnel` WRITE;
/*!40000 ALTER TABLE `Personnel` DISABLE KEYS */;
INSERT INTO `Personnel` VALUES (1,'Torres','Maya','TorresM','Torres','Technicien',1,1000,3),(2,'Jordan','Alvin','JordanA','Jordan','Cuisinier',1,1001,2),(3,'Ratliff','Xander','RatliffX','Ratliff','Chef Lingère',1,1002,4),(4,'Baird','Adam','BairdA','Baird','ASH',1,1003,1),(5,'Branch','Melyssa','BranchM','Branch','ASH',1,1004,1),(6,'Hatfield','MacKensie','HatfieldM','Hatfield','Cuisinier',1,1005,2),(7,'Ball','Pearl','BallP','Ball','Medecin',1,1006,1),(8,'Tran','Abraham','TranA','Tran','Lingère',1,1007,4),(9,'Frank','Christian','FrankC','Frank','AS',1,1008,1),(10,'Atkinson','Zelda','AtkinsonZ','Atkinson','Directeur',1,1009,7),(11,'Mckay','Neil','MckayN','Mckay','AVS',1,1010,1),(12,'Elliott','Aquila','ElliottA','Elliott','AS',1,1011,1),(13,'Maxwell','Nash','MaxwellN','Maxwell','AS',1,1012,1),(14,'Gay','Faith','GayF','Gay','AS',1,1013,1),(15,'Barber','Haley','BarberH','Barber','Technicien',1,1014,3),(16,'Foreman','Whilemina','ForemanW','Foreman','AVS',1,1015,1),(17,'Roach','Miranda','RoachM','Roach','Technicien',1,1016,3),(18,'Stanley','Courtney','StanleyC','Stanley','Chef Cuisinier',1,1017,2),(19,'Ramos','Aimee','RamosA','Ramos','ASH',1,1018,1),(20,'Chen','Camden','ChenC','Chen','Lingère',1,1019,4),(21,'Torres','Maya','Admin','1234','ADMIN',1,1000,6);
/*!40000 ALTER TABLE `Personnel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Regime`
--

DROP TABLE IF EXISTS `Regime`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Regime` (
  `idRegime` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nomRegime` varchar(45) NOT NULL,
  PRIMARY KEY (`idRegime`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Regime`
--

LOCK TABLES `Regime` WRITE;
/*!40000 ALTER TABLE `Regime` DISABLE KEYS */;
INSERT INTO `Regime` VALUES (1,'Normal'),(2,'Mixé'),(3,'Haché');
/*!40000 ALTER TABLE `Regime` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Registre`
--

DROP TABLE IF EXISTS `Registre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Registre` (
  `idRegistre` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `idService` int(10) unsigned NOT NULL,
  `idEtablissement` int(10) unsigned NOT NULL,
  PRIMARY KEY (`idRegistre`),
  KEY `fk_Registre_Service1_idx` (`idService`),
  KEY `fk_idEtabReg_idx` (`idEtablissement`),
  CONSTRAINT `fk_Registre_Service1` FOREIGN KEY (`idService`) REFERENCES `Service` (`idService`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_idEtabReg` FOREIGN KEY (`idEtablissement`) REFERENCES `Etablissement` (`idEtablissement`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Registre`
--

LOCK TABLES `Registre` WRITE;
/*!40000 ALTER TABLE `Registre` DISABLE KEYS */;
INSERT INTO `Registre` VALUES (1,1,1),(2,2,1),(3,3,1),(4,4,1),(5,5,1),(6,6,1);
/*!40000 ALTER TABLE `Registre` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Resident`
--

DROP TABLE IF EXISTS `Resident`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Resident` (
  `idResident` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nomResident` varchar(45) NOT NULL,
  `prenomResident` varchar(45) NOT NULL,
  `naissanceResident` date DEFAULT NULL,
  `idEtablissement` int(10) unsigned NOT NULL,
  `numSecuResident` varchar(15) NOT NULL,
  `numChambreResident` int(11) NOT NULL,
  `idContactUrgence` int(10) unsigned NOT NULL,
  PRIMARY KEY (`idResident`),
  KEY `fk_Resident_Etablissement1_idx` (`idEtablissement`),
  KEY `fk_Resident_ContactUrgence1_idx` (`idContactUrgence`),
  CONSTRAINT `fk_Resident_ContactUrgence1` FOREIGN KEY (`idContactUrgence`) REFERENCES `ContactUrgence` (`idContactUrgence`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Resident_Etablissement1` FOREIGN KEY (`idEtablissement`) REFERENCES `Etablissement` (`idEtablissement`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Resident`
--

LOCK TABLES `Resident` WRITE;
/*!40000 ALTER TABLE `Resident` DISABLE KEYS */;
INSERT INTO `Resident` VALUES (1,'Copeland','Seth','1944-12-28',1,'3 82 05 64 062 ',100,1),(2,'Suarez','Eve','1924-11-14',1,'4 57 94 29 511 ',101,2),(3,'Frederick','Leo','1921-02-02',1,'4 87 05 45 979 ',102,3),(4,'Moran','Lani','1933-07-17',1,'5 91 22 21 315 ',103,4),(5,'Mccarty','Iola','1932-10-28',1,'8 56 03 59 395 ',104,5),(6,'Chase','Penelope','1925-07-15',1,'4 01 06 82 589 ',105,6),(7,'Harrison','Candice','1939-03-28',1,'5 83 15 84 057 ',106,7),(8,'Benson','Rogan','1927-06-20',1,'9 25 31 28 782 ',107,8),(9,'Sutton','Yetta','1942-01-12',1,'1 10 74 07 952 ',108,9),(10,'Mueller','Wynter','1937-11-02',1,'6 01 78 37 408 ',109,10),(11,'Weaver','Beau','1928-11-17',1,'7 91 45 38 710 ',110,11),(12,'Decker','Sylvester','1941-02-07',1,'8 34 15 83 923 ',111,12),(13,'Gaines','Leilani','1936-12-20',1,'1 76 77 42 066 ',112,13),(14,'Rosales','Denton','1941-06-14',1,'9 49 28 00 118 ',113,14),(15,'Maynard','Erich','1924-09-25',1,'4 16 07 84 337 ',114,15),(16,'Huff','Quynn','1931-07-18',1,'3 56 15 21 823 ',115,16),(17,'Coleman','Cleo','1947-07-07',1,'6 54 63 20 464 ',116,17),(18,'Hewitt','Giselle','1938-02-05',1,'7 91 43 70 935 ',117,18),(19,'Glenn','Wylie','1928-07-31',1,'8 11 33 92 243 ',118,19),(20,'Crane','April','1944-03-27',1,'6 02 78 50 729 ',119,20),(21,'Huber','Illana','1944-09-28',1,'8 55 75 34 570 ',120,21),(22,'Patrick','Jasper','1944-11-09',1,'4 15 87 85 877 ',121,22),(23,'Gamble','Maggie','1925-09-22',1,'6 97 71 23 522 ',122,23),(24,'Wade','Daquan','1919-09-30',1,'2 67 30 07 651 ',123,24),(25,'Terry','Calvin','1919-04-18',1,'7 91 66 47 347 ',124,25),(26,'Sutton','Janna','1933-01-10',1,'3 51 11 09 990 ',125,26),(27,'Douglas','Lance','1949-10-27',1,'9 26 55 40 858 ',126,27),(28,'Blackwell','Brent','1928-07-07',1,'7 35 77 25 824 ',127,28),(29,'Park','Hop','1949-03-06',1,'4 35 50 81 655 ',128,29),(30,'Mathis','Tamara','1937-11-24',1,'0 49 60 65 319 ',129,30),(31,'Frye','Lara','1937-06-06',1,'9 57 17 24 985 ',130,31),(32,'Terrell','Ronan','1944-07-24',1,'5 75 20 02 754 ',131,32),(33,'Barrett','India','1943-02-14',1,'6 41 81 77 381 ',132,33),(34,'Hoffman','Mechelle','1938-02-28',1,'9 68 26 61 689 ',133,34),(35,'Curtis','Lani','1930-12-27',1,'1 71 68 32 093 ',134,35),(36,'Jensen','Jerry','1920-10-29',1,'8 38 83 60 434 ',135,36),(37,'Kane','Nash','1920-08-15',1,'1 10 56 49 533 ',136,37),(38,'Pena','Nissim','1938-08-25',1,'1 97 31 33 047 ',137,38),(39,'Hamilton','Hamish','1945-07-03',1,'4 59 31 34 633 ',138,39),(40,'Nixon','Dolan','1942-06-18',1,'4 54 72 26 005 ',139,40),(41,'Dale','Len','1948-11-22',1,'6 77 31 51 771 ',140,41),(42,'Bates','Neil','1937-09-30',1,'4 36 87 03 193 ',141,42),(43,'Lott','Isaiah','1929-08-26',1,'0 89 47 20 792 ',142,43),(44,'Sanders','Berk','1933-10-18',1,'2 84 95 67 742 ',143,44),(45,'Graves','Anika','1925-07-18',1,'2 29 72 19 880 ',144,45),(46,'Ballard','Jocelyn','1935-08-12',1,'8 50 58 39 153 ',145,46),(47,'Kemp','Kieran','1949-04-06',1,'3 17 63 08 899 ',146,47),(48,'Frye','Jaime','1944-01-04',1,'7 71 15 27 389 ',147,48),(49,'Bauer','Thor','1949-05-03',1,'0 52 36 22 353 ',148,49),(50,'Heath','Hedy','1928-07-11',1,'6 01 91 51 146 ',149,50);
/*!40000 ALTER TABLE `Resident` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Reunion`
--

DROP TABLE IF EXISTS `Reunion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Reunion` (
  `idReunion` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `dateReunion` datetime NOT NULL,
  `titreReunion` varchar(45) NOT NULL,
  PRIMARY KEY (`idReunion`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Reunion`
--

LOCK TABLES `Reunion` WRITE;
/*!40000 ALTER TABLE `Reunion` DISABLE KEYS */;
INSERT INTO `Reunion` VALUES (1,'2021-02-23 13:47:00','rutrum'),(2,'2021-12-02 15:25:00','Vivamus nibh dolor,'),(3,'2021-03-30 07:11:00','sollicitudin orci'),(4,'2021-11-02 04:08:00','urna suscipit nonummy.'),(5,'2021-03-20 07:27:00','a felis ullamcorper viverra.'),(6,'2021-10-15 04:25:00','ante bibendum'),(7,'2021-01-03 10:59:00','eget'),(8,'2021-04-19 02:00:00','et, rutrum eu, ultrices'),(9,'2021-09-03 14:53:00','iaculis odio.'),(10,'2021-12-08 15:47:00','Nulla tincidunt,'),(11,'2021-06-20 14:44:00','sem ut cursus'),(12,'2021-12-18 18:47:00','quam a felis'),(13,'2020-12-18 10:59:00','quis diam luctus'),(14,'2021-09-06 07:00:00','interdum. Nunc sollicitudin commodo ipsum.'),(15,'2020-11-23 17:44:00','sit amet ante. Vivamus'),(16,'2021-10-08 20:11:00','non, cursus non, egestas'),(17,'2020-11-07 22:31:00','natoque penatibus et'),(18,'2021-09-26 06:22:00','vulputate'),(19,'2021-12-05 18:53:00','sem. Pellentesque ut'),(20,'2022-01-23 02:44:00','vulputate dui, nec');
/*!40000 ALTER TABLE `Reunion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Service`
--

DROP TABLE IF EXISTS `Service`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Service` (
  `idService` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nomService` varchar(45) NOT NULL,
  `idEtablissement` int(10) unsigned NOT NULL,
  PRIMARY KEY (`idService`),
  KEY `fk_Service_Etablissement1_idx` (`idEtablissement`),
  CONSTRAINT `fk_Service_Etablissement1` FOREIGN KEY (`idEtablissement`) REFERENCES `Etablissement` (`idEtablissement`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Service`
--

LOCK TABLES `Service` WRITE;
/*!40000 ALTER TABLE `Service` DISABLE KEYS */;
INSERT INTO `Service` VALUES (1,'Medical',1),(2,'Cuisine',1),(3,'Tech',1),(4,'Lingerie',1),(5,'Global',1),(6,'Admin',1),(7,'Direction',1);
/*!40000 ALTER TABLE `Service` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Visite`
--

DROP TABLE IF EXISTS `Visite`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Visite` (
  `idVisite` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `dateVisite` datetime NOT NULL,
  `idDirecteur` int(10) unsigned NOT NULL,
  `idPersonnel` int(10) unsigned NOT NULL,
  PRIMARY KEY (`idVisite`),
  KEY `fk_Visite_Directeur1_idx` (`idDirecteur`,`idPersonnel`),
  CONSTRAINT `fk_Visite_Directeur1` FOREIGN KEY (`idDirecteur`, `idPersonnel`) REFERENCES `Directeur` (`idDirecteur`, `idPersonnel`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Visite`
--

LOCK TABLES `Visite` WRITE;
/*!40000 ALTER TABLE `Visite` DISABLE KEYS */;
INSERT INTO `Visite` VALUES (1,'2021-12-30 06:32:00',1,10),(2,'2021-05-26 05:37:00',1,10),(3,'2021-09-07 03:53:00',1,10),(4,'2021-06-30 14:04:00',1,10),(5,'2021-02-04 10:56:00',1,10),(6,'2021-01-19 08:49:00',1,10),(7,'2021-02-23 22:28:00',1,10),(8,'2021-11-06 10:20:00',1,10),(9,'2021-06-22 14:00:00',1,10),(10,'2020-11-24 11:46:00',1,10);
/*!40000 ALTER TABLE `Visite` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-02-26  9:16:00
