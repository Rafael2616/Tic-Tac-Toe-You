import com.android.build.api.dsl.LibraryExtension
import com.rafael.gradle.configureKotlinMultiplatform
import com.rafael.gradle.kotlinMultiplatformExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl

class MultiplatformLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.multiplatform")
            }

            extensions.configure<LibraryExtension> {
                configureKotlinMultiplatform(this)
                kotlinMultiplatformExtension {
                    @OptIn(ExperimentalWasmDsl::class)
                    wasmJs {
                        binaries.library()
                    }
                }
            }
        }
    }
}
