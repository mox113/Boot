package cn.boot;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    int i = 0;
    private BroadcastReceiver mHomeKeyEventReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(Intent.ACTION_CLOSE_SYSTEM_DIALOGS)) { // 监听home键
                // 表示按了home键,程序到了后台
//                i++;
//                if(i>5){
//                    i =0;
//                    Intent startLockscreenIntent = new Intent(getApplicationContext(), LockscreenViewService.class);
//                    stopService(startLockscreenIntent);
//                }
                Log.e("MainActivity", "home");
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //隐藏标题栏
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        registerReceiver(mHomeKeyEventReceiver, new IntentFilter(Intent.ACTION_CLOSE_SYSTEM_DIALOGS));
    }

    public void btnLock(View view) {
        Intent startLockscreenIntent = new Intent(this, LockscreenViewService.class);
        startService(startLockscreenIntent);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) { //监控/拦截/屏蔽返回键
            Toast.makeText(MainActivity.this, "BACK", Toast.LENGTH_SHORT).show();
            return false;
        } else if (keyCode == KeyEvent.KEYCODE_MENU) {
            Toast.makeText(MainActivity.this, "Menu", Toast.LENGTH_SHORT).show();
            return false;
        } else if (keyCode == KeyEvent.KEYCODE_HOME) {
            Toast.makeText(MainActivity.this, "Home", Toast.LENGTH_SHORT).show();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }
}
