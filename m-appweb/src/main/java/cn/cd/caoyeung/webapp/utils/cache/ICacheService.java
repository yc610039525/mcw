package cn.cd.caoyeung.webapp.utils.cache;

import java.util.regex.Pattern;

import net.sf.ehcache.Ehcache;

public abstract interface ICacheService
{
  public abstract void putElement(String paramString, Object paramObject);

  public abstract void removeElement(String paramString);

  public abstract void removeAll();

  public abstract void removeElementByReg(Pattern paramPattern);

  public abstract String assemblyKey(String[] paramArrayOfString);

  public abstract boolean isExistKey(String paramString);

  public abstract Object getValue(String paramString);

  public abstract void setTemstap(long paramLong);

  public abstract long getTemstap();
  
  public abstract Ehcache getCache();
  
  public void fulsh();
}
