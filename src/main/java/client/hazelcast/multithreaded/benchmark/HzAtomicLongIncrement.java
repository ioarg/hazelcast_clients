package client.hazelcast.multithreaded.benchmark;

import client.hazelcast.multithreaded.threads.impl.AtomicLongIncrementBenchmarkRunner;

/**
 * Fires Atomic Long requests
 */
public class HzAtomicLongIncrement {

    public static void main(String[] args) {
        if(args.length < 3){
            System.out.println("Usage : <warm iterations> <benchmark iterations> <number of threads>");
            System.exit(1);
        }
        int warmupIterations = Integer.parseInt(args[0]);
        int benchmarkIterations = Integer.parseInt(args[1]);
        int numberOfThreads = Integer.parseInt(args[2]);


        // WarmUp ==============================================
        AtomicLongIncrementBenchmarkRunner warmupRunner =
                new AtomicLongIncrementBenchmarkRunner(numberOfThreads);
        warmupRunner.prepareBenchmark(warmupIterations);
        warmupRunner.runBenchmark();
        warmupRunner.shutDown();

        // wait for a few seconds before starting the next
        // connections to the cluster
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Benchmark ==============================================
        AtomicLongIncrementBenchmarkRunner benchmarkRunner =
                new AtomicLongIncrementBenchmarkRunner(numberOfThreads);
        benchmarkRunner.prepareBenchmark(benchmarkIterations);
        benchmarkRunner.runBenchmark();
        benchmarkRunner.shutDown();

    }
}
