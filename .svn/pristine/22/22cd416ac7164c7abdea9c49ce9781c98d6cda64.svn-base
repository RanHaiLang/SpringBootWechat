package com.rminfo.jinmaocs.mapper;

import com.rminfo.jinmaocs.entity.R5rmAppUser;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface R5rmAppUserMapper {
    /**
     * 根据code，查询是否存在该用户
     * @param code
     * @return
     */
    @Select("select count(1) from r5rmappusers where 1=1 " +
            "and usercode = #{code} ")
    Integer countUsercode(String code);

    /**
     * 获取用户列表
     * @return
     */
    List<R5rmAppUser> selectAll();

    /**
     * 根据账号密码查询用户
     * @param map
     * @return
     */
    R5rmAppUser getUser(Map<String ,String> map);
}
