# Day02



#### CSS	层叠样式表

##### 		页面中可能有大量元素（标签）需要去设置相同央视，每个元素身上单独设置样式，造成大量代码有冗余，降低开发效率，为了解决这个问题在页面中批量设置样式--CSS

#### div+css

##### 		div	盒子模型

​					在使用 table标签来布局页面时比较复杂，且不灵活，引入盒子模型可以进行灵活布局，满足页面的多样需求

| <div>  | 块级元素，一个元素占一行   |
| ------ | -------------------------- |
| <p>    | 块级元素，一个元素占一行   |
| <span> | 行内元素，多个元素同在一行 |

##### 		css	

```java
<!--第一种引入方式只对当前标签生效-->
    <div style="color: blue;background-color: red">一个div</div>
```

```java
<!--第二种引入方式在head标签里设置style标签-->
		<style type="text/css">

        div{
            color: aqua;
            background-color: blueviolet;
        }

    </style>
```

```java
<!--第三种引入方式，引入外部css文件-->
<head>
    <meta charset="UTF-8">
    <title>div+css</title>

    <link rel="stylesheet" href="1.css"/>
</head>
```

```java
<!--第四种引入方式，import引入外部css文件-->
<head>
    <meta charset="UTF-8">
    <title>div+css</title>

    <style>
        @import "1.css";
    </style>
</head>
```

注意⚠️：1.距离标签越近的css代码，越优先生效

#### css选择器

##### 		基本选择器和扩展选择器

###### 				基本选择器：

​						id选择器

```java
        /*id选择器*/
        #div1{
            color: blueviolet;
            background-color: blue;
        }
```

​						类选择器

```java
        /*类选择器*/
        .div2{
            color: blueviolet;
            background-color: blue;
        }
```

​						元素选择器

```java
        /*元素选择器*/
        div{
            color: darkgreen;
            background-color: red;
        }
```

注意⚠️：1.距离标签越近的css代码，越优先生效

​				 2.css样式越具体越优先生效



##### 		后代选择器

###### 				后代元素选择器

```java
    <!--后代选择器-->    
		<style>
        div span{
            color: yellow;
            background-color: blueviolet;
        }
    </style>
      选择div中所有的元素名span（子级，孙子级，更低级别）
```

###### 				子元素选择器

```java
    <!--子元素选择器-->
		<style>
        div>span{
            color: greenyellow;
            background-color: blueviolet;
        }
    </style>
      只能选择到儿子级别
```

###### 				相邻兄弟选择器

```java
    <!--相邻兄弟选择器-->
    <style>
        div+span{
            color: greenyellow;
            background-color: blueviolet;
        }
    </style>
    只能选择下一个相邻的兄弟元素设置样式
```

###### 				属性选择器

```java
    <!--属性选择器-->
        <style>
            div[name]{
                color: greenyellow;
                background-color: blueviolet;
            }
        </style>
          其中name可以进行赋值，比如name=“##”
```

###### 				分组选择器（多元素选择器）

```java
    <!--多元素选择器-->
            <style>
                #div1,.div2,div+span{
                    color: greenyellow;
                    background-color: blueviolet;
                }
            </style>
            多个选择器用逗号隔开
```

###### 				伪元素选择器

​		其实就在html中预先定义好的一些选择器

| link    | 未点击的状态                         |      |
| ------- | ------------------------------------ | ---- |
| visited | 被电击过的状态                       |      |
| hover   | 鼠标移动到元素之上但是仍未点击的状态 |      |
| active  | 被鼠标点击着的状态                   |      |

```java
    <!--伪元素选择器，只能操作a标签-->
    <style>
        a:link{
            color: greenyellow;
            background-color: greenyellow;
        }

        a:visited{
            color: green;
            background-color: blueviolet;
        }

        a:hover{
            color: greenyellow;
            background-color: yellow;
        }

        a:active{
            color: darkgreen;
            background-color: aqua;
        }

    </style>
```





#### JavaScrip概述 

​				1.是一门基于对象的语言（只要有对象就可以直接操作，不需要考虑继承、多态。。。）

​				2.是一门脚本语言，解释就能执行

​				3.常用于浏览器中，后台也有使用node.js

##### 		引入js

###### 				第一种引入方式（在head标签里引入scrip子标签，在子标签里就可以书写js代码）

```
    <!--第一种引入-->
    <script type="application/javascript">
        /*在弹窗中展示信息*/
        /*alert(123)*/
        console.log(123)
    </script>
```

###### 				第二种引入方式（在head标签里引入scrip子标签，在子标签里用src引入外部js文件）

```
    <!--第二种引入方式-->
    <script type="application/javascript" src="123.js"></script>
    注意⚠️：这种引入方式不能写成自闭标签，会导致引入不成功
```

注意：会内置一个js解析器，会把所有的js代码放到js解析器中进行从上往下的来进行解析，如果发现没有分毫会自动添加上分号，如果有分号就不再添加分号



##### js语法-----定义变量

###### 			var代表弱类型，弱类型其实就是没有类型（可以接受所有类型值）

###### 			js分为局部变量和全局变量，被var修饰的就是局部变量，没有的就是全局变量

​					局部变量只能在当前范围内使用

​					全局变量可以在后续的所有的范围使用，甚至在另一个scrip标签内都能使用



##### js语法----数据类型

​			基本数据类型和复杂数据类型

###### 			基本数据类型：

​					Number数值型、String字符串型、Boolean布尔型、Undefined未定义类型、Null空类型

###### Number数值型

| MAX_VALUE         | 最大值                   |
| ----------------- | ------------------------ |
| MIN_VALUE         | 最小值                   |
| NaN               | 不是一个数（代表非数字） |
| NEGATIVE_INFINITY | 负无穷大                 |
| POSITIVE_INFINITY | 正无穷大                 |

###### String字符串型

详情见手册

###### Boolean布尔类型

​		是js的基本数据类型，也是js的包装类的对象（包含true--1和false--0）

| \|\| | 双或   |
| ---- | ------ |
| &&   | 双与   |
| \|   | 按位或 |
| &    | 按位与 |

###### Undefined未定义类型

​		是包含一个undefined这个值，表示未定义

###### Null空类型

​		只包含一个本身的值就是null

```
        /*Null空类型和Undefined未定义类型*/
        console.log(null==undefined);//true，类型和undefine类型相等
        console.log(null===undefined);//===表示绝对相等，值不相等

```



##### 复杂数据类型

###### 		函数、数组、对象

​		函数就是一段代码的集合，函数本质是一个字符串

###### 		函数的定义方式

​				1.普通函数定义					

```javascript
        function mx(参数列表){
            函数内容
        }
```

​				2.动态函数定义

```
        //动态函数定义
        var mx = new Function("a","b","return a+b");
        console.log(mx(2,3,1));
```

​				3.直接量定义

```
        //直接量定义
        var a = function (a,b){
            return a+b;
        }
        console.log(a(2,6));
```

```
        function eat(food){
            if(food == "煎饼"){
                return "摊"+food;
            }else if (food=="羊肉串"){
                return "烤"+food;
            }
        }

        function chief(cook,food){
            food = cook(food);
            return "吃"+food;
        }

        console.log(chief(eat,"煎饼"));
        注意：此处因为变量属于弱类型，所以也可以接收函数
```

注意⚠️：1.通过函数名调用。

​				2.当调用的函数参数不够时，未被接收到值的时候，默认将其转换为undefined，若参数给的比定义的函数参数列表多，剩下的参数不参与运算

​				3.遍历这个对象就能获取所有传入的参数值，遍历数组

​				4.js变量类型是若类型，可以接收所有类型数据的赋值，函数也是一种数据，变量可以接受函数。如果直接输入变量名展示的就是函数内容，如果输出的是变量名（）就是在调用函数

