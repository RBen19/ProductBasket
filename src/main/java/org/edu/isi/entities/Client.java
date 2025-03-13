package org.edu.isi.entities;

import jakarta.persistence.*;

@Entity
@Table(name="tb_client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int client_id;

    @Column(name = "nom_cli",unique = true, nullable = false)
    private String nom;



}
