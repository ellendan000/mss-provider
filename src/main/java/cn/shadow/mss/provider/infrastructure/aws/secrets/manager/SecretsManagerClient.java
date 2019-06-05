package cn.shadow.mss.provider.infrastructure.aws.secrets.manager;

import com.amazonaws.services.secretsmanager.AWSSecretsManager;
import com.amazonaws.services.secretsmanager.AWSSecretsManagerClientBuilder;
import com.amazonaws.services.secretsmanager.model.GetSecretValueRequest;
import com.amazonaws.services.secretsmanager.model.GetSecretValueResult;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Base64;

@Slf4j
@Component
@ConditionalOnProperty(value = "aws.secrets-manager.enabled", havingValue = "true")
public class SecretsManagerClient {
    private final AWSSecretsManager client;

    public SecretsManagerClient(@Value("${aws.region}") String region) {
        client = AWSSecretsManagerClientBuilder.standard()
                .withRegion(region)
                .build();
    }

    public RDSSecret getRDSSecret(String rdsSecretName) {
        String secretString = getSecret(rdsSecretName);
        try {
            return new ObjectMapper()
                    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                    .readValue(secretString, RDSSecret.class);
        } catch (IOException e) {
            log.warn("secretString format is not match rdsSecret class", e);
            throw new GetSecretValueException(e);
        }
    }

    private String getSecret(String secretName) {
        GetSecretValueRequest getSecretValueRequest = new GetSecretValueRequest()
                .withSecretId(secretName);
        GetSecretValueResult getSecretValueResult;

        try {
            getSecretValueResult = client.getSecretValue(getSecretValueRequest);
        } catch (Exception e) {
            log.warn("secret manager get value failed:", e);
            throw new GetSecretValueException(e);
        }

        if (getSecretValueResult.getSecretString() == null) {
            return new String(Base64.getDecoder().decode(getSecretValueResult.getSecretBinary()).array());
        } else {
            return getSecretValueResult.getSecretString();
        }
    }
}
