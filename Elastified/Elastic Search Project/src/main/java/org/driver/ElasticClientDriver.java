package org.driver;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Logger;

public class ElasticClientDriver {

     public static final Logger LOGGER = Logger.getLogger("ElasticClientDriver.class");

     private ElasticClientDriver() {

     }

     public static void main(String[] args) throws IOException {
          IndexResponse response;
          GetResponse getResponse;
          String line;
          TransportClient tsClient = getTransportClient();
          FileReader fReader = new FileReader("C:\\Users\\smishra\\Downloads\\My Saved Schema.json");
          BufferedReader reader = new BufferedReader(fReader);
          int i = 1;
          try {
               /*
               * Note : Here we are trying to split the whole FILE on the basis of new line.
               * After that  Transport_Client trying to make a HTTP connection and ingesting/Indexing the found document in Elastic search.
               * This is very heavy weight process if size of given file is in TBs.
               * Better choose BULK API import while indexing multiple documents.
               */
               while ((line = reader.readLine()) != "\n") {
                    String[] doc = line.split("\n");
                    //Make sure Index is already there in ES , if not create it.
                    response = tsClient.prepareIndex("person", "employee").setSource(doc[0]).execute().actionGet();
                    System.out.println("Document inserted into :: " + response.getIndex() + " Index ");
                    System.out.println("_________________________\n");
                    System.out.print(response.getId());
                    System.out.println("\n_________________________");
                    i++;
               }
          } catch (IOException ex) {
               LOGGER.info("Error Occurred" + ex.getMessage());
          } finally {
               LOGGER.info("Closing File Reader");
               fReader.close();
               LOGGER.info("Closing Reader");
               reader.close();
          }
          getResponse = tsClient.prepareGet().execute().actionGet();
          System.out.println(getResponse.getSource().toString());
     }

     public static TransportClient getTransportClient() throws UnknownHostException {
          Settings settings = Settings.settingsBuilder()
                    .put("client.transport.sniff", true).put("cluster.name", "Shaun-Cluster")
                    .put("client.transport.ping_timeout", "120s").build();
          TransportClient transportClient = TransportClient.builder().settings(settings).build()
                    .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("127.0.0.1"), 9300));
          return transportClient;
     }
}
