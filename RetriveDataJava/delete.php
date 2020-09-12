<?php
	include 'mafqudcon.php';
$data;
	if(isset($_POST['id'])){
		$id = $_POST['id'];
		$sql = "DELETE FROM users WHERE ID = '$id'";
		
		
		$query = $conn->prepare($sql);
		$query->execute();

		$data=["msg"=>"done"];

	}
	else{
		$data=["msg"=>"error"];
	}

echo  json_encode($data);
?>