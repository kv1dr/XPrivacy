package biz.bokhorst.xprivacy;

import de.robv.android.xposed.XC_MethodHook.MethodHookParam;

public class XWifiInfo extends XHook {

	public XWifiInfo(String methodName, String restrictionName, String[] permissions) {
		super(methodName, restrictionName, permissions);
	}

	// public String getMacAddress()
	// frameworks/base/wifi/java/android/net/wifi/WifiInfo.java

	@Override
	protected void before(MethodHookParam param) throws Throwable {
		if (isRestricted(param))
			param.setResult(XRestriction.cDefacedMac);
	}

	@Override
	protected void after(MethodHookParam param) throws Throwable {
		// Do nothing
	}
}
