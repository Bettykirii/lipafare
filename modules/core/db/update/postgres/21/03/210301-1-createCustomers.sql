create table LIPAFARE_CUSTOMERS (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    CUSTOMER_TYPE varchar(50),
    SALES_AGENT_CODE varchar(255),
    PHONE_NUMBER varchar(255) not null,
    PIN varchar(255),
    FIRST_NAME varchar(255) not null,
    OTHER_NAMES varchar(255) not null,
    ID_NUMBER varchar(255) not null,
    LOCALE varchar(255),
    --
    primary key (ID)
);