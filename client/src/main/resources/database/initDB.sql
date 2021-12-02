DROP TABLE IF EXISTS test;

CREATE TABLE IF NOT EXISTS test;
CREATE TYPE STATUS AS ENUM ('WAIT', 'COOK', 'READY', 'DELIVER', 'FINISH');
CREATE SEQUENCE global_seq START WITH 100000;

(
    id BIGSERIAL PRIMARY KEY DEFAULT nextval('global_seq'),
    clientEmail  VARCHAR(200) NOT NULL ,
    clientName  VARCHAR(200) NOT NULL ,
    orderName VARCHAR(200) NOT NULL ,
    deliveryAddress VARCHAR(200) NOT NULL ,
    status STATUS NOT NULL,
    timeOrder TIMESTAMP NOT NULL
)
