var Ajax = function() {
	this.ajaxGet = function(url,data,result,error){
		data.jsonp=true;
		$.ajax({  
				data: data,
                url: baseUrl+url,  
                type: 'get',  
                dataType: 'jsonp',  
                timeout: 1000,  
                cache: false, 
                jsonpCallback:"callback",
                error: function(neterror){
                		if (error != null)
                		error(neterror);
                },  //错误执行方法
                success: function(netresult){
                		result(netresult);
                } //成功执行方法
           });
	}
}
var ajax = new Ajax();