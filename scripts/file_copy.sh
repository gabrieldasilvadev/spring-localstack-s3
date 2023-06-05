#!/bin/bash

# Compacta os arquivos CSV no contêiner sem o diretório
docker exec localstack bash -c "tar -czvf /tmp/csv_files.tar.gz -C /opt/code/localstack/ --transform='s#.*/##' *.csv"

# Copia o arquivo compactado para o host
docker cp localstack:/tmp/csv_files.tar.gz ./files/

# Extrai os arquivos CSV do arquivo compactado
tar -xzvf ./files/csv_files.tar.gz -C ./files/

# Remove o arquivo compactado
rm ./files/csv_files.tar.gz
