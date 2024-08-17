import com.android.build.api.dsl.ApplicationExtension
import com.rafael.gradle.configureKotlinMultiplatform
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class MultiplatformApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.multiplatform")
            }
            extensions.configure<ApplicationExtension> {
                configureKotlinMultiplatform(this)
            }
        }
    }
}
