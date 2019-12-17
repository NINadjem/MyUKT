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
{

    /*$last_update=$_GET['last_update'];
    $select_query="SELECT message.id as 'id' ,message.id_user as 'id_sender',message.msg,message.add_date as "
            . "'write_date',messege_sent_to.id_msg,messege_sent_to.id_user,messege_sent_to.seen"
            . " FROM `messege_sent_to`,`message` WHERE (message.id=messege_sent_to.id_msg AND messege_sent_to.id_user='$id' "
            . "AND add_date>='$last_update')";}*/

        $select_query="SELECT DISTINCT message.id as 'id' ,message.id_user as 'id_sender',"
                . "message.msg,messege_sent_to.id_msg,messege_sent_to.id_user,messege_sent_to.seen,messege_sent_to.id as"
                . " 'sending' FROM `messege_sent_to`,`message` WHERE ( message.id=messege_sent_to.id_msg"
                . "  AND ( messege_sent_to.id_user='$id' or message.id_user = '$id' ) )";}
    $result_table=mysqli_query($connection, $select_query);
    if($result_table->num_rows!=0){
        $myAswer['msgs']=array();
        $myAswer['msgs_send_to']=array();
        $myAswer['journal']=array();
        $myAswer['user']=array();
        while ($row = mysqli_fetch_array($result_table)) {
            $msg['id']=$row['id'];
            $id__=$row['id'];
            $msg['id_user']=$row['id_sender'];
            $msg['msg']=$row['msg'];
            $poster=$row['id_sender'];
            $id_new=$row['sending'];
            $select_journal="select * from journal where ( id='$id__' and op=1 and id_user='$poster' and tab=20)";
             $result_journal=mysqli_query($connection, $select_journal);
             if($result_journal->num_rows!=0){
                 $_row = mysqli_fetch_array($result_journal);
                 $journal=array();
                 $journal['id']=$_row['id'];
                 $journal['id_user']=$_row['id_user'];
                 $journal['op']=$_row['op'];
                 $journal['tab']=$_row['tab'];
                 $journal['date']=$_row['date'];
             array_push($myAswer['journal'], $journal);}
            //$msg_['msg']=$msg;
            $myAswer['result']=$myAswer['result']+1;
            array_push($myAswer['msgs'], $msg);
            $s['id']=$id_new;
            $s['id_msg']=$row['id_msg'];
            $s['id_user']=$row['id_user'];
            $s['seen']=$row['seen'];
            $seen=$row['seen'];
            $receiver=$row['id_user'];
            if($seen==1){
            array_push($myAswer['msgs_send_to'],$s);
             $select_journal="select * from journal where ( id='$id_new' and op=1 and id_user='$receiver' and tab=21)";
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
            }else{
              array_push($myAswer['msgs_send_to'],$s);  
            }
             $select_poster="SELECT * FROM `user` where( id='$poster' or id='$receiver' )";           
            $result_poster=mysqli_query($connection, $select_poster);
            while ($row12 = mysqli_fetch_array($result_poster)) {
                $my_user=array();
                $my_user['id']=$row12['id'];
                $id_user=$row12['id'];
//                $my_user['user_type']=$row12['user_type'];
                $type=$row12['user_type'];
                $my_user['email']=$row12['email']; 
                $my_user['last_name']=$row12['last_name']; 
                $my_user['first_name']=$row12['first_name'];
                $my_user['sexe']=$row12['sexe'];
                $my_user['birth_date']=$row12['birth_date'];
                $my_user['home_town']=$row12['home_town'];                
                $my_user['avatar']=$row12['avatar'];
                if(!in_array($my_user, $myAswer['user']))
                array_push($myAswer['user'], $my_user);
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




