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
    <title>BUG管理</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<jsp:include page="../../layout/script.jsp"></jsp:include>
	<script type="text/javascript">
			var $dg;
			var $grid;
			$(function() {
				 $dg = $("#dg");
				 $grid=$dg.datagrid({
					//数据来源
					url : "<%=request.getContextPath()%>/bugInfo.do",
					//url : "bug/bugAction!findBugList.action",
					width : 'auto',
					height : $(this).height()-85,
					pagination:true,
					rownumbers:true,
					border:true,
					striped:true,
					singleSelect:true,
					columns : [ [ {field : 'bugName',title : 'Bug名称',width : parseInt($(this).width()*0.2)},
					              {field : 'attachmentUrl',title : 'BUG简要',width : parseInt($(this).width()*0.2)},
					              {field : 'description',title : 'Bug附件及描述',width : parseInt($(this).width()*0.5)}
					              ] ],toolbar:'#tb'
				});
				 
				//搜索框
				$("#searchbox").searchbox({ 
					 menu:"#mm", 
					 prompt :'模糊查询',
					 //与左侧下拉框连用，动态获取下拉框值value和文本框值name lxx 2017年11月27日 15:13:24
				     searcher:function(value,name){   
				    	var str="{\"searchName\":\""+name+"\",\"searchValue\":\""+value+"\"}";
				    	//alert(str)
			            $dg.datagrid('reload',$.parseJSON(str)); 
				    }
				   
				});
			});
			
			
			/* var names=$('#selTree').combotree('getValues');//数组
				var nameStr="";
				var nameString="";
				for (var i = 0; i < names.length; i++) {
					nameStr+=","+names[i];
			}
				nameString=nameStr.substring(1, nameStr.length);
				//console.info(nameString)

			var ipValue=$("#ip").textbox("getValue");
			var nameValue=$("#userName").textbox("getValue");
			
			var timeStartValue=$("#timeStart").val();
			var timeEndValue=$("#timeEnd").val();
			//datagrid  load方法的解释
			//加载和显示第一页的所有行。如果指定了'param'，它将取代'queryParams'属性。
			//通常可以通过传递一些参数执行一次查询，通过调用这个方法从服务器加载新数据。
			//**通俗的来说：调用datagrid的load方法，给参数，相当于利用这几个参数重新查询表格里符合条件的数据，并展示
			//**这里走的方法，会使用datagrid最初建立的方法，此时是getgoodsList.action
			$("#datagridDIV").datagrid("load",{
				
				'ip':ipValue,
				'userName':nameValue,
				'timeStartStr':timeStartValue,
				'timeEndStr':timeEndValue,
				'names':nameString
				
				//'goodsBean.timeEnd':timeEndValue 【在SSH框架下，赋值格式goodsBean.timeEnd:timeEndValue】
				//'goodsBean.timeEnd':timeEndValue 【在SSM框架下，赋值格式timeEnd:timeEndValue】
			}); */
			
			
			
			//删除
			function delRows(){
				var row = $dg.datagrid('getSelected');
				if(row){
					var rowIndex = $dg.datagrid('getRowIndex', row);
					$dg.datagrid('deleteRow', rowIndex);
					$.ajax({
						url : "bug/bugAction!delBug.action",
						data: "bugId="+row.bugId,
						success: function(rsp){
							parent.$.messager.show({
								title : rsp.title,
								msg : rsp.message,
								timeout : 1000 * 2
							});
						}
					});
				}else{
					parent.$.messager.show({
						title : "提示",
						msg :"请选择一行记录!",
						timeout : 1000 * 2
					});
				}
			}
			//弹窗修改
			function updRowsOpenDlg() {
				var row = $dg.datagrid('getSelected');
				if (row) {
					parent.$.modalDialog({
						title : '编辑BUG',
						width : 600,
						height : 400,
						href : "jsp/bugManager/bugEditDlg.jsp",
						onLoad:function(){
							var f = parent.$.modalDialog.handler.find("#form");
							f.form("load", row);
						},			
						buttons : [ {
							text : '编辑',
							iconCls : 'icon-ok',
							handler : function() {
								parent.$.modalDialog.openner= $grid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
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
			//弹窗增加
			function addRowsOpenDlg() {
				parent.$.modalDialog({
					title : '添加BUG',
					width : 600,
					height : 400,
					href : "jsp/bugManager/bugEditDlg.jsp",
					buttons : [ {
						text : '保存',
						iconCls : 'icon-ok',
						handler : function() {
							parent.$.modalDialog.openner= $grid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
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
		</script>
  </head>
  <body>
      <div data-options="region:'center',border : false">
  		<div class="well well-small" style="margin-left: 5px;margin-top: 5px">
				<span class="badge">提示</span>
				<p>
					在此你可以对<span class="label-info"><strong>bug</strong></span>进行管理!建议上传压缩包。直接上传图片会导致预览变形！可以直接把文件拖入编辑器中,完成上传！最大上传文件为25M!
				</p>
		</div>
		<div id="tb" style="padding:2px 0">
			<table cellpadding="0" cellspacing="0">
				<tr>
					<td style="padding-left:2px">
						<shiro:hasPermission name="bugAdd">
							<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addRowsOpenDlg();">添加</a>
						</shiro:hasPermission>
						<shiro:hasPermission name="bugEdit">
							<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="updRowsOpenDlg();">编辑</a>
						</shiro:hasPermission>
						<shiro:hasPermission name="bugDel">
							<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="delRows();">删除</a>
						</shiro:hasPermission>
					</td>
					<!--模糊搜索按钮 -->
					<td style="padding-left:2px">
						<input id="searchbox" type="text"/>
					</td>
				</tr>
			</table>
		</div>
		<div id="mm">
				<div name="bugName">BUG名称</div>
				<div name="attachmentUrl">BUG简要</div>
				<div name="description">BUG附件描述</div>
		</div>
		<table id="dg" title="BUG管理"></table>
  	</div>	
  </body>
</html>
