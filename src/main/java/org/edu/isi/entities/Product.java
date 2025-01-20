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

    @Column(name = "code_product",length = 80,nullable = false,unique = true)
    private String code_product;

    @Column(name = "unitprice_produit")
    private double unitprice_product;

    @Column(name ="product_name",length = 80,nullable = false)
    private String product_name;

    @OneToMany(mappedBy = "product")
    List<ProductBasket> productBasketList;
}
