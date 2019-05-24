package answer.king.service;

import answer.king.model.Item;
import answer.king.repo.ItemRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class ItemServiceTest {

    @TestConfiguration
    static class ItemServiceTestConfiguration {

        @Bean
        public ItemService itemService() {
            return new ItemService();
        }

    }

    @Autowired
    private ItemService itemService;

    @MockBean
    private ItemRepository itemRepository;

    @Before
    public void setUp() {
        Item item = ItemServiceTestUtils.getItem();
        Item updatedItem = ItemServiceTestUtils.getUpdatedItem();
        when(itemRepository.save(item)).thenReturn(item);
        when(itemRepository.save(updatedItem)).thenReturn(updatedItem);
    }

    @Test
    public void testCreateItemReturnsAnEquivalentItem() {
        Item item = ItemServiceTestUtils.getItem();
        Item returnedItem = itemService.save(item);
        assertTrue(ItemServiceTestUtils.areItemsEquivalent(item, returnedItem));
    }

    @Test
    public void testCreateItemCallsRepositorySaveMethod() {
        ArgumentCaptor<Item> itemArgumentCaptor = ArgumentCaptor.forClass(Item.class);
        Item item = ItemServiceTestUtils.getItem();
        when(itemRepository.save(itemArgumentCaptor.capture())).thenReturn(null);
        itemService.save(item);
        assertEquals(item, itemArgumentCaptor.getValue());
    }

    @Test
    public void testUpdatingItemReturnsTheUpdatedItem() {
        Item item = ItemServiceTestUtils.getItem();
        itemService.save(item);

        Item updatedItem = ItemServiceTestUtils.getUpdatedItem();
        Item returnedItem = itemService.save(updatedItem);
        assertTrue(ItemServiceTestUtils.areItemsEquivalent(updatedItem, returnedItem));
    }

}
