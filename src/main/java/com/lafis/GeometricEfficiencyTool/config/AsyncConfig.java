package com.lafis.GeometricEfficiencyTool.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync
public class AsyncConfig {

    @Bean(name = "simulationExecutor")
    public Executor simulationExecutor() {
        int cores = Runtime.getRuntime().availableProcessors();

        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(cores*2);
        executor.setMaxPoolSize(cores*4);
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("SimulationThread-");
        executor.initialize();
        return executor;
    }
}
