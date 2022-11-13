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
            $pstm = $conn->prepare("SELECT * FROM subjects where id = :id;");
            $pstm->bindParam(':id', $_GET['id']);
            $pstm->execute();
            $rs =$pstm->fetchAll(PDO::FETCH_ASSOC);
            if ($rs != null) {
                echo json_encode($rs[0], JSON_PRETTY_PRINT);

            }else {
                echo 'No se encontro esta materia';
            }
        }else {
            $pstm = $conn->prepare('SELECT * FROM subjects;');
            $pstm->execute();
            $rs = $pstm->fetchAll(PDO::FETCH_ASSOC);
            echo json_encode($rs, JSON_PRETTY_PRINT);

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
            $pstm = $conn->prepare("INSERT INTO `subjects` (`name`) VALUES (:name);");
            $pstm->bindParam(":name", $jsonData->name);
            $rs =$pstm->execute();
            if ($rs) {
                $_POST["error"] = false;
                $_POST["message"] = "materia registrado correctamente";
                $_POST["status"] = 200;
            } else {
                $_POST["error"] = true;
                $_POST["message"] = "Error al registrar materia";
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
            $pstm = $conn->prepare("UPDATE subjects SET `name` = :name WHERE `id` = :id;");
             $pstm->bindParam(":name", $jsonData->name);
             $pstm->bindParam(":id", $jsonData->id);
             $rs =$pstm->execute();
             if ($rs) {
                echo ("materia actualizada correctamente. Code $rs");
            } else {
                echo ("Error al actualizar materia. Code $rs");
            }
            break;
    
    default:
        echo  'Metopdo no soportado';
        break;
}

