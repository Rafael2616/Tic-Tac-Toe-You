import com.android.build.api.dsl.LibraryExtension
import com.rafael.gradle.configureComposeAndroid
import com.rafael.gradle.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.withType

class AndroidComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("org.jetbrains.kotlin.plugin.compose")

            dependencies {
                "platform"(libs.findLibrary("androidx-compose-bom").get())
                "implementation"(libs.findLibrary("androidx-compose-material3").get())
                "implementation"(libs.findLibrary("androidx-compose-animation").get())
                "implementation"(libs.findLibrary("androidx-compose-ui").get())
                "implementation"(libs.findLibrary("androidx-compose-foundation").get())
                "implementation"(libs.findLibrary("androidx-compose-runtime").get())
                "implementation"(libs.findLibrary("androidx-compose-material-icons-extended").get())
            }
            extensions.configure<LibraryExtension> {
                configureComposeAndroid(this)
            }
        }
    }
}
