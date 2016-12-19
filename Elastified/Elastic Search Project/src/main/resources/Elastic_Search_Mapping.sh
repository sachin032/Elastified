#!/bin/bash

#++++++++++++++++++++++++++ This is a sample document mapping +++++++++++++++++++++++++++++++++++++++++++++++
#--------------------------Create index [places]-------------------------------------------------------------

$1= curl -XHEAD 'localhost:9200/person?pretty'

echo "$1"

#curl -XPUT 'http://localhost:9200/person'

#-------------------------Create mapping of the document named as hotels-------------------------------------

curl -XPUT "http://localhost:9200/person/_mapping" -d '{
                "settings" : {
                                "number_of_shards" : 2,
                                "number_of_replicas" : 1
                               },

                "properties" : {
                    "first_name" : {
                        "type" : "String",
                        "store" : "yes" ,
                        "index":"not_analyzed"
                    },
                    "last_name":{
                        "type" : "String",
                        "store" : "yes" ,
                        "index":"not_analyzed"
                    },
                    "email" :{
                        "type" : "String",
                        "store" : "yes" ,
                        "index":"not_analyzed"
                    },
                    "gender" :{
                        "type" : "String",
                        "store" : "yes" ,
                        "index":"not_analyzed"
                    },
                    "ip_address" : {
                        "type" :"String",
                        "store" : "yes" ,
                        "index":"not_analyzed"
                    },
                     "geo_Location":{
                         "type" : "String",
                         "store" : "yes" ,
                         "index":"not_analyzed"
                    }
                }
 }'
