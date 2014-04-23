package fr.xebia.app.event;

public class NetworkErrorEvent {

    public final Throwable error;

    public NetworkErrorEvent(Throwable throwable) {
        error = throwable;
    }
}
