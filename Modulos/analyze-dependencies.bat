@echo off
echo Analyzing JAR dependencies with jdeps...
echo.

REM Verificar que los JARs existen
if not exist "mods\demos.pm.jar" (
    echo Error: mods\demos.pm.jar no encontrado.
    echo Genera los JARs primero con generate-jars.bat
    pause
    exit /b 1
)

echo ========================================
echo 1. ANALIZANDO DEPENDENCIAS DE demos.pm.jar
echo ========================================
jdeps -v mods\demos.pm.jar

echo.
echo ========================================
echo 2. ANALIZANDO DEPENDENCIAS DE demos.shop.jar
echo ========================================
jdeps -v mods\demos.shop.jar

echo.
echo ========================================
echo 3. ANALIZANDO DEPENDENCIAS DE service.jar
echo ========================================
jdeps -v mods\service.jar

echo.
echo ========================================
echo 4. ANALIZANDO DEPENDENCIAS DE provider.jar
echo ========================================
jdeps -v mods\provider.jar

echo.
echo ========================================
echo 5. ANALIZANDO DEPENDENCIAS DE application.jar
echo ========================================
jdeps -v mods\application.jar

echo.
echo ========================================
echo 6. ANÁLISIS CON RESUMEN DE TODOS LOS JARs
echo ========================================
jdeps -s mods\demos.pm.jar
jdeps -s mods\demos.shop.jar
jdeps -s mods\service.jar
jdeps -s mods\provider.jar
jdeps -s mods\application.jar

echo.
echo ========================================
echo 7. ANÁLISIS DETALLADO CON DOT (para generar gráfico)
echo ========================================
REM Crear directorio para los archivos .dot
if not exist "deps-graph" mkdir deps-graph

REM Generar archivos .dot para cada JAR individualmente
echo Generando archivos .dot para cada JAR...
jdeps --dotoutput deps-graph mods\demos.pm.jar
jdeps --dotoutput deps-graph mods\demos.shop.jar
jdeps --dotoutput deps-graph mods\service.jar
jdeps --dotoutput deps-graph mods\provider.jar
jdeps --dotoutput deps-graph mods\application.jar

echo.
echo Archivos .dot generados en carpeta deps-graph
echo Puedes convertir estos archivos a imágenes usando Graphviz
echo Para instalar Graphviz: https://graphviz.org/download/
echo.

pause
