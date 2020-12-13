package com.it.batch.config;

import com.it.batch.dao.mssql.bo.MssqlUser;
import com.it.batch.dao.mysql.bo.MysqlUser;
import com.it.batch.listeners.MigrationJobListener;
import com.it.batch.listeners.UserProcessorListener;
import com.it.batch.listeners.UserReaderListener;
import com.it.batch.listeners.UserWriterListener;
import com.it.batch.processors.UserItemProcessor;
import com.it.batch.processors.UserItemReader;
import com.it.batch.processors.UserItemWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author Haytham DAHRI
 */
@Configuration
@EnableBatchProcessing
public class BatchConfiguration extends DefaultBatchConfigurer {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final UserItemProcessor userItemProcessor;
    private final MigrationJobListener migrationJobListener;
    private final UserReaderListener userReaderListener;
    private final UserProcessorListener userProcessorListener;
    private final UserWriterListener userWriterListener;
    private final UserItemReader userItemReader;
    private final UserItemWriter userItemWriter;

    @Value("${job.process.chunk}")
    private int chunk;

    public BatchConfiguration(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory,
                              UserItemProcessor userItemProcessor, MigrationJobListener migrationJobListener,
                              UserReaderListener userReaderListener, UserProcessorListener userProcessorListener,
                              UserWriterListener userWriterListener, UserItemReader userItemReader, UserItemWriter userItemWriter) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.userItemProcessor = userItemProcessor;
        this.migrationJobListener = migrationJobListener;
        this.userReaderListener = userReaderListener;
        this.userProcessorListener = userProcessorListener;
        this.userWriterListener = userWriterListener;
        this.userItemReader = userItemReader;
        this.userItemWriter = userItemWriter;
    }

    @Override
    public void setDataSource(DataSource dataSource) {
    }

    @Bean
    public Step migrateMssqlToMysqlStep() {
        return stepBuilderFactory.get("MIGRATE-MSSQL-TO-MYSQL-JOB")
                .<MssqlUser, MysqlUser>chunk(this.chunk)
                .reader(this.userItemReader)
                .processor(this.userItemProcessor)
                .writer(this.userItemWriter)
                .listener(this.userReaderListener)
                .listener(this.userProcessorListener)
                .listener(this.userWriterListener)
                .build();
    }

    @Bean
    public Job migrateMssqlToMysqlJob() {
        return jobBuilderFactory.get("MIGRATE-MSSQL-TO-MYSQL-JOB")
                .listener(this.migrationJobListener)
                .flow(migrateMssqlToMysqlStep())
                .end()
                .build();
    }

}
