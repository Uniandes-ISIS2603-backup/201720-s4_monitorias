delete from SalonEntity;
delete from SedeEntity;
delete from PagoEntity;
delete from MONITORENTITY;
delete from MONITORENTITY_IDIOMAENTITY;
delete from HorarioEntity;
delete from EstudianteEntity;

insert into SedeEntity (id, name, direccion) values (100, 'Sede1', 'Calle 101 9 40');
insert into SedeEntity (id, name, direccion) values (100, 'Sede2', 'Calle 5 1 12');
insert into SedeEntity (id, name, direccion) values (100, 'Sede3', 'Calle 53 68 27');


insert into SalonEntity (id, name,  disponibilidad, localizacion) values (100,'', 1, 'W 505');
insert into SalonEntity (id, name,  disponibilidad, localizacion) values (200,'', 0, 'ML 302');
insert into SalonEntity (id, name,  disponibilidad, localizacion) values (300,'', 1, 'SD 703');
insert into SalonEntity (id, name,  disponibilidad, localizacion) values (400,'', 0, 'Q 907');
insert into SalonEntity (id, name,  disponibilidad, localizacion) values (500,'', 1, 'Z 101');

insert into MonitorEntity (id, nombre,  tipo, valPromedio, codigo) values (1,'Lucia Perez',1,4.6, 134);
insert into MonitorEntity (id, nombre,  tipo, valPromedio, codigo) values (2,'Pepe Castro',2,3.5, 14);
insert into MonitorEntity (id, nombre,  tipo, valPromedio, codigo) values (3,'Sofia Duarte',3,5.0, 13);

insert into PagoEntity (id,  valor, monitor_id) values (1, 4.6, 2);
insert into PagoEntity (id,  estado, valor, monitor_id) values (2,1, 5.6, 1);
insert into PagoEntity (id,  valor, monitor_id) values (3, 3, 2);


insert into HorarioEntity (id, horaInicio, horaFin) values (1, 'Tue 26 0[20:05:36]0 COT 2017', 'Tue 29 0[22:05:36]0 COT 2017');
insert into HorarioEntity (id, horaInicio, horaFin) values (2, 'Tue 26 0[22:05:36]0 COT 2017', 'Tue 28 0[22:05:36]0 COT 2017');
insert into HorarioEntity (id, horaInicio, horaFin) values (3, 'Tue 26 0[21:05:36]0 COT 2017', 'Tue 27 0[22:05:36]0 COT 2017');


insert into EstudianteEntity (id, name,  codigo, penalizacion,ultimaMonitoria) values (1,'Cristian', 2015, 'false','20/10/2017');
insert into EstudianteEntity (id, name,  codigo, penalizacion,ultimaMonitoria) values (2,'Cristiannn', 20153, 'false','20/10/2017');
insert into EstudianteEntity (id, name,  codigo, penalizacion,ultimaMonitoria) values (3,'Cristiann', 20151, 'false','20/10/2017');
insert into EstudianteEntity (id, name,  codigo, penalizacion,ultimaMonitoria) values (4,'Cristiannn', 20152, 'false','21/10/2017');

