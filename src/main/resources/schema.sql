CREATE TABLE ORDERS (
    customer_id VARCHAR(50) NOT NULL,
    order_number BIGINT NOT NULL,
    order_date DATE,
    description VARCHAR(255),
    PRIMARY KEY (customer_id, order_number)
);
