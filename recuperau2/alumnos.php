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
        //consulta solo 1
        if (isset($_GET['id'])) {
            $pstm = $conn->prepare("SELECT s.*, t.name as 'teachers name' FROM students as s inner 
            join teachers as t on s.teacher_id = t.id WHERE s.id = :id");
            $pstm->bindParam(':id', $_GET['id']);
            $pstm->execute();
            $rs =$pstm->fetchAll(PDO::FETCH_ASSOC);
            if ($rs != null) {
                echo json_encode($rs[0], JSON_PRETTY_PRINT);

            }else {
                echo 'No se encontro este profesor';
            }
            //consulta todos
        }else {
            $pstm = $conn->prepare('SELECT s.*, t.name as "teachers name" FROM students as s inner join teachers as t on s.teacher_id = t.id;');
            $pstm->execute();
            $rs = $pstm->fetchAll(PDO::FETCH_ASSOC);
            echo json_encode($rs, JSON_PRETTY_PRINT);
            $prueba = $rs[0].teacher_id; 

        }
        //consulta fk
        try {
            $conn = new PDO('mysql:host=localhost;dbname=recuperau2;charset=utf8', 'root', '');

        } catch (PDOException $e) {
            echo $e->getMessage();
        }

            $pstm = $conn->prepare("SELECT * FROM teachers");
            $pstm->execute();
            $rs = $pstm->fetchAll(PDO::FETCH_ASSOC);
            if ($rs != null) {
                echo json_encode($rs, JSON_PRETTY_PRINT);
            } else {
                echo "No hay registros.";
            }
        exit();
        break;

        case 'POST':
            $jsonData =json_decode(file_get_contents('php://input'));
            try {
                $conn = new PDO('mysql:host=localhost;dbname=recuperau2;charset=utf8', 'root', '');
    
            } catch (PDOException $e) {
                echo $e->getMessage();
    
            }
            $pstm = $conn->prepare("INSERT INTO `students` (`name`, `surname`, `lastname`, `birthday`,
            `curp`, matricula, teacher_id) VALUES (:name, :surname, :lastname, :birthday, :curp,
            :matricula, :teacher_id);");
            $pstm->bindParam(":name", $jsonData->name);
            $pstm->bindParam(":surname", $jsonData->surname);
            $pstm->bindParam(":lastname", $jsonData->lastname);
            $pstm->bindParam(":birthday", $jsonData->birthday);
            $pstm->bindParam(":curp", $jsonData->curp);
            $pstm->bindParam(":matricula", $jsonData->matricula);
            $pstm->bindParam(":teacher_id", $jsonData->teacher_id);
            $rs =$pstm->execute();
            if ($rs) {
                $_POST["error"] = false;
                $_POST["message"] = "Alumno registrado correctamente";
                $_POST["status"] = 200;
            } else {
                $_POST["error"] = true;
                $_POST["message"] = "Error al registrar alumno";
                $_POST["status"] = 400;
            }
            echo json_encode($_POST);
            break;

        case 'PUT':
            $jsonData =json_decode(file_get_contents('php://input'));
            try {
                $conn = new PDO('mysql:host=localhost;dbname=recuperau2;charset=utf8', 'root', '');
    
            } catch (PDOException $e) {
                echo $e->getMessage();
    
            }
            $pstm = $conn->prepare("UPDATE students SET `name` = :name, `surname` = :surname, 
            `lastname`= :lastname, `birthday` = :birthday, `curp` = :curp,
            `matricula` =:matricula, `teacher_id` = :teacher_id WHERE `id` = :id;");
             $pstm->bindParam(":name", $jsonData->name);
             $pstm->bindParam(":surname", $jsonData->surname);
             $pstm->bindParam(":lastname", $jsonData->lastname);
             $pstm->bindParam(":birthday", $jsonData->birthday);
             $pstm->bindParam(":curp", $jsonData->curp);
             $pstm->bindParam(":matricula", $jsonData->matricula);
             $pstm->bindParam(":teacher_id", $jsonData->teacher_id);
             $pstm->bindParam(":id", $jsonData->id);
             $rs =$pstm->execute();
             if ($rs) {
                echo ("Alumno actualizado correctamente. Code $rs");
            } else {
                echo ("Error al actualizar alumno. Code $rs");
            }
            break;
    
    default:
        echo  'Metopdo no soportado';
        break;
}

