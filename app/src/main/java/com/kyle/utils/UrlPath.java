package com.kyle.utils;

import com.lidroid.xutils.http.RequestParams;

/**
 * Created by Administrator on 2015/3/24.
 */
public class UrlPath {
    public static String baseUrl_Information = "http://admin.bj.straits-media.com/api_v2.php?action=list";
    public static final String picturehome="http://admin.bj.straits-media.com";
    public static final String recommended="http://admin.bj.straits-media.com/api_v2.php?action=recommend&offset=%d&count=%d&e=42511ecc63f23750e13168e98c42b4e8&uid=8054711&pid=10121&mobile=iPhone4,1&platform=i";
    public static final String home1="http://admin.bj.straits-media.com";
    public static final String recom_detail="http://admin.bj.straits-media.com/api_v2.php?action=recommend_list&offset=%d&count=%d&ztid=%s&e=42511ecc63f23750e13168e98c42b4e8&uid=8054711&pid=10121&mobile=iPhone4,1&platform=i";
    public static final String recom_detail_web="http://admin.bj.straits-media.com/api_v2.php?action=article2&id=%s&fontsize=m&mode=day&uid=13171876&e=853b764594dbfedc079fb3d06f448d02&platform=a&pid=10121&mobile=M811";
    public static final String citylook="http://admin.bj.straits-media.com/api_v2.php?action=list&sa=city&offset=%d&count=%d&e=42511ecc63f23750e13168e98c42b4e8&uid=8054711&pid=10121&mobile=iPhone4,1&platform=i";

    public static RequestParams getparams(String sa,int count){

        RequestParams params =new RequestParams();
        params.addBodyParameter("sa", sa);
        params.addBodyParameter("offset","0");
        params.addBodyParameter("count",count+"");
        params.addBodyParameter("uid","13171876");
        params.addBodyParameter("platform","a");
        params.addBodyParameter("mobile","M811");
        params.addBodyParameter("pid","10121");
        params.addBodyParameter("e","853b764594dbfedc079fb3d06f448d02");

//        sa=news&offset=0&count=15&uid=13171876&platform=a&mobile=M811&pid=10121&e=853b764594dbfedc079fb3d06f448d02
        return params;
    }
    public static String getTel(){
        return  baseUrl_Information+"2&"+"sa=tel&offset=0&count=1000&uid=13171876&platform=a&mobile=M811&pid=10121&e=853b764594dbfedc079fb3d06f448d02";
    }
    //资讯listURL
    public static String getUrlInformation(String sa,int page){
        return baseUrl_Information+"&"+"sa="+sa+"&offset="+((page-1)*15)+"&count=15&uid=13171876&platform=a&mobile=M811&pid=10121&e=853b764594dbfedc079fb3d06f448d02";
    }
    //资讯item详情页
    public static String getUrlInformationDetail(String id){
        return "http://admin.bj.straits-media.com/api_v2.php?action=article&id=" +id+"&fontsize=m&mode=day&uid=13171876&e=853b764594dbfedc079fb3d06f448d02&platform=a&pid=10121&mobile=M811";
    }
    public static String getRecommended(int offset,int count){
        return  String.format(recommended,offset,count);

    }
    //城市风貌
    public static String getCitylook(int offset,int count){
        return String.format(citylook,offset,count);
    }
    //首页请求地址
    public static String getHomePageUrl(){
        return "http://admin.bj.straits-media.com/api_v2.php?action=top";
    }
    //推荐页面
    public static String getRecom_detail(int offset,int count,String ztid){
        return String.format(recom_detail,offset,count,ztid);
    }
    //推荐图片
    public static String getTitlePicture(){
        return "http://admin.bj.straits-media.com/upload/day_140324/201403241854569_316_iphone4.jpg";
    }
    //攻略图片
    public static String getStrategyPicture() {
        return "http://admin.bj.straits-media.com/upload/day_140324/201403241854579_318_iphone4.jpg";
    }
    //资讯图片
    public static String getInfoPicture(){
        return "http://admin.bj.straits-media.com/upload/day_140324/201403241854544_309_iphone4.jpg";
    }
    //城市风貌
    public static String getCityPicture(){
        return "http://admin.bj.straits-media.com/upload/day_140324/201403241854546_310_iphone4.jpg";
    }
    //旅游的信息
    public static String getTravelPicture(){
        return "http://admin.bj.straits-media.com/upload/day_140324/201403241854573_319_iphone4.jpg";
    }
    //地图信息
    public static String getMapPicture(){
        return "http://admin.bj.straits-media.com/upload/day_140324/201403241854575_332_iphone4.jpg";
    }
    //交通图片
    public static String getTraggicPicture(){
        return "http://admin.bj.straits-media.com/upload/day_140324/201403241854549_328_iphone4.jpg";
    }
    //获取到推荐Post请求
    public static String getTitleUrl(String mobile){
        return "http://admin.bj.straits-media.com/api_v2.php?action=recommend&offset=0&count=15&e=42511ecc63f23750e13168e98c42b4e8&uid=8054711&pid=10121&mobile="+mobile+"&platform=i";
    }
    //推荐页中每个item
    public static String getTitleItemUrl(){
        return "http://admin.bj.straits-media.com/api_v2.php?action=recommend_list&ztid=1827";
    }
    //推荐页中每个item点击进去后的详情
    public static String getItemDetails(String mobile){
        return "http://admin.bj.straits-media.com/api_v2.php?action=article2&id=1831&fontsize=m&mode=day&uid=13171876&e=853b764594dbfedc079fb3d06f448d02&platform=a&pid=10121&mobile="+mobile;
    }
    //攻略地址Post请求
    public static String getStrategyUrl(String sa,String mobile){
        return "http://admin.bj.straits-media.com/api_v2.php?action=gl_zt&sa="+sa+"&offset=0&count=15&uid=13171876&platform=a&mobile="+mobile+"&pid=10121&e=853b764594dbfedc079fb3d06f448d02";
    }

}
