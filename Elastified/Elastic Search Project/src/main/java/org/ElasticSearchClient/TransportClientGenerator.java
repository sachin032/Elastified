package org.ElasticSearchClient;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by @Shaun on 08 Nov 2016 .
 */
public class TransportClientGenerator {
     public static TransportClient getTransportCLient(){
          Settings settings = Settings.settingsBuilder().put("client.transport.sniff", true).put("cluster.name", "Shaun-Cluster").put("client.transport.ping_timeout", "120s").build();
          TransportClient transportClient = null;
          try {
               transportClient = new TransportClient.Builder().settings(settings).build()
                         .addTransportAddress(new InetSocketTransportAddress(
                                   InetAddress.getByName("127.0.0.1"), 9300));
          } catch (UnknownHostException e) {
               e.printStackTrace();
          }
          return transportClient;
     }
}
