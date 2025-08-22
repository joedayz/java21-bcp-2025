@echo off
echo ========================================
echo    DEMO: Generics Type Hierarchy
echo ========================================
echo.

echo Compilando con warnings habilitados...
javac -Xlint:rawtypes -Xlint:unchecked -cp "src/main/java" src/main/java/demos/genericos/*.java

echo.
echo ========================================
echo Ejecutando demo principal...
echo ========================================
java -cp "src/main/java" demos.genericos.GenericsTypeHierarchyDemo

echo.
echo ========================================
echo Ejecutando demo de warnings...
echo ========================================
java -cp "src/main/java" demos.genericos.RawTypesWarningsDemo

echo.
echo ========================================
echo Demo completado!
echo ========================================
pause
