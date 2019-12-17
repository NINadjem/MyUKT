<?php
require './databaseConnexion.php';
$dataBase_connection = new database_connection();
$connection=$dataBase_connection->getConnection();
$myAswer=array();
if($connection){
    if (isset($_GET['id'])&&isset($_GET['user_type'])) {
    $myAswer['params']=1;
    $myAswer['result']=0;
    $id=$_GET['id'];
    $user_type=$_GET['user_type'];
    switch ($user_type){
        case 1:{
            $query="SELECT * FROM `groupe`,`level` WHERE (groupe.id='$id' AND groupe.id_level=level.id)";
            $result=mysqli_query($connection, $query);
            if($result->num_rows!=0){
                $myAswer['result']=1;
                $row = mysqli_fetch_array($result);
                $myAswer['specialty']=$row['abrg']." ".$row['name']; 
            }
            break;
                }
                case 2:{
            $query="SELECT * FROM `specialty` WHERE (id='$id')";
            $result=mysqli_query($connection, $query);
            if($result->num_rows!=0){
                $myAswer['result']=1;
                $row = mysqli_fetch_array($result);
                $myAswer['specialty']=$row['name']; 
            }
            break;
                }
                            case 5:{
            $query="SELECT * FROM `responsibility` WHERE (id='$id')";
            $result=mysqli_query($connection, $query);
            if($result->num_rows!=0){
                $myAswer['result']=1;
                $row = mysqli_fetch_array($result);
                $myAswer['specialty']=$row['name']; 
            }
            break;
                }    
    }
    }else{
    $myAswer['params']=0;
}
    mysqli_close($connection);
 header('Content-type: application/json');
 echo json_encode($myAswer); 
}

