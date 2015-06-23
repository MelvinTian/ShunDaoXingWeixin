package com.sdx.utils;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * TODO What the class does
 * @author zz
 * @date 2013-8-6-上午9:53:41
 */
@Transactional
@Component("reloadDictData")
public class ReloadDictData
{
	@Scheduled(cron = "0 0 0 * * ?")
	public void reloadDictData()
	{
		PropertiesCacheController.initBasicData();
	}
}
