@echo off
echo Ejecutando Demo de Protección de Datos Sensibles (Part 1)...
echo.

REM Compilar el proyecto
echo Compilando...
call mvn compile

echo.
echo ========================================
echo DEMO: PROTECCIÓN DE DATOS SENSIBLES
echo ========================================
echo.

REM Ejecutar el ejemplo
java -cp "target/classes" com.bcp.security.SensitiveDataProtectionExample

echo.
echo ========================================
echo DEMO COMPLETADA
echo ========================================
echo.
echo Resumen de Protección de Datos Sensibles:
echo 1. ✅ Hashing de datos sensibles (SHA-256)
echo 2. ✅ Limpieza inmediata de memoria
echo 3. ✅ Eliminación de datos sensibles de excepciones
echo 4. ✅ No serialización de datos sensibles
echo 5. ✅ Logging seguro sin datos sensibles
echo 6. ✅ Prevención de fraude e identidad robada
echo.
pause
