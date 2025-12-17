# 🧬 Projeto VekRest - VekLambda - Módulo 3

Lambda VekRest: consumer kafka via Spring Boot com Docker e Maven. **Módulo 3 / Lambda**

## 🧩 PARTES DO MÓDULO 2
| Aplicação | Descrição                              | Link                              |
|-----------|----------------------------------------|-----------------------------------|
| VekLambda | Lambda (este projeto) - Consumer Kafka | Este Repositório |

> Este projeto não depende de nenhuma outra aplicação para funcionar corretamente

---

# 1.✨ Imagem Docker (DockerHub)

> A imagem desta aplicação é atualizada a cada nova tag ou pull request na [branch main](https://github.com/VekRest/vekrest-veklambda-modulo3/tree/main)

> Link da imagem no DockerHub: [vek03/vekrest-veklambda:latest](https://hub.docker.com/r/vek03/vekrest-veklambda)

---

## 1.1 🧩 Containers necessários para rodar a aplicação:

| Container  | Imagem                               | Link                                                                                                                                           | 
|------------|--------------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------|
| MongoDB    | `mongo:latest`                       | https://hub.docker.com/_/mongo                                                                                                                 |
| OpenSearch | `opensearchproject/opensearch:2.4.0` | https://hub.docker.com/layers/opensearchproject/opensearch/2.4.0/images/sha256-c8681472b70d46e7de61fe770d288a972f84b3f122f3c74ca06ea525264b6fd5 |
| Graylog    | `graylog/graylog:5.1.5`              | https://hub.docker.com/layers/graylog/graylog/5.1.5/images/sha256-3b6967572e88731eacfa661e6d7ca41da3e259bc5eb041e58fb10e4deb823dcb             |
| Zookeeper  | `confluentinc/cp-zookeeper:7.5.0`    | https://hub.docker.com/layers/confluentinc/cp-zookeeper/7.5.0/images/sha256-d18e7b3a81326dd278a5f2121b29a7f009582e0b0f5552165eb5efc83533a52b |
| Kafka      | `confluentinc/cp-kafka:7.5.0`        | https://hub.docker.com/layers/confluentinc/cp-kafka/7.5.0/images/sha256-69022c46b7f4166ecf21689ab4c20d030b0a62f2d744c20633abfc7c0040fa80 |

---

## 1.2 ⚙ Variáveis de ambiente necessárias para rodar o container:

| Variável           | Descrição                        | Exemplo         |
|--------------------|----------------------------------|-----------------|
| `KAFKA_BROKERS`    | Endereço do broker Kafka         | `kafka:9092`    |
| `KAFKA_REPLICAS`   | Número de réplicas do Kafka      | `1`             |
| `KAFKA_PARTITIONS` | Partição do tópico Kafka         | `3`             |
| `GRAYLOG_HOST`     | Endereço do Graylog              | `graylog`       |
| `GRAYLOG_PORT`     | Porta do Graylog                 | `12201`         |

---

## 1.3 🐳 Como rodar o container

1️⃣ Para baixar a imagem do Docker Hub:
```bash
docker pull vek03/vekrest-veklambda:latest
```

2️⃣ Para rodar o container localmente:
```bash
docker run -d \
  --name veklambda \
    -e KAFKA_BROKERS=kafka:9092 \
    -e KAFKA_REPLICAS=1 \
    -e KAFKA_PARTITIONS=3 \
    -e GRAYLOG_HOST=graylog \
    -e GRAYLOG_PORT=12201 \
  vek03/vekrest-veklambda:latest
```

3️⃣ Alternativamente, você pode adicionar o serviço no seu docker-compose.yml local, descomentando ou adicionando o seguinte trecho:
```bash
services:
  veklambda:
    image: vek03/vekrest-veklambda:latest
    hostname: veklambda
    container_name: veklambda
    environment:
      KAFKA_BROKERS: kafka:9092
      KAFKA_REPLICAS: 1
      KAFKA_PARTITIONS: 3
      GRAYLOG_HOST: graylog
      GRAYLOG_PORT: 12201
    depends_on:
      mongodb:
        condition: service_healthy
      opensearch:
        condition: service_healthy
      graylog:
        condition: service_started
      zookeeper:
        condition: service_healthy
      kafka:
        condition: service_healthy
```

4️⃣ Depois de adicionar o serviço em docker-compose.yml, suba os containers:
```bash
docker-compose up -d
```

---

## 📦 Instalação e Configuração do Ambiente

### 1️⃣ Clone o projeto na sua máquina e baixe as dependências:
```bash
# Clonar
git clone https://github.com/VekRest/vekrest-veklambda-modulo3.git

# Acesse a pasta do projeto
cd vekrest-veklambda-modulo3
````

### 2️⃣ Suba os containers necessários e Rode o projeto na sua IDE de preferência (ou via comando Maven)
```bash
# Suba os containers necessários (MongoDB, Redis, OpenSearch, Graylog)
docker-compose up -d

# Agora abra o projeto na sua IDE (IntelliJ, Eclipse, VSCode, etc) e rode a aplicação Spring Boot
# Ou, se preferir, rode via terminal com properties-local:
mvn spring-boot:run -pl spring -Dspring-boot.run.jvmArguments="-Dspring.profiles.active=local"
```

### 3️⃣ (Opcional) Alternativamente, se quiser rodar via container localmente:
```bash
# Dentro da pasta do projeto:
mvn clean package -DskipTests

# Agora faça deploy no Docker local:
docker build -t vekrest/veklambda:latest .

# Descomente as últimas linhas do docker-compose.yml (relacionadas ao veklambda) e rode:
docker-compose up -d
```

> Ou execute o script .bat (executar_tudo.bat) na pasta .commands para automatizar o processo.

### 4️⃣ (Opcional) Caso deseje, pode rodar o SonarQube localmente

```bash
# Após configurar o pom.xml com as informações do Sonar em Properties:
mvn clean install sonar:sonar -Dsonar.token={TOKEN_SONAR}
```

---

## 📨 Como testar o Consumer Kafka

> Com a aplicação rodando, você pode enviar mensagens para o tópico Kafka "client.registered" e verificar se o consumer está recebendo as mensagens corretamente.

### Exemplos de mensagens para enviar ao tópico Kafka

#### Exemplo de mensagem JSON para enviar ao tópico Kafka
```json
{
  "name": "Vek",
  "birth": "2023-01-01",
  "address": {
    "cep": "03759040",
    "state": "SP"
  }
}
```

#### Exemplo de mensagem hexadecimal para enviar ao tópico Kafka
```bash
# key
6b6579

# value
7b226e616d65223a2256656b222c226269727468223a22323032332d30312d3031222c2261646472657373223a7b22636570223a223033373539303430222c227374617465223a225350227d7d
```

### Comandos para enviar a mensagem via terminal:
```bash
# Acesse o container do Kafka
docker exec -it kafka bash

# Envie uma mensagem para o tópico "client.registered"
echo '{"name":"Vek","birth":"2023-01-01","address":{"cep":"03759040","state":"SP"}}' | kafka-console-producer --broker-list localhost:9093 --topic client.registered

# Você deve ver a mensagem sendo consumida no console da aplicação VekLambda
```

---

## 🧩 Tecnologias Utilizadas

- **Spring Boot** → Framework Back-End
- **Java** → Linguagem de programação
- **Maven** → Build
- **Docker** → Containers e virtualização
- **GraalVM** → Compilação nativa (opcional)
- **Docker Hub** → Repositório de imagens Docker
- **Kafka** → Mensageria
- **Zookeeper** → Gerenciamento do Kafka
- **MongoDB** → Banco de Dados NoSQL
- **OpenSearch e Graylog** → Logs da Aplicação
- **SonarQube** → Qualidade
- **Github Actions** → CI/CD automatizado
- **.bat** → Scripts para automatizar processos no Windows

---

## ✅ Qualidade (SonarQube)

> Este projeto tem qualidade analisada pelo SonarQube Cloud. Verifique nos badges!

[![SonarQube Cloud](https://sonarcloud.io/images/project_badges/sonarcloud-dark.svg)](https://sonarcloud.io/summary/new_code?id=veklambda)

---

## 📦 Esteira CI/CD Automatizada com Github Actions

> A esteira CI/CD deste projeto é automatizada via Github Actions. A cada tag criada a esteira é disparada.

### Para executar a Esteira pelo trigger:
```bash
# Exemplo: Cria a tag
git tag <version>

# Envia a tag para o repositório remoto
git push origin <version>
```

[![VekLambda CI/CD Workflow](https://github.com/VekRest/vekrest-veklambda-modulo3/actions/workflows/main.yml/badge.svg)](https://github.com/VekRest/vekrest-veklambda-modulo3/actions/workflows/main.yml)

---