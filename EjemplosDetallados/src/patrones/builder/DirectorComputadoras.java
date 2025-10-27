package patrones.builder;

// 3. Director (Opcional) - Construye configuraciones predefinidas
class DirectorComputadoras {

    /**
     * Construye una PC básica para oficina
     */
    public static Computadora construirPCOficina() {
        return new Computadora.ComputadoraBuilder(
                "Intel Core i3-12100",
                "8GB DDR4",
                "1TB HDD"
        )
                .conWiFi(true)
                .conBluetooth(true)
                .build();
    }

    /**
     * Construye una PC Gaming de alta gama
     */
    public static Computadora construirPCGaming() {
        return new Computadora.ComputadoraBuilder(
                "AMD Ryzen 9 7950X",
                "32GB DDR5",
                "2TB NVMe"
        )
                .conTarjetaGrafica("NVIDIA RTX 4090")
                .conFuentePoder("1000W 80+ Gold")
                .conSSD(true)
                .conWiFi(true)
                .conBluetooth(true)
                .conGabinete("Torre RGB Gaming")
                .conVentiladores(6)
                .build();
    }

    /**
     * Construye una Workstation para diseño
     */
    public static Computadora construirWorkstation() {
        return new Computadora.ComputadoraBuilder(
                "Intel Xeon W-2295",
                "64GB DDR4 ECC",
                "4TB SSD"
        )
                .conTarjetaGrafica("NVIDIA RTX A5000")
                .conFuentePoder("850W 80+ Platinum")
                .conSSD(true)
                .conWiFi(true)
                .conGabinete("Torre Workstation")
                .conVentiladores(4)
                .build();
    }
}