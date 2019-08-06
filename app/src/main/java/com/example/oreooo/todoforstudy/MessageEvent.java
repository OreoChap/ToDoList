package com.example.oreooo.todoforstudy;

/**
 * @author Oreo https://github.com/OreoChap
 * @date 2018/11/27
 */

public class MessageEvent {

    public static class DoneFragmentUpdateUIEvent {
        String message;

        public DoneFragmentUpdateUIEvent(String event) {
            this.message = event;
        }
    }
}
