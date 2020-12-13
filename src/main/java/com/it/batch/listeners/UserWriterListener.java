package com.it.batch.listeners;

import com.it.batch.dao.mysql.bo.MysqlUser;
import lombok.extern.log4j.Log4j2;
import org.springframework.batch.core.ItemWriteListener;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Haytham DAHRI
 */
@Component
@Log4j2
public class UserWriterListener implements ItemWriteListener<MysqlUser> {

    @Override
    public void beforeWrite(List<? extends MysqlUser> items) {
    }

    @Override
    public void afterWrite(List<? extends MysqlUser> items) {
        log.info("NUMBER OF WRITTEN USERS: " + items.size());
    }

    @Override
    public void onWriteError(Exception exception, List<? extends MysqlUser> items) {
        log.info("An error occurred while writing users into MYSQL DB!");
    }

}
