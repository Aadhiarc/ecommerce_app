package com.example.ecommerce_app;

import org.json.JSONArray;

import java.util.ArrayList;

public class ProductModel {

        public int id;
        public String title;
        public String description;
        public String price;
        public double discountPercentage;
        public double rating;
        public int stock;
        public String brand;
        public String category;
        public String thumbnail;
        public JSONArray images;

        public ProductModel() {
        }

        public ProductModel(int id, String title, String description, String price, double discountPercentage, double rating, int stock, String brand, String category, String thumbnail, JSONArray images) {
            this.id = id;
            this.title = title;
            this.description = description;
            this.price = price;
            this.discountPercentage = discountPercentage;
            this.rating = rating;
            this.stock = stock;
            this.brand = brand;
            this.category = category;
            this.thumbnail = thumbnail;
            this.images = images;
        }


        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public double getDiscountPercentage() {
            return discountPercentage;
        }

        public void setDiscountPercentage(double discountPercentage) {
            this.discountPercentage = discountPercentage;
        }

        public double getRating() {
            return rating;
        }

        public void setRating(double rating) {
            this.rating = rating;
        }

        public int getStock() {
            return stock;
        }

        public void setStock(int stock) {
            this.stock = stock;
        }

        public String getBrand() {
            return brand;
        }

        public void setBrand(String brand) {
            this.brand = brand;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }

        public JSONArray getImages() {
            return images;
        }

        public void setImages(JSONArray images) {
            this.images = images;
        }

}
 class Root{
    public ArrayList<ProductModel> getProducts() {
        return products;
    }

    public ArrayList<ProductModel> products;
    public int total;
    public int skip;
    public int limit;
}
