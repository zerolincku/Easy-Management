pipeline {
    agent any
    tools {
        maven "maven"
    }
    stages {
        stage('Build') {
            steps {
                sh 'mvn -B -DskipTests clean package'
            }
        }
        stage('Deploy') {
            steps {
                sh 'sh ./shell/dev.sh'
            }
        }
    }
    post {
        success {
            emailext (
                subject: "测试主题",
                body: """测试内容""",
                to: "lck@shengtex.com",
                from: "lck@shengtex.com"
            )
        }
        failure {
            emailext (
                subject: "测试主题",
                body: """测试内容""",
                to: "lck@shengtex.com",
                from: "lck@shengtex.com"
            )
        }
    }
}