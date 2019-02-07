<?php
require "init.php";
$first = $_POST["f"];
$last = $_POST["l"];
$gender = $_POST["g"];
$username = $_POST["u"];
$pass = $_POST["p"];
$dept = $_POST["d"];
$yos = $_POST["y"];

$query = "INSERT INTO BRISBUDS VALUES ('$first', '$last', '$gender', '$username', '$pass', '$dept', '$yos');";

//if(mysqli_query($con, $query)) {
  //echo "<h3> Registered Successfully! </h3>";
//}
//else {
  //echo "Registration error!".mysqli_error($con);
//}
?>
