CREATE TABLE Origin
(
  id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  country CHAR(16),
  province CHAR(16),
  deleteDAY DATE
);
CREATE TABLE aliveFlower
(
  id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  name CHAR(16),
  growingTipsId INT(11),
  aliveDay INT(11),
  idOrigin INT(11) NOT NULL,
  idVisualParameter INT(11),
  CONSTRAINT aliveFlowerfk FOREIGN KEY (growingTipsId) REFERENCES growingTips (id),
  CONSTRAINT OriginID FOREIGN KEY (idOrigin) REFERENCES Origin (id),
  CONSTRAINT visualParameterId FOREIGN KEY (idVisualParameter) REFERENCES visualParameters (id)
);
CREATE INDEX aliveFlowerfk ON aliveFlower (growingTipsId);
CREATE INDEX OriginID ON aliveFlower (idOrigin);
CREATE INDEX visualParameterId ON aliveFlower (idVisualParameter);
CREATE TABLE gardenFlower
(
  id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  name CHAR(16),
  idGrowCondetion INT(11),
  typeID INT(11) NOT NULL
);
CREATE TABLE growingTips
(
  id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  idWaterInWeek INT(11),
  idTemperature INT(11),
  CONSTRAINT growingTips___fk FOREIGN KEY (idWaterInWeek) REFERENCES waterinWeek (id),
  CONSTRAINT temperatureID FOREIGN KEY (idTemperature) REFERENCES temperature (id),
  CONSTRAINT waterinWeekID FOREIGN KEY (idWaterInWeek) REFERENCES waterinWeek (id)
);
CREATE INDEX temperatureID ON growingTips (idTemperature);
CREATE INDEX waterinWeekID ON growingTips (idWaterInWeek);
CREATE TABLE `order`
(
  id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  userId INT(11),
  productId INT(11),
  CONSTRAINT productID FOREIGN KEY (productId) REFERENCES product (id),
  CONSTRAINT userID FOREIGN KEY (userId) REFERENCES user (id)
);
CREATE INDEX productID ON `order` (productId);
CREATE INDEX userID ON `order` (userId);
CREATE TABLE product
(
  id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  name CHAR(16),
  descsribe CHAR(255),
  quantisty INT(11),
  productId INT(11),
  CONSTRAINT product___fk FOREIGN KEY (productId) REFERENCES aliveFlower (id)
);
CREATE INDEX product___fk ON product (productId);
CREATE TABLE syntheticFlower
(
  id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  name CHAR(16)
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
  lastname CHAR(16),
  Gender CHAR(8),
  Birthday DATE
);
CREATE TABLE visualParameters
(
  id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  colorLeaves CHAR(32),
  colorSteam CHAR(32),
  averageHeight INT(11)
);
CREATE TABLE waterinWeek
(
  id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  min INT(11),
  max INT(11)
);