package net.tedhome.chatdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ChatActivity extends AppCompatActivity {

    static String TAG_CHAT = "[CHAT]";
    private RecyclerView rv;
    private ChatAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    EditText et_Chat;
    Button btn_Send;

    FirebaseDatabase database;
    DatabaseReference myRef;
    String key;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        init();
    }

    void init() {
       String[] dataSet = {"a", "b", "c"};

        rv = (RecyclerView) findViewById(R.id.rv_chat);
        rv.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);
        mAdapter = new ChatAdapter(dataSet);
        rv.setAdapter(mAdapter);
        et_Chat = (EditText)findViewById(R.id.et_chat);
        btn_Send = (Button) findViewById(R.id.btn_send_chat);
        btn_Send.setOnClickListener((v)-> SendChat(et_Chat.getText().toString()));

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference().child("message");
        Log.d(TAG_CHAT, " ==== Key=" + key );
    }

    void SendChat(String msg){
// Write a message to the database
        Log.d(TAG_CHAT, " ==== SendChat ====");
        myRef.setValue("test 1234");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Log.d(TAG_CHAT, "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG_CHAT, "Failed to read value.", error.toException());
            }
        });
    }
}
