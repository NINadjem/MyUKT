<?php
require './databaseConnexion.php';
$dataBase_connection = new database_connection();
$connection=$dataBase_connection->getConnection();
if($connection){
$myAswer=array();
if (isset($_GET['id_user'])&&isset($_GET['title'])&&isset($_GET['content'])) {
    $myAswer['params']=1;

    $id_user=$_GET['id_user'];
    $title=$_GET['title'];
    $content=$_GET['content'];
  {
       $insert_new_query="INSERT INTO `news`(`id_user`, `news_type`, `title`, `content`) VALUES ('$id_user',"
               . "'0','$title','$content')";
       $result_insert_news=mysqli_query($connection, $insert_new_query);
       if(mysqli_affected_rows($connection)>0){
           $myAswer['add_new']=1;
           $select="SELECT * FROM `news` WHERE (id=(SELECT MAX(id) FROM news) AND title='$title' AND content='$content' AND id_user='$id_user' AND news_type='0'";
           $result_select=mysqli_query($connection, $select);
           $new_row=mysqli_fetch_array($result_select);
           $id=$new_row['id'];
           $myAswer['id']=$id;
           $insert_in_journal_query="INSERT INTO `journal`(`id_user`, `op`, `id`, `tab`) VALUES ('$id_user','1','$id','23')";
           $result_insert_in_journal=mysqli_query($connection, $insert_in_journal_query);
       if(mysqli_affected_rows($connection)>0){
           $myAswer['add_journal']=1;
           $select="SELECT DISTINCT * FROM `journal` WHERE (id_user='$id_user' AND id='$id' AND op='1' AND tab='23')";
           $result_select=mysqli_query($connection, $select);
           $new_row=mysqli_fetch_array($result_select);           
           $myAswer['pub_date']=$new_row['date'];
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


          /* if(isset($_GET['id_groupe'])&&isset($_GET['vote'])){
                   $id_groupe=$_GET['id_groupe'];
                       $vote=$_GET['vote'];
              $select_="SELECT * FROM `groupe_member` WHERE (id_groupe='$id_groupe')";
              $result_=mysqli_query($connection, $select_);
              while ($row1 = mysqli_fetch_array($result_)) {
                  $id_=$row1['id_student'];
                  $insert="INSERT INTO `news_visibilitu`(`id_user`, `id_news`, `vote_possible`) VALUES ('$id_','$id','$vote')";
                  $result=mysqli_query($connection, $insert);
                  $myAswer['news_visibilitu_rows_added']=1;
              }
              
       }*/

