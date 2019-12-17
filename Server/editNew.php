<?php
require './databaseConnexion.php';
$dataBase_connection = new database_connection();
$connection=$dataBase_connection->getConnection();
if($connection){
$myAswer=array();
if (isset($_GET['id'])&&isset($_GET['id_new'])&&isset($_GET['id_user'])&&isset($_GET['title'])&&isset($_GET['content'])&&isset($_GET['type'])) {
    $myAswer['params']=1;
    $i=$_GET['id'];
    $id_user=$_GET['id_user'];
    $title=$_GET['title'];
    $content=$_GET['content'];
    $id_new=$_GET['id_new'];
    $type=$_GET['type'];
    $update_query="UPDATE `news` SET `id_user`='$id_user',`news_type`='$type',`title`='$title',`content`='$content' WHERE (id='$id_new')";
    $result_update=mysqli_query($connection, $update_query);
    if(mysqli_affected_rows($connection)<=0){
        $myAswer['update']=0;
    }else{
      $myAswer['update']=1; 
      $insert_in_journal_query="INSERT INTO `journal`(`id_user`, `op`, `id`, `tab`) VALUES ('$i','2','$id_new','23')";
           $result_insert_in_journal=mysqli_query($connection, $insert_in_journal_query);
       if(mysqli_affected_rows($connection)>0){
           $myAswer['add_journal']=1;
           $select="SELECT DISTINCT * FROM `journal` WHERE (id_user='$i' AND id='$id_new' AND op='2' AND tab='23')";
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





