DO
$$
    BEGIN
        IF NOT exists (SELECT FROM zone_coordinates) THEN

--             SL-R-1

            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 1, 54.4566513, 17.032469, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 1, 54.4597945, 17.0408804, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 1, 54.4576991, 17.0391638, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 1, 54.456801, 17.0403654, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 1, 54.4554039, 17.0403654, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 1, 54.4501145, 17.0408804, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 1, 54.4489168, 17.044142, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 1, 54.4478189, 17.0463736, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 1, 54.4465213, 17.0437987, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 1, 54.4443254, 17.0458586, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 1, 54.4440259, 17.0534117, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 1, 54.445623, 17.055815, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 1, 54.4481183, 17.0690329, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 1, 54.4501145, 17.0685179, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 1, 54.4505137, 17.0714361, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 1, 54.4544059, 17.0758993, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 1, 54.4597945, 17.0625098, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 1, 54.4617901, 17.0645697, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 1, 54.4636859, 17.0618231, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 1, 54.4633865, 17.0583899, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 1, 54.465781, 17.0578749, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 1, 54.4665792, 17.0613081, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 1, 54.4681754, 17.0597632, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 1, 54.4745597, 17.0540983, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 1, 54.4733627, 17.0437987, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 1, 54.4744599, 17.0413954, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 1, 54.4724649, 17.0398504, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 1, 54.4744599, 17.0395071, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 1, 54.4768538, 17.0412237, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 1, 54.4783498, 17.0371039, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 1, 54.479048, 17.0300658, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 1, 54.4775519, 17.0259459, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 1, 54.4780506, 17.021311, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 1, 54.4782501, 17.0194227, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 1, 54.4792475, 17.0197661, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 1, 54.4808432, 17.0178778, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 1, 54.4814416, 17.0146162, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 1, 54.4799456, 17.0123846, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 1, 54.4844832, 17.0003683, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 1, 54.4789981, 16.9983084, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 1, 54.4799955, 16.9917853, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 1, 54.4757067, 16.989897, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 1, 54.4765047, 16.9842322, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 1, 54.4749088, 16.9840605, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 1, 54.4753077, 16.9814856, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 1, 54.471617, 16.9761641, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 1, 54.4696219, 16.977709, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 1, 54.468325, 16.9835455, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 1, 54.4582479, 16.9807989, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 1, 54.4532583, 16.9795973, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 1, 54.4447745, 17.0001967, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 1, 54.4457727, 17.0079214, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 1, 54.4495656, 17.0170195, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 1, 54.4484677, 17.0201094, now()::TIMESTAMP, now()::TIMESTAMP);

--             SL-NP-1

            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 100, 54.4575993, 17.0332629, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 100, 54.4594827, 17.0370824, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 100, 54.4590337, 17.0388849, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 100, 54.4596199, 17.0403011, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 100, 54.460942, 17.0403654, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 100, 54.4627131, 17.0414598, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 100, 54.4637981, 17.0409019, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 100, 54.4641847, 17.0391423, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 100, 54.4646212, 17.0366962, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 100, 54.4643593, 17.0358164, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 100, 54.4639478, 17.0352156, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 100, 54.4622267, 17.0359237, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 100, 54.4615781, 17.0363528, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 100, 54.4613411, 17.0360524, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 100, 54.4611665, 17.0350654, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 100, 54.461179, 17.0346577, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 100, 54.4605678, 17.0348508, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 100, 54.4602186, 17.0344646, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 100, 54.4599692, 17.0340569, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 100, 54.4599442, 17.0335204, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 100, 54.4577365, 17.0331986, now()::TIMESTAMP, now()::TIMESTAMP);

--             SL-NP-2

            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 101, 54.4675689, 17.0266904, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 101, 54.4669579, 17.0264543, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 101, 54.4662595, 17.0265616, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 101, 54.4659852, 17.0266904, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 101, 54.4660101, 17.027377, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 101, 54.4660725, 17.0279134, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 101, 54.4668332, 17.0279564, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 101, 54.4670951, 17.0277632, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 101, 54.4685167, 17.0294584, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 101, 54.4688409, 17.030188, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 101, 54.4691028, 17.0297159, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 101, 54.4695891, 17.0299305, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 101, 54.4695891, 17.0289863, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 101, 54.4692774, 17.0289005, now()::TIMESTAMP, now()::TIMESTAMP);

--             SL-NP-3

            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 102, 54.4639025, 17.0159401, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 102, 54.4640771, 17.0170988, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 102, 54.4651247, 17.017013, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 102, 54.4650748, 17.0175279, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 102, 54.4661224, 17.0177854, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 102, 54.4662221, 17.0169271, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 102, 54.4680927, 17.0172275, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 102, 54.4680179, 17.0180429, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 102, 54.4703373, 17.0186867, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 102, 54.4718585, 17.0191158, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 102, 54.4721827, 17.0187725, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 102, 54.4725319, 17.0167555, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 102, 54.4721079, 17.0162405, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 102, 54.4727064, 17.0152963, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 102, 54.4729309, 17.0136656, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 102, 54.4726067, 17.0133651, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 102, 54.4690404, 17.0125498, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 102, 54.4686913, 17.0128502, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 102, 54.468367, 17.0125068, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 102, 54.4669454, 17.0120777, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 102, 54.4646258, 17.011434, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 102, 54.4632789, 17.0121206, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 102, 54.4631542, 17.0126356, now()::TIMESTAMP, now()::TIMESTAMP);

--             SL-NP-4

            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 103, 54.4497324, 17.0677389, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 103, 54.4507055, 17.068168, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 103, 54.4515288, 17.0659794, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 103, 54.4525269, 17.0641769, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 103, 54.4525518, 17.0605291, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 103, 54.4496825, 17.0604862, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 103, 54.4498072, 17.0648206, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 103, 54.4494828, 17.0649923, now()::TIMESTAMP, now()::TIMESTAMP);

--             SL-NP-5

            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 104, 54.4541236, 16.9935383, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 104, 54.4533003, 16.9919933, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 104, 54.4530009, 16.9947828, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 104, 54.4524021, 16.9946111, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 104, 54.4504559, 16.9939674, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 104, 54.449907, 17.0045675, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 104, 54.4473868, 17.0030655, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 104, 54.4462639, 17.0055116, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 104, 54.4474118, 17.0073999, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 104, 54.4480606, 17.0057262, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 104, 54.4485347, 17.0060266, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 104, 54.4507554, 17.0100607, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 104, 54.4532005, 17.0117773, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 104, 54.4543482, 17.0128931, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 104, 54.4545727, 17.0126785, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 104, 54.4551465, 17.008387, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 104, 54.4551964, 17.0070566, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 104, 54.45345, 17.0059408, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 104, 54.4542484, 17.0023359, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 104, 54.4542484, 17.0003189, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 104, 54.454423, 16.9993318, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 104, 54.4547723, 16.9983448, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 104, 54.4551715, 16.9943966, now()::TIMESTAMP, now()::TIMESTAMP);


--             KO-R-1

            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 2, 54.1776948, 16.2272963, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 2, 54.1827179, 16.2341628, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 2, 54.1869369, 16.2338195, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 2, 54.1891466, 16.2262664, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 2, 54.1905528, 16.2207732, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 2, 54.1933649, 16.2259231, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 2, 54.1960765, 16.216825, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 2, 54.1993903, 16.2120185, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 2, 54.199792, 16.2099585, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 2, 54.1971811, 16.205152, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 2, 54.1984866, 16.2024054, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 2, 54.1975075, 16.1976848, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 2, 54.1998673, 16.1940799, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 2, 54.2016998, 16.1970839, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 2, 54.2043104, 16.1957107, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 2, 54.2066197, 16.2030921, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 2, 54.2078244, 16.2089286, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 2, 54.2109366, 16.2111602, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 2, 54.2137473, 16.2108169, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 2, 54.2156545, 16.2070403, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 2, 54.2146508, 16.2000022, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 2, 54.2116393, 16.1984572, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 2, 54.2092299, 16.1933074, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 2, 54.2119404, 16.1910758, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 2, 54.2133458, 16.1898742, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 2, 54.2120408, 16.1866126, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 2, 54.210535, 16.1823211, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 2, 54.2091295, 16.1775145, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 2, 54.2058165, 16.1751113, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 2, 54.2023023, 16.1728797, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 2, 54.2026035, 16.1694465, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 2, 54.2051136, 16.1673865, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 2, 54.2094307, 16.1675582, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 2, 54.2100331, 16.164125, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 2, 54.212342, 16.1600051, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 2, 54.2097319, 16.153482, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 2, 54.2083264, 16.152452, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 2, 54.2070212, 16.1538253, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 2, 54.2064189, 16.1522803, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 2, 54.2048124, 16.1522803, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 2, 54.2036076, 16.1507354, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 2, 54.2007961, 16.1495337, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 2, 54.1955743, 16.1471305, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 2, 54.1879413, 16.1438689, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 2, 54.1859324, 16.1424956, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 2, 54.1850283, 16.1478171, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 2, 54.1824165, 16.1510787, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 2, 54.1822156, 16.1538253, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 2, 54.1839233, 16.1551986, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 2, 54.1871377, 16.1658416, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 2, 54.1881422, 16.1691031, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 2, 54.1866355, 16.1694465, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 2, 54.1830193, 16.1679015, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 2, 54.1810101, 16.1687598, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 2, 54.1771924, 16.173223, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 2, 54.174982, 16.1766562, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 2, 54.1746806, 16.184381, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 2, 54.1750825, 16.1897025, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 2, 54.1779962, 16.1953673, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 2, 54.1804073, 16.2005172, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 2, 54.182517, 16.2029204, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 2, 54.1807087, 16.2092719, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 2, 54.1817133, 16.2130485, now()::TIMESTAMP, now()::TIMESTAMP);

--             KO-NP-1

            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 200, 54.1969135, 16.1887609, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 200, 54.1963361, 16.1875378, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 200, 54.1958591, 16.1869155, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 200, 54.1948422, 16.1854778, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 200, 54.1944531, 16.1864005, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 200, 54.1937752, 16.1884605, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 200, 54.1936873, 16.1914645, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 200, 54.1935367, 16.193267, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 200, 54.1941141, 16.1928378, now()::TIMESTAMP, now()::TIMESTAMP);

--             KO-NP-2

            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 201, 54.1902359, 16.1860538, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 201, 54.1902108, 16.1875344, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 201, 54.1906126, 16.1877919, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 201, 54.1914537, 16.1880923, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 201, 54.192433, 16.1858392, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 201, 54.193613, 16.1837578, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 201, 54.1931988, 16.1826206, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 201, 54.1926087, 16.18217, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 201, 54.1918806, 16.1844659, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 201, 54.1914035, 16.1846161, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 201, 54.1910645, 16.1851097, now()::TIMESTAMP, now()::TIMESTAMP);

--             KO-NP-3

            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 202, 54.1948373, 16.1814864, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 202, 54.1973354, 16.1788686, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 202, 54.1971471, 16.1781176, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 202, 54.1964065, 16.1777099, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 202, 54.1962935, 16.1765726, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 202, 54.1955278, 16.1770662, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 202, 54.1951763, 16.1758645, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 202, 54.1945235, 16.1762508, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 202, 54.1935443, 16.1775812, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 202, 54.1938205, 16.1788901, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 202, 54.1941469, 16.179448, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 202, 54.193695, 16.1810788, now()::TIMESTAMP, now()::TIMESTAMP);
            INSERT INTO zone_coordinates (id, zone_code, latitude, longitude, created_at, updated_at)
            VALUES (gen_random_uuid(), 202, 54.1940214, 16.1816796, now()::TIMESTAMP, now()::TIMESTAMP);

        END IF;
    END;
$$
