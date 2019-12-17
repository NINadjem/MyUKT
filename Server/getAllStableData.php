<?php
require './databaseConnexion.php';
$dataBase_connection = new database_connection();
$connection=$dataBase_connection->getConnection();
if($connection){
$myAswer=array();
$myAswer['result']=0;
$myAswer['exam_type']=array();
$myAswer['classroom']=array();
$myAswer['days']=array();
$myAswer['level']=array();
$myAswer['session_type']=array();
$myAswer['specialty']=array();
$myAswer['town']=array();
$myAswer['work']=array();
$myAswer['esth']=array();
$myAswer['module']=array();
$myAswer['responsibility']=array();
$query="SELECT * FROM `classroom`";
$result=mysqli_query($connection, $query);
if($result->num_rows!=0){
    while ($row = mysqli_fetch_array($result)) {
        $classroom=array();
        $myAswer['result']=1+$myAswer['result'];
        $classroom['id']=$row['id'];
        $classroom['name']=$row['name'];
        array_push($myAswer['classroom'], $classroom);
    }
}

$query="SELECT * FROM `days`";
$result=mysqli_query($connection, $query);
if($result->num_rows!=0){
    while ($row = mysqli_fetch_array($result)) {
        $day=array();
        $myAswer['result']=1+$myAswer['result'];
        $day['id']=$row['id'];
        $day['name']=$row['name'];
        array_push($myAswer['days'], $day);
    }
}


$query="SELECT * FROM `exam_type`";
$result=mysqli_query($connection, $query);
if($result->num_rows!=0){
    while ($row = mysqli_fetch_array($result)) {
        $exam_type=array();
        $myAswer['result']=1+$myAswer['result'];
        $exam_type['id']=$row['id'];
        $exam_type['type']=$row['type'];
        array_push($myAswer['exam_type'], $exam_type);
    }
}


$query="SELECT * FROM `level`";
$result=mysqli_query($connection, $query);
if($result->num_rows!=0){
    while ($row = mysqli_fetch_array($result)) {
        $level=array();
        $myAswer['result']=1+$myAswer['result'];
        $level['id']=$row['id'];
        $level['abrg']=$row['abrg'];
        array_push($myAswer['level'], $level);
    }
}


$query="SELECT * FROM `session_type`";
$result=mysqli_query($connection, $query);
if($result->num_rows!=0){
    while ($row = mysqli_fetch_array($result)) {
        $session_type=array();
        $myAswer['result']=1+$myAswer['result'];
        $session_type['id']=$row['id'];
        $session_type['name']=$row['name'];
        array_push($myAswer['session_type'], $session_type);
    }
}


$query="SELECT * FROM `specialty`";
$result=mysqli_query($connection, $query);
if($result->num_rows!=0){
    while ($row = mysqli_fetch_array($result)) {
        $specialty=array();
        $myAswer['result']=1+$myAswer['result'];
        $specialty['id']=$row['id'];
        $specialty['name']=$row['name'];
        array_push($myAswer['specialty'], $specialty);
    }
}


$query="SELECT * FROM `town`";
$result=mysqli_query($connection, $query);
if($result->num_rows!=0){
    while ($row = mysqli_fetch_array($result)) {
        $town=array();
        $myAswer['result']=1+$myAswer['result'];
        $town['id']=$row['id'];
        $town['town_name']=$row['town_name'];
        array_push($myAswer['town'], $town);
    }
}


$query="SELECT * FROM `work`";
$result=mysqli_query($connection, $query);
if($result->num_rows!=0){
    while ($row = mysqli_fetch_array($result)) {
        $work=array();
        $myAswer['result']=1+$myAswer['result'];
        $work['id_work']=$row['id_work'];
        $work['name']=$row['name'];
        array_push($myAswer['work'], $work);
    }
    $query="SELECT * FROM `establishment`";
$result=mysqli_query($connection, $query);
if($result->num_rows!=0){
    while ($row = mysqli_fetch_array($result)) {
        $work=array();
        $myAswer['result']=1+$myAswer['result'];
        $work['id']=$row['id'];
        $work['name']=$row['name'];
        array_push($myAswer['esth'], $work);
    }
}
$query="SELECT * FROM `responsibility`";
$result=mysqli_query($connection, $query);
if($result->num_rows!=0){
    while ($row = mysqli_fetch_array($result)) {
        $r=array();
        $myAswer['result']=1+$myAswer['result'];
        $r['id']=$row['id'];
        $r['name']=$row['name'];
        array_push($myAswer['responsibility'], $r);
    }
}
$query="SELECT * FROM `module`";
$result=mysqli_query($connection, $query);
if($result->num_rows!=0){
    while ($row = mysqli_fetch_array($result)) {
        $m=array();
        $myAswer['result']=1+$myAswer['result'];
        $m['id']=$row['id'];
        $m['name']=utf8_encode($row['name']);
        $m['abrg']=$row['abrg'];
        array_push($myAswer['module'], $m);
    }
}
}
mysqli_close($connection);
 header('Content-type: application/json');
 echo json_encode($myAswer); 
}

