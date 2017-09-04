package com.glyfly.main.model;

import java.util.List;

/**
 * Created by Administrator on 2017/4/11.
 */
public class Result<T> {
    private List<T> list;

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
