import com.android.build.api.dsl.ApplicationExtension
import com.rafael.gradle.configureComposeAndroid
import com.rafael.gradle.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidLibraryComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("org.jetbrains.kotlin.plugin.compose")

            extensions.configure<ApplicationExtension> {
                configureComposeAndroid(this)
            }
        }
    }
}
