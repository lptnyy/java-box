layui.define(['jquery'], function(exports){
	var obj = {
		table: function(url, id, pageNo, pageSize){
			layui.use(['commonAjax'], function () {        
   				
			});
		}
	}
	
	//输出接口
    exports('ajaxPageTable', obj);
});