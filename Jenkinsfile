pipeline {
    agent { docker 'gradle:5.3.0-jdk8' }
    stages {
        stage('build') {
            steps {
                sh 'gradle clean build --info'
            }
        }
    }
}
