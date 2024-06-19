package hr.kingict.sparavec.zadatak.facade;

import hr.kingict.sparavec.zadatak.dto.ProductDTO;
import hr.kingict.sparavec.zadatak.dto.ProductSummaryDTO;
import hr.kingict.sparavec.zadatak.model.Product;
import org.springframework.stereotype.Service;

@Service
public class Mapper {

    public Product convertProductDTOToProduct(ProductDTO productDTO){
        return new Product(productDTO.getTitle(),
                productDTO.getDescription(),
                productDTO.getCategory(),
                productDTO.getPrice(),
                productDTO.getRating(),
                productDTO.getStock(),
                productDTO.getTags(),
                productDTO.getBrand(),
                productDTO.getWeight(),
                productDTO.getDimensions(),
                productDTO.getWarrantyInformation(),
                productDTO.getShippingInformation(),
                productDTO.getAvailabilityStatus(),
                productDTO.getReviews(),
                productDTO.getReturnPolicy(),
                productDTO.getMinimumOrderQuantity(),
                productDTO.getThumbnail(),
                productDTO.getImages());
    }

    public ProductDTO convertProductToProductDTO(Product product){
        return new ProductDTO(product.getTitle(),
                product.getDescription(),
                product.getCategory(),
                product.getPrice(),
                product.getRating(),
                product.getStock(),
                product.getTags(),
                product.getBrand(),
                product.getWeight(),
                product.getDimensions(),
                product.getWarrantyInformation(),
                product.getShippingInformation(),
                product.getAvailabilityStatus(),
                product.getReviews(),
                product.getReturnPolicy(),
                product.getMinimumOrderQuantity(),
                product.getThumbnail(),
                product.getImages());
    }
    public ProductSummaryDTO mapToProductSummary(Product product) {
        ProductSummaryDTO summary = new ProductSummaryDTO();
        summary.setImage(product.getThumbnail());
        summary.setTitle(product.getTitle());
        summary.setPrice(product.getPrice());
        summary.setShortDescription(product.getDescription().length() > 100
                ? product.getDescription().substring(0, 100) + "..."
                : product.getDescription());
        return summary;
    }
}
