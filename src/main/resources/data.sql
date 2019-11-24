INSERT INTO subway_monitor.company (id, name, creation_date, update_date)
VALUES (1, 'Metrô', current_timestamp(), current_timestamp());

INSERT INTO subway_monitor.line (number, name, id_company, creation_date, update_date)
VALUES (1, 'Azul', 1, current_timestamp(), current_timestamp());

INSERT INTO subway_monitor.line (number, name, id_company, creation_date, update_date)
VALUES (2, 'Verde', 1, current_timestamp(), current_timestamp());

INSERT INTO subway_monitor.line (number, name, id_company, creation_date, update_date)
VALUES (3, 'Vermelha', 1, current_timestamp(), current_timestamp());

INSERT INTO subway_monitor.line (number, name, id_company, creation_date, update_date)
VALUES (15, 'Prata', 1, current_timestamp(), current_timestamp());

INSERT INTO subway_monitor.status (slug, name, creation_date, update_date)
VALUES ('NORMAL', 'Normal', current_timestamp(), current_timestamp());

INSERT INTO subway_monitor.status (slug, name, creation_date, update_date)
VALUES ('SLOW', 'Velocidade Reduzida', current_timestamp(), current_timestamp());

INSERT INTO subway_monitor.status (slug, name, creation_date, update_date)
VALUES ('INTERRUPTED', 'Operação interrompida', current_timestamp(), current_timestamp());

INSERT INTO subway_monitor.status (slug, name, creation_date, update_date)
VALUES ('PARTIAL', 'Operação Parcial', current_timestamp(), current_timestamp());
