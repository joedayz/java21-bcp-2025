package com.bcp.functionalinterface;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Ejemplo que demuestra cómo crear interfaces funcionales personalizadas
 * y usarlas con clases anónimas
 */
public class CustomFunctionalInterfaces {

    /**
     * Interfaz funcional personalizada para calcular descuentos
     */
    @FunctionalInterface
    public interface DiscountCalculator {
        BigDecimal calculateDiscount(Product product);

        String toString() ;
        boolean equals(Object obj);
        int hashCode();
    }
    
    /**
     * Interfaz funcional personalizada para validar productos
     */
    @FunctionalInterface
    public interface ProductValidator {
        boolean isValid(Product product);
    }
    
    /**
     * Interfaz funcional personalizada para formatear productos
     */
    @FunctionalInterface
    public interface ProductFormatter {
        String format(Product product);
    }
    
    /**
     * Interfaz funcional personalizada para procesar productos
     */
    @FunctionalInterface
    public interface ProductProcessor {
        void process(Product product);
    }
    
    public static void main(String[] args) {
        System.out.println("=== Interfaces Funcionales Personalizadas ===\n");
        
        // Crear lista de productos
        List<Product> products = new ArrayList<>();
        products.add(new Product("Laptop", 999.99));
        products.add(new Product("Mouse", 25.50));
        products.add(new Product("Keyboard", 89.99));
        products.add(new Product("Monitor", 299.99));
        products.add(new Product("Headphones", 79.99));
        
        // Demo 1: DiscountCalculator con clase anónima
        System.out.println("1. DiscountCalculator - Calcular descuentos:");
        System.out.println("   DiscountCalculator vipDiscount = new DiscountCalculator() {");
        System.out.println("       @Override");
        System.out.println("       public BigDecimal calculateDiscount(Product product) {");
        System.out.println("           return product.getPrice().multiply(BigDecimal.valueOf(0.15)); // 15%");
        System.out.println("       }");
        System.out.println("   };");
        System.out.println();
        
        DiscountCalculator vipDiscount = new DiscountCalculator() {
            @Override
            public BigDecimal calculateDiscount(Product product) {
                return product.getPrice().multiply(BigDecimal.valueOf(0.15)); // 15%
            }
        };
        
        System.out.println("   Descuento VIP para Laptop: $" + vipDiscount.calculateDiscount(products.get(0)));
        System.out.println();
        
        // Demo 2: ProductValidator con clase anónima
        System.out.println("2. ProductValidator - Validar productos:");
        System.out.println("   ProductValidator expensiveValidator = new ProductValidator() {");
        System.out.println("       @Override");
        System.out.println("       public boolean isValid(Product product) {");
        System.out.println("           return product.getPrice().compareTo(BigDecimal.valueOf(100)) > 0;");
        System.out.println("       }");
        System.out.println("   };");
        System.out.println();
        
        ProductValidator expensiveValidator = new ProductValidator() {
            @Override
            public boolean isValid(Product product) {
                return product.getPrice().compareTo(BigDecimal.valueOf(100)) > 0;
            }
        };
        
        System.out.println("   Productos caros (> $100):");
        for (Product product : products) {
            if (expensiveValidator.isValid(product)) {
                System.out.println("   ✅ " + product.getName() + " - $" + product.getPrice());
            } else {
                System.out.println("   ❌ " + product.getName() + " - $" + product.getPrice());
            }
        }
        System.out.println();
        
        // Demo 3: ProductFormatter con clase anónima
        System.out.println("3. ProductFormatter - Formatear productos:");
        System.out.println("   ProductFormatter detailedFormatter = new ProductFormatter() {");
        System.out.println("       @Override");
        System.out.println("       public String format(Product product) {");
        System.out.println("           return String.format(\"📦 %s | 💰 $%.2f | 📏 %d chars\",");
        System.out.println("               product.getName(), product.getPrice(), product.getName().length());");
        System.out.println("       }");
        System.out.println("   };");
        System.out.println();
        
        ProductFormatter detailedFormatter = new ProductFormatter() {
            @Override
            public String format(Product product) {
                return String.format("📦 %s | 💰 $%.2f | 📏 %d chars",
                    product.getName(), product.getPrice(), product.getName().length());
            }
        };
        
        System.out.println("   Productos formateados:");
        for (Product product : products) {
            System.out.println("   " + detailedFormatter.format(product));
        }
        System.out.println();
        
        // Demo 4: ProductProcessor con clase anónima
        System.out.println("4. ProductProcessor - Procesar productos:");
        System.out.println("   ProductProcessor priceProcessor = new ProductProcessor() {");
        System.out.println("       @Override");
        System.out.println("       public void process(Product product) {");
        System.out.println("           BigDecimal discount = product.getPrice().multiply(BigDecimal.valueOf(0.1));");
        System.out.println("           BigDecimal finalPrice = product.getPrice().subtract(discount);");
        System.out.println("           System.out.println(\"   \" + product.getName() + \": $\" + finalPrice + \" (10% off)\");");
        System.out.println("       }");
        System.out.println("   };");
        System.out.println();
        
        ProductProcessor priceProcessor = new ProductProcessor() {
            @Override
            public void process(Product product) {
                BigDecimal discount = product.getPrice().multiply(BigDecimal.valueOf(0.1));
                BigDecimal finalPrice = product.getPrice().subtract(discount);
                System.out.println("   " + product.getName() + ": $" + finalPrice + " (10% off)");
            }
        };
        
        System.out.println("   Procesando productos con descuento:");
        for (Product product : products) {
            priceProcessor.process(product);
        }
        System.out.println();
        
        // Demo 5: Múltiples implementaciones anónimas
        System.out.println("5. Múltiples Implementaciones Anónimas:");
        System.out.println();
        
        // Diferentes calculadores de descuento
        DiscountCalculator bulkDiscount = new DiscountCalculator() {
            @Override
            public BigDecimal calculateDiscount(Product product) {
                return product.getPrice().multiply(BigDecimal.valueOf(0.20)); // 20%
            }
        };
        
        DiscountCalculator seasonalDiscount = new DiscountCalculator() {
            @Override
            public BigDecimal calculateDiscount(Product product) {
                return product.getPrice().multiply(BigDecimal.valueOf(0.25)); // 25%
            }
        };
        
        System.out.println("   Diferentes descuentos para Laptop:");
        System.out.println("   VIP (15%): $" + vipDiscount.calculateDiscount(products.get(0)));
        System.out.println("   Bulk (20%): $" + bulkDiscount.calculateDiscount(products.get(0)));
        System.out.println("   Seasonal (25%): $" + seasonalDiscount.calculateDiscount(products.get(0)));
        System.out.println();
        
        // Demo 6: Comparación con lambdas
        System.out.println("6. Comparación con Lambdas:");
        System.out.println();
        
        System.out.println("   DiscountCalculator con lambda:");
        System.out.println("   DiscountCalculator lambdaDiscount = product -> product.getPrice().multiply(BigDecimal.valueOf(0.10));");
        DiscountCalculator lambdaDiscount = product -> product.getPrice().multiply(BigDecimal.valueOf(0.10));
        System.out.println("   Lambda discount: $" + lambdaDiscount.calculateDiscount(products.get(0)));
        System.out.println();
        
        System.out.println("   ProductValidator con lambda:");
        System.out.println("   ProductValidator lambdaValidator = product -> product.getName().length() > 5;");
        ProductValidator lambdaValidator = product -> product.getName().length() > 5;
        System.out.println("   Productos con nombre largo (> 5 chars):");
        for (Product product : products) {
            if (lambdaValidator.isValid(product)) {
                System.out.println("   ✅ " + product.getName());
            } else {
                System.out.println("   ❌ " + product.getName());
            }
        }
        System.out.println();
        
        // Demo 7: Características de las interfaces funcionales personalizadas
        System.out.println("7. Características de las Interfaces Funcionales Personalizadas:");
        System.out.println();
        System.out.println("   📊 @FunctionalInterface:");
        System.out.println("      - Garantiza que la interfaz tenga exactamente un método abstracto");
        System.out.println("      - Permite usar lambdas");
        System.out.println("      - Documenta la intención");
        System.out.println();
        System.out.println("   📊 Ventajas:");
        System.out.println("      - Código más expresivo y legible");
        System.out.println("      - Reutilización de lógica");
        System.out.println("      - Flexibilidad en implementación");
        System.out.println("      - Compatibilidad con lambdas");
        System.out.println();
        
        System.out.println("=== Demo Completado ===");
    }
} 