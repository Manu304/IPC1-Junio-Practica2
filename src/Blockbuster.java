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
    boolean[] estadoClientes, estadoPelis;
    //String[] nombreClientes, nombrePelis, categoriaPelis;
    //int[][] peliculasPrestadas, idTelefonoClientes, idAnioPelis;
    //int[] telefonoClientes, idClientes, idPelis, anioPelis;
    //boolean[] clientePresta, peliDisponible;

    public Blockbuster() {
        final int CANTIDAD_TOTAL = 3; //CAMBIAR A 30 LA CANTIDAD
        clientes = new String [CANTIDAD_TOTAL][3];
        estadoClientes = new boolean [CANTIDAD_TOTAL];
        peliculas = new String [CANTIDAD_TOTAL][4];
        estadoPelis = new boolean [CANTIDAD_TOTAL];
        prestamoPeliculas = new int [CANTIDAD_TOTAL][3];
        menu();
    }

    public boolean verificarDisponibilidad(boolean[] estados){
        boolean disponible = false;
        int contador = 0;
        while ((contador < estados.length) && (estados[contador] == true)) {
            contador++;
        }
        if (contador > 0) {
            disponible = true;
        }
        return disponible;
    }

    //METODOS DE AQUi AL FINAL FUNCIONANDO AL 100
    public String [][] ordenarNumerico(String[][] datos, int columna, boolean[] estados){
        if (datosNetos(datos) > 1) {
            for (int i = 0; i < datos.length - 1; i++) {
                for (int j = 0; j < datos.length - 1 - i; j++) {
                    int primero = Integer.valueOf(datos[j+1][columna]);
                    int segundo = Integer.valueOf(datos[j][columna]);
    
                    if (segundo > primero) {
                        String[] filaTemp = datos[j];
                        datos[j] = datos[j+1];
                        datos[j+1] = filaTemp;
    
                        boolean estadoAux = estados[j];
                        estados[j] = estados[j+1];
                        estados[j+1] = estadoAux;
                    }
                }
            }
        }

        return datos;
    }

    public String [][] ordenarAlfabetico(String[][] datos, int columna, boolean[] estados){
        if (datosNetos(datos) > 1) {
            for (int i = 0; i < datos.length - 1; i++) {
                for (int j = 0; j < datos.length - 1 - i; j++) {
                    String primero = datos[j+1][columna].toLowerCase();
                    String segundo = datos[j][columna].toLowerCase();
    
                    if (primero.compareTo(segundo) < 0) {
                        String[] filaTemp = datos[j];
                        datos[j] = datos[j+1];
                        datos[j+1] = filaTemp;
                        
                        boolean estadoAux = estados[j];
                        estados[j] = estados[j+1];
                        estados[j+1] = estadoAux;
                    }
                }
            }
        } else {
            
        }

        return datos;
    }
    public int[][] agregarPrestamo(int[][] arreglo){
        int posicion = 0;
        while ((posicion < arreglo.length) && (arreglo[posicion][0] != 0)) {
            posicion ++;
        }
        if (posicion < arreglo.length) {
            mostrarClientes(clientes, false, true);
            int clientePesta = pedirID(clientes, true, "el ID del cliente que prestara");
            mostrarPelis(peliculas, false, true);
            int peliPrestada = pedirID(peliculas, true, "el ID de la pelicula que prestara");
            int diasPrestado = pedirNumero("la cantidad de dias que prestara la pelicula");
            System.out.println("El cliente: " + obtenerDatoArreglo(clientes, 1, clientePesta) + " , prestara la pelicula: "
                                + obtenerDatoArreglo(peliculas, 1, peliPrestada) + " por " + diasPrestado + " dias.");
            System.out.println("Esta seguro de que quiere realizar el prestamo? \n1. Si \n2. No");
            int opcion = pedirNumero("una opcion");
            if (opcion == 1) {
                arreglo[posicion][0] = clientePesta;
                arreglo[posicion][1] = peliPrestada;
                arreglo[posicion][2] = diasPrestado;

                estadoClientes[posicion] = false;
                estadoPelis[posicion] = false;
            }else{
                System.out.println("\nVen a prestar una peli cuando quieras");
            }
            
        } else {
            System.out.println("Lo sentimos, no se pueden realizar mas prestamos :("); 
        }
        return arreglo;

    }
    public String obtenerDatoArreglo(String[][] datos, int columna, int id){
        boolean encontrado = buscarID(id, datos, 0);
        int indice = posicionDatoID(id, datos);
        String datoObtenido = "";
        if (encontrado == true) {
            datoObtenido = datos[indice][columna];
        } else {
            
        }
        return datoObtenido;
    }
    //ENTRADO A BLOQUE DE CODIGO NUEVO
    public String[][] agregarPeli(String[][] arreglo){
        int posicion = 0;
        
        while ((posicion < arreglo.length) && (arreglo[posicion][0] != null)) {
            posicion ++;
        }
        if (posicion < arreglo.length) {
            arreglo[posicion][0] = Integer.toString(pedirID(arreglo, false, "el ID de la pelicula"));
            arreglo[posicion][1] = pedirString("el nombre de la pelicula");
            arreglo[posicion][2] = Integer.toString(pedirNumero("el anio de la pelicula"));
            arreglo[posicion][3] = pedirString("una categoria para la pelicula").toLowerCase();
            estadoPelis[posicion] = true;
            
        } else {
            System.out.println("Lo sentimos, no se pueden ingresar mas peliculas :("); 
        }
        return arreglo;
    }

    public void mostrarPelis(String [][] arreglo, boolean todas, boolean estado){
        int posicion = 0;
        if (arreglo[0][0] == null) {
            System.out.println("No hay nada para mostrar por ahora");
        } else {
            while ((posicion < arreglo.length) && (arreglo[posicion][0] != null)) {
                posicion ++;
            }
            
            for (int i = 0; i < posicion; i++) {
                if (todas == true) {
                imprimirPeli(arreglo, i);
                    
                } else{
                    if (estadoPelis[i] == estado) {
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
        System.out.println("Estado: " + imprimirEstado(estadoPelis[fila]));

    }
    public String imprimirEstado(boolean estado){
        String estadoEscrito;
        if (estado == true) {
            estadoEscrito = "Disponible";
        } else {
            estadoEscrito = "No disponible";
        }
        return estadoEscrito;
    }

    public int datosNetos(String[][] datos){
        int contador = 0;
        while ((contador < datos.length) && (datos[contador][0] != null)) {
            contador++;
        }
        return contador;
    }

    public int pedirID(String [][] datos, boolean existe, String mensaje){
        int idPedido;
        boolean valido = existe;

        if (datos[0][0] != null) {
            do {
                idPedido = pedirNumero(mensaje);
                valido = buscarID(idPedido, datos, 0);
                if (valido != existe) {
                    if (existe == false) {
                        System.out.println("\nEse ID ya se encuentra en uso");
                    } else {
                        System.out.println("\nEse ID no se encuentra registrado");
                    }
                }
                
            } while (valido != existe);
        }else{
            idPedido = pedirNumero(mensaje);
        }

        return idPedido;
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
    public int posicionDatoID(int id, String [][] datos){
        int contador = 0;
        if (datos[0][0] != null) {
            for (int i = 0; i < datosNetos(datos); i++) {
                if (Integer.valueOf(datos[i][0]) != id) {
                    contador++;
                }
            }
        }
        return contador;
    }
    //FINAL DEL CODIGO NUEVO

    public String[][] agregarCliente(String[][] arreglo){
        int posicion = 0;
        
        while ((posicion < arreglo.length) && (arreglo[posicion][0] != null)) {
            posicion ++;
        }
        if (posicion < arreglo.length) {
            arreglo[posicion][0] = Integer.toString(pedirID(arreglo, false, "el ID del cliente"));
            arreglo[posicion][1] = pedirString("el nombre del cliente");
            arreglo[posicion][2] = Integer.toString(pedirNumero("el telefono del cliente"));
            estadoClientes[posicion] = true;

        } else {
            System.out.println("Lo sentimos, no se pueden ingresar mas clientes :(");
            
        }
        return arreglo;
    }

    public void mostrarClientes(String [][] arreglo, boolean todos, boolean estado){
        int posicion = 0;
        if (arreglo[0][0] == null) {
            System.out.println("No hay nada para mostrar por ahora");
        } else {
            while ((posicion < arreglo.length) && (arreglo[posicion][0] != null)) {
                posicion ++;
            }
            for (int i = 0; i < posicion; i++) {
                if (todos == true) {
                    imprimirCliente(arreglo, i);
                } else {
                    if (estadoClientes[i] == estado) {
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
                        boolean disponiblePelis = verificarDisponibilidad(estadoPelis);
                        boolean disponibleClientes = verificarDisponibilidad(estadoClientes);
                        if ((disponiblePelis == true) && (disponibleClientes == true)) {
                            prestamoPeliculas = agregarPrestamo(prestamoPeliculas);
                        }else{
                            System.out.println("No hay peliculas o clientes disponibles");
                        }
                        
                        break;
                    case 2:
                        System.out.println("Quiere devolver una peli");
                        break;
                    case 3:
                        System.out.println("\n-----LISTA DE PELICULAS REGISTRADAS-------\n");
                        mostrarPelis(peliculas, true, true);
                        
                        break;
                    case 4:
                        System.out.println("\n----------AGREGANDO UNA PELICULA-----------\n");
                        peliculas = agregarPeli(peliculas);
                        
                        break;
                    case 5:
                        System.out.println("\n-----LISTA DE PELICULAS ORDENADAS (A-Z)-----");
                        String [][] ordenados = ordenarAlfabetico(peliculas, 1, estadoPelis);
                        mostrarPelis(ordenados, true, true);
                        break;
                    case 6:
                        System.out.println("\n----------AGREGANDO UN CLIENTE-----------\n");
                        agregarCliente(clientes);
                        break;
                    case 7:
                        System.out.println("\n-------LISTA DE CLIENTES REGISTRADOS-------");
                        mostrarClientes(clientes, true, true);
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