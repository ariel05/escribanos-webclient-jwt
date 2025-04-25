#!/bin/bash
set -e

DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"

# Configurar JDK y Maven locales
export PATH="$JAVA_HOME/bin:$MAVEN_HOME/bin:$PATH"

echo "Usando Java en $JAVA_HOME"
java -version

echo "Compilando la aplicación..."
mvn clean package

TARGET_DIR="$DIR/target"
FIXED_JAR="escribanos-webclient-jwt-0.0.1-SNAPSHOT.jar"

# Renombrar el .jar si es necesario
ORIGINAL_JAR=$(find "$TARGET_DIR" -maxdepth 1 -name "*.jar" ! -name "$FIXED_JAR" | head -n 1)
if [ -f "$ORIGINAL_JAR" ]; then
  echo "Renombrando $(basename "$ORIGINAL_JAR") a $FIXED_JAR..."
  mv -f "$ORIGINAL_JAR" "$TARGET_DIR/$FIXED_JAR"
fi

# Ejecutar el JAR
if [ -f "$TARGET_DIR/$FIXED_JAR" ]; then
  echo "Ejecutando $FIXED_JAR..."
  java -jar "$TARGET_DIR/$FIXED_JAR"
else
  echo "ERROR: No se encontró el JAR esperado ($FIXED_JAR)."
fi
