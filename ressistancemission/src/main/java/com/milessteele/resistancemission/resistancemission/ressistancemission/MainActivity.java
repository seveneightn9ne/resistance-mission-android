package com.milessteele.resistancemission.resistancemission.ressistancemission;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.content.Intent;
import java.util.ArrayList;

public class MainActivity extends Activity {
    private static final String TAG = "MainActivity";

    private MissionState mission;
    private boolean revealed;
    private boolean undoable;
    private ArrayList<String> players;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent i = getIntent();
        players = i.getStringArrayListExtra("players");
        if (players == null) {
            players = new ArrayList<String>();
        }

        String [][] missionNums = {{"0", "0", "0", "0", "0"},
                                   {"0", "0", "0", "0", "0"},
                                   {"0", "0", "0", "0", "0"},
                                   {"0", "0", "0", "0", "0"},
                                   {"0", "0", "0", "0", "0"},
                                   {"2", "3", "2", "3", "3"},
                                   {"2", "3", "4", "3", "4"},
                                   {"2", "3", "3", "4*", "4"},
                                   {"3", "4", "4", "5*", "5"},
                                   {"3", "4", "4", "5*", "5"},
                                   {"3", "4", "4", "5*", "5"}};

        ((TextView) findViewById(R.id.title)).setText("   Resistance Mission \n          " +
                                                       missionNums[players.size()][0] + "  " +
                                                       missionNums[players.size()][1] + "  " +
                                                       missionNums[players.size()][2] + "  " +
                                                       missionNums[players.size()][3] + "  " +
                                                       missionNums[players.size()][4]);


        mission = new MissionState();
        revealed = false;
        undoable = true;
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
        if (!revealed) {
            mission.addPass();
            revealed = false;
            updateDisplay();
        }
    }

    public void onClickFail(View view) {
        if (!revealed) {
            mission.addFail();
            revealed = false;
            updateDisplay();
        }
    }

    public void onClickShow(View view) {
        revealed = true;
        updateDisplay();
    }

    public void onClickReset(View view) {
        mission.reset();
        revealed = false;
        updateDisplay();
    }

    public void onClickUndo(View view) {
        if (!revealed) {
            mission.undo();
            updateDisplay();
        }
    }

    public void onClickSetup(View view) {
        Intent i = new Intent(getApplicationContext(), SetupActivity.class);
        i.putExtra("players", players);
        startActivity(i);
    }
}
