package dataDAO.register;

import commonUtil.StringUtil;

import java.util.prefs.Preferences;

/**
 * Created by Administrator on 2016/4/7.
 */
public class RegisterOperator {
    private static final String ROOTNAME="/netmonitor";
    private Preferences pre;

    public RegisterOperator()
    {
        pre= Preferences.systemRoot().node(ROOTNAME);
    }

    public void writeValue(String key,String value)
    {
        if(StringUtil.isNullOrEmpty(key)||StringUtil.isNullOrEmpty(value))
            throw new IllegalArgumentException("参数不能为空");
        pre.put(key,value);
    }

    public String readValue(String key)
    {
        return pre.get(key,null);
    }

    public void deleteValue(String key)
    {
        if(StringUtil.isNullOrEmpty(key))
            return;
        pre.remove(key);
    }
}
