
七道官网模板制作使用指南
=====
<!-- MarkdownTOC depth=0 -->

- 常用标签
    - 文章标签
    - 配置信息标签(directive_config)
    - 站点信息标签 (directive_site)
    - 站点信息标签 (directive_site_list)
    - 友情链接列表标签 (directive_friend_link_list)
    - 广告标签 (directive_advertise)
    - 图片标签 (directive_picture_list)
    - 相册图片标签 (directive_photo)
    - 相册图片列表标签 (directive_photo_list)
    - 相册标签 (directive_photo_category)
    - 相册列表标签 (directive_photo_category_list)
    - 视频标签 (directive_video)
    - 视频列表标签 (directive_video_list)
    - 活动标签 (directive_activity)
    - 活动列表标签 (directive_activity_list)
- 常用函数
    - 获取日期字符串函数(${fn_date()})
    - 截取固定长度字符串并替换多余字符(${fn_replace(input, len, replacement)})
- 全局常用共享变量

<!-- /MarkdownTOC -->

**提示**
> 本模板使用freemarker模板标记语言编写，使用时候用成对编写&lt;tag&gt;,&lt;/tag&gt;  
> 使用每个变量前必须用加上括号和感叹号，以免空信息异常  
> **例子:**   
> 变量获取(默认值为空): ${(variable_name)!}  
> 变量获取(默认值为空): ${(variable_name)!"default"}
 
## 常用标签
### 文章标签
待续...

### 配置信息标签(directive_config)
<table>
<tr>
	<th>参数</th>
	<th>类型</th>
	<th>必须</th>
	<th>描述</th>
</tr>
<tr>
	<td>key</td>
	<td>String</td>
	<td>Y</td>
	<td>配置关键码</td>
</tr>
</table>

**使用方式**

    <@directive_config key="key"/>
其他数据在网站后台查看、修改、添加 (建议使用全局常量共享变量)

### 站点信息标签 (directive_site)
**传递参数**
<table>
<tr>
	<th>参数</th>
	<th>类型</th>
	<th>必须</th>
	<th>描述</th>
</tr>
<tr>
	<td>siteAlias</td>
	<td>integer</td>
	<td>Y</td>
	<td>站点别名</td>
</tr>
</table>

**使用方式**

    <@directive_site customSiteId=1>
        siteId:         ${_entity_site.siteName!}
        customSiteId:   ${_entity_site.customSiteId!}
        siteName:       ${_entity_site.siteName!}
        siteAlias:      ${_entity_site.siteAlias!}
        url:            ${_entity_site.url!}
        imageSiteUrl:   ${_entity_site.imageSiteUrl!}
        staticSiteUrl:  ${_entity_site.staticSiteUrl!}
        pageDir:        ${_entity_site.pageDir!}
        imageDir:       ${_entity_site.imageDir!}
        icon:           ${_entity_site.icon!}
	</@directive_site>

    结果：
    siteId:         神曲官方网站
    customSiteId:   1
    url:            http://sq.wan.com
    icon:           31343030363630333239383135.jpg

**返回参数**
<table>
<tr>
	<th>参数</th>
	<th>类型</th>
	<th>描述</th>
</tr>
<tr>
	<td>siteId</td>
	<td>int</td>
	<td>ID</td>
</tr>
<tr>
	<td>customSiteId</td>
	<td>int</td>
	<td>用户自定义的站点ID</td>
</tr>
<tr>
	<td>siteName</td>
	<td>string</td>
	<td>名称</td>
</tr>
<tr>
	<td>siteAlias</td>
	<td>string</td>
	<td>站点别名</td>
</tr>
<tr>
	<td>url</td>
	<td>string</td>
	<td>站点地址</td>
</tr>
<tr>
	<td>imageSiteUrl</td>
	<td>string</td>
	<td>站点图片域名URL</td>
</tr>
<tr>
	<td>staticSiteUrl</td>
	<td>string</td>
	<td>站点静态域名URL</td>
</tr>
<tr>
	<td>icon</td>
	<td>string</td>
	<td>图标</td>
</tr>
<tr>
	<td>pageDir</td>
	<td>string</td>
	<td>生成的页面存放目录</td>
</tr>
<tr>
	<td>imageDir</td>
	<td>string</td>
	<td>图片存放目录</td>
</tr>
<tr>
	<td>templateDir</td>
	<td>string</td>
	<td>模板存放目录</td>
</tr>
<tr>
	<td>createDate</td>
	<td>DateTime</td>
	<td>创建时间</td>
</tr>
</table>

### 站点信息标签 (directive_site_list)
**传递参数**  
无

**使用方式**

    <@directive_site_list>
        <#list _list_entity_site as entity>
            siteId:         ${entity.siteName!}
            customSiteId:   ${entity.customSiteId!}
            url:            ${entity.url!}
            icon:           ${entity.icon!}       
        </#list>
    </@directive_site_list>

    结果：
    siteId:         神曲官方网站
    customSiteId:   1
    url:            http://sq.wan.com
    icon:           31343030363630333239383135.jpg
    ....


### 友情链接列表标签 (directive_friend_link_list)
**传递参数**
<table>
<tr>
	<th>参数</th>
	<th>类型</th>
	<th>必须</th>
	<th>描述</th>
</tr>
<tr>
	<td>siteId</td>
	<td>integer</td>
	<td>Y</td>
	<td>所属站点</td>
</tr>
</table>

**返回参数**
<table>
<tr>
	<th>参数</th>
	<th>类型</th>
	<th>描述</th>
</tr>
<tr>
	<td>siteName</td>
	<td>String</td>
	<td>友情链接显示名称</td>
</tr>
<tr>
	<td>siteUrl</td>
	<td>String</td>
	<td>友情链接地址</td>
</tr>
<tr>
	<td>logoUrl</td>
	<td>String</td>
	<td>友情链接logo URL地址</td>
</tr>
<tr>
	<td>siteDesc</td>
	<td>String</td>
	<td>友情链接描述</td>
</tr>
<tr>
	<td>siteEmail</td>
	<td>String</td>
	<td>友情链接站长联系邮箱</td>
</tr>
<tr>
	<td>siteQq</td>
	<td>String</td>
	<td>友情链接站长联系QQ</td>
</tr>
<tr>
	<td>siteId</td>
	<td>String</td>
	<td>所属站点编号</td>
</tr>
<tr>
	<td>hidden</td>
	<td>int</td>
	<td>显示状态， 0: 显示， 1: 隐藏</td>
</tr>
<tr>
	<td>siteOrder</td>
	<td>int</td>
	<td>所排序编号</td>
</tr>
</table>

**使用方式**

    <@directive_friend_link_list siteId=6>
        <#list _list_friend_link as entity>
            siteName:       ${entity.siteName!}
            siteUrl:        ${entity.siteUrl!}
            logoUrl:        ${entity.logoUrl!}
            siteDesc:       ${entity.siteDesc!}
            siteEmail:      ${entity.siteEmail!}
            siteQq:         ${entity.siteQq!}
            siteId:         ${entity.siteId!}
            hidden:         ${entity.hidden!}
            siteOrder:      ${entity.siteOrder!}   
        </#list>
    </@directive_friend_link_list>

    结果：
        siteName:       37wan
        siteUrl:        http://www.37wan.com
        logoUrl:        
        siteDesc:       37玩
        siteEmail:      admin@37wan.com
        siteQq:         37
        siteId:         6
        hidden:         1
        siteOrder:      0
        
        siteName:       4399
        siteUrl:        http://web.4399.com
        logoUrl:        http://web.4399.com/logo.gif
        siteDesc:       4399
        siteEmail:      admin@4399.com
        siteQq:         9999
        siteId:         6
        hidden:         0
        siteOrder:      1
        ...

### 广告标签 (directive_advertise)
**传递参数**
<table>
<tr>
	<th>参数</th>
	<th>类型</th>
	<th>必须</th>
	<th>描述</th>
</tr>
<tr>
	<td>id</td>
	<td>integer</td>
	<td>Y</td>
	<td>广告ID</td>
</tr>
</table>

**返回参数**
<table>
<tr>
	<th>参数</th>
	<th>类型</th>
	<th>描述</th>
</tr>
<tr>
	<td>id</td>
	<td>int</td>
	<td>广告ID</td>
</tr>
<tr>
	<td>title</td>
	<td>String</td>
	<td>广告标题</td>
</tr>
<tr>
	<td>imgeUrl</td>
	<td>String</td>
	<td>显示图片URL</td>
</tr>
<tr>
	<td>linkUrl</td>
	<td>String</td>
	<td>外链地址</td>
</tr>
<tr>
	<td>remark</td>
	<td>String</td>
	<td>备注</td>
</tr>
<tr>
	<td>createTime</td>
	<td>Date</td>
	<td>创建日期</td>
</tr>
</table>

**使用方式**

    <@directive_advertise id=1>
        id:            ${_entity_advertise.id!}
        title:         ${_entity_advertise.title!}
        imgUrl:        ${_entity_advertise.imgUrl!}
        linkUrl:       ${_entity_advertise.linkUrl!}
        remark:        ${_entity_advertise.remark!}
        createTime:    ${_entity_advertise.createTime!}
    
        fn_replace:    ${fn_replace(_entity_advertise.linkUrl, 10)}
    </@directive_advertise>

    结果:
    id:            1
    title:         弹弹堂选美
    imgUrl:        http://image.ddt.wan.com/images/ver1404/logo.png
    linkUrl:       http://www.7road.com
    remark:        首页右侧广告 100X100
    createTime:    2014-6-10 12:17:12
    
    fn_replace:    http://www...


### 图片标签 (directive_picture_list)
**传递参数**
<table>
<tr>
	<th>参数</th>
	<th>类型</th>
	<th>必须</th>
	<th>描述</th>
</tr>
<tr>
	<td>positionKey</td>
	<td>string</td>
	<td>Y</td>
	<td>位置关键码</td>
</tr>
<tr>
	<td>siteId</td>
	<td>integer</td>
	<td>Y</td>
	<td>站点</td>
</tr>
<tr>
	<td>size</td>
	<td>integer</td>
	<td>N</td>
	<td>显示记录条数</td>
</tr>
</table>


**返回参数**
<table>
<tr>
	<th>参数</th>
	<th>类型</th>
	<th>描述</th>
</tr>
<tr>
	<td>id</td>
	<td>int</td>
	<td>图片ID</td>
</tr>
<tr>
	<td>title</td>
	<td>String</td>
	<td>标题</td>
</tr>
<tr>
	<td>content</td>
	<td>String</td>
	<td>内容</td>
</tr>
<tr>
	<td>orderNum</td>
	<td>int</td>
	<td>序号</td>
</tr>
<tr>
	<td>remark</td>
	<td>String</td>
	<td>备注</td>
</tr>
<tr>
	<td>createTime</td>
	<td>Date</td>
	<td>创建日期</td>
</tr>
</table>

**使用方式**

    <@directive_picture_list positionKey="rmglt" siteId=7>
        <#list _list_entity_picture as entity>
            title:          ${entity.title!}
            content:        ${entity.content!}
            url:            ${entity.url!}
            orderNum:       ${entity.orderNum!}
        
        </#list>
    </@directive_picture_list>

    结果:
        title:          测试标题1
        content:        测试内容2
        url:            /web7road/upload/picture/14030832787788832.jpg
        orderNum:       1
        
        title:          测试1
        content:        测试内容1
        url:            /web7road/upload/picture/14030832787788831.jpg
        orderNum:       0


### 相册图片标签 (directive_photo)
**传递参数**
<table>
<tr>
	<th>参数</th>
	<th>类型</th>
	<th>必须</th>
	<th>描述</th>
</tr>
<tr>
	<td>id</td>
	<td>integer</td>
	<td>Y</td>
	<td>图片ID</td>
</tr>
</table>

**返回参数**
<table>
<tr>
	<th>参数</th>
	<th>类型</th>
	<th>描述</th>
</tr>
<tr>
	<td>id</td>
	<td>int</td>
	<td>图片ID</td>
</tr>
<tr>
	<td>photoCategoryId</td>
	<td>int</td>
	<td>相册ID</td>
</tr>
<tr>
	<td>photoTitle</td>
	<td>string</td>
	<td>图片标题</td>
</tr>
<tr>
	<td>photoImageUrl</td>
	<td>string</td>
	<td>图片地址</td>
</tr>
<tr>
	<td>photoThumbnailUrl</td>
	<td>string</td>
	<td>缩略图地址</td>
</tr>
<tr>
	<td>createTime</td>
	<td>DateTime</td>
	<td>创建时间</td>
</tr>
</table>

**使用方式**

    <@directive_photo id=1>
        id:                     ${_entity_photo.id!}
        photoCategoryId:        ${_entity_photo.photoCategoryId!}
        photoTitle:             ${_entity_photo.photoTitle!}
        photoImageUrl:          ${_entity_photo.photoImageUrl!}
        photoThumbnailUrl:      ${_entity_photo.photoThumbnailUrl!}
        createTime:             ${_entity_photo.createTime!}
    </@directive_photo>

    结果:
    id:                     1
    photoCategoryId:        1
    photoTitle:             弹弹堂截图
    photoImageUrl:          /web7road/upload/photo/14031605931460651.gif
    photoThumbnailUrl:      /web7road/upload/photo/14031605931460651.gif
    createTime:             2014-6-23 15:23:30


### 相册图片列表标签 (directive_photo_list)
**传递参数**
<table>
<tr>
	<th>参数</th>
	<th>类型</th>
	<th>必须</th>
	<th>描述</th>
</tr>
<tr>
	<td>photoCategoryId</td>
	<td>integer</td>
	<td>Y</td>
	<td>相册ID</td>
</tr>
<tr>
    <td>size</td>
    <td>integer</td>
    <td>N</td>
    <td>显示记录条数</td>
</tr>
</table>

**返回参数**  
参考相册图片标签(directive_photo)

**使用方式**

    <@directive_photo_list photoCategoryId=3>
        <#list _list_entity_photo as entity>
            id:                     ${entity.id!}
            photoCategoryId:        ${entity.photoCategoryId!}
            photoTitle:             ${entity.photoTitle!}
            photoImageUrl:          ${entity.photoImageUrl!}
            photoThumbnailUrl:      ${entity.photoThumbnailUrl!}
            createTime:             ${entity.createTime!}
        
        </#list>
    </@directive_photo_list>

    结果:
        id:                     2
        photoCategoryId:        3
        photoTitle:             弹弹堂1
        photoImageUrl:          /web7road/upload/photo/14035094346715399.jpg
        photoThumbnailUrl:      
        createTime:             2014-6-23 15:44:05
        
        id:                     3
        photoCategoryId:        3
        photoTitle:             弹弹堂2
        photoImageUrl:          /web7road/upload/photo/14035103051374744.jpg
        photoThumbnailUrl:      
        createTime:             2014-6-23 15:58:37
    ...

### 相册标签 (directive_photo_category)
**传递参数**
<table>
<tr>
	<th>参数</th>
	<th>类型</th>
	<th>必须</th>
	<th>描述</th>
</tr>
<tr>
	<td>id</td>
	<td>integer</td>
	<td>Y</td>
	<td>相册ID</td>
</tr>
</table>

**返回参数**
<table>
<tr>
	<th>参数</th>
	<th>类型</th>
	<th>描述</th>
</tr>
<tr>
	<td>id</td>
	<td>int</td>
	<td>ID</td>
</tr>
<tr>
	<td>photoCategoryName</td>
	<td>string</td>
	<td>相册名称</td>
</tr>
<tr>
	<td>photoCategoryCoverImageUrl</td>
	<td>string</td>
	<td>相册封面图片</td>
</tr>
<tr>
	<td>createTime</td>
	<td>DateTime</td>
	<td>创建时间</td>
</tr>
</table>

**使用方式**

    <@directive_photo_category id=1>
        id:                         ${_entity_photo_category.id!}
        siteId:                     ${_entity_photo_category.siteId!}
        photoCategoryName:          ${_entity_photo_category.photoCategoryName!}
        photoCategoryCoverImageUrl: ${_entity_photo_category.photoCategoryCoverImageUrl!}
        createTime:                 ${_entity_photo_category.createTime!}
    </@directive_photo_category>

    结果:
    id:                         1
    siteId:                     7
    photoCategoryName:          游戏截图
    photoCategoryCoverImageUrl: /web7road/upload/photo/14031605931460651.gif
    createTime:                 2014-6-19 12:30:40


### 相册列表标签 (directive_photo_category_list)
**传递参数**
<table>
<tr>
	<th>参数</th>
	<th>类型</th>
	<th>必须</th>
	<th>描述</th>
</tr>
<tr>
	<td>siteId</td>
	<td>integer</td>
	<td>Y</td>
	<td>站点</td>
</tr>
<tr>
    <td>size</td>
    <td>integer</td>
    <td>N</td>
    <td>显示记录条数</td>
</tr>
</table>

**返回参数**
<table>
<tr>
	<th>参数</th>
	<th>类型</th>
	<th>描述</th>
</tr>
<tr>
	<td>id</td>
	<td>int</td>
	<td>ID</td>
</tr>
<tr>
	<td>photoCategoryName</td>
	<td>string</td>
	<td>相册名称</td>
</tr>
<tr>
	<td>photoCategoryCoverImageUrl</td>
	<td>string</td>
	<td>相册封面图片</td>
</tr>
<tr>
	<td>createTime</td>
	<td>DateTime</td>
	<td>创建时间</td>
</tr>
</table>

**使用方式**

    <@directive_photo_category_list siteId=7>
        <#list _list_entity_photo_category as entity>
            id:                         ${entity.id!}
            siteId:                     ${entity.siteId!}
            photoCategoryName:          ${entity.photoCategoryName!}
            photoCategoryCoverImageUrl: ${entity.photoCategoryCoverImageUrl!}
            createTime:                 ${entity.createTime!}
            
        </#list>
    </@directive_photo_category_list>

    结果:
        id:                         5
        siteId:                     7
        photoCategoryName:          游戏漫画
        photoCategoryCoverImageUrl: 
        createTime:                 2014-6-23 14:22:33
    ...

### 视频标签 (directive_video)
**传递参数**
<table>
<tr>
	<th>参数</th>
	<th>类型</th>
	<th>必须</th>
	<th>描述</th>
</tr>
<tr>
	<td>id</td>
	<td>integer</td>
	<td>Y</td>
	<td>编号</td>
</tr>
</table>

**返回参数**
<table>
<tr>
	<th>参数</th>
	<th>类型</th>
	<th>描述</th>
</tr>
<tr>
	<td>id</td>
	<td>int</td>
	<td>ID</td>
</tr>
<tr>
	<td>siteId</td>
	<td>int</td>
	<td>所属站点id</td>
</tr>
<tr>
	<td>stateId</td>
	<td>int</td>
	<td>视频状态ID, 参考当前数据字典</td>
</tr>
<tr>
	<td>videoTitle</td>
	<td>string</td>
	<td>视频标题</td>
</tr>
<tr>
	<td>videoUrl</td>
	<td>string</td>
	<td>视频链接地址</td>
</tr>
<tr>
	<td>videoImg</td>
	<td>string</td>
	<td>视频的缩略图</td>
</tr>
<tr>
	<td>enableFlag</td>
	<td>int</td>
	<td>是否可用，1，标识可用，0标识不可用</td>
</tr>
<tr>
	<td>createDate</td>
	<td>DateTime</td>
	<td>创建时间</td>
</tr>
<tr>
	<td>updateDate</td>
	<td>DateTime</td>
	<td>更新时间</td>
</tr>
</table>

**使用方式**

    <@directive_video id=1>
    ...省略
    </@directive_video>

    结果:
    ...省略

### 视频列表标签 (directive_video_list)
**传递参数**
<table>
<tr>
	<th>参数</th>
	<th>类型</th>
	<th>必须</th>
	<th>描述</th>
</tr>
<tr>
	<td>siteId</td>
	<td>integer</td>
	<td>Y</td>
	<td>所属站点id</td>
</tr>
<tr>
    <td>size</td>
    <td>integer</td>
    <td>N</td>
    <td>显示记录条数</td>
</tr>
</table>

**返回参数**
<table>
<tr>
	<th>参数</th>
	<th>类型</th>
	<th>描述</th>
</tr>
<tr>
	<td>id</td>
	<td>int</td>
	<td>ID</td>
</tr>
<tr>
	<td>siteId</td>
	<td>int</td>
	<td>所属站点id</td>
</tr>
<tr>
	<td>stateId</td>
	<td>int</td>
	<td>视频状态ID, 参考当前数据字典</td>
</tr>
<tr>
	<td>videoTitle</td>
	<td>string</td>
	<td>视频标题</td>
</tr>
<tr>
	<td>videoUrl</td>
	<td>string</td>
	<td>视频链接地址</td>
</tr>
<tr>
	<td>videoImg</td>
	<td>string</td>
	<td>视频的缩略图</td>
</tr>
<tr>
	<td>enableFlag</td>
	<td>int</td>
	<td>是否可用，1，标识可用，0标识不可用</td>
</tr>
<tr>
	<td>createDate</td>
	<td>DateTime</td>
	<td>创建时间</td>
</tr>
<tr>
	<td>updateDate</td>
	<td>DateTime</td>
	<td>更新时间</td>
</tr>
</table>

**使用方式**

    <@directive_video_list siteId=1>
    ...省略
    </@directive_video_list>

    结果:
    ...省略

### 活动标签 (directive_activity)
**传递参数**
<table>
<tr>
	<th>参数</th>
	<th>类型</th>
	<th>必须</th>
	<th>描述</th>
</tr>
<tr>
	<td>id</td>
	<td>integer</td>
	<td>Y</td>
	<td>编号</td>
</tr>
</table>

**返回参数**
<table>
<tr>
	<th>参数</th>
	<th>类型</th>
	<th>描述</th>
</tr>
<tr>
	<td>id</td>
	<td>int</td>
	<td>ID</td>
</tr>
<tr>
	<td>siteId</td>
	<td>int</td>
	<td>所属站点id</td>
</tr>
<tr>
	<td>activityTitle</td>
	<td>string</td>
	<td>活动的标题</td>
</tr>
<tr>
	<td>activitySummary</td>
	<td>string</td>
	<td>活动的简介</td>
</tr>
<tr>
	<td>activityUrl</td>
	<td>string</td>
	<td>活动的链接url</td>
</tr>
<tr>
	<td>activityImg</td>
	<td>string</td>
	<td>活动的图片</td>
</tr>
<tr>
	<td>hidden</td>
	<td>int</td>
	<td>是否隐藏，1标识隐藏 0 标识显示</td>
</tr>
<tr>
	<td>startDate</td>
	<td>DateTime</td>
	<td>活动的开始日期</td>
</tr>
<tr>
	<td>endDate</td>
	<td>DateTime</td>
	<td>活动的结束日期</td>
</tr>
<tr>
	<td>createDate</td>
	<td>DateTime</td>
	<td>创建时间</td>
</tr>
</table>

**使用方式**

    <@directive_activity id=1>
    ...省略
    </@directive_activity>

    结果:
    ...省略


### 活动列表标签 (directive_activity_list)
**传递参数**
<table>
<tr>
	<th>参数</th>
	<th>类型</th>
	<th>必须</th>
	<th>描述</th>
</tr>
<tr>
	<td>siteId</td>
	<td>integer</td>
	<td>Y</td>
	<td>所属站点id</td>
</tr>
<tr>
    <td>size</td>
    <td>integer</td>
    <td>N</td>
    <td>显示记录条数</td>
</tr>
</table>

**返回参数**
<table>
<tr>
	<th>参数</th>
	<th>类型</th>
	<th>描述</th>
</tr>
<tr>
	<td>id</td>
	<td>int</td>
	<td>ID</td>
</tr>
<tr>
	<td>siteId</td>
	<td>int</td>
	<td>所属站点id</td>
</tr>
<tr>
	<td>activityTitle</td>
	<td>string</td>
	<td>活动的标题</td>
</tr>
<tr>
	<td>activitySummary</td>
	<td>string</td>
	<td>活动的简介</td>
</tr>
<tr>
	<td>activityUrl</td>
	<td>string</td>
	<td>活动的链接url</td>
</tr>
<tr>
	<td>activityImg</td>
	<td>string</td>
	<td>活动的图片</td>
</tr>
<tr>
	<td>hidden</td>
	<td>int</td>
	<td>是否隐藏，1标识隐藏 0 标识显示</td>
</tr>
<tr>
	<td>startDate</td>
	<td>DateTime</td>
	<td>活动的开始日期</td>
</tr>
<tr>
	<td>endDate</td>
	<td>DateTime</td>
	<td>活动的结束日期</td>
</tr>
<tr>
	<td>createDate</td>
	<td>DateTime</td>
	<td>创建时间</td>
</tr>
</table>

**使用方式**

    <@directive_activity_list siteId=1>
    ...省略
    </@directive_activity_list>

    结果:
    ...省略


## 常用函数
### 获取日期字符串函数(${fn_date()})
    ${fn_date('L')} result=yyyyMMddhhmmss  
    ${fn_date('S')} result=yyyyMMdd  
    ${fn_date()} result=时间戳  

	结果:
    ${fn_date()} = 1402279570991
    ${fn_date('L')} = 20140609100610
    ${fn_date('S')} = 20140609

### 截取固定长度字符串并替换多余字符(${fn_replace(input, len, replacement)})
**用法:**  
${fn_replace(input, len)}    
${fn_replace(input, len, replacement)}

**传递参数**
<table>
<tr>
	<th>参数</th>
	<th>类型</th>
	<th>必须</th>
	<th>描述</th>
</tr>
<tr>
	<td>input</td>
	<td>string</td>
	<td>Y</td>
	<td>输入字符串</td>
</tr>
<tr>
	<td>input</td>
	<td>int</td>
	<td>Y</td>
	<td>显示多少字符，超过替换 [replacement]</td>
</tr>
<tr>
	<td>replacement</td>
	<td>string</td>
	<td>N</td>
	<td>替换字符串</td>
</tr>
</table>

**使用方式**

    例子:
    ${fn_replace("1234567890", 5)}
    ${fn_replace("1234567890", 5, "...more")}

    结果:
    12345...
    12345...more

## 全局常用共享变量
包括网站常用可以配置数据和经常需要变动的数据 

    ${globals["key"]!}

    ${globals["sys.title"]!}
    ${globals["sys.host"]!}
    ${globals["sys.keywords"]!}

    结果:
    第七大道官网
    http://www.7road.com
    神曲、弹弹堂

    ...
    其他常用变量请参考网站后台常量配置
待续...