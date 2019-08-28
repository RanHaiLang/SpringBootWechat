package com.rminfo.jinmaocs.service;

import com.rminfo.jinmaocs.entity.R5rmAppUser;

import java.util.List;
import java.util.Map;

public interface R5rmAppUserService {
    List<R5rmAppUser> selectAll(Map<String ,String> map);
    R5rmAppUser getUser(Map<String ,String> map);
}
