-- Creación de la base de datos
CREATE DATABASE IF NOT EXISTS wishstore_history_db
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

-- Selección de la base de datos
USE wishstore_history_db;

-- Creación de la tabla history
CREATE TABLE IF NOT EXISTS history (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    wishlist_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    action VARCHAR(20) NOT NULL,
    description VARCHAR(255) NULL,
    created_at DATETIME NOT NULL
);

-- Datos de prueba (opcionales, solo para demostrar el funcionamiento)
INSERT INTO history (wishlist_id, product_id, action, description, created_at)
VALUES
    (1, 10, 'ADD', 'Producto agregado a favoritos', NOW()),
    (1, 11, 'ADD', 'Producto agregado a favoritos', NOW()),
    (1, 10, 'REMOVE', 'Producto eliminado de favoritos', NOW()),
    (2, 20, 'UPDATE', 'Producto actualizado en favoritos', NOW());