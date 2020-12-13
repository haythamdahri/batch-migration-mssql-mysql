package com.it.batch.processors;

import com.it.batch.dao.mysql.MySQLUserRepository;
import com.it.batch.dao.mysql.bo.MysqlUser;
import lombok.extern.log4j.Log4j2;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Haytham DAHRI
 */
@Component
@Log4j2
public class UserItemWriter extends RepositoryItemWriter<MysqlUser> {

    private final MySQLUserRepository mySQLUserRepository;

    @Autowired
    public UserItemWriter(MySQLUserRepository mySQLUserRepository) {
        this.mySQLUserRepository = mySQLUserRepository;
        this.setRepository(mySQLUserRepository);
        log.info("INITIALIZING UserItemWriter");
    }
}
