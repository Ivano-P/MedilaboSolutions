SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

-- Création de la base de données
CREATE DATABASE IF NOT EXISTS ms_patients_db;

-- Utilisation de la base de données
USE ms_patients_db;

-- ... (le reste de votre script reste inchangé)


-- --------------------------------------------------------

--
-- Structure de la table `patients`
--

DROP TABLE IF EXISTS `patients`;
CREATE TABLE IF NOT EXISTS `patients` (
  `id` int NOT NULL AUTO_INCREMENT,
  `prenom` varchar(50) NOT NULL,
  `nom` varchar(50) NOT NULL,
  `date_de_naissance` varchar(10) NOT NULL,
  `genre` enum('F','M') NOT NULL,
  `adresse_postale` varchar(250) DEFAULT NULL,
  `numero_de_telephone` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `patients`
--

INSERT INTO `patients` (`id`, `prenom`, `nom`, `date_de_naissance`, `genre`, `adresse_postale`, `numero_de_telephone`) VALUES
(1, 'Test', 'TestNone', '1966-12-31', 'F', '1 Brookside St', '100-222-3333'),
(2, 'Test ', 'TestBorderline', '1945-06-24', 'M', '2 High St', '200-333-4444'),
(3, 'Test', 'TestInDanger', '2004-06-18', 'M', '3 Club Road', '300-444-5555'),
(4, 'Test ', 'TestEarlyOnset', '2002-06-28', 'F', '4 Valley Dr ', '400-555-6666');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
