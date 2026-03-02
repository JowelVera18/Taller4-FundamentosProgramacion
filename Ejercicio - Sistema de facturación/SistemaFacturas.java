// Sistema de gestión de facturas
// Unidad 4 - Manejo de archivos y librería gráfica
// Autor: Joel Antonio Vera Ronquillo

import javax.swing.*;
import java.io.*;

public class SistemaFacturas {

    static String archivo = "facturas.txt";

    public static void main(String[] args) {

        int opcion;

        do {
            String menu = """
                    MENU FACTURAS
                    1. Registro de facturas
                    2. Consulta específica de una factura
                    3. Mostrar todas las facturas
                    4. Salir
                    """;

            opcion = Integer.parseInt(JOptionPane.showInputDialog(menu));

            switch (opcion) {
                case 1 -> registrarFactura();
                case 2 -> consultarFactura();
                case 3 -> mostrarFacturas();
                case 4 -> JOptionPane.showMessageDialog(null, "Saliendo del sistema...");
                default -> JOptionPane.showMessageDialog(null, "Opción inválida");
            }

        } while (opcion != 4);
    }

    // REGISTRAR FACTURA
    public static void registrarFactura() {
        try {
            String numero = JOptionPane.showInputDialog("Número de factura:");
            String cliente = JOptionPane.showInputDialog("Nombre del cliente:");
            String monto = JOptionPane.showInputDialog("Monto:");

            FileWriter fw = new FileWriter(archivo, true);
            fw.write(numero + "," + cliente + "," + monto + "\n");
            fw.close();

            JOptionPane.showMessageDialog(null, "Factura registrada correctamente");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al registrar factura");
        }
    }

    // CONSULTAR FACTURA
    public static void consultarFactura() {
        String buscar = JOptionPane.showInputDialog("Ingrese número de factura:");

        try {
            BufferedReader br = new BufferedReader(new FileReader(archivo));
            String linea;
            boolean encontrada = false;

            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");

                if (datos[0].equals(buscar)) {
                    JOptionPane.showMessageDialog(null,
                            "Factura encontrada\nCliente: " + datos[1]
                                    + "\nMonto: $" + datos[2]);
                    encontrada = true;
                    break;
                }
            }

            br.close();

            if (!encontrada) {
                JOptionPane.showMessageDialog(null,
                        "Factura no se encuentra registrada");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al leer archivo");
        }
    }

    // MOSTRAR TODAS
    public static void mostrarFacturas() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(archivo));
            String linea;
            String contenido = "FACTURAS REGISTRADAS\n\n";

            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                contenido += "Factura: " + datos[0]
                        + " | Cliente: " + datos[1]
                        + " | Monto: $" + datos[2] + "\n";
            }


            br.close();

            JOptionPane.showMessageDialog(null, contenido);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No existen facturas registradas");
        }
    }
}