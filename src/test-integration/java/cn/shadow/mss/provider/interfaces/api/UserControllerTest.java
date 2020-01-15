package cn.shadow.mss.provider.interfaces.api;

import cn.shadow.mss.provider.application.user.CreateUserCommand;
import cn.shadow.mss.provider.application.user.UserCreatedDTO;
import cn.shadow.mss.provider.application.user.UserService;
import cn.shadow.mss.provider.interfaces.request.CreateUserRequest;
import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;

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
        ArgumentCaptor<CreateUserCommand> captor = ArgumentCaptor.forClass(CreateUserCommand.class);

        CreateUserRequest request = CreateUserRequest.builder()
                .name("zhangsan")
                .mobileNumber("17822229999")
                .identificationCardNumber("")
                .build();

        given().body(request)
                .header(CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE)
        .when()
        .post("/users")
        .then()
        .statusCode(HttpStatus.OK.value())
        .body("userId", equalTo(1));

        verify(userService).createUser(captor.capture());
        CreateUserCommand captureCommand = captor.getValue();
        assertAll(
                () -> assertEquals(request.getName(), captureCommand.getName()),
                () -> assertEquals(request.getIdentificationCardNumber(), captureCommand.getIdentificationCardNumber()),
                () -> assertEquals(request.getMobileNumber(), captureCommand.getMobileNumber())
        );
    }
}