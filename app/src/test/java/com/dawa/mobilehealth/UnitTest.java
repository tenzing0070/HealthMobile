package com.dawa.mobilehealth;

import com.dawa.mobilehealth.bll.LoginBLL;
import com.dawa.mobilehealth.bll.SignupBLL;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class UnitTest {
   @Test

    public  void checkLogin(){

        LoginBLL loginBLL = new LoginBLL();
        boolean result = loginBLL.checkUser("ninja", "123");
        assertEquals(true, result);
    }




}