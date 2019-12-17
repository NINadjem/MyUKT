<?php
require './databaseConnexion.php';
$dataBase_connection = new database_connection();
$connection=$dataBase_connection->getConnection();
if($connection){
$myAswer=array();
if (isset($_GET['date'])&&isset($_GET['id'])&&isset($_GET['id_session'])&&isset($_GET['id_new'])) {
    $myAswer['params']=1;
    $date=$_GET['date'];
    $id_=$_GET['id'];
    $id_new=$_GET['id_new'];
    $id_session=$_GET['id_session'];
    $update_query="UPDATE `consultation` SET `id_sesson`='$id_session',`date`='$date' WHERE (id='$id_new')";
    $result_update=mysqli_query($connection, $update_query);
    if(mysqli_affected_rows($connection)<=0){
        $myAswer['update']=0;
    }else{
      $myAswer['update']=1; 
      $insert_in_journal_query="INSERT INTO `journal`(`id_user`, `op`, `id`, `tab`) VALUES ('$id_','2','$id_new','4')";
           $result_insert_in_journal=mysqli_query($connection, $insert_in_journal_query);
       if(mysqli_affected_rows($connection)>0){
           $myAswer['add_journal']=1;
           $select="SELECT DISTINCT * FROM `journal` WHERE (id_user='$id_' AND id='$id_new' AND op='2' AND tab='4')";
           $result_select=mysqli_query($connection, $select);
           $new_row=mysqli_fetch_array($result_select);           
           $myAswer['add_date']=$new_row['date'];
       }else{
           $myAswer['add_journal']=0;
       }
       
       }
    
      
          
}else{
    $myAswer['params']=0;
}
mysqli_close($connection);
 header('Content-type: application/json');
 echo json_encode($myAswer); 
}





