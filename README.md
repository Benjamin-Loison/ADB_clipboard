Push tool to Android device:

```bash
adb push main.jar /data/local/tmp/
```

Get the clipboard content:

```bash
app_process64 -Djava.class.path=/data/local/tmp/main.jar /system/bin com.benjamin_loison.adb_clipboard.Main
```

Set clipboard content:

```bash
app_process64 -Djava.class.path=/data/local/tmp/main.jar /system/bin com.benjamin_loison.adb_clipboard.Main "hello world"
```

To compile:

```bash
./gradlew assembleDebug
```

`main.jar` in `adb_clipboard/src/main/assets/`.
