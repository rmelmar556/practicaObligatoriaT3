import java.util.Scanner;

public class FernanPop {
    private static Usuario[] usuarios = new Usuario[2];
    private static Scanner sc = new Scanner(System.in);
    private static Usuario usuarioLogueado = null;

    public static void main(String[] args) {
        inicializarDatos();

        System.out.println("Bienvenido al programa FernanPop");
        System.out.println("Compra y vende tus artículos en nuestro Centro");
        System.out.println("==============================================");

        boolean salir = false;
        while (!salir) {
            if (usuarioLogueado == null) {
                login();
            } else {
                mostrarMenu();
                int opcion = -1;
                try {
                    opcion = Integer.parseInt(sc.nextLine());
                } catch (NumberFormatException e) {
                    opcion = -1;
                }
                salir = ejecutarOpcion(opcion);
            }
        }
        System.out.println("Gracias por usar FernanPop.");
    }

    private static void inicializarDatos() {
        usuarios[0] = new Usuario("carlos.barroso@fernando3martos.com", "1234", "Carlos", "Barroso", "Martos");
        usuarios[1] = new Usuario("maria.garcia@fernando3martos.com", "0000", "Maria", "Garcia", "Jaen");

        usuarios[0].ponerEnVenta(new Producto("Libro Java", 15.0, "Libro programación tema 3"));
    }

    private static void login() {
        System.out.print("Introduzca email: ");
        String email = sc.nextLine();
        System.out.print("Introduzca clave: ");
        String clave = sc.nextLine();

        boolean encontrado = false;
        for (Usuario u : usuarios) {
            if (u.getEmail().equals(email) && u.getClave().equals(clave)) {
                usuarioLogueado = u;
                System.out.println("Bienvenido " + u.getNombre());
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            System.out.println("Credenciales incorrectas.");
        }
    }

    private static void mostrarMenu() {
        System.out.println("\n*********************************");
        System.out.println("\tMenú de usuario");
        System.out.println("1. Mostrar mi perfil de usuario");
        System.out.println("2. Cambiar mis datos personales");
        System.out.println("3. Ver mis productos en venta");
        System.out.println("4. Poner a la venta un nuevo producto");
        System.out.println("5. Ver todos los productos en venta de la aplicación");
        System.out.println("6. Ver mi histórico de ventas");
        System.out.println("7. Ver mi histórico de compras");
        System.out.println("8. Cerrar la venta de un producto o quitarlo");
        System.out.println("9. Salir");
        System.out.print("Introduzca la opción deseada: ");
    }

    private static boolean ejecutarOpcion(int op) {
        switch (op) {
            case 1:
                System.out.println(usuarioLogueado.toString());
                break;
            case 2:
                cambiarDatos();
                break;
            case 3:
                mostrarMisProductos();
                break;
            case 4:
                nuevoProducto();
                break;
            case 5:
                mostrarTodosLosProductos();
                break;
            case 6:
                if (usuarioLogueado.getHistorialVenta() != null) {
                    System.out.println(usuarioLogueado.getHistorialVenta());
                } else {
                    System.out.println("No tienes ventas registradas.");
                }
                break;
            case 7:
                if (usuarioLogueado.getHistorialCompra() != null) {
                    System.out.println(usuarioLogueado.getHistorialCompra());
                } else {
                    System.out.println("No tienes compras registradas.");
                }
                break;
            case 8:
                cerrarOQuitarVenta();
                break;
            case 9:
                usuarioLogueado = null;
                System.out.println("Sesión cerrada.");
                return false;
            default:
                System.out.println("Opción no válida.");
        }
        return false;
    }

    private static void cambiarDatos() {
        System.out.print("Nuevo nombre (" + usuarioLogueado.getNombre() + "): ");
        String n = sc.nextLine();
        if (!n.isEmpty()) usuarioLogueado.setNombre(n);

        System.out.print("Nuevo apellidos (" + usuarioLogueado.getApellidos() + "): ");
        String a = sc.nextLine();
        if (!a.isEmpty()) usuarioLogueado.setApellidos(a);

        System.out.print("Nueva ciudad (" + usuarioLogueado.getCiudad() + "): ");
        String c = sc.nextLine();
        if (!c.isEmpty()) usuarioLogueado.setCiudad(c);

        System.out.println("Datos actualizados.");
    }

    private static void mostrarMisProductos() {
        Producto[] misProds = usuarioLogueado.getProductosEnVenta();
        boolean vacio = true;
        for (int i = 0; i < misProds.length; i++) {
            if (misProds[i] != null) {
                System.out.println((i + 1) + ". " + misProds[i].toString());
                vacio = false;
            }
        }
        if (vacio) System.out.println("No tienes productos en venta.");
    }

    private static void nuevoProducto() {
        System.out.print("Nombre del producto: ");
        String nom = sc.nextLine();
        System.out.print("Precio: ");
        double pre = Double.parseDouble(sc.nextLine());
        System.out.print("Descripción: ");
        String desc = sc.nextLine();

        Producto p = new Producto(nom, pre, desc);
        if (usuarioLogueado.ponerEnVenta(p)) {
            System.out.println("Producto añadido correctamente.");
        } else {
            System.out.println("Error: Has alcanzado el límite de 2 productos en venta.");
        }
    }

    private static void mostrarTodosLosProductos() {
        for (Usuario u : usuarios) {
            Producto[] prods = u.getProductosEnVenta();
            for (Producto p : prods) {
                if (p != null) {
                    System.out.println("Vendedor: " + u.getNombre() + " | " + p.toString());
                }
            }
        }
    }

    private static void cerrarOQuitarVenta() {
        mostrarMisProductos();
        System.out.print("Elige el número del producto (1 o 2) para gestionar: ");
        int indice = Integer.parseInt(sc.nextLine()) - 1;

        Producto p = usuarioLogueado.getProducto(indice);
        if (p == null) {
            System.out.println("No existe ese producto.");
            return;
        }

        System.out.println("¿Qué deseas hacer? (1: Quitar de la venta, 2: Marcar como vendido)");
        int accion = Integer.parseInt(sc.nextLine());

        if (accion == 1) {
            usuarioLogueado.quitarDeVenta(indice);
            System.out.println("Producto retirado.");
        } else if (accion == 2) {
            gestionarVenta(p, indice);
        }
    }

    private static void gestionarVenta(Producto p, int indiceProducto) {
        if (usuarioLogueado.getHistorialVenta() != null) {
            System.out.println("Error: Ya tienes 1 venta en tu histórico (límite alcanzado).");
            return;
        }

        System.out.print("Introduce el email del comprador: ");
        String emailComprador = sc.nextLine();

        Usuario comprador = null;
        for (Usuario u : usuarios) {
            if (u.getEmail().equals(emailComprador) && !u.getEmail().equals(usuarioLogueado.getEmail())) {
                comprador = u;
                break;
            }
        }

        if (comprador == null) {
            System.out.println("Usuario comprador no encontrado o eres tú mismo.");
            return;
        }

        if (comprador.getHistorialCompra() != null) {
            System.out.println("El comprador ya tiene su histórico lleno. No se puede realizar.");
            return;
        }

        System.out.print("Precio final acordado: ");
        double precioFinal = Double.parseDouble(sc.nextLine());
        System.out.print("Puntuación (0-10): ");
        int puntos = Integer.parseInt(sc.nextLine());
        System.out.print("Comentario: ");
        String com = sc.nextLine();
        System.out.print("Fecha (dd/mm/yyyy): ");
        String fecha = sc.nextLine();

        Transaccion tVenta = new Transaccion(precioFinal, comprador.getEmail(), puntos, com, fecha);
        Transaccion tCompra = new Transaccion(precioFinal, usuarioLogueado.getEmail(), puntos, com, fecha);

        usuarioLogueado.registrarVenta(tVenta);
        comprador.registrarCompra(tCompra);
        usuarioLogueado.quitarDeVenta(indiceProducto);

        System.out.println("Venta cerrada y registrada en ambos históricos.");
    }
}
