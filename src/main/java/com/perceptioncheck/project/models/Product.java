package com.perceptioncheck.project.models;

public class Product {

    private Long id;
    private String name;
    private String description;
    private Double price;
    private String image;
    private int quantity;

    public Product(Long pId, String pName, String pDescription, Double pPrice, String pImage, int pQuantity) {
        id = pId;
        name = pName;
        description = pDescription;
        price = pPrice;
        image = pImage;
        quantity = pQuantity;
    }

    // Getters

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }

    public String getImage() {
        return image;
    }

    public int getQuantity() {
        return quantity;
    }

    // Setters

    public void setId(Long pId) {
        id = pId;
    }

    public void setName(String pName) {
        name = pName;
    }

    public void setDescription(String pDescription) {
        description = pDescription;
    }

    public void setPrice(Double pPrice) {
        price = pPrice;
    }

    public void setImage(String pImage) {
        image = pImage;
    }

    public void setQuantity(int pQuantity) {
        quantity = pQuantity;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", image='" + image + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
