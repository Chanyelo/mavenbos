<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!-- 导入jquery核心类库 -->
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<!-- 导入easyui类库 -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/ext/portal.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/default.css">	
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/ext/jquery.portal.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/ext/jquery.cookie.js"></script>
<script
	src="${pageContext.request.contextPath }/js/easyui/locale/easyui-lang-zh_CN.js"
	type="text/javascript"></script>
<script type="text/javascript">
	function doAdd(){
		//alert("增加...");
		$('#addStaffWindow').window("open");
	}
	
	function doView(){
		alert("查看...");
	}
	
	function doDelete(){
		//获取选中的记录
		var rows = $("#grid").datagrid("getSelections");
		//用户没有选中一行
		if(rows.length==0){
		    $.messager.alert("友情提示","您至少选中一个取派员","warning");
		    return;
		}
		//获取选中行的id，并将拼接为字符串
		var arr = new Array();
		for(var i=0;i<rows.length;i++){
		    arr.push(rows[i].id);
		}
		var ids = arr.join();
		$.post("${pageContext.request.contextPath}/staff_deleteBatch.action",{"ids":ids},function (data) {
			if(data.result){
			    //作废成功的提示
				$.messager.alert("提示","作废操作成功！","info");
			}else {
			    $.messager.alert("警告","作废操作失败！","error");
			}
			//重新加载数据列表，刷新数据
			$("#grid").datagrid("reload");
			//清楚所有的选择
			$("#grid").datagrid("clearChecked");
        });
	}
	
	function doRestore(){
		alert("将取派员还原...");
	}
	//工具栏
	var toolbar = [ {
		id : 'button-view',	
		text : '查询',
		iconCls : 'icon-search',
		handler : doView
	}, {
		id : 'button-add',
		text : '增加',
		iconCls : 'icon-add',
		handler : doAdd
	}, {
		id : 'button-delete',
		text : '作废',
		iconCls : 'icon-cancel',
		handler : doDelete
	},{
		id : 'button-save',
		text : '还原',
		iconCls : 'icon-save',
		handler : doRestore
	}];
	// 定义列
	var columns = [ [ {
		field : 'id',
		checkbox : true,
	},{
		field : 'name',
		title : '姓名',
		width : 120,
		align : 'center'
	}, {
		field : 'telephone',
		title : '手机号',
		width : 120,
		align : 'center'
	}, {
		field : 'haspda',
		title : '是否有PDA',
		width : 120,
		align : 'center',
		formatter : function(data,row, index){
			if(data=="1"){
				return "有";
			}else{
				return "无";
			}
		}
	}, {
		field : 'deltag',
		title : '是否作废',
		width : 120,
		align : 'center',
		formatter : function(data,row, index){
			if(data=="0"){
				return "正常使用"
			}else{
				return "已作废";
			}
		}
	}, {
		field : 'standard',
		title : '取派标准',
		width : 120,
		align : 'center'
	}, {
		field : 'station',
		title : '所谓单位',
		width : 200,
		align : 'center'
	} ] ];
	
	$(function(){
		// 先将body隐藏，再显示，不会出现页面刷新效果
		$("body").css({visibility:"visible"});
		
		// 取派员信息表格
		$('#grid').datagrid( {
			iconCls : 'icon-forward',
			fit : true,
			border : false,
			rownumbers : true,
			striped : true,
			pageList: [3,5,10],
			pagination : true,
			toolbar : toolbar,
			url : "${pageContext.request.contextPath}/staff_listpage.action",
			idField : 'id',
			columns : columns,
			onDblClickRow : doDblClickRow
		});
		
		// 添加取派员窗口
		$('#addStaffWindow').window({
	        title: '添加取派员',
	        width: 400,
	        modal: true,
	        shadow: true,
	        closed: true,
	        height: 400,
	        resizable:false,
			onClose:function () {
				//重置表单信息
				$("#staffForm")[0].reset();
				//手动清楚隐藏域
				$("input[name='id']").val("");
            }
	    });

        //保存取派员的按钮单击事件
        $("#save").click(function () {
            //表单检验是否为空
            if($("#staffForm").form("validate")){
                //表单检验通过提交表单
                $("#staffForm").submit();
            }
        });
	});

	function doDblClickRow(rowIndex,rowData){
		//alert("双击表格数据...");
		//弹出修改取派员的信息窗口
		$("#addStaffWindow").window("open");
		//form表单数据回显
		$("#staffForm").form("load",rowData);
	}

	//自定义验证规则
    $.extend($.fn.validatebox.defaults.rules, {
        telephone: {
            validator: function(value, param){
                var regex =/^1[34578]\d{9}$/;
                return regex.test(value)
            },
            message: '您输入的手机号码不正确！.'
        }
    });

</script>	
</head>
<body class="easyui-layout" style="visibility:hidden;">
	<div region="center" border="false">
    	<table id="grid"></table>
	</div>
	<div class="easyui-window" title="对收派员进行添加或者修改" id="addStaffWindow" collapsible="false" minimizable="false" maximizable="false" style="top:20px;left:200px">
		<div region="north" style="height:31px;overflow:hidden;" split="false" border="false" >
			<div class="datagrid-toolbar">
				<a id="save" icon="icon-save" href="javascript:void(0)" class="easyui-linkbutton" plain="true" >保存</a>
			</div>
		</div>
		
		<div region="center" style="overflow:auto;padding:5px;" border="false">
			<form id="staffForm" action="${pageContext.request.contextPath}/staff_save.action" method="post">
				<table class="table-edit" width="80%" align="center">
					<tr class="title">
						<td colspan="2">收派员信息</td>
					</tr>
					<!-- TODO 这里完善收派员添加 table -->
					<%--<tr>
						<td>取派员编号</td>
						<td><input type="text" name="id" class="easyui-validatebox" required="true"/></td>
					</tr>--%>
					<tr>
						<td>姓名</td>
						<td>
							<!--隐藏域：取派员编号-->
							<input type="hidden" name="id">
							<input type="text" name="name" class="easyui-validatebox" required="true" validType="length[2,10]"/></td>
					</tr>
					<tr>
						<td>手机</td>
						<td><input type="text" name="telephone" class="easyui-validatebox" required="true" validType="telephone"/></td>
					</tr>
					<tr>
						<td>单位</td>
						<td><input type="text" name="station" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td colspan="2">
						<input type="checkbox" name="haspda" value="1" />
						是否有PDA</td>
					</tr>
					<tr>
						<td>取派标准</td>
						<td>
							<input type="text" name="standard" class="easyui-validatebox" required="true"/>  
						</td>
					</tr>
					</table>
			</form>
		</div>
	</div>
</body>
</html>	