package com.benjamin_loison.adb_clipboard;

import android.os.Process;
import android.system.ErrnoException;
import android.system.Os;

import com.benjamin_loison.adb_clipboard.wrappers.ClipboardManager;
import com.benjamin_loison.adb_clipboard.wrappers.ServiceManager;

public class Main {
    public static final int SHELL_UID = 2000;

    public static void main(String[] args) {
        // Set the UID of the `root` user to that of the `shell` user
        if (Os.getuid() == 0) {
            try {
                Os.seteuid(SHELL_UID);
            } catch (ErrnoException ignored) {
            }
        }
        ClipboardManager manager = ServiceManager.getClipboardManager();
        if (manager == null) {
            System.out.println("Failed to get ClipboardManager");
            return;
        }
        if (args.length == 0) {
            System.out.println(manager.getText());
        } else {
            StringBuilder text = new StringBuilder();
            for (String arg : args) {
                text.append(arg).append(" ");
            }
            boolean res = manager.setText(text.substring(0, text.length() - 1));
            if (res) {
                System.out.println("Succeeded");
            } else {
                System.out.println("Failed");
            }
        }
    }
}
