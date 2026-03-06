package tv.huan.demo.utils;

import android.graphics.Bitmap;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import eskit.sdk.support.ISoManager;
import eskit.sdk.support.args.EsMap;
import eskit.sdk.support.core.EsProxy;

/**
 * <br>
 *
 * <br>
 */
public class SdkCompat {

    private static int mSupportViewActorFlag = -10;

    public static boolean isSupportViewActor() {
        if (mSupportViewActorFlag == -1) {
            try {
                Class.forName("com.tencent.extend.views.TVViewActorHost");
                mSupportViewActorFlag = 1;
            } catch (ClassNotFoundException e) {
                mSupportViewActorFlag = 0;
            }
        }
        System.out.println("isSupportViewActor");
        return mSupportViewActorFlag != 0;
    }

    //region 加载so

    public void loadSo(String soTagName, ISoManager.Callback callback){
        ISoManager manager = EsProxy.get().getSoManager();
        manager.prepareSoFiles(soTagName, false, callback);
    }

    //endregion

    //region 图片加载

    private static Method mLoadImageMethod;
    private static Class<?> mRealCallbackClass;

    private static void initLoadImageMethod() {
        if (mLoadImageMethod == null) {
            try {
                for (Method method : EsProxy.class.getMethods()) {
                    if (!method.getName().equals("loadImageBitmap")) continue;
                    Class<?>[] parameterTypes = method.getParameterTypes();
                    if (parameterTypes.length != 2) continue;
                    mLoadImageMethod = method;
                    mRealCallbackClass = parameterTypes[1];
                    break;
                }
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    }

    public static void loadImageBitmap(EsMap params, ImageLoadCallback callback) {
        initLoadImageMethod();
        if (mLoadImageMethod == null) return;
        if (mRealCallbackClass == null) return;
        Object callBackProxy = Proxy.newProxyInstance(
                mRealCallbackClass.getClassLoader(),
                new Class[]{mRealCallbackClass},
                (proxy, method, args) -> {
                    if (method.getName().equals("onSuccess")) {
                        callback.onSuccess((Bitmap) args[0]);
                    } else {
                        callback.onFailed((Throwable) args[0]);
                    }
                    return null;
                }
        );
        try {
            mLoadImageMethod.invoke(EsProxy.get(), params, callBackProxy);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public interface ImageLoadCallback {
        void onSuccess(Bitmap bitmap);

        void onFailed(Throwable throwable);
    }

    //endregion
}
