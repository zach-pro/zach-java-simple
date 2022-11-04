package com;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author by 张益豪
 * @Classname GeneralTest
 * @Description 接口测试类
 * @Date 2022/11/3 15:13
 */
public class GeneralTest {

    @Test
    public void example(){
        String rs = "测试失败";
        System.out.println(rs);
        // 断言
        Assert.assertEquals("经过测试，没有登陆成功!", "测试成功", rs);
    }

}
