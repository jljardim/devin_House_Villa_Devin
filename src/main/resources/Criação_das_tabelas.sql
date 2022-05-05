
DROP TABLE IF EXISTS user_role;
DROP TABLE IF EXISTS resident;
DROP TABLE IF EXISTS "user";
DROP TABLE IF EXISTS "role";

CREATE TABLE "user" (
                        id SERIAL PRIMARY KEY,
                        email VARCHAR(60) NOT NULL UNIQUE,
                        password VARCHAR(60) NOT NULL
);

create table resident (
    id SERIAL,
    uuid varchar(255) NOT NULL,
    name varchar (60) NOT NULL,
    lastname varchar (100) NOT NULL,
    datenasc date,
    income decimal(10,3) NOT NULL,
    cpf varchar (20) NOT NULL unique,
    user_id BIGINT NOT NULL UNIQUE,
    CONSTRAINT resident_table_user_fk                           
    FOREIGN KEY (user_id)                      
	REFERENCES "user" (id)
);



CREATE TABLE "role" (
                        id SERIAL PRIMARY KEY,
                        name VARCHAR(60) NOT NULL UNIQUE,
                        description TEXT
);

CREATE TABLE user_role (
                           user_id BIGINT NOT NULL,
                           role_id BIGINT NOT NULL,
                           CONSTRAINT user_role_table_user_fk
                               FOREIGN KEY (user_id)
                                   REFERENCES "user" (id),
                           CONSTRAINT user_role_table_role_fk
                               FOREIGN KEY (role_id)
                                   REFERENCES role (id)
);

INSERT INTO "user" (id, email, password) VALUES(1, 'jef@teste.com', '$2a$10$Nxv2VDK6JIZPZrZmWwfgFOgaDXbwf0h4DN3tI3wWcLVPOLvTlLHSC');
INSERT INTO "user" (id, email, password) VALUES(2, 'jlj@teste.com', '$2a$10$Nxv2VDK6JIZPZrZmWwfgFOgaDXbwf0h4DN3tI3wWcLVPOLvTlLHSC');

INSERT INTO resident( uuid, name, lastname, datenasc, income, cpf,user_id ) VALUES ( '8800099777', 'Jeferson', 'Jardim', '1988-04-05', 3.600, '365.108.738-11',1);
INSERT INTO resident( uuid, name, lastname, datenasc, income, cpf,user_id ) VALUES ( '8800099777', 'Jeferson', 'Jardim', '1988-04-05', 3.600, '369.108.738-12',2);

INSERT INTO role (name) VALUES('ADMIN');
INSERT INTO role (name) VALUES('USER');

INSERT INTO user_role (user_id, role_id) VALUES(1, 1);
INSERT INTO user_role (user_id, role_id) VALUES(1, 2);
INSERT INTO user_role (user_id, role_id) VALUES(2, 2);