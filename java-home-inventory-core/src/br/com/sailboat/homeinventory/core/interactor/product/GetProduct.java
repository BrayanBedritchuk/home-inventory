package br.com.sailboat.homeinventory.core.interactor.product;

import br.com.sailboat.homeinventory.core.entity.Product;
import br.com.sailboat.homeinventory.core.interactor.UseCaseWithResponse;
import br.com.sailboat.homeinventory.core.repository.ProductRepository;

public class GetProduct implements UseCaseWithResponse<Product> {

    private ProductRepository productRepository;
    private Long productId;

    public GetProduct(ProductRepository productRepository, Long productId) {
        this.productRepository = productRepository;
        this.productId = productId;
    }

    @Override
    public void execute(Response<Product> response) {
        try {
            Product product = productRepository.findById(productId);
            response.onSuccess(product);
        } catch (Exception e) {
            response.onFail(e);
        }
    }

    public Product execute() {
        return productRepository.findById(productId);
    }

}