@echo off
setlocal enabledelayedexpansion

echo Testing Multi-Release JAR with all available Java versions...
echo.

REM Verificar que el JAR existe
if not exist "multirelease-demo.jar" (
    echo Error: multirelease-demo.jar no encontrado.
    echo Ejecuta build-multirelease.bat primero.
    pause
    exit /b 1
)

echo Available Java versions:
echo.

REM Buscar Java en PATH
java -version 2>&1 | findstr "version" >nul
if not errorlevel 1 (
    echo [1] Java en PATH:
    java -version 2>&1 | findstr "version"
    echo.
)

REM Buscar Java en directorios comunes
set "java_dirs=C:\Program Files\Java;C:\Program Files (x86)\Java;C:\graalvm-jdk-21.0.8+12.1\bin"
set "counter=2"

for %%d in (%java_dirs%) do (
    if exist "%%d" (
        for /d %%j in ("%%d\*") do (
            if exist "%%j\bin\java.exe" (
                echo [!counter!] %%j:
                "%%j\bin\java.exe" -version 2>&1 | findstr "version"
                echo.
                set /a counter+=1
            )
        )
    )
)

echo.
echo Testing with each version...
echo ========================================

REM Probar con Java en PATH
echo [1] Testing with Java in PATH:
java -jar multirelease-demo.jar
echo.

REM Probar con Java en directorios específicos
set "counter=2"
for %%d in (%java_dirs%) do (
    if exist "%%d" (
        for /d %%j in ("%%d\*") do (
            if exist "%%j\bin\java.exe" (
                echo [!counter!] Testing with %%j:
                "%%j\bin\java.exe" -jar multirelease-demo.jar
                echo.
                set /a counter+=1
            )
        )
    )
)

echo ========================================
echo Testing completed!
echo.
pause
