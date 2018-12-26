/*
 * 共通脚本
 * 提供整个系统中通用的脚本代码, 建议此脚本文件内容采用原始的JavaScript代码编写, 而不采用jQuery等框架编写
 * 
 * author: zhengdd
 * date: 2010-3-23
 */

/*
 * JavaScript的原生String的replaceAll方法
 * 
 * author: zhengdd
 * date: 2010-3-30
 */
String.prototype.replaceAll = function(stringToFind, stringToReplace) {
	var temp = this;
	var index = temp.indexOf(stringToFind);
	while (index != -1) {
		temp = temp.replace(stringToFind, stringToReplace);
		index = temp.indexOf(stringToFind);
	}
	return temp;
}

String.prototype.trim= function(){
    return this.replace(/(^\s*)|(\s*$)/g, "");  
}

String.prototype.isBlank = function() {
	if (this == null) {
		return true;
	}
	var temp = this.trim();
	if (temp == null || temp == "") {
		return true;
	}
	return false;
}

String.prototype.isNotBlank = function() {
	if (this == null) {
		return false;
	}
	var temp = this.trim();
	if (temp != null && temp != "") {
		return true;
	}
	return false;
}