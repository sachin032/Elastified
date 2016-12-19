package org.elasticSearchQueries;

/**
 * Created by @Shaun on 10 Oct 2016 .
 */
public class ElaticSearchQueries {

     private String indexName;
     private String typeName;
     private int id;
     private int from;
     private int size;

     public String getQuery() {
          String query;
          return "/" + this.getIndexName( ) + "/" + this.getTypeName( ) + "/" + this.getId( ) + "/" + "_search";

     }

     public String getIndexName() {
          return indexName;
     }

     public void setIndexName(String indexName) {
          this.indexName = indexName;
     }

     public String getTypeName() {
          return typeName;
     }

     public void setTypeName(String typeName) {
          this.typeName = typeName;
     }

     public int getId() {
          return id;
     }

     public void setId(int id) {
          this.id = id;
     }

     public int getFrom() {
          return from;
     }

     public void setFrom(int from) {
          this.from = from;
     }

     public int getSize() {
          return size;
     }

     public void setSize(int size) {
          this.size = size;
     }
}
