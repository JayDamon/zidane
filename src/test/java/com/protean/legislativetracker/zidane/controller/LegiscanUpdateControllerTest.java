package com.protean.legislativetracker.zidane.controller;

import com.protean.legislativetracker.zidane.config.ApplicationConfig;
import com.protean.legislativetracker.zidane.config.WebMvcConfig;
import com.protean.legislativetracker.zidane.config.ZidaneSecurityConfig;
import com.protean.security.auron.model.User;
import com.protean.security.auron.response.StandardResponse;
import com.protean.security.auron.security.JwtAuthenticationFilter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LegiscanUpdateControllerTest {

    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    @Before
    public void setUp() throws ServletException, IOException {
        restTemplate = new TestRestTemplate("Jaymond87", "TestPassword2");
        JwtAuthenticationFilter filter = mock(JwtAuthenticationFilter.class);

        User user = mock(User.class);
        Authentication authentication = mock(Authentication.class);
        SecurityContext context = mock(SecurityContext.class);
        when(context.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(context);
        when(SecurityContextHolder.getContext().getAuthentication().getPrincipal()).thenReturn(user);
    }

    @Test
    public void updateSessionFromLegiscan_ValidSesionId_UpdateSuccessful() {
        //arrange
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxIiwiaWF0IjoxNTQzMTUzMzcyLCJleHAiOjE1NDM3NTgxNzJ9.yDy0Wlst7w6kdxBueGRlmyukoydoQZ6syhX85at5Vy5nhlH2piWqDltA7DtLlCCl4sYAdHThxIm7nDS-5cmh-A");
        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        //act

        ResponseEntity<String> response = restTemplate.getForEntity(
                createURLWithPort("/v1/legiscan/session/1258"), String.class);
//        ResponseEntity<String> response = restTemplate.exchange(
//                createURLWithPort("/zidane/v1/legiscan/session/1258"),
//                HttpMethod.GET, entity, String.class);

//        ResponseEntity<Boolean> response = restTemplate.withBasicAuth("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxIiwiaWF0IjoxNTQzMTUzMzcyLCJleHAiOjE1NDM3NTgxNzJ9.yDy0Wlst7w6kdxBueGRlmyukoydoQZ6syhX85at5Vy5nhlH2piWqDltA7DtLlCCl4sYAdHThxIm7nDS-5cmh-A")
//                .postForEntity(
//                        "/v1/legiscan/session/1258",
//                        entity, Boolean.class
//        );
        System.out.println(response.getBody());
        assertEquals(200, response.getStatusCodeValue());
        assertTrue(response.hasBody());
//        assertTrue(response.getBody());

        //assert


    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + "/zidane" + uri;
    }

}