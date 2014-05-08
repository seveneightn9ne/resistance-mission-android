package com.milessteele.resistancemission.resistancemission.ressistancemission;

/**
 * Immutable state of a mission.
 */
public class MissionState {
    private int passes;
    private int fails;

    public MissionState() {
        this(0, 0);
    }

    private MissionState(int passes, int fails) {
        this.passes = passes;
        this.fails = fails;
    }

    public MissionState addPass() {
        return new MissionState(getPasses() + 1, getFails());
    }

    public MissionState addFail() {
        return new MissionState(getPasses(), getFails() + 1);
    }

    public int getPasses() {
        return passes;
    }

    public int getFails() {
        return fails;
    }
}
