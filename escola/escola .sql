-- phpMyAdmin SQL Dump
-- version 4.1.12
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 14-Abr-2015 às 02:59
-- Versão do servidor: 5.6.16
-- PHP Version: 5.5.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `escola`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `hibernate_sequence`
--

CREATE TABLE IF NOT EXISTS `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(1);

-- --------------------------------------------------------

--
-- Estrutura da tabela `member`
--

CREATE TABLE IF NOT EXISTS `member` (
  `id` bigint(20) NOT NULL,
  `email` varchar(255) NOT NULL,
  `name` varchar(25) NOT NULL,
  `phone_number` varchar(12) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_9qv6yhjqm8iafto8qk452gx8h` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `tb_aluno`
--

CREATE TABLE IF NOT EXISTS `tb_aluno` (
  `DATA_ANIVERSARIO` date NOT NULL,
  `MATRICULA` bigint(20) NOT NULL,
  `id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_bb5i9txqriuho8qfrojvhq7wl` (`MATRICULA`),
  KEY `FK_dukjmeukwfsm0mpbfrpgcykr` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `tb_aluno`
--

INSERT INTO `tb_aluno` (`DATA_ANIVERSARIO`, `MATRICULA`, `id`) VALUES
('1990-05-09', 11213643, 1),
('1993-12-15', 11213645, 2),
('1991-08-21', 11213647, 3);

-- --------------------------------------------------------

--
-- Estrutura da tabela `tb_pessoa`
--

CREATE TABLE IF NOT EXISTS `tb_pessoa` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `CPF` char(14) NOT NULL,
  `NOME` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_29q61rpxcuh0xf0p4xe8r3ae` (`CPF`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Extraindo dados da tabela `tb_pessoa`
--

INSERT INTO `tb_pessoa` (`id`, `CPF`, `NOME`) VALUES
(1, '860.518.214-81', 'JoÃ£o Baurete'),
(2, '637.456.259-35', 'TiÃ£o Baurete'),
(3, '377.914.546-45', 'Marisa Baurete'),
(4, '760.318.274-91', 'Carlos Baurete'),
(5, '537.156.289-25', 'Jacobis Baurete'),
(6, '277.314.526-05', 'Jim Baurete');

-- --------------------------------------------------------

--
-- Estrutura da tabela `tb_professor`
--

CREATE TABLE IF NOT EXISTS `tb_professor` (
  `SALARIO` decimal(19,2) NOT NULL,
  `id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_ol2cjgwcp0r1jfnwyscvu38sg` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `tb_professor`
--

INSERT INTO `tb_professor` (`SALARIO`, `id`) VALUES
('10252.22', 4),
('9252.73', 5),
('6252.20', 6);

--
-- Constraints for dumped tables
--

--
-- Limitadores para a tabela `tb_aluno`
--
ALTER TABLE `tb_aluno`
  ADD CONSTRAINT `FK_dukjmeukwfsm0mpbfrpgcykr` FOREIGN KEY (`id`) REFERENCES `tb_pessoa` (`id`);

--
-- Limitadores para a tabela `tb_professor`
--
ALTER TABLE `tb_professor`
  ADD CONSTRAINT `FK_ol2cjgwcp0r1jfnwyscvu38sg` FOREIGN KEY (`id`) REFERENCES `tb_pessoa` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
