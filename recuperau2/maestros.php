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
            $pstm = $conn->prepare("SELECT * FROM teachers WHERE id = :id");
            $pstm->bindParam(':id', $_GET['id']);
            $pstm->execute();
            $rs =$pstm->fetchAll(PDO::FETCH_ASSOC);
            if ($rs != null) {
                echo json_encode($rs[0], JSON_PRETTY_PRINT);

            }else {
                echo 'No se encontro este profesor';
            }
        }else {
            $pstm =$conn->prepare('SELECT * FROM teachers');
            $pstm->execute();
            $rs = $pstm->fetchAll(PDO::FETCH_ASSOC);
            echo json_encode($rs, JSON_PRETTY_PRINT);

        }
        break;

        case 'POST':
            $jsonData =json_decode(file_get_contents('php://input'));
            try {
                $conn = new PDO('mysql:host=localhost;dbname=recuperau2;charset=utf8', 'root', '');
    
            } catch (PDOException $e) {
                echo $e->getMessage();
    
            }
            $pstm = $conn->prepare("INSERT INTO `teachers` (`name`, `surname`, `lastname`, `birthday`,
            `curp`) VALUES (:name, :surname, :lastname, :birthday, :curp);");
            $pstm->bindParam(":name", $jsonData->name);
            $pstm->bindParam(":surname", $jsonData->surname);
            $pstm->bindParam(":lastname", $jsonData->lastname);
            $pstm->bindParam(":birthday", $jsonData->birthday);
            $pstm->bindParam(":curp", $jsonData->curp);
            $rs =$pstm->execute();
            if ($rs) {
                $_POST["error"] = false;
                $_POST["message"] = "Maestro registrado correctamente";
                $_POST["status"] = 200;
            } else {
                $_POST["error"] = true;
                $_POST["message"] = "Error al registrar maestro";
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
            $pstm = $conn->prepare("UPDATE teachers SET `name` = :name, `surname` = :surname, 
            `lastname`= :lastname, `birthday` = :birthday, `curp` = :curp WHERE `id` = :id;");
             $pstm->bindParam(":name", $jsonData->name);
             $pstm->bindParam(":surname", $jsonData->surname);
             $pstm->bindParam(":lastname", $jsonData->lastname);
             $pstm->bindParam(":birthday", $jsonData->birthday);
             $pstm->bindParam(":curp", $jsonData->curp);
             $pstm->bindParam(":id", $jsonData->id);
             $rs = $pstm->execute();
             if ($rs) {
                echo ("Maeastro actualizado correctamente. Code $rs");
            } else {
                echo ("Error al actualizar mestro. Code $rs");
            }
            break;
    
    default:
        echo  'Metopdo no soportado';
        break;
}

