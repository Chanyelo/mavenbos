<%--
  Created by IntelliJ IDEA.
  User: Sonner or Later
  Date: 2017/5/8
  Time: 20:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>zTree和EasyUI的Tab的选项卡整合</title>
    <!-- 导入jquery核心类库 -->
    <script type="text/javascript"
    	src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
    <!-- 导入easyui类库 -->
    <link id="easyuiTheme" rel="stylesheet" type="text/css"
    	href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css"
    	href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
    <link rel="stylesheet" type="text/css"
    	href="${pageContext.request.contextPath }/css/default.css">
    <script type="text/javascript"
    	src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
    <!-- 导入ztree类库 -->
    <link rel="stylesheet"
    	href="${pageContext.request.contextPath }/js/ztree/zTreeStyle.css"
    	type="text/css" />
    <script
    	src="${pageContext.request.contextPath }/js/ztree/jquery.ztree.all-3.5.js"
    	type="text/javascript"></script>
    <script
    	src="${pageContext.request.contextPath }/js/easyui/locale/easyui-lang-zh_CN.js"
    	type="text/javascript"></script>
    <script type="text/javascript">
        $(function(){
        			var setting2 ={
                    					data: {
                    						//默认值是标准json数据，需手动将简单json数据功能打开
                    						//当该功能开启后，idkey（默认key为id）、pidkey（默认key为pId）就有效了。
                    						simpleData: {
                    							enable: true,
                    							idKey: "id",//推荐使用默认值
                                                pIdKey: "pId",
                                                rootPId: 0
                    						}
                    					},
                    					callback:{
                                                                                     						onClick:function(event, treeId, treeNode, clickFlag){
                                                                                     							//event事件对象,	treeId:节点的ID,treeNode:节点的json数据对象，clickFlag：操作类型标识
                                                                                     							//alert(treeNode.name);
                                                                                     							//如果该属性不为空,则创建新的选项卡
                                                                                     							if(treeNode.page!=undefined&&treeNode.page!=""){
                                                                                     							    if($("#mytabs").tabs("exists",treeNode.name)){
                                                                                     							        $("#mytabs").tabs("select",treeNode.name);
                                                                                     							    }else{
                                                                                     							           $("#mytabs").tabs("add",{
                                                                                                                                                                                                                                        										title:treeNode.name,
                                                                                                                                                                                                                                        										//content:"内容。。。",
                                                                                                                                                                                                                                        										//content:"<iframe src='"+treeNode.page+"'></iframe>",
                                                                                                                                                                                                                                        										//content:"<iframe src='"+treeNode.page+"' scrolling='auto' frameborder='0' width='100%' height='100%'></iframe>",
                                                                                                                                                                                                                                        										content:'<div style="width:100%;height:100%;overflow:hidden;">'
                                                                                                                                                                                                                                        											+ '<iframe src="'
                                                                                                                                                                                                                                        											+ treeNode.page
                                                                                                                                                                                                                                        											+ '" scrolling="auto" style="width:100%;height:100%;border:0;" ></iframe></div>',
                                                                                                                                                                                                                                        										closable:true
                                                                                                                                                                                                                                        									});

                                                                                     							    }



                                                                                     							}

                                                                                     						}
                                                                                     					}
                    			};
                    			//2)准备json数据(标准)用来填充树
                    			var zNodes2 =[
                                             			{id:1, pId:0 ,name:"搜索引擎"} ,
                                             			//page是自定义的属性
                                             			{id:101, pId:1 ,name:"百度",page:"http://www.baidu.com"} ,
                                             			{id:102, pId:1 ,name:"谷歌",page:"http://www.baidu.com"} ,
                                             			{id:2, pId:0 ,name:"门户网站"}
                                             		             ];


                    			//3)初始化并生成zTree
                    			$.fn.zTree.init($("#simpleZtree"), setting2, zNodes2);

        		});

    </script>
  </head>
  <body class="easyui-layout">
    <div data-options="region:'north',title:'North Title',split:true" style="height:100px;">1</div>

    <div data-options="region:'south',title:'South Title',split:true" style="height:100px;">2</div>

    <div data-options="region:'east',iconCls:'icon-reload',title:'East',split:true" style="width:100px;">3</div>

    <div data-options="region:'west',title:'West',split:true" style="width:200px;">
        <div class="easyui-accordion" data-options="fit:true">
        			<!-- 每个选项卡，就是一个子div
        			必须提供title属性
        			-->
        			<div title="菜单1">
        			    <ul id="simpleZtree" class="ztree"></ul>
        			</div>
        			<div data-options="title:'菜单2',selected:true">菜单2内容</div>
        			<div data-options="title:'菜单3'">菜单2内容</div>
        		</div>
    </div>

    <div data-options="region:'center'" >
        <div  id="mytabs" class="easyui-tabs" data-options="fit:true, border:false">
            <div title="Tab2" data-options="closable:true">tab2 </div>


        </div>

    </div>

  </body>
</html>
