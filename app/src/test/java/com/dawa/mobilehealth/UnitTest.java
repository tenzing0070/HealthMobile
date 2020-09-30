package com.dawa.mobilehealth;

import com.dawa.mobilehealth.bll.FeedbackBLL;
import com.dawa.mobilehealth.bll.LoginBLL;
import com.dawa.mobilehealth.bll.SignupBLL;


import junit.framework.TestCase;

import org.junit.Assert;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;


public class UnitTest {

    @Test
    public  void checkLogin(){

        LoginBLL loginBLL = new LoginBLL();
        boolean result = loginBLL.checkUser("ninja", "123");
       Assert.assertEquals(true,result);
    }

    @Test
    public  void signUP(){
        SignupBLL signupBLL = new SignupBLL();
        boolean result = signupBLL.signUP("ninja", "sherpa", "thamle",
                "25", "32423423423", "ninja@gmail.com", "Male", "80", "180",
                "0","ninja123", "123", "ninja.jpg");
        Assert.assertEquals(true, result);
    }

    @Test
    public  void feedBack(){
        FeedbackBLL feedbackBLL = new FeedbackBLL();
        boolean result = feedbackBLL.feedback("dawa@gmail.com", "This is test feedback");
        Assert.assertEquals(true, result);
    }

}