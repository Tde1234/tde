package com.example.android_2;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemLongClickListener, ActionMode.Callback {

    private static final String CHANNEL_ID = "animal_notification_channel";
    private static final int NOTIFICATION_ID = 1;
    
    // ActionMode相关变量
    private ActionMode mActionMode;
    private List<Integer> mSelectedItems = new ArrayList<>();
    private ListView mAnimalList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 准备数据
        List<Map<String, Object>> data = new ArrayList<>();
        
        Map<String, Object> item1 = new HashMap<>();
        item1.put("name", "Lion");
        item1.put("image", R.drawable.lion);
        data.add(item1);
        
        Map<String, Object> item2 = new HashMap<>();
        item2.put("name", "Tiger");
        item2.put("image", R.drawable.tiger);
        data.add(item2);
        
        Map<String, Object> item3 = new HashMap<>();
        item3.put("name", "Elephant");
        item3.put("image", R.drawable.elephant);
        data.add(item3);
        
        Map<String, Object> item4 = new HashMap<>();
        item4.put("name", "Monkey");
        item4.put("image", R.drawable.monkey);
        data.add(item4);
        
        Map<String, Object> item5 = new HashMap<>();
        item5.put("name", "Dog");
        item5.put("image", R.drawable.dog);
        data.add(item5);
        
        Map<String, Object> item6 = new HashMap<>();
        item6.put("name", "Cat");
        item6.put("image", R.drawable.cat);
        data.add(item6);

        // 创建SimpleAdapter
        String[] from = {"name", "image"};
        int[] to = {R.id.animal_name, R.id.animal_image};
        SimpleAdapter adapter = new SimpleAdapter(this, data, R.layout.list_item, from, to);

        // 设置ListView
        mAnimalList = findViewById(R.id.animal_list);
        mAnimalList.setAdapter(adapter);
        mAnimalList.setOnItemLongClickListener(this);

        // 添加点击事件
        mAnimalList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 如果ActionMode已激活，则处理多选
                if (mActionMode != null) {
                    toggleSelection(view, position);
                } else {
                    // 正常点击事件
                    Map<String, Object> selectedItem = data.get(position);
                    String animalName = (String) selectedItem.get("name");
                    
                    // 显示Toast
                    Toast.makeText(MainActivity.this, "Selected: " + animalName, Toast.LENGTH_SHORT).show();
                    
                    // 发送通知
                    sendNotification(animalName);
                }
            }
        });

        createNotificationChannel();

        // 设置按钮点击事件显示自定义对话框
        Button dialogBtn = findViewById(R.id.dialog_btn);
        dialogBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomDialog();
            }
        });
    }

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


    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        if (mActionMode != null) {
            return false;
        }

        // 启动ActionMode
        mActionMode = startActionMode(this);
        toggleSelection(view, position);
        return true;
    }

    @Override
    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
        // 加载上下文菜单
        mode.getMenuInflater().inflate(R.menu.context_menu, menu);
        return true;
    }

    @Override
    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
        return false;
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

    /**
     * 切换列表项的选中状态
     */
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

    /**
     * 更新ActionMode标题，显示选中项数量
     */
    private void updateActionModeTitle() {
        if (mActionMode != null) {
            mActionMode.setTitle(mSelectedItems.size() + " selected");
        }
    }

    /**
     * 重置所有选中状态
     */
    private void resetSelection() {
        for (int position : mSelectedItems) {
            View view = mAnimalList.getChildAt(position - mAnimalList.getFirstVisiblePosition());
            if (view != null) {
                view.setBackgroundColor(Color.TRANSPARENT);
            }
        }
        mSelectedItems.clear();
    }

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
}