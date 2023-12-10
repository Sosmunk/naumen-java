import customer.Customer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import product.Product;
import product.ProductDao;
import shopping.BuyException;
import shopping.Cart;
import shopping.ShoppingService;
import shopping.ShoppingServiceImpl;

import static org.mockito.Mockito.*;

/**
 * Тестирование сервиса {@link ShoppingService}
 *
 * @author Месилов Андрей
 */
public class ShoppingServiceTest {

    private final ProductDao productDaoMock = mock(ProductDao.class);

    private final ShoppingService shoppingService = new ShoppingServiceImpl(productDaoMock);


    /**
     * Тестирование получения списка товаров
     * <p>
     * Тестировать нет смысла, так как всё что делает данный метод - вызывает метод из DAO без дополнительной логики
     */
    @Test
    public void getAllProductsTest() {

    }

    /**
     * Тест на получение товара по названию
     * <p>
     * Тестировать нет смысла, так как всё что делает данный метод - вызывает метод из DAO без дополнительной логики
     */
    @Test
    public void getProductByNameTest() {

    }

    /**
     * Проверка на получение той же самой корзины покупателя
     */
    @Test
    public void getSameCustomerCartTest() {
        Customer customer = new Customer(1, "+71234567890");
        Cart cart = shoppingService.getCart(customer);
        Product product = new Product();
        product.setName("a");
        product.addCount(10);
        cart.add(product, 1);

        Assertions.assertEquals(cart.getProducts(), shoppingService.getCart(customer).getProducts());

    }

    /**
     * Тест на покупку пустой корзины
     */
    @Test
    public void emptyCartBuyTest() throws BuyException {
        Cart cart = new Cart(null);

        Assertions.assertFalse(shoppingService.buy(cart));
        verify(productDaoMock, never()).save(any(Product.class));
    }

    /**
     * Тест на успешную покупку единственного продукта
     */
    @Test
    public void SuccessfulBuyOneItemTest() throws BuyException {
        Cart cart = new Cart(null);
        Product product = new Product();
        product.setName("a");
        product.addCount(1);
        // Тест падает, что не логично
        // Если количество продуктов == 1,
        // то должна быть возможность добавить последний продукт
        cart.add(product, 1);
        boolean res = shoppingService.buy(cart);

        Assertions.assertTrue(res);
        verify(productDaoMock, times(1)).save(product);
        verify(product, times(1)).subtractCount(product.getCount());
    }

    /**
     * Тест на покупку нескольких одинаковых продуктов из корзины
     */
    @Test
    public void BuyMultipleCountProduct() throws BuyException {
        Cart cart = new Cart(null);
        Product product = new Product();
        product.setName("a");
        product.addCount(4);
        cart.add(product, 2);
        boolean res = shoppingService.buy(cart);
        Assertions.assertTrue(res);
        Assertions.assertEquals(2, cart.getProducts().get(product));
    }

    /**
     * Тест на успешную покупку нескольких разныъ продуктов
     */
    @Test
    public void SuccessfulBuyDifferentProducts() throws BuyException {
        Cart cart = new Cart(null);

        Product prodA = new Product();
        prodA.setName("first");
        prodA.addCount(10);

        Product prodB = new Product();
        prodB.setName("second");
        prodB.addCount(15);

        cart.add(prodA, 5);
        cart.add(prodB, 4);

        boolean res = shoppingService.buy(cart);

        Assertions.assertTrue(res);
        verify(productDaoMock, times(1)).save(prodA);
        verify(productDaoMock, times(1)).save(prodB);
    }

    /**
     * Тест на выдачу ошибки,
     * если покупается больше продуктов,
     * чем есть в наличии
     */
    @Test
    public void BuyMoreProductsThanExistsThrowsExceptionTest() throws BuyException {
        Cart cart = new Cart(null);
        Cart secondCart = new Cart(null);
        Product product = new Product();
        product.setName("A");
        product.addCount(5);

        cart.add(product, 4);
        secondCart.add(product, 4);
        shoppingService.buy(secondCart);

        BuyException buyException = Assertions.assertThrows(BuyException.class, () -> shoppingService.buy(cart));
        Assertions.assertEquals(
                "В наличии нет необходимого количества товара " + "A",
                buyException.getMessage());

    }
    /**
     * Тест на покупку отрицательного количества товара
     */
    @Test
    public void BuyNegativeProductCountTest() throws BuyException {
        Cart cart = new Cart(null);
        Product product = new Product();
        product.setName("A");
        product.addCount(5);
        cart.add(product, -1);

        shoppingService.buy(cart);
        Assertions.assertEquals(5, product.getCount());
        verify(productDaoMock, never()).save(product);
    }

    // Если правильно помню из лекции, корзина не опустошается.
    // Это логическая ошибка, но сам по себе функционал не реализован
    // Поэтому тестировать его нет смысла

}

