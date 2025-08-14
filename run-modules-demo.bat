@echo off
echo === Compilando Demo de Modulos Java ===

REM Crear directorio de salida si no existe
if not exist target\classes mkdir target\classes

REM Compilar todos los modulos
echo Compilando modulo core...
javac -d target\classes src\main\java\com\bcp\modules\core\module-info.java src\main\java\com\bcp\modules\core\*.java

echo Compilando modulo utils...
javac -d target\classes --module-path target\classes --add-modules com.bcp.modules.core src\main\java\com\bcp\modules\utils\module-info.java src\main\java\com\bcp\modules\utils\*.java

echo Compilando modulo service...
javac -d target\classes --module-path target\classes --add-modules com.bcp.modules.core src\main\java\com\bcp\modules\service\module-info.java src\main\java\com\bcp\modules\service\*.java

echo Compilando modulo client...
javac -d target\classes --module-path target\classes --add-modules com.bcp.modules.core,com.bcp.modules.service,com.bcp.modules.utils src\main\java\com\bcp\modules\client\module-info.java src\main\java\com\bcp\modules\client\*.java

echo.
echo === Ejecutando Demo Principal ===
java --module-path target\classes --module com.bcp.modules.client/com.bcp.modules.client.ModuleDemo

echo.
echo === Ejecutando Demo de Reflection ===
java --module-path target\classes --module com.bcp.modules.client/com.bcp.modules.client.ReflectionDemo

pause
