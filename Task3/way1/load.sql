
create table test(
Item_Name varchar(80),
Item_Type varchar(80),
Item_Price int,
Retrieval_City varchar(80),
Retrieval_Start_Time date,
Retrieval_Courier varchar(80),
Retrieval_Courier_Gender varchar(80),
Retrieval_Courier_Phone_Number varchar(80) not null,
Retrieval_Courier_Age float,
Delivery_Finished_Time date,
Delivery_City varchar(80),
Delivery_Courier varchar(80),
Delivery_Courier_Gender varchar(80),
Delivery_Courier_Phone_Number varchar(80),
Delivery_Courier_Age float,
Item_Export_City varchar(80),
Item_Export_Tax float,
Item_Export_Time date,
Item_Import_City varchar(80),
Item_Import_Tax float,
Item_Import_Time date,
Container_Code varchar(80),
Container_Type varchar(80),
Ship_Name varchar(80),
Company_Name varchar(80),
Log_Time timestamp
);
copy test from '/home/kali/data.csv' csv;


insert into city (
    select distinct Retrieval_City from test
    union select distinct Delivery_City from test
    union select distinct Item_Import_City from test
    union select distinct Item_Export_City from test
);
insert into containers(
    select distinct Container_Code,Container_Type from test where Container_Code is not null
);

insert into company (
    select distinct Company_Name from test
);

insert into item (
    select Item_Name,Item_Type,Item_Price,Log_Time,Container_Code from test
);

insert into courier(
    select distinct Retrieval_Courier,Retrieval_Courier_Phone_Number,Retrieval_Courier_Gender,Company_Name,Item_Export_City from test where Retrieval_Courier is not null
    union select distinct Delivery_Courier,Delivery_Courier_Phone_Number,Delivery_Courier_Gender,Company_Name,Item_Import_City from test where Delivery_Courier is not null
);

insert into ship(   
    select distinct Ship_Name,Company_Name from test where Ship_Name is not null
);

insert into import(
    select Item_Name,Item_Import_City,Item_Import_Time,Item_Import_Tax from test
);

insert into export(
    select Item_Name,Item_Export_City,Item_Export_Time,Item_Export_Tax from test
);

insert into retrieval(
    select Item_Name,Retrieval_Courier,Retrieval_Courier_Phone_Number,Retrieval_City,Retrieval_Start_Time,Retrieval_Courier_Age from test  
);

insert into delivery(
    select Item_Name,Delivery_Courier,Delivery_Courier_Phone_Number,Delivery_City,Delivery_Finished_Time,Delivery_Courier_Age from test where Delivery_Courier is not null
);
