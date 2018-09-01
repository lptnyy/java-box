var Add = function() {
	this.submit = function() {
		var data = new Object();
		var moddularName = $("#moddularName").val();
		var moddularRoute = $("#moddularRoute").val();
		var procjetId = $('#projectId').val();
		data.moddularName = moddularName;
		data.moddularRoute = moddularRoute;
		data.projectId = procjetId;
		data.jarVersion = "1.0";
		add.submitAdd(data);
	}

	this.submitAdd = function(data) {
		upload.uploadFile(function(result) {
			var obj = JSON.parse(result);
			if(obj.result) {
				//{"object":{"fileName":"JavaBox.jar","fileUrl":"/upload/JavaBox.jar"},"result":true}
				var objr = obj.object;
				data.jarName = objr.fileName;
				data.jarUrl = objr.fileUrl;
				data.jarMd5 = objr.fileMd5;
				ajax.ajaxGet('addModular', data, function(addresult) {
					if(addresult.result) {
						alert("保存成功");
						base.divNestingHtml('leftContent', 'page/moudlar/list.html');
					} else {
						alert(addresult.msg);
					}
				}, null);
			} else {
				alert(obj.msg);
			}
		});
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
}
var add = new Add();
add.getProjectOptionList();