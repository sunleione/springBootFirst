 对文件操作时

 1.通过resource目录下的文件获取该文件的绝对路径
   用ResourceUtils

 2.多了解一些spring apache的对文件 流 URL URI 操作的工具类

可以使用它获取 类路径下的资源URL Resource等
 new ClassPathResource("/quartz/spring-quartz.properties")

 研究spring io util包下对文件的工具 或者apache的

io包下的ResourceLoader 专门用来加载classpath: 路径下的文件资源
通过ResourceLoader 获取Resource 就可以获取 ---> URL file 流等信息

ResourceLoader resourceLoader = new DefaultResourceLoader();
Resource resource=resourceLoader.getResource("classpath:sql/init.sql.ftl");


3.关于文件路径的总结 \\和 /

\\ 是windows系统下的文件路径分隔符
/ 是linux 下的文件路径分隔符

由于java的跨平台性  尽量使用/

所以一般在获取文件路径后  使用 StringUtils.cleanPath(path) 转换一下比较好
    在加文件路径时 分隔符用File.sepoatoer常量



4. file.createNewFile() 和 File.createTempFile(String prefix, String suffix)的区别

file.createNewFile() 如果file路径不存在 则创建失败
File.createTempFile 会根据文件名和后缀生成一个默认路径的文件




