package com.blackrock.hackerrank.controller;

import org.springframework.web.bind.annotation.*;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.ThreadMXBean;

@RestController
@RequestMapping("/blackrock/challenge/v1")
public class PerformanceController {

    @GetMapping("/performance")
    public PerformanceMetrics getMetrics() {

        long start = System.nanoTime();

        MemoryMXBean memoryBean =
                ManagementFactory.getMemoryMXBean();

        ThreadMXBean threadBean =
                ManagementFactory.getThreadMXBean();

        long memoryUsed =
                memoryBean.getHeapMemoryUsage().getUsed();

        int threadCount =
                threadBean.getThreadCount();

        long duration =
                System.nanoTime() - start;

        return new PerformanceMetrics(
                duration / 1_000_000,
                memoryUsed / (1024 * 1024),
                threadCount
        );
    }

    static class PerformanceMetrics {

        private long timeMs;
        private long memoryMb;
        private int threads;

        public PerformanceMetrics(long timeMs,
                                  long memoryMb,
                                  int threads) {

            this.timeMs = timeMs;
            this.memoryMb = memoryMb;
            this.threads = threads;
        }

        public long getTimeMs() {
            return timeMs;
        }

        public long getMemoryMb() {
            return memoryMb;
        }

        public int getThreads() {
            return threads;
        }
    }
}