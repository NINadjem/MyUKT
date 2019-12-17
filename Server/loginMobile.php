<?php
/*op:
 * 1->student
 * 2->teacher
 * 0->admin
 * 4->delegue
 * 5->responsable

 */
require './databaseConnexion.php';
$dataBase_connection = new database_connection();
$connection=$dataBase_connection->getConnection();
if($connection){
$myAswer=array();
if (isset($_GET['email'])) {
    $email= $_GET['email'];
    $myAswer['params']=1;
    $myAswer['result']=0;
    //***************************************************USER****************************************////*********//
    $select_query="SELECT * FROM `user` WHERE(user.email='$email')";
    $result_user=mysqli_query($connection, $select_query);
    if($result_user->num_rows!=0){
    //there is a result so we can get the infos 
        $row_user=mysqli_fetch_array($result_user);
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
                $my_user['avatar']=$row_user['avatar'];
            $select="SELECT DISTINCT * FROM `journal` WHERE (id='$id_user' AND op='1' AND tab='37')";
           $result_select=mysqli_query($connection, $select);
           $new_row=mysqli_fetch_array($result_select);           
           $my_user['add_date']=$new_row['date'];
                switch ($type){
                    case 4:{$myAswer['admin']=0;
    //***************************************************STUDENT****************************************////*********//
        $select_student="SELECT * FROM `student` WHERE (id_user='$id_user')";
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
                  // we check if he is a delegue
                  $select_delege="SELECT * FROM `delege` WHERE (id_delege='$id_user')";
                  $result_delegue=mysqli_query($connection, $select_delege); 
                  if($result_delegue->num_rows!=0){
                      $row_delege=mysqli_fetch_array($result_delegue);
                      $myAswer['result']=$myAswer['result']+1;
                      $my_user['id_promo']=$row_delege['id_level'];
                     $myAswer['user']=$my_user;  
                  }
              
         }
                    }
                    break;
                  }
                    case 5:{$myAswer['admin']=0;
             // the user isn't a student
        $select_teacher="SELECT * FROM `teacher` WHERE (id_user='$id_user')";
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
                      $myAswer['user']=$my_user;
                  }
                    }
                    break;
                  }
                    case 0:{// the user is maybe an admin
           $myAswer['result']=$myAswer['result']+1;
           $myAswer['admin']=1;
           break;
        }
                    case 1:{$myAswer['admin']=0;
                                $select_student="SELECT * FROM `student` WHERE (id_user='$id_user')";
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
         $myAswer['user']=$my_user;
                  }}
                  break;
                    }
                    case 2:{$myAswer['admin']=0;
        $select_teacher="SELECT * FROM `teacher` WHERE (id_user='$id_user')";
        $result_teacher=mysqli_query($connection, $select_teacher);
        if($result_teacher->num_rows!=0){
           $myAswer['result']=$myAswer['result']+1;
           $row_teacher=mysqli_fetch_array($result_teacher);
           $my_user['id_specialty']=$row_teacher['id_specialty'];
           $myAswer['user']=$my_user;

        }
                    break;}
        
        }        

                
    }else{
           $insert_in_journal_query="INSERT INTO `journal`(`id_user`, `op`, `id`, `tab`) VALUES ('40','5','NULL','37')";
           $result_insert_in_journal=mysqli_query($connection, $insert_in_journal_query);
    }
}else{
    $myAswer['params']=0;
}
mysqli_close($connection);
 header('Content-type: application/json');
 echo json_encode($myAswer); 
}