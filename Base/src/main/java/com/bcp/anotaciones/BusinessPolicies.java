package com.bcp.anotaciones;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Anotación contenedora para BusinessPolicy
 * Esta anotación contiene múltiples instancias de BusinessPolicy
 */
@Retention(RetentionPolicy.RUNTIME) // Disponible en tiempo de ejecución
@Target(ElementType.TYPE) // Aplicable a clases
public @interface BusinessPolicies {
    BusinessPolicy[] value(); // Array de anotaciones BusinessPolicy
}
