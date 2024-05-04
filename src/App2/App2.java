package src.App2;

import java.io.*;
import java.util.List;
import src.PizzaPedido.PizzaPedido;
import java.util.ArrayList;
import src.Metricas.Metricas;

public class App2 {
    public static List<PizzaPedido> pedidos = new ArrayList<>();
    public static void main(String[] args) {
        //Se selecciona el csv desde el terminal
        String csvFile = args[0];
        BufferedReader reader = null;
        String line = "";
        //Lee el csv y lo agrega a una lista llamada pedidos, utilizando el metodo de la clase PizzaPedido
        try {
            reader = new BufferedReader(new FileReader(csvFile));
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
        //excepciones si es que no logra leer el csv
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //Utiliza el constructor de la clase Metricas para crear un nuevo objeto dentro de la clase metricas
        Metricas metricas = new Metricas(pedidos);
        
        // Llamar al método ejecutar de Metricas y pasarle los argumentos de la línea de comandos
        metricas.ejecutar(args);
    }
    }