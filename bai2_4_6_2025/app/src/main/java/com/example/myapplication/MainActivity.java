package com.example.myapplication;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {

    private TextToSpeech tts;
    private EditText edtText;
    private TextView txtStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtText = findViewById(R.id.edtText);
        txtStatus = findViewById(R.id.txtStatus);
        Button btnSpeak = findViewById(R.id.btnSpeak);

        // Khởi tạo TextToSpeech
        tts = new TextToSpeech(this, this);

        btnSpeak.setOnClickListener(v -> {
            String text = edtText.getText().toString();
            if (!text.isEmpty()) {
                tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, null);
                txtStatus.setText("Đang đọc: " + text);
            } else {
                txtStatus.setText("Vui lòng nhập nội dung cần đọc");
            }
        });
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            int result = tts.setLanguage(Locale.getDefault());
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                txtStatus.setText("Ngôn ngữ không được hỗ trợ");
            } else {
                txtStatus.setText("TTS sẵn sàng");
            }
        } else {
            txtStatus.setText("Khởi tạo TTS thất bại");
        }
    }

    @Override
    protected void onDestroy() {
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }
}
