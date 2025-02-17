INSERT INTO customers (fname, lname, email, created_at)
VALUES ('John', 'Doe', 'john.doe@example.com', CURRENT_TIMESTAMP);

INSERT INTO items (sku, name, price, on_offer, created_at, last_modified)
VALUES ('SKU12345', 'Laptop', 999.99, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO orders (customer_id, order_status, completed, created_at, updated_at)
VALUES (1, 'Pending', FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO order_items (order_id, item_id, quantity, price_at_time)
VALUES (1, 1, 2, 999.99);
