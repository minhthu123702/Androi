package com.example.myapplication;

import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Currency;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    DatabaseReference ref;
    List<Player> players;
    PlayerAdapter adapter;
    RecyclerView recyclerView;
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Calendar FirebaseDatabase = null;
        ref = FirebaseDatabase.getInstance().getReference("players");
        players = new ArrayList<>();
        adapter = new PlayerAdapter(this, players);
        recyclerView = findViewById(R.id.recyclerView);
        btnAdd = findViewById(R.id.btnAdd);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

            public void onDataChange(@NonNull DataSnapshot snapshot) {
                players.clear();
                for (DataSnapshot snap : snapshot.getChildren()) {
                    Player p = snap.getValue(Player.class);
                    players.add(p);
                }
                adapter.notifyDataSetChanged();
            }

            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainActivity.this, "Lỗi đọc dữ liệu", Toast.LENGTH_SHORT).show();
            }
        });

        btnAdd.setOnClickListener(v -> showAddDialog());
    }

    private void showAddDialog() {
        // Tạo AlertDialog nhập tên, quê quán, tự sinh member_code, push lên Firebase
    }
}
