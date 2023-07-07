package org.project.gongsamo;

import lombok.extern.slf4j.Slf4j;
import org.project.gongsamo.domain.ArticleSearch;
import org.project.gongsamo.domain.TagSearch;
import org.project.gongsamo.util.Reachable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ElasticsearchInitialization implements ApplicationRunner {
    private final ElasticsearchOperations elasticsearchOperations;

    @Value("${spring.elasticsearch.rest.host}")
    private String host;

    @Value("${spring.elasticsearch.rest.port}")
    private int port;

    public ElasticsearchInitialization(ElasticsearchOperations elasticsearchOperations) {
        this.elasticsearchOperations = elasticsearchOperations;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Reachable reachable = new Reachable(this.host, this.port, 20, () -> {
            log.info("Elasticsearch is reachable");

            if (!elasticsearchOperations.indexOps(ArticleSearch.class).exists())
                elasticsearchOperations.indexOps(ArticleSearch.class).createWithMapping();

            if (!elasticsearchOperations.indexOps(TagSearch.class).exists())
                elasticsearchOperations.indexOps(TagSearch.class).createWithMapping();
        });

        reachable.start();
        reachable.join();
    }
}