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
        stages('Product') {
            steps {
                archiveArtifacts artifacts: 'target/management-0.0.1.jar', followSymlinks: false
            }
        }
    }
}