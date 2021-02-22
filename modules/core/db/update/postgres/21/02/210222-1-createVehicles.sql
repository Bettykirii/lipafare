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
    PLATE_NUMBER varchar(255) not null,
    VEHICLE_STATUS varchar(255),
    VEHICLE_CODE varchar(255),
    VEHICLE_OWNER varchar(255),
    --
    primary key (ID)
);