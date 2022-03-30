CREATE TABLE IF NOT EXISTS users ( \
    id INT AUTO_INCREMENT PRIMARY KEY, \
    username VARCHAR(255) NOT NULL, \
    password VARCHAR(255)\
    );\
CREATE TABLE IF NOT EXISTS items ( \
    id INT AUTO_INCREMENT PRIMARY KEY, \
    user_id INT,\
    title VARCHAR(255), \
    description VARCHAR(255),\
    due_date DATETIME \
    );\
CREATE TABLE IF NOT EXISTS item_attribute_values ( \
    id INT AUTO_INCREMENT PRIMARY KEY, \
    item_id INT,\
    attribute_value_id INT\
    );\
CREATE TABLE IF NOT EXISTS attribute_values ( \
    id INT AUTO_INCREMENT PRIMARY KEY, \
    attribute_id INT,\
    value VARCHAR(255) \
    );\
CREATE TABLE IF NOT EXISTS attributes ( \
    id INT AUTO_INCREMENT PRIMARY KEY, \
    user_id INT,\
    name VARCHAR(255) \
    );\
 INSERT INTO attribute_values(attribute_id, value) \
        VALUES('1', 'To do');\
 INSERT INTO attribute_values(attribute_id, value) \
        VALUES('1', 'Doing');\
 INSERT INTO attribute_values(attribute_id, value) \
        VALUES('1', 'Done');\
INSERT INTO attributes(user_id, name) \
       VALUES('1', 'Status');\
INSERT INTO users(username,password) \
       VALUES('default', '');\
