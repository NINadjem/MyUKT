<?php

require './databaseConnexion.php';
$dataBase_connection = new database_connection();
$connection=$dataBase_connection->getConnection();
if($connection){
$myAswer=array();
if (isset($_GET['id_user'])&&isset($_GET['id_msg'])&&isset($_GET['id'])) {
    $myAswer['params']=1;
    $id_msg=$_GET['id_msg'];
    $id_user=$_GET['id_user'];
    $id_=$_GET['id'];
$insert_query="INSERT INTO `messege_sent_to`(`id_msg`, `id_user`, `seen`) VALUES ('$id_msg','$id_user','0')";
$result_query=mysqli_query($connection, $insert_query);
   if(mysqli_affected_rows($connection)>0){
           $myAswer['add_vote']=1;
           $select="SELECT DISTINCT * FROM `messege_sent_to` WHERE (id_msg='$id_msg' AND id_user='$id_user' AND seen='0')";
           $result_select=mysqli_query($connection, $select);
           $new_row=mysqli_fetch_array($result_select);
           $id=$new_row['id'];
           $myAswer['id']=$id;
           $insert_in_journal_query="INSERT INTO `journal`(`id_user`, `op`, `id`, `tab`) VALUES ('$id_','1','$id','21')";
           $result_insert_in_journal=mysqli_query($connection, $insert_in_journal_query);
       if(mysqli_affected_rows($connection)>0){
           $myAswer['add_journal']=1;
           $select="SELECT DISTINCT * FROM `journal` WHERE (id_user='$id_' AND id='$id' AND op='1' AND tab='21')";
           $result_select=mysqli_query($connection, $select);
           $new_row=mysqli_fetch_array($result_select);           
           $myAswer['add_date']=$new_row['date'];
       }else{
           $myAswer['add_journal']=0;
       }
              }else{
           $myAswer['add_vote']=0;
       }
        
}else{
    $myAswer['params']=0;
}
mysqli_close($connection);
 header('Content-type: application/json');
 echo json_encode($myAswer); 
}



