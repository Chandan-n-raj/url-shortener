package com.chandan.urlshortener.repository;

import com.chandan.urlshortener.model.UrlEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@DataJpaTest
public class UrlRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UrlRepository urlRepository;

    @Test
    public void shouldInsertAndGetFullurl() {
        UrlEntity urlEntity = new UrlEntity("http://example.com");
        urlRepository.save(urlEntity);

        assertThat(urlEntity.getId(), notNullValue());

        UrlEntity urlEntityFromDb = urlRepository.findById(urlEntity.getId()).get();
        assertThat(urlEntityFromDb.getId(), equalTo(urlEntity.getId()));
    }

}
