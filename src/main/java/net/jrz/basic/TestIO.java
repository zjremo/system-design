package net.jrz.basic;

import java.io.*;

public class TestIO {

    /* 字节流 */
    public static void testFileInputStream() {
        try (InputStream fis = new FileInputStream("input.txt")) {
            System.out.println("Number of remaining bytes: " + fis.available());
            int content;
            long skip = fis.skip(2);
            System.out.println("The actual number of bytes skipped:" + skip);
            System.out.print("The content read from file:");
            while ((content = fis.read()) != -1) {
                System.out.print((char) content);
            }

        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }

    public static void testBufferedInputStream() {
        try (BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream("input.txt"))) {
            StringBuilder sb = new StringBuilder();
            int content;
            while ((content = bufferedInputStream.read()) != -1) {
                sb.append((char) content + "");
            }
            System.out.println(sb.toString());
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }

    public static void testFileOutputStream() {
        try (FileOutputStream outputStream = new FileOutputStream("output.txt")) {
            String toWrite = "hello, world";
            byte[] array = toWrite.getBytes();
            outputStream.write(array);
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }

    public static void testBufferedOutputStream() {
        try (
                FileOutputStream fos = new FileOutputStream("output.txt");
                BufferedOutputStream bos = new BufferedOutputStream(fos);) {
            String toWrite = "javaGuide";
            bos.write(toWrite.getBytes());
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }

    /* 字符流 */
    public static void testFileReader() {
        try (FileReader fr = new FileReader("input.txt");) {
            int content;
            long skip = fr.skip(2);
            System.out.println("The actual number of characters skipped:" + skip);
            System.out.print("The content read from file:");
            while ((content = fr.read()) != -1) {
                System.out.print((char) content);
            }
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }

    public static void testFileWriter() {
        try (FileWriter fw = new FileWriter("output.txt")) {
            fw.write("你好，我是Guide.");
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }

    public static void testBufferedReader_v1() {
        try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line); // 逐行读取每一行的内容
            }
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }

    public static void testBufferedWriter() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"))) {
            bw.write("hi, I am zjr");
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }

    public static void testBufferedReader_v2() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(br.readLine());
            System.out.println("n: " + n);

            String line = br.readLine();
            System.out.println("line: " + line);
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }

    public static void main(String[] args) {
        testBufferedReader_v2();
    }

}
