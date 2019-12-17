<?php
require './databaseConnexion.php';
$dataBase_connection = new database_connection();
$connection=$dataBase_connection->getConnection();
if($connection){
$myAswer=array();
if (isset($_GET['id_user'])) {
    $id= $_GET['id_user'];
    $myAswer['params']=1;
    $myAswer['result']=0;
    /*if(isset($_GET['last_update'])){
    $myAswer['with_update']=1;
    $last_update=$_GET['last_update'];
    $select_query="SELECT request_on.id ,request_on.id_admin FROM `document_request`,`request_on` WHERE "
            . "(document_request.id_user='$id' AND request_on.add_date>='$last_update')";
    $select_query_done="SELECT request_done.id,request_done.id_establishement FROM `document_request`,"
            . "`request_done` WHERE (document_request.id_user='$id' AND request_done.add_date>='$last_update')";
    }
    else{$myAswer['with_update']=0;*/
        $select_query="SELECT DISTINCT request_on.id ,request_on.id_admin FROM `document_request`,`request_on` WHERE "
            . "(document_request.id_user='$id')";
    $select_query_done="SELECT DISTINCT request_done.id,request_done.id_establishement FROM `document_request`,"
            . "`request_done` WHERE (document_request.id_user='$id')";
    
    $result_table=mysqli_query($connection, $select_query);
    $result_table_done=mysqli_query($connection, $select_query_done);
    if($result_table->num_rows!=0){
        $myAswer['result_on']=0;
        $myAswer['requests_on']=array();
        while ($row = mysqli_fetch_array($result_table)) {
            $on['id']=$row['id'];
            $id_new=$row['id'];
            $on['id_admin']=$row['id_admin'];
           $select="SELECT * FROM `journal` WHERE (id_user='$id' AND id='$id_new' AND op='1'  AND tab='27')";
           $result_select=mysqli_query($connection, $select);
           $new_row=mysqli_fetch_array($result_select); 
           $on['add_date']=$new_row['date'];
            $on_['on']=$on;
            $myAswer['result']=$myAswer['result']+1;
            $myAswer['result_on']=$myAswer['result_on']+1;
            array_push($myAswer['requests_on'], $on_);
           
    }}
        if($result_table_done->num_rows!=0){
        $myAswer['result_done']=0;    
        $myAswer['requests_done']=array();
        while ($row1 = mysqli_fetch_array($result_table_done)) {
            $done['id']=$row1['id'];
            $id_new=$row1['id'];
            $done['id_establishement']=$row1['id_establishement'];
           $select="SELECT  DISTINCT * FROM `journal` WHERE (id_user='$id' AND id='$id_new' AND op='1' AND tab='26')";
           $result_select=mysqli_query($connection, $select);
           $new_row=mysqli_fetch_array($result_select); 
           $done['add_date']=$new_row['date'];
            $done_['done']=$done;
            $myAswer['result']=$myAswer['result']+1;
            $myAswer['result_done']=$myAswer['result_done']+1;
            array_push($myAswer['requests_done'], $done_);
        }   
    }
}else{
    $myAswer['params']=0;
}
mysqli_close($connection);
 header('Content-type: application/json');
 echo json_encode($myAswer); 
}


