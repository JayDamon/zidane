package com.protean.legislativetracker.zidane.service;

import java.util.List;

public interface AsyncSupport<R> {

    void doThreadedAction(int i);

    List<R> processThreadedItems();

    void runPostThreadCleanup();

}
