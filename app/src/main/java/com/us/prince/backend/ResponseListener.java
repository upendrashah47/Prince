package com.us.prince.backend;

public interface ResponseListener {
    // API Response Listener
    void onResponce(String tag, int result, Object obj);
}
