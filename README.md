<p align="center">
  <a href="#"><img src="https://i.postimg.cc/Ssd20T27/image.png" width="200" height="200" alt="一白_"></a>
</p>
<div align="center">

# Pixiv-API

_✨ 基于 [SpringBoot](https://github.com/spring-projects/spring-boot/blob/main/README.adoc) 实现的 Pixiv - API ✨_

</div>

<p align="center">
    <a href="https://github.com/spring-projects/spring-boot/blob/main/README.adoc"><img src="https://img.shields.io/badge/Springboot-2.6%2B-green" alt="Springboot" /></a>
    <a href="https://github.com/lvyunqi/pixivPicAPI/blob/master/License"><img src="https://img.shields.io/github/license/lvyunqi/pixivPicAPI?style=flat-square" alt="license"></a>
    <img src="https://img.shields.io/badge/JDK-1.8+-brightgreen.svg?style=flat-square" alt="jdk-version">
</p>

# API接口


## 获取指定画师插画列表


**接口地址**:`/api/PainterIllustrationList`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>页面（page）可为空</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|page|页码|query|true|integer(int32)||
|uid|画师UID|query|false|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResponseResult«JSONObject»|
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|data||object||
|message||string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": {},
	"message": ""
}
```

