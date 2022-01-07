package webtests.serenityswag.cart;

//record is a shorthand to create a read-only class
//without needing to implement getter methods
public record CartItem(String title, String description, Double price) {

}
