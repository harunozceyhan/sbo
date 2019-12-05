create table postgres.operation (id int8 not null, created_at timestamp not null, updated_at timestamp not null, adi varchar(40) not null, kodu varchar(10) not null, primary key (id));
create sequence seq_operation start 1 increment 1;
create table postgres.siparis (id int8 not null, created_at timestamp not null, updated_at timestamp not null, adi varchar(40) not null, kodu varchar(10) not null, operation_id int8, primary key (id));
alter table postgres.siparis drop constraint UK_5eudwjavoou0lg5r0khaqmwl8;
alter table postgres.siparis add constraint UK_5eudwjavoou0lg5r0khaqmwl8 unique (operation_id);
create sequence seq_siparis start 1 increment 1;
alter table postgres.siparis add constraint FKflofy56038nha9pn7cilghmg0 foreign key (operation_id) references postgres.operation;