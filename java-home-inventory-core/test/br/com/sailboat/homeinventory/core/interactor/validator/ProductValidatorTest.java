package br.com.sailboat.homeinventory.core.interactor.validator;

import org.junit.Before;
import org.junit.Test;

import br.com.sailboat.homeinventory.core.entity.Product;
import br.com.sailboat.homeinventory.core.exception.FieldNotFilledException;

public class ProductValidatorTest {

    private Product product;

    @Before
    public void createProduct() {
        product = new Product();
    }

    @Test(expected = FieldNotFilledException.class)
    public void testNameNull() throws FieldNotFilledException {
        product.setName(null);
        new ProductValidator(product).validateName();
    }

    @Test(expected = FieldNotFilledException.class)
    public void testNameEmpty() throws FieldNotFilledException {
        product.setName("     ");
        new ProductValidator(product).validateName();
    }

    @Test
    public void testQuantityNull() throws FieldNotFilledException {
        product.setQuantity(null);
        new ProductValidator(product).validateQuantity();
    }

    @Test(expected = FieldNotFilledException.class)
    public void testQuantityNegative() throws FieldNotFilledException {
        product.setQuantity(-1);
        new ProductValidator(product).validateQuantity();
    }

    @Test
    public void testNameFilled() throws FieldNotFilledException {
        product.setName("Product");
        new ProductValidator(product).validateName();
    }

    @Test
    public void testQuantityZero() throws FieldNotFilledException {
        product.setQuantity(0);
        new ProductValidator(product).validateQuantity();
    }

    @Test
    public void testQuantityTen() throws FieldNotFilledException {
        product.setQuantity(10);
        new ProductValidator(product).validateQuantity();
    }

}
