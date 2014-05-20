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
    private boolean revealing;
    private boolean[] buttons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);

        Intent i = getIntent();
        players = i.getStringArrayListExtra("players");
        if (players == null) {
            players = new ArrayList<String>();
        }

        game = new GameState();

        buttons = i.getBooleanArrayExtra("buttons");
        if (buttons != null) {
            if (buttons[0]) {
                ((ToggleButton) findViewById(R.id.merlin)).setChecked(true);
                game.toggleMerlin();
            }

            if (buttons[1]) {
                ((ToggleButton) findViewById(R.id.percival)).setChecked(true);
                game.togglePercival();
            }

            if (buttons[2]) {
                ((ToggleButton) findViewById(R.id.mordred)).setChecked(true);
                game.toggleMordred();
            }

            if (buttons[3]) {
                ((ToggleButton) findViewById(R.id.oberon)).setChecked(true);
                game.toggleOberon();
            }

            if (buttons[4]) {
                ((ToggleButton) findViewById(R.id.morgana)).setChecked(true);
                game.toggleMorgana();
            }
        } else {
            buttons = new boolean[5];
            for (int j = 0; j < 5; j++) {
                buttons[j] = false;
            }
        }

        Integer[] playerObjects = {R.id.player1, R.id.player2, R.id.player3, R.id.player4, R.id.player5, R.id.player6, R.id.player7, R.id.player8, R.id.player9, R.id.player10};
        playerIDs = Arrays.asList(playerObjects);
        Integer[] spyObjects = {R.id.spy1, R.id.spy2, R.id.spy3, R.id.spy4};
        spyIDs = Arrays.asList(spyObjects);

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
        revealing = false;
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
        revealing = true;
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
        if (!revealing) {
            game.toggleMerlin();
            buttons[0] = !buttons[0];
        } else {
            ((ToggleButton) findViewById(R.id.merlin)).setChecked(buttons[0]);
        }
    }

    public void onClickPercival(View view) {
        if (!revealing) {
            game.togglePercival();
            buttons[1] = !buttons[1];
        } else {
            ((ToggleButton) findViewById(R.id.percival)).setChecked(buttons[1]);
        }
    }

    public void onClickMordred(View view) {
        if (!revealing) {
            game.toggleMordred();
            buttons[2] = !buttons[2];
        } else {
            ((ToggleButton) findViewById(R.id.mordred)).setChecked(buttons[2]);
        }
    }

    public void onClickOberon(View view) {
        if (!revealing) {
            game.toggleOberon();
            buttons[3] = !buttons[3];
        } else {
            ((ToggleButton) findViewById(R.id.oberon)).setChecked(buttons[3]);
        }
    }

    public void onClickMorgana(View view) {
        if (!revealing) {
            game.toggleMorgana();
            buttons[4] = !buttons[4];
        } else {
            ((ToggleButton) findViewById(R.id.morgana)).setChecked(buttons[4]);
        }
    }

    public void onClickGoPlay(View view) {
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        i.putExtra("players", players);
        i.putExtra("buttons", buttons);
        startActivity(i);
    }
}

