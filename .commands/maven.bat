@echo off

set MODULE=veklambda

echo ===========================
echo        MAVEN BUILD
echo ===========================

REM sobe um nível para a pasta do projeto
cd ..

REM executa comando de build do maven
mvn clean package -DskipTests

if errorlevel 1 (
	goto error_exit
)

goto success_exit

:error_exit
echo.
echo ===========================
echo    MAVEN BUILD ERROR
echo ===========================
timeout /t 30
pause


:success_exit
echo.
echo ===========================
echo    MAVEN BUILD DONE
echo ===========================
pause

