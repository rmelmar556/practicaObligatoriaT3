1. Clase FernanPop 

Es la clase que contiene la lógica de ejecución y el punto de entrada del programa.

Atributos:

- usuarios: Usuario: Array con capacidad para 2 usuarios.

- sc: Scanner: Para la entrada de datos por consola.

- usuarioLogueado: Usuario: Almacena la sesión actual.

Métodos principales:

+ main(String[] args): Arranca la aplicación.

- inicializarDatos(): Crea los usuarios y productos iniciales.

- gestionarVenta(p: Producto, indice: int): Coordina la transferencia de un producto entre vendedor y comprador.

2. Clase Usuario

Representa a los miembros de la plataforma y sus límites de almacenamiento.

Atributos:

- productosEnVenta: Producto 2: Limitado a 2 productos por usuario.

- historialVenta: Transaccion: Solo permite guardar 1 venta en el histórico.

- historialCompra: Transaccion: Solo permite guardar 1 compra en el histórico.

Métodos destacados:

+ ponerEnVenta(p: Producto): boolean: Valida si hay espacio en el array antes de añadir.

+ registrarVenta(t: Transaccion): boolean: Valida que el histórico esté vacío antes de guardar.

3. Clase Producto

Clase básica de datos para los artículos.

Atributos:

- nombre: String, - precio: double, - descripcion: String.

Métodos: Getters, Setters y toString() para visualizar el artículo.

4. Clase Transaccion

Se utiliza para registrar los detalles finales del intercambio una vez que el producto se marca como vendido.

Atributos:

- otroUsuario: String: Almacena el email del comprador o vendedor.


- puntuación: int, - comentario: String, - fecha: String.
