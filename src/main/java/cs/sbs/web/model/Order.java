package cs.sbs.web.model;

/**
 * 订单模型，包含订单号、顾客、菜品和数量
 */
public class Order {
    private String id;
    private String customer;
    private String food;
    private int quantity;

    public Order(String id, String customer, String food, int quantity) {
        this.id = id;
        this.customer = customer;
        this.food = food;
        this.quantity = quantity;
    }

    public String getId() { return id; }
    public String getCustomer() { return customer; }
    public String getFood() { return food; }
    public int getQuantity() { return quantity; }
}