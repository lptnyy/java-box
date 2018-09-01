var javaurl = "http://localhost:8762/"
var baseUrl=''
var pageSize=6;
var Base = function(){
	this.divNestingHtml = function(div,url){
		 $('#'+div).load(baseUrl+url);
	}
}
var base = new Base();
