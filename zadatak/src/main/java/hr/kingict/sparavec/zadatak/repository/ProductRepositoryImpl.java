package hr.kingict.sparavec.zadatak.repository;

import hr.kingict.sparavec.zadatak.model.Product;
import hr.kingict.sparavec.zadatak.model.ProductsList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ProductRepositoryImpl implements ProductRepository{

    @Autowired
    private WebClient.Builder webClientBuilder;
    private static final String PRODUCT_API_URL = "https://dummyjson.com/products";
    private static final int LIMIT = 30;

    @Override
    public List<Product> findAll() {
        return fetchProducts(PRODUCT_API_URL, 0, LIMIT);
    }

    @Override
    public Optional<Product> findProductById(Integer id) {
        return findAll().stream()
                .filter(f1 -> f1.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Product> findProductByCategoryAndPrice(String category, double minPrice, double maxPrice) {

        String url = PRODUCT_API_URL + "/category/" + category.toLowerCase();
        List<Product> products = fetchProducts(url, 0, LIMIT);

        return products.stream()
                .filter(product -> product.getPrice() >= minPrice && product.getPrice() <= maxPrice)
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> findProductByTitle(String title) {
        return findAll().stream()
                .filter(product -> product.getTitle().toLowerCase().contains(title.toLowerCase()))
                .collect(Collectors.toList());
    }

    private List<Product> fetchProducts(String url, int skip, int limit){

        List<Product> allProducts = new ArrayList<>();

        while(true){
            String paginatedUrl = url + "?limit=" + limit + "&skip=" + skip;
            List<Product> products = webClientBuilder.build()
                    .get()
                    .uri(paginatedUrl)
                    .retrieve()
                    .bodyToMono(ProductsList.class)
                    .map(ProductsList::getProducts)
                    .block();

            if(products == null || products.isEmpty())
                break;

            allProducts.addAll(products);
            skip += limit;

            if(products.size() < limit){
                break;
            }
        }

        return allProducts;
    }
}
