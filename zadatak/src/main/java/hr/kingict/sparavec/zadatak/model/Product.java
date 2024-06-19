package hr.kingict.sparavec.zadatak.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode

public class Product {

    private Integer id;
    private String title;
    private String description;
    private String category;
    private double price;
    private double discountPercentage;
    private double rating;
    private Integer stock;
    private List<String> tags;
    private String brand;
    private String sku;
    private double weight;
    private Dimensions dimensions;
    private String warrantyInformation;
    private String shippingInformation;
    private String availabilityStatus;
    private List<Review> reviews;
    private String returnPolicy;
    private Integer minimumOrderQuantity;
    private Meta meta;
    private String thumbnail;
    private List<String> images;

    public Product(String title, String description, String category, double price, double rating, Integer stock, List<String> tags,
                   String brand, double weight, Dimensions dimensions, String warrantyInformation, String shippingInformation,
                   String availabilityStatus, List<Review> reviews, String returnPolicy, Integer minimumOrderQuantity, String thumbnail, List<String> images) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.price = price;
        this.rating = rating;
        this.stock = stock;
        this.tags = tags;
        this.brand = brand;
        this.weight = weight;
        this.dimensions = dimensions;
        this.warrantyInformation = warrantyInformation;
        this.shippingInformation = shippingInformation;
        this.availabilityStatus = availabilityStatus;
        this.reviews = reviews;
        this.returnPolicy = returnPolicy;
        this.minimumOrderQuantity = minimumOrderQuantity;
        this.thumbnail = thumbnail;
        this.images = images;
    }
}
