-- Initial Users
-- Passwords are encrypted using BCrypt ($2a$10$8.UnVuG9HHgffUDAlk8q7OuV6Xv7.L1rO8P6B.zM4t0Uv.c86oF2a is '123')
INSERT IGNORE INTO users (username, password, role, email) VALUES 
('admin', '$2a$10$8.UnVuG9HHgffUDAlk8q7OuV6Xv7.L1rO8P6B.zM4t0Uv.c86oF2a', 'ADMIN', 'admin@artgallery.com'),
('artist', '$2a$10$8.UnVuG9HHgffUDAlk8q7OuV6Xv7.L1rO8P6B.zM4t0Uv.c86oF2a', 'ARTIST', 'artist@artgallery.com'),
('curator', '$2a$10$8.UnVuG9HHgffUDAlk8q7OuV6Xv7.L1rO8P6B.zM4t0Uv.c86oF2a', 'CURATOR', 'curator@artgallery.com'),
('test', '$2a$10$8.UnVuG9HHgffUDAlk8q7OuV6Xv7.L1rO8P6B.zM4t0Uv.c86oF2a', 'VISITOR', 'test@artgallery.com');
