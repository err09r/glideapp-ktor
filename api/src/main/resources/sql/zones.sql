DO
$$
    BEGIN
        IF NOT exists (SELECT FROM zones) THEN

--             SL

            INSERT INTO zones (id, code, title, type, created_at, updated_at)
            VALUES (gen_random_uuid(), 1, 'SL-R-1', 0, now()::TIMESTAMP, now()::TIMESTAMP);

            INSERT INTO zones (id, code, title, type, created_at, updated_at)
            VALUES (gen_random_uuid(), 100, 'SL-NP-1', 1, now()::TIMESTAMP, now()::TIMESTAMP);

            INSERT INTO zones (id, code, title, type, created_at, updated_at)
            VALUES (gen_random_uuid(), 101, 'SL-NP-2', 1, now()::TIMESTAMP, now()::TIMESTAMP);

            INSERT INTO zones (id, code, title, type, created_at, updated_at)
            VALUES (gen_random_uuid(), 102, 'SL-NP-3', 1, now()::TIMESTAMP, now()::TIMESTAMP);

            INSERT INTO zones (id, code, title, type, created_at, updated_at)
            VALUES (gen_random_uuid(), 103, 'SL-NP-4', 1, now()::TIMESTAMP, now()::TIMESTAMP);

            INSERT INTO zones (id, code, title, type, created_at, updated_at)
            VALUES (gen_random_uuid(), 104, 'SL-NP-5', 1, now()::TIMESTAMP, now()::TIMESTAMP);

--             KO

            INSERT INTO zones (id, code, title, type, created_at, updated_at)
            VALUES (gen_random_uuid(), 2, 'KO-R-1', 0, now()::TIMESTAMP, now()::TIMESTAMP);

            INSERT INTO zones (id, code, title, type, created_at, updated_at)
            VALUES (gen_random_uuid(), 200, 'KO-NP-1', 1, now()::TIMESTAMP, now()::TIMESTAMP);

            INSERT INTO zones (id, code, title, type, created_at, updated_at)
            VALUES (gen_random_uuid(), 201, 'KO-NP-2', 1, now()::TIMESTAMP, now()::TIMESTAMP);

            INSERT INTO zones (id, code, title, type, created_at, updated_at)
            VALUES (gen_random_uuid(), 202, 'KO-NP-3', 1, now()::TIMESTAMP, now()::TIMESTAMP);

        END IF;
    END
$$
