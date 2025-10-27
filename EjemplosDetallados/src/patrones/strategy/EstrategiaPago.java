package patrones.strategy;

/**
 * PATRÓN STRATEGY (Estrategia)
 *
 * Propósito: Define una familia de algoritmos, encapsula cada uno de ellos
 * y los hace intercambiables. Strategy permite que el algoritmo varíe
 * independientemente de los clientes que lo utilizan.
 *
 * Ventajas:
 * - Elimina condicionales complejos (if/else, switch)
 * - Permite cambiar algoritmos en tiempo de ejecución
 * - Facilita agregar nuevas estrategias sin modificar código existente
 * - Cada estrategia está aislada y es fácil de probar
 */

// 1. Interfaz Strategy - Define el contrato para todas las estrategias
interface EstrategiaPago {
    /**
     * Procesa un pago usando la estrategia específica
     * @param monto El monto a pagar
     * @return true si el pago fue exitoso, false en caso contrario
     */
    boolean procesarPago(double monto);

    /**
     * Obtiene el nombre de la estrategia de pago
     */
    String obtenerNombre();
}