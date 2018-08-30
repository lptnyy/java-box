var ApiList = function() {
	this.pageNo = 1;
	this.maxPage = 0;
	this.projectId = 0;
	this.projectName = '';
	this.init = function() {
		var data = new Object();
		data.pageNo = 1;
		apilist.pageNo = 1;
		data.pageSize = pageSize;
		if (apilist.projectId != 0){
			data.projectId = $("#projectId").val();
		}
		if (this.projectName != '') {
			data.moubularName = this.projectName;
		}
		ajax.ajaxGet('getApiList', data, function(result) {
			apilist.addTableList(result);
		}, null);
	}
	
	this.pageInit = function(pageNo){
		if (pageNo > this.maxPage) {
			pageNo = this.maxPage;
		}
		if (pageNo < 1) {
			pageNo = 1;
		}
		var data = new Object();
		data.pageNo = pageNo;
		apilist.pageNo = pageNo;
		data.pageSize = pageSize;
		if (apilist.projectId != 0){
			data.projectId = $("#projectId").val();
		}
		if (this.projectName != '') {
			data.moubularName = this.projectName;
		}
		ajax.ajaxGet('getApiList', data, function(result) {
			apilist.addTableList(result);
		}, null);		
	}
	
	this.optionProjectId = function(){
		apilist.projectId = $("#projectId").val();
		apilist.clearTableList();
		apilist.init();
	}

	this.addTableList = function(result) {
		var returnResult = result.result;
		if (returnResult) {
			var table = $("#tableList");
			var datas = result.object;
			var tablelist = '';
			if (result.sumPage % pageSize != 0) {
				apilist.maxPage = parseInt(result.sumPage / pageSize + 1);
			} else {
				apilist.maxPage = parseInt(result.sumPage / pageSize);
			}
			
			$("#pageInfo").html("当前"+apilist.pageNo+"页,共"+apilist.maxPage+"页");
			
			for(var i = 0; i< datas.length; i++) {
				var obj = datas[i];
				tablelist += '<tr>';
				tablelist +='<td><input type="checkbox"></td>';
				tablelist +='<td>'+obj.apiId+'</td>';
				tablelist +='<td>';
				tablelist +='<a>'+obj.apiName+'</a>';
				tablelist +='</td>';
				tablelist +='<td>'+obj.apiRoute+'</td>';
				tablelist +='<td>'+obj.classFuntion+'</td>';
				tablelist +='<td>';
				tablelist +='<a>'+obj.createTime+'</a>';
				tablelist +='</td>';
				tablelist +='<td>';
				tablelist +='<div class="am-btn-toolbar">';
				tablelist +='<div class="am-btn-group am-btn-group-xs">';
				tablelist +='<div onclick="apilist.delData('+obj.apiId+')" class="am-btn am-btn-default am-btn-xs am-text-danger"><span class="am-icon-trash-o"></span> 删除</div>';
				tablelist +='</div>';
				tablelist +='</div>';
				tablelist +='</td>';
				tablelist +='</tr>';
			}
			table.html(tablelist);
		}
	}
	
	this.delData = function(id){
		var data = new Object();
		data.id=id;
		ajax.ajaxGet('delApi',data,function(result){
			if (result.result){
				alert('删除成功');
				apilist.clearTableList();
				apilist.init();
			} else {
				alert(result.msg);
			}
		},null);
	}
	
	this.updateOpenStat = function(id, stat){
		var data = new Object();
		data.id=id;
		if (stat==1) {
			stat = 0;
		} else {
			stat = 1;
		}
		data.openStat = stat;
		ajax.ajaxGet('updateProjectOpenStat',data,function(result){
			if (result.result){
				alert('已生效');
				apilist.clearTableList();
				apilist.init();
			} else {
				alert(result.msg);
			}		
		}, null);
	}
	
	this.getProjectOptionList = function() {
		var data = new Object();
		ajax.ajaxGet('getOptionProjectList', data, function(result) {
			if(result.result) {
				var html = '';
				html = '<option value="">' + '未选择' + '</option>';
				$("#projectId").append(html);
				var datas = result.object;
				for(var i = 0; i < datas.length; i++) {
					html = '<option value="' + datas[i].projectId + '">' + datas[i].projectName + '</option>';
					$("#projectId").append(html);
				}
			} else {
				alert(result.msg);
			}
		}, null);
	}
	
	this.secbtn = function(){
		this.projectName = $("#projectName").val();
		apilist.clearTableList();
		apilist.init();
	}
	
	
	this.homePage = function(){
		apilist.pageInit(1);
	}
	
	this.tailPage = function(){
		apilist.pageInit(apilist.maxPage);
	}
	
	this.nextPage = function(){
		apilist.pageInit(apilist.pageNo + 1);
	}
	
	this.backPage = function(){
		apilist.pageInit(apilist.pageNo - 1);
	}
	
	this.clearTableList = function(){
		$("#tableList").html('');
	}
}
var apilist = new ApiList();
apilist.init();
apilist.getProjectOptionList();