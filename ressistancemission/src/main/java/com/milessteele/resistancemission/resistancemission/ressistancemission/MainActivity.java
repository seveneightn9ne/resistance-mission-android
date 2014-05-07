package com.milessteele.resistancemission.resistancemission.ressistancemission;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends Activity {
    private static final String TAG = "MainActivity";

    private int passes = 0;
    private int fails = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setInfo(String info) {
        TextView text = (TextView) findViewById(R.id.info);
        text.setText(info);
    }

    public void onClickPass(View view) {
        Log.d(TAG, "button.pass");
        passes++;
        setInfo("");
    }

    public void onClickFail(View view) {
        Log.d(TAG, "button.fail");
        fails++;
        setInfo("");
    }

    public void onClickReveal(View view) {
        Log.d(TAG, "button.reveal");
        Log.d(TAG, "passes: " + passes);
        Log.d(TAG, "fails: " + fails);
        setInfo("passes: " + passes + "  fails: " + fails);
        passes = 0;
        fails = 0;
    }

}
