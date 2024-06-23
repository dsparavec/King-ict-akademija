package hr.kingict.sparavec.zadatak.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductSummaryDTO {

    private List<String> image;
    private String title;
    private Double price;
    private String shortDescription;
}
