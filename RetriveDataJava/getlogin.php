
<?php 

$dbh=new PDO("mysql:host=localhost;dbname=mafqoud","root","");

 header('Content-Type: application/json; charset=utf-8');


     $email=$_POST['email'];                          
     $pass=$_POST['pass'];                          
   //  $email="admin@gmail.com";                          
                                              
$sql = "SELECT * from users where email='$email' AND pass='$pass'  ";
$query = $dbh->prepare($sql);
$query->execute();
$results=$query->fetchAll(PDO::FETCH_OBJ);
//$cnt=1;

$data;

if($query->rowCount() > 0)
{


foreach($results as $result)
{   

$data=["data"=>$result

,
"msg"=>"Welcome"

];
}
}
else {

$data=["msg"=>"you are not an admin"];
}


echo json_encode($data);



?>
