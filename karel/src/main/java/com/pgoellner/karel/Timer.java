package com.pgoellner.karel;

final class Timer {
    private Timer() {}

    public static void pause(long forMillis) {
        try {
            Thread.sleep(forMillis);
        } catch (InterruptedException interruptedException) {
            System.err.printf("Something really bad went wrong: %s%n", interruptedException);
            System.err.println("Please get mad at the developer");
        }
    }
}
