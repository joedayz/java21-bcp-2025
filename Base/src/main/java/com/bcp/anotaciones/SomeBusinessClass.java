package com.bcp.anotaciones;

/**
 * Clase que demuestra el uso de anotaciones repetibles
 * Forma 1: Usando múltiples @BusinessPolicy directamente
 */
@BusinessPolicy(name = "Policy A", countries = {"US", "CA"}, value = "Rule1")
@BusinessPolicy(name = "Policy B", countries = {"EU"}, value = "Rule2")
public class SomeBusinessClass {
    // Código de la clase
    public void doSomething() {
        System.out.println("Executing SomeBusinessClass logic.");
    }
}

/**
 * Clase que demuestra la forma equivalente usando el contenedor
 * Forma 2: Usando @BusinessPolicies (equivalente a la forma 1)
 */
@BusinessPolicies({
    @BusinessPolicy(name = "Policy C", countries = {"JP"}, value = "Rule3"),
    @BusinessPolicy(name = "Policy D", countries = {"AU", "NZ"}, value = "Rule4")
})
class AnotherBusinessClass {
    // Código de la clase
    public void doSomething() {
        System.out.println("Executing AnotherBusinessClass logic.");
    }
}
