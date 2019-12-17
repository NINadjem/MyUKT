<?php
require './databaseConnexion.php';
$dataBase_connection = new database_connection();
$connection=$dataBase_connection->getConnection();
if($connection){
$myAswer=array();
$myAswer['result']=0;
$query="select * from user";
$myAswer['user']=array();
$result=mysqli_query($connection, $query);
if($result->num_rows!=0){
while ($row_user = mysqli_fetch_array($result)){
    $my_user=array();
                $id_user=$row_user['id'];
                //$my_user['id']=$id_user;
                $my_user['user_type']=$row_user['user_type'];
                $type=$row_user['user_type'];
                $my_user['email']=$row_user['email'];
                $my_user['last_name']=$row_user['last_name'];
                $my_user['first_name']=$row_user['first_name'];
                $my_user['sexe']=$row_user['sexe'];
                $my_user['birth_date']=$row_user['birth_date'];
                //$my_user['password']=$row_user['pasword'];
                $home=$row_user['home_town'];
                $q="select * from town where id= '$home'";
                $rr=mysqli_query($connection, $q);
                if($rr->num_rows!=0){
                    $ro = mysqli_fetch_array($rr);
                    $my_user['home_town']=$ro['town_name'];
                }
                //$my_user['avatar']=$row_user['avatar'];
                switch ($type){
                    case 4:{
    //***************************************************STUDENT****************************************////*********//
        $select_student="SELECT * FROM `student` WHERE (id_user='$id_user')";
        $result_student=mysqli_query($connection, $select_student); 
         if($result_student->num_rows!=0){
             // the user is a student
           $row_student=mysqli_fetch_array($result_student);             
              $myAswer['result']=$myAswer['result']+1;
                  //$my_user['mat']=$row_student['mat'];
                  //$my_user['bib']=$row_student['bib'];
                  $select_student="SELECT * FROM `groupe_member` WHERE (id_student='$id_user')";
                  $result_student=mysqli_query($connection, $select_student); 
                  if($result_student->num_rows!=0){
                   $row_student=mysqli_fetch_array($result_student);   
                   $my_user['id_groupe']=$row_student['id_groupe'];
                   $grp=$row_student['id_groupe'];
                   $w="SELECT specialty.name as s ,level.abrg as l "
                           . ",groupe.name as g FROM groupe , level ,specialty WHERE "
                           . "(groupe.id='$grp' and specialty.id=groupe.id_specialty and level.id=groupe.id_level)";
                   $y=mysqli_query($connection, $w); 
                   if($y->num_rows!=0){
                       $t=mysqli_fetch_array($y);  
                       $my_user['add_date']=$t['s'];
                       $my_user['mat']=$t['g'];
                       $my_user['bib']=$t['l'];
                   }
                  // we check if he is a delegue
                  $select_delege="SELECT * FROM `delege` WHERE (id_delege='$id_user')";
                  $result_delegue=mysqli_query($connection, $select_delege); 
                  if($result_delegue->num_rows!=0){
                      $row_delege=mysqli_fetch_array($result_delegue);
                      $my_user['id_promo']=$row_delege['id_level'];
                       array_push($myAswer['user'],$my_user);
                  }
              
         }
                    }
                    break;
                  }
                    case 5:{
             // the user isn't a student
        $select_teacher="SELECT * FROM `teacher` WHERE (id_user='$id_user')";
        $result_teacher=mysqli_query($connection, $select_teacher);
        if($result_teacher->num_rows!=0){
           $myAswer['result']=$myAswer['result']+1;
           $row_teacher=mysqli_fetch_array($result_teacher);
                  $spc=$row_teacher['id_specialty'];
                  $sq="SELECT * FROM `specialty` where id= '$spc'";
                  $sqil=mysqli_query($connection, $sq);
                  $v=mysqli_fetch_array($sqil);
                  $my_user['id_specialty']=$v['name'];
                  //$myAswer['teacher_info']['teacher']=$my_teacher;   
                     $select_responsable="SELECT * FROM `responsble_teacher` WHERE (id='$id_user')";
                     $result_responsble=mysqli_query($connection, $select_responsable); 
                      if($result_responsble->num_rows!=0){
                      $row_responsbe=mysqli_fetch_array($result_responsble);
                    
                  $spc2=$row_responsbe['id_responsbility'];
                  $sq2="SELECT * FROM `responsibility` where id= '$spc2'";
                  $sqil2=mysqli_query($connection, $sq2);
                  $v2=mysqli_fetch_array($sqil2);
                  $my_user['id_responsbility']=$v2['name'];
                                 array_push($myAswer['user'],$my_user);
                  }
                    }
                    break;
                  }
                    case 0:{// the user is maybe an admin
           $myAswer['result']=$myAswer['result']+1;
            $select_teacher="SELECT * FROM `aministrator` WHERE (id_user='$id_user')";
        $result_teacher=mysqli_query($connection, $select_teacher);
        if($result_teacher->num_rows!=0){
           $myAswer['result']=$myAswer['result']+1;
           $row_teacher=mysqli_fetch_array($result_teacher);
           $wo=$row_teacher['id_work'];
           $p="select * from work where id_work = '$wo'";
           $p1=mysqli_query($connection, $p); 
           if($p1->num_rows!=0){
           $_1=mysqli_fetch_array($p1);
           $my_user['id_work']=$_1['name'];}
        array_push($myAswer['user'],$my_user);}
        break;
        }
                    case 1:{
                                $select_student="SELECT * FROM `student` WHERE (id_user='$id_user')";
        $result_student=mysqli_query($connection, $select_student); 
         if($result_student->num_rows!=0){
             // the user is a student
           $row_student=mysqli_fetch_array($result_student);             
              $myAswer['result']=$myAswer['result']+1;
                  /*$my_user['mat']=$row_student['mat'];
                  $my_user['bib']=$row_student['bib'];*/
                  $select_student="SELECT * FROM `groupe_member` WHERE (id_student='$id_user')";
                  $result_student=mysqli_query($connection, $select_student); 
                  if($result_student->num_rows!=0){
                   $row_student=mysqli_fetch_array($result_student);   
         $my_user['id_groupe']=$row_student['id_groupe'];
                            $grp=$row_student['id_groupe'];
                   $w="SELECT specialty.name as s ,level.abrg as l "
                           . ",groupe.name as g FROM groupe , level ,specialty WHERE "
                           . "(groupe.id='$grp' and specialty.id=groupe.id_specialty and level.id=groupe.id_level)";
                   $y=mysqli_query($connection, $w); 
                   if($y->num_rows!=0){
                       $t=mysqli_fetch_array($y);  
                       $my_user['add_date']=$t['s'];
                       $my_user['mat']=$t['g'];
                       $my_user['bib']=$t['l'];
                   }
           array_push($myAswer['user'],$my_user);
                  }}
                  break;
                    }
                    case 2:{
        $select_teacher="SELECT * FROM `teacher` WHERE (id_user='$id_user')";
        $result_teacher=mysqli_query($connection, $select_teacher);
        if($result_teacher->num_rows!=0){
           $myAswer['result']=$myAswer['result']+1;
           $row_teacher=mysqli_fetch_array($result_teacher);
                   $spc=$row_teacher['id_specialty'];
                  $sq="SELECT * FROM `specialty` where id= '$spc'";
                  $sqil=mysqli_query($connection, $sq);
                  $v=mysqli_fetch_array($sqil);
                  $my_user['id_specialty']=$v['name'];
           //$my_user['id_specialty']=$row_teacher['id_specialty'];
           array_push($myAswer['user'],$my_user);

        }
                    }
        break;
        }        
                
    
        
}
}
mysqli_close($connection);
 header('Content-type: application/json');
 echo json_encode($myAswer); 
}

