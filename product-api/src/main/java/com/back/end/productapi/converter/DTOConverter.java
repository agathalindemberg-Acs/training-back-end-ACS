//O método convert(Category category) converte um objeto Category em um objeto CategoryDTO. Ele cria uma instância de CategoryDTO, define os valores de id e nome com base no objeto Category fornecido e retorna o objeto CategoryDTO resultante.
//O método convert(Product product) converte um objeto Product em um objeto ProductDTO. Ele cria uma instância de ProductDTO, define os valores de nome e preco com base no objeto Product fornecido e, se a propriedade category do objeto Product não for nula, chama recursivamente o método convert para converter o objeto Category em um CategoryDTO e atribui-o à propriedade category do ProductDTO. Em seguida, retorna o objeto ProductDTO resultante.
//Transforma em entidades


package com.back.end.productapi.converter;

import com.back.end.productapi.dto.CategoryDTO;
import com.back.end.productapi.dto.ProductDTO;
import com.back.end.productapi.model.Category;
import com.back.end.productapi.model.Product;

public class DTOConverter {
    public static CategoryDTO convert(Category category) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(category.getId());
        categoryDTO.setNome(category.getNome());
        return categoryDTO;
    }

    public static ProductDTO convert(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setNome(product.getNome());
        productDTO.setPreco(product.getPreco());
        if (product.getCategory() != null) {
            productDTO.setCategory(DTOConverter.convert(product.getCategory()));
        }
        return productDTO;
    }
}