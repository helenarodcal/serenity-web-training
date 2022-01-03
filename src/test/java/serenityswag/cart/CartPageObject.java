package serenityswag.cart;

import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

@DefaultUrl("https://www.saucedemo.com/cart.html")
public class CartPageObject extends PageObject {

//    @FindBy(id = "checkout")
//    WebElementFacade checkoutButton;
//    @FindBy(css = ".title")
//    WebElementFacade title;
    private static final By CHECKOUT_BUTTON = By.id("checkout");
    private static final By TITLE = By.cssSelector(".title");
    private static final By CART_ITEMS = By.className("cart_item");

    public void checkout() {
//        checkoutButton.click();
        //we can do it without the findBy annotation and WebElementFacade
        $(CHECKOUT_BUTTON).click();
    }

    public String title() {
//       return title.getText();
        return $(TITLE).getText();
    }

//    List<WebElementFacade> cartItemElements;

    public List<CartItem> items() {
        /*
        List<CartItem> cartItems = new ArrayList<>();
//        for(WebElementFacade cartItemElement : cartItemElements) {
        for(WebElementFacade cartItemElement : findAll(CART_ITEMS)) {
            String name =  cartItemElement.findBy(".inventory_item_name").getText();
            String description = cartItemElement.findBy(".inventory_item_desc").getText();
            Double price = priceFrom(cartItemElement.findBy(".inventory_item_price").getText());
            cartItems.add(new CartItem(name, description, price));
        }
*/
        return findAll(CART_ITEMS).map(
                item -> new CartItem(
                        item.findBy(".inventory_item_name").getText(),
                        item.findBy(".inventory_item_desc").getText(),
                        priceFrom(item.findBy(".inventory_item_price").getText())
                )
        );
//        return cartItems;
    }

    private Double priceFrom(String textPriceValue) {
        return Double.parseDouble(textPriceValue.replace("$", " "));
    }
}
