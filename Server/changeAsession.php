<?php

require './databaseConnexion.php';
$dataBase_connection = new database_connection();
$connection=$dataBase_connection->getConnection();
if($connection){
$myAswer=array();
if (isset($_GET['id_old_session'])&&isset($_GET['vote'])&&
        isset($_GET['id_new_session'])&&isset($_GET['id'])
        &&isset($_GET['id_user'])&&isset($_GET['title'])&&isset($_GET['content'])) {
    $myAswer['params']=1;
    $vote=$_GET['vote'];
    $id_user=$_GET['id_user'];
    $title=$_GET['title'];
    $content=$_GET['content'];
    $id_old_session=$_GET['id_old_session'];
    $id_new_session=$_GET['id_new_session'];
    $id_=$_GET['id'];
    $update_query="UPDATE `session` SET `enabled`='0' WHERE (id='$id_old_session')";
    $result_update=mysqli_query($connection, $update_query);
    $update_query="UPDATE `session` SET `enabled`='1' WHERE (id='$id_new_session');";
    $result_update=mysqli_query($connection, $update_query);
    
    if(mysqli_affected_rows($connection)<=0){
        $myAswer['update']=0;
    }else{
      $select="SELECT * FROM `session` WHERE (id='$id_old_session')" ;
      $result=mysqli_query($connection, $select);
      $r=  mysqli_fetch_array($result);
      $id_groupe=$r['id_groupe'];
      $myAswer['update']=1;  
       $insert_new_query="INSERT INTO `news`(`id_user`, `news_type`, `title`, `content`) VALUES ('$id_user','1','$title','$content')";
       $result_insert_news=mysqli_query($connection,$insert_new_query);
       if(mysqli_affected_rows($connection)>0){
           $myAswer['add_new']=1;
           $select="SELECT * FROM `news` WHERE (id_user='$id_user' AND news_type='1' AND title='$title' AND content='$content')";
           $result_select=mysqli_query($connection, $select);
           $new_row= mysqli_fetch_array($result_select);
           $id=$new_row['id'];
           $insert="INSERT INTO `change_of_session`(`id`, `id_old_session`, `id_new_session`) VALUES "
                   . "('$id','$id_old_session','$id_new_session')";
           $result_insert=mysqli_query($connection, $insert);
           if(mysqli_affected_rows($connection)>0){
               $myAswer['add_new']=1;
               $myAswer['added_new_id']=$id;
           $insert_in_journal_query="INSERT INTO `journal`(`id_user`, `op`, `id`, `tab`) VALUES ('$id_','1','$id','2')";
           $result_insert_in_journal=mysqli_query($connection, $insert_in_journal_query);
          if(mysqli_affected_rows($connection)>0){
           $myAswer['add_journal']=1;
           $select="SELECT DISTINCT * FROM `journal` WHERE (id_user='$id_' AND id='$id' AND op='1' AND tab='2')";
           $result_select=mysqli_query($connection, $select);
           $new_row=mysqli_fetch_array($result_select);           
           $myAswer['add_date']=$new_row['date'];
              $select_="SELECT * FROM `groupe_member` WHERE (id_groupe='$id_groupe')";
              $result_=mysqli_query($connection, $select_);
              while ($row1 = mysqli_fetch_array($result_)) {
                  $id__=$row1['id_student'];
                  $insert="INSERT INTO `news_visibilitu`(`id_user`, `id_news`, `vote_possible`) VALUES ('$id__','$id','$vote')";
                  $result=mysqli_query($connection, $insert);
           $myAswer['news_visibilitu_rows_added']=1;
           $select="SELECT DISTINCT * FROM `news_visibilitu` WHERE (id_user='$id__' AND id_news='$id' AND vote='$vote')";
           $result_select=mysqli_query($connection, $select);
           $new_row=mysqli_fetch_array($result_select);           
           $m=$new_row['id'];
           $insert_in_journal_query="INSERT INTO `journal`(`id_user`, `op`, `id`, `tab`) VALUES ('$id_','1','$m','38')";
           $result_insert_in_journal=mysqli_query($connection, $insert_in_journal_query);
              }
       }else{
           $myAswer['add_journal']=0;
       }
           }else{
               $myAswer['add_change']=0;
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

