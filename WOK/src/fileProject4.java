
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.Collator;
import java.util.*;


public class fileProject4 {

    public fileProject4(List<ArrayList<String>> Array) throws IOException {

        File file = new File("yq_out_04.txt");
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
        for (ArrayList<String> aStr : Array) {
            String[] ap = aStr.toArray(new String[0]);
//            System.out.println(ap);
            for (String s : ap) {
                bufferedWriter.write(s);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
            bufferedWriter.newLine();
        }
        bufferedWriter.close();
        System.out.println("成功！");

    }

    public fileProject4(List<ArrayList<String>> Array, String pro) throws IOException {

        File file = new File("yq_out_04.txt");
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
        for (ArrayList<String> aStr : Array) {
            if (aStr.get(0).contains(pro)){
                String[] ap = aStr.toArray(new String[0]);
//            System.out.println(ap);
                for (String s : ap) {
                    bufferedWriter.write(s);
                    bufferedWriter.newLine();
                    bufferedWriter.flush();
                }
            }
        }
        bufferedWriter.close();
        System.out.println("成功！");

    }

    /**
     * 在集合中按需求处理数据，返回处理后的集合
     * @param Array
     * @return Array
     */
    public static List<ArrayList<String>> da(ArrayList<ArrayList<String>> Array){
        ArrayList<String> strArray;
        String str;
        for (ArrayList<String> strings : Array) {
            strArray = strings;
            strArray.sort((Comparator<String>) (o1, o2) -> {
                int i1 = Integer.parseInt(o1.substring(o1.lastIndexOf("\t") + 1));
                int i2 = Integer.parseInt(o2.substring(o2.lastIndexOf("\t") + 1));
                if (i2 != i1)
                    return Integer.compare(i2, i1);
                Collator collator = Collator.getInstance(java.util.Locale.CHINA);
                return collator.getCollationKey(o1).compareTo(collator.getCollationKey(o2));
            });//重写集合排序方法对集合内集合进行排序

            for (int j = 0; j < strArray.size(); j++) {
                str = strArray.get(j);
                if (str.lastIndexOf("待明确地区\t0") != -1) {
                    strArray.remove(j);
                }
            }
            for (int j = 0; j < strArray.size(); j++) {
                str = strArray.get(j);
                if (j == 0) {
                    strArray.add(j, str.substring(0, str.indexOf("\t")) +"有" + strArray.size() + "个城市");
//                    System.out.println(str.substring(0,str.indexOf("\t")+1));
                    continue;
                }
                str = str.replaceAll("\t", " ");
                strArray.set(j, str.substring(str.indexOf(" ") + 1));
//                System.out.println(strArray.get(j));
            }
        }
        Array.sort((Comparator<ArrayList<String>>) (o1, o2) -> {
            if (o2.size() != o1.size()){
                return Integer.compare(o2.size(), o1.size());
            }
            Collator collator = Collator.getInstance(java.util.Locale.CHINA);
            return collator.getCollationKey(o1.toString()).compareTo(collator.getCollationKey(o2.toString()));
        });
        return Array;
//        System.out.println(Array.toString());
    }

    /**
     * 将给定的文件输入到集合中，返回集合
     * @param pathname——>文件名
     * @return Array集合
     * @throws IOException
     */
    public static ArrayList<ArrayList<String>> readFromTextFile(String pathname) throws IOException {
        ArrayList<ArrayList<String>> Array = new ArrayList<>();//创建集合
        BufferedReader br1 = new BufferedReader(new InputStreamReader(new FileInputStream(new File(pathname)), StandardCharsets.UTF_8));
        String line = br1.readLine();//读取一行文本
        int locationNum = line.indexOf("\t");//获取该行第一个制表符位置
        String line1 = line.substring(0, locationNum);
        ArrayList<String> strArray = new ArrayList<>();
        do {
            if (!line1.equals(line.substring(0, locationNum))) {
                Array.add(strArray);
                line1 = line.substring(0, locationNum);//获取省份名
                strArray = new ArrayList<>();
            }
            if (line1.equals(line.substring(0, locationNum))){
                strArray.add(line);
            }
            line = br1.readLine();
        }while(line != null);
        Array.add(strArray);
        return Array;
    }
}
