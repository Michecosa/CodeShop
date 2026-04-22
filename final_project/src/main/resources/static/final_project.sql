-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Creato il: Apr 22, 2026 alle 11:06
-- Versione del server: 10.4.32-MariaDB
-- Versione PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `final_project`
--

-- --------------------------------------------------------

--
-- Struttura della tabella `carrello`
--

CREATE TABLE `carrello` (
  `id` bigint(20) NOT NULL,
  `id_utente` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `carrello`
--

INSERT INTO `carrello` (`id`, `id_utente`) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5),
(6, 6),
(7, 7),
(8, 8);

-- --------------------------------------------------------

--
-- Struttura della tabella `categoria`
--

CREATE TABLE `categoria` (
  `id` bigint(20) NOT NULL,
  `nome` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `categoria`
--

INSERT INTO `categoria` (`id`, `nome`) VALUES
(1, 'Snippet & Algoritmi'),
(2, 'Web Template'),
(3, 'Script Database'),
(4, 'Progetti Completi'),
(5, 'UI Kit & Design');

-- --------------------------------------------------------

--
-- Struttura della tabella `item_quantity`
--

CREATE TABLE `item_quantity` (
  `id` bigint(20) NOT NULL,
  `qtn` int(11) NOT NULL,
  `id_carrello` bigint(20) DEFAULT NULL,
  `id_ordine` bigint(20) DEFAULT NULL,
  `id_prodotto` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `item_quantity`
--

INSERT INTO `item_quantity` (`id`, `qtn`, `id_carrello`, `id_ordine`, `id_prodotto`) VALUES
(1, 1, 2, NULL, 4),
(4, 1, NULL, 1, 1),
(44, 1, 1, NULL, 2),
(48, 1, NULL, 8, 3),
(50, 1, NULL, 9, 3),
(52, 1, NULL, 10, 3),
(54, 1, NULL, 11, 3);

-- --------------------------------------------------------

--
-- Struttura della tabella `ordine`
--

CREATE TABLE `ordine` (
  `id` bigint(20) NOT NULL,
  `consegna` date DEFAULT NULL,
  `indirizzo` varchar(255) DEFAULT NULL,
  `pagato` bit(1) NOT NULL,
  `id_utente` bigint(20) DEFAULT NULL,
  `data_pagamento` date DEFAULT NULL,
  `data_ordine` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `ordine`
--

INSERT INTO `ordine` (`id`, `consegna`, `indirizzo`, `pagato`, `id_utente`, `data_pagamento`, `data_ordine`) VALUES
(1, '2026-04-09', 'Via Roma 12, Milano', b'1', 2, '2026-04-09', '2026-04-09'),
(2, '2026-04-14', 'Via Roma 12, Milano', b'1', 2, '2026-04-14', '2026-04-14'),
(3, '2026-04-11', 'Corso Vittorio 8, Torino', b'1', 3, '2026-04-11', '2026-04-11'),
(4, '2026-04-22', 'Corso Vittorio 8, Torino', b'0', 3, NULL, '2026-04-20'),
(5, '2026-04-07', 'Viale Europa 3, Roma', b'1', 4, '2026-04-07', '2026-04-07'),
(6, '2026-04-20', 'Via Giorgio Vasari 4, Napoli', b'0', 5, NULL, '2026-04-18'),
(7, '2026-04-20', 'Via Santa Palomba 9, Roma', b'0', 5, NULL, '2026-04-17'),
(8, '2026-04-21', 'donatomorra90@gmail.com', b'0', 5, NULL, '2026-04-21'),
(9, '2026-04-21', 'donatomorra90@gmail.com', b'0', 5, NULL, '2026-04-21'),
(10, '2026-04-21', 'donatomorra90@gmail.com', b'0', 5, NULL, '2026-04-21'),
(11, '2026-04-21', 'donatomorra90@gmail.com', b'0', 5, NULL, '2026-04-21');

-- --------------------------------------------------------

--
-- Struttura della tabella `prodotto`
--

CREATE TABLE `prodotto` (
  `id` bigint(20) NOT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `prezzo` double NOT NULL,
  `disponibile` bit(1) NOT NULL,
  `stock` int(11) NOT NULL,
  `link_download` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `prodotto`
--

INSERT INTO `prodotto` (`id`, `nome`, `prezzo`, `disponibile`, `stock`, `link_download`) VALUES
(1, 'Gestionale Scolastico', 14.99, b'1', 999999, 'https://drive.google.com/file/d/1Lm4wAZBZPwnGq0bnMCgy8pFa440BlahE/view?usp=drive_link'),
(2, 'Gestionale Hotel', 9.99, b'1', 999999, 'https://drive.google.com/file/d/1g_QgU2dXjsUJ_OuttkLMkBY8f-g-BbOa/view?usp=drive_link'),
(3, 'Gestione Calcoli Matematici', 12.99, b'1', 999999, 'https://drive.google.com/file/d/1XyK6v65hyxoZ9jIhVl3c3UIN_E7yu0RS/view?usp=drive_link'),
(4, 'Gestionale di un Negozio di Prodotti', 49.99, b'1', 999999, 'https://drive.google.com/file/d/1wDFFp8JNK9DQ0qU9GST1p1TTkpXszvDv/view?usp=drive_link'),
(5, 'World SQL', 119.99, b'1', 999, 'https://drive.google.com/file/d/1Yze-Md9w7QxIa9nNo8_AtgD1a3Dknuwz/view?usp=sharing'),
(6, 'Gestione Clinica SQL', 599.99, b'1', 100, 'https://drive.google.com/file/d/1D9hsj49kO4HCm7IffpLM92vQOZXjccWa/view?usp=sharing'),
(7, 'Classic Models SQL', 35.15, b'1', 100, 'https://drive.google.com/file/d/1wHP5W99f4UFL84Jo-W3mBnFgsVhgHnbz/view?usp=sharing'),
(8, 'Gestione Festival SQL', 150, b'1', 500, 'https://drive.google.com/file/d/1_ghvm--LYz6KOUsIy2F76S2ycoyLOTA7/view?usp=sharing'),
(11, 'Gestionale Pizzeria', 59.99, b'1', 999999, 'https://drive.google.com/file/d/12DUOjJvydLJ8whgvhaiehTF47JLxSz6e/view?usp=drive_link');

-- --------------------------------------------------------

--
-- Struttura della tabella `prodotto_to_categoria`
--

CREATE TABLE `prodotto_to_categoria` (
  `id_prodotto` bigint(20) NOT NULL,
  `id_categoria` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `prodotto_to_categoria`
--

INSERT INTO `prodotto_to_categoria` (`id_prodotto`, `id_categoria`) VALUES
(11, 4),
(4, 4),
(2, 4),
(1, 1),
(3, 1),
(6, 3),
(7, 3),
(8, 3),
(5, 3);

-- --------------------------------------------------------

--
-- Struttura della tabella `utente`
--

CREATE TABLE `utente` (
  `id` bigint(20) NOT NULL,
  `mail` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `roles` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `utente`
--

INSERT INTO `utente` (`id`, `mail`, `password`, `roles`, `username`) VALUES
(1, 'admin@codemarketplace.it', '$2a$10$p2sh7ng/VNXWtd78aYVXP.tqRiaeaoeRece50NzYn4uYUo2S1zhBu', 'ROLE_ADMIN', 'admin'),
(2, 'mario@mail.it', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uVs7.IEC.', 'ROLE_USER', 'mario'),
(3, 'giulia@mail.it', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uVs7.IEC.', 'ROLE_USER', 'giulia'),
(4, 'luca@mail.it', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uVs7.IEC.', 'ROLE_USER', 'luca'),
(5, 'donatomorra90@gmail.com', '$2a$10$.CyHBmFoGgCuZZjsTSR.2.LoSfsyKd8CJFVJoWMxD6saCVrNRc8dO', 'ROLE_ADMIN', 'donato'),
(6, 'mick@mick.mick', '$2a$10$wsUkT/z2mLu2u.Uh6RcoCey.rxRtU3eR6rV9lAWrrZhKKzUt1MoC2', 'ROLE_USER', 'mick_'),
(7, 'micheladellagatta1@gmail.com', '$2a$10$UlGAPi3ytpxkMJCDYmDrK.rSFHY7jtU4TuY3luFLCU5BWP24Y0yC6', 'ROLE_ADMIN', 'miche'),
(8, 'micheladellagatta06@gmail.com', '$2a$10$ax5LoKNcgorZrPzL/yHyZ.Lda/Ssg2Dn0d/uC/c.8/dpR26heuJhy', NULL, 'michela');

-- --------------------------------------------------------

--
-- Struttura della tabella `utente_ruolo`
--

CREATE TABLE `utente_ruolo` (
  `id` bigint(20) NOT NULL,
  `nome` varchar(50) NOT NULL,
  `id_utente` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `utente_ruolo`
--

INSERT INTO `utente_ruolo` (`id`, `nome`, `id_utente`) VALUES
(1, 'ROLE_USER', 6),
(2, 'ROLE_USER', 7),
(3, 'ROLE_USER', 8);

--
-- Indici per le tabelle scaricate
--

--
-- Indici per le tabelle `carrello`
--
ALTER TABLE `carrello`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKl287dga2nb4ahi1j34on39ruk` (`id_utente`);

--
-- Indici per le tabelle `categoria`
--
ALTER TABLE `categoria`
  ADD PRIMARY KEY (`id`);

--
-- Indici per le tabelle `item_quantity`
--
ALTER TABLE `item_quantity`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK8nu7h8792mtrbrg27rliswibd` (`id_carrello`),
  ADD KEY `FKiao22urd2ersaodgcu4ucla00` (`id_ordine`),
  ADD KEY `FK3o4h1cbyaiega6r09t4xbpuxg` (`id_prodotto`);

--
-- Indici per le tabelle `ordine`
--
ALTER TABLE `ordine`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKgsxxfj3dm1kfppteavqrvkwcr` (`id_utente`);

--
-- Indici per le tabelle `prodotto`
--
ALTER TABLE `prodotto`
  ADD PRIMARY KEY (`id`);

--
-- Indici per le tabelle `prodotto_to_categoria`
--
ALTER TABLE `prodotto_to_categoria`
  ADD KEY `FK8rddyu5sgh2h9rew02rara5jr` (`id_categoria`),
  ADD KEY `FK4jv72cip3f0cq3ij3otu8lole` (`id_prodotto`);

--
-- Indici per le tabelle `utente`
--
ALTER TABLE `utente`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKp72xb2utk1ksr1ymf57y37w04` (`mail`),
  ADD UNIQUE KEY `UK2vq82crxh3p7upassu0k1kmte` (`username`);

--
-- Indici per le tabelle `utente_ruolo`
--
ALTER TABLE `utente_ruolo`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKjjcwv1trgn2xeg7h4fa0sp7yw` (`id_utente`);

--
-- AUTO_INCREMENT per le tabelle scaricate
--

--
-- AUTO_INCREMENT per la tabella `carrello`
--
ALTER TABLE `carrello`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT per la tabella `categoria`
--
ALTER TABLE `categoria`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT per la tabella `item_quantity`
--
ALTER TABLE `item_quantity`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=55;

--
-- AUTO_INCREMENT per la tabella `ordine`
--
ALTER TABLE `ordine`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT per la tabella `prodotto`
--
ALTER TABLE `prodotto`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT per la tabella `utente`
--
ALTER TABLE `utente`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT per la tabella `utente_ruolo`
--
ALTER TABLE `utente_ruolo`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Limiti per le tabelle scaricate
--

--
-- Limiti per la tabella `carrello`
--
ALTER TABLE `carrello`
  ADD CONSTRAINT `FKimyxl9cko6g83slko5cldpbh` FOREIGN KEY (`id_utente`) REFERENCES `utente` (`id`);

--
-- Limiti per la tabella `item_quantity`
--
ALTER TABLE `item_quantity`
  ADD CONSTRAINT `FK3o4h1cbyaiega6r09t4xbpuxg` FOREIGN KEY (`id_prodotto`) REFERENCES `prodotto` (`id`),
  ADD CONSTRAINT `FK8nu7h8792mtrbrg27rliswibd` FOREIGN KEY (`id_carrello`) REFERENCES `carrello` (`id`),
  ADD CONSTRAINT `FKiao22urd2ersaodgcu4ucla00` FOREIGN KEY (`id_ordine`) REFERENCES `ordine` (`id`);

--
-- Limiti per la tabella `ordine`
--
ALTER TABLE `ordine`
  ADD CONSTRAINT `FKgsxxfj3dm1kfppteavrvkwcr` FOREIGN KEY (`id_utente`) REFERENCES `utente` (`id`);

--
-- Limiti per la tabella `prodotto_to_categoria`
--
ALTER TABLE `prodotto_to_categoria`
  ADD CONSTRAINT `FK4jv72cip3f0cq3ij3otu8lole` FOREIGN KEY (`id_prodotto`) REFERENCES `prodotto` (`id`),
  ADD CONSTRAINT `FK8rddyu5sgh2h9rew02rara5jr` FOREIGN KEY (`id_categoria`) REFERENCES `categoria` (`id`);

--
-- Limiti per la tabella `utente_ruolo`
--
ALTER TABLE `utente_ruolo`
  ADD CONSTRAINT `FKjjcwv1trgn2xeg7h4fa0sp7yw` FOREIGN KEY (`id_utente`) REFERENCES `utente` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
