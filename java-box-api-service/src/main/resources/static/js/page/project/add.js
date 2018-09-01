var Add = function(){
	this.submit = function(){
		var data = new Object();
		var projectName = $("#projectName").val();
		var projectRotuo = $("#projectRouto").val();
		data.projectName = projectName;
		data.Route = projectRotuo;
		add.submitAdd(data);
	}
	
	this.submitAdd = function(data){
		ajax.ajaxGet('addProject',data,function(result){
			if (result.result) {
				alert("保存成功");
				base.divNestingHtml('leftContent','page/project/list.html');
			} else {
				alert(result.msg);
			}
		}, null);
	}
}
var add = new Add();
