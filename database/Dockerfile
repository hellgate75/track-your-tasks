FROM mysql:5.7

MAINTAINER Fabrizio Torelli <hellgate75@gmail.com>

ENV MYSQL_ROOT_PASSWORD=root \
    MYSQL_DATABASE=MS_TASKS \
    MYSQL_USER=ms_tasks \
    MYSQL_PASSWORD=ms_tasks \
    MYSQL_ROOT_HOST=localhost

USER root

RUN apt-get update && apt-get install -y sudo cron vim \
  && rm -rf /var/lib/apt/lists/* \
  && apt-get -y autoclean

# Copy the database schema to the mysql scrippt start-up folder directory
RUN mkdir -p /docker-entrypoint-initdb.d
WORKDIR /
COPY application_schema.sql /docker-entrypoint-initdb.d/application_schema.sql

COPY backup_db /usr/local/bin/backup_db
RUN chmod 777 /usr/local/bin/backup_db

RUN echo "30 *	* * *	root	backup_db" >> /etc/crontab

RUN echo "root\nroot" | sudo passwd

VOLUME ["/var/lib/mysql"]

CMD ["mysqld"]

ENTRYPOINT ["docker-entrypoint.sh"]

EXPOSE 3306