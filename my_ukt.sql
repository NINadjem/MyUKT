-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le :  sam. 19 mai 2018 à 13:35
-- Version du serveur :  10.1.28-MariaDB
-- Version de PHP :  7.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `my_ukt`
--

-- --------------------------------------------------------

--
-- Structure de la table `aministrator`
--

CREATE TABLE `aministrator` (
  `id_user` bigint(20) NOT NULL,
  `id_work` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `aministrator`
--

INSERT INTO `aministrator` (`id_user`, `id_work`) VALUES
(28, 2),
(27, 3),
(29, 8),
(26, 9);

-- --------------------------------------------------------

--
-- Structure de la table `change_of_session`
--

CREATE TABLE `change_of_session` (
  `id` bigint(20) NOT NULL,
  `id_old_session` int(30) NOT NULL,
  `id_new_session` int(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `change_of_session`
--

INSERT INTO `change_of_session` (`id`, `id_old_session`, `id_new_session`) VALUES
(21, 2, 31);

-- --------------------------------------------------------

--
-- Structure de la table `classroom`
--

CREATE TABLE `classroom` (
  `id` int(11) NOT NULL,
  `name` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `classroom`
--

INSERT INTO `classroom` (`id`, `name`) VALUES
(38, 'Amphi A'),
(39, 'Amphi B'),
(40, 'Amphi C'),
(41, 'LABO 1'),
(42, 'LABO 2'),
(43, 'LABO 3'),
(44, 'LABO 4'),
(23, 'Salle 1'),
(32, 'salle 10'),
(33, 'Salle 11'),
(34, 'Salle 12'),
(35, 'Salle 13'),
(36, 'Salle 14'),
(24, 'Salle 2'),
(25, 'Salle 3'),
(26, 'Salle 4'),
(27, 'Salle 5'),
(28, 'Salle 6'),
(29, 'Salle 7'),
(30, 'Salle 8'),
(31, 'Salle 9'),
(37, 'Salle de lecture');

-- --------------------------------------------------------

--
-- Structure de la table `consultation`
--

CREATE TABLE `consultation` (
  `id` bigint(20) NOT NULL,
  `id_sesson` int(11) NOT NULL,
  `date` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `consultation`
--

INSERT INTO `consultation` (`id`, `id_sesson`, `date`) VALUES
(33, 15, '2018-05-18 10:00:00');

-- --------------------------------------------------------

--
-- Structure de la table `days`
--

CREATE TABLE `days` (
  `id` int(3) NOT NULL,
  `name` varchar(15) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `days`
--

INSERT INTO `days` (`id`, `name`) VALUES
(2, 'Dimanche'),
(6, 'Jeudi'),
(3, 'Lundi'),
(4, 'Mardi'),
(5, 'Mercredi'),
(1, 'Samedi'),
(7, 'Vendredi');

-- --------------------------------------------------------

--
-- Structure de la table `delege`
--

CREATE TABLE `delege` (
  `id` int(11) NOT NULL,
  `id_delege` bigint(20) NOT NULL,
  `id_level` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `delege`
--

INSERT INTO `delege` (`id`, `id_delege`, `id_level`) VALUES
(1, 9, 3),
(2, 33, 4);

-- --------------------------------------------------------

--
-- Structure de la table `document_request`
--

CREATE TABLE `document_request` (
  `id` bigint(20) NOT NULL,
  `id_user` bigint(20) NOT NULL,
  `doc` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `reason` text COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `document_request`
--

INSERT INTO `document_request` (`id`, `id_user`, `doc`, `reason`) VALUES
(1, 1, 'Attestation de classement', 'J ai besion de ce document pour :\r\nPour Participer au concours d acces a l ecole nationale superieur d informatique a Alger'),
(2, 1, 'Attestation de scolarité', 'Je souhaite avoir mon attestation de scolarite pour renouvller le dossier de bourse'),
(3, 1, 'Releve de note ', 'Pour renouveller le dossier d inscription'),
(7, 1, 'copie de l attestation de transfert ', 'Je vous demande de bien vouloir me faire une copie de l attestation de transfert que j ai fait en septembre 2015 pour le dossier de diplome licence ....\nje vous remercie. '),
(9, 4, 'attestation de classement ', 'acceder au concour'),
(10, 1, 'Le relever de note', 'je vous demande de bien vouloir  m extraire le releve de note pour le dossier de diplome licence'),
(11, 1, 'Le relever de note', 'je vous demande de bien vouloir  m extraire le relevÃ© de note pour le dossier de diplome licence');

-- --------------------------------------------------------

--
-- Structure de la table `encadreur`
--

CREATE TABLE `encadreur` (
  `id` bigint(20) NOT NULL,
  `id_encadreur` bigint(20) NOT NULL,
  `id_student` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `encadreur`
--

INSERT INTO `encadreur` (`id`, `id_encadreur`, `id_student`) VALUES
(5, 16, 1),
(6, 16, 12),
(9, 36, 2),
(10, 36, 7),
(14, 37, 9),
(12, 37, 10),
(13, 37, 11),
(11, 38, 8),
(7, 39, 4),
(8, 39, 5);

-- --------------------------------------------------------

--
-- Structure de la table `establishment`
--

CREATE TABLE `establishment` (
  `id` int(11) NOT NULL,
  `name` varchar(30) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `establishment`
--

INSERT INTO `establishment` (`id`, `name`) VALUES
(1, 'Bibliotheque Universitaire'),
(2, 'Scolarite'),
(4, 'Cite Universitaire'),
(5, 'La Fac Centrale');

-- --------------------------------------------------------

--
-- Structure de la table `exam`
--

CREATE TABLE `exam` (
  `id_exam_type` int(11) NOT NULL,
  `id` bigint(20) NOT NULL,
  `id_groupe` int(11) NOT NULL,
  `id_module` int(11) NOT NULL,
  `id_classroom` int(11) NOT NULL,
  `begin` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `exam`
--

INSERT INTO `exam` (`id_exam_type`, `id`, `id_groupe`, `id_module`, `id_classroom`, `begin`) VALUES
(2, 10, 17, 22, 38, '2018-01-18 11:00:00'),
(5, 13, 17, 33, 36, '2018-04-15 14:00:00'),
(5, 24, 17, 31, 36, '2018-04-25 09:30:00'),
(5, 25, 17, 34, 29, '2018-04-24 09:00:00');

-- --------------------------------------------------------

--
-- Structure de la table `exam_type`
--

CREATE TABLE `exam_type` (
  `type` varchar(30) COLLATE utf8mb4_unicode_ci NOT NULL,
  `id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `exam_type`
--

INSERT INTO `exam_type` (`type`, `id`) VALUES
('Controle examen', 1),
('Examain', 2),
('Controle TP', 4),
('Controle TD', 5);

-- --------------------------------------------------------

--
-- Structure de la table `groupe`
--

CREATE TABLE `groupe` (
  `id` int(20) NOT NULL,
  `id_specialty` int(11) NOT NULL,
  `id_level` int(11) NOT NULL,
  `name` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `groupe`
--

INSERT INTO `groupe` (`id`, `id_specialty`, `id_level`, `name`) VALUES
(19, 1, 4, 'GL'),
(18, 1, 5, 'GL'),
(20, 2, 4, 'GI'),
(21, 2, 5, 'GI'),
(22, 3, 4, 'RT'),
(23, 3, 5, 'RT'),
(7, 6, 1, 'Groupe 1'),
(8, 6, 1, 'Groupe 2'),
(9, 6, 1, 'Groupe 3'),
(10, 6, 1, 'Groupe 4'),
(12, 6, 2, '2'),
(11, 6, 2, 'Groupe 1'),
(13, 6, 3, 'Groupe 1'),
(14, 6, 3, 'Groupe 2'),
(17, 6, 3, 'Groupe 3');

-- --------------------------------------------------------

--
-- Structure de la table `groupe_member`
--

CREATE TABLE `groupe_member` (
  `id` bigint(20) NOT NULL,
  `id_groupe` int(20) NOT NULL,
  `id_student` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `groupe_member`
--

INSERT INTO `groupe_member` (`id`, `id_groupe`, `id_student`) VALUES
(1, 14, 10),
(2, 14, 11),
(3, 14, 12),
(4, 14, 13),
(5, 14, 15),
(6, 17, 1),
(7, 17, 2),
(8, 17, 4),
(9, 17, 5),
(10, 17, 6),
(11, 17, 7),
(12, 17, 8),
(13, 17, 9),
(14, 19, 33),
(15, 20, 35),
(16, 22, 34);

-- --------------------------------------------------------

--
-- Structure de la table `holiday`
--

CREATE TABLE `holiday` (
  `id` bigint(20) NOT NULL,
  `begin_date` date NOT NULL,
  `end_date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `holiday`
--

INSERT INTO `holiday` (`id`, `begin_date`, `end_date`) VALUES
(22, '2018-04-13', '2018-04-13');

-- --------------------------------------------------------

--
-- Structure de la table `journal`
--

CREATE TABLE `journal` (
  `id_user` bigint(20) NOT NULL,
  `op` int(11) NOT NULL,
  `id` bigint(100) NOT NULL,
  `tab` int(11) NOT NULL,
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `journal`
--

INSERT INTO `journal` (`id_user`, `op`, `id`, `tab`, `date`) VALUES
(1, 1, 1, 7, '2018-05-03 21:18:49'),
(1, 1, 1, 20, '2018-04-15 10:20:20'),
(1, 1, 2, 7, '2018-05-01 09:00:00'),
(1, 1, 2, 21, '2018-04-15 13:47:10'),
(1, 1, 3, 7, '2018-05-03 09:00:00'),
(1, 1, 3, 20, '2018-04-15 13:30:10'),
(1, 1, 7, 7, '2018-05-18 10:52:58'),
(1, 1, 9, 21, '2018-05-05 12:47:55'),
(1, 1, 10, 7, '2018-05-18 11:03:09'),
(1, 1, 10, 21, '2018-05-05 19:29:38'),
(1, 1, 11, 20, '2018-05-05 12:28:32'),
(1, 1, 11, 21, '2018-05-05 20:14:06'),
(1, 1, 13, 21, '2018-05-07 18:09:46'),
(1, 1, 14, 21, '2018-05-17 17:19:45'),
(1, 1, 15, 20, '2018-05-05 20:14:22'),
(1, 1, 15, 21, '2018-05-17 17:19:45'),
(1, 1, 17, 21, '2018-05-17 17:05:47'),
(1, 1, 19, 20, '2018-05-15 18:13:14'),
(1, 1, 20, 21, '2018-05-17 22:25:34'),
(1, 1, 21, 20, '2018-05-17 16:41:34'),
(1, 1, 23, 20, '2018-05-17 17:05:39'),
(1, 1, 26, 20, '2018-05-17 22:26:05'),
(1, 1, 27, 21, '2018-05-18 10:39:54'),
(1, 1, 28, 20, '2018-05-17 22:32:15'),
(1, 1, 28, 40, '2018-05-04 23:08:20'),
(1, 1, 29, 20, '2018-05-17 22:32:48'),
(1, 1, 30, 20, '2018-05-17 22:33:30'),
(1, 1, 31, 20, '2018-05-17 22:35:03'),
(1, 1, 33, 20, '2018-05-17 22:36:48'),
(1, 1, 35, 38, '2018-05-04 13:06:14'),
(1, 1, 36, 38, '2018-05-04 13:07:26'),
(1, 1, 37, 38, '2018-05-04 13:08:58'),
(1, 1, 38, 38, '2018-05-04 13:09:21'),
(1, 1, 39, 38, '2018-05-04 13:09:44'),
(1, 1, 40, 20, '2018-05-18 10:40:17'),
(1, 1, 40, 23, '2018-05-18 13:08:14'),
(1, 1, 41, 20, '2018-05-18 10:41:02'),
(1, 1, 41, 23, '2018-05-18 13:08:44'),
(1, 1, 42, 23, '2018-05-18 13:11:42'),
(1, 1, 43, 23, '2018-05-18 13:12:25'),
(1, 1, 43, 38, '2018-05-04 13:55:51'),
(1, 1, 44, 38, '2018-05-04 13:56:32'),
(1, 1, 45, 38, '2018-05-04 13:56:41'),
(1, 1, 46, 38, '2018-05-04 13:56:52'),
(1, 1, 47, 38, '2018-05-04 13:57:15'),
(1, 1, 48, 38, '2018-05-04 13:58:49'),
(1, 1, 49, 38, '2018-05-04 13:59:29'),
(1, 1, 50, 38, '2018-05-04 13:59:39'),
(1, 1, 51, 38, '2018-05-04 14:01:33'),
(1, 1, 52, 38, '2018-05-04 14:02:32'),
(1, 1, 53, 38, '2018-05-04 14:02:42'),
(1, 1, 54, 38, '2018-05-04 18:46:54'),
(2, 1, 16, 20, '2018-05-05 20:30:23'),
(2, 1, 16, 21, '2018-05-16 23:48:00'),
(2, 1, 20, 20, '2018-05-16 23:52:02'),
(2, 1, 55, 38, '2018-05-04 21:30:30'),
(2, 1, 56, 38, '2018-05-04 21:31:07'),
(2, 1, 57, 38, '2018-05-04 21:31:15'),
(2, 1, 58, 38, '2018-05-04 21:31:22'),
(2, 1, 59, 38, '2018-05-04 21:31:34'),
(2, 1, 60, 38, '2018-05-04 21:31:39'),
(2, 1, 61, 38, '2018-05-04 21:31:47'),
(2, 1, 62, 38, '2018-05-04 21:31:56'),
(2, 1, 63, 38, '2018-05-04 21:32:03'),
(2, 1, 64, 38, '2018-05-04 21:32:22'),
(2, 1, 65, 38, '2018-05-04 21:32:31'),
(2, 1, 66, 38, '2018-05-04 21:32:41'),
(2, 1, 67, 38, '2018-05-04 21:32:48'),
(2, 1, 68, 38, '2018-05-04 21:33:04'),
(2, 1, 69, 38, '2018-05-04 21:33:30'),
(2, 1, 70, 38, '2018-05-04 21:33:54'),
(2, 1, 71, 38, '2018-05-04 21:34:04'),
(4, 1, 9, 7, '2018-05-18 10:57:12'),
(4, 1, 72, 38, '2018-05-04 21:44:40'),
(4, 1, 73, 38, '2018-05-04 21:44:46'),
(4, 1, 74, 38, '2018-05-04 21:44:51'),
(4, 1, 75, 38, '2018-05-04 21:44:58'),
(4, 1, 76, 38, '2018-05-04 21:45:03'),
(4, 1, 77, 38, '2018-05-04 21:45:13'),
(4, 1, 78, 38, '2018-05-04 21:45:18'),
(4, 1, 79, 38, '2018-05-04 21:45:27'),
(4, 1, 80, 38, '2018-05-04 21:45:55'),
(4, 1, 81, 38, '2018-05-04 21:46:47'),
(4, 1, 82, 38, '2018-05-04 21:46:57'),
(4, 1, 83, 38, '2018-05-04 21:47:10'),
(4, 1, 84, 38, '2018-05-04 21:47:17'),
(4, 1, 85, 38, '2018-05-04 21:47:23'),
(4, 1, 86, 38, '2018-05-04 21:47:28'),
(5, 1, 87, 38, '2018-05-04 21:49:49'),
(5, 1, 88, 38, '2018-05-04 21:49:56'),
(5, 1, 89, 38, '2018-05-04 21:50:00'),
(5, 1, 90, 38, '2018-05-04 21:50:06'),
(5, 1, 91, 38, '2018-05-04 21:50:11'),
(5, 1, 92, 38, '2018-05-04 21:50:15'),
(5, 1, 93, 38, '2018-05-04 21:50:19'),
(5, 1, 94, 38, '2018-05-04 21:50:24'),
(5, 1, 95, 38, '2018-05-04 21:50:57'),
(5, 1, 96, 38, '2018-05-04 21:51:09'),
(5, 1, 97, 38, '2018-05-04 21:51:16'),
(5, 1, 98, 38, '2018-05-04 21:51:21'),
(6, 1, 99, 38, '2018-05-04 21:53:01'),
(6, 1, 100, 38, '2018-05-04 21:53:12'),
(6, 1, 101, 38, '2018-05-04 21:53:17'),
(6, 1, 102, 38, '2018-05-04 21:53:21'),
(6, 1, 103, 38, '2018-05-04 21:53:26'),
(6, 1, 104, 38, '2018-05-04 21:53:30'),
(7, 1, 115, 38, '2018-05-04 21:57:33'),
(7, 1, 116, 38, '2018-05-04 21:57:38'),
(7, 1, 117, 38, '2018-05-04 21:57:43'),
(7, 1, 118, 38, '2018-05-04 21:57:47'),
(7, 1, 119, 38, '2018-05-04 21:57:56'),
(7, 1, 120, 38, '2018-05-04 21:58:00'),
(7, 1, 121, 38, '2018-05-04 21:58:04'),
(8, 1, 105, 38, '2018-05-04 21:55:23'),
(8, 1, 106, 38, '2018-05-04 21:55:28'),
(8, 1, 107, 38, '2018-05-04 21:55:33'),
(8, 1, 108, 38, '2018-05-04 21:55:37'),
(8, 1, 109, 38, '2018-05-04 21:55:42'),
(8, 1, 110, 38, '2018-05-04 21:55:45'),
(8, 1, 111, 38, '2018-05-04 21:55:49'),
(8, 1, 112, 38, '2018-05-04 21:55:54'),
(8, 1, 113, 38, '2018-05-04 21:55:57'),
(8, 1, 114, 38, '2018-05-04 21:56:01'),
(10, 1, 17, 20, '2018-05-06 16:01:37'),
(10, 1, 18, 20, '2018-05-06 16:14:59'),
(12, 1, 26, 21, '2018-05-17 22:45:26'),
(12, 1, 35, 20, '2018-05-17 22:45:44'),
(16, 1, 1, 21, '2018-04-15 13:25:00'),
(16, 1, 2, 20, '2018-04-15 13:30:10'),
(16, 1, 3, 21, '2018-05-05 12:18:29'),
(16, 1, 8, 21, '2018-05-05 12:42:15'),
(16, 1, 12, 20, '2018-05-05 12:42:48'),
(16, 1, 12, 21, '2018-05-05 20:17:06'),
(16, 1, 13, 20, '2018-05-05 19:28:01'),
(16, 1, 14, 20, '2018-05-05 20:02:37'),
(17, 1, 21, 23, '2018-04-02 18:38:00'),
(19, 1, 25, 23, '2018-04-20 10:30:00'),
(19, 1, 40, 38, '2018-05-04 13:12:44'),
(19, 1, 41, 38, '2018-05-04 13:53:02'),
(19, 1, 42, 38, '2018-05-04 13:54:02'),
(20, 1, 33, 23, '2018-05-09 16:20:00'),
(20, 1, 125, 38, '2018-05-16 00:30:46'),
(20, 1, 126, 38, '2018-05-16 00:31:09'),
(20, 1, 127, 38, '2018-05-16 00:31:17'),
(21, 1, 24, 23, '2018-04-15 15:10:00'),
(22, 1, 13, 23, '2018-04-10 19:03:00'),
(22, 1, 31, 23, '2018-05-01 07:30:00'),
(22, 1, 122, 38, '2018-05-16 00:26:34'),
(22, 1, 123, 38, '2018-05-16 00:28:11'),
(22, 1, 124, 38, '2018-05-16 00:28:31'),
(26, 1, 1, 27, '2018-05-03 21:21:16'),
(26, 1, 20, 23, '2018-03-11 12:50:00'),
(26, 1, 32, 23, '2018-05-01 10:30:00'),
(27, 1, 2, 27, '2018-05-02 14:30:00'),
(27, 1, 10, 23, '2018-01-05 09:40:00'),
(27, 1, 26, 23, '2018-04-21 13:40:00'),
(27, 1, 27, 23, '2018-04-26 14:30:00'),
(27, 1, 29, 23, '2018-04-30 09:45:00'),
(28, 1, 23, 23, '2018-04-10 09:58:00'),
(29, 1, 30, 23, '2018-04-30 10:52:00'),
(29, 1, 34, 23, '2018-05-10 13:35:00'),
(31, 1, 18, 21, '2018-05-17 22:22:40'),
(31, 1, 25, 20, '2018-05-17 22:23:35'),
(31, 1, 28, 23, '2018-04-29 08:30:00'),
(31, 1, 128, 38, '2018-05-17 17:07:53'),
(31, 1, 129, 38, '2018-05-17 17:08:30'),
(32, 1, 22, 23, '2018-04-05 11:40:00'),
(32, 1, 25, 21, '2018-05-17 22:49:02'),
(32, 1, 36, 20, '2018-05-17 22:49:28'),
(32, 1, 37, 20, '2018-05-17 22:53:26'),
(32, 1, 38, 20, '2018-05-17 22:53:51'),
(40, 5, 0, 37, '2018-05-04 21:41:08');

-- --------------------------------------------------------

--
-- Structure de la table `level`
--

CREATE TABLE `level` (
  `id` int(11) NOT NULL,
  `abrg` varchar(10) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `level`
--

INSERT INTO `level` (`id`, `abrg`) VALUES
(1, '1LMD'),
(2, '2LMD'),
(3, '3LMD'),
(6, 'Doctorat'),
(4, 'Master 1'),
(5, 'Master 2');

-- --------------------------------------------------------

--
-- Structure de la table `location`
--

CREATE TABLE `location` (
  `id` bigint(255) NOT NULL,
  `id_user` bigint(20) NOT NULL,
  `latitude` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `longitude` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `altitude` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `location`
--

INSERT INTO `location` (`id`, `id_user`, `latitude`, `longitude`, `altitude`, `time`) VALUES
(2, 1, '2', '3', '1', '2018-04-28 22:59:16'),
(3, 2, '35.353255', '1.3140800000000001', '1025.3', '2018-04-28 23:39:36'),
(4, 2, '35.353255', '1.3140800000000001', '1025.3', '2018-04-28 23:40:14'),
(5, 2, '35.353255', '1.3140800000000001', '1025.3', '2018-04-28 23:40:34'),
(6, 2, '35.353255', '1.3140800000000001', '1025.3', '2018-04-28 23:43:40'),
(7, 2, '35.353255', '1.3140800000000001', '1025.3', '2018-04-28 23:44:29'),
(8, 2, '35.353255', '1.3140800000000001', '1025.3', '2018-04-28 23:44:41'),
(9, 2, '35.353255', '1.3140800000000001', '1025.3', '2018-04-28 23:56:58'),
(10, 1, '35.353255', '1.3140800000000001', '1025.3', '2018-04-29 00:39:06'),
(11, 1, '35.353255', '1.3140800000000001', '1025.3', '2018-04-29 00:39:08'),
(12, 1, '35.353255', '1.3140800000000001', '1025.3', '2018-04-29 00:39:17'),
(13, 1, '35.353255', '1.3140800000000001', '1025.3', '2018-04-29 00:54:38'),
(14, 4, '35.353255', '1.3140800000000001', '1025.3', '2018-04-29 01:16:48'),
(15, 4, '35.353255', '1.3140800000000001', '1025.3', '2018-04-29 01:42:14'),
(16, 4, '35.353255', '1.3140800000000001', '1025.3', '2018-04-29 02:43:51'),
(17, 4, '35.353255', '1.3140800000000001', '1025.3', '2018-04-29 03:18:21'),
(18, 4, '35.356525000000005', '1.4847399999999997', '979.2', '2018-04-29 19:09:42'),
(19, 2, '35.35304166666666', '1.314105', '991.7', '2018-04-30 19:16:34'),
(20, 2, '35.353008333333335', '1.31413', '1006.0', '2018-04-30 19:17:09'),
(21, 2, '35.353008333333335', '1.31413', '1006.0', '2018-04-30 19:17:17'),
(22, 2, '35.353008333333335', '1.31413', '1006.0', '2018-04-30 19:17:23'),
(23, 2, '35.353008333333335', '1.31413', '1006.0', '2018-04-30 19:17:33'),
(24, 2, '35.353008333333335', '1.31413', '1006.0', '2018-04-30 19:17:35'),
(25, 2, '35.353008333333335', '1.31413', '1006.0', '2018-04-30 20:55:25'),
(26, 2, '35.353008333333335', '1.31413', '1006.0', '2018-04-30 21:31:36'),
(27, 2, '35.353008333333335', '1.31413', '1006.0', '2018-04-30 21:43:56'),
(28, 1, '35.355896666666666', '1.4083983333333334', '954.4', '2018-05-04 23:08:19');

-- --------------------------------------------------------

--
-- Structure de la table `marks`
--

CREATE TABLE `marks` (
  `id` bigint(255) NOT NULL,
  `id_student` bigint(20) NOT NULL,
  `id_exam` bigint(20) NOT NULL,
  `note` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `marks`
--

INSERT INTO `marks` (`id`, `id_student`, `id_exam`, `note`) VALUES
(1, 1, 25, 5),
(2, 1, 25, 19),
(3, 1, 24, 18),
(4, 1, 25, 20),
(5, 1, 24, 11),
(6, 1, 13, 18),
(7, 1, 10, 6);

-- --------------------------------------------------------

--
-- Structure de la table `marks_displayed`
--

CREATE TABLE `marks_displayed` (
  `id` bigint(20) NOT NULL,
  `id_teacher` bigint(20) NOT NULL,
  `id_exam` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `marks_displayed`
--

INSERT INTO `marks_displayed` (`id`, `id_teacher`, `id_exam`) VALUES
(20, 31, 10),
(31, 22, 13);

-- --------------------------------------------------------

--
-- Structure de la table `meeting`
--

CREATE TABLE `meeting` (
  `id` bigint(20) NOT NULL,
  `id_classroom` int(11) NOT NULL,
  `date` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `meeting`
--

INSERT INTO `meeting` (`id`, `id_classroom`, `date`) VALUES
(27, 32, '2018-04-25 12:30:00');

-- --------------------------------------------------------

--
-- Structure de la table `meeting_presence`
--

CREATE TABLE `meeting_presence` (
  `id` bigint(20) NOT NULL,
  `id_user` bigint(20) NOT NULL,
  `id_meeting` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `message`
--

CREATE TABLE `message` (
  `id` bigint(100) NOT NULL,
  `id_user` bigint(20) NOT NULL,
  `msg` text COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `message`
--

INSERT INTO `message` (`id`, `id_user`, `msg`) VALUES
(1, 1, 'Bonjour Monsieur\r\nJ e Vous demande de bien vouloir nous organiser un RDV cette semaine pour corriger le travaille acheve\r\nmerci'),
(2, 16, 'Ok RDV le mardi 12/04/2018 a 14:00h a mon bureau A+'),
(3, 1, 'ok monsieur'),
(11, 1, 'salem monsieur est ce que je peux ne pas introduire ls description d inteface relatif a l application desktop?'),
(12, 16, 'oui bien sur'),
(13, 16, 'ok'),
(14, 16, 'c est bon?'),
(15, 1, 'oui c est bon'),
(16, 2, 'salut imen!'),
(17, 10, 'cc'),
(18, 10, 'cv?'),
(19, 1, 'slm Malika '),
(20, 2, 'CV?'),
(21, 1, 'sallem monsieur ,\nest ce que je peux savoir le nom de mon examinateur svp?'),
(22, 1, 'sallem monsieur ,\nest ce que je peux savoir le nom de mon examinateur svp?'),
(23, 1, 'cv hmdl '),
(24, 1, 'cv hmdl '),
(25, 31, 'votre examinateur est Mr ABID Khaked ... BN crg'),
(26, 1, 'merci monsieur....'),
(27, 1, 'CV hmdl'),
(28, 1, 'cc'),
(29, 1, 'est ce que tu as fait l exercice de cryptographie ?'),
(30, 1, 'est ce on a une consultation le lundi matin?'),
(31, 1, 'sallem monsieur combien de copie doit-on faire pour notre rapport?'),
(32, 1, 'sallem monsieur combien de copie doit-on faire pour notre rapport?'),
(33, 1, 'est-ce que tu as parler avec monsieur hattab concernant le projet?'),
(34, 1, 'est-ce que tu as parler avec monsieur hattab concernant le projet?'),
(35, 12, 'NN pas encore !!'),
(36, 32, 'vous devez faire une seule copie'),
(37, 32, 'BN crg'),
(38, 32, 'vous devez faire 2 copies'),
(39, 32, 'vous devez faire 2 copies'),
(40, 1, 'pour quoi?'),
(41, 1, 'tu ne veux  pas faire une prÃ© soutenance? '),
(42, 1, 'tu ne veux  pas faire une prÃ© soutenance? ');

-- --------------------------------------------------------

--
-- Structure de la table `messege_sent_to`
--

CREATE TABLE `messege_sent_to` (
  `id` bigint(200) NOT NULL,
  `id_msg` bigint(100) NOT NULL,
  `id_user` bigint(20) NOT NULL,
  `seen` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `messege_sent_to`
--

INSERT INTO `messege_sent_to` (`id`, `id_msg`, `id_user`, `seen`) VALUES
(1, 1, 16, 1),
(2, 2, 1, 1),
(3, 3, 16, 1),
(8, 11, 16, 1),
(9, 12, 1, 1),
(10, 13, 1, 1),
(11, 14, 1, 1),
(12, 15, 16, 1),
(13, 16, 1, 1),
(14, 17, 1, 1),
(15, 18, 1, 1),
(16, 19, 2, 1),
(17, 20, 1, 1),
(18, 21, 31, 1),
(19, 23, 2, 0),
(20, 25, 1, 1),
(21, 26, 31, 0),
(22, 28, 10, 0),
(23, 29, 10, 0),
(24, 30, 2, 0),
(25, 31, 32, 1),
(26, 33, 12, 1),
(27, 35, 1, 1),
(28, 36, 1, 0),
(29, 37, 1, 0),
(30, 38, 5, 0),
(31, 40, 12, 0),
(32, 41, 12, 0);

-- --------------------------------------------------------

--
-- Structure de la table `module`
--

CREATE TABLE `module` (
  `id` int(11) NOT NULL,
  `name` varchar(60) COLLATE utf8mb4_unicode_ci NOT NULL,
  `abrg` varchar(15) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `module`
--

INSERT INTO `module` (`id`, `name`, `abrg`) VALUES
(1, 'Redaction Scientifique', 'RS'),
(2, 'Compilation', 'Compilation'),
(3, 'Theorie des language', 'THL'),
(4, 'Programation linaire', 'PL'),
(5, 'Programation Logique', 'Prolog'),
(7, 'System D\'information', 'SI'),
(8, 'Algebre', 'Algebre'),
(9, 'Analyse', 'Analyse'),
(10, 'Algorithmique et stucture de données', 'Algo'),
(11, 'Technologie Scientefique et expression', 'Tech d\'exp'),
(12, 'Bureautique', 'Bureautique'),
(13, 'Pysique', 'Pysique'),
(14, 'Langue Anglaise', 'Anglais'),
(15, 'Probabilite et statistique descriptive', 'Proba'),
(16, 'Programmation et structure de donnees', 'algo'),
(17, 'Histoire de science', 'HS'),
(18, 'Achitecture des ordinateurs', 'archi'),
(19, 'Logique mathematique', 'logique'),
(20, 'Programation Oriente objet', 'POO'),
(21, 'Base de Donees', 'BD'),
(22, 'Geni Logiciel', 'GL'),
(24, 'Systeme d\'exploitation', 'SE'),
(25, 'Theorie des languages', 'THL'),
(26, 'Theorie des graph', 'THG'),
(27, 'Reseau de communication', 'RT'),
(28, 'Devlopement d\'application web', 'dev web'),
(29, 'Aspect juridique', 'Aspect'),
(30, 'Interface Home Machine', 'IHM'),
(31, 'Application mobiles', 'APPmobile'),
(32, 'Securite Informatique', 'Securite'),
(33, 'Administration des Base de donnees', 'BD'),
(34, 'Cryptographie', 'crypto'),
(35, 'Programation Fonctionnel', 'PF'),
(36, 'Base de Donnee avancé et Data Meaning', 'BD'),
(37, 'Architecture et administration de SGBD', 'Archi'),
(38, 'Complexite Algorithmique', 'Complexite'),
(39, 'Gestion de Qualité', 'GQ'),
(40, 'Logique pour LA', 'LA'),
(41, 'Structure Machine', 'Structure');

-- --------------------------------------------------------

--
-- Structure de la table `news`
--

CREATE TABLE `news` (
  `id` bigint(20) NOT NULL,
  `id_user` bigint(20) NOT NULL,
  `news_type` int(11) NOT NULL,
  `title` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `content` text CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `news`
--

INSERT INTO `news` (`id`, `id_user`, `news_type`, `title`, `content`) VALUES
(10, 27, 3, 'Epreuve d examen  GL', 'L\'examens GL pour les 3LMD aura lieu le 18/01/2018 a 11:00 \r\nje vous souhaite une bonne revision'),
(13, 22, 3, 'Controle n 1 de base de donnees', 'Le premier controle de BDD pour ce semestre pour le groupe 3 aura lieu le 2018-04-15 durant la seance de td...\r\nbon courage'),
(20, 26, 5, 'Affichage des notes d examen GL', 'Les notes d examens GL coresspondant au promo 3LMD sont afficher au niveau du departement informatique...\r\nveuillez consulter l affichage et raportez vos question a l enseignant responsable pendant la seance de consultation \r\nBon courage'),
(21, 17, 1, 'Changement de la seance Cours Securite informatique', 'Le Cours de Securite informatique a ete change de l heure a 11:00:00 au niveau de l Amphi A...\r\nSe changement seras applique le 14/04/2018 inchallah \r\nJe vous remercie\r\nBon courage\r\n'),
(22, 32, 4, 'Pas de travaille le 13/04/2018 !', 'Je vous informe que le 13/04/2018 srai un jour ferier pour tous les membres du departement informatique a causes des traveaux de maintenance qui aurons lieu toutes la journee...\r\n'),
(23, 28, 0, '3eme Bourse delivre', 'J\'informe tous les etudiants que la bourse du 3eme trimestre a ete livre le 14/04/2018...\r\nBonne chance'),
(24, 21, 3, 'Controle n 1 d application mobile', 'J informe les etudiant de 3LMD que le premier control TD aura lieu cette semaine pendant les seance TD...\r\nChaque etudiant doit faire le controle dans la seance prevu de son groupe, les etudiant qui vont faire le controle avec less autres groupes vont avoir automatiquement une note null ( 0 ).\r\nBonne revision \r\n'),
(25, 19, 3, 'Controle n 1 de cryptographie', 'le premier controle de cryptographie aura lieu le 24/04/2018 pendant la séance de TD\r\nJe vous informe le controle sera lors des derniers 30 min seulement \r\nalors preparer-vous et bon courages'),
(26, 27, 9, 'Greve des etudiant 2LMD', 'Les etudiant 3LMD veulent faire une greve de 3 jours en reclamant les notes obtenus au premier semestres du 02/05/2018 jusqu a le 04/05/2018...\r\nJe vous demandes de consulter les raisons de cette greve et de partager vous avis pour aider l administration a prendre se decission...\r\nMerci...'),
(27, 27, 6, 'CPC du deuxieme semestre', 'Le CPC du deuxieme semestre de l\'année universitire 2017/2018 aura lieu le 25/04/2018 à 12:30 au bureau du chef département, nous recommendons tous les enseignants et les delegues d etre present a l\'heure '),
(28, 31, 0, 'Date de soutenance 3LMD', 'Avis aux etudiants de 3LMD \r\nJe vous informe que la date des soutenances a ete fixe pour l annee universitaire 2017/2018 pour le 28/05/2018 la date de depot des memoire est ainsi fixe le 20/05/2018.... \r\nVous devez contactez vous encadrant pour connaitre l examinateur correspondant a votre sujet.\r\nbon courage '),
(29, 27, 8, 'Renouvellement de dossier de bourse', 'J\'informe tous les etudiants de departement informatique qu il doivent forcement renouveller leur dossier d inscription avant les vacances pour des raisons pedagoqique...\r\nAucune justification sera accepter si un etudiant depasse ce delais...\r\nLes docuents necessaires sont introduits au dessous.\r\nJe vous remercie.'),
(30, 29, 8, 'Renouvellement des dosssier de bourses', 'Tous les etudiants doivent renouvler leur dossiers de bourses avant les vaconces apres le renouvellement des dossier d inscription universitaire ...\r\nJe vous informe que aucun dossier ne sera accepter au debut de l annee\r\nMerci de rammener les dossier introduits lors du renouvllement.'),
(31, 22, 5, 'Affichage des notes de controle n 01 ', 'Avis au etudiant  3LMD groupe 03 \r\nLes notes de controle n 01 sont affiche au niveau du departement...\r\nla consultation des feuilles sera faite lord de la consultation d examaen .\r\nJe vous remercie.'),
(32, 26, 0, 'PV de Delibiration', 'Le PV de delibiration pour le premier semestre de l annee universitaire 2017/2018 est affiche au niveau du departement \r\nVeuillez verifier vos notes et raporter si il y a lieu vos avant le 29/04/2017 sinon votre demande ne sera pas prise en charge...\r\nMerci a tous et bon courage '),
(33, 20, 2, 'Consultation des notes RS', 'La consultation d examen Redation scientifique aura lieu le 18/05/2018 a la L AMPHI A...\r\naucune consultation ne sera faite si un etudiant le souhaite apres la date introduite...\r\nMerci'),
(34, 29, 0, 'Recuperation des livres bibliotheque', 'Je demande tous les etudiants qui ont depasser la date prevu de remise des livres de les soummetre au niveau de la bibliotheque avant le 15/05/2018 \r\nLes etudiant qui deppasent ce delais risque de rentrer au conseil dicipline...\r\n ');

-- --------------------------------------------------------

--
-- Structure de la table `news_visibilitu`
--

CREATE TABLE `news_visibilitu` (
  `id` bigint(255) NOT NULL,
  `id_user` bigint(20) NOT NULL,
  `id_news` bigint(20) NOT NULL,
  `vote_possible` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `news_visibilitu`
--

INSERT INTO `news_visibilitu` (`id`, `id_user`, `id_news`, `vote_possible`) VALUES
(1, 1, 32, 1),
(2, 2, 32, 1),
(3, 4, 32, 1),
(4, 5, 32, 1),
(5, 6, 32, 1),
(6, 7, 32, 1),
(7, 8, 32, 1),
(8, 9, 32, 1),
(9, 10, 32, 1),
(10, 11, 32, 1),
(11, 12, 32, 1),
(12, 13, 32, 1),
(13, 14, 32, 1),
(14, 15, 32, 1),
(15, 33, 32, 1),
(16, 34, 32, 1),
(17, 35, 32, 1),
(18, 1, 34, 1),
(19, 2, 34, 1),
(20, 4, 34, 1),
(21, 5, 34, 1),
(22, 6, 34, 1),
(23, 7, 34, 1),
(24, 8, 34, 1),
(25, 9, 34, 1),
(26, 10, 34, 1),
(27, 11, 34, 1),
(28, 12, 34, 1),
(29, 13, 34, 1),
(30, 14, 34, 1),
(31, 15, 34, 1),
(32, 33, 34, 1),
(33, 34, 34, 1),
(34, 35, 34, 1),
(35, 1, 23, 1),
(36, 2, 23, 1),
(37, 4, 23, 1),
(38, 5, 23, 1),
(39, 6, 23, 1),
(40, 7, 23, 1),
(41, 8, 23, 1),
(42, 9, 23, 1),
(43, 10, 23, 1),
(44, 11, 23, 1),
(45, 12, 23, 1),
(46, 13, 23, 1),
(47, 14, 23, 1),
(48, 15, 23, 1),
(49, 33, 23, 1),
(50, 34, 23, 1),
(51, 35, 23, 1),
(52, 1, 22, 1),
(53, 2, 22, 1),
(54, 4, 22, 1),
(55, 5, 22, 1),
(56, 6, 22, 1),
(57, 7, 22, 1),
(58, 8, 22, 1),
(59, 9, 22, 1),
(60, 10, 22, 1),
(61, 11, 22, 1),
(62, 12, 22, 1),
(63, 13, 22, 1),
(64, 14, 22, 1),
(65, 15, 22, 1),
(66, 16, 22, 1),
(67, 17, 22, 1),
(68, 18, 22, 1),
(69, 19, 22, 1),
(70, 20, 22, 1),
(71, 21, 22, 1),
(72, 22, 22, 1),
(73, 23, 22, 1),
(74, 24, 22, 1),
(75, 25, 22, 1),
(76, 26, 22, 1),
(77, 27, 22, 1),
(78, 28, 22, 1),
(79, 29, 22, 1),
(80, 30, 22, 1),
(81, 31, 22, 1),
(82, 32, 22, 1),
(83, 33, 22, 1),
(84, 34, 22, 1),
(85, 35, 22, 1),
(86, 36, 22, 1),
(87, 37, 22, 1),
(88, 38, 22, 1),
(89, 39, 22, 1),
(90, 40, 22, 1),
(91, 1, 26, 1),
(92, 2, 26, 1),
(93, 4, 26, 1),
(94, 5, 26, 1),
(95, 6, 26, 1),
(96, 7, 26, 1),
(97, 8, 26, 1),
(98, 9, 26, 1),
(99, 10, 26, 1),
(100, 11, 26, 1),
(101, 12, 26, 1),
(102, 13, 26, 1),
(103, 14, 26, 1),
(104, 15, 26, 1),
(105, 16, 26, 1),
(106, 17, 26, 1),
(107, 18, 26, 1),
(108, 19, 26, 1),
(109, 20, 26, 1),
(110, 21, 26, 1),
(111, 22, 26, 1),
(112, 23, 26, 1),
(113, 24, 26, 1),
(114, 25, 26, 1),
(115, 26, 26, 1),
(116, 27, 26, 1),
(117, 28, 26, 1),
(118, 29, 26, 1),
(119, 30, 26, 1),
(120, 31, 26, 1),
(121, 32, 26, 1),
(122, 33, 26, 1),
(123, 34, 26, 1),
(124, 35, 26, 1),
(125, 36, 26, 1),
(126, 37, 26, 1),
(127, 38, 26, 1),
(128, 39, 26, 1),
(129, 40, 26, 1),
(130, 1, 27, 1),
(131, 2, 27, 1),
(132, 4, 27, 1),
(133, 5, 27, 1),
(134, 6, 27, 1),
(135, 7, 27, 1),
(136, 8, 27, 1),
(137, 9, 27, 1),
(138, 10, 27, 1),
(139, 11, 27, 1),
(140, 12, 27, 1),
(141, 13, 27, 1),
(142, 14, 27, 1),
(143, 15, 27, 1),
(144, 16, 27, 1),
(145, 17, 27, 1),
(146, 18, 27, 1),
(147, 19, 27, 1),
(148, 20, 27, 1),
(149, 21, 27, 1),
(150, 22, 27, 1),
(151, 23, 27, 1),
(152, 24, 27, 1),
(153, 25, 27, 1),
(154, 26, 27, 1),
(155, 27, 27, 1),
(156, 28, 27, 1),
(157, 29, 27, 1),
(158, 30, 27, 1),
(159, 31, 27, 1),
(160, 32, 27, 1),
(161, 33, 27, 1),
(162, 34, 27, 1),
(163, 35, 27, 1),
(164, 36, 27, 1),
(165, 37, 27, 1),
(166, 38, 27, 1),
(167, 39, 27, 1),
(168, 40, 27, 1),
(169, 10, 28, 1),
(170, 11, 28, 1),
(171, 12, 28, 1),
(172, 13, 28, 1),
(173, 15, 28, 1),
(174, 1, 28, 1),
(175, 2, 28, 1),
(176, 4, 28, 1),
(177, 5, 28, 1),
(178, 6, 28, 1),
(179, 7, 28, 1),
(180, 8, 28, 1),
(181, 9, 28, 1),
(182, 10, 33, 1),
(183, 11, 33, 1),
(184, 12, 33, 1),
(185, 13, 33, 1),
(186, 15, 33, 1),
(187, 1, 33, 1),
(188, 2, 33, 1),
(189, 4, 33, 1),
(190, 5, 33, 1),
(191, 6, 33, 1),
(192, 7, 33, 1),
(193, 8, 33, 1),
(194, 9, 33, 1),
(195, 10, 20, 1),
(196, 11, 20, 1),
(197, 12, 20, 1),
(198, 13, 20, 1),
(199, 15, 20, 1),
(200, 1, 20, 1),
(201, 2, 20, 1),
(202, 4, 20, 1),
(203, 5, 20, 1),
(204, 6, 20, 1),
(205, 7, 20, 1),
(206, 8, 20, 1),
(207, 9, 20, 1),
(208, 1, 25, 1),
(209, 2, 25, 1),
(210, 4, 25, 1),
(211, 5, 25, 1),
(212, 6, 25, 1),
(213, 7, 25, 1),
(214, 8, 25, 1),
(215, 9, 25, 1),
(216, 1, 24, 1),
(217, 2, 24, 1),
(218, 4, 24, 1),
(219, 5, 24, 1),
(220, 6, 24, 1),
(221, 7, 24, 1),
(222, 8, 24, 1),
(223, 9, 24, 1),
(224, 1, 31, 1),
(225, 2, 31, 1),
(226, 4, 31, 1),
(227, 5, 31, 1),
(228, 6, 31, 1),
(229, 7, 31, 1),
(230, 8, 31, 1),
(231, 9, 31, 1),
(232, 10, 29, 1),
(233, 11, 29, 1),
(234, 12, 29, 1),
(235, 13, 29, 1),
(236, 15, 29, 1),
(237, 1, 29, 1),
(238, 2, 29, 1),
(239, 4, 29, 1),
(240, 5, 29, 1),
(241, 6, 29, 1),
(242, 7, 29, 1),
(243, 8, 29, 1),
(244, 9, 29, 1),
(245, 33, 29, 1),
(246, 35, 29, 1),
(247, 34, 29, 1),
(248, 10, 30, 1),
(249, 11, 30, 1),
(250, 12, 30, 1),
(251, 13, 30, 1),
(252, 15, 30, 1),
(253, 1, 30, 1),
(254, 2, 30, 1),
(255, 4, 30, 1),
(256, 5, 30, 1),
(257, 6, 30, 1),
(258, 7, 30, 1),
(259, 8, 30, 1),
(260, 9, 30, 1),
(261, 33, 30, 1),
(262, 35, 30, 1),
(263, 34, 30, 1),
(264, 1, 21, 1),
(281, 1, 13, 0),
(282, 2, 13, 0),
(283, 4, 13, 0),
(284, 5, 13, 0),
(285, 6, 13, 0),
(286, 7, 13, 0),
(287, 8, 13, 0),
(288, 9, 13, 0),
(289, 1, 10, 0),
(290, 2, 10, 0),
(291, 4, 10, 0),
(292, 5, 10, 0),
(293, 6, 10, 0),
(294, 7, 10, 0),
(295, 8, 10, 0),
(296, 9, 10, 0),
(297, 4, 21, 1),
(298, 2, 21, 1),
(299, 4, 21, 1),
(300, 5, 21, 1),
(301, 6, 21, 1),
(302, 10, 21, 1),
(303, 9, 21, 1),
(304, 7, 21, 1),
(305, 8, 21, 1),
(306, 31, 28, 1),
(307, 41, 22, 1),
(308, 41, 26, 1),
(309, 41, 27, 1),
(310, 41, 28, 1),
(311, 41, 32, 1);

-- --------------------------------------------------------

--
-- Structure de la table `pedagogical-procedure`
--

CREATE TABLE `pedagogical-procedure` (
  `id` bigint(20) NOT NULL,
  `begin_date` date NOT NULL,
  `end_date` date NOT NULL,
  `id_place` int(11) NOT NULL,
  `docs` text COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `pedagogical-procedure`
--

INSERT INTO `pedagogical-procedure` (`id`, `begin_date`, `end_date`, `id_place`, `docs`) VALUES
(29, '2018-04-20', '2018-06-30', 4, '-Attestation d inscription\r\n-Attestation d scolarite\r\n-Relve de notes\r\n-cheque bare\r\n'),
(30, '2018-04-18', '2018-06-01', 2, '-deux photos\r\n-carte d etudiant\r\n-act de naissance\r\n-residance');

-- --------------------------------------------------------

--
-- Structure de la table `request_done`
--

CREATE TABLE `request_done` (
  `id` bigint(20) NOT NULL,
  `id_establishement` int(11) NOT NULL,
  `id_admin` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `request_done`
--

INSERT INTO `request_done` (`id`, `id_establishement`, `id_admin`) VALUES
(1, 2, 26),
(2, 2, 27);

-- --------------------------------------------------------

--
-- Structure de la table `responsble_teacher`
--

CREATE TABLE `responsble_teacher` (
  `id` bigint(20) NOT NULL,
  `id_responsbility` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `responsble_teacher`
--

INSERT INTO `responsble_teacher` (`id`, `id_responsbility`) VALUES
(31, 1),
(18, 2),
(17, 3),
(32, 4),
(20, 5);

-- --------------------------------------------------------

--
-- Structure de la table `responsibility`
--

CREATE TABLE `responsibility` (
  `id` int(11) NOT NULL,
  `name` varchar(50) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `responsibility`
--

INSERT INTO `responsibility` (`id`, `name`) VALUES
(4, 'Chef de Departement'),
(3, 'Doyen'),
(1, 'Responsable des PFE Licence'),
(2, 'Responsable des PFE Master'),
(5, 'Vise Doyen');

-- --------------------------------------------------------

--
-- Structure de la table `session`
--

CREATE TABLE `session` (
  `id` int(11) NOT NULL,
  `id_session_type` int(11) NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  `id_day` int(4) NOT NULL,
  `time` time NOT NULL,
  `id_module` int(11) NOT NULL,
  `id_classroom` int(10) NOT NULL,
  `id_teacher` bigint(20) NOT NULL,
  `id_groupe` int(20) NOT NULL,
  `semester_1` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `session`
--

INSERT INTO `session` (`id`, `id_session_type`, `enabled`, `id_day`, `time`, `id_module`, `id_classroom`, `id_teacher`, `id_groupe`, `semester_1`) VALUES
(1, 5, 1, 3, '11:00:00', 32, 38, 17, 17, 0),
(2, 5, 1, 3, '11:00:00', 32, 38, 17, 14, 0),
(3, 5, 1, 3, '11:00:00', 32, 38, 17, 13, 0),
(4, 1, 1, 3, '12:30:00', 32, 38, 17, 13, 0),
(5, 1, 1, 3, '12:30:00', 32, 38, 17, 14, 0),
(6, 1, 1, 3, '12:30:00', 32, 38, 17, 17, 0),
(7, 5, 1, 4, '11:00:00', 1, 40, 20, 14, 0),
(8, 5, 1, 4, '11:00:00', 1, 40, 20, 13, 0),
(9, 5, 1, 4, '08:00:00', 34, 38, 19, 13, 0),
(10, 5, 1, 4, '08:00:00', 34, 38, 19, 14, 0),
(11, 5, 1, 4, '08:00:00', 34, 38, 19, 17, 0),
(12, 5, 1, 4, '15:30:00', 31, 40, 21, 13, 0),
(13, 5, 1, 4, '15:30:00', 31, 40, 21, 14, 0),
(14, 5, 1, 4, '15:30:00', 31, 40, 21, 17, 0),
(15, 5, 1, 4, '11:00:00', 1, 40, 20, 17, 0),
(16, 1, 1, 5, '08:00:00', 34, 29, 19, 17, 0),
(17, 1, 1, 5, '09:30:00', 31, 36, 21, 17, 0),
(18, 2, 1, 5, '15:30:00', 31, 44, 21, 17, 0),
(19, 1, 1, 5, '14:00:00', 33, 35, 22, 17, 0),
(21, 5, 1, 5, '11:00:00', 33, 39, 22, 17, 0),
(22, 5, 1, 5, '11:00:00', 33, 39, 22, 14, 1),
(23, 5, 1, 5, '11:00:00', 33, 39, 22, 13, 0),
(31, 5, 0, 3, '09:30:00', 32, 38, 17, 17, 0),
(35, 5, 0, 3, '09:30:00', 32, 38, 17, 14, 0),
(36, 5, 0, 3, '09:30:00', 32, 38, 17, 13, 0),
(41, 2, 0, 4, '12:30:00', 31, 41, 21, 17, 0),
(42, 2, 0, 4, '14:00:00', 31, 42, 21, 14, 0);

-- --------------------------------------------------------

--
-- Structure de la table `session_type`
--

CREATE TABLE `session_type` (
  `id` int(11) NOT NULL,
  `name` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `session_type`
--

INSERT INTO `session_type` (`id`, `name`) VALUES
(4, 'Consultation'),
(5, 'Cours'),
(1, 'TD'),
(2, 'TP');

-- --------------------------------------------------------

--
-- Structure de la table `specialty`
--

CREATE TABLE `specialty` (
  `id` int(11) NOT NULL,
  `name` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `specialty`
--

INSERT INTO `specialty` (`id`, `name`) VALUES
(2, 'Genie Informatique'),
(1, 'Genie Logiciel'),
(6, 'Informatique'),
(7, 'Mathematique'),
(5, 'Recherche Operationnel'),
(3, 'Reseau'),
(8, 'Securite Informatiqe'),
(4, 'Telecommunication');

-- --------------------------------------------------------

--
-- Structure de la table `strike`
--

CREATE TABLE `strike` (
  `id` bigint(20) NOT NULL,
  `begin_date` date NOT NULL,
  `end_date` date NOT NULL,
  `cause` text COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `strike`
--

INSERT INTO `strike` (`id`, `begin_date`, `end_date`, `cause`) VALUES
(26, '2018-05-02', '2018-05-05', '-Les enseignants n ont pas fait de consultation\r\n-L examains a ete tres difficile\r\n-Les enseignants ont absente trop pendant tous le semestre');

-- --------------------------------------------------------

--
-- Structure de la table `student`
--

CREATE TABLE `student` (
  `id_user` bigint(20) NOT NULL,
  `mat` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  `bib` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `student`
--

INSERT INTO `student` (`id_user`, `mat`, `bib`) VALUES
(1, '38010042/14', ''),
(2, '3460215/8', ''),
(4, '380041234/15', ''),
(5, '8432/5', ''),
(6, '582698412/7', ''),
(7, '98745328/52', ''),
(8, '390042/14', ''),
(9, '390012/15', ''),
(10, '9845346/25', ''),
(11, '54324/54', ''),
(12, '6435852/47', ''),
(13, '3800049/13', ''),
(14, '370045/10', ''),
(15, '58742385/42', ''),
(33, '984214863/741', ''),
(34, '89324552', ''),
(35, '38010079/14', '');

-- --------------------------------------------------------

--
-- Structure de la table `teacher`
--

CREATE TABLE `teacher` (
  `id_user` bigint(20) NOT NULL,
  `id_specialty` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `teacher`
--

INSERT INTO `teacher` (`id_user`, `id_specialty`) VALUES
(16, 2),
(20, 2),
(25, 4),
(31, 4),
(18, 5),
(30, 5),
(19, 6),
(21, 6),
(22, 6),
(24, 6),
(32, 6),
(36, 6),
(37, 6),
(38, 6),
(39, 6),
(41, 6),
(23, 7),
(17, 8);

-- --------------------------------------------------------

--
-- Structure de la table `town`
--

CREATE TABLE `town` (
  `id` int(11) NOT NULL,
  `town_name` varchar(30) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `town`
--

INSERT INTO `town` (`id`, `town_name`) VALUES
(9, 'Ain Bouchekif'),
(10, 'Ain Deheb'),
(12, 'Ain Kermes'),
(7, 'Ain Lehdid'),
(14, 'Ain Meriem'),
(11, 'Ain Sebain'),
(13, 'Ain Si Mesbah'),
(15, 'Bougara'),
(16, 'Chehaima'),
(5, 'Dahmouni'),
(17, 'Dayet Terfes'),
(18, 'Djillali ben Amar'),
(8, 'Frenda'),
(19, 'Guertoufa'),
(20, 'Hamadia'),
(6, 'Kasr Chelala'),
(23, 'Mechraa Safa'),
(21, 'Medrissa'),
(22, 'Mellakou'),
(24, 'Nador Menaouar'),
(25, 'Oued Lili'),
(3, 'Rahouia'),
(2, 'Souggar'),
(1, 'Tiaret'),
(4, 'Tisemsilet');

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL,
  `user_type` int(11) NOT NULL,
  `email` varchar(30) COLLATE utf8mb4_unicode_ci NOT NULL,
  `last_name` varchar(30) COLLATE utf8mb4_unicode_ci NOT NULL,
  `first_name` varchar(30) COLLATE utf8mb4_unicode_ci NOT NULL,
  `sexe` varchar(1) COLLATE utf8mb4_unicode_ci NOT NULL,
  `birth_date` date NOT NULL DEFAULT '0000-00-00',
  `home_town` int(11) DEFAULT NULL,
  `pasword` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `avatar` blob
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`id`, `user_type`, `email`, `last_name`, `first_name`, `sexe`, `birth_date`, `home_town`, `pasword`, `avatar`) VALUES
(1, 1, 'manno@gmail.com', 'NADJEM', 'Nour El Imane', 'f', '1996-02-01', 1, 'imen', NULL),
(2, 1, 'malika@gmail.com', 'MADENE', 'Malika', 'f', '1998-10-17', 1, 'malika', NULL),
(4, 1, 'imen@gmail.com', 'MAKHLOUFI', 'Imen', 'f', '1996-01-23', 1, 'imen', NULL),
(5, 1, 'yassou@gmail.com', 'MAKHLOUFI', 'Yassmine', 'f', '1996-09-23', 1, 'yassou', NULL),
(6, 1, 'sabrina@gmail.com', 'MEBKHOUT', 'Sabrina', 'f', '0000-00-00', 1, 'sabrina', NULL),
(7, 1, 'souhila@gmail.com', 'ZIDANE', 'Souhila', 'f', '0000-00-00', 1, 'souhila', NULL),
(8, 1, 'hannane@gmail.com', 'MEFTAHI', 'hannane', 'f', '0000-00-00', 1, 'hannane', NULL),
(9, 4, 'houari@gmail.com', 'ZEGAI', 'Houari', 'h', '0000-00-00', 1, 'houari', NULL),
(10, 1, 'younes@gmail.com', 'CHARFAOUI', 'Younes', 'h', '0000-00-00', 1, 'younes', NULL),
(11, 1, 'smail@gmail.com', 'BOUREBHI', 'Smail', 'h', '0000-00-00', 1, 'smail', NULL),
(12, 1, 'miloud@gmail.com', 'LENOUAR', 'Miloud', 'h', '0000-00-00', 9, 'miloud', NULL),
(13, 1, 'adda@gmail.com', 'MAHMOUD', 'Adda', 'h', '0000-00-00', 1, 'adda', NULL),
(14, 1, 'ilyes@gmail.com', 'NAGGAR', 'Ilyes', 'h', '0000-00-00', 1, 'ilyes', NULL),
(15, 1, 'sid_ali@gmail.com', 'MADOUNE', 'Sid Ali', 'h', '0000-00-00', 1, 'sid ali', NULL),
(16, 2, 'hattab@gmail.com', 'HATTAB', 'Noureddine', 'h', '0000-00-00', 4, 'hattab', NULL),
(17, 5, 'dahmani@gmail.com', 'DAHMANI', 'Youcef', 'h', '0000-00-00', 1, 'dahmani', NULL),
(18, 5, 'meghazi@gmail.com', 'MEGHAZI', 'Hadj Madani', 'h', '0000-00-00', 2, 'meghazi', NULL),
(19, 2, 'aid@gmail.com', 'AID', 'Lahcen', 'h', '0000-00-00', 1, 'laid', NULL),
(20, 5, 'chadli@gmail.com', 'CHADLI', 'A. Hafid', 'h', '0000-00-00', 8, 'chadli', NULL),
(21, 2, 'adda_boualem@gmail.com', 'Adda', 'Boualem', 'H', '0000-00-00', 9, 'adda', NULL),
(22, 2, 'zioual@gmail.com', 'ZIOUAL', 'Tahar', 'h', '0000-00-00', 8, 'zioual', NULL),
(23, 2, 'benhabi@gmail.com', 'BENHABI', 'Mohammed', 'h', '0000-00-00', 1, 'benhabi', NULL),
(24, 2, 'lakhdari@gmail.com', 'LAKHDARI', 'Aicha', 'f', '0000-00-00', 22, 'lakhdari', NULL),
(25, 2, 'daoud@gmail.com', 'DAOUD', 'Bachir', 'h', '0000-00-00', 1, 'daoud', NULL),
(26, 0, 'yacine@gmail.com', 'MR', 'Yacine', 'h', '0000-00-00', 1, 'yacine', NULL),
(27, 0, 'fatiha@gmail.com', 'Mme', 'Fatiha', 'f', '0000-00-00', 1, 'fatiha', NULL),
(28, 0, 'karima@gmail.com', 'Mme', 'Karima', 'f', '0000-00-00', 22, 'karima', NULL),
(29, 0, 'fatima@gmail.com', 'Mme', 'Fatima', 'f', '0000-00-00', 17, 'fatima', NULL),
(30, 2, 'talbi@gmail.com', 'TALBI', 'Mohamed', 'h', '0000-00-00', 6, 'talbi', NULL),
(31, 5, 'bakkar@gmail.com', 'BAKKAR', 'Khaled', '', '0000-00-00', 20, 'bakkar', NULL),
(32, 5, 'chef@gmail.com', 'SI ABDEL HADI', 'Mohamed', 'h', '0000-00-00', NULL, 'chef', NULL),
(33, 1, 'soualmi@gmail.com', 'SOUALMI', 'Mohamed Djamel', 'h', '0000-00-00', 1, 'djamel', NULL),
(34, 1, 'kamilia@gmail.com', 'CHIMI', 'Kamilia', 'f', '0000-00-00', 2, 'kamilia', NULL),
(35, 1, 'amir@gmail.com', 'AMIR', 'Imen', 'f', '0000-00-00', 1, 'imen', NULL),
(36, 2, 'boudaa@gmail.com', 'BOUDAA', 'Boudjemaa', 'h', '0000-00-00', 5, 'boudaa', NULL),
(37, 2, 'ouared@gmail.com', 'OUARED', 'Aek', 'h', '0000-00-00', 1, 'ouared', NULL),
(38, 2, 'benouda@gmail.com', 'BENOUDA', 'Habib', 'h', '0000-00-00', 21, 'benouda', NULL),
(39, 2, 'mokhtari@gmail.com', 'MOKHTARI', 'Ahmed', 'h', '0000-00-00', 16, 'mokhtari', NULL);
INSERT INTO `user` (`id`, `user_type`, `email`, `last_name`, `first_name`, `sexe`, `birth_date`, `home_town`, `pasword`, `avatar`) VALUES
(40, -1, 'hak@haking.com', 'hakker', 'haks', 'h', '0000-00-00', NULL, 'hak', 0xffd8ffe2021c4943435f50524f46494c450001010000020c6c636d73021000006d6e74725247422058595a2007dc00010019000300290039616373704150504c0000000000000000000000000000000000000000000000000000f6d6000100000000d32d6c636d7300000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000a64657363000000fc0000005e637072740000015c0000000b777470740000016800000014626b70740000017c000000147258595a00000190000000146758595a000001a4000000146258595a000001b80000001472545243000001cc0000004067545243000001cc0000004062545243000001cc0000004064657363000000000000000363320000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000074657874000000004642000058595a20000000000000f6d6000100000000d32d58595a20000000000000031600000333000002a458595a200000000000006fa2000038f50000039058595a2000000000000062990000b785000018da58595a2000000000000024a000000f840000b6cf63757276000000000000001a000000cb01c903630592086b0bf6103f15511b3421f1299032183b92460551775ded6b707a0589b19a7cac69bf7dd3c3e930ffffffe000104a46494600010100000100010000ffed003650686f746f73686f7020332e30003842494d04040000000000191c02670014464d6d58536d4733394e6a5432637177614b315a00ffdb00430001010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101ffdb00430101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101ffc200110801df01df03012200021101031101ffc4001e0000010501010101010000000000000000040003050607080109020affc4001c0100020301010101000000000000000000000403050601070208ffda000c03010002100310000001e30249770fea02a921c6bc08e698432e84d6a218a6cb95fe35da6da363c177044edde83e2be8cfcf5b6370ae87cb9decdd6f4426b5ee5dccba32b7ec38ee548dfa25cb3a543962ff000976d2a36084964ea9c4f09d57cb37517ee2a6c1eb6926f9007fb61fa46d273d8fa6471adaf186fb1d649de73c7d0ee66a928d626da8cb6c898bf36885b8db95364af90db2f7cf97bd8e225b98baae8157de693dd0727e83710819b954844359296d3ae79d3a1b19c76eed89c25ed0a4735182312421045c458148ac405648d211ae352767375de788f48caa3def01cf12d4c758138b8d857eff004e436fe8763a4ec79dddd7f119c0cb7a2559325256062b2a5c57df9c476f1648538a59026a423dbe8cdc9355cdb6d132118fb7d0d854254bad3967a0b39b5dd79d2e3b1f9d7ab639cbbf49794f5182c1bb8713ecf46e39af91bb2f8e777e633f37013fb2f3eb4cfd165acf3999e77b663993d88d69a25a6b6c16c7906d19fb8b23f1fee735d6371b31fd2a1c84ab03b72c32a422b226d78d18989657508d5265a2b9399502fd36d13fce0d722ec69be10559cfa39b1fc97d8c8fe9bf3e727d4ab25deae3835c77696eb72c066dbafd7b91f51c5ecd4ce11be3d1063c8040d372c969602486d4616baf7867e977cc4a796125a9c6cb1767ebff003d7bbfcebdb3a438f7e8e7cecb2a8c4fb53972f751a1e47a90d25eb1e0b644e4968f2cad35bb4374d52ccf75ceebee7214e0d95d55a4d086f8835a3b3bd4731a3b698528bd3db254920b8270230a1d5284ccad286f74da2c45c50592123db9aa1f69b9b6c8efde917faf7b069ae9ab255da730db7a566ebefb9509ea66eb5ee6f3763abc910364ca89becc46e31d4dc9fa2cc5809a91ad43695093efc4d052500d135f503e4e6f34d2f55f2a7516ad8abdf97f57fa81c4dbaadc63a8b9e2e317c7dcbf8e7f473e49233db72ebfe54f75225c7eb8e964ad73b2f250937615b25b8627f53719e87f1cf00fa5df3c906f51a4ea3874b572fa9609a244d7558c6d6e8b788288c9644348cdea508d670909a26d6adb568b6d7bf974beb57a85fc9751d226ea2fa266dc92afd1ba6bee236920ec67e229a4a32029aa416dcf33f3aceb24a02ff10e19bd3b63886697188fd4eaf7b93afee3874bbf57f43aa52db1e765f9894deb0c0744af4cf70fccdd5917baf705d22a4d180bbddd9e2b75cd7cd3f41b9199425e896db208e144f7e725ceed162842757853a4a214b1dffebb7c92fa5345ab27e237db5e3dc96c7e6d9d0675fe3da6dc17a76666e4e6f9fd11351286b4ab71b96b270abdea5e6eb2d15b41965f4f2f60adcb0fd81c09cab7dd92841ab9db9355f0be24b255a9d903b9fb4e415bb268b0b72d6a924d668ada94dd3e9e37cb735ced2626fed44ae5511b446bf5f9bd926e365aca253b5e1ae10c63abf131a643aaf48f9fe6c8afd7ac2b909b5dfea2c3aa557afb9d4763e549b979d2995549cb039896eb80e97cea4a4954bbcd7bea07ca9b6d65d7d32ccb8f744a2d3643cdfd8dcd5a7c7d2843c0ec7d4740eb0ccb37b1c4e5efee3c55cdb042573ed3cca1f266e8b24457a97a05906af4ed6dc4dc39cadb48396989a954845ac864aa5b1f35374da96911725ac0b51a1f416b4472e594d1c6b97b261b0c96377519539ce27a7610539ac6dfaacd438e46dfe11b8e8157d6ab7674d8dd7348a6dce5a8a44e425be7f4869db253e9b20a96a345bfce579e4ac2b0db6d5daec764abda09240b4c76ed0314da28d5f89756006acd463824afa93e9044c92c5facd7e26e518334ea95fe9ada3091b2509da713f51b1ac436d3a806c32e9a4e76bea4d1222ad53e233f40182b4ce3448d6d7d6d22ed0171c9ec6c9a455ef59fdb5cae3136da8b9922497549daf249cab66b83cfb919578db8c4c72e6540deab6ea9cf15bd8e817d4f9053b67cf2e6868156d22b77b920b43a758c7ab994e9014d4f9bd9375d696538409ea2e50bdac929fa9c8d8c5ad59306bb54bf9bc41dfa7915f96476027c6dc0fa50c0cde0fda10282e480d16c9497a9d88ff537475aa2deaad675d00e82149979f1a1029a59619a7668fc79cd0c9226db1b25557372d328ba864b6372d129b7fce6baed698db42b603494b92b83056c6ebe484922494ece1212ff0010b7737abe914d7d5a7653b6403e860356d8e93694d8e406871ba2a6cf2daa6e65301dc32eedd511edcbddb46c7739abe627d17e03d1a1c96e47497ade1343cab55c62aaf1a9063cb6ae5ea7c0749b0eef54e7307ed1688d4127246d3ac94eb9a7836dd0eca8df7028d9e91b0e409690a4b96d18e55ec889165223c943686dca36ed4fa3375684bd65b6d60bfd374da7bf9bb42494d6036265a0e4b3ad18944bf3fa72a46d87d896ce273bd22b7dee67196089b35eb756bfc6bc9e0d5fdc6936157949c6a6aab26fa49f35babe1a2fa3119cfad6299e6fe50e87f9f5ea99d68e9b07d1f1d083343f4708f7c00c87461871b210bf4f920ce607d68874d9246ce9d52d22224432e1add197b575c6acca6aba58d7f6e5868ae5a5c12ab49581ce3c35826add457c35926e595d1c958069bcedcda2f7529b86e2f4dc41c4b68b2d22c1571cd9b14e2f0c8a09a4d775c8d76375424957e3ec0425b6acc491311608d7228884b204c5463956d8e8b7355cbb49d332adbf9f39a2627d0fca6c9728e9fe61b556486f65b415908a7d71e8612482ea31a438d821d20e9db0425a305eb137698d9bacbc84afdca20f9a2c6d80076be3fc920acfe014729e119c913a08a2648db024d03698db2555a4b131b2f0dc4b596b5395735b25ead2f1da5b65aaf36ab73f395126b61b6bb086914b8c1035d23854439d8ce03f1e7c81c41adf64898c976ac23840cc8e96ae22b77288654e4fc1ba1f9e3d07cd637750ba52287cf9f7db04aab7cfe97b6d37775f25ec6b9333131937132e781710f3c691083ae6c90164f37f689f2624d5da761481cfa878db20522e3444db72471a3591c9578d264898a7899770d250adb0924b372d25013f5efcbcb44c971a9b92889255b9bb0536c11b5365c5c921c92323bd489684511c13b1aa0926d423a472c0a899e4fd7e6209ea2a39cfc48ac6352d1acc5cc58beac6edb0769d8eedca194375c4ae571bda6e6fe42ebdc72e95c88c8992de55c6b2521514721eea11a60661debe97afcb79e7b41c757c9889873c75795b4bc922fc780b7249366d6db963b479149e5ec22365a2c483811ab37312b0b37c94d9684928df3a7e3a46b252881dd09a92842ea59b337564ac5370830318728972495e918e208a48989223663513ec6b042a0580919b8422e73d6b06dfb5197adc405409739a4494b503d03c5ec952cf35accfa1f0a9b1a76c135e984f598d84b244b6ac6b842ea3d5040e6e07d895a602c6ab72cea898e76a1024faea7c2bb41f4abfab56fe24c95a92a959d3590eab58255ac92d4e92ab66ec6d026e27eff25499b8dabd9b006d4bd6972bfefcc73e7549c81ab1fb57484b2d1de0c4a5110877d13ac47223962610de484bad0ca11a9b8d6d572b764c1985737ea7e4ee95b5ce516a539fbf8a09babc413eedf9128bb86392f96f5db4735fdd7f97985f59e507466bd02ba22225c26ea635a347ed6f5818149607d9c9b4d7ed35ef934db946fd7d55ac9393f0fd8d68923a9ad011cca7310d1724bdc4e9726e5912683934513443873bceb53713e2df76db065d235ed69aee6f24897f55b35766c8a25e269551de2f604b91ff009e9365d7a5a20e8d4d4b24d9308a25db69b185dac86f5605795fcf3ea5fccf6f293f6dcbb55953e795a666fef5f9168b64adec793f53fa37cc59be2f85f59e5903f007a4e4ca157aeaa338911f5f4929bc1fb3c6cdd925abed2b6ac90012d259be6f221bcc7665699119bab49427621946c6d9212e346bbf0d4dccc2cdad66dcb7e247bd054946c03aabf5b9eaec91b8c1b6343d1968c1b5acf68af52f4db025a0927226494617ea3e483df59446e1d1ae0e4b471209f293708bab40d339d3489aabebf7cd2ee5e16429b9df44cba6f455152ab6bfcbb6b9ad57bf399bbf11b9e62f989f773e0d5bacca1c8da674727c5d1b70741db1b473e6ab8bf54d02be3422f66a8a6e5d0d5b72d573ae33d7e93cb46fa675189cdd1159018d6c96cec0a14171719bccdb45dd781cb89618d11cbded94fce39b45169ba7c4d14889bfdad3496a392c9555cf53cde7772c9eee4cc0c0af70e928663e989297852238dc381901bfd46a1ceb54d3b2e628f20b253aeda6c2f4cfd24f941f62314f705fcd7fe85fe49dd57e5d6407a7999b977af77ef94bde7437cdf4b4d9d69c4b43589b746021b710746d930ed2337b1b746135b8dfadd5d4458e77d34139a8538ea8ed259d84213b393683220b17bf737a64126316ddfba2d57b9bee5d8d40c5334996a2d02f68f13e73b6c06da80dd1222ed58d56e12ed131ad2dab615a8c173a10f08ed15f58dd80355925dc1a4871e3235b8e77235b84f9481c1aff009de8f2e4db46bb37c04a9fb453d87bdb9f3c636153e92f231d40412d479abe8e6771de7c730af540f53f317d0eac504390ba0667ae81b2f56493dd0f37cfbb1d3deaa06d94d966c865ae5aabe603eee1b8565c713defa9b51ae9b9d26fb95da7b2e39ea26b5f8be732f74cc290fbd7b338de77eabce98c21bd033aa486d0d86ac16844e6ee612af7aabb085374da4e8613650a553df7925112e492e4c6a8e6241081f988ea8b79e354d1a6a76febeed68a04bd55addac95b9f46d24b9e3a0b933eeafbab5aabda2996d45444ee1bd37e7a703fdcbf91deb1e478e101c96f3cfa351ad4ec37271868bb4832002b7559c80e8cb2730ed14d7ae6eb90c44775dd5b673c741a9cb6c6dda9c81a61b09388cb4826270178e94e2388e23b38a7eb709256917ae491a5cadb01b7666e0606705526ae57e7c2b342b77f88b22c49a9155afb4874c4868467e218e3a1dda97501aa4a5afaa9c9388969dc7a4638d49f96bde7971ac664a3622c957f737bd502f543637fb25489a5d3ddb943a53db1a6f854176e7187b8f84b0217e5955c1cb04700a39838389201cd682e1afd930eb457dc5cbae3827c0fb4637c9ff787d48b07c7c5159f72f11d6dc9914e92e168a5d1a5cad35ad37397a887626ae62611c048bf02c93adc869c6cda9284d4d855f144826c7580e57d538465a809db8a12648636769b351d0b4e908eab71c7528986a6e0269593769ba94dd2df5fc9cdda465d5ac1cf9cccfd35a38b1a1bd6bc70bfcfe7f563582bbef80d0e4206d240e2481b1c81c26fd024b9666b72eea9670ee9aec71b66b6442f052e34b437cb48576adb197978836bd96eb66c22a30fc4da25855ec6b22d611b244a8e68808d805d4848d72af775ea806c23d44ecfc4d9a7897ed389da9324312bbee4944c90cb830c140bb971a0e8717de9aabe144fd82bf4de477f3771ccc57363e6a47e594ec692f41ffd0a780083201c6c770084870713ad00ee0ce9cb89357d453b9805756d7bfabc8d82d15dda4dee365bac4dcdd5dc8dfb03508dc0d12869baf9cdb92b424e8524e24ec9c8e7212649539525aa51a9a30d714439ae3ac74d39a901f21f1cdf964efd45935ad9de44824248d57886d0bde8792ddbe19b967740c4daced922634cd1e35243cfd27c1c804bd7c074f321ef8190093a30389b40421dc01dc6d02bfd20e83ba8cbd6d21a5b2395c355e9b2706447613adb86c72a926ad29dc2bfab6d35c235f8f51c3a21badc70aafb79dda50aa70cd5ed2852c9ff00bfa2c639a1e264042c99f70244268d1232613091b092d54946c437a2a2b8c056e23aaa4e3ada21103b9d241869c00ccf7c044240bf1fb1c0344204db83810398383668c40363a20127105ee7735d5aa6e0638d351b9751c4c6fb05bb2c3475e81b054d9c958234d46d5d886aafce3b4d84a03f9c97a94239719d264a25dfa6e45f86412ea3468e4b0795f6ce5902adb4c232f101022b251208d6756a1249a761fc3e9b1748c8f07121c086c84092409e65025e9211dfb71021d380936808487021241e85e361237ccece80e8297cdeff004dab9b25b717b59236b737235699fa01aa3fa4c6d1225066db40a4e2ed64ee5568027459cb6c9549c98b4aafb7c6a49b8c44525ec1b7d2c0d569de432ed0c17635f8f0495178121b9c71b4382440e0e12d381eb4e84043690103908136e0e0e2481b4da02128e091439030e7a90bbe1b81838e0e400e886c0e9fab0f01b3da399c942dfa889e54709ba8e239f1d22d568b5222554919cf6c11f10e474f4e0173926dfe1b2590198509fbfc7e7f53c697e9034d11f904cb6d8383b8d81039838225a701b6db343c1db404389021d36043640e040ee38093681b6cc40bd1880f7c1c90f136e07abc40bdf07070721b0713681c1d380428a9106dc1d018db6d810e0e80843a0210e480c436e011f95fa08f21360ea8d240843920336e202074e02487020721b071c1c8049b1c0c4db808771b07136d8271b70113e200cd1df08e74c1c61b31b705d25e80c9b40e1039001b4ea183504811090ba1cc0c11087021d189008df1036ef88125e83af068106e3606369c06d3881b71206dc418189204390d83640e808f7c40da71b06d368081c86c3fffc4002f100001040201040202020105010101010001000203040511120610132114310720152230162332414224332534ffda00080101000105020cd1e0e2b839715cdc14e5cf468b8a92bbb6468425c0e065db2906ba6e9ad04164a2691f1753c316d992c3ee1f80d0ccc60be621d1de0376bf89189c5f4fd32aac972d5a8a48a4defb6d161723ebb7dad2d23f47b1ab61eba75edab9bcfdfe1819ed3a59f6def12aafd3f1b79ac42c365196c736e43729ba299467d46f695231c489e6d6fdf4d5c6dcc5754fff00e901ab5fa11b47d03135e8d36951d56f3c3ff49aabdc26c364f82a7936bd9371782cff007ea8691695aaea01bb268d730750d0f662d4d0d71a1e90635e73f856f02ce131088e004a8bb7d8140f6f6518bb74d50ab2d0cfe02330dacecdfc2781c7b12d6aa58cb978495ec5750976e095c155b0edc6f6bd67e9c7a998d08289eede8a99ba18ea7f20e1bc3420eac8b94ba72113901a5bdf62cda2c68527df1462519730c17952bed69c3e55bbfe57fa7ce6bdf1dc6912586e896a90b4d9a4c98c199a2d10da1a9c28e2da112bb579d6cc57e370a28052221ba0105e272f8f2061407be98f54e431bc65b0f37caa7836ff001993aaead6a963a6befc1e3be041d6f87aacc78519f719707c32ed07b4b32555cc9e5fb8fd98fd82ba6a9b4c3725267ccc5cd15bd76e0d4476276831abc48b1cb805a50cae8952c9b984663d53c8edf1df6e8e4d1b9fecd22d9ecd20d1075158d569a5f3cf07b5186b48aed2a71a83a87d5de2a40ded2072e01151f6c7e33e462aec5e09844e583b8e14e95a99f3c7142f18da2e2bae719f167e95a5babd37136d2fc97916c508e2a31b3c1c0c674c81eed64bdb2d31a146fd185ee530770c3da6bc0e3f32f59748f5aeda5aec5879f0fefada2cd2907a91ed46542c4204391707c39ff121d5b5caabd4754aabd435ec2c6dcaea4ceb4333dd43c94369a5f1de6910da6a8ee3756ac34b3a91696bb80a489cbecd566e7a58ed6132b61a321bda82fd8a3374ee471b281c5cb098edd6fc9357843d13e6f87847b68d3ea4b9fc8e663f4618bd0669461af5016bc5caee5699ee3634205ac27db295c7507e26e79d6579316b7fa7008b1789ab805b524ba525e572e292f39cbe62f9b2af94e27cb3212cc152c9daae60eafc9314dd5b2169c8d89cd49dc5432b8082d6d7cc76ce43fd9cc586cab48f62100a4f4a9c524f3c38ab14e4a621ff004f67bde5612bed53b4ea7274f586df18da3f0eafe51b4df374478fe3755669b8ea64bb9c7ecc12b906ed067a8226ea7f6cf8125eb537289e0a8793ccc1c0e0e7f159b03e508c7b417fde9c51f4247b753de841b979ba9af37535c71124ad2a4979991edda313b5f0e4220c758220c558798f0136bf80715fc13828e84cc3fd98b1ef6933b1bac9fc86c323dcf31d8f5b5cc20f69ed3958697c177154e8e631b958ac63b1f57056320a6c1d807c0e88f02bf15cbacc9e3f17f214adb19be9ab0eaa72d6a6bf90906983ee363b7f4a3282fb577a42c60dfd798efe3b2cba3f19cd649fce7a67564cbfd7805c020c6eff00a841fa135a6856b27a16b22e28d891e8caeee021479aab8573d438350609a08c5342871d0831d36b51ab0af86c5255685344d04bdd5cc796998fb4faf2d5ba7ffa417212b979155ab23d4914ac33bbd8fe8b03d6536217fa8ebe6ab53c3c3569cd8cafe1ea7c4fc79c31a1606f1c765bfd5f4ed74f64e775ac853ff630dff7f6831bb2cf5f6a3f463e4a1e3e6356b9a7f95f0be5c7d26559ccf93af471333dda12bb756e3432463b5c9abe94d7230a7c8b432e6551b53152173c702b4b45414e479ab8a7154b0ed0a0a70c4857f7e051b34102b9a2f6a9a56b05ab4d79313889aaecccc9019a8b8bcc4eedcf4ba52e577987a7a9dc664fa1da46668c98e7c21c4f44c4e3666b95cc3778b20ce556d8641d28e7a93a49c1182c53a7adc90d186c2cf61ebd3b428d8608d8d2a4f411f4a31ea0e511c4cadbd8dcf639b721ea4c5bb07961636b6148502a43e8bf4ae5cd32e5e76ccae289721c8ae0e505373d0c528f10aae32150d1840113740207df6e6bcaa7b9c14f986b15acef9953134a35a130f73566946aa929b51a2dd989635f353b5d3d98dc03271bc75bd58e554c578943d54da0ce95cb5cc8d9c85fdc14a56cf664ab1f8676358a7af1da665b1d1d5c8195d39e9dc67cfb977154dd4333d39730e8cfb319da93fb012b552736587f115a6dee9ecc4ad193fc93d25fcac206a63f5c7b4f73d5abda7dab5eb9f35bdae0e2a1aae260c72ab5746189aa11b3b68519d22f6a8ce9fb6e8cad6af96d45ed56af4615dcea9efcd68e1ebe946fd0daff00bd397811a6e525376cd172928a02d5751e62f442d5abd69495ec28e8bb9f4ed88ead3361b28e9dabf211aeed6665735e2c686678dcb3898a1aef86e36206f342eb0cd37f8a163d0b4e28641ca3bd0ee9e634ff00c7b83c3e28e673d0a833ad9e6fc87d3d0d3ba7d23dad4ee2ad3ddcc8daf169435d414da5411421040a8ffe31bf481d88cec944a27d7372254f79cc593c8bb7ce47bf1b0733bf12f90e2a066d089cbc5ebe32f0234dc51abe8d169468b4234d7c346aaf17bdc8d5f3ed054fab7318e30fe59ce2ff005ed8b521eabb16219339a34fa931b5de7abf1a547d658979b59de89bc3f9afc7ec86d186c5c8f19a601ce68e8368bf19d599ef30ce5ce51def2b3aab2b565c6dda0a46e82b9576a6a6ed8aae51d37111d176a38b82faed1bdbb8dedd46f6ee33b20adb573dbf6d5cd4d2b41c85c69648fd98c6d63d84307dd5aae79ab45ca0a7e851471909526311c6380928c8c46bb8af8ca489c4167a316d4f4cab55f4a68b4a666d11a50fdc0df56abb8ab54d48c702bec92d58d1ff00cd35c9cd312f888392bc71f8ae02edc8f1590c9f99f648998a3b8d30485abfb315aab0957286d0a6bc5a7c83c0895f6b7a5e72b9213a865728e5f65ee28bd79d496b809f27b56ad38a12b89a506cc6cd2ab579be9d36ea0aea08bd089789a4089a8b02929c6f52632b85362b6c9319a5351f4626853314f5daa6afe8b346466c0e4c7d45a715699c44c17f55f474b5a590e223a54ebcac82ac21435e160cdcedb5729e5ae513773aec880f447b3ecc83d10a489da313918b4241a24a93b7241e1aa3b0a3b4dd9b8ddbe5525856afb54f639aded43138be941a105750d6f754685566d42c51c48c4bc4bc0e5c0ae08b1cd45ad2a7a1b17284c148cd29a2da9abfb9aafb9a2d3240d2293dca13a5703b570b4a82adcb0bf8aca3e107481685e46ac796f386f57aeaed8dd3903918b7db6d0b7e917ed682d05c54e38b261ee40a628bd4961c0fcc50daf5e75e5535cf535e690657147da113b756276e9b36a1fb822f503140cd28c690faefc1089aa467a313548cf53556bd642869189aa7aadd4d5e4d966d189ac9a3639842c9d86c43e1e369d3c07e39ea9ea6ab0fe38e9762ea1fc5f98c75327fb8fb1e908a695f93bda879bb7d89fd3976914a5ba9fd3d48c7133ffc265e542e342f9fb46e2361cf5b6f68c6cc15d47c58a13ccd30ed42346a850851a010672ec508908b4bc4a48b4a60a41ee71cc59a121791eee31c54d13b76a2518d9f8eee058db99be88c3d5ea3cfe17a526ea380743d796e65b04efc7b92fcc7d3d47a73aa814038ac6d58ead39e5e7369c0f6e5dccad41ed5b7291eedccf72b52b9735e56a9bea781c54d5ec3178d48c6a3e82117250c4e7a82870118e0a30e955360dc0a189a4d562aac1b8d8d4224226af0b51890623f5a5205344a78915233667c7354f5dcc53c48c1b42bb5a63f62ad5e76bf17629b2f4de32e58105dcc36bbfacfaa9d91c0fe49b5f3ba4be946f72c9bda2848b7bec389fd058f65ed713cb454ef53bda5485c83f4b6d084aa6fb5231c490bc5b51b1a06f4bcae5559cd0aaa9b1c0d361e1031551b10c4e4102a34169bdb8f62148cf576bfb907a2a463b53d7572abb7244ed4316d78b83f1b138753f41d58e9632095dbcb334bab6e73b9f922d55393dbb71a9ac6d6dab5fae9097980f76f9b948ff73fd1537da9bd3237bb4a3634831235f6bc4a389ce0626a86250b346060dc355a1f0334a00a9c5b007a0543ec463407ea51f6a76ecc8c459c54834a40d224a6de172be970d4d21ff72d5cb18acae07a86eda8f19d73d3172d7525cab9f596eb1e95e95a7c76f870b61ea7636accb4b976e2a4ef19da08072e05163889e2707c95d06357851aa8d7704792dafec500ed8639471282ba869b82ab5d5589c080aa8503b403da56d427b47f7cd725b6ae7bec54ca60eecefb9bda236a7aed2ae577052336ba918ef92aee773c61e8cb920c875b60a4e99ce2f912703c8ad3888d8eec7f584e942a30bc4d462d29a2d99a2458d3dcb1a57c5695f16142aa15f4844a0aad2aad5850620d6850a858a141ea17bb7bda84a0f776da0a42dd83a5bf44a99ea608a2c524488520f572bb5e26aba3d4ec73142798b83d742d5fe6301f90836962a3f6231b422420685e26b116691f7fac7f70fa30b1c4f06953b111ea6f6381edc1a508b4b4e5e142ba11286abb71c1ee00b8a036a31a5028ca05a820544b683d735e44656af2ed737044f6da98ecad383e4ef682eab66a3abf53b373744f4f3b359bebaabd3dd6f733383ca74e64211a4105b537dcc16fb95547330c5b318e080527fce4637658d4038a2c6fe83ec31c506358a266c33ec0e0817131a8542a33b00e842766340a0f720502b68a8ca2573ed22e3d8a98a2a65245b1d66cd56a8a3971a3a6b0f8a774963e7b396384f875f2781cfe06c74de481d212b82054de91ec7bc234f87d20b8b517342ffd9530da214cc76c31c010e2431ad4020c77603d8283da806a08141040ed467d03da3edc9485c83d79579579579113d896ee54569ddbaf06d9d3a29b0f4d53a5d2d803d1d89cee129e6b3157a3bf21dea38eaa68b6fd621c0a05c493b3da443b46828cae68bb7dbc4d47d1d2316d066d081ac420692626b5089ab8ae2826fdc7ec04020542542f773050411ec5ea47fadadaf2f605485db12ed12b7dbe8cebac47ca30f4f36f64e96168e772782c766a960fa51efb57333d25d2bd1799869bafcdd5747ffab97b8d715c7b01b5bf6a1fa07483d6dbfb7d8e4d5196e8713df8bb71f687efed46805a728ffe1ffb05465097484ad4656a2f6e895cda097f60547e91f680d227b6f4b7ee63a5932d764ba3fc94f19d1fd576b046ec396c28fc69158c553ea7bd2656ac19539559f8aadec2730a176c6bb1f434e2889010dd28ca07641d9da016bb1e2117fae68145ee51cae465d212b8f60765050a1e946f77687e876df6f3af2b9195cb9a2503db6b9a8fb70533f4a43b5b57255f3b5707ae8ec6cf5e8f4c7e48c564a8dc98478ec1da2e337ceb58e7e3e08dd84919a9ab7d28c2902faee0768c3b6100e04b9a1795a1492b811ed5589c845a57070797f1467d88ec2f2b9436bd4369795432e909546762328141e16f6a3ff9ed795a83da896f707b13a41eb6b92da90e9fb6a9164a56d7ad49f5df0ddcd51a79bc6e170b6ba2e6163a9bacf376a3b99b2ae8f7d2b8ab198a43f17b72b99cde16e74e64c7d298ec7621c502820a18b9a274267a32b8cc7d986bfb821d215da46428b4ab557c43928cfb0146be94761db86c6d4763dc12a865f7cd795abcba5ccae6e5b76fe4144fa2f7212a0f6f6df68cfa1c492fd1236cfa521d33ac2e3998fa6353cf4f2d6ba97a64d7c1c34a2ae3f25f371b3b5702c0bdb15ce9ae9fa5d418cebdc5483131fd48510b8fe814639a8c3912a60e7c822728e271556be94312d694cb27ea13f756baf12e0500b8381e2e50cae0a1b1a51da77386d2f92bccbcbb5b5e55b6a0f6e895b4102dda2517b9432a90a90a2a7b0d8a1c9d1af9136bf1fbb1d2756cf18ea5c4e770f4fa971185f17e4e0c779a66382b8ba0a9fcfeade89fe49f9bfcab45c71e4af2b513e96fb051851ffb4a0f6885f15aa3afb356ba8d8d0b8e873572d68dab5e750d76903fa2e684ae256da4f61f639022c382f9ca1b5cd09d47ec46763b6fb7251bfdc6f5b4547e817b9176d17abaf841c30a77ba92ef4954c8f4de7ecbb1dd49838ac75cf526772b8fe80cdf554b45f93a7918ccfd432b49c1c4de9de94c2f5ee4a2c07e43eafa762327d763dc20170728d8e23820cf708684258409b355c239891e8de988b4f73d00d0b6d5cc227660ed1b1da8e2d8e0e088d2f3af2b4a9dcd50cfc14169a546f50bd0734ae65725cbb7f5ec0bbb6da0487687a32172c94ae96d7e373a3d36db55719d5b461cae33a5f3196c2d2ea5fc73d40309728e4ba56edacc344f00b1911571ff00ff003faab0595a793b5ead7628f78d9b51d57282b842268478a925015dcbb62864c8dab8fab57d8635836d539f665d2361cb7bed0a037da1fb2a67a9a5fe93d89e05366ac030e5799ab7155b1b01eed46ff5b5ff0098fd9e289d08ced12a33b1bdafa24a92c2379bfcad2c9bba372d81c9ff003107e58f9115a92f7897faa9d9aea1b579d7e4b9566e781b8de89e97fc7761d5723d757abdceb49e5f3d9dfe9a5b54cb4be1634b34d0ad3d4d63d5dc8b4225d2be32d50cad5b7291fa4656a9ac375cf7db4e41ed42c43a37e05fc8c617f30e46c5a94405afb39230886d1dba1fb8dfa58fb0e221f60760ed2df27c6f47ec16b806397d0dada2f6ab961acad9097ca7192cd58d564d4f218de8dadd498beafe82b7d3724dfc3d8b568dacc63358ef936e9d1cd5ae9ecee631c3f21e6a8e0b15ff008fdaacba7d3bcdd496bd49695ab1b65c7b9795a85c684723185fcd694d919ac084c850f6816a32b97957976b915cca8627d85d3bd3de533e2abb199963b1d76e5f73c0feefa75c291ba14ec382a53ed42f77625c3b6f4502e6a12eca2ed3148f6accdaf5033cb3717167faabc195c0f50e37231f5cdfa27a6fa57f1d5718ccdf4bd8f37e30c1e1ff008ffca583e9a8ba5face8370f89fa03b943b87b488e7f5f28a9af34326cab8b247b9eb4f40396906356fc0bcbb4657040efb05a2a9e26d5c38ce877153609b4198689b1419095a19d5bc481c8a862d9822462530d2c658f703f6bcad45ed283f64951a9038aff00d9954d2fa32e94969ab272fca9a389cc14a2f669d8de30f596014df967252e1ba3ba9aad587a9f2787cb53e83eaab18ec64998ce7e42c87e45c7642c6564f48fe850ed56f69412ec70695698d2268bc47ceb9a8cfa1ecc83da8ffe3fd9461da8684d61637a4ac3c55e97e0fc3e0e1890a308196a6de11def8a3319dae064efbb233054abed0669162918aa9d4955ee41fb1b40a076a33a40afa5cca90b8891ee26eda740c819b31b1c4c316847e9f19d8c9f4f437a6ab98b1823d1398b97f2d063ae4bd618cc553a30f55615b7ab67b1d250b846bf5e2b8a8ded0a1bde27d3b4d78fb7cf55aa7aa8b1c807044b57051c4e26ae16e5818de87db2e745b58b07836b152c634436b19ea90f1291ede199b0d10f56e466af74dcb56000a0aee94d51a51a919b648cd28c384d4bea12d216f921e8205a83fd4851955ab0d00bfe54d1b782810299f719509d29e5ab61ff008bb95187ada9ba0eba8ced4c36bf22e024d49c5881dfe80a0b92dfba771d14d56e7313fb64d17a8606f9a3c157b460e9771b90f44d3d49d251c4b0b856b190e3a1881a30ee3a2daf340c6f098378656f371d3ff34d23aafa85b5e1b96a6bd34680d9c6d5f418d406911a33a8c68d5fa0ce3d8715c56d6d7954d634a4b0aed8f2a842ff00b84e9472a878951a1f59094d39fa1b395f018c92f5acd65a07f6cf631b7e9f55e09d8eb51fa2171ec021f7dbfb3d53b9f0d52b90caa40dd7c670581bed7a8437cd4a2e626a1fd290e0f818d5345b170342ab2fa9e5f5f92f2309863ea6ca4427bf62f3f4b83b54e9ccf30c5c19c0a2a7ff00f121ca163b9c23f53cb53723da67b49b52b75af2cc147d87a101e2a03c879783e7c763720b198575386931b5d90141ebfa91d73d33f3ab5caeea6f1da40e2230e0f67defb7ff0092079aab7266494f22d7b36d7a9a5928cf8cbf5efc389b0b7b531f14f05c6af3b564e5d0ff0056d7aa731f92ebc10e4f397331678a03820c5045cdf4e8f05aec59c912d7a918a366d47120c459b527a10fb089f665535ae0b7cdf1851a1c7b73d80aabf40bdcf7d32a9840a80ae6e50bd5a89b6a1ebce94d2f8fc4a08b74a3f611ee107b9871f9a6abbc6cd6e9dea8b1873d35d7756e4f0e57627bf1ca20cec6c47a8610cea5fc85e265acad8b0fdb8ad34ad6d00a11ccd2a7ea3638044b7527b3a5a728c20b457152fd02bcad0a4b1ee6b5c44d2f32100b4e0828beb8a1fd46d12a97fca37fa84fa8e5680256942e343cde6b19d79d5f0d87c8f6ed69dd8763fa487477c152c8b8338201cc30f556729893aaf3929398c9051e6324a47cd298d9b018d407b0ce2a366d63e87b15da816a91fec945faeda50b06e189bb23417df6255ab1a7cef73d46831abe96b6b8211375f480da906bb53fff00484b4a05492b82f98e536461aecea7eb8758864b134a76dedc9108763fbefb00e28c4edae0ed066908b687f5317d46c73d52a2d223669044b513da250314717a1168808844b5aa428bdaae4ade124ae2a35a71ec17f5d45e803ed12a42b7b58f5e5685f297955dc9d7a2337d473645e4efb0fd0071ff00082806a8c7a5e26ad6828ffe3c14316d53a6d50b1a173519da2513a20ecd5aad2a381a082b82d290a994dc42b4fd3ee4edd46a166ca0b4833636d51fb44a2f72e7b00e8d045feb6b2d9aaf45993bf25c9ff51da1ff000950bf8288ecf30b6b69bf7c1c5414ddb82bf0517dc67437a46c7a8f96b7b556276e18b42363b7e3776de95a7b429ac29a76ab96968bd00828fb47c75ff687d92bcad0669dbba7fda684800cba192ea3869ab97e4bc4773fe1d762b8f605150cbe2308ae5829d77834e1063a70ee363428cb4bf9e87974be4391957340b8aa716c435fd81a51fb323b4643e8bd5cb0ad5871334ba518da03d0080d140efb07afb1e4729a5f735846ced537bb625e2b2d9f7314cf2f20f7283fb0ec476db7fc050520f58d97d02a395c0195ba8e7f51cbefceb9ae72aff7169c55589ca066c063801e812d524addc92e94d2b5e2d586a9a55a7120686b634e51823b05b016da89521f524addcc5bada825f02bb96715bd9d7e850fbfd021d885a4505bec7d88fd0556573269bd001051a01bb5e26a110220673305550576a8226808f15bd232fbb52a9ef789492ed01b422682a3feac283f7d87a44af23948ff00723dda28bdc09974a4b0ed11bedcbb6bbf1fd877ff00df68fecfeb54ba5620c72e08448b37da36388a75f4a389ca381c5725cc29a56a9276856ac29ee22fe642e7a40ecae4b9841795a173686492b766d2925da2f76a47a25481c4e9dfb81fa03fe0287ed56e39930e2501b3ad20831abe3a859c440ce0a3f42144e84cff0053d8e0ad5c6a9f27a79b0e7907681f7b40e887ae68bd0b1a4655e57232b948fd33cbeb9b948f40ecfe87b0fdc2fb5a7773fb9ef8cb4d68847a8c6cf05c1bb01ba8c7a050f6b7a52586953dc6ab979b01b9936ca8bf9a8cb50785b6af3b56d6d6dbdc9f5c94876643da43e87e850451ee3b7feff6df62dd21db7de43a00a07681730d2c9b5ea09573081425f7e5e085842e382379ca4bdc164b38d10cd91b161fe441e83d6f68bfd09767cebcbb44f15f24af2b90b0e45da1e5537dfe9cdbfa9edb43f4d6fbed6fdfea7befb7fd7b5cf4aae4dd58c79aa3604791afaf955d1bd095f3abc4a7cc5353f51ab59ab52a90f21dff00ba85fa0655bd8e68947f43ff00044fafd08411ec3b12dfd37d876282fa7a9a57308ec11ed21f7aefb68ee0e80b5c1472c9b92cd8044ae45ee2b7dcfd0ff072edc972edbd7608a1d89efb5f5db5dcf7debf53dc2e5fa7247b7f4fd8f73fe21f7d805fdbb1fa1dcf73fe493f7df6d0621c4fec7b05b5f6a4f434a395c5eb6b6b6b6b5a40adadadaffaedc7f40fd9de903b24e97da3e96ff5d21df7fa12b68760100e04f608076d0fd09d2db4a1dcafea51f48773fa6dab6d287605daec3b9ff2143b9ee3ee4fd01efb07b91fa028fb11b3d0ff0009eda40223d7ea3beffc25fafd7e96f7faefbeb4bfffc4003b11000200040206090203070500000000000003010413f005101114214161a10615202331518191c171e13033b10724344362d1f12240426082ffda0008010301013f01d54d4380d901d839d5eff23507de8307568e44f77cf80c90d9e84fc8d1f7197cb2690204086783e0dd6ee314c3df81f73a3252a2edc4fa8c4e40c2f13a2f823d086dcb0f5557c0c5d7fb9413f4f8ce90d514b81a87017dc956abc64f189b6b1aaecdf93335f63a26d826761b610312c2d18ba611fa5e93a55d1842530a1c0e8cf4725d5235e3a3c0e96a9299dee49f5d618ad0f8185b6afe830c0bf3a0625f930ffcf65ad413d3e331449d689198c562addc46625bb470cd99aca853e17ec422f43aba227453a5106c950988c34e8f1313c520ed6e1a7e848bd09e8f476edd1e662b3dd60f8f088dbe434c1f86508e889afd54c32d26bda09ec63c86cfb8ad171e22a41cef515d1d889c2d5b360cc191b360dc2d3a0760fe23931f22966a60cfc887d3284630df11b3f1e275acc51a1a6236fd86efbf2cba0b86226d337c87268ba253bb88add7e435b11b3e367dcdde77f1b89a83e22b0630cc1a9788a90d1cbe082af61a2036790a35f4b8a44fe17586e0cf48d4bfc85618a6ed18aa4ece470c4393b49fc2e8fe46d1585bf1056f86d870313e8eba511a4d0683a26dea97f7dff2f931892adfeaf5f9cdb3c3582a46a921865c48293b052a02b75f96789e2947c06626f9c7987aa9102970e5f61a9e1c86e169f23a986f4746616e494a8899e49c45624886e87b18a630872280d51090fa12925270d1178d9e8361dcf9681bbfd7e726e16e1585f0152144d82f280a693f8a26813f3f58c1d4e12af012af0f4341a3814b872fb0d514aaee27a449e90378a555413caa3d9d31ce91486efbf3caad23ac2075af11b8c8d9f738528c295e1e8255e02f2594af60c9118aa43544fa6eff00c0d9015e1e86270ac2b0c1b20e4fd329257731ed4eefbf2ca7be46463c6e2558f9dfbe4a5095781852bc056ef4f8cd5bbd3e336ac6a86ab813d222d4492aabf6f1d83a41f5fb927e41d43be37fa8cf087d3b0add7e594eefbf2c9ac295dc4a5771295dc052b8121204848d214a297d4a5955e37ee4336a86a8a5c2fd85b743cabc79fdcc6713fe465b7b0a298d514b81aaff004f23ab1c6a0f1585921858a90d1bbcbee2856ef4f8ce914bebd86efbf3c9aa314ee5c48b635b6e9313daef4ca8bbb18664cbe452ca914b872fb0a50a58af82043764ced377df9e4d518a7f1b4492c32ab4761896e9a22a4689506eff005f9cd57c8a9770edaafdc832ee043710dc41a9ceaf1bf7caa5dc726b7288d90ad3b5c6ae88c6a151f12797bf26aca57b725df2203370bbe5d881021d8a855e37ef96b5f520dbb88c6e71de349f6f7263bd284c27b524ee302c4e18b48f1843f42a6dd1c6f714eee032f964a179d28f13517de927b4a8d229a55e37ee29bc4d22ef9152ee0299daa97701bbfd7e48fe4fac4c57ba918bb844c51b5b1c9c771f93f67edefa08d37b0661a8fdee8f8c3447de1a7265f2c942f2a57b48681ada437be714ef666a2a9af20aa2ef976e3e111ad4b551a3ba3b7d223d5ae48461b3c2262b211c3f1b9c4e88e8d31dbea74124343abed2ad184db864f1578dfbe4a60add7e455bb893d89d13ac86cf956ee02b77a7c10177c866fbf31ada2759a4909fadd8a9c6fdf265f21bbefcceb3dda780d5d2d3c769a55f93fdcc4f0643a3a75381862753477127b8c4dbdc76151f0dbe5f0423c49e9f35f4f1e46bd02a5dc055fb8adde9f19b77df993f3dbbd0528908d17120e83b3a7770ce7e7b40ad2d7eff0013136f73dcf013dee8f3197c85cf6c27db5bb0a69578f3fb8daf777114a15222b75f91a3282fc326b49e55576c1491abd060edce1935a4fb6a922a350ac3a428fb122aad1a037a33fb97127fb88d0ec2a7dc48cfa5be3e235629453bb88a527f429c3ce195587035a22defc52b69055ec27a17ec486ecd65527a7b29150a317653f38e9f230afe32052d9e874c706a5dfc3b30242793955e26b3c4ab7b464f9ad7f573155db7b8548eff00a7dc82be99354295d86b7286ef421968d248c8ff007157c8c56420f4450f314c33abdf47b30153fbafc8d31ca0de37ee374b4919112a14b29f1fd33a7771cdad18c162f75f967015f0759d1318e91a2553c4c5313eb17d7edc8b4a6294404ff6202ef964b179346b788d68abf721bbd080bbe5928aba37dfb189e32856fda3a7def7f0fc05120df0db9d4e37ee29a29b9297932f913d3c35b12192b75f9655741aff001173e4ff0048bf910f71b18c6f8fe143c6041bb3fc8abf7ca04377a0a56cc9ad1b3e3725e552ee055bb813f891578dfb8c9f77e2e18dd6bc452a914c82c4ac5df31add04f62855e3cfef96935ea26bf5464fd2193e35bf8f06d1241d0778c4534ab77014d4dfa1d608313c6789ad7d0aa552af1e7f735e2a952ee1fec9518a778ac4dc75a0cc51c33137b7359578dfb956ee3ff4afffc400371100010105040803080300030000000000030001040513f01011216114203141518191a106152302303371b1c1d1e1122543164060ffda0008010201013f01b44fc6ae4a6b32452af1ff00b6733eec6f7c978609a238f25e7a613f3385ff0075058a4e632edb72863f18c7f5ac09532c6a6d8c5137ed12ee4826f314c5364a8796f4e05518719ca5494346287150d2ff007568cba91883322dc1160de520bae5e4f39b7e77af0a38c92e2dc334c8c4a7e901e377d105b7d65638fc1532a9b64b52ea9a88294a3b883da4e3133c39adf9a81c50e63637a8c44da53c805f7af0c14f224b5094dc144ac8bdf214305eb584aec98996084990c4585ce5ff001702f27b832589c3c3324f3ce852436386eb4434fe592c50c7ed2f34615ffb518834a3fe142c2d61767644098b1bd9bd42bdb92165821a8a09820d8514e0a238c93a94a5d535084850cae884e2052b2edfab0aff002916281458c21475abce6720bfece5f6427efb2614e845b2322319987050c7ef293c836f6fe15f39184ce09c4392142c13e7e17a94c670424c5e267e388cccee426d72532a988829d64acbb7e909c77fcbecb05342b4fcd15fd14b6c9319a84e12ac13fca4289e75829d377a8bc65f5d0ec91b143226d3827376e68ae179e7a1b6edf5822c4cc9c627372438849321460256f342d8c535477d60de8454cb1c6c215695a85258e10c698cb40085137d71b74fcd6999b508b373f9a144d8cc305e68afb44e274116298535cc534c8c43c8f45304d137d6e2982f4186b1c0aa655335655531104990c361f94e01bba231118b2afb26d53114aa767dff00684fdbafac2f4c2870422a0976299872adcb7a702cd58656cdac17c543b26c95a4db2f2ae8a5562ae525044b0631176f34fc5b09beb8a2577b2f422a13f213f6c426a28d0cb2428b1d7306776d50c8a39457e0edb1f5bebb02c4214ad51a626295957452ab05732c236ba27f2dd55f844aedaa44e3b908a825422a21b35e268a497265c9c7c4ee72641dd39f45017ffee5d347c18db1c85ebb4ff5d60fe13108b64daa6a9b54d452a2953f154cb0967b75dac658e2dd884fc985bee51c861cc0e8990a6ffb31abc250bd87e1c796b12bb5822a6170afc21142a6e75d569c15a70515f914b7a2da52584aef68ebb5822a097620fac067251287b041649633e6d50261981f722421d851b2c2935095dad9997d6c94855d532c11141fe033e4a3118941bb7ec50f8c9dd6e412ce030dbec1d74d7115aa6ea4bac54aac6c9554c44f7024e25d11c73b9166bd9b9f2afae5bc42f57d6ae4a16ffebddc93350a3d59688e2a56b12bb29554cb2555314aaa66a0be3b392f6fe031997e138b88676d5038331ec13cfc944e19e5cfdcfee83b3d9f933ed632c2576b1964d655ca68508b3916ba295692c229554d52ea9bace5f1ba7d94bf597fbddf4e55f450614982ba67fa5e311fa17b36a86461f673a30db3633e899b932c257644b0a558e76437757044aef64a9a8a25294acbb7eb52529554c5294acaba29554c531a24e13a7fafcba65bff0057a211a27d661c3eca16fec2c15cf97d978b1e7f9064336b70ea9d427fe4e81e0dc50b63397db584e135795b168993531177d71b66a66e5a2e5d93f38fb8286e0df720c4e6beb982b04fa32cd9e0c70c77f0e8a16fe7ff006d89fda631f6e1c5407e33d6fa6265a512b9044982cabaea15682b46671452067a7015eb04fe29dc1184895d755c0538e9f99e849bb7704e2e2113ece33a21142eda5878f35836ec76ee4586ff000b821f6a9bb71acb7a85b8e89ac213386acd66f4fcff002598705e70733c5d9fe97b6e3b1bf24e2c940e48a5cd093f094baa6a9554c43aed64a50c14adc8a4b25845f0516733d7ac138467d767cd00b7ddcb525655d11452908aaf5a727e7ec10e269f8d377a14f32641bd766082e3e8fb3cbec9f8b29972bd0b757045d49754d4212627faed60c6c5142ca05cafc6f5e1389ce0dcdefc90b51a25a36763e8b829266af2b6a0c1d0a199560842c914b823173b04444aed6cc4e22321574b0bbeb8d85bf14ff7d72b20cfe7746f350f7f9c067cb58ae3bd5d9295f252aa9a8429565e8cfda932a9b63131c508575935149612bb2bb2447150b8098ade6c50f70920f704aef614b612f44aed613756eb589887612bbdb2be68a3575fb938c1e6e2d4170007dc977d71b095d6d9558228ed9754d421210ad9754db18aec94acbb7e9384338f74c15d4cf74444aec844b4a444b25d531045b1045ac11260b2edfa5299c3b7ebde92bb6a12bb58212138e5c3eca56a884b464217bf2a28ad2a9754d428619084ae576564acaba295957452b2ae887ff4652d0325a064b40c909c03ab2fff0015ffc40046100001030203050308060805040300000002000412030510202206131430322333400115243442435253114450546263162145647273748225313565843646939460c4f3ffda0008010100063f02cc445d598702c047089602622b561ab0d2aa5421eae6c84536a95b48f421dd92222cd12c234fa87a110d4f6329532e94315ed4a92972c4454b91125d28a28871d4888454793ab112a8289c3518c50b5a85d2a586952a6de48b79ece3a70e207aa1d18c7025a90d31f697d3cb90ad4a439223922a448b0960448a3c91f2c74e228a8f94ba9691d28a8c752214223d2a88d34372118d49e038c74ff60222c84a751531a283f87c9ce8e522c259648b9455ab0f4a21c087e142a44289710285d61e6d1c057f66051452c34961a5536adffbd17c349797c9c89166eac6325df28c948bcab4d45112444590b91a55315255070ecfa4baf0d28b0a89cd4ac9cd6c497eb5a7d9451e925a510fb4b560448847a89790479712e95a726a2c349290949462384be9c9152e4108a6ce0a8c854bf02731c4485361c09ba2a689b8f51022aded1211f6b1245870f47a8d17c4270c0456ac2459238479038f4ae95d2ba574e12c8451c239a9d454c4a3bcf60d3911d429cb82a688868a212c235b0a834d5475f082a852c04b2888910ab3df18c9e6ba46f80d5488c69dc35860eee45d3481542424b4e5963a56a52cfd2bfc974a8c727d0bb343bc8a2a828a3975215a508d654c7daaaba7a91762b7d47068e8539a83d49db8f2fb46aa17b44a58472b61f8abd20e84dba6a90d0a4a9dca98f4afa2a14513567ed63ab1fa7cbc994574ad4b4f2a59f764a42b48a26f5057ea4358bf810b5de6a1c750a1dd8a7bbc4514c9ad6e920d68a9b32908a1adbb28e6171d3bad6ad6f24243558d14e59d4e9aa09cb38e913d0a25ce9541c251e54456a51ce3587a56a25a8b18d3144eab28a892908e0425e4d24a3188a21a3d348208b7c24489a8d35c454eef1128e1da0890ff00027ec4a3c6db0e6a990baae4557eac743bb457663eb22a25d59e58e9e6c658c8bab952a3514516f148b0d45a94a4a4a2a22a4a42a2384910d42d4a521c65d2be245781bb5c45f95bbb76dc750e1576689abae92437467eacef3e9e4756490e4909292d4a43cf8c89108d65d8d451acdd7d351aaf5788ae9252294b01a328a8be2755510d3b3ba748b836b5e9532e805225bb1f8d0b8ddc8875c0d13767c2d2a73d6b795884a0a9b81ea234d99f55413435073c79c5923965531e9e44b3eac34e0f6a486420a8b72fef521ea43be70443f02ecfda4d9bd41de8c35822acc46b8b62e80a28652922a351454724569e64b26ac0489479652c8598b29448b52691ea205aa484456a92252a7534a1937112fc00ba8454b27b3cad4b49296619612e614569c74e414489750af456b5eaad367aff00d80b765a6a7c189490c8911225ab12e78e71928f374ad5896ed4494708e1142f2e42555ebbe8b67754b75f3dc38457770e06c965f60ee7d928fe9adf789f6cdb59fd169a2bc5bde35daab5fb170b7f7bff0021be41112d4a8b51e5c79512cda701e5cb26951c822b4aa94ea776d0275d39bb5eb55aed21c7be0f99c3fabd04378bd69f62d565fd976a68b7d53856b4e90437344375bc437eb794ac8ece178b7a2716ff00f4bbb50a5720c49c1222f01a50cba7983e008bc8a390856d090f56e292a95eb7515e309562e909a736d21d3577b5d6c6156ea9d5a13c468d3e46acd12c244b4f275611f0921448a5d428516990d5574b188caa347d46f4c7f32938f5854e232a65f0273be1eb4db67ed644f1fbbd0014536b2352953d9e0a4130eeb8bfac73238cbec7a6b8a625ba2fe0ded2569badaebdaff486d95ab1beb4f7555c5144ea9dc9d6c95d3dfdb2ed4374d50f13b7d696adbf7634edaec9d42bded0bbded0fd27b806ebcddfd3a22a85222d667ef6a555f48d615bb12978dd5e2054bffd69a895d9d55a7d10ac9838a9275c5d7f365d421d953a4e13d631f46dfcd89876b4b7580d31c34f223847248b95a7c544969424a28856d1eceb0711da8b81b43b3b63eeae3c3ad9eb13b7045b516cdf1ed1363fd9f55c7b8cd2e569c23c8d2b52d3e074f2c94553a64e38591eb3ef777453263474b06571e3df5cc0f74eb749bd32b47e8eb69d5a16ada3ef7ceaeffdfd56b6dd2890b9a5edfbaa94bc0cb932f1b244a9daed25c56d0dd9f553bc51363ddfddd83770bcdba9c908527fb5b736deeda7dc115c89b8dd3626d8f6ef6962e7f35c7de1ba6d6dda22af56c84756858f683dedaab7ff0065aa2b6b821ab1ed28560eeae2d3e7b771e174f8c2ca29fba7d12dd31f41b7986f695c5da7bb5f74f4a7b3dc58ff00abfbfa17037675c5d50dff009d80fb27155c7de1baba6c76d0dbc7861d017a6d46853b55d6938f5756bb6d82b7156db4db9a5a5f007defeb08b666a46af081c7ecad63ff00d870c54484a9107587cbf05a947ec216a2ad76b63a74346007f32ab8f58574a2cffe9ed9cb3f986d53ee9c3bfac5757f75b38f1d13db65776c2f9653ed295469f3dbad9ed89ba3c17edafda18b63ed5fd8b88a1c47a42bfdb7c8dde5d1cdd6c155831373dd79dff774c069f14d6e836ea540db775569bb6feaefd53bb5388d3b98747cb76dfd63ecf9648e0f5d174dbe84c3f9bf57574da0abeb36f63c050d7de5dee29eba2a322b9eccc18d1ef379776eff00876e9db7a7b4542c950b74ff0068dcc37b56a3bb8fa4ff00c9e0d6d0edd0949e89d5616aadf31db8f7ead747552776909f133deef2aab3df08a378b7ef6d375fcc68dfd21bd75b43bba7221af4b69589ff0051eb19e43ca8e48fd80e5c10890bdbaf010fe9d59c68c77973b8ddee4fbfe3ad83da68f1fe69bfbbe39b077b53885c456b93575fa42fb8f0b7fbdb7711ee1592c2cf48b209bef9bc5b845f87422a8de32840e7ef15adf138d3b420eed26d939a3f28e08bc16ae796af03588be04da54fd2591bb3fe6710ad364acde2d9eecaf9a68393ee9bdddc50e2153b97d3c36d1bb3ab419516def1db7afe8efdc37567f25d0446e83e68a75e07bda55387f7e9fb8a7d22700c2a487a9591ab7782d5cb27d481f19fcadffa3d06eb69aa3ebd79b2d6d2eb56db6e7beeaa5dd39b5be2de952e8adf3296251c74e690e12251c659639624a39a5cc2c48850bade0eecacf4ba3e6fd61546b6971697ee2931b46ff67ee7fb4696e3bf6eae9727ed1d15d1c6d155d9a62d83f63273c1ca2d02affe56f41392f8ce78495986a5c0846aed1b483657f60f28810ef1dee403badeb8f7e9b3875227b697d56d8f4ff2beafc9d38910e72e577d9cb9a4445a4570ae08a43af42933bb50754e93199dbeb2a6e9c3178d640d0c2f365f5a6755bd04e4ab5c09fdaf68774fd8dd8c3fee2f5671c4272dc8753db3bbdc273faba4f1b33775aad74abf1efbfe3a7ae9b8c984d6d6bea712694aeb480cff003712cb243f472a39239a5e11b6f35539d23344ea88fa14f8f87e526efda0ff0088d2ded49ab4ed11392a56db7d09f0df7cacdfd6182ba3cb7b7e16ce21c7de1adbc3bcfc86ff00bcad997d6f1e2b84ed1f5b2b1ef6ab768bcf1b3f586ada6e7aff00975beb14170ef06225eda2e0f508ada8da91a9a8ed746cac7f9ae1341278e767c5c6e983ee258d7ffce9a6ceecebcaefed635aabfb8dc0fbdbabbe4888ad3935128d35a79f2964d59ba72feac5cd3f6b709d897515b9381783ba12e89a7234c845cf9d5d9db939b7d370f1adacebf1f750b67faad4a5eaede83771f79789eed05674c2d6d8684fcca67bdbd6eb7ff584f59b38dd1932e101f0560ecb8bb8a16e2dc5a9523d733deffeba1a94dbc9cb47d498da8288764e3ef1c4265b3e3691ba3fb7f68c689ffa553bbb8f7ee15aedbb445c793da156747dd53ec1391f644e01c99164d384888b1d3c88e11512ea5a62b54544b19648fe0cf1c4a5586936aba37c7eed32a8e22f2daef41b9a3dd6e90c9e714d8fb8ad3eca9ab3b1d9db6bcab4ec3bd7e7730f7955c22bc319521dfd23b88186f78776dfd5f884daf9b44cdfdec842953006cfa8306ae387f57e213917c42d6d2d1f55b91f0ddad2b35571fbc7d66e68a34489b17a89fbda94954ba5d29ff8a5cf46cca6f5ca813ee2f59c3ddd6543fd92ceeeed5d39adf157aa8738c724548b08e3d4b4e5ea5a4975612dda891605869e6116244884536de6a69bfa467457036b96ee7e8213ef3eb0a9bc78eebd37f5420607dd27b505b8ba184ce7dd5c5a7d6283854dc5bf4b49ebb65c2beeb7757e4374dacf419b5676ea47bcd0fa86e908d121bddd29680a21d95aadc868ed46d43eb73b9c18d6ad6af45a755799d8b8664eeae89efe8d2e23f3dbb85706e5766b74dadda9385d7863ded2b35a1bfb86fc8fc3ca8e95ab2c64bab19565d28be19f3a394857d24a34d5aefd46daeb86b4f08171f95c5b7f584d2e4cea0930b804e81827e5535132a154ccd36bf5dadde747b763aa767b2fbaa74be7b85519bcb78db09c51abe6ead44374aca4f2dfc30907a71fccaaaec2d598908dba6b642db46a39e3c6cf4abdd4e7dafa4221e4484b4ad386a2511e6e9a6a5597622b52d5cb8f2096ec7a70912937896f7bfb79f75510d1b2be8b2dfcfcde67d92bc5b4adb26c410ba9a1a959d469bd6344e8403dd37a1dc22263e9f52950ab5e0143b554e34e4ed8ef69d76c61dad34d89e52261b216faf3303ef6fb55bfb86e9cdd1d0f572b4e11e56951f27529121de0a18d3518c702125a896a2e9cd1e612917b5977d44b7553db0f75510ecebe703bbeb62e43dda7369b7950b8d47a1b8aee43b2a54e92bc37da20e16e82708363dd765f575d88ea2eb33451155a8972ba94bd9c34a8c544b18c5762dd76c87722ba7561f488a8963e8f5976c52cb1c0b24b26a22c64448b3dd9c54a3289e8ac9a135a71e36e3334c9e0e927614a78d47822be8e4ea2d2b4e22484a2b725a856a15211251dcad22b50ae943853974963bcadcffc384854896ee9e48f9723b6b2ecc8ea9a68e2b5b49f4a84e822bb5c298894340063584854b37d18c470eac25242dc8b52151c921c051211a759445d4976c52c2284485088e61cba50e02894b26ac4949d37912e1dbba2dd97c7eed4652c6288845137a83121c74e590a961a94536794cb4d24d9c53f6c31121c4bca886a22dca271588b2cb2479311eaf051551cd114425cb11acaa128d4a32a68a89170ca52d2bab52d55857ac0a216652445f166e9e7eae6ea5d4ba908a911445546745114bab97244352312c2424424b4bc52272a5c5128f144a44525ab08e322f01151251c91e513870511144d5892223d445e0e5ced439f4a8fb5974ad5974e5ed2b08a2a2245c37801cb1c91cf15ab93f894b2cb951faca2f291f23cbe0348a8965d38eacbab1d25848b2ea521e9cd15a49752ddd2295444553c10fe2c054897b59623d4b4e411c056ae44894b3454b39356f191291797c3470ea5d5c8e9c7565d25934e5d438f52eac65e525112f1024be91c9fe45cbd5810e1252e48a2c2386e7e2f14223cdd2bab1d5c8ea5a8975652f18284870d3934a96022ba726ac4b9722fb037245ab992225a4becfd2b5472e9c848b77d4a459a258cb24b3eaf011e58e3f870eceb2ef05692522acbbe5a73ea2e48f879611cc39e2a3bb225d4b4d621cd2ffe0e3e3a45ce8f2e59e3e335789d5f60feaf0f2fb07fffc4002a1000010105070403010100000000000000f0311011203040002150618191d10141a1b171c1e15160ffda0008010100013f21c2fe8bb21beb2c746e4350a5324828c7f7fe8d0bc3233b02f09940c11f772190bb7f99c434a23ebfbe09f133287f6c0e7f5fd60341e04b111d2b3ad91af18b59ad62e1cfd3ddb2a9e7fecb64c8214cddada4797155c21e717de2f6522e9ba6cba0432683e6c3fb474ab1fe947819dfa59323ff00b5331df88c26edb631b70e9d39b67a0c12476d8118e0a0bd7ed11be242e61fe0feb59fa3ff005b29692901d00284d4e9d9a601f37dc5de0f30ce140239eded43da36d2b45d7fa3a7b873bdb877c7b0103bd9613e6492112bcb23e508ed7ff080848a1a42c1420a0f07897a5a400f98b47cb0d7c71aaf6cc44737a936d36aaecef606faef30a70c58030119945377b9d9bf0e13e1e6c86f30477d8efc735f2deb51d9369ad06747db47231a36eac9025480e69c104ce66ebfc0be92ac73319599340e9fe0b8e8ddbe64b50eff0087b99d629cc013250b88441bd69a217063a42cc5bff2780d100a43f3ff00c86752be64d08327fd1835de25cc7932c1c99a820a1e8183742eca21e8700937c8903513e38d8c0f03d6b16aeeadf87bf758349ed8f8ded958024cb465a62343276b07a090190439d92b421f4c603106bac9ce999e5f7fc7ce8a1a2f867bc3c9f333e1a0b0039a56a8a77390101a1651c702c80791a2301f8b63a72bdddff399b2aaa3d3ff00b6e5a4272d4fdbc9f46b2edb6ca59a646465b103506dd2d8c5ae4cfa077f8f85ddebb3b7e02ec73cc9524b9bfe974be3b1c1d0b44486c477dbe397fdd8a01efaaccbfb5a181dd85727c572c4b78ca57488fcda60238cabbfb1efb1177e4e08e79962bfe7ac02fa49e72bcd14e96dc882b30b9715ae502831eb47c0152e6b6e1e62cf69952bfae576f6c1e5b6faaba9fea728de4625b2242e265807f34138007f5438b4d950ea9f82a59286f07fe9c6ff0052b5d8324d0fe2aae79d08b39e87103e477681db00ef66fdc7a477bbced66bc260dce601f47fff00ba9222822ae3034cee041dab4dc45f56ef63ca99debf97255bbbe8a38c1fd5f3fbfa703efdf70f7c030eaaa5d00c8e9403b2105c467433f9dcbc5293daeeb1e4fbda3bb6ca838f14897eaf40d0347204d708305b64c4c3c51e29a6edbf6b723c31fb625aeef7fafbeb1ca399d57a2aec77ff0049ee6bbbb37c524f155bf4d5fbfd1aef5fdba1c013cac74dd3f9fc6c2345f19bafe7f5a46c6e3d20ddf0ecdfbefb0f161e20ff0073b45eff0038be03e0abb0f33699ce13e00db8c904570027a09052338ef38eca6c074a495ebbdb3dfdbf8c3dfdec039bbd563758dd13d576caa266e708fb3a901eb8263b4d321c5212576226ff00edb719fe9f5533c803e5eff53f18ff00f4517b7fd011534c70ddd30137a229f6ea710283f39674e2195bbe33e8b7f9d1e074876843f96ef819de0ebedf78b7d937bffefcf13709442e22cac87cc4411f2e123cd47cec25659fdd97ff00fbdf45df5d87883dede2cb63ac177e6064febf1068f92a08688002358b9e990f9003bc75ff0078ccdc99e3b35e4eaa6cdfff0089b7fbd9d2029d07b0caa5c577ee08c509d11fce1f2309635908e0103a7ac6e6b4d9752fd35c6fb96bf75f828a57d4d7f5beff009e1871f538b1b3069d646fd0e901afe1e8c8faba198e57781f3bdb5893cc79661e8cf540f6074805e38583754bcdfdf1fc03316d836880fdfc844a100ec75e13e85128f31aee77a7fe6a3ff5df17e6beda2e98eff2dd91fafb37c466e3f3d0cefeafe922530e92075ac00308c545629659611a4822487309d0f4b78cde1b5acc1353ddce6fe9a0dbbc40675380ea935ffc1271c7060867c8404d3940a4c1979ad84f9b08cd2c590cd02e78fa66fdc1ae0c71de745d763c41d26bd01c8d32fd7bafff00fbe04e4e67a603260a8e08e5a001d472614f3901a10c1f3bddea20b905b528abd60c19d17857950ed3289f0e4d05791ac6dbd8f9dce512324fc9caf9de2c138a3970da278306f0885daac6cb2401b2df4faa6a0fd913aad57a1b9a88eec0ce4936579dad5bf2d5b9910007e187e152b962bc600cf43167589d61d9712d4caec2b10234df59ba67f49228ca43a74ff37d9da8f51b9427493834348317201c9f106c0524a64659f283f399db5d8c5df98b7f634a96500133171668ff00ff006fff00ff007bc3cecf7c033b0e8b0fd0e24ed406002a701f5da0b46d8d3760c20892566334b267cff3e89cd5cfb9ed8e53e598ed3f7a8f807c327ed62feba66836c40d0d10983212492610727240779fef7ffda6d50b3108de73d3ea3e30ea01b7146df8d7b2821a45022cbb18bd4a0c8e0e86043fb27e4544b4a2779ddb530501cd1f640405e388eda23a6a5043d07d442db002417f83b4fbfdf13deeb9bfa5fd91e50baebf9c114c79099c9a64e0646df7adefe7517ce7a8fdaaeb33d25769a5224214d1ce670acfed0bd8c132746391f6edf9b2fbb506bffb523e5fd5e40e9028288326e540f8537b35ed767d73f7e97f9e048766d5cb5dff004b44311105f8d4eedce24045d23ffe94f17b78f319e14eba3fafdfdf032a96234fd4cbf3beaba9277fe3c8f4da81e73b43507ac9cc41e581691c5a9c7b6c4a2798eeb85dd0dbbdbdd9e77ff61b5668af3f4c1e5526cb9a52a849f63f7da68f05ff006743fe674dfa4b8b47a62784ff00ff00c876bb77b77f8310bfda77542f4d3fced76baf50ef64d0606df6e481d2f0977bf81ccd728a89775dbcaaefe4a3f7166f8d02bb8f3f29bfdfc5d23f1390afa0ff004b3952a9a8c837ddff00ec0003ef1e741c0e97ff00e9b270ab7ffed2ff0057b284143ff60bbf1ae0ba0b5b45bd3cca89a1fe4c58a4fe29e9720192375c0e5c5ccd7017493fa68fbe0d4d9357dbee7299cde81d5e7ffe9884b35d4ee1e964e2ff00cbff0027ff0080fadf7fff00ff00b4b134dafbbf94ca9875c68fa7087fff00bfe4d5ff00ff00ffda000c03010002000300000010070804fcfe7fc122bc3fb57b0f8e907a316da82029dcd1146408de4e4b22147f5ab979983f7ab5fd0d943a9ac20003bec4e512243315a85100bc2495a57b423a59cc49c3c3b825f045dc7325b4c61c7625ea6077e875425e814836df4d786f0c40bdee9a62eb3fee1ca1779d24eb373a8051b9532554021778005799102d735e5b0e40818dc142d264acc2191f8208e3a9106c14c0274d2e40c2c4a5b188465bd7663d8e7ed374c80752310287d76c9e57c9312061a89a98be733fc5ec93005112441eb83791ff003ac8600d3e8224406414a19ca317e034de61ecad195826c4830022c1b407a236a84821a28474264133e182611113c80001e05070e338a83906846dd0090a216a038078e78db000c14038086fc40b4e8da02206b0068ca0f54253c40e400b2120031a8120537d0403b7dd013f4133c113b3726a90065ea04b57684dcf56666c2c6105beab8304a1a920a9072b47062d62d2521278d54045a41bc42ba3f1c2b93800043e7dac69811d0d8cd11290141924c112e24b82ca800b1d958ba164bc1ecc77fe6d6120a684bb6e9321ac010035c90bce701716145dbf98f9c5c6729fdb1d415b02408bf3114d6e2c98a584772c828f0855a61817b0c972222c0252f7e02638aff1bc58a30c0204a5b1380f9bdaae4381005ce7c98e59a5ac8390b5a218ec67988350050ad514400000bb0354b6c00b56002b2cf004060ea438a8c000400010863025491470294d3237c9a50057c987e1108000808300406811a612015125a7e34830403264980008010000108000b3cc1476475f073bc276c5aee0010040000401030000c22081c7091d310b3c83d80d0000800500100400000000110d443239c80a308036010812051000851002002402010410002000c00400080c32c400008000500520024004c2843000001c01000000000000042c000404ffc4002911010001030302060301010100000000000111002131415161719181a1b1c1d1f010e1f1203060ffda0008010301013f1016efd8e793bd161c0bf6e4e7cb9af823c71ae9a6fcd6562f7c9e3d7b75a676146963e2824b6a52213a597d48bdbce7bd0844e43b26f3c6df369e4ed6ede7cd4b9762e9af6f79a1046c076fc323afb1591d4f5ac0e87a564753d6b03a1e9f8c55a75c03e01dbe7ad11150c930c4212cf67cca472893c266f57d131b0a7bdeff006c52ca4337222fa5a0f0c76cd627936c5bc27cea6918b49b46009889975c5200ccc83e47cd5c7937ae0c246f687e1d7de863fc3569c1e7d6a0d8fbfc3b7e3a9f2f9a47ecf86bbdb39dea3b3b7ee9112824997c377edaafb0da5353a7f314125b045f1b75dc8e1a8206480ed6d5f17b4ed5625b4e9a6c736c70e2a4492513e313b52429b31599d7d8fcc659dfdbe62a4dfefd4fc04a1bb14e082d6b1b310d8ede951fb0c941b31a8d63e37a1b84972449856d8d7d2a23b9d40c58c4f782158c52db72e313269bdce0717ab26d6d27a26f79a900166660bc474c6db676a520b36974b16d7cf6ad3d7d8a412a1d50d0df9a537a7aa743f1d3f0c19b75b6dbf879503524db7b45fa917db6b5098622051d243441613ab56b95b6fc18dda821b917c3a47df13c4d0740c6b1ceb26dcf025195c39ce3e15346a460ed1b6de7d2aedd1bdf11f7351b95a7afb1f9776faefc7f3ca82a105d8d7e28c5ea2d402a09de27183d77632d444042482a06ac61fd6299888b174ec5ba430f2ac523460db41b05f333cdf6c14f9f080df479df7e93bd64ce96ed4c8ebec5498b9889896313318a4dcef4808a4236631da8467201791902dcf18746a4dcee520d422f9bd2653044ebb675ad35a85b7c64d71af150f2627311e9168cea0f3521bba6b795d67f9140c2059b117cd475ad02c2662fdad6cda76ab459d173c324fdc72d0e1744179e026d635e7d1d282c0b69d9dbf5e37a1c0cc46c4c4cede58b56506262dd13d8b75a02ca2e928ef671e9f0d4b77cbe6a1d9fbfd3bd4c6b1e3d3f5e557c13533d73a7b5f8af20f53dbf0a1000cb0a6bc3502ca60dde11f4bfcb511643aca116bb36c638f3844c04f4d46badfbd65d4fad1373b9f78a535161786262499358ced40248317398d4e634382992b196734f6296577885ec7efaeed2865b1bf19db3e5c5369cb2a5ba91e79daf4285e650cacb8db5a214cde271378e3a69a5225c6e5ecec650f1ef5a24eb283a17874f2c45121824d988571a0bcc1a5421c4690b47c54012104640b76aca84f3e13b5b7f06f52a0be904e31f3e51bd5dbb3c17c7ea830018dafa1b5b4ede3520c319226c00f70f5a9516130c1c86d38bf1d6f41ca4606152fe3b5efb45019a58eb09fcd0e0d054390f6f83b544e935a60b76622da4756637dca4b8597c2b835bbfc3ad1b21005851083eaf8517bac0cb7220ca76ceb99a50c239db67e62aeb066c5b788f6f2e2ad8a041981916b5bacaf89508064b0378d17f0ef5116daddadf85d9be2dd2de736e38295ddcbf5f5f3d0a733bf520d6c63136349a51047261b43afdd39a1f2234335006d193a4963a58d6f1a500c031e7fc511a7954c5fd6a2322c5bada31e6edc5102e98b58c96b75cbc4dda904de61bb3987cb3e9bd60742a6eba27df36bfe9f7b34b0d4de23561c4738da1a6f6c5fa8e868743fb4b9bcefce70f78fe545462d88c1173ef1449727138c8eb3ce348a04111702278efc674e4a92040913784deade04470612f6f5dba54050b11912602673b5ed9f0a15b012ed19661f1e9736a9217285c71f1e54922e425f511213c769b7343464191368b6a5b58ed6acbabf058d8d311b0dadc58d186b94cc64e9db3f7528316cc3e113b6d79f5a0018b113636fd79546d8fb3faf2a1b97b49ea7c5260783e6842ee355c46bf61c694157b96b4cb046471378dafd5930bac5e6cfd8b46f9a6ab1751b9be7789bf736a4c874993a5defa3af42b613028f020f1b6da6b4082c3e0541d0f87ddfcebefe0f879506c7636c6bc6f6e6bde6368331d3b957edc487831bc4a5458b4178cef8ce68201184f6f83ca9a29650d62d01bf87f429b17b5bbf36a95d67c67f39c5eb91eefe4525df2f667b5038f3e36e8636ab2eb67349777eff0ed5377521de3cb4da78a2c4bb4f94d020b3cbe69a186eeccc58e7a5f7e2a6c1919819c3fa35cc4c522ca4dc99392ef963a6c57a07e99fbb19a0049b4899c3179e37bc6946024d36e1fd797e097b19f6a26e5bbecf83b5615e263c3f65b8eb444c324d8b111e96b74e2a6e7cefe7efdea4102588c4ec7f7aaae4c6a7cafa3e5c51485c818c67f9e552001bb163683bdf5f0d29d06194b4eb2199dec7776a49777d27ee9c54c1749d7cbe7ceb3590d73a265c4c3be314d94d97fde79be8ed6ab0be061767bf9d0004b06af1ee3b952eefdfe1da9a7993f4fdf8281d0cc78dcb7a6998b454059b30cf16defdadad0605f031f7a9dff0039f57aa82417d0f6f93bfe1de5c4bf7d3e1afa78635f7b3bd4f9e173cfceef270d2c89c287831ba4f5a8c6d103a62c5b4d236b46b53d0b0dc3d27dbf75c05eda72cc6574b5153b646363ecdfcaa1217b42fc27ebcb8a4102043086a7ebcbfc2351a7d773bfe7224d9b71f773bd3b222ef966f9d6726f9db3d1f6df0f2a86c7dfd0ed45c5b534e68e85da68e745fef8e0a4626458ccc690dfcbe6a19e0f4b0df73b6315c3e5d21b7861db82a5f47dd4ef52ddf2f9fc0a4e46bc9ec3b560743d3f09b9dce9faf2ae1f2e97bf865dba549a4fdcdfc33c1c530161874e3e1448083a649b9a926d81c16da33633d1336f77a6f5d17bcc69b4c46de8c2cc1f7f8762bc5c678fd797e3a7e26b9624c6d1fcf2a1a0cb70dbe6b349c33ee7aff1a6c7eb4e7a795295ddf5a7076a5c9d339d3b4e876ab9e3ccff006b1312db49bdbb7ddab02845908be9bce58a28202341d05e487851481d20f3a36b34faf63b541b1f7f876fc4373cfe68424e09a1b9f277a2c1d0fc41b1f7f876a06835d3eb43b540e4acdd1eb8c67b71cd49725b93aebcfc6f4a72e8577377a18f18a60a233a193140a9199a102d68d478f83b563f25b48eb9f0dff04b916da90e3cfe783b541b1d8a96ef97cd7dbc1f0f2e2a2d2318f5ed093fba8d18c444717fe4fcb4208e1eb591d4af6bda8482fa1edf277ad3d7d8fc41b1f7f876ff001ee7e130da644fbeb275d9a32ed8bf2f817bddf2d68957495563683988b5b37ab98acb9659b4c32eb631648b1437b191ba131199f3e5a4c2418d676e9c1dab28627f200880314ebff28363ec7c1d8fc28bec4d3013b13ef4575d1a45cbb75d3e4ef5043580c99acd49b9f7fa77a86ceff5a9dff1c5f3a4999b6f5cdcc3f7c18f83f183d1f4a985194f1908e3113ce68650972f0c598b69f62a300958cd9bef7bb66f791daca8098491d306de18ed9fc6af9f9cdbbdbe1ab350fae7f1a7c2991d4f5ac3c1e85699e3f2c5224b993537a4417d0d75b7eab23a94362f7835bcdbe4ef53cf9f4fd79714208db239e9f277a1c165edf152753e3d3e1e5429f7a7c1da8801a0ab85f535e6968be849edf31f07e5117187d3f677a65dda7d66a19ab66efaf38bebdef429e165a6121246e5d8ddd6d0d42a4525dbd81e3a6ba6ba3725883ac5e284c14c869c535fe183d3ddacdebedf91b1962c5110c91991c38f0ab53742cf23fbde6f4282e96356939e33b8c98d61d2f1a57d268ef66bd35ed13faac33a6af4fd79714db5dd3ebb1dabae83a878c7ef8df4d7fd75d1b1738f47c0ec564fa5ca0eb3047366423b1d75298154116e30d6275be199a953218925c30e1c61750a53ba0392009ac2c867151043a11dab5fe0186ce3dff677a2dacfd9f93b950ecfdfe9de8b8b993d1cd0816306dc7c9de8067046648cf5f076edade7686ffce668e73f5cf4ef401f7eec7e246f8b77f88f2ab77b7271faf2e28b45e6c4db3bcfbd01040b93aebe15a7c2926e7dfe9dea4dcfbfd3bff985cc433d22aff454090ea24e8c288dcb96a0491621422c9860be9d726694889ac5251aa41e1b6d4e2e010dc4c4778db6bcd45262d698d22d9831b7353b19bfaa7f6dc74abbf6fc078f2e20f4c70ecd2351a5206a1d5348f61e5430089b168be0c97ce3b6d436066f1edc74d76e2a6b4de21bad937da6f3b5f15ba7c7ce8959bad381d0f4ad33c53dea5f2bbccb88dfe6a07248eeee6b6fa9e25026d6338fa46db968a1b0e30d4bbf9f4f83b152eefdfe1da8bce46b41b0f035c38a2896d2faa9376377a6f4325b5a3170e84c47974a5266e6e4c2f77d77e38a971151981564bcc4eb39d4d0b93823d02d87a124ea4444c25242d0a8a0da6c684a0d85ff000536b3ab9f89daac8c306bc1cf14019d22ce85b03d2a76f267597ad1688d8bcf19f2f2e2a4eef874f71dca94de74ccfd69e558f47e440292c2e7eb53bd32bbae7cba84c52378cba9be17b9afb14d22a58d6c5c88e9dc5e6f002be2cbb6ff007b54f3f7ea77a9373eff004ef40118d4d3a7b8ee5183a1521a96fbee77a8c910443a4b11c390da94b58176d9fbe5e0d11a23804c01243a6fae96cd04a7009b5988bfea5717a9673117ce3776a221820bc416892fae63c09d2915a3a9bfbff85c4bdddf3a6bd2fc52d1878b7828db9526d75b7ae288e8df7e755f1cceb9c14b645d273c3ecfd8a0408dbefb15c0f2e9faf2a0b925a4f6f93bd6549a3ad60de0f429a5266fbdf4bf891b5a2f3506e1c7117c5b3e87588c69b069e19b6c635a5bae3e26b31a6bd2fa4535c974267d67d9eaeb52eef7fbb1da8592fa9ea7c1da9305dc1af07ebcaa4dceff00773bd31abbc63ee3d382a4b7cce3f51a61b7147a873f2fadbc77a42bcb1e3b6fa79da80b63276c1ea7bd4b2caa63311accb336efcd1cf482a99d1d2fac734590223048e885e79075c787f8cd660873a0eb3fac06e55ae0422fbdbe91db142e11d81e719fdcf14524864c96892daf1be9a5020c60d783dc77a9b749c36b479795fc288cd832df4e783b558dbcb8fd79715f62d26125c39fef2522b30af9b3cf5e9cd237137264e4bbe587d8ab0741c70e78acd06f833ca8461199b474fd7e56ef77afebcaa66e789c7c1d8ac04716ed83c2aede1bef9bc7c9dea2cda378b4edd7b3d2b2c85f5e94d1824170b98da7ec5ef425932ded6c1efe7504125f54c63ad234b2e83aa3749b5b5f1c516b6d6ae9f95089688c6d4a8199c4bbdb465da6f42311a93f7b951d9dff005c1daaddbe3d3f5e543b23c97a3104c189f10f4f3e6a4e179bdea4e505d77446fa462f86b1c3ea2577ac4fcd081b034736f8ac54862788b64b69ac6d7e2a28636e8c45bd37c9a516036fc4c6b1f7f5e5593cfbe5cf5d6fc51749bdc99bea51341f47c1daac08d8f6f83f08b1066d0dfd68c6481dc745c93f6283005ec1e8a06264622f988fd5e35cd4d996551586ef378bfebfcd8896b98e23e0ab0498806fc1bb87b74a92e2defaeb42c97d4f53e0ed560e06bc534223d792de6733d69f2dc8905bde346fe1e150a5a2e18e9fa89fdd5b3a1e997f4fb15f69f97277fc44e93fd3de28390fbf47e4b54dd1fbcc31b9c14ea5dc97ed6f4cf1d6b1148ebe2fc7957b5f9647529805c2cf5a8406343b45eefd995a6771ea9c93cf8711a35645aec6beba7d9dff00d14c31b12f4c4db6e7c68292279fcd2651b6f3c6fe19fdd0242353dbe0a801a5bd550831307a1faf2ad3e149777eff000ed4d665d3de8e2de5d7f5e5f8d1d7d8a3d9ba2fefd38e0a47559fd5afd0c9a1d682b72719bfd63cabecf0ac0e87a151b4c45b3f827ac45bc7f55625562efde146a01843099b1043b85a352944caa72b096dedb6789ff8249ba5bd5fe55d0c26c794e5f1b9d288830636e3f5e5c5082327d8f93bd11c61ced4d09cf39b04ebc6f68d6be97c68e9eda694138a8d1e8c5f13311fdbbb50004c5825edf079528bed0f6a47011781432c4697c948425d2f38d9f8c5b9a172ce4c9d33dcef4162da1edf076af62926e77fbb9de952b24743a69519886754e38e5ee51864b139d48357afd8a4bc98486f88b39de39e6af0ab37bab323bffc9220e13d4f8ab0383c871c14d9cba7d763b5183a1591d4f52becf0ab4e87f35d7adf8fc45377129f7db8e0acc0f9f827a1d9e694c3b9ee7e0e20f2ebfaf2fc75d2cd7bf11f0f2ab36834b5b8c1f3536faaf9e9ec3b562de22f0e96d9cfcef14dd96eeeff00ce62fb5fb549881085f36ebc671e77906b299bddf6c3e7b144e18d9c7e8f53ad61b6df73ede143036d3d9c789dfc6ad0e2d43ba422f0c69a7b41d3628920886099bce3383d8a8adec5f04c7a23ca8643183dbe4ef50353b9431017e09b4fde23cd074b74e37d703a6f5d6e96bf5e91f7254d1cdc3b3c75b713a1536b3ab37f3f8e383fee8889092634bee47c45002058ca681179fe449b57d1f4fe7c340925b7f3dc7728612e84b3f5cf81a5aa032485ae84f955e835449db0779f076a5b9575fed134c775db7f0cf143833a6a71bdf6f2a9efa3637de7d626a3a47778fd79533cb3d6f49777fe9d3fc40e4a66459ddce4fe58a140b60d6340cde388e8742704ba1e9f076af0b68878981e96de209bd5b45c439c4069e1a52add57afe1b795c4f9e7d2a5bbbfd6876aeaf3fc25ddfbfc3b7fe23fffc4002a110100010302050501010101010100000001110021314151617191a1f01081b1c1d1e1f120304060ffda0008010201013f10f41d4d3fda54698cd0889d1bb511c89fe7fbfad4648243175c67ce14ad6678338bf29b638c5a03879d088847bc6f98abb9202432cc1a18befae5bcd3c27130337d38bef3cb8d0ea69fed009a02722f1336fabf0a4210cd8f8f4c1e4fc564f37e7d32399f348605a25c0b7589987bd04591887245ba6d508011802dd31515742535d0e7f933564972d0e6262385fbf3a18bf74fbbf9b5479199daf887b6dd4a70ba7911fe674a65e71f24514959be6d798d75efa6f25274962f6d3da76efe92196b79eaf6f8c1f4d49bf7e5fa752a5ddead49b94fcc6c14348fc31cb2d4c89489607210262e7b496a5e51326771b65ceb99ca55cda0594c58ddcf0bacfbc80c6db9199b25de3f95006654359f43532cb065be4f7cd0a415cdefcbec51709d4bfbd45724fbfef1f4a2897c6a5b1ef131f3146c09558d7423bd08379465215b45f4db62fb531c1799948b7b4675eb43e00498b6a4cdb1389ed4c04b20cb3370dcb6349b77699531be8fe4541030d89cba1c3976a2b286ae0d6cfa19004578ed4c4dcdeec70def1849ed9f4d74c8e67c94982ee0d781fcede88c4cddd6770e3ac4e6f1a52d9e4e79388e06bef50e1b98465300e7bbde6f4dc4635ca463cf73de6b690c8de73909fcaba930c92cea1133348816f0176c60f7c9daa4752ff71fa752945cc179d368a836f3c0e9e8b3338bdf4631d0e160d6a42f102deda4e21e666a5bb1c649137fada6ae849993576f9f1a5141491b14ba3783879a918841a42f06c66dedae6b6a0182366f01677f7a19c1240b10921adcd39cd0c836b82c710faed587a1db7f68f1ab8e25b3626f1e6f07a0a482f10d8df1a17a790908da20b3d5b6c19daa5bbdbf697a8fbf8e14ea119831bc691c4cefef49651713dd9efe4d4699260998cd9f6bfb635a01164116f67d2840052c1af0a1da917bce4c399c676deb737def992f99d33caacc702fd36f611e7bd01b8db3338b467e0e53ad1211b8117b4ebefbde74a4d4fd39b98ef832d6306f873b28f74edad211c926f1f15689b29ac6633fb9b71a124849b45cf6a875da02352d8c5b970d61a155b10292b0384c98ec640464841ce649e9e630a190d9c83a649fb7638500b30bba1be987ea7856a50998899633698b7b69420b824b126dc7954ed2e03e7f94a02fa1f5fced4257882c480a1338b1313c2a425730dddffa2b83e293316e57d24ce3c9ace2f57f96df8ed574845d5fa9663dbda8804c5836d8fe76e148de652faddb71e5d3853621644074e1cfb71a59cb9c93c2fd7b70a7755d61f8fd0e1c0f491d475fed0041048588d4336dfc1a8a366d37b9a76e7331ce8402f8d6797f3ad4d0e31f1d3df6e752c2e816fc84f58cfe506282489bc0db83fd96a482d9312c16fa9ccfbd00184424b2818dafcf3456232c92179bcdfe638c5381d10e76ff001d686c183968847b7ced47318b12f045b5263cf64a55921cb24413adb3a66811aa0b9c439c73d795232183e1fd3ad44dbabc3f9db85422029172ec59f6c72bd5e09d4cbc4b3db1f8d60723e0f4bb37c67da80343a72fc3a51040960d4c5adf1936e752d9f3db81d3fe176f233a70dadc68bc46bf7574cc66f3863cc88dd8a8da4c6b794899319cf39afba93c7bf2fe76e1521792d7f3a9d68821a10ee6c7075da8b042b1afbef40c58c1c34873edeff268104045882da69df5dae4ad39b36dfded44b599273cbf9da9373a9e70a266d238da90ccadc5957679fde34a5030909a7101fab72d8a18703576af3969d0937877cdf3988c7bf0a049990f73dafa54815563e4d312d8a1907726bd8ebfe7dfbd26f29c7ce6ef4ab757afbfcffc7276a3b833c1dcfe6dd2a6a9524706d23b544b98836bb06f8d39fbd0dc5359f8fb31b9c0a82acacccc5b877a55565babdff874a96ef9fc1d2b89df958ea64d4e742d3ca3ec75a1362c67d8fb3da97c4c6a66a68bba44df91e7be8d7706fb97edbe85e2a3a89e4d8fc76a584f19ef5062d0012fb44e6fb7b51b3a237fd8f7a977efca3e0ed52efe781d2acd10f1b510cb0e3660b73e1b4c54c02f1b75f43c2f9d66ff0006686a10e43e38703a50c93e6f45d0de8983ab853d9e3fa75a96cf468b8b4dcd1dcfc2a0852c45923418e1a72b6b4c824c7026dfe53d4ead781fced46cc23ef3b78e5a9373cff4eb5f751b7bbaf8e8742a78f7e5fcedc2acd71c797f3b563d6235785e78c1b38d2a303311ed89ef3cedad4f03583e0df9f915205f26fbcff3b70a0dcdb27d7d7e357f6c5b3662d3722f24f1d296bb7020a9224dc1184c96745a89180d1b2e26cdd78c77b544318bc702a64a6e4c3c49d38df966a299c19ccc73c5bcb7fc49b9e7fa75acde7f5591ccf9ac0e4543674f1a1d28223b70a000416e0558d8edb7f3b5302cbd68928b231f13fdc5d4d72af3a4bbf9e074a5d57ddf49ddefcbf9da9b6bb8df89f874a7a9ccccbfbef5e1ef9d3eafc2a44e65fdcbf91a694f04ae06ef2b3db07d35708d4616321c754deeed9b99091861d2709af0c6bb2c5ce24de6c1bc62675c6d58022b858842cc59677d2f4b22b945ea532c37375e09bdf1863fe4592ee4f93f0e94892e67e1fd3ad60723e2b8dc63fdf689fe549bf7e5fa752b9bbd39bbd0773a9b17d386c471acf2f165e59ed3edc0a36613af2209edeec62b95edf9e999cfe8f49c8712deff008a3072292243173e68482e60f83f4eb514977597f7de2aec4c4c1cee6dc276d34a45c49aae5399f547d25916572189de03e1a0a3308b838b70fe74921b08c2217220e845b5e86941006c4540608ff8d7e8bc4ba9978967b60fa68217307d52e37b61ccc7dbc88f7a16eebcbe874a8224bf36eeb17a6263316bb9eb52b12b7e7a9fedb81b14832dcdf7063e1bf078d35655baebc07d2432d78fd7fa7dbe8736dfc743a1eadb5dd3c743a54a614f7abdefe0f9fb5f03e73e39d62804638266063f2324c539916142c6255862336278453010b00c6348c5e111e4ce3d24dcf3fd3aff00c49b9e7fa75f54ce75b5f87ec76a0dcebda3fdbbb549b9e7fa75ac893aff004fac6d526e79fe9d6bf58eda74b72d8a937efcbf9dbd75d2132562a5fe3f1e90dceffb459c3a78ea75ac0e47c7a5f07733b4e74fad34a7727019cde3e6ddaa0cb90833d2d6b19b93889844324c5992ca6e59bcc5e6f34b33282d37bc4db338c0e35a26d9d37e1fcedc2b379faa5dfcf03a7fc02d29c15e89f87d352e3dbf381d2a5ddead4bbf9e074a41cd4e737fe38f2edc2bd9e39f13afa7276a124b6bf47e1d3d20d8e87ac1b1e7f874f502c713e36e1530ba996f78319c1bfe8a7cb24c3289374447166ec428d18e018060425b2dbe2d80022ac1284905632090f088f68a820dae0f61fced56d23c8fe76f5d5f0be34d795f87ac3b3e7fa75a0d8f2dfced44d2edd36d638f13ad14089823e56dec9d38548ccf6fdf48363cff000e9506c74f363a7a96f67a78d4eb5c9dbd1c9da9c9da90ecf4f373afa43b3e7fa75a2a50a70f29abe537b89dcccde8ad6084c36944b5e35851db953a012e5841832c4c73982a192c278008bce35e182d68a5372660be6df5a164bb93e4fc3a560723e3d35fa32399f3444131836e1fced5c8f1cb81d289cc64d48ff2ada16c10ce707d11388e1605ac67e9f854b77b7ed4264f42c16d3ecfd3afa114b4df6e07f3b54b67cfe8eb5cbea8363a79b1d281924d499389fa75a020b687d7e1d28990672a02779031311a0e1205248be261a24219cdd656092429a6a4dd42abd9e00ace592f2b1d7da9ed2c244e04ccdec718adee9d8d25c608d6c3c6452add1eb581c8f8f4d74d3cbecf4b492eb6f3eb8702952ee78f9a9d68993393ebf9db85142625999b7ba7deb5fbd395436f2b1b5a2dc35d0deb81c3c8fafc69df1ee3c3f9daa7bfaa8f1efdaa3876e5fcedc3d25bbdbf6b93b5399edfb50ff1e343a57276a69647045b38b699cf0ab65d915330c848cae8b3ac2c9a8594924bde205adb1ab7b913140f208486045cbeb4ed5188290b8ad80660f76099ab037be898449b9379d64e6e85259c0bf2ff008c3b3d3cdcebe9c8f3b72cfdc7cd05a76387e1d28824e426dcb48e5db85080021b637f6afb2926e752a530a54975f78fcc1425816634e5b7b76a0213999e20be389d4a719bcc8a62499e5828088b368c72fc3fea1704d20ca44b736876177df6de8458235b68173131126eb6bda96404490035631704b38601c504068020948b06b1ae23be6832e49b946e16c96cdad36da08cf8242dec68cc5ab0391f07af8769fabbb1c2b88edcbf9da964b393477c6bf77e14905d837da90ecf46a3876e5fcedc3d04c19bfc815399841759e0e2765b46d6a832082e4e9d7eab0ceb13a490729f214a8429244e17a38fbf7ab363a70fe76a0c2c25823409defbd28b26e4bc35fb63f1ad3cbe9ff2e0a48388ce1e3d2f73de8ad8505e11808937839d3d7ba0ed3172c44e4f7a7c199497d5100ade221c6f440202838042fef3a1a72a832128c5db0184e6cc012666e690a5ca0aeba7bba4edf1eb1c3b72fe76a0142d90c72fc3a54418121a1f671227fb506c742a0d8e8520640de7db7f6ede884280e31b9c0e1ad16028317d2d8e473a88920833ba0c25db3b61b53bc694f151d3f73c6a2c8b8325ae0cc38ed157d8595cf6e9021c381495bae62ef0a28d093e8d0f6887eea2e4a7276a1c49b64f45da9dff0078956eb0d6e45b3cf58b15bfef3f17fbe0f1ace75cfbe699adcb0b2cdf8fbc97e1bc522658d2f30b19d7387062f5312d8032db20dd665ceda69142208c9708e4cebf76e34324f9bfaa512188c78d0e948942565b0c68bb5fcdeae02c5f5718b7c63f1a844db139e13f675a88c225e1b26bc6960c699378e1ed4f30b329979749f314848589b2e4cdad7d74a100cc104b2ec3c71ed8a6925122d9dc63e384db5a8785839d512c3a798a020962204da304fb37d8a65af7de3f9dabe8a2cdf7d79ff3b7ac1b79e0513c13cbc6e75abc5b534e25fe30fd1400200b1f054af13ae3f1e9ae8f78ebfe3f45040748cdfd8f3677a1105999ef34e298038b46731be6f5265bddcbcbfa7fc4a396ce41a5cac8bf1de773876a264252f1cb1f874a7490e981d6271df4a457326a324d5e941bdecdfc9ef4ac4138d2764cfb1de848b0e447bbe539f3a322cb4028691b9c0f2f512062cdbea7f3f0a5565375859e5afb5bf0a16455cdfb7e15190ec7f9fe1f4d6bf525120b91afe548545bf663e0e3b5ed3422c97934f1a1dbd21946b9f1c3d16a3aefc7f9daa69cb9dda337d18b7d5130c42e5ad22e9d2cdb4db4b0a34dc37f228b83bffc40da336f3a1d0aba6899ef38e5a6fc6b0865a5ce47e76a8787cd0e950d8f3f83a504f1737e5dfddd33526e79fe9d6900b25875292e5dd2e6744e9e6b4dd5de8b24ee50905cc1f07e9d6b9fbd0c9ccf9a0c04687d7e952437d1c4fd5443480ede71d34ab1b1db87e152d9edf95c1f9df3aea9bdddb35ae8b6ccb4b9bd4f78ce238c69d4b70e7419440b0e224f779d4162c1a9a45bb74a2c06dff506c79fe1d2b5fbfa38dc61f3d98fc2a78f9e074a722ee77762b5d0c9ccf9ac3d9f0a9e3df97f3b70f4c8e67cd090b6869cbf9da8dd935fa9fc7d27789d7c76f481c952f079a9d6bc7eff00d7eca8d8066d114ec081baa36bf1264f346852d0da3b70cdb8e39d608d3cfc3ff0fb3d1362256278e17f5773d7faa2dce2c9d6dde3f1a5ddeaf2fe76a9be6fcf97f3b518273297313cce57db6f20ab17d8cf5f2d98b47ab6b25b5a62b2399f34282ec1a703f0e94b4ce1a2e90d6766f97f3871a8045a23165bff002cce7f456ba0b9efd61f9b1c767d7c7ebfd3eda6daee37772afc7cff000e94352da992753e874a4c874999e177aefef82820b6813c6dbf2227fbff004b230e4d18ccf1e7ade34a182dc1a36a048c6c9a2800041634f387fe85bd9f3fa3affc16f67cfe8ebe927e7d4796d26964659511ee59e76f6a08041834787f3b70a8767a350ecf46a1d9e9e6e75a8767cff4eb5b875f9ff1fa28c6f37ebc33e4d46126d9368bfc790563ff00605ac676e07f3b5264ea7b5c7a65db97a82d633b703f0e9436c13e47754f18624fa3533989bc778c2d80d3162da70dad1ac57276e5fcedc2a1b3a79b9d6a0d8e8543fc78d0e950d9d3c6874af08a1bb6d7ebfcff00e165abdbf6993cdf9c79f6af0e9c79f6e35e1d275e7da82cc5c86e6a4369f31ad000118fa8fc3a541b79e0541b1e7f874a8363a79b1d286232db97ed16036fff0013ffc400281000000307040301010101000000000000000111213141516171f08191a1b1c1d1e110f12030ffda0008010100013f10713eccd29bcd06a56f897d4d19737fa1522d8bd8a1efd44f7b31163b93e3591f437193df1193154272f75e5bb0c6b9eae7f32d95aea6c82d5a097d87addec1062a1e47eaa5035cf09f2d14ae1e2b9f43d33887a5468f6dfb7d6211aa454bec09b77b4f2a4ea4a92530fa17109cccd08c71c499b0d032b25db0c35a13949c7228a18266d8dc6108a38b526a374da53835f0241ccc9755d1049419d1646c42b5cc4bc375a3f51c155d8858b2df55a08fcaccf094bfe325768459a918557720aaee41e58ba0f1dcbafc30dcea957506c04c9d138e2b07b406445d5653a3c730de9d0c8bfafe9e0c54ade2776a6a350ab1391f3e5d55f546b38d1c21234732d3ac4b42476369842eb9f45fdba547d9eb8c340a63fe2c0b181c193ea27b2503ae212f1c0aa979c4ea256263226244d376bdc9b0f8a9db7f88f3bf9f9fe20db2d1ec7ce008d9f8b65e1915048e9becdd62e63a873d772b56a610d41a7a4ae60cd229d8d99d09090c338ae10ed388a98a91b07ab0bfb089e84e07b1f52f2d265dae1c82bbb75951d11deaed4ef1045709f725d8899e0b41c10bfdcd034c6ae6740d2c4bb412bb0635968e49b16cf3aa4de608fcaecf2b4b05012d967ef6684a250ffb3f0389376841a1efadcbc96e47004b4f7a4e46de5a31be30e242267432b6329f98b30d61038245ae7523bd490ff0e9a355b79840d820d99f16cc2a1fe785447ac619a85140a93f09b9c6422916364ee569280a9316726d34ac7f152aee0ee4bfb04419e3539bb412dedcd9e656818825df8ebf80f56e1a4d2d056b4629b343c57b5c304aac267c986212b0905585d460d6385ab8422b1274d3449fa7c462df62c5f79cd079a51b4527e13fa29569ee6de24249997b9218e319102a847a7da0f7f3c99cf3213f0df9d37f086d8f7ba748c9a1a2be7118a776073930a916c5ec631fece652406870f33ccb387808c57adb880544f56cc9c9e5a142cadbf540a6a3e5ce47a37e0a03caf1aedec5b41c36671015437a774e1bb044fd7cce45d206aa04dd5d191c0e63fa34fb8682c623f60529d1ae6bbcbd79a4527868626d99a6c45677632af929c5c3e05775fcd05127bb4eba0e1d8bb118e2dc35844e09174ae5d0c43ca7d762cc24840a0b08f5f02f6c0b5c165749f4d88c3421358b6d7563d0acae64f5c80f68aad54b470880dca47eb218ba8e2c6f20c14775d3a3bdda28748647d2c018ea2ff0b3590f40886869fc54b878ae7d0f2ad69eed74be162beb318e0f6b6f691f316a8c5156e7bf7e411e88a712c88f074609e6c6672f5a30f492f90f5e7d055cd65135dcbfa3b7082b6b00b0f5b9b2957e1a909786fce99f97390d73b91da8687f906d968f6ba711828ea4e9268c17e0c147d6559b059055772125e3fabfd0e4208ab3e3e1a02109c4f8afd7071324db94e6af0f46164d26701e52dcb76be8108a4d5ee7c15dba10d70e74fe76643f8a6a9911bb61e1f0366fec49c31b5c9cd198f5cc28d1f55374a7c586fd33f0db362a289b06c31afd4c2a45b17b1150e3109e5cb3f20b66fd2e857e838762ec3a773ebf3f8d15727f76189477aabd6015423d3ed07bf982ad7488a93a4b62c57040054f99e0812ca72fe3123a5051271b66917f8298372b357d022753287a82bdc20272ba1f463719e5f173f518dbf9f1791725ac18df64119cd35d1eea61319893558b267141057a6a49d95063b3cf5f3d0700e6267b648c446befb39301a41a117b60c53d7e9878063b87a1e06d98fd014c807b191bb79a2b7f2fe7e7e5fcfc17f3f3f2a535c9a5670160c35774f989f86fb47e23aa7deeb5115b17a2f6370e5c5dbb41fc4f744e8b513510eb88af739a4a20227519d9931791d1f1eb647b848459425bea202a3684e64bb19b6ba6c19b0b3eff000d0314593249fd1ba1b9146e9c0a8a67116bc7b137e54415b5f10ab1cc63d19ccfee3f158e518f28e1d45fcfc17f3f3f71b71a2fa266a717e651989433c7229ec108a6e76eb71b90f4e19a6811412e147ccee9ebb45d54f37ba1b0c6fa674f68f8273f86b0c3462bb9e3838b8d3f352f3f41f49b9b6e43c35c263b851300ddd47d2ac90c50fade21eb461e924f03fa8fb661a8e4df6c52bc498d487b33deae20c423efc4e892306b5b935f37b69ab43662be09940d197287d16a22841edfe631e1e97b8ff7f861ca14cb6f149d83c89f9a3da5d1a0c1cd9220212398afe2298a9fe2c8706ee5bdb888ac7b7d1642de3e8c736cc790b4cf374d46cb9cb9d91c41a1afb673f02645b90a5c87f6967a6c7c0a65b3d8e94659f1c20ab14415848e4fa3ee0e508fdc0e511c4f169e52a9847d752564d8e0928c62b1995f28421a2f4e9fb51dbf0c6a278689c3d13cba717a547b06be76c6072b72bddc60df61c3b177f98b30d63038a458e7733b509087f5cb9aea6a3c12368a93f638180d7e133b3049a4169b6aa51edd8a29dd237a20d56f392636acdeb95271d907994fad9b3a0d50aeccab9450a5de23ec34ca68ad83e01c283d75ef7623c30133745a2a024c4cd87be4c1bbad7fb61350743b2bdc7bab8b55f410c797790e4979c38f90e50f3cbdcdab082cd88f9a11b08f61b177f8631bf1aa7b3fa14fcd1ad18a551bbd1a4e31224273d9fd8f344c294d0fa1eed4570fbbefc92b510422d81cf53e7b1ee91e5f95786a57984f8f41e0ea3db664b10385791ff283f9992e0b4fe23efedb60f52b7d37fbb8dd7a717af0d0f46c7da65cd434390bbd7fbf93434e0f6c3231948b559bb53dc44a3dccd37dde3007b78f0f782122ad3e350d3d1be98c2f0214f34a1b29060266e953c68e606b1779e08f560316968d7da190ec22f86780d08956fcd6d6060c713b14bc12ca3a9e31ed16f1f453e860f4f4adbf18f9faba393468921d5dba49af698aa009ac8fcb41a03d4fe3cb141a6041ce2ed03450789dd6a2851314fe4050ebd8a31eb29b98e5782d3cca21aa166ba08ca8dd054f2e85dc3f82086b1d62c21366375966c09caf46c5db60c03e2bc4fa2a6d4ca1bcaea3e27b7d7de823d7c07d20543e2e78af15c61f4f7864089a0def7fefe59c948de8485a344647b7523b063542c6c71c189444d27ab9771642c8196b3f84450e58357844fe50d91122397e79f41e30496143c8ce377bf2371535cda01126424ce758a2cd376ab6f3a36e4bf8e7d6c5e4f73380685fe278d14cb520f47e1125965f6644b8c5c41e443e9680876c738f9ba9bc585c74384b7643d9b1428b2f15a46e633c21c8fcd0bc3944d56f970689df1e624d909a26a899f084853e8575538a41f88a3174b479f9272ac79bfd9caa2ce0201c5c98dbd74136331968888f25eb78cc602371e39381881990d201487cc84322d33ddc07f7073d19088c48e1368372165f1e307a72d6e28484d4366d289a0c85feef1984054adcc9e72e8581f8bfd7c87f0e3e3dcd7864aed08b35231e01b621d450712ae220deb95379dd43d68c3d249e03895655082834d2cf64b41831adb4dd260520d2adca10a8231abd6a6f9a0e19a9ba031b3621143cc5bac944d4e7f97a45ee0e79a65857e83cf6e6be812b8b0b77c918262669627ac7cc07d191fed87f5d9df4dacf110d5fcc457ec2abb90c5bec2218dbd93a61e2c4a5c49ac68e0df6b16de8117b5bf9b442b033d692a9a3c659f07b227559ae9cfe62dc24844a0b045791a8f30db7b90d11e31b31f48659afc08a6eb082245575899079526aafd73495b26697b3434585bc5927cc6a9b846c7c249b0e5d0d74584bfaa9165f48dee9aea114495dea967ba0e5066daebcbbfad00f3ca5f7568ed5f9d8354b79d60bf18d54fef935d0680b75c97b10164318e890a015f87df6183198d4ecb941310cbef385ea34f4fd34f262e23176bbd418ab47ab8cfcd8602644c9d2ac78c15b69b5a466517c396e39df9a3285d1ca9172082d8fd1fb1d94730a0344433ca9924fc0e04ba13e1a4da41c81b75e1746d4459ab24784c368c59e823a3e87c7046315e7c1ec6510ab4ec9178f8432c7b9f3ac26c1db314e11faaf1f437d346b0c9e81ebc61fce891645f56da1a8dcc14a788cd85c0c6af4b5295419ae26cd3a3c7816ee66a8b36441275b59ace6cd46086bdff06654d853232f742c6fce8be8e4b16fa68c729fe2a9c0c13e86ad8973fa43ea3e3d6b241661e79b930c354b230e278e58b87dc726484d7ce19aa8213c3630448f0dfe36a3d6a5a708b58221562c14c636c6940c4c134fef130f2252ce201882d7930ddb70340ae3d62fdc6aad59d22a984104b1a98f1144bccf4c50f61c37c439683940aeb3dcd62abc4c075585fe31e0ed4dd769f08d1144ff0060ada35ae1400ce64f3c9346e8936a2b188dd823081b6e1169198b023cc5361d80da6dabaa9121b44fe2ca07b6a1cf4aa2edc5203ec896d4e3a5f13d09fece74161068dbc2220fe8245ac4eca820a02cde233c8f35156e9c42a386bf58c987b720548b62f7f9fd4708f08667a0ca4c9ba656370daa5919f01a9aa7ae147412f09f9d34706e67d35ac7bc306633f90614630370c18d7f7e2e608ca9f2f40cd1b95b353d786ba1a1b9d104b71b79695380b34f2d3b3eaae0d13f31dcbe87b7f14d5d645193bf33aa0826ad7c34147a1838acd63a051c0fa57e28c61c54f6e6e85c85f0c51f6328f338da2f184547bffbc5bc7dfcb220448b630aab7f346374350e46f190c734638fc4a6d31f48fc264d9070f6a0aaf7aafc0831e6b1b344c6cc750948599ef44671250a144ee7ee88e1a24b25af4d2a09b957e9674a82a50b175a0aa11717c3980c322bf4f60c62b3bd368d906862b2a7daad10414355d35999dd6431444c7b12afdc762d21b979ba2032990d87b439cb9c020b5ae84f2681e218465bb6b40f0354ab25baf0c0e25c1f9776a2008721b4f70061ef5e65ec6c89c730c5550d5c674bb592553132ccf7f1061a17e28b93a7ca8cd600863475356c9458f5fd13c98dfe0f6dcf7a1ee2eb89f7f81eadb913a7ceb00f15cfa18df8d53d9bab827f9d37f4a4422cd2ec2b3feb14dbd9bea6916870ec5d8f0b3db785ac886336ead62163818b30923028ac30244d7b13570da06ed640c283508e6885a0c04ce37d41a772ec4c3daeb6ff0013b227c4c4ca5f3fba3248dce8d3ce830d5f8d02265c7b1967c105e9fe9d10a40c6def463281f38e91841c7e3b61f12fbc936641733d8c448f0dfe36a2e3fe25a292885961e88bcb5819689687958ce28140f19ca1ba8e69b9e624857bf2dac4c3a22e192fd640a71519e44d8c1a10ee130d2c3ea873c9dc12f494d372188099f535e1120c8f89094492d98d4a8aaa4f27bb18e406952bb72694dd4c22a84746636041e88e5d31ebb87040b94eff0007c103fba761482df4ac69b8c106709bcca62ccd72e64f7a079433939a2b2267f8e2d2361ebccbfa3c25796610c1571f81555565b3e83014f7be884c1a1f490da4d3268cc93933533fe27e342188fc6dc3c37c4381830c6dfd648358e1e8d77d3918df8d53d9e450f4cf05f09f4ee93708af23552b4e2e2daed451114e9975fe871648f5bb59014724a3ad01863cee03ca638d31b19444c4cbac3fb5a03989a924714513e7975d06067bb7823b95039426787be94694143f3d57e1f438561b5cd7f6f36715b7de1da4cc2be8338a115658d60d370a0e255c44106d91b1bd38d6d660575dfc79f40d4963575fe6e4f98deaaecf63c97bf9c71c4c41b25abd8e9c0723c8c1759ebd9c0129a1f7357c41a8d6dd9a1179e4609e09ce4d4cbf326434456044fd7cce45d20822c7a4336683746b68fe76e3204ca79192ea511be21a4e2b567042a02da33e760480a2648c3ae1348502853e2ecb8aa4a2eaf61ba5b28b0a327474a731ba39f3e6c0cd8a1a7da365541017ac5335894c1252cf6cf2f78bbd9d7532a3350599dc274392b5538a575e9a31661246051586649c192197f544bc37e74c15b82f42f0f22718ba2c03346e76cd4f5c6c2f3252f830533960e4ddc97b71108996e61c0bb99fc8434408b28bf07f453c4cd5a1eea192e17f5107f494a1da0c60dceafea2babc4c2378ae451a41528bac9ba3bb68374a66bed81e9ba6519a1d0631feca453515aca51d308a31edf783ddc6a5e95fe562d460d42adb45fd4cc5d7e49093ebc6bd8efa727bfcbcacd781314b1ad7ecc240a7a65621ff009d678fa8d14b8714cf2575e7d43e23c8f936936bead5bc8bd9ac979b850d313c9ac2e2211b444ca94d601817bf3f9d10d1671936b28dcb832137bf6daba2662fe7e0ce36c589bdda95baaa6e4c08ad4a7c6055886cfe741891bc69dce21c0ef163f52c336e245a64da68731544f935534e1f784a6998a3c22ccfd1c81dfd5ffc64601169d923f3f71661ac607148b70b730e177791246e746be340f6963f5a1bae49c50494a3fc18db8d53d93a572ebf1532dcbd0b965fe2306c96c7bd2ece7f98f311551f13cc46903711e0b57dc870bfb6f4c5d9ca98d823d9c792a8d11bf28fc2313f0df9d34385566bcbbcb050a85eaa7f1f504eb4cd7d30583f9646f96cb19aae29c4c95c353522f7f029df5bbce66e0c7d3c99c3aad51fd4f655e8f4182fc19a1fa174115e458198e66342cb7b37bdce8dc1afac34aec930d5eaed654f2373abfa8268f17658bc4957b354abb3d5263d2fba26a18dcba76da82ceef0f50f1b2962790e7cf26c0f4b3e6aed35318861b1ef78c003433f5ab8c1ee02e738730df30bfc2fb90419bd9ad31af1478209b2434ba5010ec32f0e46cd3bd3965c8f4fa3a4dd2b55698f78d7a885aaae50e49111bc7c24377f3f287c4df49723167b18b3d8548b62f61a8fe29e4d9a41022e3a7fd75482babea7cf06431c595918ac3569bddade233628f4a993b342517f1f04d13699e3c055370670c6b4a2268f6ea5c0fe8aa67026fa04a6d63bfaa9e5eaf0426d99ebea8821e625d66824b083aedd20d18686cf8af80d44b79a75b8a2cf15898ecaa3eacfa363266b8feacd3cb0ecfaabc39b9ff8580c5854de33e42ca75dbf22c1244f742be350d0f6ebae387a069eb8a8ddae30e52792c8689b846d7426bba07fa84b433985498cf7b2b628461a71e0ddf541a31e51fe2d5e84090bfa66735d9038563ec68ad5867a9301c220cd9c8e44d7c8da50906196ab2907eb78db85b5e90dfee104761eaa1eb5629376b7a78c75033b2b33c256c1eb8f6b3d19b0794b3521ecb084c7f278c4827eb1cea677a9a17f844cf73f5f8f9dfc886327e2b060c539ec732d13f30575547b320e2c5d08c2ee7e0d57a8eb70df56f1e2ae79bc6ef3be215dba209872298a4dff000dbd9d845fe12706bc4a7cd383426e81c8b13f09690d441136f09856446abfc1bd8cd849bfd25e0f8165b88d839fbb4d3c01ae7722b5094c6a2356e35be3f30d0841bb3e2dd87531a9b3766c8ec305f81bd8a72ed45d6e2322f76ed248ad8bd17b1899533f4ab05356ca6e35b11f1455611f7189fd0e1c36619c4493675dbe87ad9e7d721cc31131b26236a1b3b1bcdfa319fc4042182f4e7bc83401246694c77b568388252b65b1236a8d24261ab2220104f6f1310440555594946889373bac431cea657a9290c56daef8d058137e5931c8444965adcac2655d1f95fd44cf73f5fad5855de13e030633fafe4566e1a05cd789767480d3df472688a63ca5a5aadd422bc8c445ec38c8870b1ed579326820d27d39d5694e02ee43c473cce031612230fd1384142da311ef6ec1ab90cf23f8be7497b06b59b25eea364b41bc465cf96c3b2e19078ec7d04568c7fc093e2c67c6791e08f244af227345823bebca420dd9f16ec3a989227ba14f3a8c1bec630fe4e47351e4e0f68ef50fb5ecc60df615d5f53e7832146ac2bbbdd98313ad67e49061a90f250b2e9e1ee1fd155b26ba639c565e16b0a495dd9b3feb66224fbd606fc31a235b610cf1c725c2958657b179bc2e140be6c9a8eb587847541e052c2f4523f88804c9ccb76f88830c91ef4d79b525e192bb432cd4cc6e1e989bf4a3b14c69f97443859109784fce9bf9b98a1cb11bb7f8c4fea5fca878385ef4b9348708a72fe870ec5d8ecaa3e8cfa30762c776e429d5f9124ab854f9d96ed41bd22d344b6406259d57281c643f182ad96cb7fe06b9dc8ed434313f19bf97ac460b1d762384657b51dcc17114522dfe0c0931e75bd4dc20ec7cff00741512b1afd7ee08b5ad71ed68725677a2d213146f759bcf911595739f2e461a98c5b8490894160f95fc870ac7d8a4dbf2dfc636e745f44a389d6b0b7f02a89e31c7b8bae7d17f6c8e1d8bb062628e1b25ca4413ad735f6d08333cc23a61f49a5d8cc36063b47e2f840759766894b986aa346faef8b30e1012f9b4b483d21295e992184239e10813c0055e220c4ae4f650e4ea790e1e49af632dc31e8fb644526e2fe7e7f9bf9f80d78a4fbf7bdc30108a25ced8c2163808225a1466cc3a898c933838c061a107190fc546a797c247f4381f593a2d87e3454ba596dfd18df1a5121eb9bd4f798c17e0cc432121244f742be35175e93da6591085e6dad5751ecaaee4b59403c5ecb5e3ba40aaeaecd9badd3eb761714166fbea4b280c4abeca4968b4636e754f658a4b557afb8856d7de3438563eff00ce1a9023f3bb7cad2e0e9c6cab987061d7c87872d162dfbd850ff299a7028e49475a20c5e99f763174ee56d9b0aafa7aefd6a28b3352e2098c91fab824600a4eeef386d1165ae1f4ec244d9ec1011b176fbf5c8e0d0520deff009ed43bcc9c5d88a47f26e629d02003d44a953bfcbf9f81133dcfd7e2247b1fb0e95cbafdbb5f0d3bba88e11ebe04d6bd9c6de8857ec10ebfdde1b39c58aa993eb39482ad93bb638e1151a0e4a3da5a3008cfa10773e7f9a8d551731430a36e8a48fc9345bc7d1bab92436628c42ca7c7304bc4363f815979d4e0615b9e59d8a4d51f0c97f3d88a766fceb918b5b20631661242050582b2ef56a640528af5e648db90c14ce5a2de3e8c71276462b49078ba045790899ee7e8526e3862fa2e9aae9f8f1dcba1e80c5bfdfeb58ffc7963ec819d5c22c72ea3933966468be8e688cf242ba66598b72468232aabd1cd9a30403eca3c20aa4a6fa79a3477e0b3ca87979aa43cf91af770d1f500b6e93774fcc90aef2c9998dcc50a78add832b64665c49af47334115fac74bd7e61a97f957e1acd9a544208b4daf7d6cf158f6fa31a137d634399bd2be208271dff0013a7b4346678d6771fd68ccf1acee214d334aac1ad80a67b9fb1844d5dd17f472f31d351677632af929c5c1a3d1af615b9e85c320d6cdbd0f2707b477a8b9eaac24670210797cd6cd6088226acd1fe74590781ddccc259f8051e5daf2f9c5459662b60e7ecc35c1cd2490d7f2de3e88f5f0229ae21b1b8844f7181d8b31eb9915162b8c941cd0f7dee7e0b62288e85eb7265b81a2ce326d651ae1d8bb1e0e4f686f50c7dbcd9cbe8d0d73b91da868622b62f45ec5d06e5f796bd46a673b21778c0a8ebee5c6a8cf44c620eb59ea2a92051b4e5f0f1349150d09491600df908d8c71866864c2557c41bcd8cce51e5fce30631feca4535042f732254c66a191558de618e703337ba3e4e03551fe25577f97b921e1226b48512e3d8b448940de34297c4bea68c909a911e84a7913a09ac4b6bab5e844da6869ee0c7c55457eff39375e98d6bde35366ecd91d857e4fd0467d0ddf5c348ff00a1c96c2fbd5ec24848ae40f9290882f373f04a314bb0fa6d08cdc61522d8bd8d1c0d31bb1ee1ef5aa2edcd621d6442babaa7cf2443174b579f837c7af818df8d53d9bfa78316f1f434daec6f30935022bc8b381344f7c2be350dc939afaddcf52102edd7d3dbd98b30923028ac05bc7d0d426bbdb2ecc610e69573cbd7574ae5d7ebdd49bd3792dcc48a568f18d6344ce875b7437214abbd5f540d506dc64aa65507e938d644460c3a2c1a35333525608b0241f9d8353336713995c422f15cfa16f1f438c80b2cc4641eedd84b6f1f7f61d3c8b269abd2f88096cd2d98f544702705e3eb68316612420505876ba0c0862bb1b6174dbbca928c56daef8d060abe92a4dbfe1cd8c202ef8af3e4644e30e62d2074e178ca7230d03c2ec96b23e64095ca91dbfa32dc421081aca915ba9d0ee1c527c66c492963e4cdd18f950387900c6fc6a9ecde3dc1d599f6c7b9f7b1f83d8ca21ae7523bd490c5bc7d118f0f1b3b9e0637e754f668af3f8f9dfc8b7f0f1dcba18e24eeac569a61a108c1ee4cc893f51b93d3378743ce095154e6b156a0781ebbd5b0fe8fa2b18e1a924142d20b1bce65f420a09b862f46911c194f321a6ad1f4e839f7b1f83d8ca20ceae19e393411fe61d3cfeabeb1d7c481277f75d6ed9a8c3531701659d939ee7c6c33b070ec5d8274aa76b8e1e16fb6729491870159ccc249f913119e23f42381443b4d5ac4229e23dc180aec2b35c5da8639dcced42421839a4921afe768f19af880c5b8490894161a8f31df7b1034790751978e81e2b9f431b73aa7b28f5f0211d5c09719b16e3e6f9f64ac44c5b849089416032dff002f0f8a7bd921bb034daec6f309350609f4598d4598d459c0639d4cef5342fc625bb6ed5c7c83fa782186843227e76d6c499a615ea880cbf742a869d7807baac431c0899b8f64f8025fcd5facc99a15ccc8888602807c767dfa144165f04a1ca846ae7b9503d4ccf66ce031992dff005d2b97423d7c7e780422f7ae23e0e45508af22267432b6329f958f6fa1ebbddea8bc405fcfc0aff839b78eefcb3f165e642e1f5f56d38340424b6aca37a6582019ab1b0678c820d1330cd8f8c936448f63f62d08f5f03c618a64e3f4decc3c573e862dc35844e0916b9d48ef524316598ad839fb30d6a10d1dd23a7240e7dec7e0f632888f5f021178ee5d7e78383da3bd42889ea719b70ea155d882abb10c13efe31cea657a9290a253fb3bda2906e81a19e668c73a99dea684155d8bf193b7466558537bc562e87b6903c728622358ef9368454cc6464474d48159c114e2dc7a9a9b56418481b46f285db0702018ae5ac4d94ce824a8694322c54628a802ce72c41ec751e196e1522d8bd8548b62f7f963dff07abe7c9453f381e045b27ab9ae944702ddc97bf301917fd3212a55849a2faabebf41e93109ab90dcc50e588ddbf6839b6cba065bfe3cc80ccd7822422fea878f2018df9d17d1d7e4fd07572e8bf035cea477a9218f89edf5f7a0c684df58d1c23645fcfa2db14fe13f66078688ed787b24c18df9d53d9ccb328aa1a6f76b788cd8bf98b709211282c1ce3deeb1aeaf0426d99ebea8bb1ea717e4d035cea457a9a9fe7aef17517cd92f096d1f4b10e68e7ff5e4d3d06200b36f3f0c725408b53c706a81437d4ebe0354032d18f11a9bda2808288cdd6d44891b2bbede4c848a949cd2b6c1888530363fb45375aa77e81cd94ce1f6ac952caaee5f86a51d3125a037559b76d1d8e36a8c7f2ffa0862364a6db96c11d1d42e39230d6d62bf6e1ed49ac85bc7d0d73b915a84a62fe7e7e6e6d5825b778fa107b1eef88115e4636e754f65838a4d61a8e53cc5deeae117a9b615f7dbd83edc7cd5fcfe3da5f3cfe61a18842a936feb351a8c385672fc7587861a1fe60df5f8aa6e0a45bfc0ab1e4ef653a055380f7dee7e0b62288c6fc6a9ecc5bc7d08af21d2b78fc45790479daad3f0a28e6b47da02de3e8edda3a10aa033a9ff006eaa11e28c8dfe39b6e0105c67f62d050e401ad1d9c44e2734c4901e94ef08517acd149beeb711481a7ab35d01820d21942fcef11f4829bde2e3531c8f1f8aeaea973c990571d89123dd8334ed557f7cadf4ce52933fca26535878108f2ee3b78b9eaac256f23d584a144b6a1ce7edfd7be03551de654786656c193f7dc86a6cdd9b23b096963085757ec243ca669c06157f86e466ecb8e5776ce9a9b3e8f2f60c809aeec21a374f6cec5dcfc18cf55a32576a8c6f8c389056d7d6307c4f6fafbd035cea477a9218d4b96bfca4588d13f0df9d346337771585bc7d148f7f8341c9c3b4bc6024b4e8a17f67fac73a995ea4a5f8f7089d8f31f135668ff3a2c82abb1054cb72f4155d88419eea3d1d476c322874ede01aa77be5d5c3b00c5bb3404a4691be0891111a11b7e37a72e99015d02ab204e0890ed031a0a69edafc53d231721e25338bb2210202dcb397a4aa0811ae6626376839c2880441c99207373fc41b665d250497ec548baca5df91e567a6f0b5990c353186860c7211dd7eff000c3d9c4c5f7cf5733065368b883049354f0c89ff00429637d1d8a4188fb2eea65aa6a31d2159fb18aa11e1cac0c6cc73de8e2a05266fb59f8e0a996e5e8453f7759267118b309210282c3d1f0fed0d1e334ed5543d1bc59a4d986bd7bc88725dfe5bdc823a9eb8229dc316cf66ba7f0cc22bc8a47bfc13f19bf97ac453ec5c0de151b7b3b4aa0a7c9fa1fd51a35fa25e21a25e23b1fd17766b28e92945e3073592474fcb78fbf88b0ec9978fbd149b98663fa547428b88ad8c7466984b369e2fd4cf34321ed9d143e9c1113785accaec2bd0b6cfd13b0c4010679692e28d686798ec64a846c54aa0251a095a7ce263a20e34b983885bc7d11451641ae3e5b16bc346042bdb30c23943044dc3cd0c21b0ce286104017938db59c1ee487d4f7f8ebd035cee476a1a18b7fc1c2b1f615626f2edc4d1f54f6b2c3768c5b86b189c522c44ec9e7dc7c20f8a2f741a70f30e5776bea49e6a37f7a8e1963e96909f86eced82308ad1d739f320f6c40aba0da7287e060bf057ec62cc24840a0b0993349f67d13c85806edffbb151b40d7ce859260a031595a30eaa43d84cc6542245c7afce5e337bc5c900aaec42ee7e0cb4e59783261808e7931f0e59ac862dc358c4e291b78fa1c2b1f6394fe788e9f98b30923028ac307149ac351c32c756d208e3f16265bb472f3bbf8e438762ec6223aaaa253c0d001ebc9d8620428f282a78a570d1118c7d888c354113f3514359443f83256bd964687028da165b845f5de8b2511e200bc8207d53fa3a105fc61a8314226bf3d4462dc358c4e291a47bfc08c1e3dd8a3bafe5bc7dfc78ae7d0f8aa6c3c6bc45df9dbcb98077e209749ff5a316e12462515851d840dca099aecad3df0ddc8772d93133651e149ecf3a3482a9c0227a3996130feabda8937063ca18ec9b468414df9fc08af2114d2d5c85bf295a566ccd4bf5c5cfb11266b7e8417f99149346306265addaeb9db4d7742cb410c314c9c7e9bd9826f675faaaec5f8a996e5e86576b0c20f29ac55bb47c0c1be838762ec13cd8dce341e159becb231133a195b1941e0e0f68ef51828e6f5221d20075bff4162dba5a66260130888e246a676e0023044ad3791aab062a1810f7d661908221662d4ae5e1a802ed67d670dc41cf3a31115f09228ff8a38dad5951d70715e3860c4ad2ce8b321c2a0f3d122e0f6334913d17838bfd3bf30d4bf611f2b7d3394a4cc2d9234577a325111227e22aef550aa70191daa533680457fa5ae34354726d63249a0a29eb24ea32204f5e397343477d7a3ea2547871a1b591d7d60f9f12e7228a9dfa0e6c7e98b77591fc7871c532e1ad9f2ad60a1efd44f7b3347134c6ee7b0cb7e8f553ee362a0a3873dd96e90404635943126091acb9e4a4c807052d160cf9d8311af4fece11590496766f73dd0b8a9d7a0a996e5e85ff0084fea7f925a7650bfb2fce693e14207b5c55ec8cf4e831ee0f476f554c6882db1ae346a6e09415485be760ddb8b7746e5b05042572ac32e1632a102100c36704577aa890844d70c5e90e0402b6eef845e090097fae47bb9550428048171317c44697ab470c014283d82cf87b7c486235c79d8ff5c3b17630d4bf763a3bb8aeae0d0b177d33d03d9bd19c77b8d3568faf418510bb855c0df9caf11388808b7ef16c3a07b4b17c6a9e8437937252b8358a5dee9d064506fd30a33d6706c91319dbcd6f668929593fb464180a3cbb5e5f38afe421c19cb32218705b7152dac30d487b0e6e3e1c28ed17f76506c7c91f6f644e23a50b64eab2582058b39ab07b46aca04bef41ab7b32a27047431e41b629d424d71f131282de3e8cec1891cb56b1f04feaa45b17b0c66cb6137b4a19e98f7dee7e0b6228856d7d6304657351fcc531503f81c68ec3e2917bef73f05b11446e96e7b56ede702153cbe98621050a244b9ec5b93020a8a8b4b026aa2678d5208f52369144c190c3d887b510f46bea40e9811fb27b1519aa801aab25fe044f00bd6c6ab3060837512cb67a1ab1ad099d2664429111c4a46d6c98c050310b522442eba68bb15758419d8f3df0a5b2933fc5fcfc16f1f46aae8958e6b252d3fa2f8df8211b444ca14920122d518ee23c8d93ddce1868b8f7f5bdd14d3ac724041438c4371f2d1aa7a48bf5ac8644177eb6b70d631aaee5db5f960fa8d3e1409f41c3a9aea909ff0006441a0e4e1da5e300689e97f021c26bfb7ed11038c3c32140f171151926b1fbc454070a06abaf0345133c4ed67986bcefba7f19321c8f232bc7f1ae304e6a3ca33fe8c525aabd7dc43c573ebf3dd5c5a27b1197ae2858edbfb8be6c787972ec1228ddb2ee1a8e0e3d25e300d8cdebd36ae89189e45793d9e1b8f16f1f460e2b358e809d1f9d1ffcd86ad1e4b3db26080a967f708791015650336685a057cb65545043561bd9244718d3b9d2b63bcab661843113118b1818d16444e79513d052c88d8fd0b18c39381ccaecdd03d00bea8382782500a56865da2b59ff0001e9ac5fbaa362d1b044c8fac69cc82aa759fa6e3835ab3ab24871d8c68d442a5fc9236a291eff0002a92bafe770a2bc7c9683cec2c482ff0046a208dec7b74f4b11e94f9214918b1f354d96f59a0c5d2d5e7e0ded5875da12e0598d4664ab07abcc6122f42e863c2dbd1f60ac84fe1b9428362baafefe0683f2ed8e1da2dcbb461d47d1458520da0c73f0d9022d7336426eec1afbe35d5c30a37159c888d7df67267e7cd67cf2bc83e3719f3f1535aef21bb30cf21f8f2832afbe3d7f370e5c5dbb41c8916ec5b64461547781a105a65de1c246a877617c146412e71c6b422a41f679ccc41425d7eeea2290f93a814c8db06103a686f2f54b1151770d307ae6435b4e241215b0ce428926e8f98f94e47cbc8c353fdc3520e158fbfdc10c178393c127fc1f1c578a9c906088fce4d643ea844d63fd60804e1db73719ff00431769571f827aabb90d07251ed2d18094b1f1b70d412f19bde2e480f42c7fae472652cc8878ae7d0c5bec628cf2b210137dd18f826e1ca3d4cfd49e0e4d330d8b65beccd3fadcb1b2922ac58f5069313c7a1e4598cb47eb918a5b97b5e34218db1a50304ae46a5cce45168f0b4f46a55ec48a5f3fba3291eff021bb65e8b181e4cd951fd5031cea677a9a1070f2018b22f869e067618db1a702145b753ad472691eef84c1d3de4524d04a01a694a2c0c8fd0fe23d934be98e91093b450c43e8799c38593022428af70bd42ea82ba99ead6c4da809d064faa0c8dad89035478dbd5b882811a28742125b0afc690310715ea275d07f1cb9aea4823ff170ec5d88f5f1f9ece41ad2e07b63a4a5a1fa153d7b816d76eb268f52c6a207a4f3848b8def0aa6e04579175cfa2fedd3161aab4bd9bfeefbf268d550eae32f1ce32d4e0515c4e49b51a2823b34a6b57080c773e8981c30f78c25db028b739eb99b48391d125a4dbd90900b68ae9ce7a5144f59cf7298341c0d1fab8d8d0f15cfa08cf39aeb7153ba77f7d726cf5b5b888799ba79a88b327c31fa880d8e5afb1424a737a23642de3e8c5b8490894161c994b3234735a3ed118df8d53d9e649c99a99ff1054ebd0471b130b93d460b1f66abc3c1d8f6feb166392279c0f03954cffac116c6ceac91ad4c7dd6bba1ede60d5a2b39c3fa098a0c6cd5083450697464ff00b20c1008f478b58ff23d9a7b9cf6650400715e2e9a072272691f3c060a2599097f341c19cf321fe1123d8fd8b43052da6f7991c5f1bc2a1e863009b1bac41169389a9b60e787ae9b4e86c2ca4dda0280d679a4a38a3e3e224c068f20fa32d1d422bc898879d13529ec1fa14ccb2bd49c1c493f23fc1249cfa5c6a0f20b5caf71be300aa3a533c35c1aa933104189bce3082821d8b0d26ee4879d3d2109a7170d119aa2be9410706a259fcc6512518b309210282c14445908560eb0ddff0070f60f9d8fb1cbf12de5e34392a76a8315fe41884db0ec7636569a9b62b46fe27539c2c78723bd6945c6fe62e96af3f06f9009ba728d6bc23a3e87c7046318ff0067229a82572709ced8216daefabc4c7059f4d9310e818df8d53d9e1a03deb88f8391541bad17a4c785191293bff51ee18108b3cef492b04522c86b5137b4a19e984e60f6b5dd3c48026685c37a53351830d22af949c357025c66c5bff8639d4caf5252186861133dcfd0bd3fbe15f4518213a7b50c4dd469e1a46e1ca1e797b9b5610922653b9721a11391493986984f4cfeebe1e43e1873c98bb12fe7b7855a062eaf82cd25019de24ec2422bdc607f18103725e08f262425a9e24d398e424758d32481c998e8bae623032e6391511ab23dba6bf07a513b31ae20d07454e57fe078f201a332064c27d7c8d8315892bf0341c947b4b460357034c66e5b0993888de95ab9dd070ae5d836d66fa12c5a1eebe46823466359e3f18365e58bca502247b1fb0aaec425d9f7970c13e8c6395ac74d8d71c868d3cedfb0e7d3a5ec8de571c59591a8c25139a39dfd7130f41c8aa728cf209d279e8e8c787cec7d87163e813af85c51a0c10a92e6b14a3020e09371c6d00d21a3f327f88a8716aab9e43d07f79e58f1e969b61198845123d8fdfe31cee676a121064e936119b2284ab8b48feb470e7bc1abdf836c18cc8eea636045b7d783cd369fc5d5bf4a048bb96e12c0106b15dff9ac4513de591815189bce7c06a8f98785d4622b2d9e46a3c7f1ad069583fb326e911352c28cf1221473af5a4a0f50c5678db1010a309b182d62b5b88099fd8690520c4144ff00b3590bf9f8262ffb27c124c1044466f849fe505162a9d593d989d8a1d7b0e1d8bb1dce8c62ed28ef64e786a993536b1d60e7a22e77258ff99e271047c270987993f76548a58dfcc96af8379e1e9c9db145b24f8cd3f81c2b1f61e3c83f374861e5182ea2dae12c2c390efccf806f961663386870070bdd874b0776c9fd8070a428c99d326d7cfd5d1cbab434daec6f30935013125efe2161e045c98845841ca87a9d99e666c5387f3253a62641b51053e6fc747651d27d18b78fbf9936f644ca390c688bfc64694630628c8ad47ec300ae868445219092eca5d89200347713e44d4028a5db1fe88b9c690931e0e1cfb4e1d3820abcf452ac1e3ea562d9b18ea3352f63e364d633dfc04c189f3fa5368347365be186824c93f2d22368448f63f62499e747e4040d19a3fce8b30e0ef493f43d1830bc9b118c8302abb97e682156635be0688489dba25371858edbfb8ba6d78332aabd169a302b2f3a9c0c722ddcb7bf300436c7bdd3a464d1ed69b61118c525aabd7dc7f1e8d3b337dd55154631fece4535132d66d75cebecf08d588471f8b022dd81d3b9f418365e58bca53f189cb46ef84c18686342e56dcd8e438075b640632f5aab283195e5aa99dc636e754f64c7cfd5d1c9a3458e029d75bbce64e1aa0d8f535d108942bd91bddde2141e6197e61a18f55316abec7f512ba7bfe43a79fd8f5f03b4292c31dbc07b5b96d25d034c162cbcc3100c369d99698f238e2ec1a4534df48314da1b449901fcc1957be8115b9cb5928a791468531f3b8c5545cf2197a39e526b485aaae54e6b112f09f78fc5739f5b1793dcce00ddc72bcf22c92c51a990f1a888b439e4bf22898d73beb89a41eb469e924f02fe7e0acd823a3a87c726623d7c0c5bec2a45b17b1e0ecc7959e9bc2d6643dd5f98c3720725dbedfd482840e57672310f0b52808f5f01cfad8bc9ee67010b555ca1c9221e2b9f43c9d90548b62f60cc334624de4306fa0c73a99dea6841cdbc824d71f131280988d2af97d9db28353dd561be828a8611169770a2a43814355a701c00f5e513c3c1e813629e60501b14ff000a1c4ff2b36fc774e87f8e1d8bbff069c651886c5ec8c32c754c44519488f7a4d3c22bc8f820595d207c86d971e77563a7655b4da7ac5226d08332c62b994e0c3e0cd26ff3164844c18e7d9c6e527042f284f1d01839ac923a0f28653791cd9cd4c4c4ea99a703633c7bd75250e452792533f4354ed6f423f68c125a7651bfa3195d17430c637e754f67327d78f67cfe60df63189b4fe762454537f0d920c6b9eae7f32199af044845fd5fc639d4cef534218dfcf00c18afb7b341407efd3715bfd4bd3ebe03324e4cd4cff8825e13ef1f8ae45791345dfebe3daecd3a444116d9e8e63a501669e58567d55ff8e6c1cb1f12ebb578c5b8490894161bafd339e95480bc062cf8e07ed52283414466b1bdcdf3f68bfcadc526ff008a996e5e83df5b9792dc8e01dd3a1fe38762effdaabb17e62b6d77c68151df5593fe2cff003a1d1e9376bd0a9f327567c0837c0948f334234cd1ab3798f27643e82bad1d7670f42dd946970b5dceafea2babc61a18c5d2d1fecdd8b70d63138a443baf931d9547d59f4314056a4b09488aa31b86b3c13c62cc24840a0b0c6dce8be89134aed21c3c6eee781828fa4a936844f6d730b1597f7e070f2006eab36eda3b1c630d0c15c63b45a2e817e232734473285be244f4535b419f8c73a99dea68415c76244cf668866d24aa9e271a8f41eeafcc61b905dc7d1827d1e10d4d2fcc01820f39ec42f12163dff3f21ff124583cd12ae5ff008e3c717d3536044ed3c4e67da0b2e3d97f2ea2b1edf4533497bf27f8eae115e459bca4fcb5ed0d7cc3797918adde8e83642e05796f38c18db8d53d939ab3590f2cbb126e856828afebbd0889e4b5bbac5fc5c5986b081c123605fcfc0aeafa973c1108c1e753fbfc247d0ac4d72faf6a60c631679183be371846bf0949b8528b5ff98686155dc8612cc8e92b8a7c17b0e71cf75cd7570f0b3db785ac88344bc4763fa189cb46ef84c1ab36044d2753c07743e8647bd19e62b50aaec4208706dad3a8c56f97327feb0d4ff39fff00121d3c823ee68e65f1e1dfed9ea3f1f642de3e8b78fa3c637ac175e9fa0dd0c35a4dfb0fe26fa269c0793a20f68edb476051e7d2f0f9454627f52fe106b23ce1e83d768baa9e6e69a61cdc65ac6a1cdeb45b4ee2a0588e639f5d055e4ff2b8a3872cba89cb45e24da25c635e5ac833cf20cd21b90e067865714662ef2e3818b70d61138245e2b9f41c3c83f240e84d62b0909055ebae35effcc750c6dc6a9ec96b12467e70c68598a83009ee43a37181fe3a46edbf62bc42d1f78f06d0d55c0d5786224e8dc28087f786a5ff001bf9f9f8e1d8bb0891ec7eff0018cfcc8a3cb7810e9e41274f4b9ac592930354398c43c224dee1510d134fe4c228f0abe58d9308664d688cb4603b96c9899b28551a9f86e2baea411dc64d4caa05962e8d1de9155f317b8f62eecd6d1d34283c22bc8fea5b3a8d9c5e0d3b5df27e545978f70f8afab3b1ec3bd87d5dce4aa7c032d7336c24fec3b5e13de53fad37bb5bc466c51c3c972cba98ba8f5442fed3506af20a5731bb21c61199c83d0bfb0ae49439f5b1793dcce01ccf9b22cc2e2622db3d1cd7ca3f931af8f27a2839c5ce511451e0c4816d8b31335f4cd5bf03cd5a6852ab78478f00e6d7ae6220a36d1aaf7b36130eddd413b728910b72f480f2b3d3785accbf5c3b1761a6d76379849a9f986a7f8f7d6e5e4b72387e526fff000874f21c2b1f63eefbf268c543687df5a741ae7523bd490c4142892bd2b9560c45fa67700964a50620fea69b2988d196fd08af229f5f81eb67b270786da242e736a09926ad62d3083058e383ad5d4b74861e51818bac38c5b0b034c6cef7864e117af77c4831319be1abc7846f074721568c3bbbd59a0f6a5fa71896f60e671353d71ddd69507b9f86bbc0acd813e371aca3ff001e2b9f40d7919f2c78c1914636e354f64bb1bbd7c07944884a141045f3cfb3e85bc7d114b6b7965182ab38dfbd05b4aa8db0bf9f9fe30d483ad3a2ff002ee9d0c47af8fcc6dce8be8afe7e070ec5d870ac7d8b78fbf98b0d5585ec9fe969b61197e4194b0d4e3a8c51e3e17835cfad8bc9ee6701868632dc7297b71ded280c465e87f3d4e37734553dfc37dda9183abf572791aa842dd61b012a3a3a9d3bf1a55b654247e4980e1cba4e5d2820c4a2da2e63445a5fd3465a0fe0845ebddb122db3168517e4d074a65cb1c301ca6ba7a8078ee5d0a1f1b2dda9f9a2aef33a3c3df7b9f82d88a228f0614dd6db845d98e04aabf3df421d3c8dc4cc2258b8419d9d4fa2c0e236db10d5c4d319b16ff8899ee7ebf2fe7e7ea247b1fbff0051ebe3f73b7ef8ba0c352ff3155a1ba7a3a0efc70f7175665db1c8af231b630a04287d6e95fce04664499bb54c27afaefde82eb98424faf02039e551bf45e153bf42687458f2f9b0b540e169bebf9161055f0653843f03f897be5f41bc2c448b635a878ae7d0e17a6138dbfc672c86ac9975f1ff0097854efd0c343052a84e524a341611c9cf491983ca244250a0c2048fecf5a0abccd56460c2b17185ec2477dfdc1f363c63c797d2526072f1aaa5e7e8c89fed8e7733b5090bfc39f7b1f83d8ca3ff00070ec5d870ac7dff00a44774ba319aabe4088c3a479741e1ccf36a7ae3bb6dbce38b72108e0e6b248e9f8287f1ec8ebf514556da2d913c314cdc5e99d909ac99f64c907368662bdeba9bd81ccb703544fcabf08c2a63fc7096d835de9efbc09ced43d17b624bb21c994b3228c1e0fccbf8d5b78fa264e3327a52ae7f6186d66b7884da82e0f0707b477a87b4b1b3c76facececc651f3428bc522dfe0558f277b29d05cb938bb11a441783bce85c0714d60acda1e072c86ac9975f1ff00e5c3b177f91ebe3fde1a98cc9384697f54644fd70ec5dffbc352fd5207f5396ae418973af4de28ff003ab9a84579121fd963522820898c7bbebc9861561d933f3f316e12422505811f9d9be1296070e5d272e94106cb87f353888b45e7ceda4c34236eaeadec9a48b81787ca5a446d249ba0cee9bae71ed58d86a5cb5fe522c468a7c17b19a97b16f1f45bc7d1917f4d244f45e0e2ff004ebc11b298c1c3c6eee78fc8adcbd97a0e158fbff10e9e438563ec61a90c352fd70ac7dfe676ff0076f1f7f3d54c5aafb0e158fbfcb78fbfa69c7159ca1fc0d73a91dea486357025c66c5b88a108bebe58d278e1076b2d1eeb0809a99766bc682a77e839f7b1f83d8ca23fa9a6e86dbb04e66693251b3e989955521019be3b8c15abe0c890e64ce2ef3ccc5c556ec3324f2500d1099b29b3916703c9c9ed0dea3c9c16f1da825e13f3a68e0165a4b0a09b99b668aa6e0666bc57d6cf7a98c4e2c57635b01820543cd4494ec9fda362c0d2156b12ef22304fa1f61dfe45f474762f30ff00386a5f96f1f438563eff00c4fc37da3f11df96f1f42dc3567916caabfeb0d4bf6de3efe1e172fcf875f4ef14622617412732568351b3894a0ce5f115079f1f74185f45945bcf3559448b1d68d871bc476445d9dcd43463cd1f0c90c5a9d9c51b83753a9d5b2f1fe6b91e97c114794c6f8d3898eb19f9e3578b8316612460515861a17f8e478fc6cbfb028c21487f9f0b7db394a48c38563ec61a97e38563eff0d1e41f465a3aff008b78fbf8e158fbfc70ec5d88f5f03cc35d692719f44bf9363eff002823d7c7e61a98c352fc66e4a5a229142a2fe7e7efaa79a708efdf2cb3124f852a08a8123b23a784094c96d024cb068835f1460e51b21f3d9eea2a7e616e105bc7dfdf01af25c642700e158fbff85ff854cb72f41532dcbd7e7aef17517cd838563ec38762ec47af8fc639d4cef5342fdb78fa3335e48d48ff0089f97f3f3f5c3b177fb3f0dd687d47ff00870ec5dfee1a9844cf73f5fe1133dcfd0c352ff850e9e7f5c3b177ff00275a745fae7d6c5e4f73380734f4cabfe47f31d030d4ff005c3b177fae1d8bbffa3a572ebfddbc7dfccc5de5c70373143962376ff4e1d8bbfc70ac7d8b78fa37ae549c7641ed69b61118bf9f824a7cbcb45ba7e5bc7d16f1f45bc7d16f1f44bc37da1f11ec73a995ea4a42de3e8b78fa2de3e8b9c5a5a4963fb6ff0083c8b90c4dcc4fc375a1f51ee67c9906e1f327b844ec798fdeb95271d9011f9599e129716f1f7fcdfcfc0e158fbfdb78fbfe18e7533bd4d085bc7d11ebe3f18e7733b50908394ccf46de461a97e61a983587117b5b79188f5f1fe357125c66e5b0f74cf2fcabc38563eff5e3b97418c975626ed5b8aa6db7acd447af8fd874f3fe293707ad9eebc1e1b70d4ff2e85c26eafeff0061fdc3a79ffa38762ec38563eff70d4bf5d69d1074ae5d7f8638ef875b19933f4cd3264552327dd29fbe1fee151a5fe22c27ba35fc6ef28a8787f82f64f2de322b71d1e2070ac7dffc61d3cfe5fcfc1e5fee151841a09b31e7ce4bfd61a9fedbc7dff8d41c4e0cd0bdf7fe697efe8a16406bf839f512f069fe6de3efe99582b7ba43d0ffd9);
INSERT INTO `user` (`id`, `user_type`, `email`, `last_name`, `first_name`, `sexe`, `birth_date`, `home_town`, `pasword`, `avatar`) VALUES
(41, 2, 'abid@gmail.com', 'ABID', 'Khaled', 'h', '0000-00-00', 1, 'abid', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `voted`
--

CREATE TABLE `voted` (
  `id` bigint(200) NOT NULL,
  `id_post` bigint(20) NOT NULL,
  `id_user` bigint(20) NOT NULL,
  `agree` tinyint(1) NOT NULL,
  `reason` text CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `voted`
--

INSERT INTO `voted` (`id`, `id_post`, `id_user`, `agree`, `reason`) VALUES
(35, 34, 1, 0, 'mais on a besion des livres pour pouvoir terminer nos PFE , pourquoi ne pas les ramenner juste apres les soutenances?!!!'),
(36, 32, 1, 1, 'enfiinnn <3 !!!'),
(37, 28, 1, 0, 'on a pas encore terminer le travail!! est-ce que vous pouver prolonger la date plus?!!!'),
(38, 23, 1, 1, ''),
(39, 31, 1, 1, ''),
(40, 26, 19, 0, 'ces etudiants risquent de rentrer au conseil dicipline'),
(41, 27, 19, 1, 'tres bien !'),
(42, 22, 19, 1, 'enfin le problem est regle !'),
(43, 20, 1, 0, 'on a pas bien travaille :('),
(44, 25, 1, 1, ''),
(45, 24, 1, 0, ''),
(46, 13, 1, 1, ''),
(47, 10, 1, 1, 'merci pour prologer la date'),
(48, 26, 1, 0, 'cette greve va nous causer un grand decalage'),
(49, 33, 1, 1, ''),
(50, 27, 1, 1, ''),
(51, 21, 1, 1, ''),
(52, 30, 1, 1, ''),
(53, 29, 1, 1, ''),
(54, 22, 1, 1, ''),
(55, 34, 2, 1, 'je pense que on doit les garder jusqu aux delibirations'),
(56, 32, 2, 1, ''),
(57, 28, 2, 0, ''),
(58, 23, 2, 1, ''),
(59, 31, 2, 1, ''),
(60, 20, 2, 0, ''),
(61, 25, 2, 0, ''),
(62, 24, 2, 0, ''),
(63, 13, 2, 1, ''),
(64, 10, 2, 1, 'merci...'),
(65, 26, 2, 1, ''),
(66, 33, 2, 1, ''),
(67, 27, 2, 0, ''),
(68, 22, 2, 1, ''),
(69, 21, 2, 1, 'cette seance est mieux que l autre'),
(70, 30, 2, 0, 'vous avez commencer tres tot'),
(71, 29, 2, 0, ''),
(72, 34, 4, 1, ''),
(73, 28, 4, 0, ''),
(74, 23, 4, 1, ''),
(75, 31, 4, 1, ''),
(76, 20, 4, 0, ''),
(77, 25, 4, 0, ''),
(78, 24, 4, 0, ''),
(79, 13, 4, 1, ''),
(80, 10, 4, 0, 'cette prolongation va nous causer de retard'),
(81, 26, 4, 1, 'plus de temps pour achever le travaill'),
(82, 33, 4, 1, ''),
(83, 22, 4, 1, 'parfait'),
(84, 21, 4, 0, ''),
(85, 30, 4, 1, ''),
(86, 29, 4, 0, ''),
(87, 34, 5, 1, ''),
(88, 28, 5, 0, ''),
(89, 23, 5, 1, ''),
(90, 20, 5, 1, ''),
(91, 31, 5, 1, ''),
(92, 26, 5, 1, ''),
(93, 25, 5, 1, ''),
(94, 33, 5, 1, ''),
(95, 27, 5, 1, 'j espere que vous n aller pas rapprocher la date de soutenance'),
(96, 22, 5, 1, 'tres bien...'),
(97, 21, 5, 0, ''),
(98, 29, 5, 1, ''),
(99, 23, 6, 1, ''),
(100, 28, 6, 0, ''),
(101, 26, 6, 1, ''),
(102, 27, 6, 0, ''),
(103, 22, 6, 1, ''),
(104, 21, 6, 0, ''),
(105, 23, 8, 1, ''),
(106, 28, 8, 0, ''),
(107, 25, 8, 0, ''),
(108, 24, 8, 1, ''),
(109, 26, 8, 1, ''),
(110, 33, 8, 0, ''),
(111, 27, 8, 1, ''),
(112, 22, 8, 0, ''),
(113, 21, 8, 1, ''),
(114, 30, 8, 1, ''),
(115, 23, 7, 1, ''),
(116, 31, 7, 0, ''),
(117, 26, 7, 0, ''),
(118, 27, 7, 1, ''),
(119, 30, 7, 0, ''),
(120, 29, 7, 0, ''),
(121, 22, 7, 1, '');

-- --------------------------------------------------------

--
-- Structure de la table `work`
--

CREATE TABLE `work` (
  `id_work` int(11) NOT NULL,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `work`
--

INSERT INTO `work` (`id_work`, `name`) VALUES
(3, 'Membre de La Scolarite'),
(2, 'Responsable de la Bibliotheque'),
(8, 'Responsable de la Bourse'),
(11, 'Responsable de Transfert'),
(9, 'Responsable des notes');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `aministrator`
--
ALTER TABLE `aministrator`
  ADD PRIMARY KEY (`id_user`),
  ADD KEY `id_user` (`id_user`),
  ADD KEY `id_work` (`id_work`);

--
-- Index pour la table `change_of_session`
--
ALTER TABLE `change_of_session`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id` (`id`),
  ADD KEY `id_old` (`id_old_session`),
  ADD KEY `id_new` (`id_new_session`);

--
-- Index pour la table `classroom`
--
ALTER TABLE `classroom`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `name` (`name`),
  ADD UNIQUE KEY `name_2` (`name`);

--
-- Index pour la table `consultation`
--
ALTER TABLE `consultation`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id_groupe` (`date`),
  ADD UNIQUE KEY `id_sesson_2` (`id_sesson`,`date`),
  ADD KEY `id_sesson` (`id_sesson`);

--
-- Index pour la table `days`
--
ALTER TABLE `days`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `name` (`name`);

--
-- Index pour la table `delege`
--
ALTER TABLE `delege`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id_delege` (`id_delege`,`id_level`),
  ADD UNIQUE KEY `id_delege_2` (`id_delege`,`id_level`),
  ADD KEY `id` (`id_delege`),
  ADD KEY `id_level` (`id_level`);

--
-- Index pour la table `document_request`
--
ALTER TABLE `document_request`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_user` (`id_user`);

--
-- Index pour la table `encadreur`
--
ALTER TABLE `encadreur`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id_student_2` (`id_student`),
  ADD UNIQUE KEY `id_encadreur` (`id_encadreur`,`id_student`),
  ADD UNIQUE KEY `id_encadreur_2` (`id_encadreur`,`id_student`),
  ADD UNIQUE KEY `id_encadreur_3` (`id_encadreur`,`id_student`),
  ADD KEY `id_student` (`id_student`);

--
-- Index pour la table `establishment`
--
ALTER TABLE `establishment`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `exam`
--
ALTER TABLE `exam`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_exam_type` (`id_exam_type`),
  ADD KEY `id_groupe` (`id_groupe`),
  ADD KEY `id_module` (`id_module`),
  ADD KEY `id_classroom` (`id_classroom`);

--
-- Index pour la table `exam_type`
--
ALTER TABLE `exam_type`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id_2` (`id`),
  ADD KEY `id` (`id`);

--
-- Index pour la table `groupe`
--
ALTER TABLE `groupe`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id_specialty` (`id_specialty`,`id_level`,`name`),
  ADD UNIQUE KEY `id_specialty_2` (`id_specialty`,`id_level`,`name`),
  ADD KEY `id_level` (`id_level`);

--
-- Index pour la table `groupe_member`
--
ALTER TABLE `groupe_member`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id_groupe_2` (`id_groupe`,`id_student`),
  ADD KEY `id_groupe` (`id_groupe`),
  ADD KEY `id_student` (`id_student`);

--
-- Index pour la table `holiday`
--
ALTER TABLE `holiday`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `begin_date` (`begin_date`,`end_date`),
  ADD KEY `id` (`id`);

--
-- Index pour la table `journal`
--
ALTER TABLE `journal`
  ADD PRIMARY KEY (`id_user`,`op`,`id`,`tab`),
  ADD KEY `id_user` (`id_user`);

--
-- Index pour la table `level`
--
ALTER TABLE `level`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `abrg` (`abrg`),
  ADD UNIQUE KEY `abrg_2` (`abrg`);

--
-- Index pour la table `location`
--
ALTER TABLE `location`
  ADD PRIMARY KEY (`id`),
  ADD KEY `location_ibfk_1` (`id_user`);

--
-- Index pour la table `marks`
--
ALTER TABLE `marks`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_exam` (`id_exam`),
  ADD KEY `id_student` (`id_student`);

--
-- Index pour la table `marks_displayed`
--
ALTER TABLE `marks_displayed`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_teacher` (`id_teacher`),
  ADD KEY `id_exam` (`id_exam`);

--
-- Index pour la table `meeting`
--
ALTER TABLE `meeting`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id` (`id`),
  ADD KEY `id_classroom` (`id_classroom`);

--
-- Index pour la table `meeting_presence`
--
ALTER TABLE `meeting_presence`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_user` (`id_user`),
  ADD KEY `id_meeting` (`id_meeting`);

--
-- Index pour la table `message`
--
ALTER TABLE `message`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_user` (`id_user`);

--
-- Index pour la table `messege_sent_to`
--
ALTER TABLE `messege_sent_to`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_user` (`id_user`),
  ADD KEY `messege_sent_to_ibfk_3` (`id_msg`);

--
-- Index pour la table `module`
--
ALTER TABLE `module`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `name` (`name`),
  ADD UNIQUE KEY `name_2` (`name`);

--
-- Index pour la table `news`
--
ALTER TABLE `news`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_user` (`id_user`);

--
-- Index pour la table `news_visibilitu`
--
ALTER TABLE `news_visibilitu`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_user` (`id_user`),
  ADD KEY `id_news` (`id_news`);

--
-- Index pour la table `pedagogical-procedure`
--
ALTER TABLE `pedagogical-procedure`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id` (`id`),
  ADD KEY `place` (`id_place`);

--
-- Index pour la table `request_done`
--
ALTER TABLE `request_done`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id` (`id`),
  ADD KEY `id_establishement` (`id_establishement`),
  ADD KEY `id_admin` (`id_admin`);

--
-- Index pour la table `responsble_teacher`
--
ALTER TABLE `responsble_teacher`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id` (`id`),
  ADD KEY `id_responsbility` (`id_responsbility`);

--
-- Index pour la table `responsibility`
--
ALTER TABLE `responsibility`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `name` (`name`);

--
-- Index pour la table `session`
--
ALTER TABLE `session`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_classroom` (`id_classroom`),
  ADD KEY `id_module` (`id_module`),
  ADD KEY `id_session_type` (`id_session_type`),
  ADD KEY `id_teacher` (`id_teacher`),
  ADD KEY `id_groupe` (`id_groupe`),
  ADD KEY `id_day` (`id_day`);

--
-- Index pour la table `session_type`
--
ALTER TABLE `session_type`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `name` (`name`);

--
-- Index pour la table `specialty`
--
ALTER TABLE `specialty`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `name` (`name`);

--
-- Index pour la table `strike`
--
ALTER TABLE `strike`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id` (`id`);

--
-- Index pour la table `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`id_user`),
  ADD UNIQUE KEY `mat` (`mat`),
  ADD KEY `id_user` (`id_user`);

--
-- Index pour la table `teacher`
--
ALTER TABLE `teacher`
  ADD PRIMARY KEY (`id_user`),
  ADD KEY `specialty` (`id_specialty`);

--
-- Index pour la table `town`
--
ALTER TABLE `town`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `town_name` (`town_name`);

--
-- Index pour la table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD KEY `home_town` (`home_town`);

--
-- Index pour la table `voted`
--
ALTER TABLE `voted`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id_post_2` (`id_post`,`id_user`),
  ADD KEY `id_post` (`id_post`),
  ADD KEY `id_user` (`id_user`);

--
-- Index pour la table `work`
--
ALTER TABLE `work`
  ADD PRIMARY KEY (`id_work`),
  ADD UNIQUE KEY `name` (`name`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `classroom`
--
ALTER TABLE `classroom`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=45;

--
-- AUTO_INCREMENT pour la table `days`
--
ALTER TABLE `days`
  MODIFY `id` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT pour la table `delege`
--
ALTER TABLE `delege`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `document_request`
--
ALTER TABLE `document_request`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT pour la table `encadreur`
--
ALTER TABLE `encadreur`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT pour la table `establishment`
--
ALTER TABLE `establishment`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT pour la table `exam_type`
--
ALTER TABLE `exam_type`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT pour la table `groupe`
--
ALTER TABLE `groupe`
  MODIFY `id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT pour la table `groupe_member`
--
ALTER TABLE `groupe_member`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT pour la table `level`
--
ALTER TABLE `level`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT pour la table `location`
--
ALTER TABLE `location`
  MODIFY `id` bigint(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;

--
-- AUTO_INCREMENT pour la table `marks`
--
ALTER TABLE `marks`
  MODIFY `id` bigint(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT pour la table `meeting_presence`
--
ALTER TABLE `meeting_presence`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `message`
--
ALTER TABLE `message`
  MODIFY `id` bigint(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=43;

--
-- AUTO_INCREMENT pour la table `messege_sent_to`
--
ALTER TABLE `messege_sent_to`
  MODIFY `id` bigint(200) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;

--
-- AUTO_INCREMENT pour la table `module`
--
ALTER TABLE `module`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=42;

--
-- AUTO_INCREMENT pour la table `news`
--
ALTER TABLE `news`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=35;

--
-- AUTO_INCREMENT pour la table `news_visibilitu`
--
ALTER TABLE `news_visibilitu`
  MODIFY `id` bigint(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=312;

--
-- AUTO_INCREMENT pour la table `responsibility`
--
ALTER TABLE `responsibility`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT pour la table `session`
--
ALTER TABLE `session`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=43;

--
-- AUTO_INCREMENT pour la table `session_type`
--
ALTER TABLE `session_type`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT pour la table `specialty`
--
ALTER TABLE `specialty`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT pour la table `town`
--
ALTER TABLE `town`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- AUTO_INCREMENT pour la table `user`
--
ALTER TABLE `user`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=42;

--
-- AUTO_INCREMENT pour la table `voted`
--
ALTER TABLE `voted`
  MODIFY `id` bigint(200) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=122;

--
-- AUTO_INCREMENT pour la table `work`
--
ALTER TABLE `work`
  MODIFY `id_work` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `aministrator`
--
ALTER TABLE `aministrator`
  ADD CONSTRAINT `aministrator_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `aministrator_ibfk_2` FOREIGN KEY (`id_work`) REFERENCES `work` (`id_work`) ON UPDATE CASCADE;

--
-- Contraintes pour la table `change_of_session`
--
ALTER TABLE `change_of_session`
  ADD CONSTRAINT `change_of_session_ibfk_2` FOREIGN KEY (`id_new_session`) REFERENCES `session` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `change_of_session_ibfk_3` FOREIGN KEY (`id_old_session`) REFERENCES `session` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `change_of_session_ibfk_4` FOREIGN KEY (`id`) REFERENCES `news` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `consultation`
--
ALTER TABLE `consultation`
  ADD CONSTRAINT `consultation_ibfk_5` FOREIGN KEY (`id`) REFERENCES `news` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `consultation_ibfk_6` FOREIGN KEY (`id_sesson`) REFERENCES `session` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `delege`
--
ALTER TABLE `delege`
  ADD CONSTRAINT `delege_ibfk_1` FOREIGN KEY (`id_delege`) REFERENCES `student` (`id_user`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `delege_ibfk_2` FOREIGN KEY (`id_level`) REFERENCES `level` (`id`) ON UPDATE CASCADE;

--
-- Contraintes pour la table `document_request`
--
ALTER TABLE `document_request`
  ADD CONSTRAINT `document_request_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `encadreur`
--
ALTER TABLE `encadreur`
  ADD CONSTRAINT `encadreur_ibfk_2` FOREIGN KEY (`id_encadreur`) REFERENCES `teacher` (`id_user`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `encadreur_ibfk_3` FOREIGN KEY (`id_student`) REFERENCES `student` (`id_user`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `exam`
--
ALTER TABLE `exam`
  ADD CONSTRAINT `exam_ibfk_2` FOREIGN KEY (`id_exam_type`) REFERENCES `exam_type` (`id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `exam_ibfk_3` FOREIGN KEY (`id`) REFERENCES `news` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `exam_ibfk_4` FOREIGN KEY (`id_groupe`) REFERENCES `groupe` (`id`),
  ADD CONSTRAINT `exam_ibfk_5` FOREIGN KEY (`id_module`) REFERENCES `module` (`id`),
  ADD CONSTRAINT `exam_ibfk_6` FOREIGN KEY (`id_classroom`) REFERENCES `classroom` (`id`);

--
-- Contraintes pour la table `groupe`
--
ALTER TABLE `groupe`
  ADD CONSTRAINT `groupe_ibfk_1` FOREIGN KEY (`id_level`) REFERENCES `level` (`id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `groupe_ibfk_2` FOREIGN KEY (`id_specialty`) REFERENCES `specialty` (`id`) ON UPDATE CASCADE;

--
-- Contraintes pour la table `groupe_member`
--
ALTER TABLE `groupe_member`
  ADD CONSTRAINT `groupe_member_ibfk_1` FOREIGN KEY (`id_groupe`) REFERENCES `groupe` (`id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `groupe_member_ibfk_2` FOREIGN KEY (`id_student`) REFERENCES `student` (`id_user`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `holiday`
--
ALTER TABLE `holiday`
  ADD CONSTRAINT `holiday_ibfk_1` FOREIGN KEY (`id`) REFERENCES `news` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `journal`
--
ALTER TABLE `journal`
  ADD CONSTRAINT `journal_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`);

--
-- Contraintes pour la table `location`
--
ALTER TABLE `location`
  ADD CONSTRAINT `location_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `marks`
--
ALTER TABLE `marks`
  ADD CONSTRAINT `marks_ibfk_1` FOREIGN KEY (`id_exam`) REFERENCES `exam` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `marks_ibfk_2` FOREIGN KEY (`id_student`) REFERENCES `student` (`id_user`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `marks_displayed`
--
ALTER TABLE `marks_displayed`
  ADD CONSTRAINT `marks_displayed_ibfk_4` FOREIGN KEY (`id_teacher`) REFERENCES `teacher` (`id_user`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `marks_displayed_ibfk_5` FOREIGN KEY (`id`) REFERENCES `news` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `marks_displayed_ibfk_6` FOREIGN KEY (`id_exam`) REFERENCES `exam` (`id`);

--
-- Contraintes pour la table `meeting`
--
ALTER TABLE `meeting`
  ADD CONSTRAINT `meeting_ibfk_5` FOREIGN KEY (`id`) REFERENCES `news` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `meeting_ibfk_6` FOREIGN KEY (`id_classroom`) REFERENCES `classroom` (`id`) ON UPDATE CASCADE;

--
-- Contraintes pour la table `meeting_presence`
--
ALTER TABLE `meeting_presence`
  ADD CONSTRAINT `meeting_presence_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `meeting_presence_ibfk_2` FOREIGN KEY (`id_meeting`) REFERENCES `meeting` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `message`
--
ALTER TABLE `message`
  ADD CONSTRAINT `message_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `messege_sent_to`
--
ALTER TABLE `messege_sent_to`
  ADD CONSTRAINT `messege_sent_to_ibfk_2` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `messege_sent_to_ibfk_3` FOREIGN KEY (`id_msg`) REFERENCES `message` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `news`
--
ALTER TABLE `news`
  ADD CONSTRAINT `news_ibfk_3` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`);

--
-- Contraintes pour la table `news_visibilitu`
--
ALTER TABLE `news_visibilitu`
  ADD CONSTRAINT `news_visibilitu_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `news_visibilitu_ibfk_2` FOREIGN KEY (`id_news`) REFERENCES `news` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `pedagogical-procedure`
--
ALTER TABLE `pedagogical-procedure`
  ADD CONSTRAINT `pedagogical-procedure_ibfk_2` FOREIGN KEY (`id_place`) REFERENCES `establishment` (`id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `pedagogical-procedure_ibfk_3` FOREIGN KEY (`id`) REFERENCES `news` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `request_done`
--
ALTER TABLE `request_done`
  ADD CONSTRAINT `request_done_ibfk_2` FOREIGN KEY (`id_establishement`) REFERENCES `establishment` (`id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `request_done_ibfk_3` FOREIGN KEY (`id`) REFERENCES `document_request` (`id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `request_done_ibfk_4` FOREIGN KEY (`id_admin`) REFERENCES `aministrator` (`id_user`);

--
-- Contraintes pour la table `responsble_teacher`
--
ALTER TABLE `responsble_teacher`
  ADD CONSTRAINT `responsble_teacher_ibfk_1` FOREIGN KEY (`id`) REFERENCES `teacher` (`id_user`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `responsble_teacher_ibfk_2` FOREIGN KEY (`id_responsbility`) REFERENCES `responsibility` (`id`);

--
-- Contraintes pour la table `session`
--
ALTER TABLE `session`
  ADD CONSTRAINT `session_ibfk_1` FOREIGN KEY (`id_classroom`) REFERENCES `classroom` (`id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `session_ibfk_10` FOREIGN KEY (`id_day`) REFERENCES `days` (`id`),
  ADD CONSTRAINT `session_ibfk_3` FOREIGN KEY (`id_module`) REFERENCES `module` (`id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `session_ibfk_7` FOREIGN KEY (`id_session_type`) REFERENCES `session_type` (`id`),
  ADD CONSTRAINT `session_ibfk_8` FOREIGN KEY (`id_teacher`) REFERENCES `teacher` (`id_user`),
  ADD CONSTRAINT `session_ibfk_9` FOREIGN KEY (`id_groupe`) REFERENCES `groupe` (`id`);

--
-- Contraintes pour la table `strike`
--
ALTER TABLE `strike`
  ADD CONSTRAINT `strike_ibfk_1` FOREIGN KEY (`id`) REFERENCES `news` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `student`
--
ALTER TABLE `student`
  ADD CONSTRAINT `student_ibfk_3` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `teacher`
--
ALTER TABLE `teacher`
  ADD CONSTRAINT `teacher_ibfk_2` FOREIGN KEY (`id_specialty`) REFERENCES `specialty` (`id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `teacher_ibfk_3` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `user_ibfk_1` FOREIGN KEY (`home_town`) REFERENCES `town` (`id`) ON UPDATE CASCADE;

--
-- Contraintes pour la table `voted`
--
ALTER TABLE `voted`
  ADD CONSTRAINT `voted_ibfk_2` FOREIGN KEY (`id_user`) REFERENCES `news_visibilitu` (`id_user`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
