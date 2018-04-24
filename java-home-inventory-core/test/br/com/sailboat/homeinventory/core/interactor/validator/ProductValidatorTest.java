package br.com.sailboat.homeinventory.core.interactor.validator;

import org.junit.Test;

import br.com.sailboat.homeinventory.core.entity.Product;
import br.com.sailboat.homeinventory.core.exception.FieldNotFilledException;

public class ProductValidatorTest {

    @Test(expected = FieldNotFilledException.class)
    public void testNameNull() throws FieldNotFilledException {
        Product product = new Product();
        product.setName(null);
        product.setQuantity(10);

        ProductValidator.validate(product);
    }

    @Test(expected = FieldNotFilledException.class)
    public void testNameEmpty() throws FieldNotFilledException {
        Product product = new Product();
        product.setName("     ");
        product.setQuantity(10);

        ProductValidator.validate(product);
    }

    @Test(expected = FieldNotFilledException.class)
    public void testQuantityNegative() throws FieldNotFilledException {
        Product product = new Product();
        product.setName("Product");
        product.setQuantity(-10);

        ProductValidator.validate(product);
    }

    @Test
    public void testNameFilled() throws FieldNotFilledException {
        Product product = new Product();
        product.setName("Product");
        product.setQuantity(0);

        new ProductValidator(product).validateName();
    }

    @Test
    public void testQuantityZero() throws FieldNotFilledException {
        Product product = new Product();
        product.setName("Product");
        product.setQuantity(0);

        new ProductValidator(product).validateQuantity();
    }

    @Test
    public void testQuantityTen() throws FieldNotFilledException {
        Product product = new Product();
        product.setName("Product");
        product.setQuantity(10);

        new ProductValidator(product).validateQuantity();
    }

}
