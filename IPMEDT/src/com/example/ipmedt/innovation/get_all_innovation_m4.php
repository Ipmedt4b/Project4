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
$result = mysql_query("SELECT *FROM innovation ") or die(mysql_error());
 
// check for empty result
if (mysql_num_rows($result) > 0) {
    // looping through all results
    // products node
    $response["innovation"] = array();
 
    while ($row = mysql_fetch_array($result)) {
        // temp user array
        $innovation = array();
		$innovation ["pid"] = $row["pid"];
        $innovation	["naamsubmodel"] = $row["naamsubmodel4"];
		$innovation	["submodel"] = $row["submodel4"];
		$innovation	["subbeschrijving"] = $row["subbeschrijving4"];
        		
     
 
        // push single product into final response array
        array_push($response["innovation"], $innovation);
    }
    // success
    $response["success"] = 1;
 
    // echoing JSON response
    echo json_encode($response);
} else {
    // no products found
    $response["success"] = 0;
    $response["message"] = "No innovation models found";
 
    // echo no users JSON
    echo json_encode($response);
}
?>