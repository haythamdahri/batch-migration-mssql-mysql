package com.it.batch.listeners;

import com.it.batch.dao.mssql.bo.MssqlUser;
import lombok.extern.log4j.Log4j2;
import org.springframework.batch.core.ItemReadListener;
import org.springframework.stereotype.Component;

/**
 * @author Haytham DAHRI
 */
@Component
@Log4j2
public class UserReaderListener implements ItemReadListener<MssqlUser> {

    @Override
    public void beforeRead() {

    }

    @Override
    public void afterRead(MssqlUser item) {
        log.info("MSSQL USER ID: " + item.getId());

    }

    @Override
    public void onReadError(Exception ex) {

    }
}
