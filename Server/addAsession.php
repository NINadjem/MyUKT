<?php

require './databaseConnexion.php';
$dataBase_connection = new database_connection();
$connection=$dataBase_connection->getConnection();
if($connection){
$myAswer=array();
if (isset($_GET['id_session_type'])&&isset($_GET['id_day'])&&isset($_GET['time'])&&isset($_GET['id_module'])
        &&isset($_GET['id_classroom'])&&isset($_GET['id_teacher'])&&isset($_GET['id_groupe'])&&isset($_GET['semester'])
        &&isset($_GET['enabled'])&&isset($_GET['id'])) {
    $myAswer['params']=1;
    $id_user=$_GET['id'];
    $enabled=$_GET['enabled'];
    $id_session_type=$_GET['id_session_type'];
    $id_day=$_GET['id_day'];
    $time=$_GET['time'];
    $id_module=$_GET['id_module'];
    $id_classroom=$_GET['id_classroom'];
    $id_teacher=$_GET['id_teacher'];
    $id_groupe=$_GET['id_groupe'];
    $semester=$_GET['semester'];
    $enabled=$_GET['enabled'];
$insert_query="INSERT INTO `session`(`id_session_type`,`id_day`, `time`, `id_module`, `id_classroom`, `id_teacher`, "
        . "`id_groupe`, `semester_1`, `enabled`) VALUES ('$id_session_type','$id_day','$time','$id_module','$id_classroom',"
        . "'$id_teacher','$id_groupe','$semester','$enabled')";
$result_query=mysqli_query($connection, $insert_query);
    if(mysqli_affected_rows($connection)>0){
       $myAswer['add']=1;
       $select_new_query="SELECT * FROM `session` WHERE (id_teacher='$id_teacher' AND id_day='$id_day' AND time ='$time' AND semester_1='$semester' AND enabled='$enabled' AND id_groupe='$id_groupe'"
               . "AND `id_module`='$id_module' AND `id_classroom`='$id_classroom')";
       $result_new_query=mysqli_query($connection, $select_new_query);
       $new_row=mysqli_fetch_array($result_new_query);
       $id_new=$new_row['id'];
        $insert_in_journal_query="INSERT INTO `journal`(`id_user`, `op`, `id`, `tab`) VALUES ('$id_user','1','$id_new','30')";
           $result_insert_in_journal=mysqli_query($connection, $insert_in_journal_query);
       if(mysqli_affected_rows($connection)>0){
           $myAswer['add_journal']=1;
           $select="SELECT DISTINCT * FROM `journal` WHERE (id_user='$id_user' AND id='$id_new' AND op='1' AND tab='30')";
           $result_select=mysqli_query($connection, $select);
           $new_row=mysqli_fetch_array($result_select);           
           $myAswer['add_date']=$new_row['date'];
       }else{
           $myAswer['add_journal']=0;
       }
    }else{
        $myAswer['add']=0;
    }
    
        
        }else{
    $myAswer['params']=0;
        }
mysqli_close($connection);
 header('Content-type: application/json');
 echo json_encode($myAswer); 
}



