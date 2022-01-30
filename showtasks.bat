call runcrud.bat
if "%ERRORLEVEL%" == "0" goto chrome
echo.
echo There were problems with running script.
goto fail

:chrome
explorer "http://localhost:8080/crud/v1/tasks"
timeout 5
if "%ERRORLEVEL%" == "0" goto end
echo.
echo There were problems with running browser.
goto fail

:fail
echo.
echo There were errors

:end
echo.
echo Work is finished
