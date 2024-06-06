package net.thomas.tutorialmod.util;

public class Stopwatch {
    private long startTime;
    private long endTime;
    private boolean running;

    public Stopwatch() {
        this.running = false;
    }

    public void start() {
        this.startTime = System.nanoTime();
        this.running = true;
    }

    public void stop() {
        this.endTime = System.nanoTime();
        this.running = false;
    }

    public void reset() {
        this.startTime = 0;
        this.endTime = 0;
        this.running = false;
    }

    public long getElapsedTime() {
        long elapsed;
        if (running) {
            elapsed = System.nanoTime() - startTime;
        } else {
            elapsed = endTime - startTime;
        }
        return elapsed;
    }

    public double getElapsedTimeInSeconds() {
        return getElapsedTime() / 1_000_000_000.0;
    }
}
