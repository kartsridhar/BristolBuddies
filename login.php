<?php
require "init.php";
$uname = $_POST["u"];
$upass = $_POST["p"];
$query = "SELECT FIRSTNAME FROM BRISBUDS WHERE USERNAME LIKE 'uname' AND PASSWORD LIKE 'upass';";
$result = mysqli_query($con, $query);

if(mysqli_num_rows($result) > 0) {
  $row = mysqli_fetch_assoc($result);
  $fname = $row["FIRSTNAME"];
  echo "Welcome to Bristol Buddies ".$fname;
}
else {
  echo "Invalid details, check again";
}
 ?>
