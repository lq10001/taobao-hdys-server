#韩都衣舍

客户端地址：[http://git.oschina.net/dongfangx/taobao-hdys](http://git.oschina.net/dongfangx/taobao-hdys)

公众号：全栈程序员之路
![输入图片说明](https://mp.weixin.qq.com/mp/qrcode?scene=10000004&size=102&__biz=MzIzMTE0NTE5Mg==&mid=2651421365&idx=1&sn=ec2b310f1888fca3560ba504af0077f5&send_time= "在这里输入图片标题")

## 预览图
1. 主页
![输入图片说明](https://git.oschina.net/uploads/images/2017/0715/210704_997cf683_3018.png "在这里输入图片标题")
2. 菜单
![![输入图片说明](https://git.oschina.net/uploads/images/2017/0715/210718_fdcb22a9_3018.png "在这里输入图片标题")](https://mmbiz.qpic.cn/mmbiz/ZxibLSBRKqOmujiaicyibcopTdzet3sxDiaKkwn83EfvzxZLXVwKg2M4B4x63HJJWBAtSDg0ticZCM2hCoNuR5uOhnEA/640?wx_fmt=png&wxfrom=5&wx_lazy=1 "在这里输入图片标题")
3. 推荐
![![输入图片说明](https://git.oschina.net/uploads/images/2017/0715/210728_bf5dec8f_3018.png "在这里输入图片标题")](https://mmbiz.qpic.cn/mmbiz/ZxibLSBRKqOmujiaicyibcopTdzet3sxDiaKkva1PY3mWRjdrpUibMuup7Ugh780bpAnsxic1bnCMMYia3DXhDqRZB8w2A/640?wx_fmt=png&wxfrom=5&wx_lazy=1 "在这里输入图片标题")
4. 详情
![输入图片说明](https://git.oschina.net/uploads/images/2017/0715/210742_45d4c1bd_3018.png "在这里输入图片标题")
5. 分享
![输入图片说明](https://git.oschina.net/uploads/images/2017/0715/210751_e3475bc5_3018.png "在这里输入图片标题")

## 产品定位
天猫网店App版本

## 产品解决的痛点
天猫大网店，想拥有自己的App，类似美丽说。

## 产品潜在客户
1. 天猫网店
2. 淘宝网店

## 需求描述
1. 展示商家商品
2. 同步更新天猫或淘宝商品
3. 能收藏喜欢的商品，并购买
4. 能自定义推广的店铺
5. 能根据热度，销售排行等，推广商品。

## 技术架构-iOS
1. 图片缓存：SDWebImage
2. 数据统计：UMeng
3. 数据分享：UMeng
4. 刷新组件：EGO

## 技术架构-后端
1. Java：Nutz框架
2. 缓存：EhCache，
3. 静态模板框架：Beetl
4. 后台UI框架：B-JUI
5. 数据库：MySql
6. 后台服务器：阿里云centos6
7. HTML分析：Jsoup
8. 定时任务：Quartz

## 开发中遇到的问题
刚开始开发的时候，想直接调用阿里开发平台的数据。但是在开发过程中，发现阿里云平台还不够成熟，各种问题。
后面只有自己写了一个爬虫程序，定时抓取店铺下的所有产品数据，

## Default 设计的还不错，UI很牛的
![输入图片说明](https://git.oschina.net/uploads/images/2017/0715/210949_91b4a6f9_3018.png "在这里输入图片标题")
