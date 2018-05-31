# --- !Ups

create table "category" (
  "id" integer not null primary key autoincrement,
  "name" varchar not null
);

create table "product" (
  "id" integer not null primary key autoincrement,
  "name" varchar not null,
  "description" text not null,
  category int not null,
  foreign key(category) references category(id)
);

create table "cart" (
  " ID " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
  "orderId" INTEGER NOT NULL,
  FOREIGN KEY ("orderId") REFERENCES [order](id)
);

CREATE TABLE "order" (
  "id" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
  "orderDate" TEXT NOT NULL,
  "userId" INTEGER NOT NULL,
  "paymentId" INTEGER NOT NULL,
  FOREIGN KEY("userId") REFERENCES [user](id),
  FOREIGN KEY("paymentId") REFERENCES payment(id)
);

CREATE TABLE "orderdetails" (
  "id" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
  "orderDate" TEXT NOT NULL,
  "productPrice" REAL NOT NULL,
  "quantity" INTEGER NOT NULL,
  "totalPrice" REAL NOT NULL,
  "orderId" INTEGER NOT NULL,
  "productId" INTEGER NOT NULL,
  FOREIGN KEY("orderId") REFERENCES [order](id),
  FOREIGN KEY("productId") REFERENCES product(id)
);

CREATE TABLE "payment" (
  "id" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
  "paymentType" TEXT NOT NULL
);

CREATE TABLE "review" (
  "id" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
  "grade" INTEGER NOT NULL,
  "comment" TEXT NOT NULL,
  "productId" INTEGER NOT NULL,
  FOREIGN KEY("productId") REFERENCES product(id)
);

CREATE TABLE "user" (
  "id" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
  "username" TEXT NOT NULL,
  "password" TEXT NOT NULL
);

# --- !Downs
drop table "product" if exists;
drop table "category" if exists;
DROP TABLE "order" if exists;
DROP TABLE "cart" if exists;
DROP TABLE "orderdetails" if exists;
DROP TABLE "payment" if exists;
DROP TABLE "review" if exists;
DROP TABLE "user" if exists;