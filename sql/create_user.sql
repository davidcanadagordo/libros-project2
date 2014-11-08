drop user 'david'@'localhost';
create user 'david'@'localhost' identified by 'david';
grant all privileges on beeterdb.* to 'beeter'@'localhost';
flush privileges;