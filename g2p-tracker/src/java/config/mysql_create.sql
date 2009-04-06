CREATE TABLE todo ( 
    TODO_ID    	bigint(20) AUTO_INCREMENT NOT NULL,
    NAME       	varchar(256) NOT NULL,
    PRIORITY   	int(11) NOT NULL,
    DATE       	timestamp NOT NULL,
    DEADLINE   	timestamp NULL,
    DESCRIPTION	varchar(256) NULL,
    ALERT      	bit(1) NOT NULL,
    PRIMARY KEY(TODO_ID)
)
GO
