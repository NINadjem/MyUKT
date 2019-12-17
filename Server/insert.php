<?php
require './databaseConnexion.php';
$dataBase_connection = new database_connection();
$connection=$dataBase_connection->getConnection();
if($connection){
$query="SELECT * FROM `groupe_member`";
$result=mysqli_query($connection, $query);
while ($row = mysqli_fetch_array($result)) {
    $id=$row['id_student'];
   $q="INSERT INTO `news_visibilitu`( `id_user`, `id_news`, `vote_possible`) VALUES ('$id',13,1)";
   $t=mysqli_query($connection, $q);
}
}

