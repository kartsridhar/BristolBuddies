<?php
/* Database credentials. Assuming you are running MySQL
server with default setting (user 'root' with no password) */
$dbuser = "root";
$dbpass = "Br!st0lBudd!e5";
$dbname = "129.150.124.250:3306";
$dbstate = "mydatabase";
 
/* Attempt to connect to MySQL database */
$db = mysqli_connect($dbname, $dbuser, $dbpass, $dbstate);
 
// Check connection
if($db === false){
    die("ERROR: Could not connect. " . mysqli_connect_error());
}
?>