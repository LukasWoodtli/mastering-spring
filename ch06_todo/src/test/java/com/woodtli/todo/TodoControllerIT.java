package com.woodtli.todo;

import com.woodtli.todo.bean.Todo;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.net.URI;
import java.nio.charset.Charset;
import java.util.Base64;
import java.util.Date;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TodoApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TodoControllerIT {

    private HttpHeaders headers = createHeaders("user-name", "user-password");

    @Autowired
    TestRestTemplate template;

    @Test
    public void retrieveTodos() throws JSONException {
        String expected = "[" + "{id:1,user:Jack,desc:\"Mastering Spring 5\",done:false}" + "]";

        ResponseEntity<String> response = template.exchange("/users/Jack/todos",
                                                            HttpMethod.GET,
                                                            new HttpEntity<String>(null, headers),
                                                            String.class);

        String body = response.getBody();
        JSONAssert.assertEquals(expected, body, false);
    }

    @Test
    public void addTodo() {
        Todo todo = new Todo(-1, "Jill", "Learn Hibernate", new Date(), false);

        URI location = template.postForLocation("/users/Jill/todos", todo);

        assertThat(location.getPath(), containsString("/users/Jill/todos/4"));
    }

    @Test
    public void updateTodo() throws Exception {
        String expected = "{id:2,user:Jill,desc:\"Learn Spring\",done:false}";
        Todo todo = new Todo(2, "Jill", "Learn Spring", new Date(), false);

        ResponseEntity<String> response = template.exchange("/users/Jill/todos/" + todo.getId(),
                                                            HttpMethod.PUT,
                                                            new HttpEntity<>(todo, headers),
                                                            String.class);

        String body = response.getBody();
        JSONAssert.assertEquals(expected, body, false);
    }

    @Test
    public void deleteTodo() throws Exception {

        ResponseEntity<String> response = template.exchange("/users/Jill/todos/2",
                HttpMethod.DELETE,
                new HttpEntity<>(null, headers),
                String.class);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    private HttpHeaders createHeaders(String username, String password) {
        return new HttpHeaders() {
            {
                String auth = username + ":" + password;
                byte[] encodedAuth = Base64.getEncoder().encode(
                        auth.getBytes(Charset.forName("US-ASCII")));
                String authHeader = "Basic " + new String(encodedAuth);
                set("Authorization", authHeader);
            }
        };
    }
}
