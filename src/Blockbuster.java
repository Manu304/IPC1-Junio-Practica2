package src;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Blockbuster {
    public static void main(String[] args) {
        Blockbuster programa = new Blockbuster();
    }

    Scanner scanner = new Scanner(System.in);
    String[][] clientes, peliculas; //[fila][columna]
    int [][] prestamoPeliculas;
    //String[] nombreClientes, nombrePelis, categoriaPelis;
    //int[][] peliculasPrestadas, idTelefonoClientes, idAnioPelis;
    //int[] telefonoClientes, idClientes, idPelis, anioPelis;
    //boolean[] clientePresta, peliDisponible;

    public Blockbuster() {
        final int CANTIDAD_TOTAL = 3; //CAMBIAR A 30 LA CANTIDAD
        clientes = new String [CANTIDAD_TOTAL][3];
        menu();
        

    }

    //METODOS DE AQUÍ AL FINAL FUNCIONANDO AL 100
    public String [][] ordenarNumerico(String[][] datos, int columna){
        for (int i = 0; i < datos.length - 1; i++) {
            for (int j = 0; j < datos.length - 1 - i; j++) {
                int primero = Integer.valueOf(datos[j+1][columna]);
                int segundo = Integer.valueOf(datos[j][columna]);

                if (segundo > primero) {
                    String[] filaTemp = datos[j];
                    datos[j] = datos[j+1];
                    datos[j+1] = filaTemp;
                }
            }
        }
        return datos;
    }
    
    public String [][] ordenarAlfabetico(String[][] datos, int columna){
        for (int i = 0; i < datos.length - 1; i++) {
            for (int j = 0; j < datos.length - 1 - i; j++) {
                String primero = datos[j+1][columna].toLowerCase();
                String segundo = datos[j][columna].toLowerCase();

                if (primero.compareTo(segundo) < 0) {
                    String[] filaTemp = datos[j];
                    datos[j] = datos[j+1];
                    datos[j+1] = filaTemp;
                }
            }
        }
        return datos;
    }

    public String[][] agregarCliente(String[][] arreglo){
        int posicion = 0;
        
        while ((posicion < arreglo.length) && (arreglo[posicion][0] != null)) {
            posicion ++;
        }
        if (posicion < arreglo.length) {
            arreglo[posicion][0] = Integer.toString(pedirNumero("el ID del cliente"));
            arreglo[posicion][1] = pedirString("el nombre del cliente");
            arreglo[posicion][2] = Integer.toString(pedirNumero("el telefono del cliente"));
        } else {
            System.out.println("Lo sentimos, no se pueden ingresar mas clientes :(");
            
        }
        return arreglo;
    }
    public void mostrarClientes(String [][] arreglo){
        int posicion = 0;
        if (arreglo[0][0] == null) {
            System.out.println("No hay nada para mostrar por ahora");
        } else {
            while ((posicion < arreglo.length) && (arreglo[posicion][0] != null)) {
                posicion ++;
            }
            for (int i = 0; i < posicion; i++) {
                System.out.println("\nID: " + arreglo[i][0]);
                System.out.println("Nombre: " + arreglo[i][1]);
                System.out.println("Telefono: " + arreglo[i][2]);
            }
        }
    }

    

    public int pedirNumero(String solicitud) {
        int numero = 0;
        
        do {
            try {
                System.out.print("Ingrese " + " " + solicitud + ": ");
                int ingresado = scanner.nextInt();
                if (ingresado < 0) {
                    System.out.println("\nNo puede ingresar un valor negativo");
                }
                numero = ingresado;

            } catch (InputMismatchException e) {
                System.out.println("ERROR. Debes de ingresar un numero");
                scanner.nextLine();
                numero = -1;
            }
        } while (numero < 0);
        return numero;
    }

    public String pedirString(String solicitud) {
        System.out.print("Ingrese " + " " + solicitud + ": ");
        String palabra = scanner.nextLine();
        palabra = scanner.nextLine();
        return palabra;
    }

    public void menu() {
        boolean salir = false;
        int opcion = 0;
        while (!salir) {
            // mostrarle al usuario las opciones
            System.out.println("---------MENU PRINCIPAL---------");
            System.out.println("    \n1. Prestar pelicula" + "    \n2. Devolver pelicula" + "    \n3. Mostrar peliculas"
                    + "    \n4. Ingresar peliculas" + "    \n5. Ordenar peliculas" + "    \n6. Ingresar nuevos clientes"
                    + "    \n7. Mostrar clientes" + "    \n8. Reportes" + "    \n9. Salir\n");
            try {
                System.out.print("Ingrese una opcion: ");
                opcion = scanner.nextInt();
                switch (opcion) {
                    case 1:
                        System.out.println("Quiere prestar una peli");
                        break;
                    case 2:
                        System.out.println("Quiere devolver una peli");
                        break;
                    case 3:
                        System.out.println("Le muestra las pelis");
                        break;
                    case 4:
                        System.out.println("quiere ingresar pelis");
                        
                        break;
                    case 5:
                        System.out.println("Quiere ordenar las pelis");
                        String [][] ordenados = ordenarAlfabetico(clientes, 1);
                        mostrarClientes(ordenados);
                        break;
                    case 6:
                        System.out.println("Quire ingresar un nuevo cliente");
                        agregarCliente(clientes);
                        break;
                    case 7:
                        System.out.println("Le muestro clientes");
                        mostrarClientes(clientes);
                        break;
                    case 8:
                        System.err.println("Le muestro reportes");
                        break;
                    case 9:
                        salir = true;
                        System.out.println("Vuelva pronto :)");
                        break;
                    default:
                        System.out.println("Ups. Esa opción no existe.");
                        break;
                }

            } catch (InputMismatchException e) {
                System.out.println("\nERROR. Debes de ingresar un numero\n");
                scanner.nextLine();
            }
        }
    }

}