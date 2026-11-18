pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        // 柔光玻璃材质库（独立仓库，tag 触发 JitPack 构建）
        maven("https://jitpack.io") {
            content { includeGroupByRegex("com\\.github\\.HaoZai000.*") }
        }
    }
}

rootProject.name = "nexioui"
include(":nexioui")

// 本地存在 softglass 源码时直接编源码，改库即改即测；没有源码时回落到 JitPack 坐标。
val softglassSource = file("../softglass")
if (softglassSource.resolve("build.gradle.kts").exists()) {
    includeBuild(softglassSource) {
        dependencySubstitution {
            substitute(module("com.github.HaoZai000.SoftGlass:softglass")).using(project(":"))
        }
    }
}
