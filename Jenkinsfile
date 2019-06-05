pipeline {
    agent {
        docker {
            image 'gradle:5.3.0-jdk8'
            args '-v /tmp/gradle-caches:/home/gradle/.gradle/caches'
        }
    }
    environment {
        PATH = '/usr/local/bin:/usr/bin:/bin:/usr/sbin:/sbin'
    }
    stages {
        stage('Pmd') {
            steps {
                sh 'gradle pmdMain pmdTest -q'
            }
        }
        stage('Test') {
            steps {
                sh 'gradle clean test'
            }
        }
        stage('Build') {
            steps {
                sh 'gradle clean build --info'
            }
        }
        stage('Dockerize') {
            steps {
                withCredentials([usernamePassword(credentialsId: '2bb1efed-9b18-49cb-b2a9-ceb831ccf74f', passwordVariable: 'pass', usernameVariable: 'user')]) {
                    sh 'docker login -u ${user} -p ${pass} '
                }
                sh 'docker build -t shadowpluto/mss --no-cache .'
                sh 'docker push shadowpluto/mss'
            }
        }
    }
}
