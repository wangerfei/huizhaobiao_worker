package com.huizhaobiao.User.common;
import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.data.*;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import java.util.*;
/**
 * Created by Administrator on 2019/7/9.
 */
public class POIUtil3 {

    public  static Boolean createContent(String inParentFile,
                                       String inSonFile,
                                       String outFile,
                                       HashMap<String,String> parentContent,
                                       Map<String,HashMap<String,String>> Content,
                                       Map<String,HashMap<Integer,ArrayList<String>>> tableContent)
    {
        if (inParentFile.isEmpty()||outFile.isEmpty()||inSonFile.isEmpty())
        {
            return  false;
        }


        //综合模板内容
        Map<String,Object> content_all = new HashMap<>();

        Set<String> keySet=parentContent.keySet();
        for (String key:keySet) {
            content_all.put(key, parentContent.get(key));

        }
        /*表格模板内容*/
        List<Object> pbtables = new ArrayList<Object>();  //存储器

        Set<String> sumMapSet_content=Content.keySet();
        Set<String> sumMapSet_table=tableContent.keySet();

        for (String key:sumMapSet_table) {

            HashMap<String,String> Content_two  =Content.get(key);
            HashMap<Integer,ArrayList<String>> tableContent_two=tableContent.get(key);
            //调用创建表格
            Map<String, Object> data=POIUtil3.createTabel(Content_two,tableContent_two,inSonFile);

           // Boolean b=POIUtil.create_son_table(inSonFile,data,"G:\\BaiduNetdiskDownload\\ihrm_parent\\huizhaobiao_worker\\src\\main\\resources\\bbbb.docx");

            pbtables.add(data);

        }

        //获取父模板(获取“+”模板标签)
       String parentSegment="";
        List<String> parent_all=POIUtil3.getContent(inParentFile);
        for (String parent_all_item:parent_all)
        {
            if (parent_all_item.contains("+"))
            {
                parentSegment=parent_all_item.replace("+","");
            }
        }
        //模块模板
        DocxRenderData pbtable_temple = new DocxRenderData(new File(inSonFile), pbtables );
        content_all.put(parentSegment,pbtable_temple);

        //综合模板
        XWPFTemplate template = XWPFTemplate.compile(inParentFile).render(content_all);
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(outFile);
            template.write(out);
            out.flush();
            out.close();
            template.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return  null;
    }


    public  static  Map<String, Object> createTabel(HashMap<String,String> Content_two,HashMap<Integer,ArrayList<String>> tableContent_two,String inSonFile)
    {
        List<String> titelAll= POIUtil3.getContent(inSonFile);

        Map<String, Object> datas = new HashMap<String, Object>(){{

            //获取本文的 文本标签
            List<String> titelAll_tiem  = new ArrayList<String>(); //需换成动态的
            for (String tiem :titelAll)
            {
                if (!tiem.contains("#"))
                {
                    titelAll_tiem.add(tiem);
                }
            }

            for (String contentListKey : titelAll_tiem) {
                put(contentListKey, new TextRenderData(Content_two.get(contentListKey)));

            }

            XWPFDocument doc = new XWPFDocument();
            XWPFParagraph p = doc.createParagraph();
            p.setPageBreak(true);



            Set setTable=tableContent_two.entrySet();
            Iterator objs_table=setTable.iterator();
            while (objs_table.hasNext())
            {
                Map.Entry entry = (Map.Entry) objs_table.next();

                //需换成 动态的
                String tabelAll_tiem  = ""; //需换成动态的
                for (String tiem :titelAll)
                {
                    if (tiem.contains("#"))
                    {
                        tabelAll_tiem=tiem;
                    }
                }
                //"#compare"

                put(tabelAll_tiem.replace("#",""), new TableRenderData(new ArrayList<RenderData>(){{

                    for (String tableName:tableContent_two.get(Integer.valueOf(0)))
                    {
                        add(new TextRenderData( tableName));
                    }

                }}, new ArrayList<Object>(){{

                    for (int i=1;i<tableContent_two.get(entry.getKey()).size();i++)
                    {
                        String rowContent="";
                        for (String content : tableContent_two.get(i)) {
                            rowContent=content+";"+rowContent;
                        }
                        rowContent= rowContent.substring(0,rowContent.length()-1);
                        add(rowContent);
                    }

                }}, "no datas", 10000));


            }
        }};

        return  datas;

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
            if (!paragraph.getText().isEmpty()&&!paragraph.getText().equals("")&&paragraph.getText().contains("}}")) {
                String s=paragraph.getText().substring(paragraph.getText().indexOf("{{")+2,paragraph.getText().indexOf("}}"));
                all.add(s);
            }

        }
        return  all;
    }

}
