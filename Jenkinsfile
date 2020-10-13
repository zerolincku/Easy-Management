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
            script{
                emailext body: """
                            <!DOCTYPE html>
                            <html>
                            <head>
                            <meta charset="UTF-8">
                            </head>
                            <body leftmargin="8" marginwidth="0" topmargin="8" marginheight="4" offset="0">
                                <img src="http://192.168.1.200:8080/static/0eef74bf/images/headshot.png">
                                <table width="95%" cellpadding="0" cellspacing="0" style="font-size: 11pt; font-family: Tahoma, Arial, Helvetica, sans-serif">
                                    <tr>
                                        <td><br />
                                            <b><font color="#0B610B">构建信息</font></b>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <ul>
                                                <li>项目名称：${JOB_NAME}</li>
                                                <li>构建编号：${BUILD_ID}</li>
                                                <li>构建状态: ${status} </li>
                                                <li>项目地址：<a href="${BUILD_URL}">${BUILD_URL}</a></li>
                                                <li>构建日志：<a href="${BUILD_URL}console">${BUILD_URL}console</a></li>
                                            </ul>
                                        </td>
                                    </tr>
                                    <tr>
                                </table>
                            </body>
                            </html>  """,
                            subject: "Jenkins-${JOB_NAME}项目构建信息 ",
                            to: "1050730542@qq.com"
            }
        }
    }
}