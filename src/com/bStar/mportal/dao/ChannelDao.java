package com.bStar.mportal.dao;

import java.util.List;

import com.bStar.mportal.pojo.Channel;

public interface ChannelDao {
	List<Channel>findAll()throws Exception;
	List<Channel>findByCategoryId(int id)throws Exception;
	void save(Channel channel)throws Exception;
}