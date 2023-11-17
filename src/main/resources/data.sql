-- Update the users table to use UUID for id
INSERT INTO users (email, password, id)
VALUES
    ('user@yandex.ru', '{noop}password', RANDOM_UUID()),
    ('user1@yandex.ru', '{noop}password', RANDOM_UUID()),
    ('user2@yandex.ru', '{noop}password', RANDOM_UUID()),
    ('user3@yandex.ru', '{noop}password', RANDOM_UUID()),
    ('user4@yandex.ru', '{noop}password', RANDOM_UUID()),
    ('user5@yandex.ru', '{noop}password', RANDOM_UUID()),
    ('user6@yandex.ru', '{noop}password', RANDOM_UUID()),
    ('user7@yandex.ru', '{noop}password', RANDOM_UUID()),
    ('user8@yandex.ru', '{noop}password', RANDOM_UUID()),
    ('admin@yandex.ru', '{noop}password', RANDOM_UUID()),
    ('admin1@yandex.ru', '{noop}password', RANDOM_UUID())
;

-- Retrieve the generated UUIDs
SET @user1_id = (SELECT id FROM users WHERE email = 'user@yandex.ru');
SET @user2_id = (SELECT id FROM users WHERE email = 'user1@yandex.ru');
-- Repeat the above for other users

-- Update the user_roles table to use UUID for user_id
INSERT INTO user_roles (role, user_id)
VALUES
    ('USER', (SELECT id FROM users WHERE email = 'user@yandex.ru')),
    ('USER', (SELECT id FROM users WHERE email = 'user1@yandex.ru')),
    -- Repeat the above for other users
    ('ADMIN', (SELECT id FROM users WHERE email = 'admin@yandex.ru')),
    ('ADMIN', (SELECT id FROM users WHERE email = 'admin1@yandex.ru'))
;


-- Update the restaurant table to use UUID for id
INSERT INTO restaurant (id, name)
VALUES
    (RANDOM_UUID(), 'Stim'),         --100011
    (RANDOM_UUID(), 'Surf Coffee'),  --100012
    (RANDOM_UUID(), 'Zotman Pizza'), --100013
    (RANDOM_UUID(), 'Farro')         --100014
;

-- Update the dish table to use UUID for id and restaurant_id
INSERT INTO dish (id, name, price, restaurant_id)
VALUES
    (RANDOM_UUID(), 'Capuccino', 350, (SELECT id FROM restaurant WHERE name = 'Stim')),
    (RANDOM_UUID(), 'Latte', 350, (SELECT id FROM restaurant WHERE name = 'Stim')),
    -- ... (repeat for other dishes)
    (RANDOM_UUID(), 'Tar-tar', 800, (SELECT id FROM restaurant WHERE name = 'Farro')) --100033
;

-- Update the dish table to use UUID for id, restaurant_id, and enabled
INSERT INTO dish (id, name, price, restaurant_id, enabled)
VALUES
    (RANDOM_UUID(), 'Cookies', 500, (SELECT id FROM restaurant WHERE name = 'Stim'), FALSE),
    -- ... (repeat for other disabled dishes)
    (RANDOM_UUID(), 'Chili', 500, (SELECT id FROM restaurant WHERE name = 'Farro'), FALSE)
;

-- Update the vote table to use UUID for id, restaurant_id, and user_id
INSERT INTO vote (id, date_time, restaurant_id, user_id)
VALUES
    (RANDOM_UUID(), CURRENT_TIMESTAMP, (SELECT id FROM restaurant WHERE name = 'Surf Coffee'), @user1_id),
    -- ... (repeat for other votes)
    (RANDOM_UUID(), PARSEDATETIME('29.06.2022 10:00', 'dd.MM.yyyy HH:mm'), (SELECT id FROM restaurant WHERE name = 'Stim'), @user1_id)
;
