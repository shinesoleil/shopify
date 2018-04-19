![Model](https://raw.githubusercontent.com/shinesoleil/shopify/master/model.jpeg)



[TOC]

# Set-Up (35 min)

## 1. Eureka Server (15 min) - Done

## 2. Reverse Proxy (Traefik) (20 min) - Done 

Both Eureka Server and Traefik Proxy could be launched by docker-compose in directory EurekaServer

## 3. Config Server - Done

Config Server could be launched by docker-compose in directory ConfigServer

Config Files are stored at [github-config-server](https://github.com/shinesoleil/cloud-config)

# Store

## 1. Api (60 min) - Done

#### store (30 min)

```java
Class Store {
	String id;
	String name;
	List<Product> products;
}
```

```mysql
CREATE TABLE stores(
	id varchar(200) NOT NULL,
	name varchar(100) NOT NULL,
	PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
```

POST

/stores

GET

/stores

GET

/stores/{sid}

Delete

/stores/{sid}

#### product (30 min)

```java
Class Product {
	String id;
	String storeId;
	String name;
	ProductPrice productPrice;
}
```

```mysql
CREATE TABLE products(
	id varchar(200) NOT NULL,
	store_id varchar(200) NOT NULL,
	name varchar(100) NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY(store_id) REFERENCES stores(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
```

POST

/stores/{sid}/products

GET

/stores/{sid}/products

~~GET~~

~~/stores/{sid}/products/{pid}~~

Delete

/stores/{sid}/products/{pid}

~~GET~~

~~/products~~

GET

/product/{pid}

## 2. Dockerfile & Docker-Compose (15 min) - Done

## 3. Eureka Registering (5 min) - Done 

## 4. Front-End Components 

#### &lt;ProductList/&gt;

#### &lt;ProductInfo/&gt;

#### &lt;StoreCreation/&gt;

#### &lt;StoreDeletion/&gt;

#### &lt;ProductCreation/&gt;

#### &lt;ProductDeletion/&gt;



# Product price

## 1. Api (30 min) - Done

#### product price

```java
ProductPrice {
	String id;
	String productId;
	String productName;
	double unitPrice;
}
```

```mysql
CREATE TABLE product_prices(
	id varchar(100) NOT NULL,
	product_id varchar(100) NOT NULL,
	product_name varchar(100) NOT NULL,
	unit_price double NOT NULL,
	PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
```

POST

/products/{pid}/prices

GET

/products/{pid}/prices

GET

/products/{pid}/products-prices/{ppid}

GET

/products/{pid}/current-price

## 2. Dockerfile & Docker-Compose (15 min) - Done

## 3. Eureka Registering (5 min) - Done

## 4. Front-End Components 

#### &lt;ProductPriceList/&gt;

#### &lt;ProductPriceInfo/&gt;

#### &lt;ProductPriceCreation/&gt;



# Inventory

## 1. Api (60 min)

#### inventory (30 min)

```java
Class Inventory {
	String id;
	String productId;
	int quantity;
	TimeStamp createdAt;
}
```

POST

/products/{pid}/inventories

GET

/products/{pid}/inventories

GET

/products/{pid}/current-inventory

#### unloading request (30 min)

```java
??? Class UnloadingRequest {
	String id;
	String inventoryId;
	int unloadingAmount;
}
```

POST

/unloading-requests

GET

/unloading-requests

GET

/unloading-requests/{urid}

## 2. Dockerfile & Docker-Compose (15 min)

## 3. Eureka Registering (5 min)

## 4. Front-End Components 

#### &lt;InventoryList/&gt;

#### &lt;InventoryItemInfo/&gt;

#### &lt;UnloadRequestInfo/&gt;

#### &lt;UnloadRequestCreation/&gt;



# Order

## 1. Api (150 min)

#### order

```java
Class Order {
	String id;
	String name;
	String address;
	String phone;
	double totalAmount;
	List<OrderItem> orderItems;
}
```

```mysql
CREATE TABLE orders(
	id varchar(100) NOT NULL,
	name varchar(100) NOT NULL,
	address varchar(200) NOT NULL,
	phone varchar(100) NOT NULL,
	total_amount double NOT NULL,
	PRIMARY KEY (id),
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
```

POST 

/orders

GET   

/orders

GET  

/orders/{oid}

#### order item

```java
Class OrderItem {
	String id;
	String productPriceId;
	int quantity;
	double amount;
}
```

```mysql
CREATE TABLE order_items(
	id varchar(100) NOT NULL,
	quantity INTEGER NOT NULL,
	amount DOUBLE NOT NULL,
	order_id varchar(100) NOT NULL,
	product_price_id varchar(100) NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY(order_id) REFERENCES orders(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
```

POST

/orders/{oid}/order-items

GET

/orders/{oid}/order-items

GET

/orders/{oid}/order-items/{oiid}

#### payment

```java
Class Payment {
	String id;
	String orderId;
	double amount;
}
```

```mysql
CREATE TABLE payments(
	id varchar(100) NOT NULL,
	amount DOUBLE NOT NULL,
	order_id varchar(100) NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY(order_id) REFERENCES orders(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
```

POST

/orders/{oid}/payment

GET

/orders/{oid}/payment

#### logistic order

```java
Class LogisticsOrder {
    String id;
    String orderId;
    double transportFee;
}
```

```mysql
CREATE TABLE logistics_orders(
	id varchar(100) NOT NULL,
	transport_fee DOUBLE NOT NULL,
	order_id varchar(100) NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY(order_id) REFERENCES orders(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
```

POST

/orders/{oid}/logistic-order

GET

/orders/{oid}/logistic-order

#### logistic order confirmation

```java
Class LogisticsOrderConfirmation {
    String id;
    String logisticsOrderId;
    String receiverName;
}
```

```mysql
CREATE TABLE logistics_order_confirmations(
  id varchar(100) NOT NULL,
  receiver_name varchar(100) NOT NULL,
  logistics_order_id varchar(100) NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY(logistics_order_id) REFERENCES logistics_orders(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
```

POST

/orders/{oid}/logistic-order/confimation

GET

/orders/{oid}/logistic-order/confirmation

## 2. Dockerfile & Docker-Compose (15 min)

## 3. Eureka Registering (5 min)

## 4. Front-End Components 

#### &lt;OrderList/&gt;

#### &lt;OrderItemInfo/&gt;

#### &lt;OrderItemCreation/&gt;

#### &lt;PaymentInfo&gt;

#### &lt;PaymentCreation/&gt;



# Return 

## 1. Api (120 min)

#### return order 

```java
Class ReturnOrder {
  String id;
  String orderId;
  
}
```



POST

/return-orders

GET

/return-orders

GET

/return-orders/{roid}

#### return order item

POST

/return-orders/{roid}/return-items

GET

/return-orders/{roid}/return-items

GET

/return-orders/{roid}/return-items/{riid}

#### return receipt confirmation

POST

/return-orders/{roid}/confirmation

GET

/return-orders/{roid}/confirmation

####refund

POST

/return-orders/{roid}/refund

GET

/return-orders/{roid}/refund

## 2. Dockerfile & Docker-Compose (15 min)

## 3. Eureka Registering (5 min)

## 4. Front-End Components 

#### &lt;ReturnOrderList/&gt;

#### &lt;ReturnOrderInfo/&gt;

#### &lt;ReturnOrderCreation/&gt;

#### &lt;ReturnOrderItemInfo/&gt;



# Cart

## 1. Api (60 min)

#### cart (30 min)

POST

/cart

GET

/cart

#### cart item (30 min)

POST

/cart/cart-items

GET

/cart/cart-items

GET

/cart/cart-items/{ciid}

## 2. Dockerfile & Docker-Compose (15 min)

## 3. Eureka Registering (5 min)

## 4. Front-End Components 

#### &lt;Cart/&gt;

#### &lt;CartItemInfo/&gt;

#### &lt;AddToCart/&gt;

#### &lt;RemoveFromCart/&gt;



# user

## 1. Api (30 min)

#### user

POST

/users

GET

/users

GET

/users/{uid}

## 2. Dockerfile & Docker-Compose (15 min)

## 3. Eureka Registering (5 min)

## 4. Front-End Components 

#### &lt;UserList/&gt;

#### &lt;UserInfo/&gt;

#### &lt;UserCreation/&gt;

#### &lt;UserDeletion/&gt;











Questions:

one to many： 1. 并列(Store同时有多个有效Product)    2.替换(Product只有一个有效Price)

不同服务间，不加foreign key， 外键限制靠什么保障。 （聚合靠外键保障）

spring boot 下订单 order orderItems 的 api。