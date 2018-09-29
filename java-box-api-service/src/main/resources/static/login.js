layui.define(['element'],function(exports){
    var $ = layui.$;
    $('.input-field').on('change',function(){
        var $this = $(this),
            value = $.trim($this.val()),
            $parent = $this.parent();
        if(value !== '' && !$parent.hasClass('field-focus')){
            $parent.addClass('field-focus');
        }else{
            $parent.removeClass('field-focus');
        }
    })
    $('.login-button').on('click', function(){
   		var user_name = $("#username").val();
   		var user_pass = $("#password").val();
   		var data = new Object();
   		data.user_name = user_name;
   		data.user_pass = user_pass;
   		layui.use(['commonAjax'], function () {        
			var commajax = layui.commonAjax;
			commajax.ajax(login, data, function(result){
   			if (result.result) {
   				var user_id = result.object.user_id;
   				var user_token = result.object.user_token;
   				var user_name = data.user_name;
   				localStorage.setItem("user_id", user_id);
   				localStorage.setItem("user_token", user_token);
   				localStorage.setItem("user_name", user_name);
   				location.href = "index.html";
   			} else {
   				alert(result.msg);
   			}
   			}, function(error){
     			alert(error);	
     		});
   		})
    })
    exports('login');
});