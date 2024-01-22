DO
$$
    BEGIN
        IF NOT exists (SELECT FROM vehicles) THEN

--             SL-R-1

            FOR n IN 1..100
                LOOP
                    INSERT INTO vehicles (id, code, zone_code, battery_charge, type, status, latitude, longitude,
                                          created_at, updated_at)
                    VALUES (gen_random_uuid(), n, 1, random() * 60 + 40, 0, 0, 0, 0, now()::TIMESTAMP,
                            now()::TIMESTAMP);
                END LOOP;

--             KO-R-1

            FOR n IN 101..200
                LOOP
                    INSERT INTO vehicles (id, code, zone_code, battery_charge, type, status, latitude, longitude,
                                          created_at, updated_at)
                    VALUES (gen_random_uuid(), n, 2, random() * 60 + 40, 0, 0, 0, 0, now()::TIMESTAMP,
                            now()::TIMESTAMP);
                END LOOP;

        END IF;
    END;
$$
