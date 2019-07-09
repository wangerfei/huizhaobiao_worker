package com.huizhaobiao.User;

import com.huizhaobiao.User.common.POIUtil3;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019/7/9.
 */
public class POITest3 {
    @Test
    public void testStoryExample() throws Exception{

        System.out.println( POITest3.getContent("G:\\BaiduNetdiskDownload\\ihrm_parent\\huizhaobiao_worker\\src\\main\\resources\\out_test15.docx"));
    }

    public static List<String> getContent(String templePath)
    {
        List<String> all=new ArrayList<>();
        XWPFDocument doc = null;
        try {
            doc = new XWPFDocument(new FileInputStream(templePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 段落
        List<XWPFParagraph> paragraphs = doc.getParagraphs();
        // 获取文字
        for (XWPFParagraph paragraph : paragraphs)
        {

                String s=paragraph.getText();
                all.add(s);


        }
        return  all;
    }
}
