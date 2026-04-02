package cs.sbs.web.model;

/**
 * 菜单项模型，包含菜品名称和价格
 */
public class MenuItem {
    private String name;
    private int price;

    public MenuItem(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() { return name; }
    public int getPrice() { return price; }
}