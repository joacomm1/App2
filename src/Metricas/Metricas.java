package src.Metricas;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import src.PizzaPedido.PizzaPedido;

public class Metricas {
    private final List<PizzaPedido> pedidos;
    public Metricas(List<PizzaPedido> pedidos) {
        this.pedidos = pedidos;
    }
    public void ejecutar(String[] args) {
        List<String> metricas = Arrays.asList(args);

        for (String metrica : metricas) {
            switch (metrica) {
                case "pms":
                    pms(pedidos);
                    break;
                case "pls":
                    pls(pedidos);
                    break;
                case "dms":
                    dmsp(pedidos);
                    break;
                case "dls":
                    dls(pedidos);
                    break;
                case "dmsp":
                    dmsp(pedidos);
                    break;
                case "dlsp":
                    dlsp(pedidos);
                    break;
                case "apo":
                    apo(pedidos);
                    break;
                case "apd":
                    apd(pedidos);
                    break;
                case "ims":
                    ims(pedidos);
                    break;
                case "hp":
                    hp(pedidos);
                    break;
                default:
                    break;
            }
        }
    }

    private void pms(List<PizzaPedido> pedidos) {
        String pizzaMasVendida = pedidos.stream()
                .collect(Collectors.groupingBy(PizzaPedido::getPizzaName, Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("No hay pedidos");

        System.out.println("La pizza más vendida es: " + pizzaMasVendida);
    } 

    private void pls(List<PizzaPedido> pedidos) {
        String pizzaMenosVendida = pedidos.stream()
                .collect(Collectors.groupingBy(PizzaPedido::getPizzaName, Collectors.counting()))
                .entrySet().stream()
                .min(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("No hay pedidos");
        
        System.out.println("La pizza menos vendida es: " + pizzaMenosVendida);
    }
    private void dmsp(List<PizzaPedido> pedidos){
        Map.Entry<String, Long> fechaConMasVentas = pedidos.stream()
                .collect(Collectors.groupingBy(PizzaPedido::getOrderDate, Collectors.summingLong(pedido -> Math.round(pedido.getQuantity()))))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .orElseThrow(); // Lanza una excepción si no hay pedidos
        String fechaMasVentas = fechaConMasVentas.getKey();
        long cantidadPizzasMasVendidas = fechaConMasVentas.getValue();
        // Mostrar los resultados
        System.out.println("Fecha con más ventas: " + fechaMasVentas);
        System.out.println("Cantidad de pizzas vendidas en esa fecha: " + cantidadPizzasMasVendidas);
    }

    private void dlsp(List<PizzaPedido> pedidos){
        Map.Entry<String, Long> fechaConMenosVentas = pedidos.stream()
                .collect(Collectors.groupingBy(PizzaPedido::getOrderDate, Collectors.summingLong(pedido -> Math.round(pedido.getQuantity()))))
                .entrySet().stream()
                .min(Map.Entry.comparingByValue())
                .orElseThrow(); // Lanza una excepción si no hay pedidos
        String fechaMenosVentas = fechaConMenosVentas.getKey();
        long cantidadPizzasMenosVendidas = fechaConMenosVentas.getValue();
        System.out.println("Fecha con menos ventas: " + fechaMenosVentas + "Cantidad de pizzas vendidas en esa fecha: " + cantidadPizzasMenosVendidas);
    }

    private void hp(List<PizzaPedido> pedidos){
        Map<String, Double> pizzasPorCategoria = pedidos.stream()
                .collect(Collectors.groupingBy(PizzaPedido::getPizzaCategory, Collectors.summingDouble(PizzaPedido::getQuantity)));

        // Mostrar el resultado
        pizzasPorCategoria.forEach((categoria, cantidad) -> System.out.println("Categoría: " + categoria + ", Cantidad de pizzas vendidas: " + cantidad));

    }

   private void apo(List<PizzaPedido> pedidos){ double promedioPizzasPorOrden = pedidos.stream()
                .collect(Collectors.groupingBy(PizzaPedido::getOrderId, Collectors.summingDouble(PizzaPedido::getQuantity))) // Agrupar por order_id y sumar la cantidad de pizzas
                .values().stream()
                .mapToDouble(Double::doubleValue) // Convertir la cantidad total de pizzas a double
                .average() // Calcular el promedio
                .orElse(0); // Valor predeterminado si no hay pedidos

        // Mostrar el resultado
        System.out.println("Promedio de pizzas vendidas por orden: " + promedioPizzasPorOrden);}

        private void apd(List<PizzaPedido> pedidos){
            double promedioPizzasPorDia = pedidos.stream()
            .collect(Collectors.groupingBy(PizzaPedido::getOrderDate, Collectors.summingDouble(PizzaPedido::getQuantity))) // Agrupar por fecha y sumar la cantidad de pizzas
            .values().stream()
            .mapToDouble(Double::doubleValue) // Convertir la cantidad total de pizzas a double
            .average() // Calcular el promedio
            .orElse(0); // Valor predeterminado si no hay pedidos

    // Mostrar el resultado
    System.out.println("Promedio de pizzas vendidas por día: " + promedioPizzasPorDia);}

    private void ims(List<PizzaPedido> pedidos){
        Map<String, Long> ingredientesVendidos = pedidos.stream()
        .flatMap(pedido -> Arrays.stream(pedido.getPizzaIngredients().split(",\\s*")))
        .collect(Collectors.groupingBy(ingrediente -> ingrediente.trim(), Collectors.counting()));

// Encontrar el ingrediente con la mayor cantidad de ventas
        Map.Entry<String, Long> ingredienteMasVendido = ingredientesVendidos.entrySet().stream()
        .max(Map.Entry.comparingByValue())
        .orElseThrow(); // Lanza una excepción si no hay ingredientes

        System.out.println("Ingrediente más vendido: " + ingredienteMasVendido.getKey());}
        



        private void dls(List<PizzaPedido> pedidos){
            Map<String, Double> ventasPorFecha = pedidos.stream()
                    .collect(Collectors.groupingBy(PizzaPedido::getOrderDate,
                            Collectors.summingDouble(pedido -> pedido.getTotalPrice())));
            
            // Encontrar la fecha con la menor venta en términos de dinero
            Map.Entry<String, Double> fechaConMenorVenta = ventasPorFecha.entrySet().stream()
                    .min(Map.Entry.comparingByValue())
                    .orElseThrow(); // Lanza una excepción si no hay ventas
            
            // Mostrar el resultado
            System.out.println("Fecha con menor venta en términos de dinero: " + fechaConMenorVenta.getKey() +
                    ", Total de ventas en esa fecha: $" + fechaConMenorVenta.getValue());}



    // Otros métodos para las métricas...
}