<?php
	include 'mafqudcon.php';
$data;
	if(isset($_POST['p_id'])){
		$id = $_POST['p_id'];
		$sql = "DELETE FROM posts WHERE p_id = '$id'";
		
		
		$query = $conn->prepare($sql);
		$query->execute();

		$data=["msg"=>"done"];

	}
	else{
		$data=["msg"=>"error"];
	}

echo  json_encode($data);
?>