package com.liao.developernews.model;

import java.util.List;

/**
 * Created by liao on 2016-5-8 0008.
 */
public class NewsItem
{

    /**
     * error : false
     * results : [{"_id":"572c10ac67765974fbfcfa19","createdAt":"2016-05-06T11:34:04.190Z","desc":"一个学习 Android 开发相关技术的项目","publishedAt":"2016-05-06T12:04:47.203Z","source":"chrome","type":"Android","url":"http://www.jianshu.com/p/faf1ce1e232b","used":true,"who":"AndWang"},{"_id":"572c0df967765974f885c01c","createdAt":"2016-05-06T11:22:33.827Z","desc":"https://github.com/marcuswestin/WebViewJavascriptBridge Objective-C与JavaScript交互在Android端的扩展","publishedAt":"2016-05-06T12:04:47.203Z","source":"web","type":"Android","url":"https://github.com/gzsll/WebViewJavascriptBridge","used":true,"who":"gzsll"},{"_id":"572be7a967765974fca83109","createdAt":"2016-05-06T08:39:05.394Z","desc":"Retrofit分析-经典设计模式案例","publishedAt":"2016-05-06T12:04:47.203Z","source":"web","type":"Android","url":"http://www.jianshu.com/p/fb8d21978e38","used":true,"who":"Stay"},{"_id":"572ae41067765974fca830fc","createdAt":"2016-05-05T14:11:28.872Z","desc":"Android 插件化的过去-现在-未来","publishedAt":"2016-05-06T12:04:47.203Z","source":"web","type":"Android","url":"http://kymjs.com/code/2016/05/04/01","used":true,"who":"张涛"},{"_id":"572aae1067765974f885c009","createdAt":"2016-05-05T10:21:04.34Z","desc":"Android超高仿QQ附近的人搜索展示","publishedAt":"2016-05-05T12:14:21.156Z","source":"web","type":"Android","url":"https://github.com/ImmortalZ/RadarScan","used":true,"who":"Mr_immortalZ"},{"_id":"572aa63a67765974fca830f6","createdAt":"2016-05-05T09:47:38.946Z","desc":"Android端网传附莆田系医院名单搜索软件","publishedAt":"2016-05-05T12:14:21.156Z","source":"chrome","type":"Android","url":"https://github.com/shixinzhang/BlackheartedHospitalApp","used":true,"who":"蒋朋"},{"_id":"572a1d9767765974fbfcfa05","createdAt":"2016-05-05T00:04:39.563Z","desc":"隐藏app的app，用处自己挖掘。","publishedAt":"2016-05-05T12:14:21.156Z","source":"chrome","type":"Android","url":"https://github.com/blackbbc/Evil-Hide","used":true,"who":"Jason"},{"_id":"572a196767765974fca830f2","createdAt":"2016-05-04T23:46:47.909Z","desc":"切换页面的圆形动画效果","publishedAt":"2016-05-05T12:14:21.156Z","source":"chrome","type":"Android","url":"https://github.com/SpikeKing/wcl-circle-reveal-demo","used":true,"who":"Jason"},{"_id":"5729d02267765974f5e27e89","createdAt":"2016-05-04T18:34:10.599Z","desc":"安卓启动流程概述。","publishedAt":"2016-05-06T12:04:47.203Z","source":"web","type":"Android","url":"https://yq.aliyun.com/articles/3005","used":true,"who":null},{"_id":"5729c94767765974f5e27e88","createdAt":"2016-05-04T18:04:55.85Z","desc":"如何把你的App加入白名单，列举各大厂商手机设置指南。","publishedAt":"2016-05-06T12:04:47.203Z","source":"web","type":"Android","url":"https://github.com/lorcanluo/androidwhitelist","used":true,"who":"lorcan"}]
     */

    private boolean error;
    /**
     * _id : 572c10ac67765974fbfcfa19
     * createdAt : 2016-05-06T11:34:04.190Z
     * desc : 一个学习 Android 开发相关技术的项目
     * publishedAt : 2016-05-06T12:04:47.203Z
     * source : chrome
     * type : Android
     * url : http://www.jianshu.com/p/faf1ce1e232b
     * used : true
     * who : AndWang
     */

    private List<ResultsBean> results;

    public boolean isError()
    {
        return error;
    }

    public void setError(boolean error)
    {
        this.error = error;
    }

    public List<ResultsBean> getResults()
    {
        return results;
    }

    public void setResults(List<ResultsBean> results)
    {
        this.results = results;
    }

    public static class ResultsBean
    {
        private String _id;
        private String createdAt;
        private String desc;
        private String publishedAt;
        private String source;
        private String type;
        private String url;
        private boolean used;
        private String who;

        public String get_id()
        {
            return _id;
        }

        public void set_id(String _id)
        {
            this._id = _id;
        }

        public String getCreatedAt()
        {
            return createdAt;
        }

        public void setCreatedAt(String createdAt)
        {
            this.createdAt = createdAt;
        }

        public String getDesc()
        {
            return desc;
        }

        public void setDesc(String desc)
        {
            this.desc = desc;
        }

        public String getPublishedAt()
        {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt)
        {
            this.publishedAt = publishedAt;
        }

        public String getSource()
        {
            return source;
        }

        public void setSource(String source)
        {
            this.source = source;
        }

        public String getType()
        {
            return type;
        }

        public void setType(String type)
        {
            this.type = type;
        }

        public String getUrl()
        {
            return url;
        }

        public void setUrl(String url)
        {
            this.url = url;
        }

        public boolean isUsed()
        {
            return used;
        }

        public void setUsed(boolean used)
        {
            this.used = used;
        }

        public String getWho()
        {
            return who;
        }

        public void setWho(String who)
        {
            this.who = who;
        }
    }
}
