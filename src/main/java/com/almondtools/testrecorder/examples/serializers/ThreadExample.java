package com.almondtools.testrecorder.examples.serializers;

import net.amygdalum.testrecorder.Recorded;

public class ThreadExample {
    
    public State state;
    
    public ThreadExample() {
        state = State.INITIAL;
    }
    
    public State getState() {
        return state;
    }

    public static void main(String[] args) {
        new ThreadExample()
        .run();
    }
    
    public void run() {
        Runnable runnable = new Runnable() {

            @Override
            public void run() {
                state = State.FINISHED;
            }
            
        };
        runThread(new Thread(runnable));
    }
    
    @Recorded
    public void runThread(Thread thread) {
        thread.run();
        try {
            thread.join();
        } catch (InterruptedException e) {
        }
    }

    public enum State {
        INITIAL, FINISHED;
    }
}
