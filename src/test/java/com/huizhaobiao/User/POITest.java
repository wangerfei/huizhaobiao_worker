package com.huizhaobiao.User;

import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.data.PictureRenderData;
import com.deepoove.poi.data.RenderData;
import com.deepoove.poi.data.TableRenderData;
import com.deepoove.poi.data.TextRenderData;
import com.deepoove.poi.render.RenderAPI;
import com.huizhaobiao.User.common.POIUtil;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2019/7/6.
 */
public class POITest {

  /*  String title,String bdname,String bdcode,
      ArrayList<String> pwhcy ,HashMap<Target,ArrayList<String>> tableContent,int rowAll,String outFile)
*/

    public static void main(String[] args) throws Exception {
      /*  ArrayList pwhcy= new ArrayList<String>();
        pwhcy.add("王守超");
        HashMap<Integer,ArrayList<String>> tableContent =new  HashMap<Integer,ArrayList<String>>();
        for (int i=0;i<5;i++)
        {
            ArrayList<String> content= new ArrayList<String>();
            content.add("王守超"+i);
            content.add("王守超"+i);
            content.add("王守超"+i);
            content.add("王守超"+i);
            tableContent.put(Integer.valueOf(i),content);
        }
        PoiUtil.createTable("资格评审","河北师范大学教学楼施工","I1301000075014813001001"
        ,pwhcy,tableContent,5,"out16");*/


    //    System.out.println(POITest.getContent("G:\\BaiduNetdiskDownload\\ihrm_parent\\huizhaobiao_worker\\src\\main\\resources\\pbtable.docx"));

        Map<String,HashMap<Integer,ArrayList<String>>> tableContent=new HashMap<String,HashMap<Integer,ArrayList<String>>>();
        HashMap<String,String> Content1=new HashMap<String,String>();
        Content1.put("title","标题1111");
        Content1.put("bdcode","编号1111");
        Content1.put("bdname","ffff"+"\r");
        Content1.put("pwhcy","王兆111");


        HashMap<Integer,ArrayList<String>> table1=new HashMap<Integer, ArrayList<String>>();
        for (int i=0;i<5;i++)
        {
            ArrayList<String> content= new ArrayList<String>();
            content.add("王守超"+i+1);
            content.add("王守超"+i+1);
            content.add("王守超"+i+1);
            content.add("王守超"+i+1);
            table1.put(i,content);
        }
        tableContent.put("#compare",table1);

        POIUtil.createTable("G:\\BaiduNetdiskDownload\\ihrm_parent\\huizhaobiao_worker\\src\\main\\resources\\pbtable.docx",
                "G:\\BaiduNetdiskDownload\\ihrm_parent\\huizhaobiao_worker\\src\\main\\resources\\test8.docx",Content1,tableContent);


    }


    public static  List<String>  getContent(String templePath)
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
            if (!paragraph.getText().isEmpty()&&!paragraph.getText().equals("")) {
                String s=paragraph.getText().substring(paragraph.getText().indexOf("{{")+2,paragraph.getText().indexOf("}}"));
                all.add(s);
            }


        }
        return  all;
    }


}
