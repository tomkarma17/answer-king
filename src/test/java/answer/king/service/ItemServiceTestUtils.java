package answer.king.service;

import answer.king.model.Item;
import answer.king.model.Order;

import java.math.BigDecimal;
import java.util.Collections;

final class ItemServiceTestUtils {

    private static final Item item;

    static {
        item = new Item();
        item.setId(1L);
        item.setName("Item 1");
        item.setPrice(BigDecimal.ONE);

        Order order = new Order();
        order.setId(1L);
        order.setPaid(false);
        order.setItems(Collections.singletonList(item));
        item.setOrder(order);
    }

    static Item getItem() {
        return item;
    }

    static boolean areItemsEquivalent(Item item1, Item item2) {
        if (!item1.getId().equals(item2.getId())) {
            return false;
        }
        if (!item1.getName().equals(item2.getName())) {
            return false;
        }
        if (!item1.getPrice().equals(item2.getPrice())) {
            return false;
        }
        return areOrdersEquivalent(item1.getOrder(), item2.getOrder());
    }

    private static boolean areOrdersEquivalent(Order order1, Order order2) {
        if (!order1.getId().equals(order2.getId())) {
            return false;
        }
        if (!order1.getPaid().equals(order2.getPaid())) {
            return false;
        }
        return (order1.getItems().size() == order2.getItems().size());
    }

}
