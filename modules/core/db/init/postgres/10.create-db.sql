-- begin LIPAFARE_CUSTOMERS
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
    CUSTOMER_ROLES varchar(50),
    SALES_AGENT_CODE varchar(255),
    PHONE_NUMBER varchar(255),
    PIN varchar(255),
    FIRST_NAME varchar(255) not null,
    OTHER_NAMES varchar(255) not null,
    ID_NUMBER varchar(255) not null,
    LOCALE varchar(255),
    --
    primary key (ID)
)^
-- end LIPAFARE_CUSTOMERS
-- begin LIPAFARE_VEHICLES
create table LIPAFARE_VEHICLES (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    PLATE_NUMBER varchar(255),
    VEHICLE_CODE varchar(255),
    VEHICLE_OWNER_ID uuid,
    --
    primary key (ID)
)^
-- end LIPAFARE_VEHICLES
-- begin LIPAFARE_VEHICLE_PERMISSION_REGISTER
create table LIPAFARE_VEHICLE_PERMISSION_REGISTER (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    CUSTOMER_ID uuid,
    PERMISSION varchar(50),
    --
    primary key (ID)
)^
-- end LIPAFARE_VEHICLE_PERMISSION_REGISTER
