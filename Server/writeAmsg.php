<?php
require './databaseConnexion.php';
/*op:
 * 1->insert
 * 2->update
 * 3->delete
 * 4->read INFO
 * 5->try login
 * 6->login
 * table:
 * 1->admin
 * 2->change_of_session
 * 3->classroom
 * 4->consultation
 * 5->days
 * 6->delege
 * 7->doc_req
 * 8->encadreur
 * 9->esth
 * 10->exam
 * 11->exam_type
 * 12->grp
 * 13->grp_member
 * 14->holydy
 * 15->journal
 * 16->level
 * 17->marks_displayed
 * 18->meeting
 * 19->meeting_prensey
 * 20->messege
 * 21->messege_send_to
 * 22->module
 * 23->news
 * 24->news_visibility
 * 25->pedagogical_procedure
 * 27->request_done
 * 28->responsible_teacher
 * 29->responsibility
 * 30->session
 * 31->session_type
 * 32->spacialty
 * 33->strike
 * 34->student
 * 35->teacher
 * 36->town
 * 37->user
 * 38->voted
 * 39->work
 * 40->location
 * 41->marks
 */
$dataBase_connection = new database_connection();
$connection=$dataBase_connection->getConnection();
if($connection){
$myAswer=array();
if (isset($_GET['id'])&&isset($_GET['msg'])&&isset($_GET['date'])&&isset($_GET['id_receiver'])) {
    $myAswer['params']=1;
    $id_user=$_GET['id'];
    $msg=$_GET['msg'];
    $date=$_GET['date'];
    $receiver=$_GET['id_receiver'];
  {
       $insert_new_query="INSERT INTO `message`(`id_user`, `msg`) VALUES ('$id_user','$msg')";
       $result_insert_news=mysqli_query($connection, $insert_new_query);
       if(mysqli_affected_rows($connection)>0){
           $myAswer['add_msg']=1;
           $select="SELECT DISTINCT * FROM `message` WHERE (id_user='$id_user'AND msg='$msg')";
           $result_select=mysqli_query($connection, $select);
           $new_row=mysqli_fetch_array($result_select);
           $id=$new_row['id'];
           $msg1=array();
           $msg1['id']=$id;
           $msg1['id_user']=$id_user;
           $msg1['msg']=$msg;
           $myAswer['msg']=$msg1;
           $insert_in_journal_query="INSERT INTO `journal`(`id_user`, `op`, `id`, `tab`, `date`) "
                   . "VALUES ('$id_user','1','$id','20','$date')";
           $result_insert_in_journal=mysqli_query($connection, $insert_in_journal_query);
       if(mysqli_affected_rows($connection)>0){
         $myAswer['add_journal']=1;
                 $journal=array();
                 $journal['id']=$id;
                 $journal['id_user']=$id_user;
                 $journal['tab']="20";
                 $journal['op']="1";
                 $journal['date']=$date;
                 $myAswer['journal']=$journal;
           $insert_sent="INSERT INTO `messege_sent_to`( `id_msg`, `id_user`) VALUES ('$id','$receiver')";
           $result_insert_sent=mysqli_query($connection, $insert_sent);
           if(mysqli_affected_rows($connection)>0){
                        $myAswer['add_sent']=1;
              $select_sent="select * from `messege_sent_to` where id_msg='$id' and id_user='$receiver'" ; 
              $result_select_sent=mysqli_query($connection, $select_sent);
              $w_r=mysqli_fetch_array($result_select_sent); 
              $msg_send_to=array();
              $msg_send_to['id']=$w_r['id'];
              $msg_send_to['id_msg']=$id;
              $msg_send_to['id_user']=$receiver;
              $msg_send_to['seen']="0";
              $myAswer['msg_send_to']=$msg_send_to;
              
           }else{
               $myAswer['add_sent']=0;
           }
       }else{
           $myAswer['add_journal']=0;
       }
              }else{
           $myAswer['add_msg']=0;
       }
    }
          
}else{
    $myAswer['params']=0;
}
mysqli_close($connection);
 header('Content-type: application/json');
 echo json_encode($myAswer); 
}

