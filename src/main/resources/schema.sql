DROP TABLE IF EXISTS users;

CREATE TABLE users (
    userid int NOT NULL PRIMARY KEY,
    age int,
    bonuspoint int not null,
    firstname varchar(100) NOT NULL,
    gender varchar(20) not null,
    lastname varchar(100)
);
	
DROP TABLE IF EXISTS room;
CREATE TABLE room (
    rmid int PRIMARY KEY,
    rmprice int not null,
    rmstatus varchar(100) NOT NULL,
    rmtype varchar(100) NOT NULL,
	userid int not null
    );

