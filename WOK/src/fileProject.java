
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class fileProject {
    void fileProject1() {
        File dir = new File("./");//设置父文件夹相对路径
        File[] files = dir.listFiles();//获取父文件夹下所有文件和文件夹
        for (File file : Objects.requireNonNull(files)) {
            if ((!file.isDirectory()) && file.getName().equals("yq_in_04.txt")) {//判断文件是否是文件且文件后缀名是否为txt，若是执行判断内语句
                File newFile = new File("yq_out_04.txt");//新建一个空txt文件，并命名
                try {
                    List<String> ap = new fileProject().readFromTextFile(file.getPath());//将处理过后的文本内信息赋给集合ap
                    int size = ap.size();//获取集合ap的数量
                    String[] array = ap.toArray(new String[size]);//将集合ap转化为数组，并将值赋给array
                    FileOutputStream fos = new FileOutputStream(newFile);//打开输出流
                    for (String s : array) {
                        fos.write(s.getBytes());//将处理后的信息输出到新建的空txt文件中
                        fos.write('\r');
                        fos.write('\n');
                    }
                    fos.close();//关闭输出流
                    System.out.println("写入成功！");

                } catch (IOException e) {
                    System.out.println("出错");
                }
            }
        }
    }
    public ArrayList<String> readFromTextFile(String pathname) throws IOException {//处理文本文件信息

        ArrayList<String> strArray = new ArrayList<>();//创建集合
        File filename = new File(pathname);//将文件路径赋给filename
        InputStreamReader reader = new InputStreamReader(new FileInputStream(filename));//打开输入流
        BufferedReader br = new BufferedReader(reader);//读取文本内信息
        String line;
        String blank = "\t";
        String line1 = "";
        line = br.readLine();//读取一行文本
        int locationNum = line.indexOf(blank);//获取该行第一个制表符位置
        while(line != null) {
            if (!line1.equals(line.substring(0, locationNum))) {//判断该行省份与上一行省份是否一样,若不一样执行判断内语句
                line1 = line.substring(0, locationNum);//获取省份名
                strArray.add(line1);//将省份名输入到集合中
            }
            line = line.replace(line.substring(0, locationNum + 1), "");//删除该行省份名
            line = line.replaceAll("\t"," ");//将该行所有制表符替换为空格符
            if (!line.equals("待明确地区 0")) {//如果有“待明确地区 0”，删除
                strArray.add(line);
            }
            line = br.readLine();//读取下一行文本
        }
        return strArray;//输出处理后的集合信息
    }


    public static void main(String[] args) {
        new fileProject().fileProject1();
    }//执行

}
