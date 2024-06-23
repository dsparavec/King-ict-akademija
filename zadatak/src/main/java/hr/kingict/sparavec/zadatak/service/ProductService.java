package hr.kingict.sparavec.zadatak.service;

import hr.kingict.sparavec.zadatak.dto.ProductDTO;
import hr.kingict.sparavec.zadatak.dto.ProductSummaryDTO;

import java.util.List;

public interface ProductService {

    List<ProductSummaryDTO> getProductDTOSummaries();
    List<ProductDTO> getProductDTODetails(Integer id);
    List<ProductDTO> getProductDTOByCategoryAndPrice(String category, double minPrice, double maxPrice);
    List<ProductDTO> getProductByTitle(String title);
}
