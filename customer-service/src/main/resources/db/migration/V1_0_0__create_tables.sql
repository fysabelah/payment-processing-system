DROP SEQUENCE IF EXISTS address_id_seq;
DROP SEQUENCE IF EXISTS client_id_seq;

CREATE TABLE address(
    id integer not null PRIMARY KEY,
    street varchar(255) not null,
    city varchar(255) not null,
    state varchar(255) not null,
    country varchar(255) not null,
    zip_code varchar(255) not null
);

CREATE TABLE client(
    id integer not null PRIMARY KEY,
    name varchar(255) not null,
    email varchar(255) not null,
    document varchar(255) not null,
    cellphone varchar(255) not null,
    address_id integer not null,
    unique(document),
    foreign key (address_id) references address
);

CREATE SEQUENCE address_id_seq
    AS BIGINT
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
    OWNED BY address.id;

CREATE SEQUENCE client_id_seq
    AS BIGINT
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
    OWNED BY client.id;