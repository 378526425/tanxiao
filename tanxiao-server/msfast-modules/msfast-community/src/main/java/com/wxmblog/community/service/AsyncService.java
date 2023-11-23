package com.wxmblog.community.service;

public interface AsyncService {

    void sendMatchMessage(Integer otherUserId, Integer selfUserId);
}
