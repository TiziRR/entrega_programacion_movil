package com.example.entregamovil.models;

public class Product {
    private String name;
    private int imageResId;
    private float rating;
    private int ratingCount;

    public Product(String name, int imageResId, float rating, int ratingCount) {
        this.name = name;
        this.imageResId = imageResId;
        this.rating = rating;
        this.ratingCount = ratingCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageResId() {
        return imageResId;
    }

    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getRatingCount() {
        return ratingCount;
    }

    public void setRatingCount(int ratingCount) {
        this.ratingCount = ratingCount;
    }
}
