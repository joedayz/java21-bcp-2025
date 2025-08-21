@echo off
echo Ejecutando Demo de Guardas de Valores Erróneos...
echo.

REM Compilar el proyecto
echo Compilando...
call mvn compile

echo.
echo ========================================
echo DEMO: GUARDAS DE VALORES ERRÓNEOS
echo ========================================
echo.

REM Ejecutar el ejemplo
java -cp "target/classes" com.bcp.security.ValueGuardsExample

echo.
echo ========================================
echo DEMO COMPLETADA
echo ========================================
echo.
echo Resumen de las Guardas de Valores:
echo 1. ✅ Math.xxxExact() - Protección contra desbordamiento
echo 2. ✅ Double.isInfinite() / isNaN() - Valores de punto flotante
echo 3. ✅ Optional<T> - Protección contra referencias nulas
echo.
pause
