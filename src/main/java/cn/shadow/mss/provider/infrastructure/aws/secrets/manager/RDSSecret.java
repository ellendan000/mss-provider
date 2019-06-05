package cn.shadow.mss.provider.infrastructure.aws.secrets.manager;

import lombok.Data;

@Data
public class RDSSecret {
    private String username;
    private String password;
    private String host;
    private Integer port;
    private String dbname;
}
