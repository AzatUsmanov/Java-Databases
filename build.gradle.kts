plugins {
    id("java")
}

group = "employees"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.apache.logging.log4j:log4j-core:2.20.0")
    implementation("org.postgresql:postgresql:42.6.0")
    testImplementation("junit:junit:4.13.2")
    implementation("org.hibernate.orm:hibernate-core:5.2.4.Final")
    implementation("org.hibernate:hibernate-entitymanager:6.0.0.Alpha7")
    implementation("org.hibernate.validator:hibernate-validator:8.0.0.Final")
    implementation("org.mybatis:mybatis:3.5.13")
    implementation("org.javassist:javassist:3.29.2-GA")
}

tasks.getByName<Test>("test") {
    useJUnit()
}