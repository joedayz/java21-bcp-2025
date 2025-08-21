@echo off
echo Generando JARs para todos los módulos...
echo.

REM Crear JAR para demos.pm
echo Generando demos.pm.jar...
jar --create --file mods/demos.pm.jar --main-class demos.pm.model.ProductManager -C mods/demos.pm .

REM Crear JAR para demos.shop
echo Generando demos.shop.jar...
jar --create --file mods/demos.shop.jar --main-class demos.shop.client.ShopApp -C mods/demos.shop .

REM Crear JAR para service
echo Generando service.jar...
jar --create --file mods/service.jar -C mods/service .

REM Crear JAR para provider
echo Generando provider.jar...
jar --create --file mods/provider.jar -C mods/provider .

REM Crear JAR para application
echo Generando application.jar...
jar --create --file mods/application.jar --main-class application.Main -C mods/application .

echo.
echo Todos los JARs han sido generados exitosamente!
echo.
echo Para ejecutar la aplicación:
echo java --module-path mods -m application
echo.
pause
