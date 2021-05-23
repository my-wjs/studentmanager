package com.studentmanager.utils;

import java.util.HashMap;
import java.util.List;

public class tableModel extends HashMap<String, Object> {
    public static tableModel data(Integer count, List<?> data) {
        tableModel r = new tableModel();
        r.put("code", 0);
        r.put("msg", "");
        r.put("count", count);
        r.put("data", data);
        return r;
    }
}
