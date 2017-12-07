<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<base href="<%=basePath%>">
<title>程式管理</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">    
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<jsp:include page="../../layout/script.jsp"></jsp:include>
	<script type="text/javascript">
	
		var $dg;
		var $grid;
		var typedata=[{"type":"F","typeName":"菜单"},{"type":"O","typeName":"操作"}];
		$(function () {
			$dg = $("#dg");
			$grid=$("#dg").treegrid({
		    	url:'<%=request.getContextPath()%>/getTreegrid.do',
		        method:'post',          //请求方式
		        idField:'id',           //定义标识树节点的键名字段
		        animate:true,			//定义在节点展开或折叠的时候是否显示动画效果。
		        onlyLeafCheck:true,		//定义是否仅在叶子节点前显示复选框。
		        treeField:'name',       //定义树节点的字段
		        striped:true,//显示条纹
		        rownumbers:true,//显示行数
		        fit:true,               //网格自动撑满
		        fitColumns:true,        //设置为 true，则会自动扩大或缩小列的尺寸以适应网格的宽度并且防止水平滚动。
				pagination:true,
	 		    pageSize:3,
	 		    pageList:[1,2,3,5,7,10],
	 		    pageNumber:1,
		        columns:[[
		            {field:'name',title:'程式名称',width:100},
		            {field:'id',title:'父程式名称',width:100},
		            {field:'_parentId',title:'排序编码',width:100},
		            {field:'ico',title:'程式图标',width:100},
		            {field:'url',title:'程式路径',width:100},
		            {field:'myid',title:'程式编码',width:100},
		            {field:'type',title:'程式类型',width:100},
		            {field:'status',title:'是否启用',width:100},
		            {field:'des',title:'程式描述',width:100},
		        ]] /* ,
		        "menus":[  
		                 {"title": "展开节点", "url": "http://www.163.com?id=#id#&orgCode=#orgCode#", "target": "_blank"},  
		                 {"title": "折叠节点", "url": "http://www.163.com?2", "target": "_self"},  
		                 {"title": "删除节点", "url": "http://www.163.com?3", "target": "_blank"},  
		                 {"title": "刷新"}  
		             ]   */
		    });
		});
// 		===============================================================
		var flag=true;
		function endEdit(){
			var select = $dg.treegrid('getSelections');
			if(select){
				var nodes = $dg.treegrid('getData');
				checkedNodes(nodes);
			}
			return flag;
		}
		
		
		//遍历节点和子节点
		function checkedNodes(nodes){
			if(nodes){
				$.each(nodes,function(i,node){
					if(node){
						$dg.treegrid('endEdit', node.permissionId);
						var temp=$dg.treegrid('validateRow', node.permissionId);
						if(!temp){ flag= false; }
					}
					if(node.children){
						checkedNodes(node.children);
					}
				});
			}
			return flag;
		}
		
		function editNode(){
			var nodes = $dg.treegrid('getSelections');
			if(nodes==null||nodes==""){
				$.messager.alert("提示", "请选择行记录!");
			}else{
				$.each(nodes,function(i,node){
					if(node){
						$dg.treegrid('beginEdit', node.permissionId);
					}
				});
			}
		}
		function saveNodes(){
			if(endEdit()){
				if ($dg.treegrid('getChanges').length) {
					var inserted = $dg.treegrid('getChanges', "inserted");
					var deleted = $dg.treegrid('getChanges', "deleted");
					var updated = $dg.treegrid('getChanges', "updated");
					
					var effectRow = new Object();
					if (inserted.length) {
						effectRow["inserted"] = JSON.stringify(inserted);
					}
					if (deleted.length) {
						effectRow["deleted"] = JSON.stringify(deleted);
					}
					if (updated.length) {
						effectRow["updated"] = JSON.stringify(updated);
					}
					$.post("function/functionAction!persistenceFunction.action", effectRow, function(rsp) {
						if(rsp.status){
							$dg.datagrid('acceptChanges');
						}
						$.messager.alert(rsp.title, rsp.message);
					}, "JSON").error(function() {
						$.messager.alert("提示", "提交错误了！");
					});
				}
			}else{
				$.messager.alert("提示", "字段验证未通过!请查看");
			}
		}
		//增加并列项
		function addStandPlaceNode(){
			var temp=jqueryUtil.getRandTime();
			var node = $dg.treegrid('getSelected');
			if (node){
				$dg.treegrid('insert', {
					after: node.permissionId,
					data: {
						permissionId:temp,
						pid:node.pid,
						pname:node.pname,
						sort:node.sort+1,
						url:'javascript:void(0);',
						status:'add'
					}
				});
				$dg.treegrid('unselect', node.permissionId);
				$dg.treegrid('select', temp);
				$dg.treegrid('beginEdit', temp);
			}else{
				$.messager.alert("提示", "请选择一行记录!");
			}
		}
//增加子项
		function addSubitemNode(){
			var temp=jqueryUtil.getRandTime();
			var node = $dg.treegrid('getSelected');
			if (node){
				$dg.treegrid('insert', {
					after: node.permissionId,
					data: {
						permissionId:temp,
						pid:node.permissionId,
						pname:node.name,
						sort:node.sort+1,
						url:'javascript:void(0);',
						status:'add'
					}
				});
				$dg.treegrid('unselect', node.permissionId);
				$dg.treegrid('select', temp);
				$dg.treegrid('beginEdit', temp);
			}else{
				$.messager.alert("提示", "请选择一行记录!");
			}
		}
		
//弹窗增加
			function addRowsOpenDlg() {
				var row = $("#dg").treegrid("getSelected");
				if(row){
					if(row.type=="O"){
						parent.$.messager.show({
							title :"提示",
							msg :"操作暂无下层!",
							timeout : 1000 * 2
						});
					}else{
						parent.$.modalDialog({
							title : "添加程式",
							width : 600,
							height : 400,
							href : "jsp/function/functionEditDlg.jsp",
							onLoad:function(){
								if(row){
									var f = parent.$.modalDialog.handler.find("#form");
									f.form("load", {"pid":row.permissionId});
								}
							},	
							buttons : [ {
								text : '保存',
								iconCls : 'icon-ok',
								handler : function() {
									parent.$.modalDialog.openner= $grid;//因为添加成功之后，需要刷新这个treegrid，所以先预定义好
									var f = parent.$.modalDialog.handler.find("#form");
									f.submit();
								}
							}, {
								text : '取消',
								iconCls : 'icon-cancel',
								handler : function() {
									parent.$.modalDialog.handler.dialog('destroy');
									parent.$.modalDialog.handler = undefined;
								}
							}
							]
						});
					}
				}else{
					parent.$.modalDialog({
						title : "添加程式",
						width : 600,
						height : 400,
						href : "jsp/function/functionEditDlg.jsp",
						onLoad:function(){
							if(row){
								var f = parent.$.modalDialog.handler.find("#form");
								f.form("load", {"pid":row.permissionId});
							}
						},	
						buttons : [ {
							text : '保存',
							iconCls : 'icon-ok',
							handler : function() {
								parent.$.modalDialog.openner= $grid;//因为添加成功之后，需要刷新这个treegrid，所以先预定义好
								var f = parent.$.modalDialog.handler.find("#form");
								f.submit();
							}
						}, {
							text : '取消',
							iconCls : 'icon-cancel',
							handler : function() {
								parent.$.modalDialog.handler.dialog('destroy');
								parent.$.modalDialog.handler = undefined;
							}
						}
						]
					});
				}
			}
			
//弹窗修改
//与新增dialog引入页面共用，action方法也公用，进后台判断主键id存在与否来决定【新增】 或是 【修改】
			function updRowsOpenDlg() {
				var row = $dg.treegrid('getSelected');
				if (row) {
					parent.$.modalDialog({
						title : "编辑程式",
						width : 600,
						height : 400,
						href : "jsp/function/functionEditDlg.jsp?tempId="+row.type,
						onLoad:function(){
							var f = parent.$.modalDialog.handler.find("#form");
							f.form("load", row);
						},			
						buttons : [ {
							text : '编辑',
							iconCls : 'icon-ok',
							handler : function() {
								parent.$.modalDialog.openner= $grid;//因为添加成功之后，需要刷新这个treegrid，所以先预定义好
								var f = parent.$.modalDialog.handler.find("#form");
								f.submit();
							}
						}, {
							text : '取消',
							iconCls : 'icon-cancel',
							handler : function() {
								parent.$.modalDialog.handler.dialog('destroy');
								parent.$.modalDialog.handler = undefined;
							}
						}
						]
					});
				}else{
					parent.$.messager.show({
						title :"提示",
						msg :"请选择一行记录!",
						timeout : 1000 * 2
					});
				}
			}
			
//  		删除
			function removeNode(){
				var node = $dg.treegrid('getSelected');
				if(node){
					parent.$.messager.confirm("提示","确定要删除记录吗?",function(r){  
					    if (r){  
					    	$.post("<%=request.getContextPath()%>/delInfo.do", {id:node.permissionId}, function(rsp) {
					    		
									alert(rsp.rsp.msg)
								if(rsp.rsp.status){
									$dg.treegrid('remove', node.permissionId);
								}
								parent.$.messager.show({
									title : rsp.rsp.title,
									msg : rsp.rsp.msg,
									timeout : 1000 * 2
								});
							}, "JSON").error(function() {
								parent.$.messager.show({
									title :"提示",
									msg :"提交错误了！",
									timeout : 1000 * 2
								});
							});
					    }  
					});
				}else{
					parent.$.messager.show({
						title :"提示",
						msg :"请选择一行记录!",
						timeout : 1000 * 2
					});
				}
			} 
			
	</script>
  </head>
  <body>
  	<div class="well well-small" style="margin-left: 5px;margin-top: 5px">
				<span class="badge">提示</span>
				<p>
					在此你可以对<span class="label-info"><strong>菜单功能</strong></span>进行编辑!  &nbsp;<span class="label-info"><strong>注意</strong></span>操作功能是对菜单功能的操作权限！
					请谨慎填写程序编码，权限区分标志，请勿重复!
				</p>
			</div>
    <div id="tb" style="padding:10px;height:auto">
			<div style="margin-bottom:5px">
<%-- 			<shiro:hasPermission name="funAdd"> --%>
				<!--  <a href="javascript:void(0);" class="easyui-splitbutton" data-options="menu:'#mm1',iconCls:'icon-add'">添加</a>
				<div id="mm1" style="width:150px;">
					<div data-options="iconCls:'icon-undo'" onclick="addStandPlaceNode();">增加并列项</div>
					<div data-options="iconCls:'icon-redo'" onclick="addSubitemNode();">增加子项</div>
				</div>-->
				<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addRowsOpenDlg();">添加</a>
<%-- 			</shiro:hasPermission> --%>
<%-- 			<shiro:hasPermission name="funEdit"> --%>
				<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="updRowsOpenDlg();">编辑</a>
<%-- 			</shiro:hasPermission> --%>
<%-- 			<shiro:hasPermission name="funDel"> --%>
				<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="removeNode();">删除</a>
<%-- 			</shiro:hasPermission> --%>
		<!-- <shiro:hasPermission name="funEndEdit">
				<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="endEdit();">结束编辑</a>
			</shiro:hasPermission>
			<shiro:hasPermission name="funSave">
				<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="saveNodes();">保存</a>
			</shiro:hasPermission> -->	
			</div>
			
		</div>
  		<!-- 下拉树表 -->
  		<table id="dg" title="程式管理" class="easyui-treegrid" style="width:700px;height:250px">
		</table>
  </body>
</html>
