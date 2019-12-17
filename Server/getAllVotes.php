<?php
require './databaseConnexion.php';
$dataBase_connection = new database_connection();
$connection=$dataBase_connection->getConnection();
  /* $news_type=array(0=>"news",1=>"change_of_session",2=>"consultation",3=>"exam",4=>"holiday",5=>"marks_displayed",
            6=>"meeting",0=>"news",8=>"pedagogical-procedure",9=>"strike");*/
if($connection){
    $myAswer=array();
    if (isset($_GET['id_user'])) {
    $myAswer['voted']=array();
    $myAswer['journal']=array();
    $myAswer['users']=array();
    $id= $_GET['id_user'];
    $myAswer['params']=1;
    $myAswer['result']=0;
        $select_query="SELECT DISTINCT news.id ,news.id_user as 'id_poster',news_visibilitu.id_user,news.title, news.news_type, news.content,news_visibilitu.vote_possible FROM `news_visibilitu`,`news` WHERE (news.id=news_visibilitu.id_news AND news_visibilitu.id_user='$id')";
    $result_news=mysqli_query($connection, $select_query);
    if($result_news->num_rows!=0){ 
     while ($row = mysqli_fetch_array($result_news)) {
         $id_new=$row['id'];
         $vote_query="select * from voted where id_post = '$id_new'";
         $result_vote=mysqli_query($connection, $vote_query);
         if($result_vote->num_rows!=0){
            while ($new_row = mysqli_fetch_array($result_vote)) {
           $vote=array();
           $vote['id']=$new_row['id'];
           $id__=$new_row['id'];
           $myAswer['result']=$myAswer['result']+1;
           $vote['id_post']=$new_row['id_post'];
           $vote['id_user']=$new_row['id_user'];
           $user=$new_row['id_user'];
           $vote['agree']=$new_row['agree'];
           $vote['reason']=utf8_encode($new_row['reason']);
           array_push($myAswer['voted'], $vote);
            $select="SELECT DISTINCT * FROM `journal` WHERE (id_user='$user' AND id='$id__' AND op='1' AND tab='38')";
           $result_select=mysqli_query($connection, $select);
                $_row = mysqli_fetch_array($result_select);
                 $journal=array();
                 $journal['id']=$_row['id'];
                 $journal['id_user']=$_row['id_user'];
                 $journal['op']=$_row['op'];
                 $journal['tab']=$_row['tab'];
                 $journal['date']=$_row['date']; 
                array_push($myAswer['journal'], $journal); 
                $select_query="SELECT * FROM `user` WHERE(user.id='$user')";
                $result_user=mysqli_query($connection, $select_query);   
                 if($result_user->num_rows!=0){   
                $row_user=mysqli_fetch_array($result_user);
                $my_user=array();
                $my_user['id']=$user;
                $my_user['user_type']=$row_user['user_type'];
                $my_user['email']=$row_user['email'];
                $my_user['last_name']=$row_user['last_name'];
                $my_user['first_name']=$row_user['first_name'];
                $my_user['sexe']=$row_user['sexe'];
                $my_user['birth_date']=$row_user['birth_date'];
                $my_user['password']=$row_user['pasword'];
                $my_user['home_town']=$row_user['home_town'];
                if(!in_array($my_user, $myAswer['users']))
                array_push($myAswer['users'], $my_user); 
                 }
            }
         }
         
     }
    }
    }else{
         $myAswer['params']=0;
    }
  mysqli_close($connection);
header('Content-type: application/json; charset ');
 echo json_encode($myAswer); 
}

