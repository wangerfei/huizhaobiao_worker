package com.huizhaobiao.User.pojo;

import com.deepoove.poi.data.DocxRenderData;

/**
 * Created by Administrator on 2019/7/8.
 */
public class StoryData {


    private String storyName;
    private String storyAuthor;
    private DocxRenderData segment;
    private String storySource;
    private String summary;

    public String getStoryName() {
        return storyName;
    }

    public void setStoryName(String storyName) {
        this.storyName = storyName;
    }

    public String getStoryAuthor() {
        return storyAuthor;
    }

    public void setStoryAuthor(String storyAuthor) {
        this.storyAuthor = storyAuthor;
    }

    public DocxRenderData getSegment() {
        return segment;
    }

    public void setSegment(DocxRenderData segment) {
        this.segment = segment;
    }

    public String getStorySource() {
        return storySource;
    }

    public void setStorySource(String storySource) {
        this.storySource = storySource;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}
