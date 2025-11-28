package com.example.entregamovil.models;

import java.io.Serializable;

public class Product implements Serializable {
    private int id;
    private String name;
    private String imageUrl; // Cambié a String para guardar nombre de imagen
    private int imageResId;
    private float rating;
    private int ratingCount;
    private String categoria;
    private String localidad;
    private String ventas;
    private String direccion;
    private String descripcion;
    private String tagCategoria;

    public Product() {
    }

    public Product(int id, String name, String imageUrl, float rating, int ratingCount,
                   String categoria, String localidad, String ventas,
                   String direccion, String descripcion, String tagCategoria) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.rating = rating;
        this.ratingCount = ratingCount;
        this.categoria = categoria;
        this.localidad = localidad;
        this.ventas = ventas;
        this.direccion = direccion;
        this.descripcion = descripcion;
        this.tagCategoria = tagCategoria;
    }

    public Product(String name, String imageUrl, float rating, int ratingCount,
                   String categoria, String localidad, String ventas,
                   String direccion, String descripcion, String tagCategoria) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.rating = rating;
        this.ratingCount = ratingCount;
        this.categoria = categoria;
        this.localidad = localidad;
        this.ventas = ventas;
        this.direccion = direccion;
        this.descripcion = descripcion;
        this.tagCategoria = tagCategoria;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getVentas() {
        return ventas;
    }

    public void setVentas(String ventas) {
        this.ventas = ventas;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTagCategoria() {
        return tagCategoria;
    }

    public void setTagCategoria(String tagCategoria) {
        this.tagCategoria = tagCategoria;
    }
}