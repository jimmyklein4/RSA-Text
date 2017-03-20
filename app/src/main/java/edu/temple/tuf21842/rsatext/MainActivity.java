package edu.temple.tuf21842.rsatext;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity {
    private final String TAG = "MainActivity";
    ContentResolver cr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cr = getContentResolver();
        Cursor c = cr.query(RSAContentProvider.CONTENT_URI, null, null, null, null);
        Log.d(TAG, c.getColumnName(0));
        setContentView(R.layout.activity_main);
    }
}
