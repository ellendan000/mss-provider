package cn.shadow.mss.provider;

import lombok.Generated;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

@Generated
@SpringCloudApplication
public class BootstrapApp {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(BootstrapApp.class);
        application.run(args);
    }
}
