FROM mcr.microsoft.com/windows:1903

LABEL maintainer="Chuck"

WORKDIR C:/

COPY FirefoxSetup70.0.1.exe C:/
COPY ChromeStandaloneSetup64.exe C:/
COPY jre-8u231-windows-x64.exe C:/

COPY geckodriver.exe C:/
COPY IEDriverServer.exe C:/
COPY chromedriver.exe C:/

COPY selenium-server-standalone-3.9.1.jar C:/

RUN ["powershell", "Set-Service 'wuauserv' -StartupType Automatic"]
RUN ["powershell", "Start-Service -Name wuauserv"]
RUN ["powershell", "DISM.exe /Online /Add-Capability /CapabilityName:Microsoft.WebDriver~~~~0.0.1.0"]

RUN ["FirefoxSetup70.0.1.exe", "/S"]
RUN ["jre-8u231-windows-x64.exe", "/s"]
RUN ["ChromeStandaloneSetup64.exe", "/silent", "/install"]

COPY reg_env.bat C:/
RUN ["reg_env.bat"]
COPY wrap.bat C:/

ENTRYPOINT ["wrap.bat"]

