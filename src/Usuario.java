public class Usuario {
    private String email;
    private String clave;
    private String nombre;
    private String apellidos;
    private String ciudad;
    private Producto[] productosEnVenta;
    private Transaccion historialVenta;
    private Transaccion historialCompra;

    public Usuario(String email, String clave, String nombre, String apellidos, String ciudad) {
        this.email = email;
        this.clave = clave;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.ciudad = ciudad;
        this.productosEnVenta = new Producto[2];
        this.historialVenta = null;
        this.historialCompra = null;
    }

    public boolean ponerEnVenta(Producto p) {
        for (int i = 0; i < productosEnVenta.length; i++) {
            if (productosEnVenta[i] == null) {
                productosEnVenta[i] = p;
                return true;
            }
        }
        return false;
    }

    public boolean quitarDeVenta(int indice) {
        if (indice >= 0 && indice < productosEnVenta.length && productosEnVenta[indice] != null) {
            productosEnVenta[indice] = null;
            return true;
        }
        return false;
    }

    public boolean registrarVenta(Transaccion t) {
        if (this.historialVenta == null) {
            this.historialVenta = t;
            return true;
        }
        return false;
    }

    public boolean registrarCompra(Transaccion t) {
        if (this.historialCompra == null) {
            this.historialCompra = t;
            return true;
        }
        return false;
    }

    public Producto getProducto(int indice) {
        if (indice >= 0 && indice < productosEnVenta.length) {
            return productosEnVenta[indice];
        }
        return null;
    }

    public String getEmail() {
        return email;
    }

    public String getClave() {
        return clave;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public Transaccion getHistorialVenta() {
        return historialVenta;
    }

    public Transaccion getHistorialCompra() {
        return historialCompra;
    }

    public Producto[] getProductosEnVenta() {
        return productosEnVenta;
    }

    @Override
    public String toString() {
        return "Perfil de " + nombre + " " + apellidos + "\nEmail: " + email + "\nUbicación: " + ciudad;
    }
}