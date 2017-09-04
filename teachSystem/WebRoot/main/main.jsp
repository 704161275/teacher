<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>后台管理系统</title>

</head>	
	<frameset rows="100,*" framespacing="0" border="0" frameborder="no">
	<frame src="<%=request.getContextPath()%>/main/top.jsp" name="top" noresize="noresize" frameborder="0" scrolling="no">
	<frameset cols="200,*" framespacing="0">
		<frame src="<%=request.getContextPath()%>/loginServlet?method=getLeft" name="menu" noresize="noresize" frameborder="0" >
		<frame frameborder="0" src="<%=request.getContextPath()%>/main/center.jsp" name="mainpage" noresize="noresize" frameborder="0">
	</frameset>
</frameset>
<body>
</body>
</html>