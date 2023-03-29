pipeline {
    agent {
        docker {
            image 'maven:3.8.6-openjdk-11'
            args '-v $HOME/.m2:/root/.m2'
        }
    }
    stages {
        stage('拉取代码') {
            steps {
                echo '开始拉取代码 ..'
                checkout([$class: 'GitSCM', branches: [[name: '*/master']], extensions: [], userRemoteConfigs: [[credentialsId: '4b52c3d1-2653-4537-be52-58ea5a408a55', url: 'https://gitee.com/zerolinck/Easy-Management.git']]])
                echo '代码拉取成功'
            }
        }
        stage('编译') {
            steps {
                sh 'mvn -B -DskipTests -Pdev clean compile'
            }
        }
        stage('测试') {
            steps {
                sh 'docker build -f db/Dockerfile -t management-mysql:test db'
                sh 'docker run -d --name management-mysql-test -p 3306:3306 management-mysql:test'
                sh 'mvn -B test'
                sh 'docker rm -f management-mysql-test'
                sh 'docker rmi management-mysql:test'
            }
        }
        stage('打包') {
            steps {
                sh 'mvn -B -DskipTests -Pdev package'
            }
        }
        stage('制品') {
            steps {
                archiveArtifacts artifacts: 'target/management-*.jar', followSymlinks: false
            }
        }
    }
    post {
        success {
            dingtalk (
                robot: '79d3681d-dc2c-4784-a8b4-381e9581c5d1',
                type: 'ACTION_CARD',
                title: currentBuild.result,
                text: [
                    '### ' + currentBuild.fullProjectName,
                    '* 运行：' + currentBuild.displayName,
                    '* 结果：' + currentBuild.result,
                    '* 描述：' + currentBuild.description,
                    '* 耗时：' + currentBuild.durationString
                ]
            )
        }
        failure {
            dingtalk (
                robot: '79d3681d-dc2c-4784-a8b4-381e9581c5d1',
                type: 'ACTION_CARD',
                title: currentBuild.result,
                text: [
                    '### ' + currentBuild.fullProjectName,
                    '* 运行：' + currentBuild.displayName,
                    '* 结果：' + currentBuild.result,
                    '* 描述：' + currentBuild.description,
                    '* 耗时：' + currentBuild.durationString
                ]
            )
        }
    }
}