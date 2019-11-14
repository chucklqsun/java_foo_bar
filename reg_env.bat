setx path  "%PATH%;C:\Program Files\Java\jre1.8.0_231\bin\;C:\"
reg add "HKEY_LOCAL_MACHINE\SOFTWARE\Wow6432Node\Microsoft\Internet Explorer\Main\FeatureControl\FEATURE_BFCACHE" /v iexplore.exe /t REG_DWORD /d 0  /f
reg add "HKEY_CURRENT_USER\Software\Microsoft\Windows\CurrentVersion\Internet Settings\Zones\0" /v 2500 /t REG_DWORD /d 3 /f
reg add "HKEY_CURRENT_USER\Software\Microsoft\Windows\CurrentVersion\Internet Settings\Zones\1" /v 2500 /t REG_DWORD /d 3 /f
reg add "HKEY_CURRENT_USER\Software\Microsoft\Windows\CurrentVersion\Internet Settings\Zones\2" /v 2500 /t REG_DWORD /d 3 /f
reg add "HKEY_CURRENT_USER\Software\Microsoft\Windows\CurrentVersion\Internet Settings\Zones\3" /v 2500 /t REG_DWORD /d 3 /f
reg add "HKEY_CURRENT_USER\Software\Microsoft\Windows\CurrentVersion\Internet Settings\Zones\4" /v 2500 /t REG_DWORD /d 3 /f
reg add "HKEY_LOCAL_MACHINE\SOFTWARE\Microsoft\Internet Explorer\Main" /v DisableFirstRunCustomize /t REG_DWORD /d 1 /f
