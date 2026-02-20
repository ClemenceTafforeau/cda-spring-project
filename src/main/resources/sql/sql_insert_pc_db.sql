INSERT INTO pc_customers (email, password) VALUES 
('jean.dupont@example.com', '5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8'),
('marie.martin@example.com', '65e84be33532fb784c48129675f9eff3a682b27168c0ea744b2cf58ee02337c5'),
('luc.bernard@example.com', '741950ec8bfc7736d78b9eb2f82a8df725e539a4d8aa7a3e9b73e2fe1c59f1f3a'),
('sophie.lefevre@example.com', '3a7bd3e2360a3d29eea436fcfb7e44c735d117c42d1c1835420b6b9942dd4f1b'),
('pierre.durand@example.com', '7c4a8d09ca3762af61e59520943dc26494f8941bdbd5620bd703b6872b88f6d1'),
('camille.robert@example.com', '1a79a4d60de6718e8e5b326e338ae5339f8d8a7a5b45f7b075bc01f9e91e3f42'),
('antoine.moreau@example.com', 'd4735e3a265e16eee03f59718b9b5d03019c07d8b6c51f90da3a666eec13ab35'),
('laura.fontaine@example.com', '92eb5ffee6ae2fec3ad71c777531578f2d9255fb10396489259d91e17b16a052'),
('thomas.lemoine@example.com', 'b109f3bbbc244eb82441917ed06d618b9008dd09b3befd1b5e07394c706a8bb9'),
('emilie.girard@example.com', 'c5e478aba2865b73e0530d472dc65857f72f58b4d8e3a2b5e73d5f7c7d8a3b1e');

INSERT INTO pc_products (name, description, price, image, quantity) VALUES
('Dragonfire Polyhedral Dice Set', 'A stunning 7-piece polyhedral dice set with metallic red and gold accents, perfect for any tabletop RPG adventurer.', 19.99, 'dice_1.webp', 42),
('Frostbite Crystal Dice Set', 'This 7-piece dice set features clear blue dice with white numbering, evoking the chill of a winter''s night.', 24.99, 'dice_2.webp', 28),
('Obsidian Shadow Dice Set', 'A sleek 7-piece dice set in deep black with silver numbering, ideal for rogues and shadowy characters.', 17.99, 'dice_3.webp', 35),
('Emerald Forest Dice Set', 'A vibrant 7-piece dice set in translucent green with gold numbering, perfect for druids and rangers.', 22.99, 'dice_4.webp', 19),
('Arcane Swirl Dice Set', 'This 7-piece dice set features purple and blue swirls, perfect for spellcasters and magic users.', 21.99, 'dice_5.webp', 23),
('Dwarven Forge Dice Set', 'A sturdy 7-piece dice set in metallic gray with runic numbering, ideal for warriors and dwarven characters.', 27.99, 'dice_6.webp', 15);

INSERT INTO pc_orders (date, status, customer_id) VALUES
('2026-02-15 10:30:00', 'COMPLETED', 1),
('2026-02-16 14:15:00', 'IN_PROGRESS', 2),
('2026-02-17 09:45:00', 'PENDING', 3),
('2026-02-18 16:20:00', 'COMPLETED', 4),
('2026-02-19 11:00:00', 'CANCELED', 5),
('2026-02-20 13:30:00', 'IN_PROGRESS', 6),
('2026-02-21 08:15:00', 'PENDING', 7),
('2026-02-22 15:40:00', 'COMPLETED', 8),
('2026-02-23 12:05:00', 'IN_PROGRESS', 9),
('2026-02-24 17:25:00', 'PENDING', 10);

INSERT INTO pc_order_products (order_id, product_id, quantity) VALUES
(1, 1, 2),
(1, 3, 1),
(2, 2, 1),
(2, 5, 1),
(3, 4, 1),
(4, 6, 1),
(4, 1, 1),
(5, 3, 2),
(6, 2, 1),
(6, 4, 1),
(7, 5, 1),
(8, 6, 1),
(8, 1, 1),
(9, 3, 1),
(10, 2, 1),
(10, 4, 1);