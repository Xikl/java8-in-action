package com.ximo.java8.chap3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author 朱文赵
 * @date 2018/2/26 16:15
 * @description
 */
public class ExecuteAround {


    public static void main(String[] args) {
        try {
            String result = processFileLimited();
            System.out.println(result);

            System.out.println("-----");

            String oneLines = processFile(BufferedReader::readLine);
            System.out.println(oneLines);

            //相当于process的具体实现
            String twoLines = processFile(b -> b.readLine() + b.readLine());
            System.out.println(twoLines);

            String oneLines2 = processFile(new BufferedReaderProcessor() {
                @Override
                public String process(BufferedReader reader) throws IOException {
                    return reader.readLine();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String processFileLimited() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("data.txt"))) {
            return br.readLine();
        }
    }


    private static String processFile(BufferedReaderProcessor processor) throws IOException{
        try (BufferedReader br = new BufferedReader(new FileReader("data.txt"))) {
            return processor.process(br);
        }

    }

    @FunctionalInterface
    private interface BufferedReaderProcessor {

        /**
         * 处理方法
         * @param reader 缓冲类
         * @return 一行或者多行的内容
         * @throws IOException 文件异常
         */
        String process(BufferedReader reader) throws IOException;

    }

}
