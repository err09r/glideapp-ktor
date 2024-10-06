DELETE FROM zones;

-- SL

INSERT OR REPLACE INTO zones (id, code, title, type, created_at, updated_at)
VALUES (X'A6743AB0A69BC81AF26E867E08F68387', 1, 'SL-R-1', 0, datetime('now'), datetime('now')),
       (X'A8B7B037E47EDE83A6C181EAB4FC25D2', 100, 'SL-NP-1', 1, datetime('now'), datetime('now')),
       (X'2C2869981442F4D7A92348BD37D0C843', 101, 'SL-NP-2', 1, datetime('now'), datetime('now')),
       (X'822C9622D0BBA2D32533EE7FFF71E43C', 102, 'SL-NP-3', 1, datetime('now'), datetime('now')),
       (X'269BF8978B054BE25C3C24E377694183', 103, 'SL-NP-4', 1, datetime('now'), datetime('now')),
       (X'96BF20439C44AE136C4EF42971272F86', 104, 'SL-NP-5', 1, datetime('now'), datetime('now'));

-- KO

INSERT OR REPLACE INTO zones (id, code, title, type, created_at, updated_at)
VALUES (X'4A7E3A1F09D9AC89CE1275E91066339F', 2, 'KO-R-1', 0, datetime('now'), datetime('now')),
       (X'C6544194B816C26763020CABB563506A', 200, 'KO-NP-1', 1, datetime('now'), datetime('now')),
       (X'9DEC5E00B6938C25C8AF7DE9F7059CE8', 201, 'KO-NP-2', 1, datetime('now'), datetime('now')),
       (X'3E7E01CD4618F418D2B68C3F55B673FC', 202, 'KO-NP-3', 1, datetime('now'), datetime('now'));
