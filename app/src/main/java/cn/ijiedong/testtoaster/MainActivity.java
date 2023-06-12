package cn.ijiedong.testtoaster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.KeyEvent;
import android.view.View;

import com.hjq.toast.Toaster;

public class MainActivity extends AppCompatActivity {

    private long lastClickTime = 0L;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void toBackgroundShowToast(View v) {
        Toaster.showLong("请打开存储相机xxxxx权限");
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setData(Uri.fromParts("package", getPackageName(), null));
        startActivity(intent);
    }

    public void toDesktopToast(View v) {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);
        Toaster.showLong("请打开存储相机xxxxx权限");
    }

    public void clickActionSend(View view) {
        Toaster.showLong("请打开存储相机xxxxx权限");
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_TEXT, "toaster使用demo");
        shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        shareIntent.setType("text/plain");
        startActivity(Intent.createChooser(shareIntent, "请选择打开的app"));
    }

    public void clickLaunchQQ(View v) {
        Toaster.showLong("请打开存储相机xxxxx权限");
        PackageManager packageManager = this.getPackageManager();
        Intent intent = packageManager.getLaunchIntentForPackage("com.tencent.mobileqq");
        startActivity(intent);
    }

    public void clickLaunchWeChat(View v) {
        Toaster.showLong("请打开存储相机xxxxx权限");
        PackageManager packageManager = this.getPackageManager();
        Intent intent = packageManager.getLaunchIntentForPackage("com.tencent.mm");
        startActivity(intent);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis() - lastClickTime >= 2000) {
                Toaster.showShort("再次点击退出");
                lastClickTime = System.currentTimeMillis();
            } else {
                onBackPressed();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}