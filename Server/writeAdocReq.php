<?php
require './databaseConnexion.php';
$dataBase_connection = new database_connection();
$connection=$dataBase_connection->getConnection();
if($connection){
$myAswer=array();
if (isset($_GET['id_user'])&&isset($_GET['doc'])&&isset($_GET['reason'])) {
    $myAswer['params']=1;
    $id_user=$_GET['id_user'];
    $doc=$_GET['doc'];
    $r=$_GET['reason'];
  {
       $insert_new_query="INSERT INTO `document_request`( `id_user`, `doc`, `reason`) VALUES ('$id_user','$doc','$r')";
       $result_insert_news=mysqli_query($connection, $insert_new_query);
       if(mysqli_affected_rows($connection)>0){
           $myAswer['add_doc']=1;
           $select="SELECT DISTINCT * FROM `document_request` WHERE (id_user='$id_user' AND doc='$doc' AND reason='$r')";
           $result_select=mysqli_query($connection, $select);
           $new_row=mysqli_fetch_array($result_select);
           $id=$new_row['id'];
           $myAswer['id']=$id;
$insert_in_journal_query="INSERT INTO `journal`(`id_user`, `op`, `id`, `tab`) VALUES ('$id_user','1','$id','7')";
           $result_insert_in_journal=mysqli_query($connection, $insert_in_journal_query);
       if(mysqli_affected_rows($connection)>0){
           $myAswer['add_journal']=1;
           $select="SELECT DISTINCT * FROM `journal` WHERE (id_user='$id_user' AND id='$id' AND op='1' AND tab='7')";
           $result_select=mysqli_query($connection, $select);
           $new_row=mysqli_fetch_array($result_select);           
           $myAswer['add_date']=$new_row['date'];
       }else{
           $myAswer['add_journal']=0;
       }
              }else{
           $myAswer['add_new']=0;
       }
    }
          
}else{
    $myAswer['params']=0;
}
mysqli_close($connection);
 header('Content-type: application/json');
 echo json_encode($myAswer); 
}



