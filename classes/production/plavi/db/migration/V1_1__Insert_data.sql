insert into roles(name) values("USER");
insert into roles(name) values("ADMIN");

insert into users(firstname, lastname, username, password, gender, role_id) values("Pero", "Perić", "user", "pass", "male", 1);
insert into users(firstname, lastname, username, password, gender, role_id) values("Ivo", "Ivić", "admin", "pass", "male", 2);

insert into projects(name) values("Projekt");

insert into tasks(name, status, created, estimated, project_id, user_id) values("Task", "In progress", curdate(), 5, 1, 1);

insert into project_stakeholders(project_id, user_id) values(1, 1);