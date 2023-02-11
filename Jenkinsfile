pipeline {
    agent any
    tools {
    	maven 'Maven'
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

