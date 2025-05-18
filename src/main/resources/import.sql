INSERT INTO product (id, name, category, price, created_at) VALUES (1, 'iPhone 14', 'Electronics', 999.99, '2025-05-18 16:00:00');
INSERT INTO product (id, name, category, price, created_at) VALUES (2, 'Samsung Galaxy S23', 'Electronics', 899.99, '2025-05-18 16:00:00');
INSERT INTO product (id, name, category, price, created_at) VALUES (3, 'Java Programming Book', 'Books', 49.99, '2025-05-18 16:00:00');
INSERT INTO product (id, name, category, price, created_at) VALUES (4, 'Men T-Shirt', 'Clothing', 19.99, '2025-05-18 16:00:00');

INSERT INTO inventory (id, product_id, quantity, last_updated) VALUES (1, 1, 50, '2025-05-18 16:00:00');
INSERT INTO inventory (id, product_id, quantity, last_updated) VALUES (2, 2, 30, '2025-05-18 16:00:00');
INSERT INTO inventory (id, product_id, quantity, last_updated) VALUES (3, 3, 100, '2025-05-18 16:00:00');
INSERT INTO inventory (id, product_id, quantity, last_updated) VALUES (4, 4, 20, '2025-05-18 16:00:00');

INSERT INTO sales (id, product_id, quantity, total_amount, sale_date) VALUES (1, 1, 1, 999.99, '2025-05-01 10:00:00');
INSERT INTO sales (id, product_id, quantity, total_amount, sale_date) VALUES (2, 2, 2, 1799.98, '2025-05-02 11:00:00');
INSERT INTO sales (id, product_id, quantity, total_amount, sale_date) VALUES (3, 3, 3, 149.97, '2025-05-03 12:00:00');
INSERT INTO sales (id, product_id, quantity, total_amount, sale_date) VALUES (4, 4, 1, 19.99, '2025-05-04 13:00:00');
INSERT INTO sales (id, product_id, quantity, total_amount, sale_date) VALUES (5, 1, 2, 1999.98, '2025-05-05 14:00:00');
