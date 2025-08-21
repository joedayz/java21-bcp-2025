@echo off
echo Ejecutando Demo de Anotaciones Repetibles...
echo.

REM Compilar el proyecto
echo Compilando...
call mvn compile

REM Ejecutar la demo
echo.
echo Ejecutando demo...
java -cp "target/classes" com.bcp.anotaciones.AnnotationReader

echo.
echo Demo completada!
pause
