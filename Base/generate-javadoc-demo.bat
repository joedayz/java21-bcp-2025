@echo off
echo Generando documentación Javadoc para la demo de anotaciones...
echo.

REM Crear el directorio de salida si no existe
if not exist "docs" mkdir docs

REM Ejecutar Javadoc
REM -d docs: Directorio de salida para la documentación
REM -sourcepath src/main/java: Ruta donde se encuentran los archivos fuente
REM -subpackages demos: Incluye todos los paquetes bajo 'demos' (demos.annotations y demos.api)
javadoc -d docs -sourcepath src/main/java -subpackages demos

echo.
echo Documentación Javadoc generada en la carpeta 'docs'.
echo Abre 'docs/index.html' en tu navegador para verla.
echo.
pause
