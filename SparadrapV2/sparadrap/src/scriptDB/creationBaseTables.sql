
DROP DATABASE IF EXISTS sparadrap;

CREATE DATABASE IF NOT EXISTS sparadrap;

USE sparadrap;

CREATE TABLE contact(
   contact_id INT AUTO_INCREMENT,
   address VARCHAR(50) NOT NULL ,
   postal_code VARCHAR(5) NOT NULL,
   town VARCHAR(30) NOT NULL,
   phone VARCHAR(20) NOT NULL,
   email VARCHAR(30) NOT NULL,
   PRIMARY KEY(contact_id)
);

CREATE TABLE mutual(
   mutual_id INT AUTO_INCREMENT,
   mutual_name VARCHAR(30) NOT NULL,
   rate DOUBLE NOT NULL,
   contact_id INT,
   PRIMARY KEY(mutual_id),
   FOREIGN KEY(contact_id) REFERENCES Contact(contact_id)
);

CREATE TABLE drug_category(
   category_id INT AUTO_INCREMENT,
   category_name VARCHAR(50) NOT NULL,
   PRIMARY KEY(category_id)
);

INSERT INTO drug_category (category_name) VALUES ("Analgesiques et Anti-inflammatoires"),
            ("Antibiotiques et Antibacteriens"),
            ("Antituberculeux et Antilepreux"),
            ("Antimycosiques"),
            ("Antiviraux"),
            ("Cardiologie"),
            ("Dermatologie"),
            ("Dietetique et Nutrition"),
            ("Endocrinologie"),
            ("Gastro-enterologie et hepatologie"),
            ("Gynecologie obstetrique et contraception"),
            ("Hematologie"),
            ("Immunologie et Allergologie"),
            ("Medicaments des troubles metaboliques"),
            ("Neurologie"),
            ("Ophtalmologie"),
            ("Oto-rhino-laryngologie"),
            ("Parasitologie"),
            ("Pneumologie"),
            ("Psychiatrie"),
            ("Reanimation et toxicologie"),
            ("Rhumatologie"),
            ("Stomatologie");
            
CREATE TABLE Doctor(
   doctor_id INT auto_increment,
   doctor_firstName VARCHAR(30) NOT NULL,
   doctor_lastName VARCHAR(30) NOT NULL,
   agreement_id VARCHAR(11) NOT NULL,
   contact_id INT,
   PRIMARY KEY(doctor_id),
   FOREIGN KEY(contact_id) REFERENCES Contact(contact_id)
);

CREATE TABLE Customer(
   customer_id INT auto_increment,
   customer_firstName VARCHAR(30) NOT NULL,
   customer_lastName VARCHAR(30) NOT NULL,
   social_security_id VARCHAR(15) NOT NULL,
   customer_birthDate DATE NOT NULL,
   mutual_id INT,
   doctor_id INT,
   contact_id INT,
   PRIMARY KEY(customer_id),
   FOREIGN KEY(mutual_id) REFERENCES Mutual(mutual_id),
   FOREIGN KEY(doctor_id) REFERENCES Doctor(doctor_id),
   FOREIGN KEY(contact_id) REFERENCES Contact(contact_id)
);

CREATE TABLE Prescription(
   prescription_id INT auto_increment,
   prescription_date DATE NOT NULL,
   customer_id INT,
   doctor_id INT,
   PRIMARY KEY(prescription_id),
   FOREIGN KEY(customer_id) REFERENCES Customer(customer_id),
   FOREIGN KEY(doctor_id) REFERENCES Doctor(doctor_id)
);

CREATE TABLE Drug(
   drug_id INT auto_increment,
   drug_name VARCHAR(30) NOT NULL,
   price double NOT NULL,
   production_date DATE NOT NULL,
   quantity INT NOT NULL,
   under_prescription BOOLEAN NOT NULL,
   category_id INT NOT NULL,
   PRIMARY KEY(drug_id),
   FOREIGN KEY(category_id) REFERENCES Drug_category(category_id)
);

CREATE TABLE purchase(
   purchase_id INT auto_increment,
   purchase_date DATE NOT NULL,
   with_prescription BOOLEAN NOT NULL,
   total_price DOUBLE,
   customer_id INT,
   prescription_id INT,
   PRIMARY KEY(purchase_id),
   FOREIGN KEY(customer_id) REFERENCES Customer(customer_id),
   FOREIGN KEY(prescription_id) REFERENCES Prescription(prescription_id)
);

CREATE TABLE lister(
   prescription_id INT,
   drug_id INT,
   prescription_quantity INT NOT NULL,
   PRIMARY KEY(prescription_id, drug_id),
   FOREIGN KEY(prescription_id) REFERENCES Prescription(prescription_id),
   FOREIGN KEY(drug_id) REFERENCES Drug(drug_id)
);

CREATE TABLE contenir(
   drug_id INT,
   purchase_id INT,
   buying_quantity INT NOT NULL,
   PRIMARY KEY(drug_id, purchase_id),
   FOREIGN KEY(drug_id) REFERENCES Drug(drug_id),
   FOREIGN KEY(purchase_id) REFERENCES purchase(purchase_id)
);


