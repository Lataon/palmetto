DROP TABLE IF EXISTS orders;
DROP SEQUENCE IF EXISTS global_seq;
CREATE SEQUENCE global_seq START WITH 100000;

CREATE TABLE IF NOT EXISTS orders
(
    id INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    client_email  VARCHAR(200) NOT NULL ,
    client_name  VARCHAR(200) NOT NULL ,
    order_name VARCHAR(200) NOT NULL ,
    delivery_address VARCHAR(200) NOT NULL ,
    status VARCHAR(200) NOT NULL,
    time_order TIMESTAMP NOT NULL
)
