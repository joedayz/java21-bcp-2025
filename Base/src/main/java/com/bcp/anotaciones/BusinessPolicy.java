package com.bcp.anotaciones;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.Repeatable;

/**
 * Anotación repetible para políticas de negocio
 * Esta anotación puede aplicarse múltiples veces a la misma clase
 */
@Retention(RetentionPolicy.RUNTIME) // Disponible en tiempo de ejecución
@Target(ElementType.TYPE) // Aplicable a clases
@Repeatable(BusinessPolicies.class) // Indica que es repetible y su contenedor
public @interface BusinessPolicy {
    String name();
    String[] countries() default {};
    String value();
}
