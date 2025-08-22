@echo off
echo ========================================
echo    DEMO: Wildcard Generics
echo ========================================
echo.

echo Compilando con warnings habilitados...
javac -Xlint:rawtypes -Xlint:unchecked -cp "src/main/java" src/main/java/demos/wildcards/*.java

echo.
echo ========================================
echo Ejecutando demo principal de wildcards...
echo ========================================
java -cp "src/main/java" demos.wildcards.WildcardDemo

echo.
echo ========================================
echo Ejecutando demo de errores...
echo ========================================
java -cp "src/main/java" demos.wildcards.WildcardErrorsDemo

echo.
echo ========================================
echo Ejecutando demo de casos prácticos...
echo ========================================
java -cp "src/main/java" demos.wildcards.WildcardUtils

echo.
echo ========================================
echo Ejecutando demo de Upper Bound Wildcard...
echo ========================================
java -cp "src/main/java" demos.wildcards.UpperBoundWildcardDemo

echo.
echo ========================================
echo Ejecutando demo de casos prácticos Upper Bound...
echo ========================================
java -cp "src/main/java" demos.wildcards.UpperBoundUtils

echo.
echo ========================================
echo Ejecutando demo de Lower Bound Wildcard...
echo ========================================
java -cp "src/main/java" demos.wildcards.LowerBoundWildcardDemo

echo.
echo ========================================
echo Ejecutando demo de casos prácticos Lower Bound...
echo ========================================
java -cp "src/main/java" demos.wildcards.LowerBoundUtils

echo.
echo ========================================
echo Ejecutando demo de comparación de wildcards...
echo ========================================
java -cp "src/main/java" demos.wildcards.WildcardComparisonDemo

echo.
echo ========================================
echo Todos los demos de wildcards completados!
echo ========================================
pause
