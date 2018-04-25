package br.com.sailboat.homeinventory.core.interactor.product;

import org.junit.Before;
import org.junit.Test;

import br.com.sailboat.homeinventory.core.entity.Product;
import br.com.sailboat.homeinventory.core.exception.InvalidFieldException;

public class ProductValidatorTest {

    private Product product;

    @Before
    public void createProduct() {
        product = new Product();
        product.setName("Product");
        product.setQuantity(0);
    }

    @Test(expected = InvalidFieldException.class)
    public void testNameNull() throws InvalidFieldException {
        product.setName(null);
        new ProductValidator(product).validate();
    }

    @Test(expected = InvalidFieldException.class)
    public void testNameEmpty() throws InvalidFieldException {
        product.setName("     ");
        new ProductValidator(product).validate();
    }

    @Test
    public void testQuantityNull() throws InvalidFieldException {
        product.setQuantity(null);
        new ProductValidator(product).validate();
    }

    @Test(expected = InvalidFieldException.class)
    public void testQuantityNegative() throws InvalidFieldException {
        product.setQuantity(-1);
        new ProductValidator(product).validate();
    }

    @Test
    public void testNameFilled() throws InvalidFieldException {
        new ProductValidator(product).validate();
    }

    @Test
    public void testQuantityZero() throws InvalidFieldException {
        new ProductValidator(product).validate();
    }

    @Test
    public void testQuantityTen() throws InvalidFieldException {
        product.setQuantity(10);
        new ProductValidator(product).validate();
    }

}
