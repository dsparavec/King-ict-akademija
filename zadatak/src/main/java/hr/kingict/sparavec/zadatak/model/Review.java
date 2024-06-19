package hr.kingict.sparavec.zadatak.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class Review {

    private int rating;
    private String comment;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private Date date;
    private String reviewerName;
    private String reviewerEmail;

}
