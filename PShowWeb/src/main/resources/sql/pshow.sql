/* Drop Tables */

DROP TABLE IF EXISTS PS_ACL_MEMBER;
DROP TABLE IF EXISTS PS_ACE;
DROP TABLE IF EXISTS PS_CONTENT_ASSOC;
DROP TABLE IF EXISTS PS_CONTENT_FACET;
DROP TABLE IF EXISTS PS_CONTENT_PROPERTIES;
DROP TABLE IF EXISTS PS_CONTENT;
DROP TABLE IF EXISTS PS_CONTENT_DATA;
DROP TABLE IF EXISTS PS_ACL;
DROP TABLE IF EXISTS PS_AUTHORITY;
DROP TABLE IF EXISTS PS_PERMISSION;
DROP TABLE IF EXISTS PS_QNAME;
DROP TABLE IF EXISTS PS_NAMESPACE;
DROP TABLE IF EXISTS PS_PROPERTY_CLASS;
DROP TABLE IF EXISTS PS_WORKSPACE;
DROP TABLE IF EXISTS PS_USER_ROLE;
DROP TABLE IF EXISTS PS_USER;
DROP TABLE IF EXISTS PS_ROLE;
DROP TABLE IF EXISTS PS_VERSION_HISTORY;

DROP SEQUENCE IF EXISTS SEQ_NAMESPACE;
DROP SEQUENCE IF EXISTS SEQ_QNAME;
DROP SEQUENCE IF EXISTS SEQ_WORKSPACE;
DROP SEQUENCE IF EXISTS SEQ_CONTENT;
DROP SEQUENCE IF EXISTS SEQ_VERSION;


/* Create Tables */

CREATE TABLE PS_ROLE (
    ID BIGINT,
	NAME VARCHAR(255) NOT NULL UNIQUE,
	PERMISSIONS VARCHAR(255),
    PRIMARY KEY (ID)
) WITHOUT OIDS;

CREATE TABLE PS_USER (
   	ID BIGINT,
    LOGIN_NAME VARCHAR(255) NOT NULL UNIQUE,
    NAME VARCHAR(64),
    PASSWORD VARCHAR(255),
    SALT VARCHAR(64),
    EMAIL VARCHAR(128),
    STATUS VARCHAR(32),
    TEAM_ID BIGINT,
    PRIMARY KEY (ID)
) WITHOUT OIDS;

CREATE TABLE PS_USER_ROLE (
    USER_ID BIGINT NOT NULL,
    ROLE_ID BIGINT NOT NULL,
    PRIMARY KEY (USER_ID, ROLE_ID)
) WITHOUT OIDS;

CREATE TABLE PS_ACE
(
	ID BIGINT NOT NULL,
	PERMISSION_ID BIGINT NOT NULL,
	AUTHORITY_ID BIGINT NOT NULL,
	ALLOWED BOOLEAN NOT NULL,
	PRIMARY KEY (ID)
) WITHOUT OIDS;


CREATE TABLE PS_ACL
(
	ID BIGINT NOT NULL,
	ACL_ID VARCHAR(36) NOT NULL UNIQUE,
	INHERIT BOOLEAN NOT NULL,
	INHERITS_FROM BIGINT,
	PRIMARY KEY (ID)
) WITHOUT OIDS;


CREATE TABLE PS_ACL_MEMBER
(
	ID BIGINT NOT NULL,
	ACL_ID BIGINT NOT NULL,
	ACE_ID BIGINT NOT NULL,
	PRIMARY KEY (ID)
) WITHOUT OIDS;


CREATE TABLE PS_AUTHORITY
(
	ID BIGINT NOT NULL,
	AUTHORITY VARCHAR(100) NOT NULL,
	PRIMARY KEY (ID)
) WITHOUT OIDS;


CREATE TABLE PS_CONTENT
(
	ID BIGINT NOT NULL,
	TYPE_QNAME_ID BIGINT NOT NULL,
	ACL_ID BIGINT,
	WORKSPACE_ID BIGINT NOT NULL,
	UUID VARCHAR(36) NOT NULL,
	CREATOR VARCHAR(255),
	CREATED TIMESTAMP WITH TIME ZONE,
	MODIFIER VARCHAR(255),
	MODIFIED TIMESTAMP WITH TIME ZONE,
	VERSIONS BIGINT,
	PARENT_ID BIGINT NOT NULL,
	PRIMARY KEY (ID)
) WITHOUT OIDS;

CREATE TABLE PS_CONTENT_DATA
(
   CONTENT_URL CHARACTER VARYING(255), 
   MIMETYPE CHARACTER(20), 
   SIZE BIGINT, 
   NAME CHARACTER VARYING(255),
   PRIMARY KEY (CONTENT_URL)
) WITHOUT OIDS;

CREATE TABLE PS_CONTENT_ASSOC
(
	ID BIGINT NOT NULL,
	SOURCE_CONTENT_ID BIGINT,
	TARGET_CONTENT_ID BIGINT,
	ASSO_TYPE BIGINT,
	PRIMARY KEY (ID)
) WITHOUT OIDS;


CREATE TABLE PS_CONTENT_FACET
(
	CONTENT_ID BIGINT NOT NULL,
	QNAME_ID BIGINT NOT NULL,
	PRIMARY KEY (CONTENT_ID, QNAME_ID)
) WITHOUT OIDS;


CREATE TABLE PS_CONTENT_PROPERTIES
(
	CONTENT_ID BIGINT NOT NULL,
	ACTUAL_TYPE_N INT,
	BOOLEAN_V BOOLEAN,
	LONG_V BIGINT,
	FLOAT_V REAL,
	DOUBLE_V DOUBLE PRECISION,
	STRING_V VARCHAR(1024),
	SERIALIZABLE_V BYTEA,
	QNAME_ID BIGINT NOT NULL,
	LIST_INDEX INT,
	PRIMARY KEY (CONTENT_ID, QNAME_ID)
) WITHOUT OIDS;


CREATE TABLE PS_NAMESPACE
(
	ID BIGINT NOT NULL,
	VERSIONS BIGINT NOT NULL,
	URI VARCHAR NOT NULL,
	N_PREFIX VARCHAR(50),
	PRIMARY KEY (ID)
) WITHOUT OIDS;


CREATE TABLE PS_PERMISSION
(
	ID BIGINT NOT NULL,
	QNAME_ID BIGINT NOT NULL,
	NAME VARCHAR(100) NOT NULL,
	PERMISSION VARCHAR(255) NOT NULL,
	PRIMARY KEY (ID)
) WITHOUT OIDS;


CREATE TABLE PS_PROPERTY_CLASS
(
	ID INT NOT NULL,
	JAVA_CLASS VARCHAR(255) NOT NULL,
	SHORT_NAME VARCHAR(255) NOT NULL,
	PRIMARY KEY (ID)
) WITHOUT OIDS;


CREATE TABLE PS_QNAME
(
	ID BIGINT NOT NULL,
	NS_ID BIGINT NOT NULL,
	LOCAL_NAME VARCHAR(200) NOT NULL,
	PRIMARY KEY (ID)
) WITHOUT OIDS;


CREATE TABLE PS_WORKSPACE
(
	ID BIGINT NOT NULL,
	UUID VARCHAR(100) NOT NULL,
	CONTENT_ROOT_ID BIGINT,
	WORKSPACE_NAME VARCHAR(200) NOT NULL,
	PRIMARY KEY (ID)
) WITHOUT OIDS;

CREATE TABLE PS_VERSION_HISTORY
(
   CONTENT_ID BIGINT NOT NULL, 
   VERSION_CONTENT_ID BIGINT NOT NULL, 
   VERSION_LABEL CHARACTER VARYING(100), 
   ID BIGINT NOT NULL, 
   PRE_VERSION BIGINT NOT NULL DEFAULT -1, 
   VERSION_DESC CHARACTER VARYING(255), 
   IS_CURRENT BOOLEAN NOT NULL DEFAULT TRUE, 
   VERSION_TYPE INTEGER
) WITHOUT OIDS;

/* Create Foreign Keys */

ALTER TABLE PS_ACL_MEMBER
	ADD FOREIGN KEY (ACE_ID)
	REFERENCES PS_ACE (ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE PS_ACL_MEMBER
	ADD FOREIGN KEY (ACL_ID)
	REFERENCES PS_ACL (ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE PS_ACE
	ADD FOREIGN KEY (AUTHORITY_ID)
	REFERENCES PS_AUTHORITY (ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE PS_CONTENT_ASSOC
	ADD FOREIGN KEY (SOURCE_CONTENT_ID)
	REFERENCES PS_CONTENT (ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE PS_CONTENT_ASSOC
	ADD FOREIGN KEY (TARGET_CONTENT_ID)
	REFERENCES PS_CONTENT (ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE PS_CONTENT_FACET
	ADD FOREIGN KEY (CONTENT_ID)
	REFERENCES PS_CONTENT (ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE PS_CONTENT_PROPERTIES
	ADD FOREIGN KEY (CONTENT_ID)
	REFERENCES PS_CONTENT (ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE PS_QNAME
	ADD FOREIGN KEY (NS_ID)
	REFERENCES PS_NAMESPACE (ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE PS_ACE
	ADD FOREIGN KEY (PERMISSION_ID)
	REFERENCES PS_PERMISSION (ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE PS_CONTENT_PROPERTIES
	ADD FOREIGN KEY (ACTUAL_TYPE_N)
	REFERENCES PS_PROPERTY_CLASS (ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE PS_CONTENT_ASSOC
	ADD FOREIGN KEY (ASSO_TYPE)
	REFERENCES PS_QNAME (ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE PS_CONTENT_FACET
	ADD FOREIGN KEY (QNAME_ID)
	REFERENCES PS_QNAME (ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE PS_CONTENT_PROPERTIES
	ADD FOREIGN KEY (QNAME_ID)
	REFERENCES PS_QNAME (ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE PS_PERMISSION
	ADD FOREIGN KEY (QNAME_ID)
	REFERENCES PS_QNAME (ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;

CREATE SEQUENCE SEQ_NAMESPACE
   INCREMENT 1
   START 1;
   
CREATE SEQUENCE SEQ_QNAME
   INCREMENT 1
   START 1;

CREATE SEQUENCE SEQ_WORKSPACE
   INCREMENT 1
   START 1;
   
CREATE SEQUENCE SEQ_CONTENT
   INCREMENT 1
   START 1;
   
CREATE SEQUENCE SEQ_VERSION
   INCREMENT 1
   START 1;
   
INSERT INTO PS_ACL(ID, ACL_ID, INHERIT, INHERITS_FROM) VALUES (-1, '-1', FALSE, NULL);
INSERT INTO PS_USER (ID, LOGIN_NAME, NAME, EMAIL, PASSWORD, SALT, STATUS, TEAM_ID) VALUES(1,'admin','Admin','admin@springside.org.cn','691b14d79bf0fa2215f155235df5e670b64394cc','7efbd59d9741d34f','enabled',1);
INSERT INTO PS_USER (ID, LOGIN_NAME, NAME, EMAIL, PASSWORD, SALT, STATUS, TEAM_ID) VALUES(2,'user','Calvin','user@springside.org.cn','2488aa0c31c624687bd9928e0a5d29e7d1ed520b','6d65d24122c30500','enabled',1);
INSERT INTO PS_USER (ID, LOGIN_NAME, NAME, EMAIL, PASSWORD, SALT, STATUS, TEAM_ID) VALUES(3,'user2','Jack','jack@springside.org.cn','2488aa0c31c624687bd9928e0a5d29e7d1ed520b','6d65d24122c30500','enabled',1);
INSERT INTO PS_USER (ID, LOGIN_NAME, NAME, EMAIL, PASSWORD, SALT, STATUS, TEAM_ID) VALUES(4,'user3','Kate','kate@springside.org.cn','2488aa0c31c624687bd9928e0a5d29e7d1ed520b','6d65d24122c30500','enabled',1);
INSERT INTO PS_USER (ID, LOGIN_NAME, NAME, EMAIL, PASSWORD, SALT, STATUS, TEAM_ID) VALUES(5,'user4','Sawyer','sawyer@springside.org.cn','2488aa0c31c624687bd9928e0a5d29e7d1ed520b','6d65d24122c30500','enabled',1);
INSERT INTO PS_USER (ID, LOGIN_NAME, NAME, EMAIL, PASSWORD, SALT, STATUS, TEAM_ID) VALUES(6,'user5','Ben','ben@springside.org.cn','2488aa0c31c624687bd9928e0a5d29e7d1ed520b','6d65d24122c30500','enabled',1);

INSERT INTO PS_ROLE (ID, NAME, PERMISSIONS) VALUES(1,'Admin','user:view,user:edit');
INSERT INTO PS_ROLE (ID, NAME, PERMISSIONS) VALUES(2,'User','user:view');

INSERT INTO PS_USER_ROLE (USER_ID, ROLE_ID) VALUES(1,1);
INSERT INTO PS_USER_ROLE (USER_ID, ROLE_ID) VALUES(1,2);
INSERT INTO PS_USER_ROLE (USER_ID, ROLE_ID) VALUES(2,2);
INSERT INTO PS_USER_ROLE (USER_ID, ROLE_ID) VALUES(3,2);
INSERT INTO PS_USER_ROLE (USER_ID, ROLE_ID) VALUES(4,2);
INSERT INTO PS_USER_ROLE (USER_ID, ROLE_ID) VALUES(5,2);
INSERT INTO PS_USER_ROLE (USER_ID, ROLE_ID) VALUES(6,2);

INSERT INTO PS_PROPERTY_CLASS (ID, JAVA_CLASS, SHORT_NAME) VALUES(0, 'java.io.Serializable', 'any');
INSERT INTO PS_PROPERTY_CLASS (ID, JAVA_CLASS, SHORT_NAME) VALUES(1, 'java.lang.String', 'text');
INSERT INTO PS_PROPERTY_CLASS (ID, JAVA_CLASS, SHORT_NAME) VALUES(2, 'org.pshow.repo.datamodel.content.ContentData', 'content');
INSERT INTO PS_PROPERTY_CLASS (ID, JAVA_CLASS, SHORT_NAME) VALUES(3, 'java.lang.Integer', 'int');
INSERT INTO PS_PROPERTY_CLASS (ID, JAVA_CLASS, SHORT_NAME) VALUES(4, 'java.lang.Long', 'long');
INSERT INTO PS_PROPERTY_CLASS (ID, JAVA_CLASS, SHORT_NAME) VALUES(5, 'java.lang.Float', 'float');
INSERT INTO PS_PROPERTY_CLASS (ID, JAVA_CLASS, SHORT_NAME) VALUES(6, 'java.lang.Double', 'double');
INSERT INTO PS_PROPERTY_CLASS (ID, JAVA_CLASS, SHORT_NAME) VALUES(7, 'java.util.Date', 'date');
INSERT INTO PS_PROPERTY_CLASS (ID, JAVA_CLASS, SHORT_NAME) VALUES(8, 'java.util.Calendar', 'datetime');
INSERT INTO PS_PROPERTY_CLASS (ID, JAVA_CLASS, SHORT_NAME) VALUES(9, 'java.lang.Boolean', 'boolean');