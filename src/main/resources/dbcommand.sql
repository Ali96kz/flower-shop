CREATE TABLE aliveFlower
(
    id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name CHAR(16),
    growingTipsId INT(11),
    aliveDay INT(11),
    CONSTRAINT aliveFlowerfk FOREIGN KEY (growingTipsId) REFERENCES growingTips (id)
);
CREATE TABLE growingTips
(
    id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    idWaterInWeek INT(11),
    idTemperature INT(11),
    CONSTRAINT temperatureID FOREIGN KEY (idTemperature) REFERENCES temperature (id),
    CONSTRAINT waterinWeekID FOREIGN KEY (idWaterInWeek) REFERENCES waterinWeek (id)
);
CREATE TABLE `order`
(
    id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    userId INT(11),
    productId INT(11),
    CONSTRAINT productID FOREIGN KEY (productId) REFERENCES product (id),
    CONSTRAINT userID FOREIGN KEY (userId) REFERENCES user (id)
);
CREATE TABLE product
(
    id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name CHAR(16),
    descsribe CHAR(255),
    quantisty INT(11),
    productId INT(11),
    CONSTRAINT product___fk FOREIGN KEY (productId) REFERENCES aliveFlower (id)
);
CREATE TABLE temperature
(
    id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    min INT(11),
    max INT(11)
);
CREATE TABLE user
(
    id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    nickname CHAR(16),
    firstname CHAR(16),
    lastname CHAR(16)
);
CREATE TABLE waterinWeek
(
    id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    min INT(11),
    max INT(11)
);