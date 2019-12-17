<?php
require './databaseConnexion.php';
$dataBase_connection = new database_connection();
$connection=$dataBase_connection->getConnection();
if($connection){
$myAswer=array();
if (isset($_GET['id_session'])&&isset($_GET['id_user'])&&
        isset($_GET['id_session_type'])&&isset($_GET['id_day'])&&isset($_GET['time'])&&isset($_GET['id_module'])
        &&isset($_GET['id_classroom'])&&isset($_GET['id_teacher'])&&isset($_GET['id_groupe'])&&isset($_GET['semester'])) {
    $myAswer['params']=1;
    $id_user=$_GET['id_user'];
    $id_session_type=$_GET['id_session_type'];
    $id_day=$_GET['id_day'];
    $time=$_GET['time'];
    $id_module=$_GET['id_module'];
    $id_classroom=$_GET['id_classroom'];
    $id_teacher=$_GET['id_teacher'];
    $id_groupe=$_GET['id_groupe'];
    $semester=$_GET['semester'];
    $id_session=$_GET['id_session'];
    $enabled=1;
    $update_query="UPDATE `session` SET `id_session_type`='$id_session_type',`enabled`='$enabled',`id_day`='$id_day',`time`='$time',`id_module`='$id_module',`id_classroom`='$id_classroom',`id_teacher`='$id_teacher',`id_groupe`='$id_groupe',`semester_1`='$semester' WHERE (id=$id_session)";
    $result_update=mysqli_query($connection, $update_query);
    if(mysqli_affected_rows($connection)>0){
        $myAswer['update']=1;
           $insert_in_journal_query="INSERT INTO `journal`(`id_user`, `op`, `id`, `tab`) VALUES ('$id_user','2','$id_session','30')";
           $result_insert_in_journal=mysqli_query($connection, $insert_in_journal_query);
       if(mysqli_affected_rows($connection)>0){
           $myAswer['add_journal']=1;
           $select="SELECT DISTINCT * FROM `journal` WHERE (id_user='$id_user' AND id='$id_session' AND op='2' AND tab='30')";
           $result_select=mysqli_query($connection, $select);
           $new_row=mysqli_fetch_array($result_select);           
           $myAswer['add_date']=$new_row['date'];
       }else{
           $myAswer['add_journal']=0;
       }
    }else{
      $myAswer['update']=0; 
      
       }
    
          
}else{
    $myAswer['params']=0;
}
mysqli_close($connection);
 header('Content-type: application/json');
 echo json_encode($myAswer); 
}



