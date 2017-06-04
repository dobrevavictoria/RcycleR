<?php
 
require_once 'DBOperations.php';
 
class Functions{
 
private $db;
 
public function __construct() {
 
      $this -> db = new DBOperations();
 
}
 
public function registerUser($name, $email, $password) {
 
   $db = $this -> db;
 
   if (!empty($name) && !empty($email) && !empty($password)) {
 
      if ($db -> checkUserExist($email)) {
 
         $response["result"] = "failure";
         $response["message"] = "1";
         return json_encode($response);
 
      } else {
 
         $result = $db -> insertData($name, $email, $password);
 
         if ($result) {
 
              $response["result"] = "success";
            $response["message"] = "2";
            return json_encode($response);
 
         } else {
 
            $response["result"] = "failure";
            $response["message"] = "3";
            return json_encode($response);
 
         }
      }
   } else {
 
      return $this -> getMsgParamNotEmpty();
 
   }
}
 
public function loginUser($email, $password) {
 
  $db = $this -> db;
 
  if (!empty($email) && !empty($password)) {
 
    if ($db -> checkUserExist($email)) {
 
       $result =  $db -> checkLogin($email, $password);
 
       if(!$result) {
 
        $response["result"] = "failure";
        $response["message"] = "4";
        return json_encode($response);
 
       } else {
 
        $response["result"] = "success";
        $response["message"] = "5";
        $response["user"] = $result;
        return json_encode($response);
 
       }
    } else {
 
      $response["result"] = "failure";
      $response["message"] = "6";
      return json_encode($response);
 
    }
  } else {
 
      return $this -> getMsgParamNotEmpty();
    }
}
 
public function changePassword($email, $old_password, $new_password) {
 
  $db = $this -> db;
 
  if (!empty($email) && !empty($old_password) && !empty($new_password)) {
 
    if(!$db -> checkLogin($email, $old_password)){
 
      $response["result"] = "failure";
      $response["message"] = '7';
      return json_encode($response);
 
    } else {
 
    $result = $db -> changePassword($email, $new_password);
 
      if($result) {
 
        $response["result"] = "success";
        $response["message"] = "8";
        return json_encode($response);
 
      } else {
 
        $response["result"] = "failure";
        $response["message"] = '9';
        return json_encode($response);
 
      }
    }
  } else {
 
      return $this -> getMsgParamNotEmpty();
  }
}
 
public function isEmailValid($email){
 
  return filter_var($email, FILTER_VALIDATE_EMAIL);
}
 
public function getMsgParamNotEmpty(){
 
  $response["result"] = "failure";
  $response["message"] = "10";
  return json_encode($response);
 
}
 
public function getMsgInvalidParam(){
 
  $response["result"] = "failure";
  $response["message"] = "11";
  return json_encode($response);
 
}
 
public function getMsgInvalidEmail(){
 
  $response["result"] = "failure";
  $response["message"] = "12";
  return json_encode($response);
 
}
}
