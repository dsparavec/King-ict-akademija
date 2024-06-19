package hr.kingict.sparavec.zadatak.dto;

import lombok.Data;

@Data
public class ReviewDTO {

    private Integer rating;
    private String comment;
    private String date;
    private String reviewerName;
    private String reviewerEmail;
}
