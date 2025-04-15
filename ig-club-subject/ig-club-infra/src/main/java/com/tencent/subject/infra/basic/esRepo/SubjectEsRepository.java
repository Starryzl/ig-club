package com.tencent.subject.infra.basic.esRepo;

import com.tencent.subject.infra.basic.entity.SubjectInfoEs;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

@Component
public interface SubjectEsRepository extends ElasticsearchRepository<SubjectInfoEs,Long> {
}

