plugins {
	`java-library`
	id("io.papermc.paperweight.userdev") version "1.3.1"
}

dependencies {
	paperDevBundle("1.18.2-R0.1-SNAPSHOT")

	compileOnly("net.civmc.civmodcore:CivModCore:2.4.1:dev-all")
	compileOnly("eu.endercentral.crazy_advancements:CrazyAdvancementsAPI:2.1.9")
}
