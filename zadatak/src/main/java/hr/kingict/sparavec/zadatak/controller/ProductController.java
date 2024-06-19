package hr.kingict.sparavec.zadatak.controller;

import hr.kingict.sparavec.zadatak.dto.ProductDTO;
import hr.kingict.sparavec.zadatak.dto.ProductSummaryDTO;
import hr.kingict.sparavec.zadatak.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@AllArgsConstructor
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/summary")
    public List<ProductSummaryDTO> getProductSummaries() {
        return productService.getProductDTOSummaries();
    }

    @GetMapping("/details/{id}")
    public List<ProductDTO> getProductDetails(@PathVariable Integer id) {
        return productService.getProductDTODetails(id);
    }

    @GetMapping("/categoryAndPrice/{category}")
    public List<ProductDTO> getProductByCategoryAndPrice(@PathVariable String category, @RequestParam double minPrice, @RequestParam double maxPrice) {
        return productService.getProductDTOByCategoryAndPrice(category, minPrice, maxPrice);
    }

    @GetMapping("/title/{title}")
    public List<ProductDTO> getProductByTitle(@PathVariable String title) {
        return productService.getProductByTitle(title);
    }
}
