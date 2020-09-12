<?php

include('mafqudcon.php');

header('Content-Type: application/json; charset=utf-8');


$stmt = $conn->prepare("SELECT id, name, email , pass   FROM users;");

$stmt->execute();

$stmt-> bind_result($id , $username, $email, $password);

$products = array();

while($stmt ->fetch()){

    $temp = array();
	$temp['id'] = $id;
	$temp['username'] = $username;
	$temp['email'] = $email;
	$temp['password'] = $password;
	

	array_push($products,$temp);
	}

	echo json_encode($products);

?>