package com.rminfo.jinmaocs.service.impl;

import com.github.pagehelper.PageHelper;
import com.rminfo.jinmaocs.entity.R5rmAppUser;
import com.rminfo.jinmaocs.mapper.R5rmAppUserMapper;
import com.rminfo.jinmaocs.service.R5rmAppUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("rminterFaceTableRepository")
public class R5rmAppUserServiceImpl implements R5rmAppUserService {

    @Resource
    private R5rmAppUserMapper r5rmAppUserMapper;

    @Override
    public List<R5rmAppUser> selectAll(Map<String ,String> map) {
        //分页插件，执行后，第一个查询语句，会自动分页
        PageHelper.startPage(Integer.parseInt(map.get("page")), Integer.parseInt(map.get("rows")));
        return r5rmAppUserMapper.selectAll();
    }

    @Override
    public R5rmAppUser getUser(Map<String, String> map) {
        return r5rmAppUserMapper.getUser(map);
    }
}
