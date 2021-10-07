package com.works.repositories._elastic;

import com.works.models.elasticsearch.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface CustomerElasticSearchRepository extends ElasticsearchRepository<Customer, String> {

    @Query("{\"bool\":{\"must\":[],\"must_not\":[],\"should\":[{\"match\":{\"name\":\"?0\"}},{\"match\":{\"surname\":\"?0\"}},{\"match\":{\"tel1\":\"?0\"}},{\"match\":{\"mail\":\"?0\"}},{\"match\":{\"tcnumber\":\"?0\"}},{\"match\":{\"group\":\"?0\"}},{\"match\":{\"city\":\"?0\"}},{\"match\":{\"district\":\"?0\"}}]}},\"from\":0,\"size\":10,\"sort\":[],\"aggs\":{}")
    Page<Customer> findCustomer(String val, Pageable pageable);
}
