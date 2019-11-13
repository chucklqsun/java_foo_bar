reg add "HKEY_LOCAL_MACHINE\SOFTWARE\Wow6432Node\Microsoft\Internet Explorer\Main\FeatureControl\FEATURE_BFCACHE" /v iexplore.exe /t REG_DWORD /d 0  /f
reg add "HKEY_CURRENT_USER\Software\Microsoft\Windows\CurrentVersion\Internet Settings\Zones\0" /v 2500 /t REG_DWORD /d 3 /f
reg add "HKEY_CURRENT_USER\Software\Microsoft\Windows\CurrentVersion\Internet Settings\Zones\1" /v 2500 /t REG_DWORD /d 3 /f
reg add "HKEY_CURRENT_USER\Software\Microsoft\Windows\CurrentVersion\Internet Settings\Zones\2" /v 2500 /t REG_DWORD /d 3 /f
reg add "HKEY_CURRENT_USER\Software\Microsoft\Windows\CurrentVersion\Internet Settings\Zones\3" /v 2500 /t REG_DWORD /d 3 /f
reg add "HKEY_CURRENT_USER\Software\Microsoft\Windows\CurrentVersion\Internet Settings\Zones\4" /v 2500 /t REG_DWORD /d 3 /f
reg add "HKEY_LOCAL_MACHINE\SOFTWARE\Microsoft\Internet Explorer\Main" /v DisableFirstRunCustomize /t REG_DWORD /d 1 /f

start java -jar selenium-server-standalone-3.9.1.jar -port 4444 -role hub
start java -jar selenium-server-standalone-3.9.1.jar -port 4458 -role node  -hub http://127.0.0.1:4444/grid/register -hubHost 127.0.0.1 -hubPort 4444 -browser  "browserName=internet explorer,version=11,platform=WINDOWS,maxInstances=10"

c:\windows\system32\cmd.exe