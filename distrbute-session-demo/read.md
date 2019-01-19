## 第一步：配置启动nginx
先启动nginx</br>
    upstream  backServer{

	    server 127.0.0.1:8080   ;
		server 127.0.0.1:8081   ;
	}

	server {
        listen       80;
        server_name localhost;
        location / {
		    ### 指定上游服务器负载均衡服务器
		    proxy_pass http://backServer;

        }
    }
## 第二步：启动8080与8081实例
## 第三步：访问http://localhost/createSession?nameValue=yzx123一次，在访问http://localhost/getSession多次