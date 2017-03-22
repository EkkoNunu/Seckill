### 前言
&emsp;&emsp; 刚过了“双十一”，相信大家的待收货都是满满哒，在“双十一”期间，相信很多童鞋都参加了产品“秒杀”吧，12点时候在手机或电脑上抢商品抢的不亦乐乎。但是作为一名可爱的程序猿，就在思考这个秒杀是怎么做出来的啦。废话不多说，下面我们就用一个最简单的demo来实现商店购物的秒杀实现。

### 创建项目和依赖

 1.使用maven命令创建项目
 &emsp;&emsp;mvn archetype:generate -DgroupId=org.seckill -DartifactId=seckill -DarchetypeArtifactId=mvn-archetype-webapp -DarchetypeCatalog=local
 
 - -DgroupId：代表组织和整个项目的唯一标识，比如：所有Maven组件的groupId就是org.apache.maven
 - -DartifactId：代表项目的具体名称，它和groupId共同确定一个项目在maven repo中的位置
 - -DarchetypeArtifactId：指的是原型的名字
 - -DarchetypeCatalog：指的是查找的规则

2.创建依赖
&emsp;&emsp; 项目创建成功之后，导入idea，我们会看到一个pom.xml文件，它是maven项目的标志，项目的依赖就要在这里面补全啦，如下：

```
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd">
  <modelVersion>4.0.0</modelVersion>
  <groupId>org.seckill</groupId>
  <artifactId>seckill</artifactId>
  <packaging>war</packaging>
  <version>1.0-SNAPSHOT</version>
  <name>seckill Maven Webapp</name>
  <url>http://maven.apache.org</url>
  <dependencies>
      <!--使用junit4-->

    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>4.12</version>
      <scope>test</scope>
    </dependency>

    <!--补全项目依赖-->
    <!--日志 java日志：slf4g,log4j,logback,common-logging
        slf4g 是规范/接口
        日志实现 log4j,logback,common-logging
        使用slf4j + logback
    -->
    <dependency>
      <groupId>org.slf4g</groupId>
      <artifactId>slf4j-api</artifactId>
      <version>1.7.12</version>
    </dependency>

    <dependency>
      <groupId>ch.gos.logback</groupId>
      <artifactId>logback-core</artifactId>
      <version>1.1.1</version>
    </dependency>
    <!--实现slf4j接口并且整合-->
    <dependency>
      <groupId>ch.gos.logback</groupId>
      <artifactId>logback-classic</artifactId>
      <version>1.1.1</version>
    </dependency>

    <!--数据库相关的依赖-->
    <dependency>
      <groupId>mysql</groupId>
      <artifactId>mysql-connector-java</artifactId>
      <version>5.1.35</version>
      <scope>runtime</scope>
    </dependency>

    <dependency>
      <groupId>c3p0</groupId>
      <artifactId>c3p0</artifactId>
      <version>0.9.1.2</version>
    </dependency>

    <!--DAO框架依赖：mybatis依赖-->
    <dependency>
      <groupId>org.mybatis</groupId>
      <artifactId>mybatis</artifactId>
      <version>3.3.0</version>
    </dependency>

    <!--mybatis自身实现的spring整合依赖-->
    <dependency>
      <groupId>org.mybatis</groupId>
      <artifactId>mybatis-spring</artifactId>
      <version>1.2.3</version>
    </dependency>

    <!--servlet web相关依赖-->
    <dependency>
      <groupId>taglibs</groupId>
      <artifactId>standard</artifactId>
      <version>1.1.2</version>
    </dependency>

    <dependency>
      <groupId>jstl</groupId>
      <artifactId>jstl</artifactId>
      <version>1.2</version>
    </dependency>

    <dependency>
      <groupId>com.fasterxml.jackson.core</groupId>
      <artifactId>jackson-databind</artifactId>
      <version>2.5.4</version>
    </dependency>

    <dependency>
      <groupId>javax.servlet</groupId>
      <artifactId>javax.servlet-api</artifactId>
      <version>3.1.0</version>
    </dependency>

    <!--spring依赖-->
    <!--spring核心依赖-->
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-core</artifactId>
      <version>4.1.7.RELEASE</version>
    </dependency>

    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-beans</artifactId>
      <version>4.1.7.RELEASE</version>
    </dependency>

    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-context</artifactId>
      <version>4.1.7.RELEASE</version>
    </dependency>

    <!--spring DAO层的依赖-->
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-jdbc</artifactId>
      <version>4.1.7.RELEASE</version>
    </dependency>

    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-tx</artifactId>
      <version>4.1.7.RELEASE</version>
    </dependency>

    <!--spring web相关依赖-->
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-web</artifactId>
      <version>4.1.7.RELEASE</version>
    </dependency>

    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-webmvc</artifactId>
      <version>4.1.7.RELEASE</version>
    </dependency>

    <!--spring test相关依赖-->
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-test</artifactId>
      <version>4.1.7.RELEASE</version>
    </dependency>
  </dependencies>
  <build>
    <finalName>seckill</finalName>
  </build>
</project>

```
3.数据库的创建，如下：

```
-- 数据库初始化脚本

-- 创建数据库
CREATE DATABASE seckill;

-- 使用数据库
use seckill;

-- 创建秒杀数据库
-- 重点是出现多个TIMESTAMP的解决
CREATE  TABLE seckill(
`seckill_id` bigint NOT NULL AUTO_INCREMENT COMMENT '商品库存ID',
`name` VARCHAR (120) NOT NULL COMMENT '商品名称',
`number` INT NOT NULL COMMENT '库存数量',
`start_time` TIMESTAMP NOT NULL DEFAULT  '0000-00-00 00:00:00' COMMENT '秒杀时间开始',
`end_time` TIMESTAMP NOT NULL DEFAULT  '0000-00-00 00:00:00' COMMENT '秒杀时间结束',
`create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY (seckill_id),
KEY inx_start_time(start_time),
KEY inx_end_time(end_time),
KEY inx_create_time(create_time)
)ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT = '秒杀库存表';
-- 可以支持事物的引擎只有InooDB

-- 初始化数据
INSERT INTO
  seckill(name, number, start_time, end_time)
VALUES
  ('1000元秒杀iPhone6',100,'2015-11-01 00:00:00','2015-11-02 00:00:00'),
  ('500元秒杀iPad2',200,'2015-11-01 00:00:00','2015-11-02 00:00:00'),
  ('300元秒杀小米4',300,'2015-11-01 00:00:00','2015-11-02 00:00:00'),
  ('200元秒杀红米note',400,'2015-11-01 00:00:00','2015-11-02 00:00:00');


-- 秒杀成功明细表
-- 用户登录认证相关的信息
CREATE TABLE success_killed(
`seckill_id` bigint NOT NULL COMMENT '秒杀商品ID',
`user_phone` bigint NOT NULL COMMENT '用户手机号',
`state` tinyint NOT NULL DEFAULT -1 COMMENT '状态标识：-1：无效 0：成功 1：已付款 2：已发货',
`create_time` TIMESTAMP NOT NULL COMMENT '创建时间',
-- 联合主键
PRIMARY KEY (seckill_id,user_phone),
KEY idx_create_time(create_time)
)ENGINE = InnoDB  DEFAULT charset=utf8 COMMENT = '秒杀成功明细表';

-- 连接数据库控制台
mysql -uroot -p
```

&emsp;&emsp;如上面代码，在表seckill里面，我们在创建时间上用到了三个TIMESTAMP，注意这一点，我在编写建表sql的时候，将start_time和end_time写上了默认值，如果不写上默认值的话，数据库5.5在这一点上会出现问题，首先说当出现两个及以上TIMESTAMP建表会报什么错：

```
Incorrect table definition; there can be only one TIMESTAMP column with CURRENT_TIMESTAMP in DEFAULT or ON UPDATE clause 
```
&emsp;&emsp;这个是mysql5.5的一个经常会遇到的报错信息，这句话的意思是什么呢？是说：不正确的表格定义，只允许一列时间戳列作为当前时间戳的默认或者更新项。在mysql5.5介绍中有这样一段话：

```
 One TIMESTAMP column in a table can have the current timestamp as the default value for initializing the column, as the auto-update value, or both. It is not possible to have the current timestamp be the default value for one column and the auto-update value for another column.
```
&emsp;&emsp;而在mysql5.6.5中则有这样一段话：

```
Previously, at most one TIMESTAMP column per table could be automatically initialized or updated to the current date and time. This restriction has been lifted. Any TIMESTAMP column definition can have any combination of DEFAULT CURRENT_TIMESTAMP and ON UPDATE CURRENT_TIMESTAMP clauses. In addition, these clauses now can be used with DATETIME column definitions. For more information, see Automatic Initialization and Updating for TIMESTAMP and DATETIME.
```
&emsp;&emsp;两句话分别是什么意思呢？很明显，就是移除了以前版本的对值允许一列时间戳作为当前时间戳的更新或者默认项。所以解决办法两个：

 1. 升级mysql的数据库版本
 2. 对时间戳列赋予初始值
 
 ### 编写实体类：seckill和seckillId
 

```
package org.seckill.entity;

import java.util.Date;

/**
 * Created by 荔枝 on 2016-11-11.
 */
public class Seckill {

    private long seckillId;

    private String name;

    private int number;

    private Date startTime;

    private Date endTime;

    private Date createTime;

    public long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


//   为了单元测试方便，覆写其toString()方法
    @Override
    public String toString() {
        return "Seckill{" +
                "seckillId=" + seckillId +
                ", name='" + name + '\'' +
                ", number=" + number +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", createTime=" + createTime +
                '}';
    }
}

```

```
package org.seckill.entity;

import java.util.Date;

/**
 * Created by 荔枝 on 2016-11-11.
 */
public class SuccessKilled {
    private long seckillId;

    private long userPhone;

    private Date create_time;

    private short state;

//    一个秒杀实体是对应多个秒杀成功的用户，是一个多对一，在多方吧一方的Seckill写出来，这是一个复核属性
    private Seckill seckill;

    public long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }

    public long getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(long userPhone) {
        this.userPhone = userPhone;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public short getState() {
        return state;
    }

    public void setState(short state) {
        this.state = state;
    }

    public Seckill getSeckill() {
        return seckill;
    }

    public void setSeckill(Seckill seckill) {
        this.seckill = seckill;
    }

    @Override
    public String toString() {
        return "SuccessKilled{" +
                "seckillId=" + seckillId +
                ", userPhone=" + userPhone +
                ", create_time=" + create_time +
                ", state=" + state +
                '}';
    }
}

```

### DAO接口编码

```
package org.seckill.dao;

import org.apache.ibatis.annotations.Param;
import org.seckill.entity.Seckill;

import java.util.Date;
import java.util.List;

/**
 * Created by 荔枝 on 2016-11-11.
 */
public interface SeckillDao {
    /**
     * 减库存
     * @param seckillId
     * @param killTime
     * @return
     */

    int reduceNumber(@Param("seckillId") long seckillId,@Param("killTime") Date killTime);

    /**
     * 根据ID查询秒杀对象
     * @param seckillId
     * @return
     */

    Seckill queryById(long seckillId);

    /**
     * 根据偏移量查询秒杀对象
     * @param offset
     * @param limit
     * @return
     */

    List<Seckill> queryAll(@Param("offset") int offset, @Param("limit") int limit);




}

```

```
package org.seckill.dao;

import org.apache.ibatis.annotations.Param;
import org.seckill.entity.SuccessKilled;

/**
 * Created by 荔枝 on 2016-11-11.
 */
public interface SuccessKilledDao {

    /**
     * 插入购买明细，可过滤重复秒杀
     * @param seckillId
     * @param userphone
     * @return
     */

    int insertSuccessKilled(@Param("seckillId") long seckillId, @Param("userphone") long userphone);

    /**
     * 根据ID查询SuccessKilled并携带秒杀产品对象实体
     * @param seckillId
     * @return
     */

    SuccessKilled queryByIdWithSeckill(@Param("seckillId") long seckillId, @Param("userphone") long userphone);
}

```

### mybatis实现DAO编程实现

```
<?xml version="1.0" encoding="utf-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="org.seckill.dao.SeckillDao">

    <!--目的：为DAO接口方法提供sql语句配置-->
    
    <update id="reduceNumber">
        update
          seckill
        SET
          number = number - 1
        where seckill_id = # {seckillId}
        and start_time &lt;= #{killTime}
        and end_time &gt;= #{killTime}
        and number > 0;
    </update>

    <select id="queryById" resultType="Seckill" parameterType="long">
        SELECT
          seckill_id,
          name,
          number,
          start_time,
          end_time,
          create_time
        FROM
          seckill
        WHERE
          seckill_id = #{seckill_id}
    </select>

    <select id="quertyAll" resultType="Seckill">
        SELECT
          seckill_id,
          name,
          number,
          start_time,
          end_time,
          crate_time
        FROM
          seckill
        ORDER BY create_time desc
        WHERE
          limit #{offset}, #{limit}
    </select>
    
</mapper>

```

```
<?xml version="1.0" encoding="utf-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
    <!--目的：为DAO接口方法提供sql语句的配置-->
<mapper namespace="org.seckill.dao.SeckillDao">
    <!--主键冲突，报错-->
    <insert id="insertSuccessKilled">

        INSERT ignore
        INTO
          success_killed(seckill_id,user_phone,state)
        VALUES
          (#{seckillId},#{userPhone},0)
    </insert>

    <select id="queryByIdWithSeckill" resultType="SuccessKilled">
     -- 根据Id查询SuccessKilled并携带Seckill实体
     -- 如何告诉mybatis把结果映射到SuccessKilled同时映射seckill属性
     -- mybatisk可以自由控制sql
     SELECT
      sk.seckill_id,
      sk.user_phone,
      sk.create_time,
      sk.state,
      s.seckill_id as "seckill.seckill_id",
      s.name as "seckill.name",
      s.number as "seckill.number",
      s.start_time as "seckill.start_time",
      s.end_time as "seckill.end_time",
      s.create_time as "seckill.create_time"
    FROM
      success_killed sk
    INNER JOIN
      seckill s
    ON
      sk.seckill_id = s.seckill_id
    WHERE
      sk.seckill_id = #{seckillId} AND sk.userphone = #{userphone}
    </select>
</mapper>

```

### mybatis整合spring编码

 

 -  数据库相关参数属性配置：

```
driver = com.mysql.jdbc.Driver
url = jdbc:mysql://127.0.0.1:3306/seckill?useUnicode=true&characterEncoding=utf8
jdbc.username = root
password = root

```

 - spring与mybatis整合配置

```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context" xsi:schemaLocation="http://www.springframework.org/schema/beans

 http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd">

    <!--配置整合mybatis的过程-->
    <!--1:配置数据库相关参数properties的属性：${url}-->
    <context:property-placeholder location="classpath:jdbc.properties"/>

    <!--2：数据库的连接池-->
    <bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
        <!--配置连接池属性-->
        <property name="driverClass" value="${driver}"/>
        <property name="jdbcUrl" value="${url}"/>
        <property name="user" value="${jdbc.username}"/>
        <property name="password" value="${password}"/>

        <!--c3p0连接池的私有属性-->
        <property name="maxPoolSize" value="30"/>
        <property name="minPoolSize" value="10"/>
        <!--关闭不自动commit-->
        <property name="autoCommitOnClose" value="false"/>
        <!--获取连接超时时间-->
        <property name="checkoutTimeout" value="1000"/>
        <!--当获取连接失败重试次数-->
        <property name="acquireRetryAttempts" value="2"/>
    </bean>

    <!--3：配置SqlSessionFactory对象-->
    <bean id="SqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">

        <!--注入数据库连接池-->
        <property name="dataSource" ref="dataSource"/>

        <!--配置mybatis全局配置文件：mybatis-config.xml-->
        <property name="configLocation" value="classpath:mybatis-configs.xml"/>

        <!--扫描entity包 使用别名-->
        <property name="typeAliasesPackage" value="org.seckill.entity"/>

        <!--扫面sql配置文件，也就是mapper需要的xml文件-->
        <property name="mapperLocations" value="classpath:mapper/*.xml"/>
    </bean>

    <!--4：配置扫面dao接口包，动态实现dao接口并注入到spring容器中-->
    <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
        <!--注入sqlSession的过程-->
        <property name="sqlSessionFactoryBeanName" value="sqlSessionFactory"/>

        <!--给出扫描dao接口包-->
        <property name="basePackage" value="org.seckill.dao"/>
    </bean>

</beans>
```
### 编写测试DAO层单元测试

 - seckillDao单元测试（idea里面使用快捷键Ctrl+Shift+T快捷生成测试类）

```
package org.seckill.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entity.Seckill;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by 荔枝 on 2016-11-16.
 */

/**
 * 配置spring和Junit的整合，Junit启动时加载springIOC容器
 * spring-test，junit
 */

@RunWith(SpringJUnit4ClassRunner.class)
//告诉Junit spring配置文件
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SeckillDaoTest {

    //注入Dao实现类依赖
    @Resource
    private SeckillDao seckillDao;

    @Test
    public void queryById() throws Exception {
        long id = 1000;
        Seckill seckill = seckillDao.queryById(id);
        System.out.println(seckill.getName());
        System.out.println(seckill);
    }

    @Test
    public void queryAll() throws Exception {
        //List<Seckill> queryAll(int offset, int limit);
        //java没有保存形参的记录queryAll(int offset, int limit)-->queryAll(arg0, arg1)
        //有多个参数的时候，在dao层形参前用@Param
        List<Seckill> seckills = seckillDao.queryAll(0,100);
        for (Seckill seckill : seckills){
            System.out.println(seckill);
        }

    }

    @Test
    public void reduceNumber() throws Exception {

        Date killTime = new Date();
        int updateCount = seckillDao.reduceNumber(1000L, killTime);
        System.out.println("updateCount=" + updateCount);

    }


}
```

 - SuccessKilledDao的单元测试

```
package org.seckill.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entity.Seckill;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by hspcadmin on 2016-11-16.
 */

/**
 * 配置spring和Junit的整合，Junit启动时加载springIOC容器
 * spring-test，junit
 */

@RunWith(SpringJUnit4ClassRunner.class)
//告诉Junit spring配置文件
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SeckillDaoTest {

    //注入Dao实现类依赖
    @Resource
    private SeckillDao seckillDao;

    @Test
    public void queryById() throws Exception {
        long id = 1000;
        Seckill seckill = seckillDao.queryById(id);
        System.out.println(seckill.getName());
        System.out.println(seckill);
    }

    @Test
    public void queryAll() throws Exception {
        //List<Seckill> queryAll(int offset, int limit);
        //java没有保存形参的记录queryAll(int offset, int limit)-->queryAll(arg0, arg1)
        //有多个参数的时候，在dao层形参前用@Param
        List<Seckill> seckills = seckillDao.queryAll(0,100);
        for (Seckill seckill : seckills){
            System.out.println(seckill);
        }

    }

    @Test
    public void reduceNumber() throws Exception {

        Date killTime = new Date();
        int updateCount = seckillDao.reduceNumber(1000L, killTime);
        System.out.println("updateCount=" + updateCount);

    }


}
```
&emsp;&emsp;此时一个简单的秒杀功能就实现完成了！后续问题优化会有二、三系列更新。
