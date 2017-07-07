package com.glory.bianyitong.util;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningServiceInfo;
import android.app.KeyguardManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Environment;
import android.os.Parcelable;
import android.os.StatFs;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.text.format.Formatter;
import android.util.Log;
import android.util.TypedValue;

import com.glory.bianyitong.exception.MyApplication;
import com.glory.bianyitong.ui.activity.LoginActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ActivityUtils {

	private static long lastClickTime;// 最后点击的时间

	public static String getPermissionData(Context context) {
		StringBuffer stringBuffer = new StringBuffer();
		PackageManager pm = context.getPackageManager();
		PackageInfo pInfo;
		String[] permissions = null;
		try {
			pInfo = pm.getPackageInfo(context.getPackageName(), PackageManager.GET_PERMISSIONS);
			permissions = pInfo.requestedPermissions;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < permissions.length; i++) {
			stringBuffer.append(permissions[i]).append(",");
		}
		return stringBuffer.toString();
	}

	/**
	 * 移动网络是否打开
	 *
	 * @param--context
	 * @return
	 */
	public static boolean isMobileOpen() {
		Context context = MyApplication.getInstance();
		ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		// 如果3G、wifi、2G等网络状态是连接的，则退出，否则显示提示信息进入网络设置界面
		if (getPermissionData(context).contains("android.permission.ACCESS_NETWORK_STATE")) {
			State mobile = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();
			if (mobile == State.CONNECTED || mobile == State.CONNECTING) {
				return true;
			}
		} else {
			return false;
		}
		return false;
	}

	/**
	 * wifi 是否打开
	 *
	 * @param--context
	 * @return
	 */
	public static boolean isOpenWifi() {
		Context context = MyApplication.getInstance();
		ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (getPermissionData(context).contains("android.permission.ACCESS_NETWORK_STATE")) {
			State wifi = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();
			if (wifi == State.CONNECTED || wifi == State.CONNECTING) {
				return true;
			}
		} else {
			return false;
		}
		return false;
	}

	// 判断当前网络是否通
	public static boolean isNetworkAvailable() {
		Context context = MyApplication.getInstance();
		ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity == null) {
			return false;
		} else {
			NetworkInfo[] info = connectivity.getAllNetworkInfo();
			if (info != null) {
				for (int i = 0; i < info.length; i++) {
					if (info[i].getState() == State.CONNECTED) {
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * 获取当前版本号
	 *
	 * @param--context
	 * @return
	 */
	public static String getVersionCode() {
		PackageInfo pinfo;
		String versionCode = "0";
		try {
			Context context = MyApplication.getInstance();
			pinfo = context.getPackageManager().getPackageInfo(context.getPackageName(),PackageManager.GET_CONFIGURATIONS);
			versionCode = String.valueOf(pinfo.versionCode);
		} catch (Exception e) {
		}
		return versionCode;
	}

	/**
	 * 获取版本名称
	 *
	 * @param--context
	 * @return
	 */
	public static String getVersionName() {
		PackageInfo pinfo;
		String versionName = "";
		try {
			Context context = MyApplication.getInstance();
			pinfo = context.getPackageManager().getPackageInfo(context.getPackageName(),PackageManager.GET_CONFIGURATIONS);
			versionName = pinfo.versionName;
		} catch (Exception e) {
		}
		return versionName;
	}

	/**
	 * 得到当前文件存储路径 优先返回SDCARD路径；如无SDCARD则返回手机内存中应用程序数据文件夹路径。
	 *
	 * @param--context
	 * @return
	 */
	public static String getStoragePath() {
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
			return "/mnt/sdcard";
		} else {
			Context context = MyApplication.getInstance();
			return context.getFilesDir().toString();
		}
	}

	/**
	 * 判断当前是否有可用的SDCARD
	 *
	 * @return
	 */
	public static boolean isSDCardEabled() {
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
			return true;
		} else {
			return false;
		}
	}

	public static float getPX(int dipValue) {
		Context context = MyApplication.getInstance();
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dipValue, context.getResources()
				.getDisplayMetrics());
	}

	// public static void finishAcitivity() {
	// if (Database.currentActivity != null) {
	// Database.currentActivity.finish();
	// }
	// }

//	/**
//	 * 获取用户MAC地址
//	 *
//	 * @param--context
//	 * @return
//	 */
//	public static String getNetWorkMac() {
//		Context context = MyApplication.getInstance();
//		WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
////		WifiInfo info = (null == wifi ? null : wifi.getConnectionInfo());
//		String macAddress = "";
////		if (null != info) {
////			macAddress = info.getMacAddress();
////		}
//		return macAddress;
//	}

//	public static boolean checkApkExist(String packageName) {
//		try {
//			if (packageName == null || "".equals(packageName))
//				return false;
//			Context context = MyApplication.getInstance();
////			context.getPackageManager().getApplicationInfo(packageName, PackageManager.GET_UNINSTALLED_PACKAGES);
//			Log.i("Context", "true");
//			return true;
//		} catch (Exception e) {
//			return false;
//		}
//	}

	/**
	 * 判断是否后台运行
	 *
	 * @return
	 */
	public static boolean checkIsBackRunning(Activity activity, String processName) {
		ActivityManager activityManager = (ActivityManager) activity.getSystemService(Activity.ACTIVITY_SERVICE);
		KeyguardManager keyguardManager = (KeyguardManager) activity.getSystemService(Activity.KEYGUARD_SERVICE);
		if (activityManager == null)
			return false;
		List<RunningAppProcessInfo> processList = activityManager.getRunningAppProcesses();
		for (RunningAppProcessInfo process : processList) {
			if (process.processName.startsWith(processName)) {
				boolean isBackground = process.importance != RunningAppProcessInfo.IMPORTANCE_BACKGROUND
						&& process.importance != RunningAppProcessInfo.IMPORTANCE_VISIBLE;
				boolean isLockedState = keyguardManager.inKeyguardRestrictedInputMode();
				if (isBackground || isLockedState)
					return true;
				else
					return false;
			}
		}
		return false;
	}

	//
	// /**
	// * 获取手机卡运营商
	// *
	// * @return
	// */
	// public static int getSimType() {
	// Context context = MyApplication.getInstance();
	// TelephonyManager telManager = (TelephonyManager)
	// context.getSystemService(Context.TELEPHONY_SERVICE);
	// String operator = telManager.getSimOperator();
	// if (operator != null) {
	// if (operator.equals("46000") || operator.equals("46002") ||
	// operator.equals("46007")) {
	// return Constant.SIM_YI; // 中国移动
	// } else if (operator.equals("46001")) {
	// return Constant.SIM_UNICOM; // 中国联通
	// } else if (operator.equals("46003")) {
	// return Constant.SIM_TELE; // 中国电信
	// }
	// }
	// return Constant.SIM_OTHER; // 其他不可识别的sim卡
	// }

	public static boolean simExist() {
		Context context = MyApplication.getInstance();
		TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		int simState = telephonyManager.getSimState();
		switch (simState) {
		case TelephonyManager.SIM_STATE_ABSENT: // "无卡"
			// DialogUtils.mesToastTip("请插入SIM卡");
			return false;
			// case TelephonyManager.SIM_STATE_NETWORK_LOCKED: //
			// "需要NetworkPIN解锁"
			// DialogUtils.mesToastTip("请输入NetworkPIN解锁");
			// return false;
			//
			// case TelephonyManager.SIM_STATE_PIN_REQUIRED: // 需要PIN解锁
			// DialogUtils.mesToastTip("请输入PIN解锁");
			// return false;
			//
			// case TelephonyManager.SIM_STATE_PUK_REQUIRED: // 需要PUN解锁
			// DialogUtils.mesToastTip("请输入PUN解锁");
			// return false;
		case TelephonyManager.SIM_STATE_READY: // 良好
			return true;
			// case TelephonyManager.SIM_STATE_UNKNOWN: // 未知状态
			// DialogUtils.mesToastTip("请输入PUN解锁");
			// return false;
		default:
			// DialogUtils.mesToastTip("SIM卡不能使用");
			return false;
		}
	}

	/**
	 * 获取Application系统配置
	 *
	 * @param--context文本
	 * @return
	 */
	public static String getAppMetaData(String key) {
		try {
			ApplicationInfo ai = null;
			Context ctx = MyApplication.getInstance();
			ai = ctx.getPackageManager().getApplicationInfo(ctx.getPackageName(), PackageManager.GET_META_DATA);
			String value = ai.metaData.getString(key);
			if (!TextUtils.isEmpty(value)) {
				value = value.replaceFirst("store:", "");
			}
			return value;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取安装APK intent
	 *
	 * @param installApk
	 * @return
	 */
	public static Intent getInstallIntent(File installApk) {
		/* 打开文件进行安装 */
		Intent intent = new Intent();
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.setAction(Intent.ACTION_VIEW);
		intent.setDataAndType(Uri.fromFile(installApk), "application/vnd.android.package-archive");
		return intent;
	}

	public static void createFile(File file) throws IOException {
		File parentFile = file.getParentFile();
		if (parentFile == null || !parentFile.exists()) {
			createFile(file);
		} else {
			if (!file.exists())
				file.createNewFile();
		}
	}

	/**
	 * 创建快捷方式
	 *
	 * @Title: createShortCut
	 * @param @param act
	 * @param @param iconResId
	 * @param @param appnameResId
	 * @return void
	 * @throws
	 */
	public static void createShortCut(Parcelable parcelable, int iconResId, int appnameResId) {
		Context ctx = MyApplication.getInstance();
		String shortcutName = ctx.getResources().getString(appnameResId);
		if (hasShortcut(shortcutName)) {
			// deleteShortCut(shortcutName);
			return;
		}
		Intent shortcutintent = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
		// 不允许重复创建
		shortcutintent.putExtra("duplicate", false);
		// 需要现实的名称
		shortcutintent.putExtra(Intent.EXTRA_SHORTCUT_NAME, shortcutName);
		// 快捷图片
		Parcelable icon = Intent.ShortcutIconResource.fromContext(ctx, iconResId);
		// Parcelable icon=parcelable;
		shortcutintent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, icon);
		// Resources res=Database.currentActivity.getResources();
		// Bitmap bmp=BitmapFactory.decodeResource(res, R.drawable.log_5);
		// shortcutintent.putExtra(Intent.EXTRA_SHORTCUT_ICON, bmp);
		// 点击快捷图片，运行的程序主入口
		Intent intent = new Intent(ctx, LoginActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
		intent.addFlags(Intent.FLAG_ACTIVITY_LAUNCHED_FROM_HISTORY);
		intent.addCategory(Intent.CATEGORY_LAUNCHER);
		shortcutintent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, intent);
		// 发送广播
		ctx.sendBroadcast(shortcutintent);
	}

	/**
	 * 删除快捷方式
	 * */
	public static void deleteShortCut(String shortcutName) {
		Context ctx = MyApplication.getInstance();
		Intent shortcut = new Intent("com.android.launcher.action.UNINSTALL_SHORTCUT");
		shortcut.putExtra(Intent.EXTRA_SHORTCUT_NAME, shortcutName);
		/** 改成以下方式能够成功删除，估计是删除和创建需要对应才能找到快捷方式并成功删除 **/
		Intent intent = new Intent();
		intent.setClass(ctx, LoginActivity.class);
		intent.setAction("android.intent.action.MAIN");
		intent.addCategory("android.intent.category.LAUNCHER");
		shortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT, intent);
		ctx.sendBroadcast(shortcut);
	}

	/**
	 * 判断是否存在快捷方式
	 *
	 * @Title: hasShortcut
	 * @param @param shortcutName
	 * @param @return
	 * @return boolean
	 * @throws
	 */
	public static boolean hasShortcut(String shortcutName) {
		Context ctx = MyApplication.getInstance();
		boolean result = false;
		// 获取当前应用名称
		final String uriStr;
		if (android.os.Build.VERSION.SDK_INT < 8) {
			uriStr = "content://com.android.launcher.settings/favorites?notify=true";
		} else {
			uriStr = "content://com.android.launcher2.settings/favorites?notify=true";
		}
		final Uri CONTENT_URI = Uri.parse(uriStr);
		final Cursor c = ctx.getContentResolver().query(CONTENT_URI, null, "title=?", new String[] { shortcutName },null);
		if (c != null && c.getCount() > 0) {
			result = true;
		}
		return result;
	}

	// /**
	// * 获取手机基本信息
	// * @Title: getPhoneInfo
	// * @param @return
	// * @return String
	// * @throws
	// */
	// public static String getPhoneInfo() {
	// Map<String, String> pMap = new HashMap<String, String>();
	// try {
	// TelephonyManager tm = (TelephonyManager)
	// MyApplication.getInstance().getSystemService(Context.TELEPHONY_SERVICE);
	// pMap.put("model", android.os.Build.MODEL); //型号
	// pMap.put("brand", android.os.Build.BRAND); //手机品牌
	// pMap.put("os_sdk", android.os.Build.VERSION.SDK); //
	// pMap.put("os_ver", android.os.Build.VERSION.RELEASE); //版本号
	// pMap.put("deviceid", tm.getDeviceId()); //唯一的设备ID
	// pMap.put("softver", tm.getDeviceSoftwareVersion()); //设备的软件版本号：
	// pMap.put("simName", tm.getSimOperatorName());
	// pMap.put("simNum", tm.getSimSerialNumber());
	// pMap.put("imie", tm.getSubscriberId());
	// pMap.put("androidId", getAndroidId());
	// pMap.put("phonetype", tm.getPhoneType() + "");
	// pMap.put("mac", getNetWorkMac());
	// } catch (Exception e) {}
	// return JsonHelper.toJson(pMap);
	// }

	// /**
	// * 获取当前的网络信息
	// * @Title: getNetWorkInfo
	// * @param @return
	// * @return String
	// * @throws
	// */
	// public static String getNetWorkInfo() {
	// Map<String, String> pMap = new HashMap<String, String>();
	// try {
	// TelephonyManager tm = (TelephonyManager)
	// MyApplication.getInstance().getSystemService(Context.TELEPHONY_SERVICE);
	// pMap.put("model", android.os.Build.MODEL); //型号
	// pMap.put("softver", tm.getDeviceSoftwareVersion()); //设备的软件版本号：
	// pMap.put("phoneType", tm.getPhoneType() + "");
	// if (isWifiActive()) {
	// pMap.put("network", "wifi"); //网络类型
	// } else {
	// //2 联通2G
	// int nType = tm.getNetworkType();
	// String nName = nType + "";
	// switch (nType) {
	// case TelephonyManager.NETWORK_TYPE_GPRS:
	// case TelephonyManager.NETWORK_TYPE_EDGE:
	// nName = "移动|联通2G";
	// break;
	// case TelephonyManager.NETWORK_TYPE_CDMA:
	// nName = "电信2G";
	// break;
	// case TelephonyManager.NETWORK_TYPE_UMTS:
	// case TelephonyManager.NETWORK_TYPE_HSDPA:
	// nName = "联通3G";
	// break;
	// case TelephonyManager.NETWORK_TYPE_EVDO_0:
	// case TelephonyManager.NETWORK_TYPE_EVDO_A:
	// nName = "电信3G";
	// break;
	// default:
	// break;
	// }
	// pMap.put("network", nName); //网络类型
	// }
	// pMap.put("simName", tm.getSimOperatorName());
	// } catch (Exception e) {}
	// return JsonHelper.toJson(pMap);
	// }

	/**
	 * 判断service 是否运行
	 *
	 * @throws
	 */
	public static boolean checkServiceIsWork(Class<?> clazz) {
		Context ctx = MyApplication.getInstance().getApplicationContext();
		ActivityManager myManager = (ActivityManager) ctx.getSystemService(Context.ACTIVITY_SERVICE);
		ArrayList<RunningServiceInfo> runningService = (ArrayList<RunningServiceInfo>) myManager.getRunningServices(30);
		for (int i = 0; i < runningService.size(); i++) {
			if (runningService.get(i).service.getClassName().toString().equals(clazz.getName())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 获取网络类型
	 *
	 * @return (0 无网,1=wifi,2=2g,3=3g)
	 */
	public static int getNetWorkType() {
		try {
			TelephonyManager tm = (TelephonyManager) MyApplication.getInstance().getSystemService(Context.TELEPHONY_SERVICE);
			if (isWifiActive()) {
				return 1;
			} else {
				// 2 联通2G
				int nType = tm.getNetworkType();
				switch (nType) {
				case TelephonyManager.NETWORK_TYPE_GPRS:
				case TelephonyManager.NETWORK_TYPE_EDGE:
				case TelephonyManager.NETWORK_TYPE_CDMA:
					return 2;
				case TelephonyManager.NETWORK_TYPE_UMTS:
				case TelephonyManager.NETWORK_TYPE_HSDPA:
				case TelephonyManager.NETWORK_TYPE_EVDO_0:
				case TelephonyManager.NETWORK_TYPE_EVDO_A:
					return 3;
				}
			}
		} catch (Exception e) {
		}
		return 0;
	}

	/**
	 * 是否是wifi
	 *
	 * @Title: isWifiActive
	 * @param @param icontext
	 * @param @return
	 * @return boolean
	 * @throws
	 */
	public static boolean isWifiActive() {
		Context ctx = MyApplication.getInstance().getApplicationContext();
		ConnectivityManager connectivity = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo[] info;
		if (connectivity != null) {
			info = connectivity.getAllNetworkInfo();
			if (info != null) {
				for (int i = 0; i < info.length; i++) {
					if (info[i].getTypeName().equals("WIFI") && info[i].isConnected()) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public static boolean isFastDoubleClick() {
		long time = System.currentTimeMillis();
		long timeD = time - lastClickTime;
		if (0 < timeD && timeD < 5000) {
			return true;
		}
		lastClickTime = time;
		return false;
	}

	/**
	 * 是否为双击
	 * 
	 * @param delay
	 * @return
	 */
	public static boolean isFastDoubleClick(float delay) {
		long time = System.currentTimeMillis();
		long timeD = time - lastClickTime;
		if (0 < timeD && timeD < delay) {
			return true;
		}
		lastClickTime = time;
		return false;
	}

	/**
	 * 获取手机唯一标识
	 * 
	 * @throws
	 */
	public static String getAndroidId() {
		Context context = MyApplication.getInstance();
		String androidId = Secure.getString(context.getContentResolver(), Secure.ANDROID_ID);
		if (TextUtils.isEmpty(androidId)) {
			TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
			androidId = tm.getDeviceId();
			if (TextUtils.isEmpty(androidId)) {
				androidId = getSharedValue("local_phone_tag"); // 本地存储的手机TAG值
				if (TextUtils.isEmpty(androidId)) {
					androidId = ComUtils.randomStr(10); // 重新生成新的
					addSharedValue("local_phone_tag", androidId);
				}
			}
		}
		return androidId;
	}

	public static String getHostIp(String host) {
		try {
			java.security.Security.setProperty("networkaddress.cache.ttl", "15");
			// 修改缓存数据结束
			InetAddress address = InetAddress.getByName(host);
			if (address != null) {
				String ip = address.getHostAddress();
				if (!TextUtils.isEmpty(ip)) {
					return ip;
				}
			}
		} catch (Exception e) {
		}
		return host;
	}

	/**
	 * SharedPreferences 增加修改
	 */
	public static void addSharedValue(String key, String value) {
		SharedPreferences preferences = MyApplication.getInstance().getSharedPreferences(key, Context.MODE_PRIVATE);
		Editor editor = preferences.edit();
		editor.putString(key, value);
		editor.commit();
	}

	/**
	 * 获取 SharedPreferences值
	 * 
	 * @return
	 */
	public static String getSharedValue(String key) {
		SharedPreferences preferences = MyApplication.getInstance().getSharedPreferences(key, Context.MODE_PRIVATE);
		return preferences.getString(key, null);
	}

	/**
	 * 获取android当前可用内存大小
	 * 
	 * @return
	 */
	public static String getAvailMemory() {
		ActivityManager am = (ActivityManager) MyApplication.getInstance().getSystemService(Context.ACTIVITY_SERVICE);
		MemoryInfo mi = new MemoryInfo();
		am.getMemoryInfo(mi);
		// mi.availMem; 当前系统的可用内存
		return Formatter.formatFileSize(MyApplication.getInstance().getBaseContext(), mi.availMem);// 将获取的内存大小规格化
	}

	/**
	 * 获取总的内存大小
	 * 
	 * @return
	 */
	public static String getTotalMemory() {
		String str1 = "/proc/meminfo";// 系统内存信息文件
		String str2;
		String[] arrayOfString;
		long initial_memory = 0;
		try {
			FileReader localFileReader = new FileReader(str1);
			BufferedReader localBufferedReader = new BufferedReader(localFileReader, 8192);
			str2 = localBufferedReader.readLine();// 读取meminfo第一行，系统总内存大小
			arrayOfString = str2.split("\\s+");
			for (String num : arrayOfString) {
				Log.i(str2, num + "\t");
			}
			initial_memory = Integer.valueOf(arrayOfString[1]).intValue() * 1024;// 获得系统总内存，单位是KB，乘以1024转换为Byte
			localBufferedReader.close();
		} catch (IOException e) {
		}
		return Formatter.formatFileSize(MyApplication.getInstance().getBaseContext(), initial_memory);// Byte转换为KB或者MB，内存大小规格化
	}

	/**
	 * 获取应用占用内存
	 */
	public static String getProUseMemory() {
		Context ctx = MyApplication.getInstance();
		Runtime myRun = Runtime.getRuntime();
		String totalMemory = "\n已用内存：" + Formatter.formatFileSize(ctx, myRun.totalMemory());
		String maxMemory = "，最大内存：" + Formatter.formatFileSize(ctx, myRun.maxMemory());
		String freeMemory = "，可用内存：" + Formatter.formatFileSize(ctx, myRun.freeMemory());
		return totalMemory + maxMemory + freeMemory;
	}

	// /**
	// * 获取手机安装应用信息（系统预装应用数/用户安装应用数）
	// * @return
	// */
	// public static String getAPPInfo() {
	// Map<String, String> appMap = new HashMap<String, String>();
	// int xtAppNum = 0;
	// int fxtAppNum = 0;
	// PackageManager pm = MyApplication.getInstance().getPackageManager();
	// List<PackageInfo> packages = pm.getInstalledPackages(0);
	// for (int i = 0; i < packages.size(); i++) {
	// PackageInfo packageInfo = packages.get(i);
	// appMap.put("appName",
	// packageInfo.applicationInfo.loadLabel(pm).toString());
	// appMap.put("packageName", packageInfo.packageName);
	// appMap.put("versionName", packageInfo.versionName);
	// //Only display the non-system app info
	// if ((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) ==
	// 0) {//非系统应用
	// fxtAppNum++;
	// } else {//系统应用
	// xtAppNum++;
	// }
	// }
	// appMap.put("SystemAppNum", "" + xtAppNum);
	// appMap.put("NonSystemAppNum", "" + fxtAppNum);
	// return JsonHelper.toJson(appMap);
	// }

	/**
	 * 获取Rom剩余量
	 */
	public static long getRomMemroy() {
		// Available rom memory
		File path = Environment.getDataDirectory();
		StatFs stat = new StatFs(path.getPath());
		long blockSize = stat.getBlockSize();
		long availableBlocks = stat.getAvailableBlocks();
		return blockSize * availableBlocks / 1000000;
	}

	/**
	 * 获取Rom总量
	 */
	public static long getTotalInternalMemorySize() {
		File path = Environment.getDataDirectory();
		StatFs stat = new StatFs(path.getPath());
		long blockSize = stat.getBlockSize();
		long totalBlocks = stat.getBlockCount();
		return totalBlocks * blockSize;
	}

	// /**Ping网络信息获取
	// * @param ip ip或域名
	// * @param times ping的次数
	// * @return
	// */
	// public static ReturnPing getPing(String ip, int times) {
	// ReturnPing returnPing = new ReturnPing();
	// String resault = "";
	// try {
	// String ping = "ping";
	// if (0 != times) {
	// ping += " -c " + times + " ";
	// } else {
	// ping += " -c 3 ";
	// }
	// Process p = Runtime.getRuntime().exec(ping + ip);
	// int status = p.waitFor();
	// InputStream input = p.getInputStream();
	// BufferedReader in = new BufferedReader(new InputStreamReader(input));
	// StringBuffer buffer = new StringBuffer();
	// String line = "";
	// while ((line = in.readLine()) != null) {
	// buffer.append(line);
	// }
	// System.out.println("Return ============" + buffer.toString());
	// Log.i("ping", buffer.toString());
	// resault += buffer.toString();
	// if (status == 0) {
	// Map<String, String> map = new HashMap<String, String>();
	// String s = resault.substring(resault.indexOf("min/avg/max/mdev"));
	// String s1 = s.substring(s.indexOf("= "), s.lastIndexOf(" ms"));
	// String s2 = s1.replace("= ", "");
	// Log.i("ping", s2);
	// String s3[] = s2.split("/");
	// returnPing.setPingMinTime((int) (Float.parseFloat(s3[0].trim()) + 0.5));
	// returnPing.setPingAvgTime((int) (Float.parseFloat(s3[1].trim()) + 0.5));
	// returnPing.setPingMaxTime((int) (Float.parseFloat(s3[2].trim()) + 0.5));
	// returnPing.setPingStatus(1);
	// resault = JsonHelper.toJson(map);
	// } else {
	// resault = "faild";
	// returnPing.setPingStatus(0);
	// }
	// } catch (Exception e) {
	// returnPing.setPingStatus(0);
	// }
	// return returnPing;
	// }

	/**
	 * 获取时间
	 * 
	 * @return
	 */
	public static String getTimeShort() {
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
		Date currentTime = new Date();
		String dateString = formatter.format(currentTime);
		return dateString;
	}

	/**
	 * 获取倒计时 1天 =86400 秒 1小时 =3600 秒 1分钟 =60 秒
	 * 
	 * @param time
	 *            秒
	 * @return
	 */
	public static String getCountDown(long time) {
		long day = 0;
		long hour = 0;
		long minute = 0;
		long times = time;
		String result = "倒计时：";
		if (86400 <= times) {
			day = times / 86400;
			times = times % 86400;
			result += day + "天";
		}
		if (3600 <= times) {
			hour = times / 3600;
			times = times % 3600;
			result += hour + "时";
		}
		if (60 <= times) {
			minute = times / 60;
			times = times % 60;
		}
		if (times > 0) {
			minute += 1;
		}
		if (0 != minute) {
			result += minute + "分";
		}
		return result;
	}

	/**
	 * 打开其他应用
	 * */
	public static void openApp(String packageName) {
		try {
			Intent startIntent = MyApplication.getInstance().getPackageManager().getLaunchIntentForPackage(packageName);
			startIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			MyApplication.getInstance().startActivity(startIntent);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	// /**
	// * 参数说明
	// * destinationAddress:收信人的手机号码
	// * scAddress:发信人的手机号码
	// * text:发送信息的内容
	// * sentIntent:发送是否成功的回执，用于监听短信是否发送成功。
	// * DeliveryIntent:接收是否成功的回执，用于监听短信对方是否接收成功。
	// */
	// public static void sendSMS(String phoneNumber, String message) {
	// // ---sends an SMS message to another device---
	// SmsManager sms = SmsManager.getDefault();
	// // create the sentIntent parameter
	// Intent sentIntent = new Intent("SENT_SMS_ACTION");
	// PendingIntent sentPI =
	// PendingIntent.getBroadcast(MyApplication.getInstance(), 0, sentIntent,
	// 0);
	// // create the deilverIntent parameter
	// Intent deliverIntent = new Intent("DELIVERED_SMS_ACTION");
	// PendingIntent deliverPI =
	// PendingIntent.getBroadcast(MyApplication.getInstance(), 0, deliverIntent,
	// 0);
	// //如果短信内容超过70个字符 将这条短信拆成多条短信发送出去
	// if (message.length() > 70) {
	// ArrayList<String> msgs = sms.divideMessage(message);
	// for (String msg : msgs) {
	// sms.sendTextMessage(phoneNumber, null, msg, sentPI, deliverPI);
	// }
	// } else {
	// sms.sendTextMessage(phoneNumber, null, message, sentPI, deliverPI);
	// }
	// ContentValues values = new ContentValues();
	// //发送时间
	// values.put("date", System.currentTimeMillis());
	// //阅读状态
	// values.put("read", 0);
	// //1为收 2为发
	// values.put("type", 1);
	// //送达号码
	// values.put("address", phoneNumber);
	// //送达内容
	// values.put("body", message);
	// //插入短信库
	// Database.currentActivity.getContentResolver().insert(Uri.parse("content://sms/sent"),
	// values);
	// // SMSNotifications.showNotification(this, "短信", "您已欠费，请及时充值");
	// }

	// /**
	// * 获取非系统应用所有信息
	// * */
	// public static ArrayList<CommandUploadDesktopAppInfo> getAllAppInfo() {
	// ArrayList<CommandUploadDesktopAppInfo> appList = new
	// ArrayList<CommandUploadDesktopAppInfo>(); //用来存储获取的应用信息数据
	// List<PackageInfo> packages =
	// MyApplication.getInstance().getPackageManager().getInstalledPackages(0);
	// for (int i = 0; i < packages.size(); i++) {
	// PackageInfo packageInfo = packages.get(i);
	// CommandUploadDesktopAppInfo tmpInfo = new CommandUploadDesktopAppInfo();
	// tmpInfo.setAppName(packageInfo.applicationInfo.loadLabel(MyApplication.getInstance().getPackageManager()).toString());
	// tmpInfo.setPackageName(packageInfo.packageName);
	// tmpInfo.setVersionName(packageInfo.versionName);
	// tmpInfo.setVersionCode(packageInfo.versionCode);
	// tmpInfo.setAppIcon(packageInfo.applicationInfo.loadIcon(MyApplication.getInstance().getPackageManager()));
	// // tmpInfo.appIcon =
	// packageInfo.applicationInfo.loadIcon(getPackageManager());
	// //Only display the non-system app info
	// if ((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) ==
	// 0) {
	// appList.add(tmpInfo);//如果非系统应用，则添加至appList
	// }
	// }
	// return appList;
	// }

	// /**
	// * 获取当前运行非系统应用所有信息
	// * */
	// public static CommandUploadRunAppInfo getRunAppInfo() {
	// ActivityManager manager = (ActivityManager)
	// MyApplication.getInstance().getSystemService(Context.ACTIVITY_SERVICE);
	// RunningTaskInfo info = manager.getRunningTasks(1).get(0);
	// CommandUploadRunAppInfo tmpInfo = new CommandUploadRunAppInfo();
	// tmpInfo.setClassName(info.topActivity.getClassName());//完整类名
	// tmpInfo.setShortClassName(info.topActivity.getShortClassName());//类名
	// tmpInfo.setPackageName(info.topActivity.getPackageName());//包名
	// ArrayList<CommandUploadDesktopAppInfo> DesktopAppInfo = getAllAppInfo();
	// for (int i = 0; i < DesktopAppInfo.size(); i++) {
	// if
	// (DesktopAppInfo.get(i).getPackageName().equals(info.topActivity.getPackageName()))
	// {
	// tmpInfo.setAppName(DesktopAppInfo.get(i).getAppName());
	// }
	// }
	// return tmpInfo;
	// }

	/**
	 * 判断当前应用是否在前台运行
	 * 
	 * @param---context
	 * @return
	 */
	public static boolean isRunningForeground() {
		Context context = MyApplication.getInstance();
		ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		ComponentName cn = am.getRunningTasks(1).get(0).topActivity;
		String currentPackageName = cn.getPackageName();
		if (!TextUtils.isEmpty(currentPackageName) && currentPackageName.equals(context.getPackageName())) {
			return true;
		}
		return false;
	}

	public static String getNowDate() {
		Calendar cal = Calendar.getInstance();// 使用日历类
		int year = cal.get(Calendar.YEAR);// 得到年
		int month = cal.get(Calendar.MONTH) + 1;// 得到月，因为从0开始的，所以要加1
		int day = cal.get(Calendar.DAY_OF_MONTH);// 得到天
		String nowDate = year + month + day + "";
		return nowDate;
	}

	/**
	 * 隐藏应用图标
	 */
	public static void hidden(Context context) {
		PackageManager p = context.getPackageManager();
		final ComponentName componentName = new ComponentName(context.getPackageName(),
				"com.mx.store.lord.ui.activity.CWelcomeActivity");// 程序包名和第一个Activity的全名
		p.setComponentEnabledSetting(componentName, PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
				PackageManager.DONT_KILL_APP);
	}

	public static String getLocalIpAddress() {
		try {
			for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
				NetworkInterface intf = en.nextElement();
				for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
					InetAddress inetAddress = enumIpAddr.nextElement();
					if (!inetAddress.isLoopbackAddress()) {
						return inetAddress.getHostAddress().toString();
					}
				}
			}
		} catch (SocketException ex) {
			Log.e("getLocalIpAddress", ex.toString());
		}
		return "";
	}

	/**
	 * Get IP address from first non-localhost interface
	 * 
	 * @param--ipv4
	 *            true=return ipv4, false=return ipv6
	 * @return address or empty string
	 */
//	public static String getIPAddress(boolean useIPv4) {
//		try {
//			List<NetworkInterface> interfaces = Collections.list(NetworkInterface.getNetworkInterfaces());
//			for (NetworkInterface intf : interfaces) {
//				List<InetAddress> addrs = Collections.list(intf.getInetAddresses());
//				for (InetAddress addr : addrs) {
//					if (!addr.isLoopbackAddress()) {
//						String sAddr = addr.getHostAddress().toUpperCase();
//						boolean isIPv4 = InetAddressUtils.isIPv4Address(sAddr);
//						if (useIPv4) {
//							if (isIPv4)
//								return sAddr;
//						} else {
//							if (!isIPv4) {
//								int delim = sAddr.indexOf('%'); // drop ip6 port
//																// suffix
//								return delim < 0 ? sAddr : sAddr.substring(0, delim);
//							}
//						}
//					}
//				}
//			}
//		} catch (Exception ex) {
//		} // for now eat exceptions
//		return "";
//	}

	public static boolean isEmail(String strEmail) {
		String strPattern = "^[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]@[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]\\.[a-zA-Z][a-zA-Z\\.]*[a-zA-Z]$";

		Pattern p = Pattern.compile(strPattern);
		Matcher m = p.matcher(strEmail);
		return m.matches();
	}

	//判断有无空格（-1不存在）
	public static boolean hasKongge(String s) {
		int i = s.indexOf(" ");
		if (i == -1) {
			return false;
		}
		return true;
	}

	/**
	 * 验证手机格式
	 */
	public static boolean isMobileNO(String mobiles) {
    /*
    移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
    联通：130、131、132、152、155、156、185、186
    电信：133、153、180、189、（1349卫通）
    总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
    */
		String telRegex = "[1][358]\\d{9}";//"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
		if (TextUtils.isEmpty(mobiles)) return false;
		else return mobiles.matches(telRegex);
	}

}
