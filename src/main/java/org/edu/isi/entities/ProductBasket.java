package org.edu.isi.entities;

import jakarta.persistence.*;
import jdk.jfr.Enabled;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="tb_productbasket")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductBasket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_product_basket;

    public ProductBasket(String code_product_basket, Product product, Basket basket, double quantity) {
        this.code_product_basket = code_product_basket;
        this.product = product;
        this.basket = basket;
        this.quantity = quantity;
    }

    @Column(name = "code_product_basket", nullable = false,unique = true)
    private String code_product_basket;

    @ManyToOne()
    @JoinColumn(name = "product_id")
    private Product product;


    @ManyToOne()
    @JoinColumn(name = "id_basket")
    private Basket basket;

    @Column(name = "quantity",nullable = false)
    private double quantity;





}
