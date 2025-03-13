package org.edu.isi.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name="tb_produit")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int  product_id;



    public Product(String code_product, double unitprice_product, String product_name) {
        this.code_product = code_product;
        this.unitprice_product = unitprice_product;
        this.product_name = product_name;
    }

    @Column(name = "code_product",length = 80,nullable = false,unique = true)
    private String code_product;

    @Column(name = "unitprice_produit")
    private double unitprice_product;

    @Column(name ="product_name",length = 80,nullable = false)
    private String product_name;

    @OneToMany(mappedBy = "product")
    List<ProductBasket> productBasketList;

    @ManyToOne
    @JoinColumn(name = "categorie_id",nullable =true,foreignKey = @ForeignKey(name = "FK_product_categorie"))
    private Categorie categorie;

}
