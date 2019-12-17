<?php
require './databaseConnexion.php';
$dataBase_connection = new database_connection();
$connection=$dataBase_connection->getConnection();
if($connection){
/*$select_query="SELECT * FROM `responsble_teacher` WHERE 1";
    $result_table=mysqli_query($connection, $select_query);
    if($result_table->num_rows!=0){
        while ($row = mysqli_fetch_array($result_table)) {
            $id=$row['id'];
           $insert_in_journal_query="INSERT INTO `journal`(`id_user`, `op`, `id`, `tab`) VALUES ('26','1','$id','28')";
           $result_insert_in_journal=mysqli_query($connection, $insert_in_journal_query);
           $update_query="UPDATE `user` SET `user_type`=5 WHERE (`id`='$id')";
           $result=mysqli_query($connection, $update_query);
        }
    }*/
            /*   $insert_in_journal_query="INSERT INTO `journal`(`id_user`, `op`, `id`, `tab`) VALUES ('1','1','2','27')";
           $result_insert_in_journal=mysqli_query($connection, $insert_in_journal_query);*/
    $select="select * from `groupe_member` where id_groupe='17'";
        $result_table=mysqli_query($connection, $select);
    if($result_table->num_rows!=0){
         while ($row = mysqli_fetch_array($result_table)) {
       $id=$row['id_student'];
       $insert="INSERT INTO `news_visibilitu`( `id_user`, `id_news`, `vote_possible`) VALUES ('$id',10,0)";
       $r=mysqli_query($connection, $insert);
           
            
         }
    }
    
}

