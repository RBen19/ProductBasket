package org.edu.isi.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_categorie")
public class Categorie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int categorie_id;
}
