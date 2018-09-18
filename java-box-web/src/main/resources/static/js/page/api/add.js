var Add = function() {
	this.submit = function() {
		var data = new Object();
		var apiName = $("#apiName").val();
		var apiRoute = $("#apiRoute").val();
		var pakegerName = $("#pakegerName").val();
		var procjetId = $('#projectId').val();
		var moudurId = $('#moudurId').val();
		data.moudularId = moudurId;
		data.apiName = apiName;
		data.apiRoute = apiRoute;
		data.classFuntion = pakegerName;
		add.submitAdd(data);
	}

	this.submitAdd = function(data) {
		ajax.ajaxGet('addApi', data, function(addresult) {
			if(addresult.result) {
				alert("保存成功");
				base.divNestingHtml('leftContent', 'page/api/list.html');
			} else {
				alert(addresult.msg);
			}
		}, null);
	}

	this.getProjectOptionList = function() {
		var data = new Object();
		ajax.ajaxGet('getOptionProjectList', data, function(result) {
			if(result.result) {
				var html = '';
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

	this.getMouderOptionList = function(id) {
		var html = '';
		$("#moudurId").html(html);
		html = '<option value="">未选择</option>';
		$("#moudurId").append(html);
		if(id == 0) {
			return;
		}
		var data = new Object();
		data.pageNo = 1;
		data.pageSize = 1;
		data.projectId = id;
		ajax.ajaxGet('getOptionMoudularList', data, function(result) {
			if(result.result) {
				var html = '';
				var datas = result.object;
				for(var i = 0; i < datas.length; i++) {
					html = '<option value="' + datas[i].moudularId + '">' + datas[i].moddularName + '</option>';
					$("#moudurId").append(html);
				}
			} else {
				alert(result.msg);
			}
		}, null);
	}

	this.onchangeOptions = function(op) {
		add.getMouderOptionList(op.value);
	}

	this.init = function() {
		$("#projectId").bind("change", function() {
			add.onchangeOptions(this);
		});
	}
}
var add = new Add();
add.getProjectOptionList();
add.init();