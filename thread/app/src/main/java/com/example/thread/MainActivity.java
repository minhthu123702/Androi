package com.example.thread;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.content.Context; // Nếu cần Context tường minh
import android.util.Log; // Để ghi log

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
private Button btnStartTask;
private TextView txtProgress;
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
        btnStartTask=(Button) findViewById(R.id.buttonStartTask);
        txtProgress=(TextView) findViewById(R.id.textViewProgress);

        btnStartTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String result = downloadData();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            String result = downloadData();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                txtProgress.setText("da hoan thanh");
                            }
                        });
                    }
                }).start();
            }
        });
    }

    private String downloadData() throws InterruptedException {
        for (int i=1;i<10;i++){
            String curent =String.valueOf(i*10);
            Thread.sleep(2);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    txtProgress.setText("dang chay"+curent+"%");
                }
            });
        }
try {
    Thread.sleep(10000);

} catch (InterruptedException e) {
    throw new RuntimeException(e);
}

        return "";
    }
}