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
delete from monitoriaentity;


insert into SedeEntity (id, name, direccion) values (100, 'Sede1', 'Calle 101 9 40');
insert into SedeEntity (id, name, direccion) values (200, 'Sede2', 'Calle 5 1 12');
insert into SedeEntity (id, name, direccion) values (300, 'Sede3', 'Calle 53 68 27');


insert into SalonEntity (id, nombre, sede_id) values (100, 'W 505', 100);
insert into SalonEntity (id, nombre, sede_id) values (200, 'ML 302', 100);
insert into SalonEntity (id, nombre, sede_id) values (300, 'SD 703', 200);
insert into SalonEntity (id, nombre, sede_id) values (400, 'Q 907', 300);
insert into SalonEntity (id, nombre, sede_id) values (500, 'Z 101', 300);

insert into MonitorEntity ( nombre,  tipo, valPromedio, codigo,foto) values ('Lucia Perez',1,0, 134,'http://www.buenconsejo-madrid.com/wp-content/uploads/profesores-tutores-1.jpg');
insert into MonitorEntity ( nombre,  tipo, valPromedio, codigo,foto) values ('Pepe Castro',2,0, 14,'http://www.entrevistadetrabajo.org/wp-content/uploads/2016/07/entrevista-profesor-universitario.jpg');
insert into MonitorEntity ( nombre,  tipo, valPromedio, codigo,foto) values ('Sofia Duarte',3,0,13,'http://www.infoitaliaspagna.com/wp-content/uploads/2015/10/PROFESORES-31.png');
insert into MonitorEntity ( nombre,  tipo, valPromedio, codigo,foto) values ('Martin Ruiz',3,0, 346,'http://noticias.universia.es/net/images/educacion/7/7-/7-o/7-ofertas-de-empleo-para-profesores-de-espanol-en-el-extranjero.jpg');
insert into MonitorEntity ( nombre,  tipo, valPromedio, codigo,foto) values ('Manuel Giraldo',3,0, 1243,'http://www.sheffield.es/images/colegios-y-profesores/formacion-profesores/metodologia-prof-ingles.jpg');
insert into MonitorEntity ( nombre,  tipo, valPromedio, codigo) values ('Carlos Nope',3,0, 67);




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

insert into PagoEntity (id,  valor, monitor_codigo) values (100, 4.6, 14);
insert into PagoEntity (id,  estado, valor, monitor_codigo) values (200,1, 5.6, 13);
insert into PagoEntity (id,  valor, monitor_codigo) values (300, 3, 67);


insert into HORARIOENTITY (horaInicio, horaFin, disponibilidad) values ('2017-07-14 09:42:23', '2017-06-30 02:21:58', false);
insert into HORARIOENTITY (horaInicio, horaFin, disponibilidad) values ('2017-01-03 22:38:23', '2017-10-12 01:25:32', true);
insert into HORARIOENTITY (horaInicio, horaFin, disponibilidad) values ('2017-04-01 13:35:20', '2017-02-08 18:28:14', true);
insert into HORARIOENTITY (horaInicio, horaFin, disponibilidad) values ('2016-12-16 02:30:23', '2017-04-16 23:11:29', true);
insert into HORARIOENTITY (horaInicio, horaFin, disponibilidad) values ('2017-07-07 06:29:48', '2017-05-17 04:14:36', true);
insert into HORARIOENTITY (horaInicio, horaFin, disponibilidad) values ('2016-11-28 07:43:50', '2017-04-26 06:55:57', false);
insert into HORARIOENTITY (horaInicio, horaFin, disponibilidad) values ('2017-11-13 16:59:43', '2017-01-11 21:39:47', false);
insert into HORARIOENTITY (horaInicio, horaFin, disponibilidad) values ('2017-06-09 11:00:46', '2017-07-19 03:47:16', false);
insert into HORARIOENTITY (horaInicio, horaFin, disponibilidad) values ('2016-11-30 01:02:48', '2017-07-18 02:49:28', false);
insert into HORARIOENTITY (horaInicio, horaFin, disponibilidad) values ('2017-08-15 06:58:42', '2017-06-20 15:03:52', false);
insert into HORARIOENTITY (horaInicio, horaFin, disponibilidad) values ('2017-05-11 02:00:16', '2017-11-07 12:55:38', true);
insert into HORARIOENTITY (horaInicio, horaFin, disponibilidad) values ('2017-07-17 11:04:27', '2017-11-14 12:15:54', false);
insert into HORARIOENTITY (horaInicio, horaFin, disponibilidad) values ('2016-12-15 15:12:25', '2017-05-01 16:43:59', false);
insert into HORARIOENTITY (horaInicio, horaFin, disponibilidad) values ('2017-09-22 17:44:04', '2016-12-26 12:59:44', false);
insert into HORARIOENTITY (horaInicio, horaFin, disponibilidad) values ('2017-10-18 14:04:03', '2017-10-04 13:53:59', false);
insert into HORARIOENTITY (horaInicio, horaFin, disponibilidad) values ('2016-12-01 17:46:32', '2017-01-18 01:07:22', true);
insert into HORARIOENTITY (horaInicio, horaFin, disponibilidad) values ('2017-06-13 03:28:01', '2017-09-28 07:03:56', false);

insert into EstudianteEntity (id, name, codigo, correo, penalizacion, ultimaMonitoria) values (1, 'Giacomo Laydel', 4023467, 'glaydel0@marketwatch.com', 1, '2016-11-23 09:10:31');
insert into EstudianteEntity (id, name, codigo, correo, penalizacion, ultimaMonitoria) values (2, 'Korey Sidney', 5921319, 'ksidney1@vkontakte.ru', 1, '2017-07-04 22:40:22');
insert into EstudianteEntity (id, name, codigo, correo, penalizacion, ultimaMonitoria) values (3, 'Linette Fouldes', 4606418, 'lfouldes2@webeden.co.uk', 1, '2017-04-19 07:34:07');
insert into EstudianteEntity (id, name, codigo, correo, penalizacion, ultimaMonitoria) values (4, 'Ag Strickett', 2902476, 'astrickett3@t-online.de', 0, '2017-06-05 09:16:43');
insert into EstudianteEntity (id, name, codigo, correo, penalizacion, ultimaMonitoria) values (5, 'Ricoriki Stoney', 5338212, 'rstoney4@storify.com', 0, '2017-04-16 18:19:08');
insert into EstudianteEntity (id, name, codigo, correo, penalizacion, ultimaMonitoria) values (6, 'Sancho Beynke', 5541165, 'sbeynke5@thetimes.co.uk', 0, '2017-06-24 22:44:55');
insert into EstudianteEntity (id, name, codigo, correo, penalizacion, ultimaMonitoria) values (7, 'Allen de Juares', 6444967, 'ade6@ask.com', 0, '2016-11-30 20:49:36');
insert into EstudianteEntity (id, name, codigo, correo, penalizacion, ultimaMonitoria) values (8, 'Fergus Hammelberg', 1472685, 'fhammelberg7@hao123.com', 1, '2017-08-28 04:41:03');
insert into EstudianteEntity (id, name, codigo, correo, penalizacion, ultimaMonitoria) values (9, 'Tobi Rawls', 1640032, 'trawls8@webeden.co.uk', 1, '2017-01-21 08:19:33');
insert into EstudianteEntity (id, name, codigo, correo, penalizacion, ultimaMonitoria) values (10, 'Minnnie O''Dunneen', 8612041, 'modunneen9@cam.ac.uk', 1, '2017-01-23 15:44:56');
insert into EstudianteEntity (id, name, codigo, correo, penalizacion, ultimaMonitoria) values (11, 'Solly Meighan', 2118946, 'smeighana@oakley.com', 1, '2017-10-01 18:50:20');
insert into EstudianteEntity (id, name, codigo, correo, penalizacion, ultimaMonitoria) values (12, 'Lenci Habeshaw', 1684256, 'lhabeshawb@nymag.com', 0, '2016-11-27 10:41:26');
insert into EstudianteEntity (id, name, codigo, correo, penalizacion, ultimaMonitoria) values (13, 'Harlene McRobert', 3494822, 'hmcrobertc@ning.com', 0, '2016-11-22 06:20:42');
insert into EstudianteEntity (id, name, codigo, correo, penalizacion, ultimaMonitoria) values (14, 'Pattie Gasquoine', 5709772, 'pgasquoined@mysql.com', 1, '2017-08-30 15:00:40');
