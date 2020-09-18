<?php

include('mafqudcon.php');

header('Content-Type: application/json; charset=utf-8');


$stmt = $conn->prepare("SELECT p_id , `title`, `description`,  `status` FROM `posts` ");

$stmt->execute();

$stmt-> bind_result($p_id , $title, $description, $status);

$products = array();

while($stmt ->fetch()){

    $temp = array();
	
	$temp['p_id'] = $p_id;
	$temp['title'] = $title;
	$temp['description'] = $description;
	$temp['status'] = $status;
	

	array_push($products,$temp);
	}

	echo json_encode($products);

?>