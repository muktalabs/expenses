drop schema  em cascade;
create schema em;

create table em.expense_type
(
expense_type_id varchar(36),
expense_type varchar(50),
description varchar(200),
primary key(expense_type_id)
);

create table em.company
(
company_id varchar(36),
company_name varchar(50),
primary key (company_id)
);

create table em.user
(
user_id varchar(36),
company_id varchar(36),
login_id varchar(25),
password varchar(25),
primary key(user_id),
foreign key(company_id) references em.company(company_id)
);

create table em.expense_inventory
(
inventory_id varchar(36),
expense_type_id varchar(36),
user_id varchar(36),
company_id varchar(36),
transaction_type varchar(35),
transaction_date  date,
cost int, 
description varchar(200),
payment_mode varchar(30),
voucher_no varchar(50),
primary key(inventory_id),
foreign key(expense_type_id) references em.expense_type(expense_type_id),
foreign key(user_id) references em.user(user_id),
foreign key(company_id) references em.company(company_id)
);

