<?php

// getting the connexion php file.
require './databaseConnexion.php';
//connecting to the database 
$dataBase_connection = new database_connection();
$connection=$dataBase_connection->getConnection();
//this is where we will store the server's 
$myAswer=array();
if (isset($_GET['id'])&&isset($_GET['id_user'])) {
// it's enough to send the id and the user type to Drop the user
    $id=$_GET['id'];
    $user=$_GET['id_user'];
    $del_query="DELETE FROM `news` WHERE (`news`.`id` ='$id')"; //this will automaticly delete the user form all tables
    $del_result=  mysqli_query($connection, $del_query);
    if(mysqli_affected_rows($connection)>0){
       $myAswer['del']=1;
       $insert_in_journal_query="INSERT INTO `journal` (`id_user`,`op`,`id`,`tab`) VALUES ('$user','3','$id','23')";
       $result_insert_in_journal=mysqli_query($connection, $insert_in_journal_query);
       if(mysqli_affected_rows($connection)>0){
           $myAswer['add_journal']=1;
           $select="SELECT DISTINCT * FROM `journal` WHERE (id_user='$user' AND id='$id' AND op='3' AND tab='23')";
           $result_select=mysqli_query($connection, $select);
           $new_row=mysqli_fetch_array($result_select);           
           $myAswer['add_date']=$new_row['date'];
       }else{
           $myAswer['add_journal']=0;
       }
    }else{
        $myAswer['del']=0;
    }
}else{
    //data missing
    $myAswer['params']=0;
}
mysqli_close($connection);
 header('Content-type: application/json');
 echo json_encode($myAswer); 

