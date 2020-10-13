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
        always{
            //always部分 pipeline运行结果为任何状态都运行
            emailext body:
                '''<body leftmargin="8" marginwidth="0" topmargin="8" marginheight="4" offset="0">
                        <table width="95%" cellpadding="0" cellspacing="0"  style="font-size: 11pt; font-family: Tahoma, Arial, Helvetica, sans-serif">
                            <tr>
                                <td>
                                    <b><font color="#0B610B">Build Summary</font></b>
                                    <hr size="2" width="100%" align="center" />
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <ul>
                                        <li>Project Name: ${PROJECT_NAME}</li>
                                        <li>Build Number: # ${BUILD_NUMBER}</li>
                                        <li>Build Cause: ${CAUSE}</li>
                                        <li>Build Status: ${BUILD_STATUS}</li>
                                        <li>Build Log: <a href="${BUILD_URL}console">${BUILD_URL}console</a></li>
                                        <li>Build Url: <a href="${BUILD_URL}">${BUILD_URL}</a></li>
                                        <li>Project Url: <a href="${PROJECT_URL}">${PROJECT_URL}</a></li>
                                    </ul>
                                </td>
                            </tr>
                        </table>
                   </body>''',
            subject: '[Jenkins Build Notification] ${PROJECT_NAME} - Build # ${BUILD_NUMBER} - ${BUILD_STATUS}!',
            to: 'lck@shengtex.com;'
        }
        success {
            echo 'success'
        }
        failure {
            echo 'failure'
        }
        unstable {
            echo 'unstable'
        }
        aborted {
            echo 'aborted'
        }
        changed {
            echo 'changed'
        }
    }
}