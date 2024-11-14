CREATE TABLE users (
    id_user INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(30) UNIQUE NOT NULL,
    password VARCHAR(30) NOT NULL,
    first_name VARCHAR(30) NOT NULL,
    last_name VARCHAR(30) NOT NULL,
    phone INT NOT NULL,
    email VARCHAR(30) UNIQUE NOT NULL
);
CREATE TABLE suppliers (
    id_supplier INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(30) NOT NULL,
    address VARCHAR(30) NOT NULL,
    phone INT NOT NULL,
    last_name VARCHAR(30) NOT NULL
);
CREATE TABLE customers (
    id_customer INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(30) NOT NULL,
    last_name VARCHAR(30) NOT NULL,
    phone INT NOT NULL,
    email VARCHAR(30) NOT NULL
);
CREATE TABLE categories (
    id_category INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(30) NOT NULL,
    description VARCHAR(30) NOT NULL
);
CREATE TABLE orders (
    id_order INT AUTO_INCREMENT PRIMARY KEY,
    order_date DATETIME,
    id_customer INT,
    type INT,
    FOREIGN KEY (id_customer) REFERENCES customers(id_customer)
);
CREATE TABLE products (
    id_product INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(30) NOT NULL,
    description VARCHAR(30) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    quantity INT NOT NULL,
    id_category INT,
    id_supplier INT,
    FOREIGN KEY (id_category) REFERENCES categories(id_category),
    FOREIGN KEY (id_supplier) REFERENCES suppliers(id_supplier)
);
CREATE TABLE order_details (
    id_order_detail INT AUTO_INCREMENT PRIMARY KEY,
    unit_price DECIMAL(10, 2) NOT NULL,
    quantity INT NOT NULL,
    total DECIMAL(10, 2) NOT NULL,
    id_order INT,
    id_product INT,
    id_user INT,
    FOREIGN KEY (id_order) REFERENCES orders(id_order),
    FOREIGN KEY (id_product) REFERENCES products(id_product),
    FOREIGN KEY (id_user) REFERENCES users(id_user)
);
CREATE TABLE roles (
    id_rol INT AUTO_INCREMENT PRIMARY KEY,
    rol VARCHAR(30) NOT NULL,
    id_user INT,
    FOREIGN KEY (id_user) REFERENCES users(id_user)
);
INSERT INTO users(username, password, first_name, last_name, phone, email) values("ADMIN", "3b40b2f", "Yanina", "Manrique", 912367226, "yanina322@gmail.com");
INSERT INTO roles(rol, id_user) values("ROLE_USER", 1);
INSERT INTO roles(rol, id_user) values("ROLE_ADMIN", 1);

INSERT INTO categories(name, description) values("Electronica", "Dispositivos electronicos");
INSERT INTO categories(name, description) values("Ropa", "Ropa y accesorios");
INSERT INTO categories(name, description) values("Alimentos", "Productos alimenticios");
INSERT INTO categories(name, description) values("Herramientas", "Herramientas y equipos");
INSERT INTO categories(name, description) values("Medicamentos", "Productos farmaceuticos");
INSERT INTO categories(name, description) values("Materiales", "Materiales de Construcción");
