#!/bin/bash

echo "=== Compilando Demo de Módulos Java ==="

# Crear directorio de salida si no existe
mkdir -p target/classes

# Compilar todos los módulos
echo "Compilando módulo core..."
javac -d target/classes src/main/java/com/bcp/modules/core/module-info.java src/main/java/com/bcp/modules/core/*.java

echo "Compilando módulo utils..."
javac -d target/classes --module-path target/classes --add-modules com.bcp.modules.core src/main/java/com/bcp/modules/utils/module-info.java src/main/java/com/bcp/modules/utils/*.java

echo "Compilando módulo service..."
javac -d target/classes --module-path target/classes --add-modules com.bcp.modules.core src/main/java/com/bcp/modules/service/module-info.java src/main/java/com/bcp/modules/service/*.java

echo "Compilando módulo client..."
javac -d target/classes --module-path target/classes --add-modules com.bcp.modules.core,com.bcp.modules.service,com.bcp.modules.utils src/main/java/com/bcp/modules/client/module-info.java src/main/java/com/bcp/modules/client/*.java

echo ""
echo "=== Ejecutando Demo Principal ==="
java --module-path target/classes --module com.bcp.modules.client/com.bcp.modules.client.ModuleDemo

echo ""
echo "=== Ejecutando Demo de Reflection ==="
java --module-path target/classes --module com.bcp.modules.client/com.bcp.modules.client.ReflectionDemo
