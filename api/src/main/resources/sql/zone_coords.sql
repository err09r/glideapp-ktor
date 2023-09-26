DO
$$
    BEGIN
        IF NOT exists (SELECT FROM zone_coordinates) THEN

--             SL-R-1

            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 1, 54.45261393078834, 17.017300376477383, now()::TIMESTAMP, now()::TIMESTAMP);

            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 1, 54.45628227433493, 16.994285634097274, now()::TIMESTAMP, now()::TIMESTAMP);

            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 1, 54.463105314396195, 16.987554907552163, now()::TIMESTAMP, now()::TIMESTAMP);

            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 1, 54.46698175872949, 16.98905028662445, now()::TIMESTAMP, now()::TIMESTAMP);

            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 1, 54.487049447097625, 17.002100867619568, now()::TIMESTAMP, now()::TIMESTAMP);

            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 1, 54.488949566499976, 17.019637585831994, now()::TIMESTAMP, now()::TIMESTAMP);

            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 1, 54.473479529794574, 17.03092090065047, now()::TIMESTAMP, now()::TIMESTAMP);

            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 1, 54.474274399615005, 17.038125908908288, now()::TIMESTAMP, now()::TIMESTAMP);

            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 1, 54.47404235846866, 17.052264038319848, now()::TIMESTAMP, now()::TIMESTAMP);

            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 1, 54.46883342891788, 17.056886119088894, now()::TIMESTAMP, now()::TIMESTAMP);

            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 1, 54.45880375012081, 17.064634901555088, now()::TIMESTAMP, now()::TIMESTAMP);

            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 1, 54.450199279678934, 17.068985095220143, now()::TIMESTAMP, now()::TIMESTAMP);

            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 1, 54.44554064944707, 17.04777790110274, now()::TIMESTAMP, now()::TIMESTAMP);

            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 1, 54.45060434766859, 17.041796384813182, now()::TIMESTAMP, now()::TIMESTAMP);

            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 1, 54.459144518236315, 17.04193233251388, now()::TIMESTAMP, now()::TIMESTAMP);

            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 1, 54.45859630138437, 17.035407042016303, now()::TIMESTAMP, now()::TIMESTAMP);

            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 1, 54.45291120069188, 17.032688170975675, now()::TIMESTAMP, now()::TIMESTAMP);

            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 1, 54.448648095668695, 17.024667501405688, now()::TIMESTAMP, now()::TIMESTAMP);

            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 1, 54.45261393078834, 17.017300376477383, now()::TIMESTAMP, now()::TIMESTAMP);

--             SL-R-2

            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 2, 54.17396235264483, 16.189101004355223, now()::TIMESTAMP, now()::TIMESTAMP);

            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 2, 54.173967325852175, 16.174555044287537, now()::TIMESTAMP, now()::TIMESTAMP);

            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 2, 54.180178395059, 16.165446826301224, now()::TIMESTAMP, now()::TIMESTAMP);

            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 2, 54.18089939550689, 16.15294001951405, now()::TIMESTAMP, now()::TIMESTAMP);

            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 2, 54.18551847251743, 16.140976991083654, now()::TIMESTAMP, now()::TIMESTAMP);

            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 2, 54.20405422082191, 16.149541439010385, now()::TIMESTAMP, now()::TIMESTAMP);

            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 2, 54.21566651294967, 16.179720907562004, now()::TIMESTAMP, now()::TIMESTAMP);

            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 2, 54.21352516666405, 16.18461487543516, now()::TIMESTAMP, now()::TIMESTAMP);

            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 2, 54.22004822587525, 16.20391885982402, now()::TIMESTAMP, now()::TIMESTAMP);

            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 2, 54.20869057252908, 16.213570856167117, now()::TIMESTAMP, now()::TIMESTAMP);

            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 2, 54.2072644359574, 16.210580098022337, now()::TIMESTAMP, now()::TIMESTAMP);

            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 2, 54.200908336745236, 16.21193953354265, now()::TIMESTAMP, now()::TIMESTAMP);

            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 2, 54.19638044852026, 16.21968831600857, now()::TIMESTAMP, now()::TIMESTAMP);

            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 2, 54.18676138893763, 16.24443004247901, now()::TIMESTAMP, now()::TIMESTAMP);

            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 2, 54.18509584639773, 16.236681260013015, now()::TIMESTAMP, now()::TIMESTAMP);

            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 2, 54.18136180423657, 16.232467009899935, now()::TIMESTAMP, now()::TIMESTAMP);

            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 2, 54.183837944426514, 16.21764916272818, now()::TIMESTAMP, now()::TIMESTAMP);

            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 2, 54.17493209248349, 16.201743767140336, now()::TIMESTAMP, now()::TIMESTAMP);

            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 2, 54.17396235264483, 16.189101004355223, now()::TIMESTAMP, now()::TIMESTAMP);

--             SL-NP-1

            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 3, 54.462937, 17.032355, now()::TIMESTAMP, now()::TIMESTAMP);

            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 3, 54.463959, 17.028533, now()::TIMESTAMP, now()::TIMESTAMP);

            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 3, 54.467013, 17.027467, now()::TIMESTAMP, now()::TIMESTAMP);

            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 3, 54.469021, 17.029545, now()::TIMESTAMP, now()::TIMESTAMP);

            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 3, 54.468737, 17.032006, now()::TIMESTAMP, now()::TIMESTAMP);

            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 3, 54.4645, 17.035922, now()::TIMESTAMP, now()::TIMESTAMP);

            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 3, 54.462581, 17.033211, now()::TIMESTAMP, now()::TIMESTAMP);

--             SL-NP-2

            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 4, 54.448609, 17.025346, now()::TIMESTAMP, now()::TIMESTAMP);

            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 4, 54.45325, 17.032561, now()::TIMESTAMP, now()::TIMESTAMP);

            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 4, 54.454148, 17.041922, now()::TIMESTAMP, now()::TIMESTAMP);

            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 4, 54.44806, 17.040119, now()::TIMESTAMP, now()::TIMESTAMP);

--             SL-NP-3

            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 5, 54.466102, 16.995612, now()::TIMESTAMP, now()::TIMESTAMP);

            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 5, 54.467399, 16.984189, now()::TIMESTAMP, now()::TIMESTAMP);

            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 5, 54.480087, 16.989273, now()::TIMESTAMP, now()::TIMESTAMP);

            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 5, 54.47899, 16.998893, now()::TIMESTAMP, now()::TIMESTAMP);

            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 5, 54.474352, 16.997261, now()::TIMESTAMP, now()::TIMESTAMP);

            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 5, 54.474003, 16.999666, now()::TIMESTAMP, now()::TIMESTAMP);

            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 5, 54.470362, 16.998206, now()::TIMESTAMP, now()::TIMESTAMP);

            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 5, 54.470611, 16.995801, now()::TIMESTAMP, now()::TIMESTAMP);

        END IF;
    END;
$$
