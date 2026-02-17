package com.perceptioncheck.project.models;

public class Product {

    private Long id;
    private String name;
    private String description;
    private Double price;
    private Tax.TaxType tax;
    private String image;
    private int quantity;

    public Product() {
        super();
    }
}
