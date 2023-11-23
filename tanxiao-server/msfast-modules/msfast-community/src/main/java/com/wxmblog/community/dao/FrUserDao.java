package com.wxmblog.community.dao;

import com.wxmblog.community.common.rest.response.user.DynamicUserResponse;
import com.wxmblog.community.common.rest.response.user.FollowPageResponse;
import com.wxmblog.community.entity.FrUserEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author wanglei
 * @email 378526425@qq.com
 * @date 2022-09-22 15:46:53
 */
@Mapper
public interface FrUserDao extends BaseMapper<FrUserEntity> {

    List<DynamicUserResponse> getDynamicUser(Map<String, Object> param);

    List<FollowPageResponse> getFollowPage(Integer userId);

    List<FollowPageResponse> getFansPage(Integer userId);
}
