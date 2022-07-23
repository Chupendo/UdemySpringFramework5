DROP TABLE IF exists person;
DROP TABLE IF exists person_account;
DROP TABLE IF exists type_identification_person;
DROP TABLE IF exists person_role;

CREATE TABLE person_account (
                                account_id SERIAL CHECK (account_id > 0),
                                nick VARCHAR(20) NOT NULL UNIQUE,
                                email VARCHAR NOT NULL UNIQUE,
                                pwd VARCHAR NOT NULL,
                                input_date DATE  ,
                                exit_date DATE,
                                PRIMARY KEY (account_id)
);

CREATE TABLE type_identification_person(
                                           type_identification_id SERIAL CHECK (type_identification_id > 0),
                                           name_identify VARCHAR(20),
                                           description VARCHAR,
                                           PRIMARY KEY (type_identification_id)
);

CREATE TABLE person_role(
                            role_id SERIAL CHECK (role_id > 0) ,
                            name_role VARCHAR(20),
                            description VARCHAR,
                            PRIMARY KEY (role_id)
);


CREATE TABLE person (
                        person_id SERIAL CHECK (person_id > 0),
                        account_id INT not null unique,
                        role_id INT ,
                        type_identification_person_id INT not null,
                        identifier VARCHAR(20),
                        first_name VARCHAR(20),
                        last_name VARCHAR(20),
                        surnames VARCHAR(50),
                        address VARCHAR,
                        birthday DATE,
                        post_card INT NOT NULL ,
                        town VARCHAR(20) NOT NULL ,
                        city VARCHAR(255) NOT NULL ,
                        country VARCHAR(15) NOT NULL,
                        photo VARCHAR,
                        PRIMARY KEY (person_id),
                        CONSTRAINT fk_client FOREIGN KEY (account_id) REFERENCES person_account(account_id) ON DELETE RESTRICT,
                        CONSTRAINT fk_role FOREIGN KEY (role_id) REFERENCES person_role(role_id) ON DELETE SET NULL,
                        CONSTRAINT fk_indentifier_person FOREIGN KEY (type_identification_person_id) REFERENCES type_identification_person(type_identification_id) ON DELETE RESTRICT
);



