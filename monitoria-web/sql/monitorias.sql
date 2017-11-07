delete from SalonEntity;
delete from SedeEntity;
delete from PagoEntity;

delete  from  RecursoEntity;
delete  from BibliotecaEntity;

delete from MONITORENTITY_IDIOMAENTITY;
delete from MONITORENTITY;
delete from MONITORIAENTITY;
delete from VALORACIONENTITY;
delete from IDIOMAENTITY;
delete from ACTIVIDADENTITY;
delete from HorarioEntity;
delete from EstudianteEntity;


insert into SedeEntity (id, name, direccion) values (100, 'Sede1', 'Calle 101 9 40');
insert into SedeEntity (id, name, direccion) values (200, 'Sede2', 'Calle 5 1 12');
insert into SedeEntity (id, name, direccion) values (300, 'Sede3', 'Calle 53 68 27');


insert into SalonEntity (id,   disponibilidad, localizacion, sede_id) values (100, 1, 'W 505', 100);
insert into SalonEntity (id,   disponibilidad, localizacion, sede_id) values (200, 0, 'ML 302', 100);
insert into SalonEntity (id,   disponibilidad, localizacion, sede_id) values (300, 1, 'SD 703', 200);
insert into SalonEntity (id,   disponibilidad, localizacion, sede_id) values (400, 0, 'Q 907', 300);
insert into SalonEntity (id,   disponibilidad, localizacion, sede_id) values (500, 1, 'Z 101', 300);

insert into MonitorEntity ( nombre,  tipo, valPromedio, codigo,foto) values ('Lucia Perez',1,4.6, 134,'http://vignette2.wikia.nocookie.net/las-chicas-superpoderosas-reboot/images/5/57/Bombon_apariencia.png/revision/latest?cb=20160608043334&path-prefix=es');
insert into MonitorEntity ( nombre,  tipo, valPromedio, codigo,foto) values ('Pepe Castro',2,3.5, 14,'https://vignette.wikia.nocookie.net/superpoderosa/images/5/52/Burbuja.png/revision/latest?cb=20160218225605&path-prefix=es');
insert into MonitorEntity ( nombre,  tipo, valPromedio, codigo,foto) values ('Sofia Duarte',3,5.0,13,'http://www.canalfreak.net/wp-content/uploads/2016/04/bellota.png');
insert into MonitorEntity ( nombre,  tipo, valPromedio, codigo,foto) values ('Martin Ruiz',3,5.0, 346,'http://www.canalfreak.net/wp-content/uploads/2016/04/bellota.png');
insert into MonitorEntity ( nombre,  tipo, valPromedio, codigo,foto) values ('Manuel Giraldo',3,5.0, 1243,'http://www.canalfreak.net/wp-content/uploads/2016/04/bellota.png');
insert into MonitorEntity ( nombre,  tipo, valPromedio, codigo,foto) values ('Carlos Nope',3,5.0, 67,'http://www.canalfreak.net/wp-content/uploads/2016/04/bellota.png');




insert into BIBLIOTECAENTITY (id, name, ubicacion) values (100, 'Stamm-Mertz', 'Ilene');
insert into BIBLIOTECAENTITY (id, name, ubicacion) values (200, 'Littel-Lindgren', 'East');
insert into BIBLIOTECAENTITY (id, name, ubicacion) values (300, 'Toy, Hammes and Weissnat', 'Blaine');
insert into BIBLIOTECAENTITY (id, name, ubicacion) values (400, 'Considine LLC', 'Farragut');
insert into BIBLIOTECAENTITY (id, name, ubicacion) values (500, 'Cole-Jacobson', 'Sommers');

insert into IDIOMAENTITY(id, idioma) values (100, 'ingles');
insert into IDIOMAENTITY(id, idioma) values (200, 'frances');
insert into IDIOMAENTITY(id, idioma) values (300, 'italiano');
insert into IDIOMAENTITY(id, idioma) values (400, 'ruso');
insert into IDIOMAENTITY(id, idioma) values (500, 'aleman');

insert into RecursoEntity (id, disponibilidad, editorial, name, biblioteca_id) values (100, 0, 'Mybuzz', 'Miller, Ferry and Auer', 100);
insert into RecursoEntity (id, disponibilidad, editorial, name, biblioteca_id) values (200, 0, 'Oyoba', 'Heidenreich-Konopelski', 200);
insert into RecursoEntity (id, disponibilidad, editorial, name, biblioteca_id) values (300, 1, 'Shuffledrive', 'Buckridge, Schimmel and Labadie', 300);
insert into RecursoEntity (id, disponibilidad, editorial, name, biblioteca_id) values (400, 1, 'Oloo', 'Jacobson, Hauck and Hirthe', 400);
insert into RecursoEntity (id, disponibilidad, editorial, name, biblioteca_id) values (500, 0, 'Lazz', 'Aufderhar-Bartell', 500);

insert into ACTIVIDADENTITY(id, tareaAsignada, descripcion) values (100,'realizar lectura','leer el primer capitulo del libro: Hauck and Hirthe' );
insert into ACTIVIDADENTITY(id, tareaAsignada, descripcion) values (200,'realizar lectura 2','leer el segundo capitulo del libro: Aufderhar-Bartell' );
insert into ACTIVIDADENTITY(id, tareaAsignada, descripcion) values (300,'realizar lectura','leer el cuarto capitulo del libro: Ferry and Auer' );

insert into PagoEntity (id,  valor, monitor_codigo) values (1, 4.6, 14);
insert into PagoEntity (id,  estado, valor, monitor_codigo) values (2,1, 5.6, 13);
insert into PagoEntity (id,  valor, monitor_codigo) values (3, 3, 67);


insert into HorarioEntity (id, horaInicio, horaFin) values (1, '2018-10-08T08:30:20-05:00', '2018-10-08T09:30:20-05:00');
insert into HorarioEntity (id, horaInicio, horaFin) values (2, '2018-10-08T06:30:20-05:00', '2018-10-08T07:30:20-05:00');
insert into HorarioEntity (id, horaInicio, horaFin) values (3, '2018-10-08T09:30:20-05:00', '2018-10-08T10:30:20-05:00');


insert into EstudianteEntity (id, name,  codigo, penalizacion, ultimaMonitoria) values (1,'Cristian', 2015, 0,'2018-10-08T08:30:20-05:00');
insert into EstudianteEntity (id, name,  codigo, penalizacion, ultimaMonitoria) values (2,'Cristiannn', 20153, 1,'2018-10-08T08:30:20-05:00');
insert into EstudianteEntity (id, name,  codigo, penalizacion, ultimaMonitoria) values (3,'Cristiann', 20151, 0,'2018-10-08T08:30:20-05:00');
insert into EstudianteEntity (id, name,  codigo, penalizacion, ultimaMonitoria) values (4,'Cristiannn', 20152, 1,'2018-10-08T08:30:20-05:00');

