easyJs=window.easyJs||{};
easyJs.ns=function(str){
	var names=str.split("."),o=easyJs;
	for(var i=(names[0]=="easyJs"?1:0);i<names.length;i++){
		o[names[i]]=o[names[i]]||{};
		o=o[names[i]];
	}
}
