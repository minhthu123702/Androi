package com.example.bai12_2;

import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private static final String TAG = "MainActivity"; // ✅ Bổ sung TAG

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_options_menu, menu);
        Log.d(TAG, "onCreateOptionsMenu đã được gọi và nạp menu.");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        Log.d(TAG, "onOptionsItemSelected được gọi cho item: " + item.getTitle());

        if (itemId == R.id.action_settings) {
            Log.i(TAG, "Mục Cài đặt được chọn.");
            Toast.makeText(this, R.string.settings_selected, Toast.LENGTH_SHORT).show();
            return true;

        } else if (itemId == R.id.action_about) {
            Log.i(TAG, "Mục Giới thiệu được chọn.");
            Toast.makeText(this, R.string.about_selected, Toast.LENGTH_SHORT).show();
            return true;

        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}
