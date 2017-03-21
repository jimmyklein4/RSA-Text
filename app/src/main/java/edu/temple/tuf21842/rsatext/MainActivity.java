package edu.temple.tuf21842.rsatext;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends Activity {
    private final String TAG = "MainActivity";
    private ContentResolver cr;
    private TextView publicKeyTextView;
    private String publicKey = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cr = getContentResolver();
        Button generate = (Button) findViewById(R.id.generate);
        publicKeyTextView = (TextView) findViewById(R.id.public_key);
        generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        cr.update(RSAContentProvider.CONTENT_URI, null, null,null);
                        Cursor c = cr.query(RSAContentProvider.CONTENT_URI, null, null, null, null);
                        c.moveToFirst();
                        publicKey = c.getString(0);
                        keyHandler.sendEmptyMessage(0);
                    }
                }).start();
            }
        });
    }

    private Handler keyHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            publicKeyTextView.setText(publicKey);
            return false;
        }
    });
}
