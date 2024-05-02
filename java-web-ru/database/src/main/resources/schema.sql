-- BEGIN
DROP TABLE IF EXISTS products;
CREATE TABLE products (
id int PRIMARY KEY AUTO_INCREMENT,
title varchar(255) not null,
price int not null
);
-- END
