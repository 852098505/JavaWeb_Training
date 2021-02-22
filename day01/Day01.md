# Day01



## c/s---客户端/服务器

#### 					优点：页面精美、占网络带宽不多，对于网络要求不高

#### 					缺点：当游戏需要更新补丁，期间不能操作客户端



### B/S---浏览器/服务器

#### 					优点：不需要下载很大的客户端，更新不需要下载补丁，可以直接使用

#### 					缺点：画面不精美，对网络要求比较高





#### HTML语法组成结构

​			1.第一部分HTML的声明

​			2.HTML组成内容

注意：a.标签是由起始标签和结束标签构成------<body></body>

​			b.如果没有标签内容可以使用自闭标签----<br/>

​			c.不区分大小写

​			d.当页面中出现多个空格，制表符空格以及换行在页面展示中都是一个空格

​			e.转义字符

​				

| 空格 | &nbsp  |
| ---- | ------ |
| ""   | &qout; |
| ''   | &apos  |
| &    | &amp   |
| <    | &It    |
| >    | &gt    |

​			f.标签的身上可以通过 属性名="属性值"这种形式来给标签设置属性，在页面当中可以展示标签特点（单引号和双引号都一刻设置属性值）

​			g.标签注释 	<!---->





#### HTML常用标签

##### 		1.字体标签（字体颜色，大小)

```java
<font>fewafdsa</font>
```



| color | 字体颜色 |
| ----- | -------- |
| size  | 字体大小 |

##### 		2.标题标签（标题级别，位置）

```java
<h1>fewafdsa</h1>
```

##### 	

| H1-h6 | 标题级别                                 |
| ----- | ---------------------------------------- |
| align | 对齐方式（left，right，center，justify） |

##### 		3.列表标签（图标方式）	

```java
<ul>
			<li></li>
  		<li></li>
  		<li></li>
</ul>
```

| type   | 图标形式   |
| ------ | ---------- |
| disc   | 实心圆     |
| square | 实心正方形 |
| circle | 空心圆     |

##### 		4.图片标签（高度，宽度，路径，描述）

```java
<img src="path" alt="描述" width="" height=""/>
```

| src    | 图片所在位置                           |
| ------ | -------------------------------------- |
| alt    | 当图片不加载的时候，代替图片出现的文字 |
| width  | 图片宽度                               |
| height | 图片高度                               |

##### 5.a标签（）

```java
<a href="跳转目标地址" target="打开窗口形式" >123124132</a>
```

| target | _self(在当前窗口打开新页面) / _target(在新窗口打开新的页面) |
| ------ | ----------------------------------------------------------- |
| href   | 跳转地址                                                    |

注意⚠️：也可以在本页面进行跳转，比如返回顶层

```java
<a name="tag"/>
  <p>aewfawe</p>
  <p>aewfawe</p>
  <p>aewfawe</p>
  <p>aewfawe</p>
  <p>aewfawe</p>
  <p>aewfawe</p>
  <p>aewfawe</p>
  <p>aewfawe</p>
<a href="#tag">返回顶层</a>
```

其中a标签中可以给name属性以及id属性，第二步给定超链接来跳转到设置书签

#是指在当前页面找这个名称

#####  		6.表格标签

| <table> | 表格标签       |
| ------- | -------------- |
| <tr>    | 表格中的行标签 |
| <td>    | 行中单元格标签 |
| <th>    | 行中标题单元格 |

| Table的重要属性 | 介绍                       |
| --------------- | -------------------------- |
| border          | 边框宽度                   |
| cellspacing     | 单元格之间的空白间距       |
| cellpadding     | 边框与单元格内容之间的距离 |
| bgcolor         | 背景颜色                   |
| bordercolor     | 边框颜色                   |
| width           | 宽度                       |
| align           | 对齐方式                   |

| tr的重要属性 | 介绍     |
| ------------ | -------- |
| align        | 对齐方式 |
| bgcolor      | 背景颜色 |

| th/td的总要属性 | 介绍         |
| --------------- | ------------ |
| align           | 对齐方式     |
| bgcolor         | 背景颜色     |
| width           | 宽度         |
| height          | 高度         |
| colspan         | 可横跨的列数 |
| rowspan         | 可竖跨的行数 |

注意⚠️：caption可以定义表格的标题，若想更改标题的颜色，可以通过内嵌font的标签来进行更改

##### 		7.表单标签（提交数据到服务器的方式）

###### 				a.在地址栏来拼接参数值发送给服务器。如果要发送的参数比较多切复杂，在拼接的时候导致拼接效率低下，，交互的效率也会降低

###### 				b.可以把要发送给服务器的参数值放到表单里通过表单进行集中拼接，发送给服务器，效率高

```java
<from action="指定数据发送地址" method="提交方式get/post">
  姓名:<input type="text" name="username"/><br/>
  密码:<input type="password" name="password"/><br/>
  性别:<input type="radio" name="gender" value="male">男 <input type="radio" name="gender" value="female">女
</from>
```

| input标签type属性 | 介绍       |
| ----------------- | ---------- |
| text              | 文本输入框 |
| password          | 密码输入框 |
| radio             | 单选框     |
| checkbox          | 复选框     |
| file              | 上传文件框 |
| button            | 按钮       |
| submit            | 提交按钮   |
| reset             | 重制按钮   |
| hidden            | 隐藏框     |
| image             | 图片提交框 |

注意⚠️：提交button是把所有的name属性的值进行提交，若没有这个属性值将不会提交，value是提交给服务器的参数值，readonly只读无法编辑，disable不可用

​		get提交方式：

​					在地址栏拼接参数

​					不安全

​					地址栏拼接参数值大小一般不要大于1kb

​		post提交方式：

​					在底层来提交数据

​					相对安全

​					底层根据流来传输数据，不限制传输数据大小



##### 8.下拉框

```
<select name=“city” >
	<option value="bj">北京</option>
	<option value="cq">重庆</option>
	<option value="dl" selected="selected">大理</option>
</select>
```

注意：selected是指默认选择



##### 9.文本域

```java
<textarea rows="" col="">这是一个文本</textarea>
```

文本域是通过行数和列数进行改变大小