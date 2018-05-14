package br.com.sailboat.homeinventory.core.interactor.product;

import br.com.sailboat.homeinventory.core.entity.Product;
import br.com.sailboat.homeinventory.core.interactor.UseCase;
import br.com.sailboat.homeinventory.core.repository.ProductRepository;

public class GetProduct implements UseCase<Product> {

    private ProductRepository productRepository;
    private Long productId;

    public GetProduct(ProductRepository productRepository, Long productId) {
        this.productRepository = productRepository;
        this.productId = productId;
    }

    @Override
    public Product execute() {
        return productRepository.findById(productId);
    }

}