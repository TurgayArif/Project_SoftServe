pipeline {
    agent any
    tools {
        maven "MAVEN"
        jdk "JDK"
    }
    stages {
        stage('Initialize'){
            steps{
                echo "PATH = ${M2_HOME}/bin:${PATH}"
                echo "M2_HOME = /opt/maven"
            }
        }
        stage('Build') {
            steps {
                dir("/var/lib/jenkins/workspace/tikoj/easyrest") {
                sh 'mvn -B -DskipTests clean package'
                }
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }
     }
}