drop database if exists ProjectArquillianTest;
create database if not exists ProjectArquillian;

use ProjectArquillian;
drop table if exists `base_datas`;
drop table if exists `event_causes`;
drop table if exists `failures`;
drop table if exists `user_equipments`;
drop table if exists `operators`;
drop table if exists `errors`;
drop table if exists Users;

create table if not exists `base_datas` (
	`data_id` 		int not null AUTO_INCREMENT,
    `date_time` 	DATETIME,
    `failure_id`	INTEGER NOT NULL,
    `ue_id` 		INTEGER NOT NULL,
	`op_PK` 		INTEGER NOT NULL,
    `cell_id` 		INTEGER ,
    `duration` 		INTEGER ,
	`ec_PK` 		INTEGER NOT NULL,
    `ne_version` 	VARCHAR(5),
    `imsi` 			VARCHAR(250),
    `hier3_id` 		VARCHAR(250),
    `hier32_id` 	VARCHAR(250),
    `hier321_id` 	VARCHAR(250),
    Primary Key (`data_id`),
	FOREIGN KEY (`ec_PK`) 		REFERENCES `event_causes`(`ec_PK`),
    FOREIGN KEY (`failure_id`) 	REFERENCES `failures`(`failure_id`),
    FOREIGN KEY (`ue_id`) 		REFERENCES `user_equipments`(`ue_id`),
  FOREIGN KEY (`op_PK`) 		REFERENCES `operators`(`op_PK`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

create table if not exists `event_causes` (
	`ec_PK` 		INTEGER UNIQUE NOT NULL,
	`cause_code`	INTEGER ,
    `event_id` 		INTEGER ,
    `description` 	VARCHAR(250),
    primary key(`ec_PK`)
)ENGINE=MyISAM DEFAULT CHARSET=latin1;

create table if not exists `failures` (
	`failure_id` 	INTEGER UNIQUE NOT NULL,
    `description` 	VARCHAR(250),
    primary key(`failure_id`)
)ENGINE=MyISAM DEFAULT CHARSET=latin1;

create table if not exists `user_equipments` (
	`ue_id` 			INTEGER UNIQUE NOT NULL,
    `marketing_name` 	VARCHAR(250),
    `manufacturer` 		VARCHAR(250),
    `access_capability` VARCHAR(250),
    `model` 			VARCHAR(250),
    `vendor_name` 		VARCHAR(250),
    `ue_type` 			VARCHAR(250),
    `os` 				VARCHAR(250),
    `input_mode` 		VARCHAR(250),
    primary key(`ue_id`)
)ENGINE=MyISAM DEFAULT CHARSET=latin1;

create table if not exists `operators` (
	`op_PK` 		INTEGER UNIQUE NOT NULL,
	`mcc` 			INTEGER(3) ,
    `mnc` 			INTEGER(3) ,
    `country` 		VARCHAR(250),
    `operator_name` VARCHAR(250),
    primary key(`op_PK`)
)ENGINE=MyISAM DEFAULT CHARSET=latin1;

create table if not exists `errors` (
	`error_id` 			int not null AUTO_INCREMENT,
    `error_time` 		VARCHAR(250),
    `error_event_id` 	VARCHAR(250),
    `error_failure_id` 	VARCHAR(250),
    `error_ue_type` 	VARCHAR(250),
	`error_market` 		VARCHAR(250) ,
    `error_operator` 	VARCHAR(250) ,
    `error_cellId`	 	VARCHAR(250),
    `error_duration` 	VARCHAR(250) ,
	`error_cause_Code` 	VARCHAR(250) ,
    `error_ne_version` 	VARCHAR(250),
    `error_imsi` 		VARCHAR(250),
    `error_hier3_id` 	VARCHAR(250),
    `error_hier32_id` 	VARCHAR(250),
    `error_hier321_id` 	VARCHAR(250),
    Primary Key (`error_id`)
)ENGINE=MyISAM DEFAULT CHARSET=latin1;

create table Users
(id int not null auto_increment,
username varchar(100) not null, 
password varchar(20) not null,
usertype varchar(50) not null,
primary key (id));