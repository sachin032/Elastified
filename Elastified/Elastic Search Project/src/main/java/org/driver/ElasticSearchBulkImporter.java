package org.driver;

import org.ElasticSearchClient.TransportClientGenerator;
import org.elasticsearch.action.bulk.BackoffPolicy;
import org.elasticsearch.action.bulk.BulkProcessor;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.unit.ByteSizeUnit;
import org.elasticsearch.common.unit.ByteSizeValue;
import org.elasticsearch.common.unit.TimeValue;

import java.net.UnknownHostException;
import java.util.logging.Logger;

/**
 * Created by @Shaun on 07 Nov 2016 .
 */
public class ElasticSearchBulkImporter {

     public static final Logger logger = Logger.getLogger("ElasticSearchBulkImporter.class");
     private static TransportClient transportClient = TransportClientGenerator.getTransportCLient();
     public static BulkProcessor bulkProcessor = BulkProcessor.builder(transportClient, new BulkProcessor.Listener() {

          public void beforeBulk(long executionId, BulkRequest request) {
               System.out.println("Before Bulk :: " + request.numberOfActions());
               logger.info("Going to execute new bulk composed of {} actions" + request.numberOfActions());
          }

          public void afterBulk(long executionId, BulkRequest request, BulkResponse response) {
               System.out.println("After Bulk :: " + response.hasFailures());
               logger.info("Executed bulk composed of {} actions" + request.numberOfActions());
          }

          public void afterBulk(long executionId, BulkRequest request, Throwable failure) {
               System.out.println("Error caused by failure :: " + failure.getMessage());
               logger.info("Error executing bulk" + failure);
          }
     }).setBulkActions(10).setBulkSize(new ByteSizeValue(1, ByteSizeUnit.GB)).setFlushInterval(TimeValue.timeValueSeconds(5))
               .setConcurrentRequests(1)
               .setBackoffPolicy(
                         BackoffPolicy.exponentialBackoff(TimeValue.timeValueMillis(100), 3)).build();


     public static void main(String[] args) throws UnknownHostException {
          try {
               bulkProcessor.add(new IndexRequest("person", "employee").source("SamleJsonData.json"));
          } finally {
               bulkProcessor.close();
          }
     }
}
