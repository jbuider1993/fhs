<%@ tag pageEncoding="utf-8"%>
<!-- 定义了model标签属性 -->
<%@ attribute name="model" required="true" rtexprvalue="true"
              description="model"%>
<title><%=request.getSession().getServletContext().getAttribute(model + "_title") %></title>
<meta name="keywords" content="<%=request.getSession().getServletContext().getAttribute(model + "_keyword") %>"/>
<meta name="description" content="<%=request.getSession().getServletContext().getAttribute(model + "_desc") %>"/>