source librodb-schema.sql

insert into users values('test', 'Test','test@acme.com');
insert into users values('alicia', 'Alicia', 'alicia@acme.com');
insert into users values('blas', 'Blas', 'blas@acme.com');

insert into libros values(1,'titol1', 'autor1', 'catala','edicio1', '2003-04-09', '2003-04-09', 'editorial1');
insert into libros values(2,'titol2', 'autor2', 'catala','edicio2', '2003-04-09', '2003-04-09', 'editorial2');
insert into libros values(3,'titol3', 'autor3', 'catala','edicio3', '2003-04-09', '2003-04-09', 'editorial3');
insert into libros values(4,'titol4', 'autor4', 'catala','edicio4', '2003-04-09', '2003-04-09', 'editorial4');
insert into libros values(5,'titol5', 'autor1', 'catala','edicio1', '2003-04-09', '2003-04-09', 'editorial5');
insert into libros values(6,'titol6', 'autor2', 'catala','edicio2', '2003-04-09', '2003-04-09', 'editorial6');
insert into libros values(7,'titol7', 'autor3', 'catala','edicio3', '2003-04-09', '2003-04-09', 'editorial7');
insert into libros values(8,'titol8', 'autor4', 'catala','edicio4', '2003-04-09', '2003-04-09', 'editorial8');
insert into libros values(9,'titol9', 'autor1', 'catala','edicio1', '2003-04-09', '2003-04-09', 'editorial9');
insert into libros values(10,'titol10', 'autor2', 'catala','edicio2', '2003-04-09', '2003-04-09', 'editorial10');
insert into libros values(11,'titol11', 'autor3', 'catala','edicio3', '2003-04-09', '2003-04-09', 'editorial11');
insert into libros values(12,'titol12', 'autor4', 'catala','edicio4', '2003-04-09', '2003-04-09', 'editorial12');
	
insert into resenas values(1,7,'alicia', '2003-04-09', 'libro muy recomendable merece una buena nota');
insert into resenas values(2,1,'alicia', '2003-04-09', 'libro muy recomendable merece una buena nota');
insert into resenas values(3,2,'blas', '2003-04-09', 'libro muy recomendable merece una buena nota');
insert into resenas values(4,2,'blas', '2003-04-09', 'libro muy recomendable merece una buena nota');
insert into resenas values(5,3,'alicia', '2003-04-09', 'libro muy recomendable merece una buena nota');
insert into resenas values(6,5,'alicia', '2003-04-09', 'libro muy recomendable merece una buena nota');
insert into resenas values(7,7,'blas', '2003-04-09', 'libro muy recomendable merece una buena nota');
insert into resenas values(8,3,'blas', '2003-04-09', 'libro muy recomendable merece una buena nota');
insert into resenas values(9,6,'alicia', '2003-04-09', 'libro muy recomendable merece una buena nota');
insert into resenas values(10,7,'alicia', '2003-04-09', 'libro muy recomendable merece una buena nota');
insert into resenas values(11,2,'blas', '2003-04-09', 'libro muy recomendable merece una buena nota');
insert into resenas values(12,8,'blas', '2003-04-09', 'libro muy recomendable merece una buena nota');
insert into resenas values(13,2,'alicia', '2003-04-09', 'libro muy recomendable merece una buena nota');
insert into resenas values(14,4,'alicia', '2003-04-09', 'libro muy recomendable merece una buena nota');
insert into resenas values(15,7,'blas', '2003-04-09', 'libro muy recomendable merece una buena nota');
insert into resenas values(16,2,'blas', '2003-04-09', 'libro muy recomendable merece una buena nota');


insert into autor (name) values ('autor1');
insert into autor (name) values ('autor2');
insert into autor (name) values ('autor3');
insert into autor (name) values ('autor4');
insert into autor (name) values ('autor5');
insert into autor (name) values ('autor6');
insert into autor (name) values ('autor7');
insert into autor (name) values ('autor8');
insert into autor (name) values ('autor9');
insert into autor (name) values ('autor10');

