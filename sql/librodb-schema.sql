drop database if exists librodb;
create database librodb;

use librodb;

create table users (
    username	varchar(20) not null primary key,
	name		varchar(70) not null,
	email		varchar(255) not null
);

create table libros (
	id int not null auto_increment primary key,
	titulo varchar(100) not null,
	autor varchar(20) not null,
	lengua varchar(20) not null,
	edicion varchar(20) not null,
	fecha_ed date,
	fecha_imp date,
	editorial varchar(20) not null	
);

create table resenas (
	idres int not null auto_increment primary key,
	idlibro int not null,
    username	varchar(20) not null,
	fecha timestamp,
	texto varchar(500) not null,
	foreign key(username) references users(username),
	foreign key(idlibro) references libros(id)
);

create table autor (

    name  varchar (30) not null
    
);
