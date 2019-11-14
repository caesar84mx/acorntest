plugins {
	id("com.acornui.app")
}

kotlin {
	sourceSets {
		commonMain {
			dependencies {
				implementation("com.acornui:acornui-core")
				runtimeOnly("com.acornui.skins:basic")
			}
		}
	}
}

tasks.runJvm {
	main = "userDataInput.jvm.UserDataInputJvmAppKt"
}