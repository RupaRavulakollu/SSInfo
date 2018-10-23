package com.citchennai.dous.careercompass;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainChatActivity extends AppCompatActivity {
DatabaseReference reference,accessReference;
RecyclerView recyclerView;
EditText message;
Button send;
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_chatscreen);
        if (getSharedPreferences("user", Context.MODE_PRIVATE).getString("name","value").equals("value")){
                    finish();
                    startActivity(new Intent(MainChatActivity.this,RegisterPage.class));
                    return;
                }
        message = findViewById(R.id.messagebox);
        send = findViewById(R.id.send);


        recyclerView = findViewById(R.id.chatview);
        recyclerView.hasFixedSize();
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setStackFromEnd(true);
        recyclerView.setLayoutManager(manager);
        accessReference = FirebaseDatabase.getInstance().getReference("Users");
        reference = FirebaseDatabase.getInstance().getReference("messages");
        name = getSharedPreferences("user", Context.MODE_PRIVATE).getString("name","value");
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = message.getText().toString();
                final DatabaseReference newPost = reference.push();
                 if (!TextUtils.isEmpty(msg)){
                     InputMethodManager manager= (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                     manager.hideSoftInputFromWindow(message.getWindowToken(),0);
                            message.setText("");
                            newPost.child("content").setValue(msg);
                            //newPost.child("time").setValue()
                            newPost.child("username").setValue(name).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Toast.makeText(MainChatActivity.this, "Message Sent Successfull", Toast.LENGTH_SHORT).show();
                                }
                            });
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<Message, MessageViewHolder> adapter = new FirebaseRecyclerAdapter<Message, MessageViewHolder>(
                Message.class,
                R.layout.activity_chatlayout,
                MessageViewHolder.class,
                reference
        ) {
            @Override
            protected void populateViewHolder(MessageViewHolder viewHolder, Message model, int position) {
                viewHolder.setContent(model.getContent());
                viewHolder.setUsername(model.getUsername());
                if (model.getUsername()==name){
                    viewHolder.setGravity();
                }
            }
        };
        recyclerView.setAdapter(adapter);
    }

    public static class MessageViewHolder extends RecyclerView.ViewHolder{
    View mView;
    public MessageViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
    }
    public void setContent(String message){
        TextView textView = mView.findViewById(R.id.message);
        textView.setText(message);
    }
        public void setUsername(String username){
            TextView textView = mView.findViewById(R.id.name);
            textView.setText(username);
        }
        public void setGravity(){
            ((LinearLayout)mView).setGravity(Gravity.RIGHT);
        }
}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
