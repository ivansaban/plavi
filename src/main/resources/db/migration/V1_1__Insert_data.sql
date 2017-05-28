insert into `roles`(`name`) values("USER");
insert into `roles`(`name`) values("ADMIN");
insert into `users`(`username`, `password`, `role_id`) values("user", "pass", 1);
insert into `users`(`username`, `password`, `role_id`) values("admin", "pass", 2);