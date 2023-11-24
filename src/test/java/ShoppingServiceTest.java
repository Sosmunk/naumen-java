import customer.Customer;
import org.junit.jupiter.api.Test;
import product.ProductDao;
import shopping.Cart;
import shopping.ShoppingService;
import shopping.ShoppingServiceImpl;

import static org.mockito.Mockito.mock;

;

/**
 * Тестирование сервиса {@link ShoppingService}
 *
 * @author Пыжьянов Вячеслав
 */
public class ShoppingServiceTest {

    private final ProductDao productDaoMock = mock(ProductDao.class);

    private final ShoppingService shoppingService = new ShoppingServiceImpl(productDaoMock);

    /**
     * Тестирование получения корзины покупателя
     */
    @Test
    public void GetCartTest(Customer customer) {
        //TODO
    }

    /**
     * Тестирование получения списка товаров
     * <p>
     * Тестировать нет смысла, так как всё что делает данный метод - вызывает метод из DAO без дополнительной логики
     */
    @Test
    public void getAllProductsTest() {
//        when(productDaoMock.getAll()).thenReturn(new ArrayList<Product>());
//
//        shoppingService.getAllProducts();
//
//        verify(productDaoMock, times(1)).getAll();
    }

    /**
     * Тест на получение товара по названию
     * <p>
     * Тестировать нет смысла, так как всё что делает данный метод - вызывает метод из DAO без дополнительной логики
     */
    @Test
    public void getProductByNameTest() {
//        String name = "ТРИ КОРОЧКИ";
//
//        when(productDaoMock.getByName(name)).thenReturn(new Product());
//
//        shoppingService.getProductByName(name);
//
//        verify(productDaoMock, times(1)).getByName(name);

    }

    /**
     * Тест на успешную покупку
     */
    @Test
    public void SuccessfulBuyTest() {
        Cart cart = new Cart(null);
    }

}
