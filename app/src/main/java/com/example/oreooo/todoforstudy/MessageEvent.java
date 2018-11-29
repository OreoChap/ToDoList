package com.example.oreooo.todoforstudy;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
