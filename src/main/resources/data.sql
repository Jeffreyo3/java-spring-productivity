DELETE
FROM items;

DELETE
FROM users;

DELETE
FROM categories;

DELETE
FROM roles;

DELETE
FROM tasks;

DELETE
FROM recipes;

INSERT INTO categories(categoryid, category, created_by, created_date, last_modified_by,
                       last_modified_date)
    VALUES (1, 'Shopping', 'SYSTEM', CURRENT_TIMESTAMP, 'SYSTEM', CURRENT_TIMESTAMP),
           (2, 'Exercise', 'SYSTEM', CURRENT_TIMESTAMP, 'SYSTEM', CURRENT_TIMESTAMP),
           (3, 'Cleaning', 'SYSTEM', CURRENT_TIMESTAMP, 'SYSTEM', CURRENT_TIMESTAMP),
           (4, 'Development', 'SYSTEM', CURRENT_TIMESTAMP, 'SYSTEM', CURRENT_TIMESTAMP),
           (5, 'Misc', 'SYSTEM', CURRENT_TIMESTAMP, 'SYSTEM', CURRENT_TIMESTAMP);

INSERT INTO roles(roleid, role, created_by, created_date, last_modified_by,
                  last_modified_date)
    VALUES (1, 'ADMIN', 'SYSTEM', CURRENT_TIMESTAMP, 'SYSTEM', CURRENT_TIMESTAMP),
           (2, 'USER', 'SYSTEM', CURRENT_TIMESTAMP, 'SYSTEM', CURRENT_TIMESTAMP);

INSERT INTO items(itemid, item, price, url, created_by, created_date, last_modified_by,
                  last_modified_date)
    VALUES (1, 'Canned Tuna', '2.40', NULL, 'SYSTEM', CURRENT_TIMESTAMP, 'SYSTEM',
            CURRENT_TIMESTAMP),
           (2, 'Olive Oil', '8.99', NULL, 'SYSTEM', CURRENT_TIMESTAMP, 'SYSTEM', CURRENT_TIMESTAMP),
           (3, 'Chicken Breast', '3.48', NULL, 'SYSTEM', CURRENT_TIMESTAMP, 'SYSTEM',
            CURRENT_TIMESTAMP),
           (4, 'Mayonnaise', '2.98', 'https://www.target.com/p/hellmann-s-mayonnaise-real-30oz/-/A-13028016?ref=tgt_adv_XS000000&AFID=google_pla_df&fndsrc=tmnv&DFA=71700000078203708&CPNG=PLA_DVM%2B0060H00000uInLvQAK-Unilever_Condiments_Search_Dec_Flight&adgroup=PLA_Unilever&LID=700000001393753pgs&network=g&device=c&location=9031059&gclid=Cj0KCQiA3smABhCjARIsAKtrg6IEtUeJO0K4NSnA0wcZwJRxL1_OD1UCrEmP0CF3GzCTInQz2j6ae9MaAsMuEALw_wcB&gclsrc=aw.ds', 'SYSTEM', CURRENT_TIMESTAMP, 'SYSTEM',
            CURRENT_TIMESTAMP);

INSERT INTO users(userid, username, password, email, fname, lname, created_by, created_date,
                  last_modified_by, last_modified_date)
    VALUES (1, 'testuser', 'testpass', 'test@email.com', 'Test', 'McTesting', 'SYSTEM',
            CURRENT_TIMESTAMP, 'SYSTEM', CURRENT_TIMESTAMP),
           (2, 'testadmin', 'testadminpass', 'admin@testemail.com', 'Admin', 'Senior', 'SYSTEM',
            CURRENT_TIMESTAMP, 'SYSTEM', CURRENT_TIMESTAMP);

INSERT INTO userroles(roleid, userid, created_by, created_date, last_modified_by,
                      last_modified_date)
    VALUES (2, 1, 'SYSTEM', CURRENT_TIMESTAMP, 'SYSTEM', CURRENT_TIMESTAMP),
           (2, 2, 'SYSTEM', CURRENT_TIMESTAMP, 'SYSTEM', CURRENT_TIMESTAMP),
           (1, 2, 'SYSTEM', CURRENT_TIMESTAMP, 'SYSTEM', CURRENT_TIMESTAMP);

INSERT INTO tasks(taskid, task, completed, userid, categoryid, created_by, created_date,
                  last_modified_by, last_modified_date)
    VALUES (1, 'Take out trash', FALSE, 1, 3, 'SYSTEM', CURRENT_TIMESTAMP, 'SYSTEM', CURRENT_TIMESTAMP),
           (2, 'Go grocery shopping', FALSE, 1, 1, 'SYSTEM', CURRENT_TIMESTAMP, 'SYSTEM',
            CURRENT_TIMESTAMP),
           (3, 'Clean bathroom', FALSE, 1, 3, 'SYSTEM', CURRENT_TIMESTAMP, 'SYSTEM', CURRENT_TIMESTAMP),
           (4, 'Create Java Spring Back End', TRUE, 2, 4, 'SYSTEM', CURRENT_TIMESTAMP, 'SYSTEM',
            CURRENT_TIMESTAMP);

INSERT INTO useritems(userid, itemid, quantity, checked, notes, created_by, created_date,
                  last_modified_by, last_modified_date)
    VALUES (1, 1, 20, FALSE, NULL, 'SYSTEM', CURRENT_TIMESTAMP, 'SYSTEM', CURRENT_TIMESTAMP),
           (2, 1, 8, FALSE, NULL, 'SYSTEM', CURRENT_TIMESTAMP, 'SYSTEM', CURRENT_TIMESTAMP),
           (1, 3, 4, TRUE, 'Make sure its organic', 'SYSTEM', CURRENT_TIMESTAMP, 'SYSTEM',
            CURRENT_TIMESTAMP);

INSERT INTO recipes(recipeid, authorid, recipe, instructions, created_by, created_date,
                      last_modified_by, last_modified_date)
    VALUES (1, 1, 'Tuna Salad', 'Mix lots of mayo into tuna. Add onion.', 'SYSTEM',
            CURRENT_TIMESTAMP, 'SYSTEM', CURRENT_TIMESTAMP),
           (2, 1, 'Chicken Bake', 'Marinate chicken breast in olive oil. Bake for 30min.',
            'SYSTEM', CURRENT_TIMESTAMP, 'SYSTEM', CURRENT_TIMESTAMP);

INSERT INTO recipeitems(recipeid, itemid, quantity, measurement, created_by, created_date,
                        last_modified_by, last_modified_date)
    VALUES (1, 4, 2, 'tablespoons', 'SYSTEM',
            CURRENT_TIMESTAMP, 'SYSTEM', CURRENT_TIMESTAMP),
           (1, 1, 1, '5oz can', 'SYSTEM',
            CURRENT_TIMESTAMP, 'SYSTEM', CURRENT_TIMESTAMP),
           (2, 2, 2, 'ounces', 'SYSTEM',
            CURRENT_TIMESTAMP, 'SYSTEM', CURRENT_TIMESTAMP),
           (2, 3, 1, 'pound', 'SYSTEM',
            CURRENT_TIMESTAMP, 'SYSTEM', CURRENT_TIMESTAMP);

INSERT INTO usersubscribedrecipes(userid, recipeid, created_by, created_date, last_modified_by,
                                  last_modified_date)
    VALUES (1, 1, 'SYSTEM', CURRENT_TIMESTAMP, 'SYSTEM', CURRENT_TIMESTAMP),
           (1, 2, 'SYSTEM', CURRENT_TIMESTAMP, 'SYSTEM', CURRENT_TIMESTAMP),
           (2, 1, 'SYSTEM', CURRENT_TIMESTAMP, 'SYSTEM', CURRENT_TIMESTAMP);