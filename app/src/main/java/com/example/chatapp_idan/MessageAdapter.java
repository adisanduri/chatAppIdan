package com.example.chatapp_idan;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessageAdapter extends RecyclerView.Adapter<MessageViewHolder> {
    private List<itemMessage> messageList = new ArrayList<>();
    private String userId;

    public MessageAdapter(String userId) {
        this.userId = userId;
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("chat").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    messageList = new ArrayList<>();
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        try {
                            itemMessage m = new itemMessage(
                                    document.get("userId").toString(),
                                    document.get("userName").toString(),
                                    document.get("userPhoto").toString(),
                                    document.get("message").toString(),
                                    document.get("time").toString()
                            );
                            messageList.add(m);
                        } catch (Exception e) {
                        }
                    }
                    notifyDataSetChanged();
                }
            }
        });
        db.collection("chat").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                messageList = new ArrayList<>();
                for (DocumentSnapshot document : value.getDocuments()) {
                    try {
                        itemMessage m = new itemMessage(
                                document.get("userId").toString(),
                                document.get("userName").toString(),
                                document.get("userPhoto").toString(),
                                document.get("message").toString(),
                                document.get("time").toString()
                        );
                        messageList.add(m);
                    } catch (Exception e) {
                    }
                }
                notifyDataSetChanged();
            }
        });
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message, parent, false);
        return new MessageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {
        itemMessage message = messageList.get(position);
        if (message.userId.equals(userId)) {
            holder.messageCard_m.setVisibility(View.VISIBLE);
            holder.messageCard.setVisibility(View.GONE);
            holder.messageTextView_m.setText(message.message);
            holder.userNameTextView_m.setText(message.userName);
            holder.timeTextView_m.setText(message.time);
            Glide.with(holder.profileImageView_m.getContext()).load(message.userPhoto).into(holder.profileImageView_m);

        } else {
            holder.messageCard_m.setVisibility(View.GONE);
            holder.messageCard.setVisibility(View.VISIBLE);
            holder.messageTextView.setText(message.message);
            holder.userNameTextView.setText(message.userName);
            holder.timeTextView.setText(message.time);
            Glide.with(holder.profileImageView.getContext()).load(message.userPhoto).into(holder.profileImageView);
        }
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    public void addMessage(itemMessage m) {
        // Create a SimpleDateFormat object with the desired format
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");

        // Get the current date and time
        Date currentTime = new Date();

        // Format the date and time using the SimpleDateFormat object
        String formattedTime = dateFormat.format(currentTime);

        Map<String, Object> message = new HashMap<>();
        message.put("userId", m.userId);
        message.put("userPhoto", m.userPhoto);
        message.put("userName", m.userName);
        message.put("message",m.message);
        message.put("time", formattedTime);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("chat").document(String.valueOf(System.currentTimeMillis()))
                .set(message);
    }
}
