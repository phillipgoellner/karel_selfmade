package com.pgoellner.karel;

public class KarelSpeedSetting {
    private int speed;

    public KarelSpeedSetting(int initialSpeed) {
        speed = initialSpeed;
    }

    public int value() {
        return speed;
    }

    public void newValue(int newValue) {
        speed = newValue;
    }
}
