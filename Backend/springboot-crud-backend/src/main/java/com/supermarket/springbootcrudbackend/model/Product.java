package com.supermarket.springbootcrudbackend.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Data
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long productId;
    @Column(name = "name")
    private String name;
    @Column(name = "stock")
    private int stock;
    @Column(name = "price")
    private int price;
    @Column(name = "createDate")
    private String createDate;
    @Column(name = "updateDate")
    private String updateDate;
    @Column(name = "createUser")
    private String createUser;
    @Column(name = "updateUser")
    private String updateUser;

    public Product() {
        super();
    }

    public Product(String name, int stock, int price, String createDate, String updateDate, String createUser, String updateUser) {
        super();
        this.name = name;
        this.stock = stock;
        this.price = price;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.createUser = createUser;
        this.updateUser = updateUser;
    }
}
