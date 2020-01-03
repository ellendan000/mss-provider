package cn.shadow.mss.provider.interfaces.api;

import cn.shadow.mss.provider.application.user.UserCreatedDTO;
import cn.shadow.mss.provider.application.user.UserService;
import cn.shadow.mss.provider.interfaces.request.CreateUserRequest;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @BeforeEach
    void setUp() {
        RestAssuredMockMvc.mockMvc(mockMvc);
        RestAssuredMockMvc.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    void createUser() {
        when(userService.createUser(any())).thenReturn(UserCreatedDTO.builder().userId(1L).build());
        given().body(CreateUserRequest.builder()
                .name("zhangsan")
                .mobileNumber("17822229999")
                .identificationCardNumber("")
                .build())
        .when()
        .post("/users")
        .then()
        .statusCode(HttpStatus.OK.value())
        .body("userId", equalTo(1));
    }
}