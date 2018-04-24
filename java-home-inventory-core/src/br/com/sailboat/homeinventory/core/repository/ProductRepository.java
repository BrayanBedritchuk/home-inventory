package br.com.sailboat.homeinventory.core.repository;

import java.util.List;

import br.com.sailboat.homeinventory.core.Filter;
import br.com.sailboat.homeinventory.core.entity.Product;

public interface ProductRepository {
    void save(Product product);
    void remove(Product product);
    List<Product> getAll(Filter filter);
    Product findById(Long productId);
}
