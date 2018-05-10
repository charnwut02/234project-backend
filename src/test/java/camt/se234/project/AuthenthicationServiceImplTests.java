package camt.se234.project;

import camt.se234.project.dao.UserDao;
import camt.se234.project.entity.User;
import camt.se234.project.service.AuthenticationServiceImpl;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.Mockito.mock;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

public class AuthenticationServiceImplTest {
    UserDao userDao;
    AuthenticationServiceImpl authenticationService;

    @Before
    public void setup(){
        userDao = mock(UserDao.class);
        authenticationService = new AuthenticationServiceImpl();
        authenticationService.setUserDao(userDao);
    }

    @Test
    public void authenticateTest(){
        when(userDao.getUser("nui" ,"0000")).thenReturn(new User("nui","0000","student"));
        assertThat(authenticationService.authenticate("nui" , "0000"), is(new User("nui","0000","student")));
        assertThat(authenticationService.authenticate("poom" ,"0000"),is(nullValue()));
    }


}