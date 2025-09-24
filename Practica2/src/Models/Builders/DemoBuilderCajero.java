package Models.Builders;

/**
 * Demostración completa del patrón Builder aplicado al sistema de cajero automático
 * Muestra diferentes formas de crear cuentas y las ventajas del patrón Builder
 */
public class DemoBuilderCajero {
    public static void main(String[] args) {
        System.out.println("=== DEMOSTRACIÓN DEL PATRÓN BUILDER EN CAJERO AUTOMÁTICO ===\n");

        // Crear director para cuentas predefinidas
        DirectorCuentas director = new DirectorCuentas();

        System.out.println("PATRÓN BUILDER: Construcción flexible de objetos complejos\n");
        System.out.println("Ventajas:");
        System.out.println("• Construcción paso a paso de objetos complejos");
        System.out.println("• Código más legible y mantenible");
        System.out.println("• Diferentes representaciones del mismo objeto");
        System.out.println("• Validación durante la construcción");
        System.out.println("• Separación entre construcción y representación\n");

        // ========== CONSTRUCCIÓN DIRECTA CON BUILDER ==========
        System.out.println("CONSTRUCCIÓN DIRECTA CON BUILDER (Máxima flexibilidad):\n");

        CuentaExtendida cuentaPersonalizada = new CuentaBuilder("99999", "9999", "Ana García")
                .conSaldoInicial(15000.0)
                .conLimiteDiario(3000.0)
                .conComisionTransferencia(5.0)
                .build();

        System.out.println("Cuenta construida paso a paso:");
        System.out.println(cuentaPersonalizada.getInfoCuenta());
        System.out.println();

        // Ejemplo de construcción mínima
        CuentaExtendida cuentaMinima = new CuentaBuilder("88888", "8888", "Pedro Mínimo")
                .conSaldoInicial(1000.0)
                .build();

        System.out.println("Cuenta con configuración mínima (resto por defecto):");
        System.out.println(cuentaMinima.getInfoCuenta());
        System.out.println();

        // ========== CONSTRUCCIÓN CON DIRECTOR ==========
        System.out.println("CONSTRUCCIÓN CON DIRECTOR (Tipos predefinidos):\n");

        CuentaExtendida[] cuentas = {
                director.crearCuentaBasica("11111", "1111", "Luis Pérez", 5000.0),
                director.crearCuentaPremium("22222", "2222", "María López", 25000.0),
                director.crearCuentaEmpresarial("33333", "3333", "Empresa XYZ", 100000.0),
                director.crearCuentaEstudiante("44444", "4444", "Carlos Student", 1500.0),
                director.crearCuentaVIP("55555", "5555", "Roberto VIP", 500000.0),
                director.crearCuentaAhorro("66666", "6666", "Sandra Ahorro", 10000.0)
        };

        for (CuentaExtendida cuenta : cuentas) {
            System.out.println("🏦" + cuenta.getInfoCuenta());
        }
        System.out.println();

        // ========== DEMOSTRACIÓN DE FUNCIONALIDADES ==========
        System.out.println("DEMOSTRACIÓN DE FUNCIONALIDADES EXTENDIDAS:\n");

        CuentaExtendida cuentaEstudiante = cuentas[3]; // Carlos Student
        CuentaExtendida cuentaPremium = cuentas[1];    // María López
        CuentaExtendida cuentaBasica = cuentas[0];     // Luis Pérez

        // Probar límites diarios
        System.out.println("PRUEBA DE LÍMITES DIARIOS:");
        System.out.println("Cuenta estudiante - Límite: $" + cuentaEstudiante.getLimiteDiario());
        System.out.println("Intentando retirar $1500 (excede límite de $1000):");
        boolean exito = cuentaEstudiante.m_retirar(1500);
        System.out.println("Resultado: " + (exito ? "Exitoso" : "Fallido (como esperado)"));

        System.out.println("\nRetirando $800 (dentro del límite):");
        exito = cuentaEstudiante.m_retirar(800);
        System.out.println("Resultado: " + (exito ? "Exitoso" : "Fallido"));
        System.out.printf("Nuevo saldo: $%.2f\n", cuentaEstudiante.getA_saldo());
        System.out.printf("Retiros hoy: $%.2f de $%.2f\n",
                cuentaEstudiante.getRetirosDiarios(), cuentaEstudiante.getLimiteDiario());
        System.out.println();

        // Probar transferencias con comisión
        System.out.println("PRUEBA DE TRANSFERENCIAS CON COMISIÓN:");
        System.out.println("Transfiriendo $1000 de cuenta básica a cuenta premium");
        System.out.printf("Saldo cuenta básica antes: $%.2f\n", cuentaBasica.getA_saldo());
        System.out.printf("Comisión a aplicar: $%.2f\n", cuentaBasica.getComisionTransferencia());
        System.out.printf("Saldo cuenta premium antes: $%.2f\n", cuentaPremium.getA_saldo());

        boolean exitoTransferencia = cuentaBasica.transferirConComision(1000, cuentaPremium);

        System.out.printf("Saldo cuenta básica después: $%.2f\n", cuentaBasica.getA_saldo());
        System.out.printf("Saldo cuenta premium después: $%.2f\n", cuentaPremium.getA_saldo());
        System.out.println("Transferencia: " + (exitoTransferencia ? "Exitosa" : "Fallida"));
        System.out.println();

        // Comparar diferentes tipos de cuenta
        System.out.println("COMPARACIÓN DE TIPOS DE CUENTA:\n");
        System.out.println("Tabla comparativa:");
        System.out.println("Tipo\t\t| Límite Diario\t| Comisión\t| Premium");
        System.out.println("----------------|---------------|---------------|--------");

        for (CuentaExtendida cuenta : cuentas) {
            System.out.printf("%-15s | $%-12.0f | $%-12.2f | %s\n",
                    cuenta.getTipoCuenta(),
                    cuenta.getLimiteDiario(),
                    cuenta.getComisionTransferencia(),
                    cuenta.esPremium() ? "Sí" : "No"
            );
        }

        System.out.println();

        // ========== CASOS DE USO DEL BUILDER ==========
        System.out.println("CASOS DE USO ESPECÍFICOS DEL BUILDER:\n");

        // Caso 1: Cuenta con solo algunas características
        CuentaExtendida cuentaSimple = new CuentaBuilder("77777", "7777", "Juan Simple")
                .conSaldoInicial(3000.0)
                .conLimiteDiario(1500.0)
                // No especificamos comisión, usa valor por defecto
                .build();

        System.out.println("Cuenta con configuración parcial:");
        System.out.println(cuentaSimple.getInfoCuenta());

        // Caso 2: Cuenta completamente personalizada
        CuentaExtendida cuentaCompleta = new CuentaBuilder("00000", "0000", "Admin Sistema")
                .conSaldoInicial(1000000.0)
                .conLimiteDiario(200000.0)
                .conComisionTransferencia(0.0)
                .cuentaPremium()
                .build();

        System.out.println("\n Cuenta completamente personalizada:");
        System.out.println(cuentaCompleta.getInfoCuenta());
        System.out.println();

        // ========== VENTAJAS DEL PATRÓN ==========
        System.out.println("VENTAJAS DEMOSTRADAS:\n");
        System.out.println("FLEXIBILIDAD: Podemos crear cuentas con cualquier combinación de características");
        System.out.println("LEGIBILIDAD: El código es autodocumentado (ej: .cuentaPremium())");
        System.out.println("MANTENIBILIDAD: Fácil agregar nuevas características sin cambiar constructores");
        System.out.println("REUTILIZACIÓN: Director permite crear tipos comunes fácilmente");
        System.out.println("VALIDACIÓN: Podemos validar la configuración antes de crear el objeto");
        System.out.println("INMUTABILIDAD: Una vez creada, la configuración de la cuenta no cambia");

        System.out.println("\n ¡DEMOSTRACIÓN COMPLETADA!");
        System.out.println("El patrón Builder nos permite crear objetos complejos de manera flexible y legible.");
    }
}