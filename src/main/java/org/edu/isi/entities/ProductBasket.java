package org.edu.isi.entities;

import jakarta.persistence.*;
import jdk.jfr.Enabled;

@Entity
@Table(name="tb_productbasket")
public class ProductBasket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_productbasket;

    @Id
    @ManyToOne(targetEntity = Product.class)
    private Product product;

    @Id
    @ManyToOne
    private Basket basket;

}
