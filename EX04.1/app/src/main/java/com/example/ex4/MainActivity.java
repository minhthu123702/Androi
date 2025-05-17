package com.example.ex4;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.text.DecimalFormat;

public class MainActivity extends Activity {
    EditText editdoC, editdoF;
    Button btncf, btnfc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editdoC = findViewById(R.id.editdoC);
        editdoF = findViewById(R.id.editdoF);
        btncf = findViewById(R.id.btncf);
        btnfc = findViewById(R.id.btnfc);


        btncf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    DecimalFormat dcf = new DecimalFormat("#.00");
                    String doC = editdoC.getText()+" ";
                    int C = Integer.parseInt(doC);
                    double F = C * 1.8 + 32;
                    editdoF.setText(dcf.format(F));

            }
        });


        btnfc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    DecimalFormat dcf = new DecimalFormat("#.00");
                    String doF = editdoF.getText().toString();
                    int F = Integer.parseInt(doF);
                    double C = (F - 32) / 1.8;
                    editdoC.setText(dcf.format(C));

            }
        });
    }
}
