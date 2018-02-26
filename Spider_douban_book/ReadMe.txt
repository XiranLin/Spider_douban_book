目的：将豆瓣编程类书籍抓下，保留评论人数超过1000评分最高的40本书籍数据，保存进excel文件这中
语言：Java
编写工具：Eclipse
额外库：apache poi 用于生成excel文件

代码分成3个文件，bookSpider,Spider,Book. 

bookSpider 包含main方法，采用多线程方式，运用了同步和锁，由addUrl()将从Spider中获得的新网址存入待处理池中，getUrl()从待处理池中提取网址传入Spider中进行处理，workUrl()将网页传入Spider中进行处理、将从Spider中得到的书籍list放入存储器中并将已完成的网址放入已完成池中，并且在所有网址抓取完毕后，对书籍存储器排序，生成excel文件。

Spider 建立与网页的连接，处理得到的数据。SendGet()建立与网页链接，得到网页源数据。GetBooks()用正则表达式从网页数据中取得本网页上书籍的信息，生成Book Object List。GetLinkUrl()获取网页上关于其他页的超链接网址，由bookSpider 加入待处理池。

Book 是一个Object class，包含书籍的信息：题目，作者，出版社，出版日期，价格，评分，和评论人数。Function包含constructor，compareTo, 和toString(). CompareTo()用于将书籍按评分排序，toString() 显示书籍信息。

BookTest 和 SpiderTest 是由Junit生成的文件，用于单元检测。
sendGet_test.txt 用于SpiderTest单元检测。
resutl.xlm 保存最终结果。

程序平均耗时 110023 毫秒