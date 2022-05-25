insert into role(name) values ('programmer');
insert into role(name) values ('lead');
insert into users(name, role_id) VALUES ('Ivan', 1);
insert into users(name, role_id) VALUES ('Vasiliy', 1);
insert into users(name, role_id) VALUES ('Pavel', 2);
insert into rules(name) values ('editor');
insert into rules(name) values ('creator');
insert into rules(name) values ('viewer');
insert into role_rules(role_id, rules_id) values (1, 1);
insert into role_rules(role_id, rules_id) values (1, 3);
insert into role_rules(role_id, rules_id) values (2, 1);
insert into role_rules(role_id, rules_id) values (2, 2);
insert into role_rules(role_id, rules_id) values (2, 3);
insert into category(name) values ('Unexpected error');
insert into category(name) values ('Consultation');
insert into state(name) values ('registered');
insert into state(name) values ('processed');
insert into state(name) values ('executed');
insert into item(name, users_id, category_id, state_id) VALUES ('SOS', 1, 1, 1);
insert into item(name, users_id, category_id, state_id) VALUES ('Help', 3, 2, 2);
insert into comments(name, item_id) VALUES ('repeated problem', 1);
insert into comments(name, item_id) VALUES ('not urgent', 2);
insert into attachs(name, item_id) VALUES ('file1', 1);
insert into attachs(name, item_id) VALUES ('file2', 2);