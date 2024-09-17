INSERT INTO roles (name)
VALUES ('ROLE_USER'),
       ('ROLE_ADMIN');

-- Insert trainers
INSERT INTO trainers (first_name, last_name, phone_number, email, cost_per_session, photo)
VALUES ('John', 'Doe', '1234567890', 'john.doe@email.com', 50.00,
        'https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse2.mm.bing.net%2Fth%3Fid%3DOIP.1ROLHSxMmiqEvtKUWiq6gAHaE8%26pid%3DApi&f=1&ipt=2288db3c4503efa0a40e264e8fba94cc8fca556857bf075b4322b2c373a4621f&ipo=images'),
       ('Jane', 'Smith', '0987654321', 'jane.smith@email.com', 60.00,
        'https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse3.mm.bing.net%2Fth%3Fid%3DOIP.XO0KjUrwE6TsZiViBVpV3QHaD-%26pid%3DApi&f=1&ipt=fedd6a992d7615449baeb062c6104a1025139fb50c44602af08bbb788fb138a0&ipo=images'),
       ('Mike', 'Johnson', '1122334455', 'mike.johnson@email.com', 70.00,
        'https://external-content.duckduckgo.com/iu/?u=http%3A%2F%2Fv.img.com.ua%2Fb%2Forig%2F9%2Fb0%2F70e26eccbae8c309d690565a89925b09.jpg&f=1&nofb=1&ipt=f85dbe988c21df2f2ece49a76c2d19146f8ebc6e503ddbe74b1a1878c54bcb4d&ipo=images');

-- Insert training types
INSERT INTO training_types (name)
VALUES ('Weight Lifting'),
       ('Cardio'),
       ('Yoga');

-- Insert users
INSERT INTO users (first_name, last_name, username, email, password, trainer_id)
VALUES ('Alice', 'Wonderland', 'alice', 'alice@email.com',
        '$2a$12$PE5b3ISKmFqiA9ovy15/KeLoRZ5Iv5NvIWiRnFU1MlkF25VKvXrUi', 1),
       ('Bob', 'Builder', 'bob', 'bob@email.com', '$2a$12$PE5b3ISKmFqiA9ovy15/KeLoRZ5Iv5NvIWiRnFU1MlkF25VKvXrUi', 2),
       ('Charlie', 'Chaplin', 'charlie', 'charlie@email.com',
        '$2a$12$PE5b3ISKmFqiA9ovy15/KeLoRZ5Iv5NvIWiRnFU1MlkF25VKvXrUi', 3);

-- Insert schedules
INSERT INTO schedules (trainer_id, user_id, training_type_id, start_time, end_time)
VALUES (1, 1, 1, '2022-01-01 09:00:00', '2022-01-01 10:00:00'),
       (2, 2, 2, '2022-01-01 10:00:00', '2022-01-01 11:00:00'),
       (3, 3, 3, '2022-01-01 11:00:00', '2022-01-01 12:00:00');

-- Insert user roles
INSERT INTO user_roles (user_id, role_id)
VALUES (1, 1), -- Alice is a user
       (2, 1), -- Bob is a user
       (3, 2); -- Charlie is an admin
