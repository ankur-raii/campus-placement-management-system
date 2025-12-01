@echo off
echo ========================================
echo Campus Placement Management System
echo ========================================
echo.
echo Starting Campus Placement Management System...
echo.
echo ----------------------------------------
echo Make sure Oracle Database is running!
echo ----------------------------------------
echo.

REM ---- Build runtime classpath ----
REM If your JDBC jar is ojdbc6.jar, change ojdbc8.jar to ojdbc6.jar below

set CP=dist\trandpl.jar;lib\ojdbc8.jar;lib\AbsoluteLayout.jar

REM ---- Run the main GUI window ----
java -cp "%CP%" trandpl.gui.LoginScreenFrame

echo.
echo ========================================
echo Application terminated
echo ========================================
pause
