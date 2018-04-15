CREATE TABLE product_prices(
  id varchar(100) NOT NULL,
  product_id varchar(100) NOT NULL,
  product_name varchar(100) NOT NULL,
  unit_price double NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;