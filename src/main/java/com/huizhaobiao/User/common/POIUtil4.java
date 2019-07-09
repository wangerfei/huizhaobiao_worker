package com.huizhaobiao.User.common;

import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.data.*;

import javax.swing.text.StringContent;
import java.io.File;
import java.io.FileOutputStream;
import java.util.*;

/**
 * Created by Administrator on 2019/7/8.
 */
public class POIUtil4 {

    public  static Boolean createTable(String inFile,String outFile,
                                       HashMap<String,String> parentContent,
                                       Map<String,HashMap<String,String>> Content,
                                       Map<String,HashMap<Integer,ArrayList<String>>> tableContent)
    {
        if (inFile.isEmpty()||outFile.isEmpty())
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
        ArrayList<Object> pbtables = new ArrayList<Object>();  //存储器

        Set<String> sumMapSet_content=Content.keySet();
        Object[] sumMapSet_content_array=sumMapSet_content.toArray();
        Set<String> sumMapSet_table=tableContent.keySet();
        Object[] sumMapSet_table_array=sumMapSet_content.toArray();
        for (String key:sumMapSet_content) {

            HashMap<String,String> Content_two  =Content.get(key);
            HashMap<Integer,ArrayList<String>> tableContent_two=tableContent.get(key);
            Map<String, Object> datas = new HashMap<String, Object>(){{


                String[] contentList = {"title", "bdcode", "bdname", "pwhcy"}; //需换成动态的
                for (String contentListKey : contentList) {
                    put(contentListKey, new TextRenderData(Content_two.get(contentListKey)));

                }

                Set setTable=tableContent_two.entrySet();
                Iterator objs_table=setTable.iterator();
                while (objs_table.hasNext())
                {
                    Map.Entry entry = (Map.Entry) objs_table.next();
                    //需换成 动态的
                    put("#compare".replace("#",""), new TableRenderData(new ArrayList<RenderData>(){{

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

            pbtables.add( datas);
        }

        //模块模板
        DocxRenderData pbtable_temple = new DocxRenderData(new File("G:\\BaiduNetdiskDownload\\ihrm_parent\\huizhaobiao_worker\\src\\main\\resources\\pbtable.docx"), pbtables );
        content_all.put("segment",pbtable_temple);
        //综合模板
        XWPFTemplate template = XWPFTemplate.compile("G:\\BaiduNetdiskDownload\\ihrm_parent\\huizhaobiao_worker\\src\\main\\resources\\zonghe.docx").render(content_all);

        FileOutputStream out = null;
        try {
            out = new FileOutputStream("G:\\BaiduNetdiskDownload\\ihrm_parent\\huizhaobiao_worker\\src\\main\\resources\\out_story20.docx");
            template.write(out);
            out.flush();
            out.close();
            template.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return  null;
    }




}
