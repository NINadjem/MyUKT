<?php
require './databaseConnexion.php';
$dataBase_connection = new database_connection();
$connection=$dataBase_connection->getConnection();
if($connection){
$myAswer=array();
if (isset($_GET['id'])&&isset($_GET['id_sender'])) {
    $myAswer['params']=1;
    $id=$_GET['id'];
    $id_s=$_GET['id_sender'];
    $select_query="select `messege_sent_to`.id as id from `messege_sent_to`,`message` where `message`.id=`messege_sent_to`.id_msg and "
             . "`messege_sent_to`.id_user='$id' and `messege_sent_to`.seen='0' and `message`.id_user='$id_s'";
    $result_select=mysqli_query($connection, $select_query);
    if($result_select->num_rows!=0){
        while ($row1 = mysqli_fetch_array($result_select)) {
            $i=$row1['id'];
            $update_query="UPDATE `messege_sent_to` SET `seen`='1' WHERE (id='$i')";
            $result_update=mysqli_query($connection, $update_query);
    if(mysqli_affected_rows($connection)<=0){
        $myAswer['update']=0;
    }else{
      $myAswer['update']=1;
            $insert_in_journal_query="INSERT INTO `journal`(`id_user`, `op`, `id`, `tab`) VALUES ('$id','1','$i','21')";
           $result_insert_in_journal=mysqli_query($connection, $insert_in_journal_query);
       if(mysqli_affected_rows($connection)>0){
           $myAswer['add_journal']=1;
       }else{
           $myAswer['add_journal']=0;
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

}
