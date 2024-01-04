package com.example.demo.di2;

//실습제목--ApplicationContext 무작정따라하기
//소개목적 -myBatis같은 외부라이브러리를 사용할때 사용하라 -공통코드작성하기 -공통팀에 근무
//예 DatabaseConfiguration CorsConfiguration
public class MyContextMain {

    public static void main(String[] args) {
        MyContext mc = new MyContext(MyConfig.class);
        System.out.println(mc.map);

        TestController testController2 = new TestController();
        System.out.println("testController2 = " + testController2);

        TestLogic testLogic = (TestLogic) mc.getBean("testLogic");

        TestDao testDao = (TestDao) mc.getBean(TestDao.class);

        TestController testController = (TestController) mc.getBean("testController");
        System.out.println("testController = " + testController);

        testController.setTestLogic(testLogic);   
        testLogic.setTestDao(testDao);
        testController.testList();
        // 같은주소번지 출력 = 싱글톤패턴 디폴트
        // 멀티스레드를 운영하여 한정된자원을 여러 사용자가 누릴수있다.
        TestController testController3 = (TestController) mc.getBean(TestController.class);
        System.out.println("testController3 = " + testController3);

    }
}
