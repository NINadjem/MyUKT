<?php

require './databaseConnexion.php';
$dataBase_connection = new database_connection();
$connection=$dataBase_connection->getConnection();
if($connection){
$myAswer=array();
$myAswer['voted']=array();
$myAswer['result']=0;
if (isset($_GET['id'])&&isset($_GET['update'])&&isset($_GET['user_type'])) {
    $myAswer['params']=1;
    $id_=$_GET['id'];
    $update=$_GET['update'];
    $groupe=0;
    $user_type=$_GET['user_type'];
    if($user_type==1||$user_type==4){
        $q="select * from `groupe_member` where id_student = '$id_'";
        $t=mysqli_query($connection, $q);
        if($t->num_rows!=0){
          $ow = mysqli_fetch_array($t);
          $groupe=$ow['id_groupe'];
        }
    }
    $query="select * from `journal` where `journal`.date >= '$update'";
    $result=mysqli_query($connection, $query);
    $myAswer['in']['session']=array();
    $myAswer['in']['news']=array();
    $myAswer['in']['docs']=array();
    $myAswer['in']['abs']=array();
    $myAswer['in']['note']=array();
    $myAswer['in']['msgs']=array();
    $myAswer['in']['msgs_send_to']=array();
    $myAswer['up']['session']=array();
    $myAswer['up']['msgs_send_to']=array();
    $myAswer['journal']=array();
    $myAswer['user']=array();
    $myAswer['users']=array();
    $myAswer['groupe']=array();
    if($result->num_rows!=0){
    while ($row = mysqli_fetch_array($result)) {
      $id=$row['id'];
      $tab=$row['tab'];
     // $date=$row['date'];
      $op=$row['op'];
      switch ($tab){
          case 30:{
              if($groupe!=0){//student
              $select_query="select * from `session` where ( `id_groupe`='$groupe' and id= '$id')";
             $result_table=mysqli_query($connection, $select_query);
             if($result_table->num_rows!=0){
              $row = mysqli_fetch_array($result_table);
              $session['id']=$row['id'];
            $_=$row['id'];
            $session['id_session_type']=$row['id_session_type'];
            $session['enabled']=$row['enabled'];
            $session['id_day']=$row['id_day'];
            $session['time']=$row['time'];
            $session['id_module']=$row['id_module'];
            $session['id_classroom']=$row['id_classroom'];
            $session['id_teacher']=$row['id_teacher'];
            $id_teacher=$row['id_teacher'];
            $query="SELECT * FROM `user` where (id='$id_teacher')";
            $result=mysqli_query($connection, $query);
            if($result->num_rows!=0){
                $row_user = mysqli_fetch_array($result);
                $my_user=array();
                $id_user=$row_user['id'];
                $my_user['id']=$id_user;
                $my_user['user_type']=$row_user['user_type'];
                $type=$row_user['user_type'];
                $my_user['email']=$row_user['email'];
                $my_user['last_name']=$row_user['last_name'];
                $my_user['first_name']=$row_user['first_name'];
                $my_user['sexe']=$row_user['sexe'];
                $my_user['birth_date']=$row_user['birth_date'];
                $my_user['password']=$row_user['pasword'];
                $my_user['home_town']=$row_user['home_town'];
                if(!in_array($myAswer['user'], $my_user))
                array_push($myAswer['user'],$my_user);
            
            }
              }
              }
              else{//teacher
              $select_query="select * from `session` where ( `id_teacher`='$id_' and id= '$id')";
              $result_table=mysqli_query($connection, $select_query);
              if($result_table->num_rows!=0){
              
              $row = mysqli_fetch_array($result_table);
            $session['id']=$row['id'];
            $session['id_session_type']=$row['id_session_type'];
            $session['enabled']=$row['enabled'];
            $session['id_day']=$row['id_day'];
            $session['time']=$row['time'];
            $session['id_module']=$row['id_module'];
            $session['id_classroom']=$row['id_classroom'];
            $session['id_teacher']=$row['id_teacher'];
            $session['id_groupe']=$row['id_groupe'];
            $id_groupe=$row['id_groupe'];
            $query="SELECT * FROM `groupe` where (id='$id_groupe')";
            $result=mysqli_query($connection, $query);
            if($result->num_rows!=0){
                $row2 = mysqli_fetch_array($result);
                $module=array();
                $module['id']=$row2['id'];
                $module['id_specialty']=$row2['id_specialty'];
                $module['id_level']=$row2['id_level'];
                $module['name']=$row2['name'];
                if(!in_array($myAswer['groupe'], $module))
                array_push($myAswer['groupe'], $module);
            }
            $session['semester_1']=$row['semester_1'];
            $myAswer['result']=$myAswer['result']+1;
            if($op==1)
            array_push($myAswer['in']['session'],$session);
            else {
              array_push($myAswer['up']['session'],$session);  
            }
        }
              
              
              }
              break;//session
          };
          case 23:{
                  $select_query="SELECT DISTINCT news.id ,news.id_user as 'id_poster',news_visibilitu.id_user,news.title, news.news_type, news.content,news_visibilitu.vote_possible FROM `news_visibilitu`,`news` WHERE (news.id = '$id' and news.id=news_visibilitu.id_news AND news_visibilitu.id_user='$id_')";
                  $result_news=mysqli_query($connection, $select_query);
                  if($result_news->num_rows!=0){ 
                      $new=array();
                      $row = mysqli_fetch_array($result_news);
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
                array_push($myAswer['in']['news'],$new); 
                break;
                }    
            case 1:{
                $query="SELECT * FROM `change_of_session` WHERE (id='$id_new')";
                $result_query= mysqli_query($connection,$query);
                $change=mysqli_fetch_array($result_query);
                $new['id_old_session']=$change['id_old_session'];
                $new['id_new_session']=$change['id_new_session'];
                array_push($myAswer['in']['news'],$new); 
                break;}
            case 2:{        
                $query="SELECT * FROM `consultation` WHERE (id='$id_new')";
                $result_query=  mysqli_query($connection,$query);
                $consultation=mysqli_fetch_array($result_query);
                $new['id_sesson']=$consultation['id_sesson'];
                $new['date']=$consultation['date'];
                array_push($myAswer['in']['news'],$new); 
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
                array_push($myAswer['in']['news'],$new); 
                break;}
            case 4:{
                $query="SELECT * FROM `holiday` WHERE (id='$id_new')";
                $result_query=  mysqli_query($connection,$query);
                $holiday=mysqli_fetch_array($result_query);
                $new['begin_date']=$holiday['begin_date'];
                $new['end_date']=$holiday['end_date'];
                array_push($myAswer['in']['news'],$new); 
                break;}
            case 5:{ 
                $query="SELECT * FROM `marks_displayed` WHERE (id='$id_new')";
                $result_query=  mysqli_query($connection,$query);
                $marks_displayed=mysqli_fetch_array($result_query);
                $new['id_teacher']=$marks_displayed['id_teacher'];
                $new['id_exam']=$marks_displayed['id_exam'];
                array_push($myAswer['in']['news'],$new); 
                break;}
            case 6:{ 
                $query="SELECT * FROM `meeting` WHERE (id='$id_new')";
                $result_query=  mysqli_query($connection,$query);
                $meeting=mysqli_fetch_array($result_query);
                $new['id_classroom']=$meeting['id_classroom'];
                $new['date']=$meeting['date'];
                array_push($myAswer['in']['news'],$new); 
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
                array_push($myAswer['in']['news'],$new); 
            break;}
            case 9:{ 
                $query="SELECT * FROM `strike` WHERE (id='$id_new')";
                $result_query=mysqli_query($connection,$query);
                $strike=mysqli_fetch_array($result_query);
                $new['begin_date']=$strike['begin_date'];
                $new['end_date']=$strike['end_date'];
                $new['cause']=$strike['cause'];
                array_push($myAswer['in']['news'],$new); 
                break;
            
            }
        }
                  }
              break;//news
          };          
          case 27:{
              $select_done=" select `request_done`.`id` as `id`, `request_done`.`id_establishement` as `id_establishement`, `request_done`.`id_admin` as `id_admin` from `request_done`,`document_request` where ( `document_request`.id = '$id' and `document_request`.id =`request_done`.id and `document_request`.id_user=$id_)";
             $result_done=mysqli_query($connection, $select_done);
             if($result_done->num_rows!=0){
                 $row_d = mysqli_fetch_array($result_done);
                 $done=array();
                 $done['id']=$row_d['id'];
                 $done['id_establishement']=$row_d['id_establishement'];
                 $done['id_admin']=$row_d['id_admin'];
                 array_push($myAswer['in']['docs'], $done);
                 $my_user=array();
                 $poster=$row_d['id_admin'];
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
                $qq="SELECT * FROM `aministrator` WHERE id_user='$poster'";
                 $rr=mysqli_query($connection, $qq);
                 $rrr=mysqli_fetch_array($rr); 
                 $myAswer['result']=$myAswer['result']+1;
                 $my_user['id_work']=$rrr['id_work'];
                 if(!in_array($my_user, $myAswer['user']))
                 array_push($myAswer['user'],$my_user); 
                 $select_j="select * from journal where ( id='$id' and op=1 and tab=27 )";
                  $result_j=mysqli_query($connection, $select_j);
                  if($result_j->num_rows!=0){
                   $_row = mysqli_fetch_array($result_j);
                   $j=array();
                   $j['id']=$_row['id'];
                   $j['id_user']=$_row['id_user'];
                   $j['op']=$_row['op'];
                   $j['tab']=$_row['tab'];
                   $j['date']=$_row['date'];
                   array_push($myAswer['journal'], $j);
             }
             
             }
              
              break;//doc done
          
          };
          case 42:{
              
              break;//absence
          
          };
          case 41:{
              if($groupe!=0){
                  $query="SELECT * FROM `marks` where id_student='$id_' and id='$id'";
                  $result=mysqli_query($connection, $query);
                 if($result->num_rows!=0){
                    $row = mysqli_fetch_array($result);
                    $mark=array();
                    $mark['id']=$row['id'];
                    $mark['id_student']=$row['id_student'];
                    $mark['id_exam']=$row['id_exam'];
                    $mark['note']=$row['note'];
                    $myAswer['result']=$myAswer['result']+1;
                    array_push($myAswer['marks'],$mark);
   
                }
              }
              break;//marks
          
          };
          case 20:{
                      $select_query="SELECT DISTINCT message.id as 'id' ,message.id_user as 'id_sender',"
                . "message.msg,messege_sent_to.id_msg,messege_sent_to.id_user,messege_sent_to.seen,messege_sent_to.id as"
                . " 'sending' FROM `messege_sent_to`,`message` WHERE ( message.id=messege_sent_to.id_msg"
                . "  AND  messege_sent_to.id_user='$id_' and  `message`.id='$id' )";
                $result_table=mysqli_query($connection, $select_query);
                 if($result_table->num_rows!=0){
                     $row = mysqli_fetch_array($result_table);
                                 $msg['id']=$row['id'];
            $id__=$row['id'];
            $msg['id_user']=$row['id_sender'];
            $msg['msg']=$row['msg'];
            $poster=$row['id_sender'];
            $id_new=$row['sending'];
            $receiver=$row['id_user'];
            $select_journal="select * from journal where ( id='$id__' and op=1 and id_user='$poster' and tab=20)";
             $result_journal=mysqli_query($connection, $select_journal);
             if($result_journal->num_rows!=0){
                 $_row = mysqli_fetch_array($result_journal);
                 $journal=array();
                 $journal['id']=$_row['id'];
                 $journal['id_user']=$_row['id_user'];
                 $journal['op']=$_row['op'];
                 $journal['tab']=$_row['tab'];
                 $journal['date']=$_row['date'];
             array_push($myAswer['journal'], $journal);}
            //$msg_['msg']=$msg;
            $myAswer['result']=$myAswer['result']+1;
            array_push($myAswer['in']['msgs'], $msg);
            $s['id']=$id_new;
            $s['id_msg']=$row['id_msg'];
            $s['id_user']=$row['id_user'];
            $s['seen']=$row['seen'];
            array_push($myAswer['in']['msgs_send_to'],$s);
                            
                 
                   $select_poster="SELECT * FROM `user` where( id='$poster' )";           
            $result_poster=mysqli_query($connection, $select_poster);
            while ($row12 = mysqli_fetch_array($result_poster)) {
                $my_user=array();
                $my_user['id']=$row12['id'];
                $id_user=$row12['id'];
//                $my_user['user_type']=$row12['user_type'];
                $type=$row12['user_type'];
                $my_user['email']=$row12['email']; 
                $my_user['last_name']=$row12['last_name']; 
                $my_user['first_name']=$row12['first_name'];
                $my_user['sexe']=$row12['sexe'];
                $my_user['birth_date']=$row12['birth_date'];
                $my_user['home_town']=$row12['home_town'];                
                $my_user['avatar']=$row12['avatar'];
                if(!in_array($my_user, $myAswer['user']))
                array_push($myAswer['user'], $my_user);
            }   
                 }
              break;//msg
          
          };
          case 21:{
             $select_query="SELECT DISTINCT message.id as 'id' ,message.id_user as 'id_sender',"
                . "message.msg,messege_sent_to.id_msg,messege_sent_to.id_user,messege_sent_to.seen,messege_sent_to.id as"
                . " 'sending' FROM `messege_sent_to`,`message` WHERE ( message.id=messege_sent_to.id_msg"
                . "  AND  `message`.id_user='$id_' and  `message`.id='$id' )";
                             $result_table=mysqli_query($connection, $select_query);
                 if($result_table->num_rows!=0){
                     $row = mysqli_fetch_array($result_table); 
                     $id_new=$row['sending'];
                                 $s['id']=$id_new;
            $s['id_msg']=$row['id_msg'];
            $s['id_user']=$row['id_user'];
            $s['seen']=$row['seen'];
            $receiver=$row['id_user'];
            array_push($myAswer['up']['msgs_send_to'],$s);
            $select_journal="select * from journal where ( id='$id_new' and op=1 and id_user='$receiver' and tab=21)";
             $result_journal=mysqli_query($connection, $select_journal);
             if($result_journal->num_rows!=0){
                 $_row = mysqli_fetch_array($result_journal);
                 $journal=array();
                 $journal['id']=$_row['id'];
                 $journal['id_user']=$_row['id_user'];
                 $journal['op']=$_row['op'];
                 $journal['tab']=$_row['tab'];
                 $journal['date']=$_row['date'];
                 array_push($myAswer['journal'], $journal);
             }
              break;
                   
      }
          }
            case 38:{
    $select_query="select * from `voted` where id='$id'";
    $result_news=mysqli_query($connection, $select_query);
    if($result_news->num_rows!=0){
        $new_row = mysqli_fetch_array($result_news);
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
           $id_p=$new_row['id_post'];
           $w="select * from `news_visibilitu` where id_news='$id_p' and id_user='$id_'";
               $re=mysqli_query($connection, $w);
    if($result_news->num_rows!=0){
        $nw = mysqli_fetch_array($re);
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
                break;
      }
      }
    }
          }
          $w="select * from journal order by date DESC";
          $r=mysqli_query($connection, $w);
          if($r->num_rows!=0){
             $v = mysqli_fetch_array($r); 
            $myAswer['update']=$v['date'];
          }
    }else{
    $myAswer['params']=0;
}
mysqli_close($connection);
 header('Content-type: application/json');
 echo json_encode($myAswer); 

}

     /*   $myAswer['del']['session']=array();
    $myAswer['del']['news']=array();
    $myAswer['del']['docs']=array();
    $myAswer['del']['abs']=array();
    $myAswer['del']['note']=array();
    $myAswer['del']['msg']=array();
    $myAswer['del']['marks']=array();
    $myAswer['del']['marksD']=array();
    $myAswer['del']['exam']=array();
    $myAswer['del']['strike']=array();
    $myAswer['del']['meeting']=array();
    $myAswer['del']['holyday']=array();
    $myAswer['del']['change']=array();
    $myAswer['del']['procedure']=array();*/