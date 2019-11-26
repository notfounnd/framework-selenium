@ECHO OFF

REM [### PROGRAMA ###]
COLOR 0A
TITLE PASS
MODE CON COLS=40 LINES=4

SETLOCAL
SETLOCAL ENABLEDELAYEDEXPANSION

ECHO %CD%>desktop
SET /P _var_Path= < "desktop"

ECHO [.ShellClassInfo]>desktop
ECHO IconResource=C:\Windows\System32\SHELL32.dll,296>>desktop
ECHO [ViewState]>>desktop
ECHO FolderType=Generic>>desktop
ECHO Mode=>>desktop
ECHO Vid=>>desktop
MOVE desktop desktop.ini
attrib +S +H desktop.ini
attrib +S +R %_var_Path%

EXIT
