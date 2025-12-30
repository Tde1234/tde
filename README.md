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

**核心代码**：
```xml
<LinearLayout
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:orientation="horizontal"
    android:gravity="center"
    android:layout_marginBottom="8dp">

    <TextView
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:layout_weight="1"
        android:text="One,One"
        android:textSize="18sp"
        android:gravity="center"/>

    <TextView
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:layout_weight="1"
        android:text="One,Two"
        android:textSize="18sp"
        android:gravity="center"/>

    <TextView
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:layout_weight="1"
        android:text="One,Three"
        android:textSize="18sp"
        android:gravity="center"/>

    <TextView
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:layout_weight="1"
        android:text="One,Four"
        android:textSize="18sp"
        android:gravity="center"/>
</LinearLayout>
```

**对应文件**：
- `MainActivity1.java`
- `activity_main1.xml`

**实验结果截图**：
  <img src="./运行结果截图/1.png" width="420" alt="线性布局显示效果" />

### 实验二：表格布局(TableLayout)

**功能描述**：使用表格布局实现类似菜单的界面结构，包含多行多列的元素排列。

**技术要点**：
- TableLayout和TableRow的使用
- stretchColumns属性实现列的自动拉伸
- gravity属性控制元素对齐（左对齐/右对齐）

**核心代码**：
```xml
<TableRow
    android:background="#CCCCCC">
    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Open..."
        android:textSize="16sp"
        android:padding="8dp"
        android:gravity="left"/>

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Ctrl-O"
        android:textSize="16sp"
        android:padding="8dp"
        android:gravity="right"/>
</TableRow>

<View
    android:layout_height="1dp"
    android:background="#000000"/>

<TableRow
    android:background="#CCCCCC">
    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Save..."
        android:textSize="16sp"
        android:padding="8dp"
        android:gravity="left"/>

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Ctrl-S"
        android:textSize="16sp"
        android:padding="8dp"
        android:gravity="right"/>
</TableRow>
```

**对应文件**：
- `MainActivity2.java`
- `activity_main2.xml`

**实验结果截图**：
  <img src="./运行结果截图/2.png" width="420" alt="表格布局显示效果" />

### 实验三：约束布局(ConstraintLayout)1

**功能描述**：使用约束布局实现复杂的界面结构，包含文本、按钮和图片等元素的灵活排列。

**技术要点**：
- 约束布局的基本约束条件
- 链式布局(Chain)的使用
- 约束优先级和偏差值(bias)

**核心代码**：
```xml
<Button
    android:id="@+id/btn7"
    android:layout_width="0dp"
    android:layout_height="60dp"
    android:text="7"
    android:textSize="18sp"
    app:layout_constraintLeft_toLeftOf="parent"
    app:layout_constraintTop_toBottomOf="@id/inputValue"
    app:layout_constraintRight_toLeftOf="@id/btn8"
    android:layout_marginTop="16dp"
    android:layout_marginRight="4dp" />

<Button
    android:id="@+id/btn8"
    android:layout_width="0dp"
    android:layout_height="60dp"
    android:text="8"
    android:textSize="18sp"
    app:layout_constraintLeft_toRightOf="@id/btn7"
    app:layout_constraintTop_toBottomOf="@id/inputValue"
    app:layout_constraintRight_toLeftOf="@id/btn9"
    android:layout_marginTop="16dp"
    android:layout_marginLeft="4dp"
    android:layout_marginRight="4dp" />

<Button
    android:id="@+id/btn9"
    android:layout_width="0dp"
    android:layout_height="60dp"
    android:text="9"
    android:textSize="18sp"
    app:layout_constraintLeft_toRightOf="@id/btn8"
    app:layout_constraintTop_toBottomOf="@id/inputValue"
    app:layout_constraintRight_toLeftOf="@id/btnDot1"
    android:layout_marginTop="16dp"
    android:layout_marginLeft="4dp"
    android:layout_marginRight="4dp" />

<Button
    android:id="@+id/btnDot1"
    android:layout_width="0dp"
    android:layout_height="60dp"
    android:text="÷"
    android:textSize="18sp"
    app:layout_constraintLeft_toRightOf="@id/btn9"
    app:layout_constraintTop_toBottomOf="@id/inputValue"
    app:layout_constraintRight_toRightOf="parent"
    android:layout_marginTop="16dp"
    android:layout_marginLeft="4dp" />
```

**对应文件**：
- `MainActivity3.java`
- `activity_main3.xml`

**实验结果截图**：
  <img src="./运行结果截图/3.png" width="420" alt="约束布局显示效果" />

### 实验四：约束布局(ConstraintLayout)2

**功能描述**：使用约束布局结合图片资源实现更复杂的界面设计，包含多个图片和文本元素的排列。

**技术要点**：
- 图片资源的引用和显示
- 约束布局的权重分配
- 复杂约束条件的设置

**核心代码**：
```xml
<LinearLayout
    android:id="@+id/ll_space_stations"
    android:layout_width="0dp"
    android:layout_height="wrap_content"
    android:orientation="vertical"
    android:gravity="center_horizontal"
    app:layout_constraintStart_toStartOf="parent"
    app:layout_constraintTop_toTopOf="parent"
    app:layout_constraintEnd_toStartOf="@id/ll_flights"
    app:layout_constraintHorizontal_weight="1"
    android:layout_marginTop="16dp"
    android:layout_marginBottom="8dp">

    <ImageView
        android:id="@+id/iv_space_stations"
        android:layout_width="24dp"
        android:layout_height="24dp"
        android:src="@drawable/space_station_icon"/>

    <TextView
        android:id="@+id/tv_space_stations"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="4dp"
        android:text="Space Stations"
        android:textSize="14sp" />
</LinearLayout>

<LinearLayout
    android:id="@+id/ll_flights"
    android:layout_width="0dp"
    android:layout_height="wrap_content"
    android:orientation="vertical"
    android:gravity="center_horizontal"
    app:layout_constraintStart_toEndOf="@id/ll_space_stations"
    app:layout_constraintTop_toTopOf="parent"
    app:layout_constraintEnd_toStartOf="@id/ll_rovers"
    app:layout_constraintHorizontal_weight="1"
    android:layout_marginTop="16dp"
    android:layout_marginBottom="8dp">

    <ImageView
        android:id="@+id/iv_flights"
        android:layout_width="24dp"
        android:layout_height="24dp"
        android:src="@drawable/rocket_icon"/>

    <TextView
        android:id="@+id/tv_flights"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="4dp"
        android:text="Flights"
        android:textSize="14sp" />
</LinearLayout>

<LinearLayout
    android:id="@+id/ll_rovers"
    android:layout_width="0dp"
    android:layout_height="wrap_content"
    android:orientation="vertical"
    android:gravity="center_horizontal"
    app:layout_constraintStart_toEndOf="@id/ll_flights"
    app:layout_constraintTop_toTopOf="parent"
    app:layout_constraintEnd_toEndOf="parent"
    app:layout_constraintHorizontal_weight="1"
    android:layout_marginTop="16dp"
    android:layout_marginBottom="8dp">

    <ImageView
        android:id="@+id/iv_rovers"
        android:layout_width="24dp"
        android:layout_height="24dp"
        android:src="@drawable/rover_icon"/>

    <TextView
        android:id="@+id/tv_rovers"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="4dp"
        android:text="Rovers"
        android:textSize="14sp" />
</LinearLayout>
```

**对应文件**：
- `MainActivity4.java`
- `activity_main4.xml`
- 图片资源：`res/drawable/`目录下的航天相关图片

**实验结果截图**：
  <img src="./运行结果截图/4.png" width="420" alt="约束布局显示效果" />

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


## 布局类型对比

| 布局类型       | 特点                     | 适用场景                     |
|--------------|-------------------------|----------------------------|
| LinearLayout | 简单直观，线性排列元素       | 元素需要垂直或水平排列的简单界面    |
| TableLayout  | 表格结构，行列清晰          | 类似表格或菜单的界面结构        |
| ConstraintLayout | 灵活强大，约束条件丰富      | 复杂界面布局，需要精确定位元素     |


## 实验总结

通过完成这四个Android布局实验，我系统地学习了Android常用布局类型的使用方法和特性：

1. 掌握了线性布局的嵌套使用和权重分配
2. 理解了表格布局的行列结构和拉伸属性
3. 熟悉了约束布局的约束条件和链式布局
4. 学会了如何结合图片资源实现更复杂的界面设计

这些知识为后续开发更复杂的Android应用界面奠定了坚实的基础。