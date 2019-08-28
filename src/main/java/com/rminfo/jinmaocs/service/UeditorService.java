package com.rminfo.jinmaocs.service;

import java.util.List;
import java.util.Map;

/**
 * Created by 磊 on 2017/6/1.
 */
public interface UeditorService {
    List<Map> selectPages(Map map);
    int selectPagesCount();
    int selectCodeCount(String str);
    void deleteUeditor(String str);
    void updateUeditor(Map<String, Object> map);
    String selectUeditor(String str);
    void addUeditor(Map<String, Object> map);
    String selectnotcode();
    List<Map> selectPageJson(String str);
    List<Map> selectDoc(String str);
    void deleteDoc(String str);
    void addDoc(Map map);
    String selectDocNewname(String str);
    void updateUeditorimage(Map<String, Object> map);
    Map selectimage(String code);
    String selectimagePath(String code);
    void saveNoticeUpdate(Map<String, Object> map);
    String selectNoticeUpdate(String str);
    List<Map> selectPagesFive(Map map);
    List<Map> selectLikePage(String str);
    List<Map> selectClass();
    List<Map> selectPageJsonLike(Map<String, Object> map);
}
