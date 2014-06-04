<?php
 
/*
 * Following code will list all the products
 */
 
// array for JSON response
$response = array();
 
// include db connect class
require_once __DIR__ . '/db_connect.php';
 
// connecting to db
$db = new DB_CONNECT();
 
// get all products from products table
$result = mysql_query("SELECT *FROM namesstrategy ") or die(mysql_error());
 
// check for empty result
if (mysql_num_rows($result) > 0) {
    // looping through all results
    // products node
    $response["namesstrategy"] = array();
 
    while ($row = mysql_fetch_array($result)) {
        // temp user array
        $namesstrategy = array();
		$namesstrategy ["pid"] = $row["pid"];
        $namesstrategy	["modellen"] = $row["modellen"];
	     		
     
 
        // push single product into final response array
        array_push($response["namesstrategy"], $namesstrategy);
    }
    // success
    $response["success"] = 1;
 
    // echoing JSON response
    echo json_encode($response);
} else {
    // no products found
    $response["success"] = 0;
    $response["message"] = "No strategy models found";
 
    // echo no users JSON
    echo json_encode($response);
}
?>