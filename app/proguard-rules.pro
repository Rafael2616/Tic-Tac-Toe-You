# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

# Rules for Room DB
-keepattributes RoomDatabase, Entity, PrimaryKey, Dao, TypeConverters

-keep class * extends androidx.room.RoomDatabase
-keep class * extends androidx.room.RoomDatabase.Builder
-keep class * extends androidx.room.migration.Migration
-keep class * extends androidx.room.InvalidationTracker
-keep class * extends androidx.room.RoomSQLiteQuery
-keep class * extends androidx.room.util.CursorUtil
-keep class * extends androidx.room.RoomSQLiteOpenHelper
-keep class * extends androidx.room.util.TableInfo
-keep class com.rafael.tictactoe.settings.data.database.SettingsDatabase
-keep class com.rafael.tictactoe.settings.data.database.Migrations
-keep class com.rafael.tictactoe.settings.data.database.Migrations.Migration1To2

-keepclassmembers class com.rafael.tictactoe.settings.data.database.Migrations.Migration1To2 {
    public void migrate(androidx.sqlite.db.SupportSQLiteDatabase);
}
-keepclassmembers class com.rafael.tictactoe.settings.data.database.Migrations.Migration1To2 {
    public static final java.lang.String addAppVersion;
    public static final java.lang.String addSelectedWinnerLineColor;
    public static final java.lang.String addSelectedXColor;
    public static final java.lang.String addSelectedCircleColor;
}
# Add rules to improve performance
-keepclassmembers class * extends androidx.room.RoomDatabase {
    public static void destroyInstance(android.content.Context, java.lang.String);
}
-keepclasseswithmembers class * extends androidx.room.RoomDatabase {
    public static void clearAllTables(android.content.Context, java.lang.String);
}
-keepclassmembers class * extends androidx.room.RoomDatabase {
    public static java.lang.String[] getDatabaseNames();
}
-keepclassmembers class * extends androidx.compose.runtime.Composable {
    public static java.lang.Class[] getTopLevelComposables();
}

# Retrofit
-keep class retrofit2.** { *; }
-keepattributes Signature, InnerClasses, EnclosingMethod
-keepattributes Exceptions