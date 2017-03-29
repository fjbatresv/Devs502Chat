package com.gt.fjbatresv.devs502.lib;

import com.gt.fjbatresv.devs502.lib.base.EventBus;

/**
 * Created by javie_000 on 8/29/2016.
 */
public class GreenRobotEventBus implements EventBus {
    private org.greenrobot.eventbus.EventBus bus;

    public GreenRobotEventBus(org.greenrobot.eventbus.EventBus bus) {
        this.bus = bus;
    }

    @Override
    public void register(Object sub) {
        bus.register(sub);
    }

    @Override
    public void unRegister(Object sub) {
        bus.unregister(sub);
    }

    @Override
    public void post(Object sub) {
        bus.post(sub);
    }
}
