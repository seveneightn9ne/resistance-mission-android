package com.milessteele.resistancemission.resistancemission.ressistancemission;

import java.util.ArrayList;

public class GameState {
    private ArrayList<String> players;
    private ArrayList<String> playersCopy;
    private ArrayList<String> spiesCopy;
    private boolean useMerlin;
    private boolean usePercival;
    private boolean useMordred;
    private boolean useOberon;
    private boolean useMorgana;
    private boolean passTo;
    private int curPlayer;
    private String merlin;
    private String percival;
    private String mordred;
    private String oberon;
    private String morgana;
    private ArrayList<String> spies;

    public GameState() {
        this.players = new ArrayList<String>();
        this.useMerlin = false;
        this.usePercival = false;
        this.useMordred = false;
        this.useOberon = false;
        this.useMorgana = false;
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
        percival = "";
        mordred = "";
        oberon = "";
        morgana = "";
        spies = new ArrayList<String>();

        if (players.size() >= 5 && !((useMordred && useOberon && useMorgana) && players.size() < 7)) {
            int[] numSpies = {0, 0, 0, 0, 0, 2, 2, 3, 3, 3, 4};

            for (int i = 0; i < numSpies[players.size()]; i++) {
                int spy = (int)(Math.random() * playersCopy.size());
                spies.add(playersCopy.get(spy));
                playersCopy.remove(spy);
            }

            spiesCopy = new ArrayList<String>();
            for (String s : spies) {
                spiesCopy.add(s);
            }

            if (useMerlin) {
                merlin = playersCopy.get((int)(Math.random() * playersCopy.size()));
                playersCopy.remove(merlin);
            }

            if (usePercival) {
                percival = playersCopy.get((int)(Math.random() * playersCopy.size()));
                playersCopy.remove(percival);
            }

            if (useMordred) {
                mordred = spiesCopy.get((int)(Math.random() * spiesCopy.size()));
                spiesCopy.remove(mordred);
            }

            if (useOberon) {
                oberon = spiesCopy.get((int)(Math.random() * spiesCopy.size()));
                spiesCopy.remove(oberon);
            }

            if (useMorgana) {
                morgana = spiesCopy.get((int)(Math.random() * spiesCopy.size()));
                spiesCopy.remove(morgana);
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
                v.add("Merlin");
            } else if (percival.equals(player)) {
                v.add("Percival");
            } else if (mordred.equals(player)) {
                v.add("Mordred");
            } else if (oberon.equals(player)) {
                v.add("Oberon");
            } else if (morgana.equals(player)) {
                v.add("Morgana");
            } else if (spies.indexOf(player) != -1) {
                v.add("a spy");
            } else {
                v.add ("resistance");
            }

            v.add(player);
            v.add("");

            if (player.equals(merlin)) {
                v.add("Spies:");
                int extras = 0;
                for (int i = 0; i < 4; i++) {
                    // Merlin doesn't know Mordred
                    if (i < spies.size() && !spies.get(i).equals(mordred)) {
                        v.add(spies.get(i));
                    } else {
                        extras += 1;
                    }
                }
                for (int i = 0; i < extras; i++) {
                    v.add("");
                }
            } else if (player.equals(percival) && useMerlin) {
                // Percival sees Merlin and Morgana if present
                if (useMorgana) {
                    v.add("Merlins:");
                    if ((int) (Math.random() * 2) == 0) {
                        v.add(merlin);
                        v.add(morgana);
                    } else {
                        v.add(morgana);
                        v.add(merlin);
                    }
                    v.add("");
                    v.add("");
                } else {
                    v.add("Merlin:");
                    v.add(merlin);
                    v.add("");
                    v.add("");
                    v.add("");
                }
            } else if (player.equals(oberon)) {
                // Oberon doesn't know who other spies are
                v.add("");
                v.add("");
                v.add("");
                v.add("");
                v.add("");
            } else if (spies.indexOf(player) != -1) {
                v.add("Spies:");
                int extras = 0;
                for (int i = 0; i < 4; i++) {
                    // Spies don't know who Oberon is
                    if (i < spies.size() && !spies.get(i).equals(player) &&!spies.get(i).equals(oberon)) {
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

    public void togglePercival() {
        this.usePercival = !this.usePercival;
    }

    public void toggleMordred() {
        this.useMordred = !this.useMordred;
    }

    public void toggleOberon() {
        this.useOberon = !this.useOberon;
    }

    public void toggleMorgana() {
        this.useMorgana = !this.useMorgana;
    }

}
