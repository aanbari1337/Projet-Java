-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le :  ven. 22 mai 2020 à 22:52
-- Version du serveur :  10.1.38-MariaDB
-- Version de PHP :  7.3.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `office`
--

-- --------------------------------------------------------

--
-- Structure de la table `document`
--

CREATE TABLE `document` (
  `id_doc` int(11) NOT NULL,
  `libelle` varchar(50) NOT NULL,
  `desc_doc` text NOT NULL,
  `id_proc` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `document`
--

INSERT INTO `document` (`id_doc`, `libelle`, `desc_doc`, `id_proc`) VALUES
(5, 'document1', 'document1 de la procedure1', 1),
(6, 'document1', 'document 1 de la procedure 2', 2),
(7, 'docs1', 'document', 3),
(8, 'document', 'description', 4);

-- --------------------------------------------------------

--
-- Structure de la table `employe`
--

CREATE TABLE `employe` (
  `cin` varchar(10) NOT NULL,
  `mot_passe` varchar(50) NOT NULL,
  `date_rec` date NOT NULL,
  `Grade` varchar(50) NOT NULL,
  `Nom` varchar(50) NOT NULL,
  `Prenom` varchar(50) NOT NULL,
  `date_naissance` date NOT NULL,
  `sexe` tinyint(1) NOT NULL,
  `isArchived` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `employe`
--

INSERT INTO `employe` (`cin`, `mot_passe`, `date_rec`, `Grade`, `Nom`, `Prenom`, `date_naissance`, `sexe`, `isArchived`) VALUES
('ab123456', 'mdp', '2020-03-17', 'Chef de division', 'chef', 'chef', '1995-05-23', 1, 0),
('e123456', 'mdp', '2020-05-11', 'Responsable Etape', 'emp', 'emp', '1995-05-11', 1, 0),
('em123456', 'mdp', '2020-05-11', 'Responsable Etape', 'employe', 'employe', '1999-05-12', 1, 0),
('q123456', 'mdp', '2020-04-06', 'Responsable Etape', 'benmoumen', 'el mehdi', '1997-05-21', 1, 0),
('w123456', 'mdp', '2020-04-07', 'Responsable Etape', 'anbari', 'amine', '1996-05-17', 1, 0),
('wb183467', 'mdp', '2020-04-01', 'Chef de division', 'addi', 'abdo', '1997-12-22', 1, 0),
('z123456', 'mdp', '2020-05-07', 'Responsable Etape', 'idrissi', 'anas', '2000-05-09', 1, 0);

-- --------------------------------------------------------

--
-- Structure de la table `etape`
--

CREATE TABLE `etape` (
  `id_etape` int(11) NOT NULL,
  `libelle` varchar(50) NOT NULL,
  `desc_etape` text NOT NULL,
  `cin` varchar(10) DEFAULT NULL,
  `id_proc` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `etape`
--

INSERT INTO `etape` (`id_etape`, `libelle`, `desc_etape`, `cin`, `id_proc`) VALUES
(8, 'etape1', 'etape1 de la procedure1', 'q123456', 1),
(9, 'etape2', 'etape2 de la procedure1', 'w123456', 1),
(10, 'etape3', 'etape3 de la procedure1', 'z123456', 1),
(11, 'etape1', 'etape1 de la procedure2', 'em123456', 2),
(12, 'etape2', 'etape2 de la procedure2', 'q123456', 2),
(13, 'etape3', 'etape3 de la procedure2', 'w123456', 2),
(14, 'etape1', 'descripttt', 'e123456', 3),
(15, 'etape2', 'descriptio', 'em123456', 3),
(16, 'etp', 'etape1', 'q123456', 4),
(17, 'etp2', 'description', 'z123456', 4);

-- --------------------------------------------------------

--
-- Structure de la table `procedur`
--

CREATE TABLE `procedur` (
  `id_procedure` int(11) NOT NULL,
  `libelle` varchar(50) NOT NULL,
  `desc_proc` varchar(100) NOT NULL,
  `date_creation` date NOT NULL,
  `date_modif` date NOT NULL,
  `Nbr_doc` int(11) NOT NULL,
  `nbr_etape` int(11) NOT NULL,
  `cin` varchar(10) DEFAULT NULL,
  `isArchived` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `procedur`
--

INSERT INTO `procedur` (`id_procedure`, `libelle`, `desc_proc`, `date_creation`, `date_modif`, `Nbr_doc`, `nbr_etape`, `cin`, `isArchived`) VALUES
(1, 'procedure1', 'procedure1 3 etape 1 document', '2020-05-19', '2020-05-19', 1, 3, 'wb183467', 0),
(2, 'procedure2', 'procedure2 3etape 1document', '2020-05-19', '2020-05-19', 1, 3, 'ab123456', 0),
(3, 'procedure3', 'procedure3 description', '2020-05-20', '2020-05-20', 1, 2, 'wb183467', 0),
(4, 'procedure4', 'procedure4  2 etape 1docs', '2020-05-20', '2020-05-20', 1, 2, 'ab123456', 0);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `document`
--
ALTER TABLE `document`
  ADD PRIMARY KEY (`id_doc`),
  ADD KEY `fkIdProc` (`id_proc`);

--
-- Index pour la table `employe`
--
ALTER TABLE `employe`
  ADD PRIMARY KEY (`cin`);

--
-- Index pour la table `etape`
--
ALTER TABLE `etape`
  ADD PRIMARY KEY (`id_etape`),
  ADD KEY `fkproc1` (`id_proc`),
  ADD KEY `cinfk` (`cin`);

--
-- Index pour la table `procedur`
--
ALTER TABLE `procedur`
  ADD PRIMARY KEY (`id_procedure`),
  ADD KEY `fkicin` (`cin`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `document`
--
ALTER TABLE `document`
  MODIFY `id_doc` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT pour la table `etape`
--
ALTER TABLE `etape`
  MODIFY `id_etape` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT pour la table `procedur`
--
ALTER TABLE `procedur`
  MODIFY `id_procedure` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `document`
--
ALTER TABLE `document`
  ADD CONSTRAINT `fkIdProc` FOREIGN KEY (`id_proc`) REFERENCES `procedur` (`id_procedure`) ON DELETE CASCADE;

--
-- Contraintes pour la table `etape`
--
ALTER TABLE `etape`
  ADD CONSTRAINT `cinfk` FOREIGN KEY (`cin`) REFERENCES `employe` (`cin`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `fkproc1` FOREIGN KEY (`id_proc`) REFERENCES `procedur` (`id_procedure`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `procedur`
--
ALTER TABLE `procedur`
  ADD CONSTRAINT `fkicin` FOREIGN KEY (`cin`) REFERENCES `employe` (`cin`) ON DELETE SET NULL ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
