package com.sdx.utils;

import java.util.Map;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sdx.bms.client.BMSClient;
import com.sdx.utils.web.SdxHttpClient;

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
		Map<String, String> basicData = PropertiesCacheController.getBasicData();
		// 以下是需要重新加载的属性部分
		BMSClient.configClient(basicData);
		SdxHttpClient.configHttpClient(basicData);
	}
}
