alter table LIPAFARE_VEHICLES add column AMOUNT varchar(255) ^
update LIPAFARE_VEHICLES set AMOUNT = '' where AMOUNT is null ;
alter table LIPAFARE_VEHICLES alter column AMOUNT set not null ;
