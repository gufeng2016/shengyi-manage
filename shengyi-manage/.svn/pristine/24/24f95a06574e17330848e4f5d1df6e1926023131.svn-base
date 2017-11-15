package com.sz.common.security;


import com.sz.common.util.DESUtil;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import java.util.Properties;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: vic
 * Date: 13-5-26
 * Time: 下午5:11
 * 解密数据库配置文件
 */
public class PropertyDecipher extends PropertyPlaceholderConfigurer {
    //key过滤数组
    String[] filter = new String[]{"jdbc.username","jdbc.password"};
    //DES解密密钥
    private static final String keyStr = "sz7road.deci.u21msax8asdj";
    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactory, Properties props)
            throws BeansException {
        DESUtil.setKey(keyStr);
        Set<Object> keySet = props.keySet();
        for(Object str: keySet){
            String keyStr = str.toString();
            for(int i = 0; i < filter.length ; i++ ){
                if(keyStr.indexOf(filter[i]) != -1) {
                    //解密处理
                    String desStr = DESUtil.getDesString(props.getProperty(keyStr));
                    props.setProperty(keyStr, desStr);
                }
            }
        }
        //解析非加密properties
        super.processProperties(beanFactory, props);
    }
}
