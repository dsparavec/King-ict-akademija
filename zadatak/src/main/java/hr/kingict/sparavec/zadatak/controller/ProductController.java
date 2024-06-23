package hr.kingict.sparavec.zadatak.controller;

import hr.kingict.sparavec.zadatak.dto.ProductDTO;
import hr.kingict.sparavec.zadatak.dto.ProductSummaryDTO;
import hr.kingict.sparavec.zadatak.service.ProductService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@AllArgsConstructor
public class ProductController {

    @Autowired
    private ProductService productService;

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @GetMapping("/summary")
    public List<ProductSummaryDTO> getProductSummaries() {
        logger.info("All product summeries.");
        return productService.getProductDTOSummaries();
    }

    @GetMapping("/details/{id}")
    public List<ProductDTO> getProductDetails(@PathVariable Integer id) {
        logger.info("Product details with id: " + id);
        return productService.getProductDTODetails(id);
    }

    @GetMapping("/categoryAndPrice/{category}")
    public List<ProductDTO> getProductByCategoryAndPrice(@PathVariable String category, @RequestParam double minPrice, @RequestParam double maxPrice) {
        logger.info("Product sorted by category: " + category + ", with minimum starting price: " + minPrice + " and maximum price: " + maxPrice);
        return productService.getProductDTOByCategoryAndPrice(category, minPrice, maxPrice);
    }

    @GetMapping("/title/{title}")
    public List<ProductDTO> getProductByTitle(@PathVariable String title) {
        logger.info("Product by title: " + title);
        return productService.getProductByTitle(title);
    }
}
