# dump build stage
FROM postgres:11-alpine 
ENV POSTGRES_PASSWORD password
ENV POSTGRES_DATABASE postgres
ENV POSTGRES_USER postgres
COPY create_tables.sql /docker-entrypoint-initdb.d/create_tables.sql

EXPOSE 5430
CMD ["-p","5430"]