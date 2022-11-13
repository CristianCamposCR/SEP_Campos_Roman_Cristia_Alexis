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
            $pstm = $conn->prepare("SELECT  s.*,sb.name, t.name as teachersName, g.grade FROM gardes as g inner join 
            students as s on student_id = s.id inner join subjects as sb on subject_id = sb.id inner 
            join teachers as t on s.teacher_id = t.id where s.id = :id;");
            $pstm->bindParam(':id', $_GET['id']);
            $pstm->execute();
            $rs =$pstm->fetchAll(PDO::FETCH_ASSOC);
            if ($rs != null) {
                echo json_encode($rs, JSON_PRETTY_PRINT);

            }else {
                echo 'No se encontro este profesor';
            }
            //consulta todos
        }else {
            $pstm = $conn->prepare('SELECT  s.*,sb.name, t.name as teachersName, g.grade FROM gardes as g inner join 
            students as s on student_id = s.id inner join subjects as sb on subject_id = sb.id inner 
            join teachers as t on s.teacher_id = t.id;');
            $pstm->execute();
            $rs = $pstm->fetchAll(PDO::FETCH_ASSOC);
            echo json_encode($rs, JSON_PRETTY_PRINT);

        }
        //consulta fk
        // try {
        //     $conn = new PDO('mysql:host=localhost;dbname=recuperau2;charset=utf8', 'root', '');

        // } catch (PDOException $e) {
        //     echo $e->getMessage();
        // }

        //     $pstm = $conn->prepare("SELECT * FROM teachers");
        //     $pstm->execute();
        //     $rs = $pstm->fetchAll(PDO::FETCH_ASSOC);
        //     if ($rs != null) {
        //         echo json_encode($rs, JSON_PRETTY_PRINT);
        //     } else {
        //         echo "No hay registros.";
        //     }
        exit();
        break;

        case 'POST':
            $jsonData =json_decode(file_get_contents('php://input'));
            try {
                $conn = new PDO('mysql:host=localhost;dbname=recuperau2;charset=utf8', 'root', '');
    
            } catch (PDOException $e) {
                echo $e->getMessage();
    
            }
            $pstm = $conn->prepare("INSERT INTO `gardes` (`grade`, `subject_id`, 
            `student_id`) VALUES (:grade, :subject_id, :student_id);");
            $pstm->bindParam(":grade", $jsonData->grade);
            $pstm->bindParam(":subject_id", $jsonData->subject_id);
            $pstm->bindParam(":student_id", $jsonData->student_id);
            $rs =$pstm->execute();
            if ($rs) {
                $_POST["error"] = false;
                $_POST["message"] = "calificacion registrado correctamente";
                $_POST["status"] = 200;
            } else {
                $_POST["error"] = true;
                $_POST["message"] = "Error al registrar calificacion";
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
            $pstm = $conn->prepare("UPDATE gardes SET `grade` = :grade, `subject_id` = :subject_id WHERE `student_id` = :id;");
             $pstm->bindParam(":grade", $jsonData->grade);
             $pstm->bindParam(":subject_id", $jsonData->subject_id);
             $pstm->bindParam(":student_id", $jsonData->student_id);
             $pstm->bindParam(":id", $jsonData->student_id);
             $rs =$pstm->execute();
             if ($rs) {
                echo ("calificacion actualizado correctamente. Code $rs");
            } else {
                echo ("Error al actualizar calificacion. Code $rs");
            }
            break;
    
    default:
        echo  'Metopdo no soportado';
        break;
}

