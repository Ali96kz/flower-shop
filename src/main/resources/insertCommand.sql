INSERT INTO Origin(country, province) VALUES ('Kazakhstan','Astana');
INSERT INTO Origin(country, province) VALUES ('France','Paris');
INSERT INTO Origin(country, province) VALUES ('Kazakhstan','Aktobe');
INSERT INTO Origin(country, province) VALUES ('Kazakhstan','Pavlodar');
INSERT INTO Origin(country, province) VALUES ('Kazakhstan','Atyrau');
INSERT INTO Origin(country, province) VALUES ('Kazakhstan','Kokshetau');
INSERT INTO Origin(country, province) VALUES ('Russia','Moscow');
INSERT INTO Origin(country, province) VALUES ('Russia','Volgograd');

INSERT INTO temperature(min, max) VALUES (0, 5);
INSERT INTO temperature(min, max) VALUES (5, 10);
INSERT INTO temperature(min, max) VALUES (10 , 15);
INSERT INTO temperature(min, max) VALUES (15, 20);
INSERT INTO temperature(min, max) VALUES (20, 25);
INSERT INTO temperature(min, max) VALUES (25, 30);
INSERT INTO temperature(min, max) VALUES (30, 35);
INSERT INTO temperature(min, max) VALUES (35, 40);
INSERT INTO temperature(min, max) VALUES (40, 45);
INSERT INTO temperature(min, max) VALUES (45, 50);
INSERT INTO temperature(min, max) VALUES (55, 60);

INSERT INTO waterinWeek(min, max) VALUES (50, 100);
INSERT INTO waterinWeek(min, max) VALUES (100, 150);
INSERT INTO waterinWeek(min, max) VALUES ( 150, 200);
INSERT INTO waterinWeek(min, max) VALUES (200, 250);
INSERT INTO waterinWeek(min, max) VALUES (250, 300);
INSERT INTO waterinWeek(min, max) VALUES (300, 350);
INSERT INTO waterinWeek(min, max) VALUES (350, 400);


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

INSERT INTO Flower(name, growingConditionId, visualParametersId, flowerTypeId) VALUES ('rose', 2, 3, 1);
INSERT INTO Flower(name, growingConditionId, visualParametersId, flowerTypeId) VALUES ('red rose', 2, 3, 1);
INSERT INTO Flower(name, growingConditionId, visualParametersId, flowerTypeId) VALUES ('white rose', 2, 3, 1);
INSERT INTO Flower(name, growingConditionId, visualParametersId, flowerTypeId) VALUES ('black rose', 2, 3, 1);
INSERT INTO Flower(name, growingConditionId, visualParametersId, flowerTypeId) VALUES ('grey rose', 2, 3, 1);
INSERT INTO Flower(name, growingConditionId, visualParametersId, flowerTypeId) VALUES ('green rose', 2, 3, 1);
