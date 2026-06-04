# 智梦AI 工作流平台 (ZhimengAI Workflow)

企业级 AI Agent 可视化工作流编排平台，支持拖拽式编排 LLM 节点、工具节点与逻辑控制流，提供从工作流设计、调试到发布的完整生命周期管理。

## 技术栈

| 层级 | 技术 |
|------|------|
| 后端框架 | Java 21 / Spring Boot 3.5 / Spring AI 1.1 |
| 工作流引擎 | LangGraph4j / CompiledGraph |
| 前端 | React 18 / TypeScript 5.9 / Vite 5.4 / Ant Design 5 / ReactFlow |
| 数据库 | MySQL 8.4 / PostgreSQL / Redis 7 |
| 对象存储 | MinIO |
| 容器化 | Docker Compose |
| 持久层 | MyBatis-Plus 3.5 |

## 系统架构

```
Console Frontend (React + TypeScript + ReactFlow)   Port 3000
        |
Console Hub (Spring Boot REST + SSE + Auth)          Port 8080
       / \
      /   \
Core Workflow Engine (Java)    External Tool/Plugin Services
Port 7880                      Link / AITools / RPA
      \   /
       \ /
Infrastructure: MySQL / PostgreSQL / Redis / MinIO
```

## 本地启动

### 前置条件

- JDK 21+
- Node.js 18+
- Docker Desktop

### 1. 启动基础设施

```bash
cd docker/zhimeng-ai
cp .env.example .env
docker compose up -d
```

### 2. 启动后端

```bash
# Console Hub
cd console/backend
mvn clean install -DskipTests
mvn spring-boot:run -pl hub

# Workflow Engine
cd core-workflow-java
mvn spring-boot:run
```

### 3. 启动前端

```bash
cd console/frontend
npm install
npm run dev
```

访问 http://localhost:1881

## 环境变量

| 变量 | 说明 | 默认值 |
|------|------|--------|
| `MYSQL_HOST` / `MYSQL_PORT` / `MYSQL_DB` | MySQL 连接 | localhost / 3306 / zhimeng-ai-console |
| `MYSQL_USER` / `MYSQL_PASSWORD` | MySQL 凭证 | root / (必填) |
| `REDIS_HOST` / `REDIS_PORT` | Redis 连接 | localhost / 6379 |
| `OSS_ENDPOINT` / `OSS_ACCESS_KEY_ID` / `OSS_ACCESS_KEY_SECRET` | MinIO 凭证 | (必填) |
| `DEEPSEEK_API_KEY` | DeepSeek API Key | (必填) |

完整配置参见 `docker/zhimeng-ai/.env.example`。

## 核心功能

- **可视化工作流编排**：基于 ReactFlow 的拖拽式节点编排，支持 LLM 节点、工具节点、条件分支
- **Java 工作流执行引擎**：基于 Spring Boot + LangGraph4j 实现节点编排、执行调度与状态推进
- **多模型统一调用**：通过 Spring AI 统一抽象层接入 DeepSeek、OpenAI 等模型
- **SSE 实时流式推送**：工作流执行进度、LLM 流式输出、节点状态变更毫秒级同步
- **插件生态**：支持 MCP 协议、工具注册系统、语音合成插件

## 关键技术实现

### 工作流执行引擎

基于 LangGraph4j 的 CompiledGraph，通过 `NodeExecutor` 接口实现不同类型节点的统一调度，边驱动的顺序/并行执行链路完成从输入到 LLM 节点再到输出的流程推进。

### 多模型统一网关

基于 Spring AI 设计轻量级客户端工厂，将 OpenAI、DeepSeek 等模型的调用差异抹平为标准的流式响应，支持业务方通过配置快速切换底层模型。

### Docker Compose 一键部署

统一管理 MySQL、Redis、MinIO、Console Hub 及 Workflow Engine 等 5+ 核心服务的依赖关系与健康检查。

---

## 面试官源码阅读导航

> 如果您正在评估候选人此项目的技术深度，以下路径可按优先级阅读。

### 第一优先：工作流执行引擎

| 文件 | 关注点 |
|------|--------|
| `core-workflow-java/src/main/java/com/zhimeng/ai/workflow/engine/WorkflowEngine.java` | 工作流执行主引擎，DAG 拓扑调度 |
| `core-workflow-java/src/main/java/com/zhimeng/ai/workflow/engine/node/NodeExecutor.java` | 节点执行器接口（多态设计） |
| `core-workflow-java/src/main/java/com/zhimeng/ai/workflow/engine/node/AbstractNodeExecutor.java` | 节点执行器抽象基类 |
| `core-workflow-java/src/main/java/com/zhimeng/ai/workflow/engine/ParallelWorkflowEngine.java` | 并行执行引擎 |
| `core-workflow-java/src/main/java/com/zhimeng/ai/workflow/engine/domain/WorkflowDSL.java` | 工作流 DSL 定义 |
| `core-workflow-java/src/main/java/com/zhimeng/ai/workflow/engine/constants/NodeTypeEnum.java` | 节点类型枚举（LLM/Plugin/条件分支等） |

### 第二优先：SSE 流式推送与多模型调用

| 文件 | 关注点 |
|------|--------|
| `console/backend/hub/src/main/java/com/zhimeng/ai/console/hub/controller/WorkflowChatController.java` | SSE 端点，流式响应 |
| `console/backend/hub/src/main/java/com/zhimeng/ai/console/hub/service/WorkflowChatService.java` | 工作流聊天服务 |
| `console/backend/hub/src/main/java/com/zhimeng/ai/console/hub/client/LlmApiClient.java` | 多模型统一调度（OpenAI兼容） |
| `console/backend/hub/src/main/java/com/zhimeng/ai/console/hub/service/chat/impl/BotChatServiceImpl.java` | Bot智能体聊天服务 |

### 第三优先：前端与部署

| 文件 | 关注点 |
|------|--------|
| `console/frontend/src/components/` | ReactFlow 工作流画布组件 |
| `docker/zhimeng-ai/docker-compose.yaml` | 服务编排与健康检查 |

### 代码规模

Java 1233 · TypeScript/React 548 · 总计约 1800 个源文件（本仓库主要包含 Console 前后端与 Java Workflow Engine；外部 Python/FastAPI 插件服务未纳入本次 GitHub 展示）

---

## License

MIT
