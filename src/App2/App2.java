package src.App2;

import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import src.PizzaPedido.PizzaPedido;

import java.util.ArrayList;
import java.util.Arrays;

import src.Metricas.Metricas;

public class App2 {
    public static List<PizzaPedido> pedidos = new ArrayList<>();
    public static void main(String[] args) {
        String csvFile = args[0];
        BufferedReader reader = null;
        String line = "";

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
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        Metricas metricas = new Metricas();
        
        // Llamar al método ejecutar de Metricas y pasarle los argumentos de la línea de comandos
        metricas.ejecutar(args, pedidos);
    }
    }