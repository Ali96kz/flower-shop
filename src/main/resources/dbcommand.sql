CREATE TABLE Origin
(
    id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    country CHAR(16),
    province CHAR(16),
    deleteDAY DATE
);
CREATE TABLE Product
(
    id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    description CHAR(255),
    originId INT(11),
    type CHAR(32),
    deleteDay DATE,
    price INT(11),
    flowerId INT(11),
    quantity INT(11),
    CONSTRAINT product_fk FOREIGN KEY (originId) REFERENCES Origin (id),
    CONSTRAINT Product___fk FOREIGN KEY (flowerId) REFERENCES Flower (id)
);
CREATE INDEX product_fk ON Product (originId);
CREATE INDEX Product___fk ON Product (flowerId);
CREATE TABLE User
(
    id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    nickname CHAR(16),
    firstname CHAR(16),
    lastname CHAR(16),
    Gender CHAR(8),
    Birthday DATE,
    originId INT(11),
    description CHAR(255),
    balance INT(11),
    new_column INT(11),
    CONSTRAINT user___fk FOREIGN KEY (originId) REFERENCES Origin (id)
);
CREATE INDEX user___fk ON User (originId);
CREATE TABLE `Order`
(
    id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    userId INT(11),
    productId INT(11),
    date DATE,
    CONSTRAINT productID FOREIGN KEY (productId) REFERENCES Product (id),
    CONSTRAINT userID FOREIGN KEY (userId) REFERENCES User (id)
);
CREATE INDEX productID ON `Order` (productId);
CREATE INDEX userID ON `Order` (userId);
CREATE TABLE Temperature
(
    id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    min INT(11),
    max INT(11)
);
CREATE TABLE VisualParameters
(
    id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    averageHeight INT(11),
    colorSteam CHAR(32),
    colorLeaves CHAR(32)
);
CREATE TABLE Flower
(
    id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name CHAR(16),
    GrowingConditionId INT(11),
    visualParametersId INT(11),
    CONSTRAINT FlowerType_fk FOREIGN KEY (visualParametersId) REFERENCES VisualParameters (id),
    CONSTRAINT FlowerType___fk FOREIGN KEY (GrowingConditionId) REFERENCES GrowingCondition (id)
);
CREATE INDEX FlowerType_fk ON Flower (visualParametersId);
CREATE INDEX FlowerType___fk ON Flower (GrowingConditionId);
CREATE TABLE GrowingCondition
(
    id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    waterInWeekId INT(11),
    temperatureId INT(11),
    name CHAR(32),
    lovelight TINYINT(1),
    CONSTRAINT growingTips___fk FOREIGN KEY (waterInWeekId) REFERENCES WaterInWeek (id),
    CONSTRAINT temperatureID FOREIGN KEY (temperatureId) REFERENCES Temperature (id),
    CONSTRAINT waterinWeekID FOREIGN KEY (waterInWeekId) REFERENCES WaterInWeek (id)
);
CREATE INDEX temperatureID ON GrowingCondition (temperatureId);
CREATE INDEX waterinWeekID ON GrowingCondition (waterInWeekId);
CREATE TABLE WaterInWeek
(
    id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    min INT(11),
    max INT(11)
);