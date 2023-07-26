# Description

money-book 是一个账本系统


## api 文档
http://localhost:5000/api/doc.html

## 登录
账号:root,密码:Abcd1234

## 登录获取token
localhost:5000/api/user/login

```json
{
  "username": "root",
  "password":"Abcd1234"
}
```
需要注意的是LoginController并没有详细的登录代码
登录验证主要是spring security帮我们完成了，具体的代码
定义在`JWTAuthenticationFilter`中
如下所示

```java
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        // 从输入流中获取到登录的信息
        try {
            LoginVO loginVO = new ObjectMapper().readValue(request.getInputStream(), LoginVO.class);
            return authenticationManager.authenticate(
                    // 此处需要将密码加密验证
                    new UsernamePasswordAuthenticationToken(loginVO.getUsername(), loginVO.getPassword())
            );
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
```


## 部署后遇到的奇怪问题

前端登录会做重定向

`http://localhost:8000/user/login?redirect=%2F`

后来发现每次登录页面出现没多久 就会变成空白的 
查看网络请求 发现这个请求发送到后端了
正确做法是后端不应该处理这个请求
这个请求路径应该给前端处理
我们需要配置一下代理

```js
  devServer: {
    // development server port 8000
    port: 8000,
    // 路由代码
    proxy: {
      '/api': {
        target: 'http://localhost:5000/',
        ws: false,
        changeOrigin: true
      }
    }
  },
```
axios会对每个请求前面加上/api
所以只有api的请求需要转交给后端服务处理
如果你把所有请求都给后端处理
那么就会导致本来应该由前端处理的路由给到了后端
导致前端的路由无法发挥作用
所以应该只对api开头的请求做代理 其他请求不要做代理


## Docker 
```
# 需要注意 如果之前挂载过目录 重新创建容器的时候 需要删除原来的目录 否则密码还是原来的密码 死活他妈的登录不上
docker run --name some-mysql -e MYSQL_ROOT_PASSWORD=pass -p 3306:3306 -d -v /root/mysql/conf:/etc/mysql/conf.d -v /root/mysql/data:/var/lib/mysql mysql:8.0
```