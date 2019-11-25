INSERT INTO subway_monitor.company (id, name, creation_date, update_date)
VALUES (1, 'Metrô', current_timestamp(), current_timestamp());

INSERT INTO subway_monitor.company (id, name, creation_date, update_date)
VALUES (2, 'Via Quatro', current_timestamp(), current_timestamp());

INSERT INTO subway_monitor.company (id, name, creation_date, update_date)
VALUES (3, 'Via Mobilidade', current_timestamp(), current_timestamp());

INSERT INTO subway_monitor.company (id, name, creation_date, update_date)
VALUES (4, 'CPTM', current_timestamp(), current_timestamp());

INSERT INTO subway_monitor.line (number, name, id_company, creation_date, update_date)
VALUES (1, 'Azul', 1, current_timestamp(), current_timestamp());

INSERT INTO subway_monitor.line (number, name, id_company, creation_date, update_date)
VALUES (2, 'Verde', 1, current_timestamp(), current_timestamp());

INSERT INTO subway_monitor.line (number, name, id_company, creation_date, update_date)
VALUES (3, 'Vermelha', 1, current_timestamp(), current_timestamp());

INSERT INTO subway_monitor.line (number, name, id_company, creation_date, update_date)
VALUES (15, 'Prata', 1, current_timestamp(), current_timestamp());

INSERT INTO subway_monitor.line (number, name, id_company, creation_date, update_date)
VALUES (4, 'Amarela', 2, current_timestamp(), current_timestamp());

INSERT INTO subway_monitor.line (number, name, id_company, creation_date, update_date)
VALUES (5, 'Lilás', 3, current_timestamp(), current_timestamp());

INSERT INTO subway_monitor.line (number, name, id_company, creation_date, update_date)
VALUES (9, 'Esmeralda', 4, current_timestamp(), current_timestamp());

INSERT INTO subway_monitor.line (number, name, id_company, creation_date, update_date)
VALUES (11, 'Coral', 4, current_timestamp(), current_timestamp());

INSERT INTO subway_monitor.status (slug, name, creation_date, update_date)
VALUES ('NORMAL', 'Normal', current_timestamp(), current_timestamp());

INSERT INTO subway_monitor.status (slug, name, creation_date, update_date)
VALUES ('SLOW', 'Velocidade Reduzida', current_timestamp(), current_timestamp());

INSERT INTO subway_monitor.status (slug, name, creation_date, update_date)
VALUES ('INTERRUPTED', 'Operação interrompida', current_timestamp(), current_timestamp());

INSERT INTO subway_monitor.status (slug, name, creation_date, update_date)
VALUES ('PARTIAL', 'Operação Parcial', current_timestamp(), current_timestamp());

INSERT INTO subway_monitor.status (slug, name, creation_date, update_date)
VALUES ('FINISHED', 'Operação Encerrada', current_timestamp(), current_timestamp());
