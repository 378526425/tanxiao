package com.wxmblog.community.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wxmblog.community.entity.FrUserEntity;

public interface TestService extends IService<FrUserEntity> {

    void deleteFruser(Integer id);
}
