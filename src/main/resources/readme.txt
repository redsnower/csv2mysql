1、首先在application.properties中配置需要导入的模块名称,多个模块名称之间用“,”分割。如：attendance
2、在resources目录下新建模块名称对应的properties文件，文件内存储需要导入的表和字段信息，具体如下：
    delimiter: csv文件中内容的分割符
    table: 标识 需要将csv中数据导入到哪张表
    csv: 存放数据csv文件名称，文件必须在resources/csv路径下
    fields: 标识 表中的字段对应的名称
            *************************************warning*****************************************************

            ******************  fields字段顺序需要和csv中列的顺序保持一致，否则会导致插入的值和列不匹配问题****************

            *************************************warning*****************************************************x

