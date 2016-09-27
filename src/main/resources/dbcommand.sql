CREATE TABLE UserRole
(
    id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name CHAR(32),
    deleteDay INT(11)
);

CREATE TABLE FlowerType
(
    id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name CHAR(64),
    deleteDay DATE
);
CREATE TABLE Origin
(
    id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    country CHAR(16),
    province CHAR(16),
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
    name CHAR(32),
    deleteDay DATE
);
CREATE TABLE User
(
    id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    nickname CHAR(16),
    firstName CHAR(16),
    lastName CHAR(16),
    dateBirthday DATE,
    originId INT(11),
    description CHAR(255),
    balance INT(11),
    password CHAR(255),
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
    colorSteam CHAR(32),
    colorLeaves CHAR(32),
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
    name CHAR(32),
    lovelight TINYINT(1),
    deleteDay DATE,
    CONSTRAINT growingTips___fk FOREIGN KEY (waterInWeekId) REFERENCES WaterInWeek (id),
    CONSTRAINT temperatureID FOREIGN KEY (temperatureId) REFERENCES Temperature (id),
    CONSTRAINT waterinWeekID FOREIGN KEY (waterInWeekId) REFERENCES WaterInWeek (id)
);

CREATE TABLE Flower
(
    id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name CHAR(16),
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
    description CHAR(255),
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