package org.edu.isi.entities;

import jakarta.persistence.*;

@Entity
@Table(name="tb_commandes")
public class Commandes {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)

    private int commande_id;

    @Column(name = "numCommand",unique = true, nullable = false)
    private String numCommande;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

}
