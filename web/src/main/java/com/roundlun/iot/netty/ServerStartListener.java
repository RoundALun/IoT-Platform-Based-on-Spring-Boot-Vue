package com.roundlun.iot.netty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class ServerStartListener implements ApplicationRunner {

    @Autowired
    private PicoIdealServer picoIdealServer;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        this.picoIdealServer.start();
    }
}
