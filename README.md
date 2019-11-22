# web-side
网页端及数据处理后端
# 版本
Spring Boot版本 1.5.21

mysql 8.0.18

设计数据库阶段建议拉取新的代码之后删除所有本地的mysql数据表让jpa自动重新建表，来保持始终相同的表和字段，可以在/resource/schema.sql中设置需要初始化的数据表中的数据。