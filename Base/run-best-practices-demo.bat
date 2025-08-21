@echo off
echo Ejecutando Demo de Mejores Prácticas de Seguridad...
echo.

REM Compilar el proyecto
echo Compilando...
call mvn compile

echo.
echo ========================================
echo EJECUTANDO EJEMPLOS DE MEJORES PRÁCTICAS
echo ========================================
echo.

REM Ejecutar cada ejemplo
echo 1. ENCAPSULACIÓN ESTRICTA
echo ----------------------------------------
java -cp "target/classes" com.bcp.bestpractices.EncapsulationExample
echo.

echo 2. INMUTABILIDAD
echo ----------------------------------------
java -cp "target/classes" com.bcp.bestpractices.ImmutabilityExample
echo.

echo 3. PROBLEMAS DE HERENCIA
echo ----------------------------------------
java -cp "target/classes" com.bcp.bestpractices.InheritanceExample
echo.

echo 4. PROTECCIÓN DE BYTECODE
echo ----------------------------------------
java -cp "target/classes" com.bcp.bestpractices.BytecodeProtectionExample
echo.

echo ========================================
echo DEMO COMPLETADA
echo ========================================
echo.
echo Resumen de las 5 Mejores Prácticas:
echo 1. ✅ Enforce tight encapsulation
echo 2. ✅ Make objects as immutable as possible  
echo 3. ✅ Do not break subclass assumptions
echo 4. ✅ Design classes for inheritance or declare final
echo 5. ✅ Protect byte-code against tampering
echo.
pause
