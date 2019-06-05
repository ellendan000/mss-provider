package cn.shadow.mss.provider.infrastructure.aws.secrets.manager;

public class GetSecretValueException extends RuntimeException {
    public GetSecretValueException(String message, Throwable cause) {
        super(message, cause);
    }

    public GetSecretValueException(Throwable cause) {
        super(cause);
    }
}
