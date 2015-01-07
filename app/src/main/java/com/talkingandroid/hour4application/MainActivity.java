package com.talkingandroid.hour4application;
import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {
    public final static int MESSAGE_REQUEST_CODE = 0;
    TextView messageTextView;
    Button getMessageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        messageTextView = (TextView)findViewById(R.id.textView);
        if (savedInstanceState!=null){
            String savedMessage=savedInstanceState.getString("saved_message");
            messageTextView.setText(savedMessage);

        }
        getMessageButton = (Button) findViewById(R.id.button);
        getMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent getResult = new Intent(getApplicationContext(), MessageActivity.class);
                startActivityForResult(getResult, MESSAGE_REQUEST_CODE);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode == MESSAGE_REQUEST_CODE){
            if (resultCode== Activity.RESULT_OK){
                String message = data.getStringExtra("MESSAGE_DATA");
                messageTextView.setText(message);
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString("saved_message", messageTextView.getText().toString());
    }

}
