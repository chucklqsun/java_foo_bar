FROM mcr.microsoft.com/windows:1903

LABEL maintainer="Chuck"

WORKDIR C:/

COPY jre-8u231-windows-x64.exe C:/
COPY IEDriverServer.exe C:/
COPY selenium-server-standalone-3.9.1.jar C:/
COPY reg_env.bat C:/
COPY selenium_grid.bat C:/

RUN ["reg_env.bat"]
RUN ["jre-8u231-windows-x64.exe", "/s"]


CMD selenium_grid.bat

