package webtests.serenityswag.cart;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.jetbrains.annotations.NotNull;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import webtests.serenityswag.authentication.LoginActions;
import webtests.serenityswag.inventory.ProductList;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static webtests.serenityswag.authentication.User.*;

@RunWith(SerenityRunner.class)
public class WhenAddingAnItemToTheCart {

    @Managed(driver = "chrome")
    WebDriver driver;

    @Steps
    LoginActions login;

    @Before
    public void login() {
        login.as(STANDARD_USER);
    }

    ShoppingCart shoppingCartBadge;

    @Steps
    CartActions cart;

    @Test
    public void theCorrectItemCountShouldBeShown() {
        //check that the shopping cart badge is empty
        Serenity.reportThat("The shopping cart badge should be empty",
                () -> assertThat(shoppingCartBadge.badgeCount()).isEmpty()
        );

        //add 1 item to the cart
        cart.addItem("Sauce Labs Backpack");

        //the shopping cart badge should be 1
        Serenity.reportThat("The shopping cart badge should be 1",
                () -> assertThat(shoppingCartBadge.badgeCount()).isEqualTo("1")
        );

    }

    ProductList productList;

    @Test
    public void allTheItemsShouldAppearInTheCart() {

        // Add several items to the cart
        List<String> selectedItems = firstThreeProductTitlesDisplayed();
        cart.addItems(selectedItems);

        //the shopping cart badge should be equal to the number of items added
        String cartBadgeCount = Integer.toString(selectedItems.size());
        Serenity.reportThat("The shopping cart badge should be " + cartBadgeCount,
                () -> assertThat(shoppingCartBadge.badgeCount()).isEqualTo(cartBadgeCount)
        );

        // Open the cart page
        cart.openCart();

        // Should see all items listed
        Serenity.reportThat("Should see all the selected items listed",
                () -> assertThat(cart.displayedItems()).containsExactlyElementsOf(selectedItems)
        );
    }

    @NotNull
    private List<String> firstThreeProductTitlesDisplayed() {
        return productList.titles().subList(0, 3);
    }

    CartPageObject cartPage;

    @Test
    public void pricesForEachItemShouldBeShownInTheCart() {
        //add items to shopping cart
        cart.addItems(firstThreeProductTitlesDisplayed());

        //open cart page
        cartPage.open();

        //check that each item in the cart has a price
        //first we need a DOMAIN OBJECT called cartItem that would contain
        //title, description and price
        List<CartItem> items = cartPage.items();
            //check that all elements are in the cart
            // and price is greater than 0
            //and description is not empty
        assertThat(items).hasSize(3)
                .allMatch(item -> item.price() > 0.0)
                .allMatch(item -> !item.description().isEmpty());
    }
}
