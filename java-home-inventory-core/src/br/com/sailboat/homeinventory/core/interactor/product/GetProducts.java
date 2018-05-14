package br.com.sailboat.homeinventory.core.interactor.product;

import java.util.ArrayList;
import java.util.List;

import br.com.sailboat.homeinventory.core.Filter;
import br.com.sailboat.homeinventory.core.entity.Product;
import br.com.sailboat.homeinventory.core.interactor.UseCase;
import br.com.sailboat.homeinventory.core.repository.ProductRepository;

public class GetProducts implements UseCase<List<Product>> {

    private ProductRepository productRepository;
    private Filter filter;

    public GetProducts(ProductRepository productRepository, Filter filter) {
        this.productRepository = productRepository;
        this.filter = filter;
    }

    @Override
    public List<Product> execute() {
        List<Product> products = productRepository.getAll(filter);

        if (products == null) {
            products = new ArrayList<>();
        }

        return products;
    }

}
