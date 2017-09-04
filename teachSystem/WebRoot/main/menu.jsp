<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>  
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>My JSP 'index.jsp' starting page</title>   
  <script type="text/javascript" src="<%=request.getContextPath() %>/ztree/jquery-1.4.4.min.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath() %>/ztree/jquery.ztree.core-3.5.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath() %>/ztree/jquery.ztree.excheck-3.5.js"></script>
  
  <link rel="stylesheet" href="<%=request.getContextPath() %>/ztree/demo.css" type="text/css"/>
  <link rel="stylesheet" href="<%=request.getContextPath() %>/ztree/zTreeStyle/zTreeStyle.css" type="text/css"/>
  <script type="text/javascript">
  		var setting = {
  				data:{
  					key:{
  						name:"mname",
  						
  					},
  					simpleData:{
  						enable:true,
  						idKey:"mid",
  						pIdKey:"pid"
  					}
  				},
  			//	check:{
  				//	enable:true
  			//	}
  			};

		var zNodes =${json};
		$(document).ready(function(){
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
		});
  </script>
  </head>
  
  <body>
   
		<ul id="treeDemo" class="ztree"></ul>

  </body>
</html>
