package com.rminfo.jinmaocs.service.impl;

import com.rminfo.jinmaocs.mapper.UeditorMapper;
import com.rminfo.jinmaocs.service.UeditorService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by 磊 on 2017/6/1.
 */
@Service
public class UeditorServiceImpl implements UeditorService {
    @Resource
    private UeditorMapper ueditorMapper;

    @Override
    public List<Map> selectPages(Map map) {
        return ueditorMapper.selectPages(map);
    }

    @Override
    public int selectPagesCount() {
        return ueditorMapper.selectPagesCount();
    }

    @Override
    public int selectCodeCount(String str) {
        return ueditorMapper.selectCodeCount(str);
    }

    @Override
    public void deleteUeditor(String str) {
        ueditorMapper.deleteUeditor(str);
    }

    @Override
    public void updateUeditor(Map<String, Object> map) {
        ueditorMapper.updateUeditor(map);
    }

    @Override
    public String selectUeditor(String str) {
        return ueditorMapper.selectUeditor(str);
    }

    @Override
    public void addUeditor(Map<String, Object> map) {
        ueditorMapper.addUeditor(map);
    }

    @Override
    public String selectnotcode() {
        return ueditorMapper.selectnotcode();
    }

    @Override
    public List<Map> selectPageJson(String str) {
        return ueditorMapper.selectPageJson(str);
    }

    @Override
    public List<Map> selectDoc(String str) {
        return ueditorMapper.selectDoc(str);
    }

    @Override
    public void deleteDoc(String str) {
        ueditorMapper.deleteDoc(str);
    }

    @Override
    public void addDoc(Map map) {
        ueditorMapper.addDoc(map);
    }

    @Override
    public String selectDocNewname(String str) {
        return ueditorMapper.selectDocNewname(str);
    }

    @Override
    public void updateUeditorimage(Map<String, Object> map) {
        ueditorMapper.updateUeditorimage(map);
    }

    @Override
    public Map selectimage(String code) {
        return ueditorMapper.selectimage(code);
    }

    @Override
    public String selectimagePath(String code) {
        return ueditorMapper.selectimagePath(code);
    }

    @Override
    public void saveNoticeUpdate(Map<String, Object> map) {
        ueditorMapper.saveNoticeUpdate(map);
    }

    @Override
    public String selectNoticeUpdate(String str) {
        return ueditorMapper.selectNoticeUpdate(str);
    }

    @Override
    public List<Map> selectPagesFive(Map map) {
        return ueditorMapper.selectPagesFive(map);
    }

    @Override
    public List<Map> selectLikePage(String str) {
        return ueditorMapper.selectLikePage(str);
    }

    @Override
    public List<Map> selectClass() {
        return ueditorMapper.selectClass();
    }

    @Override
    public List<Map> selectPageJsonLike(Map<String, Object> map) {
        return ueditorMapper.selectPageJsonLike(map);
    }
}
