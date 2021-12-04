DROP TABLE IF EXISTS orders;
DROP SEQUENCE IF EXISTS global_seq;
CREATE SEQUENCE global_seq START WITH 100000;

CREATE TABLE orders
(
    id INTEGER NOT NULL AUTO_INCREMENT,
    client_email  VARCHAR(200) NOT NULL ,
    client_name  VARCHAR(200) NOT NULL ,
    order_name VARCHAR(200) NOT NULL ,
    delivery_address VARCHAR(200) NOT NULL ,
    status VARCHAR(200) NOT NULL,
    time_order TIMESTAMP NOT NULL,
    PRIMARY KEY (id)
)