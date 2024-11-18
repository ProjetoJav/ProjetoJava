@echo off
setlocal
set JAVA_HOME=C:\Program Files\Java\jdk1.8.0_202
set PATH=%JAVA_HOME%\bin;%PATH%
java -cp "C:\Users\julio\Documents\NetBeansProjects\ProjetoJava-main (1)\dist\ProjetoRetifica.jar;C:\Users\julio\Documents\NetBeansProjects\ProjetoJava-main (1)\dist\lib\*" projetoretifica.ProjetoRetifica
endlocal
pause
