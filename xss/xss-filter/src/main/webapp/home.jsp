<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="post" action="/xssfilter">
    请输入你要评论的信息
    <input type="text" value="<script>alert('sss')</script>" name="info">
    <input type="submit" value="提交">
</form>
<p>你评论的内容</p>
${info}
</body>
</html>
