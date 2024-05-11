import com.android.build.api.dsl.ApplicationExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType
import com.diffplug.gradle.spotless.SpotlessExtension
import com.rafael.gradle.libs
import org.gradle.kotlin.dsl.configure

class AndroidSpotlessConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("com.diffplug.spotless")

             extensions.configure<SpotlessExtension> {
                kotlin {
                    ktlint()
                        .setEditorConfigPath(rootProject.file(".editorconfig"))
                        .customRuleSets(
                            listOf(
                                libs.findLibrary("ktlint-compose-rules").get().get().toString(),
                            ),
                        )
                    target("src/main/kotlin/**/*.kt", "src/androidTest/java/**/*.kt")
                }
                kotlinGradle {
                    ktlint()
                        .setEditorConfigPath(rootProject.file(".editorconfig"))
                    target("*.gradle.kts")
                }
            }
        }
    }
}
