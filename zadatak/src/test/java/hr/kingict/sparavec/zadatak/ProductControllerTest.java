package hr.kingict.sparavec.zadatak;

import hr.kingict.sparavec.zadatak.controller.ProductController;
import hr.kingict.sparavec.zadatak.dto.ProductDTO;
import hr.kingict.sparavec.zadatak.dto.ProductSummaryDTO;
import hr.kingict.sparavec.zadatak.model.Dimensions;
import hr.kingict.sparavec.zadatak.model.Meta;
import hr.kingict.sparavec.zadatak.model.Product;
import hr.kingict.sparavec.zadatak.model.Review;
import hr.kingict.sparavec.zadatak.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {

    @Mock
    private ProductService productService;

    private ProductDTO productDTO1;
    private ProductDTO productDTO2;

    private ProductSummaryDTO productSummaryDTO1;
    private ProductSummaryDTO productSummaryDTO2;

    @InjectMocks
    private ProductController productController;

    List<ProductDTO> productDTOList = new ArrayList<>();
    List<ProductSummaryDTO> productSummaryDTOList = new ArrayList<>();

    Product product1;
    Product product2;
    Review review1;
    Review review2;
    Meta meta;
    Dimensions dimensions;

    DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;

    private MockMvc mockMvc;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();

        dimensions = new Dimensions(23.17,14.43,28.01);
        review1 = new Review(3, "Descritpion1", LocalDateTime.parse("2024-05-23T08:56:21.618Z", formatter),
                "John Doe", "john.doe@x.dummyjson.com");
        review2 = new Review(2, "Descritpion2", LocalDateTime.parse("2024-05-23T08:56:21.618Z", formatter),
                "Dohn Joe", "dohn.joe@x.dummyjson.com");

        meta = new Meta(LocalDateTime.parse("2024-05-23T08:56:21.618Z", formatter),
                LocalDateTime.parse("2024-05-23T08:56:21.618Z", formatter),
                "9164035109868", "...");

        product1 = new Product(
                1,
                "Product 1",
                "Description1",
                "Category1",
                9.99,
                7.17,
                4.94,
                5,
                Arrays.asList("tag1", "tag2"),
                "Brand1",
                "Sku1",
                2,
                dimensions,
                "Warranty1",
                "Shipping1",
                "Stock1",
                Arrays.asList(review1, review2),
                "Return policy1",
                24,
                meta,
                "...",
                Arrays.asList("...", "...", "...")
        );
        product2 = new Product(
                1,
                "Product 2",
                "Description2",
                "Category2",
                9.99,
                7.17,
                4.94,
                5,
                Arrays.asList("tag1", "tag2"),
                "Brand2",
                "Sku2",
                2,
                dimensions,
                "Warranty2",
                "Shipping2",
                "Stock2",
                Arrays.asList(review1, review2),
                "Return policy2",
                24,
                meta,
                "...",
                Arrays.asList("...", "...", "...")
        );

        productDTO1 = new ProductDTO(
                product1.getTitle(),
                product1.getDescription(),
                product1.getCategory(),
                product1.getPrice(),
                product1.getRating(),
                product1.getStock(),
                product1.getTags(),
                product1.getBrand(),
                product1.getWeight(),
                product1.getDimensions(),
                product1.getWarrantyInformation(),
                product1.getShippingInformation(),
                product1.getAvailabilityStatus(),
                product1.getReviews(),
                product1.getReturnPolicy(),
                product1.getMinimumOrderQuantity(),
                product1.getThumbnail(),
                product1.getImages()
        );

        productDTO2 = new ProductDTO(
                product2.getTitle(),
                product2.getDescription(),
                product2.getCategory(),
                product2.getPrice(),
                product2.getRating(),
                product2.getStock(),
                product2.getTags(),
                product2.getBrand(),
                product2.getWeight(),
                product2.getDimensions(),
                product2.getWarrantyInformation(),
                product2.getShippingInformation(),
                product2.getAvailabilityStatus(),
                product2.getReviews(),
                product2.getReturnPolicy(),
                product2.getMinimumOrderQuantity(),
                product2.getThumbnail(),
                product2.getImages()
        );
        productDTOList.add(productDTO1);
        productDTOList.add(productDTO2);

        productSummaryDTO1 = new ProductSummaryDTO(
                product1.getImages(),
                product1.getTitle(),
                product1.getPrice(),
                product1.getDescription()
        );
        productSummaryDTO2 = new ProductSummaryDTO(
                product2.getImages(),
                product2.getTitle(),
                product2.getPrice(),
                product2.getDescription()
        );

        productSummaryDTOList.add(productSummaryDTO1);
        productSummaryDTOList.add(productSummaryDTO2);
    }

    @Test
    public void getAllTest() throws Exception {
        Mockito.when(productService.getProductDTOSummaries()).thenReturn(productSummaryDTOList);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/products/summary")
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value(productSummaryDTO1.getTitle()))
                .andExpect(jsonPath("$[1].title").value(productSummaryDTO2.getTitle()));
    }

    @Test
    public void getProductDetails() throws Exception{
        List<ProductDTO> testList = new ArrayList<>();

        testList.add(productDTO1);

        Mockito.when(productService.getProductDTODetails(Mockito.anyInt())).thenReturn(testList);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/products/details/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Product 1"))
                .andDo(print());
    }

    @Test
    public void getProductByCategoryAndPrice() throws Exception {
        String category = "Category1";
        double minPrice = 5.0;
        double maxPrice = 15.0;

        List<ProductDTO> filteredProductList = new ArrayList<>();
        filteredProductList.add(productDTO1);

        Mockito.when(productService.getProductDTOByCategoryAndPrice(category, minPrice, maxPrice)).thenReturn(filteredProductList);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/products/categoryAndPrice/{category}", category)
                        .param("minPrice", String.valueOf(minPrice))
                        .param("maxPrice", String.valueOf(maxPrice))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value(productDTO1.getTitle()))
                .andDo(print());
    }

    @Test
    public void getProductByTitle() throws Exception {
        String title = "Product 1";

        List<ProductDTO> productsWithTitle = new ArrayList<>();
        productsWithTitle.add(productDTO1);

        Mockito.when(productService.getProductByTitle(title)).thenReturn(productsWithTitle);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/products/title/{title}", title)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value(productDTO1.getTitle()))
                .andDo(print());
    }
}
