delete from SalonEntity;
delete from SedeEntity;


insert into SedeEntity (id, name, direccion) values (100, 'Sede1', 'Calle 101 9 40');
insert into SedeEntity (id, name, direccion) values (100, 'Sede2', 'Calle 5 1 12');
insert into SedeEntity (id, name, direccion) values (100, 'Sede3', 'Calle 53 68 27');

insert into SalonEntity (id, name,  disponibilidad, localizacion) values (100,'', true, 'W 505');
insert into SalonEntity (id, name,  disponibilidad, localizacion) values (200,'', false, 'ML 302');
insert into SalonEntity (id, name,  disponibilidad, localizacion) values (300,'', true, 'SD 703');
insert into SalonEntity (id, name,  disponibilidad, localizacion) values (400,'', false, 'Q 907');
insert into SalonEntity (id, name,  disponibilidad, localizacion) values (500,'', true, 'Z 101');
