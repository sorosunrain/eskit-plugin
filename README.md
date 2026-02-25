# ES Plugin 模板项目

这是一个标准的 ES Plugin 模板项目，旨在帮助开发者快速搭建和启动基于 Android 的插件开发环境。本项目集成了双渠道构建系统，以满足开发调试和生产发布的不同需求。

## 📋 项目简介

该项目提供了一个清晰的工程结构和基础配置，支持通过 Gradle 进行多渠道构建。核心功能模块位于 `app` 目录下，预置了基本的 Android 组件和依赖配置。

## 🛠️ 双渠道构建系统

本项目设计了两个特定的构建渠道（Flavors），分别对应不同的开发阶段：

### 1. 开发调试渠道 (dev)
- **用途**：专用于日常开发、功能调试和测试。
- **特点**：包含调试符号，未进行代码混淆，方便排查问题。
- **构建命令**：
  ```bash
  ./gradlew assembleDevDebug
  ```
  *生成的 APK 文件通常位于：`app/build/outputs/apk/dev/debug/`*

### 2. 生产发布渠道 (general)
- **用途**：专用于最终的生产环境发布。
- **特点**：经过代码混淆和优化，包体积更小，性能更优。
- **构建命令**：
  ```bash
  ./gradlew assembleGeneralRelease
  ```
  *生成的 APK 文件通常位于：`app/build/outputs/apk/general/release/`*

## 📦 项目结构

```
.
├── app/
│   ├── src/
│   │   └── main/
│   │       ├── java/          # Java 源代码
│   │       └── AndroidManifest.xml # 清单文件
│   └── build.gradle           # 应用模块构建脚本
├── gradle/                    # Gradle Wrapper 文件
├── build.gradle               # 项目级构建脚本
├── settings.gradle            # 项目设置文件
└── README.md                  # 项目说明文档
```

## ⚙️ 环境配置要求

在开始之前，请确保您的开发环境满足以下要求：

- **JDK**: version 1.8 或更高版本
- **Android SDK**:
  - `compileSdk`: 31
  - `minSdk`: 17
  - `targetSdk`: 26
- **Gradle**: 建议使用项目自带的 Gradle Wrapper (`gradlew`)

## 🚀 快速开始

1. **克隆项目**
   ```bash
   git clone <repository-url>
   cd es-plugin
   ```

2. **配置 SDK 路径**
   在项目根目录下创建 `local.properties` 文件，并设置 SDK 路径：
   ```properties
   sdk.dir=/path/to/your/android-sdk
   ```

3. **执行构建**
   根据您的需求选择相应的构建命令（参考[双渠道构建系统](#️-双渠道构建系统)章节）。

## ❓ 常见问题解答 (FAQ)

**Q: 如何清理构建缓存？**
A: 执行 `./gradlew clean` 命令即可清理之前的构建产物。

**Q: 依赖下载失败怎么办？**
A: 请检查网络连接，并确保在 `build.gradle` 中配置了正确的 Maven 仓库地址（如阿里云镜像或 Google 官方仓库）。

## 🤝 贡献指南

我们非常欢迎您参与到本项目的改进中来！

1. Fork 本仓库
2. 创建您的特性分支 (`git checkout -b feature/AmazingFeature`)
3. 提交您的修改 (`git commit -m 'Add some AmazingFeature'`)
4. 推送到分支 (`git push origin feature/AmazingFeature`)
5. 提交 Pull Request
