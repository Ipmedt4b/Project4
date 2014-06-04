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
$result = mysql_query("SELECT *FROM leadership ") or die(mysql_error());
 
// check for empty result
if (mysql_num_rows($result) > 0) {
    // looping through all leadership
    // products node
    $response["leadership"] = array();
 
    while ($row = mysql_fetch_array($result)) {
        // temp user array
        $leadership = array();
		$leadership ["pid"] = $row["pid"];
        $leadership	["naamsubmodel"] = $row["naamsubmodel6"];
		$leadership	["submodel"] = $row["submodel6"];
		$leadership	["subbeschrijving"] = $row["subbeschrijving6"];
        		
     
 
        // push single product into final response array
        array_push($response["leadership"], $leadership);
    }
    // success
    $response["success"] = 1;
 
    // echoing JSON response
    echo json_encode($response);
} else {
    // no products found
    $response["success"] = 0;
    $response["message"] = "No leadership models found";
 
    // echo no users JSON
    echo json_encode($response);
}
?>