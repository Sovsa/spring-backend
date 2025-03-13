## Docker compoase script for running application via docker compose:

```
services:
  spring-backend:
    image: spring-backend
    build: ./spring-backend
    restart: always
    ports:
      - 3000:3000
    networks:
      - springbackend-net
    depends_on:
      - mysqldb
    environment:
      MYSQL_HOST: mysqldb
      MYSQL_USER: mysqldb.MYSQL_USER
      MYSQL_PASSWORD: mysqldb.MYSQL_PASSWORD
      MYSQL_PORT: 3306

  mysqldb:
    container_name: mysqldb
    image: mysql:8.4.3
    volumes:
      - mysqlvolume:/var/lib/mysql
    ports:
      - '3306:3306'
    networks:
      - springbackend-net
    environment:
      MYSQL_DATABASE: ingredientdb
      MYSQL_USER: springuser
      MYSQL_PASSWORD: 5SpeckledFrogs
      MYSQL_ROOT_PASSWORD: root

  spring-client:
    environment:
      BACKEND_URL: http://host.docker.internal:3000
    networks:
      - springbackend-net
    ports:
      - 8080:3001
    image: spring-client
    build: ./spring-client
    restart: always
    depends_on:
      - spring-backend
networks:
  springbackend-net:
volumes: 
  mysqlvolume: 
```
