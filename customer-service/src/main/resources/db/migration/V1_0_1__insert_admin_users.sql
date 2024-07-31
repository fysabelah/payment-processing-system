INSERT INTO address (id, street, city, state, country, zip_code)
VALUES (1, 'Rua X', 'Cidade Y', 'W', 'BR', '00000-000');

INSERT INTO client (id, name, email, document, cellphone, address_id)
VALUES
    (1, 'Sudo Su Dev', 'dev@gmai.com', '57386600054', '551140028922', 1),
    (2, 'Sudo Su QA', 'qa@gmai.com', '47792626903', '551140028922', 1);

SELECT setval('client_id_seq', 2);
SELECT setval('address_id_seq', 1);