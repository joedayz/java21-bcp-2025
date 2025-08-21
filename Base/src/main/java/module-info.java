module com.bcp.modules {
    // Requiere módulos del JDK
    requires java.logging;
    
    // Exporta todos los paquetes
    exports com.bcp.modules.core;
    exports com.bcp.modules.service;
    exports com.bcp.modules.utils;
    exports com.bcp.modules.client;
    
    // Permite reflection en todos los paquetes
    opens com.bcp.modules.core;
    opens com.bcp.modules.service;
    opens com.bcp.modules.utils;
    opens com.bcp.modules.client;
    
    // Usa el servicio de productos
    uses com.bcp.modules.core.ProductService;
    
    // Proporciona implementaciones del servicio
    provides com.bcp.modules.core.ProductService 
        with com.bcp.modules.service.InMemoryProductService,
             com.bcp.modules.service.FileBasedProductService;
}
