<?php

require './databaseConnexion.php';
$dataBase_connection = new database_connection();
$connection=$dataBase_connection->getConnection();
if($connection){
$myAswer=array();
if (isset($_GET['id'])&&isset($_GET['id_groupe'])) {
    $id= $_GET['id_groupe'];
    $id_=$_GET['id'];
    $myAswer['params']=1;
    $myAswer['result']=0;
    $select_query="SELECT DISTINCT `id`, `id_session_type`, `enabled`, `id_day`, TIME_FORMAT(`time`, '%H:%i') AS `time`, `id_module`, `id_classroom`, `id_teacher`, `id_groupe`, `semester_1` FROM `session` WHERE (id_groupe='$id')";
    /*}else{$myAswer['with_update']=0;
        $select_query="SELECT * FROM `session` WHERE (id_groupe='$id' AND semester_1='$semester'AND enabled=1)";}*/
    $result_table=mysqli_query($connection, $select_query);
    if($result_table->num_rows!=0){
        $myAswer['table']=array();
        $myAswer['user']=array();
        $myAswer['groupe']=array();
        while ($row = mysqli_fetch_array($result_table)) {
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
                $my_user['avatar']=$row_user['avatar'];
           $select="SELECT DISTINCT * FROM `journal` WHERE (id='$id_user' AND op='1' AND tab='37')";
           $result_select=mysqli_query($connection, $select);
           $new_row=mysqli_fetch_array($result_select);           
           $my_user['add_date']=$new_row['date'];
            switch ($type){
                    case 5:{
                        $myAswer['admin']=0;
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
                      array_push($myAswer['user'],$my_user);
                  }
                    }
                    break;
                  }

                    case 2:{$myAswer['admin']=0;
        $select_teacher="SELECT * FROM `teacher` WHERE (id_user='$id_user')";
        $result_teacher=mysqli_query($connection, $select_teacher);
        if($result_teacher->num_rows!=0){
           $myAswer['result']=$myAswer['result']+1;
           $row_teacher=mysqli_fetch_array($result_teacher);
           $my_user['id_specialty']=$row_teacher['id_specialty'];
           array_push($myAswer['user'],$my_user);

        }
                    break;}
        
        }         
             
            }
            $session['id_groupe']=$row['id_groupe'];
            $session['semester_1']=$row['semester_1'];
           $select="SELECT DISTINCT * FROM `journal` WHERE (id='$_' AND op='1' AND tab='30')";
           $result_select=mysqli_query($connection, $select);
           $new_row=mysqli_fetch_array($result_select); 
           $session['add_date']=$new_row['date'];
            //$session_['session']=$session;
            $myAswer['result']=$myAswer['result']+1;
            array_push($myAswer['table'],$session);
        }
        
    }
    $y="SELECT * FROM `groupe` where (id='$id')";
            $t=mysqli_query($connection, $y);
            if($t->num_rows!=0){
                $w = mysqli_fetch_array($t);
                $module=array();
                $module['id']=$w['id'];
                $module['id_specialty']=$w['id_specialty'];
                $module['id_level']=$w['id_level'];
                $module['name']=$w['name'];
                array_push($myAswer['groupe'], $module);
            }
}else{
    $myAswer['params']=0;
}
mysqli_close($connection);
 header('Content-type: application/json');
 echo json_encode($myAswer); 
}

