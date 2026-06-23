# Notice and Attribution

This repository is a public learning, secondary-development and interview-reference version of zhimeng-ai-workflow. It may incorporate code, scaffolding, examples, or module structures derived from upstream open-source projects, engineering templates, or tutorial materials.

## Upstream Projects and Dependencies

This project builds on the following open-source frameworks and services:

| Project | License | Usage |
|---------|---------|-------|
| Spring Boot | Apache 2.0 | Application framework |
| Spring AI | Apache 2.0 | AI model abstraction layer |
| React | MIT | Frontend framework |
| ReactFlow | MIT | Workflow canvas component |
| Ant Design 5 | MIT | UI component library |
| MyBatis-Plus | Apache 2.0 | ORM / data access layer |
| MySQL | GPLv2 (server) | Relational database |
| Redis | BSD-3-Clause | Caching / session state |
| MinIO | AGPLv3 | Object storage |
| Docker Compose | Apache 2.0 | Container orchestration |
| Vite | MIT | Frontend build tool |

## Attribution Policy

- Do not remove upstream author comments, copyright headers, license files, or notices.
- If a previously unlisted upstream project is identified, add its name, repository link, and license here.
- Console/admin modules, generic scaffold code, and third-party integrations should not be presented as original work.

## Historical Notes

The Console module was previously developed under the internal name "Astron." Source files may contain author metadata from contributors including `mingsuiyongheng`, `Omuigix`, `yun-zhi-ztl`, `clliu19`, `xxzhang23`, `snoopyYang`, `YiHui`, and related tutorial authors. These attributions are retained in compliance with upstream license requirements.

## Scope of Original Contribution

The core value demonstrated in this repository lies in:

- Visual workflow orchestration editor (ReactFlow-based drag-and-drop node composition)
- Java workflow execution engine (WorkflowDSL / DAG scheduling / NodeExecutor abstraction)
- SSE-based real-time streaming with Spring SseEmitter
- Multi-model unified gateway via Spring AI (DeepSeek, OpenAI-compatible)
- Plugin ecosystem (MCP protocol, tool registration, text-to-speech integration)
- Docker Compose one-command local deployment with health checks

External Python/FastAPI plugin services (Link, AITools, RPA) are not included in this GitHub showcase. Template-based admin panels, generic utility code, and third-party infrastructure integrations are supporting components and should be evaluated as such.
