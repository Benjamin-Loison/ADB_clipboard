package com.benjamin_loison.adb_clipboard.wrappers;

import android.annotation.TargetApi;
import android.content.AttributionSource;
import android.content.ContextWrapper;
import android.os.Build;
import android.os.Process;

public final class FakeContext extends ContextWrapper {

    public static final String PACKAGE_NAME = "com.android.shell";
    public static final int ROOT_UID = 0; // Like android.os.Process.ROOT_UID, but before API 29

    private FakeContext() {
        super(null);
    }
}
