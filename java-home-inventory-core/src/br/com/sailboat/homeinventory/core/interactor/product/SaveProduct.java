package br.com.sailboat.homeinventory.core.interactor.product;

import br.com.sailboat.homeinventory.core.entity.Product;
import br.com.sailboat.homeinventory.core.interactor.UseCase;
import br.com.sailboat.homeinventory.core.repository.ProductRepository;

public class SaveProduct implements UseCase<Boolean> {

    private Product product;
    private ProductRepository productRepository;

    public SaveProduct(Product product, ProductRepository productRepository) {
        this.product = product;
        this.productRepository = productRepository;
    }

    @Override
    public Boolean execute() throws Exception {
        new ProductValidator(product).execute();
        productRepository.save(product);
        return true;
    }

}
