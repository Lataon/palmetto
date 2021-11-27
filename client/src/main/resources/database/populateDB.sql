DELETE FROM orders;

ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO orders VALUES
(1, 'vpetrov@jr1.com', 'Vassily Petrov1',  'pizza tempo1', 'Gomel1', 'COOK', '2021-11-25 09:30:20-07'),
(2, 'vpetrov@jr2.com', 'Vassily Petrov2',  'pizza tempo2', 'Gomel2', 'COOK', '2021-11-25 09:30:20-07'),
(3, 'vpetrov@jr3.com', 'Vassily Petrov3',  'pizza tempo3', 'Gomel3', 'COOK', '2021-11-25 09:30:20-07');