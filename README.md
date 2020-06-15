# 本机获取公网IP服务


因为要远程控制家里电脑，无奈家用宽带现在都是动态获取公网IP，所以写了springboot项目，里面就一个功能
启动定时任务：发送本机公网IP信息到指定邮箱邮箱


## 可配置的

找到  info.properties
介绍：
+ 发送邮箱地址
e.semail=18057147037@163.com
+ 邮箱授权码
e.sqm=【脱敏】
+ host
e.host=smtp.163.com
+ 接受邮箱地址 目标邮箱地址
e.remail=double_cheng@qq.com
+ 发件人
e.sender=18057147037@163.com
+ 邮箱主题
e.subject=ip info
+ 定时任务发送时间 （cron）
e.cron=0/30 * * * * ?
