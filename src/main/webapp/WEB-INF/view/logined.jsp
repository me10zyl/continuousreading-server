<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div style="display: none;" id="json">{"success" : true, "message" : "${message}", "username" : "${username}","password" : "${password}"}</div>
<div style="width:200px;">${message}</div>
<div>Current website url is:</div>
<div style="border:1px solid black;margin: 10px 0px;padding:5px;" id="url"></div>
<div><button id="send">Send url to <span id="username_to_send" style="color: red">${username}</span>'s Mobile</button></div>