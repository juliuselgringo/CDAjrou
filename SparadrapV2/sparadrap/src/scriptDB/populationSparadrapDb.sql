INSERT INTO contact (address, postal_code, town, phone, email) 
VALUES ("10 rue de la Sante", "75000", "Paris", "01 23 45 67 89", "contact@harmonie.fr"),
("25 Av de la Mutualite", "69000", "Lyon", "04 56 78 90 12", "contact@mgen.fr"),
("12 Rue de Paris", "75000", "Paris", "01 23 45 67 89", "jean.dupont@medecin.fr"),
("45 Av des Champs", "69000", "Lyon", "04 56 78 90 12", "marie.martin@medecin.fr"),
("12 Rue de Pffft", "71000", "Pfffft", "06 23 45 67 89", "jeannot.ducont@medecin.fr"),
("45 Av des Choux", "68000", "Colmar", "02 56 78 90 12", "mario.martais@medecin.fr"),
("12 Rue de Paris", "75000", "Paris", "01 23 45 67 89", "alice.lefevre@mail.fr"),
("34 Av des Camps", "69000", "Lyon", "04 56 78 90 12", "marc.petit@mail.fr"),
("12 Rue de Paris", "54000", "Nancy", "03 23 45 67 89", "jacques.bourdin@mail.fr"),
("34 Av des Champs", "60000", "Chaipas", "04 56 78 90 12", "marc.petit@mail.fr");

INSERT INTO mutual (mutual_name, rate, contact_id) 
VALUES ("Harmonie Mutuelle", 0.75, 1),
("Mgen", 0.80, 2);