# Keepr
## Features:
- Support for multiple users
- Support for CRUD operations
     - Entries could have:
	     - Title
	     - Description
	     - Time frame (start date, end date)
	     - Status
	     - Category
	     - Priority
- Three default categories.
	- Each category must have a title and a colour
	- The user can add and customize categories
- Statistics
- ?Generalize atributes?

#### Database schema:
***Old***:

Items:
- id(pk)
- title(VARCHAR2)
- Description(text)
- time_frame(date)
- id_status(int)
- id_category(int)
- id_priorty(int)

Status:
- id(pk)
- value(string)

Categories:
- id(pk)
- title(string)
- color(string)

Priority:
- id(pk)
- value(string)

#### ***Generalized***
***Updated:***

Attributes:
- id(pk)
- user_id(int)
- name(string)

Attribute_values:
- id(pk)
- attribute_id(int)
- value(string)

Item_attribute_values:
- id(pk)
- item_id(int)
- attribute_value_id(int)

Items:
- id(pk)
- user_id(int)
- title(VARCHAR2)
- Description(text)
- due_date(date)

## To do list
- [ ] Auth
	- [ ] Register
	- [ ] Login
	- [ ] Logout
- [ ] Data management
	- [ ] Create a user(non authenticated)
	- [ ] Create tables for all entities
	- [ ] Seed the database (cu faker)
