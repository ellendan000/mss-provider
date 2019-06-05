package cn.shadow.mss.provider.infrastructure.configuration;

import cn.shadow.mss.provider.infrastructure.aws.secrets.manager.RDSSecret;
import cn.shadow.mss.provider.infrastructure.aws.secrets.manager.SecretsManagerClient;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DatabaseDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;


@Configuration
@Profile("prod")
@ConditionalOnClass(DataSource.class)
@ConditionalOnProperty(name = "spring.datasource.type", havingValue = "org.apache.tomcat.jdbc.pool.DataSource", matchIfMissing = true)
public class DataSourceConfiguration {
    private final RDSSecret rdsSecret;

    public DataSourceConfiguration(@Value("${aws.secrets-manager.mysql-secret-name}")
                                           String mysqlSecretName,
                                   SecretsManagerClient secretsManagerClient) {
        this.rdsSecret = secretsManagerClient.getRDSSecret(mysqlSecretName);
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.tomcat")
    public DataSource dataSource(
            DataSourceProperties properties) {
        updateBySecretValue(properties);

        DataSource dataSource = properties.initializeDataSourceBuilder()
                .type(DataSource.class).build();
        DatabaseDriver databaseDriver = DatabaseDriver
                .fromJdbcUrl(properties.determineUrl());
        String validationQuery = databaseDriver.getValidationQuery();
        if (validationQuery != null) {
            dataSource.setTestOnBorrow(true);
            dataSource.setValidationQuery(validationQuery);
        }
        return dataSource;
    }

    private void updateBySecretValue(DataSourceProperties properties) {
        properties.setUrl(
                String.format("jdbc:mysql://%s:%s/%s",
                        rdsSecret.getHost(),
                        rdsSecret.getPort(),
                        rdsSecret.getDbname()
                )
        );
        properties.setUsername(rdsSecret.getUsername());
        properties.setPassword(rdsSecret.getPassword());
    }
}
