import Vs.appCompat
import Vs.arch_core_testingv
import Vs.constrainLayout
import Vs.core_testing
import Vs.glide
import Vs.javarx2_androidv
import Vs.javarx2v
import Vs.junitV
import Vs.koinv
import Vs.kotlin
import Vs.kotlin_corutinesv
import Vs.lifeCycle
import Vs.materialGoogleV
import Vs.mockito_core
import Vs.mockito_core_testing
import Vs.nav_versionv
import Vs.paging_libv
import Vs.paging_rxjava2v
import Vs.retrofit2_adapter_javarx2v
import Vs.retrofit2v
import Vs.roomv
import Vs.rxkotlinv
import Vs.test_espresso_contribv
import Vs.test_espressov
import Vs.test_orchestratorv
import Vs.test_rulesv
import Vs.test_runnerv
import Vs.viewPagerv

object Vs {
    val glide = "4.9.0"
    val kotlin = "1.3.11"
    val appCompat = "1.0.0-beta01"
    val lifeCycle = "2.0.0-beta01"
    val constrainLayout = "1.1.3"
    val junitV = "4.12"
    val core_testing = "2.0.0-beta01"
    val mockito_core ="2.10.0"
    val mockito_core_testing = "2.23.4"

    val arch_core_testingv = "2.0.0"


    val test_runnerv = "1.1.0-alpha4"
    val test_orchestratorv = "1.1.0"

    val test_espressov = "3.1.0-alpha4"


    val test_rulesv = "1.1.0-alpha4"
    val test_espresso_contribv = "3.1.0-alpha4"


    //room
    val roomv = "2.2.1"

    //retrofit
    val retrofit2v = "2.0.2"
    val retrofit2_adapter_javarx2v = "2.5.0"
    // RxJava
    val javarx2v = "2.2.19"
    val javarx2_androidv = "2.1.1"

    //koin
    val koinv = "1.0.2"

    //pagedList
    val paging_libv = "2.1.1"

    val nav_versionv = "2.2.0"


    //coroutines
    val kotlin_corutinesv = "1.0.1"
    val paging_rxjava2v = "1.0.0-alpha1"

    //kotlin rx
    val rxkotlinv = "2.3.0"

    val viewPagerv = "1.0.0"

    val materialGoogleV = "1.1.0-beta01"
}

object Dp {
    val glideLib = "com.github.bumptech.glide:glide:$glide"
    val glideCompilerLib = "com.github.bumptech.glide:compiler:$glide"
    val kotlinLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlin"
    val appCompatLib = "androidx.appcompat:appcompat:$appCompat"

    val lifeCycleLib = "androidx.lifecycle:lifecycle-extensions:$lifeCycle"
    val constrainLayoutLib = "androidx.constraintlayout:constraintlayout:$constrainLayout"
    val junitLib = "junit:junit:$junitV"
    val core_testingLib = "androidx.arch.core:core-testing:$core_testing"
    val mockito_coreLib = "org.mockito:mockito-core:$mockito_core"
    val arch_core_testing = "androidx.arch.core:core-testing:$arch_core_testingv"
    val mockito_testing = "org.mockito:mockito-android:$mockito_core_testing"


    val test_runner = "androidx.test:runner:$test_runnerv"
    val test_orchestrator = "androidx.test:orchestrator:$test_orchestratorv"

    val test_espresso = "androidx.test.espresso:espresso-core:$test_espressov"


    val test_rules = "androidx.test:rules:$test_rulesv"
    val test_espresso_contrib = "androidx.test.espresso:espresso-contrib:$test_espresso_contribv"




    //room
    val room_runtime = "androidx.room:room-runtime:$roomv"
    val room_compiler = "androidx.room:room-compiler:$roomv"
    val room_rxjava2 = "androidx.room:room-rxjava2:$roomv"

    //retrofit
    val retrofit2 = "com.squareup.retrofit2:retrofit:$retrofit2v"
    val retrofit2_gson = "com.squareup.retrofit2:converter-gson:$retrofit2v"
    val retrofit2_adapter_javarx2 = "com.squareup.retrofit2:adapter-rxjava2:$retrofit2_adapter_javarx2v"
    // RxJava
    val javarx2 = "io.reactivex.rxjava2:rxjava:$javarx2v"
    val javarx2_android = "io.reactivex.rxjava2:rxandroid:$javarx2_androidv"

    //koin
    val koin_viewmodel = "org.koin:koin-android-viewmodel:$koinv"
    val koin_test = "org.koin:koin-test:$koinv"

    //pagedList
    val paging_lib = "androidx.paging:paging-runtime:$paging_libv"
    val paging_lib_rx = "androidx.paging:paging-rxjava2:$paging_libv"


    //val navigation_fragment = "android.arch.navigation:navigation-fragment:$nav_versionv" // For Kotlin use navigation-fragment-ktx
    //val navigation_ui = "android.arch.navigation:navigation-ui:$nav_versionv" // For Kotlin use navigation-ui-ktx

    val navigation_fragment = "androidx.navigation:navigation-fragment-ktx:$$nav_versionv"
    val navigation_ui = "androidx.navigation:navigation-ui-ktx:$$nav_versionv"


    //coroutines
    val kotlin_coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$kotlin_corutinesv"
    val paging_rxjava2 = "android.arch.paging:rxjava2:$paging_rxjava2v"

    //kotlin rx
    val rxkotlin = "io.reactivex.rxjava2:rxkotlin:$rxkotlinv"

    val viewPager = "androidx.viewpager2:viewpager2:$viewPagerv"

    val materialGoogle = "com.google.android.material:material:$materialGoogleV"

}