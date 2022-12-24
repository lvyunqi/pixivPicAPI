package com.mryunqi.pixivtgbot.service;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.mryunqi.pixivtgbot.service.Visit.getUrlData;
import static com.mryunqi.pixivtgbot.utils.PixivPicDateChangeUtil.pixivPicDateChange;

/**
 * @author mryunqi
 * @date 2022/12/24
 */
public class Structure {
    public static JSONObject painterIllustrationStructure(long uid,int page) throws Exception {
        String url;
        if (page == 1){
            url = "https://www.pixiv.net/touch/ajax/user/illusts?id="+uid+"&type=illust&lang=zh";
        }else {
            url = "https://www.pixiv.net/touch/ajax/user/illusts?id="+uid+"&p="+page+"&lang=zh";
        }
        JSONObject jsonData = getUrlData(url);
        List<JSONObject> painterIllustrationList = getPainterIllustrationList(jsonData);
        JSONObject body = jsonData.getJSONObject("body");
        JSONArray tags = body.getJSONArray("tags");
        int total = body.getIntValue("total");
        int lastPage = body.getIntValue("lastPage");
        JSONObject meta = body.getJSONObject("meta");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("illusts",painterIllustrationList);
        jsonObject.put("tags",tags);
        jsonObject.put("total",total);
        jsonObject.put("current",page);
        jsonObject.put("lastPage",lastPage);
        jsonObject.put("meta",meta);
        return jsonObject;
    }

    public static List<JSONObject> getPainterIllustrationList(JSONObject initData){
        List<JSONObject> result = new ArrayList<>();
        if (initData == null){
            return result;
        }
        JSONObject body = initData.getJSONObject("body");
        JSONArray illustsData = body.getJSONArray("illusts");
        for(int i=0;i<illustsData.size();i++){
            JSONObject job = illustsData.getJSONObject(i);
            long pid = job.getLong("id");
            String title = job.getString("title");
            String alt = job.getString("alt");
            String comment = job.getString("comment");
            int width  = job.getIntValue("width");
            int height = job.getIntValue("height");
            int aiType = job.getIntValue("ai_type");
            Long uploadDate = job.getLong("upload_timestamp");
            List<String> tags = job.getList("tags", String.class);
            String initUrl = job.getString("url");
            // 定义正则表达式
            Pattern pattern = Pattern.compile("p\\d+");
            // 创建Matcher对象
            Matcher matcher = pattern.matcher(initUrl);
            int page = 0;
            if (matcher.find()){
                String response = matcher.group();
                int pIndex = response.indexOf("p");
                page = Integer.parseInt(response.substring(pIndex + 1, pIndex + 2));
            }
            String originalUrl = "https://i.pixiv.re/img-original/img/"+pixivPicDateChange(uploadDate)+"/"+pid+"_p"+page+".jpg";
            String urlS = "https://i.pixiv.re/c/360x360_10_webp/img-master/img/"+pixivPicDateChange(uploadDate)+"/"+pid+"_p"+page+"_square1200.jpg";
            String urlSm = "https://i.pixiv.re/c/540x540_10_webp/img-master/img/"+pixivPicDateChange(uploadDate)+"/"+pid+"_p"+page+"_square1200.jpg";
            String urlW = "https://i.pixiv.re/c/1080x600_10_webp/img-master/img/"+pixivPicDateChange(uploadDate)+"/"+pid+"_p"+page+"_square1200.jpg";
            JSONObject obj = new JSONObject();
            obj.put("pid",pid);
            obj.put("title",title);
            obj.put("alt",alt);
            obj.put("comment",comment);
            obj.put("width",width);
            obj.put("height",height);
            obj.put("aiType",aiType);
            obj.put("uploadDate",uploadDate);
            obj.put("tags",tags);
            obj.put("urlS",urlS);
            obj.put("urlSm",urlSm);
            obj.put("urlW",urlW);
            obj.put("originalUrl",originalUrl);
            result.add(obj);
        }
        return result;
    }

}
