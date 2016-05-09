package com.liao.notebook.model;

/**
 * Created by liao on 2016-5-2 0002.
 */
public class NoteBean
{
    private String id;

    private String title;

    private String content;

    private String imgUri;

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public String getImgUri()
    {
        return imgUri;
    }

    public void setImgUri(String imgUri)
    {
        this.imgUri = imgUri;
    }
}
