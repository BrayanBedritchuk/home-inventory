package br.com.sailboat.homeinventory.core.interactor;

import java.util.ArrayList;
import java.util.List;

import br.com.sailboat.homeinventory.core.Filter;
import br.com.sailboat.homeinventory.core.entity.Product;
import br.com.sailboat.homeinventory.core.repository.ProductRepository;

public class GetProducts implements UseCaseWithResponse<List<Product>> {

    private ProductRepository productRepository;
    private Filter filter;

    public GetProducts(ProductRepository productRepository, Filter filter) {
        this.productRepository = productRepository;
        this.filter = filter;
    }

    @Override
    public void execute(Response<List<Product>> response) {
        try {
            List<Product> products = productRepository.getAll(filter);

            if (products == null) {
                products = new ArrayList<>();
            }

            response.onSuccess(products);
        } catch (Exception e) {
            response.onFail(e);
        }
    }

}
