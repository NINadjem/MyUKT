<?php
require './databaseConnexion.php';
$dataBase_connection = new database_connection();
$connection=$dataBase_connection->getConnection();
if($connection){
   $myAswer=array();
if (isset($_GET['id_user'])&&isset($_GET['alt'])&&isset($_GET['long'])&&isset($_GET['lat'])) { 
    $id_user=$_GET['id_user'];
    $alt=$_GET['alt'];
    $long=$_GET['long'];
    $lat=$_GET['lat'];
    $myAswer['params']=1;
    $query="INSERT INTO `location`( `id_user`, `latitude`, `longitude`,`altitude`) VALUES "
            . "('$id_user','$lat','$long','$alt')";
    $result=mysqli_query($connection, $query);
    if(mysqli_affected_rows($connection)<=0){
        $myAswer['insert']=0;
    }else{
           $select="SELECT DISTINCT * FROM `location` WHERE (id_user='$id_user' AND `latitude`='$lat' and"
                   . " `longitude`='$long' and `altitude`='$alt') order by id DESC";
           $result_select=mysqli_query($connection, $select);
           $new_row=mysqli_fetch_array($result_select);
           $id_new=$new_row['id'];
           $myAswer['id_new']=$id_new;
           $insert_in_journal_query="INSERT INTO `journal`(`id_user`, `op`, `id`, `tab`) VALUES ('$id_user','1','$id_new','40')";
           $result_insert_in_journal=mysqli_query($connection, $insert_in_journal_query);
       if(mysqli_affected_rows($connection)>0){
           $myAswer['add_journal']=1;
           $select="SELECT DISTINCT * FROM `journal` WHERE (id_user='$id_user' AND id='$id_new' AND op='1' AND tab='40')";
           $result_select=mysqli_query($connection, $select);
           $new_row=mysqli_fetch_array($result_select);           
           $myAswer['date']=$new_row['date'];
       }else{
           $myAswer['add_journal']=0;
       }
    }
}else{
   $myAswer['params']=0; 
}
mysqli_close($connection);
 header('Content-type: application/json');
 echo json_encode($myAswer); 
}
