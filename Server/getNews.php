<?php 
require './databaseConnexion.php';
$dataBase_connection = new database_connection();
$connection=$dataBase_connection->getConnection();
  /* $news_type=array(0=>"news",1=>"change_of_session",2=>"consultation",3=>"exam",4=>"holiday",5=>"marks_displayed",
            6=>"meeting",0=>"news",8=>"pedagogical-procedure",9=>"strike");*/
if($connection){
$myAswer=array();
if (isset($_GET['id_user'])) {
    
    $id= $_GET['id_user'];
    $myAswer['params']=1;
    $myAswer['result']=0;
    $myAswer['news_visibilitu']=array();
    $myAswer['result_change_of_session']=0;
    $myAswer['change_of_session']=array();
    $myAswer['result_consultation']=0;
    $myAswer['consultation']=array();
    $myAswer['result_exam']=0;
    $myAswer['exam']=array();
    $myAswer['result_holiday']=0;
    $myAswer['holiday']=array();
    $myAswer['result_marks_displayed']=0;
    $myAswer['marks_displayed']=array();
    $myAswer['result_meeting']=0;
    $myAswer['meeting']=array();
    $myAswer['result_news']=0;
    $myAswer['news']=array();
    $myAswer['result_pedagogical_procedure']=0;
    $myAswer['pedagogical_procedure']=array();
    $myAswer['result_strike']=0;
    $myAswer['strike']=array();
    $myAswer['result_voted']=0;
    $myAswer['voted']=array();
    $myAswer['result_user']=0;
    $myAswer['user']=array();
    $myAswer['journal']=array();
 
    //***************************************************USER****************************************////*********//
    $select_query="SELECT DISTINCT news.id ,news.id_user as 'id_poster',news_visibilitu.id_user,news.title, news.news_type, news.content,news_visibilitu.vote_possible FROM `news_visibilitu`,`news` WHERE (news.id=news_visibilitu.id_news AND news_visibilitu.id_user='$id')";
    $result_news=mysqli_query($connection, $select_query);
    if($result_news->num_rows!=0){ 
     while ($row = mysqli_fetch_array($result_news)) {
            /*$new_visibilitu['id_user']=$row['id_user'];
            $new_visibilitu['id_news']=$row['id'];
            $new_visibilitu['vote_possible']=$row['vote_possible'];
            $visible['visibility']=$new_visibilitu;*/
            $new['id']=$row['id'];
            $new['id_user']=$row['id_poster'];
            $poster=$row['id_poster'];
            $select_poster="SELECT DISTINCT * FROM `user` where(id='$poster')";           
            $result_poster=mysqli_query($connection, $select_poster);
            $row12=mysqli_fetch_array($result_poster);
                $my_user['id']=$row12['id'];
                $id_user=$row12['id'];
                $my_user['user_type']=$row12['user_type'];
                $type=$row12['user_type'];
                $my_user['email']=$row12['email']; 
                $my_user['last_name']=$row12['last_name']; 
                $my_user['first_name']=$row12['first_name'];
                $my_user['sexe']=$row12['sexe'];
                $my_user['birth_date']=$row12['birth_date'];
                $my_user['home_town']=$row12['home_town'];                
                $my_user['avatar']=$row12['avatar'];
                switch ($type){
                    case 4:{
    //***************************************************STUDENT****************************************////*********//
        $select_student="SELECT DISTINCT * FROM `student` WHERE (id_user='$id_user')";
        $result_student=mysqli_query($connection, $select_student); 
         if($result_student->num_rows!=0){
             // the user is a student
           $row_student=mysqli_fetch_array($result_student);             
              $myAswer['result']=$myAswer['result']+1;
                  $my_user['mat']=$row_student['mat'];
                  $my_user['bib']=$row_student['bib'];
                  $select_student="SELECT DISTINCT * FROM `groupe_member` WHERE (id_student='$id_user')";
                  $result_student=mysqli_query($connection, $select_student); 
                  if($result_student->num_rows!=0){
                   $row_student=mysqli_fetch_array($result_student);   
                   $my_user['id_groupe']=$row_student['id_groupe'];
                  // we check if he is a delegue
                  $select_delege="SELECT * FROM `delege` WHERE (id_delege='$id_user')";
                  $result_delegue=mysqli_query($connection, $select_delege); 
                  if($result_delegue->num_rows!=0){
                      $row_delege=mysqli_fetch_array($result_delegue);
                      $myAswer['result']=$myAswer['result']+1;
                      $my_user['id_promo']=$row_delege['id_level'];
                    // $myAswer['user']=$my_user;  
                  }
              
         }
                    }
                    
                  }
                    case 5:{
             // the user isn't a student
        $select_teacher="SELECT DISTINCT * FROM `teacher` WHERE (id_user='$id_user')";
        $result_teacher=mysqli_query($connection, $select_teacher);
        if($result_teacher->num_rows!=0){
           $myAswer['result']=$myAswer['result']+1;
           $row_teacher=mysqli_fetch_array($result_teacher);
                  $my_user['id_specialty']=$row_teacher['id_specialty'];
                  //$myAswer['teacher_info']['teacher']=$my_teacher;   
                     $select_responsable="SELECT * FROM `responsble_teacher` WHERE (id='$id_user')";
                     $result_responsble=mysqli_query($connection, $select_responsable); 
                      if($result_responsble->num_rows!=0){
                      $row_responsbe=mysqli_fetch_array($result_responsble);
                      $myAswer['result']=$myAswer['result']+1;
                      $my_user['id_responsbility']=$row_responsbe['id_responsbility'];
                      //$myAswer['user']=$my_user;
                  }

                    }
                    
                  }
                    case 0:{// the user is maybe an admin
           $qq="SELECT * FROM `aministrator` WHERE id_user='$poster'";
           $rr=mysqli_query($connection, $qq);
           $rrr=mysqli_fetch_array($rr); 
           $myAswer['result']=$myAswer['result']+1;
           $my_user['id_work']=$rrr['id_work'];
           
           
        }
                    case 1:{
                                $select_student="SELECT DISTINCT * FROM `student` WHERE (id_user='$id_user')";
        $result_student=mysqli_query($connection, $select_student); 
         if($result_student->num_rows!=0){
             // the user is a student
           $row_student=mysqli_fetch_array($result_student);             
              $myAswer['result']=$myAswer['result']+1;
                  $my_user['mat']=$row_student['mat'];
                  $my_user['bib']=$row_student['bib'];
                  $select_student="SELECT * FROM `groupe_member` WHERE (id_student='$id_user')";
                  $result_student=mysqli_query($connection, $select_student); 
                  if($result_student->num_rows!=0){
                   $row_student=mysqli_fetch_array($result_student);   
         $my_user['id_groupe']=$row_student['id_groupe'];
         //$myAswer['user']=$my_user;
                  }}
                    }
                    case 2:{
        $select_teacher="SELECT * FROM `teacher` WHERE (id_user='$id_user')";
        $result_teacher=mysqli_query($connection, $select_teacher);
        if($result_teacher->num_rows!=0){
           $myAswer['result']=$myAswer['result']+1;
           $row_teacher=mysqli_fetch_array($result_teacher);
           $my_user['id_specialty']=$row_teacher['id_specialty'];
           //$myAswer['user']=$my_user;

        }
                    }
        
        }        
         
                
    
               // $user_['user']=$my_user;
                $myAswer['result_user']=$myAswer['result_user']+1;
                $myAswer['result']=$myAswer['result']+1;
                 if(!in_array($my_user, $myAswer['user']))
                array_push($myAswer['user'],$my_user); 
            $new['news_type']=$row['news_type'];
            $id_new=$row['id'];
            $type2=$row['news_type'];
            $new['title']=utf8_encode($row['title']);
            $new['content']=utf8_encode($row['content']);
            $n=$row['content'];
           $select="SELECT DISTINCT TIME_FORMAT(`date`, '%d/%m/%Y %H:%i') AS "
                   . "`date` FROM `journal` WHERE (id_user='$poster' AND id='$id_new' AND op='1' AND tab='23')";
           $result_select=mysqli_query($connection, $select);
           $new_row=mysqli_fetch_array($result_select); 
           $date=array();
           $date['id_user']=$poster;
           $date['id']=$id_new;
           $date['op']="1";
           $date['tab']="23";
           $date['date']=$new_row['date'];
           array_push($myAswer['journal'], $date);
            //array_push($myAswer['news_visibilitu'],$visible);
            $myAswer['result']=$myAswer['result']+1;
            switch ($type2){
            case 0:{ 
                $new_['new']=$new;
                $myAswer['result_news']=$myAswer['result_news']+1;
                array_push($myAswer['news'],$new); 
                break;
                }    
            case 1:{
                $query="SELECT * FROM `change_of_session` WHERE (id='$id_new')";
                $result_query= mysqli_query($connection,$query);
                $change=mysqli_fetch_array($result_query);
                $new['id_old_session']=$change['id_old_session'];
                $new['id_new_session']=$change['id_new_session'];
                $new_['new']=$new;
                $myAswer['result_change_of_session']=$myAswer['result_change_of_session']+1;
                array_push($myAswer['change_of_session'],$new); 
                break;}
            case 2:{        
                $query="SELECT * FROM `consultation` WHERE (id='$id_new')";
                $result_query=  mysqli_query($connection,$query);
                $consultation=mysqli_fetch_array($result_query);
                $new['id_sesson']=$consultation['id_sesson'];
                $new['date']=$consultation['date'];
                $new_['new']=$new;
                $myAswer['result_consultation']=$myAswer['result_consultation']+1;
                array_push($myAswer['consultation'],$new); 
                break;}
            case 3:{ 
                $query="SELECT * FROM `exam` WHERE (id='$id_new')";
                $result_query=  mysqli_query($connection,$query);
                $exam=mysqli_fetch_array($result_query);
                $new['id_exam_type']=$exam['id_exam_type'];
                $new['begin']=$exam['begin'];
                $new['id_groupe']=$exam['id_groupe'];
                $new['id_module']=$exam['id_module'];
                $new['id_classroom']=$exam['id_classroom'];
                $new_['new']=$new;
                $myAswer['result_exam']=$myAswer['result_exam']+1;
                array_push($myAswer['exam'],$new); 
                break;}
            case 4:{
                $query="SELECT * FROM `holiday` WHERE (id='$id_new')";
                $result_query=  mysqli_query($connection,$query);
                $holiday=mysqli_fetch_array($result_query);
                $new['begin_date']=$holiday['begin_date'];
                $new['end_date']=$holiday['end_date'];
                $new_['new']=$new;
                $myAswer['result_holiday']=$myAswer['result_holiday']+1;
                array_push($myAswer['holiday'],$new); 
                break;}
            case 5:{ 
                $query="SELECT * FROM `marks_displayed` WHERE (id='$id_new')";
                $result_query=  mysqli_query($connection,$query);
                $marks_displayed=mysqli_fetch_array($result_query);
                $new['id_teacher']=$marks_displayed['id_teacher'];
                $new['id_exam']=$marks_displayed['id_exam'];
                $new_['new']=$new;
                $myAswer['result_marks_displayed']=$myAswer['result_marks_displayed']+1;
                array_push($myAswer['marks_displayed'],$new); 
                break;}
            case 6:{ 
                $query="SELECT * FROM `meeting` WHERE (id='$id_new')";
                $result_query=  mysqli_query($connection,$query);
                $meeting=mysqli_fetch_array($result_query);
                $new['id_classroom']=$meeting['id_classroom'];
                $new['date']=$meeting['date'];
                $new_['new']=$new;
                $myAswer['result_meeting']=$myAswer['result_meeting']+1;
                array_push($myAswer['meeting'],$new); 
                break;}
            case 8:{ 
                $query="SELECT * FROM `pedagogical-procedure` WHERE (id='$id_new')";
                $result_query=mysqli_query($connection,$query);
                $pedagogical_procedure=mysqli_fetch_array($result_query);
                $new['begin_date']=$pedagogical_procedure['begin_date'];
                $new['end_date']=$pedagogical_procedure['end_date'];
                $new['id_place']=$pedagogical_procedure['id_place'];
                $new['docs']=$pedagogical_procedure['docs'];
                $new_['procedure']=$new;
                $myAswer['result_pedagogical_procedure']=$myAswer['result_pedagogical_procedure']+1;
                array_push($myAswer['pedagogical_procedure'],$new); 
            break;}
            case 9:{ 
                $query="SELECT * FROM `strike` WHERE (id='$id_new')";
                $result_query=mysqli_query($connection,$query);
                $strike=mysqli_fetch_array($result_query);
                $new['begin_date']=$strike['begin_date'];
                $new['end_date']=$strike['end_date'];
                $new['cause']=$strike['cause'];
                $new_['new']=$new;
                $myAswer['result_strike']=$myAswer['result_strike']+1;
                array_push($myAswer['strike'],$new); 
                break;
            
            }
        }
      /*  $select_vote="SELECT * FROM `voted`,`user` WHERE (id_post='$id_new' AND voted.id_user=user.id)";
        $result_vote=  mysqli_query($connection, $select_vote);
        if($result_vote->num_rows!=0){
            while ($row1 = mysqli_fetch_array($result_vote)) {
                
                $vote['id']=$row1['id'];
                $id_vote=$row1['id'];
                $vote['id_post']=$row1['id_post'];
                $vote['id_user']=$row1['id_user'];
                $i=$vote['id_user'];
                $vote['agree']=$row1['agree'];
                $vote['reason']=$row1['reason'];
                $select="SELECT * FROM `journal` WHERE (id_user='$i' AND id='$id_vote' AND op='1' AND tab='38')";
                $result_select=mysqli_query($connection, $select);
                $new_row=mysqli_fetch_array($result_select);           
                $vote['add_date']=$new_row['date'];
                $user['id']=$row1['id'];
                $user['email']=$row1['email']; 
                $user['last_name']=$row1['last_name']; 
                $user['first_name']=$row1['first_name'];
                $user['sexe']=$row1['sexe'];
                $user['birth_date']=$row1['birth_date'];
                $user['home_town']=$row1['home_town'];                
                $user['avatar']=$row1['avatar'];
                $vote_['vote']=$vote;
                $user_['user']=$user;
                $myAswer['result_voted']=$myAswer['result_voted']+1;
                array_push($myAswer['voted'],$vote); 
                $myAswer['result']=$myAswer['result']+1;
                $myAswer['result_user']=$myAswer['result_user']+1;
                array_push($myAswer['user'],$user);
            }
        } */
        
        
              
}
    }

            }else{
    $myAswer['params']=0;
}
mysqli_close($connection);
header('Content-type: application/json; charset ');
 echo json_encode($myAswer); 
}


