
/* Drop Tables */

DROP TABLE IF EXISTS PS_ACL_MEMBER;
DROP TABLE IF EXISTS PS_ACE;
DROP TABLE IF EXISTS PS_CONTENT_ASSOC;
DROP TABLE IF EXISTS PS_CONTENT_FACET;
DROP TABLE IF EXISTS PS_CONTENT_PROPERTIES;
DROP TABLE IF EXISTS PS_CONTENT;
DROP TABLE IF EXISTS PS_ACL;
DROP TABLE IF EXISTS PS_AUTHORITY;
DROP TABLE IF EXISTS PS_PERMISSION;
DROP TABLE IF EXISTS PS_QNAME;
DROP TABLE IF EXISTS PS_NAMESPACE;
DROP TABLE IF EXISTS PS_PROPERTY_CLASS;
DROP TABLE IF EXISTS PS_WORKSPACE;




/* Create Tables */

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
	PRIMARY KEY (ID)
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


ALTER TABLE PS_CONTENT
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


ALTER TABLE PS_CONTENT
	ADD FOREIGN KEY (PARENT_ID)
	REFERENCES PS_CONTENT (ID)
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


ALTER TABLE PS_CONTENT
	ADD FOREIGN KEY (TYPE_QNAME_ID)
	REFERENCES PS_QNAME (ID)
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


ALTER TABLE PS_CONTENT
	ADD FOREIGN KEY (WORKSPACE_ID)
	REFERENCES PS_WORKSPACE (ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;

CREATE SEQUENCE SEQ_NAMESPACE
   INCREMENT 1
   START 1;
