CREATE TABLE cart_items(
  id varchar(200) NOT NULL,
  cart_id varchar(200) NOT NULL,
  product_id varchar(200) NOT NULL,
  quantity integer NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY(cart_id) REFERENCES carts(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;