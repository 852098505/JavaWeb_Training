## JDBC概述

### 		数据库驱动

#### 					数据库驱动的概念

​									数据库厂商提供的用来操作数据库的jar包就叫做数据库的驱动。开发人员只需要倒入数据库驱动包就可以通过该驱动个提供的api来操作数据库

​									不同的数据库的驱动个包互不兼容，需要操作什么数据库就要倒入该数据库的驱动包

### 		

### 		JDBC

#### 					JDBC的概念

​									JDBC（Java DataBase Connectivity）就是Java数据库连接，说白了就是用Java语言来操作数据库。

由于不同的数据库厂商提供的数据库驱动各不相同,在使用不同数据库时需要学习对应数据库驱动的api，对于开发人员来说学习成本十分的高。

于是sun提供了JDBC的规范,本质上一大堆的接口,要求不同的数据库厂商提供的驱动都实现这套接口,这样以来开发人员只需要学会JDBC这套接口,所有的数据库驱动作为这套接口的实现，就都会使用了。

#### 					JDBC包

​									JDBC主要是由 java.sql 和javax.sql包组成的,并且这两个包已经被集成到J2SE的规范中了,这意味着,只要一个普通的java程序就可以使用JDBC。

要注意的是,在开发数据库程序时,除了如上的两个包,还需要手动的导入具体的数据库驱动。

#### 					JDBC详解

##### 			1.准备

```mysql
create database mydb4;

use mydb4;

create table user(
	id int primary key auto_increment,
	name varchar(20),
	age int,
	addr varchar(20)
);

insert into user values (null,'aaa',19,'bj');
insert into user values (null,'bbb',20,'sh');
insert into user values (null,'ccc',32,'gz');
insert into user values (null,'ddd',24,'sz');
insert into user values (null,'eee',26,'tl');

```

##### 			2.导入数据库驱动包

​		lib下拖入连接包，右键加入project工程lib

##### 			3.代码实现

```java
import com.mysql.cj.jdbc.Driver;

import java.sql.*;

public class CRDU.Test01 {

    public static void main(String[] args) throws SQLException {
        //注册数据库驱动
        DriverManager.registerDriver(new Driver());
        //获取数据库连接
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb4","root","12345678");
        //获取传输器
        Statement stat = conn.createStatement();
        //执行sql获取结果集
        ResultSet rs  = stat.executeQuery("select * from user");
        //遍历结果集
        while (rs.next()){
            String name = rs.getString("name");
            int age = rs.getInt("age");
            System.out.println(name + "   " + age);
        }
        rs.close();
        stat.close();
        conn.close();
    }

}
```

##### 			4.JDBC API详解

##### 					注册数据库驱动：

​							使用DriverManager.registerDriver(new Driver());注册数据库有两个缺点，首先，通过观察mysql的中Driver接口的实现类发现在静态代码块中注册驱动的逻辑，所以这种方式会造成驱动被注册两次。另外，这种方式导致了程序和具体的数据库驱动绑死在了一起，程序的灵活性比较低。

```
所以推荐使用：
Class.forName(“com.mysql.jdbc.Driver”);
的方式注册数据库驱动。
```

获取数据库连接

Connection conn = DriverManager.getConnection(url,name,psw);

##### 					数据库URL

```
URL用于标识数据库的位置，程序员通过URL地址告诉JDBC程序连接哪个数据库，URL的写法为：
   jdbc:mysql://localhost:3306/test ?参数名=参数值

Mysql的url地址的简写形式： jdbc:mysql:///sid
注意⚠️：mysql的简写形式来源
					1.如果不指定主机名默认连接localhost
					2.如果不指定端口号默认连接3306
```

| 数据库URL地址 | 写法                                                       |      |
| ------------- | ---------------------------------------------------------- | ---- |
| Oracle写法    | jdbc:oracle:thin:@localhost:1521:sid                       |      |
| SqlServer写法 | jdbc:microsoft:sqlserver://localhost:1433;DatabaseName=sid |      |
| MySql         | jdbc:mysql://localhost:3306/sid                            |      |

##### 						Connection创建传输器

```
Jdbc程序中的Connection，它用于代表数据库的链接，Connection是数据库编程中最重要的一个对象，客户端与数据库所有交互都是通过connection对象完成的，这个对象的常用方法：
createStatement()：创建向数据库发送sql的statement对象。
prepareStatement(sql):创建向数据库发送预编译sql的PreparedSatement对象。
prepareCall(sql)：创建执行存储过程的callableStatement对象。 
setAutoCommit(boolean autoCommit)：设置事务是否自动提交。 
commit()：在链接上提交事务。
rollback()：在此链接上回滚事务。
```

| 常用方法                          | 解释                                            |
| :-------------------------------- | ----------------------------------------------- |
| createStatement()                 | 创建向数据库发送sql的statement对象              |
| prepareStatement(sql)             | 创建向数据库发送预编译sql的PreparedSatement对象 |
| prepareCall(sql)                  | 创建执行存储过程的callableStatement对象         |
| setAutoCommit(boolean autoCommit) | 设置事务是否自动提交                            |
| commit()                          | 在链接上提交事务                                |
| rollback()                        | 在此链接上回滚事务                              |

##### 						Statement对象

Jdbc程序中的Statement对象用于向数据库发送SQL语句

| executeQuery(String sql)  | 用于向数据库发送查询语句                   |
| ------------------------- | ------------------------------------------ |
| executeUpdate(String sql) | 用于向数据库发送insert、update或delete语句 |
| execute(String sql)       | 用于向数据库发送任意sql语句                |
| addBatch(String sql)      | 把多条sql语句放到一个批处理中              |
| executeBatch()            | 向数据库发送一批sql语句执行                |

##### 						ResultSet

Jdbc程序中的ResultSet用于代表Sql语句的执行结果。Resultset封装执行结果时，采用的类似于表格的方式。ResultSet 对象维护了一个指向表格数据行的游标，初始的时候，游标在第一行之前，调用ResultSet.next() 方法，可以使游标指向具体的数据行，进行调用方法获取该行的数据。

```
ResultSet既然用于封装执行结果的，所以该对象提供的都是用于获取数据的get方法：
获取任意类型的数据
getObject(int index)
getObject(string columnName)
获取指定类型的数据，例如：
getString(int index)
getString(String columnName)
getInt(columnIndex)
getInt(columnLabel)
getDouble(columnIndex)
getDouble(columnLabel)
...
操作游标的方法,例如:
next()：移动到下一行
Previous()：移动到前一行
absolute(int row)：移动到指定行
beforeFirst()：移动resultSet的最前面。
afterLast() ：移动到resultSet的最后面。
...
```

##### 						释放资源

```java
Connection conn = null;
        Statement stat = null;
        ResultSet rs = null;

        try {
            //注册数据库驱动
            //DriverManager.registerDriver(new Driver());//注意⚠️：仔细查看Dirver()方法，会发现静态代码块中也会注册一遍，返回的对象再一次放回注册管理器中，二次注册
            Class.forName("com.mysql.cj.jdbc.Driver");//反射机制，去内存找有没有这个，有就返回类名，有且仅有一次加载
            //获取数据库连接
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb4", "root", "12345678");
            //获取传输器
            stat = conn.createStatement();
            //执行sql获取结果集
            rs = stat.executeQuery("select * from user");
            //遍历结果集
            while (rs.next()) {
                String name = rs.getString("name");
                int age = rs.getInt("age");
                System.out.println(name + "   " + age);
            }
        } catch (Exception e) {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }finally {
                    rs = null;
                }
            }
            if (stat != null) {
                try {
                    stat.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }finally {
                    stat = null;
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }finally {
                    conn = null;
                }
            }
        }
```

##### Sql注入攻击的原理

```
由于jdbc程序在执行的过程中sql语句在拼装时使用了由页面传入参数，如果用户,恶意传入一些sql中的特殊关键字，会导致sql语句意义发生变化，这种攻击方式就叫做sql注入。
如何防止SQL注入攻击呢？
这时候就需要用到PreparedStatement对象。
```

##### 						JDBC预编译

###### 			PreparedStatement对象

```
PreparedStatement是Statement的孩子，不同的是，PreparedStatement使用预编译机制，在创建PreparedStatement对象时就需要将sql语句传入，传入的过程中参数要用?替代，这个过程回导致传入的sql被进行预编译，然后再调用PreparedStatement的setXXX将参数设置上去，由于sql语句已经经过了预编译，再传入特殊值也不会起作用了。
PreparedStatement使用了预编译机制，sql语句在执行的过程中效率比Statement要高。
PreparedStatement使用了 “？”通配符省去了字符串的拼接，使代码更加优雅。
```

###### 			PreparedStatement防止sql注入

```java
public static void main(String[] args) {
        //1.读取输入的用户名和密码
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入用户名：");
        String username = scanner.nextLine();
        System.out.println("请输入密码：");
        String password = scanner.nextLine();

        //2.查询数据库中的正确的用户名和密码
        Connection conn = null;
        java.sql.PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql:///mydb4","root","12345678");
            //使用PrepareStatement预编译功能防止传入的参数影响sql语句
            ps = conn.prepareStatement("select * from user2 where username =  ? and password = ?");
            ps.setString(1,username);
            ps.setString(2,password);
            rs = ps.executeQuery();
            if (rs.next()){
                System.out.println("用户名密码正确！");
            }else {
                System.out.println("用户名密码错误！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(rs !=null){
                try {
                    rs.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }finally {
                    rs = null;
                }
            }
            if(ps !=null){
                try {
                    ps.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }finally {
                    ps = null;
                }
            }
            if(conn !=null){
                try {
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }finally {
                    conn = null;
                }
            }
        }


    }
```

```
    相对于Statement，PreparedStatement的优点如下
        1。可以防止sql注入攻击
        2。由预编译机制，效率更高
        3。减少sql的拼接，是代码更加优雅
```

##### 批处理

###### 		批处理业务场景

当需要向数据库发送一批SQL语句执行时，应避免向数据库一条条的发送执行，而应采用JDBC的批处理机制，以提升执行效率。

##### 		Statement批处理

```java
Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql:///mydb4", "root", "12345678");
            state = conn.createStatement();
            state.addBatch("create database mydb5");
            state.addBatch("use mydb5");
            state.addBatch("create table user (id int,name varchar(20))");
            state.addBatch("insert into user values (1,'aaa')");
            state.addBatch("insert into user values (2,'bbb')");
            state.addBatch("insert into user values (3,'ccc')");

            int[] result = state.executeBatch();
            for (int i : result) {
                System.out.println(i);
            }
```

注意⚠️：优点：可以向数据库发送多条不同的SQL语句

​				 缺点：SQL语句没有预编译

##### PreparedStatement批处理

```java
Class.forName("com.mysql.cj.jdbc.Driver");
conn = DriverManager.getConnection("jdbc:mysql:///mydb5", "root", "12345678");
ps = conn.prepareStatement("insert into user values (?,?)");
for (int i = 1; i < 1000; i++) {
    ps.setInt(1, i);
    ps.setString(2, "w" + i);
    ps.addBatch();
}
ps.executeBatch();
```

注意⚠️：优点：发送的是预编译后的SQL语句，执行效率高

​				 缺点：只能应用在SQL语句相同，但参数不同的批处理中。因此此种形式的批处理经常用于同一个表中批量插入数据，或批量更新表的数据

#### 连接池

##### 		连接池概述

```
用户每次请求都需要向数据库获得链接，而数据库创建连接通常需要消耗相对较大的资源，创建时间也较长。假设网站一天10万访问量，数据库服务器就需要创建10万次连接，极大的浪费数据库的资源，并且极易造成数据库服务器内存溢出、宕机。

频繁的开关连接相当的耗费资源,所以我们可以设置一个连接池,在程序启动时就初始化一批连接,在程序中共享,需要连接时从池中获取,用完再还回池中,通过池共享连接,减少开关连接的次数,提高程序的效率。
```

##### 		手写连接池

```java
Sun公司为连接池提供 javax.sql.DataSource接口,要求连接池去实现,所以连接池也叫数据源。
我们可以自己实现这个接口来实现一个连接池。
package com.tarena;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.sql.DataSource;


public class MyPool implements DataSource {
	private static List<Connection> pool = new LinkedList<Connection>();
	static{
		try{
			Class.forName("com.mysql.jdbc.Driver");
			for(int i=0;i<5;i++){
				Connection conn = DriverManager.getConnection("jdbc:mysql:///day11","root","root");
				pool.add(conn);
			}
		}catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public Connection getConnection() throws SQLException {
		if(pool.size()==0){
			for(int i=0;i<3;i++){
				Connection conn = DriverManager.getConnection("jdbc:mysql:///day11","root","root");
				pool.add(conn);
			}
		}
		System.out.println("获取了一个连接,池里还剩余"+pool.size()+"个连接");
		return pool.remove(0);
	}

	
	private void retConn(Connection conn){
		try {
			if(conn!=null && !conn.isClosed()){
				pool.add(conn);
				System.out.println("还回了一个连接,池里还剩余"+pool.size()+"个连接");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Connection getConnection(String username, String password)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public PrintWriter getLogWriter() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public int getLoginTimeout() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setLogWriter(PrintWriter out) throws SQLException {
		// TODO Auto-generated method stub

	}

	public void setLoginTimeout(int seconds) throws SQLException {
		// TODO Auto-generated method stub

	}

	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	public <T> T unwrap(Class<T> iface) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
如上代码写的连接池，还需要在使用完连接后记得不能关闭连接，而是要调用retConn方法将连接还回池中。
我们想能不能想办法改造conn的close方法，使close方法不会真的关闭连接而是将连接还回池中。
```

##### 		C3P0

###### 			C3P0概述

​					我们手写的连接池是比较简陋的，是为了讲解连接池的原理。其实在真实开发中可以使用开源的数据库连接池。其中C3P0是比较常用的一种。

###### 			使用C3P0

```java
(1)导入jar包
(2)写配置文件,放置到类加载目录下c3p0-config.xml

<?xml version="1.0"?>
<c3p0-config>
	<default-config>
		<property name="driverClass">com.mysql.jdbc.Driver</property>
		<property name="jdbcUrl">jdbc:mysql:///mydb1</property>
		<property name="user">root</property>
		<property name="password">root</property>
	</default-config>
</c3p0-config>

(3)程序中获取连接池对象
ComboPooledDataSource pool = new ComboPooledDataSource();
```

