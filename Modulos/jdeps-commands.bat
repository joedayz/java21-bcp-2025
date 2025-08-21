@echo off
echo Comandos jdeps para analizar JARs
echo.

echo Comandos disponibles:
echo.
echo 1. Analizar un JAR específico:
echo    jdeps -v mods\demos.pm.jar
echo    jdeps -v mods\demos.shop.jar
echo    jdeps -v mods\service.jar
echo    jdeps -v mods\provider.jar
echo    jdeps -v mods\application.jar
echo.
echo 2. Analizar todos los JARs:
echo    jdeps -v mods\*.jar
echo.
echo 3. Resumen de dependencias:
echo    jdeps -s mods\*.jar
echo.
echo 4. Generar gráfico de dependencias:
echo    jdeps --dotoutput deps-graph mods\*.jar
echo.
echo 5. Analizar con classpath:
echo    jdeps --class-path mods -v mods\application.jar
echo.
echo 6. Analizar módulos específicos:
echo    jdeps --module-path mods -m application
echo.

echo Ejecutando análisis básico de todos los JARs...
echo ========================================
jdeps -s mods\*.jar

echo.
echo ========================================
echo Análisis detallado de application.jar:
echo ========================================
jdeps -v mods\application.jar

echo.
pause
