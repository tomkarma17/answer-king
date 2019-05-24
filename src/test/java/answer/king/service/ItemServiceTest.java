package answer.king.service;

import answer.king.model.Item;
import answer.king.repo.ItemRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;

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
        Mockito.when(itemRepository.save(item)).thenReturn(item);
    }

    @Test
    public void testCreateItemReturnsAnEquivalentItem() {
        Item item = ItemServiceTestUtils.getItem();
        Item returnedItem = itemService.save(item);
        assertTrue(ItemServiceTestUtils.areItemsEquivalent(item, returnedItem));
    }

}
