package br.com.sailboat.homeinventory.core.interactor;

import br.com.sailboat.homeinventory.core.entity.Product;
import br.com.sailboat.homeinventory.core.interactor.validator.ProductValidator;
import br.com.sailboat.homeinventory.core.repository.ProductRepository;

public class SaveProduct implements UseCase {

    private Product product;
    private ProductRepository productRepository;

    public SaveProduct(Product product, ProductRepository productRepository) {
        this.product = product;
        this.productRepository = productRepository;
    }

    @Override
    public void execute(Response response) {
        try {
            ProductValidator.validate(product);
            productRepository.save(product);
            response.onSuccess();
        } catch (Exception e) {
            response.onFail(e);
        }
    }

}
