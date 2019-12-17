<?php
require './databaseConnexion.php';
$dataBase_connection = new database_connection();
$connection=$dataBase_connection->getConnection();
if($connection){
$myAswer=array();
if (isset($_GET['begin_date'])&&isset($_GET['end_date'])&&isset($_GET['vote'])&&isset($_GET['id'])&&isset($_GET['id_exam_type'])&&
        isset($_GET['id_user'])&&isset($_GET['title'])&&isset($_GET['content'])) {
    $myAswer['params']=1;
    $vote=$_GET['vote'];
    $id_user=$_GET['id_user'];
    $title=$_GET['title'];
    $content=$_GET['content'];
    $bdate=$_GET['begin_date'];
    $edate=$_GET['end_date'];
    $id_=$_GET['id'];
    $id_exam_type=$_GET['id_exam_type'];
{
       $insert_new_query="INSERT INTO `news`(`id_user`, `news_type`, `title`, `content`) VALUES ('$id_user','3','$title','$content')";
       $result_insert_news=mysqli_query($connection,$insert_new_query);
       if(mysqli_affected_rows($connection)>0){
           $myAswer['add_new']=1;
           $select="SELECT * FROM `news` WHERE (id=(SELECT MAX(id) FROM news) AND title='$title' AND content='$content' AND id_user='$id_user' AND news_type='3')";
           $result_select=mysqli_query($connection, $select);
           $new_row= mysqli_fetch_array($result_select);
           $id=$new_row['id'];
           $insert="INSERT INTO `exam`(`id_exam_type`, `id`, `begin_date`, `end_date`) VALUES ('$id_exam_type','$id','$bdate','$edate')";
           $result_insert=mysqli_query($connection, $insert);
           if(mysqli_affected_rows($connection)>0){
               $myAswer['add_exam']=1;
               $myAswer['added_new_id']=$id;
           $insert_in_journal_query="INSERT INTO `journal`(`id_user`, `op`, `id`, `tab`) VALUES ('$id_','1','$id','10')";
           $result_insert_in_journal=mysqli_query($connection, $insert_in_journal_query);
          if(mysqli_affected_rows($connection)>0){
           $myAswer['add_journal']=1;
           $select="SELECT DISTINCT * FROM `journal` WHERE (id_user='$id_' AND id='$id' AND op='1' AND tab='10')";
           $result_select=mysqli_query($connection, $select);
           $new_row=mysqli_fetch_array($result_select);           
           $myAswer['add_date']=$new_row['date'];
       }else{
           $myAswer['add_journal']=0;
       }
           }else{
               $myAswer['add_consulatation']=0;
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




