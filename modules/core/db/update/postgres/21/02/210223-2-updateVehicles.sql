-- update LIPAFARE_VEHICLES set VEHICLE_CODE = <default_value> where VEHICLE_CODE is null ;
alter table LIPAFARE_VEHICLES alter column VEHICLE_CODE set not null ;
