@echo off
echo Building Multi-Release JAR...
echo.

REM Crear directorios temporales
if not exist "temp" mkdir temp
if not exist "temp\classes" mkdir temp\classes
if not exist "temp\classes-9" mkdir temp\classes-9
if not exist "temp\classes-10" mkdir temp\classes-10
if not exist "temp\classes-11" mkdir temp\classes-11

REM Compilar versión por defecto (Java 8 compatible)
echo Compiling default version (Java 8 compatible)...
javac -d temp\classes src\main\java\module-info.java src\main\java\demos\data\*.java

REM Compilar versión Java 9
echo Compiling Java 9 version...
javac -d temp\classes-9 --release 9 src\main\java-9\demos\data\*.java

REM Compilar versión Java 10
echo Compiling Java 10 version...
javac -d temp\classes-10 --release 10 src\main\java-10\demos\data\*.java

REM Compilar versión Java 11
echo Compiling Java 11 version...
javac -d temp\classes-11 --release 11 src\main\java-11\demos\data\*.java

REM Crear el Multi-Release JAR
echo Creating Multi-Release JAR...
jar --create --file multirelease-demo.jar --manifest META-INF\MANIFEST.MF -C temp\classes .

REM Agregar versiones específicas
echo Adding Java 9 version...
jar --update --file multirelease-demo.jar -C temp\classes-9 demos

echo Adding Java 10 version...
jar --update --file multirelease-demo.jar -C temp\classes-10 demos

echo Adding Java 11 version...
jar --update --file multirelease-demo.jar -C temp\classes-11 demos

REM Limpiar archivos temporales
echo Cleaning up...
rmdir /s /q temp

echo.
echo Multi-Release JAR created: multirelease-demo.jar
echo.
echo To test with different Java versions:
echo java -jar multirelease-demo.jar
echo.
pause
