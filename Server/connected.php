<?php
require './databaseConnexion.php';
$connection = new database_connection();
$myAswer=array();

if($connection->connection->connect_error){
    $myAswer['connected']=0;
}else{
    $myAswer['connected']=1;

}
mysqli_close($connection->connection);
 header('Content-type: application/json');
 echo json_encode($myAswer); 
