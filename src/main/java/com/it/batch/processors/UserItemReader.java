package com.it.batch.processors;

import com.it.batch.dao.mssql.MSSQLUserRepository;
import com.it.batch.dao.mssql.bo.MssqlUser;
import lombok.extern.log4j.Log4j2;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author Haytham DAHRI
 */
@Component
@Log4j2
public class UserItemReader extends RepositoryItemReader<MssqlUser> {

    private final MSSQLUserRepository mssqlUserRepository;

    @Autowired
    public UserItemReader(MSSQLUserRepository mssqlUserRepository, Environment environment) {
        this.mssqlUserRepository = mssqlUserRepository;
        this.setRepository(mssqlUserRepository);
        this.setMethodName("findAll");
        this.setPageSize(environment.getProperty("pagging.page.size", Integer.class));
        this.setSort(Collections.singletonMap("id", Sort.Direction.DESC));
        log.info("INITIALIZING UserItemReader");
    }

}
