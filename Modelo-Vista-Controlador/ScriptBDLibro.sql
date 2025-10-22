CREATE DATABASE IF NOT EXISTS biblioteca CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE biblioteca;

DROP TABLE IF EXISTS libros;
CREATE TABLE libros (
  id INT AUTO_INCREMENT PRIMARY KEY,
  titulo VARCHAR(200) NOT NULL,
  autor  VARCHAR(150) NOT NULL,
  isbn   VARCHAR(20)  NOT NULL,
  precio INT UNSIGNED NOT NULL           -- entero positivo (0 no permitido por la regla de negocio)
);

INSERT INTO libros (titulo, autor, isbn, precio) VALUES
('El Quijote', 'Miguel de Cervantes', '9788491050299', 20),
('Cien años de soledad', 'Gabriel García Márquez', '9780307474728', 18);