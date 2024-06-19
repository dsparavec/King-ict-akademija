package hr.kingict.sparavec.zadatak.service;

import hr.kingict.sparavec.zadatak.dto.ProductDTO;
import hr.kingict.sparavec.zadatak.dto.ProductSummaryDTO;
import hr.kingict.sparavec.zadatak.facade.Mapper;
import hr.kingict.sparavec.zadatak.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    private Mapper mapper;

    @Override
    public List<ProductSummaryDTO> getProductDTOSummaries() {
        return productRepository.findAll().stream()
                .map(mapper::mapToProductSummary)
                .toList();
    }
    @Override
    public List<ProductDTO> getProductDTODetails(Integer id) {
        return productRepository.findProductById(id).stream()
                .map(mapper::convertProductToProductDTO)
                .toList();
    }

    @Override
    public List<ProductDTO> getProductDTOByCategoryAndPrice(String category, double minPrice, double maxPrice) {
        return productRepository.findProductByCategoryAndPrice(category, minPrice, maxPrice).stream()
                .map(mapper::convertProductToProductDTO)
                .toList();
    }

    @Override
    public List<ProductDTO> getProductByTitle(String title) {
        return productRepository.findProductByTitle(title).stream()
                .map(mapper::convertProductToProductDTO)
                .toList();
    }

}
