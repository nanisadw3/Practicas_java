public abstract class Bebida {
    protected int temperatura = 13; // Se recomienda protected o private

    public abstract String mostrarDescripcion(); // Método abstracto correctamente declarado
    public void calentar(int temperatura) {
        this.temperatura += temperatura;
    }
}
