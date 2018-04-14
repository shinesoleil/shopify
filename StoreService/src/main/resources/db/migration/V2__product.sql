CREATE TABLE products(
  id varchar(200) NOT NULL,
  store_id varchar(200) NOT NULL,
  name varchar(100) NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY(store_id) REFERENCES stores(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;