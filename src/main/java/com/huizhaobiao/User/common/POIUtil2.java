package com.huizhaobiao.User.common;

import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.data.DocxRenderData;
import com.deepoove.poi.data.MiniTableRenderData;
import com.deepoove.poi.data.RowRenderData;

import java.io.File;
import java.io.FileOutputStream;
import java.util.*;

/**
 * Created by Administrator on 2019/7/8.
 */
public class POIUtil2 {

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

        content_all.put("zonghe",parentContent);
        /*表格模板内容*/
        List pbtables = new ArrayList<>();  //存储器

        Set setContent=Content.entrySet();
        Iterator objs_content=setContent.iterator();

        Set setTable=tableContent.entrySet();
        Iterator objs_table=setTable.iterator();

        while (objs_table.hasNext()&&objs_content.hasNext()) {

            HashMap<Object, Object> pbtable = new HashMap<Object, Object>();
            Map.Entry entry = (Map.Entry) objs_table.next();


            Set<String> sumMapSet=Content.keySet();
            for (String key:sumMapSet)
            {
                HashMap<String, String> sub_tableContent=Content.get(key);
                Set<String> Sub_sumMapSet=sub_tableContent.keySet();
                for (String sub_key: Sub_sumMapSet)
                {
                    pbtable.put(key, sub_tableContent.get(key));
                }
            }


            Set<String> sumMapSet_table=tableContent.keySet();
            for (String key:sumMapSet_table) {

                //Map.Entry entry_table_hashMap = (Map.Entry) entry_table.getValue();
                //获取表格中的内容

                HashMap<Integer, ArrayList<String>> sub_tableContent=tableContent.get(key);

                List<RowRenderData> rows = new ArrayList<RowRenderData>();
                Set<Integer> sub_tableContent_index=sub_tableContent.keySet();
                for (Integer index:sub_tableContent_index) {

                    RowRenderData row = RowRenderData.build(String.valueOf(sub_tableContent.get(index)));

                    rows.add(row);
                }
                pbtable.put(entry.getKey(), new MiniTableRenderData(rows));
                pbtables.add(pbtable);
            }
        }

        //模块模板
        DocxRenderData pbtable_temple = new DocxRenderData(new File("G:\\BaiduNetdiskDownload\\ihrm_parent\\huizhaobiao_worker\\src\\main\\resources\\pbtable.docx"), pbtables );
        content_all.put("pbtable",pbtable_temple);
        //综合模板
        XWPFTemplate template = XWPFTemplate.compile("G:\\BaiduNetdiskDownload\\ihrm_parent\\huizhaobiao_worker\\src\\main\\resources\\zonghe.docx").render(content_all);

        FileOutputStream out = null;
        try {
            out = new FileOutputStream("G:\\BaiduNetdiskDownload\\ihrm_parent\\huizhaobiao_worker\\src\\main\\resources\\out_story1.docx");
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
