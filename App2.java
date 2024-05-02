import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.ArrayList;

public class App2 {
    public static List<PizzaPedido> pedidos = new ArrayList<>();

    public static class PizzaPedido {
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

    public static void main(String[] args) {

        String file = "ventas1.csv";
        BufferedReader reader = null;
        String line = "";

        try {
            reader = new BufferedReader(new FileReader(file));
            int count = 0;
            line = reader.readLine();
            while ((line = reader.readLine()) != null) {

                String[] row = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
                pedidos.add(new PizzaPedido(
                        Float.parseFloat(row[0]),
                        Float.parseFloat(row[1]),
                        row[2],
                        Float.parseFloat(row[3]),
                        row[4],
                        row[5],
                        Float.parseFloat(row[6]),
                        Float.parseFloat(row[7]),
                        row[8],
                        row[9],
                        row[10],
                        row[11]));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        Map.Entry<String, Long> fechaConMasVentas = pedidos.stream()
                .collect(Collectors.groupingBy(PizzaPedido::getOrderDate, Collectors.summingLong(pedido -> Math.round(pedido.getQuantity()))))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .orElseThrow(); // Lanza una excepción si no hay pedidos

        // Obtener la fecha con menos ventas y el número total de pizzas vendidas
        Map.Entry<String, Long> fechaConMenosVentas = pedidos.stream()
                .collect(Collectors.groupingBy(PizzaPedido::getOrderDate, Collectors.summingLong(pedido -> Math.round(pedido.getQuantity()))))
                .entrySet().stream()
                .min(Map.Entry.comparingByValue())
                .orElseThrow(); // Lanza una excepción si no hay pedidos

        // Obtener las fechas y cantidades de ventas
        String fechaMasVentas = fechaConMasVentas.getKey();
        long cantidadPizzasMasVendidas = fechaConMasVentas.getValue();
        String fechaMenosVentas = fechaConMenosVentas.getKey();
        long cantidadPizzasMenosVendidas = fechaConMenosVentas.getValue();

        // Mostrar los resultados
        System.out.println("Fecha con más ventas: " + fechaMasVentas);
        System.out.println("Cantidad de pizzas vendidas en esa fecha: " + cantidadPizzasMasVendidas);
        System.out.println("Fecha con menos ventas: " + fechaMenosVentas);
        System.out.println("Cantidad de pizzas vendidas en esa fecha: " + cantidadPizzasMenosVendidas);
    }
    }
