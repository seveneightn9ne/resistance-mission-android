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

    private MissionState mission;
    private boolean revealed;
    private boolean undoable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mission = new MissionState();
        revealed = false;
        undoable = false;
        updateDisplay();
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

    public void updateDisplay() {
        ((TextView) findViewById(R.id.nVotes)).setText("" + (mission.getPasses() + mission.getFails()));

        if (revealed) {
            ((TextView) findViewById(R.id.nPasses)).setText("" + mission.getPasses());
            ((TextView) findViewById(R.id.nFails)).setText("" + mission.getFails());
            findViewById(R.id.show).setVisibility(View.GONE);
            findViewById(R.id.reset).setVisibility(View.VISIBLE);
        } else {
            ((TextView) findViewById(R.id.nPasses)).setText("X");
            ((TextView) findViewById(R.id.nFails)).setText("X");
            findViewById(R.id.show).setVisibility(View.VISIBLE);
            findViewById(R.id.reset).setVisibility(View.GONE);
        }

        if (undoable)
            findViewById(R.id.undo).setVisibility(View.VISIBLE);
        else
            findViewById(R.id.undo).setVisibility(View.GONE);
    }

    public void onClickPass(View view) {
        mission = mission.addPass();
        revealed = false;
        updateDisplay();
    }

    public void onClickFail(View view) {
        mission = mission.addFail();
        revealed = false;
        updateDisplay();
    }

    public void onClickShow(View view) {
        revealed = true;
        updateDisplay();
    }

    public void onClickReset(View view) {
        mission = new MissionState();
        revealed = false;
        updateDisplay();
    }

    public void onClickUndo(View view) {
        updateDisplay();
    }
}
