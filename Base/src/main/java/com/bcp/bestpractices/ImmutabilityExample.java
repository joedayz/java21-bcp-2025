package com.bcp.bestpractices;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

/**
 * Ejemplo 2: Make objects as immutable as possible
 * 
 * Demuestra cómo crear objetos inmutables y manejar referencias
 * a objetos mutables de forma segura.
 */
public class ImmutabilityExample {
    
    /**
     * Clase inmutable bien diseñada
     */
    public static final class ImmutablePerson {
        private final String name;
        private final int age;
        private final List<String> hobbies;
        private final Date birthDate;
        
        public ImmutablePerson(String name, int age, List<String> hobbies, Date birthDate) {
            this.name = name;
            this.age = age;
            
            // Clonar la lista para evitar modificaciones externas
            this.hobbies = new ArrayList<>(hobbies);
            
            // Clonar la fecha porque Date es mutable
            this.birthDate = new Date(birthDate.getTime());
        }
        
        // Getters que devuelven copias defensivas
        public String getName() { return name; }
        public int getAge() { return age; }
        
        public List<String> getHobbies() {
            // Devolver vista inmutable para proteger la lista interna
            return Collections.unmodifiableList(hobbies);
        }
        
        public Date getBirthDate() {
            // Devolver copia defensiva de la fecha
            return new Date(birthDate.getTime());
        }
        
        // Método para agregar hobby (crea nueva instancia)
        public ImmutablePerson withHobby(String hobby) {
            List<String> newHobbies = new ArrayList<>(hobbies);
            newHobbies.add(hobby);
            return new ImmutablePerson(name, age, newHobbies, birthDate);
        }
        
        // Método para cambiar edad (crea nueva instancia)
        public ImmutablePerson withAge(int newAge) {
            return new ImmutablePerson(name, newAge, hobbies, birthDate);
        }
        
        @Override
        public String toString() {
            return "Person{name='" + name + "', age=" + age + 
                   ", hobbies=" + hobbies + ", birthDate=" + birthDate + "}";
        }
    }
    
    /**
     * Clase mutable problemática (para comparar)
     */
    public static class MutablePerson {
        private String name;
        private int age;
        private List<String> hobbies;
        private Date birthDate;
        
        public MutablePerson(String name, int age, List<String> hobbies, Date birthDate) {
            this.name = name;
            this.age = age;
            this.hobbies = hobbies; // ❌ Referencia directa - peligroso
            this.birthDate = birthDate; // ❌ Referencia directa - peligroso
        }
        
        // Getters que devuelven referencias directas
        public String getName() { return name; }
        public int getAge() { return age; }
        public List<String> getHobbies() { return hobbies; } // ❌ Peligroso
        public Date getBirthDate() { return birthDate; } // ❌ Peligroso
        
        // Setters que permiten modificación
        public void setName(String name) { this.name = name; }
        public void setAge(int age) { this.age = age; }
        public void setHobbies(List<String> hobbies) { this.hobbies = hobbies; }
        public void setBirthDate(Date birthDate) { this.birthDate = birthDate; }
        
        @Override
        public String toString() {
            return "Person{name='" + name + "', age=" + age + 
                   ", hobbies=" + hobbies + ", birthDate=" + birthDate + "}";
        }
    }
    
    /**
     * Clase que demuestra los problemas de mutabilidad
     */
    public static class MutabilityTester {
        
        public static void testImmutability() {
            System.out.println("=== DEMO: Inmutabilidad ===\n");
            
            // Crear datos de prueba
            List<String> originalHobbies = new ArrayList<>();
            originalHobbies.add("Leer");
            originalHobbies.add("Nadar");
            
            Date originalDate = new Date(90, 0, 15); // 15 de enero de 1990
            
            System.out.println("1. CREANDO OBJETOS INMUTABLES Y MUTABLES");
            System.out.println("Datos originales: " + originalHobbies + ", " + originalDate);
            
            // Crear objetos
            ImmutablePerson immutablePerson = new ImmutablePerson("Ana", 25, originalHobbies, originalDate);
            MutablePerson mutablePerson = new MutablePerson("Bob", 30, originalHobbies, originalDate);
            
            System.out.println("Persona inmutable: " + immutablePerson);
            System.out.println("Persona mutable: " + mutablePerson);
            
            System.out.println("\n2. MODIFICANDO DATOS ORIGINALES");
            // Modificar los datos originales
            originalHobbies.add("Cocinar");
            originalDate.setYear(95); // Cambiar año a 1995
            
            System.out.println("Datos modificados: " + originalHobbies + ", " + originalDate);
            System.out.println("Persona inmutable (sin cambios): " + immutablePerson);
            System.out.println("Persona mutable (afectada): " + mutablePerson);
            
            System.out.println("\n3. INTENTANDO MODIFICAR OBJETOS INMUTABLES");
            
            // Intentar modificar la lista de hobbies de la persona inmutable
            try {
                List<String> hobbies = immutablePerson.getHobbies();
                hobbies.add("Pintar"); // Esto debería fallar
                System.out.println("❌ Modificación exitosa (no debería pasar)");
            } catch (UnsupportedOperationException e) {
                System.out.println("✅ Protección exitosa: " + e.getMessage());
            }
            
            // Intentar modificar la fecha de la persona inmutable
            Date retrievedDate = immutablePerson.getBirthDate();
            retrievedDate.setYear(100); // Modificar la fecha obtenida
            System.out.println("Fecha modificada: " + retrievedDate);
            System.out.println("Persona inmutable (sin cambios): " + immutablePerson);
            
            System.out.println("\n4. CREANDO NUEVAS INSTANCIAS INMUTABLES");
            
            // Crear nueva persona con hobby adicional
            ImmutablePerson personWithNewHobby = immutablePerson.withHobby("Pintar");
            System.out.println("Persona original: " + immutablePerson);
            System.out.println("Persona con nuevo hobby: " + personWithNewHobby);
            
            // Crear nueva persona con edad diferente
            ImmutablePerson personWithNewAge = immutablePerson.withAge(26);
            System.out.println("Persona original: " + immutablePerson);
            System.out.println("Persona con nueva edad: " + personWithNewAge);
            
            System.out.println("\n5. VENTAJAS DE LA INMUTABILIDAD");
            System.out.println("✅ Thread-safe por defecto");
            System.out.println("✅ Sin efectos secundarios");
            System.out.println("✅ Fácil de testear");
            System.out.println("✅ Caché-friendly");
            System.out.println("✅ Evita bugs de estado compartido");
        }
    }
    
    public static void main(String[] args) {
        MutabilityTester.testImmutability();
    }
}
