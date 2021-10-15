
DROP TABLE IF EXISTS PROPERTIES;

create table PROPERTIES(
id integer not null auto_increment
, CREATED_ON datetime
, APPLICATION varchar(255)
, PROFILE varchar(255)
, LABEL varchar(255)
, KEY varchar(255)
, VALUE varchar(255)
, primary key (id));


INSERT INTO properties (CREATED_ON, APPLICATION, PROFILE, LABEL, KEY, VALUE)
VALUES (NULL,'espark-api-server','dev','latest','user-name','espark-user-dev');

INSERT INTO properties (CREATED_ON, APPLICATION, PROFILE, LABEL, KEY, VALUE)
VALUES (NULL,'espark-api-server','prod','latest','user-name','espark-user-prod');