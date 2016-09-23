import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.node.Node;
import org.elasticsearch.node.NodeBuilder;

import java.util.HashMap;
import java.util.Map;

public class ElasticClientDriver {
     public static void main(String[] args) {
          try {
               Map<String , Object > jsonDoc = new HashMap<String, Object>();
               jsonDoc.put("Name" ,"Sachin" );
               jsonDoc.put("Content" , "THis is the best content ever");
               jsonDoc.put("Article" ,"For the time being this is assumed to be an article");
               Node node = NodeBuilder.nodeBuilder().settings(Settings.builder().put("path.home","C:\\elasticsearch-2.4.0\\bin")).clusterName("ElasticSearch_Cluster").node();
               /*for(int i=0;i<20000;i++){
                    System.out.println("Waited for : "+ i);
               }*/
               Client client = node.client();
               client.prepareIndex("ye_index_hai" ,"ye_type_hai").setSource(jsonDoc).execute().actionGet();

               GetResponse elasticSearchResponse = client.prepareGet("ye_index_hai" ,"ye_type_hai","1").execute().actionGet();
               System.out.println(":::::::::::::::::::::::::::::::Elastic Search Information:::::::::::::::::::::::::::::::");
               System.out.println(node.client().toString());
               System.out.println("Elastic search Index Name ::  "+elasticSearchResponse.getIndex());
               System.out.println("Elastic search Type Name :: "+elasticSearchResponse.getType());
               System.out.println(":::::::::::::::::::::::::::::::Document Information ::::::::::::::::::::::::::::::::::::");
               System.out.println(elasticSearchResponse.getSource().values());
          } catch (Exception e) {
          e.printStackTrace();
          }
     }
}
