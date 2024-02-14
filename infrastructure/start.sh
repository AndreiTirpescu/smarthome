echo "Stop current running to avoid port conflict"
docker stop $(docker ps -q)

echo "Starting smarthome dev containers"
docker start smarthome-db  
