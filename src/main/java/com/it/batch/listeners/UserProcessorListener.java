package com.it.batch.listeners;

import com.it.batch.dao.mysql.bo.MysqlUser;
import com.it.batch.dao.mssql.bo.MssqlUser;
import lombok.extern.log4j.Log4j2;
import org.springframework.batch.core.ItemProcessListener;
import org.springframework.stereotype.Component;

/**
 * @author Haytham DAHRI
 */
@Component
@Log4j2
public class UserProcessorListener implements ItemProcessListener<MssqlUser, MysqlUser> {

    @Override
    public void beforeProcess(MssqlUser item) {

    }

    @Override
    public void afterProcess(MssqlUser item, MysqlUser result) {
        log.info("MSSQL USER ID: " + item.getId() + " | MYSQL USER ID: " + result.getId());
    }

    @Override
    public void onProcessError(MssqlUser item, Exception e) {
        log.error("An Error Occurred While Processing User!");
    }
}
