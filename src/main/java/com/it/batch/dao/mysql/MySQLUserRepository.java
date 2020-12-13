package com.it.batch.dao.mysql;

import com.it.batch.dao.mysql.bo.MysqlUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Haytham DAHRI
 */
@Repository
public interface MySQLUserRepository extends CrudRepository<MysqlUser, String> {
}
