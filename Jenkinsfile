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
        stage('Deliver') {
            steps {
                sh 'sh ./shell/dev.sh'
            }
        }
    }
    post {
        always {
            emailext body: """来自Jenkins的邮件""",
                     subject: "测试邮箱",
                     to: "1050730542@qq.com"
        }
    }
}