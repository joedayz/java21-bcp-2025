package com.bcp;

import java.sql.*;
import java.util.Properties;

/**
 * Ejemplo de uso de Apache Derby Database
 * Derby es una base de datos relacional escrita en Java
 */
public class DerbyExample {
    
    // URL de conexión para Derby embebido
    private static final String DB_URL = "jdbc:derby:bcpDB;create=true";
    
    public static void main(String[] args) {
        DerbyExample example = new DerbyExample();
        
        try {
            // Cargar el driver de Derby explícitamente
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            
            // Crear tabla y insertar datos
            example.createTable();
            example.insertData();
            
            // Consultar datos
            example.queryData();
            
            // Cerrar conexión
            example.shutdown();
            
        } catch (ClassNotFoundException e) {
            System.err.println("Driver de Derby no encontrado: " + e.getMessage());
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Error en la base de datos: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Crea una tabla de ejemplo
     */
    public void createTable() throws SQLException {
        String createTableSQL = """
            CREATE TABLE empleados (
                id INTEGER PRIMARY KEY,
                nombre VARCHAR(50) NOT NULL,
                apellido VARCHAR(50) NOT NULL,
                salario DECIMAL(10,2),
                fecha_contratacion DATE
            )
            """;
        
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {
            
            stmt.execute(createTableSQL);
            System.out.println("Tabla 'empleados' creada exitosamente");
        }
    }
    
    /**
     * Inserta datos de ejemplo
     */
    public void insertData() throws SQLException {
        String insertSQL = """
            INSERT INTO empleados (id, nombre, apellido, salario, fecha_contratacion) 
            VALUES (?, ?, ?, ?, ?)
            """;
        
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
            
            // Insertar varios empleados
            insertEmployee(pstmt, 1, "Juan", "Pérez", 50000.00, "2023-01-15");
            insertEmployee(pstmt, 2, "María", "García", 60000.00, "2023-02-20");
            insertEmployee(pstmt, 3, "Carlos", "López", 55000.00, "2023-03-10");
            
            System.out.println("Datos insertados exitosamente");
        }
    }
    
    /**
     * Método auxiliar para insertar un empleado
     */
    private void insertEmployee(PreparedStatement pstmt, int id, String nombre, 
                              String apellido, double salario, String fecha) throws SQLException {
        pstmt.setInt(1, id);
        pstmt.setString(2, nombre);
        pstmt.setString(3, apellido);
        pstmt.setBigDecimal(4, java.math.BigDecimal.valueOf(salario));
        pstmt.setDate(5, java.sql.Date.valueOf(fecha));
        pstmt.executeUpdate();
    }
    
    /**
     * Consulta y muestra los datos
     */
    public void queryData() throws SQLException {
        String querySQL = "SELECT * FROM empleados ORDER BY salario DESC";
        
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(querySQL)) {
            
            System.out.println("\n=== Empleados ordenados por salario ===");
            System.out.printf("%-5s %-15s %-15s %-10s %-15s%n", 
                            "ID", "Nombre", "Apellido", "Salario", "Fecha Contratación");
            System.out.println("------------------------------------------------------------");
            
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                double salario = rs.getDouble("salario");
                Date fecha = rs.getDate("fecha_contratacion");
                
                System.out.printf("%-5d %-15s %-15s %-10.2f %-15s%n", 
                                id, nombre, apellido, salario, fecha);
            }
        }
    }
    
    /**
     * Cierra la base de datos Derby
     */
    public void shutdown() {
        try {
            // Cerrar Derby
            DriverManager.getConnection("jdbc:derby:;shutdown=true");
        } catch (SQLException e) {
            // Derby siempre lanza una excepción al cerrar, esto es normal
            if (e.getSQLState().equals("XJ015")) {
                System.out.println("Base de datos Derby cerrada exitosamente");
            } else {
                System.err.println("Error al cerrar Derby: " + e.getMessage());
            }
        }
    }
}
