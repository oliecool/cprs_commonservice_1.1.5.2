/*
 * jQuery自定义脚本
 * 提供整个系统中通用jQuery自定义脚本代码
 * 
 * author: zhengdd
 * date: 2010-4-15
 */

/*
 * 登录页面淡入与输入框方法
 */
function JQ_login() {
	$(".login-box").hide();
	$(".login-box").fadeIn(1000);
	$(".inp").bind("focus", function() {
		$(this).addClass("inp2");
	});
	$(".inp").bind("blur", function() {
		$(this).removeClass("inp2");
	});
}
/*
 * 重新调整id为"main"的iframe的大小, 用于解决该iframe内部如果通过Javascript动态加入html代码后, 
 * iframe的大小发生了变化而外部的父iframe却没有重新调整过大小, 导致该iframe的部分内容被遮挡
 */
function resizeMainIframe() {
	var mainIframe = jQuery("#main", parent.document.body)[0];
	jQuery("#main", parent.document.body).attr("height",
			mainIframe.contentWindow.document.documentElement.scrollHeight);
}
/*
 * 登陆页面点击登陆
 */
jQuery(function(){
    jQuery("#logins").click(function() {
		jQuery("#loginForm").submit();
	});
});
/*
 * fromid:源list的id. toid:目标list的id. moveOrAppend参数("move"或者是"append"): move --
 * 源list中选中的option会删除.源list中选中的option移动到目标list中,若目标list中已存在则该option不添加. append --
 * 源list中选中的option不会删除.源list中选中的option添加到目标list的后面,若目标list中已存在则该option不添加.
 * 
 * isAll参数(true或者false):是否全部移动或添加
 */
jQuery.listTolist = function(fromid, toid, moveOrAppend, isAll) {
	if (moveOrAppend.toLowerCase() == "move") { // 移动
		if (isAll == true) { // 全部移动
			$("#" + fromid + " option").each(function() {
				// 将源list中的option添加到目标list,当目标list中已有该option时不做任何操作.
					$(this).appendTo(
							$("#" + toid + ":not(:has(option[value="
									+ $(this).val() + "]))"));
				});
			$("#" + fromid).empty(); // 清空源list
		} else if (isAll == false) {
			$("#" + fromid + " option:selected")
					.each(function() {
						// 将源list中的option添加到目标list,当目标list中已有该option时不做任何操作.
							$(this).appendTo(
									$("#" + toid + ":not(:has(option[value="
											+ $(this).val() + "]))"));
							// 目标list中已经存在的option并没有移动,仍旧在源list中,将其清空.
							if ($("#" + fromid + " option[value="
									+ $(this).val() + "]").length > 0) {
								$("#" + fromid).get(0).removeChild(
										$(
												"#" + fromid + " option[value="
														+ $(this).val() + "]")
												.get(0));
							}
						});
		}
	} else if (moveOrAppend.toLowerCase() == "append") {
		if (isAll == true) {
			$("#" + fromid + " option").each(
					function() {
						$("<option></option>").val($(this).val()).text(
								$(this).text()).appendTo(
								$("#" + toid + ":not(:has(option[value="
										+ $(this).val() + "]))"));
					});
		} else if (isAll == false) {
			$("#" + fromid + " option:selected").each(
					function() {
						$("<option></option>").val($(this).val()).text(
								$(this).text()).appendTo(
								$("#" + toid + ":not(:has(option[value="
										+ $(this).val() + "]))"));
					});
		}
	}
};
/*
 * 功能大体同上("move"). 不同之处在于当源list中的选中option在目标list中存在时,源list中的option不会删除.
 * 
 * isAll参数(true或者false):是否全部移动或添加
 */
jQuery.list2list = function(fromid, toid, isAll) {
	if (isAll == true) {
		$("#" + fromid + " option").each(
				function() {
					$(this).appendTo(
							$("#" + toid + ":not(:has(option[value="
									+ $(this).val() + "]))"));
				});
	} else if (isAll == false) {
		$("#" + fromid + " option:selected").each(
				function() {
					$(this).appendTo(
							$("#" + toid + ":not(:has(option[value="
									+ $(this).val() + "]))"));
				});
	}
};
/*
 * 返回前一页
 */
$(function() {
	$("#back").click(function() {
		history.back(-1);
		return false;
	});
});
/*
 * 菜单展开、收缩
 */
$(function() {
	$("div .menu :header").click(function() {
		$(this).next("ul").slideToggle("normal", function() {
			$(this).prev(" :header").toggleClass("menu-closed");
		});
		window.name = $(this).prevAll("#modules :header").length;
		if ($(this).hasClass("menu-closed")) {
			$(this).siblings(":header").each(function(i, e) {
				if (!$(e).hasClass("menu-closed")) {
					$(e).next("ul").slideUp("normal", function() {
						$(this).prev(" :header").toggleClass("menu-closed");
					});
				}
			});
		}
	});
});
/*
 * 左侧菜单显示
 */
$(function() {
	jQuery.loadMenu = function(tabnum, menunum) {
		$("div .menu").each(function(i, e) {
			if (tabnum == i) {
				$(e).show();
				$(e).find(":header").each(function(i2, e2) {
					if (i2 == menunum) {
						$(this).removeClass("menu-closed");
						$(this).next("ul").show();
					} else {
						$(this).addClass("menu-closed");
						$(this).next("ul").hide();
					}
				});
			} else {
				$(e).hide();
			}
		});
	};
});
/*
 * 全选
 */
$(function() {
	$(".list-table .all-checkbox").click(function() {
		if ($(".list-table .all-checkbox").is(":checked")) {
			$(".list-table .row-checkbox").each(function() {
				$(this).attr("checked", "checked");
			});
		} else {
			$(".list-table input[type=checkbox]").each(function() {
				$(this).removeAttr("checked");
			});
		}
	});
});
/*
 * 顶部菜单切换
 */
$(function() {
	$(".body-top .nav a").click(function() {
		var curli = $(this).parent();
		curli.parent().find("li").remove(".front");
		var size = curli.parent().children("li").length;
		var num = curli.prevAll("li").length;
		curli.addClass("c");
		curli.siblings("li").each(function(i, e) {
			$(e).removeClass("c");
			if (num + 1 != size && i + 2 == size) {
				$(e).addClass("last");
			}
		});
		if (num + 1 == size) {
			curli.removeClass("last");
		}
		if (num > 0) {
			curli.parent().prepend("<li class='front'> </li>");
		}
		jQuery.loadMenu(num, 0);
	});
});
/*
 * 清空日期
 */
$(function() {
	$("a[id=delDate]").click(function() {
		$(this).siblings("input").each(function(i, e) {
			$(e).val("");
		});
	});
});
var _form;
var _data;
var _action;
var _expression;
function paginate(p) {
	if (_form == "" && _action == "" && _expression == "") {
		var url = window.location.href;
		if (url.indexOf("?") != -1) {
			if (url.charAt(url.length - 1) != "&") {
				window.location.href = url + "&pageNo=" + p;
			} else {
				window.location.href = url + "pageNo=" + p;
			}
		} else {
			window.location.href = url + "?pageNo=" + p;
		}
	} else if (_form != "") {
		if (_action != "") {
			// TODO;
		}
		if (_expression != "") {
			$("#" + _form).append("<input type='hidden' name='" + _expression + "' value='" + p + "' />");
		} else {
			$("#" + _form + " input[type=hidden][name=pageNo]").remove();
			$("#" + _form).append("<input type='hidden' name='pageNo' value='" + p + "' />");
		}
		if (_data != "") {
			$("#" + _data).load(_action, $("#" + _form).serializeArray());
		} else {
			$("#" + _form).submit();
		}	
	}
}
function bindPager() {
	$("#_page a[id=_pre_page],#_page a[id=_next_page]").live("click", function() {
		paginate($.trim($(this).next().val()));
	});
	$("#_page a[id=_none_pre_page],#_page a[id=_none_next_page]").live("click", function() {
		return false;
	});
	$("#_page a[id=_page_no]").live("click", function() {
		paginate($.trim($(this).text()));
	});
	$("#_page a[id=_go]").live("click", function() {
		var p = $.trim($("#_page input[id=_go_page]").val());
		var pTotal = $.trim($("#_page input[id=_total_page]").val());
		if (p.isBlank()) {
			alert("请输入页数");
			return;
		}
		if (isNaN(p)) {
			alert("请输入正确的页数");
			return;
		}
		if (parseInt(p) <= 0) {
			p = "1";
		} else if (parseInt(p) >= parseInt(pTotal) ) {
			p = pTotal;
		}
		paginate(p);
		return false;
	});
}
$(function() {
	bindPager();
});