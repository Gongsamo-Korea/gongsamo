#!/bin/bash

docker network ls | grep elastic > /dev/null

if [ $? -ne 0 ]; then
    docker network create elastic
fi

docker compose down
docker compose up -d