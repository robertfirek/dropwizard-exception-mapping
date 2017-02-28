package org.firek;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class ErrorTrigger {

    public static final int NUMBER_OF_CONCURRENT_REQUESTS = 300;

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(NUMBER_OF_CONCURRENT_REQUESTS);

        IntStream.range(0, NUMBER_OF_CONCURRENT_REQUESTS)
                .forEach(
                        threadNumber -> executorService.execute(new ResourceCaller(threadNumber)));

        executorService.shutdown();
        try {
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static class ResourceCaller implements Runnable {
        private final Logger logger = LoggerFactory.getLogger(this.getClass());
        private final int threadNumber;

        public ResourceCaller(int threadNumber) {
            this.threadNumber = threadNumber;
        }

        @Override
        public void run() {
            String address = "http://localhost:8080/test/error";
            Response resp = ClientBuilder.newBuilder()
                    .build()
                    .target(address)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get(Response.class);
            if (resp.getStatus() == 200) {
                logger.info("Status: " + resp.getStatus() + "\n" +
                        "Thread Number: " + threadNumber + "\n" +
                        "Headers: " + resp.getHeaders() + "\n" +
                        "Body: " + resp.readEntity(String.class) + "\n"
                );
            } else {
                logger.error("Status: " + resp.getStatus() + "\n" +
                        "Thread Number: " + threadNumber + "\n" +
                        "Headers: " + resp.getHeaders() + "\n" +
                        "Body: " + resp.readEntity(String.class) + "\n"
                );
            }
        }
    }
}
