<?php
header('Content-Type: application/json');
$metodo = $_SERVER['REQUEST_METHOD'];

switch ($metodo) {
    case 'GET':
        try {
            $conn = new PDO('mysql:host=localhost;dbname=recuperau2;charset=utf8', 'root', '');

        } catch (PDOException $e) {
            echo $e->getMessage();

        }
        if (isset($_GET['id'])) {
            $pstm = $conn->prepare("SELECT avg(g.grade) as 'el promedio del alumno es' FROM gardes as g inner join 
            students as s on student_id = s.id inner join subjects as sb on subject_id = sb.id inner 
            join teachers as t on s.teacher_id = t.id where s.id = :id;");
            $pstm->bindParam(':id', $_GET['id']);
            $pstm->execute();
            $rs =$pstm->fetchAll(PDO::FETCH_ASSOC);
            if ($rs != null) {

                echo json_encode($rs, JSON_PRETTY_PRINT);

            }else {
                echo 'No se encontro esta materia';
            }
        }else {
            $pstm = $conn->prepare('SELECT avg(grade) as " es promedio de todos los alumnos es" FROM gardes;');
            $pstm->execute();
            $rs = $pstm->fetchAll(PDO::FETCH_ASSOC);
            echo json_encode($rs, JSON_PRETTY_PRINT);

        }
        exit();
        break;
    
    default:
        echo  'Metopdo no soportado';
        break;
}

