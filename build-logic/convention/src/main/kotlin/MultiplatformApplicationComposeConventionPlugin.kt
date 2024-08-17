import com.android.build.api.dsl.ApplicationExtension
import com.rafael.gradle.configureComposeMultiplatform
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class MultiplatformApplicationComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("org.jetbrains.kotlin.plugin.compose")
                apply("org.jetbrains.compose")
            }

            extensions.configure<ApplicationExtension> {
                configureComposeMultiplatform()
            }
        }
    }
}
