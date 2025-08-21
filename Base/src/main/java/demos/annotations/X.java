package demos.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Anotación X - Marcada con @Documented
 * Esta anotación aparecerá en la documentación Javadoc.
 */
@Documented // Esta es la clave para que aparezca en Javadoc
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface X {}
