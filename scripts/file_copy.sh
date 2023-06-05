#!/bin/bash

# Copia os arquivos CSV do contêiner para o host
docker cp localstack:/opt/code/localstack/*.csv ./localstack/files/