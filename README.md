docker build . -t test-on-windows:latest

start docker run --name grid_hub --rm -p 4444:4444 test-on-windows java -jar selenium-server-standalone-3.9.1.jar -port 4444 -role hub


## assume your host ip is 192.168.43.35

## start IE node
start docker run --rm test-on-windows java -jar selenium-server-standalone-3.9.1.jar -port 4458 -role node -hub http://192.168.43.35:4444/grid/register -hubHost 192.168.43.35 -hubPort 4444 -browser  "browserName=internet explorer,version=11,platform=WINDOWS,maxInstances=10"

## start Firefox node
start docker run --rm test-on-windows java -jar selenium-server-standalone-3.9.1.jar -port 4458 -role node -hub http://192.168.43.35:4444/grid/register -hubHost 192.168.43.35 -hubPort 4444 -browser  "browserName=firefox,version=70.0.1,platform=WINDOWS,maxInstances=10"

## start Chrome node
start docker run --rm test-on-windows java -jar selenium-server-standalone-3.9.1.jar -port 4458 -role node -hub http://192.168.43.35:4444/grid/register -hubHost 192.168.43.35 -hubPort 4444 -browser  "browserName=chrome,version=78,platform=WINDOWS,maxInstances=10"

## start Edge node
start docker run --rm test-on-windows java -jar selenium-server-standalone-3.9.1.jar -port 4458 -role node -hub http://192.168.43.35:4444/grid/register -hubHost 192.168.43.35 -hubPort 4444 -browser  "browserName=MicrosoftEdge,version=44,maxInstances=10"

## stop all container
FOR /f "tokens=*" %i IN ('docker ps -q') DO docker stop %i

## Use docker-compose to scale
docker-compose up
docker-compose scale chrome=3
docker-compose down

