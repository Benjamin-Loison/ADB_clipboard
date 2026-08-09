package com.benjamin_loison.adb_clipboard.wrappers;

import android.annotation.SuppressLint;
import android.os.IBinder;
import android.os.IInterface;

import java.lang.reflect.Method;

@SuppressLint("PrivateApi,DiscouragedPrivateApi")
public final class ServiceManager {

    private static final Method GET_SERVICE_METHOD;
    static {
        try {
            GET_SERVICE_METHOD = Class.forName("android.os.ServiceManager").getDeclaredMethod("getService", String.class);
        } catch (Exception e) {
            throw new AssertionError(e);
        }
    }

    private static ClipboardManager clipboardManager;

    private ServiceManager() {
        /* not instantiable */
    }

    private static IInterface getService(String service, String type) {
        try {
            IBinder binder = (IBinder) GET_SERVICE_METHOD.invoke(null, service);
            Method asInterfaceMethod = Class.forName(type + "$Stub").getMethod("asInterface", IBinder.class);
            return (IInterface) asInterfaceMethod.invoke(null, binder);
        } catch (Exception e) {
            throw new AssertionError(e);
        }
    }

    public static ClipboardManager getClipboardManager() {
        if (clipboardManager == null) {
            IInterface clipboard = getService("clipboard", "android.content.IClipboard");
            if (clipboard == null) {
                // Some devices have no clipboard manager
                // <https://github.com/Genymobile/scrcpy/issues/1440>
                // <https://github.com/Genymobile/scrcpy/issues/1556>
                return null;
            }
            clipboardManager = new ClipboardManager(clipboard);
        }
        return clipboardManager;
    }
}
