# Android布局实验报告

## 实验概述

本项目包含四个Android布局实验，旨在通过实际操作掌握Android不同布局类型的使用方法和特性。实验涵盖了线性布局(LinearLayout)、表格布局(TableLayout)和约束布局(ConstraintLayout)的核心概念和应用场景。

## 实验内容

### 实验一：线性布局(LinearLayout)

**功能描述**：使用线性布局实现嵌套的界面结构，包含文本、按钮和图片等元素的垂直和水平排列。

**技术要点**：
- 嵌套LinearLayout的使用
- orientation属性（vertical/horizontal）
- layout_weight属性实现元素的权重分配
- gravity和layout_gravity属性控制元素对齐方式

**对应文件**：
- `MainActivity1.java`
- `activity_main1.xml`

### 实验二：表格布局(TableLayout)

**功能描述**：使用表格布局实现类似菜单的界面结构，包含多行多列的元素排列。

**技术要点**：
- TableLayout和TableRow的使用
- stretchColumns属性实现列的自动拉伸
- gravity属性控制元素对齐（左对齐/右对齐）
- 分隔线的实现

**对应文件**：
- `MainActivity2.java`
- `activity_main2.xml`

### 实验三：约束布局(ConstraintLayout)1

**功能描述**：使用约束布局实现复杂的界面结构，包含文本、按钮和图片等元素的灵活排列。

**技术要点**：
- 约束布局的基本约束条件
- 链式布局(Chain)的使用
- 约束优先级和偏差值(bias)

**对应文件**：
- `MainActivity3.java`
- `activity_main3.xml`

### 实验四：约束布局(ConstraintLayout)2

**功能描述**：使用约束布局结合图片资源实现更复杂的界面设计，包含多个图片和文本元素的排列。

**技术要点**：
- 图片资源的引用和显示
- 约束布局的权重分配
- 复杂约束条件的设置
- 界面的响应式设计

**对应文件**：
- `MainActivity4.java`
- `activity_main4.xml`
- 图片资源：`res/drawable/`目录下的航天相关图片

## 项目结构

```
AndroidStudioProjects/Test/
├── app/
│   └── src/
│       └── main/
│           ├── java/com/example/test/
│           │   ├── MainActivity.java      # 主界面，导航入口
│           │   ├── MainActivity1.java     # 实验一：线性布局
│           │   ├── MainActivity2.java     # 实验二：表格布局
│           │   ├── MainActivity3.java     # 实验三：约束布局1
│           │   └── MainActivity4.java     # 实验四：约束布局2
│           ├── res/
│           │   ├── drawable/              # 图片资源
│           │   ├── layout/                # 布局文件
│           │   │   ├── activity_main.xml  # 主界面布局
│           │   │   ├── activity_main1.xml # 实验一布局
│           │   │   ├── activity_main2.xml # 实验二布局
│           │   │   ├── activity_main3.xml # 实验三布局
│           │   │   └── activity_main4.xml # 实验四布局
│           │   └── values/                # 资源文件
│           └── AndroidManifest.xml        # 应用配置文件
└── README.md                              # 本文件
```

## 运行方法

### 环境要求

- Android Studio 4.0+ 或其他支持Android开发的IDE
- JDK 8+ 或兼容版本
- Android SDK 26+ (Android 8.0 Oreo+)

### 操作步骤

1. 克隆或下载项目到本地
2. 使用Android Studio打开项目
3. 连接Android设备或启动模拟器
4. 点击"Run"按钮或使用快捷键Shift+F10运行项目
5. 在主界面点击对应按钮进入不同实验

## 技术要点总结

### 布局类型对比

| 布局类型       | 特点                     | 适用场景                     |
|--------------|-------------------------|----------------------------|
| LinearLayout | 简单直观，线性排列元素       | 元素需要垂直或水平排列的简单界面    |
| TableLayout  | 表格结构，行列清晰          | 类似表格或菜单的界面结构        |
| ConstraintLayout | 灵活强大，约束条件丰富      | 复杂界面布局，需要精确定位元素     |

### 核心概念

- **布局属性**：控制元素在布局中的位置和大小
- **约束条件**：定义元素之间的关系（如对齐、间距等）
- **权重分配**：控制元素在可用空间中的分配比例
- **响应式设计**：确保界面在不同尺寸设备上的良好显示效果

## 实验总结

通过完成这四个Android布局实验，我系统地学习了Android常用布局类型的使用方法和特性：

1. 掌握了线性布局的嵌套使用和权重分配
2. 理解了表格布局的行列结构和拉伸属性
3. 熟悉了约束布局的约束条件和链式布局
4. 学会了如何结合图片资源实现更复杂的界面设计

这些知识为后续开发更复杂的Android应用界面奠定了坚实的基础。