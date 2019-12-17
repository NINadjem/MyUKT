<?php
require './databaseConnexion.php';
$dataBase_connection = new database_connection();
$connection=$dataBase_connection->getConnection();
if($connection){
$myAswer=array();
if (isset($_GET['id_user'])&&isset($_GET['id_news'])&&isset($_GET['vote_possible'])&&isset($_GET['id'])) {
    $id= $_GET['id_news'];
    $id_=$_GET['id_user'];
    $id__=$_GET['id'];
    $vote=$_GET['vote_possible'];
    $myAswer['params']=1;
    $insert="INSERT INTO `news_visibilitu`(`id_user`, `id_news`, `vote_possible`) VALUES ('$id_','$id','$vote')";
    $result=mysqli_query($connection, $insert);
           $select="SELECT DISTINCT * FROM `news_visibilitu` WHERE (id_user='$id_' AND id_news='$id')";
           $result_select=mysqli_query($connection, $select);
           $new_row=mysqli_fetch_array($result_select);
           $id2=$new_row['id'];
                      $insert_in_journal_query="INSERT INTO `journal`(`id_user`, `op`, `id`, `tab`) VALUES ('$id__','1','$id2','24')";
           $result_insert_in_journal=mysqli_query($connection, $insert_in_journal_query);
       if(mysqli_affected_rows($connection)>0){
           $myAswer['add_journal']=1;
           $select="SELECT DISTINCT * FROM `journal` WHERE (id_user='$id__' AND id='$id2' AND op='1' AND tab='24')";
           $result_select=mysqli_query($connection, $select);
           $new_row=mysqli_fetch_array($result_select);           
           $myAswer['add_date']=$new_row['date'];
       }else{
           $myAswer['add_journal']=0;
       }
            }else{
    $myAswer['params']=0;
}
mysqli_close($connection);
header('Content-type: application/json; charset ');
 echo json_encode($myAswer); 
}




