CREATE TABLE orders (
  id           VARCHAR(200) NOT NULL,
  name         VARCHAR(100) NOT NULL,
  address      VARCHAR(200) NOT NULL,
  phone        VARCHAR(100) NOT NULL,
  total_amount DOUBLE       NOT NULL,
  PRIMARY KEY (id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE order_items (
  id               VARCHAR(200) NOT NULL,
  quantity         INTEGER      NOT NULL,
  amount           DOUBLE       NOT NULL,
  order_id         VARCHAR(200) NOT NULL,
  product_price_id VARCHAR(200) NOT NULL,
  product_id       VARCHAR(200) NOT NULL,
  product_name     VARCHAR(100) NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (order_id) REFERENCES orders (id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;