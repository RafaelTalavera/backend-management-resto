/* Populate tables menues*/

INSERT INTO menues (imagen, title, detail, price) VALUES ('https://www.freepik.es/foto-gratis/pizza-pizza-rellena-tomates-salami-aceitunas_6087618.htm#query=PIZZA&position=0&from_view=search&track=sph&uuid=6d775c39-9e90-4f89-9aa7-671037cd7e61', 'pizza Napolitana','pizza rellena con tomates, salami y aceitunas', '25.0');
INSERT INTO menues (imagen, title, detail, price) VALUES ('https://www.freepik.es/foto-gratis/rebanada-pizza-crujiente-carne-queso_7359437.htm#query=PIZZA&position=2&from_view=search&track=sph&uuid=6d775c39-9e90-4f89-9aa7-671037cd7e61', 'pizza Carne','Pizza con pequeños pedazos de carne crujiente', '30.0')

INSERT INTO customers (name, pago) VALUES ('pepe', 'true');

INSERT INTO boards ( chair, name, condition) VALUES (1, 'mesa 2', true);

INSERT INTO reservations ( people, date_time, customer_id, board_id ) VALUES ( 1, '2023-11-21 15:30:00', 1, 1);




