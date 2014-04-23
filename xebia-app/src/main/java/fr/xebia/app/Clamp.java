package fr.xebia.app;

public final class Clamp {

    private Clamp() {
    }

    public static float clamp(float value, float max, float min) {
        return Math.max(Math.min(value, min), max);
    }
}
