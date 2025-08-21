package com.bcp.anotaciones;

import java.lang.annotation.Annotation;

/**
 * Clase para leer y mostrar anotaciones en tiempo de ejecución
 * Demuestra cómo acceder a anotaciones repetibles usando reflexión
 */
public class AnnotationReader {

    public static void main(String[] args) {
        System.out.println("=== DEMO: Anotaciones Repetibles ===\n");

        // Analizar SomeBusinessClass (forma 1: anotaciones repetibles directas)
        System.out.println("1. ANALIZANDO SomeBusinessClass (anotaciones repetibles directas):");
        analyzeClass(SomeBusinessClass.class);

        System.out.println("\n" + "=".repeat(60) + "\n");

        // Analizar AnotherBusinessClass (forma 2: usando contenedor)
        System.out.println("2. ANALIZANDO AnotherBusinessClass (usando contenedor):");
        analyzeClass(AnotherBusinessClass.class);

        System.out.println("\n=== FIN DEL DEMO ===");
    }

    /**
     * Analiza las anotaciones de una clase usando reflexión
     */
    private static void analyzeClass(Class<?> clazz) {
        System.out.println("Clase: " + clazz.getSimpleName());
        System.out.println("Anotaciones encontradas:");

        // Obtener todas las anotaciones de la clase
        Annotation[] annotations = clazz.getAnnotations();

        if (annotations.length == 0) {
            System.out.println("  - No se encontraron anotaciones");
            return;
        }

        for (Annotation annotation : annotations) {
            System.out.println("  - " + annotation.annotationType().getSimpleName());

            if (annotation instanceof BusinessPolicy) {
                // Anotación individual
                BusinessPolicy policy = (BusinessPolicy) annotation;
                printPolicyDetails(policy, "    ");
            } else if (annotation instanceof BusinessPolicies) {
                // Anotación contenedora
                BusinessPolicies policies = (BusinessPolicies) annotation;
                System.out.println("    Contenedor con " + policies.value().length + " políticas:");
                for (BusinessPolicy policy : policies.value()) {
                    printPolicyDetails(policy, "      ");
                }
            }
        }

        // También podemos usar getAnnotationsByType para obtener directamente las BusinessPolicy
        System.out.println("\n  Usando getAnnotationsByType(BusinessPolicy.class):");
        BusinessPolicy[] policies = clazz.getAnnotationsByType(BusinessPolicy.class);
        for (BusinessPolicy policy : policies) {
            printPolicyDetails(policy, "    ");
        }
    }

    /**
     * Imprime los detalles de una política de negocio
     */
    private static void printPolicyDetails(BusinessPolicy policy, String indent) {
        System.out.println(indent + "Name: " + policy.name());
        System.out.println(indent + "Countries: " + String.join(", ", policy.countries()));
        System.out.println(indent + "Value: " + policy.value());
        System.out.println(indent + "---");
    }
}
