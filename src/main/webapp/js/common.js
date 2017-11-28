//验证方法
function judge() {
	var username = $(":text").val();
	var password = $(":password").eq(0).val();
	var password1 = $(":password").eq(1).val();
	if (username.length > 20 || username.length < 6) {
		alert("用户名长度必须在6-20个字符之间！");
		return false;
	} else if (password.length > 20 || password.length < 6) {
		alert("密码长度必须在6-20个字符之间！");
		return false;
	} else if (password != password1 ) {
		alert("密码与重复密码必须相同！");
		return false;
	}
	return true;
}
