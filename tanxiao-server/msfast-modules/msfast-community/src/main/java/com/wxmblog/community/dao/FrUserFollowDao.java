package com.wxmblog.community.dao;

import com.wxmblog.community.entity.FrUserFollowEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 前台用户关注关系
 *
 * @author wanglei
 * @email 378526425@qq.com
 * @date 2022-11-12 15:22:41
 */
@Mapper
public interface FrUserFollowDao extends BaseMapper<FrUserFollowEntity> {

}
