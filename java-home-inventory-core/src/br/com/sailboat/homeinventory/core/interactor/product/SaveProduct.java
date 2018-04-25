package br.com.sailboat.homeinventory.core.interactor.product;

import br.com.sailboat.homeinventory.core.entity.Product;
import br.com.sailboat.homeinventory.core.interactor.UseCase;
import br.com.sailboat.homeinventory.core.repository.ProductRepository;

public class SaveProduct {

    private Product product;
    private ProductRepository productRepository;

    public SaveProduct(Product product, ProductRepository productRepository) {
        this.product = product;
        this.productRepository = productRepository;
    }

    public void execute(UseCase.OnFail response) {
        try {
            new ProductValidator(product).validate();
            productRepository.save(product);
        } catch (Exception e) {
            response.onFail(e);
        }
    }

}
