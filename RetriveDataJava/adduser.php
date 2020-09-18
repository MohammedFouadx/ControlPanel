<?php

$dbh=new PDO("mysql:host=localhost;dbname=mafqoud","root","");

$data;
if (isset($_POST['name'])){
    
    $name = $_POST['name'];
    $email = $_POST['email'];
    $password = $_POST['password'];
  
    
    
    $sql="INSERT INTO `users`(`name`, `email`, `pass`) VALUES ('$name','$email','$password')";
    $query =$dbh->prepare($sql);
    $query->execute();
    $lastInsertId=$dbh->lastInsertId();
    if ( $lastInsertId){
        $data=["msg"=>"done"];
    
    }else{
        
        $data=["msg"=>"error"];
    }
   

    
}
echo json_encode($data);
?>