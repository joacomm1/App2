package src.PizzaPedido;

public class PizzaPedido {
    private float pizza_id;
    private float order_id;
    private String pizza_name_id;
    private float quantity;
    private String order_date;
    private String order_time;
    private float unit_price;
    private float total_price;
    private String pizza_size;
    private String pizza_category;
    private String pizza_ingredients;
    private String pizza_name;

    public PizzaPedido(float pizza_id, float order_id, String pizza_name_id, float quantity, String order_date,
                       String order_time, float unit_price, float total_price, String pizza_size,
                       String pizza_category, String pizza_ingredients, String pizza_name) {
        this.pizza_id = pizza_id;
        this.order_id = order_id;
        this.pizza_name_id = pizza_name_id;
        this.quantity = quantity;
        this.order_date = order_date;
        this.order_time = order_time;
        this.unit_price = unit_price;
        this.total_price = total_price;
        this.pizza_size = pizza_size;
        this.pizza_category = pizza_category;
        this.pizza_ingredients = pizza_ingredients;
        this.pizza_name = pizza_name;
    }
    public float getPizzaId() {
        return pizza_id;
    }

    public float getOrderId() {
        return order_id;
    }

    public String getPizzaNameId() {
        return pizza_name_id;
    }

    public float getQuantity() {
        return quantity;
    }

    public String getOrderDate() {
        return order_date;
    }

    public String getOrderTime() {
        return order_time;
    }

    public float getUnitPrice() {
        return unit_price;
    }

    public float getTotalPrice() {
        return total_price;
    }

    public String getPizzaSize() {
        return pizza_size;
    }

    public String getPizzaCategory() {
        return pizza_category;
    }

    public String getPizzaIngredients() {
        return pizza_ingredients;
    }

    public String getPizzaName() {
        return pizza_name;
    }
}