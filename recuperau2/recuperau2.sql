CREATE SCHEMA IF NOT EXISTS `recuperau2` DEFAULT CHARACTER SET utf8 ;
USE `recuperau2` ;

-- tabla maestros
CREATE TABLE IF NOT EXISTS `teachers` (
  `id` BIGINT primary key AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `surname` VARCHAR(45) NOT NULL,
  `lastname` VARCHAR(45) NOT NULL,
  `birthday` DATE NOT NULL,
  `curp` VARCHAR(45) NOT NULL unique);

-- tabla estudiantes
CREATE TABLE IF NOT EXISTS `students` (
  `id` BIGINT primary key AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `surname` VARCHAR(45) NOT NULL,
  `lastname` VARCHAR(45) NOT NULL,
  `birthday` DATE NOT NULL,
  `curp` VARCHAR(45) NOT NULL unique,
  `matricula` VARCHAR(45) NOT NULL unique,
  `teacher_id` BIGINT NOT NULL,
   constraint fk_teachers foreign key (teacher_id) references teachers (id));


-- tabla materias
CREATE TABLE IF NOT EXISTS `recuperau2`.`subjects` (
  `id` BIGINT primary key AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL);


-- tabla calificaciones
CREATE TABLE IF NOT EXISTS `recuperau2`.`gardes` (
  `id` BIGINT primary key AUTO_INCREMENT,
  `grade` DOUBLE NOT NULL,
  `subject_id` BIGINT NOT NULL,
  `student_id` BIGINT NOT NULL,
   constraint fk_subjects foreign key (subject_id) references subjects (id),
    constraint fk_students foreign key (student_id) references students (id));
