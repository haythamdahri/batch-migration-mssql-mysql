package com.it.batch.processors;

import com.it.batch.dao.mysql.bo.MysqlUser;
import com.it.batch.dao.mssql.bo.MssqlUser;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

/**
 * @author Haytham DAHRI
 */
@Component
public class UserItemProcessor implements ItemProcessor<MssqlUser, MysqlUser> {

    @Override
    public MysqlUser process(MssqlUser item) throws Exception {
        MysqlUser user = new MysqlUser();
        BeanUtils.copyProperties(item, user);
        return user;
    }
}
