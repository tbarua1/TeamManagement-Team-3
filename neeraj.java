package spring;
 import org.springframework.context.support.ClassPathXmlApplicationContext;
public class Main {

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
            ClassPathXmlApplicationContext cp = new  ClassPathXmlApplicationContext("Spring.xml");
            Student s =(Student) cp.getBean("newstudent");
            System.out.println("pp");
	}

}
