# 项目简介
本项目是springcloud单点登录服务,采用springboot-2.1.13,springcloud-Greenwich.SR5版本
主要解决前后端分离服务认证解决方案

# 应用使用
* 资源服务需要引入相应的依赖包
* 加入OAuth2ResourceServerConfig配置
* yml配置文件中加入security.oauth2的配置


#应用注册
http://localhost:9999/service/register

# 登录
## 表单登录
* POST http://localhost:9999/v1/sso-server/form/token
* username:13901010101
* password:123456
* 在Authonrization选择Basic Auth 加入应用注册的clientId和clientSecret
* Username:client1 
* Password:client1
* 见图 https://github.com/vangyong/microservice-sso/blob/master/doc/form-login1.png
* 见图 https://github.com/vangyong/microservice-sso/blob/master/doc/form-login2.png

## 接口请求
请求头添加Authorization信息
key: Authorization
value: bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiIxMzkwMTAxMDEwMSIsImF1dGhvciI6Indhbmd5b25nIiwic2NvcGUiOlsiYWxsIiwicmVhZCIsIndyaXRlIl0sImNvbXBhbnkiOiJ3d3cuc2VnZW1hLmNuIiwiZXhwIjoxNTg0MTk3ODExLCJqdGkiOiIyY2YxYTU3Ny0xNjg3LTQ0OGItYjEzMi0yZmFjM2QyNjI5MjQiLCJjbGllbnRfaWQiOiJjbGllbnQyIn0.lgFkXGdEaCMigiy5r_EiPyqH_9kECDp9IG-ZDa7uVZU
* 见图 https://github.com/vangyong/microservice-sso/blob/master/doc/form-login3.png


## postman测试:
https://blog.csdn.net/qq_19671173/article/details/79748422

