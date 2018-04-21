CREATE TABLE carts(
  id varchar(200) NOT NULL,
  owner varchar(100) UNIQUE NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;