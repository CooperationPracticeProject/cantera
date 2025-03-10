# 🛒 抖音商城后端项目（仿抖音商城复刻版）

## 📖 项目简介
本项目是一个仿抖音商城的后端复刻版，旨在模拟抖音商城的基本功能，包括用户注册登录、商品管理、购物车、订单管理等功能。**请注意，本项目仅为学习和技术演示用途，与抖音官方无关。** 🚫📜

---

## 🛠️ 技术栈
- 编程语言: Java (Spring Boot) ☕
- 数据库: MySQL 🐬
- 缓存: Redis (开发中) 🚧
- 消息队列: 无（未来可能会开发，不确定） 🤔
- API 文档: Swagger 📚
- 构建工具: Maven 🛠️
- 部署: Docker (未来可能会开发，不确定) 🐳

---

## 🎯 功能模块
1. 用户模块 👤
    - 用户注册、登录、个人信息管理
    - 角色权限管理（买家、卖家、管理员、超级管理员）
2. 商品模块 🛍️
    - 商品发布、编辑、上下架
    - 商品分类管理
    - 商品图片管理
3. 购物车模块 🛒
    - 添加、删除、修改购物车商品
    - 结算购物车商品
4. 订单模块 📦
    - 创建订单、取消订单
    - 订单状态管理（待付款、待发货、待收货、已完成、已取消）
5. 地址模块 🏠
    - 收货地址管理
    - 默认地址设置
6. 权限模块 🔐
    - 角色管理
    - 权限分配

---

## 🚀 环境搭建

### 1. 依赖环境
- JDK 17+ ☕
- MySQL 8.0+ 🐬
- Redis 6.0+ (开发中) 🚧
- Maven 3.6+ 🛠️

### 2. 数据库初始化
1. 创建数据库：
   执行 SQL:
   ```mysql
   CREATE DATABASE douyin_mall;
   ```

2. 导入表结构：
    - 执行 `sql/douyin_mall.sql` 文件初始化表结构+插入测试数据。

### 3. 配置文件
修改 `application.yml` 中 `spring.profiles.active`配置，并根据本地环境修改配置。

# Redis 功能目前正在开发中 🚧

---

## 🏃‍♂️ 运行项目

### 1. 本地运行
1. 克隆项目：
   执行命令: 
   ```shell
   git clone https://github.com/CooperationPracticeProject/cantera.git
   ```

2. 进入项目目录：
   执行命令: 
   ```shell
   cd cantera
   ```

3. 编译项目：
   执行命令: 
   ```shell
   mvn clean install
   ```

4. 运行项目：
   执行命令: 
   ```shell
   mvn spring-boot:run
   ```

### 2. Docker 运行
Docker 支持目前尚未开发，未来可能会添加。🤔🐳

---

## 📚 API 文档
项目启动后，访问以下地址查看 API 文档：
- Swagger UI: http://localhost:8081/swagger-ui.html 📖
- OpenAPI JSON: http://localhost:8081/v3/api-docs 📄

---

## 🗃️ 数据库设计
### 主要表结构
1. 用户表 (user) 👤
    - 存储用户基本信息。
2. 商品表 (product) 🛍️
    - 存储商品信息。
3. 订单表 (order) 📦
    - 存储订单信息。
4. 购物车表 (cart) 🛒
    - 存储用户购物车信息。
5. 地址表 (address) 🏠
    - 存储用户收货地址信息。

详细表结构请参考 `/sql/douyin_mall.sql`。

---

## 🧪 测试数据
项目中包含了一些测试数据，可以通过 `/sql/douyin_mall.sql` 文件导入。

---

## 🤝 贡献指南
详情参见[贡献文档](/CONTRIBUTING.md)

---

## 📜 许可证
本项目基于 MIT 许可证。

---

### 🎉 备注
- 本项目为仿抖音商城的复刻版，仅用于学习和技术演示，与抖音官方无关。🚫📜
- Redis 缓存功能正在开发中，消息队列和 Docker 支持未来可能会添加（不确定）。🤔🚧
- 如果有任何问题或建议，欢迎提交 Issue 或 Pull Request。💬🔧