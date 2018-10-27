<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>部署表管理</title>
<%@include file="/commons/include/get.jsp" %>
<script type="text/javascript">
		window.name="win";
		var deployID="";
		
		$(function(){
			$("#layoutMain").ligerLayout({ rightWidth: 350, height: '100%'});
		});
		
		function selectid()
		{
		        if (!confirm("确认要生成？")) {
            			window.event.returnValue = false;
        			}else{
        			window.location.href='build.ht';
        			}
		}
		
		
</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">部署表管理列表</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link search" id="btnSearch"><span></span>查询</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link add" href="edit.ht"><span></span>添加</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link update" id="btnUpd" action="edit.ht"><span></span>修改</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link del"  action="del.ht"><span></span>删除</a></div>
					<a href='#' class='button'  onclick="selectid()"><span class="icon ok"></span><span >生成</span></a>
				</div>	
			</div>
			<div class="panel-search">
				<form id="searchForm" method="post" action="list.ht">
					<div class="row">
					</div>
				</form>
			</div>
		</div>
		<div class="panel-body">
	    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
			</c:set>
		    <display:table name="deployList" id="deployItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
			  		<input type="checkbox" class="pk" name="id" value="${deployItem.id}">
				</display:column>
				<display:column property="deviceID" title="设备ID" sortable="true" sortName="F_DEVICEID"></display:column>
				<display:column property="URL" title="服务访问地址URL" sortable="true" sortName="F_URL"></display:column>
				<display:column property="serviceno" title="服务端口号" sortable="true" sortName="F_SERVICENO"></display:column>
				<display:column property="platformID" title="平台服务ID" sortable="true" sortName="F_PLATFORMID"></display:column>
				<display:column title="管理" media="html" style="width:220px">
					<a href="del.ht?id=${deployItem.id}" class="link del"><span></span>删除</a>
					<a href="edit.ht?id=${deployItem.id}" class="link edit"><span></span>编辑</a>
					<a href="get.ht?id=${deployItem.id}" class="link detail"><span></span>明细</a>
				</display:column>
			</display:table>
			<hotent:paging tableId="deployItem"/>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


