CREATE TABLE IF NOT EXISTS Users ( \
    id INT AUTO_INCREMENT PRIMARY KEY, \
    username VARCHAR(255) NOT NULL, \
    password VARCHAR(255)\
    );\
CREATE TABLE IF NOT EXISTS Items ( \
    id INT AUTO_INCREMENT PRIMARY KEY, \
    user_id INT,\
    title VARCHAR(255), \
    description VARCHAR(255),\
    due_date DATETIME \
    );\
CREATE TABLE IF NOT EXISTS Item_attribute_values ( \
    id INT AUTO_INCREMENT PRIMARY KEY, \
    item_id INT,\
    attribute_value_id INT\
    );\
CREATE TABLE IF NOT EXISTS Attribute_values ( \
    id INT AUTO_INCREMENT PRIMARY KEY, \
    attribute_id INT,\
    value VARCHAR(255) \
    );\
CREATE TABLE IF NOT EXISTS Attributes ( \
    id INT AUTO_INCREMENT PRIMARY KEY, \
    user_id INT,\
    name VARCHAR(255) \
    );\
 INSERT INTO Attribute_values(attribute_id, value) \
        VALUES('1', 'To do');\
 INSERT INTO Attribute_values(attribute_id, value) \
        VALUES('1', 'Doing');\
 INSERT INTO Attribute_values(attribute_id, value) \
        VALUES('1', 'Done');\
INSERT INTO Attributes(user_id, name) \
       VALUES('1', 'Status');\
INSERT INTO Users(username,password) \
       VALUES('default', '');\
