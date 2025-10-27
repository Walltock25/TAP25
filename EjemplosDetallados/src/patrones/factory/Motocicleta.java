package patrones.factory;

class Motocicleta implements Vehiculo {
    @Override
    public void conducir() {
        System.out.println("🏍️ Conduciendo motocicleta a 80 km/h");
    }

    @Override
    public void detener() {
        System.out.println("🛑 Motocicleta detenida con frenos de disco");
    }

    @Override
    public String obtenerTipo() {
        return "Motocicleta";
    }
}
