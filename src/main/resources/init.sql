CREATE TABLE UserRole
(
    id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name CHAR(32)  CHARACTER SET utf8,
    deleteDay INT(11));

CREATE TABLE FlowerType
(
    id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name CHAR(64) CHARACTER SET utf8,
    deleteDay DATE
);
CREATE TABLE Origin
(
    id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    country CHAR(16) CHARACTER SET utf8,
    province CHAR(16) CHARACTER SET utf8,
    deleteDay DATE
);
CREATE TABLE Temperature
(
    id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    tmin INT(11),
    tmax INT(11),
    deleteDay DATE
);
CREATE TABLE Transaction
(
    id INT(11) PRIMARY KEY NOT NULL,
    name CHAR(32) CHARACTER SET utf8,
    deleteDay DATE
);
CREATE TABLE User
(
    id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    nickname CHAR(16) CHARACTER SET utf8,
    firstName CHAR(16) CHARACTER SET utf8,
    lastName CHAR(16) CHARACTER SET utf8,
    dateBirthday DATE,
    originId INT(11),
    balance INT(11),
    password CHAR(255) CHARACTER SET utf8,
    deleteDay DATE,
    userRoleId INT(11),
    CONSTRAINT User_fk FOREIGN KEY (userRoleId) REFERENCES UserRole (id),
    CONSTRAINT user___fk FOREIGN KEY (originId) REFERENCES Origin (id)
);
CREATE TABLE UserTransaction
(
    id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    userId INT(11),
    transactionId INT(11),
    sum INT(11),
    transactionDate DATE,
    deleteDay INT(11),
    CONSTRAINT UserTransaction_ibfk_1 FOREIGN KEY (transactionId) REFERENCES Transaction (id),
    CONSTRAINT UserTransaction_ibfk_2 FOREIGN KEY (userId) REFERENCES User (id)
);
CREATE TABLE VisualParameters
(
    id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    colorSteam CHAR(32) CHARACTER SET utf8,
    colorLeaves CHAR(32) CHARACTER SET utf8,
    deleteDay DATE
);
CREATE TABLE WaterInWeek
(
    id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    min INT(11),
    max INT(11),
    deleteDay DATE
);

CREATE TABLE GrowingCondition
(
    id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    waterInWeekId INT(11),
    temperatureId INT(11),
    name CHAR(32) CHARACTER SET utf8,
    lovelight boolean,
    deleteDay DATE,
    CONSTRAINT growingTips___fk FOREIGN KEY (waterInWeekId) REFERENCES WaterInWeek (id),
    CONSTRAINT temperatureID FOREIGN KEY (temperatureId) REFERENCES Temperature (id),
    CONSTRAINT waterinWeekID FOREIGN KEY (waterInWeekId) REFERENCES WaterInWeek (id)
);

CREATE TABLE Flower
(
    id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name CHAR(16) CHARACTER SET utf8,
    growingConditionId INT(11),
    visualParametersId INT(11),
    flowerTypeId INT(11),
    deleteDay DATE,
    averageHeight INT(11),
    CONSTRAINT FlowerType_fk FOREIGN KEY (visualParametersId) REFERENCES VisualParameters (id),
    CONSTRAINT FlowerType___fk FOREIGN KEY (growingConditionId) REFERENCES GrowingCondition (id),
        CONSTRAINT Flower___fk FOREIGN KEY (flowerTypeId) REFERENCES FlowerType (id)
        );
CREATE TABLE Product
(
    id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    description CHAR(255) CHARACTER SET utf8,
    originId INT(11),
    deleteDay DATE,
    price INT(11),
    flowerId INT(11),
    quantity INT(11),
    CONSTRAINT product_fk FOREIGN KEY (originId) REFERENCES Origin (id),
    CONSTRAINT Product___fk FOREIGN KEY (flowerId) REFERENCES Flower (id)
);
CREATE TABLE UserOrder
(
    id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    userId INT(11),
    productId INT(11),
    orderDate DATE,
    deleteDay DATE,
    CONSTRAINT productID FOREIGN KEY (productId) REFERENCES Product (id),
    CONSTRAINT userID FOREIGN KEY (userId) REFERENCES User (id)
);
INSERT INTO Origin(country, province) VALUES ('Kazakhstan','Astana');
INSERT INTO Origin(country, province) VALUES ('France','Paris');
INSERT INTO Origin(country, province) VALUES ('Kazakhstan','Aktobe');
INSERT INTO Origin(country, province) VALUES ('Kazakhstan','Pavlodar');
INSERT INTO Origin(country, province) VALUES ('Kazakhstan','Atyrau');
INSERT INTO Origin(country, province) VALUES ('Kazakhstan','Kokshetau');
INSERT INTO Origin(country, province) VALUES ('Russia','Moscow');
INSERT INTO Origin(country, province) VALUES ('Russia','Volgograd');

INSERT INTO Temperature(tmin, tmax) VALUES (0, 5);
INSERT INTO Temperature(tmin, tmax) VALUES (5, 10);
INSERT INTO Temperature(tmin, tmax) VALUES (10 , 15);
INSERT INTO Temperature(tmin, tmax) VALUES (15, 20);
INSERT INTO Temperature(tmin, tmax) VALUES (20, 25);
INSERT INTO Temperature(tmin, tmax) VALUES (25, 30);
INSERT INTO Temperature(tmin, tmax) VALUES (30, 35);
INSERT INTO Temperature(tmin, tmax) VALUES (35, 40);
INSERT INTO Temperature(tmin, tmax) VALUES (40, 45);
INSERT INTO Temperature(tmin, tmax) VALUES (45, 50);
INSERT INTO Temperature(tmin, tmax) VALUES (55, 60);

INSERT INTO WaterInWeek(min, max) VALUES (50, 100);
INSERT INTO WaterInWeek(min, max) VALUES (100, 150);
INSERT INTO WaterInWeek(min, max) VALUES ( 150, 200);
INSERT INTO WaterInWeek(min, max) VALUES (200, 250);
INSERT INTO WaterInWeek(min, max) VALUES (250, 300);
INSERT INTO WaterInWeek(min, max) VALUES (300, 350);
INSERT INTO WaterInWeek(min, max) VALUES (350, 400);
INSERT INTO GrowingCondition (waterInWeekId, temperatureId, name, lovelight) VALUES (1, 1, 'rooms', TRUE );
INSERT INTO GrowingCondition (waterInWeekId, temperatureId, name, lovelight) VALUES (2, 5, 'tropic', FALSE );
INSERT INTO GrowingCondition (waterInWeekId, temperatureId, name, lovelight) VALUES (7, 9, 'paname', FALSE );
INSERT INTO GrowingCondition (waterInWeekId, temperatureId, name, lovelight) VALUES (6, 10, 'word', TRUE );
INSERT INTO GrowingCondition (waterInWeekId, temperatureId, name, lovelight) VALUES (4, 6, 'words', FALSE );
INSERT INTO GrowingCondition (waterInWeekId, temperatureId, name, lovelight) VALUES (4, 3, 'some words', TRUE );

INSERT INTO FlowerType(name) VALUES ('Home flower');
INSERT INTO FlowerType(name) VALUES ('Synthetic flower');
INSERT INTO FlowerType(name) VALUES ('Garden flower');
INSERT INTO FlowerType(name) VALUES ('Street flower');
INSERT INTO FlowerType(name) VALUES ('Present flower');
INSERT INTO FlowerType(name) VALUES ('Growing flower');
INSERT INTO UserRole(name) VALUES ('admin');
INSERT INTO UserRole(name) VALUES ('manager');
INSERT INTO UserRole(name) VALUES ('customer');
INSERT INTO VisualParameters(colorSteam, colorLeaves) VALUES ('red', 'red');
INSERT INTO VisualParameters(colorSteam, colorLeaves) VALUES ('red', 'green');
INSERT INTO VisualParameters(colorSteam, colorLeaves) VALUES ('green', 'grey');
INSERT INTO VisualParameters(colorSteam, colorLeaves) VALUES ('white', 'yellow');

INSERT INTO Flower(name, growingConditionId, visualParametersId, flowerTypeId, averageHeight) VALUES ('rose', 2, 4, 1, 150);
INSERT INTO Flower(name, growingConditionId, visualParametersId, flowerTypeId, averageHeight) VALUES ('red rose', 2, 3, 2, 40);
INSERT INTO Flower(name, growingConditionId, visualParametersId, flowerTypeId, averageHeight) VALUES ('white rose', 2, 3, 3, 60);
INSERT INTO Flower(name, growingConditionId, visualParametersId, flowerTypeId, averageHeight) VALUES ('black rose', 2, 3, 4, 80);
INSERT INTO Flower(name, growingConditionId, visualParametersId, flowerTypeId, averageHeight) VALUES ('grey rose', 2, 3, 5, 95);
INSERT INTO Flower(name, growingConditionId, visualParametersId, flowerTypeId, averageHeight) VALUES ('green rose', 2, 3, 5, 60);

INSERT INTO Product(description, originId, price, flowerId, quantity) VALUES ('This is a description', 1, 456, 1, 369);
INSERT INTO Product(description, originId, price, flowerId, quantity) VALUES ('This is a description', 2, 456, 2, 487);
INSERT INTO Product(description, originId, price, flowerId, quantity) VALUES ('This is a description', 3, 456, 3, 456);
INSERT INTO Product(description, originId, price, flowerId, quantity) VALUES ('This is a description', 3, 456, 4, 200);
INSERT INTO Product(description, originId, price, flowerId, quantity) VALUES ('This is a description', 5, 456, 4, 2000);
INSERT INTO Product(description, originId, price, flowerId, quantity) VALUES ('This is a description', 4, 456, 6, 450);
INSERT INTO Product(description, originId, price, flowerId, quantity) VALUES ('This is a description', 8, 456, 5, 700);
INSERT INTO Product(description, originId, price, flowerId, quantity) VALUES ('This is a description', 7, 456, 4, 369);
INSERT INTO Product(description, originId, price, flowerId, quantity) VALUES ('This is a description', 6, 456, 5, 487);
INSERT INTO Product(description, originId, price, flowerId, quantity) VALUES ('This is a description', 5, 456, 2, 456);
INSERT INTO Product(description, originId, price, flowerId, quantity) VALUES ('This is a description', 4, 456, 2, 200);
INSERT INTO Product(description, originId, price, flowerId, quantity) VALUES ('This is a description', 2, 456, 3, 2000);
INSERT INTO Product(description, originId, price, flowerId, quantity) VALUES ('This is a description', 2, 456, 2, 450);

INSERT INTO
  User (nickname,
        firstName,
        lastName,
        dateBirthday,
        originId,
        balance,
        password,  userRoleId)
VALUES ('admin', 'admin','admin','1996-12-11', 2, 10000, 'fcea920f7412b5da7be0cf42b8c93759', 1);
INSERT INTO
  User (nickname,
        firstName,
        lastName,
        dateBirthday,
        originId,
        balance,
        password,  userRoleId)
VALUES ('Ali', 'Zhagparov','Armanovich','1996-12-11', 2, 10000, 'fcea920f7412b5da7be0cf42b8c93759', 3);

INSERT INTO
  User (nickname,
        firstName,
        lastName,
        dateBirthday,
        originId,
        balance,
        password,  userRoleId)
VALUES ('Fedor', 'Changaridi','Fedorovich','1996-12-11', 3, 10000, 'fcea920f7412b5da7be0cf42b8c93759', 2);

INSERT INTO Transaction(id, name) VALUES (1, 'add money');
INSERT INTO Transaction(id, name) VALUES (2, 'buy product');
INSERT INTO Transaction(id, name) VALUES (3, 'withdraw money');
