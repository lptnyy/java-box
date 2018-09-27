var Upload = function(){
	this.uploadFile = function(result){
		var fileObj = $("#file")[0].files[0];
		var url = javaurl+"uploadJar";
		var from = new FormData();
		from.append("file",fileObj);
		var xhr = new XMLHttpRequest();
		xhr.onreadystatechange = function() {//服务器返回值的处理函数，此处使用匿名函数进行实现
            if (xhr.readyState == 4 && xhr.status == 200) {//
                var responseText = xhr.responseText;
                result(responseText);
            }
        };
		xhr.open("POST",url);
		xhr.send(from);
	}
	
	this.uploadPlFile = function(result){
		var fileObj = $("#file")[0].files[0];
		var url = javaurl+"uploadPlJar";
		var from = new FormData();
		from.append("file",fileObj);
		var xhr = new XMLHttpRequest();
		xhr.onreadystatechange = function() {//服务器返回值的处理函数，此处使用匿名函数进行实现
            if (xhr.readyState == 4 && xhr.status == 200) {//
                var responseText = xhr.responseText;
                result(responseText);
            }
        };
		xhr.open("POST",url);
		xhr.send(from);
	}
}
var upload = new Upload();
