package hr.kingict.sparavec.zadatak.repository;

import hr.kingict.sparavec.zadatak.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    List<Product> findAll();

    Optional<Product> findProductById(Integer id);

    List<Product> findProductByCategoryAndPrice(String category, double minPrice, double maxPrice);

    List<Product> findProductByTitle(String title);

}
