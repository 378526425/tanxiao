# 简介
谈笑视频匹配app,一款以陌生人视频匹配为主的交友app,帮助用户匹配合适的人，主要有视频匹配 视频聊天 社交 交友软件等功能
# 原文地址
https://www.wxmblog.com/archives/shi-pin-pi-pei-jiao-you-she-jiao-app
# 演示地址
## 安卓APK
下载地址：<br>
https://www.wxmblog.com/devminio/tanxiao/2023/11/23/f6c5d4f9-4951-47f2-a274-b7abefc1e1eb.apk <br>
测试账号：<br>
用户名：15902393423 <br>
密码：123456 <br>
# 合作洽谈
专业后端，资深UI，前端，运维，产品，测试，项目管理俱全，项目经验丰富，如果你有软件开发需求可与我联系，微信：MMRWXM（请注明来意）
# 项目地址
## github
https://github.com/378526425/tanxiao
## gitee
https://gitee.com/wxmr0/tanxiao
# 关于授权
✅允许
- 允许用于个人学习、毕业设计、教学案例、公益事业、商业使用
- 禁止将本项目的代码和资源进行任何形式的出售，产生的一切任何后果责任由侵权者自负
- 推荐Watch、Star项目，获取项目第一时间更新，同时也是对项目最好的支持 <br>

# 项目清单
完整项目清单包含如下<br>
1 APP端(uni-app)<br>
2 服务端(tanxiao-server) <br>

# 项目结构
## APP端
```
platform
|--api api接口请求
|--components 公共组件
|--pages tab页面
|--request restful接口请求封装
|--static 静态资源
|--subpackages 分包页面
|--uni_modules 引用组件包
|--utils 工具包
|--......
```
## 服务端
```
platform
|--authority 权限认证
|--common 公共工具类
|--controller 控制层
|--dao 数据访问层 
|--entity 实体类
|--schedules 定时器
|--service 业务层
|--......
```
# 技术架构
## APP端
开发框架 uniapp<br>
app端使用uniapp开发，一次开发可垮三端平台，目前暂未调试兼容小程序和h5,需要的话可能需要自己修改兼容下。
## 服务端
开发语言： java<br>
开发框架： springboot,spring cloud alibaba 两种版本，设计时按微服务设计开发，鉴于微服务的复杂性，降低维护成本，改造了一个单体springboot版本，功能完全一样<br>
数据库：mysql redis<br>
文件存储:  minio<br>
短信通知：阿里云短信
## 后台管理端
开发框架： vue
# 功能模块
## 客户端
- 在线用户浏览
- 用户视频匹配
- 用户视频聊天
- 个人中心
- 用户注册
- 找回密码
- 登录 注销登录
- 粉丝，关注列表

# 安装教程
1. 从git上拉下来，一共包含服务端（tanxiao-server）和客户端(uni-app)两个项目文件
2. 可直接运行客户端,此时客户端连接的是线上服务接口地址，如需要连接自己的服务端接口则需要本地启动服务端（tanxiao-server），然后修改客户端相关配置，具体修改细则查看对应项目中README.md <br>

# 使用说明
1. 项目演示apk中演示的功能就是目前开发的全部功能了
2. 项目中涉及的第三方服务有 腾讯云im,阿里云短信需自行开通并修改相应配置
3. 项目中部分功能还未开发完成，后续功能暂时没有开发计划，需要可自行开发完善，可联系作者索要ui设计图

# 页面展示
## 客户端
![image-1700813209046](https://www.wxmblog.com/upload/2023/11/image-1700813209046.png)
![image-1700813256839](https://www.wxmblog.com/upload/2023/11/image-1700813256839.png)
![image-1700813122639](https://www.wxmblog.com/upload/2023/11/image-1700813122639.png)
![image-1700813332982](https://www.wxmblog.com/upload/2023/11/image-1700813332982.png)
![image-1700814048054](https://www.wxmblog.com/upload/2023/11/image-1700814048054.png)
![image-1700814101289](https://www.wxmblog.com/upload/2023/11/image-1700814101289.png)

# 联系我们
## 作者微信
MMRWXM
## QQ交流群
665351147
# 其他优秀开源项目推荐
https://www.wxmblog.com/categories/open