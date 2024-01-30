package org.example;

import java.time.Duration;
import java.time.Instant;

public class GameTimer {
    private Instant startTime;
    private Instant endTime;
    private boolean isRunning;

    public GameTimer() {
        isRunning = false;
    }

    public void start() {
        if (!isRunning) {
            startTime = Instant.now();
            isRunning = true;
        }
    }

    public void stop() {
        if (isRunning) {
            endTime = Instant.now();
            isRunning = false;
        }
    }

    public long getElapsedTimeInSeconds() {
        if (startTime == null) {
            return 0; // Timer has not started yet
        }
        if (isRunning) {
            return Duration.between(startTime, Instant.now()).getSeconds();
        } else {
            if (endTime == null) {
                return Duration.between(startTime, Instant.now()).getSeconds(); // Timer stopped but endTime not set
            }
            return Duration.between(startTime, endTime).getSeconds();
        }
    }
}
