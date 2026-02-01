public class Transaccion {
    private double precio;
    private String otroUsuario;
    private int puntuacion;
    private String comentario;
    private String fecha;

    public Transaccion(double precio, String otroUsuario, int puntuacion, String comentario, String fecha) {
        this.precio = precio;
        this.otroUsuario = otroUsuario;
        this.puntuacion = puntuacion;
        this.comentario = comentario;
        this.fecha = fecha;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getOtroUsuario() {
        return otroUsuario;
    }

    public void setOtroUsuario(String otroUsuario) {
        this.otroUsuario = otroUsuario;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Fecha: " + fecha + " | Precio: " + precio + " | Usuario implicado: " + otroUsuario +
                "\nCalificación: " + puntuacion + "/10 | Comentario: " + comentario;
    }
}
