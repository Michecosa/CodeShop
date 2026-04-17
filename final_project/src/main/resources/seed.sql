-- =============================================================
-- SEED DATA - final_project
-- =============================================================

USE final_project;

-- -------------------------------------------------------------
-- UTENTE
-- Password in chiaro (da codificare con BCrypt in produzione):
--   admin123  -> $2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lh7y
--   user123   -> $2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uVs7.IEC.
-- -------------------------------------------------------------
INSERT INTO utente (id, username, mail, password, roles) VALUES
(1, 'admin',   'admin@shop.it',   '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lh7y', 'ROLE_ADMIN,ROLE_USER'),
(2, 'mario',   'mario@mail.it',   '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uVs7.IEC.', 'ROLE_USER'),
(3, 'giulia',  'giulia@mail.it',  '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uVs7.IEC.', 'ROLE_USER'),
(4, 'luca',    'luca@mail.it',    '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uVs7.IEC.', 'ROLE_USER');

-- -------------------------------------------------------------
-- CATEGORIA
-- -------------------------------------------------------------
INSERT INTO categoria (id, nome) VALUES
(1, 'Elettronica'),
(2, 'Abbigliamento'),
(3, 'Sport'),
(4, 'Casa e Cucina'),
(5, 'Libri');

-- -------------------------------------------------------------
-- PRODOTTO
-- -------------------------------------------------------------
INSERT INTO prodotto (id, nome, prezzo) VALUES
(1,  'Smartphone XZ Pro',       699.99),
(2,  'Cuffie Bluetooth',         59.99),
(3,  'Tastiera Meccanica',        89.99),
(4,  'Monitor 27"',              349.99),
(5,  'T-Shirt Cotone Bianca',     19.99),
(6,  'Jeans Slim Fit',            49.99),
(7,  'Giacca Impermeabile',       89.99),
(8,  'Scarpe da Corsa',           79.99),
(9,  'Tappetino Yoga',            24.99),
(10, 'Borraccia Termica',         18.99),
(11, 'Set Pentole Antiaderenti',  65.99),
(12, 'Lampada LED da Scrivania',  29.99),
(13, 'Clean Code - R. Martin',   34.99),
(14, 'Design Patterns',          39.99);

-- -------------------------------------------------------------
-- PRODOTTO_TO_CATEGORIA (join table ManyToMany)
-- -------------------------------------------------------------
INSERT INTO prodotto_to_categoria (id_prodotto, id_categoria) VALUES
(1,  1),  -- Smartphone      -> Elettronica
(2,  1),  -- Cuffie          -> Elettronica
(3,  1),  -- Tastiera        -> Elettronica
(4,  1),  -- Monitor         -> Elettronica
(5,  2),  -- T-Shirt         -> Abbigliamento
(6,  2),  -- Jeans           -> Abbigliamento
(7,  2),  -- Giacca          -> Abbigliamento
(7,  3),  -- Giacca          -> Sport (multi-categoria)
(8,  2),  -- Scarpe          -> Abbigliamento
(8,  3),  -- Scarpe          -> Sport
(9,  3),  -- Tappetino       -> Sport
(10, 3),  -- Borraccia       -> Sport
(10, 4),  -- Borraccia       -> Casa e Cucina
(11, 4),  -- Pentole         -> Casa e Cucina
(12, 4),  -- Lampada         -> Casa e Cucina
(12, 1),  -- Lampada         -> Elettronica
(13, 5),  -- Clean Code      -> Libri
(14, 5);  -- Design Patterns -> Libri

-- -------------------------------------------------------------
-- CARRELLO (uno per utente)
-- -------------------------------------------------------------
INSERT INTO carrello (id, id_utente) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4);

-- -------------------------------------------------------------
-- ORDINE
-- -------------------------------------------------------------
INSERT INTO ordine (id, id_utente, pagato, consegna, indirizzo) VALUES
(1, 2, true,  '2026-04-10', 'Via Roma 12, Milano'),
(2, 2, true,  '2026-04-15', 'Via Roma 12, Milano'),
(3, 3, true,  '2026-04-12', 'Corso Vittorio 8, Torino'),
(4, 3, false, '2026-04-22', 'Corso Vittorio 8, Torino'),
(5, 4, true,  '2026-04-08', 'Viale Europa 3, Roma');

-- -------------------------------------------------------------
-- ITEM_QUANTITY
-- Nota: id_carrello e id_ordine sono mutuamente esclusivi per
-- coerenza logica (un item appartiene al carrello OR all'ordine)
-- -------------------------------------------------------------

-- Carrello di mario (id=2): 2 prodotti nel carrello
INSERT INTO item_quantity (id, qtn, id_prodotto, id_carrello, id_ordine) VALUES
(1,  1, 4,  2, NULL),  -- Monitor nel carrello di mario
(2,  2, 10, 2, NULL);  -- 2x Borraccia nel carrello di mario

-- Carrello di giulia (id=3): 1 prodotto
INSERT INTO item_quantity (id, qtn, id_prodotto, id_carrello, id_ordine) VALUES
(3,  1, 6,  3, NULL);  -- Jeans nel carrello di giulia

-- Carrello di luca (id=4): vuoto (nessun item)

-- Ordine 1 (mario, pagato)
INSERT INTO item_quantity (id, qtn, id_prodotto, id_carrello, id_ordine) VALUES
(4,  1, 1,  NULL, 1),  -- Smartphone
(5,  1, 2,  NULL, 1);  -- Cuffie

-- Ordine 2 (mario, pagato)
INSERT INTO item_quantity (id, qtn, id_prodotto, id_carrello, id_ordine) VALUES
(6,  3, 5,  NULL, 2);  -- 3x T-Shirt

-- Ordine 3 (giulia, pagato)
INSERT INTO item_quantity (id, qtn, id_prodotto, id_carrello, id_ordine) VALUES
(7,  1, 13, NULL, 3),  -- Clean Code
(8,  1, 14, NULL, 3);  -- Design Patterns

-- Ordine 4 (giulia, non pagato)
INSERT INTO item_quantity (id, qtn, id_prodotto, id_carrello, id_ordine) VALUES
(9,  1, 11, NULL, 4),  -- Pentole
(10, 2, 12, NULL, 4);  -- 2x Lampada

-- Ordine 5 (luca, pagato)
INSERT INTO item_quantity (id, qtn, id_prodotto, id_carrello, id_ordine) VALUES
(11, 1, 8,  NULL, 5),  -- Scarpe da corsa
(12, 1, 9,  NULL, 5),  -- Tappetino Yoga
(13, 1, 7,  NULL, 5);  -- Giacca Impermeabile
