package de.slowloris.community.v2.utils.events;

public class EventManager {
    private CommunityEvent event;
    private boolean running;

    public EventManager() {
        this.running = false;
    }

    public void setEvent(CommunityEvent event) {
        boolean restart = false;
        if(isRunning()){
            this.event.stop();
            restart = true;
        }
        this.event = event;
        if(restart) {
            this.event.start();
        }
    }

    public CommunityEvent getEvent() {
        return event;
    }

    public void startEvent(){
        this.event.start();
        setRunning(true);
    }

    public void stopEvent(){
        this.event.stop();
        setRunning(false);
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }
}
