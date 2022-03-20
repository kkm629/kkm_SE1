
import java.io.IOException;
import java.util.Scanner;

public class test {
	public void test1() throws IOException {
        new fileProject().fileProject1();
    }
    public void test2() throws IOException {
        Scanner scanner = new Scanner(System.in);
        new fileProject4(fileProject4.da(fileProject4.readFromTextFile(scanner.nextLine())));
    }

    public void test3() throws IOException {
        Scanner scanner = new Scanner(System.in);
        new fileProject4(fileProject4.da(fileProject4.readFromTextFile(scanner.next())),scanner.next());
    }

    public static void main(String[] args) throws IOException {
    	new test().test1();
        new test().test2();//输入文件名
        new test().test3();//输入文件名 省名，中间用空格分开
    }

}
