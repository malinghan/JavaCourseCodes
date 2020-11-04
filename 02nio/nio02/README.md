# netty-gateway


### 作业1:
 
  使用不同的httpClient 调用Netty Server

### 作业2:
    
   Netty Server + Filter
   
- MyHttpRequestFilter接收用户请求，并往header中传递auth等param
- MyHttpResponseFilter 在gateway调用实际服务之后，在返回的response中对body进行篡改
   
### 作业3:
   
   Netty Server + Router
    
    
- MyHttpEndpointRouter 实现了一个简单的轮询的网关路由       
- endpoints 取代proxyServer 传入多个实际调用服务信息
- 
```