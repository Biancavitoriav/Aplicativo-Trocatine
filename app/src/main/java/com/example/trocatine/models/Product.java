package com.example.trocatine.models;

public class Product {
    private int idProduct;
    private int idUser;
    private String name;
    private String description;
    private double value;
    private long stock;
    private String createdAt;
    private boolean flagTroca;

    public Product(int idProduct, int idUser, String name, String description, double value, long stock, String createdAt, boolean flagTroca) {
        this.idProduct = idProduct;
        this.idUser = idUser;
        this.name = name;
        this.description = description;
        this.value = value;
        this.stock = stock;
        this.createdAt = createdAt;
        this.flagTroca = flagTroca;
    }

    public Product() {
    }

    public int getIdProduct() {
        return idProduct;
    }

    public int getIdUser() {
        return idUser;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getValue() {
        return value;
    }

    public long getStock() {
        return stock;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public boolean getFlagTroca() {
        return flagTroca;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public void setStock(long stock) {
        this.stock = stock;
    }

    public void setFlagTroca(boolean flagTroca) {
        this.flagTroca = flagTroca;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Product{" +
                "idProduct=" + idProduct +
                ", idUser=" + idUser +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", value=" + value +
                ", stock=" + stock +
                ", createdAt='" + createdAt + '\'' +
                ", flagTroca=" + flagTroca +
                '}';
    }
}
