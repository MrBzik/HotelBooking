
import org.gradle.api.artifacts.dsl.DependencyHandler

object Dependencies {

    const val hiltAndroid = "com.google.dagger:hilt-android${Versions.hilt}"
    const val hiltCompiler = "com.google.dagger:hilt-android-compiler${Versions.hilt}"

    const val ktorClientCore = "io.ktor:ktor-client-core:${Versions.ktor}"
    const val ktorClientCIO = "io.ktor:ktor-client-cio:${Versions.ktor}"
    const val ktorClientContentNegotiation = "io.ktor:ktor-client-content-negotiation:${Versions.ktor}"
    const val ktorJson = "io.ktor:ktor-serialization-kotlinx-json:${Versions.ktor}"

    const val navHostFragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navhost}"
    const val navHostUi = "androidx.navigation:navigation-ui-ktx:${Versions.navhost}"

    const val roomRuntime = "androidx.room:room-runtime:${Versions.room}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
    const val roomPaging = "androidx.room:room-paging:${Versions.room}"

}

fun DependencyHandler.ktor(){
    implementation(Dependencies.ktorClientCore)
    implementation(Dependencies.ktorClientCIO)
    implementation(Dependencies.ktorClientContentNegotiation)
    implementation(Dependencies.ktorJson)
}

fun DependencyHandler.hilt(){
    implementation(Dependencies.hiltAndroid)
    kapt(Dependencies.hiltCompiler)
}

fun DependencyHandler.room(){
    implementation(Dependencies.roomRuntime)
    implementation(Dependencies.roomPaging)
    kapt(Dependencies.roomCompiler)
}