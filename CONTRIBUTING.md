# 贡献指南

感谢您考虑为本项目做出贡献！以下是参与项目开发时需要遵循的规范。

## Commit 提交规范

我们使用 [Conventional Commits](https://www.conventionalcommits.org/zh-hans/) 规范来确保提交信息的一致性。

### 提交格式

```
<类型>[可选 范围]: <描述>

[可选 正文]

[可选 脚注]
```

### 主要提交类型

- `feat`: 新增功能
- `fix`: 修复缺陷
- `docs`: 文档更新
- `style`: 代码格式调整
- `refactor`: 代码重构
- `perf`: 性能优化
- `test`: 测试相关
- `build`: 构建相关
- `ci`: CI/CD相关
- `chore`: 其他修改

### 示例

```
feat(auth): 添加用户登录功能

- 实现JWT认证
- 添加登录表单
- 集成后端API

Closes #123
```

## 分支管理

- `main`: 主分支，保持稳定可发布状态
- `develop`: 开发分支
- `feature/*`: 功能分支
- `fix/*`: 修复分支
- `release/*`: 发布分支

## 开发环境搭建

### 基本要求

- JDK 17
- Maven 3.8+
- MySQL 8.0+
- IDE 推荐使用 IntelliJ IDEA（如果使用其他开发工具请添加 git 忽略规则）

### 技术栈

- Spring Boot 3.0.2
- MyBatis-Plus 3.5.3.1
- Sa-Token 1.39.0
- SpringDoc OpenAPI 2.2.0

### 环境搭建步骤

1. **克隆项目**
   ```bash
   git clone [项目地址]
   cd backend
   ```

2. **配置数据库**
   - 安装并启动 MySQL 8.0+
   - 创建数据库 douyin_mall
   - 数据库初始化脚本位于根目录下的 sql 文件夹下
   - 数据库配置信息参见 application.yml 部分

3. **IDE 配置**
   - 使用 IDE 打开项目
   - 确保 Maven 配置正确
   - 等待依赖下载完成

4. **运行项目**
   - 运行 BackendApplication 主类
   - 访问 http://localhost:8081/swagger-ui/index.html 查看API文档
   - OpenAPI 文档地址：http://localhost:8081/v3/api-docs

### 配置说明

1. **服务器配置**
   - 默认端口：8081
   - 如需修改，请在 application.yaml 中调整 server.port

2. **API文档**
   - Swagger UI：http://localhost:8081/swagger-ui/index.html
   - OpenAPI文档：http://localhost:8081/v3/api-docs

3. **认证配置**
   - 使用 Sa-Token 认证框架
   - Token 有效期：30天
   - 支持多端同时登录
   - Token 格式：UUID

### 常见问题

1. **依赖下载失败**
   - 检查 Maven 设置
   - 尝试使用国内镜像源
   ```xml
   <mirrors>
     <mirror>
       <id>aliyun</id>
       <mirrorOf>central</mirrorOf>
       <url>https://maven.aliyun.com/repository/central</url>
     </mirror>
   </mirrors>
   ```

2. **端口冲突**
   - 修改 application.yml 中的服务器端口

## Pull Request 流程

1. Fork 本仓库
2. 创建功能分支
3. 提交更改
4. 提交 Pull Request

## 其他注意事项

- 请确保提交前执行了所有测试
- 保持代码干净整洁
- 提交前使用代码格式化工具对代码进行格式化
- 请对代码进行必要的注释说明
- 重要更改请先讨论