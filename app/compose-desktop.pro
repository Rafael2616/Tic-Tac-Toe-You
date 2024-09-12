-keep public class com.google.common.** { public *; }
-dontwarn com.google.common.**

-keep class androidx.sqlite.** { *; }
-keep class androidx.room.** { *; }
-dontwarn androidx.room.**
-keep class com.sun.jna.** { *; }
-keep class * implements com.sun.jna.** { *; }
