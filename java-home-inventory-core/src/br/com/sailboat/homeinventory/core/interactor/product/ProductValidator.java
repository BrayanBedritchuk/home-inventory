package br.com.sailboat.homeinventory.core.interactor.product;

import java.util.ArrayList;
import java.util.List;

import br.com.sailboat.homeinventory.core.entity.Product;
import br.com.sailboat.homeinventory.core.exception.InvalidFieldException;
import br.com.sailboat.homeinventory.core.interactor.InvalidFieldRule;
import br.com.sailboat.homeinventory.core.interactor.UseCase;

public class ProductValidator implements UseCase<Boolean> {

    private Product product;
    private List<InvalidFieldRule> rules;

    public ProductValidator(Product product) {
        this.product = product;
        this.rules = new ArrayList<>();
    }

    @Override
    public Boolean execute() throws Exception {
        validateName();
        validateQuantity();

        if (!rules.isEmpty()) {
            throw new InvalidFieldException(rules);
        }

        return true;
    }

    private void validateName() throws InvalidFieldException {
        if (product.getName() == null || product.getName().trim().isEmpty()) {
            rules.add(InvalidProductFields.NAME_NOT_FILLED);
        }
    }

    private void validateQuantity() throws InvalidFieldException {
        if (product.getQuantity() != null && product.getQuantity() < 0) {
            rules.add(InvalidProductFields.QUANTITY_NEGATIVE);
        }
    }

    public enum InvalidProductFields implements InvalidFieldRule {
        NAME_NOT_FILLED, QUANTITY_NEGATIVE
    }
}