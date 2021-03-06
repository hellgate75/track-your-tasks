CREATE DATABASE IF NOT EXISTS MS_TASKS;
USE MS_TASKS;
DROP TABLE IF EXISTS `TASKS`;
CREATE TABLE `TASKS` (
  id BIGINT NOT NULL AUTO_INCREMENT,
  taskId VARCHAR(50) NOT NULL,
  duration BIGINT NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
DROP TABLE IF EXISTS `AVERAGE`;
CREATE TABLE `AVERAGE` (
  taskId VARCHAR(50) NOT NULL,
  average DECIMAL(12,5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
GRANT ALL ON MS_TASKS.* TO 'ms_tasks'@'%';