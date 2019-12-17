<?php
require './databaseConnexion.php';
$dataBase_connection = new database_connection();
$connection=$dataBase_connection->getConnection();
if($connection){
$myAswer=array();
$myAswer['journal']=array();
$myAswer['requests']=array();
$myAswer['requests_done']=array();
$myAswer['users']=array();
if (isset($_GET['id_user'])) {
    $id= $_GET['id_user'];
    $myAswer['params']=1;
    $myAswer['result']=0;
    $query="SELECT * FROM `document_request` WHERE id_user='$id'";
    $result=mysqli_query($connection, $query);
    if($result->num_rows!=0){
         while ($row = mysqli_fetch_array($result)) {
             $request=array();
             $myAswer['result']=1;
             $request['id']=$row['id'];
             $request['id_user']=$row['id_user'];
             $request['doc']=utf8_encode($row['doc']);
             $request['reason']=utf8_encode($row['reason']);
             $id_doc=$row['id'];
             array_push($myAswer['requests'], $request);
             $select_journal="select * from journal where ( id='$id_doc' and op=1 and id_user='$id' and tab=7)";
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
             $select_done=" select * from `request_done` where (id= '$id_doc')";
             $result_done=mysqli_query($connection, $select_done);
             if($result_done->num_rows!=0){
                 $row_d = mysqli_fetch_array($result_done);
                 $done=array();
                 $done['id']=$row_d['id'];
                 $done['id_establishement']=$row_d['id_establishement'];
                 $done['id_admin']=$row_d['id_admin'];
                 array_push($myAswer['requests_done'], $done);
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
                 array_push($myAswer['users'],$my_user); 
                 $select_j="select * from journal where ( id='$id_doc' and op=1 and tab=27 )";
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
         }
         
    }
    
    
}else{
    $myAswer['params']=0;
}
mysqli_close($connection);
 header('Content-type: application/json');
 echo json_encode($myAswer); 

}


