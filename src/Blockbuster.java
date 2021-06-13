package src;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Blockbuster {
    public static void main(String[] args) {
        Blockbuster programa = new Blockbuster();
    }

    Scanner scanner = new Scanner(System.in);
    final String[] CATEGORIAS = {"aventura", "accion", "terror", "drama", "comedia", "otro"};
    String[][] clientes, peliculas; //[fila][columna]
    int [][] prestamoPeliculas;
    //String[] nombreClientes, nombrePelis, categoriaPelis;
    //int[][] peliculasPrestadas, idTelefonoClientes, idAnioPelis;
    //int[] telefonoClientes, idClientes, idPelis, anioPelis;
    //boolean[] clientePresta, peliDisponible;

    public Blockbuster() {
        final int CANTIDAD_TOTAL = 3; //CAMBIAR A 30 LA CANTIDAD
        clientes = new String [CANTIDAD_TOTAL][4];
        peliculas = new String [CANTIDAD_TOTAL][5];

        menu();
        

    }

    //METODOS DE AQUi AL FINAL FUNCIONANDO AL 100
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
    //ENTRADO A BLOQUE DE CODIGO NUEVO
    public String[][] agregarPeli(String[][] arreglo){
        int posicion = 0;
        
        while ((posicion < arreglo.length) && (arreglo[posicion][0] != null)) {
            posicion ++;
        }
        if (posicion < arreglo.length) {
            arreglo[posicion][0] = Integer.toString(pedirNumero("el ID de la pelicula"));
            arreglo[posicion][1] = pedirString("el nombre de la pelicula");
            arreglo[posicion][2] = Integer.toString(pedirNumero("el anio de la pelicula"));
            arreglo[posicion][3] = pedirString("una categoria para la pelicula").toLowerCase();
            arreglo[posicion][4] = "disponible";
            
        } else {
            System.out.println("Lo sentimos, no se pueden ingresar mas peliculas :("); 
        }
        return arreglo;
    }

    public void mostrarPelis(String [][] arreglo, boolean todas, String estado){
        int posicion = 0;
        if (arreglo[0][0] == null) {
            System.out.println("No hay nada para mostrar por ahora");
        } else {
            while ((posicion < arreglo.length) && (arreglo[posicion][0] != null)) {
                posicion ++;
            }
            
            for (int i = 0; i < posicion; i++) {
                if (todas) {
                imprimirPeli(arreglo, i);
                    
                } else{
                    if (arreglo[i][4].equals(estado)) {
                        imprimirPeli(arreglo, i);
                    }
                }
            }
        }
    }
    public void imprimirPeli(String[][] arreglo, int fila){
        System.out.println("\nID: " + arreglo[fila][0]);
        System.out.println("Nombre: " + arreglo[fila][1]);
        System.out.println("Anio: " + arreglo[fila][2]);
        System.out.println("Categoria: " + arreglo[fila][3]);
        System.out.println("Estado: " + arreglo[fila][4]);

    }

    public int datosNetos(String[][] datos){
        int contador = 0;
        while ((contador < datos.length) && (datos[contador][0] != null)) {
            contador++;
        }
        return contador;
    }
    public void prestarPelis(){
        mostrarClientes(clientes, false, "disponible");
        boolean valido = false;
        while (valido) {
            int idCliente = pedirNumero("el id del clinete que prestara");
            valido = buscarID(idCliente, clientes, 0);
            if (valido) {
                System.out.println("\nEl usuario no existe");
            }
        }
        mostrarPelis(peliculas, false, "disponible");
    }

    public boolean buscarID(int id, String[][] datos, int columna){
        boolean correcto = false;
        if (datos[0][0] != null) {
            for (int i = 0; i < datosNetos(datos); i++) {
                if(Integer.valueOf(datos[i][columna]) == id){
                    correcto = true;
                }
            }
        }
        return correcto;
    }





    //FINAL DEL CODIGO NUEVO

    public String[][] agregarCliente(String[][] arreglo){
        int posicion = 0;
        
        while ((posicion < arreglo.length) && (arreglo[posicion][0] != null)) {
            posicion ++;
        }
        if (posicion < arreglo.length) {
            arreglo[posicion][0] = Integer.toString(pedirNumero("el ID del cliente"));
            arreglo[posicion][1] = pedirString("el nombre del cliente");
            arreglo[posicion][2] = Integer.toString(pedirNumero("el telefono del cliente"));
            arreglo[posicion][3] = "disponible";

        } else {
            System.out.println("Lo sentimos, no se pueden ingresar mas clientes :(");
            
        }
        return arreglo;
    }

    public void mostrarClientes(String [][] arreglo, boolean todos, String estado){
        int posicion = 0;
        if (arreglo[0][0] == null) {
            System.out.println("No hay nada para mostrar por ahora");
        } else {
            while ((posicion < arreglo.length) && (arreglo[posicion][0] != null)) {
                posicion ++;
            }
            for (int i = 0; i < posicion; i++) {
                if (todos) {
                    imprimirCliente(arreglo, i);
                } else {
                    if (arreglo[i][3].equals(estado)) {
                        imprimirCliente(arreglo, i);
                    } 
                }
            }
        }
    }

    public void imprimirCliente(String[][] arreglo, int fila){
        System.out.println("\nID: " + arreglo[fila][0]);
        System.out.println("Nombre: " + arreglo[fila][1]);
        System.out.println("Telefono: " + arreglo[fila][2]);
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
            System.out.println("\n---------MENU PRINCIPAL---------");
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
                        System.out.println("\n-----LISTA DE PELICULAS REGISTRADAS-------\n");
                        mostrarPelis(peliculas, true, "");
                        
                        break;
                    case 4:
                        System.out.println("\n----------AGREGANDO UNA PELICULA-----------\n");
                        peliculas = agregarPeli(peliculas);
                        
                        break;
                    case 5:
                        System.out.println("\n-----LISTA DE PELICULAS ORDENADAS (A-Z)-----");
                        String [][] ordenados = ordenarAlfabetico(peliculas, 1);
                        mostrarPelis(ordenados, true, "");
                        break;
                    case 6:
                        System.out.println("\n----------AGREGANDO UN CLIENTE-----------\n");
                        agregarCliente(clientes);
                        break;
                    case 7:
                        System.out.println("\n-------LISTA DE CLIENTES REGISTRADOS-------");
                        mostrarClientes(clientes, true, "");
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