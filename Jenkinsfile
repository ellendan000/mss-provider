pipeline {
    agent none
    environment {
        PATH = '/usr/local/bin:/usr/bin:/bin:/usr/sbin:/sbin'
    }
    stages {
        stage('Pmd') {
            agent {
                docker {
                    image 'gradle:5.3.0-jdk8'
                }
            }
            steps {
                sh 'gradle pmdMain pmdTest --info'
            }
        }
        stage('Test') {
            agent {
                docker {
                    image 'gradle:5.3.0-jdk8'
                }
            }
            steps {
                sh 'gradle clean test'
            }
        }
        stage('Build') {
            agent {
                docker {
                    image 'gradle:5.3.0-jdk8'
                }
            }
            steps {
                sh 'gradle clean build --info'
            }
        }
        stage('Dockerize') {
            agent any
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
