$(function() {
	$("#login").click(function() {
		var username = $("#username").val();
		var password = $("#password").val();
		if(username == null || password == null || username == "" || password == "")
		{
			$(".error").html("请填写用户名密码");
			$(".error").slideDown();
			return;
		}
		$("body").load("user/login", {
			"username" : username,
			"password" : password
		});
	})
})