pipeline {
    agent any
    stages {
        stage('编译测试') {
            steps {
                sh 'mvn -B -Pdev clean test'
            }
        }
        stage('打包') {
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
    post {
        success {
            dingtalk (
                robot: 'df5ba78f-ef02-411f-8da5-bd329a6d9974',
                type: 'ACTION_CARD',
                title: currentBuild.result,
                text: [
                    projectName,
                    number,
                    currentBuild.result,
                    durationString
                ]
            )
        }
        failure {
            dingtalk (
                robot: 'df5ba78f-ef02-411f-8da5-bd329a6d9974',
                type: 'ACTION_CARD',
                title: currentBuild.result,
                text: [
                    projectName,
                    number,
                    currentBuild.result,
                    durationString
                ]
            )
        }
    }
}