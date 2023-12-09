package example.container;

import org.junit.Test;

import static org.junit.Assert.*;

public class ContainerTest {

    @Test
    public void ContainerAddItem() {
        Container container = new Container();
        Item item = new Item(333);
        container.add(item);

        assertEquals(1, container.size());
        assertEquals(item, container.get(0));

    }

    @Test
    public void ContainerDeleteItem() {
        Container container = new Container();
        Item firstItem = new Item(123);
        Item secondItem = new Item(456);

        container.add(firstItem);
        container.add(secondItem);
        container.remove(firstItem);

        assertEquals(secondItem, container.get(0));
    }

}