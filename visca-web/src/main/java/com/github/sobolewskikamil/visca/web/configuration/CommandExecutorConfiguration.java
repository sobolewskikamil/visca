package com.github.sobolewskikamil.visca.web.configuration;

import com.github.sobolewskikamil.visca.communication.ViscaCommandExecutor;
import jssc.SerialPort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommandExecutorConfiguration {
    @Value("${comm}")
    private String comm;

    @Bean
    public ViscaCommandExecutor viscaCommandExecutor() throws Exception {
        return new ViscaCommandExecutor(new SerialPort(comm));
    }
}
