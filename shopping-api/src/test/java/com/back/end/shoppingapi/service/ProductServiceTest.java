package com.back.end.shoppingapi.service;

import com.back.end.java.dto.ProductDTO;
import	com.fasterxml.jackson.databind.ObjectMapper;
import	okhttp3.mockwebserver.MockResponse;
import	okhttp3.mockwebserver.MockWebServer;
import	org.junit.jupiter.api.*;
import	org.junit.jupiter.api.extension.ExtendWith;
import	org.mockito.InjectMocks;
import org.mockito.Mockito;
import	org.mockito.junit.jupiter.MockitoExtension;
import	org.springframework.test.util.ReflectionTestUtils;
import	java.io.IOException;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
    public static MockWebServer mockBackEnd;
    @InjectMocks
    private ProductService productService;

    @Test
    void test_getProductByIdentifier() throws Exception{
        ProductDTO productDTO = new ProductDTO();
        productDTO.setPreco(1000F);
        productDTO.setProductIdentifier("prod-identifier");

        ObjectMapper objectMapper = new ObjectMapper();

        mockBackEnd.enqueue(new MockResponse()
                .setBody(objectMapper.writeValueAsString(productDTO))
                .addHeader("Content=Type", "aplication/json"));

        productDTO = productService.getProductByIdentifier("prod-identifier");

        Assertions.assertEquals(1000F, productDTO.getPreco());
        Assertions.assertEquals("prod-identifier", productDTO.getProductIdentifier());

    }



    @BeforeEach
    void setUp() throws IOException {
        mockBackEnd = new MockWebServer();
        mockBackEnd.start();

        String baseUrl = String.format("http://localhost:%s", mockBackEnd.getPort());
        ReflectionTestUtils.setField(productService, "productApiUrl", baseUrl);
    }
     @AfterEach
    void tearDown() throws IOException{
        mockBackEnd.shutdown();
    }
}
