<?php

//defining 
define('DATABASE_SERVER','localhost');
define('DATABASE_USER','root');
define('DATABASE_PASSWORD','');
define('DATABASE_NAME','my_ukt');

class database_connection {
    public $connection,$connection_wr,$connection_wrd;
    
    //a counstructor
    function __construct() {
        $this->connection = $this->connect();
    }
    
    //function will be called from the constructor
    public function connect() {
        // returns the connection
        return mysqli_connect(DATABASE_SERVER,
                DATABASE_USER,
                DATABASE_PASSWORD,
    DATABASE_NAME);}
    
    public function getConnection(){
        return $this->connection;
    }
    
}
