pipeline {
    agent any
    tools {
    	maven 'Maven'
    	jdk 'jdk17'
    }
    stages {
        stage('Build') {
            steps {
                sh 'mvn -B -DskipTests clean package'
                }
            }
        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }
     }
}

