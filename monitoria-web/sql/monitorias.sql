delete from SalonEntity;
delete from SedeEntity;
delete from MonitorEntity;

insert into SedeEntity (id, name, direccion) values (100, 'Sede1', 'Calle 101 9 40');
insert into SedeEntity (id, name, direccion) values (100, 'Sede2', 'Calle 5 1 12');
insert into SedeEntity (id, name, direccion) values (100, 'Sede3', 'Calle 53 68 27');

insert into AuthorEntity (id, name,  disponibilidad, idSede, localizacion) values (100,'', true, 100,'W 505');
insert into AuthorEntity (id, name,  disponibilidad, idSede, localizacion) values (200,'', false, 300,'ML 302');
insert into AuthorEntity (id, name,  disponibilidad, idSede, localizacion) values (300,'', true, 300,'SD 703');
insert into AuthorEntity (id, name,  disponibilidad, idSede, localizacion) values (400,'', false, 100,'Q 907');
insert into AuthorEntity (id, name,  disponibilidad, idSede, localizacion) values (500,'', true, 200,'Z 101');


insert into MonitorEntity (id, name,  tipo, valPromedio, codigo) values (1,'Lucia Perez',1,4.6, 134);
insert into MonitorEntity (id, name,  tipo, valPromedio, codigo) values (2,'Pepe Castro',2,3.5, 14);
insert into MonitorEntity (id, name,  tipo, valPromedio, codigo) values (3,'Sofia Duarte',3,5.0, 13);
