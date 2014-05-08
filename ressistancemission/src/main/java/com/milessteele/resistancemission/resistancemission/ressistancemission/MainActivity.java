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

    private int passes;
    private int fails;
    private int undo_passes;
    private int undo_fails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        passes = 0;
        fails = 0;
        undo_passes = 0;
        undo_fails = 0;
        updateVotesTable(false);
        findViewById(R.id.undo).setVisibility(View.GONE);
        findViewById(R.id.reset).setVisibility(View.GONE);
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

    private void updateVotesTable(boolean showResults) {
        ((TextView) findViewById(R.id.nVotes)).setText("" + (passes + fails));
        if (showResults) {
            ((TextView) findViewById(R.id.nPasses)).setText("" + passes);
            ((TextView) findViewById(R.id.nFails)).setText("" + fails);
        } else {
            ((TextView) findViewById(R.id.nPasses)).setText("X");
            ((TextView) findViewById(R.id.nFails)).setText("X");
        }
    }

    public void onClickPass(View view) {
        passes++;
        undo_passes = 1;
        undo_fails = 0;
        updateVotesTable(false);
        findViewById(R.id.show).setVisibility(View.VISIBLE);
        findViewById(R.id.reset).setVisibility(View.GONE);
        findViewById(R.id.undo).setVisibility(View.VISIBLE);
    }

    public void onClickFail(View view) {
        fails++;
        undo_passes = 0;
        undo_fails = 1;
        updateVotesTable(false);
        findViewById(R.id.show).setVisibility(View.VISIBLE);
        findViewById(R.id.reset).setVisibility(View.GONE);
        findViewById(R.id.undo).setVisibility(View.VISIBLE);
    }

    public void onClickShow(View view) {
        updateVotesTable(true);
        findViewById(R.id.show).setVisibility(View.GONE);
        findViewById(R.id.reset).setVisibility(View.VISIBLE);
    }

    public void onClickReset(View view) {
        passes = 0;
        fails = 0;
        undo_passes = 0;
        undo_fails = 0;
        updateVotesTable(false);
        findViewById(R.id.show).setVisibility(View.VISIBLE);
        findViewById(R.id.reset).setVisibility(View.GONE);
        findViewById(R.id.undo).setVisibility(View.GONE);
    }

    public void onClickUndo(View view) {
        passes += undo_passes;
        fails += undo_fails;
        updateVotesTable(false);
        findViewById(R.id.show).setVisibility(View.VISIBLE);
        findViewById(R.id.reset).setVisibility(View.GONE);
        findViewById(R.id.undo).setVisibility(View.GONE);
    }
}
