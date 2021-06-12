FROM postgres:13
ENV POSTGRES_PASSWORD postgres
COPY tables.sql /docker-entrypoint-initdb.d/
EXPOSE 5432