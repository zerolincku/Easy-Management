pipeline {
    agent any
    stages {
        stage('测试') {
            steps {
                sh 'mvn -B -Pdev clean test'
            }
        }
        stage('编译') {
            steps {
                sh 'mvn -B -DskipTests -Pdev package'
            }
        }
        stage('部署') {
            steps {
                sh 'sh ./shell/dev.sh'
            }
        }
        stage('制品') {
            steps {
                archiveArtifacts artifacts: 'target/management-0.0.1.jar', followSymlinks: false
            }
        }
    }
}