pipeline {
    agent any
    tools {
            maven 'maven'
    }
    stages {
        stage('Build') {
            steps {
                sh 'mvn -B -DskipTests clean package'
            }
        }
        stage('Deploy') {
            steps {
                sh './shell/dev.sh'
            }
        }
    }
}