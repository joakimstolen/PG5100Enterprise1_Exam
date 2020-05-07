

create sequence hibernate_sequence start with 1 increment by 1;
create table copy (id bigint not null, duplicates integer not null, item_copy_owner_userid varchar(255), item_information_id bigint not null, primary key (id));
create table item (id bigint not null, description varchar(255), name varchar(255), price bigint check (price>=0 AND price<=3000), type varchar(255), primary key (id));
create table users (userid varchar(255) not null, available_boxes integer not null, currency bigint not null, email varchar(255), enabled boolean not null, first_name varchar(128), hashed_password varchar(255) not null, last_name varchar(128), primary key (userid));
create table users_roles (users_userid varchar(255) not null, roles varchar(255));
alter table users add constraint UK_6dotkott2kjsp8vw4d0m25fb7 unique (email);
alter table copy add constraint FK7257n5rmn3jdm4rex3oj40wcg foreign key (item_copy_owner_userid) references users;
alter table copy add constraint FKsadw2gxrjmsdpwwauacde7n1d foreign key (item_information_id) references item;
alter table users_roles add constraint FKnqgxij5udu4xrsqju9dtbc8pr foreign key (users_userid) references users;