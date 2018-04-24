package br.com.sailboat.homeinventory.core.interactor.validator;

import br.com.sailboat.homeinventory.core.entity.Product;
import br.com.sailboat.homeinventory.core.exception.FieldNotFilledException;

public class ProductValidator {

    private Product product;

    public ProductValidator(Product product) {
        this.product = product;
    }

    public static void validate(Product product) throws FieldNotFilledException {
        ProductValidator validator = new ProductValidator(product);
        validator.validateName();
        validator.validateQuantity();
    }

    public void validateName() throws FieldNotFilledException {
        if (product.getName() == null || product.getName().trim().isEmpty()) {
            throw new FieldNotFilledException();
        }
    }

    public void validateQuantity() throws FieldNotFilledException {
        if (product.getQuantity() != null && product.getQuantity() < 0) {
            throw new FieldNotFilledException();
        }
    }
}
