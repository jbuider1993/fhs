<%--
  Created by IntelliJ IDEA.
  User: qh
  Date: 2018-08-23
  Time: 10:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>跳转frame页面</title>
    <script>
        gojsp();
        function gojsp(){
            var url0 = "${param.url}";
            var url = decodeURI(url0);
            console.log(url);
            window.location.href = url;
        }

    </script>
</head>
<body>

</body>
</html>
