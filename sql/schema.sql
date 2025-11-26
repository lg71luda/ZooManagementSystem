CREATE DATABASE IF NOT EXISTS zoo_db CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE zoo_db;

CREATE TABLE Staff (
                       staff_id INT AUTO_INCREMENT PRIMARY KEY,
                       login VARCHAR(50) UNIQUE NOT NULL,
                       password_hash VARCHAR(255) NOT NULL,
                       first_name VARCHAR(50),
                       last_name VARCHAR(50),
                       role VARCHAR(20) NOT NULL
);

CREATE TABLE Animals (
                         animal_id INT AUTO_INCREMENT PRIMARY KEY,
                         name VARCHAR(100),
                         species VARCHAR(100),
                         age INT,
                         health_status VARCHAR(50)
);

CREATE TABLE Enclosures (
                            enclosure_id INT AUTO_INCREMENT PRIMARY KEY,
                            name VARCHAR(100),
                            type VARCHAR(50),
                            capacity INT
);

CREATE TABLE Animal_Enclosure (
                                  animal_id INT,
                                  enclosure_id INT,
                                  assignment_date DATE,
                                  FOREIGN KEY (animal_id) REFERENCES Animals(animal_id),
                                  FOREIGN KEY (enclosure_id) REFERENCES Enclosures(enclosure_id)
);

CREATE TABLE Feedings (
                          feeding_id INT AUTO_INCREMENT PRIMARY KEY,
                          animal_id INT,
                          staff_id INT,
                          feeding_time DATETIME,
                          food_type VARCHAR(100),
                          amount DECIMAL(6,2),
                          FOREIGN KEY (animal_id) REFERENCES Animals(animal_id),
                          FOREIGN KEY (staff_id) REFERENCES Staff(staff_id)
);

CREATE TABLE Tickets (
                         ticket_id INT AUTO_INCREMENT PRIMARY KEY,
                         sale_date DATE,
                         price DECIMAL(10,2),
                         visitor_type VARCHAR(20)
);

-- Админ по умолчанию
INSERT INTO Staff (login, password_hash, first_name, last_name, role)
VALUES ('admin', '123456', 'Алексей', 'Админ', 'ADMIN');
