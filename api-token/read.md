## 获取 request 中用POST方式"Content-type"是"application/x-www-form-urlencoded;charset=utf-8"发送的 json 数据问题？
request中发送json数据用post方式发送Content-type用application/json;charset=utf-8方式发送的话，
直接用springMVC的@RequestBody标签接收后面跟实体对象就行了，spring会帮你自动拼装成对象，
如果Content-type设置成application/x-www-form-urlencoded;charset=utf-8就不能用spring的东西了，
只能以常规的方式获取json串了</br>
需要再请求中加入： @PostMapping(value = "/insert",produces = "application/json; charset=utf-8")
## 测试1--postman测试
浏览器访问：http://localhost:9000/，用于生成token并且保存到redis中</br>
post方法，地址：http://localhost:9000/insert1</br>
header参数为token，值为http://localhost:9000/生成的token(也可以到redis中拿出来)</br>
body参数：{"username":"insert1111","password":"1111"}</br>
最后send测试多次</br>

## 测试2--表单提交
浏览器访问：http://localhost:9000/
然后填写表单，点击提交，后发现添加成功，查看数据库，添加数据成功了,</br>
最后在当前成功的页面右键重新加载，结果讲呈现：不能重复提交，则表明成功了</br>