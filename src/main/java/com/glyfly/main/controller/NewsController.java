package com.glyfly.main.controller;

import com.glyfly.main.bean.News;
import com.glyfly.main.model.NewsModel;
import com.glyfly.main.model.Response;
import com.glyfly.main.model.Result;
import com.glyfly.tools.ToolString;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.jfinal.core.Controller;

import java.util.List;

/**
 * Created by Administrator on 2017/5/19.
 */
public class NewsController extends Controller{

    public void addNews(){
        String news_list = getPara("news_list");
        List<News> newsList = null;
        if (!ToolString.isEmpty(news_list)){
            try {
                newsList = (List<News>) new Gson().fromJson(news_list, new TypeToken<List<News>>(){}.getType());
            } catch (JsonSyntaxException e) {
                e.printStackTrace();
            }
        }
        if (news_list != null){
            for (int i = 0; i < newsList.size(); i++) {
                String news_pics = newsList.get(i).getNewsUrl();
                String news_from = newsList.get(i).getNewsTime();
                String news_name = newsList.get(i).getNewsTitle();
                String news_id = null;
                if (!ToolString.isEmpty(news_pics)) {
                    news_id = ToolString.getNumFromString(news_pics);
                }
                if (ToolString.isEmpty(news_id)){
                    news_id = (Math.random()*9+1)*1000000 + "";
                }
                String sql = "select * from news where news_id = " + news_id;
                List<NewsModel> news = NewsModel.newsModel.find(sql);
                if (news == null || news.size() == 0) {
                    NewsModel newsModel = new NewsModel();
                    newsModel.set("news_name",news_name);
                    newsModel.set("news_pics",news_pics);
                    newsModel.set("news_from",news_from);
                    newsModel.set("news_id",news_id);
                    newsModel.save();
                }
            }
        }
        Response response = new Response();
        response.setCode(1);
        response.setMsg("success");
        renderJson(response);
    }

    public void getNews(){
        Response response = new Response();
        Result<NewsModel> result = new Result<NewsModel>();
        List<NewsModel> list = null;
        try {
            list = NewsModel.newsModel.find("select * from news");
        } catch (Exception e) {
            e.printStackTrace();
        }
        result.setList(list);
        response.setResult(result);
        renderJson(response);
    }
}
