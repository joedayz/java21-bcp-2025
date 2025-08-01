package com.bcp.memberinnerclass;

/**
 * Demo comparativo entre Static Nested Classes y Member Inner Classes
 */
public class ComparisonDemo {
    
    /**
     * Clase externa que contiene ambos tipos de clases anidadas
     */
    public static class OuterClass {
        
        private String instanceField = "Campo de instancia";
        private static String staticField = "Campo estático";
        
        // Constructor
        public OuterClass() {
            System.out.println("OuterClass instanciada");
        }
        
        /**
         * Clase anidada estática (Static Nested Class)
         */
        public static class StaticNested {
            public void demonstrateStaticNested() {
                System.out.println("  ✅ StaticNested puede acceder a campos estáticos: " + staticField);
                System.out.println("  ❌ StaticNested NO puede acceder a campos de instancia");
                System.out.println("  ✅ StaticNested puede existir sin instancia de OuterClass");
                System.out.println("  ✅ StaticNested puede tener métodos estáticos");
            }
            
            public static void staticMethod() {
                System.out.println("  ✅ StaticNested puede tener métodos estáticos");
            }
        }
        
        /**
         * Clase anidada de miembro (Member Inner Class)
         */
        public class MemberInner {
            public void demonstrateMemberInner() {
                System.out.println("  ✅ MemberInner puede acceder a campos de instancia: " + instanceField);
                System.out.println("  ✅ MemberInner puede acceder a campos estáticos: " + staticField);
                System.out.println("  ❌ MemberInner NO puede existir sin instancia de OuterClass");
                System.out.println("  ❌ MemberInner NO puede tener métodos estáticos");
            }
        }
        
        /**
         * Método para crear instancia de MemberInner
         */
        public MemberInner createMemberInner() {
            return new MemberInner();
        }
    }
    
    public static void main(String[] args) {
        System.out.println("=== Comparación: Static Nested vs Member Inner Classes ===\n");
        
        // Demo 1: Static Nested Class
        System.out.println("1. Static Nested Class:");
        System.out.println("   OuterClass.StaticNested staticNested = new OuterClass.StaticNested();");
        OuterClass.StaticNested staticNested = new OuterClass.StaticNested();
        staticNested.demonstrateStaticNested();
        OuterClass.StaticNested.staticMethod();
        System.out.println();
        
        // Demo 2: Member Inner Class
        System.out.println("2. Member Inner Class:");
        System.out.println("   OuterClass outer = new OuterClass();");
        OuterClass outer = new OuterClass();
        System.out.println("   OuterClass.MemberInner memberInner = outer.createMemberInner();");
        OuterClass.MemberInner memberInner = outer.createMemberInner();
        memberInner.demonstrateMemberInner();
        System.out.println();
        
        // Demo 3: Diferencias clave
        System.out.println("3. Diferencias Clave:");
        System.out.println("   📊 Static Nested Classes:");
        System.out.println("      - Son independientes de la instancia de la clase externa");
        System.out.println("      - Pueden ser instanciadas sin crear la clase externa");
        System.out.println("      - Solo pueden acceder a miembros estáticos de la clase externa");
        System.out.println("      - Pueden tener métodos estáticos");
        System.out.println();
        System.out.println("   📊 Member Inner Classes:");
        System.out.println("      - Están asociadas a una instancia específica de la clase externa");
        System.out.println("      - No pueden existir sin una instancia de la clase externa");
        System.out.println("      - Pueden acceder a todos los miembros de la instancia");
        System.out.println("      - No pueden tener métodos estáticos");
        System.out.println();
        
        // Demo 4: Casos de uso
        System.out.println("4. Casos de Uso:");
        System.out.println("   🔧 Static Nested Classes:");
        System.out.println("      - Cuando la clase anidada es independiente de la instancia");
        System.out.println("      - Para agrupar clases relacionadas");
        System.out.println("      - Cuando necesitas métodos estáticos en la clase anidada");
        System.out.println();
        System.out.println("   🔧 Member Inner Classes:");
        System.out.println("      - Cuando la clase anidada necesita acceder a la instancia");
        System.out.println("      - Para implementar callbacks o listeners");
        System.out.println("      - Cuando la clase anidada es parte de la instancia");
        System.out.println();
        
        System.out.println("=== Comparación Completada ===");
    }
} 