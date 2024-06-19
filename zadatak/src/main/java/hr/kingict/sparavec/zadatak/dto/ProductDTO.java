package hr.kingict.sparavec.zadatak.dto;

import hr.kingict.sparavec.zadatak.model.Dimensions;
import hr.kingict.sparavec.zadatak.model.Review;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private String title;
    private String description;
    private String category;
    private double price;
    private double rating;
    private int stock;
    private List<String> tags;
    private String brand;
    private double weight;
    private Dimensions dimensions;
    private String warrantyInformation;
    private String shippingInformation;
    private String availabilityStatus;
    private List<Review> reviews;
    private String returnPolicy;
    private int minimumOrderQuantity;
    private String thumbnail;
    private List<String> images;

}
