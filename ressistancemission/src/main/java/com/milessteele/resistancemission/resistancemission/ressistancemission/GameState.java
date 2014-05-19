package com.milessteele.resistancemission.resistancemission.ressistancemission;

import java.util.ArrayList;

public class GameState {
    private ArrayList<String> players;
    private ArrayList<String> playersCopy;
    private boolean useMerlin;
    private boolean passTo;
    private int curPlayer;
    private String merlin;
    private ArrayList<String> spies;

    public GameState() {
        this.players = new ArrayList<String>();
        this.useMerlin = true;
    }

    public void start(ArrayList<String> players) {
        this.players = players;
        curPlayer = 0;
        passTo = true;
        playersCopy = new ArrayList<String>();
        for (String p : players) {
            playersCopy.add(p);
        }
        merlin = "";
        spies = new ArrayList<String>();

        if (players.size() >= 5) {
            int[] numSpies = {0, 0, 0, 0, 0, 2, 2, 3, 3, 3, 4};

            for (int i = 0; i < numSpies[players.size()]; i++) {
                int spy = (int)(Math.random() * playersCopy.size());
                spies.add(playersCopy.get(spy));
                playersCopy.remove(spy);
            }

            if (useMerlin) {
                merlin = playersCopy.get((int)(Math.random() * playersCopy.size()));
            }
        }
    }

    public ArrayList<String> next() {
        ArrayList<String> v = new ArrayList<String>();

        if (passTo) {
            if (curPlayer == players.size()) {
                v.add("");
                v.add("");
                v.add("");
                v.add("");
                v.add("");

                v.add("");
                v.add("");
                v.add("");
                v.add("");
            } else {
                passTo = !passTo;

                v.add("");
                v.add("");
                v.add("Pass to");
                v.add(players.get(curPlayer));
                v.add("");

                v.add("");
                v.add("");
                v.add("");
                v.add("");
            }
        } else {
            String player = players.get(curPlayer);
            curPlayer += 1;
            passTo = !passTo;

            v.add("You are ");
            if (merlin.equals(player)) {
                v.add("merlin");
            } else if (spies.indexOf(player) != -1) {
                v.add("a spy");
            } else {
                v.add ("resistance");
            }

            v.add(player);
            v.add("");

            if (player.equals(merlin)) {
                v.add("Spies:");
                for (int i = 0; i < 4; i++) {
                    if (i < spies.size()) {
                        v.add(spies.get(i));
                    } else {
                        v.add("");
                    }
                }
            } else if (spies.indexOf(player) != -1) {
                v.add("Spies:");
                int extras = 0;
                for (int i = 0; i < 4; i++) {
                    if (i < spies.size() && !spies.get(i).equals(player)) {
                        v.add(spies.get(i));
                    } else {
                        extras += 1;
                    }
                }
                for (int i = 0; i < extras; i++) {
                        v.add("");
                }
            } else {
                v.add("");
                v.add("");
                v.add("");
                v.add("");
                v.add("");
            }
        }

        return v;
    }

    public void toggleMerlin() {
        this.useMerlin = !this.useMerlin;
    }
}
