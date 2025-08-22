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
echo Todos los demos de wildcards completados!
echo ========================================
pause
