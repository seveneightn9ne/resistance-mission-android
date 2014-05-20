package com.milessteele.resistancemission.resistancemission.ressistancemission;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import android.widget.Button;
import android.widget.ToggleButton;

public class SetupActivity extends Activity {
    private static final String TAG = "SetupActivity";

    private ArrayList<String> players;
    private List<Integer> playerIDs;
    private List<Integer> spyIDs;
    private GameState game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);

        Intent i = getIntent();
        players = i.getStringArrayListExtra("players");
        if (players == null) {
            players = new ArrayList<String>();
        }

        Integer[] playerObjects = {R.id.player1, R.id.player2, R.id.player3, R.id.player4, R.id.player5, R.id.player6, R.id.player7, R.id.player8, R.id.player9, R.id.player10};
        playerIDs = Arrays.asList(playerObjects);
        Integer[] spyObjects = {R.id.spy1, R.id.spy2, R.id.spy3, R.id.spy4};
        spyIDs = Arrays.asList(spyObjects);
        game = new GameState();
        // turn on Merlin by default
       //  ((ToggleButton) findViewById(R.id.merlin)).setChecked(true);
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
        ((TextView) findViewById(R.id.youAre)).setText("");
        ((TextView) findViewById(R.id.role)).setText("");
        ((TextView) findViewById(R.id.pass)).setText("");
        ((TextView) findViewById(R.id.passTo)).setText("");
        ((TextView) findViewById(R.id.spies)).setText("");
        ((TextView) findViewById(R.id.next)).setText("Start");
        findViewById(R.id.reset).setVisibility(View.INVISIBLE);

        for (Integer s : spyIDs) {
            ((TextView) findViewById(s)).setText("");
        }
        for (Integer p : playerIDs) {
            ((TextView) findViewById(p)).setText("");
        }
        for (String p : players) {
            ((TextView) findViewById(playerIDs.get(players.indexOf(p)))).setText(p);
        }
    }

    public void updateDisplay(ArrayList<String> values) {
        ((TextView) findViewById(R.id.youAre)).setText(values.get(0));
        ((TextView) findViewById(R.id.role)).setText(values.get(1));
        ((TextView) findViewById(R.id.pass)).setText(values.get(2));
        ((TextView) findViewById(R.id.passTo)).setText(values.get(3));
        ((TextView) findViewById(R.id.spies)).setText(values.get(4));

        for (Integer s : spyIDs) {
            ((TextView) findViewById(s)).setText("");
        }

        ((TextView) findViewById(R.id.spy1)).setText(values.get(5));
        ((TextView) findViewById(R.id.spy2)).setText(values.get(6));
        ((TextView) findViewById(R.id.spy3)).setText(values.get(7));
        ((TextView) findViewById(R.id.spy4)).setText(values.get(8));

        for (Integer p : playerIDs) {
            ((TextView) findViewById(p)).setText("");
        }
        for (String p : players) {
            ((TextView) findViewById(playerIDs.get(players.indexOf(p)))).setText(p);
        }
    }

    public void onClickPlayer(View view) {
        Button b = (Button)view;
        if (players.indexOf(b.getText().toString()) == -1) {
            players.add(b.getText().toString());
        }
        updateDisplay();
    }

    public void onClickPlayerReset(View view) {
        players = new ArrayList<String>();
        updateDisplay();
    }

    public void onClickNext(View view) {
        if (((TextView) findViewById(R.id.next)).getText().toString().equals("Start")) {
            ((TextView) findViewById(R.id.next)).setText("Next");
            findViewById(R.id.reset).setVisibility(View.VISIBLE);
            game.start(players);
        }
        updateDisplay(game.next());
    }

    public void onClickReset(View view) {
        ((TextView) findViewById(R.id.next)).setText("Start");
        findViewById(R.id.reset).setVisibility(View.INVISIBLE);
        updateDisplay();
    }

    public void onClickMerlin(View view) {
        game.toggleMerlin();
    }

    public void onClickPercival(View view) {
        game.togglePercival();
    }

    public void onClickMordred(View view) {
        game.toggleMordred();
    }

    public void onClickOberon(View view) {
        game.toggleOberon();
    }

    public void onClickMorgana(View view) {
        game.toggleMorgana();
    }


    public void onClickGoPlay(View view) {
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        i.putExtra("players", players);
        startActivity(i);
    }
}

