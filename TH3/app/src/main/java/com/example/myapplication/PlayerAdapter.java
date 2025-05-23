package com.example.myapplication;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Calendar;
import java.util.List;

public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder> {
    private List<Player> players;
    private Context context;
    private DatabaseReference ref;

    public PlayerAdapter(Context context, List<Player> players) {
        this.context = context;
        this.players = players;
        Calendar FirebaseDatabase;
        ref = FirebaseDatabase.getInstance().getReference("players");
    }

    @NonNull
    @Override
    public PlayerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_player, parent, false);
        return new PlayerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayerViewHolder holder, int position) {
        Player p = players.get(position);
        holder.txtName.setText(p.username);
        holder.txtCode.setText(p.member_code);
        holder.txtHometown.setText("Quê: " + p.hometown);

        holder.btnDelete.setOnClickListener(v -> {
            ref.child(p.member_code).removeValue();
        });

        holder.btnEdit.setOnClickListener(v -> {
            // Mở Dialog sửa (tự triển khai)
        });
    }

    @Override
    public int getItemCount() {
        return players.size();
    }

    public class PlayerViewHolder extends RecyclerView.ViewHolder {
        TextView txtName, txtCode, txtHometown;
        Button btnEdit, btnDelete;
        ImageView imgAvatar;

        public PlayerViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            txtCode = itemView.findViewById(R.id.txtCode);
            txtHometown = itemView.findViewById(R.id.txtHometown);
            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnDelete = itemView.findViewById(R.id.btnDelete);
            imgAvatar = itemView.findViewById(R.id.imgAvatar);
        }
    }
}
