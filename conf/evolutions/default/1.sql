# --- !Ups

create table "category" (
  "id" integer not null primary key autoincrement,
  "name" text not null
);

create table "product" (
  "id" integer not null primary key autoincrement,
  "name" text not null,
  "description" text not null,
  category int,
  foreign key(category) references category(id)
);

create table "cart" (
  "id" integer not null primary key autoincrement,
  "orderId" integer not null,
  foreign key ("orderId") references [order](id)
);

create table "order" (
  "id" integer not null primary key autoincrement,
  "orderDate" text not null,
  "userId" integer not null,
  "paymentId" integer not null,
  foreign key("userId") references [user](id),
  foreign key("paymentId") references payment(id)
);

create table "orderdetails" (
  "id" integer not null primary key autoincrement,
  "orderDate" text not null,
  "productPrice" real not null,
  "quantity" integer not null,
  "totalPrice" real not null,
  "orderId" integer not null,
  "productId" integer not null,
  foreign key("orderId") references [order](id),
  foreign key("productId") references product(id)
);

create table "payment" (
  "id" integer not null primary key autoincrement,
  "paymentType" text not null
);

create table "review" (
  "id" integer not null primary key autoincrement,
  "grade" integer not null,
  "comment" text not null,
  "productId" integer not null,
  foreign key("productId") references product(id)
);

create table "user" (
  "id" integer not null primary key autoincrement,
  "username" text not null,
  "email" text not null,
  "token" text not null
);

# --- !Downs

drop table "product" if exists;
drop table "category" if exists;
drop table "cart" if exists;
drop table "orderdetails" if exists;
drop table "payment" if exists;
drop table "review" if exists;
drop table "user" if exists;
drop table "order" if exists;
