@echo off
echo jdeps para Windows - Análisis de dependencias de JARs
echo.

REM Verificar que los JARs existen
if not exist "mods\demos.pm.jar" (
    echo Error: JARs no encontrados en mods\
    echo Ejecuta generate-jars.bat primero
    pause
    exit /b 1
)

echo ========================================
echo ANÁLISIS BÁSICO DE DEPENDENCIAS
echo ========================================

echo.
echo 1. demos.pm.jar:
jdeps -s mods\demos.pm.jar

echo.
echo 2. demos.shop.jar:
jdeps -s mods\demos.shop.jar

echo.
echo 3. service.jar:
jdeps -s mods\service.jar

echo.
echo 4. provider.jar:
jdeps -s mods\provider.jar

echo.
echo 5. application.jar:
jdeps -s mods\application.jar

echo.
echo ========================================
echo ANÁLISIS DETALLADO DE application.jar
echo ========================================
jdeps -v mods\application.jar

echo.
echo ========================================
echo GENERANDO ARCHIVOS DOT
echo ========================================

REM Crear directorio si no existe
if not exist "deps-graph" (
    mkdir deps-graph
    echo Directorio deps-graph creado
)

echo Generando archivos .dot...

REM Generar archivos .dot uno por uno
echo - demos.pm.jar...
jdeps --dotoutput deps-graph mods\demos.pm.jar

echo - demos.shop.jar...
jdeps --dotoutput deps-graph mods\demos.shop.jar

echo - service.jar...
jdeps --dotoutput deps-graph mods\service.jar

echo - provider.jar...
jdeps --dotoutput deps-graph mods\provider.jar

echo - application.jar...
jdeps --dotoutput deps-graph mods\application.jar

echo.
echo Archivos .dot generados en carpeta deps-graph
echo.
echo Para convertir a imágenes, instala Graphviz y ejecuta:
echo dot -Tpng deps-graph\demos.pm.jar.dot -o demos.pm.png
echo dot -Tpng deps-graph\application.jar.dot -o application.png
echo.

pause
