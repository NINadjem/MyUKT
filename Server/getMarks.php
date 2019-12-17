<?php
require './databaseConnexion.php';
$dataBase_connection = new database_connection();
$connection=$dataBase_connection->getConnection();
if($connection){
$myAswer=array();
if(isset($_GET['id_user'])){
$myAswer['result']=0;
$myAswer['params']=1;
$myAswer['marks']=array();
$id=$_GET['id_user'];
$query="select * from marks where id_student = '$id'";
$result=mysqli_query($connection, $query);
if($result->num_rows!=0){
    while ($row = mysqli_fetch_array($result)){
        $mark=array();
        $mark['id']=$row['id'];
        $mark['id_student']=$row['id_student'];
        $mark['id_exam']=$row['id_exam'];
        $mark['note']=$row['note'];
        $myAswer['result']=$myAswer['result']+1;
        array_push($myAswer['marks'],$mark);
       
    }
}
}else{
 $myAswer['params']=0; 
}

mysqli_close($connection);
 header('Content-type: application/json');
 echo json_encode($myAswer); 
}

