package com.milessteele.resistancemission.resistancemission.ressistancemission;
import java.util.ArrayList;

public class MissionState {
    private ArrayList<Integer> votes;

    public MissionState() {
        this.votes = new ArrayList<Integer>();
    }

    public void addPass() {
        this.votes.add(1);
    }

    public void addFail() {
        this.votes.add(0);
    }

    public int getPasses() {
        int passes = 0;
        for (Integer i : this.votes) {
            if (i.equals(1)) {
                passes += 1;
            }
        }
        return passes;
    }

    public int getFails() {
        int fails = 0;
        for (Integer i : this.votes) {
            if (i.equals(0)) {
                fails += 1;
            }
        }
        return fails;
    }

    public void reset() {
        this.votes = new ArrayList<Integer>();
    }

    public void undo() {
        if (this.votes.size() > 0) {
            this.votes.remove(this.votes.size() - 1);
        }
    }
}
