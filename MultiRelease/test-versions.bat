@echo off
echo Testing Multi-Release JAR with different Java versions...
echo.

REM Verificar si Docker está disponible
docker --version >nul 2>&1
if errorlevel 1 (
    echo Docker no está disponible. Usando Java local...
    goto :local_test
)

echo Using Docker to test different Java versions...
echo.

REM Crear un directorio temporal para el JAR
if not exist "test-temp" mkdir test-temp
copy multirelease-demo.jar test-temp\

echo Testing with Java 8...
docker run --rm -v "%cd%\test-temp:/app" openjdk:8 java -jar /app/multirelease-demo.jar

echo.
echo Testing with Java 9...
docker run --rm -v "%cd%\test-temp:/app" openjdk:9 java -jar /app/multirelease-demo.jar

echo.
echo Testing with Java 10...
docker run --rm -v "%cd%\test-temp:/app" openjdk:10 java -jar /app/multirelease-demo.jar

echo.
echo Testing with Java 11...
docker run --rm -v "%cd%\test-temp:/app" openjdk:11 java -jar /app/multirelease-demo.jar

echo.
echo Testing with Java 17...
docker run --rm -v "%cd%\test-temp:/app" openjdk:17 java -jar /app/multirelease-demo.jar

echo.
echo Testing with Java 21...
docker run --rm -v "%cd%\test-temp:/app" openjdk:21 java -jar /app/multirelease-demo.jar

REM Limpiar
rmdir /s /q test-temp
goto :end

:local_test
echo Testing with local Java version...
java -jar multirelease-demo.jar

echo.
echo To test with different versions, install multiple Java versions or use Docker.
echo.

:end
pause
