package com.huizhaobiao.User;

import com.deepoove.poi.data.MiniTableRenderData;
import com.deepoove.poi.data.RowRenderData;
import com.deepoove.poi.data.TextRenderData;
import com.huizhaobiao.User.common.POIUtil4;
import com.huizhaobiao.User.common.POiUtil5;
import com.huizhaobiao.User.pojo.ParentData;
import com.huizhaobiao.User.pojo.SegmentData;
import com.huizhaobiao.User.pojo.TableData;
import org.junit.Test;

import javax.xml.soap.Text;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019/7/10.
 */
public class POITestStatic {


    @Test
    public void test()
    {

        ParentData data=new ParentData();
        data.setTitle("大标题");
        List<SegmentData>   segmentDatas=new ArrayList<>();
        SegmentData s1=new SegmentData();
        s1.setTitle("小标题");
        s1.setBdname("标段名称");
        s1.setBdcode("标段编号");
        s1.setPwhcy("评标人");

       TableData tableData=new TableData();
       tableData.setHeader(RowRenderData.build(new TextRenderData("姓名"),new TextRenderData("学历")));

       List<RowRenderData> bodys =new ArrayList<>();
       RowRenderData row0 = RowRenderData.build("张三", "研究生");
       bodys.add(row0);
       RowRenderData row1 = RowRenderData.build("李四", "博士");
       bodys.add(row1);
       RowRenderData row2 = RowRenderData.build("王五", "博士后");
       bodys.add(row2);

       tableData.setBodys(bodys);

       s1.setCompare(new MiniTableRenderData(tableData.getHeader(),tableData.getBodys()));

       segmentDatas.add(s1);

       SegmentData s2=new SegmentData();
       s2.setTitle("小标题1");
       s2.setBdname("标段名称1");
       s2.setBdcode("标段编号1");
       s2.setPwhcy("评标人1");
       TableData tableData2=new TableData();
       tableData2.setHeader(RowRenderData.build(new TextRenderData("姓名1"),new TextRenderData("学历1")));

       List<RowRenderData> bodys2 =new ArrayList<>();
       RowRenderData row02 = RowRenderData.build("张三1", "研究生1");
       bodys2.add(row02);
       RowRenderData row12 = RowRenderData.build("李四1", "博士1");
       bodys2.add(row12);

       for (int i=0;i<30;i++)
       {
          RowRenderData row22 = RowRenderData.build("王五1", "博士后1");
          bodys2.add(row22);
       }
       RowRenderData row22 = RowRenderData.build("王五1", "博士后1");
       bodys2.add(row22);
       tableData2.setBodys(bodys2);
       s2.setCompare(new MiniTableRenderData(tableData2.getHeader(),tableData2.getBodys()));
       segmentDatas.add(s2);
       POiUtil5.creatPOI("G:\\BaiduNetdiskDownload\\ihrm_parent\\huizhaobiao_worker\\src\\main\\resources\\pbtable.docx",
               "G:\\BaiduNetdiskDownload\\ihrm_parent\\huizhaobiao_worker\\src\\main\\resources\\zonghe.docx",
               "G:\\BaiduNetdiskDownload\\ihrm_parent\\huizhaobiao_worker\\src\\main\\resources\\static_out5.docx",
               segmentDatas,data);
   }
}
