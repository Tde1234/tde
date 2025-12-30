# Android界面组件实验报告

## 实验概述

本实验实现了Android界面组件的四个内容，包括：
1. 使用`SimpleAdapter`创建带自定义项的ListView
2. 创建自定义布局的AlertDialog
3. 使用XML定义带字体大小和颜色设置的菜单
4. 实现ListView的ActionMode上下文菜单

## 实验一：SimpleAdapter创建ListView

### 实验要求
使用`SimpleAdapter`创建带有自定义项（TextView+ImageView）的ListView，点击时显示Toast并发送包含列表项内容的通知。

### 核心代码

#### 自定义列表项布局 (list_item.xml)
```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:padding="16dp">

    <ImageView
        android:id="@+id/animal_image"
        android:layout_width="80dp"
        android:layout_height="80dp"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintBottom_toBottomOf="parent"
        android:scaleType="centerCrop" />

    <TextView
        android:id="@+id/animal_name"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        app:layout_constraintStart_toEndOf="@+id/animal_image"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintTop_toTopOf="@+id/animal_image"
        app:layout_constraintBottom_toBottomOf="@+id/animal_image"
        android:layout_marginStart="16dp"
        android:textSize="24sp"
        android:textStyle="bold" />

</androidx.constraintlayout.widget.ConstraintLayout>
```

#### ListView初始化和点击事件
```java
// 准备数据
List<Map<String, Object>> data = new ArrayList<>();

Map<String, Object> item1 = new HashMap<>();
item1.put("name", "Lion");
item1.put("image", R.drawable.lion);
data.add(item1);
// ... 添加其他动物数据

// 创建SimpleAdapter
String[] from = {"name", "image"};
int[] to = {R.id.animal_name, R.id.animal_image};
SimpleAdapter adapter = new SimpleAdapter(this, data, R.layout.list_item, from, to);

// 设置ListView
mAnimalList = findViewById(R.id.animal_list);
mAnimalList.setAdapter(adapter);

// 添加点击事件
mAnimalList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Map<String, Object> selectedItem = data.get(position);
        String animalName = (String) selectedItem.get("name");
        
        // 显示Toast
        Toast.makeText(MainActivity.this, "Selected: " + animalName, Toast.LENGTH_SHORT).show();
        
        // 发送通知
        sendNotification(animalName);
    }
});
```

#### 通知发送功能
```java
private void createNotificationChannel() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        CharSequence name = "Animal Notification Channel";
        String description = "Channel for animal list notifications";
        int importance = NotificationManager.IMPORTANCE_DEFAULT;
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
        channel.setDescription(description);
        
        NotificationManager notificationManager = getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(channel);
    }
}

private void sendNotification(String animalName) {
    NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle(animalName)
            .setContentText("You selected " + animalName + " from the animal list.")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT);

    NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
    notificationManager.notify(NOTIFICATION_ID, builder.build());
}
```

**实验结果截图**：
点击Elephant项，显示Toast和发送通知
  <img src="./运行结果截图/1.png" width="420" alt="SimpleAdapter创建ListView" />
  <img src="./运行结果截图/1_1.png" width="420" alt="SimpleAdapter创建ListView" />

## 实验二：自定义布局的AlertDialog

### 实验要求
创建一个带有自定义布局的AlertDialog，通过`AlertDialog.Builder.setView()`实现。

### 核心代码

#### 自定义对话框布局 (custom_dialog.xml)
```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:padding="20dp">

    <TextView
        android:id="@+id/dialog_title"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="Sign In"
        android:textSize="24sp"
        android:textStyle="bold"
        android:gravity="center"
        android:marginBottom="20dp"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent" />

    <EditText
        android:id="@+id/username_edit"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="Username"
        android:padding="10dp"
        android:background="@drawable/edittext_border"
        android:layout_marginBottom="15dp"
        app:layout_constraintTop_toBottomOf="@+id/dialog_title"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent" />

    <EditText
        android:id="@+id/password_edit"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="Password"
        android:inputType="textPassword"
        android:padding="10dp"
        android:background="@drawable/edittext_border"
        android:layout_marginBottom="20dp"
        app:layout_constraintTop_toBottomOf="@+id/username_edit"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent" />

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="horizontal"
        android:gravity="center"
        app:layout_constraintTop_toBottomOf="@+id/password_edit"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent">

        <Button
            android:id="@+id/cancel_btn"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="Cancel"
            android:layout_marginEnd="10dp" />

        <Button
            android:id="@+id/signin_btn"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="Sign In" />

    </LinearLayout>

</androidx.constraintlayout.widget.ConstraintLayout>
```

#### 对话框显示功能
```java
private void showCustomDialog() {
    // 创建AlertDialog.Builder
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    
    // 加载自定义布局
    View customView = getLayoutInflater().inflate(R.layout.custom_dialog, null);
    
    // 设置自定义布局
    builder.setView(customView);
    
    // 获取对话框中的控件
    EditText usernameEdit = customView.findViewById(R.id.username_edit);
    EditText passwordEdit = customView.findViewById(R.id.password_edit);
    Button cancelBtn = customView.findViewById(R.id.cancel_btn);
    Button signinBtn = customView.findViewById(R.id.signin_btn);
    
    // 创建对话框
    final AlertDialog dialog = builder.create();
    
    // 设置取消按钮点击事件
    cancelBtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            dialog.dismiss();
        }
    });
    
    // 设置登录按钮点击事件
    signinBtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String username = usernameEdit.getText().toString();
            String password = passwordEdit.getText().toString();
            
            // 显示Toast提示
            Toast.makeText(MainActivity.this, "Username: " + username + "\nPassword: " + password, Toast.LENGTH_SHORT).show();
            
            dialog.dismiss();
        }
    });
    
    // 显示对话框
    dialog.show();
}
```

**实验结果截图**：
点击"Show Custom Dialog"按钮，显示自定义对话框
  <img src="./运行结果截图/2.png" width="420" alt="自定义布局的AlertDialog" />
输入用户名和密码，点击登录按钮，显示Toast提示
  <img src="./运行结果截图/2_1.png" width="420" alt="自定义布局的AlertDialog" />

## 实验三：XML定义菜单

### 实验要求
定义一个XML菜单，包含字体大小（小/10sp，中/16sp，大/20sp）的子菜单，一个普通菜单项（点击显示Toast），以及字体颜色（红/黑）的子菜单，点击时动态更新文本样式。

### 核心代码

#### 菜单XML定义 (main_menu.xml)
```xml
<?xml version="1.0" encoding="utf-8"?>
<menu xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto">

    <!-- 字体大小子菜单 -->
    <item
        android:id="@+id/font_size_menu"
        android:title="字体大小"
        app:showAsAction="never">
        <menu>
            <item
                android:id="@+id/font_size_small"
                android:title="小" />
            <item
                android:id="@+id/font_size_medium"
                android:title="中" />
            <item
                android:id="@+id/font_size_large"
                android:title="大" />
        </menu>
    </item>

    <!-- 普通菜单项 -->
    <item
        android:id="@+id/normal_menu_item"
        android:title="普通菜单项"
        app:showAsAction="never" />

    <!-- 字体颜色子菜单 -->
    <item
        android:id="@+id/font_color_menu"
        android:title="字体颜色"
        app:showAsAction="never">
        <menu>
            <item
                android:id="@+id/font_color_red"
                android:title="红色" />
            <item
                android:id="@+id/font_color_black"
                android:title="黑色" />
        </menu>
    </item>
</menu>
```

#### 菜单加载和处理
```java
@Override
public boolean onCreateOptionsMenu(Menu menu) {
    // 加载菜单XML
    getMenuInflater().inflate(R.menu.main_menu, menu);
    return true;
}

@Override
public boolean onOptionsItemSelected(MenuItem item) {
    // 获取测试文本视图
    TextView testText = findViewById(R.id.test_text);
    
    // 根据菜单项ID处理点击事件
    int itemId = item.getItemId();
    
    // 字体大小选项
    if (itemId == R.id.font_size_small) {
        testText.setTextSize(10);
        return true;
    } else if (itemId == R.id.font_size_medium) {
        testText.setTextSize(16);
        return true;
    } else if (itemId == R.id.font_size_large) {
        testText.setTextSize(20);
        return true;
    } 
    // 普通菜单项
    else if (itemId == R.id.normal_menu_item) {
        Toast.makeText(this, "您点击了普通菜单项", Toast.LENGTH_SHORT).show();
        return true;
    }
    // 字体颜色选项
    else if (itemId == R.id.font_color_red) {
        testText.setTextColor(Color.RED);
        return true;
    } else if (itemId == R.id.font_color_black) {
        testText.setTextColor(Color.BLACK);
        return true;
    }
    
    return super.onOptionsItemSelected(item);
}
```

**实验结果截图**：
点击右上角按钮，显示XML定义菜单
  <img src="./运行结果截图/3.png" width="420" alt="XML定义菜单" />
点击字体大小子菜单，动态改变文本大小，分别为小、中、大
  <img src="./运行结果截图/3_1.png" width="420" alt="XML定义菜单" />
点击普通菜单项，显示Toast提示
  <img src="./运行结果截图/3_2.png" width="420" alt="XML定义菜单" />
点击字体颜色子菜单，动态改变文本颜色，分别为红、黑
  <img src="./运行结果截图/3_3.png" width="420" alt="XML定义菜单" />

## 实验四：ActionMode上下文菜单

### 实验要求
为ListView实现ActionMode上下文菜单，支持长按激活、多选、删除操作。

### 核心代码

#### 上下文菜单XML (context_menu.xml)
```xml
<?xml version="1.0" encoding="utf-8"?>
<menu xmlns:android="http://schemas.android.com/apk/res/android">
    <item
        android:id="@+id/action_delete"
        android:title="删除"
        android:icon="@android:drawable/ic_menu_delete"
        android:showAsAction="ifRoom" />
</menu>
```

#### ActionMode实现
```java
// 设置长按监听器
mAnimalList.setOnItemLongClickListener(this);

// ActionMode回调实现
@Override
public boolean onCreateActionMode(ActionMode mode, Menu menu) {
    // 加载上下文菜单
    mode.getMenuInflater().inflate(R.menu.context_menu, menu);
    return true;
}

@Override
public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
    if (item.getItemId() == R.id.action_delete) {
        // 处理删除操作
        Toast.makeText(this, "删除了 " + mSelectedItems.size() + " 项", Toast.LENGTH_SHORT).show();
        mode.finish();
        return true;
    }
    return false;
}

@Override
public void onDestroyActionMode(ActionMode mode) {
    // 重置选择状态
    mActionMode = null;
    resetSelection();
}

// 长按处理
@Override
public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
    if (mActionMode != null) return false;

    // 启动ActionMode
    mActionMode = startActionMode(this);
    toggleSelection(view, position);
    return true;
}

// 切换选择状态
private void toggleSelection(View view, int position) {
    if (mSelectedItems.contains(position)) {
        // 取消选中
        mSelectedItems.remove(Integer.valueOf(position));
        view.setBackgroundColor(Color.TRANSPARENT);
    } else {
        // 选中
        mSelectedItems.add(position);
        view.setBackgroundColor(Color.LTGRAY);
    }

    // 更新ActionMode标题
    updateActionModeTitle();

    // 如果没有选中项，关闭ActionMode
    if (mSelectedItems.isEmpty()) {
        mActionMode.finish();
    }
}
```

**实验结果截图**：
点击ListView项，长按激活ActionMode上下文菜单
  <img src="./运行结果截图/4.png" width="420" alt="ActionMode上下文菜单" />

## 项目结构

```
app/
├── src/
│   ├── main/
│   │   ├── java/com/example/android_2/
│   │   │   └── MainActivity.java      # 主活动，实现所有功能
│   │   ├── res/
│   │   │   ├── drawable/             # 图片资源
│   │   │   │   ├── lion.jpeg
│   │   │   │   ├── tiger.jpg
│   │   │   │   └── ...
│   │   │   ├── layout/
│   │   │   │   ├── activity_main.xml  # 主布局
│   │   │   │   ├── list_item.xml      # ListView项布局
│   │   │   │   └── custom_dialog.xml  # 自定义对话框布局
│   │   │   ├── menu/
│   │   │   │   ├── main_menu.xml      # 顶部菜单
│   │   │   │   └── context_menu.xml   # ActionMode菜单
│   │   │   └── ...
│   │   └── AndroidManifest.xml        # 应用配置文件
```

## 实验总结

本次Android界面组件实验成功实现了四个核心功能模块，涵盖了Android开发中常用的界面组件和交互模式。通过本次实验，不仅掌握了Android界面组件的使用方法，还理解了Android应用开发的基本架构和设计模式。实验过程中遇到的问题（如菜单不显示、ActionMode实现等）也通过查阅文档和调试得到了解决，提高了问题解决能力和代码调试技巧。这些知识和经验将对后续的Android应用开发工作产生积极的影响。