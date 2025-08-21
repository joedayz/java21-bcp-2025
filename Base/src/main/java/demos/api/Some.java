package demos.api;

import demos.annotations.X;
import demos.annotations.Y;

/**
 * Clase de ejemplo para demostrar el uso de anotaciones en Javadoc.
 * Esta clase utiliza las anotaciones @X (Documented) y @Y (No Documented).
 *
 * @author John Doe
 * @version 1.0
 */
public class Some {

    /**
     * Método a - Anotado con @X (Documented).
     * Esta anotación debería aparecer en la documentación Javadoc.
     */
    @X
    public void a() {
        System.out.println("Method a() called.");
    }

    /**
     * Método b - Anotado con @Y (No Documented).
     * Esta anotación NO debería aparecer en la documentación Javadoc.
     */
    @Y
    public void b() {
        System.out.println("Method b() called.");
    }
}
