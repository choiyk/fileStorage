CREATE TABLE myfile(
	id int PRIMARY KEY AUTO_INCREMENT,
	originName varchar(200) NOT NULL,
	fName varchar(255) NOT NULL,
	fSize int NOT NULL,
	fType varchar(10) NOT NULL,
	fDatetime timestamp
);

INSERT INTO myfile(originName, fName, fSize, fType)
	VALUES ('origin_name', 'origin_name_2021030909080000', '385', 'jpg');
	
SELECT *FROM myfile;