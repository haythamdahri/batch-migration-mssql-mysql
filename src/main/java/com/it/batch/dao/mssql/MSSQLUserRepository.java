package com.it.batch.dao.mssql;

import com.it.batch.dao.mssql.bo.MssqlUser;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Haytham DAHRI
 */
@Repository
public interface MSSQLUserRepository extends PagingAndSortingRepository<MssqlUser, String> {
}
