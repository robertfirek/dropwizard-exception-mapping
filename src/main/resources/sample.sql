CREATE TABLE IF NOT EXISTS errorTestTable (description VARCHAR(255) DEFAULT '');
DELETE FROM errorTestTable;
INSERT INTO errorTestTable VALUES ('ABC');
INSERT INTO errorTestTable VALUES ('DEF');
INSERT INTO errorTestTable VALUES ('GHI');
