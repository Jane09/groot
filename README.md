# groot
    knownledge set
    
    git rm --cached .idea
    git rm --cached *.iml
    
    ahc:hostname:[port]     异步调用Http服务
    direct:name 同步调用另外一个endpoint，在同一个CamelContext下
    elasticsearch://clustername
    
    exec://executable?options 执行系统命令
    file://nameOfFileOrDirectory    发送消息到一个文件或者 获得一个文件或文件夹
    
    ftp://host[:port]/fileName 发送或者接收文件
    gauth://name[?opts]     OAuth
    hdfs://path[?opts]      读写文件
    minia:tcp://hostname[:port]
    http://hostname[:port]  外部服务
    http4:/hostname[:port]  HttpClient 4.x
    ibatis://statementName
    jdbc:dataSourceName?options
    ldap:host[:port]?base=...[&scope=<scope>]
    log:loggingCategory[?level=ERROR]    commons logging
    lucene:searchName:insert/query[?analyzer=?]
    [tcp|udp|vm]:host[:port]
    mock:name               测试
    mongodb:connection?options
    mqtt:name
    mybatis://statementName
    netty:[tcp|udp]//host[:port]?options
    properties://key[?options]
    quartz://groupName/timerName
    ref:name                在注册中心查找已存在的Endpoint
    restlet:restletUrl[?options]        消费生产restful资源
    rmi://host[:port]
    seda:name               在同一个CamelContext中异步调用另外一个Endpoint
    servlet:uri             暴露http服务，发布到web容器
    solr://host[:port]/solr?[options]
    spring-batch:job[options]
    spring-integration:defaultChannelName   camel连接spring integration
    spring-redis:restletUrl[?options]
    
    spring-ws:[mapping-type:]address[?options]  使用spring-webservices客户端调用web services；服务端创建 web services

    sql:select * from table where id=#
    
    stream:[in|out|err|file]        读写流
    
    timer://name
    websocket://host:port/path
    zookeeper://host:port/path