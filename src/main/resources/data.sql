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
            CURRENT_TIMESTAMP);

INSERT INTO users(userid, username, password, email, fname, lname, created_by, created_date,
                  last_modified_by,
                  last_modified_date)
    VALUES (1, 'testuser', 'testpass', 'test@email.com', 'Test', 'McTesting', 'SYSTEM',
            CURRENT_TIMESTAMP, 'SYSTEM', CURRENT_TIMESTAMP),
           (2, 'testadmin', 'testadminpass', 'admin@testemail.com', 'Admin', 'Senior', 'SYSTEM',
            CURRENT_TIMESTAMP, 'SYSTEM', CURRENT_TIMESTAMP);

INSERT INTO userroles(roleid, userid, created_by, created_date, last_modified_by,
                      last_modified_date)
    VALUES (2, 1, 'SYSTEM', CURRENT_TIMESTAMP, 'SYSTEM', CURRENT_TIMESTAMP),
           (1, 2, 'SYSTEM', CURRENT_TIMESTAMP, 'SYSTEM', CURRENT_TIMESTAMP);

INSERT INTO tasks(taskid, task, completed, userid, categoryid, created_by, created_date,
                  last_modified_by,
                      last_modified_date)
VALUES (1, 'Take out trash', false, 1, 3, 'SYSTEM', CURRENT_TIMESTAMP, 'SYSTEM', CURRENT_TIMESTAMP),
       (2, 'Go grocery shopping', false, 1, 1, 'SYSTEM', CURRENT_TIMESTAMP, 'SYSTEM',
        CURRENT_TIMESTAMP),
       (3, 'Clean bathroom', false, 1, 3, 'SYSTEM', CURRENT_TIMESTAMP, 'SYSTEM', CURRENT_TIMESTAMP),
       (4, 'Create Java Spring Back End', true, 2, 4, 'SYSTEM', CURRENT_TIMESTAMP, 'SYSTEM',
        CURRENT_TIMESTAMP);