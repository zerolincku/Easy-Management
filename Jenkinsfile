pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                sh 'mvn -B -DskipTests -Pdev clean package'
            }
        }
        stage('Deploy') {
            steps {
                sh 'sh ./shell/dev.sh'
            }
        }
    }
}