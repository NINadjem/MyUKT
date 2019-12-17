<?php
require './databaseConnexion.php';
$dataBase_connection = new database_connection();
$connection=$dataBase_connection->getConnection();
if($connection){
$myAswer=array();
if (isset($_GET['id_user'])&&isset($_GET['id_post'])&&isset($_GET['agree'])&&isset($_GET['reason'])) {
    $myAswer['params']=1;
    $id_post=$_GET['id_post'];
    $id_user=$_GET['id_user'];
    $agree=$_GET['agree'];
    $reason=($_GET['reason']);
$insert_query="INSERT INTO `voted`(`id_post`, `id_user`, `agree`, `reason`) VALUES ('$id_post','$id_user','$agree','$reason')";
$result_query=mysqli_query($connection, $insert_query);
   if(mysqli_affected_rows($connection)>0){
           $myAswer['done']=1;
           $select="SELECT DISTINCT * FROM `voted` WHERE (id_post='$id_post' AND id_user='$id_user' AND agree='$agree' AND reason='$reason')";
           $result_select=mysqli_query($connection, $select);
           $new_row=mysqli_fetch_array($result_select);
           $vote=array();
           $vote['id']=$new_row['id'];
           $id__=$new_row['id'];
           $vote['id_post']=$id_post;
           $vote['id_user']=$id_user;
           $vote['agree']=$agree;
           $vote['reason']=utf8_encode($reason);
           $myAswer['vote']=$vote;
           $insert_in_journal_query="INSERT INTO `journal`(`id_user`, `op`, `id`, `tab`) VALUES ('$id_user','1','$id__','38')";
           $result_insert_in_journal=mysqli_query($connection, $insert_in_journal_query);
       if(mysqli_affected_rows($connection)>0){
           $myAswer['add_journal']=1;
           $select="SELECT DISTINCT * FROM `journal` WHERE (id_user='$id_user' AND id='$id__' AND op='1' AND tab='38')";
           $result_select=mysqli_query($connection, $select);
                $_row = mysqli_fetch_array($result_select);
                 $journal=array();
                 $journal['id']=$_row['id'];
                 $journal['id_user']=$_row['id_user'];
                 $journal['op']=$_row['op'];
                 $journal['tab']=$_row['tab'];
                 $journal['date']=$_row['date'];         
           $myAswer['journal']=$journal;
       }else{
           $myAswer['add_journal']=0;
       }
              }else{
           $myAswer['done']=0;
       }
        
}else{
    $myAswer['params']=0;
}
mysqli_close($connection);
 header('Content-type: application/json');
 echo json_encode($myAswer); 
}

