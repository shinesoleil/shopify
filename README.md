![Model](https://raw.githubusercontent.com/shinesoleil/shopify/master/model.jpeg)



[TOC]

# Set-Up (35 min)

## 1. Eureka Server (15 min)

## 2. Reverse Proxy (Nginx or Traefik) (20 min) 



# Store

## 1. Api (60 min)

#### store (30 min)

```java
Class Store {
	String id;
	String name;
}
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
}
```

POST

/stores/{sid}/products

GET

/stores/{sid}/products

GET

/stores/{sid}/products/{pid}

Delete

/stores/{sid}/products/{pid}

GET

/products

GET

/product/{pid}

## 2. Dockerfile & Docker-Compose (15 min)

## 3. Eureka Registering (5 min) 

## 4. Front-End Components 

#### &lt;ProductList/&gt;

#### &lt;ProductInfo/&gt;

#### &lt;StoreCreation/&gt;

#### &lt;StoreDeletion/&gt;

#### &lt;ProductCreation/&gt;

#### &lt;ProductDeletion/&gt;



# Product price

## 1. Api (30 min)

#### product price

```java
ProductPrice {
    String id;
    String productId;
    double amount;
}
```

POST

/product-prices

GET

/product-prices?product-id

## 2. Dockerfile & Docker-Compose (15 min)

## 3. Eureka Registering (5 min)

## 4. Front-End Components 

#### &lt;ProductPriceList/&gt;

#### &lt;ProductPriceInfo/&gt;

#### &lt;ProductPriceCreation/&gt;



# Inventory

## 1. Api (60 min)

#### inventory (30 min)

POST

/inventories

GET

/inventories

GET

/inventories/{iid}

#### unloading request (30 min)

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

POST 

/orders

GET   

/orders

GET  

/orders/{oid}

#### order item

POST

/orders/{oid}/order-items

GET

/orders/{oid}/order-items

GET

/orders/{oid}/order-items/{oiid}

#### payment

POST

/orders/{oid}/payment

GET

/orders/{oid}/payment

#### logistic order

POST

/orders/{oid}/logistic-order

GET

/orders/{oid}/logistic-order

#### logistic order confirmation

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



