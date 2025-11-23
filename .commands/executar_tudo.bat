@echo off

set MODULE=veklambda

echo ==========================================
echo CONFIGURACOES INICIAIS
echo ==========================================
echo.

echo Modulo definido: %MODULE%
echo.


echo ==========================================
echo         BUILD E DEPLOY DO MODULO
echo ==========================================

echo ===== PROCESSANDO MODULO: %MODULE% =====
echo.

call maven.bat
cd .commands
call docker.bat

echo ==========================================
echo         BUILD E DEPLOY COMPLETOS
echo ==========================================
pause
