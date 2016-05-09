package com.liao.developernews.model;

/**
 * Created by liao on 2016-4-30 0030.
 */
public class ItemBean
{
    // 标题
    private String title;
    // 头像
    private String headerPic;
    // 昵称
    private String name;
    // 赞
    private String like;
    // 评论
    private String comment;
    // title的链接
    private String url;

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getHeaderPic()
    {
        return headerPic;
    }

    public void setHeaderPic(String headerPic)
    {
        this.headerPic = headerPic;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getLike()
    {
        return like;
    }

    public void setLike(String like)
    {
        this.like = like;
    }

    public String getComment()
    {
        return comment;
    }

    public void setComment(String comment)
    {
        this.comment = comment;
    }
}
