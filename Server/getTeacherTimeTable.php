<?php
require './databaseConnexion.php';
$dataBase_connection = new database_connection();
$connection=$dataBase_connection->getConnection();
if($connection){
$myAswer=array();
if (isset($_GET['id_teacher'])&&isset($_GET['id'])) {
    $id= $_GET['id_teacher'];
    $id_user=$_GET['id'];
    $myAswer['params']=1;
    $myAswer['result']=0;
    $select_query="SELECT DISTINCT `id`, `id_session_type`, `enabled`, `id_day`, TIME_FORMAT(`time`, '%H:%i') AS `time`, `id_module`, `id_classroom`, `id_teacher`, `id_groupe`, `semester_1` FROM `session` WHERE (id_teacher='$id')";
    $result_table=mysqli_query($connection, $select_query);
    if($result_table->num_rows!=0){
        $myAswer['table']=array();
        $myAswer['groupe']=array();
        while ($row = mysqli_fetch_array($result_table)) {
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
                array_push($myAswer['groupe'], $module);
            }
            $session['semester_1']=$row['semester_1'];
            $select="SELECT DISTINCT * FROM `journal` WHERE (id='$id' AND op='1' AND tab='30')";
            $result_select=mysqli_query($connection, $select);
            $new_row=mysqli_fetch_array($result_select);
            $session['add_date']=$new_row['date'];
            //$session_['session']=$session;
            $myAswer['result']=$myAswer['result']+1;
            array_push($myAswer['table'],$session);
        }
        
    }
}else{
    $myAswer['params']=0;
}
mysqli_close($connection);
 header('Content-type: application/json');
 echo json_encode($myAswer); 
}

