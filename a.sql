create table item(
    name varchar(80) primary key ,
    type varchar(80),
    price int,
    Log_time timestamp,
    containers varchar(80),
    foreign key (containers)
        references containers(code)
);

create table containers(
    code varchar(80) primary key ,
    type varchar(80)
);

create table City(
    name varchar(80)
);

create table courier(
    name varchar(80) not null ,
    phone_number varchar(80) not null ,
    primary key (name,phone_number),
    age int,
    gender varchar(80),
    company varchar(80),
    city varchar(80),
    foreign key (city)
        references City(name),
    foreign key (company)
        references company(name)
);

create table company(
    name varchar(80) primary key
);

create table ship(
    name varchar(80) primary key ,
    company varchar(80),
    foreign key (company)
        references company(name)
);

create table import(
    item varchar(80) ,
    city varchar(80),
    primary key (item,city),
    time date,
    tax float,
    foreign key (item) references item(name),
    foreign key (city) references City(name)
);

create table export(
    item varchar(80) ,
    city varchar(80),
    primary key (item,city),
    time date,
    tax float,
    foreign key (item) references item(name),
    foreign key (city) references City(name)
);

create table retrieval(
    item varchar(80) ,
    courier varchar(80),
    primary key (item,courier),
    Start_time date,
    foreign key (item) references item(name),
    foreign key (courier) references courier(name)
);

create table delivery(
    item varchar(80) ,
    courier varchar(80),
    primary key (item,courier),
    Finish_time date,
    foreign key (item) references item(name),
    foreign key (courier) references courier(name)
);
