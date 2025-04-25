@echo off
setlocal

:: Configurar Maven local
set "MAVEN_DIR=%~dp0apache-maven-3.6.3"
set "PATH=%MAVEN_DIR%\bin;%PATH%"

:: Configurar JDK local
set "JAVA_HOME=%~dp0jdk11"
set "PATH=%JAVA_HOME%\bin;%PATH%"

echo Usando Java de: %JAVA_HOME%
java -version

echo Compilando la aplicación...
call mvn clean package

set "FIXED_JAR=escribanos-webclient-jwt-0.0.1-SNAPSHOT.jar"
set "TARGET_DIR=target"

:: Buscar y renombrar
for %%f in (%TARGET_DIR%\*.jar) do (
    echo Renombrando %%~nxf a %FIXED_JAR%...
    move /Y "%%~f" "%TARGET_DIR%\%FIXED_JAR%"
    goto ejecutar
)

echo ERROR: No se encontró ningún .jar en la carpeta target\
goto fin

:ejecutar
echo Ejecutando %FIXED_JAR%...
java -jar "%TARGET_DIR%\%FIXED_JAR%"

:fin
endlocal
pause
