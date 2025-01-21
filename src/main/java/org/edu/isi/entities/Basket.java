package org.edu.isi.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name="tb_Basket")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Basket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_basket;

    @Column(name = "code_basket",nullable = false,unique = true,length = 80)
    private String code_basket;

    @OneToMany(mappedBy = "basket")
    List<ProductBasket> basketList;

    public Basket(String code_basket) {
        this.code_basket = code_basket;
    }
}
