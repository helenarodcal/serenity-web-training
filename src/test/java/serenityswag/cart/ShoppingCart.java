package serenityswag.cart;

import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;

public class ShoppingCart extends PageObject {

    public String badgeCount() {
        return find(".shopping_cart_link").getText();
    }

    public static By iconLink() {
        return By.cssSelector(".shopping_cart_link");
    }
}
