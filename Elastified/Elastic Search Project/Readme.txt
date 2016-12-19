This project is to learn development of elastic search based CRUD ops. through JAVA API.

Basic components   :
                    Maven dependency :
                     <dependency>
                                <groupId>org.elasticsearch</groupId>
                                <artifactId>elasticsearch</artifactId>
                                <version>2.4.0</version>
                    </dependency>

Contributors       :
                    @shaun
                    @dpaul

Structure          :

Development curve  :
                    1.Document indexing through java API. ----------[Done]
                    2.Document search through JAVA API.-------------[To be implemented]
                    3.Document update through JAVA API.-------------[To be implemented]
                    4.Document deletion through java API.-----------[To be implemented]
                    5.Document mapping through CURL commands.-------[To be implemented]
                    6.Advance CRUD operations over document.--------[To be implemented]

---------------------------------------------------------------------------------------------------------------------------------
Basic CURL commands regarding CRUD operations.

1.Making an index.
    curl -XPUT 'localhost:9200/<indexName>'

2.Custom mapping of document.
    curl -XPOST 'localhost:9200/<indexName>/_mapping' -d{
     "<indexName>" :{
        "<document name>":{
            "<field name1 >" :{
                "type":"<mapping type>"
            },

            "<field name2>" :{
                "type":"<mapping type>"
            },

            "<field name3>" :{
                "type":"<mapping type>"
            }
        }
     }
    }

3.To get mapping of the document.
    curl -XGET 'localhost:9200/<indexname>/_mapping?pretty'

4.To index a document.
    curl -XPUT 'localhost:9200/<indexname>/<documentName>/' -d{
        //payload in form of json
    }

5. To Search at index level [_search]
    curl -XGET 'localhost:9200/<indexName>/_search'


6. To Search at document level [_search]
    curl -XGET 'localhost:9200/<indexName>/<documentName>/_search'

7.To delete an index.
    curl -XDELETE 'localhost:9200/<indexname>'

8.To delete a document in a particular index.
    curl -XDELETE 'localhost:9200/<indexname>/<documentName>'
