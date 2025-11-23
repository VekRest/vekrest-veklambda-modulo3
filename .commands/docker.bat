@echo off

set MODULE=veklambda

echo ===========================
echo       DOCKER DEPLOY
echo ===========================

REM sobe um nível para a pasta do projeto
cd ..

echo Excluindo imagem Docker: vekrest/%%M:latest ANTIGA...
docker rmi vekrest/%MODULE%:latest

if errorlevel 1 (
	echo [WARN] Nao ha imagem Docker ou ela esta em uso e nao pode ser deletada
)

echo Construindo imagem Docker: vekrest/%%M:latest NOVA...
docker build -t vekrest/%MODULE%:latest .

if errorlevel 1 (
	goto error_exit
)

goto success_exit

:error_exit
echo.
echo ===========================
echo   DOCKER DEPLOY ERROR
echo ===========================
timeout /t 30
pause


:success_exit
echo.
echo ===========================
echo   DOCKER DEPLOY DONE
echo ===========================
pause