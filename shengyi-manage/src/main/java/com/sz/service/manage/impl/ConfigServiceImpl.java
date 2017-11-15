package com.sz.service.manage.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sz.dao.manage.ConfigDao;
import com.sz.pojo.manage.config.Config;
import com.sz.service.manage.ConfigService;

/**
 * 配置 Service
 * @author jiangfan.zhou
 * date Jun 4, 2014
 */
@Service
public class ConfigServiceImpl implements ConfigService {
    
    final Logger logger = LoggerFactory.getLogger(getClass());
    
    // 缓存配置
    private static final Map<String, String> cache = new HashMap<String, String>();
    
	@Autowired
	private ConfigDao configDao;
	
	@Override
	public void reload(){
	    try {
            List<Config> list = configDao.queryAllConfig();
            Map<String, String> map = new HashMap<String, String>();
            if(list != null) {
                for(Config entity : list) {
					String mapKey = entity.getKey();
					map.put(mapKey , entity.getValue());
				}
            }
            cache.clear();
            cache.putAll(map);
            logger.info("加载配置缓存成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	@Override
	public String getCacheConfigByKey(String key){
		if (StringUtils.isBlank(key)) {
			return null;
		}
	    return cache.get(key);
	}
	
    @Override
    public List<Config> queryAllConfig() throws Exception {
        return configDao.queryAllConfig();
    }

    @Override
    public boolean addConfig(Config entity) throws Exception {
        configDao.addConfig(entity);
        reload();
        return true;
    }

    @Override
    public Config queryConfigById(int id) throws Exception {
        return configDao.queryConfigById(id);
    }

    @Override
    public Config queryConfigByKey(String key) throws Exception {
        return configDao.queryConfigByKey(key);
    }
    
    @Override
    public List<Config> queryConfigByKeyFuzzy(String key) throws Exception {
    	return configDao.queryByKeyFuzzy(key);
    }

    @Override
    public boolean updateConfig(Config entity) throws Exception {
        configDao.updateConfig(entity);
        reload();
        return true;
    }

    @Override
    public boolean delConfig(int id) throws Exception {
        configDao.delConfig(id);
        reload();
        return true;
    }



}
